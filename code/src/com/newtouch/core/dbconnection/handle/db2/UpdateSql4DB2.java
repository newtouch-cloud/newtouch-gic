package com.newtouch.core.dbconnection.handle.db2;

import java.util.ArrayList;
import java.util.List;

import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.dbconnection.handle.util.ReplaceUtil;
import com.newtouch.utils.dateutil.DateUtil;

public class UpdateSql4DB2 implements UpdateSqlable {

	private String sql = "";

	private Object[] params;
	private List<Object> param = new ArrayList<Object>();

	public UpdateSql4DB2(String sql, Object[] params) {
		this.setSql(sql);
		this.setParameter(params);
	}

	public UpdateSql4DB2() {

	}

	public String getPrintSql() {
		String printSql = this.sql;
		if (this.params == null || params.length == 0) {
			this.params = param.toArray();
		}

		if (this.params != null && params.length > 0) {
			Integer size = params.length;
			ReplaceUtil util = new ReplaceUtil();
			for (int i = 0; i < size; i++) {
				Object obj = params[i];
				if (obj instanceof String) {
					obj = util.replaceSpecialChar((String) obj);
					// System.out.print(obj + " | ");
					printSql = printSql.replaceFirst("\\?", "'" + (String) obj
							+ "'");
					continue;
				}
				if (obj instanceof Integer) {
					printSql = printSql
							.replaceFirst("\\?", String.valueOf(obj));
					continue;
				}
				if (obj instanceof java.sql.Timestamp) {
					printSql = printSql.replaceFirst("\\?", "timestamp('"
							+ String.valueOf(obj) + "')");
					continue;
				}

				if (obj instanceof java.util.Date) {
					printSql = printSql.replaceFirst(
							"\\?",
							"date('"
									+ DateUtil
											.date2String((java.util.Date) obj)
									+ "')");
					continue;
				}
				printSql = printSql.replaceFirst("\\?", "''");

			}
			printSql = util.revertSpecialChar(printSql);
		}

		return printSql;
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
		params = null;
		param.clear();
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
