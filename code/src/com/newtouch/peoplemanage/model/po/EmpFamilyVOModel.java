package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

/**
 * 员工家庭成员实体类
 * @author Ming Ying
 * @time 2017-11-30
 */
public class EmpFamilyVOModel implements IEmpFamilyVOModel{
	private String seq_id; 
	private String person_no;
	private String family_name;//家庭成员姓名
	private String family_sex; //家庭成员性别
	private Date family_birthday;//家庭成员生日
	private String family_relation;//与本人关系
	private String family_position;//公司以及职务
	private String family_political;//家庭成员政治面貌
	
	
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
		
		this.person_no= person_no;
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
	public String getFamily_political() {
		return family_political;
	}
	public void setFamily_political(String family_political) {
		this.family_political = family_political;
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
