package com.ca.cacore.msss.model.bo;

import java.sql.Date;

public interface IProductType3Model {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getProduct_type_code();

	public void setProduct_type_code(String product_type_code);

	public String getProduct_type_name();

	public void setProduct_type_name(String product_type_name);

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