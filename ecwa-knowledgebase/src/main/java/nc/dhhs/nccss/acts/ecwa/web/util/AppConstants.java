package nc.dhhs.nccss.acts.ecwa.web.util;

/**
 * @author Vijay Peddapalli
 *
 */
public class AppConstants
{
	
	public static final String  MFA_GROUP_DN					=	"DHHS-CSS-MFA";
	
	public static final String	ECWA_CASE_WORKER_SEARCH			= "ECWA_caseWorkerSearch";
	
	public static final String	ECWA_CASE_WORKER_LIST			= "ECWA_caseWorkerList";
	
	public static final String	ECWA_CASE_WORKER_DETAIL			= "ECWA_caseWorkerDetail";
	
	public static final String	ECWA_CASE_WORKER_MODIFY			= "ECWA_caseWorkerModify";
	
	public static final String	ECWA_caseWorkerCreate			= "ECWA_caseWorkerCreate";
								
	public static final String	LOGIN							= "login";

	public static final String	LOGOUT							= "logout";

	public static final String	PAGE_NOT_FOUND					= "404";

	public static final String	ACCESS_DENIED					= "403";

	public static final String	SERVER_ERROR					= "500";

	public static final String	ERROR							= "error";

	public static final String	ECWA_REPORTS					= "ECWA_reports";

	public static final String	ECWA_CASE_APPLN_CP_INFO			= "ECWA_caseApplicationCPInfo";

	public static final String	ECWA_CASE_APPLN_NCP_DETAILS		= "ECWA_caseApplicationNCPDetails";

	public static final String	ECWA_CASE_APPLN_NCP_INFO		= "ECWA_caseApplicationNCPInfo";

	public static final String	ECWA_CASE_APPLN_CHILD_INFO		= "ECWA_caseApplicationChildInfo";

	public static final String	ECWA_CASE_APPLN_CHILD_DETAILS	= "ECWA_caseApplicationChildDetails";

	public static final String	ECWA_CASE_APPLN_LIST			= "ECWA_caseApplicationList";

	public static final String	ECWA_CASE_APPLN_PART_LIST		= "ECWA_caseApplicationPartList";

	public static final String	ECWA_CASE_ACTS_PART_LIST		= "ECWA_caseActsPartList";

	public static final String	ECWA_CASE_ACTS_PART_DETAILS		= "ECWA_caseActsPartDetails";

	public static final String	ECWA_CASE_ACTS_CREATE			= "ECWA_caseActsCreate";

	public static final String	ECWA_CASE_APPLN_PART_UPDATE		= "ECWA_caseApplicationPartUpdate";

	public static final String	ECWA_CASE_APPLN_SEARCH			= "ECWA_caseApplicationSearch";

	public static final String	ECWA_CASE_APPLN_MISC_INFO		= "ECWA_caseApplicationMiscInfo";

	public static final String	ECWA_LOGGEDIN_HOME				= "ECWA_loggedInHome";

	//dao constants
	public static final String	OPERATION_INSERT				= "INSERT";

	public static final String	OPERATION_UPDATE				= "UPDATE";

	public static final String	OPERATION_DELETE				= "DELETE";

	public static final String	OPERATION_SUCCESS				= "0000";

	public static final String	APP_FIELD_NCID					= "NCID";

	public static final String	APP_FIELD_CNTY					= "CNTY";

	public static final String	DEFAULT_DATE					= "0001-01-01";

	public static final String	INTERFACE_CODE					= "WCSSAI";

	public static final String	DB_UPDATE						= "update";

	public static final String	DB_CREATE						= "create";

	//string to separate field values passed to the SP
	public static final String	FLD_SEPARATOR					= "@#%";

	//screen constants
	public static final String	CP_PARTICIPANT_TYPE				= "1";

	public static final String	NCP_PARTICIPANT_TYPE			= "2";

	public static final String	CHLD_PARTICIPANT_TYPE			= "3";

	//Application status
	public static final String	APP_STATUS_ACTIVE				= "1";

	public static final String	APP_STATUS_INACTIVE				= "2";

	public static final String	APP_STATUS_SUBMIT				= "3";

	public static final String	APP_STATUS_COMPLETE				= "4";

	public static final String	APP_STATUS						= "applStatus";

	//session constants

	public static final String	SELECTED_APPLICATION			= "selectedApp";

	public static final String	IS_APPLICATION_LOCKED			= "isApplLocked";

	public static final String	APPLICATION_LOCKED_BY			= "applLockedBy";

	public static final String	APPLICATION_LOCKED_BY_NAME		= "applLockedByName";

	public static final String	SELECTED_APP_PART				= "selectedParticipant";

	public static final String	SELECTED_ACTS_PART				= "selectedActsParticipant";

	public static final String	USER_LOGIN_NAME					= "userLoginName";

	public static final String	WRKR_NAME						= "wrkrName";

	public static final String	USER_EMAIL						= "userEmail";

	public static final String	PAGE_DEFAULT_DATE				= "01/01/0001";

	public static final String	WRKR_CNTY						= "wrkrCntyLst";

	//login messages
	public static final String	USER_NOT_AUTHORIZED_MSG			= "User is not authorized to access the application.";

	public static final String	USER_NOT_AUTHORIZED_CODE		= "50";

	public static final String	APP_UNAVAILABLE_MSG				= "Worker portal is not available at this time.";

	public static final String	APP_UNAVAILABLE_CODE			= "60";

	public static final String	NCID_PUBLIC_ERROR				= "NCID used was not state employee NCID.";

	public static final String	NCID_ERROR						= "Error while login.";

	public static final String	NCID_AUTH_SUCCESS				= "0";

	public static final String	NCID_USER_LOCKED_CODE			= "113";

	public static final String	NCID_PASSWORD_EXPIRED			= "111";

	public static final String	LOGIN_ERROR_MSG					= "loginError";

	//Contact
	public static final String	CONTACT_TYPE_HOME				= "1";

	public static final String	CONTACT_TYPE_WORK				= "2";

	public static final String	CONTACT_TYPE_CELL				= "3";

	public static final String	CONTACT_TYPE_EMAIL				= "4";

	// Address
	public static final String	ADDRESS_MAILING					= "1";

	public static final String	ADDRESS_RESIDENTIAL				= "2";

	//Income Other/Assets

	public static final String	INCOME_OTHER					= "6";

	//code table

	public static final String	CODE_LOOKUP_COUNTY_VALUE		= "COUNTY";

	public static final String	CODE_LOOKUP_COUNTY_LIST			= "countyLookup";

	public static final String	CONDITION_TRUE					= "y";

	public static final String	CONDITION_FALSE					= "n";

	//case creation messages

	public static final String	DUPLICATE_CASE_CODE				= "20";

	public static final String	DUPLICATE_CASE_ERROR			= " Case already exists in ACTS for selected participants.";

	public static final String	CODE_LOOKUP_STATE_VALUE			= "STATEN";

	public static final String	CODE_LOOKUP_CTRY_VALUE			= "COUNTRY";

	public static final String	CODE_LOOKUP_STATE_LIST			= "stateLookup";

	public static final String	CODE_LOOKUP_CTRY_LIST			= "countryLookup";

	//Exceptions

	public static final String	MAIL_ERROR_SUBJECT				= "E-ChildSupport worker portal Exception";

	public static final String	MAIL_FROM						= "e.childsupport@dhhs.nc.gov";

	public static final String	APP_ERROR						= "apperror";

	public static final String	APPLICATION_ERROR				= "We are having technical problems. Please try in a few minutes. The Technical Team has been notified.";

	public static final String	USER_SIGN_TYPE_1				= "ARRS";																									// Application Rights
	// Application Rights &
	// Responsibilities
	// Signature

	public static final String	USER_SIGN_TYPE_2				= "APARS";																									// Application Public
	// Application Assistance
	// Recipient
	// Signature

	public static final String	USER_SIGN_TYPE_3				= "ASBS";																									// Application
	// Application Submission
	// Signature

	// application feess

	public static final String	ANNUAL_FEES						= "annualFees";

	public static final String	PAYMT_DISBURSED_AMT				= "disbrsdAmt";

	public static final String	APPL_REV_DATE					= "revDt";

}
