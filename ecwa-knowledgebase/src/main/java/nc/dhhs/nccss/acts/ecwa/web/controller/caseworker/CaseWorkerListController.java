package nc.dhhs.nccss.acts.ecwa.web.controller.caseworker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecwa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecwa.caseworker.beans.CaseWorker;
import nc.dhhs.nccss.acts.ecwa.caseworker.web.service.CaseWorkerService;

@Controller
public class CaseWorkerListController extends BasicAnnotatedFormController
{

	protected final Logger			logger	= Logger.getLogger(CaseWorkerListController.class);
	
	@Autowired
	private CaseWorkerService	caseWorkerService;
	
	Map <String, String> spParmList=new HashMap<String, String>();
	String targetPage=""; 
	
	@RequestMapping(value="/cswp/user/listCaseWorker.htm",method = RequestMethod.POST)
	public String listWorker (HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute(AppConstants.USER_LOGIN_NAME);
		
		try
		{
			Map paramList = new HashMap(); 
			for (Object obj : request.getParameterMap().entrySet())
			{
				Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) obj;
				if (entry.getValue() != null && entry.getValue().length > 0) {
					paramList.put(entry.getKey(), entry.getValue()[0]);
					System.out.println( entry.getKey() +entry.getValue() );
				}
			}
			//Get the Formatted parm list as Expected by the Stored Proceudre  
			spParmList=getSelectionCriteria(paramList);
			
			//Saving this initial Selection in Session 
			session.setAttribute("initCaseWokerCriteria", spParmList);
			List <CaseWorker> caseWorkerList=caseWorkerService.getCaseWorkerListBySelectionCriteria(spParmList);
			 
			if (caseWorkerList.isEmpty()) {
					model.addAttribute("message", "No Records found for Selection criteria");
					targetPage=AppConstants.ECWA_CASE_WORKER_SEARCH;
				
			}else {
					model.addAttribute("caseWorkerList", caseWorkerList);
					//session.setAttribute("caseWorkerList", caseWorkerList);
					//targetPage= AppConstants.ECWA_CASE_WORKER_LIST;
					targetPage=AppConstants.ECWA_CASE_WORKER_SEARCH;
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

	@RequestMapping(value="/cswp/user/listCaseWorker.htm",method = RequestMethod.GET)
	public String listOfWorker (HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		//Saving this initial Selection in Session 
		try
		{
			spParmList=(Map<String, String>) session.getAttribute("initCaseWokerCriteria");
			List <CaseWorker> caseWorkerList=caseWorkerService.getCaseWorkerListBySelectionCriteria(spParmList);
			model.addAttribute("caseWorkerList", caseWorkerList);
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
		return AppConstants.ECWA_CASE_WORKER_SEARCH;
		//return AppConstants.ECWA_CASE_WORKER_LIST;
	}
	private Map getSelectionCriteria(Map paramList)
	{
		 
		if (paramList.get("selectcriteria").equals("RACFID")) {
			spParmList.put("ID_WRKR_LOGON", (String) paramList.get("RACF_ID"));
			spParmList.put("ID_WRKR", "");
			spParmList.put("CD_COUNTY", "");
			spParmList.put("NM_WRKR_LAST", "");
			spParmList.put("NM_WRKR_FIRST", "");
			
		} else if(paramList.get("selectcriteria").equals("WORKERNUM")) {
			spParmList.put("ID_WRKR_LOGON", "");
			spParmList.put("ID_WRKR", (String) paramList.get("WORKER_NUM"));
			spParmList.put("CD_COUNTY", "");
			spParmList.put("NM_WRKR_LAST", "");
			spParmList.put("NM_WRKR_FIRST", "");
			
		} else if(paramList.get("selectcriteria").equals("COUNTY")) {
			spParmList.put("ID_WRKR_LOGON", "");
			spParmList.put("ID_WRKR", "");
			spParmList.put("CD_COUNTY", (String) paramList.get("COUNTY_ID"));
			spParmList.put("NM_WRKR_LAST", "");
			spParmList.put("NM_WRKR_FIRST", "");
			
		} else if(paramList.get("selectcriteria").equals("COUNTY_LNAME")) {
			spParmList.put("ID_WRKR_LOGON", "");
			spParmList.put("ID_WRKR", "");
			spParmList.put("CD_COUNTY", (String) paramList.get("COUNTY_LNAME_COUNTY_ID"));
			spParmList.put("NM_WRKR_LAST", (String) paramList.get("COUNTY_LNAME_LASTNAME"));
			spParmList.put("NM_WRKR_FIRST", "");
		} else if(paramList.get("selectcriteria").equals("LNAME")) {
			spParmList.put("ID_WRKR_LOGON", "");
			spParmList.put("ID_WRKR", "");
			spParmList.put("CD_COUNTY", "");
			spParmList.put("NM_WRKR_LAST", (String) paramList.get("LNAME"));
			spParmList.put("NM_WRKR_FIRST", "");
			
		}else if(paramList.get("selectcriteria").equals("LNAME_FNAME")) {
			spParmList.put("ID_WRKR_LOGON", "");
			spParmList.put("ID_WRKR", "");
			spParmList.put("CD_COUNTY", "");
			spParmList.put("NM_WRKR_LAST", (String) paramList.get("LNAME_FNAME_LASTNAME"));
			spParmList.put("NM_WRKR_FIRST", (String) paramList.get("LNAME_FNAME_FIRSTNAME"));
		}
		
		

		return spParmList;
	}
	
	
	
}
