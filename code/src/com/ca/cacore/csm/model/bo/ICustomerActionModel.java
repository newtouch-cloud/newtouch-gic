package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ICustomerActionModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getCustomer_id();

	public void setCustomer_id(String customer_id);

	public String getAction_type();

	public void setAction_type(String action_type);

	public Date getAction_time();

	public void setAction_time(Date action_time);

	public String getAction_notes();

	public void setAction_notes(String action_notes);

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

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);
	
	public String getMember_id();

	public void setMember_id(String member_id);

}