package com.ca.cacore.msss.model.bo;

import java.sql.Date;

public interface ILibChargeTypeModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getCharge_type_code();

	public void setCharge_type_code(String charge_type_code);

	public String getCharge_type_name();

	public void setCharge_type_name(String charge_type_name);

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