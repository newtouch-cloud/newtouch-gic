package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 
* @since:    2014年1月2日   
* @author    newtouchlxy
* @description:客户基本信息日志表(CRM_Customer_Log)
 */
public class CustomerLogModel implements ICustomerLogModel {
	private Integer seq_id; // 序号
	private String customer_id;// 客户代码
	private String member_id;//会员编号
	private String customer_type;//客户类型
	private String name;// 姓名
	private String title;// 称呼
	private String gender;// 性别
	private Date birthday;// 出生日期
	private String certi_type;// 证件类型
	private String certi_no;// 证件号码
	private Date certi_validdate; //证件有效期限
	private String education;// 学历
	private String nationality;// 国籍
	private String nation;// 民族 
	private String homeplace;// 籍贯
	private String seat_address;// 户口所在地
	private String height;// 身高
	private String weight;// 体重
	private String political;// 政治面貌
	private String education2;// 教育程度 
	private String marital_stat;// 婚姻状况 
	private String health;// 健康状况
	private String job_type;// 职业类别 
	private String job_code;// 职业
	private String income_type;// 收入
	private String bank_code;// 银行编码
	private String bank_account_no;// 银行账号
	private String bank_account_name;// 银行账户名
	private String is_telmsgservice;//是否接受手机短信服务
	private String status;// 状态0：失效 1：有效
	private String remark; // 备注
	private Integer log_seq_id;// 备份数据主键
	private String log_bustype; //备份业务类型
	private Date log_busdate;//备份业务归属时间
	private Date log_date;//备份时间
	private String log_remark;//备份备注
	private String createuser; // 创建人
	private Date createdate; // 创建时间
	private String modifyuser; // 最后修改人
	private Date modifydate; // 最后时间
	private PageCount pageCount = new PageCount();
	private String company_name;                 //单位名称
	   private String company_address;              //地址
	   private String company_telphone;             //电话
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
	/** 
	* 
	* @return 
	* @description:
	*/
	public Integer getSeq_id() {
		return seq_id;
	}
	/** 
	* 
	* @param seq_id 
	* @description:
	*/
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getCustomer_id() {
		return customer_id;
	}
	/** 
	* 
	* @param customer_id 
	* @description:
	*/
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getCustomer_type() {
		return customer_type;
	}
	/** 
	* 
	* @param customer_type 
	* @description:
	*/
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getName() {
		return name;
	}
	/** 
	* 
	* @param name 
	* @description:
	*/
	public void setName(String name) {
		this.name = name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getTitle() {
		return title;
	}
	/** 
	* 
	* @param title 
	* @description:
	*/
	public void setTitle(String title) {
		this.title = title;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getGender() {
		return gender;
	}
	/** 
	* 
	* @param gender 
	* @description:
	*/
	public void setGender(String gender) {
		this.gender = gender;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Date getBirthday() {
		return birthday;
	}
	/** 
	* 
	* @param birthday 
	* @description:
	*/
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getCerti_type() {
		return certi_type;
	}
	/** 
	* 
	* @param certi_type 
	* @description:
	*/
	public void setCerti_type(String certi_type) {
		this.certi_type = certi_type;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getCerti_no() {
		return certi_no;
	}
	/** 
	* 
	* @param certi_no 
	* @description:
	*/
	public void setCerti_no(String certi_no) {
		this.certi_no = certi_no;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Date getCerti_validdate() {
		return certi_validdate;
	}
	/** 
	* 
	* @param certi_validdate 
	* @description:
	*/
	public void setCerti_validdate(Date certi_validdate) {
		this.certi_validdate = certi_validdate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getEducation() {
		return education;
	}
	/** 
	* 
	* @param education 
	* @description:
	*/
	public void setEducation(String education) {
		this.education = education;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getNationality() {
		return nationality;
	}
	/** 
	* 
	* @param nationality 
	* @description:
	*/
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getNation() {
		return nation;
	}
	/** 
	* 
	* @param nation 
	* @description:
	*/
	public void setNation(String nation) {
		this.nation = nation;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getHomeplace() {
		return homeplace;
	}
	/** 
	* 
	* @param homeplace 
	* @description:
	*/
	public void setHomeplace(String homeplace) {
		this.homeplace = homeplace;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getSeat_address() {
		return seat_address;
	}
	/** 
	* 
	* @param seat_address 
	* @description:
	*/
	public void setSeat_address(String seat_address) {
		this.seat_address = seat_address;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getHeight() {
		return height;
	}
	/** 
	* 
	* @param height 
	* @description:
	*/
	public void setHeight(String height) {
		this.height = height;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getWeight() {
		return weight;
	}
	/** 
	* 
	* @param weight 
	* @description:
	*/
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getPolitical() {
		return political;
	}
	/** 
	* 
	* @param political 
	* @description:
	*/
	public void setPolitical(String political) {
		this.political = political;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getEducation2() {
		return education2;
	}
	/** 
	* 
	* @param education2 
	* @description:
	*/
	public void setEducation2(String education2) {
		this.education2 = education2;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getMarital_stat() {
		return marital_stat;
	}
	/** 
	* 
	* @param marital_stat 
	* @description:
	*/
	public void setMarital_stat(String marital_stat) {
		this.marital_stat = marital_stat;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getHealth() {
		return health;
	}
	/** 
	* 
	* @param health 
	* @description:
	*/
	public void setHealth(String health) {
		this.health = health;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getJob_type() {
		return job_type;
	}
	/** 
	* 
	* @param job_type 
	* @description:
	*/
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getJob_code() {
		return job_code;
	}
	/** 
	* 
	* @param job_code 
	* @description:
	*/
	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getIncome_type() {
		return income_type;
	}
	/** 
	* 
	* @param income_type 
	* @description:
	*/
	public void setIncome_type(String income_type) {
		this.income_type = income_type;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getBank_code() {
		return bank_code;
	}
	/** 
	* 
	* @param bank_code 
	* @description:
	*/
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getBank_account_no() {
		return bank_account_no;
	}
	/** 
	* 
	* @param bank_account_no 
	* @description:
	*/
	public void setBank_account_no(String bank_account_no) {
		this.bank_account_no = bank_account_no;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getBank_account_name() {
		return bank_account_name;
	}
	/** 
	* 
	* @param bank_account_name 
	* @description:
	*/
	public void setBank_account_name(String bank_account_name) {
		this.bank_account_name = bank_account_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getIs_telmsgservice() {
		return is_telmsgservice;
	}
	/** 
	* 
	* @param is_telmsgservice 
	* @description:
	*/
	public void setIs_telmsgservice(String is_telmsgservice) {
		this.is_telmsgservice = is_telmsgservice;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getStatus() {
		return status;
	}
	/** 
	* 
	* @param status 
	* @description:
	*/
	public void setStatus(String status) {
		this.status = status;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getRemark() {
		return remark;
	}
	/** 
	* 
	* @param remark 
	* @description:
	*/
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Integer getLog_seq_id() {
		return log_seq_id;
	}
	/** 
	* 
	* @param log_seq_id 
	* @description:
	*/
	public void setLog_seq_id(Integer log_seq_id) {
		this.log_seq_id = log_seq_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getLog_bustype() {
		return log_bustype;
	}
	/** 
	* 
	* @param log_bustype 
	* @description:
	*/
	public void setLog_bustype(String log_bustype) {
		this.log_bustype = log_bustype;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Date getLog_busdate() {
		return log_busdate;
	}
	/** 
	* 
	* @param log_busdate 
	* @description:
	*/
	public void setLog_busdate(Date log_busdate) {
		this.log_busdate = log_busdate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Date getLog_date() {
		return log_date;
	}
	/** 
	* 
	* @param log_date 
	* @description:
	*/
	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getLog_remark() {
		return log_remark;
	}
	/** 
	* 
	* @param log_remark 
	* @description:
	*/
	public void setLog_remark(String log_remark) {
		this.log_remark = log_remark;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getCreateuser() {
		return createuser;
	}
	/** 
	* 
	* @param createuser 
	* @description:
	*/
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Date getCreatedate() {
		return createdate;
	}
	/** 
	* 
	* @param createdate 
	* @description:
	*/
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getModifyuser() {
		return modifyuser;
	}
	/** 
	* 
	* @param modifyuser 
	* @description:
	*/
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Date getModifydate() {
		return modifydate;
	}
	/** 
	* 
	* @param modifydate 
	* @description:
	*/
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public PageCount getPageCount() {
		return pageCount;
	}
	/** 
	* 
	* @param pageCount 
	* @description:
	*/
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		return this.pageCount.getStart();
	}
	@Override
	public int getLimit() {
		return this.pageCount.getLimit();
	}
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
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
   
}
