package com.ca.cacore.lms.model.bo;

import java.sql.Date;


/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description:职级指标信息t_rank_index
*/
public class RankIndexModel implements IRankIndexModel {
	
	private String serno;// 01.serno
	private String impmeans_no;// 子办法编号
	private String rank_no;//职级代码
	private String rela_type;// 关系类型
	private String index_no;//指标代码
	private String dept_type;//部门类型
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
	public String getRank_no() {
		return rank_no;
	}
	public void setRank_no(String rank_no) {
		this.rank_no = rank_no;
	}
	public String getRela_type() {
		return rela_type;
	}
	public void setRela_type(String rela_type) {
		this.rela_type = rela_type;
	}
	public String getIndex_no() {
		return index_no;
	}
	public void setIndex_no(String index_no) {
		this.index_no = index_no;
	}
	public String getDept_type() {
		return dept_type;
	}
	public void setDept_type(String dept_type) {
		this.dept_type = dept_type;
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
