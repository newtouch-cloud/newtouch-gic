package com.ca.cacore.ams.model.bo;

import java.sql.Date;

public class RegStatusTypeModel {
	private Integer	seq_id;//	主键
	private String	regulation_status_code;//	规章状态代码
	private String	regulation_status_name;//	规章状态名称
	private String	ordernum;//	排序序号
	private String	remark;//	备注
	private String	createuser;//	创建人
	private Date createdate;//	创建时间
	private String	modifyuser;//	最后修改人
	private Date modifydate;//	最后修改时间
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getRegulation_status_code() {
		return regulation_status_code;
	}
	public void setRegulation_status_code(String regulation_status_code) {
		this.regulation_status_code = regulation_status_code;
	}
	public String getRegulation_status_name() {
		return regulation_status_name;
	}
	public void setRegulation_status_name(String regulation_status_name) {
		this.regulation_status_name = regulation_status_name;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
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
