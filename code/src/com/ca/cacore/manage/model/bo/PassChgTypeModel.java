package com.ca.cacore.manage.model.bo;

import java.sql.Date;

/**
* @since:    2013年11月16日 上午11:40:49   
* @author    GCH
* @description:密码变更类型字典信息model
*/
public class PassChgTypeModel{
	public Integer seq_id;
	public String passchg_type_code;
	public String passchg_type_name;
	public String remark;
	public String createUser;
	public Date createDate;
	public String modifyUser;
	public Date modifyDate;
	public PassChgTypeModel() {
		super();
	}
	public PassChgTypeModel(Integer seq_id) {
		super();
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getPasschg_type_code() {
		return passchg_type_code;
	}
	public void setPasschg_type_code(String passchg_type_code) {
		this.passchg_type_code = passchg_type_code;
	}
	public String getPasschg_type_name() {
		return passchg_type_name;
	}
	public void setPasschg_type_name(String passchg_type_name) {
		this.passchg_type_name = passchg_type_name;
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
