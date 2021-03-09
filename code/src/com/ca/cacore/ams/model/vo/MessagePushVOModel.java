package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class MessagePushVOModel implements IMessagePushVOModel{
	private Integer seq_id; //主键
	private String task_type; //类型代码
	private String task_name; //类型名称
	private Integer ordernum; //排序序号
	private String remark; //备注
	private String createuser; //创建人
	private Date createdate; //创建时间
	private String modifyuser; //最后修改人
	private Date modifydate; //最后修改时间
	private String taskid; //任务id
	private String proce_status; //任务状态
	private String task_title; //任务标题
	private Date task_proce_date; //任务提醒开始日期
	private String task_content; //任务内容
	private String task_object_id; //任务内容相关号码
	private String task_sales_id; //任务接收人
	private Date task_failure; //任务提醒失效时间
	private PageCount pageCount = new PageCount();
	
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
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
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getProce_status() {
		return proce_status;
	}
	public void setProce_status(String proce_status) {
		this.proce_status = proce_status;
	}
	public String getTask_title() {
		return task_title;
	}
	public void setTask_title(String task_title) {
		this.task_title = task_title;
	}
	public Date getTask_proce_date() {
		return task_proce_date;
	}
	public void setTask_proce_date(Date task_proce_date) {
		this.task_proce_date = task_proce_date;
	}
	public String getTask_content() {
		return task_content;
	}
	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}
	public String getTask_object_id() {
		return task_object_id;
	}
	public void setTask_object_id(String task_object_id) {
		this.task_object_id = task_object_id;
	}
	public String getTask_sales_id() {
		return task_sales_id;
	}
	public void setTask_sales_id(String task_sales_id) {
		this.task_sales_id = task_sales_id;
	}
	public Date getTask_failure() {
		return task_failure;
	}
	public void setTask_failure(Date task_failure) {
		this.task_failure = task_failure;
	}
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
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
