package com.ca.cacore.manage.model.vo;

/**
* @since:    2014年2月18日   
* @author    ss
* @description:封装弹出式搜索的参数
*/
public class SearchParamsModel implements ISearchParamsModel {
	private String emp_id;//操作人代码
	private String keyWord;//搜索关键字
	private String table_name;//数据来源的表名
	private String t_name;//要查询的字段名
	private String reg;//匹配规则
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public SearchParamsModel() {
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
