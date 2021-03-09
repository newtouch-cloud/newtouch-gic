package com.ca.cacore.manage.model.bo;

import java.util.Date;
/**
 * 操作账户密码变更历史
 */
public class UserPassChgHisModel {
	private Integer seq_id;
	private String  userName;
	private String  chg_type;
	private String  bef_password;
	private String  aft_password;
	private Date  eff_date;
	private String  remark;
	private String  createUser;
	private Date  createDate;
	private String  modifyUser;
	private Date  modifyDate;
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		this.userName = username;
	}
 
	public String getBef_password() {
		return bef_password;
	}
	public void setBef_password(String bef_password) {
		this.bef_password = bef_password;
	}
	public String getAft_password() {
		return aft_password;
	}
	public void setAft_password(String aft_password) {
		this.aft_password = aft_password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChg_type() {
		return chg_type;
	}
	public void setChg_type(String chg_type) {
		this.chg_type = chg_type;
	}
	public Date getEff_date() {
		return eff_date;
	}
	public void setEff_date(Date eff_date) {
		this.eff_date = eff_date;
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
