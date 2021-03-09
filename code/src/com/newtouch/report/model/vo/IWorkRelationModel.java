package com.newtouch.report.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IWorkRelationModel extends IPageCount{
	
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name();
	public void setBranch_name(String branch_name) ;
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
	public Integer getNumA1();
	public void setNumA1(Integer numA1);
	public Integer getNumA2();
	public void setNumA2(Integer numA2);
	public Integer getNumB1();
	public void setNumB1(Integer numB1);
	public Integer getNumC1();
	public void setNumC1(Integer numC1);
	public Integer getNumC2();
	public void setNumC2(Integer numC2);
	public Integer getNumC3();
	public void setNumC3(Integer numC3);
	public Integer getNumC4();
	public void setNumC4(Integer numC4);
	public Integer getNumC5();
	public void setNumC5(Integer numC5);
	public Integer getNumC6();
	public void setNumC6(Integer numC6);
	public Integer getNumC7();
	public void setNumC7(Integer numC7);
	public Integer getNum();
	public void setNum(Integer num);
	public String getBranch_level();
	public void setBranch_level(String branch_level);
	public String getDept_list();
	public void setDept_list(String dept_list);
	

}
