package com.newtouch.peoplemanage.model.po;

import com.newtouch.core.model.IPageCount;


/**
 * 高管实体类接口
 * @author liu_yn
 *
 */
public interface IGleaderInfoVOModel extends IPageCount{
	
	public String getGleader();
	public void setGleader(String gleader);
	public String getApproval_time();
	public void setApproval_time(String approval_time);
	public String getApproval_file();
	public void setApproval_file(String approval_file);
	public String getEmployment_term();
	public void setEmployment_term(String employment_term);
	public String getUpload_approval_file();
	public void setUpload_approval_file(String upload_approval_file);
	public String getPerson_no();
	public void setPerson_no(String person_no);
	public String getGvalid_time();
	public void setGvalid_time(String gvalid_time);
	public String getFilename();
	public void setFilename(String filename); 

}
