package com.newtouch.peoplemanage.model.po;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 高管实体类
 * @author liu_yn
 *
 */
public class GleaderInfoVOModel implements IGleaderInfoVOModel{
	private String gleader; //是否高管
	private String approval_time; //高管批复时间
	private String approval_file; //高管批复文件
	private String employment_term; //高管聘期
	private String upload_approval_file; //上传批复文件
	private String person_no; //人员编码
	private String gvalid_time; //有效期截至
	private String filename;//文件名称
	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private PageCount pageCount = new PageCount();
	@Override
	public PageCount getPageCount() {
		// TODO Auto-generated method stub
		return pageCount;
	}
	
	@Override
	public void setPageCount(PageCount pageCount) {
		// TODO Auto-generated method stub
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
	
	
	public String getGleader() {
		return gleader;
	}
	public void setGleader(String gleader) {
		this.gleader = gleader;
	}
	public String getApproval_time() {
		return approval_time;
	}
	public void setApproval_time(String approval_time) {
		this.approval_time = approval_time;
	}
	public String getApproval_file() {
		return approval_file;
	}
	public void setApproval_file(String approval_file) {
		this.approval_file = approval_file;
	}
	public String getEmployment_term() {
		return employment_term;
	}
	public void setEmployment_term(String employment_term) {
		this.employment_term = employment_term;
	}
	public String getUpload_approval_file() {
		return upload_approval_file;
	}
	public void setUpload_approval_file(String upload_approval_file) {
		this.upload_approval_file = upload_approval_file;
	}
	public String getPerson_no() {
		return person_no;
	}
	public void setPerson_no(String person_no) {
		this.person_no = person_no;
	}
	public String getGvalid_time() {
		return gvalid_time;
	}

	public void setGvalid_time(String gvalid_time) {
		this.gvalid_time = gvalid_time;
	}

}
