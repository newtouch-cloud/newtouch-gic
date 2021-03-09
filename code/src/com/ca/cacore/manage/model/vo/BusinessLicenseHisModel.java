package com.ca.cacore.manage.model.vo;

import java.sql.Date;

public class BusinessLicenseHisModel {
	private int Seq_id;
	private String licensepath;
	private Date c_crt_tm;
	private Date c_up_tm;
	private String c_crt_user;
	private String c_up_user;
	private String fileName;
	private String status;
	private String branch_name;
	private int id;
	private int c_last_mark;

	public int getSeq_id() {
		return Seq_id;
	}
	public void setSeq_id(int seq_id) {
		Seq_id = seq_id;
	}
	public String getLicensepath() {
		return licensepath;
	}
	public void setLicensepath(String licensepath) {
		this.licensepath = licensepath;
	}
	public Date getC_crt_tm() {
		return c_crt_tm;
	}
	public void setC_crt_tm(Date c_crt_tm) {
		this.c_crt_tm = c_crt_tm;
	}
	public Date getC_up_tm() {
		return c_up_tm;
	}
	public void setC_up_tm(Date c_up_tm) {
		this.c_up_tm = c_up_tm;
	}
	public String getC_crt_user() {
		return c_crt_user;
	}
	public void setC_crt_user(String c_crt_user) {
		this.c_crt_user = c_crt_user;
	}
	public String getC_up_user() {
		return c_up_user;
	}
	public void setC_up_user(String c_up_user) {
		this.c_up_user = c_up_user;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getC_last_mark() {
		return c_last_mark;
	}
	public void setC_last_mark(int c_last_mark) {
		this.c_last_mark = c_last_mark;
	}

}
