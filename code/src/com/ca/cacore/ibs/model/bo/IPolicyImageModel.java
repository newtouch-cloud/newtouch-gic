package com.ca.cacore.ibs.model.bo;

import java.util.Date;

public interface IPolicyImageModel {

	public  Integer getSeq_id();

	public  void setSeq_id(Integer seq_id);

	public  String getFile_id();

	public  void setFile_id(String file_id);

	public  String getBus_type();

	public  void setBus_type(String bus_type);

	public String getSend_code();
	
	public void setSend_code(String send_code);

	public  Long getPolicy_id();

	public  void setPolicy_id(Long policy_id);

	public  Integer getSeq_num();

	public  void setSeq_num(Integer seq_num);

	public  Date getScan_time();

	public  void setScan_time(Date scan_time);

	public  String getFile_path();

	public  void setFile_path(String file_path);

	public  String getFile_name();

	public  void setFile_name(String file_name);

	public  String getRemark();

	public  void setRemark(String remark);

	public  String getCreateUser();

	public  void setCreateUser(String createUser);

	public  Date getCreateDate();

	public  void setCreateDate(Date createDate);

	public  String getModifyUser();

	public  void setModifyUser(String modifyUser);

	public  Date getModifyDate();

	public  void setModifyDate(Date modifyDate);
	
	public String getInsBranch_id();
	
	public void setInsBranch_id(String insBranch_id);

	public String getPolicy_code();

	public void setPolicy_code(String policy_code);

	public String getApp_name();

	public void setApp_name(String app_name);
	
	public String getBranch_name();
	
	public void setBranch_name(String branch_name);
	
	public String getBranch_id();
	
	public void setBranch_id(String branch_id);
	
	public String[] getFile_ids();
	
	public void setFile_ids(String[] file_ids);

}