package com.ca.cacore.msss.model.vo;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class InsRncDfnVOModel implements IInsRncDfnVOModel {
	private String classCode;
	private String className;
	private String riskCode;
	private String riskName;
	private String status;
	private String status_name;
	private String rtype;
	private String product_source;//产品来源
	private String riskCodeOrign;//原来险种代码（修改产品）
	
	private PageCount pageCount=new PageCount();
	
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getRtype() {
		return rtype;
	}
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	
	public String getProduct_source() {
		return product_source;
	}
	public void setProduct_source(String product_source) {
		this.product_source = product_source;
	}
	public String getRiskCodeOrign() {
		return riskCodeOrign;
	}
	public void setRiskCodeOrign(String riskCodeOrign) {
		this.riskCodeOrign = riskCodeOrign;
	}
	
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	
	
}
