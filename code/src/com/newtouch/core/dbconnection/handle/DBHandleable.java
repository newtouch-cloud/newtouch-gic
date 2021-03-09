package com.newtouch.core.dbconnection.handle;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;

public interface DBHandleable {
	/**
	 * 设置事务开始
	 */
	public void startTransaction();

	/**
	 * 提交事务
	 */
	public void commitTransaction();

	/**
	 * 回滚事务
	 */
	public void rollbackTransaction();

	/**
	 * 执行查询多行记录
	 */
	public List<Map<String, Object>> queryList(QuerySqlable querySQL);

	/**
	 * 执行查询多行单列的记录
	 * 
	 * @param querySQL
	 * @return
	 */
	public List<Object> queryObjectList(QuerySqlable querySQL);

	/**
	 * 使用JDBC执行查询多行记录
	 * 
	 * @param querySql
	 * @return
	 */
	public List<Map<String, Object>> queryList4Jdbc(QuerySqlable querySql);

	/**
	 * 执行更新
	 */
	public int update(UpdateSqlable updateSQL);

	/**
	 * 执行查询一行记录
	 */
	public Map<String, Object> query(QuerySqlable querySQL);

	/**
	 * 查询字段类型
	 * 
	 * @param querySQL
	 * @return
	 */
	public Map<String, Integer> queryMetaData(QuerySqlable querySQL);

	/**
	 * 获取一个新的连接
	 * <p>
	 * 使用DataSource创建一个新的连接，此连接需要手工关闭。<br>
	 * 一般不要使用此方法。
	 * 
	 * @return 数据库链接
	 */
	public Connection getConnection();

	/**
	 * 获取当前连接
	 * 
	 * @return
	 */
	public Connection getCurConn();

	/**
	 * 设置数据源
	 * 
	 * @param dataSource
	 */

	public void setThreadLocal(ThreadLocal<ServerObj> threadLocal);

	public ThreadLocal<ServerObj> getThreadLocal();
}
