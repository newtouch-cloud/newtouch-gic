package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

/**
 * 
 * @author xxz521
 *
 */
public class ContractLifeBeneficiaryModel implements IContractLifeBeneficiaryModel {
	private Integer seq_id; // 序号
	private String branch_id; // 机构代码 Ref：SYS_Branch
	private String insBranch_id; // 保险公司机构
	private Long policy_id; // 保单号
	private String customer_id;// 受益人客户号
	private String insurant_id;// 被保人id
	private Integer bene_age;//收益人年龄
	private String bene_certi_code;//受益人证件类型
	private String bene_certi_no;//受益人证件号码
	private String bene_tel;//受益人电话
	private Integer bene_order;//收益顺序
	private Double bene_rate;//收益比例
	private String bene_type;// 受益性质 Ref：CBS_Beneficiary_Type
	private String relation1; // 与投保人关系 Ref：CBS_Applicant_Relation2
	private String relation2; // 与主被保人关系Ref：CBS_Applicant_Relation2
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
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
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public Long getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getInsurant_id() {
		return insurant_id;
	}
	public void setInsurant_id(String insurant_id) {
		this.insurant_id = insurant_id;
	}
	public Integer getBene_age() {
		return bene_age;
	}
	public void setBene_age(Integer bene_age) {
		this.bene_age = bene_age;
	}
	public String getBene_certi_code() {
		return bene_certi_code;
	}
	public void setBene_certi_code(String bene_certi_code) {
		this.bene_certi_code = bene_certi_code;
	}
	public String getBene_certi_no() {
		return bene_certi_no;
	}
	public void setBene_certi_no(String bene_certi_no) {
		this.bene_certi_no = bene_certi_no;
	}
	public String getBene_tel() {
		return bene_tel;
	}
	public void setBene_tel(String bene_tel) {
		this.bene_tel = bene_tel;
	}
	public Integer getBene_order() {
		return bene_order;
	}
	public void setBene_order(Integer bene_order) {
		this.bene_order = bene_order;
	}
	public Double getBene_rate() {
		return bene_rate;
	}
	public void setBene_rate(Double bene_rate) {
		this.bene_rate = bene_rate;
	}
	public String getBene_type() {
		return bene_type;
	}
	public void setBene_type(String bene_type) {
		this.bene_type = bene_type;
	}
	public String getRelation1() {
		return relation1;
	}
	public void setRelation1(String relation1) {
		this.relation1 = relation1;
	}
	public String getRelation2() {
		return relation2;
	}
	public void setRelation2(String relation2) {
		this.relation2 = relation2;
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
}
