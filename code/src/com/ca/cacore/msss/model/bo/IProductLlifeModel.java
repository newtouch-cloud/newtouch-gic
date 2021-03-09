package com.ca.cacore.msss.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 
 * @author Wangds
 * @since 2013-12-4
 * @description PDT_Product_Llife 寿险产品信息 接口;
 */
public interface IProductLlifeModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getInsBranch_id();

	public void setInsBranch_id(String insBranch_id);

	public String getProduct_id();

	public void setProduct_id(String product_id);

	public String getProduct_name();

	public void setProduct_name(String product_name);

	public String getProduct_abbr();

	public void setProduct_abbr(String product_abbr);

	public String getProduct_enName();

	public void setProduct_enName(String product_enName);

	public String getProduct_enAbbr();

	public void setProduct_enAbbr(String product_enAbbr);

	public String getProduct_ver();

	public void setProduct_ver(String product_ver);

	public String getProduct_type();

	public void setProduct_type(String product_type);

	public String getProduct_type2();

	public void setProduct_type2(String product_type2);

	public String getProduct_type3();

	public void setProduct_type3(String product_type3);

	public String getIns_type();

	public void setIns_type(String ins_type);

	public String getPeriod_type();

	public void setPeriod_type(String period_type);

	public String getSurr_permit();

	public void setSurr_permit(String surr_permit);

	public String getRenew();

	public void setRenew(String renew);

	public String getInsuredFlag();

	public void setInsuredFlag(String insuredFlag);

	public String getBenefit_type();

	public void setBenefit_type(String benefit_type);

	public String getDuty();

	public void setDuty(String duty);

	public String getPolicy_holder();

	public void setPolicy_holder(String policy_holder);

	public String getException();

	public void setException(String exception);

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
	
	public String getIs_auth_partreceived();
	
	public void setIs_auth_partreceived(String is_auth_partreceived) ;
	
	public String getIs_can_borrow() ;
	
	public void setIs_can_borrow(String is_can_borrow) ;
	
	public String getInner_product_id() ;
	
	public void setInner_product_id(String inner_product_id) ;
	
	
}