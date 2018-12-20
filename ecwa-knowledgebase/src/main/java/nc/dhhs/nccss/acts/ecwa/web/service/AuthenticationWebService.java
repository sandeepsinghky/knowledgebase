package nc.dhhs.nccss.acts.ecwa.web.service;

import org.springframework.ws.WebServiceException;

import gov.nc.ncidng.ncidngwebservice.schemas.AuthenticateToNCIDv2Response;
import gov.nc.ncidng.ncidngwebservice.schemas.IsMemberOfAGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.ModifyGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;

/**
 * 
 * 
 * @author Vijay Peddapalli
 *
 */
public interface AuthenticationWebService
{

	/**
	 * @param loginName
	 * @param password
	 * @return
	 * @throws WebServiceException
	 */
	public AuthenticateToNCIDv2Response getNCIDAuthenticationReponse(String loginName, String password)
			throws WebServiceException;

	/**
	 * @param loginName
	 * @return
	 * @throws WebServiceException
	 */
	public SearchUserByLoginIDResponse searchUserByLoginID(String loginName) throws WebServiceException;

	/**
	 * @param memberDn, memeberGUID, groupDN
	 * @return
	 * @throws WebServiceException
	 */
	public IsMemberOfAGroupResponse  memberExistInGroup(String memberDn,String memberGUID, String groupDN)  throws WebServiceException;
	
	/**
	 * @param memberDn, memeberGUID, groupDN
	 * @return
	 * @throws WebServiceException
	 */
	public ModifyGroupResponse  modifyGroup(String memberDn,String memberGUID, String groupDN, String action)  throws WebServiceException;
	
}
