package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 
* @since:    2014年1月2日   
* @author    newtouchlxy
* @description:客户类型数据字典(CRM_Customer_Type)
 */
public class CustomerTypeModel implements ICustomerTypeModel {
	private Integer seq_id;//主键
	private String type_code;//类型代码
	private String type_name;//类型名称
	private Integer ordernum;//排序序号
	private String remark;//备注
	private String createuser;//创建人
	private Date createdate;//创建时间
	private String modifyuser;//最后修改人
	private Date modifydate;//最后修改时间
	private PageCount  pageCount = new PageCount();
	/** 
	* 
	* @return 
	* @description:
	*/
	public Integer getSeq_id() {
		return seq_id;
	}
	/** 
	* 
	* @param seq_id 
	* @description:
	*/
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getType_code() {
		return type_code;
	}
	/** 
	* 
	* @param type_code 
	* @description:
	*/
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getType_name() {
		return type_name;
	}
	/** 
	* 
	* @param type_name 
	* @description:
	*/
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Integer getOrdernum() {
		return ordernum;
	}
	/** 
	* 
	* @param ordernum 
	* @description:
	*/
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getRemark() {
		return remark;
	}
	/** 
	* 
	* @param remark 
	* @description:
	*/
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getCreateuser() {
		return createuser;
	}
	/** 
	* 
	* @param createuser 
	* @description:
	*/
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Date getCreatedate() {
		return createdate;
	}
	/** 
	* 
	* @param createdate 
	* @description:
	*/
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getModifyuser() {
		return modifyuser;
	}
	/** 
	* 
	* @param modifyuser 
	* @description:
	*/
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public Date getModifydate() {
		return modifydate;
	}
	/** 
	* 
	* @param modifydate 
	* @description:
	*/
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public PageCount getPageCount() {
		return pageCount;
	}
	/** 
	* 
	* @param pageCount 
	* @description:
	*/
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		return pageCount.getStart();
	}
	@Override
	public int getLimit() {
		return this.pageCount.getLimit();
	}
	
	
}
