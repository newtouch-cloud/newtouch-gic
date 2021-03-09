package com.newtouch.core.dbconnection.handle.db2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.stringutil.StringUtil;
import com.newtouch.utils.uniqueseq.UniqueSeq;

public class DB2Handle implements DBHandleable {
	private ThreadLocal<ServerObj> threadLocal;
	private Connection conn;

	public Connection getCurConn() {
		try {
			if (conn == null)
				conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug(threadLocal.get().getSourceType() + "  |  "
						+ "创建连接 getCurConn" + "  |  " + conn.hashCode()
						+ "  |  " + Thread.currentThread().getId());
				threadLocal.get().setConnection(conn);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
		return conn;
	}

	public void startTransaction() {
		try {
			if (conn == null)
				conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug(threadLocal.get().getSourceType() + "  |  "
						+ "创建连接 startTransaction" + "  |  " + conn.hashCode()
						+ "  |  " + Thread.currentThread().getId());
				threadLocal.get().setConnection(conn);
			}
			conn.setAutoCommit(false);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
	}

	public void commitTransaction() {
		try {
			if (conn != null && !conn.isClosed())
				conn.commit();
			if (conn == null || conn.isClosed())
				conn = threadLocal.get().getConnection();
			if (conn != null && !conn.isClosed())
				conn.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				this.closeConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public void rollbackTransaction() {
		try {
			if (conn != null && !conn.isClosed())
				conn.rollback();
			if (conn == null || conn.isClosed())
				conn = threadLocal.get().getConnection();
			if (conn != null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			if (conn != null)
				try {
					this.closeConn();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
		}
	}

	public Map<String, Object> query(QuerySqlable querySQL) {
		ResultSet rs = null;
		Statement st = null;
		Map<String, Object> resultSetTable = new LinkedHashMap<String, Object>();
		try {
			conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug(threadLocal.get().getSourceType() + "  |  "
						+ "创建连接 query" + "  |  " + conn.hashCode() + "  |  "
						+ Thread.currentThread().getId());
			}
			st = conn.createStatement();
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ querySQL.getSql() + "  |  " + conn.hashCode() + "  |  "
					+ Thread.currentThread().getId());
			rs = st.executeQuery(querySQL.getSql());
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				resultSetTable = this.initResult(rsmd, rs);
				break;
			}
			return resultSetTable;
		} catch (Exception e) {
			e.printStackTrace();
			throw initErrorMsg(e);
		} finally {
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
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			try {
				this.closeAutoConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());

			}
		}
	}

	private RuntimeException initErrorMsg(Exception e) {
		String error = e.getMessage();
		if (error == null) {
			return new RuntimeException(error);
		}
		int char1 = error.indexOf('=');
		int char2 = error.indexOf(',');
		if (error != null && !"".equals(error) && char1 > 0 && char2 > 0) {
			String indexof = error.substring(char1 + 1, char2);
			try {
				Message message = new Message(indexof);
				error = message.getMsgInfo();
			} catch (Throwable e1) {
				Ulog.debug(e1.getMessage());
			}
		}
		return new RuntimeException(error);
	}

	public List<Map<String, Object>> queryList4Jdbc(QuerySqlable querySql) {
		ResultSet rs = null;
		Statement stat = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug(threadLocal.get().getSourceType() + "  |  "
						+ "创建连接 queryList4Jdbc" + "  |  " + conn.hashCode()
						+ "  |  " + Thread.currentThread().getId());
			}
			stat = conn.createStatement();
			String sql = "";
			if (querySql.isPaginate()) {
				PageCount pageCount = this.threadLocal.get().getPageCount();
				// 当前第几页
				int pageNum = pageCount.getNowPage();
				// 每页显示多少行
				int row4Page = pageCount.getRows4Page();
				sql = "SELECT *  FROM (SELECT tmp.*, row_number() over() rn FROM ("
						+ querySql.getSql()
						+ ") tmp) WHERE rn > "
						+ (pageNum - 1)
						* row4Page
						+ "  AND rn <= "
						+ pageNum
						* row4Page;
			} else {
				sql = querySql.getSql();
			}
			Ulog.debug(threadLocal.get().getSourceType() + "  |  " + sql
					+ "  |  " + conn.hashCode() + "  |  "
					+ Thread.currentThread().getId());
			rs = stat.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int pageID = 0;
			while (rs.next()) {
				pageID++;
				list.add(this.initResult(rsmd, rs));
			}
			// 查询记录总数
			int allRows = this.queryCount(querySql, conn);
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
			threadLocal.get().getPageCount().setPageRows(pageID);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw this.initErrorMsg(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			try {
				this.closeAutoConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public List<Map<String, Object>> queryList(QuerySqlable querySQL) {
		if (!querySQL.isLocalOrder()) {
			querySQL.setOrderBy(threadLocal.get().getPageCount().getOrderBy());
		}
		ResultSet rs = null;
		CallableStatement cstmt = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {

			conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug(threadLocal.get().getSourceType() + "  |  "
						+ "创建连接 queryList" + "  |  " + conn.hashCode()
						+ "  |  " + Thread.currentThread().getId());
			}
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ querySQL.getSql() + "  |  " + conn.hashCode() + "  |  "
					+ Thread.currentThread().getId());
			// TODO 加入存储过程获取抛错信息
			cstmt = conn.prepareCall("{call page_query(?,?,?,?,?,?)}");
			cstmt.clearParameters();
			cstmt.setString(1, querySQL.getSql());
			cstmt.setString(2, querySQL.getField());
			if (querySQL.isPaginate()) {
				// 每页显示多少行
				int pageCount = threadLocal.get().getPageCount().getRows4Page();
				cstmt.setInt(3, pageCount);
				int nowPage = threadLocal.get().getPageCount().getNowPage();
				if (nowPage < 1) {
					nowPage = 1;
				}
				// 取第几页数据
				cstmt.setInt(4, nowPage);
				// 设置分页
				cstmt.setInt(5, 1);
			} else {
				// 每页显示多少行
				cstmt.setInt(3, 0);
				// 取第几页数据
				cstmt.setInt(4, 0);
				// 设置不分页
				cstmt.setInt(5, 0);
			}
			cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
			cstmt.execute();
			/* 查询总记录数据 */
			int rowNum = cstmt.getInt(6);
			/* 循环遍历得到返回的查询数据 */
			rs = cstmt.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			int pageID = 0;
			while (rs.next()) {
				pageID++;
				list.add(this.initResult(rsmd, rs));
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
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw this.initErrorMsg(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			if (cstmt != null)
				try {
					cstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			try {
				this.closeAutoConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public List<Object> queryObjectList(QuerySqlable querySQL) {
		ResultSet rs = null;
		Statement stmt = null;
		List<Object> list = new ArrayList<Object>();
		try {

			conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug(threadLocal.get().getSourceType() + "  |  "
						+ "创建连接 queryObjectList" + "  |  " + conn.hashCode()
						+ "  |  " + Thread.currentThread().getId());
			}
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ querySQL.getSql() + "  |  " + conn.hashCode() + "  |  "
					+ Thread.currentThread().getId());
			stmt = conn.createStatement();
			/* 循环遍历得到返回的查询数据 */
			rs = (ResultSet) stmt.executeQuery(querySQL.getSql());
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				list.add(this.initResult4Object(rsmd, rs));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw this.initErrorMsg(e);
		} finally {
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
				this.closeAutoConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public int update(UpdateSqlable updateSQL) {

		PreparedStatement st = null;
		conn = threadLocal.get().getConnection();
		try {
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug(threadLocal.get().getSourceType() + "  |  "
						+ "创建连接 update" + "  |  " + conn.hashCode() + "  |  "
						+ Thread.currentThread().getId());
			}
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ updateSQL.getPrintSql() + "  |  " + conn.hashCode()
					+ "  |  " + Thread.currentThread().getId());
			st = conn.prepareStatement(updateSQL.getSql());
			Object[] paramList = updateSQL.getParamList();
			int size = paramList.length;
			for (int i = 1; i <= size; i++) {
				Object obj = paramList[i - 1];
				if (obj instanceof Double) {
					st.setDouble(i, ((Double) obj).intValue());
					continue;
				}
				if (obj instanceof Integer) {
					st.setInt(i, ((Integer) obj).intValue());
					continue;
				}
				if (obj instanceof Timestamp) {
					st.setTimestamp(i, (Timestamp) obj);
					continue;
				}
				if (obj instanceof java.util.Date) {
					st.setDate(i, new Date(((java.util.Date) obj).getTime()));
					continue;
				}
				st.setString(i, StringUtil.trimStr(obj));

			}
			int result = st.executeUpdate();
			if (result > 0) {
				this.annalLog(updateSQL.getPrintSql(), result);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw this.initErrorMsg(e);
		} finally {
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage());
				}
			try {
				if (conn != null && !conn.isClosed()) {
					if (conn.getAutoCommit()) {
						this.closeAutoConn();
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public DB2Handle(ThreadLocal<ServerObj> threadLocal) {
		this.threadLocal = threadLocal;
	}

	public DB2Handle() {

	}

	public Connection getConnection() {
		try {
			Connection con = threadLocal.get().getDataSoruce().getConnection();
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ "创建连接 getConnection() " + "  |  " + con.hashCode()
					+ "  |  " + Thread.currentThread().getId());
			return con;
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
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug(threadLocal.get().getSourceType() + "  |  "
						+ "创建连接 queryMetaData" + "  |  " + conn.hashCode()
						+ "  |  " + Thread.currentThread().getId());
			}
			st = conn.createStatement();
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ querySQL.getSql() + "  |  " + conn.hashCode() + "  |  "
					+ Thread.currentThread().getId());
			rs = st.executeQuery(querySQL.getSql());
			ResultSetMetaData rsmd = rs.getMetaData();
			Map<String, Integer> table = new LinkedHashMap<String, Integer>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				table.put(rsmd.getColumnName(i), rsmd.getColumnType(i));
			}
			return table;
		} catch (Exception e) {
			throw this.initErrorMsg(e);
		} finally {
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
				this.closeAutoConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	private void closeAutoConn() throws SQLException {
		if (threadLocal.get().getConnection() != null
				&& !threadLocal.get().getConnection().isClosed()
				&& threadLocal.get().getConnection().getAutoCommit()) {
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ "关闭连接 closeAutoConn" + "  |  "
					+ threadLocal.get().getConnection().hashCode() + "  |  "
					+ Thread.currentThread().getId());
			threadLocal.get().getConnection().close();
		}
		if (conn != null && !conn.isClosed() && conn.getAutoCommit()) {
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ "关闭连接 closeAutoConn_conn" + "  |  " + conn.hashCode()
					+ "  |  " + Thread.currentThread().getId());
			conn.close();
			threadLocal.get().setConnection(null);
		}
	}

	private void closeConn() throws SQLException {
		if (threadLocal.get().getConnection() != null
				&& !threadLocal.get().getConnection().isClosed()) {
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ "关闭连接 closeConn" + "  |  "
					+ threadLocal.get().getConnection().hashCode() + "  |  "
					+ Thread.currentThread().getId());
			threadLocal.get().getConnection().close();

		}
		if (conn != null && !conn.isClosed()) {
			Ulog.debug(threadLocal.get().getSourceType() + "  |  "
					+ "关闭连接 closeConn_conn" + "  |  " + conn.hashCode()
					+ "  |  " + Thread.currentThread().getId());
			conn.close();
			threadLocal.get().setConnection(null);
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
		if (conn == null)
			conn = threadLocal.get().getConnection();
		PreparedStatement st = null;
		try {
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug(threadLocal.get().getSourceType() + "  |  "
						+ "创建连接 annalLog" + "  |  " + conn.hashCode() + "  |  "
						+ Thread.currentThread().getId());
			}
			st = conn.prepareStatement("INSERT INTO t_optlog "
					+ "  (serno, did, OBJNO, btnid, memo, optid, optdate) "
					+ "   VALUES  (?, ?, ?, ?, ?, ?, ?)");
			st.setLong(1, UniqueSeq.getUniqueSeq("logs"));
			st.setString(2, user.getDid());
			st.setString(3, user.getMenuID());
			st.setString(4, user.getFuncID());
			st.setString(5, sql + " 【更新了" + result + "条记录。】");
			st.setString(6, user.getOptID());
			st.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();
		} catch (SQLException err) {
			Ulog.debug("INSERT INTO t_optlog "
					+ "    (serno, did, OBJNO, btnid, memo, optid, optdate) "
					+ "  VALUES " + "(" + UniqueSeq.getUniqueSeq("logs")
					+ ", '" + user.getDid() + "', '" + user.getMenuID()
					+ "', '" + user.getFuncID() + "','" + sql + " 【更新了"
					+ result + "条记录。】" + "', '" + user.getOptID()
					+ "', timestamp("
					+ new Timestamp(System.currentTimeMillis()) + "))");
			err.printStackTrace();
			throw new RuntimeException(
					new Message("SMS-0005", err.getMessage()));
		} finally {
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			try {
				this.closeAutoConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	/**
	 * 返回记录总数
	 * 
	 * @param querySql
	 * @param conn
	 * @return
	 */
	private Integer queryCount(QuerySqlable querySql, Connection conn) {
		ResultSet rs = null;
		Statement stat = null;
		int pageID = 0;
		try {
			stat = conn.createStatement();
			String sql = "SELECT COUNT(*) num FROM (" + querySql.getSql() + ")";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				pageID = rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			}
		}
		return pageID;
	}

	private Map<String, Object> initResult(ResultSetMetaData rsmd, ResultSet rs)
			throws SQLException {
		Map<String, Object> resultSetTable = new LinkedHashMap<String, Object>();
		int dataType = -1;
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			dataType = rsmd.getColumnType(i);
			if (dataType == Types.TIMESTAMP) {
				java.sql.Timestamp date = rs.getTimestamp(i);
				if (date != null) {
					resultSetTable.put(rsmd.getColumnName(i).toLowerCase(),
							date);
				}
				continue;
			}
			if (dataType == Types.DATE) {
				java.sql.Date date = rs.getDate(i);
				if (date != null) {
					resultSetTable.put(rsmd.getColumnName(i).toLowerCase(),
							date);
				}
			} else {
				String retVal = rs.getString(i);
				resultSetTable.put(rsmd.getColumnName(i).toLowerCase(),
						retVal == null ? "" : retVal);
			}
		}
		return resultSetTable;
	}

	private Object initResult4Object(ResultSetMetaData rsmd, ResultSet rs)
			throws SQLException {
		Object value = new Object();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			int dataType = rsmd.getColumnType(i);
			if (dataType == Types.DATE) {
				java.sql.Date date = rs.getDate(i);
				if (date != null) {
					value = date;
				}
			} else {
				String retVal = rs.getString(i);
				value = retVal;
			}
		}
		return value;
	}

	public void setThreadLocal(ThreadLocal<ServerObj> threadLocal) {
		this.threadLocal = threadLocal;
	}

	public ThreadLocal<ServerObj> getThreadLocal() {
		return this.threadLocal;
	}
}
