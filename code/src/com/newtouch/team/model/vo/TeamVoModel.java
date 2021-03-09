package com.newtouch.team.model.vo;
import java.sql.Date;
public class TeamVoModel {
	    //序号
		private String branch_id;//归属机构编码 
		private String team_id;//团队编码
		private String team_name;//团队名称
		private String group_type;//团队类型
		private Date found_date;//建立日期
		private Date recall_date;//注销日期
		private String status;
		public String getBranch_id() {
			return branch_id;
		}
		public void setBranch_id(String branch_id) {
			this.branch_id = branch_id;
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
		
		
		
}
