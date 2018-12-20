package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import nc.dhhs.nccss.acts.dao.CaseWorkerDao;
import nc.dhhs.nccss.acts.dao.rowmap.CaseWorkerAddressRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.CaseWorkerListRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.CaseWorkerRowMapper;
import nc.dhhs.nccss.acts.ecwa.caseworker.beans.CaseWorker;
import nc.dhhs.nccss.acts.ecwa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class CaseWorkerDaoImpl implements CaseWorkerDao
{

	protected final Logger		logger		= Logger.getLogger(CaseWorkerDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;
	private SimpleJdbcCall	    procCaseWorkerList,procCaseWorkerByWorkerID,procCaseWorker3,procCaseWorkerAddress,procCaseWorker4,procCaseWorker5,jdbcCall;
	
	private static final String	SCREEN_SP	= "APP_RESP";
	String returnCode = ",,,,";
	
	
	@Autowired
	// @Required
	public void setDataSource(DataSource dataSource)
	{
		logger.debug("\n********** IN CaseWorkerDaoImpl: SETDATASOURCE**********\n");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcTemplate.setSkipUndeclaredResults(true);
		
		procCaseWorkerList = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FK_R_GET_WRKR_LIST");
		procCaseWorkerByWorkerID = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FK_R_WORKER_DETAIL");
		procCaseWorkerAddress = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FK_R_3RD_PARTY_DETAIL");
		procCaseWorker3 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_SP3");
		procCaseWorker4 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_SP4");
		procCaseWorker5 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_SP5");
	    jdbcCall = new 
				   SimpleJdbcCall(dataSource).withFunctionName(WebsiteConfiguration.getDbSchema() + ".FK_FUNC_R_GET_WRKR()"); 
	}
	
	
	@Override
	public List<CaseWorker> getCaseWorkerListBySelectionCriteria(Map paramList) throws SQLException
	{
		logger.debug("\n********** IN CaseWorkerDaoImpl: getCaseWorkerListBySelectionCriteria**********\n");
		
		// function call for Getting List
	 
		 /*String sql1 ="select FROM " + WebsiteConfiguration.getDbSchema() + ".FK_FUNC_R_GET_WRKR('TS51C00','','','','') ";
		 String sql1 ="select NM_WRKR_L, NM_WRKR_F, NM_WRKR_MI,ID_WRKR,CD_WRKR_TYPE FROM TABLE (" + WebsiteConfiguration.getDbSchema()  + ".FK_FUNC_R_GET_WRKR('TS51C00','','','',''))" + " T" ;
		//String sql1="select NM_WRKR_L, NM_WRKR_F, NM_WRKR_MI,ID_WRKR,CD_WRKR_TYPE FROM TABLE (DHRZFKF.FK_FUNC_R_GET_WRKR('TS51C00','','','','')) T";
		System.out.println(sql1);
		String returnResult = jdbcTemplate.queryForObject(sql1, String.class).toString();
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("WEBOAPRM_ID_WRKR_LOGON",paramList.get("ID_WRKR_LOGON"))
				.addValue("WEBOAPRM_ID_WRKR", paramList.get("ID_WRKR"))
				.addValue("WEBOAPRM_CD_COUNTY", paramList.get("CD_COUNTY"))
				.addValue("WEBOAPRM_NM_WRKR_LAST", paramList.get("NM_WRKR_LAST"))
				.addValue("WEBOAPRM_NM_WRKR_FIRST", paramList.get("NM_WRKR_FIRST"));
		 
		 String results = jdbcCall.executeFunction(String.class, in);*/
		
		
		 procCaseWorkerList.declareParameters(
				new SqlParameter("WEBOAPRM_ID_WRKR_LOGON", Types.CHAR),
				new SqlParameter("WEBOAPRM_ID_WRKR", Types.CHAR),
				new SqlParameter("WEBOAPRM_CD_COUNTY", Types.CHAR),
				new SqlParameter("WEBOAPRM_NM_WRKR_LAST", Types.CHAR),
				new SqlParameter("WEBOAPRM_NM_WRKR_FIRST", Types.CHAR) 
				 
				).returningResultSet("caseWorker", new CaseWorkerListRowMapper());
		
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("WEBOAPRM_ID_WRKR_LOGON",paramList.get("ID_WRKR_LOGON"))
				.addValue("WEBOAPRM_ID_WRKR", paramList.get("ID_WRKR"))
				.addValue("WEBOAPRM_CD_COUNTY", paramList.get("CD_COUNTY"))
				.addValue("WEBOAPRM_NM_WRKR_LAST", paramList.get("NM_WRKR_LAST"))
				.addValue("WEBOAPRM_NM_WRKR_FIRST", paramList.get("NM_WRKR_FIRST"));
		
		Map<String, Object> results = procCaseWorkerList.execute(in); 
		//Test Code
		for (Map.Entry<String,Object> entry : results.entrySet())  {
            System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue()); 
		}
     
	
		logger.info("createCaseWorker returned code: " + results.get("caseWorker"));
		return (List<CaseWorker>) results.get("caseWorker");
		 
		/*//Test Code
		List <CaseWorker> caseWorkerList = getMockData();
		return caseWorkerList;
		//Test Code  
*/	}
	
	@Override
	public CaseWorker getCaseWorkerByWorkerNumber(String workernum) throws SQLException
	{
		logger.debug("\n*** IN CaseWorkerDaoImpl: getCaseWorkerByWorkerNumber.Worker Num "+workernum + " ****\n");
		procCaseWorkerByWorkerID.declareParameters(new SqlParameter("WORKER_NUM", Types.CHAR)).returningResultSet("caseWorker", new CaseWorkerRowMapper());
		SqlParameterSource in = new MapSqlParameterSource().addValue("WORKER_NUM", workernum);
		Map<String, Object> results = procCaseWorkerByWorkerID.execute(in);
		List<CaseWorker>  ls= (List<CaseWorker>) results.get("caseWorker");
		
		//Get the Demographic information of Worker 
		
		return (CaseWorker) ls.get(0); 
	}
	

	 
	
	@Override
	public String createCaseWorker(CaseWorker caseWorker) throws SQLException
	{
		logger.debug("\n********** IN CaseWorkerDaoImpl: createCaseWorker**********\n");
		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, caseWorker.getWorker_num().trim());
		
		try
		{
			String dataFields = buildDataParam(caseWorker);
			procCaseWorker3.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));
			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);
			Map<String, Object> results = procCaseWorker3.execute(in);
			logger.info("createCaseWorker returned code: " + results.get("RETURNCODE"));
			
			if (results.get("RETURNCODE").toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				 
			}
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}
		
		//?? What need to be returned
		
		return null;
	}

	@Override
	public String upadateCaseWorker(CaseWorker caseWorker) throws SQLException
	{
		logger.debug("\n********** IN CaseWorkerDaoImpl: upadateCaseWorker**********\n");
		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, caseWorker.getWorker_num().trim());
		try
		{
			String dataFields = buildDataParam(caseWorker);
			procCaseWorker3.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));
			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);
			Map<String, Object> results = procCaseWorker3.execute(in);
			logger.info("createCaseWorker returned code: " + results.get("RETURNCODE"));
			
			if (results.get("RETURNCODE").toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				 
			}
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}
		
		//?? What need to be returned
		
		return null;
		
	}

	@Override
	public String deleteCaseWorker(String workernum) throws SQLException
	{
		logger.debug("\n********** IN CaseWorkerDaoImpl: deleteCaseWorker**********\n");
		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, workernum.trim());
		return null;
	}
	
	/**
	 * @param op
	 * @param ncid
	 * @return
	 */
	private String buildCommonParam(String op, String workernum)
	{
		logger.debug("\n********** IN CaseWorkerDaoImpl: buildCommonParam(op: " + op + "," + "workernum: " + workernum + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + workernum + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}
	
	/**
	 * @param CaseWorker
	 * @return
	 */
	private String buildDataParam(CaseWorker caseWorkerBean)
	{
		logger.debug("\n********** IN CaseWorkerDaoImpl: buildDataParam **********\n");
		// string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;
		StringBuffer dataParam = new StringBuffer(" ");
		dataParam.append(caseWorkerBean.getWorker_num().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getWorker_type().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getLast_name().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getFirst_name().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getMiddle_name().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getTitle().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getIvd_area().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getCounty().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getTerm_id().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getPrinter_id().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getCaseloadtotal()+ fldSeperator);
		dataParam.append(caseWorkerBean.getNivdTotal()+ fldSeperator);
		
		dataParam.append(caseWorkerBean.getSupervisor().trim() + fldSeperator);
		
		dataParam.append(caseWorkerBean.getStreetAddress1().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getStreetAddress2().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getCity().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getState().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getZipcode().trim() + fldSeperator);
		
		//dataParam.append(caseWorkerBean.getPhone_num().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getPhone_ext().trim() + fldSeperator);
		//dataParam.append(caseWorkerBean.getFax().trim() + fldSeperator);
		
		dataParam.append(caseWorkerBean.getMfacriteria().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getNcid().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getRacfid().trim() + fldSeperator);
		
		dataParam.append(caseWorkerBean.getActsSSPRole().trim() + fldSeperator);
		dataParam.append(caseWorkerBean.getOcseSSPRole().trim() + fldSeperator);
		
		dataParam.append(caseWorkerBean.getDateHired() + fldSeperator);
		dataParam.append(caseWorkerBean.getStartTime() + fldSeperator);
		dataParam.append(caseWorkerBean.getEndTime() + fldSeperator);
		dataParam.append(caseWorkerBean.getStartLunchTime() + fldSeperator);
		dataParam.append(caseWorkerBean.getEndLunchTime()+ fldSeperator);
		
		dataParam.append(caseWorkerBean.getLastUpdateDate() + fldSeperator);
		dataParam.append(caseWorkerBean.getLastUpdateBy().trim() + fldSeperator);
		
		
		logger.info("********** buildDataParam value is: " + dataParam.toString() + "**********");
		return dataParam.toString().trim();
	}
	//This is Test code 
	private List<CaseWorker> getMockData() {
			
			List <CaseWorker> caseWorkerList = new ArrayList<CaseWorker>();
			
			for (int i=0; i<25; i++) {
				CaseWorker caseWorker = new CaseWorker();
				 
				caseWorker.setLast_name("TestLasttName" + i);
				caseWorker.setFirst_name("TestFirtstName" + i);
				caseWorker.setMiddle_name("M");
				caseWorker.setWorker_num("WK00000" +i);
				caseWorker.setWorker_type("SSM");
				caseWorker.setRacfid ("TS8003" +i);
				caseWorker.setNcid("NCID" +i);
				caseWorker.setCounty("Wake");
				caseWorker.setMfacriteria("Yes");
				caseWorker.setTerm_id("P46"+i);
				caseWorker.setPrinter_id("P46"+i+1);
			//	caseWorker.setDateHired("11/19/2017");
				/*caseWorker.setStartTime("08:00 AM");
				caseWorker.setEndTime("05:30 PM");
				caseWorker.setStartLunchTime("12:00 PM");
				caseWorker.setEndLunchTime("12:30 PM");*/
				caseWorker.setLastUpdateBy("SSINGH3");
			//	caseWorker.setLastUpdateDate ("2018-03-12");
			//	caseWorker.setLastUpdateTime ("16:00:24");
				caseWorkerList.add(caseWorker);
			}
			
			return caseWorkerList;
		}
	//End Test code 




}
