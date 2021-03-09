package com.newtouch.report.model.vo;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class BusinessTypeModel implements IBusinessTypeModel{
	
	private String branch_id;//中介公司机构id 
	private String branch_name;//中介公司机构名称
	private String branch_level;//机构层级
	private String term;//期次
	private String business_flag;//业务类型
	private String u_month_count;//核保口径-本月-保单数量
	private String u_month_premium;//核保口径-本月-代理保费
	private String u_month_fee;//核保口径-本月-跟单手续费
	private String u_month_paidfee;//核保口径-本月-财务已结算手续费
	private String u_year_count;//核保口径-本年-保单数量
	private String u_year_premium;//核保口径-本年-代理保费
	private String u_year_fee;//核保口径-本年-跟单手续费
	private String u_year_paidfee;//核保口径-本年-财务已结算手续费
	
	private String dept_list;//权限
	private String empId;//用户id
	
	
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
	public String getBranch_level() {
		return branch_level;
	}
	public void setBranch_level(String branch_level) {
		this.branch_level = branch_level;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getBusiness_flag() {
		return business_flag;
	}
	public void setBusiness_flag(String business_flag) {
		this.business_flag = business_flag;
	}
	public String getU_month_count() {
		return u_month_count;
	}
	public void setU_month_count(String u_month_count) {
		this.u_month_count = u_month_count;
	}
	public String getU_month_premium() {
		return u_month_premium;
	}
	public void setU_month_premium(String u_month_premium) {
		this.u_month_premium = u_month_premium;
	}
	public String getU_month_fee() {
		return u_month_fee;
	}
	public void setU_month_fee(String u_month_fee) {
		this.u_month_fee = u_month_fee;
	}
	public String getU_month_paidfee() {
		return u_month_paidfee;
	}
	public void setU_month_paidfee(String u_month_paidfee) {
		this.u_month_paidfee = u_month_paidfee;
	}
	public String getU_year_count() {
		return u_year_count;
	}
	public void setU_year_count(String u_year_count) {
		this.u_year_count = u_year_count;
	}
	public String getU_year_premium() {
		return u_year_premium;
	}
	public void setU_year_premium(String u_year_premium) {
		this.u_year_premium = u_year_premium;
	}
	public String getU_year_fee() {
		return u_year_fee;
	}
	public void setU_year_fee(String u_year_fee) {
		this.u_year_fee = u_year_fee;
	}
	public String getU_year_paidfee() {
		return u_year_paidfee;
	}
	public void setU_year_paidfee(String u_year_paidfee) {
		this.u_year_paidfee = u_year_paidfee;
	}
	public String getDept_list() {
		return dept_list;
	}
	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}


	private PageCount pageCount = new PageCount();
	@Override
	public PageCount getPageCount() {
		// TODO Auto-generated method stub
		return pageCount;
	}
	@Override
	public void setPageCount(PageCount pageCount) {
		// TODO Auto-generated method stub
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		// TODO Auto-generated method stub
		return this.getPageCount().getLimit();
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
}
