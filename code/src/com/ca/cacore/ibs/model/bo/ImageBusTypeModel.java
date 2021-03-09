package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

/**
 * 
* @since:    2014年1月2日   
* @author    newtouchlxy
* @description:影像件类型数据字典(IMG_Image_BusType)
 */
public class ImageBusTypeModel  {
	private Integer seq_id;//主键
	private String img_type_code;//类型代码
	private String img_type_name;//类型名称
	private Integer ordernum;//排序序号
	private String remark;//备注
	private String createuser;//创建人
	private Date createdate;//创建时间
	private String modifyuser;//最后修改人
	private Date modifydate;//最后修改时间
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getImg_type_code() {
		return img_type_code;
	}
	public void setImg_type_code(String img_type_code) {
		this.img_type_code = img_type_code;
	}
	public String getImg_type_name() {
		return img_type_name;
	}
	public void setImg_type_name(String img_type_name) {
		this.img_type_name = img_type_name;
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
	
	
}
