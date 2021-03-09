package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ICustomerTypeModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getType_code();

	public void setType_code(String type_code);

	public String getType_name();

	public void setType_name(String type_name);

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

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

}