package com.ca.cacore.lms.model.bo;

import java.sql.Date;


/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description:实施管理办法信息t_impmeansver
*/
public class ImpmeansverModel implements IImpmeansverModel  {
	
	private String serno;// 01.serno
	private String impmeansver_no;// 职级序列代码
	private String impmeansver_name;//职级序列名称
	private String impmeans_no;// 职级序列类型
	private String dept_no;//部门编号
	private String calc_order;//计算顺序
	private String iss_unit;//颁布单位
	private Date iss_date;//颁布日期
	private Date start_date;// 03.开始日期
	private Date end_date;// 04.结束日期
	private Date crt_date;// 05.创建日期
	private Date mdf_date;// 06.修改日期
	private String crt_user;// 07.创建操作员
	private String mdf_user;// 08.修改操作员
	private String data_flag;// 09.有效性标志
	private String patch_memo;// 备注信息
	public String getSerno() {
		return serno;
	}
	public void setSerno(String serno) {
		this.serno = serno;
	}
	public String getImpmeansver_no() {
		return impmeansver_no;
	}
	public void setImpmeansver_no(String impmeansver_no) {
		this.impmeansver_no = impmeansver_no;
	}
	public String getImpmeansver_name() {
		return impmeansver_name;
	}
	public void setImpmeansver_name(String impmeansver_name) {
		this.impmeansver_name = impmeansver_name;
	}
	public String getImpmeans_no() {
		return impmeans_no;
	}
	public void setImpmeans_no(String impmeans_no) {
		this.impmeans_no = impmeans_no;
	}
	public String getDept_no() {
		return dept_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}
	public String getCalc_order() {
		return calc_order;
	}
	public void setCalc_order(String calc_order) {
		this.calc_order = calc_order;
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
	public String getPatch_memo() {
		return patch_memo;
	}
	public void setPatch_memo(String patch_memo) {
		this.patch_memo = patch_memo;
	}
	
}
