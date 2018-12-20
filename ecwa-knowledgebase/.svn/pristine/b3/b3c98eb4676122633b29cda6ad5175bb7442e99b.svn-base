package nc.dhhs.nccss.acts.ecwa.beans;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author pkonuru
 *
 */

public class CaseAppSearch implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7653839768051927554L;

	private String				refNum				= "";

	private String				fName				= "";

	private String				lName				= "";

	private String				ssnNum				= "";

	private String				cnty				= "";

	private Date				dtFrom				= null;

	private Date				dtTo				= null;

	private String				ncId				= "";

	private String				searchType			= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat			DATE_FORMAT_PAGE	= new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * @return the refNum
	 */
	public String getRefNum()
	{
		return refNum;
	}

	/**
	 * @param refNum
	 *            the refNum to set
	 */
	public void setRefNum(String refNum)
	{
		this.refNum = refNum;
	}

	/**
	 * @return the fName
	 */
	public String getFName()
	{
		return fName;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setFName(String fName)
	{
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getLName()
	{
		return lName;
	}

	/**
	 * @param lName
	 *            the lName to set
	 */
	public void setLName(String lName)
	{
		this.lName = lName;
	}

	/**
	 * @return the ssnNum
	 */
	public String getSsnNum()
	{
		return ssnNum;
	}

	/**
	 * @param ssnNum
	 *            the ssnNum to set
	 */
	public void setSsnNum(String ssnNum)
	{
		this.ssnNum = ssnNum;
	}

	/**
	 * @return the cnty
	 */
	public String getCnty()
	{
		return cnty;
	}

	/**
	 * @param cnty
	 *            the cnty to set
	 */
	public void setCnty(String cnty)
	{
		this.cnty = cnty;
	}

	/**
	 * @return the dtFrom
	 */
	public String getDtFrom()
	{
		if (dtFrom == null) return null;
		else
			return DATE_FORMAT.format(dtFrom).trim();
	}

	/**
	 * @param dtFrom
	 *            the dtFrom to set
	 */
	public void setDtFrom(String dtFrom)
	{
		try
		{
			this.dtFrom = DATE_FORMAT_PAGE.parse(dtFrom);
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * @return the dtTo
	 */
	public String getDtTo()
	{
		if (dtTo == null) return null;
		else
			return DATE_FORMAT.format(dtTo).trim();
	}

	/**
	 * @param dtTo
	 *            the dtTo to set
	 */
	public void setDtTo(String dtTo)
	{

		try
		{
			this.dtTo = DATE_FORMAT_PAGE.parse(dtTo);
		}
		catch (Exception e)
		{
		}
	}

	public String getNcId()
	{
		return ncId;
	}

	public void setNcId(String ncId)
	{
		this.ncId = ncId;
	}

	public String getSearchType()
	{
		return searchType;
	}

	public void setSearchType(String searchType)
	{
		this.searchType = searchType;
	}

}
