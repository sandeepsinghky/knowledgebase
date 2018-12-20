package nc.dhhs.nccss.acts.ecwa.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.CaseCourtOrderDao;
import nc.dhhs.nccss.acts.dao.CasePartAddrDao;
import nc.dhhs.nccss.acts.dao.CasePartBenefitDao;
import nc.dhhs.nccss.acts.dao.CasePartChldAffilDao;
import nc.dhhs.nccss.acts.dao.CasePartContactDao;
import nc.dhhs.nccss.acts.dao.CasePartEmpDao;
import nc.dhhs.nccss.acts.dao.CasePartIncomeDao;
import nc.dhhs.nccss.acts.dao.CasePartInsuranceDao;
import nc.dhhs.nccss.acts.dao.CasePartNCPExtDao;
import nc.dhhs.nccss.acts.dao.CasePartOrderDao;
import nc.dhhs.nccss.acts.dao.CasePartOtherDao;
import nc.dhhs.nccss.acts.dao.CasePartPaterntyDao;
import nc.dhhs.nccss.acts.dao.CasePartProfileDao;
import nc.dhhs.nccss.acts.dao.CasePartProfileExtDao;
import nc.dhhs.nccss.acts.dao.CaseParticipantDao;
import nc.dhhs.nccss.acts.dao.CaseVehicleDao;
import nc.dhhs.nccss.acts.dao.ThirdPartyDao;
import nc.dhhs.nccss.acts.ecwa.beans.CaseActsParticipant;
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
import nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService;

/*
 * @author Phani Konuru
 */

public class CaseParticipantServiceImpl implements CaseParticipantService
{

	protected final Logger			logger	= Logger.getLogger(CaseParticipantServiceImpl.class);

	@Autowired
	private CaseParticipantDao		casePartDao;

	//@Autowired
	//private CaseApplicationDao		caseApplDao;

	@Autowired
	private CasePartAddrDao			casePartAddrDao;

	@Autowired
	private CasePartContactDao		casePartContactDao;

	@Autowired
	private CasePartInsuranceDao	casePartInsuranceDAO;

	@Autowired
	private CasePartNCPExtDao		partNCPExtDao;

	@Autowired
	private CasePartEmpDao			casePartEmpDAO;

	@Autowired
	private ThirdPartyDao			thirdPartyDao;

	@Autowired
	private CasePartIncomeDao		casePartIncomeDao;

	@Autowired
	private CaseCourtOrderDao		caseOrderDao;

	@Autowired
	private CasePartOrderDao		caseOrderChildDao;

	@Autowired
	private CasePartProfileDao		casePartProfileDao;

	@Autowired
	private CasePartOtherDao		casePartOtherDao;

	@Autowired
	private CasePartBenefitDao		benefitDao;

	@Autowired
	private CasePartProfileExtDao	casePartProfileExtDao;

	@Autowired
	private CasePartPaterntyDao		casePartPaterntyDao;

	@Autowired
	private CasePartChldAffilDao	casePartChldAffilDAO;

	@Autowired
	private CaseVehicleDao			caseVehicleDao;

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getParticipantByPartType(java.lang.String, java.lang.String)
	 */
	@Transactional
	public List<CaseParticipant> getParticipantByPartType(String applId, String partType) throws SQLException
	{
		return casePartDao.getParticipantByPartType(applId, partType);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getParticipantsAll(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CaseParticipant> getParticipantsAll(String applId) throws SQLException
	{
		return casePartDao.getAllParticipants(applId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getParticipantByPartId(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CaseParticipant> getParticipantByPartId(String applId, String partId) throws SQLException
	{
		return casePartDao.getParticipantByPartId(applId, partId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * searchActsPaticipant(java.lang.String, java.lang.String)
	 */
	public List<CaseActsParticipant> searchActsPaticipant(String fname, String lname) throws SQLException
	{
		return casePartDao.searchActsParticipant(fname, lname);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getPaticipantDetails(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CaseActsParticipant> getPaticipantDetails(String partId) throws SQLException
	{
		return casePartDao.getActsParticipantsById(partId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getCasePaticipantDetails(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CaseActsParticipant> getCasePaticipantDetails(String caseNum) throws SQLException
	{
		return casePartDao.getActsCaseParticipants(caseNum);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * createUseActsPart(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Transactional
	public String createUseActsPart(String appId, String partId, String mpi, String ncId, String op) throws SQLException
	{
		return casePartDao.createUseActsPart(appId, partId, mpi, ncId, op);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#createActsCase
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Transactional
	public Map<String, String> createActsCase(String appId, String nb_case, String mpiList, String ncid, String op)
			throws SQLException
	{
		return casePartDao.createActsCase(appId, nb_case, mpiList, ncid, op);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#getPartContact
	 * (java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartContact> getPartContact(String applId, String applicantId) throws SQLException
	{
		return casePartContactDao.getCasePartContact(applId, applicantId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getPartContactByType(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Transactional(readOnly = true)
	public CasePartContact getPartContactByType(String applId, String applicantId, String contactType)
			throws SQLException
	{
		return casePartContactDao.getCasePartContactType(applId, applicantId, contactType);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getParticipantAddr(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartAddress> getParticipantAddr(String applId, String partId) throws SQLException
	{
		return casePartAddrDao.getCasePartAddr(applId, partId);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#updateActsPart
	 * (nc.dhhs.nccss.acts.ecwa.beans.CaseParticipant, java.lang.String)
	 */
	@Transactional
	public String updateActsPart(CaseParticipant partInfo, String ncId) throws SQLException
	{
		return casePartDao.updateActsParticipant(partInfo, ncId);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#getPartDemo(nc
	 * .dhhs.nccss.acts.ecwa.beans.CaseParticipant)
	 */
	@Transactional
	public List<CasePartDemo> getPartDemo(CaseParticipant partInfo) throws SQLException
	{
		return casePartDao.getPartDemo(partInfo);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getPartInsurance(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartInsurance> getPartInsurance(String applicationId, String applicantId) throws SQLException
	{
		return casePartInsuranceDAO.getCasePartInsurance(applicationId, applicantId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getCasePartNCPExtByPartId(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartNCPExt> getCasePartNCPExtByPartId(String applId, String applicantId) throws SQLException
	{
		return partNCPExtDao.getCasePartNCPExtByPartId(applId, applicantId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getThirdPartyBy3ptyId(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<ThirdParty> getThirdPartyBy3ptyId(String applId, String thirdPtyId) throws SQLException
	{
		return thirdPartyDao.getThirdPartyBy3ptyId(applId, thirdPtyId);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#getPartEmp(
	 * java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartEmp> getPartEmp(String applicationId, String applicantId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getPartEmp(applId: " + applicationId + ",applicantId: " + applicantId + ")**********\n");
		return casePartEmpDAO.getPartEmp(applicationId, applicantId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getParticipantIncome(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartIncome> getParticipantIncome(String applId, String applicantId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getParticipantIncome(applId: " + applId + ",applicantId: " + applicantId + ")**********\n");
		return casePartIncomeDao.getCasePartIncome(applId, applicantId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getCaseCourtOrderByPartId(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CaseCourtOrder> getCaseCourtOrderByPartId(String applId, String applicantId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getCaseCourtOrderByPartId(applId: " + applId + ",applicantId: " + applicantId + ")**********\n");

		return caseOrderDao.getCaseCourtOrderByPartId(applId, applicantId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getCasePartOrderByOrderNum(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartOrder> getCasePartOrderByOrderNum(String orderNum) throws SQLException

	{
		logger.debug("\n********** IN CaseParticipantServiceImpl:getCasePartOrderByOrderNum(orderNum: " + orderNum + " )**********\n");

		return caseOrderChildDao.getCasePartOrderByOrderNum(orderNum);

	}

	// ADDED BELOW METHODS FOR PRINT SUPPORT

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#getPartProfile
	 * (java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartProfile> getPartProfile(String applicationId, String applicantId) throws SQLException
	{
		return casePartProfileDao.getPartProfile(applicationId, applicantId);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#getPartOther(
	 * java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartOther> getPartOther(String applicationId, String applicantId) throws SQLException
	{
		return casePartOtherDao.getCasePartOtherByPartId(applicationId, applicantId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getCasePartBenefitsByFieldId(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartBenefit> getCasePartBenefitsByFieldId(String applId, String applicantId, String partType)
			throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getCasePartBenefitsByFieldId(applId: " + applId + ",applicantId: " + applicantId + ")**********\n");

		return benefitDao.getCasePartBenefitsByFieldId(applId, applicantId, partType);

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getPartProfileExt(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartProfileExt> getPartProfileExt(String applicationId, String applicantId) throws SQLException
	{
		return casePartProfileExtDao.getCasePartProfileExt(applicationId, applicantId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getPartPaternty(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartPaternty> getPartPaternty(String applicationId, String chldId) throws SQLException
	{
		return casePartPaterntyDao.getPartPaternty(applicationId, chldId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getChldNcpAffil(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartChldAffil> getChldNcpAffil(String applId, String childId) throws SQLException
	{
		return casePartChldAffilDAO.getPartChld(applId, childId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getCasePartOtherByPartId(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartOther> getCasePartOtherByPartId(String applId, String applicantId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getCasePartOtherByPartId(applId: " + applId + ",applicantId: " + applicantId + ")**********\n");

		return casePartOtherDao.getCasePartOtherByPartId(applId, applicantId);

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getThirdPartyByApplIdandType(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<ThirdParty> getThirdPartyByApplIdandType(String applicationId, String thirdPartyType)
			throws SQLException
	{
		logger.debug("\n**********IN CaseParticipantServiceImpl: getThirdPartyByApplIdandType(applicationId :" + applicationId + ")**********\n");

		return thirdPartyDao.getThirdPartyByApplIdandType(applicationId, thirdPartyType);

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getNCPLinkedChildren(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CasePartChldAffil> getNCPLinkedChildren(String applId, String partId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl:getNCPLinkedChildren (applId: " + applId + ",partId: " + partId + ")**********\n");

		return casePartChldAffilDAO.getNCPLinkedChildren(applId, partId);

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService#
	 * getCaseVehiclesByPartId(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<Vehicle> getCaseVehiclesByPartId(String applId, String applicantId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantServiceImpl: getCaseVehicleByPartId(applId: " + applId + ",applicantId: " + applicantId + ")**********\n");

		return caseVehicleDao.getCaseVehiclesByPartId(applId, applicantId);

	}

}
