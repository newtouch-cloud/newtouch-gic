package com.ca.cacore.msss.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 
 * @author Wang_ds
 * @since 2013-12-4
 * @description PDT_Product_Llife 寿险产品信息';
 */
public class ProductLlifeModel implements IProductLlifeModel {
	private Integer seq_id; // 主键
	private String insBranch_id; // 保险公司
	private String product_id; // 险种代码
	private String product_name; // 产品名称
	private String product_abbr; // 产品简称
	private String product_enName; //产品英文名称
	private String product_enAbbr; //产品英文简称
	private String product_ver; // 险种版本
	private String product_type; //产品分类（产品大类）
	private String product_type2; //产品分类2
	private String product_type3; // 产品分类3
	private String ins_type; //主附险标志
	private String period_type; // 保险期限类型
	private String surr_permit; // 可否退保 Y可以 N 不可以
	private String renew; // 是否可续保 Y 可以 N 不可以
	private String insuredFlag; // 是否多被保人
	private String benefit_type; //保障范围说明
	private String duty; //基本责任说明
	private String policy_holder; // 投保人员限制说明
	private String exception; // 保险责任免除说明
	private Date start_date; // 起售时间
	private Date end_date; // 停售时间
	private String status; // 状态
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private String is_auth_partreceived;//是否可以部分领取年金
	private String is_can_borrow;//  是否可以保单借款
	private String inner_product_id;//  内部产品代码
	
	private PageCount pageCount=new PageCount();
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_abbr() {
		return product_abbr;
	}
	public void setProduct_abbr(String product_abbr) {
		this.product_abbr = product_abbr;
	}
	public String getProduct_enName() {
		return product_enName;
	}
	public void setProduct_enName(String product_enName) {
		this.product_enName = product_enName;
	}
	public String getProduct_enAbbr() {
		return product_enAbbr;
	}
	public void setProduct_enAbbr(String product_enAbbr) {
		this.product_enAbbr = product_enAbbr;
	}
	public String getProduct_ver() {
		return product_ver;
	}
	public void setProduct_ver(String product_ver) {
		this.product_ver = product_ver;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduct_type2() {
		return product_type2;
	}
	public void setProduct_type2(String product_type2) {
		this.product_type2 = product_type2;
	}
	public String getProduct_type3() {
		return product_type3;
	}
	public void setProduct_type3(String product_type3) {
		this.product_type3 = product_type3;
	}
	public String getIns_type() {
		return ins_type;
	}
	public void setIns_type(String ins_type) {
		this.ins_type = ins_type;
	}
	public String getPeriod_type() {
		return period_type;
	}
	public void setPeriod_type(String period_type) {
		this.period_type = period_type;
	}
	public String getSurr_permit() {
		return surr_permit;
	}
	public void setSurr_permit(String surr_permit) {
		this.surr_permit = surr_permit;
	}
	public String getRenew() {
		return renew;
	}
	public void setRenew(String renew) {
		this.renew = renew;
	}
	public String getInsuredFlag() {
		return insuredFlag;
	}
	public void setInsuredFlag(String insuredFlag) {
		this.insuredFlag = insuredFlag;
	}
	public String getBenefit_type() {
		return benefit_type;
	}
	public void setBenefit_type(String benefit_type) {
		this.benefit_type = benefit_type;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getPolicy_holder() {
		return policy_holder;
	}
	public void setPolicy_holder(String policy_holder) {
		this.policy_holder = policy_holder;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
	public String getIs_auth_partreceived() {
		return is_auth_partreceived;
	}
	public void setIs_auth_partreceived(String is_auth_partreceived) {
		this.is_auth_partreceived = is_auth_partreceived;
	}
	public String getIs_can_borrow() {
		return is_can_borrow;
	}
	public void setIs_can_borrow(String is_can_borrow) {
		this.is_can_borrow = is_can_borrow;
	}
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	public String getInner_product_id() {
		return inner_product_id;
	}
	public void setInner_product_id(String inner_product_id) {
		this.inner_product_id = inner_product_id;
	}
	
}
