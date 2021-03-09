package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2014年2月27日   
* @author    GCH
* @description:培训立项审批
*/
public class TrainProjectApprVOModel implements ITrainProjectApprVOModel{
	private Integer seq_id;//主键
	private String branch_id;// 机构代码
	private String apprBranchId;//审批事项所属机构代码
	private String project_id;// 培训计划（立项编号）
	private String project_name;// 培训计划（立项名称）
	private String training_format_code;// 制式类型
	private String training_type_code;// 培训类型
	private String training_class_name;// 培训班名称
	private String training_item;// 培训项目代码
	private String training_item_name;//培训项目名称
	private String plan_periods;// 培训期数
	private String training_mode_code;// 培训方式
	private Integer training_day;// 培训天数（天）
	private Integer plan_person_num;// 培训人数
	private String training_object;// 培训对象
	private Double teach_fee;// 授课费
	private Double person_average_fee;// 人均培训费用（元）
	private Double training_budget;// 培训经费预算（元）
	private Double chief_branch_fee;// 总公司承担费（元）
	private Double lower_branch_fee;// 分公司承担费（元）
	private String training_address;// 培训地点
	private Date upload_time;// 附件上传时间
	private Integer approval_id;// 审批单编号
	private String process_completed;// 系统是否已生效
	private String status_code;// 立项状态
	private String remark;// 备注
	private String createUser;// 创建人
	private Date createDate;// 创建时间
	private String modifyUser;// 最后修改人
	private Date modifyDate;// 最后修改时间	
	
	private String applicant; //申请人
	private Date application_time;//申请时间
	private String approval;//审批人
	private Date approval_time;//审批时间
	private String approval_status;//审批状态
	
	private String channel_id;//渠道代码
	private String business_type;//审批业务对象类型
	private String business_id;//审批业务对象代码
	private String approval_item;//审批事项
	private String approval_views;//审批意见 
	
	private String branch_name;//机构名称
	private String flag;//标志位
	
	private String file_name;//附件名称
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
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getTraining_format_code() {
		return training_format_code;
	}
	public void setTraining_format_code(String training_format_code) {
		this.training_format_code = training_format_code;
	}
	public String getTraining_type_code() {
		return training_type_code;
	}
	public void setTraining_type_code(String training_type_code) {
		this.training_type_code = training_type_code;
	}
	public String getTraining_class_name() {
		return training_class_name;
	}
	public void setTraining_class_name(String training_class_name) {
		this.training_class_name = training_class_name;
	}
	public String getTraining_item() {
		return training_item;
	}
	public void setTraining_item(String training_item) {
		this.training_item = training_item;
	}
	public String getTraining_item_name() {
		return training_item_name;
	}
	public void setTraining_item_name(String training_item_name) {
		this.training_item_name = training_item_name;
	}
	public String getPlan_periods() {
		return plan_periods;
	}
	public void setPlan_periods(String plan_periods) {
		this.plan_periods = plan_periods;
	}
	public String getTraining_mode_code() {
		return training_mode_code;
	}
	public void setTraining_mode_code(String training_mode_code) {
		this.training_mode_code = training_mode_code;
	}
	public Integer getTraining_day() {
		return training_day;
	}
	public void setTraining_day(Integer training_day) {
		this.training_day = training_day;
	}
	public Integer getPlan_person_num() {
		return plan_person_num;
	}
	public void setPlan_person_num(Integer plan_person_num) {
		this.plan_person_num = plan_person_num;
	}
	public String getTraining_object() {
		return training_object;
	}
	public void setTraining_object(String training_object) {
		this.training_object = training_object;
	}
	public String getTraining_address() {
		return training_address;
	}
	public void setTraining_address(String training_address) {
		this.training_address = training_address;
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
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
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
	public Double getTeach_fee() {
		return teach_fee;
	}
	public void setTeach_fee(Double teach_fee) {
		this.teach_fee = teach_fee;
	}
	public Double getPerson_average_fee() {
		return person_average_fee;
	}
	public void setPerson_average_fee(Double person_average_fee) {
		this.person_average_fee = person_average_fee;
	}
	public Double getTraining_budget() {
		return training_budget;
	}
	public void setTraining_budget(Double training_budget) {
		this.training_budget = training_budget;
	}
	public Double getChief_branch_fee() {
		return chief_branch_fee;
	}
	public void setChief_branch_fee(Double chief_branch_fee) {
		this.chief_branch_fee = chief_branch_fee;
	}
	public Double getLower_branch_fee() {
		return lower_branch_fee;
	}
	public void setLower_branch_fee(Double lower_branch_fee) {
		this.lower_branch_fee = lower_branch_fee;
	}
	
}
