package com.ca.cacore.manage.model.bo;

import java.sql.Date;

/**
 * 菜单打开方式信息
 * @author admin
 *
 */
public class FuncMenuOpenTypeModel  {
	private Integer seq_id		;        //主键
	private String opentype_code;        //打开方式代码		             
	private String opentype_name;        //打开方式名称		             
	private String remark		;        //备注		             
	private String createUser	;	     //创建人	             
	private Date createDate	;        	//创建时间	             
	private String modifyUser	;        	//最后修改人	             
	private Date modifyDate	;         //最后修改时间	

	public FuncMenuOpenTypeModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}
	public FuncMenuOpenTypeModel(){}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getOpentype_code() {
		return opentype_code;
	}
	public void setOpentype_code(String opentype_code) {
		this.opentype_code = opentype_code;
	}
	public String getOpentype_name() {
		return opentype_name;
	}
	public void setOpentype_name(String opentype_name) {
		this.opentype_name = opentype_name;
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
