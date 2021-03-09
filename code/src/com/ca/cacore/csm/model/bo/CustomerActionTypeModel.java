package com.ca.cacore.csm.model.bo;

import java.sql.Date;

public class CustomerActionTypeModel {
	private Integer seq_id;
	private String actiontype_code;//行为分类代码
	private String actiontype_name;//行为分类名称
	private Integer orderNum;//排序序号
	private String remark;//备注
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//修改人
	private Date modifyDate;//修改时间
	
	public CustomerActionTypeModel() {}
	public CustomerActionTypeModel(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getActiontype_code() {
		return actiontype_code;
	}
	public void setActiontype_code(String actiontype_code) {
		this.actiontype_code = actiontype_code;
	}
	public String getActiontype_name() {
		return actiontype_name;
	}
	public void setActiontype_name(String actiontype_name) {
		this.actiontype_name = actiontype_name;
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
