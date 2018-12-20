package nc.dhhs.nccss.acts.ecwa.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;
import nc.dhhs.nccss.acts.ecwa.beans.EcwaUser;
import nc.dhhs.nccss.acts.ecwa.web.service.AuthenticationWebService;
import nc.dhhs.nccss.acts.ecwa.web.service.UserService;

/**
 * @author Vijay Peddapalli
 *
 */
public class UserServiceImpl implements UserService
{

	protected final Logger				logger	= Logger.getLogger(getClass());

	@Autowired
	private AuthenticationWebService	authenticationService;

	public UserServiceImpl()
	{
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserService#getUser(java.lang.String)
	 */
	public EcwaUser getUser(String loginId)
	{
		// TODO Auto-generated method stub
		logger.info("in userId passed in userserviceimpl is : " + loginId);

		EcwaUser user = null;

		user = buildUser(loginId);

		return user;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserService#userExists(java.lang.
	 * String)
	 */
	public boolean userExists(String userId) throws Exception
	{

		// call the dao layer to see if the user exists,

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.UserService#createUser(nc.dhhs.nccss.
	 * acts.ecoa.beans.EcoaUser)
	 */
	public boolean createUser(EcwaUser user) throws Exception
	{
		return true;
	}

	/**
	 * @param loginId
	 * @return
	 */
	private EcwaUser buildUser(String loginId)
	{

		SearchUserByLoginIDResponse slogInIDResponse = null;

		slogInIDResponse = authenticationService.searchUserByLoginID(loginId);

		if (slogInIDResponse == null) { return null; }

		EcwaUser user = new EcwaUser();

		user.setGuiId(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getGUID());
		user.setFirstName(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getFirstName());
		user.setLastName(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getLastName());
		user.setEmail(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getEMail());
		user.setLoginName(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getUserID());
		user.setUserType(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getUserType());

		return user;

	}
}
