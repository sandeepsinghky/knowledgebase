package nc.dhhs.nccss.acts.ecwa.web.controller.applprocess;

import java.util.ArrayList;
import java.util.HashMap;
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

import nc.dhhs.nccss.acts.ecwa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecwa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartInsurance;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartEmp;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartIncome;
import nc.dhhs.nccss.acts.ecwa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartNCPExt;
import nc.dhhs.nccss.acts.ecwa.beans.CaseCourtOrder;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartOrder;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartProfile;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartProfileExt;
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
public class CaseApplicationMiscController extends BasicAnnotatedFormController
{
	@Autowired
	protected CaseParticipantService	casePartService;

	@Autowired
	protected CaseApplicationService	caseApplService;

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cswp/user/caseApplicationPartMiscInfo.htm")
	public String getCaseApplictionMisc(HttpServletRequest request, Model model)
	{
		logger.info("\n********** IN CaseApplicationMiscController: getCaseApplictionMisc**********\n");

		CasePartNCPExt ncpExtDetails = null;
		HttpSession session = request.getSession();
		String applId = (String) session.getAttribute(AppConstants.SELECTED_APPLICATION);
		String selectedApplPartId = (String) session.getAttribute(AppConstants.SELECTED_APP_PART);
		HashMap<String, CasePartInsurance> insuranceMap = new HashMap();
		CaseParticipant applPartInfo = new CaseParticipant();
		List<CaseCourtOrder> supportOrdList = new ArrayList<CaseCourtOrder>();
		HashMap<String, CasePartProfileExt> partProfileExtMap = new HashMap();
		String assets = "";
		try
		{
			applPartInfo = casePartService.getParticipantByPartId(applId, selectedApplPartId).get(0);
			List<CasePartContact> contactList = casePartService.getPartContact(applId, selectedApplPartId);
			if (contactList.size() > 0)
			{
				for (CasePartContact cont : contactList)
				{
					if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_CELL))
					{
						model.addAttribute("cPh", cont.getContactInfo());
					}
					else if (cont.getContactType().equals(AppConstants.CONTACT_TYPE_EMAIL))
					{
						model.addAttribute("email", cont.getContactInfo());
					}
				}
			}

			//Employment information
			CasePartEmp empBean = new CasePartEmp();
			List<CasePartEmp> empList = casePartService.getPartEmp(applId, selectedApplPartId);
			if (empList.size() > 0)
			{
				empBean = empList.get(0);
				ThirdParty thrdPartyBean = new ThirdParty();
				List<ThirdParty> trdPtyList = casePartService.getThirdPartyBy3ptyId(applId, empBean.getThirdPartyId());
				if (trdPtyList.size() > 0)
				{
					empBean.setThirdPartyInfo(trdPtyList.get(0));
				}
			}

			//child insurance information
			List<CasePartInsurance> casePartInsurance = casePartService.getPartInsurance(applId, selectedApplPartId);
			if (casePartInsurance != null && casePartInsurance.size() > 0)
			{
				for (CasePartInsurance insurance : casePartInsurance)
				{
					insuranceMap.put(insurance.getInsuranceType().trim(), insurance);

				}

			}

			//assets
			List<CasePartIncome> incomeList = casePartService.getParticipantIncome(applId, selectedApplPartId);
			if (incomeList != null && incomeList.size() > 0)
			{
				for (CasePartIncome income : incomeList)
				{
					if (income.getIncomeSrc().equals(AppConstants.INCOME_OTHER))
					{
						assets = income.getIncomeAmtStr();
					}

				}

			}

			List<CasePartNCPExt> ncpExtDetailsList = casePartService.getCasePartNCPExtByPartId(applId, selectedApplPartId);
			if (ncpExtDetailsList.size() > 0)
			{
				ncpExtDetails = ncpExtDetailsList.get(0);

			}

			//Court Order
			if (ncpExtDetails != null && ncpExtDetails.getOrderStatus().equals("1"))
			{
				supportOrdList = casePartService.getCaseCourtOrderByPartId(applId, selectedApplPartId);
				if (supportOrdList.size() > 0)
				{
					for (CaseCourtOrder support : supportOrdList)
					{
						if (support.getOrderNm() != null && !support.getOrderNm().equals(""))
						{
							List<CasePartOrder> selectChildList = casePartService.getCasePartOrderByOrderNum(support.getOrderNm());
							if (selectChildList != null && selectChildList.size() > 0)
							{
								for (CasePartOrder order : selectChildList)
								{
									CaseParticipant chldPart = casePartService.getParticipantByPartId(applId, order.getChildApplicantId()).get(0);
									support.setChildren(chldPart.getApplicantFNm() + " " + chldPart.getApplicantLNm());
								}
							}
						}
					}
				}
			}

			//for Marriage Data
			if (applPartInfo.getPartType().equals(AppConstants.CHLD_PARTICIPANT_TYPE))
			{
				//part profile
				List<CasePartProfile> partProfileList = casePartService.getPartProfile(applPartInfo.getApplicationId(), applPartInfo.getApplicantId());
				CasePartProfile partProfile = new CasePartProfile();
				if (partProfileList.size() > 0)
				{
					partProfile = partProfileList.get(0);

				}

				//part profile ext information
				if (partProfile != null && partProfile.getChldWdlck().equals("1"))
				{
					List<CasePartProfileExt> casePartProfileExtList = casePartService.getPartProfileExt(applPartInfo.getApplicationId(), applPartInfo.getApplicantId());

					if (casePartProfileExtList != null && casePartProfileExtList.size() > 0)

					{
						for (CasePartProfileExt partProfileExt : casePartProfileExtList)
						{

							partProfileExtMap.put(partProfileExt.getProfileExtType().trim(), partProfileExt);

						}

					}
				}
			}

			model.addAttribute("partInfo", applPartInfo);
			model.addAttribute("applId", applId);
			model.addAttribute("empInfo", empBean);
			model.addAttribute("assets", assets);
			model.addAttribute("chldInsurnceMap", insuranceMap);
			model.addAttribute("ncpExtDetails", ncpExtDetails);
			model.addAttribute("supportOrderList", supportOrdList);
			model.addAttribute("partProfileExtMap", partProfileExtMap);

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

		return AppConstants.ECWA_CASE_APPLN_MISC_INFO;
	}

}
