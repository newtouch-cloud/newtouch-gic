package com.ca.cacore.csm.model.bo;



import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;


/**
 * 客户明细查询理赔信息
 * @author wangkang
 *
 */
public class InfclaimsModel implements IInfclaimsModel {
	
	private String policyno; //保单号
	
	private String claims_no; //赔案号
	
	private Date event_date;  // 出险日期
	
	private double determined_money; //已决金额
	
	private double undetermined_money; //未决金额
	
	private String claims_status;      //赔案状态
	
	private double sumMoney;
	
	private String claims_status_name;   //赔案状态名称
	
	public String getClaims_status_name() {
		return claims_status_name;
	}

	public void setClaims_status_name(String claims_status_name) {
		this.claims_status_name = claims_status_name;
	}

	public double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public String getPolicyno() {
		return policyno;
	}

	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}

	public String getClaims_no() {
		return claims_no;
	}

	public void setClaims_no(String claims_no) {
		this.claims_no = claims_no;
	}

	public Date getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}

	public double getDetermined_money() {
		return determined_money;
	}

	public void setDetermined_money(double determined_money) {
		this.determined_money = determined_money;
	}

	public double getUndetermined_money() {
		return undetermined_money;
	}

	public void setUndetermined_money(double undetermined_money) {
		this.undetermined_money = undetermined_money;
	}

	public String getClaims_status() {
		return claims_status;
	}

	public void setClaims_status(String claims_status) {
		this.claims_status = claims_status;
	}
	private PageCount pageCount = new PageCount();
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
}
