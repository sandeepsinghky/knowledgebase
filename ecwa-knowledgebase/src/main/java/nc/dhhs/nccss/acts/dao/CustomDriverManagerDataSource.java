package nc.dhhs.nccss.acts.dao;

import java.util.Base64;
import java.nio.charset.Charset;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * @author Phani Konuru
 */

//public class CustomDriverManagerDataSource extends DriverManagerDataSource
public class CustomDriverManagerDataSource extends BasicDataSource
{

	private String dbSchema;

	public CustomDriverManagerDataSource()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.AbstractDriverBasedDataSource#
	 * setPassword(java.lang.String)
	 */
	public synchronized void setPassword(String encryptedPassword)
	{
		super.setPassword(base64Decode(encryptedPassword));
	}

	/**
	 * @param token
	 * @return encoded
	 */
	public static String base64Encode(String token)
	{

		return Base64.getEncoder().encodeToString(token.getBytes());
	}

	/**
	 * @param token
	 * @return
	 */
	public static String base64Decode(String token)
	{
		byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
		return new String(decodedBytes, Charset.forName("UTF-8"));
	}

	/**
	 * @return the dbSchema
	 */
	public String getDbSchema()
	{
		return dbSchema;
	}

	/**
	 * @param dbSchema
	 *            the dbSchema to set
	 */
	public void setDbSchema(String dbSchema)
	{
		this.dbSchema = dbSchema;
	}
}
