package nc.dhhs.nccss.acts.ecwa.caseworker.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import nc.dhhs.nccss.acts.ecwa.caseworker.beans.CaseWorker;

public interface CaseWorkerService
{

	
	/**
	 * @param paramList of Screen Input
	 * @param Workernum
	 * @return CaseWorker for given Worker #
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public CaseWorker getCaseWorkerByWorkerNumber(String workernum) throws SQLException;
	
	/**
	 * @param paramList of Screen Input
	 * @param HashMap
	 * @return List of CaseWorker
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List <CaseWorker> getCaseWorkerListBySelectionCriteria(Map paramList) throws SQLException;
	
	/**
	 * @param CaseWorker Object Containing information about Case Worker to be Created 
	 * @param HashMap
	 * @return String
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public String createCaseWorker(CaseWorker caseWorker) throws SQLException;
	
	/**
	 * @param CaseWorker Object Containing information about Case Worker to be Updated 
	 * @param HashMap
	 * @return String
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public String upadateCaseWorker(CaseWorker caseWorker) throws SQLException;
	
	/**
	 * @param CaseWorker Object Containing information about Case Worker to be Deleted 
	 * @param HashMap
	 * @return String
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public String deleteCaseWorker(String workernum) throws SQLException;
}
