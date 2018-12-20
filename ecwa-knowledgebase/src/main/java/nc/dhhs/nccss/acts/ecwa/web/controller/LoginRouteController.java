package nc.dhhs.nccss.acts.ecwa.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecwa.beans.EcwaUser;
import nc.dhhs.nccss.acts.ecwa.web.EcwaSession;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecwa.web.service.UserService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * LoginRoute Controller acts as a gateway for the application access
 * redirecting to the appropriate landing page after successful user
 * authentication and authorization.
 * 
 * User roles & permissions (if at all) are retrieved from the database and
 * authorities are set here for the application access.
 * 
 * New case application creation or forward to edit pending application logic is
 * set here.
 * 
 * @author Vijay Peddapalli
 *
 */
@Controller
@RequestMapping("/loginRoute.htm")
public class LoginRouteController extends BasicAnnotatedFormController
{

	protected final Logger			logger	= Logger.getLogger(getClass());

	@Autowired
	private UserService				userService;

	@Autowired
	private CaseApplicationService	caseApplService;

	/**
	 * <p>
	 * Redirects user to correct page once logged in
	 * </p>
	 * 
	 * @param request
	 *            current request
	 * @param modelMap
	 *            model data
	 * @return page to load
	 */

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request, Model model)
	{

		String returnPage = "";
		// get user from the authenticated principal
		String loginId = getUserId();

		// get the loaded Ecwa User from the authenticated NCID serviceUserResponse.
		EcwaUser user = userService.getUser(loginId);

		List<GrantedAuthority> authorities = null;
		authorities = new ArrayList<GrantedAuthority>();

		if (user.getUserType() != null && (user.getUserType().equals("S") || user.getUserType().equals("L")))
		{

			try
			{

				// see if the user is exists in acts database
				Map<String, Object> appResult = caseApplService.getCaseApplicationByNcID(user.getLoginName());
				if (((String) appResult.get("RETURNCODE")).toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					logger.info("*************in LOGIN ROUTE USER EXISTS IF **********");

					String dataFields = (String) appResult.get("DATAFIELDS").toString().trim();

					if ((dataFields).substring(148, 195) != null)
					{
						user.setWkrNm((dataFields).substring(148, 195));
					}

					if ((dataFields).substring(195, 198) != null)
					{
						user.setWkrCntyCode((dataFields).substring(195, 198));
					}

					if ((dataFields).substring(198, 218) != null)
					{
						user.setWkrCntyNm((dataFields).substring(198, 218));
					}

					if ((dataFields).substring(218, 219) != null)
					{
						user.setWkrIsAdmin((dataFields).substring(218, 219).trim());
					}
					returnPage = "redirect:/cswp/user/caseApplicationList.htm";

					logger.info("*************UserType " + user.getUserType());
					logger.info("*************UserName " + user.getFirstName());
					logger.info("*************UserLName " + user.getLastName());
					logger.info("*************Email " + user.getEmail());
					logger.info("*************UserId " + user.getLoginName());
					logger.info("*************Worker County Code" + user.getWkrCntyCode());
					logger.info("*************Worker County Name" + user.getWkrCntyNm());
					logger.info("*************Worker Admin? " + user.getWkrIsAdmin());

					logger.info("redirecting to application list");

					// then set users usage details in the database, expecting new table

					// setUsageDetails(request, user, new Usage());

					setUsageDetails(request, user);

					// hard coding user permissions here, will get from DB ones we have tables ready.
					// Goal is to have user drive his permissions once he agrees upon the system rights.

					String perm = "VIEW_EDIT_ALL_USERS";

					// redirect to the login if the credentials are bad or user had not
					// done self activation.
					// return "redirect:/login.htm";

					authorities.add(new SimpleGrantedAuthority("PERMISSION_" + perm));

					// user is now authorized, next task is to set usage details:saving his IP address, login time etc.
					//setUsageDetails(request, user, new Usage());

					EcwaSession ecwaSession = getEcwaSession(request);

					// change authenticated principal by setting with the new authenticated token passing principal, credentials and authorities
					SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), SecurityContextHolder.getContext().getAuthentication().getCredentials(), authorities));

				}
				else
				{
					String returnCode = (String) appResult.get("RETURNCODE");
					logger.info("**Return code from worker validation store procedure " + returnCode);
					if (returnCode.toString().substring(0, 4).trim().equals(AppConstants.APP_UNAVAILABLE_CODE))
					{
						model.addAttribute("message", AppConstants.APP_UNAVAILABLE_MSG);
					}
					else if (returnCode.toString().substring(0, 4).trim().equals(AppConstants.USER_NOT_AUTHORIZED_CODE))
					{
						model.addAttribute("message", AppConstants.USER_NOT_AUTHORIZED_MSG);
					}
					else
					{
						throw new Exception("LoginRouteController: get: worker validation. Return code: " + returnCode);
					}
					returnPage = "redirect:/login.htm?login_error=1";
				}
			}
			catch (Exception e)
			{
				logger.error(e.getMessage());
				model.addAttribute("message", AppConstants.APPLICATION_ERROR);
				ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
				request.setAttribute("errorBean", error);
				error = null;
				return "forward:/apperror.htm";
			}
		}
		else
		{
			logger.info("NCID used was not state employee NCID.");
			model.addAttribute("message", AppConstants.NCID_PUBLIC_ERROR);
			returnPage = "redirect:/login.htm?login_error=1";
		}

		logger.info("before final return");

		return returnPage;

	}

	/**
	 * Sets Usage details for the login user. Waiting on separate table to
	 * maintain usage details
	 * 
	 * @param request
	 * @param user
	 * @param usage
	 * @throws Exception
	 */
	//private void setUsageDetails(HttpServletRequest request, EcwaUser user, Usage uage)

	private void setUsageDetails(HttpServletRequest request, EcwaUser user)
	{

		//String userAgent = request.getHeader("user-agent");
		// user.setLoginTime(new Date());
		// user.setIpAddress(request.getRemoteAddr());
		// user.setBrowserAgent(userAgent == null ? "" : userAgent);

		/*
		 * String userAgent = request.getHeader("user-agent");
		 * usage.setLoginTime(new Date()); usage.setUserId(user.getUserId());
		 * usage.setLoginName(user.getLoginName());
		 * usage.setLastName(user.getLastName());
		 * usage.setFirstName(user.getFirstName());
		 * usage.setAppName(ECOAApp.getName());
		 * usage.setIpAddress(request.getRemoteAddr());
		 * usage.setBrowserAgent(userAgent == null ? "" : userAgent);
		 */

		HttpSession session = request.getSession();

		session.setAttribute("userFirstName", user.getFirstName());
		session.setAttribute("userLastName", user.getLastName());
		session.setAttribute("userLoginName", user.getLoginName());
		session.setAttribute("userEmail", user.getEmail());
		session.setAttribute("wrkrName", user.getWkrNm());
		session.setAttribute("wrkrIsAdmin", user.getWkrIsAdmin());
		session.setAttribute("wrkrCnty", user.getWkrCntyCode());
		logger.info(".....usage details has been set..." + user.getFirstName());
	}

}
