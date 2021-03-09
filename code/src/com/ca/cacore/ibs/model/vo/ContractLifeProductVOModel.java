package com.ca.cacore.ibs.model.vo;

import java.sql.Date;
/**
 * 
 * @author xxz521
 *
 */
public class ContractLifeProductVOModel implements IContractLifeProductVOModel {
	private Integer	seq_id	;//主键
	private Integer	item_id	;//产品项目id
	private String	branch_id;	//销售机构 
	private String	insBranch_id;//保险公司机构
	private Long policy_id;//保单id	
	private String	send_code;//投保单号码	
	private String	policy_code;//保单号码	
	private String	app_id	;//投保人	
	private String	insurant_id;//第一被保人	
	private String	relation_id	;// 投保人与主被保人关系
	private String	product_id	;// 产品代码
	private String	ins_type	;//主附险标志 
	private String	product_type	;// 产品分类1（产品大类）
	private String	product_type2	;// 产品分类2
	private String	product_type3	;//产品分类3（保障类型，财务用）
	private String	sell_way	;// 销售方式
	private String	charge_type	;//当期缴费方式
	private String	charge_next	;//下期缴费方式
	private String	charge_period	;//缴费期限类型
	private String	pay_mode	;//当期付款方式
	private String	pay_mode_next	;//下期付款方式	
	private Integer	policy_year	;//保单年度 
	private Integer charge_year;//缴费年限
	private String	coverage_period	;//保障期限类型	
	private Integer coverage_year;//保障年数
	private String	period_type	;//保障期限分类	
	private Integer 	unit;//份数	
	private Double  amount	;//保额	
	private Double 	period_prem	;//保费
	private String 	money_id	;//币种
	private String	overdue_manage	;// 保险费逾期未付的选择
	private Date hold_date	; // 投保日期	      
	private Date scan_time	;            //	影像上传日期	
	private Date validate_date	;          //  	保单生效日期	
	private Date due_time	   ;            //  	本次应收日期	
	private Date next_due_time	;              // 	下次应收日期	
	private Date sign_date	    ;      // 	客户签收日期	
	private Date rtn_date	   ;        // 	回执核销日期	
	private Date end_date	    ;        //	保单终止日期	
	private Date pause_time	 ;        //	保单停效日期	
	private String  is_answered	;//      	是否已回访	  
	private String  high_policy	;//        	是否高额件	  
	private String insure_stop	;//        	是否终止	    
	private String ins_status	;//                	险种状态	    
	private String remark	;//                  	备注	        
	private String createuser	;//      	创建人	      
	private Date   createdate	;//创建时间	    
	private String modifyuser	;//    	最后修改人	  
	private Date   modifydate	;//	最后修改时间	
	
	//寿险保单费用分险种明细信息
	private Integer	fee_id	;//费用id
	private String	agent_id;	//销售人员 
	private String	service_id;//服务人员代码
	private Integer policy_period; //  缴费次数
	private String fee_type;//费用业务类型
	private Double actual_prem;//本期实缴保费
	private String bank_code;//付款开户银行
	private String bank_account;//付款银行账号
	private Date received_date;//应收回销日期
	private String fee_status;//费用处理状态
	private String posted;//是否以生成记账
	private String post_id;//记账确认人员id
	private Integer cred_id;//记账凭证号
	private Date post_time;//记账凭证生成时间
	private String createUser	;// 创建人	      
	private Date   createDate	;//创建时间	    
	private String modifyUser	;// 最后修改人	  
	private Date   modifyDate	;//最后修改时间
	private Integer prem_id;//
	private String	customer_id;//付费人
	private Integer auth_payAge;//年金领取年龄
	private String auth_draw;//年金领取方式
	private Double auth_firstPay;//首期领取金额
	private String dividend_choice;//红利领取方式
    private String is_autoRen;//是否可以续保
    
    private String product_name;
    private String ap;
    private String insurant_name;
    private String charge_period_name;
    private String charge_type_name;
    private String renew;//产品是否自动续保
    
    private Double fee_rate;//比例 
    private Double fee;//手续费
    private Integer log_seq_id;//	备份数据主键;
	 private String log_bustype;//	备份业务类型;
	 private Date log_busdate;//	备份业务归属时间;
	 private Date log_date;//	备份时间;
	 private String log_remark;//	备份备注;
	 
	public Integer getLog_seq_id() {
		return log_seq_id;
	}
	public void setLog_seq_id(Integer log_seq_id) {
		this.log_seq_id = log_seq_id;
	}
	public String getLog_bustype() {
		return log_bustype;
	}
	public void setLog_bustype(String log_bustype) {
		this.log_bustype = log_bustype;
	}
	public Date getLog_busdate() {
		return log_busdate;
	}
	public void setLog_busdate(Date log_busdate) {
		this.log_busdate = log_busdate;
	}
	public Date getLog_date() {
		return log_date;
	}
	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}
	public String getLog_remark() {
		return log_remark;
	}
	public void setLog_remark(String log_remark) {
		this.log_remark = log_remark;
	}
	public Double getFee_rate() {
		return fee_rate;
	}
	public void setFee_rate(Double fee_rate) {
		this.fee_rate = fee_rate;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public String getRenew() {
		return renew;
	}
	public void setRenew(String renew) {
		this.renew = renew;
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
	public String getInsurant_name() {
		return insurant_name;
	}
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}
	public String getAp() {
		return ap;
	}
	public void setAp(String ap) {
		this.ap = ap;
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
	public Long getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
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
	public String getProduct_type3() {
		return product_type3;
	}
	public void setProduct_type3(String product_type3) {
		this.product_type3 = product_type3;
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
	public String getInsure_stop() {
		return insure_stop;
	}
	public void setInsure_stop(String insure_stop) {
		this.insure_stop = insure_stop;
	}
	public String getIns_status() {
		return ins_status;
	}
	public void setIns_status(String ins_status) {
		this.ins_status = ins_status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	public Integer getFee_id() {
		return fee_id;
	}
	public void setFee_id(Integer fee_id) {
		this.fee_id = fee_id;
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
	public Integer getPolicy_period() {
		return policy_period;
	}
	public void setPolicy_period(Integer policy_period) {
		this.policy_period = policy_period;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public Double getActual_prem() {
		return actual_prem;
	}
	public void setActual_prem(Double actual_prem) {
		this.actual_prem = actual_prem;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
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
}
