package com.newtouch.customerManager.model;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class IPolicyManagerModel implements IPageCount {
	private		Integer		seq_id	;//	主键 
	private		 String 	ord_id	;//	归属支公司代码
	private		 String 	org_name	;//	机构名称
	private		 String 	statistics_type	;//	统计口径（不用）
	private		 String 	channel_type	;//	渠道类型'专业代理'
	private		 String 	channel_no	;//	渠道代码
	private		 String		insurance_type_name	;//	险种名称
	private		 String		product_name	;//	产品名称
	private		 Date		sign_date	;//	签单日期
	private		 Date		accomplish_date	;//	成单日期
	private		 Date		statistics_date	;//	统计时间
	private		 String 	insure_no	;//	投保单号
	private		 String 	policy_no	;//	保单号
	private		 String 	serial_no	;//	快钱流水号
	private		 String		applicant_name	;//	投保人姓名
	private		 String		insured_name	;//	被保险人姓名
	private		 String 	lpn	;//	车牌号
	private		 Double		premium	;//	保费
	private		 String		repair_coding	;//	推荐维修码
	private		 String		rack_no	;//	车架号
	private		 Date		check_date	;//	核保日期
	private		 Date		begin_date	;//	起保日期
	private		 Date		end_date	;//	终保日期
	private		 Date		approval_date	;//	批单日期
	private		 Double		fee_ratio	;//	中介公司跟单手续费比例
	private		 Double		fee	;//	中介公司跟单手续费
	private		 Double		clean_charge	;//	已结算手续费金额
	private		 Double		wei_rate	;//	代理人佣金比例
	private		 Double		commission	;//	代理人佣金
	private		 Double		cash	;//	提现金额
	private		 String 	status	;//	有效无效
	private		 String 	person_name	;//	（财险）归属人工号
	private		 String 	person_no	;//	（财险）归属人姓名
	private		 String		insurance_class_no	;//	险类代码
	private		 String 	insurance_type_no	;//	险种代码
	private		 String		product_no	;//	产品代码
	private		 String 	insured_cid	;//	被保险人身份证
	private		 String 	insured_company_type	;//	被保险人单位性质
	private		 String 	insured_papertype	;//	被保险人证件类型
	private		 String		insured_add	;//	被保险人地址
	private		 String 	insured_mailbox	;//	被保险人邮箱
	private		 String 	insured_phone	;//	被保险人手机
	private		 String 	insured_tel	;//	被保险人固话
	private		 String		insurance_class_name	;//	险类名称
	private		 String 	endor_no	;//	批单号
	private		 String 	car_type	;//	车型编码
	private		 String 	vin	;//	vin车架号
	private		 String 	engine_no	;//	发动机号
	private		 String		car_class	;//	车辆种类
	private		 String		use_type	;//	使用性质
	private		 Date		first_date	;//	初登日期
	private		 String		clause_type	;//	条款类型
	private		 Double		net_premium	;//	净保费（不含税）
	private		 Double		vehicel_tax	;//	车船税
	private		 String		car_branchname	;//	车行名称
	private		 Date		print_date	;//	打印日期
	private		 String		approval_flag	;//	新/续保标志 ： 0：新 1：续
	private		 Double		amount	;//	保额
	private		 Double		premium_tax	;//	保费增值税
	private		 String 	endor_type	;//	批单类型
	private		 String		province_orgid	;//	财险省份机构代码
	private		 String		province_orgname	;//	财险省份机构名称
	private		 String		city_orgid	;//	财险市级机构代码
	private		 String		city_orgname	;//	财险市级机构名称
	private		 String		area_orgid	;//	财险区县机构代码
	private		 String		area_orgname	;//	财险区县机构名称
	private		 String 	responsible_no	;//	经办人工号
	private		 String 	responsible_name	;//	经办人姓名
	private		 String 	person_cid	;//	代理人身份证号
	private		 Date		create_date	;//	创建时间
	private		 String		create_user	;//	创建人
	private		 Date		mdf_date	;//	修改时间
	private		 String 	mdf_user	;//	修改人
	private		 String		car_name	;//	车型名称
	private		 String		org_id	;//	财险归属经营单位代码
	private		 Date		ins_validate	;//	保单生效日期（起报日期核保日期取大的）
	private		 String		data_type	;//	数据类型：0 ：保单类型 1：批单类型
	private		 String		change_nu	;//	数量变化
	private		 String		owner_no	;//	（中介公司）业务归属人工号
	private		 String		owner_name	;//	（中介公司）业务归属人姓名
	private		 String		inter_flag	;//	互联网标识
	private		 String		business_flag	;//业务类型
	private		 Double		service_fee;//服务费金额
	private		 Double		car_value;//车辆价值
	private		 String		car_use_year	;//使用年限
	private		 String		remarks	;//备注
	private		 String		settled_date	;//结算日期
	private		 Date		endor_date	;//	批改日期
	private		 Date		endor_valid	;//	批改生效日期
	private 	 Date 		settled_time;//分单时间
	private 	 String  	settled_date1;//分单时间
	
	private PageCount pageCount = new PageCount();
	private Date createDateL;// 查询时间起
	private Date createDateG;// 查询时间末
	private Date check_date_L;// 核保日期起
	private Date check_date_G;// 核保日期起
	private Date ins_validate_L;//批改生效日期从
	private Date ins_validate_G;//至
	private Date approval_date_L;//批改日期从
	private Date approval_date_G;//至
	
	
	private Date begin_date_G;// 起保日期末
	
	private String branch_name;//保险公司机构选择
	private String branch_id;//保险公司机构选择
	private String branch_id_Branch;// 中介公司机构
	private String branch_name_branch;// 中介公司机构
	private String branch_level_Branch;// 中介公司机构
	private	String user_jurisdiction	;//当前登录人
	
	private Integer count;
	
	

	/**
	 * @return the createDateL
	 */
	public Date getCreateDateL() {
		return createDateL;
	}

	/**
	 * @param createDateL
	 *            the createDateL to set
	 */
	public void setCreateDateL(Date createDateL) {
		this.createDateL = createDateL;
	}

	/**
	 * @return the createDateG
	 */
	public Date getCreateDateG() {
		return createDateG;
	}

	/**
	 * @param createDateG
	 *            the createDateG to set
	 */
	public void setCreateDateG(Date createDateG) {
		this.createDateG = createDateG;
	}

	/**
	 * @return the pageCount
	 */

	@Override
	public void setPageCount(PageCount pagination) {
		this.pageCount = pagination;
	}

	public int getStart() {
		return this.getPageCount().getStart();
	}

	public int getLimit() {
		return this.getPageCount().getLimit();
	}

	@Override
	public PageCount getPageCount() {
		return pageCount;
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

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	public String getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(String ord_id) {
		this.ord_id = ord_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getStatistics_type() {
		return statistics_type;
	}

	public void setStatistics_type(String statistics_type) {
		this.statistics_type = statistics_type;
	}

	public String getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}

	public String getChannel_no() {
		return channel_no;
	}

	public void setChannel_no(String channel_no) {
		this.channel_no = channel_no;
	}

	public String getInsurance_type_name() {
		return insurance_type_name;
	}

	public void setInsurance_type_name(String insurance_type_name) {
		this.insurance_type_name = insurance_type_name;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Date getSign_date() {
		return sign_date;
	}

	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}

	public Date getAccomplish_date() {
		return accomplish_date;
	}

	public void setAccomplish_date(Date accomplish_date) {
		this.accomplish_date = accomplish_date;
	}

	public Date getStatistics_date() {
		return statistics_date;
	}

	public void setStatistics_date(Date statistics_date) {
		this.statistics_date = statistics_date;
	}

	public String getInsure_no() {
		return insure_no;
	}

	public void setInsure_no(String insure_no) {
		this.insure_no = insure_no;
	}

	public String getPolicy_no() {
		return policy_no;
	}

	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
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

	public String getLpn() {
		return lpn;
	}

	public void setLpn(String lpn) {
		this.lpn = lpn;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public String getRepair_coding() {
		return repair_coding;
	}

	public void setRepair_coding(String repair_coding) {
		this.repair_coding = repair_coding;
	}
	public String getRack_no() {
		return rack_no;
	}

	public void setRack_no(String rack_no) {
		this.rack_no = rack_no;
	}

	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}

	public Date getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getApproval_date() {
		return approval_date;
	}

	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}

	public Double getFee_ratio() {
		return fee_ratio;
	}

	public void setFee_ratio(Double fee_ratio) {
		this.fee_ratio = fee_ratio;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getClean_charge() {
		return clean_charge;
	}

	public void setClean_charge(Double clean_charge) {
		this.clean_charge = clean_charge;
	}

	public Double getWei_rate() {
		return wei_rate;
	}

	public void setWei_rate(Double wei_rate) {
		this.wei_rate = wei_rate;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getPerson_no() {
		return person_no;
	}

	public void setPerson_no(String person_no) {
		this.person_no = person_no;
	}

	public String getInsurance_class_no() {
		return insurance_class_no;
	}

	public void setInsurance_class_no(String insurance_class_no) {
		this.insurance_class_no = insurance_class_no;
	}

	public String getInsurance_type_no() {
		return insurance_type_no;
	}

	public void setInsurance_type_no(String insurance_type_no) {
		this.insurance_type_no = insurance_type_no;
	}

	public String getProduct_no() {
		return product_no;
	}

	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}

	public String getInsured_cid() {
		return insured_cid;
	}

	public void setInsured_cid(String insured_cid) {
		this.insured_cid = insured_cid;
	}

	public String getInsured_company_type() {
		return insured_company_type;
	}

	public void setInsured_company_type(String insured_company_type) {
		this.insured_company_type = insured_company_type;
	}

	public String getInsured_papertype() {
		return insured_papertype;
	}

	public void setInsured_papertype(String insured_papertype) {
		this.insured_papertype = insured_papertype;
	}

	public String getInsured_add() {
		return insured_add;
	}

	public void setInsured_add(String insured_add) {
		this.insured_add = insured_add;
	}

	public String getInsured_mailbox() {
		return insured_mailbox;
	}

	public void setInsured_mailbox(String insured_mailbox) {
		this.insured_mailbox = insured_mailbox;
	}

	public String getInsured_phone() {
		return insured_phone;
	}

	public void setInsured_phone(String insured_phone) {
		this.insured_phone = insured_phone;
	}

	public String getInsured_tel() {
		return insured_tel;
	}

	public void setInsured_tel(String insured_tel) {
		this.insured_tel = insured_tel;
	}

	public String getInsurance_class_name() {
		return insurance_class_name;
	}

	public void setInsurance_class_name(String insurance_class_name) {
		this.insurance_class_name = insurance_class_name;
	}

	public String getEndor_no() {
		return endor_no;
	}

	public void setEndor_no(String endor_no) {
		this.endor_no = endor_no;
	}

	public String getCar_type() {
		return car_type;
	}

	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getEngine_no() {
		return engine_no;
	}

	public void setEngine_no(String engine_no) {
		this.engine_no = engine_no;
	}

	public String getCar_class() {
		return car_class;
	}

	public void setCar_class(String car_class) {
		this.car_class = car_class;
	}

	public String getUse_type() {
		return use_type;
	}

	public void setUse_type(String use_type) {
		this.use_type = use_type;
	}

	public Date getFirst_date() {
		return first_date;
	}

	public void setFirst_date(Date first_date) {
		this.first_date = first_date;
	}

	public String getClause_type() {
		return clause_type;
	}

	public void setClause_type(String clause_type) {
		this.clause_type = clause_type;
	}

	public Double getNet_premium() {
		return net_premium;
	}

	public void setNet_premium(Double net_premium) {
		this.net_premium = net_premium;
	}

	public Double getVehicel_tax() {
		return vehicel_tax;
	}

	public void setVehicel_tax(Double vehicel_tax) {
		this.vehicel_tax = vehicel_tax;
	}

	public String getCar_branchname() {
		return car_branchname;
	}

	public void setCar_branchname(String car_branchname) {
		this.car_branchname = car_branchname;
	}

	public Date getPrint_date() {
		return print_date;
	}

	public void setPrint_date(Date print_date) {
		this.print_date = print_date;
	}

	public String getApproval_flag() {
		return approval_flag;
	}

	public void setApproval_flag(String approval_flag) {
		this.approval_flag = approval_flag;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPremium_tax() {
		return premium_tax;
	}

	public void setPremium_tax(Double premium_tax) {
		this.premium_tax = premium_tax;
	}

	public String getEndor_type() {
		return endor_type;
	}

	public void setEndor_type(String endor_type) {
		this.endor_type = endor_type;
	}

	public String getProvince_orgid() {
		return province_orgid;
	}

	public void setProvince_orgid(String province_orgid) {
		this.province_orgid = province_orgid;
	}

	public String getProvince_orgname() {
		return province_orgname;
	}

	public void setProvince_orgname(String province_orgname) {
		this.province_orgname = province_orgname;
	}

	public String getCity_orgid() {
		return city_orgid;
	}

	public void setCity_orgid(String city_orgid) {
		this.city_orgid = city_orgid;
	}

	public String getCity_orgname() {
		return city_orgname;
	}

	public void setCity_orgname(String city_orgname) {
		this.city_orgname = city_orgname;
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

	public String getResponsible_no() {
		return responsible_no;
	}

	public void setResponsible_no(String responsible_no) {
		this.responsible_no = responsible_no;
	}

	public String getResponsible_name() {
		return responsible_name;
	}

	public void setResponsible_name(String responsible_name) {
		this.responsible_name = responsible_name;
	}

	public String getPerson_cid() {
		return person_cid;
	}

	public void setPerson_cid(String person_cid) {
		this.person_cid = person_cid;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public Date getMdf_date() {
		return mdf_date;
	}

	public void setMdf_date(Date mdf_date) {
		this.mdf_date = mdf_date;
	}

	public String getMdf_user() {
		return mdf_user;
	}

	public void setMdf_user(String mdf_user) {
		this.mdf_user = mdf_user;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public Date getIns_validate() {
		return ins_validate;
	}

	public void setIns_validate(Date ins_validate) {
		this.ins_validate = ins_validate;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getChange_nu() {
		return change_nu;
	}

	public void setChange_nu(String change_nu) {
		this.change_nu = change_nu;
	}

	public String getOwner_no() {
		return owner_no;
	}

	public void setOwner_no(String owner_no) {
		this.owner_no = owner_no;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getInter_flag() {
		return inter_flag;
	}

	public void setInter_flag(String inter_flag) {
		this.inter_flag = inter_flag;
	}

	public String getBusiness_flag() {
		return business_flag;
	}

	public void setBusiness_flag(String business_flag) {
		this.business_flag = business_flag;
	}

	public Double getService_fee() {
		return service_fee;
	}

	public void setService_fee(Double service_fee) {
		this.service_fee = service_fee;
	}

	public Double getCar_value() {
		return car_value;
	}

	public void setCar_value(Double car_value) {
		this.car_value = car_value;
	}

	public String getCar_use_year() {
		return car_use_year;
	}

	public void setCar_use_year(String car_use_year) {
		this.car_use_year = car_use_year;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getSettled_date() {
		return settled_date;
	}

	public void setSettled_date(String settled_date) {
		this.settled_date = settled_date;
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

	public String getBranch_level_Branch() {
		return branch_level_Branch;
	}

	public void setBranch_level_Branch(String branch_level_Branch) {
		this.branch_level_Branch = branch_level_Branch;
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

	public Date getSettled_time() {
		return settled_time;
	}

	public void setSettled_time(Date settled_time) {
		this.settled_time = settled_time;
	}

	public String getSettled_date1() {
		return settled_date1;
	}

	public void setSettled_date1(String settled_date1) {
		this.settled_date1 = settled_date1;
	}

	public Date getIns_validate_L() {
		return ins_validate_L;
	}

	public void setIns_validate_L(Date ins_validate_L) {
		this.ins_validate_L = ins_validate_L;
	}

	public Date getIns_validate_G() {
		return ins_validate_G;
	}

	public void setIns_validate_G(Date ins_validate_G) {
		this.ins_validate_G = ins_validate_G;
	}

	public Date getApproval_date_L() {
		return approval_date_L;
	}

	public void setApproval_date_L(Date approval_date_L) {
		this.approval_date_L = approval_date_L;
	}

	public Date getApproval_date_G() {
		return approval_date_G;
	}

	public void setApproval_date_G(Date approval_date_G) {
		this.approval_date_G = approval_date_G;
	}

	
}
