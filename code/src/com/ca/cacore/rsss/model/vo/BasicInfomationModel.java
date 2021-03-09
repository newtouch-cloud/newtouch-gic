package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class BasicInfomationModel implements IBasicInfomationModel{
	
	
	private String branch_id; //中介公司id
	private String branch_level;//公司机构层级
	private String baseinfo;//基本情况
	private Integer baseinfo_value;//基本情况对应的值
	private String term;//期次
	private Date entry_date1;//入职时间从
	private Date entry_date2;//至
	private Date end_date1;//离职时间从
	private Date end_date2;//至
	private Date sys_date1;//系统时间从
	private Date sys_date2;//至
	private int numbers;//公司现有人数（1）
	private int compared;//同比（+/-）（2）
	private int is_practice;//总持证数（3）
	private int gleaderNum;//高级管理人员人数（4）
	private int gleaderIsPracticeNum;//高级管理人员持证数（5）
	private int businessNum;//业务人员人数（6）
	private int businessIsPracticeNum;//业务人员持证数（7）
	private int noBusinessNum;//非业务人员人数（8）
	private int noBusinessIsPracticeNum;//非业务人员持证数（9）
	private int policyNum;//保单件数（10）
	private Integer orders;//排序
	private String dept_list; //权限
    
    
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_level() {
		return branch_level;
	}
	public void setBranch_level(String branch_level) {
		this.branch_level = branch_level;
	}
	public String getBaseinfo() {
		return baseinfo;
	}
	public void setBaseinfo(String baseinfo) {
		this.baseinfo = baseinfo;
	}
	public Integer getBaseinfo_value() {
		return baseinfo_value;
	}
	public void setBaseinfo_value(Integer baseinfo_value) {
		this.baseinfo_value = baseinfo_value;
	}
	
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	public int getCompared() {
		return compared;
	}
	public void setCompared(int compared) {
		this.compared = compared;
	}
	public int getIs_practice() {
		return is_practice;
	}
	public void setIs_practice(int is_practice) {
		this.is_practice = is_practice;
	}
	public int getGleaderNum() {
		return gleaderNum;
	}
	public void setGleaderNum(int gleaderNum) {
		this.gleaderNum = gleaderNum;
	}
	public int getGleaderIsPracticeNum() {
		return gleaderIsPracticeNum;
	}
	public void setGleaderIsPracticeNum(int gleaderIsPracticeNum) {
		this.gleaderIsPracticeNum = gleaderIsPracticeNum;
	}
	public int getBusinessNum() {
		return businessNum;
	}
	public void setBusinessNum(int businessNum) {
		this.businessNum = businessNum;
	}
	public int getBusinessIsPracticeNum() {
		return businessIsPracticeNum;
	}
	public void setBusinessIsPracticeNum(int businessIsPracticeNum) {
		this.businessIsPracticeNum = businessIsPracticeNum;
	}
	public int getNoBusinessNum() {
		return noBusinessNum;
	}
	public void setNoBusinessNum(int noBusinessNum) {
		this.noBusinessNum = noBusinessNum;
	}
	public int getNoBusinessIsPracticeNum() {
		return noBusinessIsPracticeNum;
	}
	public void setNoBusinessIsPracticeNum(int noBusinessIsPracticeNum) {
		this.noBusinessIsPracticeNum = noBusinessIsPracticeNum;
	}
	public int getPolicyNum() {
		return policyNum;
	}
	public void setPolicyNum(int policyNum) {
		this.policyNum = policyNum;
	}
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Date getEntry_date1() {
		return entry_date1;
	}
	public void setEntry_date1(Date entry_date1) {
		this.entry_date1 = entry_date1;
	}
	public Date getEntry_date2() {
		return entry_date2;
	}
	public void setEntry_date2(Date entry_date2) {
		this.entry_date2 = entry_date2;
	}
	public Date getEnd_date1() {
		return end_date1;
	}
	public void setEnd_date1(Date end_date1) {
		this.end_date1 = end_date1;
	}
	public Date getEnd_date2() {
		return end_date2;
	}
	public void setEnd_date2(Date end_date2) {
		this.end_date2 = end_date2;
	}
	public Date getSys_date1() {
		return sys_date1;
	}
	public void setSys_date1(Date sys_date1) {
		this.sys_date1 = sys_date1;
	}
	public Date getSys_date2() {
		return sys_date2;
	}
	public void setSys_date2(Date sys_date2) {
		this.sys_date2 = sys_date2;
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
	
	public String getDept_list() {
		return dept_list;
	}
	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}
    
}
