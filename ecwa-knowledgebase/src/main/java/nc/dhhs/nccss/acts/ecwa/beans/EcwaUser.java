/**
 * 
 */
package nc.dhhs.nccss.acts.ecwa.beans;

import java.io.Serializable;

/**
 * Bean used to leverage NCID user to the DAO user.
 * 
 * @author Vijay Peddapalli
 *
 */
public class EcwaUser implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 
	 */
	private String				guiId;

	private String				loginName;

	private String				lastName;

	private String				firstName;

	private String				middleName;

	private String				email;

	private String				phone;

	private String				userType;

	private String				wkrNm;

	private String				wkrCntyCode;

	private String				wkrCntyNm;

	private String				wkrIsAdmin;

	/**
	 * @return the userId
	 */
	public String getGuiId()
	{
		return guiId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setGuiId(String guiId)
	{
		this.guiId = guiId;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName()
	{
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	/**
	 * @return the fullName
	 */
	public String getMiddlelName()
	{
		return middleName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setMiddlelName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	public String getWkrNm()
	{
		return wkrNm;
	}

	public void setWkrNm(String wkrNm)
	{
		this.wkrNm = wkrNm;
	}

	public String getWkrCntyCode()
	{
		return wkrCntyCode;
	}

	public void setWkrCntyCode(String wkrCntyCode)
	{
		this.wkrCntyCode = wkrCntyCode;
	}

	public String getWkrCntyNm()
	{
		return wkrCntyNm;
	}

	public void setWkrCntyNm(String wkrCntyNm)
	{
		this.wkrCntyNm = wkrCntyNm;
	}

	public String getWkrIsAdmin()
	{
		return wkrIsAdmin;
	}

	public void setWkrIsAdmin(String wkrIsAdmin)
	{
		this.wkrIsAdmin = wkrIsAdmin;
	}

}
