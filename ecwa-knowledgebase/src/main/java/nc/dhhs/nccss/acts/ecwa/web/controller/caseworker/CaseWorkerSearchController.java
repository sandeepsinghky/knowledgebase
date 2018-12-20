package nc.dhhs.nccss.acts.ecwa.web.controller.caseworker;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nc.dhhs.nccss.acts.ecwa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecwa.web.controller.BasicAnnotatedFormController;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

@Controller
public class CaseWorkerSearchController extends BasicAnnotatedFormController
{

	/*
	 *   
	 */

	@RequestMapping(value="/cswp/user/searchWorker.htm",method = RequestMethod.GET)
	public String workerSearchScreen (HttpServletRequest request, Model model)
	{
		model.addAttribute("initLoadSerchPage","init");
		model.addAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, (List<CodeLookUp>) request.getServletContext().getAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST));
		return AppConstants.ECWA_CASE_WORKER_SEARCH;
	}
	

}
