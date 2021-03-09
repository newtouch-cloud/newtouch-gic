package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

public class InsBranchModel {
	private Integer seq_id; //主键
	private String insBranch_parentid;//上级机构代码
	private String insBranch_id;    //机构代码
	private String insBranch_name;  //机构名称
	private String insBranch_abbr;  //机构简称
	private String insBranch_level; //机构级别
	private String insBranch_allpath;//机构路径
	private String delegate;   //法人代表
	private String address;    //联系地址
	private String zip;    //邮政编码
	private String telephone;//电话号码 
	private String fax;   //机构传真
	private String email; //电子邮件
	private Date found_date;//建立日期
	private Date recall_date;//撤销日期
	private String status;  //状态
	private String remark;  //备注
	private String createUser;//创建人
	private Date createDate;  //创建时间
	private String modifyUser;//修改人
	private Date modifyDate; //修改时间
	public InsBranchModel() {}
	public InsBranchModel(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getInsBranch_parentid() {
		return insBranch_parentid;
	}
	public void setInsBranch_parentid(String insBranch_parentid) {
		this.insBranch_parentid = insBranch_parentid;
	}
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public String getInsBranch_name() {
		return insBranch_name;
	}
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}
	public String getInsBranch_abbr() {
		return insBranch_abbr;
	}
	public void setInsBranch_abbr(String insBranch_abbr) {
		this.insBranch_abbr = insBranch_abbr;
	}
	public String getInsBranch_level() {
		return insBranch_level;
	}
	public void setInsBranch_level(String insBranch_level) {
		this.insBranch_level = insBranch_level;
	}
	public String getInsBranch_allpath() {
		return insBranch_allpath;
	}
	public void setInsBranch_allpath(String insBranch_allpath) {
		this.insBranch_allpath = insBranch_allpath;
	}
	public String getDelegate() {
		return delegate;
	}
	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFound_date() {
		return found_date;
	}
	public void setFound_date(Date found_date) {
		this.found_date = found_date;
	}
	public Date getRecall_date() {
		return recall_date;
	}
	public void setRecall_date(Date recall_date) {
		this.recall_date = recall_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
