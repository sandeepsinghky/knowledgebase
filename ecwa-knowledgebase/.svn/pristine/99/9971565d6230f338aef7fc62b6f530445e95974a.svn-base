package nc.dhhs.nccss.acts.ecwa.web.controller.applprocess;

import java.util.ArrayList;
import java.util.Date;
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

import nc.dhhs.nccss.acts.ecwa.beans.CaseActsParticipant;
import nc.dhhs.nccss.acts.ecwa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService;
import nc.dhhs.nccss.acts.ecwa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Controller
public class CaseActsCreateController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseParticipantService	casePartService;

	@Autowired
	protected CaseApplicationService	caseApplService;

	/**
	 * @return
	 */
	@RequestMapping("/cswp/user/caseActsCreate.htm")
	public String getCaseActsCreate(HttpServletRequest request, Model model)
	{
		logger.info("\n********** IN CaseActsCreateController: getCaseActsCreate**********\n");

		HttpSession session = request.getSession();
		List<CaseParticipant> partList = new ArrayList<CaseParticipant>();
		List applCases = new ArrayList();
		String applId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		try
		{
			String selPartCase = null;

			partList = casePartService.getParticipantsAll(applId);
			applCases = caseApplService.getApplCases(applId);

			if (session.getAttribute("selPartCase") != null)
			{
				selPartCase = (String) session.getAttribute("selPartCase");
				session.setAttribute("selPartCase", null);
			}

			if (selPartCase != null)
			{
				String[] selPartCaseList = selPartCase.split(",");
				List selPartCaseList1 = new ArrayList();
				for (String val : selPartCaseList)
				{
					selPartCaseList1.add(val);
				}
				session.setAttribute("selPartCase", null);
				for (CaseParticipant part : partList)
				{
					if (selPartCaseList1.contains(part.getPartId()))
					{
						part.setPartSelected(true);
					}
				}
			}

			model.addAttribute("partList", partList);
			model.addAttribute("applId", applId);
			model.addAttribute("applCases", applCases);
			model.addAttribute("selectedCase", "");

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
		return AppConstants.ECWA_CASE_ACTS_CREATE;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cswp/user/caseActsCreate.htm", method = RequestMethod.POST)
	public String CreateCase(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userLoginName");
		String returnCode = AppConstants.OPERATION_SUCCESS;
		String action = (String) request.getParameter("action");
		String applId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		String selCasePartVal = (String) request.getParameter("selCasePartVal");
		String appId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);

		//selected case from the caseActsPartDetails screen
		String selectedCase = (String) request.getParameter("caseId");

		List<CaseActsParticipant> casePartList = new ArrayList<CaseActsParticipant>();

		String screen_to_go = "";

		try
		{
			if (action.equals("createCase"))
			{
				Map<String, String> result = casePartService.createActsCase(appId, "", selCasePartVal, userId, AppConstants.OPERATION_INSERT);
				returnCode = result.get("returnCode").substring(0, 4).trim();

				if (returnCode.equals(AppConstants.OPERATION_SUCCESS))
				{
					String caseId = "";
					if (result.get("caseId") != null)
					{
						caseId = result.get("caseId");
					}
					model.addAttribute("success", "IVD case " + caseId + " was created successfully.");
				}
				else
				{
					String error = "";
					if (returnCode.equals(AppConstants.DUPLICATE_CASE_CODE))
					{
						error = AppConstants.DUPLICATE_CASE_ERROR;
						model.addAttribute("message", "Problem encountered during case creation." + error);
					}
					else
					{
						throw new Exception("CaseActsCreateController: CreateCase: createActsCase. Return code: " + returnCode);
					}
				}
				session.setAttribute("selPartCase", selCasePartVal);
				screen_to_go = "redirect:/cswp/user/caseActsCreate.htm?mpiList=selCasePartVal";
			}
			else
			{
				List<CaseParticipant> partList = casePartService.getParticipantsAll(applId);
				List applCases = caseApplService.getApplCases(applId);
				if (selectedCase != null && !selectedCase.equals(""))
				{
					casePartList = casePartService.getCasePaticipantDetails(selectedCase);
					model.addAttribute("selectedCase", selectedCase);
				}
				else
				{
					model.addAttribute("selectedCase", "");
				}
				model.addAttribute("partList", partList);
				model.addAttribute("applId", applId);
				model.addAttribute("applCases", applCases);
				model.addAttribute("casePartList", casePartList);
				screen_to_go = AppConstants.ECWA_CASE_ACTS_CREATE;
			}

		}
		catch (Exception e)
		{
			//logger.debug(e.getMessage());
			//screen_to_go = "redirect:/cswp/user/caseActsCreate.htm";
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
