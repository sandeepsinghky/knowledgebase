package nc.dhhs.nccss.acts.ecwa.web.security;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import gov.nc.ncidng.ncidngwebservice.schemas.AuthenticateToNCIDv2Response;
import gov.nc.ncidng.ncidngwebservice.schemas.IsMemberOfAGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.ModifyGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;
import nc.dhhs.nccss.acts.ecwa.web.service.AuthenticationWebService;
import nc.dhhs.nccss.acts.ecwa.web.exception.ErrorDescriptor;
import nc.dhhs.nccss.acts.ecwa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;
import nc.dhhs.nccss.acts.ecwa.web.util.EmailManager;

/**
 * This class retrieves user details from the authentication web services.
 * 
 * NCID web service call & logic should go in here.
 * 
 * @author Vijay Peddapalli
 *
 */
public class EcwaUserDetailsServiceImpl implements UserDetailsService
{

	@Autowired
	protected EmailManager emailManager;
	
	private AuthenticationWebService	authenticationService	= null;

	protected final Logger				logger					= Logger.getLogger(getClass());

	public EcwaUserDetailsServiceImpl()
	{
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
	{
		// TODO Auto-generated method stub
		return loadUser(userName, null, null, null, false);
	}

	/**
	 * @param userName
	 * @param password
	 * @param ipaddress
	 * @param browserUserAgent
	 * @return
	 */
	public UserDetails loadAndAuthenticate(String userName, String password, String ipaddress, String browserUserAgent)
	{
		return loadUser(userName, password, ipaddress, browserUserAgent, true);
	}

	/**
	 * @param username
	 * @param password
	 * @param ipaddress
	 * @param browserUserAgent
	 * @param authenticate
	 * @return
	 * @throws AuthenticationException
	 * @throws DataAccessException
	 */
	private UserDetails loadUser(String username, String password, String ipaddress, String browserUserAgent,
			boolean authenticate) throws AuthenticationException, DataAccessException
	{
		UserDetails userDetails = null;
		AuthenticateToNCIDv2Response authResponse = null;
		SearchUserByLoginIDResponse slogInIDResponse = null;
		IsMemberOfAGroupResponse    isMemberOfAGroupResponse=null;
		ModifyGroupResponse modifyGroupResponse= null;
		
		boolean isPasswordExpired = false;
		boolean isAccountExpired = false;
		boolean isAccountDisabled = false;
		boolean isAccountLocked = false;

		try
		{
			if (authenticate)
			{
				authResponse = getAuthenticationService().getNCIDAuthenticationReponse(username, password);
			}

			// check if the authentication is success or not
			String authenticationCode = authResponse.getAuthenticateToNCIDv2Result().getMessageArray().getMessage().get(0).getCode();
			String authDesc = authResponse.getAuthenticateToNCIDv2Result().getMessageArray().getMessage().get(0).getContent();
			if (authenticationCode.equals(AppConstants.NCID_AUTH_SUCCESS))
			{
				slogInIDResponse = getAuthenticationService().searchUserByLoginID(username);
				userDetails = new EcwaUserDetails(slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getUserID(), password, new ArrayList<GrantedAuthority>(), !isAccountExpired, !isAccountLocked, !isPasswordExpired, !isAccountDisabled, authDesc);
				//if  Member exist in Group  
				String memberGUID= slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getGUID();
				String memberDn = slogInIDResponse.getSearchUserByLoginIDResult().getEntryArray().getEntry().get(0).getUserDN();
				isMemberOfAGroupResponse=getAuthenticationService().memberExistInGroup(memberDn, memberGUID, "");
				// isMemberOfAGroupResponse.getIsMemberOfAGroupResult().getMessageArray().getMessage();
				//if  Add Member to Group  
				modifyGroupResponse=getAuthenticationService().modifyGroup(memberDn, memberGUID, "", "ADD");
			}
			else{
				// else check for the authentication failures reason/ fault codes.
				// have to include code for errors & should show appropriate messages on the front end why user failed login.
				if(authenticationCode.equals(AppConstants.NCID_USER_LOCKED_CODE)){
					userDetails = new EcwaUserDetails(username, password, new ArrayList<GrantedAuthority>(), !isAccountExpired, isAccountLocked, !isPasswordExpired, !isAccountDisabled, authDesc);
				}else if (authenticationCode.equals(AppConstants.NCID_PASSWORD_EXPIRED)){
					userDetails = new EcwaUserDetails(username, password, new ArrayList<GrantedAuthority>(), !isAccountExpired, !isAccountLocked, isPasswordExpired, !isAccountDisabled, authDesc);
				}				
				
			}

		}

		catch (UsernameNotFoundException unfex)
		{
			logger.error(unfex.getMessage());
			throw unfex;
		}
		catch (AuthenticationServiceException asex)
		{
			logger.error(asex.getMessage());
			throw asex;
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			ErrorDescriptor errorBean = new ErrorDescriptor(this.getClass().getName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);

			String body = errorBean.getEmailBody();

			emailManager.sendEmail(WebsiteConfiguration.getEmailNotify(), AppConstants.MAIL_FROM,
					AppConstants.MAIL_ERROR_SUBJECT, body);

			throw new AuthenticationServiceException(
					"A technical problem with login has been detected.  Please try to login later. An Administrator has been notified of the error.");
		}

		return userDetails;

	}

	/**
	 * @return
	 */
	public AuthenticationWebService getAuthenticationService()
	{
		return authenticationService;
	}

	/**
	 * @param authenticationService
	 */
	public void setAuthenticationService(AuthenticationWebService authenticationService)
	{
		this.authenticationService = authenticationService;
	}
}
