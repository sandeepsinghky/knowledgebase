package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecwa.beans.CasePartBenefit;

/**
 * @author Phani Konuru
 */
public interface CasePartBenefitDao
{

	/**
	 * @param benefitBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createCasePartBenefit(CasePartBenefit benefitBean, String ncid) throws SQLException;

	/**
	 * @param applicationId
	 * @param applicantId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartBenefit> getCasePartBenefitsByFieldId(String applicationId, String applicantId, String partType)
			throws SQLException;

	/**
	 * @param benefitBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String updateCasePartBenefit(CasePartBenefit benefitBean, String ncid) throws SQLException;

	/**
	 * @param benefitBean
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String deleteCasePartBenefit(CasePartBenefit benefitBean, String ncid) throws SQLException;
}
