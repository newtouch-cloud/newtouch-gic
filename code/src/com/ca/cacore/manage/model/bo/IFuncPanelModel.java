package com.ca.cacore.manage.model.bo;

import java.sql.Date;

public interface IFuncPanelModel {
	public Integer getSeq_id() ;
	public void setSeq_id(Integer seq_id) ;
	public String getEmp_id() ;
	public void setEmp_id(String emp_id) ;
	public Integer getBelong_row() ;
	public void setBelong_row(Integer belong_row) ;
	public Integer getBelong_column() ;
	public void setBelong_column(Integer belong_column) ;
	public Integer getBelong_zone() ;
	public void setBelong_zone(Integer belong_zone) ;
	public String getStyle() ;
	public void setStyle(String style) ;
	public String getLink_url() ;
	public void setLink_url(String link_url) ;
	public String getFunc_name() ;
	public void setFunc_name(String func_name) ;
	public String getIs_last() ;
	public void setIs_last(String is_last) ;
	public String getCreateUser() ;
	public void setCreateUser(String createUser) ;
	public Date getCreateDate() ;
	public void setCreateDate(Date createDate) ;
	public String getModifyUser() ;
	public void setModifyUser(String modifyUser) ;
	public Date getModifyDate() ;
	public void setModifyDate(Date modifyDate) ;
}
