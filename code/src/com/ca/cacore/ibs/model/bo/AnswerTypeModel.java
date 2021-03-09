package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

public class AnswerTypeModel {
	private Integer seq_id;//序号
	private String answer_type_code;//回访方式代码
	private String answer_type_name;//回访方式名称
	private Integer orderNum;//序号
	private String remark;//备注
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//修改人
	private Date modifyDate;//修改时间
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getAnswer_type_code() {
		return answer_type_code;
	}
	public void setAnswer_type_code(String answer_type_code) {
		this.answer_type_code = answer_type_code;
	}
	public String getAnswer_type_name() {
		return answer_type_name;
	}
	public void setAnswer_type_name(String answer_type_name) {
		this.answer_type_name = answer_type_name;
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
