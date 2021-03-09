package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

/**
 * 
 * @author xxz521
 *
 */
public class ContractLifePremModel implements IContractLifePremModel {
	private Integer prem_id;//
	private Integer	seq_id	;//主键
	private String	branch_id;	//销售机构 
	private String	insBranch_id;//保险公司机构
	private Integer policy_id;//保单id	
	private String	send_code;//投保单号码	
	private String	policy_code;//保单号码	
	private String	agent_id;	//销售人员 
	private String	service_id;//服务人员代码
	private String	customer_id;//付费人
	private String insurant_id;//分单被保险人
	private Integer policy_year; // 保单年度
	private Integer policy_period; //  缴费次数
	private String fee_type;//费用业务类型
	private String money_id;//投保货币代码
	private String pay_mode; // 当期付款方式
	private String bank_code;//付款开户银行
	private String bank_account;//付款银行账号
	private Double period_prem;//本期应缴保费
	private Double actual_prem;//本期实缴保费
	private Date due_time;//本次应收日期
	private String fee_status;//费用处理状态
	private String remark	;//  备注	        
	private String createUser	;// 创建人	      
	private Date   createDate	;//创建时间	    
	private String modifyUser	;// 最后修改人	  
	private Date   modifyDate	;//最后修改时间	
	private String	product_id	;// 产品代码
	
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public Integer getPrem_id() {
		return prem_id;
	}
	public void setPrem_id(Integer prem_id) {
		this.prem_id = prem_id;
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
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public Integer getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Integer policy_id) {
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
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getInsurant_id() {
		return insurant_id;
	}
	public void setInsurant_id(String insurant_id) {
		this.insurant_id = insurant_id;
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
	public String getMoney_id() {
		return money_id;
	}
	public void setMoney_id(String money_id) {
		this.money_id = money_id;
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
	public Date getDue_time() {
		return due_time;
	}
	public void setDue_time(Date due_time) {
		this.due_time = due_time;
	}
	public String getFee_status() {
		return fee_status;
	}
	public void setFee_status(String fee_status) {
		this.fee_status = fee_status;
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
