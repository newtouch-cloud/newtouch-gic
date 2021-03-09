package com.newtouch.peoplemanage.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 人员管理实体类
 * 
 * @author Ming Ying
 * @time 2017-11-30
 */
public class PersonManageVOModel implements IPersonManageVOModel {
	// 人员信息表 sys_employee 主表
	private String seq_id;
	private String person_no;// 人员编码*
	private String person_name;// 人员姓名*

	private String branch_id; // 所属部门*
	private String team_id;// 所属团队*
	private String branch_name; // 所属部门*
	private String team_name;// 所属团队*
	private Date entry_date; // 入职时间*
	private String ontrial_date;// 试用期ONTRIAL_DATE
	private Date formal_date;// 转正日期FORMAL_DATE
	private Date end_date; // 离职时间*
	private String person_status; // 人员状态*
	private String person_type;// 人员类型
	private String work_nature; // 用工类型
	private String work_nature_name; // 用工类型

	@Override
	public String getWork_nature_name() {
		return work_nature_name;
	}

	@Override
	public void setWork_nature_name(String work_nature_name) {
		this.work_nature_name = work_nature_name;
	}

	private String position; // 岗位名称
	private String position_id; // 岗位编码
	private String entry_source; // 入职来源
	private String ischarge; // 是否为负责人职位
	private String superior_position_id; // 上级岗位编码
	private String superior_position_name; // 上级岗位编码
	private String profession_type; // 岗位类型

	private String org_id; // 财险归属机构*
	private String org_name; // 财险机构名称
	private String belong_opt_no; // 归属人工号*
	private String belong_opt_name;// 归属人姓名*
	private String belong_idcard;// 归属人身份证号*

	private String enum_name;
	private String contractno;//合同编码
	private String school;

	// 人员基本信息 sys_enployee_info 表
	private String sex; // 性别*
	private Date birthday;// 生日*
	private String national; // 民族*
	private String political;// 政治面貌
	private String education;// 学历*
	private String idcard_adress;// 户籍所在地*
	private String birth_address; // 出生地
	private String card_type; // 户口类型
	private String idcard; // 身份证
	private String phone; // 手机
	private String tel; // 电话
	private String email;// 邮箱
	private String technology_no; // 专业技术证书*
	private String ismarrid; // 婚姻状况
	private String home_address; // 家庭地址
	private Date political_startdate; // 入党时时间
	private Date political_joindate;// 转正时间
	private String political_company;// 参加党派时所在单位
	private String party_org; // 现所在党组织
	private String political_fee;// 党费
	private String health;// 健康状况
	private String social_security_no;// 社会保障号
	private Date work_date;// 参加工作时间
	private String audit_status;// 审核状态
	private String is_party_org; // 党籍是否转入公司
	// 员工对应的代理人信息 sys_agent_info
	private String qualification_no; // 资格证书号码
	private String practice_type;// 执业证类型
	private String practice_status;// 执业证状态
	private String practice_no;// 执业证编号
	private Date issue_date;// 发证日期
	private Date practice_startdate;// 执业证有效期开始时间
	private Date practice_enddate;// 执业证失效时间
	private String practice_area;// 从业区域
	private String channel_type;// 渠道类型
	private String ins_sales_no;// 保险营销员编号
	private String business_scope;// 业务范围
	private String contract_type; // 合同类型
	private String islogout;// 是否注销
	private String exam_score;// 在线考试成绩

	// 员工教育/从业信息 sys_education
	private Date start_date;// 入学年份/工作单位起始日期
	private Date graduation_date; // 毕业年份/离职日期
	private String address;// 学校/工作单位
	private String major;// 专业/工作部门
	private String year;// 学年
	private String degree;// 学历
	private String approve_person;// 证明人
	private String work_position;// 职务（工作经历）
	private String ishigh_degree; // 是否最高学历
	private String major_type;// 专业类别
	private String education_type;// 教育类型
	private String degree_type;// 学位
	private String type;// 教育经历的数据为E /工作经历的数据为w

	// 员工家庭成员 sys_emp_family

	private String family_name;// 家庭成员姓名
	private String family_sex; // 家庭成员性别
	private Date family_birthday;// 家庭成员生日
	private String family_relation;// 与本人关系
	private String family_position;// 公司以及职务
	private String family_political;// 家庭成员政治面貌

	// 员工离职信息 sys_emp_leave
	// private Date endtime;//离职时间
	private String reason;// 离职原因
	private Date apply_date; // 申报时间
	private Date check_date;// 审核时间
	private String status;// 办理状态
	// private String practice_no;//展业编号

	// 员工专业技术信息 sys_license_info
	private String license_name;// 资格证证书名
	private String license_channel;// 获取途径
	private Date license_startdate;// 资格证获取时间

	// 员工展业信息 sys_practice_info
	private String microshop_id;// 微店账号
	private String shopkeeper_name; // 微店主姓名
	private String increase_type;// 增员方式
	private String channel_code;// 渠道码
	private String microshop_view;// 微店访问量
	private String open_stye;// 开启方式
	private Date register_date;// 领店日期/注册日期
	private String shopkeeper_idcard; // 店主身份证号

	// 有关事项说明 sys_emp_note 有关事项说明表
	private String content_code;// 有关事项说明
	private String flag;// 是否有相关事项说明

	private Date create_date;
	private String creat_user;
	private String may_date;
	private Date may_user;
	private String work_relation;
	private String branch_level; //a by liu_yn
	private String branch_level_code; //a by liu_yn
	//高管信息  a by liu_yn start
	private String gleader; 
	private String approval_time; //高管批复时间
	private String approval_file; //高管批复文号
	private String employment_term; //高管聘期
	private String upload_approval_file; //上传批复文件
	private Date retire_time;//退休时间
	private String person_class;//人员分类
	private String bankcard_number;//银行账号
	private String bank_name;//开户行
	private String gvalid_time;//有效期截至
	private String is_practice;//是否执业
	private String age;//年龄
	private String branch_level_name;//机构层级名称
	private String mark;//是否有高管权限
	
	private Date entry_date1;//入职时间从
	private Date entry_date2;//至
	private Date end_date1;//离职时间从
	private Date end_date2;//至
	
	
	//a by liu_yn end
	
	public String getBranch_level_name() {
		return branch_level_name;
	}
	public void setBranch_level_name(String branch_level_name) {
		this.branch_level_name = branch_level_name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIs_practice() {
		return is_practice;
	}
	public void setIs_practice(String is_practice) {
		this.is_practice = is_practice;
	}
	
	public String getGvalid_time() {
		return gvalid_time;
	}

	public void setGvalid_time(String gvalid_time) {
		this.gvalid_time = gvalid_time;
	}
	
	public String getBankcard_number() {
		return bankcard_number;
	}

	public void setBankcard_number(String bankcard_number) {
		this.bankcard_number = bankcard_number;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getPerson_class() {
		return person_class;
	}
	public void setPerson_class(String person_class) {
		this.person_class = person_class;
	}
	
	public Date getRetire_time() {
		return retire_time;
	}

	public void setRetire_time(Date retire_time) {
		this.retire_time = retire_time;
	}
	
	public String getApproval_time() {
		return approval_time;
	}

	public void setApproval_time(String approval_time) {
		this.approval_time = approval_time;
	}

	public String getApproval_file() {
		return approval_file;
	}

	public void setApproval_file(String approval_file) {
		this.approval_file = approval_file;
	}

	public String getEmployment_term() {
		return employment_term;
	}

	public void setEmployment_term(String employment_term) {
		this.employment_term = employment_term;
	}

	public String getUpload_approval_file() {
		return upload_approval_file;
	}

	public void setUpload_approval_file(String upload_approval_file) {
		this.upload_approval_file = upload_approval_file;
	}

	public String getBranch_level() {
		return branch_level;
	}
	
	public String getBranch_level_code() {
		return branch_level_code;
	}
	
	public void setBranch_level_code(String branch_level_code) {
		this.branch_level_code = branch_level_code;
	}

	public void setBranch_level(String branch_level) {
		this.branch_level = branch_level;
	}

	public String getGleader() {
		return gleader;
	}

	public void setGleader(String gleader) {
		this.gleader = gleader;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public String getWork_relation() {
		return work_relation;
	}

	public void setWork_relation(String work_relation) {
		this.work_relation = work_relation;
	}

	private PageCount pageCount = new PageCount();

	private String dept_list;

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public String getExam_score() {
		return exam_score;
	}

	public void setExam_score(String exam_score) {
		this.exam_score = exam_score;
	}

	public String getMicroshop_view() {
		return microshop_view;
	}

	public void setMicroshop_view(String microshop_view) {
		this.microshop_view = microshop_view;
	}

	public String getOpen_stye() {
		return open_stye;
	}

	public void setOpen_stye(String open_stye) {
		this.open_stye = open_stye;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	public String getShopkeeper_idcard() {
		return shopkeeper_idcard;
	}

	public void setShopkeeper_idcard(String shopkeeper_idcard) {
		this.shopkeeper_idcard = shopkeeper_idcard;
	}

	public String getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}

	public String getPerson_no() {
		return person_no;
	}

	public void setPerson_no(String person_no) {
		this.person_no = person_no;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getPerson_status() {
		return person_status;
	}

	public void setPerson_status(String person_status) {
		this.person_status = person_status;
	}

	public String getPerson_type() {
		return person_type;
	}

	public void setPerson_type(String person_type) {
		this.person_type = person_type;
	}

	public String getWork_nature() {
		return work_nature;
	}

	public void setWork_nature(String work_nature) {
		this.work_nature = work_nature;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition_id() {
		return position_id;
	}

	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}

	public String getEntry_source() {
		return entry_source;
	}

	public void setEntry_source(String entry_source) {
		this.entry_source = entry_source;
	}

	public String getIscharge() {
		return ischarge;
	}

	public void setIscharge(String ischarge) {
		this.ischarge = ischarge;
	}

	public String getSuperior_position_id() {
		return superior_position_id;
	}

	public void setSuperior_position_id(String superior_position_id) {
		this.superior_position_id = superior_position_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getBelong_opt_no() {
		return belong_opt_no;
	}

	public void setBelong_opt_no(String belong_opt_no) {
		this.belong_opt_no = belong_opt_no;
	}

	public String getBelong_opt_name() {
		return belong_opt_name;
	}

	public void setBelong_opt_name(String belong_opt_name) {
		this.belong_opt_name = belong_opt_name;
	}

	public String getBelong_idcard() {
		return belong_idcard;
	}

	public void setBelong_idcard(String belong_idcard) {
		this.belong_idcard = belong_idcard;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getIdcard_adress() {
		return idcard_adress;
	}

	public void setIdcard_adress(String idcard_adress) {
		this.idcard_adress = idcard_adress;
	}

	public String getBirth_address() {
		return birth_address;
	}

	public void setBirth_address(String birth_address) {
		this.birth_address = birth_address;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTechnology_no() {
		return technology_no;
	}

	public void setTechnology_no(String technology_no) {
		this.technology_no = technology_no;
	}

	public String getIsmarrid() {
		return ismarrid;
	}

	public void setIsmarrid(String ismarrid) {
		this.ismarrid = ismarrid;
	}

	public String getHome_address() {
		return home_address;
	}

	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}

	public Date getPolitical_startdate() {
		return political_startdate;
	}

	public void setPolitical_startdate(Date political_startdate) {
		this.political_startdate = political_startdate;
	}

	public Date getPolitical_joindate() {
		return political_joindate;
	}

	public void setPolitical_joindate(Date political_joindate) {
		this.political_joindate = political_joindate;
	}

	public String getPolitical_company() {
		return political_company;
	}

	public void setPolitical_company(String political_company) {
		this.political_company = political_company;
	}

	public String getParty_org() {
		return party_org;
	}

	public void setParty_org(String party_org) {
		this.party_org = party_org;
	}

	public String getPolitical_fee() {
		return political_fee;
	}

	public void setPolitical_fee(String political_fee) {
		this.political_fee = political_fee;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getSocial_security_no() {
		return social_security_no;
	}

	public void setSocial_security_no(String social_security_no) {
		this.social_security_no = social_security_no;
	}

	public Date getWork_date() {
		return work_date;
	}

	public void setWork_date(Date work_date) {
		this.work_date = work_date;
	}

	@Override
	public String getAudit_status() {

		return audit_status;
	}

	@Override
	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}

	public String getIs_party_org() {
		return is_party_org;
	}

	public void setIs_party_org(String is_party_org) {
		this.is_party_org = is_party_org;
	}

	public String getQualification_no() {
		return qualification_no;
	}

	public void setQualification_no(String qualification_no) {
		this.qualification_no = qualification_no;
	}

	public String getPractice_type() {
		return practice_type;
	}

	public void setPractice_type(String practice_type) {
		this.practice_type = practice_type;
	}

	public String getPractice_status() {
		return practice_status;
	}

	public void setPractice_status(String practice_status) {
		this.practice_status = practice_status;
	}

	public String getPractice_no() {
		return practice_no;
	}

	public void setPractice_no(String practice_no) {
		this.practice_no = practice_no;
	}

	public Date getPractice_startdate() {
		return practice_startdate;
	}

	public void setPractice_startdate(Date practice_startdate) {
		this.practice_startdate = practice_startdate;
	}

	public Date getPractice_enddate() {
		return practice_enddate;
	}

	public void setPractice_enddate(Date practice_enddate) {
		this.practice_enddate = practice_enddate;
	}

	public String getPractice_area() {
		return practice_area;
	}

	public void setPractice_area(String practice_area) {
		this.practice_area = practice_area;
	}

	public String getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}

	public String getIns_sales_no() {
		return ins_sales_no;
	}

	public void setIns_sales_no(String ins_sales_no) {
		this.ins_sales_no = ins_sales_no;
	}

	public String getBusiness_scope() {
		return business_scope;
	}

	public void setBusiness_scope(String business_scope) {
		this.business_scope = business_scope;
	}

	public String getContract_type() {
		return contract_type;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getGraduation_date() {
		return graduation_date;
	}

	public void setGraduation_date(Date graduation_date) {
		this.graduation_date = graduation_date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getApprove_person() {
		return approve_person;
	}

	public void setApprove_person(String approve_person) {
		this.approve_person = approve_person;
	}

	public String getWork_position() {
		return work_position;
	}

	public void setWork_position(String work_position) {
		this.work_position = work_position;
	}

	public String getIshigh_degree() {
		return ishigh_degree;
	}

	public void setIshigh_degree(String ishigh_degree) {
		this.ishigh_degree = ishigh_degree;
	}

	public String getMajor_type() {
		return major_type;
	}

	public void setMajor_type(String major_type) {
		this.major_type = major_type;
	}

	public String getEducation_type() {
		return education_type;
	}

	public void setEducation_type(String education_type) {
		this.education_type = education_type;
	}

	public String getDegree_type() {
		return degree_type;
	}

	public void setDegree_type(String degree_type) {
		this.degree_type = degree_type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the profession_type
	 */
	public String getProfession_type() {
		return profession_type;
	}

	/**
	 * @param profession_type
	 *            the profession_type to set
	 */
	public void setProfession_type(String profession_type) {
		this.profession_type = profession_type;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getFamily_sex() {
		return family_sex;
	}

	public void setFamily_sex(String family_sex) {
		this.family_sex = family_sex;
	}

	public Date getFamily_birthday() {
		return family_birthday;
	}

	public void setFamily_birthday(Date family_birthday) {
		this.family_birthday = family_birthday;
	}

	public String getFamily_relation() {
		return family_relation;
	}

	public void setFamily_relation(String family_relation) {
		this.family_relation = family_relation;
	}

	public String getFamily_position() {
		return family_position;
	}

	public void setFamily_position(String family_position) {
		this.family_position = family_position;
	}

	// public Date getEndtime() {
	// return endtime;
	// }
	// public void setEndtime(Date endtime) {
	// this.endtime = endtime;
	// }
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getApply_date() {
		return apply_date;
	}

	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}

	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicense_name() {
		return license_name;
	}

	public void setLicense_name(String license_name) {
		this.license_name = license_name;
	}

	public String getLicense_channel() {
		return license_channel;
	}

	public void setLicense_channel(String license_channel) {
		this.license_channel = license_channel;
	}

	public Date getLicense_startdate() {
		return license_startdate;
	}

	public void setLicense_startdate(Date license_startdate) {
		this.license_startdate = license_startdate;
	}

	public String getMicroshop_id() {
		return microshop_id;
	}

	public void setMicroshop_id(String microshop_id) {
		this.microshop_id = microshop_id;
	}

	public String getShopkeeper_name() {
		return shopkeeper_name;
	}

	public void setShopkeeper_name(String shopkeeper_name) {
		this.shopkeeper_name = shopkeeper_name;
	}

	public String getIncrease_type() {
		return increase_type;
	}

	public void setIncrease_type(String increase_type) {
		this.increase_type = increase_type;
	}

	public String getChannel_code() {
		return channel_code;
	}

	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

	public String getContent_code() {
		return content_code;
	}

	public void setContent_code(String content_code) {
		this.content_code = content_code;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreat_user() {
		return creat_user;
	}

	public void setCreat_user(String creat_user) {
		this.creat_user = creat_user;
	}

	public String getMay_date() {
		return may_date;
	}

	public void setMay_date(String may_date) {
		this.may_date = may_date;
	}

	public Date getMay_user() {
		return may_user;
	}

	public void setMay_user(Date may_user) {
		this.may_user = may_user;
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
		return this.getPageCount().getStart();
	}

	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}

	public String getEnum_name() {
		return enum_name;
	}

	public void setEnum_name(String enum_name) {
		this.enum_name = enum_name;
	}

	public String getIslogout() {
		return islogout;
	}

	public void setIslogout(String islogout) {
		this.islogout = islogout;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getFamily_political() {
		return family_political;
	}

	public void setFamily_political(String family_political) {
		this.family_political = family_political;
	}

	public String getDept_list() {
		return dept_list;
	}

	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}

	// public String getPractice_no() {
	// return practice_no;
	// }
	// public void setPractice_no(String practice_no) {
	// this.practice_no = practice_no;
	// }
	public String getOntrial_date() {
		return ontrial_date;
	}

	public void setOntrial_date(String ontrial_date) {
		this.ontrial_date = ontrial_date;
	}

	public Date getFormal_date() {
		return formal_date;
	}

	public void setFormal_date(Date formal_date) {
		this.formal_date = formal_date;
	}

	public String getSuperior_position_name() {
		return superior_position_name;
	}

	public void setSuperior_position_name(String superior_position_name) {
		this.superior_position_name = superior_position_name;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Date getEntry_date1() {
		return entry_date1;
	}
	public void setEntry_date1(Date entry_date1) {
		this.entry_date1 = entry_date1;
	}
	public Date getEntry_date2() {
		return entry_date2;
	}
	public void setEntry_date2(Date entry_date2) {
		this.entry_date2 = entry_date2;
	}
	public Date getEnd_date1() {
		return end_date1;
	}
	public void setEnd_date1(Date end_date1) {
		this.end_date1 = end_date1;
	}
	public Date getEnd_date2() {
		return end_date2;
	}
	public void setEnd_date2(Date end_date2) {
		this.end_date2 = end_date2;
	}
	
	

}
