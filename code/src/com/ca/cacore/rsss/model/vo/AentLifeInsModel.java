package com.ca.cacore.rsss.model.vo;

import java.math.BigDecimal;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class AentLifeInsModel implements IAentLifeInsModel{
	
	private String branch_id; //中介公司id     
	private String org_id;//保险公司id
	private String risk_id; //险种id    
	private String risk_name; //险种名称            
	private BigDecimal policy_count; //新单保障对象数量 -本期
	private BigDecimal amount; //新单保障额度/赔偿限额  -本期               
	private BigDecimal newpolicyfee; //新保单保费金额 -本期            
	private BigDecimal renewalfee; //续期保单金额 -本期              
	private BigDecimal premium; //应付保费  -本期              
	private BigDecimal newpolicy_agcommiss; //新单代理佣金 -本期     
	private BigDecimal renewal_agcommiss; //续期代理佣金 -本期       
	private BigDecimal autotro_newpolicyfee; //自营网络平台渠道新单保费金额 -本期    
	private BigDecimal autotro_renewalfee; //自营网络平台渠道续期保单金额 -本期      
	private BigDecimal autotro_newpolicy_agcommiss; //自营网络平台渠道新单代理佣金 -本期
	private BigDecimal autotro_renewal_agcommiss; //自营网络平台渠道续期代理佣金 -本期  
	private BigDecimal tdparty_newpolicyfee; //第三方网络平台渠道新单保费金额 -本期 
	private BigDecimal tdparty_renewalfee; //第三方网络平台渠道续期保单金额 -本期        
	private BigDecimal tdparty_newpolicy_agcommiss; //第三方网络平台渠道新单代理佣金 -本期 
	private BigDecimal tdparty_renewal_agcommiss; //第三方网络平台渠道续期代理佣金 -本期 
	private BigDecimal policy_count_sum; //新单保障对象数量 -累计
	private BigDecimal amount_sum; //新单保障额度/赔偿限额  -累计               
	private BigDecimal newpolicyfee_sum; //新保单保费金额 -累计            
	private BigDecimal renewalfee_sum; //续期保单金额 -累计             
	private BigDecimal premium_sum; //应付保费  -累计            
	private BigDecimal newpolicy_agcommiss_sum; //新单代理佣金 -累计     
	private BigDecimal renewal_agcommiss_sum; //续期代理佣金 -累计      
	private BigDecimal autotro_newpolicyfee_sum; //自营网络平台渠道新单保费金额 -累计 
	private BigDecimal autotro_renewalfee_sum; //自营网络平台渠道续期保单金额 -累计     
	private BigDecimal autotro_newpolicy_agcommiss_sum; //自营网络平台渠道新单代理佣金 -累计
	private BigDecimal autotro_renewal_agcommiss_sum; //自营网络平台渠道续期代理佣金 -累计
	private BigDecimal tdparty_newpolicyfee_sum; //第三方网络平台渠道新单保费金额 -累计
	private BigDecimal tdparty_renewalfee_sum; //第三方网络平台渠道续期保单金额 -累计   
	private BigDecimal tdparty_newpolicy_agcommiss_sum; //第三方网络平台渠道新单代理佣金 -累计
	private BigDecimal tdparty_renewal_agcommiss_sum; //第三方网络平台渠道续期代理佣金 -累计
	private String term; //年月     
	private String sign_path;//统计口径:0签单口径1统计口径
	private String dept_list;//权限
	
	private BigDecimal total_policy_count; //新单保障对象数量 -本期
	private BigDecimal total_amount; //新单保障额度/赔偿限额  -本期              
	private BigDecimal total_newpolfee;//总新保单保费金额 -本期          
	private BigDecimal total_renewfee;//总续期保单金额 -本期          
	private BigDecimal total_permium;//总应付保费 -本期          
	private BigDecimal total_newagent;//总新单代理佣金  -本期           
	private BigDecimal total_renewagent; //总续期代理佣金 -本期          	
	private BigDecimal total_autonewpolfee; //自营网络平台渠道新单保费金额  -本期          	
	private BigDecimal total_auto_renewfee; //自营网络平台渠道续期保单金额  -本期            	
	private BigDecimal total_newagcommiss; //自营网络平台渠道新单代理佣金	 -本期          
	private BigDecimal total_renewplofee; //自营网络平台渠道续期代理佣金  -本期            	
	private BigDecimal total_tdnewplofee; //第三方网络平台渠道新单保费金额  -本期           	
	private BigDecimal total_tdrenewfee; //第三方网络平台渠道续期保单金额  -本期            	
	private BigDecimal total_newploagcommiss; //第三方网络平台渠道新单代理佣金  -本期          	
	private BigDecimal total_renewagcomiss; //第三方网络平台渠道续期代理佣金  -本期          
	private BigDecimal total_policycount_sum; //新单保障对象数量 -累计
	private BigDecimal total_amount_sum; //新单保障额度/赔偿限额  -累计               
	private BigDecimal total_newpolicyfee_sum; //新保单保费金额 -累计            
	private BigDecimal total_renewalfee_sum; //续期保单金额 -累计             
	private BigDecimal total_premium_sum; //应付保费  -累计            
	private BigDecimal total_new_agcommiss_sum; //新单代理佣金 -累计     
	private BigDecimal total_renew_agcommiss_sum; //续期代理佣金 -累计      
	private BigDecimal total_autonewfee_sum; //自营网络平台渠道新单保费金额 -累计 
	private BigDecimal total_autorenewfee_sum; //自营网络平台渠道续期保单金额 -累计     
	private BigDecimal total_autonew_agcommiss_sum; //自营网络平台渠道新单代理佣金 -累计
	private BigDecimal total_autorenew_agcommiss_sum; //自营网络平台渠道续期代理佣金 -累计
	private BigDecimal total_tdnewfee_sum; //第三方网络平台渠道新单保费金额 -累计
	private BigDecimal total_tdrenew_sum; //第三方网络平台渠道续期保单金额 -累计   
	private BigDecimal total_tdnew_agcommiss_sum; //第三方网络平台渠道新单代理佣金 -累计
	private BigDecimal total_tdrenew_agcommiss_sum; //第三方网络平台渠道续期代理佣金 -累计
	
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
	public BigDecimal getNewpolicyfee() {
		return newpolicyfee;
	}
	public void setNewpolicyfee(BigDecimal newpolicyfee) {
		this.newpolicyfee = newpolicyfee;
	}
	public BigDecimal getRenewalfee() {
		return renewalfee;
	}
	public void setRenewalfee(BigDecimal renewalfee) {
		this.renewalfee = renewalfee;
	}
	public BigDecimal getPremium() {
		return premium;
	}
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	public BigDecimal getNewpolicy_agcommiss() {
		return newpolicy_agcommiss;
	}
	public void setNewpolicy_agcommiss(BigDecimal newpolicy_agcommiss) {
		this.newpolicy_agcommiss = newpolicy_agcommiss;
	}
	public BigDecimal getRenewal_agcommiss() {
		return renewal_agcommiss;
	}
	public void setRenewal_agcommiss(BigDecimal renewal_agcommiss) {
		this.renewal_agcommiss = renewal_agcommiss;
	}
	public BigDecimal getAutotro_newpolicyfee() {
		return autotro_newpolicyfee;
	}
	public void setAutotro_newpolicyfee(BigDecimal autotro_newpolicyfee) {
		this.autotro_newpolicyfee = autotro_newpolicyfee;
	}
	public BigDecimal getAutotro_renewalfee() {
		return autotro_renewalfee;
	}
	public void setAutotro_renewalfee(BigDecimal autotro_renewalfee) {
		this.autotro_renewalfee = autotro_renewalfee;
	}
	public BigDecimal getAutotro_newpolicy_agcommiss() {
		return autotro_newpolicy_agcommiss;
	}
	public void setAutotro_newpolicy_agcommiss(BigDecimal autotro_newpolicy_agcommiss) {
		this.autotro_newpolicy_agcommiss = autotro_newpolicy_agcommiss;
	}
	public BigDecimal getAutotro_renewal_agcommiss() {
		return autotro_renewal_agcommiss;
	}
	public void setAutotro_renewal_agcommiss(BigDecimal autotro_renewal_agcommiss) {
		this.autotro_renewal_agcommiss = autotro_renewal_agcommiss;
	}
	public BigDecimal getTdparty_newpolicyfee() {
		return tdparty_newpolicyfee;
	}
	public void setTdparty_newpolicyfee(BigDecimal tdparty_newpolicyfee) {
		this.tdparty_newpolicyfee = tdparty_newpolicyfee;
	}
	public BigDecimal getTdparty_renewalfee() {
		return tdparty_renewalfee;
	}
	public void setTdparty_renewalfee(BigDecimal tdparty_renewalfee) {
		this.tdparty_renewalfee = tdparty_renewalfee;
	}
	public BigDecimal getTdparty_newpolicy_agcommiss() {
		return tdparty_newpolicy_agcommiss;
	}
	public void setTdparty_newpolicy_agcommiss(BigDecimal tdparty_newpolicy_agcommiss) {
		this.tdparty_newpolicy_agcommiss = tdparty_newpolicy_agcommiss;
	}
	public BigDecimal getTdparty_renewal_agcommiss() {
		return tdparty_renewal_agcommiss;
	}
	public void setTdparty_renewal_agcommiss(BigDecimal tdparty_renewal_agcommiss) {
		this.tdparty_renewal_agcommiss = tdparty_renewal_agcommiss;
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
	
	public BigDecimal getTotal_newpolfee() {
		return total_newpolfee;
	}
	public void setTotal_newpolfee(BigDecimal total_newpolfee) {
		this.total_newpolfee = total_newpolfee;
	}
	public BigDecimal getTotal_renewfee() {
		return total_renewfee;
	}
	public void setTotal_renewfee(BigDecimal total_renewfee) {
		this.total_renewfee = total_renewfee;
	}
	public BigDecimal getTotal_permium() {
		return total_permium;
	}
	public void setTotal_permium(BigDecimal total_permium) {
		this.total_permium = total_permium;
	}
	public BigDecimal getTotal_newagent() {
		return total_newagent;
	}
	public void setTotal_newagent(BigDecimal total_newagent) {
		this.total_newagent = total_newagent;
	}
	public BigDecimal getTotal_renewagent() {
		return total_renewagent;
	}
	public void setTotal_renewagent(BigDecimal total_renewagent) {
		this.total_renewagent = total_renewagent;
	}
	public BigDecimal getTotal_autonewpolfee() {
		return total_autonewpolfee;
	}
	public void setTotal_autonewpolfee(BigDecimal total_autonewpolfee) {
		this.total_autonewpolfee = total_autonewpolfee;
	}
	public BigDecimal getTotal_auto_renewfee() {
		return total_auto_renewfee;
	}
	public void setTotal_auto_renewfee(BigDecimal total_auto_renewfee) {
		this.total_auto_renewfee = total_auto_renewfee;
	}
	public BigDecimal getTotal_newagcommiss() {
		return total_newagcommiss;
	}
	public void setTotal_newagcommiss(BigDecimal total_newagcommiss) {
		this.total_newagcommiss = total_newagcommiss;
	}
	public BigDecimal getTotal_renewplofee() {
		return total_renewplofee;
	}
	public void setTotal_renewplofee(BigDecimal total_renewplofee) {
		this.total_renewplofee = total_renewplofee;
	}
	public BigDecimal getTotal_tdnewplofee() {
		return total_tdnewplofee;
	}
	public void setTotal_tdnewplofee(BigDecimal total_tdnewplofee) {
		this.total_tdnewplofee = total_tdnewplofee;
	}
	public BigDecimal getTotal_tdrenewfee() {
		return total_tdrenewfee;
	}
	public void setTotal_tdrenewfee(BigDecimal total_tdrenewfee) {
		this.total_tdrenewfee = total_tdrenewfee;
	}
	public BigDecimal getTotal_newploagcommiss() {
		return total_newploagcommiss;
	}
	public void setTotal_newploagcommiss(BigDecimal total_newploagcommiss) {
		this.total_newploagcommiss = total_newploagcommiss;
	}
	public BigDecimal getTotal_renewagcomiss() {
		return total_renewagcomiss;
	}
	public void setTotal_renewagcomiss(BigDecimal total_renewagcomiss) {
		this.total_renewagcomiss = total_renewagcomiss;
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
	public BigDecimal getNewpolicyfee_sum() {
		return newpolicyfee_sum;
	}
	public void setNewpolicyfee_sum(BigDecimal newpolicyfee_sum) {
		this.newpolicyfee_sum = newpolicyfee_sum;
	}
	public BigDecimal getRenewalfee_sum() {
		return renewalfee_sum;
	}
	public void setRenewalfee_sum(BigDecimal renewalfee_sum) {
		this.renewalfee_sum = renewalfee_sum;
	}
	public BigDecimal getPremium_sum() {
		return premium_sum;
	}
	public void setPremium_sum(BigDecimal premium_sum) {
		this.premium_sum = premium_sum;
	}
	public BigDecimal getNewpolicy_agcommiss_sum() {
		return newpolicy_agcommiss_sum;
	}
	public void setNewpolicy_agcommiss_sum(BigDecimal newpolicy_agcommiss_sum) {
		this.newpolicy_agcommiss_sum = newpolicy_agcommiss_sum;
	}
	public BigDecimal getRenewal_agcommiss_sum() {
		return renewal_agcommiss_sum;
	}
	public void setRenewal_agcommiss_sum(BigDecimal renewal_agcommiss_sum) {
		this.renewal_agcommiss_sum = renewal_agcommiss_sum;
	}
	public BigDecimal getAutotro_newpolicyfee_sum() {
		return autotro_newpolicyfee_sum;
	}
	public void setAutotro_newpolicyfee_sum(BigDecimal autotro_newpolicyfee_sum) {
		this.autotro_newpolicyfee_sum = autotro_newpolicyfee_sum;
	}
	public BigDecimal getAutotro_renewalfee_sum() {
		return autotro_renewalfee_sum;
	}
	public void setAutotro_renewalfee_sum(BigDecimal autotro_renewalfee_sum) {
		this.autotro_renewalfee_sum = autotro_renewalfee_sum;
	}
	public BigDecimal getAutotro_newpolicy_agcommiss_sum() {
		return autotro_newpolicy_agcommiss_sum;
	}
	public void setAutotro_newpolicy_agcommiss_sum(
			BigDecimal autotro_newpolicy_agcommiss_sum) {
		this.autotro_newpolicy_agcommiss_sum = autotro_newpolicy_agcommiss_sum;
	}
	public BigDecimal getAutotro_renewal_agcommiss_sum() {
		return autotro_renewal_agcommiss_sum;
	}
	public void setAutotro_renewal_agcommiss_sum(
			BigDecimal autotro_renewal_agcommiss_sum) {
		this.autotro_renewal_agcommiss_sum = autotro_renewal_agcommiss_sum;
	}
	public BigDecimal getTdparty_newpolicyfee_sum() {
		return tdparty_newpolicyfee_sum;
	}
	public void setTdparty_newpolicyfee_sum(BigDecimal tdparty_newpolicyfee_sum) {
		this.tdparty_newpolicyfee_sum = tdparty_newpolicyfee_sum;
	}
	public BigDecimal getTdparty_renewalfee_sum() {
		return tdparty_renewalfee_sum;
	}
	public void setTdparty_renewalfee_sum(BigDecimal tdparty_renewalfee_sum) {
		this.tdparty_renewalfee_sum = tdparty_renewalfee_sum;
	}
	public BigDecimal getTdparty_newpolicy_agcommiss_sum() {
		return tdparty_newpolicy_agcommiss_sum;
	}
	public void setTdparty_newpolicy_agcommiss_sum(
			BigDecimal tdparty_newpolicy_agcommiss_sum) {
		this.tdparty_newpolicy_agcommiss_sum = tdparty_newpolicy_agcommiss_sum;
	}
	public BigDecimal getTdparty_renewal_agcommiss_sum() {
		return tdparty_renewal_agcommiss_sum;
	}
	public void setTdparty_renewal_agcommiss_sum(
			BigDecimal tdparty_renewal_agcommiss_sum) {
		this.tdparty_renewal_agcommiss_sum = tdparty_renewal_agcommiss_sum;
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
	public BigDecimal getTotal_newpolicyfee_sum() {
		return total_newpolicyfee_sum;
	}
	public void setTotal_newpolicyfee_sum(BigDecimal total_newpolicyfee_sum) {
		this.total_newpolicyfee_sum = total_newpolicyfee_sum;
	}
	public BigDecimal getTotal_renewalfee_sum() {
		return total_renewalfee_sum;
	}
	public void setTotal_renewalfee_sum(BigDecimal total_renewalfee_sum) {
		this.total_renewalfee_sum = total_renewalfee_sum;
	}
	public BigDecimal getTotal_premium_sum() {
		return total_premium_sum;
	}
	public void setTotal_premium_sum(BigDecimal total_premium_sum) {
		this.total_premium_sum = total_premium_sum;
	}
	public BigDecimal getTotal_new_agcommiss_sum() {
		return total_new_agcommiss_sum;
	}
	public void setTotal_new_agcommiss_sum(BigDecimal total_new_agcommiss_sum) {
		this.total_new_agcommiss_sum = total_new_agcommiss_sum;
	}
	public BigDecimal getTotal_renew_agcommiss_sum() {
		return total_renew_agcommiss_sum;
	}
	public void setTotal_renew_agcommiss_sum(BigDecimal total_renew_agcommiss_sum) {
		this.total_renew_agcommiss_sum = total_renew_agcommiss_sum;
	}
	public BigDecimal getTotal_autonewfee_sum() {
		return total_autonewfee_sum;
	}
	public void setTotal_autonewfee_sum(BigDecimal total_autonewfee_sum) {
		this.total_autonewfee_sum = total_autonewfee_sum;
	}
	public BigDecimal getTotal_autorenewfee_sum() {
		return total_autorenewfee_sum;
	}
	public void setTotal_autorenewfee_sum(BigDecimal total_autorenewfee_sum) {
		this.total_autorenewfee_sum = total_autorenewfee_sum;
	}
	public BigDecimal getTotal_autonew_agcommiss_sum() {
		return total_autonew_agcommiss_sum;
	}
	public void setTotal_autonew_agcommiss_sum(
			BigDecimal total_autonew_agcommiss_sum) {
		this.total_autonew_agcommiss_sum = total_autonew_agcommiss_sum;
	}
	public BigDecimal getTotal_autorenew_agcommiss_sum() {
		return total_autorenew_agcommiss_sum;
	}
	public void setTotal_autorenew_agcommiss_sum(
			BigDecimal total_autorenew_agcommiss_sum) {
		this.total_autorenew_agcommiss_sum = total_autorenew_agcommiss_sum;
	}
	public BigDecimal getTotal_tdnewfee_sum() {
		return total_tdnewfee_sum;
	}
	public void setTotal_tdnewfee_sum(BigDecimal total_tdnewfee_sum) {
		this.total_tdnewfee_sum = total_tdnewfee_sum;
	}
	public BigDecimal getTotal_tdrenew_sum() {
		return total_tdrenew_sum;
	}
	public void setTotal_tdrenew_sum(BigDecimal total_tdrenew_sum) {
		this.total_tdrenew_sum = total_tdrenew_sum;
	}
	public BigDecimal getTotal_tdnew_agcommiss_sum() {
		return total_tdnew_agcommiss_sum;
	}
	public void setTotal_tdnew_agcommiss_sum(BigDecimal total_tdnew_agcommiss_sum) {
		this.total_tdnew_agcommiss_sum = total_tdnew_agcommiss_sum;
	}
	public BigDecimal getTotal_tdrenew_agcommiss_sum() {
		return total_tdrenew_agcommiss_sum;
	}
	public void setTotal_tdrenew_agcommiss_sum(
			BigDecimal total_tdrenew_agcommiss_sum) {
		this.total_tdrenew_agcommiss_sum = total_tdrenew_agcommiss_sum;
	}
	
	
}
