package com.ca.cacore.ibs.model.bo;

import java.sql.Date;


/**
* @since:    2014年1月23日   
* @author    wang_ds
* @description: 理赔状态数据字典 CBS_Claims_Status
*/
public class ClaimsStatusModel {
	
	private Integer seq_id;//序号
	private String ClaimsStatus_Code;//保单ID
	private String ClaimsStatus_Name;//销售机构
	private String orderNum;//保险公司机构
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
	public String getClaimsStatus_Code() {
		return ClaimsStatus_Code;
	}
	public void setClaimsStatus_Code(String claimsStatus_Code) {
		ClaimsStatus_Code = claimsStatus_Code;
	}
	public String getClaimsStatus_Name() {
		return ClaimsStatus_Name;
	}
	public void setClaimsStatus_Name(String claimsStatus_Name) {
		ClaimsStatus_Name = claimsStatus_Name;
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
