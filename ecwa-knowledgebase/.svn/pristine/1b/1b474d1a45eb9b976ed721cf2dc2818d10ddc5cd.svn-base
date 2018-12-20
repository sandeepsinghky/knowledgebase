package nc.dhhs.nccss.acts.ecwa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import nc.dhhs.nccss.acts.ecwa.web.service.UsageService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * Logout Controller takes care of setting usage related aspects before logging
 * out
 * 
 * @author Vijay Peddapalli
 *
 */
public class LogoutController extends BasicAnnotatedFormController
{
	
	@Autowired
	private UsageService	usageService;

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout.htm")
	public String execute(HttpServletRequest request)
	{
		HttpSession session = request.getSession();

		if (session != null)
		{
			//long usageId = (Long) session.getAttribute("usageId");

			try
			{
				logger.info("logging out .....");

				//Usage usage = getUsageService().getUsage(usageId);
				//usage.setLogoutTime(new Date());
				//getUsageService().updateUsage(usage);
			}
			catch (Exception e)
			{
				logger.error("Error occured while updating Usage" + e.getMessage());
			}

			session.setAttribute(AppConstants.LOGOUT, true);
			
			logger.info("..........log out is successful .....");
		}
		return "redirect:login.htm";
	}

	/**
	 * @return the usageService
	 */
	//public UsageService getUsageService()
	//{
	//	return usageService;
	//}

	/**
	 * @param usageService
	 *            the usageService to set
	 */
	//public void setUsageService(UsageService usageService)
	//{
	//	this.usageService = usageService;
	//}

}
