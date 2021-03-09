package com.ca.cacore.ibs.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 保单的vo
 * @author xxz521
 */
public class ContractLifeVOModel implements IContractLifeVOModel {
	private Integer seq_id;
	private String type;
	private String jsp_url;//根据页面传值进行跳转
	private Long policy_id; // 保单ID
	private String branch_id; // 机构代码 Ref：SYS_Branch
	private String insBranch_id; // 保险公司机构
	private String insBranch_name;//保险公司名称
	private String send_code; // 投保单号码
	private String policy_code; // 保单号码
	private String agent_id; // 保单销售人员 REF: SMIS_Sales
	private String agent_name; // 保单销售人员 REF: SMIS_Sales
	private String service_id; // 保单服务人员 REF:SMIS_Sales
	private String service_name; // 保单服务人员 REF:SMIS_Sales
	private String holder_id; // 投保人 REF:CBS_Customer
	private String app_name; // 投保人姓名
	private String  insurant_id; // 第一被保人 REF:CBS_Customer
	private String relation_id; // 投保人与被保人关系 REF:CBS_Applicant_Relation1
	private String relation_name;//投保人与主被保人关系
	private String sell_way; // 消费方式 Ref：CBS_Policy_Sell_Way
	private String charge_type; // 当期缴费方式 Ref：PDT_Lib_ChargeType
	private String charge_next; // 续期缴费方式 Ref：PDT_Lib_ChargeType
	private String pay_mode; //当期付款方式 Ref：SYS_Library_PayMode
	private String pay_mode_next; // 续期付款方式 Ref：SYS_Library_PayMode
	private Integer policy_year; // 保单年度
	private Integer policy_period; // 缴费期次
	private Double period_prem; // 保费合计
	private String money_id; // 币种 Ref：SYS_Library_MoneyType
	private String bank_code; // 开户银行代码
	private String account_type; // 银行账号类型 Ref：SYS_Library_BankAccountType
	private String bank_accName; // 银行开户人
	private String bank_account; // 银行账号
	private String overdue_manage; // 保险费逾期未付的选择 Ref：CBS_PolicyLife_Overdue
	private Date hold_date; // 投保日期
	private Date scan_time; // 影像上传日期
	private Date validate_date; // 保单生效日期
	private Date sign_date; // 客户签收日期
	private Date end_date; // 保单终止日期
	private Date pause_time; // 保单停效日期
	private String is_answered; // 是否已回访 Y是N否
	private String high_policy; // 是否高额件 Y是N否
	private String status; // 投保单状态 Ref：CBS_Policy_Status
	private String status_name; // 保单的状态
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
	private Date rtn_date;//回执核销日期
	private String branch_name;//机构名称
	public List<IContractLifePeopleVOModel> holderListView  = new ArrayList<IContractLifePeopleVOModel>();//投保人集合
	public List<IContractLifePeopleVOModel>insurantListView = new ArrayList<IContractLifePeopleVOModel>();//被保人集合
	public List<IContractLifePeopleVOModel >beneficiaryListView = new ArrayList<IContractLifePeopleVOModel>();//受益人集合
	public List<IContractLifeProductVOModel >productListView = new ArrayList<IContractLifeProductVOModel>();//险种集合
	private String[]policyPeople_customer_id;
	private String[]policyLifeInsured_customer_id;
	private String[]policyLifeInsured_relation1;
	private String[]policyLifeInsured_relation_code;
	private String[]policyLifeBeneficiary_customer_id;
	private String[]policyLifeBeneficiary_insurant_name_value;
	private String[]policyLifeBeneficiary_relation1;
	private String[]policyLifeBeneficiary_relation_code;
	private String[]policyLifeBeneficiary_bene_type_code;
	private String[]policyLifeBeneficiay_bene_order;
	private String[]policyLifeBeneficiay_bene_rate;
	//产品保存相关接值
	private String[] product_id_arr ;//产品代码
	private String[] coverage_period_arr ;//保障期限类型
	private String[] coverage_year_arr ;//保障时间
	private String[] charge_period_arr ;//缴费期限类型
	private String[] charge_year_arr ;//缴费年数
	private String[] charge_type_arr; //缴费方式
	private String[] amount_arr;//基本保障金额
	private String[]period_prem_arr;//保费
	private PageCount pageCount = new PageCount();
	
	private String fee_type;//费用业务类型
	private Integer actual_prem;//本期实缴保费
	private Integer	fee_id	;//费用id
	private String	ins_type	;//主附险标志 
	private String	product_type	;// 产品分类1（产品大类）
	private String	product_type2	;// 产品分类2
	private String	product_type3	;//产品分类3（保障类型，财务用）
	private String	period_type	;//保障期限分类	
	private Date due_time;//本次应收日期
	private Date received_date;//应收回销日期
	private String fee_status;//费用处理状态
	private String posted;//是否以生成记账
	private String post_id;//记账确认人员id
	private Integer cred_id;//记账凭证号
	private Date post_time;//记账凭证生成时间
	//投保单费用信息
	private Integer prem_id;//费用id
	private String	customer_id;//付费人
	//产品相关
	private Integer	item_id	;//产品项目id	
	private String	app_id	;//投保人	
	private Integer 	unit;//份数	
	private Double  amount	;//保额	
	private Date next_due_time	;              // 	下次应收日期	
	private String insure_stop	;//        	是否终止	    
	private Integer auth_payAge;//年金领取年龄
	private String auth_draw;//年金领取方式
	private Double auth_firstPay;//首期领取金额
	private String dividend_choice;//红利领取方式
    private String is_autoRen;//是否可以续保
    private String result_flag;//保存是否成功
    private String[]product_insurant_id;//产品和被保人的关系
    private String[] auth_payAge_arr ;//年金领取年龄
    private String[] auth_draw_arr;//年金领取方式
    private String[] auth_firstPay_arr ;//首期领取金额
    private String[] dividend_choice_arr ;//红利领取方式
    private String[] is_autoRen_arr ;//是否自动续保
    private String product_name;//产品名称
    private String product_id;//产品代码
    private String insurant_name;//主被保人名称
    private String answer_type;//回访方式
    private String bef_status;//变更前的状态
    private String aft_status;//变更后的状态
    private String is_success;//回访是否成功
    
    public String getIs_success() {
		return is_success;
	}
	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}
	public String getBef_status() {
		return bef_status;
	}
	public void setBef_status(String bef_status) {
		this.bef_status = bef_status;
	}
	public String getAft_status() {
		return aft_status;
	}
	public void setAft_status(String aft_status) {
		this.aft_status = aft_status;
	}
	public String getAnswer_type() {
		return answer_type;
	}
	public void setAnswer_type(String answer_type) {
		this.answer_type = answer_type;
	}
	public String getInsurant_name() {
		return insurant_name;
	}
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
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
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJsp_url() {
		return jsp_url;
	}
	public void setJsp_url(String jsp_url) {
		this.jsp_url = jsp_url;
	}
	public Long getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public String getInsBranch_name() {
		return insBranch_name;
	}
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}
	public String getSend_code() {
		return send_code;
	}
	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	public String getPolicy_code() {
		return policy_code;
	}
	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getHolder_id() {
		return holder_id;
	}
	public void setHolder_id(String holder_id) {
		this.holder_id = holder_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getInsurant_id() {
		return insurant_id;
	}
	public void setInsurant_id(String insurant_id) {
		this.insurant_id = insurant_id;
	}
	public String getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(String relation_id) {
		this.relation_id = relation_id;
	}
	public String getRelation_name() {
		return relation_name;
	}
	public void setRelation_name(String relation_name) {
		this.relation_name = relation_name;
	}
	public String getSell_way() {
		return sell_way;
	}
	public void setSell_way(String sell_way) {
		this.sell_way = sell_way;
	}
	public String getCharge_type() {
		return charge_type;
	}
	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}
	public String getCharge_next() {
		return charge_next;
	}
	public void setCharge_next(String charge_next) {
		this.charge_next = charge_next;
	}
	public String getPay_mode() {
		return pay_mode;
	}
	public void setPay_mode(String pay_mode) {
		this.pay_mode = pay_mode;
	}
	public String getPay_mode_next() {
		return pay_mode_next;
	}
	public void setPay_mode_next(String pay_mode_next) {
		this.pay_mode_next = pay_mode_next;
	}
	public Integer getPolicy_year() {
		return policy_year;
	}
	public void setPolicy_year(Integer policy_year) {
		this.policy_year = policy_year;
	}
	public Integer getPolicy_period() {
		return policy_period;
	}
	public void setPolicy_period(Integer policy_period) {
		this.policy_period = policy_period;
	}
	public Double getPeriod_prem() {
		return period_prem;
	}
	public void setPeriod_prem(Double period_prem) {
		this.period_prem = period_prem;
	}
	public String getMoney_id() {
		return money_id;
	}
	public void setMoney_id(String money_id) {
		this.money_id = money_id;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getBank_accName() {
		return bank_accName;
	}
	public void setBank_accName(String bank_accName) {
		this.bank_accName = bank_accName;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getOverdue_manage() {
		return overdue_manage;
	}
	public void setOverdue_manage(String overdue_manage) {
		this.overdue_manage = overdue_manage;
	}
	public Date getHold_date() {
		return hold_date;
	}
	public void setHold_date(Date hold_date) {
		this.hold_date = hold_date;
	}
	public Date getScan_time() {
		return scan_time;
	}
	public void setScan_time(Date scan_time) {
		this.scan_time = scan_time;
	}
	public Date getValidate_date() {
		return validate_date;
	}
	public void setValidate_date(Date validate_date) {
		this.validate_date = validate_date;
	}
	public Date getSign_date() {
		return sign_date;
	}
	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getPause_time() {
		return pause_time;
	}
	public void setPause_time(Date pause_time) {
		this.pause_time = pause_time;
	}
	public String getIs_answered() {
		return is_answered;
	}
	public void setIs_answered(String is_answered) {
		this.is_answered = is_answered;
	}
	public String getHigh_policy() {
		return high_policy;
	}
	public void setHigh_policy(String high_policy) {
		this.high_policy = high_policy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
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
	public Date getRtn_date() {
		return rtn_date;
	}
	public void setRtn_date(Date rtn_date) {
		this.rtn_date = rtn_date;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	
	public List<IContractLifePeopleVOModel> getHolderListView() {
		return holderListView;
	}
	public void setHolderListView(List<IContractLifePeopleVOModel> holderListView) {
		this.holderListView = holderListView;
	}
	public List<IContractLifePeopleVOModel> getInsurantListView() {
		return insurantListView;
	}
	public void setInsurantListView(
			List<IContractLifePeopleVOModel> insurantListView) {
		this.insurantListView = insurantListView;
	}
	public List<IContractLifePeopleVOModel> getBeneficiaryListView() {
		return beneficiaryListView;
	}
	public void setBeneficiaryListView(
			List<IContractLifePeopleVOModel> beneficiaryListView) {
		this.beneficiaryListView = beneficiaryListView;
	}
	public List<IContractLifeProductVOModel> getProductListView() {
		return productListView;
	}
	public void setProductListView(List<IContractLifeProductVOModel> productList) {
		this.productListView = productList;
	}
	public String[] getPolicyPeople_customer_id() {
		return policyPeople_customer_id;
	}
	public void setPolicyPeople_customer_id(String[] policyPeople_customer_id) {
		this.policyPeople_customer_id = policyPeople_customer_id;
	}
	public String[] getPolicyLifeInsured_customer_id() {
		return policyLifeInsured_customer_id;
	}
	public void setPolicyLifeInsured_customer_id(
			String[] policyLifeInsured_customer_id) {
		this.policyLifeInsured_customer_id = policyLifeInsured_customer_id;
	}
	public String[] getPolicyLifeInsured_relation1() {
		return policyLifeInsured_relation1;
	}
	public void setPolicyLifeInsured_relation1(String[] policyLifeInsured_relation1) {
		this.policyLifeInsured_relation1 = policyLifeInsured_relation1;
	}
	public String[] getPolicyLifeInsured_relation_code() {
		return policyLifeInsured_relation_code;
	}
	public void setPolicyLifeInsured_relation_code(
			String[] policyLifeInsured_relation_code) {
		this.policyLifeInsured_relation_code = policyLifeInsured_relation_code;
	}
	public String[] getPolicyLifeBeneficiary_customer_id() {
		return policyLifeBeneficiary_customer_id;
	}
	public void setPolicyLifeBeneficiary_customer_id(
			String[] policyLifeBeneficiary_customer_id) {
		this.policyLifeBeneficiary_customer_id = policyLifeBeneficiary_customer_id;
	}
	public String[] getPolicyLifeBeneficiary_insurant_name_value() {
		return policyLifeBeneficiary_insurant_name_value;
	}
	public void setPolicyLifeBeneficiary_insurant_name_value(
			String[] policyLifeBeneficiary_insurant_name_value) {
		this.policyLifeBeneficiary_insurant_name_value = policyLifeBeneficiary_insurant_name_value;
	}
	public String[] getPolicyLifeBeneficiary_relation1() {
		return policyLifeBeneficiary_relation1;
	}
	public void setPolicyLifeBeneficiary_relation1(
			String[] policyLifeBeneficiary_relation1) {
		this.policyLifeBeneficiary_relation1 = policyLifeBeneficiary_relation1;
	}
	public String[] getPolicyLifeBeneficiary_relation_code() {
		return policyLifeBeneficiary_relation_code;
	}
	public void setPolicyLifeBeneficiary_relation_code(
			String[] policyLifeBeneficiary_relation_code) {
		this.policyLifeBeneficiary_relation_code = policyLifeBeneficiary_relation_code;
	}
	public String[] getPolicyLifeBeneficiary_bene_type_code() {
		return policyLifeBeneficiary_bene_type_code;
	}
	public void setPolicyLifeBeneficiary_bene_type_code(
			String[] policyLifeBeneficiary_bene_type_code) {
		this.policyLifeBeneficiary_bene_type_code = policyLifeBeneficiary_bene_type_code;
	}
	public String[] getPolicyLifeBeneficiay_bene_order() {
		return policyLifeBeneficiay_bene_order;
	}
	public void setPolicyLifeBeneficiay_bene_order(
			String[] policyLifeBeneficiay_bene_order) {
		this.policyLifeBeneficiay_bene_order = policyLifeBeneficiay_bene_order;
	}
	public String[] getPolicyLifeBeneficiay_bene_rate() {
		return policyLifeBeneficiay_bene_rate;
	}
	public void setPolicyLifeBeneficiay_bene_rate(
			String[] policyLifeBeneficiay_bene_rate) {
		this.policyLifeBeneficiay_bene_rate = policyLifeBeneficiay_bene_rate;
	}
	public String[] getProduct_id_arr() {
		return product_id_arr;
	}
	public void setProduct_id_arr(String[] product_id_arr) {
		this.product_id_arr = product_id_arr;
	}
	public String[] getCoverage_period_arr() {
		return coverage_period_arr;
	}
	public void setCoverage_period_arr(String[] coverage_period_arr) {
		this.coverage_period_arr = coverage_period_arr;
	}
	public String[] getCoverage_year_arr() {
		return coverage_year_arr;
	}
	public void setCoverage_year_arr(String[] coverage_year_arr) {
		this.coverage_year_arr = coverage_year_arr;
	}
	public String[] getCharge_period_arr() {
		return charge_period_arr;
	}
	public void setCharge_period_arr(String[] charge_period_arr) {
		this.charge_period_arr = charge_period_arr;
	}
	public String[] getCharge_year_arr() {
		return charge_year_arr;
	}
	public void setCharge_year_arr(String[] charge_year_arr) {
		this.charge_year_arr = charge_year_arr;
	}
	public String[] getCharge_type_arr() {
		return charge_type_arr;
	}
	public void setCharge_type_arr(String[] charge_type_arr) {
		this.charge_type_arr = charge_type_arr;
	}
	public String[] getAmount_arr() {
		return amount_arr;
	}
	public void setAmount_arr(String[] amount_arr) {
		this.amount_arr = amount_arr;
	}
	public String[] getPeriod_prem_arr() {
		return period_prem_arr;
	}
	public void setPeriod_prem_arr(String[] period_prem_arr) {
		this.period_prem_arr = period_prem_arr;
	}
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public Integer getActual_prem() {
		return actual_prem;
	}
	public void setActual_prem(Integer actual_prem) {
		this.actual_prem = actual_prem;
	}
	public Integer getFee_id() {
		return fee_id;
	}
	public void setFee_id(Integer fee_id) {
		this.fee_id = fee_id;
	}
	public String getIns_type() {
		return ins_type;
	}
	public void setIns_type(String ins_type) {
		this.ins_type = ins_type;
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
	public String getPeriod_type() {
		return period_type;
	}
	public void setPeriod_type(String period_type) {
		this.period_type = period_type;
	}
	public Date getDue_time() {
		return due_time;
	}
	public void setDue_time(Date due_time) {
		this.due_time = due_time;
	}
	public Date getReceived_date() {
		return received_date;
	}
	public void setReceived_date(Date received_date) {
		this.received_date = received_date;
	}
	public String getFee_status() {
		return fee_status;
	}
	public void setFee_status(String fee_status) {
		this.fee_status = fee_status;
	}
	public String getPosted() {
		return posted;
	}
	public void setPosted(String posted) {
		this.posted = posted;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public Integer getCred_id() {
		return cred_id;
	}
	public void setCred_id(Integer cred_id) {
		this.cred_id = cred_id;
	}
	public Date getPost_time() {
		return post_time;
	}
	public void setPost_time(Date post_time) {
		this.post_time = post_time;
	}
	public Integer getPrem_id() {
		return prem_id;
	}
	public void setPrem_id(Integer prem_id) {
		this.prem_id = prem_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getNext_due_time() {
		return next_due_time;
	}
	public void setNext_due_time(Date next_due_time) {
		this.next_due_time = next_due_time;
	}
	public String getInsure_stop() {
		return insure_stop;
	}
	public void setInsure_stop(String insure_stop) {
		this.insure_stop = insure_stop;
	}
	public Integer getAuth_payAge() {
		return auth_payAge;
	}
	public void setAuth_payAge(Integer auth_payAge) {
		this.auth_payAge = auth_payAge;
	}
	public String getAuth_draw() {
		return auth_draw;
	}
	public void setAuth_draw(String auth_draw) {
		this.auth_draw = auth_draw;
	}
	public Double getAuth_firstPay() {
		return auth_firstPay;
	}
	public void setAuth_firstPay(Double auth_firstPay) {
		this.auth_firstPay = auth_firstPay;
	}
	public String getDividend_choice() {
		return dividend_choice;
	}
	public void setDividend_choice(String dividend_choice) {
		this.dividend_choice = dividend_choice;
	}
	public String getIs_autoRen() {
		return is_autoRen;
	}
	public void setIs_autoRen(String is_autoRen) {
		this.is_autoRen = is_autoRen;
	}

	public String getResult_flag() {
		return result_flag;
	}
	public void setResult_flag(String result_flag) {
		this.result_flag = result_flag;
	}
	public String[] getProduct_insurant_id() {
		return product_insurant_id;
	}
	public void setProduct_insurant_id(String[] product_insurant_id) {
		this.product_insurant_id = product_insurant_id;
	}
	public String[] getAuth_payAge_arr() {
		return auth_payAge_arr;
	}
	public void setAuth_payAge_arr(String[] auth_payAge_arr) {
		this.auth_payAge_arr = auth_payAge_arr;
	}
	public String[] getAuth_draw_arr() {
		return auth_draw_arr;
	}
	public void setAuth_draw_arr(String[] auth_draw_arr) {
		this.auth_draw_arr = auth_draw_arr;
	}
	public String[] getAuth_firstPay_arr() {
		return auth_firstPay_arr;
	}
	public void setAuth_firstPay_arr(String[] auth_firstPay_arr) {
		this.auth_firstPay_arr = auth_firstPay_arr;
	}
	public String[] getDividend_choice_arr() {
		return dividend_choice_arr;
	}
	public void setDividend_choice_arr(String[] dividend_choice_arr) {
		this.dividend_choice_arr = dividend_choice_arr;
	}
	public String[] getIs_autoRen_arr() {
		return is_autoRen_arr;
	}
	public void setIs_autoRen_arr(String[] is_autoRen_arr) {
		this.is_autoRen_arr = is_autoRen_arr;
	}
	
}
