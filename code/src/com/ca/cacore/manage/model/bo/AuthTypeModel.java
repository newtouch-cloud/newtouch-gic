package com.ca.cacore.manage.model.bo;

import java.sql.Date;

/**
 * 权限类型信息
 * @author admin
 *
 */
public class AuthTypeModel {
	private Integer seq_id;   //主键		
	private String auth_type_code;	//权限类型代码
	private String auth_type_name;	//权限类型名称
	private String remark;   //备注		             
	private String createUser;	//创建人	             
	private Date createDate;       //创建时间	             
	private String modifyUser;   //最后修改人	             
	private Date modifyDate;       //最后修改时间
	public AuthTypeModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}
	public AuthTypeModel(){}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getAuth_type_code() {
		return auth_type_code;
	}
	public void setAuth_type_code(String auth_type_code) {
		this.auth_type_code = auth_type_code;
	}
	public String getAuth_type_name() {
		return auth_type_name;
	}
	public void setAuth_type_name(String auth_type_name) {
		this.auth_type_name = auth_type_name;
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
