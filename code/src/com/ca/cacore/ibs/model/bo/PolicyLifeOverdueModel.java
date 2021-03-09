package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

/**
 *保费逾期未付数据字典信息
 * 对应的数据库表：CBS_PolicyLife_Overdue
 * @author xxz521
 */
public class PolicyLifeOverdueModel {
	private Integer seq_id;//序号
	private String overdue_code;//选择代码
	private String overdue_name;//选择名称
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
	public String getOverdue_code() {
		return overdue_code;
	}
	public void setOverdue_code(String overdue_code) {
		this.overdue_code = overdue_code;
	}
	public String getOverdue_name() {
		return overdue_name;
	}
	public void setOverdue_name(String overdue_name) {
		this.overdue_name = overdue_name;
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
