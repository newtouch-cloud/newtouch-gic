package com.newtouch.core.rightsmgmt.model;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class SuperiorModel implements ISuperiorModel{
	
	private String opt_no;//人员编码
	private String opt_name;//人员姓名
	private String superior_no;//领导编号
	private String superior_name;//领导姓名
	private String branch_id;//机构id
	private String branch_name;//机构名称
	private String superior_id;//序列
	
	public String getOpt_no() {
		return opt_no;
	}
	public String getOpt_name() {
		return opt_name;
	}
	public String getSuperior_no() {
		return superior_no;
	}
	public void setOpt_no(String opt_no) {
		this.opt_no = opt_no;
	}
	public void setOpt_name(String opt_name) {
		this.opt_name = opt_name;
	}
	public void setSuperior_no(String superior_no) {
		this.superior_no = superior_no;
	}
	public String getSuperior_name() {
		return superior_name;
	}
	public void setSuperior_name(String superior_name) {
		this.superior_name = superior_name;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getSuperior_id() {
		return superior_id;
	}
	public void setSuperior_id(String superior_id) {
		this.superior_id = superior_id;
	}

	private PageCount pageCount = new PageCount();
	@Override
	public PageCount getPageCount() {
		// TODO Auto-generated method stub
		return pageCount;
	}
	@Override
	public void setPageCount(PageCount pageCount) {
		// TODO Auto-generated method stub
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		// TODO Auto-generated method stub
		return this.getPageCount().getLimit();
	}

}
