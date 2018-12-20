package nc.dhhs.nccss.acts.ecwa.web;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * 
 * @author Vijay Peddapalli
 * 
 */
public class EcwaSession implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1792754884477964889L;

	// private Permission permObj;

	private Long				permission;

	private List<Long>			permissions;

	/**
	 * @param permissions
	 *            the permissions to set
	 */
	public void setPermissions(List<Long> permissions)
	{
		this.permissions = permissions;
	}

	/**
	 * 
	 * @return
	 */
	public Long getPermission()
	{
		return permission;
	}

	/**
	 * 
	 * @param permission
	 */
	// public void setPermission(long permission)
	// {

	// this.permission = permission;
	// }

	/**
	 * 
	 * @return
	 */
	public List<Long> getPermissions()
	{
		return permissions;
	}

	/**
	 * @return the permObj
	 */
	// public Permission getPermObj()
	// {
	// return permObj;
	// }

	/**
	 * @param permObj
	 *            the permObj to set
	 */
	// public void setPermObj(Permission permObj)
	// {
	// this.permObj = permObj;
	// }

}
