/**
 * 
 */
package nc.dhhs.nccss.acts.ecwa.web.config;

import java.util.ResourceBundle;

/**
 * @author Vijay Peddapalli
 *
 */
public class WebsiteConfiguration
{

	private static Long		businessAreaId	= null;

	private static String	appIDReqNCID	= "";

	private static String	appPwdReqNCID	= "";

	private static String	searchOp		= "";

	private static String	dbSchema		= "";
	
	private static String dbSchemaSQL = "";
	
	private static String primaryEmail = "";
	
	private static String  emailNotify = "";


	/**
	 * @return the dbSchemaSQL
	 */

	static
	{
		ResourceBundle rb = null;
		try
		{
			rb = ResourceBundle.getBundle("config");

			if (rb.containsKey("appIDReqNCID"))
			{
				appIDReqNCID = rb.getString("appIDReqNCID");
			}

			if (rb.containsKey("appPwdReqNCID"))
			{
				appPwdReqNCID = rb.getString("appPwdReqNCID");
			}

			if (rb.containsKey("searchOp"))
			{
				searchOp = rb.getString("searchOp");
			}

			if (rb.containsKey("businessAreaId"))
			{
				setBusinessAreaId(Long.parseLong(rb.getString("businessAreaId")));
			}

			if (rb.containsKey("dbSchema"))
			{
				setDbSchema(rb.getString("dbSchema"));
			}
			
			if (rb.containsKey("dbSchemaSQL")) {
				setDbSchemaSQL(rb.getString("dbSchemaSQL"));
			}
			
			if (rb.containsKey("application.primaryEmail")) {
				setPrimaryEmail(rb.getString("application.primaryEmail"));
			}
			
			if (rb.containsKey("application.emailMsgNotify")) {
				setEmailNotify(rb.getString("application.emailMsgNotify"));
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// method just used to load properties
	public static void init()
	{
	}

	/**
	 * @return
	 */
	public static String getAppIDReqNCID()
	{
		return appIDReqNCID;
	}

	public static void setAppIDReqNCID(String appIDReqNCID)
	{
		WebsiteConfiguration.appIDReqNCID = appIDReqNCID;
	}

	public static String getAppPwdReqNCID()
	{
		return appPwdReqNCID;
	}

	/**
	 * @param appPwdReqNCID
	 */
	public static void setAppPwdReqNCID(String appPwdReqNCID)
	{
		WebsiteConfiguration.appPwdReqNCID = appPwdReqNCID;
	}

	/**
	 * @return
	 */
	public static String getSearchOp()
	{
		return searchOp;
	}

	/**
	 * @return the dbSchema
	 */
	public static String getDbSchema()
	{
		return dbSchema;
	}

	/**
	 * @param dbSchema
	 *            the dbSchema to set
	 */
	public static void setDbSchema(String dbSchema)
	{
		WebsiteConfiguration.dbSchema = dbSchema;
	}

	/**
	 * @param searchOp
	 */
	public static void setSearchOp(String searchOp)
	{
		WebsiteConfiguration.searchOp = searchOp;
	}

	/**
	 * @return the applicationId
	 */
	public static Long getBusinessAreaId()
	{
		return businessAreaId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public static void setBusinessAreaId(Long businessAreaId)
	{
		WebsiteConfiguration.businessAreaId = businessAreaId;
	}

	public static String getDbSchemaSQL()
	{
		return dbSchemaSQL;
	}

	public static void setDbSchemaSQL(String dbSchemaSQL)
	{
		WebsiteConfiguration.dbSchemaSQL = dbSchemaSQL;
	}

	public static String getPrimaryEmail()
	{
		return primaryEmail;
	}

	public static void setPrimaryEmail(String primaryEmail)
	{
		WebsiteConfiguration.primaryEmail = primaryEmail;
	}

	public static String getEmailNotify()
	{
		return emailNotify;
	}

	public static void setEmailNotify(String emailNotify)
	{
		WebsiteConfiguration.emailNotify = emailNotify;
	}

}
