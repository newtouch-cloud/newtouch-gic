package com.newtouch.protocol.model.vo;

import java.util.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class ProtocolManageModel implements IProtocolManageModel{
	private Integer seq_id;
	private String ins_branch;// 保险公司 200 非空
	private String ins_branchdept;// 签约机构 200 非空
	private String contacts_name;// 联系人 18
	private String contacts_way;//联系方式 18
	private String agreement_no;// 协议号 18 非空
    private String agreement_no_success;//协议号拼接
	private String branch_id;// 使用机构ID 18 非空 ins_branch
	private String branch_name;// 使用机构NAME 200
	private String startdate;// 生效日期   从 Date 非空
	private String enddate;// 终止日期  从 Date 非空
	private String dept_list;//权限
	private String dateofsign;//协议签订日期 Date 非空
	private String persion_id;// 签订人代码 18 非空 
	private String persion_name;//	签订人姓名  200
	private String sales_id;//人员代码 15
	private String sales_name;//人员姓名 50
	private String status;//状态 关联-状态数据字典表
	private String status_code;//状态编号 10 
	private String status_name;//状态名称 32
	private Date systemdate;// 系统时间
	private Date create_date;//数据创建时间
	private Date mdf_date;//数据修改时间
	private String create_user;//创建人
	private String mdf_user;//修改人
	private String agreement_type;//协议类型
	private String code;
	private String name;
	private String insBranch_id;
	private String insBranch_name;
	private String emp_id;
	private String file_name;
	private String bus_id;
	private String file_id;
	private String agreement_agency_type;
	private PageCount pageCount = new PageCount();
	private String protocol_type;  //导入协议类型 1.其他协议 2.保险公司协议 3.代理协议'
	private String sup_protocol_no; //补充协议编号
	private String sup_protocol_content;//补充协议内容
	private Date sup_protocol_stadate;//补充协议生效日期
	private String protocol_category;//协议类别 01保险公司合同02业务合作协议03项目合作协议04固定资产合同05租房合同06租车合同07广告宣传合同08会务合同09培训合同99其他
	private String contract_type;//合同类型：1保险代理合同2协赔合同3服务合同4其他
	private String sign_type;//签订类型 1.新签 0.续签
	private String isOverdue;//是否一个月内过期
	private String party_a;//甲方
	private String party_b;//乙方
	private String party_c;//丙方
	private String enterprise_type;//乙方企业类型
	private String credit_code;//乙方社会统一信用代码
	private String isconsult;//汽车信息咨询
	private String bank_name;//乙方开户行
	private String bank_code;//乙方银行账号
	private String party_b_name;//乙方联系人
	private String party_b_phone;//乙方电话
	private double amount;//合同金额
	private String push_code;//维修推送码
	private String ins_branchname;// 保险公司 200 非空
	private String remarks;//备注
	private String picture_path;//图片路径
	private String picture_pathName;//文件名称
	private String dateofsign1;//用于条件查询
	private String dateofsign2;
	private String enddate1;//用于条件查询
	private String enddate2;
	private String reason;//解除协议原因
	
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getParty_c() {
		return party_c;
	}

	public void setParty_c(String party_c) {
		this.party_c = party_c;
	}

	public String getIns_branchname() {
		return ins_branchname;
	}

	public void setIns_branchname(String ins_branchname) {
		this.ins_branchname = ins_branchname;
	}

	public String getDateofsign1() {
		return dateofsign1;
	}

	public void setDateofsign1(String dateofsign1) {
		this.dateofsign1 = dateofsign1;
	}

	public String getDateofsign2() {
		return dateofsign2;
	}

	public void setDateofsign2(String dateofsign2) {
		this.dateofsign2 = dateofsign2;
	}

	public String getEnddate1() {
		return enddate1;
	}

	public void setEnddate1(String enddate1) {
		this.enddate1 = enddate1;
	}

	public String getEnddate2() {
		return enddate2;
	}

	public void setEnddate2(String enddate2) {
		this.enddate2 = enddate2;
	}

	public String getPicture_pathName() {
		return picture_pathName;
	}

	public void setPicture_pathName(String picture_pathName) {
		this.picture_pathName = picture_pathName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPicture_path() {
		return picture_path;
	}

	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}

	public String getInsBranch_id() {
		return insBranch_id;
	}

	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}

	public String getInsBranch_name() {
		return insBranch_name;
	}

	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}

	public String getPush_code() {
		return push_code;
	}

	public void setPush_code(String push_code) {
		this.push_code = push_code;
	}

	public String getParty_a() {
		return party_a;
	}

	public void setParty_a(String party_a) {
		this.party_a = party_a;
	}

	public String getParty_b() {
		return party_b;
	}

	public void setParty_b(String party_b) {
		this.party_b = party_b;
	}

	public String getEnterprise_type() {
		return enterprise_type;
	}

	public void setEnterprise_type(String enterprise_type) {
		this.enterprise_type = enterprise_type;
	}

	public String getCredit_code() {
		return credit_code;
	}

	public void setCredit_code(String credit_code) {
		this.credit_code = credit_code;
	}

	public String getIsconsult() {
		return isconsult;
	}

	public void setIsconsult(String isconsult) {
		this.isconsult = isconsult;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getParty_b_name() {
		return party_b_name;
	}

	public void setParty_b_name(String party_b_name) {
		this.party_b_name = party_b_name;
	}

	public String getParty_b_phone() {
		return party_b_phone;
	}

	public void setParty_b_phone(String party_b_phone) {
		this.party_b_phone = party_b_phone;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getProtocol_type() {
		return protocol_type;
	}

	public void setProtocol_type(String protocol_type) {
		this.protocol_type = protocol_type;
	}

	public String getSup_protocol_no() {
		return sup_protocol_no;
	}

	public void setSup_protocol_no(String sup_protocol_no) {
		this.sup_protocol_no = sup_protocol_no;
	}

	public String getSup_protocol_content() {
		return sup_protocol_content;
	}

	public void setSup_protocol_content(String sup_protocol_content) {
		this.sup_protocol_content = sup_protocol_content;
	}

	public Date getSup_protocol_stadate() {
		return sup_protocol_stadate;
	}

	public void setSup_protocol_stadate(Date sup_protocol_stadate) {
		this.sup_protocol_stadate = sup_protocol_stadate;
	}

	public String getProtocol_category() {
		return protocol_category;
	}

	public void setProtocol_category(String protocol_category) {
		this.protocol_category = protocol_category;
	}

	public String getContract_type() {
		return contract_type;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

	public String getAgreement_agency_type() {
		return agreement_agency_type;
	}

	public void setAgreement_agency_type(String agreement_agency_type) {
		this.agreement_agency_type = agreement_agency_type;
	}

	@Override
	public String getIns_branch() {
		// TODO Auto-generated method stub
		return ins_branch;
	}

	@Override
	public void setIns_branch(String ins_branch) {
		// TODO Auto-generated method stub
		this.ins_branch = ins_branch;
		
	}

	@Override
	public String getIns_branchdept() {
		// TODO Auto-generated method stub
		return ins_branchdept;
	}

	@Override
	public void setIns_branchdept(String ins_branchdept) {
		// TODO Auto-generated method stub
		this.ins_branchdept = ins_branchdept;
	}

	@Override
	public String getContacts_name() {
		// TODO Auto-generated method stub
		return contacts_name;
	}

	@Override
	public void setContacts_name(String contacts_name) {
		// TODO Auto-generated method stub
		this.contacts_name = contacts_name;
	}

	@Override
	public String getContacts_way() {
		// TODO Auto-generated method stub
		return contacts_way;
	}

	@Override
	public void setContacts_way(String contacts_way) {
		// TODO Auto-generated method stub
		this.contacts_way = contacts_way;
	}

	@Override
	public String getAgreement_no() {
		// TODO Auto-generated method stub
		return agreement_no;
	}

	@Override
	public void setAgreement_no(String agreement_no) {
		// TODO Auto-generated method stub
		this.agreement_no = agreement_no;
	}

	@Override
	public String getBranch_id() {
		// TODO Auto-generated method stub
		return branch_id;
	}

	@Override
	public void setBranch_id(String branch_id) {
		// TODO Auto-generated method stub
		this.branch_id = branch_id;
	}

	@Override
	public String getBranch_name() {
		// TODO Auto-generated method stub
		return branch_name;
	}

	@Override
	public void setBranch_name(String branch_name) {
		// TODO Auto-generated method stub
		this.branch_name = branch_name;
	}

	@Override
	public String getStartdate() {
		// TODO Auto-generated method stub
		return startdate;
	}

	@Override
	public void setStartdate(String startdate) {
		// TODO Auto-generated method stub
		this.startdate = startdate;
	}

	@Override
	public String getEnddate() {
		// TODO Auto-generated method stub
		return enddate;
	}

	@Override
	public void setEnddate(String enddate) {
		// TODO Auto-generated method stub
		this.enddate = enddate;
	}


	@Override
	public String getDateofsign() {
		// TODO Auto-generated method stub
		return dateofsign;
	}

	@Override
	public void setDateofsign(String dateofsign) {
		// TODO Auto-generated method stub
		this.dateofsign = dateofsign;
	}

	@Override
	public String getPersion_id() {
		// TODO Auto-generated method stub
		return persion_id;
	}

	@Override
	public void setPersion_id(String persion_id) {
		// TODO Auto-generated method stub
		this.persion_id = persion_id;
	}

	@Override
	public String getPersion_name() {
		// TODO Auto-generated method stub
		return persion_name;
	}

	@Override
	public void setPersion_name(String persion_name) {
		// TODO Auto-generated method stub
		this.persion_name = persion_name;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status = status;
	}

	@Override
	public Date getSystemdate() {
		// TODO Auto-generated method stub
		return systemdate;
	}

	@Override
	public void setSystemdate(Date systemdate) {
		// TODO Auto-generated method stub
		this.systemdate = systemdate;
	}

	@Override
	public Date getCreat_date() {
		// TODO Auto-generated method stub
		return create_date;
	}

	@Override
	public void setCreate_date(Date create_date) {
		// TODO Auto-generated method stub
		this.create_date = create_date;
		
	}

	@Override
	public Date getMdf_date() {
		// TODO Auto-generated method stub
		return mdf_date;
	}

	@Override
	public void setMdf_date(Date mdf_date) {
		// TODO Auto-generated method stub
		this.mdf_date = mdf_date;
	}

	@Override
	public String getCreate_user() {
		// TODO Auto-generated method stub
		return create_user;
	}

	@Override
	public void setCreate_user(String create_user) {
		// TODO Auto-generated method stub
		this.create_user= create_user;
	}

	@Override
	public String getMdf_user() {
		// TODO Auto-generated method stub
		return mdf_user;
	}

	@Override
	public void setMdf_user(String create_user) {
		// TODO Auto-generated method stub
		this.mdf_user = mdf_user;
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
		// TODO Auto-generated method stub
		return this.getPageCount().getStart();
	}

	@Override
	public int getLimit() {
		// TODO Auto-generated method stub
		return this.getPageCount().getLimit();
	}

	@Override
	public String getEmp_id() {
		// TODO Auto-generated method stub
		return emp_id;
	}

	@Override
	public void setEmp_id(String emp_id) {
		// TODO Auto-generated method stub
		this.emp_id = emp_id;
	}

	@Override
	public String getFile_name() {
		// TODO Auto-generated method stub
		return file_name;
	}

	@Override
	public void setFile_name(String file_name) {
		// TODO Auto-generated method stub
		this.file_name = file_name;
	}

	@Override
	public String getBus_id() {
		// TODO Auto-generated method stub
		return bus_id;
	}

	@Override
	public void setBus_id(String bus_id) {
		// TODO Auto-generated method stub
		this.bus_id = bus_id;
	}

	@Override
	public String getFile_id() {
		// TODO Auto-generated method stub
		return file_id;
	}

	@Override
	public void setFile_id(String file_id) {
		// TODO Auto-generated method stub
		this.file_id = file_id;
	}

	@Override
	public String getStatus_code() {
		// TODO Auto-generated method stub
		return status_code;
	}

	@Override
	public void setStatus_code(String status_code) {
		// TODO Auto-generated method stub
		this.status_code = status_code;
	}

	@Override
	public String getStatus_name() {
		// TODO Auto-generated method stub
		return status_name;
	}

	@Override
	public void setStatus_name(String status_name) {
		// TODO Auto-generated method stub
		this.status_name = status_name;
	}

	@Override
	public String getdept_list() {
		return dept_list;
	}

	@Override
	public void setdept_list(String dept_list) {
		this.dept_list = dept_list;
		
	}

	@Override
	public String getAgreement_type() {
		// TODO Auto-generated method stub
		return agreement_type;
	}

	@Override
	public void setAgreement_type(String agreement_type) {
		// TODO Auto-generated method stub
		this.agreement_type = agreement_type;
		
	}

	@Override
	public Integer getSeq_id() {
		// TODO Auto-generated method stub
		return seq_id;
	}

	@Override
	public void setSeq_id(Integer seq_id) {
		// TODO Auto-generated method stub
		this.seq_id=seq_id;
		
	}

	

	

//	@Override
//	public String getAgreement_no_success() {
//		// TODO Auto-generated method stub
//		return agreement_no_success;
//	}
//
//	@Override
//	public void setAgreement_no_success(String agreement_no_success) {
//		// TODO Auto-generated method stub
//		this.agreement_no_success=agreement_no_success;
//	}

}
