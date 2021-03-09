package com.ca.cacore.msss.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IProductFeeRateModel extends IPageCount {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getInsBranch_id();

	public void setInsBranch_id(String insBranch_id);

	public String getProduct_id();

	public void setProduct_id(String product_id);

	public String getCoverage_period();

	public void setCoverage_period(String coverage_period);

	public String getCoverage_year();

	public void setCoverage_year(String coverage_year);

	public String getSell_way();

	public void setSell_way(String sell_way);
	
	public String getSell_way_name();

	public void setSell_way_name(String sell_way_name);

	public String getCharge_type();

	public void setCharge_type(String charge_type);

	public Integer getCharge_year();

	public void setCharge_year(Integer charge_year);

	public String getPolicy_year();

	public void setPolicy_year(String policy_year);

	public String getPolicy_period();

	public void setPolicy_period(String policy_period);

	public double getFee_rate();

	public void setFee_rate(double fee_rate);

	public Date getStart_date();

	public void setStart_date(Date start_date);

	public Date getEnd_date();

	public void setEnd_date(Date end_date);

	public String getStatus();

	public void setStatus(String status);

	public String getRemark();

	public void setRemark(String remark);

	public String getCreateUser();

	public void setCreateUser(String createUser);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getModifyUser();

	public void setModifyUser(String modifyUser);

	public Date getModifyDate();

	public void setModifyDate(Date modifyDate);

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

	public int getStart();

	public int getLimit();

}