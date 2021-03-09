package com.ca.cacore.manage.model.bo;

import java.sql.Date;

import org.springframework.stereotype.Repository;

import com.newtouch.core.model.IPageCount;

/**
 * 部门基本信息接口类
 * 
 * @author ma_cj
 * 
 */
@Repository
public interface IDeptmentModel extends IPageCount {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getDept_parentid();

	public void setDept_parentid(String dept_parentid);

	public String getDept_id();

	public void setDept_id(String dept_id);

	public String getDept_name();

	public void setDept_name(String dept_name);

	public String getDept_abbr();

	public void setDept_abbr(String dept_abbr);

	public String getZip();

	public void setZip(String zip);

	public String getAddress();

	public void setAddress(String address);

	public String getTelephone();

	public void setTelephone(String telephone);

	public String getFax();

	public void setFax(String fax);

	public Date getFound_date();

	public void setFound_date(Date found_date);

	public Date getRecall_date();

	public void setRecall_date(Date recall_date);

	public String getStatus();

	public void setStatus(String status);

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
}
