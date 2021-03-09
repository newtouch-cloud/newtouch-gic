package com.ca.cacore.msss.model.bo;

import java.sql.Date;

/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description PDT_Lib_ChargeType  缴费方式数据字典
 */
public class LibChargeTypeModel implements ILibChargeTypeModel   {
	
	private Integer seq_id; // 主键
	private String charge_type_code; // 缴费方式代码
	private String charge_type_name; // 缴费方式名称
	private int orderNum; // 排序序号
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getCharge_type_code() {
		return charge_type_code;
	}
	public void setCharge_type_code(String charge_type_code) {
		this.charge_type_code = charge_type_code;
	}
	public String getCharge_type_name() {
		return charge_type_name;
	}
	public void setCharge_type_name(String charge_type_name) {
		this.charge_type_name = charge_type_name;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	
	
}
