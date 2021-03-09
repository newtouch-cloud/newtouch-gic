package com.ca.cacore.msss.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IProductFeeRateVOModel extends IPageCount {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getInsBranch_id();

	public void setInsBranch_id(String insBranch_id);
	
	public String getInsBranch_name();

	public void setInsBranch_name(String insBranch_name);

	public String getProduct_id();

	public void setProduct_id(String product_id);
	
	public String getProduct_name();
	
	public void setProduct_name(String product_name);
	
	public String getCoverage_period();

	public void setCoverage_period(String coverage_period);
	
	public String getCoverage_period_name();

	public void setCoverage_period_name(String coverage_period_name);

	public String getCoverage_year();

	public void setCoverage_year(String coverage_year);

	public String getSell_way();

	public void setSell_way(String sell_way);
	
	public String getSell_way_name();

	public void setSell_way_name(String sell_way_name);

	public String getCharge_type();

	public void setCharge_type(String charge_type);
	
	public String getCharge_type_name();

	public void setCharge_type_name(String charge_type_name);

	public Integer getCharge_year();

	public void setCharge_year(Integer charge_year);

	public String getPolicy_year();

	public void setPolicy_year(String policy_year);

	public String getPolicy_period();

	public void setPolicy_period(String policy_period);

	public Double getFee_rate() ;

	public void setFee_rate(Double fee_rate) ;

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
	
}