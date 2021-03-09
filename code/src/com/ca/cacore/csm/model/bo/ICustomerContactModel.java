package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ICustomerContactModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);
	
	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public String getCustomer_id();

	public void setCustomer_id(String customer_id);

	public String getAddress();

	public void setAddress(String address);

	public String getZip();

	public void setZip(String zip);

	public String getMobile();

	public void setMobile(String mobile);

	public String getFax();

	public void setFax(String fax);

	public String getTelphone();

	public void setTelphone(String telphone);

	public String getEmail();

	public void setEmail(String email);

	public String getJob_com();

	public void setJob_com(String job_com);

	public String getJob_tel();

	public void setJob_tel(String job_tel);

	public String getStatus();

	public void setStatus(String status);

	public String getRemark();

	public void setRemark(String remark);

	public Integer getLog_seq_id();

	public void setLog_seq_id(Integer log_seq_id);

	public String getLog_bustype();

	public void setLog_bustype(String log_bustype);

	public Date getLog_busdate();

	public void setLog_busdate(Date log_busdate);

	public Date getLog_date();

	public void setLog_date(Date log_date);

	public String getLog_remark();

	public void setLog_remark(String log_remark);

	public String getCreateuser();

	public void setCreateuser(String createuser);

	public Date getCreatedate();

	public void setCreatedate(Date createdate);

	public String getModifyuser();

	public void setModifyuser(String modifyuser);

	public Date getModifydate();

	public void setModifydate(Date modifydate);

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);
	
	public String getFile_id();//附件id
	
	public void setFile_id(String file_id);
	
	public String getFile_name();//附件名称
	
	public void setFile_name(String file_name);
	
	public String getFile_flag();  //附件标示
	
	public void setFile_flag(String file_flag);
	
	public String getAttachment_type();
	
	public void setAttachment_type(String attachment_type);
	public String getCorporation_contact_person();
	public void setCorporation_contact_person(String corporation_contact_person);
	public String getCorporation_contact_mobile();
	public void setCorporation_contact_mobile(String corporation_contact_mobile);
	public String getCorporation_contact_qq();
	public void setCorporation_contact_qq(String corporation_contact_qq);
	public String getCorporation_contact_wechat();
	public void setCorporation_contact_wechat(String corporation_contact_wechat);
}