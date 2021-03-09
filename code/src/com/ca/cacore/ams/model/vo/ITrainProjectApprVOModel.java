package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ITrainProjectApprVOModel extends IPageCount{
	public Integer getSeq_id();
	
	public void setSeq_id(Integer seq_id);
	
	public String getBranch_id();
	
	public void setBranch_id(String branch_id);
	
	public String getApprBranchId();
	
	public void setApprBranchId(String apprBranchId);
	
	public String getProject_id();
	
	public void setProject_id(String project_id);
	
	public String getProject_name();
	
	public void setProject_name(String project_name);
	
	public String getTraining_format_code() ;
	
	public void setTraining_format_code(String training_format_code);
	
	public String getTraining_type_code();
	
	public void setTraining_type_code(String training_type_code);
	
	public String getTraining_class_name();
	
	public void setTraining_class_name(String training_class_name);
	
	public String getTraining_item();
	
	public void setTraining_item(String training_item);
	
	public String getTraining_item_name();
	
	public void setTraining_item_name(String training_item_name);
	
	public String getPlan_periods();
	
	public void setPlan_periods(String plan_periods);
	
	public String getTraining_mode_code();
	
	public void setTraining_mode_code(String training_mode_code);
	
	public Integer getTraining_day();
	
	public void setTraining_day(Integer training_day);
	
	public Integer getPlan_person_num();
	
	public void setPlan_person_num(Integer plan_person_num);
	
	public String getTraining_object();
	
	public void setTraining_object(String training_object);
	
	public Double getTeach_fee() ;
	public void setTeach_fee(Double teach_fee);
	public Double getPerson_average_fee() ;
	public void setPerson_average_fee(Double person_average_fee) ;
	public Double getTraining_budget() ;
	public void setTraining_budget(Double training_budget) ;
	public Double getChief_branch_fee();
	public void setChief_branch_fee(Double chief_branch_fee) ;
	public Double getLower_branch_fee();
	public void setLower_branch_fee(Double lower_branch_fee);
	
	public String getTraining_address();
	
	public void setTraining_address(String training_address);
	
	public Date getUpload_time();
	
	public void setUpload_time(Date upload_time);
	
	public Integer getApproval_id();
	
	public void setApproval_id(Integer approval_id);
	
	public String getProcess_completed();
	
	public void setProcess_completed(String process_completed);
	
	public String getStatus_code();
	
	public void setStatus_code(String status_code);
	
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
	
	public String getApplicant();
	
	public void setApplicant(String applicant);
	
	public Date getApplication_time();
	
	public void setApplication_time(Date application_time);
	
	public String getApproval();
	
	public void setApproval(String approval) ;
	
	public Date getApproval_time() ;
	
	public void setApproval_time(Date approval_time) ;
	
	public String getApproval_status();
	
	public void setApproval_status(String approval_status);
	
	public String getChannel_id();
	
	public void setChannel_id(String channel_id);
	
	public String getBusiness_type();
	
	public void setBusiness_type(String business_type);
	
	public String getBusiness_id();
	
	public void setBusiness_id(String business_id);
	
	public String getApproval_item();
	
	public void setApproval_item(String approval_item);
	
	public String getApproval_views();
	
	public void setApproval_views(String approval_views);
	
	public String getBranch_name();
	
	public void setBranch_name(String branch_name);
	
	public String getFlag();
	
	public void setFlag(String flag);
	
	public PageCount getPageCount();
	
	public void setPageCount(PageCount pageCount);
	
	public String getFile_name();
	
	public void setFile_name(String file_name);
	
	public String getFile_id();
	
	public void setFile_id(String file_id);
}
