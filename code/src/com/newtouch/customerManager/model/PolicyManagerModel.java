package com.newtouch.customerManager.model;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface PolicyManagerModel extends IPageCount {
	public Integer getSeq_id();// 主键

	public void setSeq_id(Integer seq_id);

	public String getWeidian_id();

	public void setWeidian_id(String weidian_id);

	public String getWeidian_no();

	public void setWeidian_no(String weidian_no);

	public String getIdentity_id();

	public void setIdentity_id(String identity_id);

	public String getAgent_name();

	public void setAgent_name(String agent_name);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBranch_name();
	public void setOrg_id(String branch_id);
	public String getOrg_id();

	public void setBranch_name(String branch_name);

	public String getProvince();

	public void setProvince(String province);

	public String getCity();

	public void setCity(String city);

	public String getArea();

	public void setArea(String area);

	public Integer getProvince_code();

	public void setProvince_code(Integer province_code);

	public Integer getCity_code();

	public void setCity_code(Integer city_code);

	public Integer getArea_code();

	public void setArea_code(Integer area_code);

	public String getStatistics_type();

	public void setStatistics_type(String statistics_type);

	public String getChannel_type();

	public void setChannel_type(String channel_type);

	public String getChannel_name();

	public void setChannel_name(String channel_name);

	public String getChannel_no();

	public void setChannel_no(String channel_no);

	public String getInsurance_type_name();

	public void setInsurance_type_name(String insurance_type_name);

	public String getInsurance_class_name();

	public void setInsurance_class_name(String insurance_class_name);

	public String getProduct_name();

	public void setProduct_name(String product_name);

	public Date getSign_date();

	public void setSign_date(Date sign_date);

	public Date getAccomplish_date();

	public void setAccomplish_date(Date accomplish_date);

	public Date getStatistics_date();

	public void setStatistics_date(Date statistics_date);

	public String getInsure_no();

	public void setInsure_no(String insure_no);

	public String getPolicy_no();

	public void setPolicy_no(String policy_no);

	public String getSerial_no();

	public void setSerial_no(String serial_no);

	public String getApplicant_name();

	public void setApplicant_name(String applicant_name);

	public String getInsured_name();

	public void setInsured_name(String insured_name);

	public String getLPN();

	public void setLPN(String lPN);

	public String getPremium();

	public void setPremium(String premium);

	public String getRepair_coding();

	public void setRepair_coding(String repair_coding);

	public String getRack_no();

	public void setRack_no(String rack_no);

	public Date getCheck_date();

	public void setCheck_date(Date check_date);

	public Date getBegin_date();

	public void setBegin_date(Date begin_date);

	public Date getEnd_date();

	public void setEnd_date(Date end_date);

	public Date getApproval_date();

	public void setApproval_date(Date approval_date);

	public Integer getService_charge();

	public void setService_charge(Integer service_charge);

	public Integer getOrder_pay();

	public void setOrder_pay(Integer order_pay);

	public Integer getClean_charge();

	public void setClean_charge(Integer clean_charge);

	public Integer getWei_rate();

	public void setWei_rate(Integer wei_rate);

	public Integer getCommission();

	public void setCommission(Integer commission);

	public Integer getCash();

	public void setCash(Integer cash);

	public String getStatus();

	public void setStatus(String status);

	public void setPerson_name(String person_name);

	public String getPerson_no();

	public void setPerson_no(String person_no);

	public Date getCreateDateL();

	public void setCreateDateL(Date createDateL);

	public Date getCreateDateG();

	public void setCreateDateG(Date createDateG);

	public Integer getCount();

	public void setCount(Integer count);
	public String getPerson_name();
}
