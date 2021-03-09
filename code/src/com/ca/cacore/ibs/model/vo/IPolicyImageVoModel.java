package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

/**
 * 2013-12-23  
 * 保单管理-->保单影像管理-->保单影像件查询vo类
 * @author ZhangChen
 *
 */

public interface IPolicyImageVoModel extends IPageCount {

	public  Integer getSeq_id();

	public  void setSeq_id(Integer seq_id);

	public  String getBranch_id();

	public  void setBranch_id(String branch_id);

	public  String getBranch_name();

	public  void setBranch_name(String branch_name);

	public  String getInsBranch_id();

	public  void setInsBranch_id(String insBranch_id);
	
	public String getInsbranch_name();
	
	public void setInsbranch_name(String insbranch_name);

	public  String getProduct_id();

	public  void setProduct_id(String product_id);

	public  String getProduct_name();

	public  void setProduct_name(String product_name);

	public Long getPolicy_id();
	
	public void setPolicy_id(Long policy_id);

	public  String getPolicy_code();

	public  void setPolicy_code(String policy_code);

	public  String getAgent_name();

	public  void setAgent_name(String agent_name);

	public  String getService_name();

	public  void setService_name(String service_name);

	public  String getApp_name();

	public  void setApp_name(String app_name);

	public String getInsurant_name();
	
	public void setInsurant_name(String insurant_name);

	public  Date getHold_date();

	public  void setHold_date(Date hold_date);

	public  Date getValidate_date();

	public  void setValidate_date(Date validate_date);

	public Date getScan_time();
	
	public void setScan_time(Date scan_time);
	
	public String getBus_type();

	public void setBus_type(String bus_type);

	public  String getStatus_name();

	public  void setStatus_name(String status_name);
	
	public String getHasImage();

	public void setHasImage(String hasImage);
	
	public String getSend_code();

	public void setSend_code(String send_code);
	
	public String getEmp_id();

	public void setEmp_id(String emp_id);

}