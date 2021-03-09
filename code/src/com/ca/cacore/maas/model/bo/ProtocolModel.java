package com.ca.cacore.maas.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理表 CD_AGREEMENT 
*/
public class ProtocolModel implements IProtocolModel  {

	private Integer seq_id;
	private String ins_branch;// 保险公司机构代码
	private String ins_branchname;// 保险公司机构名称
	private String contacts_name;// 联系人 18
	private String contacts_way;//联系方式 18
	private String agreement_no;// 协议号 18 非空
	private String branch_id;// 销售公司机构id
	private String branch_name;// 销售公司名称
	private String   startdate;// 生效日期   从 Date 非空
	private Date   startdate1;// 生效日期 至 非空
	private String   enddate;// 终止日期  从 Date 非空
	private Date   enddate1;//终止日期  至 Date 非空
	private String   dateofsign;//协议签订日期 Date 非空
	private String persion_id;// 签订人代码 18 非空 
	private String persion_name;//	签订人姓名  200
	private String sales_id;//人员代码 15
	private String sales_name;//人员姓名 50
	private String status;//状态 关联-状态数据字典表-SYS_Library_CommonStatus  status_code 
	private String status_code;//状态编号 10 
	private String status_name;//状态名称 32
	private Date   systemdate;// 系统时间
	private String code;//签订人id
	private String name;//签订人姓名
	private String insBranch_id;//保险公司id
	private String insBranch_name;//保险公司名称
	private String emp_id;
	private String file_name;
	private String bus_id;
	private String file_id;
	
	private String className;//险类名称
	private String classCode;//险类编号
	private String riskName;//险种名称
	private String riskCode;//险种编号
	private Float poundage;//手续费
	
	
	
	
	
	
	

	public String getIns_branchname() {
		return ins_branchname;
	}

	public void setIns_branchname(String ins_branchname) {
		this.ins_branchname = ins_branchname;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public Float getPoundage() {
		return poundage;
	}

	public void setPoundage(Float poundage) {
		this.poundage = poundage;
	}

	private PageCount pageCount = new PageCount();

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Integer getSeq_id() {
		return seq_id;
	}

	/** 
	* 
	* @param seq_id 
	* @description:
	*/
	@Override
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getIns_branch() {
		return ins_branch;
	}

	/** 
	* 
	* @param ins_branch 
	* @description:
	*/
	@Override
	public void setIns_branch(String ins_branch) {
		this.ins_branch = ins_branch;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getContacts_name() {
		return contacts_name;
	}

	/** 
	* 
	* @param contacts_name 
	* @description:
	*/
	@Override
	public void setContacts_name(String contacts_name) {
		this.contacts_name = contacts_name;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getContacts_way() {
		return contacts_way;
	}

	/** 
	* 
	* @param contacts_way 
	* @description:
	*/
	@Override
	public void setContacts_way(String contacts_way) {
		this.contacts_way = contacts_way;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getAgreement_no() {
		return agreement_no;
	}

	/** 
	* 
	* @param agreement_no 
	* @description:
	*/
	@Override
	public void setAgreement_no(String agreement_no) {
		this.agreement_no = agreement_no;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getBranch_id() {
		return branch_id;
	}

	/** 
	* 
	* @param branch_id 
	* @description:
	*/
	@Override
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getBranch_name() {
		return branch_name;
	}

	/** 
	* 
	* @param branch_name 
	* @description:
	*/
	@Override
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getStartdate() {
		return startdate;
	}

	/** 
	* 
	* @param startdate 
	* @description:
	*/
	@Override
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getStartdate1() {
		return startdate1;
	}

	/** 
	* 
	* @param startdate1 
	* @description:
	*/
	@Override
	public void setStartdate1(Date startdate1) {
		this.startdate1 = startdate1;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getEnddate() {
		return enddate;
	}

	/** 
	* 
	* @param enddate 
	* @description:
	*/
	@Override
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getEnddate1() {
		return enddate1;
	}

	/** 
	* 
	* @param enddate1 
	* @description:
	*/
	@Override
	public void setEnddate1(Date enddate1) {
		this.enddate1 = enddate1;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getDateofsign() {
		return dateofsign;
	}

	/** 
	* 
	* @param dateofsign 
	* @description:
	*/
	@Override
	public void setDateofsign(String dateofsign) {
		this.dateofsign = dateofsign;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getPersion_id() {
		return persion_id;
	}

	/** 
	* 
	* @param persion_id 
	* @description:
	*/
	@Override
	public void setPersion_id(String persion_id) {
		this.persion_id = persion_id;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getPersion_name() {
		return persion_name;
	}

	/** 
	* 
	* @param persion_name 
	* @description:
	*/
	@Override
	public void setPersion_name(String persion_name) {
		this.persion_name = persion_name;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getSales_id() {
		return sales_id;
	}

	/** 
	* 
	* @param sales_id 
	* @description:
	*/
	@Override
	public void setSales_id(String sales_id) {
		this.sales_id = sales_id;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getSales_name() {
		return sales_name;
	}

	/** 
	* 
	* @param sales_name 
	* @description:
	*/
	@Override
	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getStatus() {
		return status;
	}

	/** 
	* 
	* @param status 
	* @description:
	*/
	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getStatus_code() {
		return status_code;
	}

	/** 
	* 
	* @param status_code 
	* @description:
	*/
	@Override
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getStatus_name() {
		return status_name;
	}

	/** 
	* 
	* @param status_name 
	* @description:
	*/
	@Override
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getSystemdate() {
		return systemdate;
	}

	/** 
	* 
	* @param systemdate 
	* @description:
	*/
	@Override
	public void setSystemdate(Date systemdate) {
		this.systemdate = systemdate;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCode() {
		return code;
	}

	/** 
	* 
	* @param code 
	* @description:
	*/
	@Override
	public void setCode(String code) {
		this.code = code;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getName() {
		return name;
	}

	/** 
	* 
	* @param name 
	* @description:
	*/
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getInsBranch_id() {
		return insBranch_id;
	}

	/** 
	* 
	* @param insBranch_id 
	* @description:
	*/
	@Override
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getInsBranch_name() {
		return insBranch_name;
	}

	/** 
	* 
	* @param insBranch_name 
	* @description:
	*/
	@Override
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}

	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public PageCount getPageCount() {
		return pageCount;
	}

	/** 
	* 
	* @param pageCount 
	* @description:
	*/
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

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getBus_id() {
		return bus_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	
}
