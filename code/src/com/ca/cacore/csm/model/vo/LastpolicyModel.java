package com.ca.cacore.csm.model.vo;



import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class LastpolicyModel implements ILaspolicyModel {
	private String riskname;   //险种名称
	
	private Date startdate;   //起保日期
	
	private Date enddate;    //终保日期
	
	public String getRiskname() {
		return riskname;
	}

	public void setRiskname(String riskname) {
		this.riskname = riskname;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getPolicyno() {
		return policyno;
	}

	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}

	public double getNetpremium() {
		return netpremium;
	}

	public void setNetpremium(double netpremium) {
		this.netpremium = netpremium;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String policyno;  //保单号
	
	private double netpremium;  //保费
	
	private String status;  //状态
	private double sum;
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
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
