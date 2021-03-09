package com.ca.cacore.lms.model.bo;

import java.sql.Date;


/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description:职级与序列关系t_rank_rankseq
*/
public class RankRankSeqModel implements IRankRankSeqModel{
	
	private String serno;// 01.serno
	private String rank_no;// 职级代码
	private String rank_class_no;// 职级序列代码
	private String rank_name;// 职级名称
	private Integer rank_seniority;// 职级资历
	private String up_rank_no;//上级职级
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
	public String getRank_no() {
		return rank_no;
	}
	public void setRank_no(String rank_no) {
		this.rank_no = rank_no;
	}
	public String getRank_class_no() {
		return rank_class_no;
	}
	public void setRank_class_no(String rank_class_no) {
		this.rank_class_no = rank_class_no;
	}
	public String getRank_name() {
		return rank_name;
	}
	public void setRank_name(String rank_name) {
		this.rank_name = rank_name;
	}
	public Integer getRank_seniority() {
		return rank_seniority;
	}
	public void setRank_seniority(Integer rank_seniority) {
		this.rank_seniority = rank_seniority;
	}
	public String getUp_rank_no() {
		return up_rank_no;
	}
	public void setUp_rank_no(String up_rank_no) {
		this.up_rank_no = up_rank_no;
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
