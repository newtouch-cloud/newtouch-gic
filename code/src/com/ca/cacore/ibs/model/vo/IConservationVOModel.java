package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IConservationVOModel {

	public Integer getSeq_id() ;
	public void setSeq_id(Integer seq_id);
	public Long getPolicy_id() ;
	public void setPolicy_id(Long policy_id) ;
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name() ;
	public void setBranch_name(String branch_name) ;
	public String getInsBranch_id() ;
	public void setInsBranch_id(String insBranch_id) ;
	public String getPolicy_code() ;
	public void setPolicy_code(String policy_code);
	public String getHolder_id() ;
	public void setHolder_id(String holder_id) ;
	public String getHolder_name() ;
	public void setHolder_name(String holder_name) ;
	public String getInsurant_id() ;
	public void setInsurant_id(String insurant_id) ;
	public String getInsurant_name();
	public void setInsurant_name(String insurant_name) ;
	public String getConservationItem_code();
	public void setConservationItem_code(String conservationItem_code);
	public String getConservationItem_name();
	public void setConservationItem_name(String conservationItem_name);
	public Date getApplication_time() ;
	public void setApplication_time(Date application_time) ;
	public String getApplicant_id() ;
	public void setApplicant_id(String applicant_id) ;
	public String getApplicant_name() ;
	public void setApplicant_name(String applicant_name) ;
	public String getReplace_applicant() ;
	public void setReplace_applicant(String replace_applicant) ;
	public String getRemark() ;
	public void setRemark(String remark) ;
	public String getCreateUser() ;
	public void setCreateUser(String createUser) ;
	public Date getCreateDate() ;
	public void setCreateDate(Date createDate) ;
	public String getModifyUser() ;
	public void setModifyUser(String modifyUser) ;
	public Date getModifyDate() ;
	public void setModifyDate(Date modifyDate);
	
	public String getInsBranch_name() ;
	public void setInsBranch_name(String insBranch_name) ;
	public PageCount getPageCount() ;
	public void setPageCount(PageCount pageCount) ;
	public int getStart() ;
	public int getLimit() ;
	public Date getValidate_date();
	public void setValidate_date(Date validate_date);
	

}