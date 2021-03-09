package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IContractLifeAnswerModel extends IPageCount{

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);
	
	public String getPolicy_code();

	public void setPolicy_code(String policy_code);

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

}