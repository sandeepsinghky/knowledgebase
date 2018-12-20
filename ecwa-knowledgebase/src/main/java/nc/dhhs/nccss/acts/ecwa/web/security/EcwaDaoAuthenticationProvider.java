package nc.dhhs.nccss.acts.ecwa.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import nc.dhhs.nccss.acts.ecwa.web.util.AppConstants;

/**
 * @author Vijay Peddapalli
 *
 */

public class EcwaDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{

	private EcwaUserDetailsServiceImpl userDetailsService;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks(
	 * org.springframework.security.core.userdetails.UserDetails,
	 * org.springframework.security.authentication.
	 * UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider#retrieveUser(java.lang.String,
	 * org.springframework.security.authentication.
	 * UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException
	{
		UserDetails loadedUser;
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		
		HttpSession session = request.getSession();

		
		try
		{
			String password = authentication.getCredentials().toString();

			// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			String ipaddress = request.getRemoteAddr();
			String browserUserAgent = request.getHeader("User-Agent");

			loadedUser = getUserDetailsService().loadAndAuthenticate(username, password, ipaddress, browserUserAgent);

		}
		catch (DataAccessException repositoryProblem)
		{
			throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}
		
		if(loadedUser == null){
			session.setAttribute(AppConstants.LOGIN_ERROR_MSG, "Invalid credentials");
			logger.info("ERROR; Invalid credentials");
		}else if(((EcwaUserDetails)loadedUser).isError()){
			session.setAttribute(AppConstants.LOGIN_ERROR_MSG, ((EcwaUserDetails)loadedUser).getErrorMessage());
		}
		
		if (loadedUser == null || ((EcwaUserDetails)loadedUser).isError()) { throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation"); }

		return loadedUser;
	}

	/**
	 * @return
	 */
	public EcwaUserDetailsServiceImpl getUserDetailsService()
	{
		return userDetailsService;
	}

	/**
	 * @param userDetailsService
	 */
	public void setUserDetailsService(EcwaUserDetailsServiceImpl userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

}
