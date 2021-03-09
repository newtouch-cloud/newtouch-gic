package com.ca.cacore.ams.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IPreserveRegVOModel extends IPageCount{

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getRegulation_id();

	public abstract void setRegulation_id(String regulation_id);

	public abstract String getRegulation_name();

	public abstract void setRegulation_name(String regulation_name);

	public abstract String getRegulation_status_code();

	public abstract void setRegulation_status_code(String regulation_status_code);

	public abstract String getRegulation_description();

	public abstract void setRegulation_description(String regulation_description);

	public abstract String getMakers();

	public abstract void setMakers(String makers);

	public abstract Date getMake_time();

	public abstract void setMake_time(Date make_time);

	public abstract Date getUpload_time();

	public abstract void setUpload_time(Date upload_time);

	public abstract String getRemark();

	public abstract void setRemark(String remark);

	public abstract String getCreateuser();

	public abstract void setCreateuser(String createuser);

	public Date getFirstDate();
	
	public void setFirstDate(Date firstDate);
	
	public Date getSecondDate();
	
	public void setSecondDate(Date secondDate);
	
	public abstract Date getCreatedate();

	public abstract void setCreatedate(Date createdate);

	public abstract String getModifyuser();

	public abstract void setModifyuser(String modifyuser);

	public abstract Date getModifydate();

	public abstract void setModifydate(Date modifydate);

	public abstract String getRegulation_status_name();

	public abstract void setRegulation_status_name(String regulation_status_name);

	public PageCount getPageCount();
	
	public void setPageCount(PageCount pageCount);
	
	public String getFile_name();

	public void setFile_name(String file_name);

	public String getFile_id();

	public void setFile_id(String file_id);
	
	public Integer[] getSeq_ids();

	public void setSeq_ids(Integer[] seq_ids);
	
	public String getFile_flag();

	public void setFile_flag(String file_flag);

}