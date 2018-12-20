package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecwa.caseworker.beans.CaseWorker;

public class CaseWorkerListRowMapper  implements RowMapper<CaseWorker>
{
	
	protected final Logger logger = Logger.getLogger(CaseWorkerListRowMapper.class);

	public CaseWorkerListRowMapper()
	{

	}

	@Override
	public CaseWorker mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.info("IN: CaseWorkerListRowMapper- mapRow");
		CaseWorker caseWorker = new CaseWorker();
		
		if (rs.getString("NM_WRKR_L") != null && !rs.getString("NM_WRKR_L").equals(""))
		{
			caseWorker.setLast_name (rs.getString("NM_WRKR_L").trim());
		}
		
		if (rs.getString("NM_WRKR_F") != null && !rs.getString("NM_WRKR_F").equals(""))
		{
			caseWorker.setFirst_name(rs.getString("NM_WRKR_F").trim());
		}
		
		if (rs.getString("NM_WRKR_MI") != null && !rs.getString("NM_WRKR_MI").equals(""))
		{
			caseWorker.setMiddle_name (rs.getString("NM_WRKR_MI").trim());
		}
		
		if (rs.getString("ID_WRKR") != null && !rs.getString("ID_WRKR").equals(""))
		{
			caseWorker.setWorker_num  (rs.getString("ID_WRKR").trim());
		}
		
		if (rs.getString("CD_WRKR_TYPE") != null && !rs.getString("CD_WRKR_TYPE").equals(""))
		{
			caseWorker.setWorker_type  (rs.getString("CD_WRKR_TYPE").trim());
		}
		return caseWorker;
	}

}
