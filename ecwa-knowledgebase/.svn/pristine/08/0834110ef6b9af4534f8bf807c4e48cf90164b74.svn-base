package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.CaseApplicationDao;
import nc.dhhs.nccss.acts.dao.rowmap.ApplicationCaseListRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.CaseApplicationListRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.CaseApplicationRowMapper;
import nc.dhhs.nccss.acts.ecwa.beans.CaseAppSearch;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplicationList;
import nc.dhhs.nccss.acts.ecwa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Repository
public class CaseApplicationDaoImpl implements CaseApplicationDao
{

	protected final Logger		logger		= Logger.getLogger(CaseApplicationDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseApplication1, procCaseApplication2, procCaseApplication3, procCaseApplication4,
			procCaseApplication5, procCaseApplication6, procCaseApplication7, procCaseApplication8;

	private static final String	SCREEN_SP	= "APP_RESP";

	private static final String	DB_SCHEMA	= WebsiteConfiguration.getDbSchema();

	@Autowired
	//@Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCaseApplication1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_NCID_CSS_APPL");

		procCaseApplication2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_COUNTY_CSS_APPL");

		procCaseApplication3 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_UPDATE_CSS_APPL");

		procCaseApplication4 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_APPLID_CSS_APPL");

		procCaseApplication5 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_APPLICATION_CASE");

		procCaseApplication6 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_ACTS_WRKR_VALIDATION");

		procCaseApplication7 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_ACTS_WRKR_VALIDATION");

		// works
		procCaseApplication8 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(WebsiteConfiguration.getDbSchema()).withProcedureName("FKWEB_R_APPLID_CSS_APPL_REP");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseApplication> getCaseApplicationByField(String field, String value) throws SQLException
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: getCaseApplicationByField **********\n");

		Map<String, Object> results = null;

		if (field.equals(AppConstants.APP_FIELD_NCID))
		{
			logger.debug("field = " + AppConstants.APP_FIELD_NCID);

			procCaseApplication1.declareParameters(new SqlParameter("NCID", Types.CHAR)).returningResultSet("appList", new CaseApplicationRowMapper());

			SqlParameterSource ncidParam = new MapSqlParameterSource().addValue("NCID", value);

			results = procCaseApplication1.execute(ncidParam);

		}

		if (field.equals(AppConstants.APP_FIELD_CNTY))
		{

			logger.debug("\n**********field = " + AppConstants.APP_FIELD_CNTY + "**********\n");

			procCaseApplication2.declareParameters(new SqlParameter("CD_APPL_COUNTY", Types.CHAR)).returningResultSet("appList", new CaseApplicationRowMapper());

			SqlParameterSource countyParam = new MapSqlParameterSource().addValue("CD_APPL_COUNTY", value);

			results = procCaseApplication2.execute(countyParam);

		}

		return (List<CaseApplication>) results.get("appList");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getCaseApplicationByNcID(String ncId) throws SQLException
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: getCaseApplicationByNcID**********\n");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String commonParam = buildCommonParam("SELECT", ncId.trim());

		String returnCode = ",,,,";

		CaseAppSearch applSearchBean = new CaseAppSearch();
		applSearchBean.setNcId(ncId.trim());

		//String dataFields = ncId.trim()+"@#%";
		String dataFields = buildDataParam(applSearchBean, "");

		logger.info("User NCID: " + ncId);

		try
		{
			procCaseApplication7.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR)).returningResultSet("appList", new CaseApplicationListRowMapper());

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication7.execute(in);

			logger.info("Worker Validation and Application List:" + results.get("RETURNCODE").toString());

			returnCode = results.get("RETURNCODE").toString();
			resultMap.put("RETURNCODE", returnCode);
			if (results.get("RETURNCODE").toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				System.out.println("datafields length: " + results.get("DATAFIELDS").toString().length());
				dataFields = results.get("DATAFIELDS").toString().trim();
				resultMap.put("DATAFIELDS", dataFields);

				logger.info("Worker Validation and Application List: Datafields: " + dataFields);

				List<CaseApplicationList> applList = (List<CaseApplicationList>) results.get("appList");
				if (applList != null)
				{
					resultMap.put("APPLIST", applList);
				}
				System.out.println("List size =" + applList.size());
			}

		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}

		return resultMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#getCaseApplicationByAppId(java.
	 * lang.String)
	 */

	@SuppressWarnings("unchecked")
	public List<CaseApplication> getCaseApplicationByAppId(String applId)
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: getCaseApplicationByAppId(applID:" + applId + ")" + "**********\n");

		procCaseApplication4.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR)).returningResultSet("caseApp", new CaseApplicationRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId);

		Map<String, Object> results = procCaseApplication4.execute(in);

		return (List<CaseApplication>) results.get("caseApp");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#updateCaseApplication(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseApplication)
	 */
	@Override
	public String updateCaseApplication(CaseApplication applBean) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationDaoImpl: updateCaseApplication**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, applBean.getNcId().trim());

		String returnCode = ",,,,";

		String dataFields = buildApplDataParam(applBean);

		logger.info("User NCID: " + applBean.getNcId().trim());

		try
		{
			procCaseApplication3.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplication3.execute(in);

			logger.info("Update Case Application returned:" + results.get("RETURNCODE"));

			returnCode = results.get("RETURNCODE").toString();

			System.out.println("the return code is" + returnCode);
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}

		return returnCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getApplCases(String appId) throws SQLException
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: getApplCases **********\n");

		Map<String, Object> results = null;

		procCaseApplication5.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR)).returningResultSet("appCaseList", new ApplicationCaseListRowMapper());

		SqlParameterSource appIdParam = new MapSqlParameterSource().addValue("ID_APPLICATION", appId);

		results = procCaseApplication5.execute(appIdParam);

		return (List<String>) results.get("appCaseList");
	}

	@Override
	public Map<String, Object> ApplAdvSearch(CaseAppSearch applSearchBean) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  ApplAdvSearch(Application Id:" + applSearchBean.getRefNum() + ", First Name: " + applSearchBean.getFName() + ")" + "**********\n");

		String commonParam = buildCommonParam("SELECT", applSearchBean.getNcId().trim());
		String returnCode = ",,,,";
		String fldSeperator = AppConstants.FLD_SEPARATOR;
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//String dataFields = applSearchBean.getRefNum() + fldSeperator + applSearchBean.getCnty() + fldSeperator + applSearchBean.getFName() + fldSeperator + applSearchBean.getLName() + "," + applSearchBean.getDtFrom() + "," + applSearchBean.getDtTo() + ",";

		String dataFields = buildDataParam(applSearchBean, "ADVANCED");

		procCaseApplication6.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR)).returningResultSet("appList", new CaseApplicationListRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		try
		{
			Map<String, Object> results = procCaseApplication6.execute(in);

			logger.info("Worker Validation and Application List:" + results.get("RETURNCODE").toString());

			returnCode = results.get("RETURNCODE").toString();
			resultMap.put("RETURNCODE", returnCode);
			if (results.get("RETURNCODE").toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				System.out.println("datafields length: " + results.get("DATAFIELDS").toString().length());
				dataFields = results.get("DATAFIELDS").toString().trim();
				resultMap.put("DATAFIELDS", dataFields);

				logger.info("Worker Validation and Application List: Datafields: " + dataFields);

				List<CaseApplication> applList = (List<CaseApplication>) results.get("appList");
				if (applList != null)
				{
					resultMap.put("APPLIST", applList);
				}
				System.out.println("List size =" + applList.size());
			}
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}

		return resultMap;

	}

	/**
	 * @param op
	 * @param ncid
	 * @return
	 */
	private String buildCommonParam(String op, String ncid)
	{
		logger.debug("\n********** IN CaseApplicationDaoImpl: buildCommonParam(op: " + op + "," + "ncid: " + ncid + ")**********\n");

		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("*****commonParam value: " + commonParam + "*****");
		return commonParam;
	}

	/**
	 * @param applBean
	 * @return
	 */
	private String buildDataParam(CaseAppSearch applSearchBean, String srchType)
	{

		logger.debug("\n********** IN CaseApplicationDaoImpl: buildDataParam **********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(applSearchBean.getNcId().trim() + fldSeperator);

		dataParam.append(srchType + fldSeperator);

		dataParam.append(applSearchBean.getRefNum() + fldSeperator);

		dataParam.append(applSearchBean.getCnty().trim() + fldSeperator);

		dataParam.append(applSearchBean.getFName().trim() + fldSeperator);

		dataParam.append(applSearchBean.getLName().trim() + fldSeperator);

		dataParam.append(applSearchBean.getSsnNum().trim() + fldSeperator);

		if (applSearchBean.getDtFrom() != null) dataParam.append(applSearchBean.getDtFrom().trim() + fldSeperator);
		else
			dataParam.append("" + fldSeperator);

		if (applSearchBean.getDtTo() != null) dataParam.append(applSearchBean.getDtTo().trim() + fldSeperator);
		else
			dataParam.append("" + fldSeperator);

		logger.debug("********** buildDataParam value is: " + dataParam.toString() + "**********");

		return dataParam.toString().trim();
	}

	/**
	 * @param applBean
	 * @return
	 */
	private String buildApplDataParam(CaseApplication applBean)
	{

		logger.info("\n********** IN CaseApplicationDaoImpl: buildApplDataParam **********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(applBean.getApplicationId().trim() + fldSeperator);

		if (applBean.getApplCreateDtStr() != null)
			dataParam.append(applBean.getApplCreateDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		if (applBean.getApplSubmitDtStr() != null)
			dataParam.append(applBean.getApplSubmitDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		dataParam.append(applBean.getApplStatus().trim() + fldSeperator);

		if (applBean.getApplEndDtStr() != null) dataParam.append(applBean.getApplEndDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		if (applBean.getApplCertSignDtStr() != null)
			dataParam.append(applBean.getApplCertSignDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		dataParam.append(applBean.getNcId().trim() + fldSeperator);

		dataParam.append(applBean.getEmailId().trim() + fldSeperator);

		dataParam.append(applBean.getLastName().trim() + fldSeperator);

		dataParam.append(applBean.getFirstName().trim() + fldSeperator);

		dataParam.append(applBean.getMiddleName().trim() + fldSeperator);

		if (applBean.getApplCmpltDtStr() != null) dataParam.append(applBean.getApplCmpltDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		dataParam.append(applBean.getApplFeeRcvd().trim() + fldSeperator);

		if (applBean.getApplFeeRcvdDtStr() != null)
			dataParam.append(applBean.getApplFeeRcvdDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		dataParam.append(applBean.getApplFeeRcvdBy().trim() + fldSeperator);

		if (applBean.getLastUpdatDtStr() != null) dataParam.append(applBean.getLastUpdatDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		if (applBean.getLastUpdateTmStr() != null)
			dataParam.append(applBean.getLastUpdateTmStr().trim() + fldSeperator);
		else
			dataParam.append(fldSeperator);

		dataParam.append(applBean.getApplicantId().trim() + fldSeperator);

		if (applBean.getApplicantSignDtStr() != null)
			dataParam.append(applBean.getApplicantSignDtStr().trim() + fldSeperator);
		else
			dataParam.append(AppConstants.DEFAULT_DATE + fldSeperator);

		if (applBean.getApplCounty().trim().equals(""))
			dataParam.append(applBean.getApplCounty().trim() + fldSeperator);
		else
			dataParam.append(applBean.getApplCounty().trim() + fldSeperator);

		dataParam.append(applBean.getContactViaEmail().trim() + fldSeperator);

		dataParam.append(applBean.getEnforState().trim() + fldSeperator);

		dataParam.append(applBean.getEnforCountry().trim() + fldSeperator);

		dataParam.append(applBean.getEnforCompanyName().trim() + fldSeperator);

		dataParam.append(applBean.getApplNotes().trim().replaceAll(fldSeperator, "") + fldSeperator);

		logger.debug("********** buildDataParam value is: " + dataParam.toString() + "**********");

		return dataParam.toString().trim();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#getCaseApplicationIdForReport(
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseApplication> getCaseApplicationIdForReport(String applId, String ncId) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationDaoImpl: getCaseApplicationIdForReport(applID:" + applId + ", NCID " + ncId + ")" + "**********\n");

		procCaseApplication8.declareParameters(new SqlParameter("ID_APPLICATION", Types.CHAR), new SqlParameter("NCID", Types.CHAR)).returningResultSet("caseApp", new CaseApplicationRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("ID_APPLICATION", applId).addValue("NCID", ncId);

		Map<String, Object> results = procCaseApplication8.execute(in);

		return (List<CaseApplication>) results.get("caseApp");
	}

}
