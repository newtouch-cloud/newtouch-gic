package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IRecruitBatchApprVOModel extends IPageCount{
	public Integer getSeq_id();
	
	public void setSeq_id(Integer seq_id);
	
	public String getChannel_id();
	
	public void setChannel_id(String channel_id);
	
	public String getBranch_id();
	
	public void setBranch_id(String branch_id);
	
	public String getApprBranchId();
	
	public void setApprBranchId(String apprBranchId);
	
	public String getBusiness_type();
	
	public void setBusiness_type(String business_type);
	
	public String getBusiness_id();
	
	public void setBusiness_id(String business_id);
	
	public String getApproval_item();
	
	public void setApproval_item(String approval_item);
	
	public Integer getApproval_id();
	
	public void setApproval_id(Integer approval_id);
	
	public String getApproval_views();
	
	public void setApproval_views(String approval_views);
	
	public String getRemark() ;
	
	public void setRemark(String remark);
	
	public String getApplicant();
	
	public void setApplicant(String applicant);
	
	public Date getApplication_time();
	
	public void setApplication_time(Date application_time);
	
	public String getApproval_status();
	
	public void setApproval_status(String approval_status);
	
	public String getApproval();
	
	public void setApproval(String approval);
	
	public Date getApproval_time();
	
	public void setApproval_time(Date approval_time);
	
	public String getCreateUser();
	
	public void setCreateUser(String createUser);
	
	public Date getCreateDate();
	
	public void setCreateDate(Date createDate);
	
	public String getModifyUser();
	
	public void setModifyUser(String modifyUser);
	
	public Date getModifyDate();
	
	public void setModifyDate(Date modifyDate);
	
	public String getStatus_code();
	
	public void setStatus_code(String status_code);
	
	public String getStatus_name();
	
	public void setStatus_name(String status_name);
	
	public String getBatch_id();
	
	public void setBatch_id(String batch_id);
	
	public String getBatch_name();
	
	public void setBatch_name(String batch_name);
	
	public Integer getPlan_recruitNum();
	
	public void setPlan_recruitNum(Integer plan_recruitNum);
	
	public String getBatch_status();
	
	public void setBatch_status(String batch_status);
	
	public Date getStart_time();
	
	public void setStart_time(Date start_time);
	
	public Date getEnd_time();
	
	public void setEnd_time(Date end_time) ;
	
	public String getProcess_completed();
	
	public void setProcess_completed(String process_completed);
	
	public String getBranch_name();
	
	public void setBranch_name(String branch_name);
	
	public String getFlag();
	
	public void setFlag(String flag);
	
	public PageCount getPageCount();
	
	public void setPageCount(PageCount pageCount);
}
