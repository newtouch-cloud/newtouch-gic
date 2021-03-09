package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

/**
* @since:    2014年1月15日   
* @author    ss
* @description:用于封装产品可选缴费方式、产品可选保障期限类型、产品可选缴费期限类型的信息
* 				PDT_Product_Charge_Type、PDT_Product_Coverage_Period、PDT_Product_Charge_Period
*/
public class PolicyLifeTypeSelect {
	private Integer seq_id;//主键
	private String insbranch_id;//保险公司
	private String product_id;//险种代码
	private String product_ver;//险种版本
	private String charge_type_code;//缴费方式代码
	private String charge_type_name;//缴费方式名称
	private String coverage_period_code;//保障期限类型代码
	private String coverage_period_name;//保险期限类型名称
	private String  charge_period_code;//缴费期限类型代码
	private String charge_period_name;//缴费期限类型名称
	private String createuser;//创建人
	private Date createdate;//创建时间
	private String modifyuser;//最后修改人
	private Date modifydate;//最后修改时间
	
	public PolicyLifeTypeSelect() {
	}
	public PolicyLifeTypeSelect(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getInsbranch_id() {
		return insbranch_id;
	}
	public void setInsbranch_id(String insbranch_id) {
		this.insbranch_id = insbranch_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_ver() {
		return product_ver;
	}
	public void setProduct_ver(String product_ver) {
		this.product_ver = product_ver;
	}
	public String getCharge_type_code() {
		return charge_type_code;
	}
	public void setCharge_type_code(String charge_type_code) {
		this.charge_type_code = charge_type_code;
	}
	public String getCharge_type_name() {
		return charge_type_name;
	}
	public void setCharge_type_name(String charge_type_name) {
		this.charge_type_name = charge_type_name;
	}
	public String getCoverage_period_code() {
		return coverage_period_code;
	}
	public void setCoverage_period_code(String coverage_period_code) {
		this.coverage_period_code = coverage_period_code;
	}
	public String getCoverage_period_name() {
		return coverage_period_name;
	}
	public void setCoverage_period_name(String coverage_period_name) {
		this.coverage_period_name = coverage_period_name;
	}
	public String getCharge_period_code() {
		return charge_period_code;
	}
	public void setCharge_period_code(String charge_period_code) {
		this.charge_period_code = charge_period_code;
	}
	public String getCharge_period_name() {
		return charge_period_name;
	}
	public void setCharge_period_name(String charge_period_name) {
		this.charge_period_name = charge_period_name;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

}
