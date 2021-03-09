package com.ca.cacore.manage.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 部门基本信息
 * 
 * @author admin
 * 
 */
public class DeptmentModel implements IDeptmentModel{
	private Integer seq_id; // 主键
	private String branch_id; // 机构代码
	private String dept_parentid; // 上级部门代码
	private String dept_id; // 部门代码
	private String dept_name; // 部门名称
	private String dept_abbr; // 部门简称
	private String zip; // 邮政编码
	private String address; // 联系地址
	private String telephone; // 电话号码
	private String fax; // 机构传真
	private Date found_date; // 建立日期
	private Date recall_date; // 撤消日期
	private String status; // 状态
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private PageCount pageCount= new PageCount();
	
	
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pagination) {
		this.pageCount = pagination;
	}
	public int getStart() {
		return this.getPageCount().getStart();
	}
	public int getLimit() {
		return this.getPageCount().getLimit();
	}	
	
	
	public DeptmentModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}
	
	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getDept_parentid() {
		return dept_parentid;
	}

	public void setDept_parentid(String dept_parentid) {
		this.dept_parentid = dept_parentid;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDept_abbr() {
		return dept_abbr;
	}

	public void setDept_abbr(String dept_abbr) {
		this.dept_abbr = dept_abbr;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
