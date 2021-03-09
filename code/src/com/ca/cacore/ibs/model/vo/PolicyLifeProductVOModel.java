package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

public class PolicyLifeProductVOModel implements IPolicyLifeProductVOModel {
	private Integer seq_id; // 序号
	private Long policy_id;
	private Integer item_id;//产品项目id
	private String branch_id; // 机构代码 Ref：SYS_Branch
	private String insBranch_id; // 保险公司机构
	private String send_code; // 投保单号码
	private String agent_id; // 保单销售人员 REF: SMIS_Sales
	private String policy_code; // 保单号
	private String service_id; // 保单服务人员 REF:SMIS_Sales
	private String app_id;// 投保人
	private String insurant_id; // 第一被保人 REF:CBS_Customer
	private String relation_id; // 投保人与被保人关系 REF:CBS_Applicant_Relation1
	private String product_id;// 险种代码Ref：PDT_Product_Llife';
	private String product_name;// 险种代码Ref：PDT_Product_Llife';
	private String ins_type;// 主附险标志
	private String product_type; // 产品分类1（产品大类）
	private String product_type2;// 产品分类2
	private String product_type3;//产品分类3
	private String sell_way; // 消费方式 Ref：CBS_Policy_Sell_Way
	private String charge_type; // 当期缴费方式 Ref：PDT_Lib_ChargeType
	private String charge_next; // 续期缴费方式 Ref：PDT_Lib_ChargeType
	private String charge_period;// 缴费期限类型
	private String pay_mode; // 当期付款方式 Ref：SYS_Library_PayMode
	private String pay_mode_next; // 续期付款方式 Ref：SYS_Library_PayMode
	private Integer policy_year;// 保单年度
	private Integer charge_year; // 缴费年限
	private String coverage_period;// 保障期限类型
	private Integer coverage_year;// 保障年数
	private String period_type; // 保险期限类型
	private Integer unit;// 份数
	private Double amount;// 保额
	private Double period_prem; // 保费
	private String money_id;// 币种
	private String overdue_manage;// 保险费逾期未付的选择
	private String hold_date;// 投保日期
	private Date scan_time; // 影像上传日期
	private Date validate_date; // 保单生效日期
	private Date due_time; // 本次应收日期
	private Date next_due_time; // 下次应收日期
	private Date sign_date;// 客户签收日期
	private Date rtn_date; // 回执核销日期
	private Date end_date; // 保单终止日期
	private Date pause_time; // 保单停效日期
	private String is_answered; // 是否已回访 Y是N否
	private String high_policy; // 是否高额件 Y是N否
	private String ins_status; // 险种状态Ref：CBS_Policy_ProductStatus
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
	private String ap; // 拼接字符串
	private String charge_type_name;// 缴费方式名称
	private String charge_period_name;// 缴费期限
	private String insurant_name;//
	private String insure_stop;//是否终止
	private String remark;//备注
	//产品表新增
	
	private Integer auth_payAge;//年金领取年龄
	private String auth_draw;//年金领取方式
	private Double auth_firstPay;//首期领取金额
	private String dividend_choice;//红利领取方式
    private String is_autoRen;//是否可以续保
    private String renew;//产品表中的是否自动续保
	
	public String getRenew() {
		return renew;
	}
	public void setRenew(String renew) {
		this.renew = renew;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInsure_stop() {
		return insure_stop;
	}
	public void setInsure_stop(String insure_stop) {
		this.insure_stop = insure_stop;
	}
	public String getProduct_type3() {
		return product_type3;
	}
	public void setProduct_type3(String product_type3) {
		this.product_type3 = product_type3;
	}
	public Long getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
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
	public String getSend_code() {
		return send_code;
	}
	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getPolicy_code() {
		return policy_code;
	}
	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
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
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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
	public String getCharge_period() {
		return charge_period;
	}
	public void setCharge_period(String charge_period) {
		this.charge_period = charge_period;
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
	public Integer getCharge_year() {
		return charge_year;
	}
	public void setCharge_year(Integer charge_year) {
		this.charge_year = charge_year;
	}
	public String getCoverage_period() {
		return coverage_period;
	}
	public void setCoverage_period(String coverage_period) {
		this.coverage_period = coverage_period;
	}
	public Integer getCoverage_year() {
		return coverage_year;
	}
	public void setCoverage_year(Integer coverage_year) {
		this.coverage_year = coverage_year;
	}
	public String getPeriod_type() {
		return period_type;
	}
	public void setPeriod_type(String period_type) {
		this.period_type = period_type;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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
	public String getOverdue_manage() {
		return overdue_manage;
	}
	public void setOverdue_manage(String overdue_manage) {
		this.overdue_manage = overdue_manage;
	}
	public String getHold_date() {
		return hold_date;
	}
	public void setHold_date(String hold_date) {
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
	public Date getDue_time() {
		return due_time;
	}
	public void setDue_time(Date due_time) {
		this.due_time = due_time;
	}
	public Date getNext_due_time() {
		return next_due_time;
	}
	public void setNext_due_time(Date next_due_time) {
		this.next_due_time = next_due_time;
	}
	public Date getSign_date() {
		return sign_date;
	}
	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}
	public Date getRtn_date() {
		return rtn_date;
	}
	public void setRtn_date(Date rtn_date) {
		this.rtn_date = rtn_date;
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
	public String getIns_status() {
		return ins_status;
	}
	public void setIns_status(String ins_status) {
		this.ins_status = ins_status;
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
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public String getAp() {
		return ap;
	}
	public void setAp(String ap) {
		this.ap = ap;
	}
	public String getCharge_type_name() {
		return charge_type_name;
	}
	public void setCharge_type_name(String charge_type_name) {
		this.charge_type_name = charge_type_name;
	}
	public String getCharge_period_name() {
		return charge_period_name;
	}
	public void setCharge_period_name(String charge_period_name) {
		this.charge_period_name = charge_period_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getInsurant_name() {
		return insurant_name;
	}
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}
	
}
