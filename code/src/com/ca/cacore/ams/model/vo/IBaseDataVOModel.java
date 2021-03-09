package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IBaseDataVOModel extends IPageCount{
	public String getId();
	public void setId(String id);
	public String getCode();
	public void setCode(String code);
	public String getTypeid();
	public void setTypeid(String typeid);
	public String getTypecode();
	public void setTypecode(String typecode);
	public Integer getLevelnum();
	public void setLevelnum(Integer levelnum);
	public String getIsleaf();
	public void setIsleaf(String isleaf);
	public String getName();
	public void setName(String name);
	public String getStatus();
	public void setStatus(String status);
	public String getStartdate();
	public void setStartdate(String startdate);
	public String getEnddate();
	public void setEnddate(String enddate);
	public String getDescription();
	public void setDescription(String description);
	public String getTreecode();
	public void setTreecode(String treecode);
	public Integer getSeqno();
	public void setSeqno(Integer seqno);
	public String getCreatedby();
	public void setCreatedby(String createdby);
	public String getUpdatedby();
	public void setUpdatedby(String updatedby);
	public Date getCreated();
	public void setCreated(Date created);
	public Date getUpdated();
	public void setUpdated(Date updated);
	public String getParentid();
	public void setParentid(String parentid);
	public PageCount getPageCount();
	public void setPageCount(PageCount pageCount);
	
}
