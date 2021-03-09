package com.ca.cacore.manage.model.vo;

public class BranchTree{

	private String branch_id;
	private String branch_name;
	private String branch_parentid;
	private String selected;//是否选中 
	
	/**
	 * 对用户权限表增加以及删除使用
	 */
	private String role_id;
	private String userName;
	private String isChecked;
	
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
	public String getBranch_parentid() {
		return branch_parentid;
	}
	public void setBranch_parentid(String branch_parentid) {
		this.branch_parentid = branch_parentid;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	

	
	
	
	
}
