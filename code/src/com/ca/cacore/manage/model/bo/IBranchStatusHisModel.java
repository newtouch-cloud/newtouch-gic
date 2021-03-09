package com.ca.cacore.manage.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IBranchStatusHisModel extends IPageCount {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getChannel_id();

	public void setChannel_id(String channel_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBef_stat_code();

	public void setBef_stat_code(String bef_stat_code);

	public String getAft_stat_code();

	public void setAft_stat_code(String aft_stat_code);

	public Date getEffect_date();

	public void setEffect_date(Date effect_date);

	public Integer getApproval_id();

	public void setApproval_id(Integer approval_id);

	public String getProcess_completed();

	public void setProcess_completed(String process_completed);

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