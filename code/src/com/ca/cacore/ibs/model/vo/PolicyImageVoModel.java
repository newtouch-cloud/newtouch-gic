package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 2013-12-23
 * 保单管理-->保单影像管理-->保单影像件查询vo类
 * @author ZhangChen
 *
 */

public class PolicyImageVoModel implements IPolicyImageVoModel {
	
	private Integer seq_id; // 序号  主表 cbs_contractlife
	private String branch_id; // 机构代码 表-->SYS_Branch
	private String branch_name;// 机构姓名
	private String insBranch_id; // 保险公司机构
	private String insbranch_name;//保险公司机构名称
	private String product_id;// 险种代码
	private String product_name;// 险种名称
	private Long policy_id; // 保单号
	private String policy_code; // 保单号
	private String send_code;//投保单号码
	private String agent_name; // 销售人员姓名
	private String service_name; // 保单服务人员
	private String app_name;// 投保人姓名
	private String insurant_name; // 主被保人 表-->CBS_Customer
	private Date hold_date;// 投保日期
	private Date validate_date; // 保单生效日期
	private Date scan_time;    // 影像件上传日期  
	private String bus_type; //影像件上传类型
	private String status_name; // 保单的状态
	private String hasImage;// 是否有管理的影像件
	private String emp_id;//用户empid
	private PageCount pageCount = new PageCount();  //分页
	
	
	@Override
	public Integer getSeq_id() {
		return seq_id;
	}
	
	@Override
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	
	@Override
	public String getBranch_id() {
		return branch_id;
	}
	
	@Override
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	
	@Override
	public String getBranch_name() {
		return branch_name;
	}
	
	@Override
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	
	@Override
	public String getInsBranch_id() {
		return insBranch_id;
	}
	
	@Override
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
		
	@Override
	public String getInsbranch_name() {
		return insbranch_name;
	}
	
	@Override
	public void setInsbranch_name(String insbranch_name) {
		this.insbranch_name = insbranch_name;
	}

	@Override
	public String getProduct_id() {
		return product_id;
	}
	
	@Override
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	@Override
	public String getProduct_name() {
		return product_name;
	}
	
	@Override
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	
	@Override
	public Long getPolicy_id() {
		return policy_id;
	}
	@Override
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}

	@Override
	public String getPolicy_code() {
		return policy_code;
	}
	
	@Override
	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}
	
	@Override
	public String getAgent_name() {
		return agent_name;
	}
	
	@Override
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	
	@Override
	public String getService_name() {
		return service_name;
	}
	
	@Override
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	
	@Override
	public String getApp_name() {
		return app_name;
	}
	
	@Override
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}	
	
	@Override
	public String getInsurant_name() {
		return insurant_name;
	}
	
	@Override
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}

	@Override
	public Date getHold_date() {
		return hold_date;
	}
	
	@Override
	public void setHold_date(Date hold_date) {
		this.hold_date = hold_date;
	}
	
	@Override
	public Date getValidate_date() {
		return validate_date;
	}
	
	@Override
	public void setValidate_date(Date validate_date) {
		this.validate_date = validate_date;
	}
	
	@Override
	public Date getScan_time() {
		return scan_time;
	}
	
	@Override
	public void setScan_time(Date scan_time) {
		this.scan_time = scan_time;
	}
	
	@Override
	public String getStatus_name() {
		return status_name;
	}

	@Override
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	@Override
	public PageCount getPageCount() {
		return this.pageCount;
	}

	@Override
	public void setPageCount(PageCount pageCount) {
		this.pageCount=pageCount;
	}

	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}

	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}

	public String getBus_type() {
		return bus_type;
	}

	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}

	public String getHasImage() {
		return hasImage;
	}

	public void setHasImage(String hasImage) {
		this.hasImage = hasImage;
	}

	public String getSend_code() {
		return send_code;
	}

	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
	


}
