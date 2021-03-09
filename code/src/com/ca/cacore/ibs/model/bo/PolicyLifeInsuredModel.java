package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * @description:寿险投保单被保险人信息
 *  对应的数据库表CBS_PolicyLife_Insured
 * @author xxz
 *@since 2013/12/05
 */
public class PolicyLifeInsuredModel implements IPolicyLifeInsuredModel {
	private Integer seq_id; // 序号
	private String branch_id; // 机构代码 Ref：SYS_Branch
	private String insbranch_id;//保险公司机构
	private Long policy_id; // 保单号
	private String customer_id;// 被保人客户号
	private String relation1; // 与投保人关系
	private String relation2; // 与主被保人关系
	private String insurant_name;// 被保人姓名
	private String insurant_certi_code; // 被保人证件类型 Ref：SYS_Library_Certi_Type
	private String insurant_certi_no;// 被保人证件号码
	private String insurant_tel; // 被保人联系电话
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
	private Integer  insurant_age;//被保人年龄
	private PageCount pageCount = new PageCount();

	@Override
	public Integer getSeq_id() {
		return seq_id;
	}

	@Override
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	@Override
	public String getBranch_id() {
		return branch_id;
	}

	@Override
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	@Override
	public Long getPolicy_id() {
		return policy_id;
	}

	@Override
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}

	@Override
	public String getCustomer_id() {
		return customer_id;
	}

	@Override
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	@Override
	public String getRelation1() {
		return relation1;
	}

	@Override
	public void setRelation1(String relation1) {
		this.relation1 = relation1;
	}

	@Override
	public String getRelation2() {
		return relation2;
	}

	@Override
	public void setRelation2(String relation2) {
		this.relation2 = relation2;
	}

	@Override
	public String getInsurant_name() {
		return insurant_name;
	}

	@Override
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}

	@Override
	public String getInsurant_certi_code() {
		return insurant_certi_code;
	}

	@Override
	public void setInsurant_certi_code(String insurant_certi_code) {
		this.insurant_certi_code = insurant_certi_code;
	}

	@Override
	public String getInsurant_certi_no() {
		return insurant_certi_no;
	}

	@Override
	public void setInsurant_certi_no(String insurant_certi_no) {
		this.insurant_certi_no = insurant_certi_no;
	}

	@Override
	public String getInsurant_tel() {
		return insurant_tel;
	}

	@Override
	public void setInsurant_tel(String insurant_tel) {
		this.insurant_tel = insurant_tel;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getCreateUser() {
		return createUser;
	}

	@Override
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String getModifyUser() {
		return modifyUser;
	}

	@Override
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Override
	public Date getModifyDate() {
		return modifyDate;
	}

	@Override
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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

	public String getInsbranch_id() {
		return insbranch_id;
	}

	public void setInsbranch_id(String insbranch_id) {
		this.insbranch_id = insbranch_id;
	}

	public Integer getInsurant_age() {
		return insurant_age;
	}

	public void setInsurant_age(Integer insurant_age) {
		this.insurant_age = insurant_age;
	}
}
