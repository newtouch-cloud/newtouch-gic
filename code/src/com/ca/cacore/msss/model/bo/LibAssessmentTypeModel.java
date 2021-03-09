package com.ca.cacore.msss.model.bo;

import java.sql.Date;

/**
* @since:    2013年12月25日   
* @author    Wang_ds
* @description:PDT_Lib_Assessment_Type 产品评估类型数据字典
*/
public class LibAssessmentTypeModel implements ILibAssessmentTypeModel  {

	private Integer seq_id; // 主键
	private String assess_type_code; //  评估类型代码代码
	private String assess_type_name; //  评估类型代码名称
	private int orderNum; // 排序序号
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	public LibAssessmentTypeModel() {
		super();
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	
	public String getAssess_type_code() {
		return assess_type_code;
	}
	public void setAssess_type_code(String assess_type_code) {
		this.assess_type_code = assess_type_code;
	}
	public String getAssess_type_name() {
		return assess_type_name;
	}
	public void setAssess_type_name(String assess_type_name) {
		this.assess_type_name = assess_type_name;
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
