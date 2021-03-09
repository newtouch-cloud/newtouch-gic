package com.ca.cacore.msss.model.bo;

import java.sql.Date;

/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description PDT_Lib_ChargePeriod  缴费期限类型数据字典
 */
public class LibChargePeriodModel implements ILibChargePeriodModel    {
	
	private Integer seq_id; // 主键
	private String charge_period_code; //  缴费期限类型代码
	private String charge_period_name; //  缴费期限类型名称
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
	public String getCharge_period_code() {
		return charge_period_code;
	}
	public void setCharge_period_code(String charge_period_code) {
		this.charge_period_code = charge_period_code;
	}
	public String getCharge_period_name() {
		return charge_period_name;
	}
	public void setCharge_period_name(String charge_period_name) {
		this.charge_period_name = charge_period_name;
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
