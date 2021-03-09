package com.ca.cacore.msss.model.bo;

import java.sql.Date;

public interface IProductInsuredFlagModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getInsuredFlag_code();

	public void setInsuredFlag_code(String insuredFlag_code);

	public String getInsuredFlag_name();

	public void setInsuredFlag_name(String insuredFlag_name);

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