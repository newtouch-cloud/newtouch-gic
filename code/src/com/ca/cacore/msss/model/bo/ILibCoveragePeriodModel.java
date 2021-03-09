package com.ca.cacore.msss.model.bo;

import java.sql.Date;

public interface ILibCoveragePeriodModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getCoverage_period_code();

	public void setCoverage_period_code(String coverage_period_code);

	public String getCoverage_period_name();

	public void setCoverage_period_name(String coverage_period_name);

	public int getOrderNum();

	public void setOrderNum(int orderNum);

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

}