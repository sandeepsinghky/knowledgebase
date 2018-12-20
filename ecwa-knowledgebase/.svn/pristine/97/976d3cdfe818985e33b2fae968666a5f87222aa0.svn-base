/**
 * 
 */
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

import nc.dhhs.nccss.acts.dao.CaseParticipantDao;
import nc.dhhs.nccss.acts.dao.rowmap.ActsCaseParticipantRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.CaseActsPartDetailsRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.CaseActsParticipantRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.CasePartDemoRowMapper;
import nc.dhhs.nccss.acts.dao.rowmap.CaseParticipantRowMapper;
import nc.dhhs.nccss.acts.ecwa.beans.CaseActsParticipant;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartDemo;
import nc.dhhs.nccss.acts.ecwa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecwa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Phani Konuru
 *
 */

@Repository
public class CaseParticipantDaoImpl implements CaseParticipantDao
{

	protected final Logger		logger		= Logger.getLogger(CaseParticipantDaoImpl.class);

	private JdbcTemplate		jdbcTemplate;

	private SimpleJdbcCall		procCaseParticipant1, procCaseParticipant2, procCaseParticipant3, procCaseParticipant4,
			procCaseParticipant5, procCaseParticipant6, procCaseParticipant7, procCaseParticipant8,
			procCaseParticipant9, procCaseParticipant10, procCaseParticipant11;

	private static final String	SCREEN_SP	= "APP_APPLICANT";

	private static final String	DB_SCHEMA	= WebsiteConfiguration.getDbSchema();

	@Autowired
	//@Required
	public void setDataSource(DataSource dataSource)
	{

		logger.debug("\n********** IN CaseParticipantDaoImpl: SET DATASOURCE **********\n");

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.jdbcTemplate.setSkipUndeclaredResults(true);

		procCaseParticipant1 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_APPL_ROLE_PART");

		procCaseParticipant2 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_UPDATE_PARTICIPANT");

		procCaseParticipant3 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_APPLICANT_PART");

		procCaseParticipant4 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_APPLICATION_PART");

		procCaseParticipant5 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_SEARCH_PART_BY_NAME");

		procCaseParticipant6 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_ACTS_PART_DETAIL");

		procCaseParticipant7 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_ACTS_CASE_PART");

		procCaseParticipant8 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_ACTS_UPDATE_PART");

		procCaseParticipant9 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_ACTS_UPDATE_CASE");

		procCaseParticipant10 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_ACTS_UPDATE_DEMO");

		procCaseParticipant11 = new SimpleJdbcCall(jdbcTemplate).withoutProcedureColumnMetaDataAccess().withSchemaName(DB_SCHEMA).withProcedureName("FKWEB_R_PART_DEMO_STATUS");

	}

	/**
	 * @param operationInsert
	 * @param trim
	 * @return
	 */
	private String buildCommonParam(String op, String ncid)
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  buildCommonParam(" + op + "," + ncid + ")" + "**********\n");
		// TODO Auto-generated method stub
		String commonParam = op + ",,," + SCREEN_SP + "," + ncid + "," + AppConstants.INTERFACE_CODE + ",,";

		logger.info("commonParam: " + commonParam);
		return commonParam;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#getParticipantByPartType(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CaseParticipant> getParticipantByPartType(String applId, String partType) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  getParticipantByPartType(appId: " + applId + "," + "partType: " + partType + ")" + "**********\n");

		procCaseParticipant1.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Participant Type", Types.CHAR)).returningResultSet("caseParticipant", new CaseParticipantRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applId).addValue("Participant Type", partType);

		Map<String, Object> results = procCaseParticipant1.execute(in);

		return (List<CaseParticipant>) results.get("caseParticipant");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#updateParticipant(nc.dhhs.nccss
	 * .acts.ecoa.beans.CaseParticipant, java.lang.String)
	 */
	public String updateParticipant(CaseParticipant partInfo, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  updateParticipant(partinfo," + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncid.trim());
		String returnCode = ",,,,";

		String dataFields = buildDataParam(partInfo);

		try
		{
			procCaseParticipant2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseParticipant2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("updateParticipant returned: " + returnCode);
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
		return returnCode;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#deleteParticipant(nc.dhhs.nccss
	 * .acts.ecoa.beans.CaseParticipant, java.lang.String)
	 */
	public String deleteParticipant(CaseParticipant partInfo, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  deleteParticipant(partinfo,ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_DELETE, ncid.trim());

		String returnCode = ",,,,";

		String dataFields = buildDataParam(partInfo);

		try
		{
			procCaseParticipant2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseParticipant2.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("deleteParticipant returned: " + results.get("RETURNCODE"));
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#createParticipant(nc.dhhs.nccss
	 * .acts.ecoa.beans.CaseParticipant, java.lang.String)
	 */
	public String createParticipant(CaseParticipant partInfo, String ncid) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl: createParticipant(partinfo,ncid: " + ncid + ")" + "**********\n");

		String commonParam = buildCommonParam(AppConstants.OPERATION_INSERT, ncid.trim());
		String returnCode = ",,,,";
		String dataFields = buildDataParam(partInfo);
		String partId = "";

		try
		{
			procCaseParticipant2.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseParticipant2.execute(in);

			logger.info("createParticipant returned: " + results.get("RETURNCODE"));

			if ((results.get("RETURNCODE")).toString().substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				partId = results.get("DATAFIELDS").toString().substring(10, 20).trim();

				logger.info("partId returned: " + partId + "...........");
			}

		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
		}
		return partId;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#getParticipantByPartId(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CaseParticipant> getParticipantByPartId(String applId, String partId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  getParticipantByPartId(applId:" + applId + ", partId: " + partId + ")" + "**********\n");

		procCaseParticipant3.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Applicant ID", Types.CHAR)).returningResultSet("caseParticipant", new CaseParticipantRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applId).addValue("Applicant ID", partId);

		Map<String, Object> results = procCaseParticipant3.execute(in);

		//return (CaseParticipant) results.get("caseParticipant");

		return (List<CaseParticipant>) results.get("caseParticipant");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#getAllParticipants(java.lang.
	 * String)
	 */
	@SuppressWarnings("unchecked")
	public List<CaseParticipant> getAllParticipants(String applId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  getAllParticipants(applId:" + applId + ")" + "**********\n");

		procCaseParticipant4.declareParameters(new SqlParameter("Application ID", Types.CHAR)).returningResultSet("caseParticipant", new CaseParticipantRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", applId);

		Map<String, Object> results = procCaseParticipant4.execute(in);

		//return (CaseParticipant) results.get("caseParticipant");

		return (List<CaseParticipant>) results.get("caseParticipant");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#updateParticipant(nc.dhhs.nccss
	 * .acts.ecoa.beans.CaseParticipant, java.lang.String)
	 */
	public List<CaseActsParticipant> searchActsParticipant(String fName, String lName) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  searchParticipant(First Name:" + fName + ", Last Name: " + lName + ")" + "**********\n");

		String fldSeperator = AppConstants.FLD_SEPARATOR;
		String commonParam = buildCommonParam("", "");
		String returnCode = ",,,,";

		String dataFields = lName + fldSeperator + fName + fldSeperator;

		procCaseParticipant5.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlParameter("DATAFIELDS", Types.VARCHAR)).returningResultSet("caseActsParticipant", new CaseActsParticipantRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

		Map<String, Object> results = procCaseParticipant5.execute(in);

		returnCode = results.get("RETURNCODE").toString();

		logger.info("CaseParticipantDaoImpl:  searchParticipant returned returncode: " + returnCode);

		List<CaseActsParticipant> actPartList = (List<CaseActsParticipant>) results.get("caseActsParticipant");

		logger.debug("\n********** IN CaseParticipantDaoImpl:  searchParticipant(returned list length:" + actPartList.size() + ")");

		return actPartList;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#getActsParticipantsById(java.
	 * lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CaseActsParticipant> getActsParticipantsById(String partId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  getActsParticipantsById(partId: " + partId + ")" + "**********\n");

		procCaseParticipant6.declareParameters(new SqlParameter("Part ID", Types.CHAR)).returningResultSet("caseActsParticipantInfo", new CaseActsPartDetailsRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Part ID", partId);

		Map<String, Object> results = procCaseParticipant6.execute(in);

		return (List<CaseActsParticipant>) results.get("caseActsParticipantInfo");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#getActsCaseParticipants(java.
	 * lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CaseActsParticipant> getActsCaseParticipants(String caseNum) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  getActsCaseParticipants(Case Num: " + caseNum + ")" + "**********\n");

		procCaseParticipant7.declareParameters(new SqlParameter("Case Num", Types.CHAR)).returningResultSet("ActsCaseParticipants", new ActsCaseParticipantRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Case Num", caseNum);

		Map<String, Object> results = procCaseParticipant7.execute(in);

		return (List<CaseActsParticipant>) results.get("ActsCaseParticipants");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.dao.CaseParticipantDao#createUseActsPart(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String createUseActsPart(String appId, String partId, String mpi, String ncId, String op) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  createUseActsPart(Application Id:" + appId + ", Part Id: " + partId + ", MPI#: " + mpi + ", Operation: " + op + ")" + "**********\n");

		String fldSeperator = AppConstants.FLD_SEPARATOR;
		String commonParam = buildCommonParam(op, ncId);
		String returnCode = ",,,,";

		String dataFields = appId + fldSeperator + partId + fldSeperator + mpi + fldSeperator;

		try
		{
			procCaseParticipant8.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseParticipant8.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("createUseActsPart returned: " + returnCode);

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
	 * @see nc.dhhs.nccss.acts.dao.CaseParticipantDao#createActsCase(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Map<String, String> createActsCase(String appId, String nb_case, String mpiList, String ncId, String op)
			throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  createActsCase(Application Id:" + appId + ", case Num: " + nb_case + ", MPI list: " + mpiList + ", Operation: " + op + ")" + "**********\n");

		Map<String, String> resultMap = new HashMap<String, String>();
		String fldSeperator = AppConstants.FLD_SEPARATOR;
		String caseId = "";
		String commonParam = buildCommonParam(op, ncId.trim());
		String returnCode = ",,,,";
		String caseMpiList = mpiList.replaceAll(",", AppConstants.FLD_SEPARATOR);

		String dataFields = appId + fldSeperator + nb_case + fldSeperator + caseMpiList + fldSeperator;

		try
		{
			procCaseParticipant9.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlInOutParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseParticipant9.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("createActsCase returned: " + returnCode);

			if (returnCode.substring(0, 4).trim().equals(AppConstants.OPERATION_SUCCESS))
			{
				String datafields = null;
				if (results.get("DATAFIELDS") != null)
				{
					datafields = results.get("DATAFIELDS").toString();
				}

				logger.info("Create case: ApplicationId: " + appId + ", datafields returned: " + datafields + "...........");

				if (datafields != null && datafields.substring(10, 20).trim() != null)
				{
					caseId = datafields.substring(10, 20).trim();
				}

				logger.info("Create case: ApplicationId: " + appId + ", caseId returned: " + caseId + "...........");

				resultMap.put("caseId", caseId);
			}
			resultMap.put("returnCode", returnCode);
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}
		return resultMap;

	}

	public String updateActsParticipant(CaseParticipant partInfo, String ncId) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  updateActsParticipant(partId," + partInfo.getApplicantId() + ")" + "**********\n");

		String fldSeperator = AppConstants.FLD_SEPARATOR;
		String commonParam = buildCommonParam(AppConstants.OPERATION_UPDATE, ncId);
		String returnCode = ",,,,";

		String dataFields = buildDataParam(partInfo);
		//String dataFields = partInfo.getApplicationId() + fldSeperator + partInfo.getApplicantId() + fldSeperator + partInfo.getPartId() + fldSeperator + "y" + fldSeperator + "n" + fldSeperator +"n" + fldSeperator + "y" + fldSeperator + "y" + fldSeperator + "y" + fldSeperator +"n" + fldSeperator + "n" + fldSeperator + "n" + fldSeperator;

		try
		{
			procCaseParticipant10.declareParameters(new SqlParameter("COMMONPARAM", Types.CHAR), new SqlInOutParameter("RETURNCODE", Types.CHAR), new SqlParameter("DATAFIELDS", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource().addValue("DATAFIELDS", dataFields).addValue("RETURNCODE", returnCode).addValue("COMMONPARAM", commonParam);

			Map<String, Object> results = procCaseParticipant10.execute(in);

			returnCode = results.get("RETURNCODE").toString();

			logger.info("updateActsParticipant returned: " + returnCode);
		}

		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}
		return returnCode;

	}

	@SuppressWarnings("unchecked")
	public List<CasePartDemo> getPartDemo(CaseParticipant partInfo) throws SQLException
	{
		logger.debug("\n********** IN CaseParticipantDaoImpl:  getPartDemoStatus(applId:" + partInfo.getApplicationId() + ", partId: " + partInfo.getApplicantId() + ", mpiId: " + partInfo.getPartId() + ")" + "**********\n");

		procCaseParticipant11.declareParameters(new SqlParameter("Application ID", Types.CHAR), new SqlParameter("Applicant ID", Types.CHAR), new SqlParameter("Part ID", Types.CHAR)).returningResultSet("partDemo", new CasePartDemoRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("Application ID", partInfo.getApplicationId()).addValue("Applicant ID", partInfo.getApplicantId()).addValue("Part ID", partInfo.getPartId());

		Map<String, Object> results = procCaseParticipant11.execute(in);

		return (List<CasePartDemo>) results.get("partDemo");
	}

	/**
	 * @param partInfo
	 * @return
	 */
	private String buildDataParam(CaseParticipant partInfo)
	{

		logger.debug("\n********** IN CaseParticipantDaoImpl:  buildDataParam(partInfo)" + "**********\n");

		//string to separate field values passed to the SP
		String fldSeparator = AppConstants.FLD_SEPARATOR;

		StringBuffer dataParam = new StringBuffer(" ");

		dataParam.append(partInfo.getApplicationId().trim() + fldSeparator);

		dataParam.append(partInfo.getApplicantId().trim() + fldSeparator);

		dataParam.append(partInfo.getPartId().trim() + fldSeparator);

		dataParam.append(partInfo.getIsPartNameSelected() + fldSeparator);

		dataParam.append(partInfo.getIsPartSSNSelected() + fldSeparator);

		dataParam.append(partInfo.getIsPartDOBSelected() + fldSeparator);

		dataParam.append(partInfo.getIsPartRaceSelected() + fldSeparator);

		dataParam.append(partInfo.getIsPartGndrSelected() + fldSeparator);

		dataParam.append(partInfo.getIsPartLangSelected() + fldSeparator);

		dataParam.append(partInfo.getIsPartHphSelected() + fldSeparator);

		dataParam.append(partInfo.getIsPartWphSelected() + fldSeparator);

		dataParam.append(partInfo.getIsPartAddrSelected() + fldSeparator);

		logger.debug("buildDataParam =: " + dataParam.toString().trim());

		return dataParam.toString().trim();
	}

}
