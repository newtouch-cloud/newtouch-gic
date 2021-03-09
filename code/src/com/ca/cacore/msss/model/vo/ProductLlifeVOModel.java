package com.ca.cacore.msss.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ca.cacore.msss.model.bo.IProductAssessmentModel;
import com.ca.cacore.msss.model.bo.LibAssessmentTypeModel;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.ca.cacore.msss.model.bo.IProductRelationModel;
/**
 * 
 * @author Wang_ds
 * @since 2013-12-4
 * @description PDT_Product_Llife 寿险产品信息';
 */
public class ProductLlifeVOModel implements IProductLlifeVOModel {
	private Integer seq_id; // 主键
	private String insBranch_id; // 保险公司
	private String insBranch_name; // 保险公司名称
	private String product_id; // 险种代码
	private String product_name; // 产品名称
	private String product_abbr; // 产品简称
	private String product_enName; //产品英文名称
	private String product_enAbbr; //产品英文简称
	private String product_ver; // 险种版本
	private String product_type; //产品分类（产品大类）
	private String product_type1_name; //产品分类（产品大类）名称
	private String product_type2; //产品分类2
	private String product_type2_name; //产品分类2名称
	private String product_type3; // 产品分类3
	private String product_type3_name; // 产品分类3名称
	private String ins_type; //主附险标志
	private String ins_type_name; //主附险标志
	private String period_type; // 保险期限类型
	private String periodtype_name; // 保险期限类型
	private String surr_permit; // 可否退保 Y可以 N 不可以
	private String renew; // 是否可续保 Y 可以 N 不可以
	private String insuredFlag; // 是否多被保人
	private String benefit_type; //保障范围说明
	private String duty; //基本责任说明
	private String policy_holder; // 投保人员限制说明
	private String exception; // 保险责任免除说明
	private Date start_date; // 起售时间
	private Date end_date; // 停售时间
	private String status; // 状态代码
	private String status_name; // 状态名称
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private String charge_type_code; //产品可选缴费方式
	private String coverage_period_code; //产品可选保障期限类型
	private String charge_period_code; //产品可选缴费期限类型
	
	private String [] charge_type_codes;//产品可选缴费方式
	private String [] coverage_period_codes;//产品可选保障期限类型
	private String [] charge_period_codes;//产品可选缴费期限类型
	private String pkg_id;//产品组合代码
	private String pkg_name;//产品组合名称
	private String description;//评估说明
	private String evaluation_seq_id;
	
	private String [] product_ids ;//多个产品代码
	private List<IProductLlifeVOModel> productList = new ArrayList<IProductLlifeVOModel>();
	private List<IProductAssessmentModel> productAssessmentList = new ArrayList<IProductAssessmentModel>();
    private List<IProductAssessmentVOModel> productAssessmentVOModelList = new ArrayList<IProductAssessmentVOModel>();
	private List<LibAssessmentTypeModel> libAssessmentTypeList = new ArrayList<LibAssessmentTypeModel>();
	private String business_id;//评估项目ID
	private String is_auth_partreceived;//是否可以部分领取年金
	private String is_can_borrow;//  是否可以保单借款
	private List<IProductRelationModel> ProductRelationList = new ArrayList<IProductRelationModel>();//产品主附险关系

    private String[] ins_type_names;
    private String[] insBranch_ids;
    private Integer item_id;//用于判断产品时出单前还是出单后
	
	
	private PageCount pageCount=new PageCount();
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_abbr() {
		return product_abbr;
	}
	public void setProduct_abbr(String product_abbr) {
		this.product_abbr = product_abbr;
	}
	public String getProduct_enName() {
		return product_enName;
	}
	public void setProduct_enName(String product_enName) {
		this.product_enName = product_enName;
	}
	public String getProduct_enAbbr() {
		return product_enAbbr;
	}
	public void setProduct_enAbbr(String product_enAbbr) {
		this.product_enAbbr = product_enAbbr;
	}
	public String getProduct_ver() {
		return product_ver;
	}
	public void setProduct_ver(String product_ver) {
		this.product_ver = product_ver;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduct_type2() {
		return product_type2;
	}
	public void setProduct_type2(String product_type2) {
		this.product_type2 = product_type2;
	}
	public String getProduct_type3() {
		return product_type3;
	}
	public void setProduct_type3(String product_type3) {
		this.product_type3 = product_type3;
	}
	public String getIns_type() {
		return ins_type;
	}
	public void setIns_type(String ins_type) {
		this.ins_type = ins_type;
	}
	public String getPeriod_type() {
		return period_type;
	}
	public void setPeriod_type(String period_type) {
		this.period_type = period_type;
	}
	public String getSurr_permit() {
		return surr_permit;
	}
	public void setSurr_permit(String surr_permit) {
		this.surr_permit = surr_permit;
	}
	public String getRenew() {
		return renew;
	}
	public void setRenew(String renew) {
		this.renew = renew;
	}
	public String getInsuredFlag() {
		return insuredFlag;
	}
	public void setInsuredFlag(String insuredFlag) {
		this.insuredFlag = insuredFlag;
	}
	public String getBenefit_type() {
		return benefit_type;
	}
	public void setBenefit_type(String benefit_type) {
		this.benefit_type = benefit_type;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getPolicy_holder() {
		return policy_holder;
	}
	public void setPolicy_holder(String policy_holder) {
		this.policy_holder = policy_holder;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
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
	public String getProduct_type1_name() {
		return product_type1_name;
	}
	public void setProduct_type1_name(String product_type1_name) {
		this.product_type1_name = product_type1_name;
	}
	public String getProduct_type2_name() {
		return product_type2_name;
	}
	public void setProduct_type2_name(String product_type2_name) {
		this.product_type2_name = product_type2_name;
	}
	public String getProduct_type3_name() {
		return product_type3_name;
	}
	public void setProduct_type3_name(String product_type3_name) {
		this.product_type3_name = product_type3_name;
	}
	public String getIns_type_name() {
		return ins_type_name;
	}
	public void setIns_type_name(String ins_type_name) {
		this.ins_type_name = ins_type_name;
	}
	public String getPeriodtype_name() {
		return periodtype_name;
	}
	public void setPeriodtype_name(String periodtype_name) {
		this.periodtype_name = periodtype_name;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
	public String getCharge_type_code() {
		return charge_type_code;
	}
	public void setCharge_type_code(String charge_type_code) {
		this.charge_type_code = charge_type_code;
	}
	public String getCoverage_period_code() {
		return coverage_period_code;
	}
	public void setCoverage_period_code(String coverage_period_code) {
		this.coverage_period_code = coverage_period_code;
	}
	public String getCharge_period_code() {
		return charge_period_code;
	}
	public void setCharge_period_code(String charge_period_code) {
		this.charge_period_code = charge_period_code;
	}

	public String[] getCharge_type_codes() {
		return charge_type_codes;
	}
	public void setCharge_type_codes(String[] charge_type_codes) {
		this.charge_type_codes = charge_type_codes;
	}
	public String[] getCoverage_period_codes() {
		return coverage_period_codes;
	}
	public void setCoverage_period_codes(String[] coverage_period_codes) {
		this.coverage_period_codes = coverage_period_codes;
	}
	public String[] getCharge_period_codes() {
		return charge_period_codes;
	}
	public void setCharge_period_codes(String[] charge_period_codes) {
		this.charge_period_codes = charge_period_codes;
	}
	public String getPkg_id() {
		return pkg_id;
	}
	public void setPkg_id(String pkg_id) {
		this.pkg_id = pkg_id;
	}
	public String getPkg_name() {
		return pkg_name;
	}
	public void setPkg_name(String pkg_name) {
		this.pkg_name = pkg_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEvaluation_seq_id() {
		return evaluation_seq_id;
	}
	public void setEvaluation_seq_id(String evaluation_seq_id) {
		this.evaluation_seq_id = evaluation_seq_id;
	}
	public String getInsBranch_name() {
		return insBranch_name;
	}
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}
	public String[] getProduct_ids() {
		return product_ids;
	}
	public void setProduct_ids(String[] product_ids) {
		this.product_ids = product_ids;
	}
	public List<IProductLlifeVOModel> getProductList() {
		return productList;
	}
	public void setProductList(List<IProductLlifeVOModel> productList) {
		this.productList = productList;
	}
	public List<IProductAssessmentModel> getProductAssessmentList() {
		return productAssessmentList;
	}
	public void setProductAssessmentList(List<IProductAssessmentModel> productAssessmentList) {
		this.productAssessmentList = productAssessmentList;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public List<LibAssessmentTypeModel> getLibAssessmentTypeList() {
		return libAssessmentTypeList;
	}
	public void setLibAssessmentTypeList(
			List<LibAssessmentTypeModel> libAssessmentTypeList) {
		this.libAssessmentTypeList = libAssessmentTypeList;
	}
	public String getIs_auth_partreceived() {
		return is_auth_partreceived;
	}
	public void setIs_auth_partreceived(String is_auth_partreceived) {
		this.is_auth_partreceived = is_auth_partreceived;
	}
	public String getIs_can_borrow() {
		return is_can_borrow;
	}
	public void setIs_can_borrow(String is_can_borrow) {
		this.is_can_borrow = is_can_borrow;
	}
	
	public List<IProductRelationModel> getProductRelationList() {
		return ProductRelationList;
	}
	public void setProductRelationList(
			List<IProductRelationModel> productRelationList) {
		ProductRelationList = productRelationList;
	}

    public List<IProductAssessmentVOModel> getProductAssessmentVOModelList() {
        return productAssessmentVOModelList;
    }

    public void setProductAssessmentVOModelList(List<IProductAssessmentVOModel> productAssessmentVOModelList) {
        this.productAssessmentVOModelList = productAssessmentVOModelList;
    }

    public String[] getIns_type_names() {
        return ins_type_names;
    }

    public void setIns_type_names(String[] ins_type_names) {
        this.ins_type_names = ins_type_names;
    }

    public String[] getInsBranch_ids() {
        return insBranch_ids;
    }

    public void setInsBranch_ids(String[] insBranch_ids) {
        this.insBranch_ids = insBranch_ids;
    }
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
    
}
