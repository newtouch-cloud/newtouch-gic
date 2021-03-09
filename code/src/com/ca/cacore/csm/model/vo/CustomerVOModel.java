package com.ca.cacore.csm.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户查询页面VO
* 需要的表：CRM_CUSTOMER,CRM_CUSTOMER_CONTACT,CRM_CUSTOMER_ACTION;
 */
public class CustomerVOModel implements ICustomerVOModel{
	 
	   private String branch_id;//机构代码
	   private String branch_name;//机构名称
	   private String customer_id;//客户代码
	   private String name;//姓名
	   private String certi_type_name;//证件类型名称
	   private String certi_no;//证件号码
	   private String home_address;//被保险人住址
	   private String mobile;//	移动电话;
	   private String email;//	电子邮箱;
	   private String company_telphone;//电话
	   private String dept_list;
	   private String seq_id;
	   
	   public String getSeq_id() {
		return seq_id;
	   }
	   public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	   }
	   private String customer_type;//客户类型代码
	   private String type_name;//客户类型名称
	   private String member_id;//会员编号
	   private String gender;//性别代码
	   private String gender_name;//性别名称
	   private Date   birthday;//出生日期
	   private String certi_type;//证件类型代码
	   private Date certi_validdate;//证件有效期限
	   private String education;//学历
	   private String nationality;//国籍
	   private String nation;// 民族 Ref：SYS_Library_Nation
	   private String seat_address;// 户口所在地
	   private Integer height;// 身高
	   private Integer weight;// 体重
	   private String political;// 政治面貌 Ref：SYS_Library_Political
	   private String health;// 健康状况 Ref：SYS_Library_Health
	   private String bank_code;// 银行编码
	   private String bank_account_no;// 银行账号
	   private String bank_account_name;// 银行账户名
	   private String education2;//教育程度代码
	   private String education_name;//教育程度名称
	   private String marital_stat;//婚姻状况代码
	   private String marital_stat_name;//婚姻状况名称
	   private String address;//	家庭地址;
	   private String job_type;//职业类别代码
	   private String jobtype_name;//职业类别名称
	   private String job_code;//职业
	   private String income_type;//收入区间代码
	   private String incomtype_name;//收入区间名称
	   private String homeplace;//籍贯
	   private String status;// 状态0：失效 1：有效
	   private String remark; // 备注
	   private String createUser; // 创建人
	   private Date createDate; // 创建时间
	   private String modifyUser; // 最后修改人
	   private Date modifyDate; // 最后时间
		 
	   private String is_telmsgservice;//是否接受手机短信服务
	   private String zip;//	邮政编码;
	   private String fax;//	传真电话;
	   private String telphone;//	住宅电话;
	   private String job_com;//	工作单位名称;
	   private String job_tel ;//	办公电话;
	   private Integer log_seq_id;//	备份数据主键;
	   private String log_bustype;//	备份业务类型;
	   private Date log_busdate;//	备份业务归属时间;
	   private Date log_date;//	备份时间;
	   private String log_remark;//备份备注
		private String  insurant_name_value; //被保险人集合
		private String  insurant_name_arr; //被保险人id集合
		private String insurant_name="";//被保人姓名
		private String flag ;//客户明细标志
		private String policy_tr_id;//被选中的tr
		private String  relation1 ;//与投保人关系
		private String relation2;//与主被保人关系
		private String bene_type;//收益性质
		private Integer bene_order  ;//收益顺序
		private Double bene_rate ;//收益比例
		private String relation ;
		private String policyno;//保单号
		private String car_no;//车牌号
		private String claimno;//赔案号
		private String insurCount;//出险次数
		private String emp_id;
		private String company_name;                 //单位名称
		private String company_address;              //地址
		private String company_fax;                  //传真
		private String company_mobile;               //联系方式
		private String company_postcode;             //邮编
		private String corporation_represen;         //法人代表
		private String company_url;                  //公司网址
		private String company_mail;                 //电子邮件
		private String company_industry;             //行业性质
		private String corporation_represen_qq;      //qq(法人代表)
		private String corporation_represen_wechat;  //微信(法人代表)
		private String company_remark;               //备注
		private String corporation_contact_person;   //联系人
		private String corporation_contact_mobile;   //联系电话
		private String corporation_contact_qq;       // qq(联系人)
		private String corporation_contact_wechat;   // 微信(联系人)		
		
		private String insured_cid;//被保险人身份证
		private String insured_company_type;//被保险人性质
		private String insured_papertype;//被保险人证件类型（代码/名称）
		private String insured_mailbox;//被保险人邮箱
		private String insured_phone;//被保险人手机
		private String insured_tel;//被保险人固话
		private String insured_name;//被保险人姓名
		private String org_id;
	    private PageCount pageCount = new PageCount();
	    private double sum;
		
	    public double getSum() {
			return sum;
		}
		public void setSum(double sum) {
			this.sum = sum;
		}
		public String getOrg_id() {
			return org_id;
		}
		public void setOrg_id(String org_id) {
			this.org_id = org_id;
		}
		public String getInsured_name() {
			return insured_name;
		}
		public void setInsured_name(String insured_name) {
			this.insured_name = insured_name;
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
		
		
		
		public String getRelation() {
			return relation;
		}
		public void setRelation(String relation) {
			this.relation = relation;
		}
		public String getInsurant_name_value() {
			return insurant_name_value;
		}
		public void setInsurant_name_value(String insurant_name_value) {
			this.insurant_name_value = insurant_name_value;
		}
		public String getInsurant_name_arr() {
			return insurant_name_arr;
		}
		public void setInsurant_name_arr(String insurant_name_arr) {
			this.insurant_name_arr = insurant_name_arr;
		}
		public String getInsurant_name() {
			return insurant_name;
		}
		public void setInsurant_name(String insurant_name) {
			this.insurant_name = insurant_name;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public String getPolicy_tr_id() {
			return policy_tr_id;
		}
		public void setPolicy_tr_id(String policy_tr_id) {
			this.policy_tr_id = policy_tr_id;
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
		public String getBene_type() {
			return bene_type;
		}
		public void setBene_type(String bene_type) {
			this.bene_type = bene_type;
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
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender_name() {
		return gender_name;
	}
	public void setGender_name(String gender_name) {
		this.gender_name = gender_name;
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
	public String getCerti_type_name() {
		return certi_type_name;
	}
	public void setCerti_type_name(String certi_type_name) {
		this.certi_type_name = certi_type_name;
	}
	public String getCerti_no() {
		return certi_no;
	}
	public void setCerti_no(String certi_no) {
		this.certi_no = certi_no;
	}
	public Date getCerti_validdate() {
		return certi_validdate;
	}
	public void setCerti_validdate(Date certi_validdate) {
		this.certi_validdate = certi_validdate;
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
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
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
	public String getEducation2() {
		return education2;
	}
	public void setEducation2(String education2) {
		this.education2 = education2;
	}
	public String getEducation_name() {
		return education_name;
	}
	public void setEducation_name(String education_name) {
		this.education_name = education_name;
	}
	public String getMarital_stat() {
		return marital_stat;
	}
	public void setMarital_stat(String marital_stat) {
		this.marital_stat = marital_stat;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	public String getJobtype_name() {
		return jobtype_name;
	}
	public void setJobtype_name(String jobtype_name) {
		this.jobtype_name = jobtype_name;
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
	public String getIncomtype_name() {
		return incomtype_name;
	}
	public void setIncomtype_name(String incomtype_name) {
		this.incomtype_name = incomtype_name;
	}
	public String getHomeplace() {
		return homeplace;
	}
	public void setHomeplace(String homeplace) {
		this.homeplace = homeplace;
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
	public String getIs_telmsgservice() {
		return is_telmsgservice;
	}
	public void setIs_telmsgservice(String is_telmsgservice) {
		this.is_telmsgservice = is_telmsgservice;
	}
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
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
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	
	public String getMarital_stat_name() {
		return marital_stat_name;
	}
	public void setMarital_stat_name(String marital_stat_name) {
		this.marital_stat_name = marital_stat_name;
	}
	public String getLog_remark() {
		return log_remark;
	}
	public void setLog_remark(String log_remark) {
		this.log_remark = log_remark;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	public String getCar_no() {
		return car_no;
	}
	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}
	public String getClaimno() {
		return claimno;
	}
	public void setClaimno(String claimno) {
		this.claimno = claimno;
	}
	public String getInsurCount() {
		return insurCount;
	}
	public void setInsurCount(String insurCount) {
		this.insurCount = insurCount;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_telphone() {
		return company_telphone;
	}
	public void setCompany_telphone(String company_telphone) {
		this.company_telphone = company_telphone;
	}
	public String getCompany_fax() {
		return company_fax;
	}
	public void setCompany_fax(String company_fax) {
		this.company_fax = company_fax;
	}
	public String getCompany_mobile() {
		return company_mobile;
	}
	public void setCompany_mobile(String company_mobile) {
		this.company_mobile = company_mobile;
	}
	public String getCompany_postcode() {
		return company_postcode;
	}
	public void setCompany_postcode(String company_postcode) {
		this.company_postcode = company_postcode;
	}
	public String getCorporation_represen() {
		return corporation_represen;
	}
	public void setCorporation_represen(String corporation_represen) {
		this.corporation_represen = corporation_represen;
	}
	public String getCompany_url() {
		return company_url;
	}
	public void setCompany_url(String company_url) {
		this.company_url = company_url;
	}
	public String getCompany_mail() {
		return company_mail;
	}
	public void setCompany_mail(String company_mail) {
		this.company_mail = company_mail;
	}
	public String getCompany_industry() {
		return company_industry;
	}
	public void setCompany_industry(String company_industry) {
		this.company_industry = company_industry;
	}
	public String getCorporation_represen_qq() {
		return corporation_represen_qq;
	}
	public void setCorporation_represen_qq(String corporation_represen_qq) {
		this.corporation_represen_qq = corporation_represen_qq;
	}
	public String getCorporation_represen_wechat() {
		return corporation_represen_wechat;
	}
	public void setCorporation_represen_wechat(String corporation_represen_wechat) {
		this.corporation_represen_wechat = corporation_represen_wechat;
	}
	public String getCompany_remark() {
		return company_remark;
	}
	public void setCompany_remark(String company_remark) {
		this.company_remark = company_remark;
	}
	public String getCorporation_contact_person() {
		return corporation_contact_person;
	}
	public void setCorporation_contact_person(String corporation_contact_person) {
		this.corporation_contact_person = corporation_contact_person;
	}
	public String getCorporation_contact_mobile() {
		return corporation_contact_mobile;
	}
	public void setCorporation_contact_mobile(String corporation_contact_mobile) {
		this.corporation_contact_mobile = corporation_contact_mobile;
	}
	public String getCorporation_contact_qq() {
		return corporation_contact_qq;
	}
	public void setCorporation_contact_qq(String corporation_contact_qq) {
		this.corporation_contact_qq = corporation_contact_qq;
	}
	public String getCorporation_contact_wechat() {
		return corporation_contact_wechat;
	}
	public void setCorporation_contact_wechat(String corporation_contact_wechat) {
		this.corporation_contact_wechat = corporation_contact_wechat;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getDept_list() {
		return dept_list;
	}
	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}
}
