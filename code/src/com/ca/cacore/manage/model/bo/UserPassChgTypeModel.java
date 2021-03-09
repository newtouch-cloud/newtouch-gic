package com.ca.cacore.manage.model.bo;

import java.util.Date;

/**
 * 操作账户密码变更类型
 */
public class UserPassChgTypeModel {
	private Integer seq_id;  //主键	
	private String	passchg_type_code; //变更类型代码
	private String	passchg_type_name;	//变更类型名称
	private String	remark;	//备注
	private String	createUser;	//创建人	
	private Date	CreateDate;	//创建时间
	private String	modifyUser;	//最后修改人
	private Date	modifyDate;	//最后修改时间
	
	public UserPassChgTypeModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getPasschg_type_code() {
		return passchg_type_code;
	}
	public void setPasschg_type_code(String passchg_type_code) {
		this.passchg_type_code = passchg_type_code;
	}
	public String getPasschg_type_name() {
		return passchg_type_name;
	}
	public void setPasschg_type_name(String passchg_type_name) {
		this.passchg_type_name = passchg_type_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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
