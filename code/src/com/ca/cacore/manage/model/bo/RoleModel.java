package com.ca.cacore.manage.model.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 角色基本信息
 */
public class RoleModel implements IRoleModel{

	private Integer	seq_id;    //主键	
	private String 	role_type;	//角色类型
	private String  role_id	;  //角色代码
	private String 	role_name;  //角色名称
	private String 	status	;//状态
	private String	remark	;// 备注
	private String 	createUser;//创建人	
	private Date	createDate	;//创建时间 
	private String 	modifyUser;//最后修改人	
	private Date 	modifyDate;//最后修改时间
	
	
	private PageCount pageCount = new PageCount();
	
	public RoleModel(){};
	public RoleModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}
	
	public  RoleModel(     
			Integer	seq_id,     
			String 	role_type,	
			String  role_id	,   
			String 	role_name,  
			String 	status,     
			String	remark,     
			String 	createUser, 
			Date	createDate,	
			String 	modifyUser, 
			Date 	modifyDate
			){
		this.setSeq_id(seq_id);      
		this.setRole_type(role_type);	            
		this.setRole_id(role_id);            
		this.setRole_name(role_name);            
		this.setStatus(status);	            
		this.setRemark(remark);           
		this.setCreateUser(createUser);            
		this.setCreateDate(createDate);            
		this.setModifyUser(modifyUser);           
		this.setModifyDate(modifyDate);            
	}
	
	public RoleModel(
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
		this.setRole_type(role_type);	            
		this.setRole_id(role_id);            
		this.setRole_name(role_name);            
		this.setStatus(status);	            
		this.setRemark(remark);           
		this.setCreateUser(createUser);            
		this.setCreateDate(createDate);            
		this.setModifyUser(modifyUser);           
		this.setModifyDate(modifyDate);            
	}
	
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
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
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
	public String getCreateDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(createDate);
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
	public String getModifyDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(modifyDate);
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	
}