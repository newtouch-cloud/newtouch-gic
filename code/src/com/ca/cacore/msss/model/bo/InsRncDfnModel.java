package com.ca.cacore.msss.model.bo;


import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 
 * @author Wang_ds
 * @since 2013-12-4
 * @description  INF_INSRNC_DFN  
 */
public class InsRncDfnModel implements IInsRncDfnModel  {
	
	private String classCode;//险类编码
	private String className;//险类名称
	private String riskCode;//险种编码
	private String riskName;//险种名称
	private String status;//状态
	private String status_name;//状态名
	private String rtype;//保监会分类
	private String product_source;//产品来源
	private Integer seq_id;
	private Date   modifydate;
	private String product_code;//产品编码  --不要
	private String product_name;//产品名称  --不要
	private String branch_id;//保险公司编码
	private String branch_name;//保险公司名称
	private String type_code;//险别编码  --不要
	private String type_name;//险别名称  --不要
	private String poundage;
	private String parname;//所属保险公司名称（一级） by zdd02 20190617
	private String parcode;//所属保险公司编码（一级） by zdd02 20190617
	private String org_level;//等级by zdd02 20190617
	private String modifyUser; //by zdd02 20190620
	private String bjtype;//保监统计分类  //zddxiu
	private String bjtypename;//保监统计分类  //zddxiu
	private String flag;//判断险类编码和险类名称是否为空的状态  1-不为空   add by lyn20190911
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getPoundage() {
		return poundage;
	}
	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
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
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	
	public String getParname() {
		return parname;
	}
	public void setParname(String parname) {
		this.parname = parname;
	}
	public String getParcode() {
		return parcode;
	}
	public void setParcode(String parcode) {
		this.parcode = parcode;
	}
	
	public String getOrg_level() {
		return org_level;
	}
	public void setOrg_level(String org_level) {
		this.org_level = org_level;
	}
	
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	
	public String getBjtype() {
		return bjtype;
	}
	public void setBjtype(String bjtype) {
		this.bjtype = bjtype;
	}
	@Override
	public String getBjtypename() {
		return bjtypename;
	}
	@Override
	public void setBjtypename(String bjtypename) {
		this.bjtypename = bjtypename;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "InsRncDfnModel [classCode=" + classCode + ", className=" + className + ", riskCode=" + riskCode
				+ ", riskName=" + riskName + ", status=" + status + ", status_name=" + status_name + ", rtype=" + rtype
				+ ", product_source=" + product_source + ", seq_id=" + seq_id + ", modifydate=" + modifydate
				+ ", product_code=" + product_code + ", product_name=" + product_name + ", branch_id=" + branch_id
				+ ", branch_name=" + branch_name + ", type_code=" + type_code + ", type_name=" + type_name
				+ ", poundage=" + poundage + ", pageCount=" + pageCount + "]";
	}
	
}
