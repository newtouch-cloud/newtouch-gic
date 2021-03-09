package com.ca.cacore.msss.model.bo;

import java.sql.Date;

/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description PDT_Product_Type3 PDT_Lib_CoveragePeriod  保障期限类型数据字典
 */
public class LibCoveragePeriodModel implements ILibCoveragePeriodModel   {
	
	private Integer seq_id; // 主键
	private String coverage_period_code; // 产品类型3代码
	private String coverage_period_name; // 产品类型3名称
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
	public String getCoverage_period_code() {
		return coverage_period_code;
	}
	public void setCoverage_period_code(String coverage_period_code) {
		this.coverage_period_code = coverage_period_code;
	}
	public String getCoverage_period_name() {
		return coverage_period_name;
	}
	public void setCoverage_period_name(String coverage_period_name) {
		this.coverage_period_name = coverage_period_name;
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
