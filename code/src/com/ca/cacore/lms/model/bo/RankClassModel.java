package com.ca.cacore.lms.model.bo;

import java.sql.Date;


/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description:职级分类信息t_rank_class
*/
public class RankClassModel implements IRankClassModel {
	
	private String serno;// 01.serno
	private String rank_class_no;// 职级序列代码
	private String rank_class_name;//职级序列名称
	private String rank_class_type;// 职级序列类型
	private String up_rank_class;//上级职级序列
	private String dept_type;//部门类型
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
	public String getRank_class_no() {
		return rank_class_no;
	}
	public void setRank_class_no(String rank_class_no) {
		this.rank_class_no = rank_class_no;
	}
	public String getRank_class_name() {
		return rank_class_name;
	}
	public void setRank_class_name(String rank_class_name) {
		this.rank_class_name = rank_class_name;
	}
	public String getRank_class_type() {
		return rank_class_type;
	}
	public void setRank_class_type(String rank_class_type) {
		this.rank_class_type = rank_class_type;
	}
	public String getUp_rank_class() {
		return up_rank_class;
	}
	public void setUp_rank_class(String up_rank_class) {
		this.up_rank_class = up_rank_class;
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
	public String getPatch_memo() {
		return patch_memo;
	}
	public void setPatch_memo(String patch_memo) {
		this.patch_memo = patch_memo;
	}


}
