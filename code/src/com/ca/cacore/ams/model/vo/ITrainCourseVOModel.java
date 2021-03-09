package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ITrainCourseVOModel extends IPageCount{


	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id) ;
	
	public String getApprBranchId();

	public void setApprBranchId(String apprBranchId);

	public String getCourse_id();

	public void setCourse_id(String course_id);

	public String getCourse_name();

	public void setCourse_name(String course_name);

	public String getCourse_type_code();

	public void setCourse_type_code(String course_type_code);

	public String getCourse_level_code();

	public void setCourse_level_code(String course_level_code);

	public String getCourse_period();

	public void setCourse_period(String course_period);

	public String getTraining_item_code();

	public void setTraining_item_code(String training_item_code);

	public String getTeacher_id() ;

	public void setTeacher_id(String teacher_id);

	public String getCourse_introduction();

	public void setCourse_introduction(String course_introduction);

	public String getIs_preseted();

	public void setIs_preseted(String is_preseted);

	public Date getUpload_time();

	public void setUpload_time(Date upload_time) ;

	public Integer getApproval_id() ;

	public void setApproval_id(Integer approval_id);

	public String getProcess_completed();

	public void setProcess_completed(String process_completed);

	public String getCourse_status_code();

	public void setCourse_status_code(String course_status_code) ;

	public String getRemark() ;

	public void setRemark(String remark);

	public String getCreateUser() ;

	public void setCreateUser(String createUser) ;

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getModifyUser();

	public void setModifyUser(String modifyUser) ;

	public Date getModifyDate() ;

	public void setModifyDate(Date modifyDate);

	public String getApplicant() ;

	public void setApplicant(String applicant);

	public Date getApplication_time();

	public void setApplication_time(Date application_time);

	public String getApproval();

	public void setApproval(String approval);

	public Date getApproval_time();

	public void setApproval_time(Date approval_time);

	public String getApproval_status() ;

	public void setApproval_status(String approval_status);

	public String getChannel_id() ;

	public void setChannel_id(String channel_id) ;

	public String getBusiness_type() ;

	public void setBusiness_type(String business_type);

	public String getBusiness_id();

	public void setBusiness_id(String business_id);

	public String getApproval_item() ;

	public void setApproval_item(String approval_item) ;

	public String getApproval_views();

	public void setApproval_views(String approval_views);

	public String getCourse_type_name() ;

	public void setCourse_type_name(String course_type_name);

	public String getCourse_level_name() ;

	public void setCourse_level_name(String course_level_name);

	public String getTraining_item_name();

	public void setTraining_item_name(String training_item_name);

	public String getTeacher_name();

	public void setTeacher_name(String teacher_name);

	public String getCourse_status_name();

	public void setCourse_status_name(String course_status_name);

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
