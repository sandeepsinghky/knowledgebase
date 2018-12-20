/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecwa.beans.CaseActsParticipant;

/**
 * @author Phani Konuru
 *
 */
public class ActsCaseParticipantRowMapper implements RowMapper<CaseActsParticipant>
{
	protected final Logger logger = Logger.getLogger(ActsCaseParticipantRowMapper.class);

	public ActsCaseParticipantRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	public CaseActsParticipant mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CaseActsParticipantRowMapper- mapRow");

		CaseActsParticipant caseActsParticipant = new CaseActsParticipant();

		if (rs.getString("NM_PART") != null && !rs.getString("NM_PART").equals(""))
			caseActsParticipant.setPartFullNm(rs.getString("NM_PART").trim());

		if (rs.getString("CD_CASE_RELSHP") != null && !rs.getString("CD_CASE_RELSHP").equals(""))
			caseActsParticipant.setPartType(rs.getString("CD_CASE_RELSHP").trim());

		if (rs.getString("DT_BRTH") != null && !rs.getString("DT_BRTH").equals(""))
			caseActsParticipant.setBrthDt(rs.getDate("DT_BRTH"));

		if (rs.getString("NB_SSN") != null && !rs.getString("NB_SSN").equals(""))
			caseActsParticipant.setSsnNb(rs.getString("NB_SSN").trim());

		return caseActsParticipant;
	}

}
