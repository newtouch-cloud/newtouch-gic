package com.newtouch.core.dbconnection.handle.jdbctemplate;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.dbconnection.handle.util.ReplaceUtil;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.uniqueseq.UniqueSeq;

@Component("jdbcTemplateHandle")
public class JdbcTemplateHandle implements DBHandleable {

	// @Autowired
	// @Qualifier("ThreadLocalServerObj")
	private ThreadLocal<ServerObj> threadLocal;

	@Autowired()
	@Qualifier("defaultJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	private Connection conn;

	public Connection getCurConn() {
		return conn;
	}

	public void startTransaction() {
		Ulog.debug("嘛也没执行，咱用Spring控事务。");
	}

	public void commitTransaction() {
		Ulog.debug("嘛也没执行，咱用Spring控事务。");
	}

	public void rollbackTransaction() {
		Ulog.debug("嘛也没执行，咱用Spring控事务。");
	}

	@SuppressWarnings("rawtypes")
	public Map<String, Object> query(QuerySqlable querySQL) {
		Map<String, Object> resultSetTable = new LinkedHashMap<String, Object>();
		Ulog.debug(querySQL.getPrintSql(), 1);
		List list = this.jdbcTemplate.queryForList(querySQL.getSql());
		Iterator iterator = list.iterator();
		Set keySet = null;
		Map rs = null;
		while (iterator.hasNext()) {
			rs = (Map) iterator.next();
			if (keySet == null)
				keySet = rs.keySet();
			resultSetTable = this.initResult(keySet, rs);
		}
		querySQL.clearParam();
		return resultSetTable;
	}

	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> queryList4Jdbc(QuerySqlable querySql) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String sql = "";
		if (querySql.isPaginate()) {
			PageCount pageCount = this.threadLocal.get().getPageCount();
			// 当前第几页
			int pageNum = pageCount.getNowPage();
			// 每页显示多少行
			int row4Page = pageCount.getRows4Page();
			sql = "SELECT *  FROM (SELECT tmp.*, rownum rn FROM ("
					+ querySql.getSql() + ") tmp) WHERE rn > " + (pageNum - 1)
					* row4Page + "  AND rn <= " + (pageNum + 3) * row4Page;
		} else {
			sql = querySql.getSql();
		}
		List rsList = this.jdbcTemplate.queryForList(sql);
		Iterator iterator = rsList.iterator();
		Set keySet = null;
		Map rs = null;
		while (iterator.hasNext()) {
			rs = (Map) iterator.next();
			if (keySet == null)
				keySet = rs.keySet();
			list.add(this.initResult(keySet, rs));
		}
		// 查询记录总数
		int allRows = this.queryCount(querySql);
		// 记录总数
		threadLocal.get().getPageCount().setAllRows(allRows);
		// 查询记录页数
		threadLocal
				.get()
				.getPageCount()
				.setAllPage(
						allRows == 0 ? allRows : (allRows - 1)
								/ threadLocal.get().getPageCount()
										.getRows4Page() + 1);
		// 本页记录数
		threadLocal.get().getPageCount().setPageRows(list.size());
		querySql.clearParam();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryList(QuerySqlable querySQL) {
		if (!querySQL.isLocalOrder()) {
			querySQL.setOrderBy(threadLocal.get().getPageCount().getOrderBy());
		}
		Ulog.debug(querySQL.getPrintSql(), 1);
		// 设置存储过程查询对象
		JdbcTemplatePackageCreator jdbcPackage = new JdbcTemplatePackageCreator();
		jdbcPackage.setQuerySQL(querySQL);
		jdbcPackage.setThreadLocal(threadLocal);
		// 设置存储过程回写对象
		JdbcTemplatePackageCallback jdbcCallback = new JdbcTemplatePackageCallback();
		jdbcCallback.setQuerySQL(querySQL);
		jdbcCallback.setThreadLocal(threadLocal);
		List<Map<String, Object>> resultList = (List<Map<String, Object>>) jdbcTemplate
				.execute(jdbcPackage, jdbcCallback);
		return resultList;
	}

	public List<Object> queryObjectList(QuerySqlable querySQL) {
		ResultSet rs = null;
		Statement stmt = null;
		List<Object> list = new ArrayList<Object>();
		try {
			Ulog.debug(querySQL.getSql());
			conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed())
				conn = threadLocal.get().getDataSoruce().getConnection();
			stmt = conn.createStatement();
			/* 循环遍历得到返回的查询数据 */
			rs = (ResultSet) stmt.executeQuery(querySQL.getSql());
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				list.add(ReplaceUtil.initResult4Object(rsmd, rs));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			querySQL.clearParam();
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			try {
				this.closeConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public int update(UpdateSqlable updateSQL) {
		System.out.println("============="+1+"================");
		Object[] paramList = updateSQL.getParamList();
		for(int i=0;i<paramList.length;i++){
			System.out.println("==========="+paramList[i]+"===========");
			if(paramList[i].equals("有效"))
				paramList[i]="1";
		}
		Ulog.debug(updateSQL.getPrintSql(), 1);
		int result = this.jdbcTemplate.update(updateSQL.getSql(),
				updateSQL.getParamList());
		/*
		 * if (result > 0) { this.annalLog(updateSQL.getPrintSql(), result); }
		 */
		updateSQL.clearParam();
		return result;
	}

	public JdbcTemplateHandle() {

	}

	public JdbcTemplateHandle(ThreadLocal<ServerObj> threadLocal) {
		this.threadLocal = threadLocal;
	}

	public Connection getConnection() {
		try {
			return this.jdbcTemplate.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Map<String, Integer> queryMetaData(QuerySqlable querySQL) {
		ResultSet rs = null;
		Statement st = null;
		try {
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getConnection();
			}
			if (conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
			}
			st = conn.createStatement();
			rs = st.executeQuery(querySQL.getSql());
			ResultSetMetaData rsmd = rs.getMetaData();
			Map<String, Integer> table = new LinkedHashMap<String, Integer>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				table.put(rsmd.getColumnName(i), rsmd.getColumnType(i));
			}
			return table;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			querySQL.clearParam();
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			try {
				this.closeConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	private void closeConn() throws SQLException {
		if (conn != null && conn.getAutoCommit()) {
			threadLocal.get().setConnection(null);
			conn.close();
		}
	}

	/**
	 * 记录日志
	 * 
	 * @param sql
	 *            updateSql语句
	 * @param result
	 *            更新的记录数
	 */
	private void annalLog(String sql, Integer result) {
		if (threadLocal.get().getUser() == null) {
			return;
		}
		User user = threadLocal.get().getUser();
		String ins_sql = "INSERT INTO e_t_optlog "
				+ "  (e_id, dept_code, menu_id, btn_id, memo, opt_id, opt_date) "
				+ "   VALUES  (?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[7];
		param[0] = UniqueSeq.getUniqueSeq("e_id").toString();
		param[1] = user.getDid();
		param[2] = user.getMenuID();
		param[3] = user.getFuncID();
		param[4] = sql + " 【更新了" + result + "条记录。】";
		param[5] = user.getOptID();
		param[6] = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(ins_sql, param);
	}

	/**
	 * 返回记录总数
	 * 
	 * @param querySql
	 * @param conn
	 * @return
	 */
	private Integer queryCount(QuerySqlable querySql) {
		String sql = "SELECT COUNT(*) num FROM (" + querySql.getSql() + ")";
		int pageID = this.jdbcTemplate.queryForInt(sql);
		return pageID;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, Object> initResult(Set keySet, Map rs) {
		Map<String, Object> resultSetTable = new LinkedHashMap<String, Object>();
		Object[] objSet = keySet.toArray(new Object[keySet.size()]);
		for (int i = 0; i < objSet.length; i++) {
			Object obj = rs.get(objSet[i]);
			if (obj instanceof java.util.Date) {
				resultSetTable.put(
						((String) objSet[i]).toLowerCase(),
						obj == null ? "" : new Date(((java.util.Date) obj)
								.getTime()));

			} else {
				String retVal = obj == null ? "" : obj.toString();
				retVal = retVal == null ? "" : retVal;
				String clmName = ((String) objSet[i]).toLowerCase();
				resultSetTable.put(clmName, retVal);
				Map<String, Object> valName = ReplaceUtil.replaceEnum(clmName,
						retVal);
				if (valName != null && !valName.isEmpty()) {
					resultSetTable.putAll(valName);
				}
			}
		}
		return resultSetTable;
	}

	public void setThreadLocal(ThreadLocal<ServerObj> threadLocal) {
		this.threadLocal = threadLocal;
	}

	public ThreadLocal<ServerObj> getThreadLocal() {
		return this.threadLocal;
	}
}
