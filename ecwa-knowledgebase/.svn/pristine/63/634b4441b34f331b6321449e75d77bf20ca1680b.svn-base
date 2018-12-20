package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import nc.dhhs.nccss.acts.ecwa.beans.CaseAppSearch;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplication;

/**
 * @author Phani Konuru
 */
public interface CaseApplicationDao
{

	/**
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public List getCaseApplicationByField(String field, String value) throws SQLException;

	/**
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getCaseApplicationByNcID(String value) throws SQLException;

	/**
	 * @param applId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseApplication> getCaseApplicationByAppId(String applId) throws SQLException;

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
	 * @param applBean
	 * @return
	 * @throws SQLException
	 */
	public String updateCaseApplication(CaseApplication applBean) throws SQLException;

	/**
	 * @param applId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseApplication> getCaseApplicationIdForReport(String applId, String ncId) throws SQLException;

}
