package com.ca.cacore.lms.model.bo;

import java.sql.Date;


/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description:参数信息t_paradef
*/
public class ParadefModel implements IParadefModel  {
	
	private String serno;// 01.serno
	private String impmeans_no;//子办法编号
	private String para_type;//参数类型
	private String rank_no;// 职级代码
	private String para_no;//参数代码
	private String para_name;//参数名称
	private String start_value1;//开始值一
	private String end_value1;//结束值一
	private String start_value2;//开始值二
	private String end_value2;//结束值二
	private String para_value;//参数值
	private String para_flag;//参数用途
	private String memo;// 备注信息
	private Date start_date;// 03.开始日期
	private Date end_date;// 04.结束日期
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
	public String getImpmeans_no() {
		return impmeans_no;
	}
	public void setImpmeans_no(String impmeans_no) {
		this.impmeans_no = impmeans_no;
	}
	public String getPara_type() {
		return para_type;
	}
	public void setPara_type(String para_type) {
		this.para_type = para_type;
	}
	public String getRank_no() {
		return rank_no;
	}
	public void setRank_no(String rank_no) {
		this.rank_no = rank_no;
	}
	public String getPara_no() {
		return para_no;
	}
	public void setPara_no(String para_no) {
		this.para_no = para_no;
	}
	public String getPara_name() {
		return para_name;
	}
	public void setPara_name(String para_name) {
		this.para_name = para_name;
	}
	public String getStart_value1() {
		return start_value1;
	}
	public void setStart_value1(String start_value1) {
		this.start_value1 = start_value1;
	}
	public String getEnd_value1() {
		return end_value1;
	}
	public void setEnd_value1(String end_value1) {
		this.end_value1 = end_value1;
	}
	public String getStart_value2() {
		return start_value2;
	}
	public void setStart_value2(String start_value2) {
		this.start_value2 = start_value2;
	}
	public String getEnd_value2() {
		return end_value2;
	}
	public void setEnd_value2(String end_value2) {
		this.end_value2 = end_value2;
	}
	public String getPara_value() {
		return para_value;
	}
	public void setPara_value(String para_value) {
		this.para_value = para_value;
	}
	public String getPara_flag() {
		return para_flag;
	}
	public void setPara_flag(String para_flag) {
		this.para_flag = para_flag;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
}
