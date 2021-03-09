package com.ca.cacore.dms.model.bo;

import java.util.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 
 * @author zdd03 20190621
 *
 */
public class DocumentMagModel implements IDocumentMagModel{
	private Integer seqid;         
	private String receiptdate;                         //领用日期
	private String receiptdate1;                         //领用日期
	private String applyuser_id;                      //领用人
	private String applyuser_name;                    //领用人
	private String applyuser_signname;                    //领用人签名
	private String apply_sysbranchid;                 //领用机构
	private String apply_sysbranchname;               //领用机构
	private String applay_cpybranchid;                //保险公司
	private String applay_cpybranchname;              //保险公司
	private String document_idcard;                   //单证识别码
	private String document_name;                     //单证名称
	private Integer  document_serial_beg;               //单证流水号（起）
	private Integer  document_serial_end;               //单证流水号(止)
	private String return_time;                         //交回日期
	private String return_time1;                         //交回日期
	private String returnuser_code;                   //交回人
	private String returnuser_name;                   //交回人
	private String return_sysbranchid;               //交回机构
	private String return_sysbranchname;             //交回机构
	private String return_cpybranchid;                //交回保险公司
	private String return_cpybranchname;              //交回保险公司
	private Integer  user_number;                       //其中：已使用
	private Integer  obsolete_number;                   //其中：作废
	private Integer  not_used_number;                   //其中：未使用
	private String remarks;                           //备注
	private String create_user;
	private String modify_user;
	private Integer   applay_num;                       // 数量=单证流水号（止）-单证流水号（始）+1			
	private Integer return_num;                        //交回的数量还要等于：其中已使用+其中作废+其中未使用			
	private PageCount pageCount=new PageCount();
	private String re_document_idcard;                   //单证识别码
	private String re_document_name;                     //单证名称
	private Integer re_document_serial_beg;               //单证流水号（起）
	private Integer re_document_serial_end;               //单证流水号(止)
	private String redocument_idcard;                   //单证识别码
	private String redocument_name;                     //单证名称
	private Integer  redocument_serial_beg;               //单证流水号（起）
	private Integer  redocument_serial_end;               //单证流水号(止)
	private String ccode;
	private String ccodename;
	@Override   
	public Integer getSeqid() {
		return seqid;
	}
	@Override   
	public void setSeqid(Integer seqid) {
		this.seqid = seqid;
	}
	@Override
	public String getReceiptdate() {
		return receiptdate;
	}
	@Override
	public void setReceiptdate(String receiptdate) {
		this.receiptdate = receiptdate;
	}

	@Override   
	public String getApplyuser_id() {
		return applyuser_id;
	}
	@Override   
	public void setApplyuser_id(String applyuser_id) {
		this.applyuser_id = applyuser_id;
	}
	@Override
	public String getApplyuser_name() {
		return applyuser_name;
	}
	@Override   
	public void setApplyuser_name(String applyuser_name) {
		this.applyuser_name = applyuser_name;
	}
	@Override  
	public String getApply_sysbranchid() {
		return apply_sysbranchid;
	}
	@Override   
	public void setApply_sysbranchid(String apply_sysbranchid) {
		this.apply_sysbranchid = apply_sysbranchid;
	}
	@Override   
	public String getApply_sysbranchname() {
		return apply_sysbranchname;
	}
	@Override   
	public void setApply_sysbranchname(String apply_sysbranchname) {
		this.apply_sysbranchname = apply_sysbranchname;
	}
	@Override   
	public String getApplay_cpybranchid() {
		return applay_cpybranchid;
	}
	@Override   
	public void setApplay_cpybranchid(String applay_cpybranchid) {
		this.applay_cpybranchid = applay_cpybranchid;
	}
	@Override   public String getApplay_cpybranchname() {
		return applay_cpybranchname;
	}
	@Override   
	public void setApplay_cpybranchname(String applay_cpybranchname) {
		this.applay_cpybranchname = applay_cpybranchname;
	}
	@Override   
	public String getDocument_idcard() {
		return document_idcard;
	}
	@Override   public void setDocument_idcard(String document_idcard) {
		this.document_idcard = document_idcard;
	}
	@Override   
	public String getDocument_name() {
		return document_name;
	}
	@Override   
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}
	@Override   
	public Integer  getDocument_serial_beg() {
		return document_serial_beg;
	}
	@Override   
	public void setDocument_serial_beg(Integer  document_serial_beg) {
		this.document_serial_beg = document_serial_beg;
	}
	@Override   
	public Integer getDocument_serial_end() {
		return document_serial_end;
	}
	@Override   
	public void setDocument_serial_end(Integer document_serial_end) {
		this.document_serial_end = document_serial_end;
	}
	@Override
	public String getReturn_time() {
		return return_time;
	}
	@Override   
	public void setReturn_time(String return_time) {
		this.return_time = return_time;
	}
	@Override   
	public String getReturnuser_code() {
		return returnuser_code;
	}
	@Override   
	public void setReturnuser_code(String returnuser_code) {
		this.returnuser_code = returnuser_code;
	}
	@Override  
	public String getReturnuser_name() {
		return returnuser_name;
	}
	@Override   
	public void setReturnuser_name(String returnuser_name) {
		this.returnuser_name = returnuser_name;
	}
	@Override   
	public String getReturn_sysbranchid() {
		return return_sysbranchid;
	}
	@Override   
	public void setReturn_sysbranchid(String return_sysbranchid) {
		this.return_sysbranchid = return_sysbranchid;
	}
	@Override   
	public String getReturn_sysbranchname() {
		return return_sysbranchname;
	}
	@Override   
	public void setReturn_sysbranchname1(String return_sysbranchname) {
		this.return_sysbranchname = return_sysbranchname;
	}
	@Override   
	public String getReturn_cpybranchid() {
		return return_cpybranchid;
	}
	@Override   
	public void setReturn_cpybranchid(String return_cpybranchid) {
		this.return_cpybranchid = return_cpybranchid;
	}
	@Override   
	public String getReturn_cpybranchname() {
		return return_cpybranchname;
	}
	@Override   
	public void setReturn_cpybranchname(String return_cpybranchname) {
		this.return_cpybranchname = return_cpybranchname;
	}
	@Override   
	public Integer getUser_number() {
		return user_number;
	}
	@Override   
	public void setUser_number(Integer user_number) {
		this.user_number = user_number;
	}
	@Override  
	public Integer getObsolete_number() {
		return obsolete_number;
	}
	@Override   
	public void setObsolete_number(Integer obsolete_number) {
		this.obsolete_number = obsolete_number;
	}
	@Override   
	public Integer getNot_used_number() {
		return not_used_number;
	}
	@Override  
	public void setNot_used_number(Integer not_used_number) {
		this.not_used_number = not_used_number;
	}
	@Override   
	public String getRemarks() {
		return remarks;
	}
	@Override  
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override  
	public String getCreate_user() {
		return create_user;
	}
	@Override   
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	@Override   
	public String getModify_user() {
		return modify_user;
	}
	@Override   
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	@Override   
	public Integer getApplay_num() {
		return applay_num;
	}
	@Override   
	public void setApplay_num(Integer applay_num) {
		this.applay_num = applay_num;
	}
	@Override   
	public Integer getReturn_num() {
		return return_num;
	}
	@Override   
	public void setReturn_num(Integer return_num) {
		this.return_num = return_num;
	}
	@Override   
	public String getReceiptdate1() {
		return receiptdate1;
	}
	@Override   
	public void setReceiptdate1(String receiptdate1) {
		this.receiptdate1 = receiptdate1;
	}
	@Override   
	public String getReturn_time1() {
		return return_time1;
	}
	@Override   
	public void setReturn_time1(String return_time1) {
		this.return_time1 = return_time1;
	}
	@Override   
	public PageCount getPageCount() {
		return pageCount;
	}
	@Override   
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	@Override
	public String getApplyuser_signname() {
		return applyuser_signname;
	}
	@Override
	public void setApplyuser_signname(String applyuser_signname) {
		this.applyuser_signname = applyuser_signname;
	}
	@Override
	public void setReturn_sysbranchname(String return_sysbranchname) {
		this.return_sysbranchname = return_sysbranchname;
	}
	@Override
	public String getRe_document_idcard() {
		return re_document_idcard;
	}
	@Override
	public void setRe_document_idcard(String re_document_idcard) {
		this.re_document_idcard = re_document_idcard;
	}
	@Override
	public String getRe_document_name() {
		return re_document_name;
	}
	@Override
	public void setRe_document_name(String re_document_name) {
		this.re_document_name = re_document_name;
	}
	@Override
	public Integer getRe_document_serial_beg() {
		return re_document_serial_beg;
	}
	@Override
	public void setRe_document_serial_beg(Integer re_document_serial_beg) {
		this.re_document_serial_beg = re_document_serial_beg;
	}
	@Override
	public Integer getRe_document_serial_end() {
		return re_document_serial_end;
	}
	@Override
	public void setRe_document_serial_end(Integer re_document_serial_end) {
		this.re_document_serial_end = re_document_serial_end;
	}
	public String getRedocument_idcard() {
		return redocument_idcard;
	}
	public void setRedocument_idcard(String redocument_idcard) {
		this.redocument_idcard = redocument_idcard;
	}
	public String getRedocument_name() {
		return redocument_name;
	}
	public void setRedocument_name(String redocument_name) {
		this.redocument_name = redocument_name;
	}
	public Integer getRedocument_serial_beg() {
		return redocument_serial_beg;
	}
	public void setRedocument_serial_beg(Integer redocument_serial_beg) {
		this.redocument_serial_beg = redocument_serial_beg;
	}
	public Integer getRedocument_serial_end() {
		return redocument_serial_end;
	}
	public void setRedocument_serial_end(Integer redocument_serial_end) {
		this.redocument_serial_end = redocument_serial_end;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCcodename() {
		return ccodename;
	}
	public void setCcodename(String ccodename) {
		this.ccodename = ccodename;
	}
	
	private String dept_list;
	public String getDept_list() {
		return dept_list;
	}
	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}
	
}
