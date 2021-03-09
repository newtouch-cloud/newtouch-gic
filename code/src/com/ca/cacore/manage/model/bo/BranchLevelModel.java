package com.ca.cacore.manage.model.bo;

import java.sql.Date;
/**
 * 机构级别信息
 * @author admin
 */
public class BranchLevelModel {
	private Integer seq_id		;        //主键		  
	private String	branch_level_code;	//机构级别代码
	private String  branch_level_name;//机构级别名称
	private String 	branch_parent_level;//上级机构级别
	private String remark		;        //备注		             
	private String createUser	;	     //创建人	             
	private Date createDate	;        	//创建时间	             
	private String modifyUser	;        	//最后修改人	             
	private Date modifyDate	;         //最后修改时间	   
	
	public BranchLevelModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}
	public BranchLevelModel(){}
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getBranch_level_code() {
		return branch_level_code;
	}
	public void setBranch_level_code(String branch_level_code) {
		this.branch_level_code = branch_level_code;
	}
	public String getBranch_level_name() {
		return branch_level_name;
	}
	public void setBranch_level_name(String branch_level_name) {
		this.branch_level_name = branch_level_name;
	}
	public String getBranch_parent_level() {
		return branch_parent_level;
	}
	public void setBranch_parent_level(String branch_parent_level) {
		this.branch_parent_level = branch_parent_level;
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
	

}
