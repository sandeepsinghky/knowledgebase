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
public class CaseActsPartDetailsRowMapper implements RowMapper<CaseActsParticipant>
{
	protected final Logger logger = Logger.getLogger(CaseActsPartDetailsRowMapper.class);

	public CaseActsPartDetailsRowMapper()
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
		logger.debug("IN: CaseActsPartDetailsRowMapper- mapRow");

		CaseActsParticipant caseActsParticipant = new CaseActsParticipant();

		if (rs.getString("DT_BRTH") != null && !rs.getString("DT_BRTH").equals(""))
			caseActsParticipant.setBrthDt(rs.getDate("DT_BRTH"));

		if (rs.getString("NB_SSN") != null && !rs.getString("NB_SSN").equals(""))
			caseActsParticipant.setSsnNb(rs.getString("NB_SSN").trim());

		if (rs.getString("ID_PART") != null && !rs.getString("ID_PART").equals(""))
			caseActsParticipant.setPartId(rs.getString("ID_PART").trim());

		if (rs.getString("NB_CASE") != null && !rs.getString("NB_CASE").equals(""))
			caseActsParticipant.setPartNbCase(rs.getString("NB_CASE").trim());

		if (rs.getString("CD_CASE_RELSHP") != null && !rs.getString("CD_CASE_RELSHP").equals(""))
			caseActsParticipant.setPartType(rs.getString("CD_CASE_RELSHP").trim());

		if (rs.getString("CD_PART_STAT") != null && !rs.getString("CD_PART_STAT").equals(""))
			caseActsParticipant.setPartStat(rs.getString("CD_PART_STAT").trim());

		if (rs.getString("CD_CASE_TYPE") != null && !rs.getString("CD_CASE_TYPE").equals(""))
			caseActsParticipant.setPartCaseType(rs.getString("CD_CASE_TYPE").trim());

		if (rs.getString("CD_CASE_STAT") != null && !rs.getString("CD_CASE_STAT").equals(""))
			caseActsParticipant.setPartCaseStat(rs.getString("CD_CASE_STAT").trim());

		if (rs.getString("CD_FIPS_CASE") != null && !rs.getString("CD_FIPS_CASE").equals(""))
			caseActsParticipant.setPartCaseCty(rs.getString("CD_FIPS_CASE").trim());

		if (rs.getString("CD_PRCS_STAT") != null && !rs.getString("CD_PRCS_STAT").equals(""))
			caseActsParticipant.setPartPrcsStat(rs.getString("CD_PRCS_STAT").trim());

		if (rs.getString("CD_RSN_UNWRK") != null && !rs.getString("CD_RSN_UNWRK").equals(""))
			caseActsParticipant.setPartWorkable(rs.getString("CD_RSN_UNWRK").trim());

		if (rs.getString("NB_CASE_INTR") != null && !rs.getString("NB_CASE_INTR").equals(""))
			caseActsParticipant.setPartIntrCase(rs.getString("NB_CASE_INTR").trim());

		if (rs.getString("ID_WRKR_RESP") != null && !rs.getString("ID_WRKR_RESP").equals(""))
			caseActsParticipant.setRespWrkrId(rs.getString("ID_WRKR_RESP").trim());

		if (rs.getString("NM_PART") != null && !rs.getString("NM_PART").equals(""))
			caseActsParticipant.setPartFullNm(rs.getString("NM_PART").trim());

		return caseActsParticipant;
	}

}
