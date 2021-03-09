package com.ca.cacore.rsss.model.bo;

import java.sql.Date;

public interface IStatisticMonthModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getStatistic_month_code();
	
	public void setStatistic_month_code(String statistic_month_code);
	
	public String getStatistic_month_name();
	
	public void setStatistic_month_name(String statistic_month_name);

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