package nc.dhhs.nccss.acts.ecwa.caseworker.beans;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class CaseWorker implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String worker_num = "";
	private String worker_type = "";
	private String last_name = "";
	private String first_name = "";
	private String middle_name = "";
	private String title = "";
	private String ivd_area = "";
	private String county= "";
	private String cd_Fips_Wrkr="";
	private String term_id = "";
	private String printer_id = "";
	private int caseloadtotal = 0;
	private int nivdTotal = 0;
	private String supervisor = "";
	private String streetAddress1 = "";
	private String streetAddress2 = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";
	private String phoneACD="";
	private String phoneEXC="";
	private String phoneLN="";
	private String phone_ext = "";
	private String faxACD = "";
	private String faxEXC = "";
	private String faxLN = "";
	private String mfacriteria="";
	private String ncid = "";
	private String racfid = "";
	private String actsSSPRole = "";
	private String ocseSSPRole = "";
	private Date dateHired ;
	private Time startTime;
	private Time  endTime ;
	private Time startLunchTime;
	private Time endLunchTime;
		
	private Date lastUpdateDate;
	private Time lastUpdateTime ;
	private String lastUpdateBy = "";
	
	public String getWorker_num()
	{
		return worker_num;
	}

	public void setWorker_num(String worker_num)
	{
		this.worker_num = worker_num;
	}

	public String getWorker_type()
	{
		return worker_type;
	}

	public void setWorker_type(String worker_type)
	{
		this.worker_type = worker_type;
	}

	public String getLast_name()
	{
		return last_name;
	}

	public void setLast_name(String last_name)
	{
		this.last_name = last_name;
	}

	public String getFirst_name()
	{
		return first_name;
	}

	public void setFirst_name(String first_name)
	{
		this.first_name = first_name;
	}

	public String getMiddle_name()
	{
		return middle_name;
	}

	public void setMiddle_name(String middle_name)
	{
		this.middle_name = middle_name;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getIvd_area()
	{
		return ivd_area;
	}

	public void setIvd_area(String ivd_area)
	{
		this.ivd_area = ivd_area;
	}

	public String getCounty()
	{
		return county;
	}

	public void setCounty(String county)
	{
		this.county = county;
	}

	public String getTerm_id()
	{
		return term_id;
	}

	public void setTerm_id(String term_id)
	{
		this.term_id = term_id;
	}

	public String getPrinter_id()
	{
		return printer_id;
	}

	public void setPrinter_id(String printer_id)
	{
		this.printer_id = printer_id;
	}

	

	public String getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(String supervisor)
	{
		this.supervisor = supervisor;
	}

	public String getStreetAddress1()
	{
		return streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1)
	{
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2()
	{
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2)
	{
		this.streetAddress2 = streetAddress2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	
	public String getPhone_ext()
	{
		return phone_ext;
	}

	public void setPhone_ext(String phone_ext)
	{
		this.phone_ext = phone_ext;
	}

	

	public String getFaxACD()
	{
		return faxACD;
	}

	public void setFaxACD(String faxACD)
	{
		this.faxACD = faxACD;
	}

	public String getFaxEXC()
	{
		return faxEXC;
	}

	public void setFaxEXC(String faxEXC)
	{
		this.faxEXC = faxEXC;
	}

	public String getFaxLN()
	{
		return faxLN;
	}

	public void setFaxLN(String faxLN)
	{
		this.faxLN = faxLN;
	}

	public String getMfacriteria()
	{
		return mfacriteria;
	}

	public void setMfacriteria(String mfacriteria)
	{
		this.mfacriteria = mfacriteria;
	}

	public String getNcid()
	{
		return ncid;
	}

	public void setNcid(String ncid)
	{
		this.ncid = ncid;
	}

	public String getRacfid()
	{
		return racfid;
	}

	public void setRacfid(String racfid)
	{
		this.racfid = racfid;
	}

	public String getActsSSPRole()
	{
		return actsSSPRole;
	}

	public void setActsSSPRole(String actsSSPRole)
	{
		this.actsSSPRole = actsSSPRole;
	}

	public String getOcseSSPRole()
	{
		return ocseSSPRole;
	}

	public void setOcseSSPRole(String ocseSSPRole)
	{
		this.ocseSSPRole = ocseSSPRole;
	}

	public Date getDateHired()
	{
		return dateHired;
	}

	public void setDateHired(Date dateHired)
	{
		this.dateHired = dateHired;
	}

	

	public Date getLastUpdateDate()
	{
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate)
	{
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateBy()
	{
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy)
	{
		this.lastUpdateBy = lastUpdateBy;
	}

	public Time getLastUpdateTime()
	{
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Time lastUpdateTime)
	{
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getPhoneACD()
	{
		return phoneACD;
	}

	public void setPhoneACD(String phoneACD)
	{
		this.phoneACD = phoneACD;
	}

	public String getPhoneEXC()
	{
		return phoneEXC;
	}

	public void setPhoneEXC(String phoneEXC)
	{
		this.phoneEXC = phoneEXC;
	}

	public String getPhoneLN()
	{
		return phoneLN;
	}

	public void setPhoneLN(String phoneLN)
	{
		this.phoneLN = phoneLN;
	}

	public Time getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Time startTime)
	{
		this.startTime = startTime;
	}

	public Time getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Time endTime)
	{
		this.endTime = endTime;
	}

	public Time getStartLunchTime()
	{
		return startLunchTime;
	}

	public void setStartLunchTime(Time startLunchTime)
	{
		this.startLunchTime = startLunchTime;
	}

	public Time getEndLunchTime()
	{
		return endLunchTime;
	}

	public void setEndLunchTime(Time endLunchTime)
	{
		this.endLunchTime = endLunchTime;
	}

	public String getCd_Fips_Wrkr()
	{
		return cd_Fips_Wrkr;
	}

	public void setCd_Fips_Wrkr(String cd_Fips_Wrkr)
	{
		this.cd_Fips_Wrkr = cd_Fips_Wrkr;
	}

	public int getCaseloadtotal()
	{
		return caseloadtotal;
	}

	public void setCaseloadtotal(int caseloadtotal)
	{
		this.caseloadtotal = caseloadtotal;
	}

	public int getNivdTotal()
	{
		return nivdTotal;
	}

	public void setNivdTotal(int nivdTotal)
	{
		this.nivdTotal = nivdTotal;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
	
}
