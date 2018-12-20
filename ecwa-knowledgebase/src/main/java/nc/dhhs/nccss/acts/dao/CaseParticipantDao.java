package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.*;

import nc.dhhs.nccss.acts.ecwa.beans.CaseActsParticipant;
import nc.dhhs.nccss.acts.ecwa.beans.CasePartDemo;
import nc.dhhs.nccss.acts.ecwa.beans.CaseParticipant;;

/**
 * @author Phani Konuru
 */
public interface CaseParticipantDao
{
	/**
	 * @param partInfo
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String createParticipant(CaseParticipant partInfo, String ncid) throws SQLException;

	/**
	 * @param applId
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseParticipant> getParticipantByPartId(String applId, String partId) throws SQLException;

	/**
	 * @param applId
	 * @throws SQLException
	 */
	public List<CaseParticipant> getAllParticipants(String applId) throws SQLException;

	/**
	 * @param applId
	 * @param partType
	 * @return
	 * @throws SQLException
	 */
	public List<CaseParticipant> getParticipantByPartType(String applId, String partType) throws SQLException;

	/**
	 * @param partInfo
	 * @throws SQLException
	 */
	public String updateParticipant(CaseParticipant partInfo, String ncid) throws SQLException;

	/**
	 * @param partInfo
	 * @throws SQLException
	 */
	public String deleteParticipant(CaseParticipant partInfo, String ncid) throws SQLException;

	/**
	 * @param fName
	 * @param lName
	 * @return
	 * @throws SQLException
	 */
	public List<CaseActsParticipant> searchActsParticipant(String fName, String lName) throws SQLException;

	/**
	 * @param partId
	 * @return
	 * @throws SQLException
	 */
	public List<CaseActsParticipant> getActsParticipantsById(String partId) throws SQLException;

	/**
	 * @param caseNum
	 * @return
	 * @throws SQLException
	 */
	public List getActsCaseParticipants(String caseNum) throws SQLException;

	/**
	 * @param appId
	 * @param partId
	 * @param mpiNum
	 * @param op
	 * @throws SQLException
	 */
	public String createUseActsPart(String appId, String partId, String mpiNum, String ncId, String op) throws SQLException;

	/**
	 * @param appId
	 * @param mpiList
	 * @param op
	 * @return
	 * @throws SQLException
	 */
	public Map<String, String> createActsCase(String appId, String nb_case, String mpiList, String ncid, String op) throws SQLException;
	
	/**
	 * @param partInfo
	 * @param ncid
	 * @return
	 * @throws SQLException
	 */
	public String updateActsParticipant(CaseParticipant partInfo, String ncId) throws SQLException;
	
	/**
	 * @param applId
	 * @param partId
	 * @param mpiId
	 * @return
	 * @throws SQLException
	 */
	public List<CasePartDemo> getPartDemo(CaseParticipant partInfo) throws SQLException;

}
