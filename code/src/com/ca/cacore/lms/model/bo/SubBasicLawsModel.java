package com.ca.cacore.lms.model.bo;

import java.sql.Date;



/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description:子基本法信息	t_impmeans
*/
public class SubBasicLawsModel implements ISubBasicLawsModel{
	
	private String serno;
	private String basiclaw_no;
	private String impmeans_no;
	private String impmeans_name;
	private Date start_date;
	private Date end_date;
	private String memo;
	private Date crt_date;
	private Date mdf_date;
	private String crt_user;
	private String mdf_user;
	private String data_flag;


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

	public String getImpmeans_no() {
		return impmeans_no;
	}

	public void setImpmeans_no(String impmeans_no) {
		this.impmeans_no = impmeans_no;
	}

	public String getImpmeans_name() {
		return impmeans_name;
	}

	public void setImpmeans_name(String impmeans_name) {
		this.impmeans_name = impmeans_name;
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
