package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

/**
 * 
* @since:    2014年1月10日   
* @author    ZhangChen
* @description:费用处理状态字典model  from CBS_FEE_STATUS
 */
public class FeeStatusModel  {
	private Integer seq_id;//主键
	private String status_code;//类型代码
	private String status_name;//类型名称
	private Integer ordernum;//排序序号
	private String remark;//备注
	private String createuser;//创建人
	private Date createdate;//创建时间
	private String modifyuser;//最后修改人
	private Date modifydate;//最后修改时间
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
	

	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
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

	
	
}
