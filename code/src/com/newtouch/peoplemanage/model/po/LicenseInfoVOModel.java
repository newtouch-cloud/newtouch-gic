package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

/**
 * 员工专业技术信息实体类
 * @author Ming Ying
 * @time 2017-11-30
 */
public class LicenseInfoVOModel implements ILicenseInfoVOModel{
	private String seq_id; 
	private String person_no; //人员编号

	private String license_name;//资格证证书名
	private String license_channel;//获取途径
	private Date license_startdate;//资格证获取时间
	
	
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
	@Override
	public String getLicense_name() {
		return license_name;
	}
	@Override
	public void setLicense_name(String license_name) {
		this.license_name = license_name;
	}
	@Override
	public String getLicense_channel() {
		return license_channel;
	}
	@Override
	public void setLicense_channel(String license_channel) {
		this.license_channel = license_channel;
	}
	@Override
	public Date getLicense_startdate() {
		return license_startdate;
	}
	@Override
	public void setLicense_startdate(Date license_startdate) {
		this.license_startdate = license_startdate;
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
