package nc.dhhs.nccss.acts.ecwa.web.controller.applprocess;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import nc.dhhs.nccss.acts.ecwa.beans.CaseActsParticipant;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartDemo;
import nc.dhhs.nccss.acts.ecwa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecwa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Controller
@SessionAttributes("applId")
public class CaseApplicationPartListController extends BasicAnnotatedFormController
{

	@Autowired
	protected CaseParticipantService	casePartService;

	@Autowired
	protected CaseApplicationService	caseApplService;

	/**
	 * @return
	 */
	@RequestMapping("/cswp/user/caseApplicationPartList.htm")
	public String getApplicationPartList(HttpServletRequest request, Model model)
	{
		logger.info("\n********** IN CaseApplicationPartListController: getApplicationPartList**********\n");

		HttpSession session = request.getSession();
		List<CaseParticipant> partList = new ArrayList<CaseParticipant>();
		List applCases = new ArrayList();
		session.setAttribute(AppConstants.SELECTED_APP_PART, null);
		String applId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		try
		{
			partList = casePartService.getParticipantsAll(applId);
			applCases = caseApplService.getApplCases(applId);
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
		model.addAttribute("applId", applId);
		model.addAttribute("applCases", applCases);
		return AppConstants.ECWA_CASE_APPLN_PART_LIST;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cswp/user/caseApplicationPartList.htm", method = RequestMethod.POST)
	public String processApplicationPartList(HttpServletRequest request, Model model, final Principal principal)
	{
		logger.info("\n********** IN CaseApplicationPartListController: processApplicationPartList**********\n");

		HttpSession session = request.getSession();
		String action = (String) request.getParameter("action");
		String selectedPart = (String) request.getParameter("selectApplPart");
		String appId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		String screen_to_go = "";
		String rValue = AppConstants.OPERATION_SUCCESS;
		String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);

		CaseApplLock applLockBean = null;

		try
		{
			if (action.equals("printappl"))
			{
				logger.info("\n********* *********** Going to PRINT ********** ********** ");

				return AppConstants.ECWA_REPORTS;
			}

			else if (action.equals("searchActsPart"))
			{
				session.setAttribute(AppConstants.SELECTED_APP_PART, selectedPart);
				screen_to_go = "redirect:/cswp/user/caseActsPartList.htm";
			}
			else if (action.equals("createActsPart"))
			{
				rValue = casePartService.createUseActsPart(appId, selectedPart, " ", userId, AppConstants.OPERATION_INSERT);
				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					model.addAttribute("success", "Information is saved successfully.");
				}
				else
				{
					//model.addAttribute("message", "Problem encountered while saving.");
					throw new Exception("CaseApplicationPartListController: processApplicationPartList: createActsPart. Return code: " + rValue);
				}
				screen_to_go = "redirect:/cswp/user/caseApplicationPartList.htm";

			}
			else if (action.equals("updateActsPart"))
			{
				session.setAttribute(AppConstants.SELECTED_APP_PART, selectedPart);
				screen_to_go = "redirect:/cswp/user/caseApplicationPartUpdate.htm";
			}
			else if (action.equals("createActsCase"))
			{
				screen_to_go = "redirect:/cswp/user/caseActsCreate.htm";
			}
			else if (action.equals("createApplLock"))
			{

				applLockBean = new CaseApplLock();
				applLockBean.setApplicationId(appId);
				applLockBean.setLockedBy(userId);
				applLockBean.setTsLock(new Timestamp(System.currentTimeMillis()));
				rValue = caseApplService.LockApplication(applLockBean, userId);
				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					model.addAttribute("success", "Application is locked successfully.");
					session.setAttribute(AppConstants.IS_APPLICATION_LOCKED, "Y");
					session.setAttribute(AppConstants.APPLICATION_LOCKED_BY, (String) session.getAttribute(AppConstants.USER_LOGIN_NAME));
					session.setAttribute(AppConstants.APPLICATION_LOCKED_BY_NAME, (String) session.getAttribute(AppConstants.WRKR_NAME));
				}
				else
				{
					//model.addAttribute("message", "Problem encountered while Locking the application.");
					throw new Exception("CaseApplicationPartListController: processApplicationPartList: LockApplication. Return code: " + rValue);
				}
				screen_to_go = "redirect:/cswp/user/caseApplicationPartList.htm";
			}
			else if (action.equals("unlockAppl"))
			{
				applLockBean = new CaseApplLock();
				applLockBean.setApplicationId(appId);
				rValue = caseApplService.deleteApplicationLock(applLockBean, userId);
				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
				{
					model.addAttribute("success", "Application is unlocked successfully.");
					session.setAttribute(AppConstants.IS_APPLICATION_LOCKED, "N");
					session.setAttribute(AppConstants.APPLICATION_LOCKED_BY, "");
					session.setAttribute(AppConstants.APPLICATION_LOCKED_BY_NAME, "");
				}
				else
				{
					//model.addAttribute("message", "Problem encountered while Locking the application.");
					throw new Exception("CaseApplicationPartListController: processApplicationPartList: deleteApplicationLock. Return code: " + rValue);
				}
				screen_to_go = "redirect:/cswp/user/caseApplicationPartList.htm";
			}
			else if (action.equals("cmpltAppl"))
			{
				List<CaseApplication> applBeanList = caseApplService.getCaseApplicationByAppId(appId);
				if (applBeanList != null && applBeanList.size() > 0)
				{
					CaseApplication applBean = applBeanList.get(0);
					applBean.setApplStatus(AppConstants.APP_STATUS_COMPLETE);
					rValue = caseApplService.updateCaseApplicationStatus(applBean);
					if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
					{
						model.addAttribute("success", "Application completed successfully.");
						session.setAttribute(AppConstants.APP_STATUS, AppConstants.APP_STATUS_COMPLETE);
					}
					else
					{
						model.addAttribute("message", "Problem encountered while completing application.");
						throw new Exception("CaseApplicationPartListController: processApplicationPartList: updateCaseApplicationStatus. Return code: " + rValue);
					}
					screen_to_go = "redirect:/cswp/user/caseApplicationPartList.htm";
				}
			}
			else
			{
				//selected case from the caseApplicationPartList screen
				String selectedCase = (String) request.getParameter("caseId");
				List<CaseParticipant> partList = new ArrayList<CaseParticipant>();
				List applCases = new ArrayList();
				List<CaseActsParticipant> casePartList = new ArrayList<CaseActsParticipant>();
				partList = casePartService.getParticipantsAll(appId);
				applCases = caseApplService.getApplCases(appId);
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
				model.addAttribute("applId", appId);
				model.addAttribute("applCases", applCases);
				model.addAttribute("casePartList", casePartList);
				return AppConstants.ECWA_CASE_APPLN_PART_LIST;
			}

		}
		catch (Exception e)
		{
			//logger.debug(e.getMessage());
			//screen_to_go = "redirect:/cswp/user/caseApplicationPartList.htm";
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

	@RequestMapping("/cswp/user/caseApplicationPartUpdate.htm")
	public String getApplicationPartDetails(HttpServletRequest request, Model model)
	{
		logger.info("\n********** IN CaseApplicationPartListController: getApplicationPartDetails**********\n");

		HttpSession session = request.getSession();
		HashMap<String, String> partDemoMap = new HashMap<String, String>();
		CaseParticipant applPartInfo = new CaseParticipant();
		String applId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		String selectedApplPartId = (String) session.getAttribute(AppConstants.SELECTED_APP_PART);
		try
		{
			applPartInfo = casePartService.getParticipantByPartId(applId, selectedApplPartId).get(0);
			List<CasePartContact> contactList = casePartService.getPartContact(applId, selectedApplPartId);
			if (contactList.size() > 0)
			{
				for (CasePartContact cont : contactList)
				{
					if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_HOME))
					{
						model.addAttribute("hPh", cont.getContactInfo());
					}
					else if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_WORK))
					{
						model.addAttribute("wPh", cont.getContactInfo());
					}
				}
			}

			List<CasePartAddress> addrList = casePartService.getParticipantAddr(applId, selectedApplPartId);
			if (addrList.size() > 0)
			{
				for (CasePartAddress addr : addrList)
				{
					if (addr.getAddrType().equals(AppConstants.ADDRESS_MAILING))
					{
						model.addAttribute("mailAddr", addr);
					}
					else if (addr.getAddrType().equals(AppConstants.ADDRESS_RESIDENTIAL))
					{
						model.addAttribute("resAddr", addr);
					}
				}
			}

			List<CasePartDemo> partDemo = casePartService.getPartDemo(applPartInfo);
			if (partDemo != null && partDemo.size() > 0)
			{
				for (CasePartDemo partDemoBean : partDemo)
				{
					partDemoMap.put(partDemoBean.getPartDemo(), partDemoBean.getDemoStatus());
				}
			}
			model.addAttribute("partDemo", partDemoMap);

		}
		catch (Exception e)
		{
			//logger.error(e.getMessage());
			logger.error(e.getMessage());
			model.addAttribute("message", AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			if (session.getAttribute("selectedApp") != null)
				error.setApplicationId((String) session.getAttribute("selectedApp"));
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/apperror.htm";
		}
		model.addAttribute("partInfo", applPartInfo);
		model.addAttribute("applId", applId);
		return AppConstants.ECWA_CASE_APPLN_PART_UPDATE;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cswp/user/caseApplicationPartUpdate.htm", method = RequestMethod.POST)
	public String processCaseApplicationPartUpdate(HttpServletRequest request, Model model)
	{
		logger.info("\n********** IN CaseApplicationPartListController: processCaseApplicationPartUpdate**********\n");

		HttpSession session = request.getSession();
		String selectedApplPartId = (String) session.getAttribute(AppConstants.SELECTED_APP_PART);
		String applId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		String screen_to_go = "";
		String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);

		try
		{
			CaseParticipant applPartInfo = casePartService.getParticipantByPartId(applId, selectedApplPartId).get(0);
			if (request.getParameter("name") != null)
			{
				applPartInfo.setIsPartNameSelected(AppConstants.CONDITION_TRUE);
			}
			else
			{
				applPartInfo.setIsPartNameSelected(AppConstants.CONDITION_FALSE);
			}

			if (request.getParameter("ssn") != null)
			{
				applPartInfo.setIsPartSSNSelected(AppConstants.CONDITION_TRUE);
			}
			else
			{
				applPartInfo.setIsPartSSNSelected(AppConstants.CONDITION_FALSE);
			}

			if (request.getParameter("dob") != null)
			{
				applPartInfo.setIsPartDOBSelected(AppConstants.CONDITION_TRUE);
			}
			else
			{
				applPartInfo.setIsPartDOBSelected(AppConstants.CONDITION_FALSE);
			}

			if (request.getParameter("race") != null)
			{
				applPartInfo.setIsPartRaceSelected(AppConstants.CONDITION_TRUE);
			}
			else
			{
				applPartInfo.setIsPartRaceSelected(AppConstants.CONDITION_FALSE);
			}

			if (request.getParameter("gndr") != null)
			{
				applPartInfo.setIsPartGndrSelected(AppConstants.CONDITION_TRUE);
			}
			else
			{
				applPartInfo.setIsPartGndrSelected(AppConstants.CONDITION_FALSE);
			}

			if (request.getParameter("lang") != null)
			{
				applPartInfo.setIsPartLangSelected(AppConstants.CONDITION_TRUE);
			}
			else
			{
				applPartInfo.setIsPartLangSelected(AppConstants.CONDITION_FALSE);
			}

			if (request.getParameter("hph") != null)
			{
				applPartInfo.setIsPartHphSelected(AppConstants.CONDITION_TRUE);
			}
			else
			{
				applPartInfo.setIsPartHphSelected(AppConstants.CONDITION_FALSE);
			}

			if (request.getParameter("wph") != null)
			{
				applPartInfo.setIsPartWphSelected(AppConstants.CONDITION_TRUE);
			}
			else
			{
				applPartInfo.setIsPartWphSelected(AppConstants.CONDITION_FALSE);
			}

			if (request.getParameter("addr") != null)
			{
				applPartInfo.setIsPartAddrSelected(AppConstants.CONDITION_TRUE);
			}
			else
			{
				applPartInfo.setIsPartAddrSelected(AppConstants.CONDITION_FALSE);
			}

			String rValue = casePartService.updateActsPart(applPartInfo, userId);

			if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				model.addAttribute("success", "ACTS has been updated successfully.");
			}
			else
			{
				model.addAttribute("message", "Problem encountered while saving.");
				throw new Exception("CaseApplicationPartListController: processCaseApplicationPartUpdate: updateActsPart. Return code: " + rValue);
			}
			screen_to_go = "redirect:/cswp/user/caseApplicationPartUpdate.htm";

		}
		catch (Exception e)
		{
			//logger.debug(e.getMessage());
			//screen_to_go = "redirect:/cswp/user/caseApplicationPartUpdate.htm";
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
