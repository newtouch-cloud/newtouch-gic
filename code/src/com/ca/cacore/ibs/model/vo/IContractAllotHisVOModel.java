
package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2013年12月24日   
* @author    WanBo
* @description: 
*/
public interface IContractAllotHisVOModel extends IPageCount{
	public Integer getSeq_id();
	public void setSeq_id(Integer seq_id);
	public String getPolicy_id();
	public void setPolicy_id(String policy_id);
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name();
	public void setBranch_name(String branch_name);
	public String getInsBranch_id();
	public void setInsBranch_id(String insBranch_id);
	public String getInsBranch_name();
	public void setInsBranch_name(String insBranch_name);
	public String getSend_code();
	public void setSend_code(String send_code);
	public String getPolicy_code();
	public void setPolicy_code(String policy_code);
	public String getHolder_id();
	public void setHolder_id(String holder_id);
	public String getHolder_name() ;
	public void setHolder_name(String holder_name);
	public String getInsurant_id();
	public void setInsurant_id(String insurant_id) ;
	public String getInsurant_name();
	public void setInsurant_name(String insurant_name);
	public String getSend_status();
	public void setSend_status(String send_status);
	public String getPolicy_status();
	public void setPolicy_status(String policy_status);
	public String getPolicy_status_name();
	public void setPolicy_status_name(String policy_status_name);
	public Date getHold_date() ;
	public void setHold_date(Date hold_date);
	public String getAgent_id();
	public void setAgent_id(String agent_id);
	public String getAgent_name();
	public void setAgent_name(String agent_name);
	public String getService_id();
	public void setService_id(String service_id);
//	public String getService_name();
//	public void setService_name(String service_name);
	public String getBef_service_id();
	public void setBef_service_id(String bef_service_id);
	public String getAft_service_id();
	public void setAft_service_id(String aft_service_id);
	public String getBef_service_name();
	public void setBef_service_name(String bef_service_name);
	public String getAft_service_name();
	public void setAft_service_name(String aft_service_name);
	public String getAft_service_status();
	public void setAft_service_status(String aft_service_status);
	public Date getValidate_date();
	public void setValidate_date(Date validate_date);
	public String getProduct_name();
	public void setProduct_name(String product_name);
	public Date getAllot_date();
	public void setAllot_date(Date allot_date);
	public String getService_post_code();
	public void setService_post_code(String service_post_code);
	public String getProduct_id();
	public void setProduct_id(String product_id);
	public Integer getAmount();
	public void setAmount(Integer amount);
	public Integer getPeriod_prem();
	public void setPeriod_prem(Integer period_prem);
	public String getBef_policy_code();
	public void setBef_policy_code(String bef_policy_code);
	public String getBef_holder_name();
	public void setBef_holder_name(String bef_holder_name);
	public String[] getPolicy_ids();
	public void setPolicy_ids(String[] policy_ids);
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
	
	public PageCount getPageCount();
	public void setPageCount(PageCount pageCount);

	public int getStart();
	public int getLimit();
	
}
