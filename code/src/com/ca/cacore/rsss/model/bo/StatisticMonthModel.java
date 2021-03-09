package com.ca.cacore.rsss.model.bo;

import java.sql.Date;

/**
 * 
 * @author fqy
 * @since 2014-1-21
 * @description 统计年份数据字典
 */
public class StatisticMonthModel implements IStatisticMonthModel  {
	
	private Integer seq_id; // 主键
	private String statistic_month_code; // 统计季度代码
	private String statistic_month_name; // 统计季度名称
	private  int orderNum; // 排序序号
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
	
	public String getStatistic_month_code() {
		return statistic_month_code;
	}
	public void setStatistic_month_code(String statistic_month_code) {
		this.statistic_month_code = statistic_month_code;
	}
	public String getStatistic_month_name() {
		return statistic_month_name;
	}
	public void setStatistic_month_name(String statistic_month_name) {
		this.statistic_month_name = statistic_month_name;
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
