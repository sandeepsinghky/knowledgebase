package nc.dhhs.nccss.acts.ecwa.web.servlets.listeners;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import nc.dhhs.nccss.acts.ecwa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecwa.web.service.CodeLookUpService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * 
 * 
 * @author Phani Konuru
 *
 */

@WebListener
public class ApplicationStartUpListener implements ServletContextListener
{

	protected final Logger		logger	= Logger.getLogger(ApplicationStartUpListener.class);

	@Autowired
	protected CodeLookUpService	codeLookUpService;

	
	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event)
	{

		logger.debug("#######---- initialize servlet context -----");
		// add initialization code here

		ServletContext ctx = event.getServletContext();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		// Load look up tables at application startup once and place it on
		// context to be used for all users on the screens.
		// Application restart is required to refresh the lookup tables data

		loadLookUp(ctx);
		
		//get annual service fees		
		getApplFees(ctx);

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		logger.debug("#######---- destroying servlet context -----");
		// clean up resources
	}

	private void loadLookUp(ServletContext ctx)
	{
		// Load look up tables at application startup
		try
		{
			//look up for County 
			List<CodeLookUp> cntyLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_COUNTY_VALUE);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_COUNTY_LIST, cntyLookup);
			
			//look up for State 
			List<CodeLookUp> stateLookup = codeLookUpService.getCodeLookup(AppConstants.CODE_LOOKUP_STATE_VALUE);
			ctx.setAttribute(AppConstants.CODE_LOOKUP_STATE_LIST, stateLookup);
	}
		catch (Exception e)
		{
			logger.error("########error in startup" + e.getMessage());
		}

	}
	
	//get annual service fees
	/**
	 * @param ctx
	 */
	private void getApplFees(ServletContext ctx)
	{
		logger.info("ApplicationStartUpListener: getApplFees");
		String annualFees = "";
		String dsbrsdAmt = "";
		String revDt = "";
		try
		{
			String applFeesStr = codeLookUpService.getApplFees();
			logger.debug("#####Annual fees string = "+applFeesStr);
			if (applFeesStr != null && !applFeesStr.equals(""))
			{
				StringTokenizer annualTokenTemp = new StringTokenizer(applFeesStr, "REV");
				if (annualTokenTemp.hasMoreTokens())
				{
					applFeesStr = annualTokenTemp.nextToken();
					if (annualTokenTemp.hasMoreTokens())
					{
						revDt = annualTokenTemp.nextToken();
					}
					StringTokenizer annualToken = new StringTokenizer(applFeesStr, "F");
					{
						dsbrsdAmt = annualToken.nextToken();
						StringTokenizer dsbrsdAmtTokens = new StringTokenizer(dsbrsdAmt, "COL");
						if (dsbrsdAmtTokens.hasMoreTokens())
						{
							dsbrsdAmtTokens.nextToken();
							dsbrsdAmt = dsbrsdAmtTokens.nextToken();
						}
						dsbrsdAmt = removeLeadingZero(dsbrsdAmt);
						dsbrsdAmt = getAmtDecimal(dsbrsdAmt);

						if (annualToken.hasMoreTokens())
						{
							annualFees = annualToken.nextToken();
							annualFees = removeLeadingZero(annualFees);
							annualFees = getAmtDecimal(annualFees);
						}

					}
				}
			}
			ctx.setAttribute(AppConstants.ANNUAL_FEES, annualFees);
			ctx.setAttribute(AppConstants.PAYMT_DISBURSED_AMT, dsbrsdAmt);
			ctx.setAttribute(AppConstants.APPL_REV_DATE, revDt);
		}
		catch (Exception e)
		{
			logger.error("########error in startup, getApplFees: " + e.getMessage());
		}
	}

	/**
	 * @param value
	 * @return
	 */
	private String getAmtDecimal(String value)
	{
		String amtWithDecimal = "";
		if (value != null && !value.equals("") && value.endsWith("00"))
		{
			amtWithDecimal = value.substring(0, value.length() - 2);
		}
		else
		{
			String decimal = value.substring(value.length() - 2);
			amtWithDecimal = value.substring(0, value.length() - 2) + "." + decimal;
		}
		return amtWithDecimal;
	}

	/**
	 * @param value
	 * @return
	 */
	private String removeLeadingZero(String value)
	{
		if (value != null && !value.equals(""))
		{
			while (value.startsWith("0"))
			{
				value = value.substring(1);
			}
		}

		return value;
	}
}