package com.ca.cacore.manage.model.vo;

import java.sql.Date;

public interface IFuncButtonVOModel {

	public Integer getSeq_id();
	public void setSeq_id(Integer seq_id);
	public String getMenu_id() ;
	public void setMenu_id(String menu_id) ;
	public String getButton_type() ;
	public void setButton_type(String button_type) ;
	public String getButton_id() ;
	public void setButton_id(String button_id) ;
	public String getButton_name() ;
	public void setButton_name(String button_name) ;
	public String getStatus() ;
	public void setStatus(String status) ;
	public String getRemark() ;
	public void setRemark(String remark) ;
	public String getIsAuth() ;
	public void setIsAuth(String isAuth) ;
	public String getCreateUser() ;
	public void setCreateUser(String createUser) ;
	public Date getCreateDate();
	public void setCreateDate(Date createDate) ;
	public String getModifyUser();
	public void setModifyUser(String modifyUser) ;
	public Date getModifyDate() ;
	public void setModifyDate(Date modifyDate);
}
