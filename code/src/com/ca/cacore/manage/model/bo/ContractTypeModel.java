package com.ca.cacore.manage.model.bo;

import java.sql.Date;

public class ContractTypeModel {
	public Integer seq_id;
	public String contract_type_code;
	public String contract_type_name;
	public Integer orderNum;
	public String remark;
	public String createUser;
	public Date createDate;
	public String modifyUser;
	public Date modifyDate;
	public ContractTypeModel() {
		super();
	}
	public ContractTypeModel(Integer seq_id) {
		super();
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getContract_type_code() {
		return contract_type_code;
	}
	public void setContract_type_code(String contract_type_code) {
		this.contract_type_code = contract_type_code;
	}
	public String getContract_type_name() {
		return contract_type_name;
	}
	public void setContract_type_name(String contract_type_name) {
		this.contract_type_name = contract_type_name;
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
