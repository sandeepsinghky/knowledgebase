package nc.dhhs.nccss.acts.ecwa.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 * 
 */

public class CaseActsParticipant implements Serializable
{

	private static final long	serialVersionUID	= 436524482078390108L;

	private String				partId				= "";

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

	private String				applicantAliasNm	= "";

	private String				partType			= "";

	private String				partTypeDesc		= "";

	private String				partNmSTAT			= "";

	private String				partNbCase			= "";

	private String				partStat			= "";

	private String				partCaseType		= "";

	private String				partCaseStat		= "";

	private String				partCaseCty			= "";

	private String				partPrcsStat		= "";

	private String				partWorkable		= "";

	private String				partIntrCase		= "";

	private String				respWrkrId			= "";

	private String				partFullNm			= "";

	SimpleDateFormat			DATE_FORMAT			= new SimpleDateFormat("yyyy-MM-dd");
	
	SimpleDateFormat			DATE_FORMAT_SCREEN	= new SimpleDateFormat("MM/dd/yyyy");

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
	public String getPartNmSTAT()
	{
		return partNmSTAT;
	}

	/**
	 * @param partNmSTAT
	 */
	public void setPartNmSTAT(String partNmSTAT)
	{
		this.partNmSTAT = partNmSTAT;
	}

	public String getPartNbCase()
	{
		return partNbCase;
	}

	public void setPartNbCase(String partNbCase)
	{
		this.partNbCase = partNbCase;
	}

	public String getPartStat()
	{
		return partStat;
	}

	public void setPartStat(String partStat)
	{
		this.partStat = partStat;
	}

	public String getPartCaseType()
	{
		return partCaseType;
	}

	public void setPartCaseType(String partCaseType)
	{
		this.partCaseType = partCaseType;
	}

	public String getPartCaseStat()
	{
		return partCaseStat;
	}

	public void setPartCaseStat(String partCaseStat)
	{
		this.partCaseStat = partCaseStat;
	}

	public String getPartCaseCty()
	{
		return partCaseCty;
	}

	public void setPartCaseCty(String partCaseCty)
	{
		this.partCaseCty = partCaseCty;
	}

	public String getPartPrcsStat()
	{
		return partPrcsStat;
	}

	public void setPartPrcsStat(String partPrcsStat)
	{
		this.partPrcsStat = partPrcsStat;
	}

	public String getPartWorkable()
	{
		return partWorkable;
	}

	public void setPartWorkable(String partWorkable)
	{
		this.partWorkable = partWorkable;
	}

	public String getPartIntrCase()
	{
		return partIntrCase;
	}

	public void setPartIntrCase(String partIntrCase)
	{
		this.partIntrCase = partIntrCase;
	}

	public String getRespWrkrId()
	{
		return respWrkrId;
	}

	public void setRespWrkrId(String respWrkrId)
	{
		this.respWrkrId = respWrkrId;
	}

	public String getPartFullNm()
	{
		return partFullNm;
	}

	public void setPartFullNm(String partFullNm)
	{
		this.partFullNm = partFullNm;
	}

}