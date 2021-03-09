package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2014年1月23日   
* @author    wang_ds
* @description: 寿险理赔信息 CBS_Claims
*/
public class ConservationVOModel implements IConservationVOModel{
	
	private Integer seq_id;			//序号
	private Long policy_id;			//保单ID
	private String branch_id;		//销售机构
	private String branch_name;		//销售机构
	private String insBranch_id;	//保险公司机构
	private String insBranch_name;	//保险公司机构
	private String  policy_code;	//保单号
	private String holder_id;		//投保人
	private String holder_name;		//投保姓名
	private String insurant_id;		//第一被保人
	private String insurant_name;	//第一被保人名字
	private String conservationItem_code;//保全项目
	private String conservationItem_name;	//保全项目
	private Date application_time;	//申请时间
	private String applicant_id;	//申请人
	private String applicant_name;	//申请人
	private String replace_applicant;//代办人
	private String remark;//备注
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//修改人
	private Date modifyDate;//修改时间
	private Date validate_date;//保单生效日期
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
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
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
	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}
	public String getHolder_id() {
		return holder_id;
	}
	public void setHolder_id(String holder_id) {
		this.holder_id = holder_id;
	}
	public String getHolder_name() {
		return holder_name;
	}
	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}
	public String getInsurant_id() {
		return insurant_id;
	}
	public void setInsurant_id(String insurant_id) {
		this.insurant_id = insurant_id;
	}
	public String getInsurant_name() {
		return insurant_name;
	}
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}

	public String getConservationItem_code() {
		return conservationItem_code;
	}
	public void setConservationItem_code(String conservationItem_code) {
		this.conservationItem_code = conservationItem_code;
	}
	public String getConservationItem_name() {
		return conservationItem_name;
	}
	public void setConservationItem_name(String conservationItem_name) {
		this.conservationItem_name = conservationItem_name;
	}
	public Date getApplication_time() {
		return application_time;
	}
	public void setApplication_time(Date application_time) {
		this.application_time = application_time;
	}
	public String getApplicant_id() {
		return applicant_id;
	}
	public void setApplicant_id(String applicant_id) {
		this.applicant_id = applicant_id;
	}
	public String getApplicant_name() {
		return applicant_name;
	}
	public void setApplicant_name(String applicant_name) {
		this.applicant_name = applicant_name;
	}
	public String getReplace_applicant() {
		return replace_applicant;
	}
	public void setReplace_applicant(String replace_applicant) {
		this.replace_applicant = replace_applicant;
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
	
	public String getInsBranch_name() {
		return insBranch_name;
	}
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
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
	public Date getValidate_date() {
		return validate_date;
	}
	public void setValidate_date(Date validate_date) {
		this.validate_date = validate_date;
	}

	
	
}
