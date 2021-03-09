package com.ca.cacore.manage.model.vo;

import java.sql.Date;

public class FuncButtonVOModel implements IFuncButtonVOModel{

	private Integer seq_id; // 主键
	private String menu_id;// 菜单代码
	private String button_type;// 按钮类型
	private String button_id;// 按钮代码
	private String button_name;// 按钮名称
	private String status;// 状态
	private String remark;// 备注
	private String isAuth;//是否授权
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	
	public FuncButtonVOModel(){}
	public FuncButtonVOModel(String menu_id){
		this.setMenu_id(menu_id);
	}
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getButton_type() {
		return button_type;
	}
	public void setButton_type(String button_type) {
		this.button_type = button_type;
	}
	public String getButton_id() {
		return button_id;
	}
	public void setButton_id(String button_id) {
		this.button_id = button_id;
	}
	public String getButton_name() {
		return button_name;
	}
	public void setButton_name(String button_name) {
		this.button_name = button_name;
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
	public String getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
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
