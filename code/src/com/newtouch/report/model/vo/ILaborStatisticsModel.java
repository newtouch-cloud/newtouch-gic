package com.newtouch.report.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;



public interface ILaborStatisticsModel extends IPageCount{
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name();
	public void setBranch_name(String branch_name);
	public String getWork_nature();
	public void setWork_nature(String work_nature);
	public String getWork_relation();
	public void setWork_relation(String work_relation);
	public Date getEntry_date1();
	public void setEntry_date1(Date entry_date1);
	public Date getEntry_date2();
	public void setEntry_date2(Date entry_date2);
	public Date getEnd_date1();
	public void setEnd_date1(Date end_date1);
	public Date getEnd_date2();
	public void setEnd_date2(Date end_date2);
	public Date getSys_date1();
	public void setSys_date1(Date sys_date1);
	public Date getSys_date2();
	public void setSys_date2(Date sys_date2);
	public Integer getNumbers();
	public void setNumbers(Integer numbers);
	public Integer getWomannum();
	public void setWomannum(Integer womannum);
	public Integer getPartynum();
	public void setPartynum(Integer partynum);
	public Integer getAge1();
	public void setAge1(Integer age1);
	public Integer getAge2();
	public void setAge2(Integer age2);
	public Integer getAge3();
	public void setAge3(Integer age3);
	public Integer getEducation();
	public void setEducation(Integer education);
	public String getIs_practice();
	public void setIs_practice(String is_practice);
	public String getNo_practice();
	public void setNo_practice(String no_practice);
	public String getDept_list();
	public void setDept_list(String dept_list);

	
}
