package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IClaimsModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public Long getPolicy_id();
	
	public void setPolicy_id(Long policy_id) ;
	
	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getInsBranch_id();

	public void setInsBranch_id(String insBranch_id);

	public String getPolicy_code();

	public void setPolicy_code(String policy_code);

	public Date getEvent_date();

	public void setEvent_date(Date event_date);

	public String getHolder_id();

	public void setHolder_id(String holder_id);

	public String getInsurant_id();

	public void setInsurant_id(String insurant_id);

	public String getEvent_id();

	public void setEvent_id(String event_id);

	public String getEvent_process();

	public void setEvent_process(String event_process);

	public String getClaims_status();

	public void setClaims_status(String claims_status);

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

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

	public int getStart();

	public int getLimit();

}