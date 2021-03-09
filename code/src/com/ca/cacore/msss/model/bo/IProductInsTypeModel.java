package com.ca.cacore.msss.model.bo;

import java.sql.Date;

public interface IProductInsTypeModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getIns_type_code();

	public void setIns_type_code(String ins_type_code);

	public String getIns_type_name();

	public void setIns_type_name(String ins_type_name);

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