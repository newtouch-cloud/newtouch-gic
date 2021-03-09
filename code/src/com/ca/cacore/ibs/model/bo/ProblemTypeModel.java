package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

/**
* @since:    2013年12月10日 下午3:46:23   
* @author    GCH
* @description:问题件类型数据字典
*/
public class ProblemTypeModel {
	private Integer seq_id;
	private String type_code;
	private String type_name;
	private Integer orderNum;
	private String remak;
	private String createUser;
	private Date createDate;
	private String modifyUser;
	private Date modifyDate;
	public ProblemTypeModel() {}
	public ProblemTypeModel(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getRemak() {
		return remak;
	}
	public void setRemak(String remak) {
		this.remak = remak;
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
