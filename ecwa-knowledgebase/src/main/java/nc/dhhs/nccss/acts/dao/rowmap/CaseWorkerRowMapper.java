package nc.dhhs.nccss.acts.dao.rowmap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import nc.dhhs.nccss.acts.ecwa.caseworker.beans.CaseWorker;

public class CaseWorkerRowMapper  implements RowMapper<CaseWorker>
{
	protected final Logger logger = Logger.getLogger(CaseWorkerRowMapper.class);
	
	public CaseWorkerRowMapper()
	{

	}
	
	@Override
	public CaseWorker mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		logger.info("IN: CaseWorkerRowMapper- mapRow");
		CaseWorker caseWorker = new CaseWorker();
		 
		
		if (rs.getString("ID_WRKR") != null && !rs.getString("ID_WRKR").equals(""))
		{
			caseWorker.setWorker_num(rs.getString("ID_WRKR").trim());
		}
		
		if (rs.getString("CD_WRKR_TYPE") != null && !rs.getString("CD_WRKR_TYPE").equals(""))
		{
			caseWorker.setWorker_type(rs.getString("CD_WRKR_TYPE").trim());
		}
		
		if (rs.getString("NM_WRKR_L") != null && !rs.getString("NM_WRKR_L").equals(""))
		{
			caseWorker.setLast_name (rs.getString("ID_WRKR").trim());
		}
		
		if (rs.getString("NM_WRKR_F") != null && !rs.getString("NM_WRKR_F").equals(""))
		{
			caseWorker.setFirst_name (rs.getString("NM_WRKR_F").trim());
		}

		if (rs.getString("NM_WRKR_MI") != null && !rs.getString("NM_WRKR_MI").equals(""))
		{
			caseWorker.setMiddle_name (rs.getString("NM_WRKR_MI").trim());
		}

		if (rs.getString("DE_WRKR_TITLE") != null && !rs.getString("DE_WRKR_TITLE").equals(""))
		{
			caseWorker.setTitle (rs.getString("DE_WRKR_TITLE").trim());
		}
		
		//CD_FIPS_WRKR- 12-345-678910 (12=State Code, 345-County Code, 678910=IVD Code)
		if (rs.getString("CD_FIPS_WRKR") != null && !rs.getString("CD_FIPS_WRKR").equals(""))
		{
			if ((rs.getString("CD_FIPS_WRKR").length()==10)) {
				logger.info("CD_FIPS_WRKR " + rs.getString("CD_FIPS_WRKR") );
				caseWorker.setState(rs.getString("CD_FIPS_WRKR").substring(0,2));
				caseWorker.setCounty(rs.getString("CD_FIPS_WRKR").substring(2,5));
				caseWorker.setIvd_area(rs.getString("CD_FIPS_WRKR").substring(5));
				
			 
			}
			//caseWorker.setWorker_num(rs.getString("ID_WRKR").trim());
		}
		
		if (rs.getString("ID_TRML") != null && !rs.getString("ID_TRML").equals(""))
		{
			caseWorker.setTerm_id (rs.getString("ID_TRML").trim());
		}
		
		if (rs.getString("ID_PRTR") != null && !rs.getString("ID_PRTR").equals(""))
		{
			caseWorker.setPrinter_id (rs.getString("ID_PRTR").trim());
		}
		// Case Load Total
		//NIVD Total
		
		//Worker Supervisor
		if (rs.getString("ID_WRKR_SUP") != null && !rs.getString("ID_WRKR_SUP").equals(""))
		{
			caseWorker.setSupervisor (rs.getString("ID_WRKR_SUP").trim());
		}
		
		/*//county Street Address 1 
		if (rs.getString("AD_3PTY_LN_1") != null && !rs.getString("AD_3PTY_LN_1").equals(""))
		{
			caseWorker.setStreetAddress1(rs.getString("AD_3PTY_LN_1").trim());
		}
		
		//county Street Address 2
		if (rs.getString("AD_3PTY_LN_2") != null && !rs.getString("AD_3PTY_LN_2").equals(""))
		{
			caseWorker.setStreetAddress2(rs.getString("AD_3PTY_LN_2").trim());
		}
		
		//County City
		if (rs.getString("AD_3PTY_CTY") != null && !rs.getString("AD_3PTY_CTY").equals(""))
		{
			caseWorker.setCity(rs.getString("AD_3PTY_CTY").trim());
		}
		
		//County State
		if (rs.getString("AD_3PTY_ST") != null && !rs.getString("AD_3PTY_ST").equals(""))
		{
			//caseWorker.setCity(rs.getString("AD_3PTY_ST").trim());
		}
		
		//county Zip-5
		if (rs.getString("AD_3PTY_ZIP_5") != null && !rs.getString("AD_3PTY_ZIP_5").equals(""))
		{
			caseWorker.setZipcode (rs.getString("AD_3PTY_ZIP_5").trim());
		}
		
		//county Zip-4
		if (rs.getString("AD_3PTY_ZIP_4") != null && !rs.getString("AD_3PTY_ZIP_4").equals(""))
		{
			//caseWorker.setCity(rs.getString("AD_3PTY_ST").trim());
		}*/
				
		//Worker Phone and Ext 
		if (rs.getString("NB_TEL_WRKR_ACD") != null && !rs.getString("NB_TEL_WRKR_ACD").equals(""))
		{
			caseWorker.setPhoneACD(rs.getString("NB_TEL_WRKR_ACD"));
		}
				
		if (rs.getString("NB_TEL_WRKR_EXC") != null && !rs.getString("NB_TEL_WRKR_EXC").equals(""))
		{
			caseWorker.setPhoneEXC(rs.getString("NB_TEL_WRKR_EXC").trim());
		}
				
		if (rs.getString("NB_TEL_WRKR_LN") != null && !rs.getString("NB_TEL_WRKR_LN").equals(""))
		{
			caseWorker.setPhoneLN(rs.getString("NB_TEL_WRKR_LN").trim());
		}
				
		if (rs.getString("NB_TEL_WRKR_EXT") != null && !rs.getString("NB_TEL_WRKR_EXT").equals(""))
		{
			caseWorker.setPhone_ext(rs.getString("NB_TEL_WRKR_EXT").trim());
		}
		
		
		
		//Worker FAX
		if (rs.getString("NB_FAX_WRKR_ACD") != null && !rs.getString("NB_FAX_WRKR_ACD").equals(""))
		{
			caseWorker.setFaxACD(rs.getString("NB_FAX_WRKR_ACD").trim());
		}
		
		if (rs.getString("NB_FAX_WRKR_EXC") != null && !rs.getString("NB_FAX_WRKR_EXC").equals(""))
		{
			caseWorker.setFaxEXC(rs.getString("NB_FAX_WRKR_EXC").trim());
		}
		
		if (rs.getString("NB_FAX_WRKR_LN") != null && !rs.getString("NB_FAX_WRKR_LN").equals(""))
		{
			caseWorker.setFaxLN (rs.getString("NB_FAX_WRKR_LN").trim());
		}
		
		//MFA Enabled 
		if (rs.getString("IN_MFA_ENABLED") != null && !rs.getString("IN_MFA_ENABLED").equals(""))
		{
			if (rs.getString("IN_MFA_ENABLED").equals("Y")){
				caseWorker.setMfacriteria("Yes");
			}else if (rs.getString("IN_MFA_ENABLED").equals("N")){
				caseWorker.setMfacriteria("No");
			}
			 
		}
		
		//NCID
		if (rs.getString("ID_NCID") != null && !rs.getString("ID_NCID").equals(""))
		{
			caseWorker.setNcid(rs.getString("ID_NCID").trim());
		}
		
		//RACFID==ID_WRKR_LOGON
		
		if (rs.getString("ID_WRKR_LOGON") != null && !rs.getString("ID_WRKR_LOGON").equals(""))
		{
			caseWorker.setRacfid (rs.getString("ID_WRKR_LOGON").trim());
		}
		
		//actssp role??????
		/*if (rs.getString("CD_FEDSSP_ACTSTYPE") != null && !rs.getString("CD_FEDSSP_ACTSTYPE").equals(""))
		{
			caseWorker.setActsSSPRole(rs.getString("CD_FEDSSP_ACTSTYPE").trim());
		}*/
		
		//ocseSSPRole????
		
		//Set Hire date
		if (rs.getDate("DT_HIRE") != null && !rs.getDate("DT_HIRE").equals(""))
		{
			  caseWorker.setDateHired  (rs.getDate("DT_HIRE"));
		}
		
		//Keep a tab on Start Time , End Time, Start Lunch Time, End Lunch Time
		
		if (rs.getTime("TM_WRK_STRT") != null && !rs.getTime("TM_WRK_STRT").equals(""))
		{
			 
			  caseWorker.setStartTime(rs.getTime("TM_WRK_STRT"));
		}
		
		if (rs.getTime("TM_WRK_END") != null && !rs.getTime("TM_WRK_END").equals(""))
		{
			 caseWorker.setEndTime  (rs.getTime("TM_WRK_END"));
		}

		if (rs.getTime("TM_LUNCH_STRT") != null && !rs.getTime("TM_LUNCH_STRT").equals(""))
		{
			 caseWorker.setStartLunchTime (rs.getTime("TM_LUNCH_STRT"));
		}

		if (rs.getTime("TM_LUNCH_END") != null && !rs.getTime("TM_LUNCH_END").equals(""))
		{
			  caseWorker.setEndLunchTime (rs.getTime("TM_LUNCH_END"));
		}
		//Date Last Updated 
		if (rs.getDate("DT_LST_UPD") != null && !rs.getDate("DT_LST_UPD").equals(""))
		{
			  caseWorker.setLastUpdateDate (rs.getDate("DT_LST_UPD"));
		}
		
		//Time Last Updated 
		if (rs.getTime("TM_LST_UPD") != null && !rs.getTime("TM_LST_UPD").equals(""))
		{
			  caseWorker.setLastUpdateTime (rs.getTime("TM_LST_UPD"));
		}
		
		//Updated by Worker
		if (rs.getString("ID_WRKR_LST_UPD") != null && !rs.getString("ID_WRKR_LST_UPD").equals(""))
		{
			caseWorker.setLastUpdateBy(rs.getString("ID_WRKR_LST_UPD").trim());
		}
		
		//Updated by PC-Terminal
		if (rs.getString("ID_TRML_LST_UPD") != null && !rs.getString("ID_TRML_LST_UPD").equals(""))
		{
			//caseWorker.setLastUpdateBy(rs.getString("ID_TRML_LST_UPD").trim());
		}
		
		//Case Load Total
		if (rs.getInt("NB_RFRL") != 0 )
		{
				caseWorker.setCaseloadtotal(rs.getInt("NB_RFRL")); 
		}
		
		//Non 1V D
		if (rs.getInt("NB_RFRL") != 0 )
		{
			caseWorker.setCaseloadtotal(rs.getInt("NB_RFRL_NIVD")); 
		}
				
		
		if (rs.getString("IN_MAIL_WRKLST") != null && !rs.getString("IN_MAIL_WRKLST").equals(""))
		{
		 //caseWorker.setTerm_id (rs.getString("IN_MAIL_WRKLST").trim());
		}
					
		return caseWorker;
	}

}
