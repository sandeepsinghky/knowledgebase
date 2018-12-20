package nc.dhhs.nccss.acts.ecwa.web.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.CaseApplicationDao;
import nc.dhhs.nccss.acts.dao.CaseApplicationLockDao;
import nc.dhhs.nccss.acts.ecwa.beans.CaseAppSearch;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/*
 * @author Phani Konuru
 */

public class CaseApplicationServiceImpl implements CaseApplicationService
{

	protected final Logger			logger	= Logger.getLogger(CaseApplicationServiceImpl.class);

	@Autowired
	private CaseApplicationDao		caseApplDao;

	@Autowired
	private CaseApplicationLockDao	caseApplLockDao;

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService#
	 * getCaseApplicationByFieldName(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CaseApplication> getCaseApplicationByFieldName(String field, String value) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: getCaseApplicationByFieldName(field" + field + "," + value + ") **********\n");

		return caseApplDao.getCaseApplicationByField(field, value);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService#
	 * getCaseApplicationByNcID(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> getCaseApplicationByNcID(String ncId) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: getCaseApplicationByncId(NCID" + ncId + ") **********\n");

		return caseApplDao.getCaseApplicationByNcID(ncId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService#
	 * getCaseApplicationByAppId(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CaseApplication> getCaseApplicationByAppId(String applId) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: getCaseApplicationByAppId(applId: " + applId + ")");

		return caseApplDao.getCaseApplicationByAppId(applId);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService#getApplCases(
	 * java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List getApplCases(String appId) throws SQLException
	{
		return caseApplDao.getApplCases(appId);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService#ApplAdvSearch(
	 * nc.dhhs.nccss.acts.ecwa.beans.CaseAppSearch)
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> ApplAdvSearch(CaseAppSearch applSearchBean) throws SQLException
	{
		return caseApplDao.ApplAdvSearch(applSearchBean);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService#
	 * LockApplication(nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock,
	 * java.lang.String)
	 */
	@Transactional
	public String LockApplication(CaseApplLock applLockBean, String userId) throws SQLException
	{
		logger.info("\n********** IN CaseApplicationServiceImpl: LockApplication");
		CaseApplLock applLock = caseApplLockDao.getApplLock(applLockBean.getApplicationId());
		if (applLock == null)
		{
			return caseApplLockDao.createCaseApplicationLock(applLockBean);
		}
		else
		{
			return caseApplLockDao.UpdateCaseApplicationLock(applLockBean, userId);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService#
	 * updateApplicationLock(nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock,
	 * java.lang.String)
	 */
	@Transactional
	public String updateApplicationLock(CaseApplLock applLockBean, String userId) throws SQLException
	{
		logger.info("\n********** IN CaseApplicationServiceImpl: updateApplicationLock");
		String returnCode = "";
		CaseApplLock applLock = caseApplLockDao.getApplLock(applLockBean.getApplicationId());
		if (applLock != null)
		{
			returnCode = caseApplLockDao.UpdateCaseApplicationLock(applLockBean, userId);
		}
		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService#
	 * deleteApplicationLock(nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock,
	 * java.lang.String)
	 */
	@Transactional
	public String deleteApplicationLock(CaseApplLock applLockBean, String userId) throws SQLException
	{
		logger.info("\n********** IN CaseApplicationServiceImpl: deleteApplicationLock");
		String returnCode = "";
		CaseApplLock applLock = caseApplLockDao.getApplLock(applLockBean.getApplicationId());
		if (applLock != null)
		{
			returnCode = caseApplLockDao.deleteCaseApplicationLock(applLock, userId);
		}
		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService#
	 * updateCaseApplicationStatus(nc.dhhs.nccss.acts.ecwa.beans.
	 * CaseApplication)
	 */
	@Transactional
	public String updateCaseApplicationStatus(CaseApplication applBean) throws SQLException
	{
		logger.info("\n********** IN CaseApplicationServiceImpl: updateCaseApplicationStatus");

		CaseApplication appl = getCaseApplicationByAppId(applBean.getApplicationId()).get(0);
		appl.setApplCmpltDt(new Date());
		appl.setApplStatus(AppConstants.APP_STATUS_COMPLETE);

		return caseApplDao.updateCaseApplication(appl);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService#
	 * getCaseApplicationByAppIdAndNCID(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CaseApplication> getCaseApplicationIdForReport(String appId, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: getCaseApplicationIdForReport(  applId: " + appId + " , ncid " + ncid + ")");

		return caseApplDao.getCaseApplicationIdForReport(appId, ncid);
	}

}
