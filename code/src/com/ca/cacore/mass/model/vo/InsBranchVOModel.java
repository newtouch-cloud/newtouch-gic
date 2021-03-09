package com.ca.cacore.mass.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 保险公司机构model
 * @author guochunhua
 */
public class InsBranchVOModel implements IInsBranchVOModel{
	private String seq_id;//主键
	private String insBranch_parentId;//上级机构代码
	private String bef_insBranch_id;//变更前机构代码
	private String insBranch_id;//机构代码
	private String insBranch_name;//机构名称
	private String insBranch_abbr;//机构简称
	private String insBranch_level;//机构级别
	private String insBranch_allPath;//机构路径
	private String last_insBranch_allpath;//上级机构路径
	private String delegate;//法人代表
	private String address;//联系地址
	private String zip;//邮政编码
	private String telephone;//电话号码
	private String fax;//机构传真
	private String email;//电子邮件
	private Date found_date;//建立日期
	private Date recall_date;//撤销日期
	private String status;//状态
	private String bef_status;//变更前状态
	private String aft_status;//变更后状态
	private String status_name;
	private String remark;//备注
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//最后修改人
	private Date modifyDate;//最后修改时间
	private String bank_name;//开户行
	private String bank_account_no;//银行账号
	private String modifyFlag;//新增成功的标志
	
	private Integer count;
	private PageCount pageCount = new PageCount();
	
	public String getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}
	public String getInsBranch_parentId() {
		return insBranch_parentId;
	}
	public void setInsBranch_parentId(String insBranch_parentId) {
		this.insBranch_parentId = insBranch_parentId;
	}
	public String getBef_insBranch_id() {
		return bef_insBranch_id;
	}
	public void setBef_insBranch_id(String bef_insBranch_id) {
		this.bef_insBranch_id = bef_insBranch_id;
	}
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public String getInsBranch_name() {
		return insBranch_name;
	}
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}
	public String getInsBranch_abbr() {
		return insBranch_abbr;
	}
	public void setInsBranch_abbr(String insBranch_abbr) {
		this.insBranch_abbr = insBranch_abbr;
	}
	public String getInsBranch_level() {
		return insBranch_level;
	}
	public void setInsBranch_level(String insBranch_level) {
		this.insBranch_level = insBranch_level;
	}
	public String getInsBranch_allPath() {
		return insBranch_allPath;
	}
	public void setInsBranch_allPath(String insBranch_allPath) {
		this.insBranch_allPath = insBranch_allPath;
	}
	public String getLast_insBranch_allpath() {
		return last_insBranch_allpath;
	}
	public void setLast_insBranch_allpath(String last_insBranch_allpath) {
		this.last_insBranch_allpath = last_insBranch_allpath;
	}
	public String getDelegate() {
		return delegate;
	}
	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFound_date() {
		return found_date;
	}
	public void setFound_date(Date found_date) {
		this.found_date = found_date;
	}
	public Date getRecall_date() {
		return recall_date;
	}
	public void setRecall_date(Date recall_date) {
		this.recall_date = recall_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBef_status() {
		return bef_status;
	}
	public void setBef_status(String bef_status) {
		this.bef_status = bef_status;
	}
	public String getAft_status() {
		return aft_status;
	}
	public void setAft_status(String aft_status) {
		this.aft_status = aft_status;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_account_no() {
		return bank_account_no;
	}
	public void setBank_account_no(String bank_account_no) {
		this.bank_account_no = bank_account_no;
	}
	
	
	
	public String getModifyFlag() {
		return modifyFlag;
	}
	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	public int getStart() {
		return this.pageCount.getStart();
	}
	public int getLimit() {
		return this.pageCount.getLimit();
	}
}
