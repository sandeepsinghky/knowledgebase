package nc.dhhs.nccss.acts.ecwa.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.CodeLookUpDao;
import nc.dhhs.nccss.acts.ecwa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecwa.web.service.CodeLookUpService;

/*
 * @author Phani Konuru
 */

public class CodeLookUpServiceImpl implements CodeLookUpService
{

	protected final Logger	logger	= Logger.getLogger(CodeLookUpServiceImpl.class);

	@Autowired
	private CodeLookUpDao	codeLookUpDao;

	/* (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CodeLookUpService#getCodeLookup(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CodeLookUp> getCodeLookup(String lookup) throws SQLException
	{
		return codeLookUpDao.getCodeLookup(lookup);
	}

	/* (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.CodeLookUpService#getGrpCntyLookup(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<CodeLookUp> getGrpCntyLookup(String cnty) throws SQLException
	{
		return codeLookUpDao.getGrpCntyLookup(cnty);
	}
	
	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.CodeLookUpService#getApplFees()
	 */
	@Transactional(readOnly = true)
	public String getApplFees() throws SQLException
	{
		return codeLookUpDao.getApplFees();
	}

}
