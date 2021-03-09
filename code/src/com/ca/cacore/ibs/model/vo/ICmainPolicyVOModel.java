package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

public interface ICmainPolicyVOModel {

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getPolicyno();

	public abstract void setPolicyno(String policyno);

	public abstract String getRiskcode();

	public abstract void setRiskcode(String riskcode);

	public abstract String getRiskname();

	public abstract void setRiskname(String riskname);

	public abstract String getClasscode();

	public abstract void setClasscode(String classcode);

	public abstract String getClassname();

	public abstract void setClassname(String classname);

	public abstract String getRtype();

	public abstract void setRtype(String rtype);
	
	public abstract String getProduct_source();
	
	public abstract void setProduct_source(String product_source);

	public abstract String getCcurno();

	public abstract void setCcurno(String ccurno);

	public abstract Double getNetpremium();

	public abstract void setNetpremium(Double netpremium);

	public abstract Date getSigndate();

	public abstract void setSigndate(Date signdate);

	public abstract Date getOperatedate();

	public abstract void setOperatedate(Date operatedate);

	public abstract Date getStartdate();

	public abstract void setStartdate(Date startdate);

	public abstract Date getEnddate();

	public abstract void setEnddate(Date enddate);

	public abstract Date getDcoldte();

	public abstract void setDcoldte(Date dcoldte);

	public abstract Double getSumamount();

	public abstract void setSumamount(Double sumamount);

	public abstract String getPaymode();

	public abstract void setPaymode(String paymode);

	public abstract String getStype();

	public abstract void setStype(String stype);

	public abstract Double getFrate();

	public abstract void setFrate(Double frate);

	public abstract Double getFnum();

	public abstract void setFnum(Double fnum);

	public abstract String getApplicode();

	public abstract void setApplicode(String applicode);

	public abstract String getAppliname();

	public abstract void setAppliname(String appliname);

	public abstract String getInsuredcode();

	public abstract void setInsuredcode(String insuredcode);

	public abstract String getInsuredname();

	public abstract void setInsuredname(String insuredname);

	public abstract String getHandlercode();

	public abstract void setHandlercode(String handlercode);

	public abstract String getHandlername();

	public abstract void setHandlername(String handlername);

	public abstract String getTeam_no();

	public abstract void setTeam_no(String team_no);

	public abstract String getCar_no();

	public abstract void setCar_no(String car_no);

	public abstract Date getModifydate();

	public abstract void setModifydate(Date modifydate);

	public abstract Date getIs_pay_date();

	public abstract void setIs_pay_date(Date is_pay_date);

	public abstract String getRenewalflag();

	public abstract void setRenewalflag(String renewalflag);

	public abstract String getBusinesscode();

	public abstract void setBusinesscode(String businesscode);

	public abstract String getCommission_poundage_scale();

	public abstract void setCommission_poundage_scale(
			String commission_poundage_scale);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getCustomer_type();

	public abstract void setCustomer_type(String customer_type);

	public abstract String getCerti_type();

	public abstract void setCerti_type(String certi_type);

	public abstract String getCerti_no();

	public abstract void setCerti_no(String certi_no);

	public abstract String getGender();

	public abstract void setGender(String gender);

	public abstract Date getBirthday();

	public abstract void setBirthday(Date birthday);

	public abstract String getCompany_telphone();

	public abstract void setCompany_telphone(String company_telphone);

	public abstract String getModifyuser();

	public abstract void setModifyuser(String modifyuser);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getAddress();

	public abstract void setAddress(String address);

	public abstract String getMobile();

	public abstract void setMobile(String mobile);

	public abstract String getTelphone();

	public abstract void setTelphone(String telphone);

	public abstract String getStatus();

	public abstract void setStatus(String status);

	public abstract String getRemark();

	public abstract void setRemark(String remark);

	public abstract String getCreateuser();

	public abstract void setCreateuser(String createuser);

	public abstract Integer getLog_seq_id();

	public abstract void setLog_seq_id(Integer log_seq_id);

	public abstract String getLog_bustype();

	public abstract void setLog_bustype(String log_bustype);

	public abstract Date getLog_busdate();

	public abstract void setLog_busdate(Date log_busdate);

	public abstract Date getLog_date();

	public abstract void setLog_date(Date log_date);

	public abstract String getLog_remark();

	public abstract void setLog_remark(String log_remark);

	public abstract String getFlag();

	public abstract void setFlag(String flag);

}