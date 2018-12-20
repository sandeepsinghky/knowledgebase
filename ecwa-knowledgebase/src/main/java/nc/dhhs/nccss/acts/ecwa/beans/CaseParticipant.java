package nc.dhhs.nccss.acts.ecwa.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author pkonuru
 *
 */
public class CaseParticipant implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5878882458381095791L;

	private String				applicationId		= "";

	private String				applicantId			= "";

	private String				partId				= "";

	private String				partIdResolved		= "";

	private String				applicantFNm		= "";

	private String				applicantLNm		= "";

	private String				applicantMNm		= "";

	private String				applicantSufix		= "";

	private String				applicantMdnNm		= "";

	private String				applicantGender		= "";

	private String				applicantGenderDesc	= "";

	private Date				brthDt;

	private String				brthDtStr			= "";

	private String				ssnNb				= "";

	private String				ethncGrp			= "";

	private String				ethncGrpDesc		= "";

	private String				langPref			= "";

	private String				langPrefDesc		= "";

	private String				specialAssist		= "";

	private String				specialAssistStr	= "";

	private double				income				= 0;

	private String				protOrd				= "";

	private String				applicantAliasNm	= "";

	private Date				lastUpdatDt;

	private String				lastUpdatDtStr		= "";

	private String				partType			= "";

	private String				partTypeDesc		= "";

	private String				partPrntGrdian		= "";

	private String				resCnty				= "";

	private String				isPartNameSelected	= "";

	private String				isPartSSNSelected	= "";

	private String				isPartDOBSelected	= "";

	private String				isPartRaceSelected	= "";

	private String				isPartGndrSelected	= "";

	private String				isPartLangSelected	= "";

	private String				isPartHphSelected	= "";

	private String				isPartWphSelected	= "";

	private String				isPartAddrSelected	= "";

	private String				specialAssistOt		= "";

	private boolean				partSelected		= false;

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat			DATE_FORMAT_SCREEN	= new SimpleDateFormat("MM/dd/yyyy");

	NumberFormat				formatter			= new DecimalFormat("#0.00");

	private String				empStatus			= "";

	/**
	 * @return the applicationId
	 */
	public String getApplicationId()
	{
		return applicationId.trim();
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}

	/**
	 * @return the applicantId
	 */
	public String getApplicantId()
	{
		return applicantId.trim();
	}

	/**
	 * @param applicantId
	 *            the applicantId to set
	 */
	public void setApplicantId(String applicantId)
	{
		this.applicantId = applicantId;
	}

	/**
	 * @return the partId
	 */
	public String getPartId()
	{
		return partId.trim();
	}

	/**
	 * @param partId
	 *            the partId to set
	 */
	public void setPartId(String partId)
	{
		this.partId = partId;
	}

	/**
	 * @return the partIdResolved
	 */
	public String getPartIdResolved()
	{
		return partIdResolved.trim();
	}

	/**
	 * @param partIdResolved
	 *            the partIdResolved to set
	 */
	public void setPartIdResolved(String partIdResolved)
	{
		this.partIdResolved = partIdResolved;
	}

	/**
	 * @return the applicantFNm
	 */
	public String getApplicantFNm()
	{
		return applicantFNm.trim();
	}

	/**
	 * @param applicantFNm
	 *            the applicantFNm to set
	 */
	public void setApplicantFNm(String applicantFNm)
	{
		this.applicantFNm = applicantFNm;
	}

	/**
	 * @return the applicantLNm
	 */
	public String getApplicantLNm()
	{
		return applicantLNm.trim();
	}

	/**
	 * @param applicantLNm
	 *            the applicantLNm to set
	 */
	public void setApplicantLNm(String applicantLNm)
	{
		this.applicantLNm = applicantLNm;
	}

	/**
	 * @return the applicantMNm
	 */
	public String getApplicantMNm()
	{
		return applicantMNm.trim();
	}

	/**
	 * @param applicantMNm
	 *            the applicantMNm to set
	 */
	public void setApplicantMNm(String applicantMNm)
	{
		this.applicantMNm = applicantMNm;
	}

	/**
	 * @return the applicantSufix
	 */
	public String getApplicantSufix()
	{
		return applicantSufix.trim();
	}

	/**
	 * @param applicantSufix
	 *            the applicantSufix to set
	 */
	public void setApplicantSufix(String applicantSufix)
	{
		this.applicantSufix = applicantSufix;
	}

	/**
	 * @return the applicantMdnNm
	 */
	public String getApplicantMdnNm()
	{
		return applicantMdnNm.trim();
	}

	/**
	 * @param applicantMdnNm
	 *            the applicantMdnNm to set
	 */
	public void setApplicantMdnNm(String applicantMdnNm)
	{
		this.applicantMdnNm = applicantMdnNm;
	}

	/**
	 * @return the applicantGender
	 */
	// report uses below method
	public String getApplicantSex()
	{
		return applicantGender.trim();
	}

	public String getApplicantGender()
	{
		return applicantGender.trim();
	}

	/**
	 * @param applicantGender
	 *            the applicantGender to set
	 */
	public void setApplicantGender(String applicantGender)
	{
		this.applicantGender = applicantGender;
	}

	/**
	 * @return the applicantGenderDesc
	 */
	public String getApplicantGenderDesc()
	{
		return applicantGenderDesc.trim();
	}

	/**
	 * @param applicantGenderDesc
	 *            the applicantGenderDesc to set
	 */
	public void setApplicantGenderDesc(String applicantSexDesc)
	{
		this.applicantGenderDesc = applicantSexDesc;
	}

	/**
	 * @return the brthDt
	 */
	public Date getBrthDt()
	{
		return brthDt;
	}

	/**
	 * @param brthDt
	 *            the brthDt to set
	 */
	public void setBrthDt(Date brthDt)
	{
		this.brthDt = brthDt;
	}

	/**
	 * @return
	 */
	public String getBrthDtStr()
	{
		if (brthDt == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(brthDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(brthDt).trim();
	}

	/**
	 * @return
	 */
	public String getBrthDtStrPg()
	{
		if (brthDt == null) return "";
		else
			return DATE_FORMAT_SCREEN.format(brthDt).trim().equals(AppConstants.PAGE_DEFAULT_DATE) ? "" : DATE_FORMAT_SCREEN.format(brthDt).trim();
	}

	/**
	 * @return the ssnNb
	 */
	public String getSsnNb()
	{
		return ssnNb.trim();
	}

	/**
	 * @param ssnNb
	 *            the ssnNb to set
	 */
	public void setSsnNb(String ssnNb)
	{
		this.ssnNb = ssnNb;
	}

	/**
	 * @return the ethncGrp
	 */
	public String getEthncGrp()
	{
		return ethncGrp.trim();
	}

	/**
	 * @param ethncGrp
	 *            the ethncGrp to set
	 */
	public void setEthncGrp(String ethncGrp)
	{
		this.ethncGrp = ethncGrp;
	}

	/**
	 * @return the ethncGrpDesc
	 */
	public String getEthncGrpDesc()
	{
		return ethncGrpDesc.trim();
	}

	/**
	 * @param ethncGrpDesc
	 *            the ethncGrpDesc to set
	 */
	public void setEthncGrpDesc(String ethncGrpDesc)
	{
		this.ethncGrpDesc = ethncGrpDesc;
	}

	/**
	 * @return the langPref
	 */
	public String getLangPref()
	{
		return langPref.trim();
	}

	/**
	 * @param langPref
	 *            the langPref to set
	 */
	public void setLangPref(String langPref)
	{
		this.langPref = langPref;
	}

	/**
	 * @return the langPrefDesc
	 */
	public String getLangPrefDesc()
	{
		return langPrefDesc.trim();
	}

	/**
	 * @param langPrefDesc
	 *            the langPrefDesc to set
	 */
	public void setLangPrefDesc(String langPrefDesc)
	{
		this.langPrefDesc = langPrefDesc;
	}

	/**
	 * @return the specialAssist
	 */
	public String getSpecialAssist()
	{
		return specialAssist.trim();
	}

	/**
	 * @param specialAssist
	 *            the specialAssist to set
	 */
	public void setSpecialAssist(String specialAssist)
	{
		this.specialAssist = specialAssist;
	}

	public String getSpecialAssistStr()
	{
		return specialAssistStr;
	}

	public void setSpecialAssistStr(String specialAssistStr)
	{
		this.specialAssistStr = specialAssistStr;
	}

	/**
	 * @return the income
	 */
	public String getIncomeStr()
	{
		return formatter.format(income);
	}

	/**
	 * @return the income
	 */
	public double getIncome()
	{
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(double income)
	{
		this.income = income;
	}

	/**
	 * @return the protOrd
	 */
	public String getProtOrd()
	{
		return protOrd.trim();
	}

	/**
	 * @param protOrd
	 *            the protOrd to set
	 */
	public void setProtOrd(String protOrd)
	{
		this.protOrd = protOrd;
	}

	/**
	 * @return the applicantAliasNm
	 */
	public String getApplicantAliasNm()
	{
		return applicantAliasNm.trim();
	}

	/**
	 * @param applicantAliasNm
	 *            the applicantAliasNm to set
	 */
	public void setApplicantAliasNm(String applicantAliasNm)
	{
		this.applicantAliasNm = applicantAliasNm;
	}

	/**
	 * @return the lastUpdatDt
	 */
	public Date getLastUpdatDt()
	{
		return lastUpdatDt;
	}

	/**
	 * @return
	 */
	public String getLastUpdatDtStr()
	{
		if (lastUpdatDt == null) return null;
		else
			return DATE_FORMAT.format(lastUpdatDt).trim();
	}

	/**
	 * @param lastUpdatDt
	 */
	public void setLastUpdatDt(Date lastUpdatDt)
	{
		this.lastUpdatDt = lastUpdatDt;
	}

	/**
	 * @return the partType
	 */
	public String getPartType()
	{
		return partType.trim();
	}

	/**
	 * @param partType
	 *            the partType to set
	 */
	public void setPartType(String partType)
	{
		this.partType = partType;
	}

	/**
	 * @return the partPrntGrdian
	 */
	public String getPartPrntGrdian()
	{
		return partPrntGrdian.trim();
	}

	/**
	 * @param partPrntGrdian
	 *            the partPrntGrdian to set
	 */
	public void setPartPrntGrdian(String partPrntGrdian)
	{
		this.partPrntGrdian = partPrntGrdian;
	}

	/**
	 * @return
	 */
	public String getPartTypeDesc()
	{
		return partTypeDesc;
	}

	/**
	 * @param partTypeDesc
	 */
	public void setPartTypeDesc(String partTypeDesc)
	{
		this.partTypeDesc = partTypeDesc;
	}

	/**
	 * @return
	 */
	public String getIsPartNameSelected()
	{
		return isPartNameSelected;
	}

	/**
	 * @param isPartNameSelected
	 */
	public void setIsPartNameSelected(String isPartNameSelected)
	{
		this.isPartNameSelected = isPartNameSelected;
	}

	/**
	 * @return
	 */
	public String getIsPartSSNSelected()
	{
		return isPartSSNSelected;
	}

	/**
	 * @param isPartSSNSelected
	 */
	public void setIsPartSSNSelected(String isPartSSNSelected)
	{
		this.isPartSSNSelected = isPartSSNSelected;
	}

	/**
	 * @return
	 */
	public String getIsPartDOBSelected()
	{
		return isPartDOBSelected;
	}

	/**
	 * @param isPartDOBSelected
	 */
	public void setIsPartDOBSelected(String isPartDOBSelected)
	{
		this.isPartDOBSelected = isPartDOBSelected;
	}

	/**
	 * @return
	 */
	public String getIsPartRaceSelected()
	{
		return isPartRaceSelected;
	}

	/**
	 * @param isPartRaceSelected
	 */
	public void setIsPartRaceSelected(String isPartRaceSelected)
	{
		this.isPartRaceSelected = isPartRaceSelected;
	}

	/**
	 * @return
	 */
	public String getIsPartGndrSelected()
	{
		return isPartGndrSelected;
	}

	/**
	 * @param isPartGndrSelected
	 */
	public void setIsPartGndrSelected(String isPartGndrSelected)
	{
		this.isPartGndrSelected = isPartGndrSelected;
	}

	/**
	 * @return
	 */
	public String getIsPartLangSelected()
	{
		return isPartLangSelected;
	}

	/**
	 * @param isPartLangSelected
	 */
	public void setIsPartLangSelected(String isPartLangSelected)
	{
		this.isPartLangSelected = isPartLangSelected;
	}

	/**
	 * @return
	 */
	public String getIsPartHphSelected()
	{
		return isPartHphSelected;
	}

	/**
	 * @param isPartHphSelected
	 */
	public void setIsPartHphSelected(String isPartHphSelected)
	{
		this.isPartHphSelected = isPartHphSelected;
	}

	/**
	 * @return
	 */
	public String getIsPartWphSelected()
	{
		return isPartWphSelected;
	}

	/**
	 * @param isPartWphSelected
	 */
	public void setIsPartWphSelected(String isPartWphSelected)
	{
		this.isPartWphSelected = isPartWphSelected;
	}

	/**
	 * @return
	 */
	public String getIsPartAddrSelected()
	{
		return isPartAddrSelected;
	}

	/**
	 * @param isPartAddrSelected
	 */
	public void setIsPartAddrSelected(String isPartAddrSelected)
	{
		this.isPartAddrSelected = isPartAddrSelected;
	}

	/**
	 * @return
	 */
	public boolean isPartSelected()
	{
		return partSelected;
	}

	/**
	 * @param partSelected
	 */
	public void setPartSelected(boolean partSelected)
	{
		this.partSelected = partSelected;
	}

	/**
	 * @return the specialAssistOt
	 */
	public String getSpecialAssistOt()
	{
		return specialAssistOt;
	}

	/**
	 * @param specialAssistOt
	 *            the specialAssistOt to set
	 */
	public void setSpecialAssistOt(String specialAssistOt)
	{
		this.specialAssistOt = specialAssistOt;
	}

	/**
	 * @return the ResCnty
	 */
	public String getResCnty()
	{
		return resCnty;
	}

	/**
	 * @param ResCnty
	 *            the ResCnty to set
	 */
	public void setResCnty(String partResCnty)
	{
		this.resCnty = partResCnty;
	}

	/**
	 * @return the empStatus
	 */
	public String getEmpStatus()
	{
		return empStatus;
	}

	/**
	 * @param empStatus
	 *            the empStatus to set
	 */
	public void setEmpStatus(String empStatus)
	{
		this.empStatus = empStatus;
	}

}