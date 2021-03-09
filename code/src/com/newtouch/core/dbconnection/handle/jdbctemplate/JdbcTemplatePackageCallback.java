package com.newtouch.core.dbconnection.handle.jdbctemplate;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;

import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.util.ReplaceUtil;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;

public class JdbcTemplatePackageCallback implements CallableStatementCallback {
	private QuerySqlable querySQL = null;
	private ThreadLocal<ServerObj> threadLocal = null;

	@Override
	public Object doInCallableStatement(CallableStatement cs)
			throws SQLException, DataAccessException {
		List<Map<String, Object>> resultsMap = new ArrayList<Map<String, Object>>();
		cs.execute();
		/* 查询总记录数据 */
		int rowNum = cs.getInt(6);
		/* 循环遍历得到返回的查询数据 */
		ResultSet rs = null;
		rs = (ResultSet) cs.getObject(7);
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int pageID = 0;
			while (rs.next()) {
				pageID++;
				resultsMap.add(ReplaceUtil.initResult(rsmd, rs));
			}
			// 记录总数
			threadLocal.get().getPageCount().setAllRows(rowNum);
			// 查询记录页数
			threadLocal
					.get()
					.getPageCount()
					.setAllPage(
							rowNum == 0 ? rowNum : (rowNum - 1)
									/ threadLocal.get().getPageCount()
											.getRows4Page() + 1);
			// 本页记录数
			threadLocal.get().getPageCount().setPageRows(pageID);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null)
				try {
					rs.close();
					querySQL.clearParam();
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
			if (cs != null) {
				cs.close();
			}
		}
		return resultsMap;
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
