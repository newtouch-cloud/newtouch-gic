package com.newtouch.utils.shujuyunwei.service.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.context.SpringContext;
import com.newtouch.core.dbconnection.handle.util.ReplaceUtil;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.shujuyunwei.service.IShuJuYunWeiService;
import com.newtouch.utils.stringutil.StringUtil;
import com.newtouch.utils.writerfile.WriterFile;

@Service
public class ShuJuYunWeiServiceImp extends ServerBase implements
		IShuJuYunWeiService {
	private static ServerBase serverBase = null;

	@Override
	public ReturnMsg chiXinSql(Map<String, Object> param) {
		if (serverBase == null)
			serverBase = (ServerBase) SpringContext.getBean("serverbase");
		ReturnMsg msg = new ReturnMsg();
		String paramSql = StringUtil.trimStr(param.get("sql"));
		if (StringUtil.isNull(paramSql)) {
			return msg;
		}
		String[] sqlArray = paramSql.split(";");
		Connection con = this.dbHandle().getConnection();
		Statement stat = null;
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> logList = new ArrayList<String>();
		String errorSql = "";
		try {
			con.setAutoCommit(false);
			stat = con.createStatement();
			for (String sql : sqlArray) {
				errorSql = sql;
				sql = StringUtil.trimStr(sql);
				if (sql.toUpperCase().startsWith("SELECT")) {
					PageCount pageCount = serverBase.getThreadLocal().get()
							.getPageCount();
					// 当前第几页
					int pageNum = pageCount.getNowPage();
					// 每页显示多少行
					int row4Page = pageCount.getRows4Page();
					sql = "SELECT *  FROM (SELECT tmp.*, rownum rn FROM ("
							+ sql + ") tmp) WHERE rn > " + (pageNum - 1)
							* row4Page + "  AND rn <= " + pageNum * row4Page;
					rs = stat.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					int pageID = 0;
					while (rs.next()) {
						pageID++;
						list.add(ReplaceUtil.initResult(rsmd, rs));
					}
					// 查询记录总数
					int allRows = this.queryCount(sql, con);
					// 记录总数
					serverBase.getThreadLocal().get().getPageCount()
							.setAllRows(allRows);
					// 查询记录页数
					serverBase
							.getThreadLocal()
							.get()
							.getPageCount()
							.setAllPage(
									allRows == 0 ? allRows : (allRows - 1)
											/ serverBase.getThreadLocal().get()
													.getPageCount()
													.getRows4Page() + 1);
					// 本页记录数
					serverBase.getThreadLocal().get().getPageCount()
							.setPageRows(pageID);
					msg.setDataList(list);
					continue;
				}
				if (sql.toUpperCase().startsWith("UPDATE")
						|| sql.toUpperCase().startsWith("DELETE")) {
					int row = stat.executeUpdate(sql);
					if (row <= 10) {
						msg.getWarnList().add(
								new Message(sql, "影响[" + row + "]行"));
						con.commit();
						logList.add(sql + "\t" + "影响[" + row + "]行" + "\t"
								+ StringUtil.trimStr(param.get("memo")) + "\t"
								+ StringUtil.trimStr(param.get("opterid"))
								+ "\t" + DateUtil.sysTimestamp());
						continue;
					}
					msg.getWarnList().add(
							new Message(sql, "未执行，影响行数为[" + row + "]，超过[10]行"));
					con.rollback();
					continue;
				}
				msg.getWarnList().add(new Message(sql, "未执行"));
			}
		} catch (SQLException e) {
			msg.setFailMessage(new Message(e.getMessage(), errorSql));
			e.printStackTrace();
		} finally {
			WriterFile.isAppend(true);
			String path = "";
			String osName = System.getProperty("os.name");
			if (osName.contains("Windows")) {
				path = "C:/shujuyunwei/shujuyunwei_" + DateUtil.sysDate()
						+ ".txt";
			} else {
				path = "/app/IBM/WebSphere/AppServer/profiles/AppSrv02/logs/server1/shujuyunwei_"
						+ DateUtil.sysDate() + ".txt";
			}
			WriterFile.write(path, logList);
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					msg.setFailMessage(new Message(e.getMessage(), errorSql));
					e.printStackTrace();
				}
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					msg.setFailMessage(new Message(e.getMessage(), errorSql));
					e.printStackTrace();
				}
			if (con != null)
				try {
					if (!con.isClosed()) {
						con.commit();
						con.close();
					}
				} catch (SQLException e) {
					msg.setFailMessage(new Message(e.getMessage(), errorSql));
					e.printStackTrace();
				}

		}
		return msg;
	}

	/**
	 * 返回记录总数
	 * 
	 * @param querySql
	 * @param conn
	 * @return
	 */
	private Integer queryCount(String querySql, Connection conn) {
		ResultSet rs = null;
		Statement stat = null;
		int pageID = 0;
		try {
			stat = conn.createStatement();
			String sql = "SELECT COUNT(*) num FROM (" + querySql + ")";
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
}
