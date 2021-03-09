package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * @description:寿险投保单投保人信息
 *  对应的数据库表CBS_PolicyLife_Holder
 * @author xxz
 * @since 2013/12/05
 */
public class PolicyLifeHolderModel implements IPolicyLifeHolderModel {
	private Integer seq_id; // 序号
	private String insbranch_id; // 保险公司机构
	private String branch_id; // 机构代码 Ref：SYS_Branch
	private String send_code; // 投保单号码
	private Long policy_id; // 保单号
	private String customer_id;// 投保人客户号
	private String app_name;// 投保人姓名
	private String app_certi_code;// 投保人证件类型 Ref：SYS_Library_Certi_Type
	private String app_certi_no;// 投保人证件号码
	private String app_address;// 投保人地址
	private String app_tel;// 投保人联系电话
	private String app_zip;// 投保人邮编
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
	private Integer app_age;//年龄
	
	private PageCount pageCount = new PageCount();

	public PolicyLifeHolderModel() {
	}

	public PolicyLifeHolderModel(Integer seq_id) {
		this.seq_id = seq_id;
	}

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

	public String getSend_code() {
		return send_code;
	}

	public void setSend_code(String send_code) {
		this.send_code = send_code;
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

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_certi_code() {
		return app_certi_code;
	}

	public void setApp_certi_code(String app_certi_code) {
		this.app_certi_code = app_certi_code;
	}

	public String getApp_certi_no() {
		return app_certi_no;
	}

	public void setApp_certi_no(String app_certi_no) {
		this.app_certi_no = app_certi_no;
	}

	public String getApp_address() {
		return app_address;
	}

	public void setApp_address(String app_address) {
		this.app_address = app_address;
	}

	public String getApp_tel() {
		return app_tel;
	}

	public void setApp_tel(String app_tel) {
		this.app_tel = app_tel;
	}

	public String getApp_zip() {
		return app_zip;
	}

	public void setApp_zip(String app_zip) {
		this.app_zip = app_zip;
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

	public Integer getApp_age() {
		return app_age;
	}

	public void setApp_age(Integer app_age) {
		this.app_age = app_age;
	}

	
	public String getInsbranch_id() {
		return insbranch_id;
	}

	public void setInsbranch_id(String insbranch_id) {
		this.insbranch_id = insbranch_id;
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
