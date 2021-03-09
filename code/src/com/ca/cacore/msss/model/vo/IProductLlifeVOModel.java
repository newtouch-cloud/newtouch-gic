package com.ca.cacore.msss.model.vo;

import java.sql.Date;
import java.util.List;

import com.ca.cacore.msss.model.bo.IProductAssessmentModel;
import com.ca.cacore.msss.model.bo.LibAssessmentTypeModel;
import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.ca.cacore.msss.model.bo.IProductRelationModel;
/**
 * 
 * @author Wang_ds
 * @since 2013-12-4
 * @description PDT_Product_Llife 寿险产品信息 接口;
 */
public interface IProductLlifeVOModel extends IPageCount{

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
	
	public String getProduct_type1_name() ;
	
	public void setProduct_type1_name(String product_type1_name);
	
	public String getProduct_type2_name() ;
	
	public void setProduct_type2_name(String product_type2_name);
	
	public String getProduct_type3_name();
	
	public void setProduct_type3_name(String product_type3_name);
	
	public String getIns_type_name();
	
	public void setIns_type_name(String ins_type_name);
	
	public String getPeriodtype_name() ;
	
	public void setPeriodtype_name(String periodtype_name);
	
	public String getStatus_name();
	
	public void setStatus_name(String status_name) ;
	
	public String getCharge_type_code();
	
	public void setCharge_type_code(String charge_type_code);
	
	public String getCoverage_period_code() ;
	
	public void setCoverage_period_code(String coverage_period_code) ;
	
	public String getCharge_period_code() ;
	
	public void setCharge_period_code(String charge_period_code);
	
	public String getPkg_id() ;
	
	public void setPkg_id(String pkg_id);
	
	public String getPkg_name() ;
	
	public void setPkg_name(String pkg_name) ;
	
	public String getDescription() ;
	
	public void setDescription(String description) ;
	
	public String getEvaluation_seq_id();
	
	public void setEvaluation_seq_id(String evaluation_seq_id) ;
	
	public String[] getCharge_type_codes() ;
	
	public void setCharge_type_codes(String[] charge_type_codes);
	
	public String[] getCoverage_period_codes();
	
	public void setCoverage_period_codes(String[] coverage_period_codes);
	
	public String[] getCharge_period_codes();
	
	public void setCharge_period_codes(String[] charge_period_codes);
	
	public String getInsBranch_name() ;
	
	public void setInsBranch_name(String insBranch_name);
	
	public String[] getProduct_ids();
	
	public void setProduct_ids(String[] product_ids);
	
	public List<IProductLlifeVOModel> getProductList();
	
	public void setProductList(List<IProductLlifeVOModel> productList);
	
	public List<IProductAssessmentModel> getProductAssessmentList();
	
	public void setProductAssessmentList(List<IProductAssessmentModel> productAssessmentList);
	public String getBusiness_id() ;
	
	public void setBusiness_id(String business_id);
	
	public List<LibAssessmentTypeModel> getLibAssessmentTypeList() ;
	
	public void setLibAssessmentTypeList(List<LibAssessmentTypeModel> libAssessmentTypeList);

    public List<IProductAssessmentVOModel> getProductAssessmentVOModelList();

    public void setProductAssessmentVOModelList(List<IProductAssessmentVOModel> productAssessmentVOModelList);
	public String getIs_auth_partreceived() ;
	
	
	public void setIs_auth_partreceived(String is_auth_partreceived) ;
	public String getIs_can_borrow();
	
	public void setIs_can_borrow(String is_can_borrow) ;
	public List<IProductRelationModel> getProductRelationList() ;
	
	public void setProductRelationList(List<IProductRelationModel> productRelationList);
	
	public void setIns_type_names(String[] ins_type_names);
    public String[] getIns_type_names();

    public String[] getInsBranch_ids();

    public void setInsBranch_ids(String[] insBranch_ids);
    
    public Integer getItem_id();
    
	public void setItem_id(Integer item_id) ;
}