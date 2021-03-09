package com.ca.cacore.msss.model.vo;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IInsRncDfnVOModel {

	public String getClassCode();

	public void setClassCode(String classCode);

	public String getClassName();

	public void setClassName(String className);

	public String getRiskCode();

	public void setRiskCode(String riskCode);

	public String getRiskName();

	public void setRiskName(String riskName);

	public String getStatus();

	public void setStatus(String status);

	public String getStatus_name();

	public void setStatus_name(String status_name);

	public String getRtype();

	public void setRtype(String rtype);

	public String getProduct_source();

	public void setProduct_source(String product_source);

	public String getRiskCodeOrign();

	public void setRiskCodeOrign(String riskCodeOrign);

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

	public int getStart();

	public int getLimit();

}