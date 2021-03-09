package com.newtouch.report.model.vo;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IBusinessTypeModel extends IPageCount{
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name();
	public void setBranch_name(String branch_name);
	public String getBranch_level();
	public void setBranch_level(String branch_level);
	public String getTerm();
	public void setTerm(String term);
	public String getBusiness_flag();
	public void setBusiness_flag(String business_flag);
	public String getU_month_count();
	public void setU_month_count(String u_month_count);
	public String getU_month_premium();
	public void setU_month_premium(String u_month_premium);
	public String getU_month_fee();
	public void setU_month_fee(String u_month_fee);
	public String getU_month_paidfee();
	public void setU_month_paidfee(String u_month_paidfee);
	public String getU_year_count();
	public void setU_year_count(String u_year_count);
	public String getU_year_premium();
	public void setU_year_premium(String u_year_premium);
	public String getU_year_fee();
	public void setU_year_fee(String u_year_fee);
	public String getU_year_paidfee();
	public void setU_year_paidfee(String u_year_paidfee);
	public String getDept_list();
	public void setDept_list(String dept_list);
	public String getEmpId();
	public void setEmpId(String empId);


}
