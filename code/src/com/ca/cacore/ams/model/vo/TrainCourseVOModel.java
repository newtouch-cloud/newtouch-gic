package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class TrainCourseVOModel implements ITrainCourseVOModel{
	private Integer seq_id;//主键
	private String branch_id;//机构代码
	private String apprBranchId;//审批事项所属机构代码
	private String course_id;//课程编号
	private String course_name;//课程名称
	private String course_type_code;//课程类型
	private String course_level_code;//课程级别
	private String course_period;//课程时长
	private String training_item_code;//培训项目
	private String teacher_id;//讲师编号
	private String course_introduction;//课程介绍
	private String is_preseted;//是否为预置课程
	private Date upload_time;//附件上传时间
	private Integer approval_id;//审批单号
	private String process_completed;//课程是否已生效
	private String course_status_code;//课程状态
	private String remark;//备注
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//修改人
	private Date modifyDate;//修改时间
	
	private String applicant;//申请人
	private Date application_time;//申请时间
	private String approval;//审批人
	private Date approval_time;//审批时间
	private String approval_status;//审批状态
	
	private String channel_id;//渠道代码
	private String business_type;//审批业务对象类型
	private String business_id;//审批业务对象代码
	private String approval_item;//审批事项
	private String approval_views;//审批意见
	
	private String course_type_name;//课程类型名称
	private String course_level_name;//课程级别名称
	private String training_item_name;//培训项目名称
	private String teacher_name;//讲师项目
	private String course_status_name;//课程状态
	private String branch_name;//机构名称
	private String flag;//标志位
	
	private String file_name;//附件名
	private String file_id;//附件id
	
	private PageCount pageCount = new PageCount();

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	
	public String getApprBranchId() {
		return apprBranchId;
	}

	public void setApprBranchId(String apprBranchId) {
		this.apprBranchId = apprBranchId;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_type_code() {
		return course_type_code;
	}

	public void setCourse_type_code(String course_type_code) {
		this.course_type_code = course_type_code;
	}

	public String getCourse_level_code() {
		return course_level_code;
	}

	public void setCourse_level_code(String course_level_code) {
		this.course_level_code = course_level_code;
	}

	public String getCourse_period() {
		return course_period;
	}

	public void setCourse_period(String course_period) {
		this.course_period = course_period;
	}

	public String getTraining_item_code() {
		return training_item_code;
	}

	public void setTraining_item_code(String training_item_code) {
		this.training_item_code = training_item_code;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getCourse_introduction() {
		return course_introduction;
	}

	public void setCourse_introduction(String course_introduction) {
		this.course_introduction = course_introduction;
	}

	public String getIs_preseted() {
		return is_preseted;
	}

	public void setIs_preseted(String is_preseted) {
		this.is_preseted = is_preseted;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public Integer getApproval_id() {
		return approval_id;
	}

	public void setApproval_id(Integer approval_id) {
		this.approval_id = approval_id;
	}

	public String getProcess_completed() {
		return process_completed;
	}

	public void setProcess_completed(String process_completed) {
		this.process_completed = process_completed;
	}

	public String getCourse_status_code() {
		return course_status_code;
	}

	public void setCourse_status_code(String course_status_code) {
		this.course_status_code = course_status_code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getApplication_time() {
		return application_time;
	}

	public void setApplication_time(Date application_time) {
		this.application_time = application_time;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public Date getApproval_time() {
		return approval_time;
	}

	public void setApproval_time(Date approval_time) {
		this.approval_time = approval_time;
	}

	public String getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getApproval_item() {
		return approval_item;
	}

	public void setApproval_item(String approval_item) {
		this.approval_item = approval_item;
	}

	public String getApproval_views() {
		return approval_views;
	}

	public void setApproval_views(String approval_views) {
		this.approval_views = approval_views;
	}

	public String getCourse_type_name() {
		return course_type_name;
	}

	public void setCourse_type_name(String course_type_name) {
		this.course_type_name = course_type_name;
	}

	public String getCourse_level_name() {
		return course_level_name;
	}

	public void setCourse_level_name(String course_level_name) {
		this.course_level_name = course_level_name;
	}

	public String getTraining_item_name() {
		return training_item_name;
	}

	public void setTraining_item_name(String training_item_name) {
		this.training_item_name = training_item_name;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getCourse_status_name() {
		return course_status_name;
	}

	public void setCourse_status_name(String course_status_name) {
		this.course_status_name = course_status_name;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public PageCount getPageCount() {
		return pageCount;
	}

	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}

	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
}
