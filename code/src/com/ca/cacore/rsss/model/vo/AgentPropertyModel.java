package com.ca.cacore.rsss.model.vo;


import java.math.BigDecimal;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class AgentPropertyModel implements IAgentPropertyModel{
	
	private String branch_id; //中介公司id	
	private String org_id;//保险公司id
	private String risk_id; //险种id	
	private String risk_name; //险种名称	
	private BigDecimal policy_count;//业务笔数（保单件数）-本期
	private BigDecimal amount;//保障额度/赔偿限额 -本期
	private BigDecimal premium; //保费金额 -本期
	private BigDecimal premium_payable; //应付保费 -本期
	private BigDecimal agentcommission; //代理佣金	-本期
	private BigDecimal autotrophy_premium; //自营网络平台渠道保费金额 -本期	
	private BigDecimal autotrophy_agentcommission; //自营网络平台渠道代理佣金 -本期	
	private BigDecimal thirdparty_premium; //第三方网络平台渠道保费金额 -本期	 
	private BigDecimal thirdparty_agentcommission; //第三方网络平台渠道代理佣金 -本期	
	private BigDecimal policy_count_sum;//业务笔数（保单件数）-累计
	private BigDecimal amount_sum;//保障额度/赔偿限额 -累计
	private BigDecimal premium_sum; //保费金额 -累计
	private BigDecimal premium_payable_sum; //应付保费 -累计
	private BigDecimal agentcommission_sum; //代理佣金 -累计	
	private BigDecimal autotrophy_premium_sum; //自营网络平台渠道保费金额 -累计	
	private BigDecimal autotrophy_agentcommission_sum; //自营网络平台渠道代理佣金 -累计	
	private BigDecimal thirdparty_premium_sum; //第三方网络平台渠道保费金额 -累计	
	private BigDecimal thirdparty_agentcommission_sum; //第三方网络平台渠道代理佣金 -累计	
	private String term; //年月	
	private String sign_path;//统计口径 0签单1结算
	private String dept_list;//权限
	private BigDecimal total_policy_count;//业务笔数（保单件数）-本期
	private BigDecimal total_amount;//保障额度/赔偿限额 -本期
	private BigDecimal total_permium;//总保费金额 -本期
	private BigDecimal total_ppayable;//总应付保费 -本期
	private BigDecimal total_agcommiss; //总代理佣金 -本期	
	private BigDecimal total_autoprem; //自营网络平台渠道保费金额 -本期	
	private BigDecimal total_autoagcommiss; //自营网络平台渠道代理佣金 -本期	
	private BigDecimal total_trdprem; //第三方网络平台渠道保费金额 -本期	
	private BigDecimal total_trdagcommiss; //第三方网络平台渠道代理佣金 -本期	
	private BigDecimal total_policycount_sum;//业务笔数（保单件数）-累计
	private BigDecimal total_amount_sum;//保障额度/赔偿限额 -累计
	private BigDecimal total_permium_sum;//总保费金额 -累计
	private BigDecimal total_ppayable_sum;//总应付保费 -累计
	private BigDecimal total_agcommiss_sum; //总代理佣金 -累计	
	private BigDecimal total_autoprem_sum; //自营网络平台渠道保费金额 -累计	
	private BigDecimal total_autoagcommiss_sum; //自营网络平台渠道代理佣金 -累计	
	private BigDecimal total_trdprem_sum; //第三方网络平台渠道保费金额 -累计	
	private BigDecimal total_trdagcommiss_sum; //第三方网络平台渠道代理佣金 -累计
	
	private PageCount pageCount = new PageCount();

	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getRisk_id() {
		return risk_id;
	}
	public void setRisk_id(String risk_id) {
		this.risk_id = risk_id;
	}
	public String getRisk_name() {
		return risk_name;
	}
	public void setRisk_name(String risk_name) {
		this.risk_name = risk_name;
	}
	public BigDecimal getPremium() {
		return premium;
	}
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	public BigDecimal getPremium_payable() {
		return premium_payable;
	}
	public void setPremium_payable(BigDecimal premium_payable) {
		this.premium_payable = premium_payable;
	}
	public BigDecimal getAgentcommission() {
		return agentcommission;
	}
	public void setAgentcommission(BigDecimal agentcommission) {
		this.agentcommission = agentcommission;
	}
	public BigDecimal getAutotrophy_premium() {
		return autotrophy_premium;
	}
	public void setAutotrophy_premium(BigDecimal autotrophy_premium) {
		this.autotrophy_premium = autotrophy_premium;
	}
	public BigDecimal getAutotrophy_agentcommission() {
		return autotrophy_agentcommission;
	}
	public void setAutotrophy_agentcommission(BigDecimal autotrophy_agentcommission) {
		this.autotrophy_agentcommission = autotrophy_agentcommission;
	}
	public BigDecimal getThirdparty_premium() {
		return thirdparty_premium;
	}
	public void setThirdparty_premium(BigDecimal thirdparty_premium) {
		this.thirdparty_premium = thirdparty_premium;
	}
	public BigDecimal getThirdparty_agentcommission() {
		return thirdparty_agentcommission;
	}
	public void setThirdparty_agentcommission(BigDecimal thirdparty_agentcommission) {
		this.thirdparty_agentcommission = thirdparty_agentcommission;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getSign_path() {
		return sign_path;
	}
	public void setSign_path(String sign_path) {
		this.sign_path = sign_path;
	}
	@Override
	public PageCount getPageCount() {
		// TODO Auto-generated method stub
		return pageCount;
	}
	@Override
	public void setPageCount(PageCount pageCount) {
		// TODO Auto-generated method stub
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		// TODO Auto-generated method stub
		return this.getPageCount().getLimit();
	}
	
	public String getDept_list() {
		return dept_list;
	}
	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}
	
	public BigDecimal getTotal_permium() {
		return total_permium;
	}
	public void setTotal_permium(BigDecimal total_permium) {
		this.total_permium = total_permium;
	}
	public BigDecimal getTotal_ppayable() {
		return total_ppayable;
	}
	public void setTotal_ppayable(BigDecimal total_ppayable) {
		this.total_ppayable = total_ppayable;
	}
	public BigDecimal getTotal_agcommiss() {
		return total_agcommiss;
	}
	public void setTotal_agcommiss(BigDecimal total_agcommiss) {
		this.total_agcommiss = total_agcommiss;
	}
	public BigDecimal getTotal_autoprem() {
		return total_autoprem;
	}
	public void setTotal_autoprem(BigDecimal total_autoprem) {
		this.total_autoprem = total_autoprem;
	}
	public BigDecimal getTotal_autoagcommiss() {
		return total_autoagcommiss;
	}
	public void setTotal_autoagcommiss(BigDecimal total_autoagcommiss) {
		this.total_autoagcommiss = total_autoagcommiss;
	}
	public BigDecimal getTotal_trdprem() {
		return total_trdprem;
	}
	public void setTotal_trdprem(BigDecimal total_trdprem) {
		this.total_trdprem = total_trdprem;
	}
	public BigDecimal getTotal_trdagcommiss() {
		return total_trdagcommiss;
	}
	public void setTotal_trdagcommiss(BigDecimal total_trdagcommiss) {
		this.total_trdagcommiss = total_trdagcommiss;
	}
	public BigDecimal getPolicy_count() {
		return policy_count;
	}
	public void setPolicy_count(BigDecimal policy_count) {
		this.policy_count = policy_count;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getPolicy_count_sum() {
		return policy_count_sum;
	}
	public void setPolicy_count_sum(BigDecimal policy_count_sum) {
		this.policy_count_sum = policy_count_sum;
	}
	public BigDecimal getAmount_sum() {
		return amount_sum;
	}
	public void setAmount_sum(BigDecimal amount_sum) {
		this.amount_sum = amount_sum;
	}
	public BigDecimal getPremium_sum() {
		return premium_sum;
	}
	public void setPremium_sum(BigDecimal premium_sum) {
		this.premium_sum = premium_sum;
	}
	public BigDecimal getPremium_payable_sum() {
		return premium_payable_sum;
	}
	public void setPremium_payable_sum(BigDecimal premium_payable_sum) {
		this.premium_payable_sum = premium_payable_sum;
	}
	public BigDecimal getAgentcommission_sum() {
		return agentcommission_sum;
	}
	public void setAgentcommission_sum(BigDecimal agentcommission_sum) {
		this.agentcommission_sum = agentcommission_sum;
	}
	public BigDecimal getAutotrophy_premium_sum() {
		return autotrophy_premium_sum;
	}
	public void setAutotrophy_premium_sum(BigDecimal autotrophy_premium_sum) {
		this.autotrophy_premium_sum = autotrophy_premium_sum;
	}
	public BigDecimal getAutotrophy_agentcommission_sum() {
		return autotrophy_agentcommission_sum;
	}
	public void setAutotrophy_agentcommission_sum(
			BigDecimal autotrophy_agentcommission_sum) {
		this.autotrophy_agentcommission_sum = autotrophy_agentcommission_sum;
	}
	public BigDecimal getThirdparty_premium_sum() {
		return thirdparty_premium_sum;
	}
	public void setThirdparty_premium_sum(BigDecimal thirdparty_premium_sum) {
		this.thirdparty_premium_sum = thirdparty_premium_sum;
	}
	public BigDecimal getThirdparty_agentcommission_sum() {
		return thirdparty_agentcommission_sum;
	}
	public void setThirdparty_agentcommission_sum(
			BigDecimal thirdparty_agentcommission_sum) {
		this.thirdparty_agentcommission_sum = thirdparty_agentcommission_sum;
	}
	public BigDecimal getTotal_policy_count() {
		return total_policy_count;
	}
	public void setTotal_policy_count(BigDecimal total_policy_count) {
		this.total_policy_count = total_policy_count;
	}
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	public BigDecimal getTotal_policycount_sum() {
		return total_policycount_sum;
	}
	public void setTotal_policycount_sum(BigDecimal total_policycount_sum) {
		this.total_policycount_sum = total_policycount_sum;
	}
	public BigDecimal getTotal_amount_sum() {
		return total_amount_sum;
	}
	public void setTotal_amount_sum(BigDecimal total_amount_sum) {
		this.total_amount_sum = total_amount_sum;
	}
	public BigDecimal getTotal_permium_sum() {
		return total_permium_sum;
	}
	public void setTotal_permium_sum(BigDecimal total_permium_sum) {
		this.total_permium_sum = total_permium_sum;
	}
	public BigDecimal getTotal_ppayable_sum() {
		return total_ppayable_sum;
	}
	public void setTotal_ppayable_sum(BigDecimal total_ppayable_sum) {
		this.total_ppayable_sum = total_ppayable_sum;
	}
	public BigDecimal getTotal_agcommiss_sum() {
		return total_agcommiss_sum;
	}
	public void setTotal_agcommiss_sum(BigDecimal total_agcommiss_sum) {
		this.total_agcommiss_sum = total_agcommiss_sum;
	}
	public BigDecimal getTotal_autoprem_sum() {
		return total_autoprem_sum;
	}
	public void setTotal_autoprem_sum(BigDecimal total_autoprem_sum) {
		this.total_autoprem_sum = total_autoprem_sum;
	}
	public BigDecimal getTotal_autoagcommiss_sum() {
		return total_autoagcommiss_sum;
	}
	public void setTotal_autoagcommiss_sum(BigDecimal total_autoagcommiss_sum) {
		this.total_autoagcommiss_sum = total_autoagcommiss_sum;
	}
	public BigDecimal getTotal_trdprem_sum() {
		return total_trdprem_sum;
	}
	public void setTotal_trdprem_sum(BigDecimal total_trdprem_sum) {
		this.total_trdprem_sum = total_trdprem_sum;
	}
	public BigDecimal getTotal_trdagcommiss_sum() {
		return total_trdagcommiss_sum;
	}
	public void setTotal_trdagcommiss_sum(BigDecimal total_trdagcommiss_sum) {
		this.total_trdagcommiss_sum = total_trdagcommiss_sum;
	}
	
	
}
