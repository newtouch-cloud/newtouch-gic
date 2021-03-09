package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IPolicyLifeProblemModel extends IPageCount{
	public String getPolicy_code();
	public void setPolicy_code(String policy_code);
	public String getFlag() ;
	public void setFlag(String flag);
	public IPolicyImageModel getPolicyImageModel() ;
	public void setPolicyImageModel(IPolicyImageModel policyImageModel);
	
	public Integer getSeq_id();
	public void setSeq_id(Integer seq_id);
	
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	
	public String getBranch_name();
	public void setBranch_name(String branch_name);
	
	public String getInsBranch_id();
	public void setInsBranch_id(String insBranch_id);
	
	public String getInsBranch_name();
	public void setInsBranch_name(String insBranch_name);
	
	public String getType_code();
	public void setType_code(String type_code);
	
	public String getType_name();
	public void setType_name(String type_name);
	
	public String getSend_id();
	public void setSend_id(String send_id);
	
	public Long getPolicy_id();
	public void setPolicy_id(Long policy_id);
	
	public String getSend_code();
	public void setSend_code(String send_code);
	
	public String getApp_name();
	public void setApp_name(String app_name);
	
	public String getOrigin_type();
	public void setOrigin_type(String origin_type);
	
	public String getOrigin_type_code();
	public void setOrigin_type_code(String origin_type_code);
	
	public String getOrigin_type_name();
	public void setOrigin_type_name(String origin_type_name);
	
	public String getNotes();
	public void setNotes(String notes);
	
	public String getReturn_notes();
	public void setReturn_notes(String return_notes);
	
	public String getStatus();
	public void setStatus(String status);
	
	public String getStatus_code();
	public void setStatus_code(String status_code);
	
	public String getStatus_name();
	public void setStatus_name(String status_name);
	
	public String getRemark();
	public void setRemark(String remark);
	
	public String getCreateUser();
	public void setCreateUser(String createUser);
	
	public Date getCreateDate();
	public void setCreateDate(Date createDate);
	
	public String getModifyUser();
	public void setModifyUser(String modifyUser);
	
	public Date getModifyDate();
	public void setModifyDate(Date modifyDate);
	
	public PageCount getPageCount();
	public void setPageCount(PageCount pageCount);
}
