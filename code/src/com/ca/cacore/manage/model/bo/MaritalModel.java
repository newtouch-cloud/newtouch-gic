package com.ca.cacore.manage.model.bo;

import java.sql.Date;

public class MaritalModel {
	public Integer seq_id;
	public String marital_stat_code;
	public String marital_stat_name;
	public Integer orderNum;
	public String remark;
	public String createUser;
	public Date createDate;
	public String modifyUser;
	public Date modifyDate;
	public MaritalModel() {
		super();
	}
	public MaritalModel(Integer seq_id) {
		super();
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getMarital_stat_code() {
		return marital_stat_code;
	}
	public void setMarital_stat_code(String marital_stat_code) {
		this.marital_stat_code = marital_stat_code;
	}
	public String getMarital_stat_name() {
		return marital_stat_name;
	}
	public void setMarital_stat_name(String marital_stat_name) {
		this.marital_stat_name = marital_stat_name;
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
