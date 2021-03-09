package com.ca.cacore.mass.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IInsBranchVOModel extends IPageCount{
	public String getSeq_id();
	
	public void setSeq_id(String seq_id);
	
	public String getInsBranch_parentId();
	
	public void setInsBranch_parentId(String insBranch_parentId);
	
	public String getBef_insBranch_id();
	
	public void setBef_insBranch_id(String bef_insBranch_id);
	
	public String getInsBranch_id();
	
	public void setInsBranch_id(String insBranch_id);
	
	public String getInsBranch_name();
	
	public void setInsBranch_name(String insBranch_name);
	
	public String getInsBranch_abbr();
	
	public void setInsBranch_abbr(String insBranch_abbr);
	
	public String getInsBranch_level();
	
	public void setInsBranch_level(String insBranch_level);
	
	public String getInsBranch_allPath();
	
	public void setInsBranch_allPath(String insBranch_allPath);
	
	public String getLast_insBranch_allpath();
	
	public void setLast_insBranch_allpath(String last_insBranch_allpath);
	
	public String getDelegate();
	
	public void setDelegate(String delegate);
	
	public String getAddress();
	
	public void setAddress(String address);
	
	public String getZip();
	
	public void setZip(String zip);
	
	public String getTelephone();
	
	public void setTelephone(String telephone);
	
	public String getFax();
	
	public void setFax(String fax);
	
	public String getEmail();
	
	public void setEmail(String email);
	
	public Date getFound_date();
	
	public void setFound_date(Date found_date);
	
	public Date getRecall_date();
	
	public void setRecall_date(Date recall_date);
	
	public String getStatus();
	
	public void setStatus(String status);
	
	public String getBef_status();
	
	public void setBef_status(String bef_status);
	
	public String getAft_status();
	
	public void setAft_status(String aft_status);
	
	public String getStatus_name();
	
	public void setStatus_name(String status_name);
	
	public String getRemark();
	
	public void setRemark(String remark);
	
	public String getCreateUser();
	
	public void setCreateUser(String createUser);
	
	public Date getCreateDate();
	
	public void setCreateDate(Date createDate);
	
	public String getModifyUser();
	
	public void setModifyUser(String modifyUser);
	
	public Date getModifyDate();
	
	public void setModifyDate(Date modifyDate);
	
	public String getBank_name();
	public void setBank_name(String bank_name);
	
	public String getBank_account_no();
	public void setBank_account_no(String bank_account_no);
	
	public String getModifyFlag();
	public void setModifyFlag(String modifyFlag);
	
	public Integer getCount();
	public void setCount(Integer count);
	
	public PageCount getPageCount();
	public void setPageCount(PageCount pageCount);
}
