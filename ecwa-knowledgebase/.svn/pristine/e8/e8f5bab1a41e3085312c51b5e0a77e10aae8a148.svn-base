package nc.dhhs.nccss.acts.ecwa.web.controller.applprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecwa.beans.CaseApplicationList;
import nc.dhhs.nccss.acts.ecwa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecwa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecwa.web.service.CodeLookUpService;
import nc.dhhs.nccss.acts.ecwa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class CaseApplicationListController extends BasicAnnotatedFormController
{
	@Autowired
	protected CaseApplicationService	caseApplService;

	@Autowired
	protected CodeLookUpService			codeLookUpService;

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cswp/user/caseApplicationList.htm")
	public String userCaseAplicationList(HttpServletRequest request, Model model)
	{
		logger.info("\n********** IN CaseApplicationListController: userCaseApplicationList**********\n");

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);
		session.setAttribute(AppConstants.SELECTED_APPLICATION, null);
		session.setAttribute(AppConstants.SELECTED_APP_PART, null);
		session.setAttribute("selPartCase", null);
		session.setAttribute(AppConstants.SELECTED_ACTS_PART, null);
		session.setAttribute(AppConstants.IS_APPLICATION_LOCKED, null);
		session.setAttribute(AppConstants.APPLICATION_LOCKED_BY, null);
		session.setAttribute(AppConstants.APPLICATION_LOCKED_BY_NAME, null);
		session.setAttribute(AppConstants.APP_STATUS, null);
		try
		{
			Map<String, Object> appResult = caseApplService.getCaseApplicationByNcID(userId);
			List<CaseApplicationList> tempList = (List<CaseApplicationList>) appResult.get("APPLIST");
			List<CaseApplicationList> appList = new ArrayList<CaseApplicationList>();
			for (CaseApplicationList app : tempList)
			{
				if (app.getApplStatus().equals(AppConstants.APP_STATUS_SUBMIT))
				{
					appList.add(app);
				}
			}
			tempList = null;
			List<CodeLookUp> wrkrCnty = new ArrayList();
			wrkrCnty = codeLookUpService.getGrpCntyLookup((String) session.getAttribute("wrkrCnty"));
			session.setAttribute(AppConstants.WRKR_CNTY, wrkrCnty);
			wrkrCnty = null;
			model.addAttribute("appList", appList);
			model.addAttribute("appStatusSel", AppConstants.APP_STATUS_SUBMIT);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			model.addAttribute("message", AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if (session.getAttribute("selectedApp") != null)
				error.setApplicationId((String) session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/apperror.htm";
		}
		return AppConstants.ECWA_CASE_APPLN_LIST;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cswp/user/caseApplicationList.htm", method = RequestMethod.POST)
	public String processCaseApplication(HttpServletRequest request, Model model)
	{
		logger.info("\n********** IN CaseApplicationListController: processCaseApplication**********\n");

		String actionVal = (String) request.getParameter("action");
		String selectedapp = (String) request.getParameter("selectappl");
		String isApplLocked = (String) request.getParameter(selectedapp + "_locked");
		String applLockedBy = (String) request.getParameter(selectedapp + "_lockedBy");
		String applLockedByName = (String) request.getParameter(selectedapp + "_lockedByName");
		String applStatus = (String) request.getParameter(selectedapp + "_status");
		String screen_to_go = "";
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);
		try
		{
			if (actionVal != null && !actionVal.equals(""))
			{
				Map<String, Object> appResult = caseApplService.getCaseApplicationByNcID(userId);
				List<CaseApplicationList> tempList = (List<CaseApplicationList>) appResult.get("APPLIST");
				List<CaseApplicationList> appList = new ArrayList<CaseApplicationList>();
				for (CaseApplicationList app : tempList)
				{
					if (app.getApplStatus().equals(actionVal))
					{
						appList.add(app);
					}
				}
				tempList = null;
				model.addAttribute("appList", appList);
				model.addAttribute("appStatusSel", actionVal);
				screen_to_go = AppConstants.ECWA_CASE_APPLN_LIST;

			}
			else
			{
				if (selectedapp != null && selectedapp != "")
				{
					session.setAttribute(AppConstants.SELECTED_APPLICATION, selectedapp);
					session.setAttribute(AppConstants.IS_APPLICATION_LOCKED, isApplLocked);
					session.setAttribute(AppConstants.APPLICATION_LOCKED_BY, applLockedBy);
					session.setAttribute(AppConstants.APPLICATION_LOCKED_BY_NAME, applLockedByName);
					session.setAttribute(AppConstants.APP_STATUS, applStatus);

					screen_to_go = "redirect:/cswp/user/caseApplicationPartList.htm";
				}
				else
				{
					screen_to_go = "redirect:/cswp/user/caseApplicationList.htm";
				}
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			model.addAttribute("message", AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if (session.getAttribute("selectedApp") != null)
				error.setApplicationId((String) session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/apperror.htm";
		}

		return screen_to_go;

	}

}
