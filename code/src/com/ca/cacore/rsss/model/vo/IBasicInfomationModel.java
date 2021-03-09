package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IBasicInfomationModel extends IPageCount{
	
	public String getBranch_id(); 
	public void setBranch_id(String branch_id);
	public String getBranch_level();
	public void setBranch_level(String branch_level);
	public String getBaseinfo();
	public void setBaseinfo(String baseinfo);
	public Integer getBaseinfo_value();
	public void setBaseinfo_value(Integer baseinfo_value);
	public Integer getOrders();
	public void setOrders(Integer orders);
	public String getTerm();
	public void setTerm(String term);
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
	public int getNumbers();
	public void setNumbers(int numbers);
	public int getCompared();
	public void setCompared(int compared);
	public int getIs_practice();
	public void setIs_practice(int is_practice);
	public int getGleaderNum();
	public void setGleaderNum(int gleaderNum);
	public int getGleaderIsPracticeNum();
	public void setGleaderIsPracticeNum(int gleaderIsPracticeNum);
	public int getBusinessNum();
	public void setBusinessNum(int businessNum);
	public int getBusinessIsPracticeNum();
	public void setBusinessIsPracticeNum(int businessIsPracticeNum);
	public int getNoBusinessNum();
	public void setNoBusinessNum(int noBusinessNum);
	public int getNoBusinessIsPracticeNum();
	public void setNoBusinessIsPracticeNum(int noBusinessIsPracticeNum);
	public int getPolicyNum();
	public void setPolicyNum(int policyNum);
	public PageCount getPageCount();
	public void setPageCount(PageCount pageCount);
	public int getStart();
	public int getLimit();
	public String getDept_list();
	public void setDept_list(String dept_list);
	
}
