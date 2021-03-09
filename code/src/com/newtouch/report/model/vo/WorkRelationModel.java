package com.newtouch.report.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class WorkRelationModel implements IWorkRelationModel{
	
	private String branch_id;//机构id
	private String branch_name;//机构名称
	private String branch_level;//机构层级
	private String work_relation;//用工性质
	private Date entry_date1;//入职时间从
	private Date entry_date2;//至
	private Date end_date1;//离职时间从
	private Date end_date2;//至
	private Date sys_date1;//系统时间从
	private Date sys_date2;//至
	private String dept_list;

	private Integer numA1;
	private Integer numA2;
	private Integer numB1;
	private Integer numC1;
	private Integer numC2;
	private Integer numC3;
	private Integer numC4;
	private Integer numC5;
	private Integer numC6;
	private Integer numC7;
	private Integer num;
	

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
	public Integer getNumA1() {
		return numA1;
	}
	public void setNumA1(Integer numA1) {
		this.numA1 = numA1;
	}
	public Integer getNumA2() {
		return numA2;
	}
	public void setNumA2(Integer numA2) {
		this.numA2 = numA2;
	}
	public Integer getNumB1() {
		return numB1;
	}
	public void setNumB1(Integer numB1) {
		this.numB1 = numB1;
	}
	public Integer getNumC1() {
		return numC1;
	}
	public void setNumC1(Integer numC1) {
		this.numC1 = numC1;
	}
	public Integer getNumC2() {
		return numC2;
	}
	public void setNumC2(Integer numC2) {
		this.numC2 = numC2;
	}
	public Integer getNumC3() {
		return numC3;
	}
	public void setNumC3(Integer numC3) {
		this.numC3 = numC3;
	}
	public Integer getNumC4() {
		return numC4;
	}
	public void setNumC4(Integer numC4) {
		this.numC4 = numC4;
	}
	public Integer getNumC5() {
		return numC5;
	}
	public void setNumC5(Integer numC5) {
		this.numC5 = numC5;
	}
	public Integer getNumC6() {
		return numC6;
	}
	public void setNumC6(Integer numC6) {
		this.numC6 = numC6;
	}
	public Integer getNumC7() {
		return numC7;
	}
	public void setNumC7(Integer numC7) {
		this.numC7 = numC7;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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

	public String getBranch_level() {
		return branch_level;
	}
	public void setBranch_level(String branch_level) {
		this.branch_level = branch_level;
	}
	
	
}
