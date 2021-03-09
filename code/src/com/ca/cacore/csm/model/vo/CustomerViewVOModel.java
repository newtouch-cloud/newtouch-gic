package com.ca.cacore.csm.model.vo;

import java.sql.Date;
import java.util.List;

import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerJieChuModel;
import com.ca.cacore.csm.model.bo.IInfclaimsModel;

/**
 * 
* @since:    2013年12月24日   
* @author    newtouchlxy  修改张晨 2014-1-6 添加行为信息
* @description:客户修改页面Vo +　客户行为明细
* 需要的表：CRM_CUSTOMER,CRM_CUSTOMER_CONTACT,CRM_Customer_Action;
 */
public class CustomerViewVOModel {
	private Integer seq_id;
	private Integer customer_seq_id;
	private Integer customercontact_seq_id;
	 private String branch_id;//	机构代码;
	 private String branch_name;//机构名称
	 private String address;//	家庭地址;
	 private String zip;//	邮政编码;
	 private String mobile;//	移动电话;
	 private String fax;//	传真电话;
	 private String telphone;//	住宅电话;
	 private String job_tel ;//	办公电话;
	 private String email;//	电子邮箱;
	 private String job_com;//	工作单位名称;
	 private Date modifydate;//	最后修改时间;
	 private String customer_id;//客户代码
	 private String customer_type;//客户类型
	 private String name;//姓名
	 private String member_id;//会员编号
	 private String title;//称呼
	 private String gender;//性别
	 private Date   birthday;//出生日期
	 private String certi_type;//证件类型
	 private String certi_no;//证件号码
	 private Date certi_validdate; //证件有效期
	 private String education;//学历
	 private String nationality;//国籍
	 private String nation;//民族
	 private String homeplace;//籍贯
	 private String seat_address;//户口所在地
	 private String height;//身高
	 private String weight;//体重
	 private String political;//政治面貌
	 private String education2;//教育程度
	 private String marital_stat;//婚姻状况
	 private String health;//健康状况
	 private String job_type;//职业类别
	 private String job_code;//职业
	 private String income;//收入
	 private String income_type; //收入区间
	 private String bank_code; //银行编码 
	 private String bank_account_no;//银行账号 
	 private String bank_account_name;//银行账户名 
	 private String remark;//备注
	 private String is_telmsgservice; //是否开通短信服务
	 private String file_id;//附件id
	 private String file_name;//附件名称
     private String file_flag;//附件标识
     private Date upload_time;//附件上传时间
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
	 public Date getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}
	private List<IInfclaimsModel> listinf;
	 
	 private List<ICustomerJieChuModel> listJieChu;
	 
	 private List<ILaspolicyModel> listLas;
	 public List<ILaspolicyModel> getListLas() {
		return listLas;
	}
	public void setListLas(List<ILaspolicyModel> listLas) {
		this.listLas = listLas;
	}
	public List<IInfclaimsModel> getListinf() {
		return listinf;
	}
	public void setListinf(List<IInfclaimsModel> listinf) {
		this.listinf = listinf;
	}
	public List<ICustomerJieChuModel> getListJieChu() {
		return listJieChu;
	}
	public void setListJieChu(List<ICustomerJieChuModel> listJieChu) {
		this.listJieChu = listJieChu;
	}
	public List<ICustomerContactModel> getListCont() {
		return listCont;
	}
	public void setListCont(List<ICustomerContactModel> listCont) {
		this.listCont = listCont;
	}
	private List<ICustomerContactModel> listCont;
	 private String action_type;//行为类型
	 private Date action_time;//行为时间
	 private String action_notes;//行为描述
	 
	public Integer getCustomer_seq_id() {
		return customer_seq_id;
	}
	public void setCustomer_seq_id(Integer customer_seq_id) {
		this.customer_seq_id = customer_seq_id;
	}
	public Integer getCustomercontact_seq_id() {
		return customercontact_seq_id;
	}
	public void setCustomercontact_seq_id(Integer customercontact_seq_id) {
		this.customercontact_seq_id = customercontact_seq_id;
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
	public String getJob_tel() {
		return job_tel;
	}
	public void setJob_tel(String job_tel) {
		this.job_tel = job_tel;
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
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
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
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
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
	
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCerti_validdate() {
		return certi_validdate;
	}
	public void setCerti_validdate(Date certi_validdate) {
		this.certi_validdate = certi_validdate;
	}
	public String getIs_telmsgservice() {
		return is_telmsgservice;
	}
	public void setIs_telmsgservice(String is_telmsgservice) {
		this.is_telmsgservice = is_telmsgservice;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public Date getAction_time() {
		return action_time;
	}
	public void setAction_time(Date action_time) {
		this.action_time = action_time;
	}
	public String getAction_notes() {
		return action_notes;
	}
	public void setAction_notes(String action_notes) {
		this.action_notes = action_notes;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_flag() {
		return file_flag;
	}
	public void setFile_flag(String file_flag) {
		this.file_flag = file_flag;
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
