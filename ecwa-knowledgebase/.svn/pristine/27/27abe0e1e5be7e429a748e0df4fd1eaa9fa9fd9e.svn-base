package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.*;
import nc.dhhs.nccss.acts.ecwa.beans.CaseApplLock;
import nc.dhhs.nccss.acts.ecwa.beans.CaseAppSearch;

/**
 * @author Phani Konuru
 */
public interface CaseApplicationLockDao
{

	
	/**
	 * @param applLockBean
	 * @return
	 * @throws SQLException
	 */
	public String createCaseApplicationLock(CaseApplLock applLockBean) throws SQLException;
	
	
	/**
	 * @param applLockBean
	 * @return
	 * @throws SQLException
	 */
	public String UpdateCaseApplicationLock(CaseApplLock applLockBean, String ncId) throws SQLException;

	
	/**
	 * @param applLockBean
	 * @return
	 * @throws SQLException
	 */
	public String deleteCaseApplicationLock(CaseApplLock applLockBean, String ncId) throws SQLException;
	
	/**
	 * @param applicationId
	 * @return
	 * @throws SQLException
	 */
	public CaseApplLock getApplLock(String applicationId) throws SQLException;



}
