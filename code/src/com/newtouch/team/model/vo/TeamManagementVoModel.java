package com.newtouch.team.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class TeamManagementVoModel implements TeamManagementVoModelImpl{
	private String branch_id;//归属机构编码 
	private String branch_name;//归属机构名称
	private String team_id;//团队编码
	private String team_name;//团队名称
	private String group_type;//团队类型
	private Date found_date;//建立日期
	private Date recall_date;//注销日期
	private String status;//团队状态
	private String team_type;//区别团队/部门
	private String deptlist;//权限
	private String modifyuser;//修改人
	private Date modifydate;//修改时间
	private String createuser;//创建人
	private Date createdate;//创建时间
	private PageCount pageCount= new PageCount();
    private double sum;
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	
	public String getGroup_type() {
		return group_type;
	}
	public void setGroup_type(String group_type) {
		this.group_type = group_type;
	}
	public Date getFound_date() {
		return found_date;
	}
	public void setFound_date(Date found_date) {
		this.found_date = found_date;
	}
	public Date getRecall_date() {
		return recall_date;
	}
	public void setRecall_date(Date recall_date) {
		this.recall_date = recall_date;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTeam_type() {
		return team_type;
	}
	public void setTeam_type(String team_type) {
		this.team_type = team_type;
	}
	public String getDeptlist() {
		return deptlist;
	}
	public void setDeptlist(String deptlist) {
		this.deptlist = deptlist;
	}
	
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
    
}
