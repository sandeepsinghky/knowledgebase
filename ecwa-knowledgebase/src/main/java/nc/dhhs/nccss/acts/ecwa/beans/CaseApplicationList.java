package nc.dhhs.nccss.acts.ecwa.beans;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 * 
 */

public class CaseApplicationList implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -400591535734667100L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				firstName			= "";

	private String				lastName			= "";

	private String				middleName			= "";

	private String				applicantName		= "";
	
	private Date				applicantDOB;

	private String				applicantSsn		= "";

	private String				ncId				= "";

	private String				emailId				= "";

	private Date				applSubmitDt;

	private String				applStatus			= "";

	private String				applStatusDesc		= "";

	private String				applCounty			= "";

	private String				applCountyDesc		= "";

	private Date				applCmpltDt;

	private String				isApplLocked		= "";

	private String				wrkrNcId			= "";

	private String				wrkrName			= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");
	
	SimpleDateFormat			DATE_FORMAT_SCREEN	= new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * @return
	 */
	public String getApplicationId()
	{
		return applicationId.trim();
	}

	/**
	 * @param applicationId
	 */
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}

	/**
	 * @return
	 */
	public String getApplicantId()
	{
		return applicantId.trim();
	}

	/**
	 * @param applicantId
	 */
	public void setApplicantId(String applicantId)
	{
		this.applicantId = applicantId;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName.trim();
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName.trim();
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @return
	 */
	public String getNcId()
	{
		return ncId.trim();
	}

	/**
	 * @param ncId
	 */
	public void setNcId(String ncId)
	{
		this.ncId = ncId;
	}

	/**
	 * @return
	 */
	public String getEmailId()
	{
		return emailId.trim();
	}

	/**
	 * @param emailId
	 */
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	/**
	 * @return
	 */
	public Date getApplSubmitDt()
	{
		return applSubmitDt;
	}

	/**
	 * @return
	 */
	public String getApplSubmitDtStr()
	{
		if (this.applSubmitDt == null) 
			return "";
		else
			return DATE_FORMAT_SCREEN.format(this.applSubmitDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(this.applSubmitDt).trim();
	}

	/**
	 * @param applSubmitDt
	 */
	public void setApplSubmitDt(Date applSubmitDt)
	{
		this.applSubmitDt = applSubmitDt;
	}

	/**
	 * @return
	 */
	public String getApplStatus()
	{
		return applStatus.trim();
	}

	/**
	 * @param applStatus
	 */
	public void setApplStatus(String applStatus)
	{
		this.applStatus = applStatus;
	}

	/**
	 * @return the applStatusDesc
	 */
	public String getApplStatusDesc()
	{
		return applStatusDesc.trim();
	}

	/**
	 * @param applStatusDesc
	 *            the applStatusDesc to set
	 */
	public void setApplStatusDesc(String applStatusDesc)
	{
		this.applStatusDesc = applStatusDesc;
	}

	/**
	 * @return
	 */
	public String getApplCounty()
	{
		return applCounty.trim();
	}

	/**
	 * @param applCounty
	 */
	public void setApplCounty(String applCounty)
	{
		this.applCounty = applCounty;
	}

	/**
	 * @return the applCountyDesc
	 */
	public String getApplCountyDesc()
	{
		return applCountyDesc.trim();
	}

	/**
	 * @param applCountyDesc
	 *            the applCountyDesc to set
	 */
	public void setApplCountyDesc(String applCountyDesc)
	{
		this.applCountyDesc = applCountyDesc;
	}


	/**
	 * @return
	 */
	public Date getApplCmpltDt()
	{
		return applCmpltDt;
	}

	/**
	 * @return
	 */
	public String getApplCmpltDtStr()
	{
		if (this.applCmpltDt == null) 
			return "";
		else
			return DATE_FORMAT_SCREEN.format(this.applCmpltDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(this.applCmpltDt).trim();
	}

	/**
	 * @param applCmpltDt
	 */
	public void setApplCmpltDt(Date applCmpltDt)
	{
		this.applCmpltDt = applCmpltDt;
	}

	public String getApplicantName()
	{
		return applicantName;
	}

	public void setApplicantName(String applicantName)
	{
		this.applicantName = applicantName;
	}

	public String getIsApplLocked()
	{
		return isApplLocked;
	}

	public void setIsApplLocked(String isApplLocked)
	{
		this.isApplLocked = isApplLocked;
	}

	public String getWrkrNcId()
	{
		return wrkrNcId;
	}

	public void setWrkrNcId(String wrkrNcId)
	{
		this.wrkrNcId = wrkrNcId;
	}

	public String getWrkrName()
	{
		return wrkrName;
	}

	public void setWrkrName(String wrkrName)
	{
		this.wrkrName = wrkrName;
	}

	public String getApplicantSsn()
	{
		return applicantSsn;
	}

	public void setApplicantSsn(String applicantSsn)
	{
		this.applicantSsn = applicantSsn;
	}

	public String getApplicantDOB()
	{
		if (this.applicantDOB == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(this.applicantDOB).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(this.applicantDOB).trim();
	}

	public void setApplicantDOB(Date applicantDOB)
	{
		this.applicantDOB = applicantDOB;
	}

}
