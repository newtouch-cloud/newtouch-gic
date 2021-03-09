package com.newtouch.core.dbconnection;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.newtouch.core.context.SpringContext;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.log.Ulog;

@Component
public class MultipleDataSources extends ServerBase {
	private static Map<String, DBHandleable> DB_MAP = new HashMap<String, DBHandleable>();

	/**
	 * 初始化数据库操作对象
	 * 
	 * @param dbSourceType
	 *            数据源类型
	 * @param dataSource
	 *            数据源对象
	 * @param dbHandle
	 *            数据库操作对象
	 */
	public synchronized DBHandleable initDBHandle(String dbSourceType) {
		if (DB_MAP.isEmpty()) {
			DB_MAP.put("defaultDataSource", this.dbHandle());
		}
		if ("".equals(dbSourceType)) {
			dbSourceType = "defaultDataSource";
		}
		if (DB_MAP.get(dbSourceType) != null) {
			if (DB_MAP.get(dbSourceType).getThreadLocal() == null)
				DB_MAP.get(dbSourceType).setThreadLocal(
						this.initThreadLocal(dbSourceType));
			if (DB_MAP.get(dbSourceType).getThreadLocal().get() == null)
				DB_MAP.get(dbSourceType).setThreadLocal(
						this.initThreadLocal(dbSourceType));
			DB_MAP.get(dbSourceType).startTransaction();
			return DB_MAP.get(dbSourceType);
		}
		ThreadLocal<ServerObj> uamsTL = this.initThreadLocal(dbSourceType);
		DBHandleable dbHandle = DBHandleCreator.getInstance().getDBHandle(
				uamsTL);
		dbHandle.startTransaction();
		DB_MAP.put(dbSourceType, dbHandle);
		return dbHandle;
	}

	private ThreadLocal<ServerObj> initThreadLocal(String dbSourceType) {
		ThreadLocal<ServerObj> uamsTL = new ThreadLocal<ServerObj>();
		if (this.getThreadLocal().get() == null) {
			Ulog.debug("");
		}
		uamsTL.set(this.getThreadLocal().get());
		uamsTL.get().setSourceType(dbSourceType);
		DataSource dataSource = (DataSource) SpringContext
				.getBean(dbSourceType);
		uamsTL.get().setDataSoruce(dataSource);
		return uamsTL;
	}
}
