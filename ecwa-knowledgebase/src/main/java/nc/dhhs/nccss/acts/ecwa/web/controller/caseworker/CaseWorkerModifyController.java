package nc.dhhs.nccss.acts.ecwa.web.controller.caseworker;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.nc.ncidng.ncidngwebservice.schemas.IsMemberOfAGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.Message;
import gov.nc.ncidng.ncidngwebservice.schemas.ModifyGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;
import nc.dhhs.nccss.acts.ecwa.caseworker.web.service.CaseWorkerService;
import nc.dhhs.nccss.acts.ecwa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecwa.web.service.MFAWebService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecwa.web.util.EmailManager;
import nc.dhhs.nccss.acts.ecwa.beans.CaseAppSearch;
import nc.dhhs.nccss.acts.ecwa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecwa.caseworker.beans.CaseWorker;

@Controller
public class CaseWorkerModifyController extends BasicAnnotatedFormController
{

	protected final Logger			logger	= Logger.getLogger(CaseWorkerModifyController.class);
	@Autowired
	private CaseWorkerService	caseWorkerService;
	
	@Autowired
	private MFAWebService mfaWebService;
	
	String targetPage="";
	Map <String, String> spParmList=new HashMap<String, String>();
	
	@RequestMapping(value="/cswp/user/modfiyCaseWorker.htm",method = RequestMethod.POST)
	public String modifyCaseWorker (HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);
		String action = (String) request.getParameter("action");
		String selectCaseWorker = (String) request.getParameter("selectCaseWorker"); 
	//	List <CaseWorker> caseWorkerList=(List<CaseWorker>) session.getAttribute("caseWorkerList");
		CaseWorker caseWorker = new CaseWorker();
		
		if ((selectCaseWorker=="") || (selectCaseWorker==null)) {
			// redirect to Error Page 
		} 
		
	    
		try
		{
			 List <String> mfaSelectList = getMFASelectList();
			 model.addAttribute("mfaSelectList",mfaSelectList);
			 
			 List<CodeLookUp>  listCounty= (List<CodeLookUp>) request.getServletContext().getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST);
			 List<CodeLookUp>  listState= (List<CodeLookUp>) request.getServletContext().getAttribute(AppConstants.CODE_LOOKUP_STATE_LIST);
			 
			if (action.equals("create"))
			{
				logger.info("\n********* CaseWorkerModifyController*** Creating New Case Worker  ********** ********** ");
				model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, listCounty);
				model.addAttribute("actionType", "Create");
				
				targetPage= AppConstants.ECWA_CASE_WORKER_DETAIL;
			
			}else if (action.equals("delete")) {
				
				logger.info("\n********* CaseWorkerModifyController*** Deleting Case Worker selectCaseWorker " +selectCaseWorker);
				
				//If MFA is there delete Pass REMOVE
				
				//Get the CaseWorker Object from the caseWrokerList for Delete Processing
				//caseWorker =getSelectedWorker(selectCaseWorker,caseWorkerList);
				
				//pass this worker to Stored Procedure for Delete Processing 
				String rValue =caseWorkerService.deleteCaseWorker(selectCaseWorker); 
				
				if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
					model.addAttribute("success", "Case Worker "+selectCaseWorker + "has been Deleted successfully.");
					//Return the Updated List 
					
				}else{
					//model.addAttribute("message", "Problem encountered during delete!!");
					throw new Exception("CaseWorkerModifyController: modifyCaseWorker: Case Worker delete failed. Return code: "+rValue);
				}
				
				
				
				targetPage= AppConstants.ECWA_CASE_WORKER_LIST;
			
			}else if (action.equals("update")) {
			
				logger.info("\n********* CaseWorkerModifyController*** Updating Case Worker  "+ selectCaseWorker +" ********** ********** ");
				 
				model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, (List<CodeLookUp>) request.getServletContext().getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST));
				caseWorker = caseWorkerService.getCaseWorkerByWorkerNumber(selectCaseWorker);
				 
				//Test Code 
				logger.info("Start Time " + caseWorker.getStartTime() + " End Time " + caseWorker.getEndTime());
				//Test Code 
				
				model.addAttribute("actionType", "Update");
				model.addAttribute("caseWorker", caseWorker);
				targetPage= AppConstants.ECWA_CASE_WORKER_DETAIL;
			
			} else if (action.equals("inquiry")) {
				
				logger.info("\n********* CaseWorkerModifyController*** Inquiry  Case Worker  ********** ********** ");
				model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, (List<CodeLookUp>) request.getServletContext().getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST));
				//caseWorker =getSelectedWorker(selectCaseWorker,caseWorkerList);
				caseWorker = caseWorkerService.getCaseWorkerByWorkerNumber(selectCaseWorker);
				 
				//Test Code 
				logger.info("Start Time " + caseWorker.getStartTime() + " End Time " + caseWorker.getEndTime());
				model.addAttribute("actionType", "View");
				model.addAttribute("caseWorker", caseWorker);
				targetPage= AppConstants.ECWA_CASE_WORKER_DETAIL;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("message",AppConstants.APPLICATION_ERROR);
			ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
			request.setAttribute("errorBean", error);
			error = null;
			return "forward:/error.htm";
		} 
	
	
	
		return targetPage;
	}

	/**
	 * Mapping to Create and Update Case Worker from Case Worker Details Screen
	 * @throws Exception
	 */

	@RequestMapping(value="/cswp/user/updateDBWorker.htm",method = RequestMethod.POST)
	public String updateDBWorker (HttpServletRequest request, Model model,@ModelAttribute("caseWorker") CaseWorker caseWorker)
	{
		HttpSession session = request.getSession(); 	
	    String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);
	    LocalDate dateNow= LocalDate.now();
		DateTimeFormatter formatTimeNow=DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime timeNow=LocalTime.now();
		String action = (String) request.getParameter("action");
		String mfaServiceError="";
		List <String> mfaSelectList = getMFASelectList();
		model.addAttribute("mfaSelectList",mfaSelectList);
		 
		 Map paramList = new HashMap(); 
			for (Object obj : request.getParameterMap().entrySet())
			{
				Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) obj;
				if (entry.getValue() != null && entry.getValue().length > 0) {
					paramList.put(entry.getKey(), entry.getValue()[0]);
					System.out.println( entry.getKey() +entry.getValue() );
				}
			}
			
			try
			{
				//Convert Non Blank Start Time, End time , Start Lunch Time, End Lunch Time to Time format hh:mm:ss
				//caseWorker= changeTimeFormatForDB(caseWorker);
			
				if (action.equalsIgnoreCase("createCaseWorkerDB")) {
					
					//If MFA Enabled is Yes and NCID is Present, then Call the MFA Service to ADD 
					
					if ((caseWorker.getMfacriteria().equalsIgnoreCase("Yes")) && (caseWorker.getNcid()!="")) {
						logger.info("\n* MFA Service calling for Worker NCID to ADD: " + caseWorker.getNcid() + " For Action " +action);
						mfaServiceError=callMFAService(caseWorker.getNcid(), "ADD");
					}
					
					//If MFA Service Success Add Record through Stored Procedure
					
					if (mfaServiceError =="") {
						
						//Construct CD_FIPS_WRKR
						caseWorker.setCd_Fips_Wrkr(caseWorker.getState()+caseWorker.getCounty()+ caseWorker.getIvd_area());
						
						//Stored Procedure Call for Add Case Worker to DB2
						
						String rValue = caseWorkerService.createCaseWorker(caseWorker); 
						
						if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
							
							spParmList=(Map<String, String>) session.getAttribute("initCaseWokerCriteria");
							List <CaseWorker> caseWorkerList=caseWorkerService.getCaseWorkerListBySelectionCriteria(spParmList);
							model.addAttribute("caseWorkerList", caseWorkerList);
							model.addAttribute("success", "Case Worker has been Added successfully.");
							targetPage= AppConstants.ECWA_CASE_WORKER_SEARCH;
							
						}else{
							//model.addAttribute("message", "Problem encountered during delete!!");
							throw new Exception("CaseWorkerModifyController: updateDBWorker: Case Worker ADD failed. Return code: "+rValue);
						}
						
						
					}else {
							//if service Error return the CaseWorker Object along with Exception on update Page
							model.addAttribute("actionType", "Create");
							model.addAttribute("caseWorker", caseWorker);
							model.addAttribute("message", mfaServiceError);
							targetPage= AppConstants.ECWA_CASE_WORKER_DETAIL;
					}
				 
					
					
				}else if ((action.equalsIgnoreCase("updateCaseWorkerDB"))) {
					logger.info("Update Date " + dateNow + " Update Time " +timeNow.format(formatTimeNow) + " Updated By "+userId );
					

					//If MFA Enabled is Yes and NCID is Present, then Call the MFA Service to Update 
					
					if ((caseWorker.getMfacriteria().equalsIgnoreCase("Yes")) && (caseWorker.getNcid()!="")) {
						logger.info("\n* MFA Service calling for Worker NCID to ADD: " + caseWorker.getNcid() + " For Action " +action);
						mfaServiceError=callMFAService(caseWorker.getNcid(), "ADD");
					} else if ((caseWorker.getMfacriteria().equalsIgnoreCase("NO")) && (caseWorker.getNcid()!="")) {
						logger.info("\n* MFA Service calling for Worker NCID to REMOVE: " + caseWorker.getNcid() + " For Action " +action);	
						mfaServiceError=callMFAService(caseWorker.getNcid(), "REMOVE");
					}
					
					//MFA Service Success and update Record through Stored Procedure
					if (mfaServiceError =="") {
						//Construct CD_FIPS_WRKR
						caseWorker.setCd_Fips_Wrkr(caseWorker.getState()+caseWorker.getCounty()+ caseWorker.getIvd_area());
						//Stored Procedure Call to Update Case Worker to DB2
						String rValue = caseWorkerService.upadateCaseWorker(caseWorker); 
						
						if (rValue != null && rValue.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS)) {
							
							spParmList=(Map<String, String>) session.getAttribute("initCaseWokerCriteria");
							List <CaseWorker> caseWorkerList=caseWorkerService.getCaseWorkerListBySelectionCriteria(spParmList);
							model.addAttribute("caseWorkerList", caseWorkerList);
							model.addAttribute("success", "Case Worker has been Added successfully.");
							targetPage= AppConstants.ECWA_CASE_WORKER_SEARCH;
							
						}else{
							//model.addAttribute("message", "Problem encountered during delete!!");
							throw new Exception("CaseWorkerModifyController: updateDBWorker: Case Worker Update failed. Return code: "+rValue);
						}
						
					}else {
						//if service Error return the CaseWorker Object along with Exception on update Page
						model.addAttribute("actionType", "Update");
						model.addAttribute("caseWorker", caseWorker);
						model.addAttribute("message", mfaServiceError);
						targetPage= AppConstants.ECWA_CASE_WORKER_DETAIL;
					}
					
					
					
					
				}
				 
			}
			catch (Exception e)
			{
				e.printStackTrace();
				model.addAttribute("message",AppConstants.APPLICATION_ERROR);
				ErrorDescriptor error = new ErrorDescriptor(this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage(), e);
				request.setAttribute("errorBean", error);
				error = null;
				return "forward:/error.htm";
			}
		 
			return targetPage;
	} 

	 

	 
	


	/**
	 * @param String ncid, String action
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	private String callMFAService(String ncid, String action)
	{
		  
		String memberDn = "";
		//ncid="ssingh3";
		SearchUserByLoginIDResponse slogInIDResponse = null;
		//IsMemberOfAGroupResponse isMemberOfAGroupResponse=null;
		ModifyGroupResponse modifyGroupResponse=null;
		
		slogInIDResponse =mfaWebService.searchUserByNCID(ncid);
		
		if   ((slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().isEmpty()) ||(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry() ==null)){	
		      logger.info("\n* MFA Service opeartion searchUserByNCID called  for NCID "+ ncid + " NCID don't Exist");
		      return "MFA Service-searchUserByNCID " + ncid + " Don't Exist"; 
		      
		} 
			 
		memberDn = slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getUserDN();
		
		if (( memberDn =="") || (memberDn==null)){
				logger.info("\n* MFA Service  opeartion searchUserByNCID called  for NCID "+ ncid + " NCID Exist but User DN not found");
				 return "MFA Service-searchUserByNCID NCID " + ncid + "Exist but User DN is missing"; 
		} 
				
		//No Need to check if the member exist is in the Group as Service Validates that 
		modifyGroupResponse =mfaWebService.modifyGroup(memberDn, AppConstants.MFA_GROUP_DN, action);
		
		if (modifyGroupResponse.getModifyGroupResult().getMessageArray().getMessage().isEmpty()){
			 return "MFA Service-getModifyGroupResult NCID " + ncid + " Role Modified Unsuccessful"; 		
		}
	
		String retCode=modifyGroupResponse.getModifyGroupResult().getMessageArray().getMessage().get(0).getCode();
		String retDesc=modifyGroupResponse.getModifyGroupResult().getMessageArray().getMessage().get(0).getContent();
		logger.info(retCode + retDesc);
		
		if (!retCode.equalsIgnoreCase("1")) {
			// Return code is not 1 
			return "MFA Service-getModifyGroupResult NCID " + ncid + " Role Modified Unsuccessful with Description " +retDesc; 
		}
		return "";
	}



	private List<String> getMFASelectList()
	{
		 List <String> mfaSelectList = new ArrayList<String>();
		 mfaSelectList.add("");
		 mfaSelectList.add("Yes");
		 mfaSelectList.add("No");
		return mfaSelectList;
	}
	 
	/**
	 * @param String selectCaseWorker,List <CaseWorker> caseWorkerList
	 * @param HashMap
	 * @return CaseWorker Object
	 * @throws Exception
	 */
	public CaseWorker getSelectedWorker (String selectCaseWorker,List <CaseWorker> caseWorkerList) throws Exception {
		 
		selectCaseWorker=selectCaseWorker.trim();
		CaseWorker caseWorker = new CaseWorker();
		 for(CaseWorker caseWorkertemp:caseWorkerList) {
			 
			 if (caseWorkertemp.getWorker_num().equalsIgnoreCase(selectCaseWorker)) {
				 caseWorker=caseWorkertemp;
				 break;
			 }
		 }
		 return caseWorker;
		
	}

	
	
	
}
