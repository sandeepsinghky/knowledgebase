package nc.dhhs.nccss.acts.ecwa.caseworker.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sun.media.jfxmedia.logging.Logger;

import nc.dhhs.nccss.acts.dao.CaseParticipantDao;
import nc.dhhs.nccss.acts.dao.CaseWorkerDao;
import nc.dhhs.nccss.acts.ecwa.caseworker.beans.CaseWorker;
import nc.dhhs.nccss.acts.ecwa.caseworker.web.service.CaseWorkerService;

public class CaseWorkerServiceImpl implements CaseWorkerService
{

	@Autowired
	private CaseWorkerDao		caseWorkerDao;
	
	@Transactional
	public  CaseWorker  getCaseWorkerByWorkerNumber(String workernum) throws SQLException
	{
		// TODO Auto-generated method stub
		return caseWorkerDao.getCaseWorkerByWorkerNumber(workernum);
	}
	
	@Transactional
	public List<CaseWorker> getCaseWorkerListBySelectionCriteria(Map paramList) throws SQLException
	{
		
		return caseWorkerDao.getCaseWorkerListBySelectionCriteria(paramList);
	}

	@Transactional
	public String createCaseWorker(CaseWorker caseWorker) throws SQLException
	{
				
		return caseWorkerDao.createCaseWorker(caseWorker);
	}

	@Transactional
	public String upadateCaseWorker(CaseWorker caseWorker) throws SQLException
	{
		return caseWorkerDao.upadateCaseWorker(caseWorker);
	}

	@Transactional
	public String deleteCaseWorker(String workernum) throws SQLException
	{
		return caseWorkerDao.deleteCaseWorker(workernum);
	}
 

	
	

}
