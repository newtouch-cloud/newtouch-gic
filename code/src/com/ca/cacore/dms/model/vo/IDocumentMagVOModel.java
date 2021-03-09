package com.ca.cacore.dms.model.vo;

import java.util.Date;

import com.newtouch.core.model.IPageCount;
/**
 * 
 * @author zdd
 *
 */
public interface IDocumentMagVOModel extends IPageCount{

	Integer getSeqid();

	void setSeqid(Integer seqid);

	Date getReceiptdate();

	void setReceiptdate(Date receiptdate);

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

	Date getReturn_time();

	void setReturn_time(Date return_time);

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

	Date getReceiptdate1();

	void setReceiptdate1(Date receiptdate1);

	Date getReturn_time1();

	void setReturn_time1(Date return_time1);

	void setApplyuser_signname(String applyuser_signname);

	void setReturn_sysbranchname1(String return_sysbranchname);


}
