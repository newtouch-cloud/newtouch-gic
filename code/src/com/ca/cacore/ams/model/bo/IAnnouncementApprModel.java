package com.ca.cacore.ams.model.bo;

import java.sql.Date;

public interface IAnnouncementApprModel {
	public Integer getSeq_id();//主键
	public void setSeq_id(Integer seq_id);
	public String getBranch_id();//通告机构
	public void setBranch_id(String branch_id);
	public String getAnnouncement_id();//公告编号
	public void setAnnouncement_id(String announcement_id);
	public Integer getApproval_id();//审批单编号
	public void setApproval_id(Integer approval_Id);
	public String getApplicant();//申请人
	public void setApplicant(String applicant);
	public Date getApplication_time();//申请时间
	public void setApplication_time(Date application_Time);
	public String getApproval();//审批人
	public void setApproval(String approval);
	public Date getApproval_time();//审批时间
	public void setApproval_time(Date approval_Time);
	public String getApproval_status();//审批状态
	public void setApproval_status(String approval_Status);
	public String getRemark();//备注
	public void setRemark(String remark);
	public String getCreateUser();//创建人
	public void setCreateUser(String createUser);
	public Date getCreateDate();//创建时间
	public void setCreateDate(Date createDate);
	public String getModifyUser();//最后修改人
	public void setModifyUser(String modifyUser);
	public Date getModifyDate();//最后修改时间
	public void setModifyDate(Date modifyDate);

}
