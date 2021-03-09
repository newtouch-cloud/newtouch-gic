package com.ca.cacore.msss.model.bo;

import java.sql.Date;
/**
* @since:    2013年12月25日   
* @author    Wang_ds
* @description:PDT_Lib_Assessment_Type 产品评估类型数据字典接口
*/
public interface ILibAssessmentTypeModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getAssess_type_code() ;
	
	public void setAssess_type_code(String assess_type_code) ;
	
	public String getAssess_type_name() ;
	
	public void setAssess_type_name(String assess_type_name);
	
	public int getOrderNum();

	public void setOrderNum(int orderNum);

	public String getRemark();

	public void setRemark(String remark);

	public String getCreateUser();

	public void setCreateUser(String createUser);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getModifyUser();

	public void setModifyUser(String modifyUser);

	public Date getModifyDate();

	public void setModifyDate(Date modifyDate);

}