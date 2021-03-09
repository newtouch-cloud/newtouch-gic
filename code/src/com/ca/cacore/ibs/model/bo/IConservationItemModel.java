package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IConservationItemModel {

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public Long getConservationItem_Code();

	public void setConservationItem_Code(Long conservationItem_Code);

	public String getConservationItem_Name();

	public void setConservationItem_Name(String conservationItem_Name);

	public Integer getOrderNum();

	public void setOrderNum(Integer orderNum);

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

	public int getStart();

	public int getLimit();

}