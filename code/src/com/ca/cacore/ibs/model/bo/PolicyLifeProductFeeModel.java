package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

/**
 * 寿险投保单费用分险种明细信息
 * @author xxz521
 *
 */
public class PolicyLifeProductFeeModel implements IPolicyLifeProductFeeModel {
	private Integer	seq_id	;//主键
	private Integer	fee_id	;//费用id
	private String	branch_id;	//销售机构 
	private String	insBranch_id;//保险公司机构
	private Long policy_id;//保单id	
	private String	send_code;//投保单号码	
	private String	agent_id;	//销售人员 
	private String	service_id;//服务人员代码
	private String	product_id	;// 产品代码
	private String	ins_type	;//主附险标志 
	private String	product_type	;// 产品分类1（产品大类）
	private String	product_type2	;// 产品分类2
	private String	product_type3	;//产品分类3（保障类型，财务用）
	private String	coverage_period	;//保障期限类型	
	private String	period_type	;//保障期限分类	
	private String	sell_way	;// 销售方式
	private String	charge_type	;//缴费方式
	private Integer charge_year;//缴费年限
	private Integer policy_year	;//保单年度 
	private Integer policy_period; //  缴费次数
	private String fee_type;//费用业务类型
	private Double period_prem;//本期应缴保费
	private Double actual_prem;//本期实缴保费
	private String pay_mode; // 付款方式
	private String money_id;//投保货币代码
	private String bank_code;//付款开户银行
	private String bank_account;//付款银行账号
	private Date hold_date;//投保日期
	private Date due_time;//本次应收日期
	private Date received_date;//应收回销日期
	private String fee_status;//费用处理状态
	private String posted;//是否以生成记账
	private String post_id;//记账确认人员id
	private Integer cred_id;//记账凭证号
	private Date post_time;//记账凭证生成时间
	private String remark	;//  备注	        
	private String createUser	;// 创建人	      
	private Date   createDate	;//创建时间	    
	private String modifyUser	;// 最后修改人	  
	private Date   modifyDate	;//最后修改时间	
	
	public String getMoney_id() {
		return money_id;
	}
	public void setMoney_id(String money_id) {
		this.money_id = money_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public Integer getFee_id() {
		return fee_id;
	}
	public void setFee_id(Integer fee_id) {
		this.fee_id = fee_id;
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
	public String getCoverage_period() {
		return coverage_period;
	}
	public void setCoverage_period(String coverage_period) {
		this.coverage_period = coverage_period;
	}
	public String getPeriod_type() {
		return period_type;
	}
	public void setPeriod_type(String period_type) {
		this.period_type = period_type;
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
	public Integer getCharge_year() {
		return charge_year;
	}
	public void setCharge_year(Integer charge_year) {
		this.charge_year = charge_year;
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
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public Double getPeriod_prem() {
		return period_prem;
	}
	public void setPeriod_prem(Double period_prem) {
		this.period_prem = period_prem;
	}
	public Double getActual_prem() {
		return actual_prem;
	}
	public void setActual_prem(Double actual_prem) {
		this.actual_prem = actual_prem;
	}
	public String getPay_mode() {
		return pay_mode;
	}
	public void setPay_mode(String pay_mode) {
		this.pay_mode = pay_mode;
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
	public Date getHold_date() {
		return hold_date;
	}
	public void setHold_date(Date hold_date) {
		this.hold_date = hold_date;
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
}
