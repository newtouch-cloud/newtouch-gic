package com.ca.cacore.manage.model.bo;

import java.util.Date;



/**
 * 2013-12-11 15:30
 * 影像基本信息Model  对应表 IMG_Image
 * @author ZhangChen
 *
 */

public class UserPortraitModel implements IUserPortraitModel  {
	private Integer seq_id; 	// 序号
	private String file_id; 	//影像件编号
	private String emp_id;  //员工代码
	private Date scan_time;		// 扫描时间
	private String file_path;	// 文件路径
	private String file_name;	// 文件名
	private String remark;		//备注
	private String createUser; 	// 创建人
	private Date createDate; 	// 创建时间
	private String modifyUser; 	// 最后修改人
	private Date modifyDate; 	// 最后时间
	
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Date getScan_time() {
		return scan_time;
	}
	public void setScan_time(Date scan_time) {
		this.scan_time = scan_time;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
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
