package com.ca.cacore.lms.model.bo;

import java.sql.Date;


/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description:指标信息t_index
*/
public class IndexModel implements IIndexModel {
	
	private String serno;// 01.serno
	private String impmeans_no;// 指标计算码
	private String index_no;// 指标类型
	private String index_name;// 计算顺序
	private String index_calcno;//计算方法
	private String index_order;// 计算类
	private String is_valid;//10.数补备注
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
	public String getIndex_no() {
		return index_no;
	}
	public void setIndex_no(String index_no) {
		this.index_no = index_no;
	}
	public String getIndex_name() {
		return index_name;
	}
	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}
	public String getIndex_calcno() {
		return index_calcno;
	}
	public void setIndex_calcno(String index_calcno) {
		this.index_calcno = index_calcno;
	}
	public String getIndex_order() {
		return index_order;
	}
	public void setIndex_order(String index_order) {
		this.index_order = index_order;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
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
