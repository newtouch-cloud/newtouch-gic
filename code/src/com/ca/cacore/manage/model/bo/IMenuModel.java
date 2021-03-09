package com.ca.cacore.manage.model.bo;

import java.sql.Date;

/**
* @since:    2014年2月18日   
* @author    ss
* @description:
*/
public interface IMenuModel {
	public String getSerno() ;
	public void setSerno(String serno) ;
	public String getMenu_no() ;
	public void setMenu_no(String menu_no) ;
	public String getMenu_name() ;
	public void setMenu_name(String menu_name) ;
	public String getMenu_url() ;
	public void setMenu_url(String menu_url) ;
	public String getParent_no() ;
	public void setParent_no(String parent_no) ;
	public String getMenu_order() ;
	public void setMenu_order(String menu_order) ;
	public String getMenu_type() ;
	public void setMenu_type(String menu_type) ;
	public String getMenu_status() ;
	public void setMenu_status(String menu_status) ;
	public String getMenu_seq() ;
	public void setMenu_seq(String menu_seq) ;
	public Date getCrt_date() ;
	public void setCrt_date(Date crt_date) ;
	public Date getMdf_date() ;
	public void setMdf_date(Date mdf_date) ;
	public String getCrt_user() ;
	public void setCrt_user(String crt_user) ;
	public String getMdf_user() ;
	public void setMdf_user(String mdf_user) ;
	public String getData_flag() ;
	public void setData_flag(String data_flag) ;
	public String getPatch_memo() ;
	public void setPatch_memo(String patch_memo) ;
}
