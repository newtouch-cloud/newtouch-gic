package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class PreserveRegVOModel implements IPreserveRegVOModel{
	private Integer seq_id;//主键
	private String regulation_id;//规章编号
	private String regulation_name;//规章名称
	private String regulation_status_code;//规章状态
	private String regulation_status_name;//规章状态名称
	private String regulation_description	;//规章描述
	private String makers;//制定人
	private Date make_time;//制定时间
	private Date firstDate;//查询条件中的制定时间  自
	private Date secondDate;//查询条件中的制定时间  到
	private Date upload_time;//附件上传时间
	private String remark;//备注
	private String createuser;//创建人
	private Date createdate	;//创建时间
	private String modifyuser;//最后修改人
	private Date modifydate;//最后修改时间
	
	private Integer[] seq_ids;//规章制度的seq_id数组
	
	private String file_id;//文件上传编号
	private String file_name;//文件上传名字
	private String file_flag;//判断回显时是否隐藏删除附件按钮
	private PageCount pageCount = new PageCount();
	
	public PreserveRegVOModel(){}
	public PreserveRegVOModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getRegulation_id() {
		return regulation_id;
	}
	public void setRegulation_id(String regulation_id) {
		this.regulation_id = regulation_id;
	}
	public String getRegulation_name() {
		return regulation_name;
	}
	public void setRegulation_name(String regulation_name) {
		this.regulation_name = regulation_name;
	}
	public String getRegulation_status_code() {
		return regulation_status_code;
	}
	public void setRegulation_status_code(String regulation_status_code) {
		this.regulation_status_code = regulation_status_code;
	}
	public String getRegulation_description() {
		return regulation_description;
	}
	public void setRegulation_description(String regulation_description) {
		this.regulation_description = regulation_description;
	}
	public Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	public Date getSecondDate() {
		return secondDate;
	}
	public void setSecondDate(Date secondDate) {
		this.secondDate = secondDate;
	}
	public String getMakers() {
		return makers;
	}
	public void setMakers(String makers) {
		this.makers = makers;
	}
	public Date getMake_time() {
		return make_time;
	}
	public void setMake_time(Date make_time) {
		this.make_time = make_time;
	}
	public Date getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getRegulation_status_name() {
		return regulation_status_name;
	}
	public void setRegulation_status_name(String regulation_status_name) {
		this.regulation_status_name = regulation_status_name;
	}
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	
	public Integer[] getSeq_ids() {
		return seq_ids;
	}
	public void setSeq_ids(Integer[] seq_ids) {
		this.seq_ids = seq_ids;
	}
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getFile_flag() {
		return file_flag;
	}

	public void setFile_flag(String file_flag) {
		this.file_flag = file_flag;
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
