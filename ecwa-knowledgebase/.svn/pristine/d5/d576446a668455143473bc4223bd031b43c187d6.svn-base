package nc.dhhs.nccss.acts.ecwa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli, Phani Konuru
 *
 */
@Controller
public class LoginController extends BasicAnnotatedFormController
{

	/**
	 * @return
	 */
	@RequestMapping("/login.htm")
	public String getLogin(HttpServletRequest request, Model model, final Principal principal)
	{
		if (null == principal) {
			return AppConstants.LOGIN;
		}
		else{
			return "redirect:/cswp/user/caseApplicationList.htm";
		}
		
	}
	
	/**
	 * @return
	 */
	@RequestMapping("/loginerr.htm")
	public String errorLogin(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute(AppConstants.LOGIN_ERROR_MSG) != null){
			logger.info((String)session.getAttribute(AppConstants.LOGIN_ERROR_MSG));
			model.addAttribute("message", (String)session.getAttribute(AppConstants.LOGIN_ERROR_MSG));
			session.setAttribute(AppConstants.LOGIN_ERROR_MSG, null);
		}
		return "redirect:/login.htm?login_error=1";
	}
}
