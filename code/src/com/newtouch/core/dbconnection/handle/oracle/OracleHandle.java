package com.newtouch.core.dbconnection.handle.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.stereotype.Component;

import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.Sqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.dbconnection.handle.util.ReplaceUtil;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.stringutil.StringUtil;
import com.newtouch.utils.uniqueseq.UniqueSeq;

@Component("oracleHandle")
public class OracleHandle implements DBHandleable {

	// @Autowired
	// @Qualifier("ThreadLocalServerObj")
	private ThreadLocal<ServerObj> threadLocal;

	private Connection conn;
	private String optLog = "INSERT INTO sys_opt_log (serno, crt_date, mdf_date, crt_user, mdf_user, data_flag, call_method, menu_no, func_no, dept_no, sql_info)"
			+ "VALUES (?, sysdate, sysdate, ?, ?, ?, ?, ?, ?, ?, ?)";

	public Connection getCurConn() {
		try {
			if (conn == null)
				conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug("创建连接[" + conn.hashCode() + "]");
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
			if (conn == null) {
				conn = threadLocal.get().getConnection();
			}
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				threadLocal.get().setConnection(conn);
				Ulog.debug("创建连接[" + conn.hashCode() + "]");
			}
			Ulog.debug("开始事务[" + conn.hashCode() + "].");
			conn.setAutoCommit(false);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
	}

	public void commitTransaction() {
		try {
			if (conn == null)
				conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed())
				conn = threadLocal.get().getDataSoruce().getConnection();
			Ulog.debug("提交事务[" + conn.hashCode() + "].");
			conn.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (conn != null) {
					threadLocal.get().setConnection(null);
					Ulog.debug("关闭连接[" + conn.hashCode() + "]");
					conn.close();

				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public void rollbackTransaction() {
		try {
			if (conn == null)
				conn = threadLocal.get().getConnection();
			if (conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug("创建连接[" + conn.hashCode() + "]");
			}
			Ulog.debug("事务回滚[" + conn.hashCode() + "]");
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
				Ulog.debug("创建连接[" + conn.hashCode() + "]");
			}
			st = conn.createStatement();
			this.ulogSql(querySQL);
			rs = st.executeQuery(querySQL.getSql());
			ResultSetMetaData rsmd = rs.getMetaData();
			if (rs.next()) {
				resultSetTable = ReplaceUtil.initResult(rsmd, rs);
			}
			return resultSetTable;
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

	public List<Map<String, Object>> queryList4Jdbc(QuerySqlable querySql) {
		ResultSet rs = null;
		Statement stat = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug("创建连接[" + conn.hashCode() + "]");
			}
			stat = conn.createStatement();
			String sql = "";
			if (querySql.isPaginate()) {
				PageCount pageCount = this.threadLocal.get().getPageCount();
				// 当前第几页
				int pageNum = pageCount.getNowPage();
				// 每页显示多少行
				int row4Page = pageCount.getRows4Page();
				sql = "SELECT *  FROM (SELECT tmp.*, rownum rn FROM ("
						+ querySql.getSql() + ") tmp) WHERE rn > "
						+ (pageNum - 1) * row4Page + "  AND rn <= " + pageNum
						* row4Page;
			} else {
				sql = querySql.getSql();
			}
			this.ulogSql(querySql);
			rs = stat.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int pageID = 0;
			while (rs.next()) {
				pageID++;
				list.add(ReplaceUtil.initResult(rsmd, rs));
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
			throw new RuntimeException(e.getMessage());
		} finally {
			querySql.clearParam();
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
				this.closeConn();
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
			this.ulogSql(querySQL);
			conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug("创建连接[" + conn.hashCode() + "]");
			}
			cstmt = conn
					.prepareCall("{call page_query.PAGE_PRO(?,?,?,?,?,?,?)}");
			cstmt.clearParameters();
			cstmt.setString(1, querySQL.getSql());
			cstmt.setString(2, querySQL.getField());
			if (querySQL.isPaginate()) {
				// 每页显示多少行
				int pageCount = threadLocal.get().getPageCount().getRows4Page();
				cstmt.setInt(3, pageCount);
				// 取第几页数据
				cstmt.setInt(4, threadLocal.get().getPageCount().getNowPage());
				cstmt.setString(5, "1");
			} else {
				// 每页显示多少行
				cstmt.setInt(3, 0);
				// 取第几页数据
				cstmt.setInt(4, 0);
				cstmt.setString(5, "0");
			}
			cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
			cstmt.registerOutParameter(7, OracleTypes.CURSOR);
			cstmt.execute();
			/* 查询总记录数据 */
			int rowNum = cstmt.getInt(6);
			/* 循环遍历得到返回的查询数据 */
			rs = (ResultSet) cstmt.getObject(7);
			ResultSetMetaData rsmd = rs.getMetaData();
			int pageID = 0;
			while (rs.next()) {
				pageID++;
				list.add(ReplaceUtil.initResult(rsmd, rs));
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
			if (cstmt != null)
				try {
					cstmt.close();
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

	public List<Object> queryObjectList(QuerySqlable querySQL) {
		ResultSet rs = null;
		Statement stmt = null;
		List<Object> list = new ArrayList<Object>();
		try {
			this.ulogSql(querySQL);
			conn = threadLocal.get().getConnection();
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug("创建连接[" + conn.hashCode() + "]");
			}
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

		PreparedStatement st = null;
		conn = threadLocal.get().getConnection();
		try {
			this.ulogSql(updateSQL);
			if (conn == null || conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug("创建连接[" + conn.hashCode() + "]");
			}
			st = conn.prepareStatement(updateSQL.getSql());
			Object[] paramList = updateSQL.getParamList();
			int size = paramList.length;
			for (int i = 1; i <= size; i++) {
				Object obj = paramList[i - 1];
				if (obj instanceof Double) {
					st.setDouble(i, (Double) obj);
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
				// this.annalLog(updateSQL.getPrintSql(), result);
			}
			return result;
		} catch (SQLException err) {
			err.printStackTrace();
			throw new RuntimeException(err.getMessage());
		} finally {
			updateSQL.clearParam();
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			try {
				if (conn != null && !conn.isClosed()) {
					if (conn.getAutoCommit()) {
						this.closeConn();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public OracleHandle(ThreadLocal<ServerObj> threadLocal) {
		this.threadLocal = threadLocal;

	}

	public OracleHandle() {
	}

	public Connection getConnection() {
		try {
			Connection con = threadLocal.get().getDataSoruce().getConnection();
			Ulog.debug("创建连接[" + con.hashCode() + "]");
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
			if (conn.isClosed()) {
				conn = threadLocal.get().getDataSoruce().getConnection();
				Ulog.debug("创建连接[" + conn.hashCode() + "]");
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
			Ulog.debug("关闭连接[" + conn.hashCode() + "]");
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
		if (conn == null)
			conn = threadLocal.get().getConnection();
		PreparedStatement st = null;
		try {
			if (conn == null || conn.isClosed())
				conn = threadLocal.get().getDataSoruce().getConnection();
			st = conn.prepareStatement(this.optLog);
			st.setString(1, UniqueSeq.getUniqueSeq("serno", "sys_opt_log")
					.toString());
			st.setString(2, user.getOptID());
			st.setString(3, user.getOptID());
			st.setString(4, "1");
			String clazz = Ulog.callMethod();
			st.setString(5, clazz);
			st.setString(6, user.getMenuID());
			st.setString(7, user.getFuncID());
			st.setString(8, user.getDid());
			st.setString(9, sql + " 【更新了" + result + "条记录。】");
			st.executeUpdate();
		} catch (SQLException err) {
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
				this.closeConn();
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

	public void setThreadLocal(ThreadLocal<ServerObj> threadLocal) {
		this.threadLocal = threadLocal;
	}

	public ThreadLocal<ServerObj> getThreadLocal() {
		return this.threadLocal;
	}

	private void ulogSql(Sqlable querySQL) {
		Ulog.debug(querySQL.getPrintSql() + " " + Ulog.callMethod(), 2);
	}
}
