package com.ca.cacore.csm.model.bo;



import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;


/**
 * 客户明细查询理赔信息
 * @author wangkang
 *
 */
public interface IInfclaimsModel extends IPageCount{

	public String getPolicyno();

	public void setPolicyno(String policyno);

	public String getClaims_no();

	public void setClaims_no(String claims_no);

	public Date getEvent_date();

	public void setEvent_date(Date event_date);

	public double getDetermined_money();

	public void setDetermined_money(double determined_money);

	public double getUndetermined_money();

	public void setUndetermined_money(double undetermined_money);

	public String getClaims_status();

	public void setClaims_status(String claims_status);
	
	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);
	
	public double getSumMoney();
	
	public void setSumMoney(double sumMoney);
	
	public String getClaims_status_name();
	
	public void setClaims_status_name(String claims_status_name);

}