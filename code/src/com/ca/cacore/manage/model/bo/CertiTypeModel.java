package com.ca.cacore.manage.model.bo;

import java.sql.Date;

public class CertiTypeModel {
	public Integer seq_id;
	public String certi_type_code;
	public String certi_type_name;
	public Integer orderNum;
	public String remark;
	public String createUser;
	public Date createDate;
	public String modifyUser;
	public Date modifyDate;
	public CertiTypeModel() {
		super();
	}
	public CertiTypeModel(Integer seq_id) {
		super();
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getCerti_type_code() {
		return certi_type_code;
	}
	public void setCerti_type_code(String certi_type_code) {
		this.certi_type_code = certi_type_code;
	}
	public String getCerti_type_name() {
		return certi_type_name;
	}
	public void setCerti_type_name(String certi_type_name) {
		this.certi_type_name = certi_type_name;
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
