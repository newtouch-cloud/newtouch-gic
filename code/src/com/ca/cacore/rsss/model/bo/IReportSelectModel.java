package com.ca.cacore.rsss.model.bo;

import java.sql.Date;

public interface IReportSelectModel {

	public Integer getSeq_id();
	public void setSeq_id(Integer seq_id);
	
	public String getReport_code();
	public void setReport_code(String report_code);
	
	public String getReport_name();
	public void setReport_name(String report_name);
	
	public String getOrderNum();
	public void setOrderNum(String orderNum);
	
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
	
	public String getStatus();
	public void setStatus(String status);

}