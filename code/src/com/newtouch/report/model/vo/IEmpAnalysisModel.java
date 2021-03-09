package com.newtouch.report.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IEmpAnalysisModel extends IPageCount{

	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name();
	public void setBranch_name(String branch_name);
	public String getPerson_no();
	public void setPerson_no(String person_no);
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
	public String getWork_relation();
	public void setWork_relation(String work_relation);
	public Integer getNum1();
	public void setNum1(Integer num1);
	public Integer getNum2();
	public void setNum2(Integer num2);
	public Integer getNum3();
	public void setNum3(Integer num3);
	public Integer getNum4();
	public void setNum4(Integer num4);
	public Integer getNum5();
	public void setNum5(Integer num5);
	public Integer getNum6();
	public void setNum6(Integer num6);
	public Integer getTotal();
	public void setTotal(Integer total);
	public String getRemark();
	public void setRemark(String remark);
	public Integer getTotal1();
	public void setTotal1(Integer total1);
	public Integer getTotal2();
	public void setTotal2(Integer total2);
	public Integer getTotal3();
	public void setTotal3(Integer total3);
	public Integer getTotal4();
	public void setTotal4(Integer total4);
	public Integer getTotal5();
	public void setTotal5(Integer total5);
	public Integer getTotal6();
	public void setTotal6(Integer total6);
	public Integer getTotal7();
	public void setTotal7(Integer total7);
	public String getDept_list();
	public void setDept_list(String dept_list);
}
