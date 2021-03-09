package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;


/**
 * 
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户接触信息
 */

public class CustomerJieChuModel implements ICustomerJieChuModel {

	private Integer   seq_id;//	主键
	private String    branch_id;//	机构代码
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
	private String    customer_id;//	客户代码
	private String    action_type;//	接触类型
	private Date   action_time;//	接触时间
	private String    action_notes;//	接触描述
	private String    remark;//	备注
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public Date getAction_time() {
		return action_time;
	}
	public void setAction_time(Date action_time) {
		this.action_time = action_time;
	}
	public String getAction_notes() {
		return action_notes;
	}
	public void setAction_notes(String action_notes) {
		this.action_notes = action_notes;
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
	private String 	   createuser;//	创建人
	private Date    createdate;//	创建时间
	private String 	   modifyuser;//	最后修改人
	private Date    modifydate;//	最后修改时间
	private PageCount pageCount = new PageCount();
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
