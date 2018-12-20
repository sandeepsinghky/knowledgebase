package nc.dhhs.nccss.acts.ecwa.web.controller.applprocess;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecwa.beans.CaseApplicationList;
import nc.dhhs.nccss.acts.ecwa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecwa.beans.CaseAppSearch;
import nc.dhhs.nccss.acts.ecwa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class CaseApplicationSearchController extends BasicAnnotatedFormController
{
	@Autowired
	protected CaseApplicationService caseApplService;

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cswp/user/caseApplicationSearch.htm")
	public String getCaseApplictionSearch(HttpServletRequest request, Model model)
	{
		logger.info("\n********** IN CaseApplicationSearchController: getCaseApplictionSearch**********\n");

		model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, (List<CodeLookUp>) request.getServletContext().getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST));
		return AppConstants.ECWA_CASE_APPLN_SEARCH;
	}

	@RequestMapping(value = "/cswp/user/caseApplicationSearch.htm", method = RequestMethod.POST)
	public String processCaseApplicationSearch(HttpServletRequest request, Model model,
			@ModelAttribute("appSearchBean") CaseAppSearch appSearchBean)
	{
		logger.info("\n********** IN CaseApplicationSearchController: processCaseApplicationSearch**********\n");

		HttpSession session = request.getSession();
		try
		{
			appSearchBean.setNcId((String) session.getAttribute("userLoginName"));
			Map<String, Object> appResult = caseApplService.ApplAdvSearch(appSearchBean);
			if (((String) appResult.get("RETURNCODE")).toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				List<CaseApplicationList> appList = (List<CaseApplicationList>) appResult.get("APPLIST");
				model.addAttribute("appList", appList);
			}
			else
			{
				throw new Exception("CaseApplicationSearchController: processCaseApplicationSearch: ApplAdvSearch. Return code: " + ((String) appResult.get("RETURNCODE")).toString());
			}
		}
		catch (Exception e)
		{
			//logger.debug(e.getMessage());
			logger.error(e.getMessage());
			model.addAttribute("message", AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if (session.getAttribute("selectedApp") != null)
				error.setApplicationId((String) session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/apperror.htm";
		}
		model.addAttribute("searchType", "searchadv");
		session.setAttribute(AppConstants.SELECTED_APPLICATION, null);
		session.setAttribute(AppConstants.SELECTED_APP_PART, null);
		session.setAttribute("selPartCase", null);
		session.setAttribute(AppConstants.SELECTED_ACTS_PART, null);
		session.setAttribute(AppConstants.IS_APPLICATION_LOCKED, null);
		session.setAttribute(AppConstants.APPLICATION_LOCKED_BY, null);
		session.setAttribute(AppConstants.APPLICATION_LOCKED_BY_NAME, null);
		session.setAttribute(AppConstants.APP_STATUS, null);
		return AppConstants.ECWA_CASE_APPLN_LIST;

	}

}
