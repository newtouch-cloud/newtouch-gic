package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 
* @since:    2013年12月31日   
* @author    newtouchlxy
* @description:客户收入范围数据字典(CRM_Customer_IncomType)
 */
public class CustomerIncomTypeModel implements ICustomerIncomTypeModel {
	private Integer	seq_id;//	主键
	private String incomtype_code;//收入范围代码
	private String incomtype_name;//收入范围名称
	private Integer ordernum;//排序序号
	private String remark;//备注
	private String createuser;//创建人
	private Date createdate;//创建时间
	private String modifyuser;//最后修改人
	private Date modifydate;//最后修改时间
	PageCount pageCount = new PageCount();
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
	public String getIncomtype_code() {
		return incomtype_code;
	}
	/** 
	* 
	* @param incomtype_code 
	* @description:
	*/
	public void setIncomtype_code(String incomtype_code) {
		this.incomtype_code = incomtype_code;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	public String getIncomtype_name() {
		return incomtype_name;
	}
	/** 
	* 
	* @param incomtype_name 
	* @description:
	*/
	public void setIncomtype_name(String incomtype_name) {
		this.incomtype_name = incomtype_name;
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
	@Override
	public PageCount getPageCount() {
		return pageCount;
	}
	@Override
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		return this.pageCount.getStart();
	}
	@Override
	public int getLimit() {
		return this.pageCount.getLimit();
	}
	
	
}
