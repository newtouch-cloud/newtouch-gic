package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2014年1月23日   
* @author    wang_ds
* @description: 寿险理赔信息 CBS_Claims
*/
public class ClaimsModel implements IClaimsModel {
	
	private Integer seq_id;//序号
	private Long policy_id;//保单ID
	private String branch_id;//销售机构
	private String insBranch_id;//保险公司机构
	private String  policy_code;//保单号
	private String holder_id;//投保人
	private String insurant_id;//第一被保人
	private String event_id;//出险人
	private Date event_date;//出险时间
	private String event_process;//出险经过
	private String claims_status;//'当前状态
	private String remark;//备注
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//修改人
	private Date modifyDate;//修改时间
	
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
	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	
	public String getPolicy_code() {
		return policy_code;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public String getHolder_id() {
		return holder_id;
	}
	public void setHolder_id(String holder_id) {
		this.holder_id = holder_id;
	}
	public String getInsurant_id() {
		return insurant_id;
	}
	public void setInsurant_id(String insurant_id) {
		this.insurant_id = insurant_id;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getEvent_process() {
		return event_process;
	}
	public void setEvent_process(String event_process) {
		this.event_process = event_process;
	}
	public String getClaims_status() {
		return claims_status;
	}
	public void setClaims_status(String claims_status) {
		this.claims_status = claims_status;
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
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	public int getStart() {
		return this.getPageCount().getStart();
	}
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	
	
}
