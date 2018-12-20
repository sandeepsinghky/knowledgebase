/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;


/**
 * @author Phani Konuru
 *
 */
public class ApplicationCaseListRowMapper implements RowMapper<String>
{

	protected final Logger logger = Logger.getLogger(ApplicationCaseListRowMapper.class);

	public ApplicationCaseListRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CaseApplicationListRowMapper- mapRow");

		String caseNb = "";

		if (rs.getString("NB_CASE") != null && !rs.getString("NB_CASE").equals(""))
		{
			caseNb = (rs.getString("NB_CASE").trim());
		}

		return caseNb;
	}

}
