package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户联系信息
 */
public class CustomerContactModel implements ICustomerContactModel {
	 private Integer seq_id;//		主键
	 private String branch_id;//	机构代码;
	 private String branch_name;// 机构名称
	 private String customer_id;//	客户代码;
	 private String address;//	家庭地址;
	 private String zip;//	邮政编码;
	 private String mobile;//	移动电话;
	 private String fax;//	传真电话;
	 private String telphone;//	住宅电话;
	 private String email;//	电子邮箱;
	 private String job_com;//	工作单位名称;
	 private String job_tel ;//	办公电话;
	 private String status;//	状态
	 private String remark;//	备注;
	 private Integer log_seq_id;//	备份数据主键;
	 private String log_bustype;//	备份业务类型;
	 private Date log_busdate;//	备份业务归属时间;
	 private Date log_date;//	备份时间;
	 private String log_remark;//	备份备注;
	 private String createuser;//	创建人;
	 private Date createdate;//	创建时间;
	 private String modifyuser;//	最后修改人;
	 private Date modifydate;//	最后修改时间;
	 private PageCount pageCount= new PageCount();
	 private String corporation_contact_person;   //联系人
	 private String corporation_contact_mobile;   //联系电话
	 private String corporation_contact_qq;       // qq(联系人)
	 private String corporation_contact_wechat;   // 微信(联系人)	
	 
	 	private String file_id;//附件id
		public String getFile_id() {
			return file_id;
		}
		public void setFile_id(String file_id) {
			this.file_id = file_id;
		}
		public String getFile_name() {
			return file_name;
		}
		public void setFile_name(String file_name) {
			this.file_name = file_name;
		}
		public String getFile_flag() {
			return file_flag;
		}
		public void setFile_flag(String file_flag) {
			this.file_flag = file_flag;
		}
		private String file_name;//附件名称
		private String file_flag;//附件标识
		private String attachment_type;
	 
	 public String getAttachment_type() {
			return attachment_type;
		}
		public void setAttachment_type(String attachment_type) {
			this.attachment_type = attachment_type;
		}
	public CustomerContactModel(){};
	 public CustomerContactModel(Integer seq_id){
		 this.seq_id = seq_id;
	 }
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getSeq_id()
	 */
	public Integer getSeq_id() {
		return seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setSeq_id(java.lang.Integer)
	 */
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getBranch_id()
	 */
	public String getBranch_id() {
		return branch_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setBranch_id(java.lang.String)
	 */
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getCustomer_id()
	 */
	public String getCustomer_id() {
		return customer_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setCustomer_id(java.lang.String)
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getAddress()
	 */
	public String getAddress() {
		return address;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setAddress(java.lang.String)
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getZip()
	 */
	public String getZip() {
		return zip;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setZip(java.lang.String)
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getMobile()
	 */
	public String getMobile() {
		return mobile;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setMobile(java.lang.String)
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getFax()
	 */
	public String getFax() {
		return fax;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setFax(java.lang.String)
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getTelphone()
	 */
	public String getTelphone() {
		return telphone;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setTelphone(java.lang.String)
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getEmail()
	 */
	public String getEmail() {
		return email;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setEmail(java.lang.String)
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getJob_com()
	 */
	public String getJob_com() {
		return job_com;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setJob_com(java.lang.String)
	 */
	public void setJob_com(String job_com) {
		this.job_com = job_com;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getJob_tel()
	 */
	public String getJob_tel() {
		return job_tel;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setJob_tel(java.lang.String)
	 */
	public void setJob_tel(String job_tel) {
		this.job_tel = job_tel;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getStatus()
	 */
	public String getStatus() {
		return status;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setStatus(java.lang.String)
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getRemark()
	 */
	public String getRemark() {
		return remark;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setRemark(java.lang.String)
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getLog_seq_id()
	 */
	public Integer getLog_seq_id() {
		return log_seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setLog_seq_id(java.lang.Integer)
	 */
	public void setLog_seq_id(Integer log_seq_id) {
		this.log_seq_id = log_seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getLog_bustype()
	 */
	public String getLog_bustype() {
		return log_bustype;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setLog_bustype(java.lang.String)
	 */
	public void setLog_bustype(String log_bustype) {
		this.log_bustype = log_bustype;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getLog_busdate()
	 */
	public Date getLog_busdate() {
		return log_busdate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setLog_busdate(java.sql.Date)
	 */
	public void setLog_busdate(Date log_busdate) {
		this.log_busdate = log_busdate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getLog_date()
	 */
	public Date getLog_date() {
		return log_date;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setLog_date(java.sql.Date)
	 */
	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getLog_remark()
	 */
	public String getLog_remark() {
		return log_remark;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setLog_remark(java.lang.String)
	 */
	public void setLog_remark(String log_remark) {
		this.log_remark = log_remark;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getCreateuser()
	 */
	public String getCreateuser() {
		return createuser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setCreateuser(java.lang.String)
	 */
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getCreatedate()
	 */
	public Date getCreatedate() {
		return createdate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setCreatedate(java.sql.Date)
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getModifyuser()
	 */
	public String getModifyuser() {
		return modifyuser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setModifyuser(java.lang.String)
	 */
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getModifydate()
	 */
	public Date getModifydate() {
		return modifydate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setModifydate(java.sql.Date)
	 */
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#getPageCount()
	 */
	public PageCount getPageCount() {
		return pageCount;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.bo.ICustomerContactModel#setPageCount(com.newtouch.core.quanxianguanli.pojo.PageCount)
	 */
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
	
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	};
	 
	public String getCorporation_contact_person() {
		return corporation_contact_person;
	}
	public void setCorporation_contact_person(String corporation_contact_person) {
		this.corporation_contact_person = corporation_contact_person;
	}
	public String getCorporation_contact_mobile() {
		return corporation_contact_mobile;
	}
	public void setCorporation_contact_mobile(String corporation_contact_mobile) {
		this.corporation_contact_mobile = corporation_contact_mobile;
	}
	public String getCorporation_contact_qq() {
		return corporation_contact_qq;
	}
	public void setCorporation_contact_qq(String corporation_contact_qq) {
		this.corporation_contact_qq = corporation_contact_qq;
	}
	public String getCorporation_contact_wechat() {
		return corporation_contact_wechat;
	}
	public void setCorporation_contact_wechat(String corporation_contact_wechat) {
		this.corporation_contact_wechat = corporation_contact_wechat;
	}
	 
	 
}
