package com.ca.cacore.maas.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-Model
*/
public interface IProtocolModel extends IPageCount{
	
	public String getIns_branchname();

	public void setIns_branchname(String ins_branchname);

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getIns_branch();

	public abstract void setIns_branch(String ins_branch);


	public abstract String getContacts_name();

	public abstract void setContacts_name(String contacts_name);

	public abstract String getContacts_way();

	public abstract void setContacts_way(String contacts_way);

	public abstract String getAgreement_no();

	public abstract void setAgreement_no(String agreement_no);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getBranch_name();

	public abstract void setBranch_name(String branch_name);

	public abstract String getStartdate();

	public abstract void setStartdate(String startdate);

	public abstract Date getStartdate1();

	public abstract void setStartdate1(Date startdate1);

	public abstract String getEnddate();

	public abstract void setEnddate(String enddate);

	public abstract Date getEnddate1();

	public abstract void setEnddate1(Date enddate1);

	public abstract String getDateofsign();

	public abstract void setDateofsign(String dateofsign);

	public abstract String getPersion_id();

	public abstract void setPersion_id(String persion_id);

	public abstract String getPersion_name();

	public abstract void setPersion_name(String persion_name);

	public abstract String getSales_id();

	public abstract void setSales_id(String sales_id);

	public abstract String getSales_name();

	public abstract void setSales_name(String sales_name);

	public abstract String getStatus();

	public abstract void setStatus(String status);

	public abstract String getStatus_code();

	public abstract void setStatus_code(String status_code);

	public abstract String getStatus_name();

	public abstract void setStatus_name(String status_name);

	public abstract Date getSystemdate();

	public abstract void setSystemdate(Date systemdate);

	public abstract String getCode();

	public abstract void setCode(String code);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract String getInsBranch_name();

	public abstract void setInsBranch_name(String insBranch_name);

	public abstract PageCount getPageCount();

	public abstract void setPageCount(PageCount pageCount);
	
	public  int getStart();

	public  int getLimit();
	public String getEmp_id();
	public void setEmp_id(String emp_id);
	
	public String getFile_name();
	public void setFile_name(String file_name);

	public String getBus_id();
	public void setBus_id(String bus_id);
	
	public String getFile_id();
	public void setFile_id(String file_id);
	
	public String getClassName();

	public void setClassName(String className);

	public String getClassCode();

	public void setClassCode(String classCode);

	public String getRiskName();

	public void setRiskName(String riskName);

	public String getRiskCode();

	public void setRiskCode(String riskCode);
	
	public Float getPoundage() ;

	public void setPoundage(Float poundage) ;
}