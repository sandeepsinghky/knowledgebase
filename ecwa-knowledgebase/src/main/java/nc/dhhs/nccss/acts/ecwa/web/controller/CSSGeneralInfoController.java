package nc.dhhs.nccss.acts.ecwa.web.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class CSSGeneralInfoController extends BasicAnnotatedFormController
{

	/**
	 * @return
	 */
	@RequestMapping("/loggedInHome.htm")
	public String getLoggedIndex(final Principal principal)
	{
		if (null == principal) return "redirect:/index.jsp";

		return AppConstants.ECWA_LOGGEDIN_HOME;
	}
	
	@RequestMapping("/cssHome.htm")
	public String getLoggedHome(final Principal principal)
	{
		if (null == principal) 
			return "redirect:/login.htm";
		else
			return "redirect:/cswp/user/caseApplicationList.htm";
	}
	
}
