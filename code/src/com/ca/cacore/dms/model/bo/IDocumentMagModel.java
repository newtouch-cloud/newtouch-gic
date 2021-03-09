package com.ca.cacore.dms.model.bo;

import java.util.Date;

import com.newtouch.core.model.IPageCount;
/**
 * 
 * @author zdd
 *
 */
public interface IDocumentMagModel extends IPageCount{

	Integer getSeqid();

	void setSeqid(Integer seqid);

	String getReceiptdate();

	void setReceiptdate(String receiptdate);

	String getApplyuser_id();

	void setApplyuser_id(String applyuser_id);

	String getApplyuser_name();

	void setApplyuser_name(String applyuser_name);

	String getApply_sysbranchid();

	void setApply_sysbranchid(String apply_sysbranchid);

	String getApply_sysbranchname();

	void setApply_sysbranchname(String apply_sysbranchname);

	String getApplay_cpybranchid();

	void setApplay_cpybranchid(String applay_cpybranchid);

	String getApplay_cpybranchname();

	void setApplay_cpybranchname(String applay_cpybranchname);

	String getDocument_idcard();

	void setDocument_idcard(String document_idcard);

	String getDocument_name();

	void setDocument_name(String document_name);

	Integer getDocument_serial_beg();

	void setDocument_serial_beg(Integer document_serial_beg);

	Integer getDocument_serial_end();

	void setDocument_serial_end(Integer document_serial_end);

	String getReturn_time();

	void setReturn_time(String return_time);

	String getReturnuser_code();

	void setReturnuser_code(String returnuser_code);

	String getReturnuser_name();

	void setReturnuser_name(String returnuser_name);

	String getReturn_sysbranchid();

	void setReturn_sysbranchid(String return_sysbranchid);

	String getReturn_sysbranchname();

	void setReturn_sysbranchname(String return_sysbranchname);

	String getReturn_cpybranchid();

	void setReturn_cpybranchid(String return_cpybranchid);

	String getReturn_cpybranchname();

	void setReturn_cpybranchname(String return_cpybranchname);

	Integer getUser_number();

	void setUser_number(Integer user_number);

	Integer getObsolete_number();

	void setObsolete_number(Integer obsolete_number);

	Integer getNot_used_number();

	void setNot_used_number(Integer not_used_number);

	String getRemarks();

	void setRemarks(String remarks);

	String getCreate_user();

	void setCreate_user(String create_user);

	String getModify_user();

	void setModify_user(String modify_user);

	Integer getApplay_num();

	void setApplay_num(Integer applay_num);

	Integer getReturn_num();

	void setReturn_num(Integer return_num);

	String getReceiptdate1();

	void setReceiptdate1(String receiptdate1);

	String getReturn_time1();

	void setReturn_time1(String return_time1);

	void setApplyuser_signname(String applyuser_signname);

	void setReturn_sysbranchname1(String return_sysbranchname);

	String getApplyuser_signname();

	String getRe_document_idcard();

	void setRe_document_idcard(String re_document_idcard);

	String getRe_document_name();

	void setRe_document_name(String re_document_name);

	Integer getRe_document_serial_beg();

	void setRe_document_serial_beg(Integer re_document_serial_beg);

	Integer getRe_document_serial_end();

	void setRe_document_serial_end(Integer re_document_serial_end);
	public String getRedocument_idcard();
	public void setRedocument_idcard(String redocument_idcard);
	public String getRedocument_name();
	public void setRedocument_name(String redocument_name);
	public Integer getRedocument_serial_beg();
	public void setRedocument_serial_beg(Integer redocument_serial_beg);
	public Integer getRedocument_serial_end();
	public void setRedocument_serial_end(Integer redocument_serial_end);
	public String getCcode();
	public void setCcode(String ccode);
	public String getCcodename();
	public void setCcodename(String ccodename);
	public String getDept_list();
	public void setDept_list(String dept_list);

}
