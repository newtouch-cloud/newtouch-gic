package com.ca.cacore.csm.model.vo;



import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ILaspolicyModel extends IPageCount{

	public String getRiskname();

	public void setRiskname(String riskname);

	public Date getStartdate();

	public void setStartdate(Date startdate);

	public Date getEnddate();

	public void setEnddate(Date enddate);

	public String getPolicyno();

	public void setPolicyno(String policyno);

	public double getNetpremium();

	public void setNetpremium(double netpremium);

	public String getStatus();

	public void setStatus(String status);
	
	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);
    
	public double getSum();
	
	public void setSum(double getSum);
}