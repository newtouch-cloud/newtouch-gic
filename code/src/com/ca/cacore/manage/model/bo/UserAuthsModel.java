package com.ca.cacore.manage.model.bo;

import java.util.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 用户权限信息
 */
public class UserAuthsModel implements IUserAuthsModel{
	private Integer seq_id;//主键
	private String userName;//用户名
	private String branch_id;//机构代码
	private String role_id;//角色代码
	private String status;//状态
	private String remark;//备注
	private String createUser;//创建人	
	private Date createDate;//创建时间	
	private String modifyUser;//创建时间	
	private Date modifyDate;//创建时间	
	private PageCount pageCount= new PageCount() ;
	
	
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
	public UserAuthsModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}
	
	public UserAuthsModel(){};
	
	public  UserAuthsModel(     
			 Integer seq_id,//主键
			 String userName,//用户名
			 String branch_id,//机构代码
			 String role_id,//角色代码
			 String status,//状态
			 String remark,//备注
			 String createUser,//创建人	
			 Date createDate,//创建时间	
			 String modifyUser,//创建时间	
			 Date modifyDate
			){
		this.setSeq_id(seq_id);      
		this.setUserName(userName);            
		this.setBranch_id(branch_id);     
		this.setRole_id(role_id);     
		this.setStatus(status);	            
		this.setRemark(remark);           
		this.setCreateUser(createUser);            
		this.setCreateDate(createDate);            
		this.setModifyUser(modifyUser);           
		this.setModifyDate(modifyDate);            
	}
	
	public UserAuthsModel(
			String 	role_type,
			String  role_id,   
			String 	role_name,  
			String 	status,     
			String	remark,     
			String 	createUser, 
			Date	createDate,	
			String 	modifyUser, 
			Date 	modifyDate
			){
		this.setUserName(userName);            
		this.setBranch_id(branch_id);     
		this.setRole_id(role_id);     
		this.setStatus(status);	            
		this.setRemark(remark);           
		this.setCreateUser(createUser);            
		this.setCreateDate(createDate);            
		this.setModifyUser(modifyUser);           
		this.setModifyDate(modifyDate);              
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
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
