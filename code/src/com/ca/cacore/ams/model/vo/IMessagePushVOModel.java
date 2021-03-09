package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IMessagePushVOModel extends IPageCount{
	public Integer getSeq_id();
	public void setSeq_id(Integer seq_id);
	public String getTask_type();
	public void setTask_type(String task_type);
	public String getTask_name();
	public void setTask_name(String task_name);
	public Integer getOrdernum();
	public void setOrdernum(Integer ordernum);
	public String getRemark();
	public void setRemark(String remark);
	public String getCreateuser();
	public void setCreateuser(String createuser);
	public Date getCreatedate();
	public void setCreatedate(Date createdate);
	public String getModifyuser();
	public void setModifyuser(String modifyuser);
	public Date getModifydate();
	public void setModifydate(Date modifydate);
	public String getTaskid();
	public void setTaskid(String taskid);
	public String getProce_status();
	public void setProce_status(String proce_status);
	public String getTask_title();
	public void setTask_title(String task_title);
	public Date getTask_proce_date();
	public void setTask_proce_date(Date task_proce_date);
	public String getTask_content();
	public void setTask_content(String task_content);
	public String getTask_object_id();
	public void setTask_object_id(String task_object_id);
	public String getTask_sales_id();
	public void setTask_sales_id(String task_sales_id);
	public Date getTask_failure();
	public void setTask_failure(Date task_failure);
	public PageCount getPageCount();
	public void setPageCount(PageCount pageCount);
}
