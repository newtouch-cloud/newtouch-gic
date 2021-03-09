package com.ca.cacore.ibs.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeHolderModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;
/**
 * 
 * @author szl
 * @description:投保单vo实体
 * 
 */
public class PolicyLifeInfoVOModel {
	private String send_code; // 投保单号码
	private String branch_id; // 机构代码 
	private String agent_id; // 保单销售人员 
	private String agent_name; // 保单销售人员名称 
	private String insBranch_id; // 保险公司机构代码
	private String insBranch_name;//保险公司名称
	private String high_policy; // 是否高额件 Y是N否
	private Date hold_date; // 投保日期
	public PolicyLifeHolderModel  holderModel  ;//投保人集合
	public IPolicyImageModel policyImageModel ;//影像上传集合
	public List<PolicyLifeInsuredModel>insurantList = new ArrayList<PolicyLifeInsuredModel>();//被保人集合
	public List<PolicyLifeBeneficiaryModel>beneficiaryList = new ArrayList<PolicyLifeBeneficiaryModel>();//受益人集合
	public List<IPolicyLifeProductFeePremVOModel>productList = new ArrayList<IPolicyLifeProductFeePremVOModel>();//险种集合
	
	public List<IPolicyLifePeopleVOModel> holderListView  = new ArrayList<IPolicyLifePeopleVOModel>();//投保人集合
	public List<IPolicyLifePeopleVOModel>insurantListView = new ArrayList<IPolicyLifePeopleVOModel>();//被保人集合
	public List<IPolicyLifePeopleVOModel >beneficiaryListView = new ArrayList<IPolicyLifePeopleVOModel>();//受益人集合
	public List<IPolicyLifeProductVOModel >productListView = new ArrayList<IPolicyLifeProductVOModel>();//险种集合
	
	private Double period_prem; // 保费合计
	private String pay_mode;//首期付款方式
	private String pay_mode_next;//续期付款方式
	private String money_id; // 币种 
	private String bank_code; // 开户银行代码
	private String account_type; // 银行账号类型 Ref：SYS_Library_BankAccountType
	private String bank_accName; // 银行开户人
	private String bank_account; // 银行账号
	private String overdue_manage; // 保险费逾期未付的选择 Ref：CBS_PolicyLife_Overdue
	private String[] file_id; // 影像id
	private Long policy_id ;//保单id
	private String policy_code; // 保单号码
	
	private Integer seq_id; // 序号
	private String service_id; // 保单服务人员 REF:SMIS_Sales
	private String holder_id; // 投保人 REF:CBS_Customer
	private String insurant_id; // 第一被保人 REF:CBS_Customer
	private String relation_id; // 投保人与被保人关系 REF:CBS_Applicant_Relation1
	private String relation_name;//投保人与被保人关系
	private String sell_way; // 消费方式 Ref：CBS_Policy_Sell_Way
	private String charge_type; // 当期缴费方式 Ref：PDT_Lib_ChargeType
	private String charge_next; // 续期缴费方式 Ref：PDT_Lib_ChargeType
	private Integer policy_year; // 保单年度
	private Integer policy_period; // 缴费期次
	private Date scan_time; // 影像上传日期
	private Date validate_date; // 保单生效日期
	private Date sign_date; // 客户签收日期
	private Date end_date; // 保单终止日期
	private Date pause_time; // 保单停效日期
	private String is_answered; // 是否已回访 Y是N否
	private String status; // 投保单状态 Ref：CBS_Policy_Status
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
	private Date rtn_date;//回执核销日期
	private String result_flag ;//核心接口返回标志
	private String branch_name;//机构名称
	 private Integer log_seq_id;//	备份数据主键;
	 private String log_bustype;//	备份业务类型;
	 private Date log_busdate;//	备份业务归属时间;
	 private Date log_date;//	备份时间;
	 private String log_remark;//	备份备注;
	 private String inner_send_code;//内部投保单号码
	 private String log;//核心返回信息
	
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
	public IPolicyImageModel getPolicyImageModel() {
		return policyImageModel;
	}
	public void setPolicyImageModel(IPolicyImageModel policyImageModel) {
		this.policyImageModel = policyImageModel;
	}
	public String getRelation_name() {
		return relation_name;
	}
	public void setRelation_name(String relation_name) {
		this.relation_name = relation_name;
	}
	public List<IPolicyLifePeopleVOModel> getHolderListView() {
		return holderListView;
	}
	public void setHolderListView(List<IPolicyLifePeopleVOModel> holderListView) {
		this.holderListView = holderListView;
	}
	public List<IPolicyLifePeopleVOModel> getInsurantListView() {
		return insurantListView;
	}
	public void setInsurantListView(List<IPolicyLifePeopleVOModel> insurantListView) {
		this.insurantListView = insurantListView;
	}
	public List<IPolicyLifePeopleVOModel> getBeneficiaryListView() {
		return beneficiaryListView;
	}
	public void setBeneficiaryListView(
			List<IPolicyLifePeopleVOModel> beneficiaryListView) {
		this.beneficiaryListView = beneficiaryListView;
	}
	public List<IPolicyLifeProductVOModel> getProductListView() {
		return productListView;
	}
	public void setProductListView(List<IPolicyLifeProductVOModel> productListView) {
		this.productListView = productListView;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getResult_flag() {
		return result_flag;
	}
	public void setResult_flag(String result_flag) {
		this.result_flag = result_flag;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
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
	public Date getRtn_date() {
		return rtn_date;
	}
	public void setRtn_date(Date rtn_date) {
		this.rtn_date = rtn_date;
	}
	public Long getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	
	public String getPolicy_code() {
		return policy_code;
	}
	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}
	public String getSend_code() {
		return send_code;
	}
	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
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
	public String getHigh_policy() {
		return high_policy;
	}
	public void setHigh_policy(String high_policy) {
		this.high_policy = high_policy;
	}
	public Date getHold_date() {
		return hold_date;
	}
	public void setHold_date(Date hold_date) {
		this.hold_date = hold_date;
	}
	public PolicyLifeHolderModel getHolderModel() {
		return holderModel;
	}
	public void setHolderModel(PolicyLifeHolderModel holderModel) {
		this.holderModel = holderModel;
	}
	public List<PolicyLifeInsuredModel> getInsurantList() {
		return insurantList;
	}
	public void setInsurantList(List<PolicyLifeInsuredModel> insurantList) {
		this.insurantList = insurantList;
	}
	public List<PolicyLifeBeneficiaryModel> getBeneficiaryList() {
		return beneficiaryList;
	}
	public void setBeneficiaryList(List<PolicyLifeBeneficiaryModel> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}

	public List<IPolicyLifeProductFeePremVOModel> getProductList() {
		return productList;
	}
	public void setProductList(List<IPolicyLifeProductFeePremVOModel> productList) {
		this.productList = productList;
	}
	public Double getPeriod_prem() {
		return period_prem;
	}
	public void setPeriod_prem(Double period_prem) {
		this.period_prem = period_prem;
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
	public String[] getFile_id() {
		return file_id;
	} 
	public void setFile_id(String[] file_id) {
		this.file_id = file_id;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getInner_send_code() {
		return inner_send_code;
	}
	public void setInner_send_code(String inner_send_code) {
		this.inner_send_code = inner_send_code;
	}
}
