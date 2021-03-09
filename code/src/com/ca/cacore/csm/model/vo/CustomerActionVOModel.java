package com.ca.cacore.csm.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户行为查询页面信息
* 需要的表：CRM_CUSTOMER,CRM_CUSTOMER_ACTION;
 */
public class CustomerActionVOModel implements ICustomerActionVOModel {
	private Integer seq_id;//	'主键
	private String  branch_id;//	机构代码
	private String branch_name;// 机构名称
	private String  customer_id;//	客户代码
	private String 	name;//客户姓名
	private String customer_type;//客户类型
	private String gender; //客户性别
	private String  action_type;//	行为类型
	private Date   action_time;//	行为时间
	private String  action_notes;//	行为描述
	private PageCount pageCount = new PageCount();
	
	
	
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#getSeq_id()
	 */
	public Integer getSeq_id() {
		return seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#setSeq_id(java.lang.Integer)
	 */
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#getBranch_id()
	 */
	public String getBranch_id() {
		return branch_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#setBranch_id(java.lang.String)
	 */
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#getCustomer_id()
	 */
	public String getCustomer_id() {
		return customer_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#setCustomer_id(java.lang.String)
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#getName()
	 */
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#getAction_type()
	 */
	public String getAction_type() {
		return action_type;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#setAction_type(java.lang.String)
	 */
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#getAction_time()
	 */
	public Date getAction_time() {
		return action_time;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#setAction_time(java.sql.Date)
	 */
	public void setAction_time(Date action_time) {
		this.action_time = action_time;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#getAction_notes()
	 */
	public String getAction_notes() {
		return action_notes;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#setAction_notes(java.lang.String)
	 */
	public void setAction_notes(String action_notes) {
		this.action_notes = action_notes;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#getPageCount()
	 */
	public PageCount getPageCount() {
		return pageCount;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.crm.model.vo.ICustomerActionVOModel#setPageCount(com.newtouch.core.quanxianguanli.pojo.PageCount)
	 */
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
