package nc.dhhs.nccss.acts.ecwa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nc.dhhs.nccss.acts.ecwa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecwa.web.util.EmailManager;

/**
 * @author Vijay Peddapalli
 *
 */
@Controller
public class ErrorController extends BasicAnnotatedFormController
{

	@Autowired
	protected EmailManager emailManager;

	/**
	 * <p>
	 * Renders error page
	 * </p>
	 * 
	 * @return page to load
	 */
	@RequestMapping("/apperror.htm")
	public String processApplerror(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		ErrorDescriptor errorBean = (ErrorDescriptor) request.getAttribute("errorBean");
		errorBean.setUserId((String) session.getAttribute("userLoginName"));
		try
		{
			emailManager.sendEmail(WebsiteConfiguration.getEmailNotify(), AppConstants.MAIL_FROM, AppConstants.MAIL_ERROR_SUBJECT, errorBean.getEmailBody());
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
		// model.addAttribute("message", AppConstants.APPLICATION_ERROR);
		model.addAttribute("redirectPage", errorBean.getScreenToGo());
		return AppConstants.APP_ERROR;
	}

	@RequestMapping("/error.htm")
	public String processError(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		ErrorDescriptor errorBean = (ErrorDescriptor) request.getAttribute("errorBean");
		errorBean.setUserId((String) session.getAttribute("userLoginName"));
		try
		{
			emailManager.sendEmail(WebsiteConfiguration.getEmailNotify(), AppConstants.MAIL_FROM, AppConstants.MAIL_ERROR_SUBJECT, errorBean.getEmailBody());
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
		return AppConstants.ERROR;
	}

	@RequestMapping("/404.htm")
	public String getPageNotFoundErrPage()
	{
		return AppConstants.PAGE_NOT_FOUND;
	}

	@RequestMapping("/403.htm")
	public String getAccessDeniedErrPage()
	{
		return AppConstants.ACCESS_DENIED;
	}

	@RequestMapping("/500.htm")
	public String getServerErrPage()
	{
		return AppConstants.SERVER_ERROR;
	}
}
