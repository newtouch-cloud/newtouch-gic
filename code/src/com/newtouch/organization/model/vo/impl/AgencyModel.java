package com.newtouch.organization.model.vo.impl;

import java.sql.Date;
import java.util.List;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.organization.model.vo.IAgencyModel;

public class AgencyModel implements IAgencyModel{

	private Integer seq_id;
	private String agency_no;  //中介机构编码
	private String agency_name;  //中介机构名称
	private Date create_date; //创建时间 -本地时间
	private String create_user;  //创建人 -当前操作
	private String data_flag;  //1有效 0无效
	private String business_license;  //营业执照编号
	private String permission_license;  //许可证编号
	private Date found_date;  //成立时间
	private String agency_address;  //地址
	private String agency_phone;  //中介机构电话
	private String agency_type;  //中介结构代理类型
	private String branch_id;  //中介公司机构代码
	private String branch_name; //中介公司机构名称
	private String org_id;  //财险机构代码
	private String org_name;  //财险机构名字
	private Date mdf_date;  //修改时间
	private String mdf_user;  //修改人
	private String agreement_no;  //协议号
	private Date startdate;  //协议生效起期
	private Date startdate1;
	private Date enddate;  //协议生效止期
	private Date enddate1; 
	private String displayLable;
	private String isSelected;
	private List<String> list;//要新增的list
	private String dept_list; //机构权限集合
	private String setUploadFilename;
	private String getUploadFilename; //获取文件名称
	private String social_code; //社会统一信用代码
	private String channelcode; //渠道码
	private String repair_coding;  //推荐维修码
	
	private List<Object> Olist;  
	
	
	public List<Object> getOlist() {
		return Olist;
	}

	public void setOlist(List<Object> olist) {
		Olist = olist;
	}

	public String getGetUploadFilename() {
		return getUploadFilename;
	}

	public void setGetUploadFilename(String getUploadFilename) {
		this.getUploadFilename = getUploadFilename;
	}

	public String getSetUploadFilename() {
		return setUploadFilename;
	}

	public void setSetUploadFilename(String setUploadFilename) {
		this.setUploadFilename = setUploadFilename;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	private PageCount pageCount = new PageCount();
	
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

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	public String getAgency_no() {
		return agency_no;
	}

	public void setAgency_no(String agency_no) {
		this.agency_no = agency_no;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getData_flag() {
		return data_flag;
	}

	public void setData_flag(String data_flag) {
		this.data_flag = data_flag;
	}

	public String getBusiness_license() {
		return business_license;
	}

	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}

	public String getPermission_license() {
		return permission_license;
	}

	public void setPermission_license(String permission_license) {
		this.permission_license = permission_license;
	}

	public Date getFound_date() {
		return found_date;
	}

	public void setFound_date(Date found_date) {
		this.found_date = found_date;
	}

	public String getAgency_address() {
		return agency_address;
	}

	public void setAgency_address(String agency_address) {
		this.agency_address = agency_address;
	}

	public String getAgency_phone() {
		return agency_phone;
	}

	public void setAgency_phone(String agency_phone) {
		this.agency_phone = agency_phone;
	}

	public String getAgency_type() {
		return agency_type;
	}

	public void setAgency_type(String agency_type) {
		this.agency_type = agency_type;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public Date getMdf_date() {
		return mdf_date;
	}

	public void setMdf_date(Date mdf_date) {
		this.mdf_date = mdf_date;
	}

	public String getMdf_user() {
		return mdf_user;
	}

	public void setMdf_user(String mdf_user) {
		this.mdf_user = mdf_user;
	}

	public String getAgreement_no() {
		return agreement_no;
	}

	public void setAgreement_no(String agreement_no) {
		this.agreement_no = agreement_no;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String brach_name) {
		this.branch_name = brach_name;
	}
	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public Date getStartdate1() {
		return startdate1;
	}

	public void setStartdate1(Date startdate1) {
		this.startdate1 = startdate1;
	}

	public Date getEnddate1() {
		return enddate1;
	}

	public void setEnddate1(Date enddate1) {
		this.enddate1 = enddate1;
	}

	public String getDisplayLable() {
		return displayLable;
	}

	public void setDisplayLable(String displayLable) {
		this.displayLable = displayLable;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

	public String getDept_list() {
		return dept_list;
	}

	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}

	public String getSocial_code() {
		return social_code;
	}

	public void setSocial_code(String social_code) {
		this.social_code = social_code;
	}

	public String getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}

	public String getRepair_coding() {
		return repair_coding;
	}

	public void setRepair_coding(String repair_coding) {
		this.repair_coding = repair_coding;
	}

	
	

}
