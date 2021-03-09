package com.newtouch.report.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class LaborStatisticsModel implements ILaborStatisticsModel{
	

	private String branch_id;//中介公司机构
	private String branch_name;//机构名称
	private String work_nature;//合同类型
	private String work_relation;//用工性质
	private Date entry_date1;//入职时间从
	private Date entry_date2;//至
	private Date end_date1;//离职时间从
	private Date end_date2;//至
	private Date sys_date1;//系统时间从
	private Date sys_date2;//至
	private Integer numbers;//总人数
	private Integer womannum;//女性人数
	private Integer partynum;//党员人数
	private Integer age1;//35岁及以下
	private Integer age2;//男60岁及以上
	private Integer age3;//女55岁及以上
	private Integer education;//大学本科及以上
	private String is_practice ;//持证人数
	private String no_practice;//未持证人数
	private String dept_list;//权限
	
	
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
	public String getWork_nature() {
		return work_nature;
	}
	public void setWork_nature(String work_nature) {
		this.work_nature = work_nature;
	}
	public String getWork_relation() {
		return work_relation;
	}
	public void setWork_relation(String work_relation) {
		this.work_relation = work_relation;
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
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	public Integer getWomannum() {
		return womannum;
	}
	public void setWomannum(Integer womannum) {
		this.womannum = womannum;
	}
	public Integer getPartynum() {
		return partynum;
	}
	public void setPartynum(Integer partynum) {
		this.partynum = partynum;
	}
	public Integer getAge1() {
		return age1;
	}
	public void setAge1(Integer age1) {
		this.age1 = age1;
	}
	public Integer getAge2() {
		return age2;
	}
	public void setAge2(Integer age2) {
		this.age2 = age2;
	}
	public Integer getAge3() {
		return age3;
	}
	public void setAge3(Integer age3) {
		this.age3 = age3;
	}
	public Integer getEducation() {
		return education;
	}
	public void setEducation(Integer education) {
		this.education = education;
	}
	public String getIs_practice() {
		return is_practice;
	}
	public void setIs_practice(String is_practice) {
		this.is_practice = is_practice;
	}
	public String getNo_practice() {
		return no_practice;
	}
	public void setNo_practice(String no_practice) {
		this.no_practice = no_practice;
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
