package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

/**
 * 员工教育/从业信息实体类
 * @author Ming Ying
 * @time 2017-11-30
 */
public class EducationVOModel implements IEducationVOModel{
	private String seq_id; 
	private String person_no; //人员姓名
	private Date start_date;//入学年份/工作单位起始日期
	private Date graduation_date; //毕业年份/离职日期
	private String address;//学校/工作单位
	private String major;//专业/工作部门
	private String year;//学年
	private String degree;//学历
	private String approve_person;//证明人
	private String work_position;//职务（工作经历）
	private String ishigh_degree; //是否最高学历
	private String major_type;//专业类别
	private String education_type;//教育类型
	private String degree_type;//学位
	private String type;//教育经历的数据为E /工作经历的数据为w
	
	
	private Date  create_date;
	private String  creat_user;
	private String  may_date;
	private Date  may_user;
	
	@Override
	public String getSeq_id() {
		return seq_id;
	}
	@Override
	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}
	@Override
	public String getPerson_no() {
		return person_no;
	}
	@Override
	public void setPerson_no(String person_no) {
		this.person_no = person_no;
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
	@Override
	public Date getCreate_date() {
		return create_date;
	}
	@Override
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String getCreat_user() {
		return creat_user;
	}
	@Override
	public void setCreat_user(String creat_user) {
		this.creat_user = creat_user;
	}
	@Override
	public String getMay_date() {
		return may_date;
	}
	@Override
	public void setMay_date(String may_date) {
		this.may_date = may_date;
	}
	@Override
	public Date getMay_user() {
		return may_user;
	}
	@Override
	public void setMay_user(Date may_user) {
		this.may_user = may_user;
	}
	
	
	
}
