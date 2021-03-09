package com.ca.cacore.csm.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ICustomerActionVOModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getCustomer_id();

	public void setCustomer_id(String customer_id);

	public String getName();

	public void setName(String name);

	public String getAction_type();

	public void setAction_type(String action_type);

	public Date getAction_time();

	public void setAction_time(Date action_time);

	public String getAction_notes();

	public void setAction_notes(String action_notes);

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);
	
	public String getCustomer_type();

	public void setCustomer_type(String customer_type);
	
	public String getGender();
	
	public void setGender(String gender);
	
	public String getBranch_name();
	
	public void setBranch_name(String branch_name);

}