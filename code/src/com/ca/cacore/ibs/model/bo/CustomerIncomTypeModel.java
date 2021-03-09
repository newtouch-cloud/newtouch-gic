package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

/**
 * 
 * @author xxz521
 *
 */
public class CustomerIncomTypeModel {
	private Integer seq_id;//序号
	private String incomType_code;//收入范围代码
	private String incomType_name;//收入范围名称
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
	public String getIncomType_code() {
		return incomType_code;
	}
	public void setIncomType_code(String incomType_code) {
		this.incomType_code = incomType_code;
	}
	public String getIncomType_name() {
		return incomType_name;
	}
	public void setIncomType_name(String incomType_name) {
		this.incomType_name = incomType_name;
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
