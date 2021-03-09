package com.ca.cacore.msss.model.bo;

import java.sql.Date;

/**
 * 
 * @author Wangds
 * @since 2013-12-9
 * @description PDT_Product_PeriodType  保险期限分类数据字典
 */
public class ProductPeriodTypeModel implements IProductPeriodTypeModel    {
	
	private Integer seq_id; // 主键
	private String periodType_code; // 保险期限分类代码
	private String periodType_name; // 保险期限分类名称
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
	public String getPeriodType_code() {
		return periodType_code;
	}
	public void setPeriodType_code(String periodType_code) {
		this.periodType_code = periodType_code;
	}
	public String getPeriodType_name() {
		return periodType_name;
	}
	public void setPeriodType_name(String periodType_name) {
		this.periodType_name = periodType_name;
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
