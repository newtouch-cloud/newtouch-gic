package com.newtouch.component.axis2.model;

/**
* @since:    2014年4月14日   
* @author    ZhangChen
* @description:校验中介人员的对应的协议号是否合法!
*   CD_AGREEMENT,SMIS_SALES,SYS_BRANCH
 */
public class ProtocolQueryVoModel {
	
	private String sales_id;//中介人员id
	private String sales_name;//中介人员姓名;
	private String branch_id;//机构id
	private String branch_parentid;//机构父节点id
	private String branch_allpath;//机构上下节点关联
	private String agreement_no;// 协议号 18 非空
	private String agreement_flag;//判断是否存在协议号 0不存在 1存在
	
	
	public String getSales_id() {
		return sales_id;
	}
	public void setSales_id(String sales_id) {
		this.sales_id = sales_id;
	}
	public String getSales_name() {
		return sales_name;
	}
	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getAgreement_no() {
		return agreement_no;
	}
	public void setAgreement_no(String agreement_no) {
		this.agreement_no = agreement_no;
	}
	public String getBranch_parentid() {
		return branch_parentid;
	}
	public void setBranch_parentid(String branch_parentid) {
		this.branch_parentid = branch_parentid;
	}
	public String getBranch_allpath() {
		return branch_allpath;
	}
	public void setBranch_allpath(String branch_allpath) {
		this.branch_allpath = branch_allpath;
	}
	public String getAgreement_flag() {
		return agreement_flag;
	}
	public void setAgreement_flag(String agreement_flag) {
		this.agreement_flag = agreement_flag;
	}
	
}
