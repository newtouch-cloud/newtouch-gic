package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * @author guochunhua
 * @since 2014-2-25
 * sys_branch、smis_appr、hrm_recruit_Batch、hrm_recruit_status、hrm_recruit_BatchAppr
 */
public class RecruitBatchApprVOModel implements IRecruitBatchApprVOModel{
	private Integer seq_id;
	private String channel_id;//渠道代码
	private String branch_id;//机构代码
	private String apprBranchId;//审批总表机构代码
	private String business_type;//审批业务对象类型
	private String business_id;//审批业务对象代码
	private String approval_item;//审批事项
	private Integer approval_id;//审批单编号
	private String approval_views;//审批意见 
	private String remark;//备注
	private String applicant;//申请人
	private Date application_time;//申请时间
	private String approval_status;//审批状态
	private String approval;//审批人
	private Date approval_time;//审批时间
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//修改人
	private Date modifyDate;//修改时间
	
	private String status_code;//批次状态代码
	private String status_name;//批次状态名称
	
	private String batch_id;//批次号
	private String batch_name;//批次名称
	private Integer plan_recruitNum;//计划招聘人数
	private String batch_status;//批次状态
	private Date start_time;//开始时间
	private Date end_time;//结束时间
	private String process_completed;//系统是否已生效
	
	private String branch_name;//机构名称
	private String flag;//标志位
	private PageCount pageCount = new PageCount();
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
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
	public Integer getApproval_id() {
		return approval_id;
	}
	public void setApproval_id(Integer approval_id) {
		this.approval_id = approval_id;
	}
	public String getApproval_views() {
		return approval_views;
	}
	public void setApproval_views(String approval_views) {
		this.approval_views = approval_views;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
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
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getBatch_name() {
		return batch_name;
	}
	public void setBatch_name(String batch_name) {
		this.batch_name = batch_name;
	}
	public Integer getPlan_recruitNum() {
		return plan_recruitNum;
	}
	public void setPlan_recruitNum(Integer plan_recruitNum) {
		this.plan_recruitNum = plan_recruitNum;
	}
	public String getBatch_status() {
		return batch_status;
	}
	public void setBatch_status(String batch_status) {
		this.batch_status = batch_status;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getProcess_completed() {
		return process_completed;
	}
	public void setProcess_completed(String process_completed) {
		this.process_completed = process_completed;
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
	
}
