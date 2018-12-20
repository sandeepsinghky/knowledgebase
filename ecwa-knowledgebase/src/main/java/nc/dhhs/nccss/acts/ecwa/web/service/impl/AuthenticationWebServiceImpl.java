/**
 * 
 */
package nc.dhhs.nccss.acts.ecwa.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.WebServiceException;
import org.springframework.ws.client.core.WebServiceTemplate;

import gov.nc.ncidng.ncidngwebservice.schemas.AuthenticateToNCIDv2Request;
import gov.nc.ncidng.ncidngwebservice.schemas.AuthenticateToNCIDv2Response;
import gov.nc.ncidng.ncidngwebservice.schemas.IsMemberOfAGroupRequest;
import gov.nc.ncidng.ncidngwebservice.schemas.IsMemberOfAGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.ModifyGroupRequest;
import gov.nc.ncidng.ncidngwebservice.schemas.ModifyGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDRequest;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;
import nc.dhhs.nccss.acts.ecwa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecwa.web.service.AuthenticationWebService;

/**
 * @author Vijay Peddapalli, Phani Konuru
 *
 */
public class AuthenticationWebServiceImpl implements AuthenticationWebService
{

	@Autowired
	private WebServiceTemplate	webServiceTemplate;

	protected final Logger		logger	= Logger.getLogger(getClass());

	/**
	 * 
	 */
	public AuthenticationWebServiceImpl()
	{
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.AuthenticationWebService#
	 * getNCIDAuthenticationReponse(java.lang.String, java.lang.String)
	 */
	public AuthenticateToNCIDv2Response getNCIDAuthenticationReponse(String loginName, String password)
			throws WebServiceException
	{

		AuthenticateToNCIDv2Request authRequest = new AuthenticateToNCIDv2Request();

		authRequest.setAppID(WebsiteConfiguration.getAppIDReqNCID());

		authRequest.setAppPassword(WebsiteConfiguration.getAppPwdReqNCID());

		authRequest.setUserID(loginName);

		authRequest.setUserPassword(password);

		AuthenticateToNCIDv2Response authResponse = (AuthenticateToNCIDv2Response) webServiceTemplate.marshalSendAndReceive(authRequest);

		/*
		 * String returnCode =
		 * authResponse.getAuthenticateToNCIDv2Result().getMessageArray().
		 * getMessage().get(0).getCode(); if (returnCode.equals("0")) {
		 * logger.info("after AuthenticateToNCIDv2Response"); }
		 */
		return authResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.AuthenticationWebService#
	 * searchUserByLoginID(java.lang.String)
	 */
	public SearchUserByLoginIDResponse searchUserByLoginID(String loginName) throws WebServiceException
	{
		// TODO Auto-generated method stub

		SearchUserByLoginIDRequest sLogInIDRequest = new SearchUserByLoginIDRequest();

		sLogInIDRequest.setUserID(loginName);

		sLogInIDRequest.setAppID(WebsiteConfiguration.getAppIDReqNCID());
		sLogInIDRequest.setAppPassword(WebsiteConfiguration.getAppPwdReqNCID());
		sLogInIDRequest.setSearchOp(WebsiteConfiguration.getSearchOp());

		SearchUserByLoginIDResponse slogInIDResponse = (SearchUserByLoginIDResponse) webServiceTemplate.marshalSendAndReceive(sLogInIDRequest);

		return slogInIDResponse;
	}
	
	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.AuthenticationWebService#
	 * memberExistinGroup(java.lang.String)
	 */
	public IsMemberOfAGroupResponse memberExistInGroup(String memberDn,String memberGUID, String groupDN) throws WebServiceException
	{
		groupDN="cn=DHHS-CSS-MFA - "+memberGUID+",ou=Groups,o=NC";
	 			 
		IsMemberOfAGroupRequest isMemberOfAGroupRequest = new IsMemberOfAGroupRequest();
		isMemberOfAGroupRequest.setAppID(WebsiteConfiguration.getAppIDReqNCID());
		isMemberOfAGroupRequest.setAppPassword(WebsiteConfiguration.getAppPwdReqNCID());
		isMemberOfAGroupRequest.setMemberDN(memberDn);
		isMemberOfAGroupRequest.setGroupDN(groupDN);
		logger.info("isMemberOfAGroup opeartion  with AppID: " + WebsiteConfiguration.getAppIDReqNCID()+ " App Password " + WebsiteConfiguration.getAppPwdReqNCID() + " Member DN "+memberDn + " Group DN "+ groupDN); 
		IsMemberOfAGroupResponse isMemberOfAGroupResponse = (IsMemberOfAGroupResponse) webServiceTemplate.marshalSendAndReceive(isMemberOfAGroupRequest);
		
		return isMemberOfAGroupResponse;
	
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.AuthenticationWebService#
	 * modifyGroup(java.lang.String)
	 */
	public ModifyGroupResponse modifyGroup(String memberDn, String memberGUID, String groupDN, String action)
			throws WebServiceException
	{
		groupDN="cn=DHHS-CSS-MFA - "+memberGUID+",ou=Groups,o=NC";
		
		ModifyGroupRequest modifyGroupRequest = new ModifyGroupRequest();
		modifyGroupRequest.setAppID(WebsiteConfiguration.getAppIDReqNCID());
		modifyGroupRequest.setAppPassword(WebsiteConfiguration.getAppPwdReqNCID());
		modifyGroupRequest.setMemberDN(memberDn);
		modifyGroupRequest.setGroupDN(groupDN);
		modifyGroupRequest.setAction("ADD");
		logger.info("modifyGroup opeartion  with AppID: " + WebsiteConfiguration.getAppIDReqNCID()+ " App Password " + WebsiteConfiguration.getAppPwdReqNCID() + " Member DN "+memberDn + " Group DN "+ groupDN); 
		ModifyGroupResponse modifyGroupResponse = (ModifyGroupResponse) webServiceTemplate.marshalSendAndReceive(modifyGroupRequest);
		return modifyGroupResponse;
	}
	

}
