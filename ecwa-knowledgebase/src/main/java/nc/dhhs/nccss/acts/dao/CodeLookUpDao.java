package nc.dhhs.nccss.acts.dao;

import java.sql.SQLException;
import java.util.List;

import nc.dhhs.nccss.acts.ecwa.beans.CodeLookUp;

/**
 * @author Phani Konuru
 */
public interface CodeLookUpDao
{
	/**
	 * @param lookup
	 * @return
	 * @throws SQLException
	 */
	public List<CodeLookUp> getCodeLookup(String lookup) throws SQLException;
	
	/**
	 * @param cnty
	 * @return
	 * @throws SQLException
	 */
	public List<CodeLookUp> getGrpCntyLookup(String cnty) throws SQLException;
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public String getApplFees() throws SQLException;
}
