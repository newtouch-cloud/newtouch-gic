package com.ca.cacore.msss.model.bo;

import java.sql.Date;

public interface IProductPeriodTypeModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getPeriodType_code();

	public void setPeriodType_code(String periodType_code);

	public String getPeriodType_name();

	public void setPeriodType_name(String periodType_name);

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