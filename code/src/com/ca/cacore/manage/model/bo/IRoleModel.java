package com.ca.cacore.manage.model.bo;

import java.util.Date;

import com.newtouch.core.model.IPageCount;

/**
 * 角色基本信息
 */
public interface IRoleModel  extends IPageCount{ 
	public String getRole_type() ;
	public void setRole_type(String role_type);
	public String getRole_id() ;
	public void setRole_id(String role_id);
	public String getRole_name() ;
	public void setRole_name(String role_name) ;
	public String getStatus() ;
	public void setStatus(String status);
	public String getRemark();
	public void setRemark(String remark) ;
	public String getCreateUser();
	public void setCreateUser(String createUser) ;
	public String getCreateDate() ;
	public void setCreateDate(Date createDate) ;
	public String getModifyUser() ;
	public void setModifyUser(String modifyUser) ;
	public String getModifyDate() ;
	public void setModifyDate(Date modifyDate) ;
	public void setSeq_id(Integer seq_id);
	public Integer getSeq_id() ;
}
