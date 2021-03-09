package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * @description: 客户基本信息
 *  对应的数据库表CRM_Customer
 * @author xxz
 * @since 2013/12/05
 */
public class CustomerModel implements ICustomerModel {
	private Integer seq_id; // 序号
	private String customer_id;// 客户号
	private String name;// 姓名
	private String title;// 称呼
	private String gender;// 性别
	private String birthday;// 出生日期
	private String certi_type;// 证件类型Ref：SYS_Library_Certi_Type
	private String certi_no;// 证件号码
	private String education;// 学历
	private String nationality;// 国籍
	private String nation;// 民族 Ref：SYS_Library_Nation
	private String homeplace;// 籍贯
	private String seat_address;// 户口所在地
	private String height;// 身高
	private String weight;// 体重
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
	private Date certi_validDate; //证件有效期限
	private String is_telMsgService;//是否接受手机短信服务
	private String customer_type;//增加客户类型
	
	private PageCount pageCount = new PageCount();

	
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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

	public String getIncome_type() {
		return income_type;
	}

	public void setIncome_type(String income) {
		this.income_type = income;
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

	@Override
	public PageCount getPageCount() {
		return this.pageCount;
	}

	@Override
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
}
