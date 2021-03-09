package com.ca.cacore.manage.model.bo;

import java.sql.Date;

public interface IRoleAuthsModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getRole_id();

	public void setRole_id(String role_id);

	public String getMenu_id();

	public void setMenu_id(String menu_id);

	public String getButton_id();

	public void setButton_id(String button_id);

	public String getStatus();

	public void setStatus(String status);

	public String getRemark();

	public void setRemark(String remark);

	public String getCreateUser();

	public void setCreateUser(String createUser);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getModifyUser();

	public void setModifyUser(String modifyUser);

	public Date getModifyDate();

	public void setModifyDate(Date modifyDate);

}