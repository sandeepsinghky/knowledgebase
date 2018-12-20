/**
 * 
 */
package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecwa.beans.CaseApplicationList;

/**
 * @author Phani Konuru
 *
 */
public class CaseApplicationListRowMapper implements RowMapper<CaseApplicationList>
{

	protected final Logger logger = Logger.getLogger(CaseApplicationListRowMapper.class);

	public CaseApplicationListRowMapper()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public CaseApplicationList mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.debug("IN: CaseApplicationRowMapper- mapRow");

		CaseApplicationList caseApplication = new CaseApplicationList();

		if (rs.getString("ID_APPLICATION") != null && !rs.getString("ID_APPLICATION").equals(""))
		{
			caseApplication.setApplicationId(rs.getString("ID_APPLICATION").trim());
		}
		
		if (rs.getString("APPLICANT NAME") != null && !rs.getString("APPLICANT NAME").equals(""))
		{
			caseApplication.setApplicantName(rs.getString("APPLICANT NAME").trim());
		}


		if (rs.getDate("DT_BRTH") != null && !rs.getDate("DT_BRTH").toString().equals(""))
		{
			caseApplication.setApplicantDOB(rs.getDate("DT_BRTH"));
		}

		
		if (rs.getString("NB_SSN") != null && !rs.getString("NB_SSN").equals(""))
		{
			caseApplication.setApplicantSsn(rs.getString("NB_SSN").trim());
		}

		if (rs.getDate("DT_APPL_SUBMIT") != null && !rs.getDate("DT_APPL_SUBMIT").toString().equals(""))
		{
			caseApplication.setApplCmpltDt(rs.getDate("DT_APPL_SUBMIT"));
		}

		if (rs.getString("CD_APPL_COUNTY") != null && !rs.getString("CD_APPL_COUNTY").equals(""))
		{
			caseApplication.setApplCounty(rs.getString("CD_APPL_COUNTY").trim());
		}

		if (rs.getString("NM_COUNTY") != null && !rs.getString("NM_COUNTY").equals(""))
		{
			caseApplication.setApplCountyDesc(rs.getString("NM_COUNTY").trim());
		}
		
		if (rs.getString("CD_APPL_STATUS") != null && !rs.getString("CD_APPL_STATUS").equals(""))
		{
			caseApplication.setApplStatus(rs.getString("CD_APPL_STATUS").trim());
		}
		
		if (rs.getString("DE_APPL_STATUS") != null && !rs.getString("DE_APPL_STATUS").equals(""))
		{
			caseApplication.setApplStatusDesc(rs.getString("DE_APPL_STATUS").trim());
		}
		
		if (rs.getString("CD_APPL_LOCK") != null && !rs.getString("CD_APPL_LOCK").equals(""))
		{
			caseApplication.setIsApplLocked(rs.getString("CD_APPL_LOCK").trim());
		}
		
		if (rs.getString("ID_WRKR_NCID") != null && !rs.getString("ID_WRKR_NCID").equals(""))
		{
			caseApplication.setWrkrNcId(rs.getString("ID_WRKR_NCID").trim());
		}
		
		if (rs.getString("WORKER NAME") != null && !rs.getString("WORKER NAME").equals(""))
		{
			caseApplication.setWrkrName(rs.getString("WORKER NAME").trim());
		}

		return caseApplication;
	}

}
