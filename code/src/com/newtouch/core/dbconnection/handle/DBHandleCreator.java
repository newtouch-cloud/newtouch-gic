package com.newtouch.core.dbconnection.handle;

import java.util.HashMap;
import java.util.Map;

import com.newtouch.core.dbconnection.handle.pojo.DBTypes;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;
import com.newtouch.utils.observer.initparam.ParamObservable;
import com.newtouch.utils.observer.initparam.ParamSubject;

/**
 * 数据库操作对象获取类
 * 
 * @author cn_shuai
 * 
 */
public class DBHandleCreator implements ParamObservable {
	private static DBHandleCreator dbCreator = null;
	private static DBTypes dbType = null;

	// 数据库对象创建路径。数据库类型->操作对象->操作对象路径
	private static Map<DBTypes, Map<String, String>> dbClassPath = new HashMap<DBTypes, Map<String, String>>();

	/**
	 * 返回默认类型的数据库操作对象
	 * <p>
	 * 根据配置文件中记录的当前数据库连接类型，返回默认的数据库操作对象。<br>
	 * 数据库连接从指的数据源中得到。
	 * 
	 * @param threadLocal
	 *            线程对象
	 * @return
	 */
	public DBHandleable getDBHandle(ThreadLocal<ServerObj> threadLocal) {
		return this.getDBHandle(dbType, threadLocal);
	}

	/**
	 * 返回默认类型的数据库查询对象
	 * <p>
	 * 根据配置文件中记录的当前数据库连接类型，返回默认的数据库查询对象。
	 * 
	 * @return 数据库查询对象
	 */
	public QuerySqlable getQuerySql() {
		return this.getQuerySql(dbType);
	}

	/**
	 * 返回默认类型的数据库查询对象
	 * <p>
	 * 根据配置文件中记录的当前数据库连接类型，返回默认的数据库查询对象。<br>
	 * 此处返回的对象不会进行分页以及使用程序中的排序条件。
	 * 
	 * @return 数据库查询对象
	 */
	public QuerySqlable getQuerySql4Calc() {
		QuerySqlable query = this.getQuerySql(dbType);
		query.setLocalOrder(true);
		query.setPaginate(false);
		return query;
	}

	/**
	 * 返回默认类型的数据库更新对象
	 * 
	 * @return 数据库更新对象
	 */
	public UpdateSqlable getUpdateSql() {
		return this.getUpdateSql(dbType);
	}

	/**
	 * 返回指定类型的数据库操作对象
	 * <p>
	 * 数据库连接从默认数据源中得到。
	 * 
	 * @param dbType
	 *            数据库类型
	 * @param threadLocal
	 *            线程对象
	 * @return 数据库操作对象
	 */
	public DBHandleable getDBHandle(DBTypes dbType,
			ThreadLocal<ServerObj> threadLocal) {
		try {
			DBHandleable dbHandle = (DBHandleable) Class.forName(
					dbClassPath.get(dbType).get("DBHandle")).newInstance();
			dbHandle.setThreadLocal(threadLocal);
			return dbHandle;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回指定类型的数据库查询对象
	 * 
	 * @return 数据库查询对象
	 */
	public QuerySqlable getQuerySql(DBTypes dbType) {
		try {
			return (QuerySqlable) Class.forName(
					dbClassPath.get(dbType).get("QuerySql")).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回指定类型的数据库更新对象
	 * 
	 * @return 数据库更新对象
	 */
	public UpdateSqlable getUpdateSql(DBTypes dbType) {
		try {
			return (UpdateSqlable) Class.forName(
					dbClassPath.get(dbType).get("UpdateSql")).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private DBHandleCreator() {
		this.reloadParam();
		ParamSubject.getInstance().addParamObserver(this);
	}

	/**
	 * 得到数据库操作实例
	 * 
	 * @return
	 */
	public synchronized static DBHandleCreator getInstance() {
		if (dbCreator == null) {
			dbCreator = new DBHandleCreator();
		}
		return dbCreator;
	}

	public synchronized void reloadParam() {
		// TODO 添加初始化数据库类型代码
		String baseClaaPatch = "com.newtouch.core.dbconnection.handle.";
		Map<String, String> db2Map = new HashMap<String, String>();
		db2Map.put("DBHandle", baseClaaPatch + "db2.DB2Handle");
		db2Map.put("QuerySql", baseClaaPatch + "db2.QuerySql4DB2");
		db2Map.put("UpdateSql", baseClaaPatch + "db2.UpdateSql4DB2");
		Map<String, String> oracleMap = new HashMap<String, String>();
		oracleMap.put("DBHandle", baseClaaPatch + "oracle.OracleHandle");
		oracleMap.put("QuerySql", baseClaaPatch + "oracle.QuerySql4Oracle");
		oracleMap.put("UpdateSql", baseClaaPatch + "oracle.UpdateSql4Oracle");
		dbClassPath.put(DBTypes.DB2, db2Map);
		dbClassPath.put(DBTypes.ORACLE, oracleMap);
		dbType = DBTypes.ORACLE;
	}

}
