package com.newtouch.core.dbconnection.objfactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.newtouch.core.dbconnection.SpringFactory;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.serverbase.ServerBaseable;

public class ServerFactory {

	private static ServerFactory serverFactory;
	private static ThreadLocal<ServerObj> threadLocal = new ThreadLocal<ServerObj>();
	private Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
	private static String DEF_DATA_SOURCE = "dataSource";

	/**
	 * 获取不受事务控制的默认数据库链接
	 * <p>
	 * 需要自己关闭连接和控制事务。
	 * 
	 * @return 返回数据库链接
	 */
	public Connection getConnection() {
		return this.getConnection(DEF_DATA_SOURCE);
	}

	/**
	 * 获取不受事务控制的指定源数据库链接
	 * <p>
	 * 需要自己关闭连接和控制事务。
	 * 
	 * @return 返回数据库链接
	 */
	public Connection getConnection(String dataSource) {
		try {
			Connection con = dataSourceMap.get(dataSource).getConnection();
			System.out
					.println("创建连接 ServerFactory getConnection" + "  |  "
							+ con.hashCode() + "  |  "
							+ Thread.currentThread().getId());
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ServerFactory() {
	}

	public synchronized static ServerFactory getServerFactory() {
		if (serverFactory == null) {
			serverFactory = new ServerFactory();
		}
		return serverFactory;
	}

	/**
	 * 初始化业务类
	 * 
	 * @param clazz
	 *            类
	 * @return 业务类对象
	 */
	@Deprecated
	public ServerBaseable getInstance(Class<ServerBaseable> clazz) {
		return this.getInstance(clazz.getCanonicalName());

	}

	/**
	 * 初始化业务类
	 * <p>
	 * 根据入参操作员对象、分页信息进行初始化业务对象。<br>
	 * 若线程对象中已经存在数据连接，则使用此连接初始化对象。<br>
	 * 否则重新创建数据库连接。
	 * 
	 * @param server
	 *            要初始化的类
	 * @param user
	 *            当前操作员
	 * @param pageCount
	 *            分页信息
	 * @return 业务类对象
	 */
	public ServerBaseable getInstance(ServerBaseable server, User user,
			PageCount pageCount) {
		try {
			return this.getInstance(server, DEF_DATA_SOURCE, user, pageCount);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 初始化业务类
	 * <p>
	 * 若线程中已经存在分页对象、操作员对象，则使用此值赋给业务对象。<br>
	 * 若不存在则不进行初始化。
	 * 
	 * @param server
	 *            业务类
	 * @return 业务类对象
	 */
	public ServerBaseable getInstance(ServerBaseable server) {
		try {
			User user = null;
			PageCount pageCount = null;
			if (threadLocal.get() != null) {
				user = threadLocal.get().getUser();
				pageCount = threadLocal.get().getPageCount();
			}
			return this.getInstance(server, DEF_DATA_SOURCE, user, pageCount);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 初始化业务类
	 * <p>
	 * 若线程中已经存在分页对象、操作员对象，则使用此值赋给业务对象。<br>
	 * 若不存在则不进行初始化。
	 * 
	 * @param className
	 *            全类名
	 * @return 初始化完成的业务类对象
	 */
	@Deprecated
	public ServerBaseable getInstance(String className) {
		try {
			return this.getInstance((ServerBaseable) Class.forName(className)
					.newInstance(), DEF_DATA_SOURCE, threadLocal.get()
					.getUser(), threadLocal.get().getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 初始化业务类
	 * 
	 * @param className
	 *            业务类全类名
	 * @param user
	 *            当前操作员
	 * @param pageCount
	 *            分页信息
	 * @return 业务类对象
	 */
	public ServerBaseable getInstance(String className, User user,
			PageCount pageCount) {
		try {
			return this.getInstance((ServerBaseable) Class.forName(className)
					.newInstance(), DEF_DATA_SOURCE, user, pageCount);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 初始化业务类
	 * <p>
	 * 使用入参对象初始化业务类，若入参操作员对象、分页对象如何为空则不进行初始化。
	 * 
	 * @param server
	 *            业务类
	 * @param dataSource
	 *            数据源
	 * @param user
	 *            操作员对象
	 * @param pageCount
	 *            分页信息
	 * @return 初始化完成的业务对象
	 */
	public ServerBaseable getInstance(ServerBaseable server, String dataSource,
			User user, PageCount pageCount) {
		try {
			ServerObj dataBase = threadLocal.get();
			if (dataBase == null) {
				dataBase = new ServerObj();
				dataBase.setPageCount(new PageCount());
			}
			if (user != null) {
				dataBase.setUser(user);
			}
			if (pageCount != null) {
				dataBase.setPageCount(pageCount);
			}
			if (dataBase.getConnection() == null
					|| dataBase.getConnection().isClosed()) {
				// Connection con =
				// this.getDataSource(dataSource).getConnection();
				// Ulog.debug("ServerFactory getInstance创建连接" + " | "
				// + con.hashCode() + " | "
				// + Thread.currentThread().getId());
				// dataBase.setConnection(con);
				dataBase.setSourceType(dataSource);
				dataBase.setDataSoruce(this.getDataSource(dataSource));
			}
			threadLocal.set(dataBase);
			DBHandleable jdbcDao = DBHandleCreator.getInstance().getDBHandle(
					threadLocal);
			server.setDBHandle(jdbcDao);

			if (user != null) {
				server.setUser(user);
			}
			if (pageCount != null) {
				server.setPageCount(pageCount);
			}
			return server;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public synchronized DataSource getDataSource(String dataSource) {
		DataSource ds = this.dataSourceMap.get(dataSource);
		if (ds != null) {
			return ds;
		}
		ds = SpringFactory.getInstance().getDataSource(dataSource);
		this.dataSourceMap.put(dataSource, ds);
		return ds;
	}

	public synchronized void setDataSource(String sourceName,
			DataSource dataSource) {
		this.dataSourceMap.put(sourceName, dataSource);
	}
}