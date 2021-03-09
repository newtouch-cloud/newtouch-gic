package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;


/**
 * 
* @since:    2014年1月9日   
* @author    ZhangChen
* @description:保单费用明细vomodel 主表 cbs_contractLife_product_fee
* join left --> sys_branch sys_insbranch pdt_product_llife pdt_product_type1 pdt_product_type2 pdt_product_type3
* smis_sales cbs_contractlife cbs_contractlife_holder cbs_contractlife_insured pdt_product_instype  pdt_product_periodtype
* pdt_lib_coverageperiod pdt_lib_chargetype cbs_fee_status
 */
public class ContractCostVOModel implements  IContractCostVOModel {
	private Integer seq_id;
	private String type;
	private Long policy_id; // 保单ID
	private String branch_id; // 机构代码 Ref：SYS_Branch
	private String branch_name;//机构名称
	private String insBranch_id; // 保险公司机构
	private String insBranch_name;//保险公司名称
	private String product_name;//产品名称
	private String product_id;//产品代码
	private String product_type1_name;//产品分类1
	private String product_type2_name;//产品分类2
	private String product_type3_name;//产品分类3
	private String send_code; // 投保单号码
	private String policy_code; // 保单号码
	private String agent_id; // 保单销售人员 REF: SMIS_Sales
	private String agent_name; // 保单销售人员 REF: SMIS_Sales
	private String service_id; // 保单服务人员 REF:SMIS_Sales
	private String service_name; // 保单服务人员 REF:SMIS_Sales
	private String holder_id; // 投保人 REF:CBS_Customer
	private String app_name; // 投保人姓名
	private String insurant_id; //被保人id REF:CBS_Customer
	private String insurant_name;//被保人名称  ref:cbs_contractlife_insured
	private String ins_type_name;//主附险标志
	private String periodtype_name;//保险期限类型
	private String coverage_period_name;//保障期限类型
	private String sell_way_name; // 销售方式
	private String charge_type_name; // 缴费方式 Ref：PDT_Lib_ChargeType
	private String coverage_year;// 保单年数
	private String fee_type;//费用业务类型  
	private String feetype_name;//费用业务类型名称
	private String policy_period;//缴费次数
	private Double period_prem;//应缴保费
	private Double actual_prem;//实缴保费
	private String pay_mode_name; //当期付款方式 Ref：SYS_Library_PayMode
	private String money_id; // 币种 Ref：SYS_Library_MoneyType
	private Date hold_date;//投保日期
	private Date due_time;//应缴日期
	private Date received_date;//应回收缴日期
	private String posted;//是否以生成记账
	private Integer cred_id;//记账凭证号
	private Date post_time;//记账凭证生成时间
	private String fee_status;//费用处理状态代码
	private String status_name;//费用处理状态名称

	private PageCount pageCount = new PageCount();


   
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Integer getSeq_id() {
		return seq_id;
	}
	/** 
	* 
	* @param seq_id 
	* @description:
	*/
	@Override
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getType() {
		return type;
	}
	/** 
	* 
	* @param type 
	* @description:
	*/
	@Override
	public void setType(String type) {
		this.type = type;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Long getPolicy_id() {
		return policy_id;
	}
	/** 
	* 
	* @param policy_id 
	* @description:
	*/
	@Override
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getBranch_id() {
		return branch_id;
	}
	/** 
	* 
	* @param branch_id 
	* @description:
	*/
	@Override
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getBranch_name() {
		return branch_name;
	}
	/** 
	* 
	* @param branch_name 
	* @description:
	*/
	@Override
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getInsBranch_id() {
		return insBranch_id;
	}
	/** 
	* 
	* @param insBranch_id 
	* @description:
	*/
	@Override
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getInsBranch_name() {
		return insBranch_name;
	}
	/** 
	* 
	* @param insBranch_name 
	* @description:
	*/
	@Override
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getProduct_name() {
		return product_name;
	}
	/** 
	* 
	* @param product_name 
	* @description:
	*/
	@Override
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getProduct_id() {
		return product_id;
	}
	/** 
	* 
	* @param product_id 
	* @description:
	*/
	@Override
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getProduct_type1_name() {
		return product_type1_name;
	}
	/** 
	* 
	* @param product_type1_name 
	* @description:
	*/
	@Override
	public void setProduct_type1_name(String product_type1_name) {
		this.product_type1_name = product_type1_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getProduct_type2_name() {
		return product_type2_name;
	}
	/** 
	* 
	* @param product_type2_name 
	* @description:
	*/
	@Override
	public void setProduct_type2_name(String product_type2_name) {
		this.product_type2_name = product_type2_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getProduct_type3_name() {
		return product_type3_name;
	}
	/** 
	* 
	* @param product_type3_name 
	* @description:
	*/
	@Override
	public void setProduct_type3_name(String product_type3_name) {
		this.product_type3_name = product_type3_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getSend_code() {
		return send_code;
	}
	/** 
	* 
	* @param send_code 
	* @description:
	*/
	@Override
	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getPolicy_code() {
		return policy_code;
	}
	/** 
	* 
	* @param policy_code 
	* @description:
	*/
	@Override
	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getAgent_id() {
		return agent_id;
	}
	/** 
	* 
	* @param agent_id 
	* @description:
	*/
	@Override
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getAgent_name() {
		return agent_name;
	}
	/** 
	* 
	* @param agent_name 
	* @description:
	*/
	@Override
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getService_id() {
		return service_id;
	}
	/** 
	* 
	* @param service_id 
	* @description:
	*/
	@Override
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getService_name() {
		return service_name;
	}
	/** 
	* 
	* @param service_name 
	* @description:
	*/
	@Override
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getHolder_id() {
		return holder_id;
	}
	/** 
	* 
	* @param holder_id 
	* @description:
	*/
	@Override
	public void setHolder_id(String holder_id) {
		this.holder_id = holder_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getApp_name() {
		return app_name;
	}
	/** 
	* 
	* @param app_name 
	* @description:
	*/
	@Override
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getInsurant_id() {
		return insurant_id;
	}
	/** 
	* 
	* @param insurant_id 
	* @description:
	*/
	@Override
	public void setInsurant_id(String insurant_id) {
		this.insurant_id = insurant_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getInsurant_name() {
		return insurant_name;
	}
	/** 
	* 
	* @param insurant_name 
	* @description:
	*/
	@Override
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getIns_type_name() {
		return ins_type_name;
	}
	/** 
	* 
	* @param ins_type_name 
	* @description:
	*/
	@Override
	public void setIns_type_name(String ins_type_name) {
		this.ins_type_name = ins_type_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getPeriodtype_name() {
		return periodtype_name;
	}
	/** 
	* 
	* @param periodtype_name 
	* @description:
	*/
	@Override
	public void setPeriodtype_name(String periodtype_name) {
		this.periodtype_name = periodtype_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCoverage_period_name() {
		return coverage_period_name;
	}
	/** 
	* 
	* @param coverage_period_name 
	* @description:
	*/
	@Override
	public void setCoverage_period_name(String coverage_period_name) {
		this.coverage_period_name = coverage_period_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getSell_way_name() {
		return sell_way_name;
	}
	/** 
	* 
	* @param sell_way 
	* @description:
	*/
	@Override
	public void setSell_way_name(String sell_way_name) {
		this.sell_way_name = sell_way_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCharge_type_name() {
		return charge_type_name;
	}
	/** 
	* 
	* @param charge_type_name 
	* @description:
	*/
	@Override
	public void setCharge_type_name(String charge_type_name) {
		this.charge_type_name = charge_type_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCoverage_year() {
		return coverage_year;
	}
	/** 
	* 
	* @param coverage_year 
	* @description:
	*/
	@Override
	public void setCoverage_year(String coverage_year) {
		this.coverage_year = coverage_year;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getFee_type() {
		return fee_type;
	}
	/** 
	* 
	* @param fee_type 
	* @description:
	*/
	@Override
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getPolicy_period() {
		return policy_period;
	}
	/** 
	* 
	* @param policy_period 
	* @description:
	*/
	@Override
	public void setPolicy_period(String policy_period) {
		this.policy_period = policy_period;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Double getPeriod_prem() {
		return period_prem;
	}
	/** 
	* 
	* @param period_prem 
	* @description:
	*/
	@Override
	public void setPeriod_prem(Double period_prem) {
		this.period_prem = period_prem;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Double getActual_prem() {
		return actual_prem;
	}
	/** 
	* 
	* @param actual_prem 
	* @description:
	*/
	@Override
	public void setActual_prem(Double actual_prem) {
		this.actual_prem = actual_prem;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getPay_mode_name() {
		return pay_mode_name;
	}
	/** 
	* 
	* @param pay_mode 
	* @description:
	*/
	@Override
	public void setPay_mode_name(String pay_mode_name) {
		this.pay_mode_name = pay_mode_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getMoney_id() {
		return money_id;
	}
	/** 
	* 
	* @param money_id 
	* @description:
	*/
	@Override
	public void setMoney_id(String money_id) {
		this.money_id = money_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getHold_date() {
		return hold_date;
	}
	/** 
	* 
	* @param hold_date 
	* @description:
	*/
	@Override
	public void setHold_date(Date hold_date) {
		this.hold_date = hold_date;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getDue_time() {
		return due_time;
	}
	/** 
	* 
	* @param due_time 
	* @description:
	*/
	@Override
	public void setDue_time(Date due_time) {
		this.due_time = due_time;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getReceived_date() {
		return received_date;
	}
	/** 
	* 
	* @param received_date 
	* @description:
	*/
	@Override
	public void setReceived_date(Date received_date) {
		this.received_date = received_date;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getPosted() {
		return posted;
	}
	/** 
	* 
	* @param posted 
	* @description:
	*/
	@Override
	public void setPosted(String posted) {
		this.posted = posted;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Integer getCred_id() {
		return cred_id;
	}
	/** 
	* 
	* @param cred_id 
	* @description:
	*/
	@Override
	public void setCred_id(Integer cred_id) {
		this.cred_id = cred_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getPost_time() {
		return post_time;
	}
	/** 
	* 
	* @param post_time 
	* @description:
	*/
	@Override
	public void setPost_time(Date post_time) {
		this.post_time = post_time;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getFee_status() {
		return fee_status;
	}
	/** 
	* 
	* @param fee_status 
	* @description:
	*/
	@Override
	public void setFee_status(String fee_status) {
		this.fee_status = fee_status;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getStatus_name() {
		return status_name;
	}
	/** 
	* 
	* @param status_name 
	* @description:
	*/
	@Override
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public PageCount getPageCount() {
		return pageCount;
	}
	/** 
	* 
	* @param pageCount 
	* @description:
	*/
	@Override
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	@Override
	public String getFeetype_name() {
		return feetype_name;
	}
	@Override
	public void setFeetype_name(String feetype_name) {
		this.feetype_name = feetype_name;
	}
	
	
}
