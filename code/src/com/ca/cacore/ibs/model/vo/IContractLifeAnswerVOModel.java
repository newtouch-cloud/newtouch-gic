package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IContractLifeAnswerVOModel extends IPageCount{

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);
	
	public String getPolicy_code();
	
	public void setPolicy_code(String policy_code) ;

	public abstract String getAnswer_type();

	public abstract void setAnswer_type(String answer_type);

	public abstract String getIs_success();

	public abstract void setIs_success(String is_success);

	public abstract String getAnswer_notes();

	public abstract void setAnswer_notes(String answer_notes);

	public abstract Date getVisit_date();

	public abstract void setVisit_date(Date visit_date);

	public abstract String getRemark();

	public abstract void setRemark(String remark);

	public abstract String getCreateUser();

	public abstract void setCreateUser(String createUser);

	public abstract Date getCreateDate();

	public abstract void setCreateDate(Date createDate);

	public abstract String getModifyUser();

	public abstract void setModifyUser(String modifyUser);

	public abstract Date getModifyDate();

	public abstract void setModifyDate(Date modifyDate);

	public abstract String getBranch_name();

	public abstract void setBranch_name(String branch_name);

	public abstract String getApp_name();

	public abstract void setApp_name(String app_name);

	public abstract PageCount getPageCount();

	public abstract void setPageCount(PageCount pageCount);

}