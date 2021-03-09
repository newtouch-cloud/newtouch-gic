package com.ca.cacore.manage.model.bo;

import java.sql.Date;

public class NationalityModel {
	public Integer seq_id;
	public String nationality_code;
	public String nationality_name;
	public Integer orderNum;
	public String remark;
	public String createUser;
	public Date createDate;
	public String modifyUser;
	public Date modifyDate;
	public NationalityModel() {
		super();
	}
	public NationalityModel(Integer seq_id) {
		super();
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getNationality_code() {
		return nationality_code;
	}
	public void setNationality_code(String nationality_code) {
		this.nationality_code = nationality_code;
	}
	public String getNationality_name() {
		return nationality_name;
	}
	public void setNationality_name(String nationality_name) {
		this.nationality_name = nationality_name;
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
