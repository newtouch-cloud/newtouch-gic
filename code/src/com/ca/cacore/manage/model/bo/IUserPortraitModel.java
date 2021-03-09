package com.ca.cacore.manage.model.bo;

import java.util.Date;

public interface IUserPortraitModel {

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getFile_id();

	public abstract void setFile_id(String file_id);

	public abstract String getEmp_id();

	public abstract void setEmp_id(String emp_id);

	public abstract Date getScan_time();

	public abstract void setScan_time(Date scan_time);

	public abstract String getFile_path();

	public abstract void setFile_path(String file_path);

	public abstract String getFile_name();

	public abstract void setFile_name(String file_name);

	public abstract String getRemark();

	public abstract void setRemark(String remark);

	public abstract String getCreateUser();

	public abstract void setCreateUser(String createUser);

	public abstract Date getCreateDate();

	public abstract void setCreateDate(Date createDate);

	public abstract String getModifyUser();

	public abstract void setModifyUser(String modifyUser);

	public abstract Date getModifyDate();

	public abstract void setModifyDate(Date modifyDate);

}