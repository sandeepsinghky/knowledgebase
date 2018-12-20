package nc.dhhs.nccss.acts.ecwa.web.controller.applprocess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecwa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecwa.beans.CaseActsParticipant;
import nc.dhhs.nccss.acts.ecwa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class CaseActsPartController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseParticipantService casePartService;

	@RequestMapping("/cswp/user/caseActsPartList.htm")
	public String getActsPartSearchList(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		CaseParticipant casePart = new CaseParticipant();
		List<CaseActsParticipant> partList = new ArrayList<CaseActsParticipant>();
		session.setAttribute(AppConstants.SELECTED_ACTS_PART, null);
		String applId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		String partId = (String) session.getAttribute(AppConstants.SELECTED_APP_PART);
		try
		{
			casePart = casePartService.getParticipantByPartId(applId, partId).get(0);
			partList = casePartService.searchActsPaticipant(casePart.getApplicantFNm(), casePart.getApplicantLNm());
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
		model.addAttribute("partList", partList);
		model.addAttribute("casePartName", casePart.getApplicantFNm() + " " + casePart.getApplicantLNm());
		if (!casePart.getBrthDtStr().equals(AppConstants.DEFAULT_DATE))
			model.addAttribute("casePartDOB", casePart.getBrthDtStr());
		model.addAttribute("casePartSSN", casePart.getSsnNb());
		return AppConstants.ECWA_CASE_ACTS_PART_LIST;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cswp/user/caseActsPartList.htm", method = RequestMethod.POST)
	public String processActsPart(HttpServletRequest request, Model model)
	{
		String selectedActsPart = (String) request.getParameter("selectActsPart");
		String screen_to_go = "";
		HttpSession session = request.getSession();
		try
		{
			session.setAttribute(AppConstants.SELECTED_ACTS_PART, selectedActsPart);
			screen_to_go = "redirect:/cswp/user/caseActsPartDetails.htm";

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

		return screen_to_go;

	}

	@RequestMapping("/cswp/user/caseActsPartDetails.htm")
	public String getActsPartDetails(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String applId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		CaseActsParticipant casePart = new CaseActsParticipant();
		List<CaseActsParticipant> partList = new ArrayList<CaseActsParticipant>();
		String partId = (String) session.getAttribute(AppConstants.SELECTED_ACTS_PART);
		String applPartId = (String) session.getAttribute(AppConstants.SELECTED_APP_PART);
		CaseParticipant applPartInfo = new CaseParticipant();

		try
		{
			partList = casePartService.getPaticipantDetails(partId);
			if (partList.size() > 0)
			{
				casePart = (CaseActsParticipant) partList.get(0);
				partList.remove(0);
			}

			applPartInfo = casePartService.getParticipantByPartId(applId, applPartId).get(0);
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
		model.addAttribute("partList", partList);
		model.addAttribute("casePartName", casePart.getPartFullNm());
		if (!casePart.getBrthDtStr().equals(AppConstants.DEFAULT_DATE))
			model.addAttribute("casePartDOB", casePart.getBrthDtStr());
		model.addAttribute("casePartSSN", casePart.getSsnNb());
		model.addAttribute("casePartMPI", casePart.getPartId());
		model.addAttribute("selectedActsPartCase", "");
		model.addAttribute("applPart", applPartInfo);
		return AppConstants.ECWA_CASE_ACTS_PART_DETAILS;
	}

	@RequestMapping(value = "/cswp/user/caseActsPartDetails.htm", method = RequestMethod.POST)
	public String getActsPartCaseDetails(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);
		String screen_to_go = "";
		String action = (String) request.getParameter("action");
		CaseActsParticipant casePart = new CaseActsParticipant();
		List<CaseActsParticipant> partList = new ArrayList<CaseActsParticipant>();

		//participants in the selected case
		List<CaseActsParticipant> casePartList = new ArrayList<CaseActsParticipant>();

		//selected case from the caseActsPartDetails screen
		String selectedActsPartCase = (String) request.getParameter("selectPartCase");

		String appId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		String actsPartId = (String) session.getAttribute(AppConstants.SELECTED_ACTS_PART);
		String applPartId = (String) session.getAttribute(AppConstants.SELECTED_APP_PART);
		try
		{
			if (action.equals("usePart"))
			{
				String rValue = casePartService.createUseActsPart(appId, applPartId, actsPartId, userId, AppConstants.OPERATION_UPDATE);

				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					model.addAttribute("success", "Information is saved successfully.");
				}
				else
				{
					//model.addAttribute("message", "Problem encountered while saving.");
					throw new Exception("CaseActsPartController: getActsPartCaseDetails: usePart. Return code: " + rValue);
				}

				screen_to_go = "redirect:/cswp/user/caseApplicationPartList.htm";
			}
			else
			{
				partList = casePartService.getPaticipantDetails(actsPartId);
				if (partList.size() > 0) { 
					casePart = (CaseActsParticipant) partList.get(0);
					partList.remove(0);
				}

				if (selectedActsPartCase != null && !selectedActsPartCase.equals(""))
				{
					casePartList = casePartService.getCasePaticipantDetails(selectedActsPartCase);
					model.addAttribute("selectedActsPartCase", selectedActsPartCase);
				}
				else
				{
					model.addAttribute("selectedActsPartCase", "");
				}
				model.addAttribute("partList", partList);
				model.addAttribute("casePartName", casePart.getPartFullNm());
				if (!casePart.getBrthDtStr().equals(AppConstants.DEFAULT_DATE))
					model.addAttribute("casePartDOB", casePart.getBrthDtStr());
				model.addAttribute("casePartSSN", casePart.getSsnNb());
				model.addAttribute("casePartMPI", casePart.getPartId());
				model.addAttribute("casePartList", casePartList);
				screen_to_go = AppConstants.ECWA_CASE_ACTS_PART_DETAILS;
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
