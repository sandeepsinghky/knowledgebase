package nc.dhhs.nccss.acts.ecwa.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.WebServiceException;
import org.springframework.ws.client.core.WebServiceTemplate;

import gov.nc.ncidng.ncidngwebservice.schemas.IsMemberOfAGroupRequest;
import gov.nc.ncidng.ncidngwebservice.schemas.IsMemberOfAGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.ModifyGroupRequest;
import gov.nc.ncidng.ncidngwebservice.schemas.ModifyGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDRequest;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;
import nc.dhhs.nccss.acts.ecwa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecwa.web.service.MFAWebService;

public class MFAWebServiceImpl implements MFAWebService
{
	@Autowired
	private WebServiceTemplate	webServiceTemplate;
	protected final Logger		logger	= Logger.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.MFAWebService#
	 * searchUserByLoginID(java.lang.String)
	 */
	
	public SearchUserByLoginIDResponse searchUserByNCID(String ncid) throws WebServiceException
	{
		SearchUserByLoginIDRequest sLogInIDRequest = new SearchUserByLoginIDRequest();

		sLogInIDRequest.setUserID(ncid);
		sLogInIDRequest.setAppID(WebsiteConfiguration.getAppIDReqNCID());
		sLogInIDRequest.setAppPassword(WebsiteConfiguration.getAppPwdReqNCID());
		sLogInIDRequest.setSearchOp(WebsiteConfiguration.getSearchOp());
		SearchUserByLoginIDResponse slogInIDResponse = (SearchUserByLoginIDResponse) webServiceTemplate.marshalSendAndReceive(sLogInIDRequest);

		return slogInIDResponse;
		 
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.MFAWebService#
	 * memberExistinGroup(java.lang.String)
	 */
	public IsMemberOfAGroupResponse memberExistInGroup(String memberDn, String groupDN)
			throws WebServiceException
	{
		
		 
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
	 * @see nc.dhhs.nccss.acts.ecwa.web.service.MFAWebService#
	 * modifyGroup(java.lang.String)
	 */
	
	public ModifyGroupResponse modifyGroup(String memberDn,  String groupDN, String action)
			throws WebServiceException
	{
		 
		
		ModifyGroupRequest modifyGroupRequest = new ModifyGroupRequest();
		modifyGroupRequest.setAppID(WebsiteConfiguration.getAppIDReqNCID());
		modifyGroupRequest.setAppPassword(WebsiteConfiguration.getAppPwdReqNCID());
		modifyGroupRequest.setMemberDN(memberDn);
		modifyGroupRequest.setGroupDN(groupDN);
		modifyGroupRequest.setAction(action);
		logger.info("modifyGroup opeartion  with AppID: " + WebsiteConfiguration.getAppIDReqNCID()+ " App Password " + WebsiteConfiguration.getAppPwdReqNCID() + " Member DN "+memberDn + " Group DN "+ groupDN); 
		ModifyGroupResponse modifyGroupResponse = (ModifyGroupResponse) webServiceTemplate.marshalSendAndReceive(modifyGroupRequest);
		return modifyGroupResponse;
	}

}
