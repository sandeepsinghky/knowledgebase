package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecwa.beans.CasePartOther;

/**
 * @author Phani Konuru
 */
public interface CasePartOtherDao
{
	/**
	 * @param applNCPExtBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartOther> getCasePartOtherByPartId(String applId, String applicantId) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @param relationId
	 * @return
	 * @throws SQLException
	 */
	public CasePartOther getCasePartOtherByRelation(String applId, String applicantId, String relationId)
			throws SQLException;

	/**
	 * @param relationBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createCasePartOTher(CasePartOther relationBean, String ncid) throws SQLException;

	/**
	 * @param relationBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String updateCasePartOTher(CasePartOther relationBean, String ncid) throws SQLException;

	/**
	 * @param relationBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */

	public String deleteCasePartOTher(CasePartOther relationBean, String ncid) throws SQLException;

}
