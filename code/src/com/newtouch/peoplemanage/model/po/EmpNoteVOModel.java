package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

/**
 * 相关事项说明实体类
 * @author Ming Ying
 *
 */
public class EmpNoteVOModel implements IEmpNoteVOModel{
	private String seq_id;
	private String person_no;
	private String content_code;
	private String  flag;
	
	private Date  create_date;
	private String  creat_user;
	private String  may_date;
	private Date  may_user;
	
	public String getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}
	public String getPerson_no() {
		return person_no;
	}
	public void setPerson_no(String person_no) {
		this.person_no = person_no;
	}
	public String getContent_code() {
		return content_code;
	}
	public void setContent_code(String content_code) {
		this.content_code = content_code;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getCreat_user() {
		return creat_user;
	}
	public void setCreat_user(String creat_user) {
		this.creat_user = creat_user;
	}
	public String getMay_date() {
		return may_date;
	}
	public void setMay_date(String may_date) {
		this.may_date = may_date;
	}
	public Date getMay_user() {
		return may_user;
	}
	public void setMay_user(Date may_user) {
		this.may_user = may_user;
	}

	
}
