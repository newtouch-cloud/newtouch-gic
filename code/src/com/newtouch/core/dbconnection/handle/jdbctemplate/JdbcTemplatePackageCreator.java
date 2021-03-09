package com.newtouch.core.dbconnection.handle.jdbctemplate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.CallableStatementCreator;

import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;

public class JdbcTemplatePackageCreator implements CallableStatementCreator {
	private QuerySqlable querySQL = null;
	private ThreadLocal<ServerObj> threadLocal = null;

	@Override
	public CallableStatement createCallableStatement(Connection con)
			throws SQLException {
		String storedProc = "{call page_query.PAGE_PRO(?,?,?,?,?,?,?)}";// 调用的sql
		CallableStatement cs = con.prepareCall(storedProc);
		// 设置输入参数的值
		cs.setString(1, querySQL.getSql());
		cs.setString(2, querySQL.getField());
		if (querySQL.isPaginate()) {
			// 每页显示多少行
			int pageCount = threadLocal.get().getPageCount().getRows4Page();
			cs.setInt(3, pageCount);
			// 取第几页数据
			cs.setInt(4, threadLocal.get().getPageCount().getNowPage());
			cs.setString(5, "1");
		} else {
			// 每页显示多少行
			cs.setInt(3, 0);
			// 取第几页数据
			cs.setInt(4, 0);
			cs.setString(5, "0");
		}
		// 注册输出参数的类型
		cs.registerOutParameter(6, java.sql.Types.INTEGER);
		cs.registerOutParameter(7, OracleTypes.CURSOR);
		return cs;
	}

	public QuerySqlable getQuerySQL() {
		return querySQL;
	}

	public void setQuerySQL(QuerySqlable querySQL) {
		this.querySQL = querySQL;
	}

	public ThreadLocal<ServerObj> getThreadLocal() {
		return threadLocal;
	}

	public void setThreadLocal(ThreadLocal<ServerObj> threadLocal) {
		this.threadLocal = threadLocal;
	}
}
