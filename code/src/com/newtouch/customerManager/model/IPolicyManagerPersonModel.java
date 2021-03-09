package com.newtouch.customerManager.model;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class IPolicyManagerPersonModel implements IPageCount {
	
	private Integer  id;
	private String  province_orgname			;//	保险公司省级机构名称
	private String  province_orgid			;//	保险公司省级机构代码
	private String  city_orgname			;//	保险公司地市机构名称
	private String  city_orgid			;//	保险公司地市机构代码
	private String  area_orgid			;//	保险公司县支公司名称
	private String  area_orgname			;//	保险公司县支公司代码
	private String  sale_org_name			;//	中介公司机构名称
	private String  sale_org_id			;//	中介公司机构代码
	private String  settled_date			;//	分单时间
	private String  policy_no			;//	保单号
	private String  insure_no			;//	投保单号
	private String  center_risk_flag			;//	主险/附加险标识
	private String  center_risk_name			;//	险种名称
	private String  center_risk_id			;//	险种代码
	private String  applicant_name			;//	投保人
	private String  insured_name			;//	被保险人
	private Date  insure_date			;//	承保日期
	private Date  paper_date			;//	签发日期
	private Date  cancel_date			;//	回执核销日期
	private String  cancel_reason			;//	终止原因名称
	private String  pay_fee_type			;//	缴费方式名称
	private String  pay_date			;//	缴费期
	private String  insured_during			;//	保险期间
	private String  pay_type			;//	付款方式名称
	private Double  premium			;//	保费
	private Double  amount			;//	保额
	private Double  fee_ratio			;//	手续费比例
	private Double  new_paper_fee			;//	新单佣金
	private Double  renew_paper_fee			;//	续期佣金
	private Double  total_fee			;//	手续费（合计）
	private String  owner_name			;//	中介公司归属人员姓名
	private String  owner_no			;//	中介公司归属人员编码
	private Double  agent_fee_rate			;//	代理人佣金支付比例
	private Double  agent_fee			;//	代理人佣金金额
	private String  business_flag			;//	业务类型
	private String  inter_flag			;//	互联网标识
	private String  endor_no			;//	批单号
	private Date  endor_date			;//	批改日期
	private Date  endor_valid			;//	批改生效日期
	private String  settled_time			;//结算时间
	
	private String center_risk_type;//产品类型
	private String long_risk_flag;//长短险标志
	private String renew_flag;//新单续收标识
	private String applicant_id;//投保人身份证号
	private String insured_id;//被保险险人身份证号
	private String insured_during_flag;//保险期类型
	private String pay_fee_year;//缴费年限
	private String risk_year;//保单年度
	private Date   ins_validate;//业务生效日期
	private Date   visit_date;//业务生效日期
	private	Integer change_nu	;//	数量变化
	
	private PageCount pageCount = new PageCount() ;
	private Date createDateL;// 查询时间起
	private Date createDateG;// 查询时间末
	private Date check_date_L;// 核保日期起
	private Date check_date_G;// 核保日期起
	private Date begin_date_G;// 起保日期末
	private String branch_id_Branch;// 中介公司机构
	private String branch_name_branch;// 中介公司机构
	private	String user_jurisdiction	;//当前登录人
	private	String branch_id	;//当前登录人
	private String org_id				;
	private String org_name				;
	
	
	private Integer count;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvince_orgname() {
		return province_orgname;
	}

	public void setProvince_orgname(String province_orgname) {
		this.province_orgname = province_orgname;
	}

	public String getProvince_orgid() {
		return province_orgid;
	}

	public void setProvince_orgid(String province_orgid) {
		this.province_orgid = province_orgid;
	}

	public String getCity_orgname() {
		return city_orgname;
	}

	public void setCity_orgname(String city_orgname) {
		this.city_orgname = city_orgname;
	}

	public String getCity_orgid() {
		return city_orgid;
	}

	public void setCity_orgid(String city_orgid) {
		this.city_orgid = city_orgid;
	}

	public String getArea_orgid() {
		return area_orgid;
	}

	public void setArea_orgid(String area_orgid) {
		this.area_orgid = area_orgid;
	}

	public String getArea_orgname() {
		return area_orgname;
	}

	public void setArea_orgname(String area_orgname) {
		this.area_orgname = area_orgname;
	}

	public String getSale_org_name() {
		return sale_org_name;
	}

	public void setSale_org_name(String sale_org_name) {
		this.sale_org_name = sale_org_name;
	}

	public String getSale_org_id() {
		return sale_org_id;
	}

	public void setSale_org_id(String sale_org_id) {
		this.sale_org_id = sale_org_id;
	}

	public String getSettled_date() {
		return settled_date;
	}

	public void setSettled_date(String settled_date) {
		this.settled_date = settled_date;
	}

	public String getPolicy_no() {
		return policy_no;
	}

	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}

	public String getInsure_no() {
		return insure_no;
	}

	public void setInsure_no(String insure_no) {
		this.insure_no = insure_no;
	}

	public String getCenter_risk_flag() {
		return center_risk_flag;
	}

	public void setCenter_risk_flag(String center_risk_flag) {
		this.center_risk_flag = center_risk_flag;
	}

	public String getCenter_risk_name() {
		return center_risk_name;
	}

	public void setCenter_risk_name(String center_risk_name) {
		this.center_risk_name = center_risk_name;
	}

	public String getCenter_risk_id() {
		return center_risk_id;
	}

	public void setCenter_risk_id(String center_risk_id) {
		this.center_risk_id = center_risk_id;
	}

	public String getApplicant_name() {
		return applicant_name;
	}

	public void setApplicant_name(String applicant_name) {
		this.applicant_name = applicant_name;
	}

	public String getInsured_name() {
		return insured_name;
	}

	public void setInsured_name(String insured_name) {
		this.insured_name = insured_name;
	}

	public Date getInsure_date() {
		return insure_date;
	}

	public void setInsure_date(Date insure_date) {
		this.insure_date = insure_date;
	}

	public Date getPaper_date() {
		return paper_date;
	}

	public void setPaper_date(Date paper_date) {
		this.paper_date = paper_date;
	}

	public Date getCancel_date() {
		return cancel_date;
	}

	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}

	public String getCancel_reason() {
		return cancel_reason;
	}

	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}

	public String getPay_fee_type() {
		return pay_fee_type;
	}

	public void setPay_fee_type(String pay_fee_type) {
		this.pay_fee_type = pay_fee_type;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public String getInsured_during() {
		return insured_during;
	}

	public void setInsured_during(String insured_during) {
		this.insured_during = insured_during;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getFee_ratio() {
		return fee_ratio;
	}

	public void setFee_ratio(Double fee_ratio) {
		this.fee_ratio = fee_ratio;
	}

	public Double getNew_paper_fee() {
		return new_paper_fee;
	}

	public void setNew_paper_fee(Double new_paper_fee) {
		this.new_paper_fee = new_paper_fee;
	}

	public Double getRenew_paper_fee() {
		return renew_paper_fee;
	}

	public void setRenew_paper_fee(Double renew_paper_fee) {
		this.renew_paper_fee = renew_paper_fee;
	}

	public Double getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Double total_fee) {
		this.total_fee = total_fee;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getOwner_no() {
		return owner_no;
	}

	public void setOwner_no(String owner_no) {
		this.owner_no = owner_no;
	}

	public Double getAgent_fee_rate() {
		return agent_fee_rate;
	}

	public void setAgent_fee_rate(Double agent_fee_rate) {
		this.agent_fee_rate = agent_fee_rate;
	}

	public Double getAgent_fee() {
		return agent_fee;
	}

	public void setAgent_fee(Double agent_fee) {
		this.agent_fee = agent_fee;
	}

	public String getBusiness_flag() {
		return business_flag;
	}

	public void setBusiness_flag(String business_flag) {
		this.business_flag = business_flag;
	}

	public String getInter_flag() {
		return inter_flag;
	}

	public void setInter_flag(String inter_flag) {
		this.inter_flag = inter_flag;
	}

	public String getEndor_no() {
		return endor_no;
	}

	public void setEndor_no(String endor_no) {
		this.endor_no = endor_no;
	}

	public Date getEndor_date() {
		return endor_date;
	}

	public void setEndor_date(Date endor_date) {
		this.endor_date = endor_date;
	}

	public Date getEndor_valid() {
		return endor_valid;
	}

	public void setEndor_valid(Date endor_valid) {
		this.endor_valid = endor_valid;
	}

	public PageCount getPageCount() {
		return pageCount;
	}

	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}

	public Date getCreateDateL() {
		return createDateL;
	}

	public void setCreateDateL(Date createDateL) {
		this.createDateL = createDateL;
	}

	public Date getCreateDateG() {
		return createDateG;
	}

	public void setCreateDateG(Date createDateG) {
		this.createDateG = createDateG;
	}

	public Date getCheck_date_L() {
		return check_date_L;
	}

	public void setCheck_date_L(Date check_date_L) {
		this.check_date_L = check_date_L;
	}

	public Date getBegin_date_G() {
		return begin_date_G;
	}

	public void setBegin_date_G(Date begin_date_G) {
		this.begin_date_G = begin_date_G;
	}

	public String getBranch_id_Branch() {
		return branch_id_Branch;
	}

	public void setBranch_id_Branch(String branch_id_Branch) {
		this.branch_id_Branch = branch_id_Branch;
	}

	public String getBranch_name_branch() {
		return branch_name_branch;
	}

	public void setBranch_name_branch(String branch_name_branch) {
		this.branch_name_branch = branch_name_branch;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public int getStart() {
		return this.getPageCount().getStart();
	}

	public int getLimit() {
		return this.getPageCount().getLimit();
	}

	public String getUser_jurisdiction() {
		return user_jurisdiction;
	}

	public void setUser_jurisdiction(String user_jurisdiction) {
		this.user_jurisdiction = user_jurisdiction;
	}

	public Date getCheck_date_G() {
		return check_date_G;
	}

	public void setCheck_date_G(Date check_date_G) {
		this.check_date_G = check_date_G;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getCenter_risk_type() {
		return center_risk_type;
	}

	public void setCenter_risk_type(String center_risk_type) {
		this.center_risk_type = center_risk_type;
	}

	public String getLong_risk_flag() {
		return long_risk_flag;
	}

	public void setLong_risk_flag(String long_risk_flag) {
		this.long_risk_flag = long_risk_flag;
	}

	public String getRenew_flag() {
		return renew_flag;
	}

	public void setRenew_flag(String renew_flag) {
		this.renew_flag = renew_flag;
	}

	public String getApplicant_id() {
		return applicant_id;
	}

	public void setApplicant_id(String applicant_id) {
		this.applicant_id = applicant_id;
	}

	public String getInsured_id() {
		return insured_id;
	}

	public void setInsured_id(String insured_id) {
		this.insured_id = insured_id;
	}

	public String getInsured_during_flag() {
		return insured_during_flag;
	}

	public void setInsured_during_flag(String insured_during_flag) {
		this.insured_during_flag = insured_during_flag;
	}

	public String getPay_fee_year() {
		return pay_fee_year;
	}

	public void setPay_fee_year(String pay_fee_year) {
		this.pay_fee_year = pay_fee_year;
	}

	public String getRisk_year() {
		return risk_year;
	}

	public void setRisk_year(String risk_year) {
		this.risk_year = risk_year;
	}

	public Date getIns_validate() {
		return ins_validate;
	}

	public void setIns_validate(Date ins_validate) {
		this.ins_validate = ins_validate;
	}

	public Date getVisit_date() {
		return visit_date;
	}

	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}

	public Integer getChange_nu() {
		return change_nu;
	}

	public void setChange_nu(Integer change_nu) {
		this.change_nu = change_nu;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getSettled_time() {
		return settled_time;
	}

	public void setSettled_time(String settled_time) {
		this.settled_time = settled_time;
	}
	
	
}
