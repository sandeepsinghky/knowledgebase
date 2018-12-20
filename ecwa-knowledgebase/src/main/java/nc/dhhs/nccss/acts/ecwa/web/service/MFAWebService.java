package nc.dhhs.nccss.acts.ecwa.web.service;

import org.springframework.ws.WebServiceException;

import gov.nc.ncidng.ncidngwebservice.schemas.IsMemberOfAGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.ModifyGroupResponse;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;

public interface MFAWebService
{
	/**
	 * @param loginName
	 * @return
	 * @throws WebServiceException
	 */
	public SearchUserByLoginIDResponse searchUserByNCID(String ncid) throws WebServiceException;

	/**
	 * @param memberDn, memeberGUID, groupDN
	 * @return
	 * @throws WebServiceException
	 */
	public IsMemberOfAGroupResponse  memberExistInGroup(String memberDn, String groupDN)  throws WebServiceException;
	
	/**
	 * @param memberDn, memeberGUID, groupDN
	 * @return
	 * @throws WebServiceException
	 */
	public ModifyGroupResponse  modifyGroup(String memberDn , String groupDN, String action)  throws WebServiceException;
	
}
