package com.newtouch.core.errorcode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.newtouch.core.dbconnection.objfactory.ServerFactory;
import com.newtouch.utils.observer.initparam.ParamObservable;
import com.newtouch.utils.observer.initparam.ParamSubject;

public class ErrorCodeInfo implements ParamObservable {
	private static ErrorCodeInfo aERRORINFO = null;
	private static Map<String, String> ERRORMSGLIST = new HashMap<String, String>();

	private ErrorCodeInfo() {
		this.initErrorCode();
		ParamSubject.getInstance().addParamObserver(this);
	}

	/**
	 * 得到数据库操作实例
	 * 
	 * @return
	 */
	public synchronized static ErrorCodeInfo getInstance() {
		if (aERRORINFO == null) {
			aERRORINFO = new ErrorCodeInfo();
		}
		return aERRORINFO;
	}

	/**
	 * 根据序列名称查找序列号。
	 * 
	 * @param seqName
	 * @return
	 */
	public String getErrorMsg(String msgCode) {
		if (ERRORMSGLIST.containsKey(msgCode)) {
			return ERRORMSGLIST.get(msgCode);
		} else {
			throw new RuntimeException("未查找到[" + msgCode + "]错误代码。");
		}
	}

	private void initErrorCode() {
		Connection con = ServerFactory.getServerFactory().getConnection();
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = con.createStatement();
			// 查询数据库的中当前的值
			String select = "SELECT errorcode code, errormsg msg FROM t_errorcode";
			rs = stat.executeQuery(select);
			while (rs.next()) {
				ERRORMSGLIST.put(rs.getString("code"), rs.getString("msg"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void reloadParam() {
		this.initErrorCode();
	}
}
