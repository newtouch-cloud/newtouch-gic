package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class ContractLifeModel implements IContractLifeModel {
	private Integer seq_id; // 序号
	private String branch_id; // 机构代码 Ref：SYS_Branch
    private String branch_name; // 机构
	private String insBranch_id; // 保险公司机构
	private String insBranch_name; // 保险公司机构
	private String send_code; // 投保单号码
	private String policy_code; // 保单号
	private Long policy_id; // 保单号
	private String agent_id; // 保单销售人员 REF: SMIS_Sales
	private String service_id; // 保单服务人员 REF:SMIS_Sales
	private String holder_id; // 投保人 REF:CBS_Customer
	private String app_name; // 投保人姓名
	private String insurant_id; // 第一被保人 REF:CBS_Customer
	private String relation_id; // 投保人与被保人关系 REF:CBS_Applicant_Relation1
	private String sell_way; // 消费方式 Ref：CBS_Policy_Sell_Way
	private String charge_type; // 当期缴费方式 Ref：PDT_Lib_ChargeType
	private String charge_next; // 续期缴费方式 Ref：PDT_Lib_ChargeType
	private String pay_mode; //当期付款方式 Ref：SYS_Library_PayMode
	private String pay_mode_next; // 续期付款方式 Ref：SYS_Library_PayMode
	private String policy_year; // 保单年度
	private String policy_period; // 缴费期次
	private String period_prem; // 保费合计
	private String money_id; // 币种 Ref：SYS_Library_MoneyType
	private String bank_code; // 开户银行代码
	private String account_type; // 银行账号类型 Ref：SYS_Library_BankAccountType
	private String bank_accName; // 银行开户人
	private String bank_account; // 银行账号
	private String overdue_manage; // 保险费逾期未付的选择 Ref：CBS_PolicyLife_Overdue
	private Date hold_date; // 投保日期
	private Date scan_time; // 影像上传日期
	private Date validate_date; // 保单生效日期
	private Date due_time; // 本次应收日期
	private Date next_due_time; // 下次应收日期
	private Date sign_date; // 客户签收日期
	private Date rtn_date; // 回执核销日期
	private Date end_date; // 保单终止日期
	private Date pause_time; // 保单停效日期
	private String is_answered; // 是否已回访 Y是N否
	private String high_policy; // 是否高额件 Y是N否
	private String status; // 投保单状态 Ref：CBS_Policy_Status
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
	private PageCount pageCount = new PageCount();
	
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
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getInsBranch_name() {
		return insBranch_name;
	}
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public String getSend_code() {
		return send_code;
	}
	public void setSend_code(String send_code) {
		this.send_code= send_code;
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
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getHolder_id() {
		return holder_id;
	}
	public void setHolder_id(String holder_id) {
		this.holder_id = holder_id;
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
	public String getPolicy_year() {
		return policy_year;
	}
	public void setPolicy_year(String policy_year) {
		this.policy_year = policy_year;
	}
	public String getPolicy_period() {
		return policy_period;
	}
	public void setPolicy_period(String policy_period) {
		this.policy_period = policy_period;
	}
	public String getPeriod_prem() {
		return period_prem;
	}
	public void setPeriod_prem(String period_prem) {
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
	
	@Override
	public PageCount getPageCount() {
		return this.pageCount;
	}

	@Override
	public void setPageCount(PageCount pageCount) {
		this.pageCount=pageCount;
	}

	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}

	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
}
