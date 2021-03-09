package com.ca.cacore.lms.model.bo;

import java.sql.Date;


/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description: t_index_elements 指标元素配置表
*/
public class IndexElementsModel implements IIndexElementsModel  {
	
	private String serno;// 01.serno
	private String index_calcno;// 指标计算码
	private String variable_no;// 变量编码
	private String variable_name;// 变量名称
	private String attribute_no;// c属性编码
	private String variable_order;//变量顺序
	private String expand_1;//扩展一
	private String expand_2;//扩展二
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
	public String getVariable_no() {
		return variable_no;
	}
	public void setVariable_no(String variable_no) {
		this.variable_no = variable_no;
	}
	public String getVariable_name() {
		return variable_name;
	}
	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}
	public String getAttribute_no() {
		return attribute_no;
	}
	public void setAttribute_no(String attribute_no) {
		this.attribute_no = attribute_no;
	}
	public String getVariable_order() {
		return variable_order;
	}
	public void setVariable_order(String variable_order) {
		this.variable_order = variable_order;
	}
	public String getExpand_1() {
		return expand_1;
	}
	public void setExpand_1(String expand_1) {
		this.expand_1 = expand_1;
	}
	public String getExpand_2() {
		return expand_2;
	}
	public void setExpand_2(String expand_2) {
		this.expand_2 = expand_2;
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
