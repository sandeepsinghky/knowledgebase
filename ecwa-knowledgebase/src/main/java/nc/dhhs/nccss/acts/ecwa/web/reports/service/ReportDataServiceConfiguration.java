/**
 * 
 */
package nc.dhhs.nccss.acts.ecwa.web.reports.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nc.dhhs.nccss.acts.ecwa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecwa.web.service.CaseParticipantService;
import nc.dhhs.nccss.acts.ecwa.web.service.UserSignatureService;
import nc.dhhs.nccss.acts.ecwa.web.service.impl.CaseApplicationServiceImpl;
import nc.dhhs.nccss.acts.ecwa.web.service.impl.CaseParticipantServiceImpl;
import nc.dhhs.nccss.acts.ecwa.web.service.impl.UserSignatureServiceImpl;

/**
 * @author Vijay Peddapalli
 *
 */
@Configuration
public class ReportDataServiceConfiguration
{

	/**
	 * @return
	 */
	@Bean
	public CaseApplicationService caseApplService()
	{
		return new CaseApplicationServiceImpl();
	}

	/**
	 * @return
	 */
	@Bean
	public UserSignatureService userSignatureService()
	{
		return new UserSignatureServiceImpl();
	}

	/**
	 * @return
	 */
	@Bean
	public CaseParticipantService casePartService()
	{
		return new CaseParticipantServiceImpl();
	}

}
