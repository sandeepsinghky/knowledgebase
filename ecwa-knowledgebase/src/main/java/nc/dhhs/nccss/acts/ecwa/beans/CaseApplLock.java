package nc.dhhs.nccss.acts.ecwa.beans;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 * 
 */

public class CaseApplLock implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3240458758005542767L;

	private String				applicationId		= "";
	
	private String				lockedBy		= "";
	
	private Timestamp tsLock =null;
	
	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

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

	public String getLockedBy()
	{
		return lockedBy;
	}

	public void setLockedBy(String lockedBy)
	{
		this.lockedBy = lockedBy;
	}

	public Timestamp getTsLock()
	{
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return tsLock;
	}

	public void setTsLock(Timestamp tsLock)
	{
		this.tsLock = tsLock;
	}



}
