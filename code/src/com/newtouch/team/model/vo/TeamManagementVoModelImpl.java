package com.newtouch.team.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface TeamManagementVoModelImpl extends IPageCount {
	public String getBranch_id();//归属机构编码 
	public String getBranch_name();//归属机构名称
	public String getTeam_id();//团队编码
	public String getTeam_name();//团队名称
	public String getGroup_type();//团队类型
	public Date getFound_date();//建立日期
	public Date getRecall_date();//注销日期
	public String getStatus();//团队状态
	public String getDeptlist();//权限
	public String getTeam_type();
	
	public void setBranch_id(String branch_id);
	public void setBranch_name(String branch_name);
	public void setTeam_id(String team_id);
	public void setTeam_name(String team_name);
	public void setGroup_type(String group_type);
	public void setFound_date(Date found_date);
	public void setRecall_date(Date recall_date);
	public void setStatus(String status);
	public void setDeptlist(String deptlist);
	public void setTeam_type(String Team_type);
	
	public PageCount getPageCount();
    public void setPageCount(PageCount pageCount);
    public double getSum();
	public void setSum(double getSum);
	
}
