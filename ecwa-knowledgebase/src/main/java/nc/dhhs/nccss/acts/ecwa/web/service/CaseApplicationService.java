package nc.dhhs.nccss.acts.ecwa.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import nc.dhhs.nccss.acts.ecwa.beans.CaseAppSearch;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplication;

/*
 * @author Phani Konuru
 */
/**
 * @author pkonuru
 *
 */
public interface CaseApplicationService
{
	/**
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List<CaseApplication> getCaseApplicationByFieldName(String field, String value) throws SQLException;

	/**
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getCaseApplicationByNcID(String ncId) throws SQLException;

	/**
	 * @param appId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseApplication> getCaseApplicationByAppId(String appId) throws SQLException;

	/**
	 * @param appId
	 * @return
	 * @throws SQLException
	 */
	public List getApplCases(String appId) throws SQLException;

	/**
	 * @param applSearchBean
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> ApplAdvSearch(CaseAppSearch applSearchBean) throws SQLException;

	/**
	 * @param applLockBean
	 * @return
	 * @throws SQLException
	 */
	public String LockApplication(CaseApplLock applLockBean, String ncId) throws SQLException;

	/**
	 * @param applLockBean
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String updateApplicationLock(CaseApplLock applLockBean, String ncId) throws SQLException;

	/**
	 * @param applLockBean
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public String deleteApplicationLock(CaseApplLock applLockBean, String ncId) throws SQLException;

	/**
	 * @param applBean
	 * @return
	 * @throws SQLException
	 */
	public String updateCaseApplicationStatus(CaseApplication applBean) throws SQLException;

	/**
	 * @param appId
	 * @param ncId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseApplication> getCaseApplicationIdForReport(String appId, String ncid) throws SQLException;

}
