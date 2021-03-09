package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface ICustomerIncomTypeModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getIncomtype_code();

	public void setIncomtype_code(String incomtype_code);

	public String getIncomtype_name();

	public void setIncomtype_name(String incomtype_name);

	public Integer getOrdernum();

	public void setOrdernum(Integer ordernum);

	public String getRemark();

	public void setRemark(String remark);

	public String getCreateuser();

	public void setCreateuser(String createuser);

	public Date getCreatedate();

	public void setCreatedate(Date createdate);

	public String getModifyuser();

	public void setModifyuser(String modifyuser);

	public Date getModifydate();

	public void setModifydate(Date modifydate);

}