package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ICustomerActionTypeModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getActiontype_code();

	public void setActiontype_code(String actiontype_code);

	public String getActiontype_name();

	public void setActiontype_name(String actiontype_name);

	public String getOrdernum();

	public void setOrdernum(String ordernum);

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

}