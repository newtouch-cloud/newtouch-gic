package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

/**
* @since:    2013年12月5日 下午5:22:57   
* @author    GCH
* @description:CBS_Problem_Origin_Type
*/
public class ProblemOriginTypeModel {
	private Integer seq_id;
	private String origin_type_code;
	private String origin_type_name;
	private String orderNum;
	private String remark;
	private String createUser;
	private Date createDate;
	private String modifyUser;
	private Date modifyDate;
	public ProblemOriginTypeModel() {}
	public ProblemOriginTypeModel(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getOrigin_type_code() {
		return origin_type_code;
	}
	public void setOrigin_type_code(String origin_type_code) {
		this.origin_type_code = origin_type_code;
	}
	public String getOrigin_type_name() {
		return origin_type_name;
	}
	public void setOrigin_type_name(String origin_type_name) {
		this.origin_type_name = origin_type_name;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
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
