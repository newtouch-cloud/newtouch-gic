package com.ca.cacore.manage.model.bo;

import java.util.Date;

import com.newtouch.core.model.IPageCount;

/**
 * 用户权限信息
 */
	public interface IUserAuthsModel extends IPageCount{ 
		public Integer getSeq_id();
		public void setSeq_id(Integer seq_id) ;
		public String getUserName();
		public void setUserName(String userName);
		public String getBranch_id();
		public void setBranch_id(String branch_id);
		public String getRole_id();
		public void setRole_id(String role_id) ;
		public String getStatus() ;
		public void setStatus(String status) ;
		public String getRemark();
		public void setRemark(String remark);
		public String getCreateUser() ;
		public void setCreateUser(String createUser);
		public Date getCreateDate();
		public void setCreateDate(Date createDate);
		public String getModifyUser() ;
		public void setModifyUser(String modifyUser);
		public Date getModifyDate();
		public void setModifyDate(Date modifyDate);
	}
