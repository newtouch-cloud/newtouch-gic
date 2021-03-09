package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

/**
 * 员工对应的代理人信息实体类
 * @author Ming Ying
 *
 */
public class AgentInfoVOModel implements IAgentInfoVOModel{
	private String seq_id; 
	private String person_no; //人员姓名
	private String branch_id;//部门机构
	private String qualification_no; //资格证书号码
	private String practice_license_type;//执业证类型
	private String practice_license_status;//执业证状态
	private String practice_license_no;//执业证编号
	private Date practice_startdate;//执业证有效期开始时间
	private Date practice_enddate;//执业证失效时间
	private String practice_area;//从业区域
	private String channel_type;//渠道类型
	private String ins_sales_no;//保险营销员编号
	private String business_scope;//业务范围 
	private String contract_type; //合同类型
	
	
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
	public String getBranch_id() {
		return branch_id;
	}
	@Override
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	@Override
	public String getQualification_no() {
		return qualification_no;
	}
	@Override
	public void setQualification_no(String qualification_no) {
		this.qualification_no = qualification_no;
	}
	@Override
	public String getPractice_license_type() {
		return practice_license_type;
	}
	@Override
	public void setPractice_license_type(String practice_license_type) {
		this.practice_license_type = practice_license_type;
	}
	@Override
	public String getPractice_license_status() {
		return practice_license_status;
	}
	@Override
	public void setPractice_license_status(String practice_license_status) {
		this.practice_license_status = practice_license_status;
	}
	@Override
	public String getPractice_license_no() {
		return practice_license_no;
	}
	@Override
	public void setPractice_license_no(String practice_license_no) {
		this.practice_license_no = practice_license_no;
	}
	@Override
	public Date getPractice_startdate() {
		return practice_startdate;
	}
	@Override
	public void setPractice_startdate(Date practice_startdate) {
		this.practice_startdate = practice_startdate;
	}
	@Override
	public Date getPractice_enddate() {
		return practice_enddate;
	}
	@Override
	public void setPractice_enddate(Date practice_enddate) {
		this.practice_enddate = practice_enddate;
	}
	@Override
	public String getPractice_area() {
		return practice_area;
	}
	@Override
	public void setPractice_area(String practice_area) {
		this.practice_area = practice_area;
	}
	@Override
	public String getChannel_type() {
		return channel_type;
	}
	@Override
	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}public AgentInfoVOModel() {
		// TODO Auto-generated constructor stub
	}@Override
	public String getIns_sales_no() {
		return ins_sales_no;
	}
	@Override
	public void setIns_sales_no(String ins_sales_no) {
		this.ins_sales_no = ins_sales_no;
	}
	@Override
	public String getBusiness_scope() {
		return business_scope;
	}
	@Override
	public void setBusiness_scope(String business_scope) {
		this.business_scope = business_scope;
	}
	@Override
	public String getContract_type() {
		return contract_type;
	}
	@Override
	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
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
