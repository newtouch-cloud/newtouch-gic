package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

public class PolicyLifePeopleVOModel implements IPolicyLifePeopleVOModel {
	private Integer seq_id; // 序号
	private String customer_id;// 客户号
	private String name;// 姓名
	private String title;// 称呼
	private String gender;// 性别
	private Date birthday;// 出生日期
	private String certi_type;// 证件类型Ref：SYS_Library_Certi_Type
	private String certi_no;// 证件号码
	private String education;// 学历
	private String nationality;// 国籍
	private String nation;// 民族 Ref：SYS_Library_Nation
	private String homeplace;// 籍贯
	private String seat_address;// 户口所在地
	private Integer height;// 身高
	private Integer weight;// 体重
	private String political;// 政治面貌 Ref：SYS_Library_Political
	private String education2;// 教育程度 Ref：SYS_Library_Education
	private String marital_stat;// 婚姻状况 Ref：SYS_Library_Marital
	private String health;// 健康状况 Ref：SYS_Library_Health
	private String job_type;// 职业类别 Ref：SYS_Library_JobType
	private String job_code;// 职业
	private String  income_type;// 收入
	private String bank_code;// 银行编码
	private String bank_account_no;// 银行账号
	private String bank_account_name;// 银行账户名
	private String status;// 状态0：失效 1：有效
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
	private String branch_id; // 机构代码 Ref：SYS_Branch
	private String send_code; // 投保单号码
	private Long policy_id; // 保单号
	private String app_name;// 投保人姓名
	private String app_certi_code;// 投保人证件类型 Ref：SYS_Library_Certi_Type
	private String app_certi_no;// 投保人证件号码
	private String app_address;// 投保人地址
	private String app_tel;// 投保人联系电话
	private String app_zip;// 投保人邮编
	private String sex;//性别
	private String app_email;//
	private String relation1; // 与投保人关系
	private String relation2; // 与主被保人关系
	private String relation1_name; // 与投保人关系
	private String relation2_name; // 与主被保人关系
	private String insurant_name;// 被保人姓名
	private String insurant_certi_code; // 被保人证件类型 Ref：SYS_Library_Certi_Type
	private String insurant_certi_no;// 被保人证件号码
	private String insurant_tel; // 被保人联系电话
	private String insurant_email;//邮箱
	private String insurant_address;//地址
	private String bene_type;// 受益性质 Ref：CBS_Beneficiary_Type
	private String ben_email;//邮箱
	private String bene_address;//地址
	private String bene_certi_code; // 受益人证件类型 Ref：SYS_Library_Certi_Type
	private String bene_certi_no;// 受益人证件号码
	private String bene_tel; // 受益人联系电话
	private String bene_type_name;// 受益性质 Ref：CBS_Beneficiary_Type
	private String bene_name;//受益人姓名	
	//顾客备份表
	 private String address;//	家庭地址;
	 private String zip;//	邮政编码;
	 private String mobile;//	移动电话;
	 private String fax;//	传真电话;
	 private String telphone;//	住宅电话;
	 private String email;//	电子邮箱;
	 private String job_com;//	工作单位名称;
	 private String job_tel ;//	办公电话;
	 private Integer log_seq_id;//	备份数据主键;
	 private String log_bustype;//	备份业务类型;
	 private Date log_busdate;//	备份业务归属时间;
	 private Date log_date;//	备份时间;
	 private String log_remark;//	备份备注;
	 private String relation;
	 private String sales_name;//机构名字
	 private String branch_name;//机构代码
	 private String policy_tr_id;//选中的tr的id
	 private String flag;//状态标志位
	 
	 	private String  insurant_name_value; //被保险人集合
		private String  insurant_name_arr; //被保险人id集合
		private String name_tow;// 投保人姓名（二录）
		private String type="block";//view为查看 update为修改
		private String product_id;//险种id
		private String product_name;//险种名称
		private String coverage_period;//保障期限类型
		private String coverage_year;//保障年限
		private String charge_period;//缴费期限类型
		private String charge_year;//缴费年龄或年限
		private String charge_type_code;//缴费方式
		private String coverage_time;//基本保险金额
		
		private String jsp_url;//根据页面传值进行跳转
		
		private String insBranch_id; // 保险公司机构
		private String insBranch_name;//保险公司名称
		private String agent_id; // 保单销售人员 REF: SMIS_Sales
		private String agent_name; // 保单销售人员 REF: SMIS_Sales
		private String policy_code; // 保单号
		private String service_id; // 保单服务人员 REF:SMIS_Sales
		private String service_name; // 保单服务人员 REF:SMIS_Sales
		private String holder_id; // 投保人 REF:CBS_Customer
		private String insurant_id; // 第一被保人 REF:CBS_Customer
		private String relation_id; // 投保人与被保人关系 REF:CBS_Applicant_Relation1
		private String relation_name;//投保人与主被保人关系
		private String sell_way; // 消费方式 Ref：CBS_Policy_Sell_Way
		private String charge_type; // 当期缴费方式 Ref：PDT_Lib_ChargeType
		private String charge_next; // 续期缴费方式 Ref：PDT_Lib_ChargeType
		private String pay_mode; //当期付款方式 Ref：SYS_Library_PayMode
		private String pay_mode_next; // 续期付款方式 Ref：SYS_Library_PayMode
		private String policy_year; // 保单年度
		private String policy_period; // 缴费期次
		private String period_prem; // 保费合计
		private String money_id; // 币种 Ref：SYS_Library_MoneyType
		private String account_type; // 银行账号类型 Ref：SYS_Library_BankAccountType
		private String bank_accName; // 银行开户人
		private String bank_account; // 银行账号
		private String overdue_manage; // 保险费逾期未付的选择 Ref：CBS_PolicyLife_Overdue
		private Date hold_date; // 投保日期
		private Date scan_time; // 影像上传日期
		private Date validate_date; // 保单生效日期
		private Date sign_date; // 客户签收日期
		private Date end_date; // 保单终止日期
		private Date pause_time; // 保单停效日期
		private String is_answered; // 是否已回访 Y是N否
		private String high_policy; // 是否高额件 Y是N否
		private String status_name; // 保单的状态
		private Date rtn_date;//回执核销日期
		private String[]policyPeople_customer_id;
		private String[]policyLifeInsured_customer_id;
		private String[]policyLifeInsured_relation1;
		private String[]policyLifeInsured_relation_code;
		private String[]policyLifeBeneficiary_customer_id;
		private String[]policyLifeBeneficiary_insurant_name_value;
		private String[]policyLifeBeneficiary_relation1;
		private String[]policyLifeBeneficiary_relation_code;
		private String[]policyLifeBeneficiary_bene_type_code;
		private String[]policyLifeBeneficiay_bene_order;
		private String[]policyLifeBeneficiay_bene_rate;
		
		//受益人新增:
		private Integer bene_age;//收益人年龄
		private Integer bene_order;//收益顺序
		private Double bene_rate;//收益比例
		
		//customer
		private Date certi_validDate; //证件有效期限
		private String is_telMsgService;//是否接受手机短信服务
		private String customer_type;//客户类型
		private String gender_name;//
		private String certi_type_name;//
		private Integer age;//年龄
		private String sales_status;//销售人员状态
		private String nation_name;//民族
		private String nationality_name;//国籍
		private String incomtype_name;//收入范围名字
		private String marital_stat_name;//婚姻名称
		private String political_name;//政治面貌名称
		private String education_name;//教育程度名称
		private String health_name;//健康状况名称
		private String is_telmsgserviceName;//是否接受短信服务
		
		
      	public String getNation_name() {
			return nation_name;
		}
		public void setNation_name(String nation_name) {
			this.nation_name = nation_name;
		}
		public String getNationality_name() {
			return nationality_name;
		}
		public void setNationality_name(String nationality_name) {
			this.nationality_name = nationality_name;
		}
		public String getIncomtype_name() {
			return incomtype_name;
		}
		public void setIncomtype_name(String incomtype_name) {
			this.incomtype_name = incomtype_name;
		}
		public String getMarital_stat_name() {
			return marital_stat_name;
		}
		public void setMarital_stat_name(String marital_stat_name) {
			this.marital_stat_name = marital_stat_name;
		}
		public String getPolitical_name() {
			return political_name;
		}
		public void setPolitical_name(String political_name) {
			this.political_name = political_name;
		}
		public String getEducation_name() {
			return education_name;
		}
		public void setEducation_name(String education_name) {
			this.education_name = education_name;
		}
		public String getHealth_name() {
			return health_name;
		}
		public void setHealth_name(String health_name) {
			this.health_name = health_name;
		}
		public String getIs_telmsgserviceName() {
			return is_telmsgserviceName;
		}
		public void setIs_telmsgserviceName(String is_telmsgserviceName) {
			this.is_telmsgserviceName = is_telmsgserviceName;
		}
		public String getSales_status() {
			return sales_status;
		}
		public void setSales_status(String sales_status) {
			this.sales_status = sales_status;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public String getGender_name() {
			return gender_name;
		}
		public void setGender_name(String gender_name) {
			this.gender_name = gender_name;
		}
		public String getCerti_type_name() {
			return certi_type_name;
		}
		public void setCerti_type_name(String certi_type_name) {
			this.certi_type_name = certi_type_name;
		}
		public String getCustomer_type() {
					return customer_type;
				}
				public void setCustomer_type(String customer_type) {
					this.customer_type = customer_type;
				}
		public Date getCerti_validDate() {
			return certi_validDate;
		}
		public void setCerti_validDate(Date certi_validDate) {
			this.certi_validDate = certi_validDate;
		}
		public String getIs_telMsgService() {
			return is_telMsgService;
		}
		public void setIs_telMsgService(String is_telMsgService) {
			this.is_telMsgService = is_telMsgService;
		}
	public String[] getPolicyLifeBeneficiay_bene_order() {
			return policyLifeBeneficiay_bene_order;
		}
		public void setPolicyLifeBeneficiay_bene_order(
				String[] policyLifeBeneficiay_bene_order) {
			this.policyLifeBeneficiay_bene_order = policyLifeBeneficiay_bene_order;
		}
		public String[] getPolicyLifeBeneficiay_bene_rate() {
			return policyLifeBeneficiay_bene_rate;
		}
		public void setPolicyLifeBeneficiay_bene_rate(
				String[] policyLifeBeneficiay_bene_rate) {
			this.policyLifeBeneficiay_bene_rate = policyLifeBeneficiay_bene_rate;
		}
	public String getInsurant_name_arr() {
			return insurant_name_arr;
		}
		public void setInsurant_name_arr(String insurant_name_arr) {
			this.insurant_name_arr = insurant_name_arr;
		}
		public String getName_tow() {
			return name_tow;
		}
		public void setName_tow(String name_tow) {
			this.name_tow = name_tow;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getProduct_id() {
			return product_id;
		}
		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
		public String getCoverage_period() {
			return coverage_period;
		}
		public void setCoverage_period(String coverage_period) {
			this.coverage_period = coverage_period;
		}
		public String getCoverage_year() {
			return coverage_year;
		}
		public void setCoverage_year(String coverage_year) {
			this.coverage_year = coverage_year;
		}
		public String getCharge_period() {
			return charge_period;
		}
		public void setCharge_period(String charge_period) {
			this.charge_period = charge_period;
		}
		public String getCharge_year() {
			return charge_year;
		}
		public void setCharge_year(String charge_year) {
			this.charge_year = charge_year;
		}
		public String getCharge_type_code() {
			return charge_type_code;
		}
		public void setCharge_type_code(String charge_type_code) {
			this.charge_type_code = charge_type_code;
		}
		public String getCoverage_time() {
			return coverage_time;
		}
		public void setCoverage_time(String coverage_time) {
			this.coverage_time = coverage_time;
		}
		public String getJsp_url() {
			return jsp_url;
		}
		public void setJsp_url(String jsp_url) {
			this.jsp_url = jsp_url;
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
		public String getService_name() {
			return service_name;
		}
		public void setService_name(String service_name) {
			this.service_name = service_name;
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
		public String getRelation_name() {
			return relation_name;
		}
		public void setRelation_name(String relation_name) {
			this.relation_name = relation_name;
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
		public String getHigh_policy() {
			return high_policy;
		}
		public void setHigh_policy(String high_policy) {
			this.high_policy = high_policy;
		}
		public String getStatus_name() {
			return status_name;
		}
		public void setStatus_name(String status_name) {
			this.status_name = status_name;
		}
		public Date getRtn_date() {
			return rtn_date;
		}
		public void setRtn_date(Date rtn_date) {
			this.rtn_date = rtn_date;
		}
		public String[] getPolicyPeople_customer_id() {
			return policyPeople_customer_id;
		}
		public void setPolicyPeople_customer_id(String[] policyPeople_customer_id) {
			this.policyPeople_customer_id = policyPeople_customer_id;
		}
		public String[] getPolicyLifeInsured_customer_id() {
			return policyLifeInsured_customer_id;
		}
		public void setPolicyLifeInsured_customer_id(
				String[] policyLifeInsured_customer_id) {
			this.policyLifeInsured_customer_id = policyLifeInsured_customer_id;
		}
		public String[] getPolicyLifeInsured_relation1() {
			return policyLifeInsured_relation1;
		}
		public void setPolicyLifeInsured_relation1(String[] policyLifeInsured_relation1) {
			this.policyLifeInsured_relation1 = policyLifeInsured_relation1;
		}
		public String[] getPolicyLifeInsured_relation_code() {
			return policyLifeInsured_relation_code;
		}
		public void setPolicyLifeInsured_relation_code(
				String[] policyLifeInsured_relation_code) {
			this.policyLifeInsured_relation_code = policyLifeInsured_relation_code;
		}
		public String[] getPolicyLifeBeneficiary_customer_id() {
			return policyLifeBeneficiary_customer_id;
		}
		public void setPolicyLifeBeneficiary_customer_id(
				String[] policyLifeBeneficiary_customer_id) {
			this.policyLifeBeneficiary_customer_id = policyLifeBeneficiary_customer_id;
		}
		public String[] getPolicyLifeBeneficiary_insurant_name_value() {
			return policyLifeBeneficiary_insurant_name_value;
		}
		public void setPolicyLifeBeneficiary_insurant_name_value(
				String[] policyLifeBeneficiary_insurant_name_value) {
			this.policyLifeBeneficiary_insurant_name_value = policyLifeBeneficiary_insurant_name_value;
		}
		public String[] getPolicyLifeBeneficiary_relation1() {
			return policyLifeBeneficiary_relation1;
		}
		public void setPolicyLifeBeneficiary_relation1(
				String[] policyLifeBeneficiary_relation1) {
			this.policyLifeBeneficiary_relation1 = policyLifeBeneficiary_relation1;
		}
		public String[] getPolicyLifeBeneficiary_relation_code() {
			return policyLifeBeneficiary_relation_code;
		}
		public void setPolicyLifeBeneficiary_relation_code(
				String[] policyLifeBeneficiary_relation_code) {
			this.policyLifeBeneficiary_relation_code = policyLifeBeneficiary_relation_code;
		}
		public String[] getPolicyLifeBeneficiary_bene_type_code() {
			return policyLifeBeneficiary_bene_type_code;
		}
		public void setPolicyLifeBeneficiary_bene_type_code(
				String[] policyLifeBeneficiary_bene_type_code) {
			this.policyLifeBeneficiary_bene_type_code = policyLifeBeneficiary_bene_type_code;
		}
	public String getPolicy_tr_id() {
		return policy_tr_id;
	}
	public void setPolicy_tr_id(String policy_tr_id) {
		this.policy_tr_id = policy_tr_id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSales_name() {
		return sales_name;
	}
	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCerti_type() {
		return certi_type;
	}
	public void setCerti_type(String certi_type) {
		this.certi_type = certi_type;
	}
	public String getCerti_no() {
		return certi_no;
	}
	public void setCerti_no(String certi_no) {
		this.certi_no = certi_no;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getHomeplace() {
		return homeplace;
	}
	public void setHomeplace(String homeplace) {
		this.homeplace = homeplace;
	}
	public String getSeat_address() {
		return seat_address;
	}
	public void setSeat_address(String seat_address) {
		this.seat_address = seat_address;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getEducation2() {
		return education2;
	}
	public void setEducation2(String education2) {
		this.education2 = education2;
	}
	public String getMarital_stat() {
		return marital_stat;
	}
	public void setMarital_stat(String marital_stat) {
		this.marital_stat = marital_stat;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	public String getJob_code() {
		return job_code;
	}
	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}
	public String getIncome_type() {
		return income_type;
	}
	public void setIncome_type(String income_type) {
		this.income_type = income_type;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_account_no() {
		return bank_account_no;
	}
	public void setBank_account_no(String bank_account_no) {
		this.bank_account_no = bank_account_no;
	}
	public String getBank_account_name() {
		return bank_account_name;
	}
	public void setBank_account_name(String bank_account_name) {
		this.bank_account_name = bank_account_name;
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
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getSend_code() {
		return send_code;
	}
	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	public Long getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getApp_certi_code() {
		return app_certi_code;
	}
	public void setApp_certi_code(String app_certi_code) {
		this.app_certi_code = app_certi_code;
	}
	public String getApp_certi_no() {
		return app_certi_no;
	}
	public void setApp_certi_no(String app_certi_no) {
		this.app_certi_no = app_certi_no;
	}
	public String getApp_address() {
		return app_address;
	}
	public void setApp_address(String app_address) {
		this.app_address = app_address;
	}
	public String getApp_tel() {
		return app_tel;
	}
	public void setApp_tel(String app_tel) {
		this.app_tel = app_tel;
	}
	public String getApp_zip() {
		return app_zip;
	}
	public void setApp_zip(String app_zip) {
		this.app_zip = app_zip;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getApp_email() {
		return app_email;
	}
	public void setApp_email(String app_email) {
		this.app_email = app_email;
	}
	public String getRelation1() {
		return relation1;
	}
	public void setRelation1(String relation1) {
		this.relation1 = relation1;
	}
	public String getRelation2() {
		return relation2;
	}
	public void setRelation2(String relation2) {
		this.relation2 = relation2;
	}
	public String getRelation1_name() {
		return relation1_name;
	}
	public void setRelation1_name(String relation1_name) {
		this.relation1_name = relation1_name;
	}
	public String getRelation2_name() {
		return relation2_name;
	}
	public void setRelation2_name(String relation2_name) {
		this.relation2_name = relation2_name;
	}
	public String getInsurant_name() {
		return insurant_name;
	}
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}
	public String getInsurant_certi_code() {
		return insurant_certi_code;
	}
	public void setInsurant_certi_code(String insurant_certi_code) {
		this.insurant_certi_code = insurant_certi_code;
	}
	public String getInsurant_certi_no() {
		return insurant_certi_no;
	}
	public void setInsurant_certi_no(String insurant_certi_no) {
		this.insurant_certi_no = insurant_certi_no;
	}
	public String getInsurant_tel() {
		return insurant_tel;
	}
	public void setInsurant_tel(String insurant_tel) {
		this.insurant_tel = insurant_tel;
	}
	public String getInsurant_email() {
		return insurant_email;
	}
	public void setInsurant_email(String insurant_email) {
		this.insurant_email = insurant_email;
	}
	public String getInsurant_address() {
		return insurant_address;
	}
	public void setInsurant_address(String insurant_address) {
		this.insurant_address = insurant_address;
	}
	public String getBene_type() {
		return bene_type;
	}
	public void setBene_type(String bene_type) {
		this.bene_type = bene_type;
	}
	public String getBen_email() {
		return ben_email;
	}
	public void setBen_email(String ben_email) {
		this.ben_email = ben_email;
	}
	public String getBene_address() {
		return bene_address;
	}
	public void setBene_address(String bene_address) {
		this.bene_address = bene_address;
	}
	public String getBene_certi_code() {
		return bene_certi_code;
	}
	public void setBene_certi_code(String bene_certi_code) {
		this.bene_certi_code = bene_certi_code;
	}
	public String getBene_certi_no() {
		return bene_certi_no;
	}
	public void setBene_certi_no(String bene_certi_no) {
		this.bene_certi_no = bene_certi_no;
	}
	public String getBene_tel() {
		return bene_tel;
	}
	public void setBene_tel(String bene_tel) {
		this.bene_tel = bene_tel;
	}
	public String getBene_type_name() {
		return bene_type_name;
	}
	public void setBene_type_name(String bene_type_name) {
		this.bene_type_name = bene_type_name;
	}
	public String getBene_name() {
		return bene_name;
	}
	public void setBene_name(String bene_name) {
		this.bene_name = bene_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob_com() {
		return job_com;
	}
	public void setJob_com(String job_com) {
		this.job_com = job_com;
	}
	public String getJob_tel() {
		return job_tel;
	}
	public void setJob_tel(String job_tel) {
		this.job_tel = job_tel;
	}
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
	public Integer getBene_age() {
		return bene_age;
	}
	public void setBene_age(Integer bene_age) {
		this.bene_age = bene_age;
	}
	public Integer getBene_order() {
		return bene_order;
	}
	public void setBene_order(Integer bene_order) {
		this.bene_order = bene_order;
	}
	public Double getBene_rate() {
		return bene_rate;
	}
	public void setBene_rate(Double bene_rate) {
		this.bene_rate = bene_rate;
	}
	public String getInsurant_name_value() {
		return insurant_name_value;
	}
	public void setInsurant_name_value(String insurant_name_value) {
		this.insurant_name_value = insurant_name_value;
	}
	
}
