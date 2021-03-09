package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

public class PayModeModel {
	public Integer seq_id;
	public String pay_mode_code;
	public String pay_mode_name;
	public Integer orderNum;
	public String remark;
	public String createUser;
	public Date createDate;
	public String modifyUser;
	public Date modifyDate;
	public PayModeModel() {
		super();
	}
	public PayModeModel(Integer seq_id) {
		super();
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getPay_mode_code() {
		return pay_mode_code;
	}
	public void setPay_mode_code(String pay_mode_code) {
		this.pay_mode_code = pay_mode_code;
	}
	public String getPay_mode_name() {
		return pay_mode_name;
	}
	public void setPay_mode_name(String pay_mode_name) {
		this.pay_mode_name = pay_mode_name;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
