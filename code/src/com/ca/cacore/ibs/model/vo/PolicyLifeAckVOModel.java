package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class PolicyLifeAckVOModel  implements IPolicyLifeAckVOModel{
	private Integer seq_id; // 序号
	private Long policy_id; // 保单号
	private String branch_id; // 机构代码
	private Date ack_branch_date;// 公司签收回执日期
	private Date ack_customer_date;// 客户签收回执日期
	private String ack_notes;//回执说明
	private String remark;//备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
	private String insBranch_id; // 保险公司机构
	private String policy_code; // 保单号
	private String send_code; // 投投单号
	private Date hold_date;//投保日期
	private String hold_dateStr;//投保日期字符串
	private String ack_branch_dateStr;//公司签收日期
	private String ack_customer_dateStr;//客户签收日期
	private String app_name;//投保人名字
	private String branch_name;//机构名字
	private String status;//保单状态
	private String insBranch_name;//保险公司名称
	private String agent_name;//销售人员名称
	private String insurant_name;//服务人员名称
	private String service_name;//被保人名称
	private String status_name;//保单的状态名称
	public IPolicyImageModel policyImageModel ;//影像上传集合
	private PageCount pageCount = new PageCount();
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public Long getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public Date getAck_branch_date() {
		return ack_branch_date;
	}
	public void setAck_branch_date(Date ack_branch_date) {
		this.ack_branch_date = ack_branch_date;
	}
	public Date getAck_customer_date() {
		return ack_customer_date;
	}
	public void setAck_customer_date(Date ack_customer_date) {
		this.ack_customer_date = ack_customer_date;
	}
	public String getAck_notes() {
		return ack_notes;
	}
	public void setAck_notes(String ack_notes) {
		this.ack_notes = ack_notes;
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
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public IPolicyImageModel getPolicyImageModel() {
		return policyImageModel;
	}
	public void setPolicyImageModel(IPolicyImageModel policyImageModel) {
		this.policyImageModel = policyImageModel;
	}
	
	public String getInsBranch_name() {
		return insBranch_name;
	}
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getInsurant_name() {
		return insurant_name;
	}
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getSend_code() {
		return send_code;
	}
	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	public String getPolicy_code() {
		return policy_code;
	}
	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}
	public Date getHold_date() {
		return hold_date;
	}
	public void setHold_date(Date hold_date) {
		this.hold_date = hold_date;
	}
	public String getHold_dateStr() {
		return hold_dateStr;
	}
	public void setHold_dateStr(String hold_dateStr) {
		this.hold_dateStr = hold_dateStr;
	}
	public String getAck_branch_dateStr() {
		return ack_branch_dateStr;
	}
	public void setAck_branch_dateStr(String ack_branch_dateStr) {
		this.ack_branch_dateStr = ack_branch_dateStr;
	}
	public String getAck_customer_dateStr() {
		return ack_customer_dateStr;
	}
	public void setAck_customer_dateStr(String ack_customer_dateStr) {
		this.ack_customer_dateStr = ack_customer_dateStr;
	}
	@Override
	public PageCount getPageCount() {
		return this.pageCount;
	}

	@Override
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
