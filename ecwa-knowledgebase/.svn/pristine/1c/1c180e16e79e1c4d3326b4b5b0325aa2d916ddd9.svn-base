package nc.dhhs.nccss.acts.ecwa.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import nc.dhhs.nccss.acts.ecwa.beans.CaseCourtOrder;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartBenefit;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartChldAffil;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartDemo;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartEmp;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartIncome;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartInsurance;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartNCPExt;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartOrder;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartOther;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartPaternty;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartProfile;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartProfileExt;
import nc.dhhs.nccss.acts.ecwa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecwa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecwa.beans.Vehicle;

/*
 * @author Phani Konuru
 */
public interface CaseParticipantService
{

	/**
	 * @param applId
	 * @param partType
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getParticipantByPartType(String applId, String partType) throws SQLException;

	/**
	 * @param appId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseParticipant> getParticipantByPartId(String applId, String partId) throws SQLException;

	/**
	 * @param applId
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getParticipantsAll(String applId) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List searchActsPaticipant(String fname, String lname) throws SQLException;

	/**
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getPaticipantDetails(String partId) throws SQLException;

	/**
	 * @param caseNum
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getCasePaticipantDetails(String caseNum) throws SQLException;

	/**
	 * @param appId
	 * @param PartId
	 * @param mpi
	 * @param op
	 * @return
	 * @throws SQLException
	 */
	public String createUseActsPart(String appId, String PartId, String mpi, String ncId, String op)
			throws SQLException;

	/**
	 * @param appId
	 * @param mpiList
	 * @param op
	 * @return
	 * @throws SQLException
	 */
	public Map<String, String> createActsCase(String appId, String nb_case, String mpiList, String ncid, String op)
			throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartContact> getPartContact(String applId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @param contactType
	 * @return
	 * @throws SQLException
	 */
	public CasePartContact getPartContactByType(String applId, String applicantId, String contactType)
			throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartAddress> getParticipantAddr(String applId, String partId) throws SQLException;

	/**
	 * @param appId
	 * @param partId
	 * @param mpi
	 * @return
	 * @throws SQLException
	 */
	public String updateActsPart(CaseParticipant partInfo, String ncId) throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartDemo> getPartDemo(CaseParticipant partInfo) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartInsurance> getPartInsurance(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */

	public List<CasePartNCPExt> getCasePartNCPExtByPartId(String applId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartEmp> getPartEmp(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param thirdPartyId
	 * @return
	 * @throws SQLException
	 */
	public List<ThirdParty> getThirdPartyBy3ptyId(String applId, String thirdPartyId) throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartIncome> getParticipantIncome(String applId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseCourtOrder> getCaseCourtOrderByPartId(String applId, String applicantId) throws SQLException;

	/**
	 * @param orderNum
	 * @throws SQLException
	 */

	public List<CasePartOrder> getCasePartOrderByOrderNum(String orderNum) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * 
	 * @throws SQLException
	 */
	public List<CasePartProfile> getPartProfile(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartOther> getPartOther(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartBenefit> getCasePartBenefitsByFieldId(String applId, String applicantId, String PartType)
			throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartProfileExt> getPartProfileExt(String applicationId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param chldId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartPaternty> getPartPaternty(String applicationId, String chldId) throws SQLException;

	/**
	 * @param applId
	 * @param childId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartChldAffil> getChldNcpAffil(String applId, String childId) throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartOther> getCasePartOtherByPartId(String applId, String applicantId) throws SQLException;

	/**
	 * @param applicationId
	 * @param thirdPartyType
	 * @return
	 * @throws SQLException
	 */
	public List<ThirdParty> getThirdPartyByApplIdandType(String applicationId, String thirdPartyType)
			throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartChldAffil> getNCPLinkedChildren(String applId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<Vehicle> getCaseVehiclesByPartId(String applId, String applicantId) throws SQLException;

}
