package com.ca.cacore.lms.model.bo;

import java.sql.Date;

/**
* @since:    2014年3月2日   
* @author    ss
* @description:基本法信息	t_basiclaws
*/
public class BasicLawsModel implements IBasicLawsModel{
	private String serno;// 01.serno
	private String basiclaw_no;// 基本法编号
	private String impmeansver_name;// 实施办法名称
	private String basiclaw_ver;// 基本法版本
	private String dept_type;// 部门类型
	private String iss_unit;// 颁布单位
	private Date iss_date;// 颁布日期
	private Date start_date;// 03.开始日期
	private Date end_date;// 04.结束日期
	private String memo;// 备注信息
	private Date crt_date;// 05.创建日期
	private Date mdf_date;// 06.修改日期
	private String crt_user;// 07.创建操作员
	private String mdf_user;// 08.修改操作员
	private String data_flag;// 09.有效性标志
	public BasicLawsModel() {
	}
	public BasicLawsModel(String serno) {
		this.serno = serno;
	}
	public String getSerno() {
		return serno;
	}
	public void setSerno(String serno) {
		this.serno = serno;
	}
	public String getBasiclaw_no() {
		return basiclaw_no;
	}
	public void setBasiclaw_no(String basiclaw_no) {
		this.basiclaw_no = basiclaw_no;
	}
	public String getImpmeansver_name() {
		return impmeansver_name;
	}
	public void setImpmeansver_name(String impmeansver_name) {
		this.impmeansver_name = impmeansver_name;
	}
	public String getBasiclaw_ver() {
		return basiclaw_ver;
	}
	public void setBasiclaw_ver(String basiclaw_ver) {
		this.basiclaw_ver = basiclaw_ver;
	}
	public String getDept_type() {
		return dept_type;
	}
	public void setDept_type(String dept_type) {
		this.dept_type = dept_type;
	}
	public String getIss_unit() {
		return iss_unit;
	}
	public void setIss_unit(String iss_unit) {
		this.iss_unit = iss_unit;
	}
	public Date getIss_date() {
		return iss_date;
	}
	public void setIss_date(Date iss_date) {
		this.iss_date = iss_date;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getCrt_date() {
		return crt_date;
	}
	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}
	public Date getMdf_date() {
		return mdf_date;
	}
	public void setMdf_date(Date mdf_date) {
		this.mdf_date = mdf_date;
	}
	public String getCrt_user() {
		return crt_user;
	}
	public void setCrt_user(String crt_user) {
		this.crt_user = crt_user;
	}
	public String getMdf_user() {
		return mdf_user;
	}
	public void setMdf_user(String mdf_user) {
		this.mdf_user = mdf_user;
	}
	public String getData_flag() {
		return data_flag;
	}
	public void setData_flag(String data_flag) {
		this.data_flag = data_flag;
	}
	
}
