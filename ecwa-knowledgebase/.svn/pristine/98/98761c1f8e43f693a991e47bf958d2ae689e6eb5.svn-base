package nc.dhhs.nccss.acts.ecwa.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import nc.dhhs.nccss.acts.ecwa.web.EcwaSession;
import nc.dhhs.nccss.acts.ecwa.web.security.EcwaUserDetails;
import nc.dhhs.nccss.acts.ecwa.web.typeconverters.DateTypeConverter;

/**
 * Base Class for all ecwa controllers.
 * 
 * 
 * @author Vijay Peddapalli
 * 
 */
@Controller
public class BasicAnnotatedFormController
{

	protected final Logger logger = Logger.getLogger(getClass());

	/**
	 * <p>
	 * Registers all custom type handlers
	 * </p>
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateTypeConverter());
	}

	/**
	 * 
	 * @param request
	 * @return instance of EcoaSession object.
	 */
	protected EcwaSession getEcwaSession(HttpServletRequest request)
	{

		HttpSession session = request.getSession();
		EcwaSession ecwaSession = (EcwaSession) session.getAttribute("ecwaSession");
		if (ecwaSession == null)
		{
			ecwaSession = new EcwaSession();
			session.setAttribute("ecwaSession", ecwaSession);
			logger.info("Ecwa session has been created....");
		}
		return ecwaSession;
	}

	/**
	 * @return
	 */
	protected EcwaUserDetails getAuthenticatedPrincipal()
	{
		EcwaUserDetails userDetails = (EcwaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
	}

	protected String getUserId()
	{
		return getAuthenticatedPrincipal().getUsername();
	}

}
