package com.ca.cacore.ibs.model.bo;

import java.sql.Date;
/**
 * 收益性质数据字典
 * @author xxz521
 *
 */
public class BeneficiaryTypeModel {
	private Integer seq_id; // 序号
	private String bene_type_code;//收益性质代码
	private String bene_type_name;//收益名称
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
	public String getBene_type_code() {
		return bene_type_code;
	}
	public void setBene_type_code(String bene_type_code) {
		this.bene_type_code = bene_type_code;
	}
	public String getBene_type_name() {
		return bene_type_name;
	}
	public void setBene_type_name(String bene_type_name) {
		this.bene_type_name = bene_type_name;
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
