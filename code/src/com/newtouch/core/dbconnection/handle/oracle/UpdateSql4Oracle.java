package com.newtouch.core.dbconnection.handle.oracle;

import java.util.ArrayList;
import java.util.List;

import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.dbconnection.handle.util.ReplaceUtil;

public class UpdateSql4Oracle implements UpdateSqlable {

	private String sql = "";
	private Object[] params;
	private List<Object> param = new ArrayList<Object>();

	public UpdateSql4Oracle(String sql, Object[] params) {
		this.setSql(sql);
		this.setParameter(params);
	}

	public UpdateSql4Oracle() {

	}

	public String getPrintSql() {
		String printSql = this.sql;
		if (this.params == null || params.length == 0) {
			params = param.toArray();
		}
		return ReplaceUtil.replaceSql(params, printSql);
	}

	public String getSql() {
		return sql;
	}

	public void setParameter(Object[] paramList) {
		this.checkParam(paramList);
		this.params = paramList;
	}

	public Object[] getParamList() {
		if (this.params == null || params.length == 0) {
			this.params = param.toArray();
		}
		return this.params;
	}

	public void setSql(String sql) {
		// params = null;
		// param.clear();
		this.checkSql(sql);
		this.sql = this.replaceSpace(sql);
	}

	private void checkParam(Object[] paramList) {
		if (paramList == null) {
			throw new RuntimeException("参数不能为空。");
		}
	}

	private void checkSql(String sql) {
		if (sql == null || sql.equals("")) {
			throw new RuntimeException("Sql不能空。");
		}
		if (sql.contains("'")) {
			throw new RuntimeException("Sql语句包含非法字符。");
		}
	}

	private String replaceSpace(String string) {
		while (string.indexOf("\t") >= 0) {
			string = string.replaceAll("\t", " ");
		}
		while (string.indexOf("\n") >= 0) {
			string = string.replaceAll("\n", " ");
		}
		while (string.indexOf("\r") >= 0) {
			string = string.replaceAll("\r", " ");
		}
		while (string.indexOf("  ") >= 0) {
			string = string.replaceAll("  ", " ");
		}
		return string;
	}

	public void add(Object para) {
		this.param.add(para);
	}

	public void clearParam() {
		this.param.clear();
		this.params = null;
	}
}
