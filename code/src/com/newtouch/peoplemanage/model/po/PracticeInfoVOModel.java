package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

/**
 * 员工展业信息实体类
 * @author Ming Ying
 * @time 2017-11-30
 */
public class PracticeInfoVOModel implements IPracticeInfoVOModel{
	private String seq_id; 
	private String person_no; //人员编码
	private String microshop_id;//微店账号
	private String shopkeeper_name; //微店主姓名
	private String channel_type;//增员方式
	private String channel_code;//渠道码
	
	
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
	public String getChannel_type() {
		return channel_type;
	}
	@Override
	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}public PracticeInfoVOModel() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getMicroshop_id() {
		return microshop_id;
	}
	@Override
	public void setMicroshop_id(String microshop_id) {
		this.microshop_id = microshop_id;
	}
	@Override
	public String getShopkeeper_name() {
		return shopkeeper_name;
	}
	@Override
	public void setShopkeeper_name(String shopkeeper_name) {
		this.shopkeeper_name = shopkeeper_name;
	}
	@Override
	public String getChannel_code() {
		return channel_code;
	}
	@Override
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
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
