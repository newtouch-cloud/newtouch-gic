package com.ca.cacore.manage.model.bo;

import java.sql.Date;

/**
* @since:    2014年2月15日   
* @author    ss
* @description:自定制常用功能配置信息存储
* 功能界面配置信息 SYS_FuncPanel_Conf
*/
public class FuncPanelModel implements IFuncPanelModel{
	private Integer seq_id;//主键
	private String emp_id;//用户代码
	private Integer belong_row;//所在行
	private Integer belong_column;//所在列
	private Integer belong_zone;//所属功能区
	private String style;//样式
	private String link_url;//功能链接
	private String func_name;//功能名称
	private String is_last;//是否是行尾
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//最后修改人
	private Date modifyDate;//最后修改时间
	
	public FuncPanelModel() {
	}
	public FuncPanelModel(Integer seq_id) {
		this.seq_id = seq_id;
	}
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Integer getBelong_row() {
		return belong_row;
	}
	public void setBelong_row(Integer belong_row) {
		this.belong_row = belong_row;
	}
	public Integer getBelong_column() {
		return belong_column;
	}
	public void setBelong_column(Integer belong_column) {
		this.belong_column = belong_column;
	}
	public Integer getBelong_zone() {
		return belong_zone;
	}
	public void setBelong_zone(Integer belong_zone) {
		this.belong_zone = belong_zone;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public String getFunc_name() {
		return func_name;
	}
	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}
	public String getIs_last() {
		return is_last;
	}
	public void setIs_last(String is_last) {
		this.is_last = is_last;
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
