package com.newtouch.core.dbconnection.handle.db2;

import java.util.ArrayList;
import java.util.List;

import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.util.ReplaceUtil;

public class QuerySql4DB2 implements QuerySqlable {
	private String field = " * ";
	private Object[] paramList;
	private String orderBy;
	private String sql;
	private boolean paginate = true;
	private boolean localOrder = false;
	private List<Object> param = new ArrayList<Object>();

	/**
	 * 构造一个不包含？号的SQL语句对象。
	 * 
	 * @param field
	 *            设置要查询出的字段。相当于SQL中select以后from以前中间的值。
	 * @param sql
	 *            查询Sql语句
	 * 
	 */
	public QuerySql4DB2(String field, String sql) {
		this.setSql(sql);
		this.setField(field);
	}

	public QuerySql4DB2() {

	}

	/**
	 * 构造一个包含？号的SQL语句对象，？号中的值使用paramList中的值替换。
	 * 
	 * @param field
	 *            设置要查询出的字段。相当于SQL中select以后from以前中间的值。
	 * @param sql
	 *            查询Sql语句
	 * @param paramList
	 *            参数列表
	 */
	public QuerySql4DB2(String field, String sql, Object[] paramList) {
		this.setSql(sql);
		this.setField(field);
		this.setParameter(paramList);
	}

	public void setField(String field) {
		this.checkField(field);
		this.field = ReplaceUtil.replaceSpace(field);
	}

	public void setSql(String sql) {
		paramList = new Object[] {};
		param.clear();
		this.checkSql(sql);
		this.sql = ReplaceUtil.replaceSpace(sql);
	}

	public void setParameter(Object[] paramList) {
		this.paramList = paramList;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getPrintSql() {
		String tmpSql = ReplaceUtil.replaceSql(paramList, sql);
		if (orderBy != null && !orderBy.equals("")) {
			tmpSql += " order by " + orderBy;
		}
		return tmpSql;
	}

	public void setPaginate(boolean paginate) {
		this.paginate = paginate;
	}

	private void checkField(String field) {
		if (field == null || field.equals("")) {
			throw new RuntimeException("Sql查询字段不能空。");
		}
	}

	private void checkSql(String sql) {
		if (sql == null || sql.equals("")) {
			throw new RuntimeException("Sql不能空。");
		}
	}

	public boolean isPaginate() {
		return paginate;
	}

	public String getField() {
		return this.field;
	}

	public String getSql() {
		String tmpSql = ReplaceUtil.replaceSql(paramList, sql);
		if (orderBy != null && !orderBy.equals("")) {
			tmpSql += " order by " + orderBy;
		}
		if (tmpSql.indexOf("dataflag") < 0) {
			// Ulog.debug(tmpSql);
			// throw new RuntimeException("未限制数据有效性。");
		}
		return tmpSql;
	}

	public boolean isLocalOrder() {
		return localOrder;
	}

	public void setLocalOrder(boolean localOrder) {
		this.localOrder = localOrder;
	}

	public void add(Object para) {
		param.add(para);
	}

	public void clearParam() {
		this.param.clear();
		this.paramList = null;
	}
}
