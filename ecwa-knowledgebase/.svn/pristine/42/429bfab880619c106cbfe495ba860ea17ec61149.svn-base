/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecwa.beans.CasePartDemo;

/**
 * @author Phani Konuru
 *
 */
public class CasePartDemoRowMapper implements RowMapper<CasePartDemo>
{

	protected final Logger logger = Logger.getLogger(CasePartDemoRowMapper.class);

	public CasePartDemoRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CasePartDemo mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CasePartDemoRowMapper- mapRow");


		CasePartDemo casePartDemo = new CasePartDemo();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			casePartDemo.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}

		if (rs.getString("ID_APPLICANT") != null && !rs.getString("ID_APPLICANT").equals(""))
		{
			casePartDemo.setApplicantId(rs.getString("ID_APPLICANT").trim());
		}
		
		if (rs.getString("ID_PART") != null && !rs.getString("ID_PART").equals(""))
		{
			casePartDemo.setPartId(rs.getString("ID_PART").trim());
		}
		
		if (rs.getString("CD_DEMOGRAPHIC") != null && !rs.getString("CD_DEMOGRAPHIC").equals(""))
		{
			casePartDemo.setPartDemo(rs.getString("CD_DEMOGRAPHIC").trim());
		}
		
		if (rs.getString("CD_STATUS") != null && !rs.getString("CD_STATUS").equals(""))
		{
			casePartDemo.setDemoStatus(rs.getString("CD_STATUS").trim());
		}

		return casePartDemo;

	}

}
