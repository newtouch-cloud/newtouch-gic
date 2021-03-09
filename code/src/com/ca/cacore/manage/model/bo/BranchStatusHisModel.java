package com.ca.cacore.manage.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 机构状态变更历史
 * @author newtouchlxy
 *
 */
public class BranchStatusHisModel implements IBranchStatusHisModel{
	
	private Integer seq_id;
	private String channel_id ;//渠道代码
	private String branch_id ;//机构代码
	private String bef_stat_code;//变更前状态0：有效,1：失效'
	private String aft_stat_code;//变更后状态0：有效,1：失效'
	private Date effect_date ;//变更生效时间
	private Integer approval_id ;//审批单编号
	private String process_completed ;//系统是否已处理Y已处理,N未处理'
	private String remark;//备注
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//最后修改人
	private Date modifyDate;//最后修改时间
	private PageCount pageCount =new PageCount();
	public BranchStatusHisModel(){};
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBef_stat_code() {
		return bef_stat_code;
	}
	public void setBef_stat_code(String bef_stat_code) {
		this.bef_stat_code = bef_stat_code;
	}
	public String getAft_stat_code() {
		return aft_stat_code;
	}
	public void setAft_stat_code(String aft_stat_code) {
		this.aft_stat_code = aft_stat_code;
	}
	public Date getEffect_date() {
		return effect_date;
	}
	public void setEffect_date(Date effect_date) {
		this.effect_date = effect_date;
	}
	public Integer getApproval_id() {
		return approval_id;
	}
	public void setApproval_id(Integer approval_id) {
		this.approval_id = approval_id;
	}
	public String getProcess_completed() {
		return process_completed;
	}
	public void setProcess_completed(String process_completed) {
		this.process_completed = process_completed;
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
	@Override
	public PageCount getPageCount() {
		return pageCount;
	}

	@Override
	public void setPageCount(PageCount pagination) {
		this.pageCount = pagination;
	}
	

	public int getStart() {
		return this.getPageCount().getStart();
	}

	public int getLimit() {
		return this.getPageCount().getLimit();
	}	
	
}
