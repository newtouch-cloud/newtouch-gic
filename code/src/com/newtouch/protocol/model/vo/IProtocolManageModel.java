package com.newtouch.protocol.model.vo;

import java.util.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * @since: 2017年11月29日
 * @author LJR
 * @description:协议管理-Model
 */
public interface IProtocolManageModel extends IPageCount {
	// 主键
	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	// 签约协议
	public String getIns_branch();

	public void setIns_branch(String ins_branch);
	public String getIns_branchname();

	public void setIns_branchname(String ins_branchname);
	// 签约机构
	public String getIns_branchdept();

	public void setIns_branchdept(String ins_branchdept);

	// 联系人，系统当前用户
	public String getContacts_name();

	public void setContacts_name(String contacts_name);

	// 联系方式
	public String getContacts_way();

	public void setContacts_way(String contacts_way);

	// 协议号
	public String getAgreement_no();

	public void setAgreement_no(String agreement_no);

	// //协议号拼接
	// public String getAgreement_no_success();
	// public void setAgreement_no_success(String agreement_no_success);
	// 使用机构id
	public String getBranch_id();

	public void setBranch_id(String branch_id);

	// 使用机构名称
	public String getBranch_name();

	public void setBranch_name(String branch_name);

	// 协议生效日期
	public String getStartdate();

	public void setStartdate(String startdate);

	// 协议终止日期
	public String getEnddate();

	public void setEnddate(String enddate);

	// 协议签订日期
	public String getDateofsign();

	public void setDateofsign(String dateofsign);

	// 签订人代码
	public String getPersion_id();

	public void setPersion_id(String persion_id);

	public String getPicture_pathName();

	public void setPicture_pathName(String picture_pathName);
	// 签订人姓名
	public String getPersion_name();

	public void setPersion_name(String persion_name);
	
	//用于条件查询
	public String getDateofsign1();
	public void setDateofsign1(String dateofsign1);

	public String getDateofsign2();
	public void setDateofsign2(String dateofsign2);

	public String getEnddate1();
	public void setEnddate1(String enddate1) ;

	public String getEnddate2();
	public void setEnddate2(String enddate2);

	// 状态 1有效，0无效
	public String getStatus();

	public void setStatus(String status);
	
	public String getRemarks();

	public void setRemarks(String remarks);

	public String getPicture_path();

	public void setPicture_path(String picture_path);
	
	//推送保修码
	public String getPush_code();
	
	public void setPush_code(String push_code) ;
	
	public String getInsBranch_id();

	public void setInsBranch_id(String insBranch_id);

	public String getInsBranch_name() ;

	public void setInsBranch_name(String insBranch_name);
	

	public String getStatus_code();

	public void setStatus_code(String status_code);

	public String getStatus_name();

	public void setStatus_name(String status_name);

	// 权限管理
	public String getdept_list();

	public void setdept_list(String dept_list);

	// 系统时间
	public Date getSystemdate();

	public void setSystemdate(Date systemdate);

	// 数据创建时间
	public Date getCreat_date();

	public void setCreate_date(Date create_date);

	// 数据修改时间
	public Date getMdf_date();

	public void setMdf_date(Date mdf_date);

	// 创建人
	public String getCreate_user();

	public void setCreate_user(String create_user);

	// 修改人
	public String getMdf_user();

	public void setMdf_user(String create_user);

	// 协议类型
	public String getAgreement_type();

	public void setAgreement_type(String agreement_type);
	
	
	//导入协议类型
	public String getProtocol_type();

	public void setProtocol_type(String protocol_type);

	//补充协议编号
	public String getSup_protocol_no() ;

	public void setSup_protocol_no(String sup_protocol_no) ;

	//补充协议内容
	public String getSup_protocol_content();

	public void setSup_protocol_content(String sup_protocol_content);

	//补充协议生效日期
	public Date getSup_protocol_stadate();
	public void setSup_protocol_stadate(Date sup_protocol_stadate);
	
	//协议类别
	public String getProtocol_category() ;

	public void setProtocol_category(String protocol_category);

	//合同类型
	public String getContract_type() ;

	public void setContract_type(String contract_type);
	
	//签订类型
	public String getSign_type();

	public void setSign_type(String sign_type) ;
	
	//甲方
	public String getParty_a();

	public void setParty_a(String party_a);

	//乙方
	public String getParty_b();

	public void setParty_b(String party_b);

	//丙方
	public String getParty_c();

	public void setParty_c(String party_c);
	//乙方代理类型
	public String getEnterprise_type();

	public void setEnterprise_type(String enterprise_type) ;

	//社会码
	public String getCredit_code();

	public void setCredit_code(String credit_code);

	//是否汽车咨询
	public String getIsconsult();

	public void setIsconsult(String isconsult);

	//开户行
	public String getBank_name();

	public void setBank_name(String bank_name) ;

	//银行账号
	public String getBank_code();

	public void setBank_code(String bank_code);

	//乙方联系人
	public String getParty_b_name();

	public void setParty_b_name(String party_b_name);

	//乙方联系人电话
	public String getParty_b_phone();

	public void setParty_b_phone(String party_b_phone);

	//合同金额
	public double getAmount() ;

	public void setAmount(double amount);

	//是否一月内过期
	public String getIsOverdue();
	
	public void setIsOverdue(String isOverdue) ;
	// 分页
	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

	public int getStart();

	public int getLimit();

	// 导入导出
	public String getEmp_id();

	public void setEmp_id(String emp_id);

	public String getFile_name();

	public void setFile_name(String file_name);

	public String getBus_id();

	public void setBus_id(String bus_id);

	public String getFile_id();

	public void setFile_id(String file_id);

	public String getAgreement_agency_type();

	public void setAgreement_agency_type(String agreement_agency_type);
	
	public String getReason();

	public void setReason(String reason);
}
