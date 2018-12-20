package nc.dhhs.nccss.acts.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import nc.dhhs.nccss.acts.dao.CaseApplicationLockDao;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecwa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */
@Repository
public class CaseApplicationLockDaoImpl implements CaseApplicationLockDao
{

	protected final Logger		logger		= Logger.getLogger(CaseApplicationLockDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseApplLock1, procCaseApplLock2;

	private static final String	SCREEN_SP	= "APP_RESP";
	private static final String	DB_SCHEMA	= WebsiteConfiguration.getDbSchema();

	@Autowired
	//@Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CaseApplicationLockDaoImpl: SETDATASOURCE**********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		//this.jdbcTemplate.setResultsMapCaseInsensitive(true);
		
		procCaseApplLock1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_UPDATE_APPL_LOCK");
		procCaseApplLock2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_LOCK_APPLID");

	}


	/* (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.CaseApplicationLockDao#createCaseApplicationLock(nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock)
	 */
	@Override
	public String createCaseApplicationLock(CaseApplLock applLockBean) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationLockDaoImpl: createCaseApplicationLock **********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, applLockBean.getLockedBy());

		String returnCode = ",,,,";

		String dataFields = buildDataParam(applLockBean);

		logger.info("\n********** Users NCID: " + applLockBean.getLockedBy() + "**********\n");

		try
		{
			procCaseApplLock1.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplLock1.execute(in);

			logger.info("Create createCaseApplicationLock returned code: " + results.get("RETURNCODE"));

			if (results.get("RETURNCODE").toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				returnCode = results.get("RETURNCODE").toString();

			}
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());			
		}

		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseApplicationDao#updateCaseApplication(nc.dhhs.
	 * nccss.acts.ecoa.beans.CaseApplication)
	 */
	@Override
	public String UpdateCaseApplicationLock(CaseApplLock applLockBean, String ncId) throws SQLException
	{
		logger.debug("\n********** IN createCaseApplicationLock: UpdateCaseApplicationLock**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncId);

		String returnCode = ",,,,";

		String dataFields = buildDataParam(applLockBean);

		logger.info("User NCID: " + ncId);

		try
		{
			procCaseApplLock1.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplLock1.execute(in);

			logger.info("UpdateCaseApplicationLock returned:" + results.get("RETURNCODE"));

			returnCode = results.get("RETURNCODE").toString();
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}

		return returnCode;
	}

	/* (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.dao.CaseApplicationLockDao#deleteCaseApplicationLock(nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock, java.lang.String)
	 */
	@Override
	public String deleteCaseApplicationLock(CaseApplLock applLockBean, String ncId) throws SQLException
	{
		logger.debug("\n********** IN createCaseApplicationLock:  deleteCaseApplicationLock(applLockBean,ncid: " + ncId + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncId.trim());

		String returnCode = ",,,,";

		String dataFields = buildDataParam(applLockBean);

		try
		{
			procCaseApplLock1.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseApplLock1.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("deleteCaseApplicationLock returned: " + results.get("RETURNCODE"));
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}
		return returnCode;
	}
	
	@Override
	public CaseApplLock getApplLock(String applicationId) throws SQLException
	{
		logger.debug("\n********** IN createCaseApplicationLock: getApplLock**********\n");

		procCaseApplLock2.declareParameters(new SqlInOutParameter("Application ID", Types.CHAR), new SqlInOutParameter("LOCKED BY ID", Types.CHAR), new SqlOutParameter("TS LOCK", Types.TIMESTAMP));

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applicationId);

		Map<String, Object> results = procCaseApplLock2.execute(in);

		CaseApplLock applLockBean = null;

		if (results.get("LOCKED BY ID") != null)
		{
			applLockBean = new CaseApplLock();
			applLockBean.setApplicationId(applicationId);
			applLockBean.setLockedBy((String)results.get("LOCKED BY ID"));
			if(results.get("TS LOCK") != null){
				applLockBean.setTsLock((Timestamp)results.get("TS LOCK"));
			}
		}

		return applLockBean;
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
	 * @param applLockBean
	 * @return
	 */
	private String buildDataParam(CaseApplLock applLockBean)
	{

		logger.debug("\n********** IN CaseApplicationLockDaoImpl:  buildDataParam(applLockBean)" + "**********\n");

		//string to separate field values passed to the SP
		String fldSeperator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(applLockBean.getApplicationId().trim() + fldSeperator);

		dataParam.append(applLockBean.getLockedBy().trim() + fldSeperator);

		logger.info("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}

}
