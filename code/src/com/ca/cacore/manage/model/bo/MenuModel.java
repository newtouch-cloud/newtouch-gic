package com.ca.cacore.manage.model.bo;

import java.sql.Date;

/**
* @since:    2014年2月18日   
* @author    ss
* @description:
*/
public class MenuModel implements IMenuModel {
	private String serno;//serno
	private String menu_no;//菜单编号
	private String menu_name;//菜单名称
	private String menu_url;//菜单事件
	private String parent_no;//上级菜单
	private String menu_order;//菜单排序
	private String menu_type;//菜单类型
	private String menu_status;//菜单状态
	private String menu_seq;//菜单序列
	private Date crt_date;//创建日期
	private Date mdf_date;//修改日期
	private String crt_user;//创建操作员
	private String mdf_user;//修改操作员
	private String data_flag;//有效性标志
	private String patch_memo;//数补备注
	
	public MenuModel() {
	}
	public MenuModel(String serno) {
		this.serno = serno;
	}
	
	public String getSerno() {
		return serno;
	}
	public void setSerno(String serno) {
		this.serno = serno;
	}
	public String getMenu_no() {
		return menu_no;
	}
	public void setMenu_no(String menu_no) {
		this.menu_no = menu_no;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getParent_no() {
		return parent_no;
	}
	public void setParent_no(String parent_no) {
		this.parent_no = parent_no;
	}
	public String getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(String menu_order) {
		this.menu_order = menu_order;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getMenu_status() {
		return menu_status;
	}
	public void setMenu_status(String menu_status) {
		this.menu_status = menu_status;
	}
	public String getMenu_seq() {
		return menu_seq;
	}
	public void setMenu_seq(String menu_seq) {
		this.menu_seq = menu_seq;
	}
	public Date getCrt_date() {
		return crt_date;
	}
	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}
	public Date getMdf_date() {
		return mdf_date;
	}
	public void setMdf_date(Date mdf_date) {
		this.mdf_date = mdf_date;
	}
	public String getCrt_user() {
		return crt_user;
	}
	public void setCrt_user(String crt_user) {
		this.crt_user = crt_user;
	}
	public String getMdf_user() {
		return mdf_user;
	}
	public void setMdf_user(String mdf_user) {
		this.mdf_user = mdf_user;
	}
	public String getData_flag() {
		return data_flag;
	}
	public void setData_flag(String data_flag) {
		this.data_flag = data_flag;
	}
	public String getPatch_memo() {
		return patch_memo;
	}
	public void setPatch_memo(String patch_memo) {
		this.patch_memo = patch_memo;
	}
}
