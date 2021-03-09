package com.newtouch.utils.uniqueseq;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.newtouch.core.context.SpringContext;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.uniqueseq.pojo.UniqueObj;

/**
 * 需要使用自己的链接，不能使用业务类的链接。
 * <p>
 * 用于获取
 * 
 * @author cn_shuai
 * 
 */
class UniqueSeqInfo {
	private static UniqueSeqInfo AUNIQUESEQ = null;
	private static Map<String, UniqueObj> UNIQUESEQ = new HashMap<String, UniqueObj>();
	// 递增量
	private final static int INCREMENTAL_CHANGE = 1;

	private UniqueSeqInfo() {

	}

	/**
	 * 得到数据库操作实例
	 * 
	 * @return
	 */
	public synchronized static UniqueSeqInfo getInstance() {
		if (AUNIQUESEQ == null) {
			AUNIQUESEQ = new UniqueSeqInfo();
		}
		return AUNIQUESEQ;
	}

	/**
	 * 根据序列名称查找序列号。
	 * 
	 * @param seqName
	 * @return
	 */
	public synchronized Long getUniqueSeq(String seqName) {
		// 如果列表中不存在该序列值，则去创建序列值
		if (!UNIQUESEQ.containsKey(seqName)) {
			UNIQUESEQ.put(seqName, this.createUniqueSeq(seqName));
		}
		// 从列表中取出相应的序列
		// 如果现在的值大于等于分配的最大值，则再创建一次
		if (UNIQUESEQ.get(seqName).getNow() >= UNIQUESEQ.get(seqName).getMax()) {
			UNIQUESEQ.put(seqName, this.createUniqueSeq(seqName));
		}
		UNIQUESEQ.get(seqName).setNow(UNIQUESEQ.get(seqName).getNow() + 1);
		return UNIQUESEQ.get(seqName).getNow();
	}

	/**
	 * 根据序列名称、表名称查找序列号。
	 * 
	 * @param seqName
	 * @return
	 */
	public synchronized Long getUniqueSeq(String seqName, String tableName) {
		// 如果列表中不存在该序列值，则去创建序列值
		if (!UNIQUESEQ.containsKey(seqName + tableName)) {
			UNIQUESEQ.put(seqName + tableName,
					this.createUniqueSeq(seqName, tableName));
		}
		// 从列表中取出相应的序列
		// 如果现在的值大于等于分配的最大值，则再创建一次
		if (UNIQUESEQ.get(seqName + tableName).getNow() >= UNIQUESEQ.get(
				seqName + tableName).getMax()) {
			UNIQUESEQ.put(seqName + tableName,
					this.createUniqueSeq(seqName, tableName));
		}
		UNIQUESEQ.get(seqName + tableName).setNow(
				UNIQUESEQ.get(seqName + tableName).getNow() + 1);
		return UNIQUESEQ.get(seqName + tableName).getNow();
	}

	/**
	 * 查询数据库中当前的code，并且生成新的code。
	 * 
	 * @param seqName
	 * @return
	 */
	private synchronized UniqueObj createUniqueSeq(String seqName) {
		ServerBase serverBase = (ServerBase) SpringContext
				.getBean("serverbase");
		Connection con = serverBase.dbHandle().getConnection();
		Statement stat = null;
		ResultSet rs = null;
		long code = -1;
		UniqueObj uniqueObj = new UniqueObj();
		String select = null;
		String insertSql = null;
		try {
			stat = con.createStatement();
			// 查询数据库的中当前的值
			select = "SELECT code FROM t_uniquetable WHERE upper(name) = '"
					+ seqName.toUpperCase() + "' and space1 is null";
			rs = stat.executeQuery(select);
			while (rs.next()) {
				code = Long.parseLong(rs.getString("code"));
			}
			if (code == -1) {
				insertSql = "insert into t_uniquetable(name,code) values ('"
						+ seqName + "',1)";
				stat.executeUpdate(insertSql);
				code = 1;
				System.out.println("未定义[" + seqName + "]主键，系统已自动添加。");
			}
			// 设置值的增加
			insertSql = "UPDATE t_uniquetable SET code = "
					+ (code + INCREMENTAL_CHANGE) + " WHERE upper(name) = '"
					+ seqName + "' ";
			stat.executeUpdate(insertSql);
			// 设置现在的值
			uniqueObj.setNow(code);
			// 设置最大值
			uniqueObj.setMax(code + INCREMENTAL_CHANGE);
			// 设置本次开始值
			uniqueObj.setStart(code);
			con.commit();
		} catch (SQLException e) {
			System.out.println("select = " + select);
			System.out.println("insertSql = " + insertSql);
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
					System.out.println("关闭连接 UniqueSeqInfo " + "  |  "
							+ con.hashCode() + "  |  "
							+ Thread.currentThread().getId());
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return uniqueObj;
	}

	/**
	 * 根据seqName、tableName 查询数据库中当前的code，并且生成新的code。
	 * 
	 * @param seqName
	 * @return
	 */
	private synchronized UniqueObj createUniqueSeq(String seqName,
			String tableName) {
		ServerBase serverBase = (ServerBase) SpringContext
				.getBean("serverbase");
		Connection con = serverBase.dbHandle().getConnection();
		Statement stat = null;
		ResultSet rs = null;
		long code = -1;
		UniqueObj uniqueObj = new UniqueObj();
		String select = null;
		String insertSql = null;
		try {
			stat = con.createStatement();
			// 查询数据库的中当前的值
			select = "SELECT code FROM t_uniquetable WHERE upper(name) = '"
					+ seqName + "' and upper(space1) = '" + tableName + "'";
			rs = stat.executeQuery(select);
			while (rs.next()) {
				code = Long.parseLong(rs.getString("code"));
			}
			if (code == -1) {
				insertSql = "insert into t_uniquetable(name,space1,code) values ('"
						+ seqName + "','" + tableName + "',1)";
				stat.executeUpdate(insertSql);
				code = 1;
				System.out.println("未定义[" + seqName + "," + tableName
						+ "]主键，系统已自动添加。");
			}
			// 设置值的增加
			insertSql = "UPDATE t_uniquetable SET code = "
					+ (code + INCREMENTAL_CHANGE) + " WHERE upper(name) = '"
					+ seqName + "' and upper(space1) = '" + tableName + "'";
			stat.executeUpdate(insertSql);
			// 设置现在的值
			uniqueObj.setNow(code);
			// 设置最大值
			uniqueObj.setMax(code + INCREMENTAL_CHANGE);
			// 设置本次开始值
			uniqueObj.setStart(code);
			con.commit();
		} catch (SQLException e) {
			System.out.println("select = " + select);
			System.out.println("insertSql = " + insertSql);
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
					System.out.println("关闭连接 UniqueSeqInfo " + "  |  "
							+ con.hashCode() + "  |  "
							+ Thread.currentThread().getId());
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return uniqueObj;
	}
}
