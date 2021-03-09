package com.ca.cacore.lms.model.bo;

import java.sql.Date;


/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description:指标计算规则信息t_index_calc
*/
public class IndexCalcModel implements IIndexCalcModel {
	
	private String serno;// 01.serno
	private String index_calcno;// 指标计算码
	private String index_type;// 指标类型
	private String calc_order;// 计算顺序
	private String calc_module;// 计算类
	private String calc_mthd;//计算方法
	private String memo;//10.数补备注
	private Date crt_date;// 05.创建日期
	private Date mdf_date;// 06.修改日期
	private String crt_user;// 07.创建操作员
	private String mdf_user;// 08.修改操作员
	private String data_flag;// 09.有效性标志
	public String getSerno() {
		return serno;
	}
	public void setSerno(String serno) {
		this.serno = serno;
	}
	public String getIndex_calcno() {
		return index_calcno;
	}
	public void setIndex_calcno(String index_calcno) {
		this.index_calcno = index_calcno;
	}
	public String getIndex_type() {
		return index_type;
	}
	public void setIndex_type(String index_type) {
		this.index_type = index_type;
	}
	public String getCalc_order() {
		return calc_order;
	}
	public void setCalc_order(String calc_order) {
		this.calc_order = calc_order;
	}
	public String getCalc_module() {
		return calc_module;
	}
	public void setCalc_module(String calc_module) {
		this.calc_module = calc_module;
	}
	public String getCalc_mthd() {
		return calc_mthd;
	}
	public void setCalc_mthd(String calc_mthd) {
		this.calc_mthd = calc_mthd;
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
