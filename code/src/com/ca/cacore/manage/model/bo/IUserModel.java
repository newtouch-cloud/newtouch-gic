package com.ca.cacore.manage.model.bo;

import java.util.Date;

import com.newtouch.core.model.IPageCount;

/**
 * 操作用户基本信息
 *
 */
public interface IUserModel extends IPageCount{ 
	public Integer getSeq_id() ;
	public void setSeq_id(Integer seq_id);
	public String getBranch_id() ;
	public void setBranch_id(String branch_id) ;
	public String getUser_type() ;
	public void setUser_type(String user_type) ;
	public void setUser_typeName(String user_typeNme);
	public String getUser_typeName();
	public String getUserName();
	public void setUserName(String userName);
	public String getPassword();
	public void setPassword(String password);
	public String getName();
	public void setName(String name);
	public String getSex_code();
	public void setSex_code(String sex_code) ;
	public String getMobile() ;
	public void setMobile(String mobile) ;
	public String getFixed_line();
	public void setFixed_line(String fixed_line);
	public String getEmail() ;
	public void setEmail(String email) ;
	public String getEmp_id();
	public void setEmp_id(String emp_id);
	public String getJob_tel();
	public void setJob_tel(String job_tel);
	public String getJob_address();
	public void setJob_address(String job_address);
	public String getStatus();
	public void setStatus(String status);
	public String getRemark();
	public void setRemark(String remark);
	public String getCreateUser() ;
	public void setCreateUser(String createUser);
	public Date getCreateDate();
	public void setCreateDate(Date createDate);
	public String getModifyUser();
	public void setModifyUser(String modifyUser);
	public Date getModifyDate();
	public void setModifyDate(Date modifyDate);
	public String getPortraitPath();
	public void setPortraitPath(String portraitPath);
	public String getCore_usercode();
	public void setCore_usercode(String core_usercode);
	public String getCore_password();
	public void setCore_password(String core_password);
	public String getDept_list();
	public void setDept_list(String dept_list);
}
