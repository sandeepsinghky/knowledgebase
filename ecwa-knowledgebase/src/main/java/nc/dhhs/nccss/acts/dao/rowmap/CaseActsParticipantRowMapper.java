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
public class CaseActsParticipantRowMapper implements RowMapper<CaseActsParticipant>
{
	protected final Logger logger = Logger.getLogger(CaseActsParticipantRowMapper.class);

	public CaseActsParticipantRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CaseActsParticipant mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CaseActsParticipantRowMapper- mapRow");

		CaseActsParticipant caseActsParticipant = new CaseActsParticipant();

		if (rs.getString("NM_PART_F") != null && !rs.getString("NM_PART_F").equals(""))
			caseActsParticipant.setApplicantFNm(rs.getString("NM_PART_F").trim());

		if (rs.getString("NM_PART_L") != null && !rs.getString("NM_PART_L").equals(""))
			caseActsParticipant.setApplicantLNm(rs.getString("NM_PART_L").trim());

		if (rs.getString("NM_PART_M") != null && !rs.getString("NM_PART_M").equals(""))
			caseActsParticipant.setApplicantMNm(rs.getString("NM_PART_M").trim());

		if (rs.getString("CD_SEX") != null && !rs.getString("CD_SEX").equals(""))
			caseActsParticipant.setApplicantGender(rs.getString("CD_SEX").trim());

		if (rs.getString("DT_BRTH") != null && !rs.getString("DT_BRTH").equals(""))
			caseActsParticipant.setBrthDt(rs.getDate("DT_BRTH"));

		if (rs.getString("NB_SSN") != null && !rs.getString("NB_SSN").equals(""))
			caseActsParticipant.setSsnNb(rs.getString("NB_SSN").trim());

		if (rs.getString("CD_ETHNC_GRP") != null && !rs.getString("CD_ETHNC_GRP").equals(""))
			caseActsParticipant.setEthncGrp(rs.getString("CD_ETHNC_GRP").trim());

		if (rs.getString("ID_PART") != null && !rs.getString("ID_PART").equals(""))
			caseActsParticipant.setPartId(rs.getString("ID_PART").trim());

		if (rs.getString("CD_NM_STAT") != null && !rs.getString("CD_NM_STAT").equals(""))
			caseActsParticipant.setPartNmSTAT(rs.getString("CD_NM_STAT").trim());

		return caseActsParticipant;
	}

}
