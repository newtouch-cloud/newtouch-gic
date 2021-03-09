package com.ca.cacore.ibs.model.bo;

import java.sql.Date;
/**
 * 
 * @author xxz521
 *
 */
public class ContractLifeInsurantModel implements IContractLifeInsurantModel {
	private Integer seq_id; // 序号
	private String branch_id; // 机构代码 Ref：SYS_Branch
	private String insBranch_id;//保险公司机构
	private Long policy_id; // 保单号
	private String customer_id;// 被保人客户号
	private String relation1; // 与投保人关系
	private String relation2; // 与主被保人关系
	private Integer  insurant_age;//被保人年龄 ?
	private String insurant_name;// 被保人姓名
	private String insurant_certi_code; // 被保人证件类型 Ref：SYS_Library_Certi_Type
	private String insurant_certi_no;// 被保人证件号码
	private String insurant_tel; // 被保人联系电话
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
	public Integer getInsurant_age() {
		return insurant_age;
	}
	public void setInsurant_age(Integer insurant_age) {
		this.insurant_age = insurant_age;
	}
	public String getInsurant_name() {
		return insurant_name;
	}
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}
	public String getInsurant_certi_code() {
		return insurant_certi_code;
	}
	public void setInsurant_certi_code(String insurant_certi_code) {
		this.insurant_certi_code = insurant_certi_code;
	}
	public String getInsurant_certi_no() {
		return insurant_certi_no;
	}
	public void setInsurant_certi_no(String insurant_certi_no) {
		this.insurant_certi_no = insurant_certi_no;
	}
	public String getInsurant_tel() {
		return insurant_tel;
	}
	public void setInsurant_tel(String insurant_tel) {
		this.insurant_tel = insurant_tel;
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
