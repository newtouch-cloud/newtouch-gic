package com.newtouch.utils.junit;

import java.util.List;
import java.util.Map;

import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.dbconnection.objfactory.ServerFactory;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.junit.initxmldata.InitXmlData;

public class SMSTester extends ServerBase {
	private static SMSTester TESTER = null;
	private static ServerFactory FACTORY = null;
	private static User USER = null;
	private static InitXmlData initData = null;
	static {
		if (TESTER == null) {
			initTester();
		}
	}

	public List<Map<String, Object>> queryList(QuerySqlable querySql) {
		if (TESTER == null)
			initTester();
		return TESTER.dbHandle().queryList(querySql);
	}

	/**
	 * 设置事务开始
	 */
	public void startTransaction() {
		TESTER.dbHandle().startTransaction();
	}

	/**
	 * 回滚事务
	 */
	public void rollbackTransaction() {
		TESTER.dbHandle().rollbackTransaction();
	}

	/**
	 * 设置事务提交
	 */
	public void commit() {
		initData.commit();
		TESTER.dbHandle().commitTransaction();

	}

	public Map<String, Object> query(QuerySqlable querySql) {
		if (TESTER == null)
			initTester();
		return TESTER.dbHandle().query(querySql);

	}

	public List<Map<String, Object>> queryList4Jdbc(QuerySqlable querySql) {
		if (TESTER == null)
			initTester();
		return TESTER.dbHandle().queryList4Jdbc(querySql);
	}

	public int update(UpdateSqlable updateSql) {
		if (TESTER == null)
			initTester();
		return TESTER.dbHandle().update(updateSql);
	}

	/**
	 * 初始化数据。删除需初始化数据
	 * 
	 * @param patch
	 *            数据文件路径
	 */
	public void initData(String patch) {

		this.initData(patch, true);
	}

	/**
	 * 初始化数据。删除需初始化数据
	 * 
	 * @param clazz
	 *            同名数据文件路径
	 */
	@SuppressWarnings("rawtypes")
	public void initData(Class clazz) {
		this.initData(clazz, true);
	}

	/**
	 * 初始化数据。自动删除需初始化数据
	 * 
	 * @param patch
	 *            数据文件路径
	 * @param delDate
	 *            删除数据<br>
	 *            true：删除<br>
	 *            false：
	 */
	public void initData(String patch, boolean delDate) {
		initData.initData(patch, delDate);
	}

	/**
	 * 初始化数据。不删除需初始化数据
	 * 
	 * @param clazz
	 *            同名数据文件路径
	 * @param delDate
	 *            删除数据<br>
	 *            true：删除<br>
	 *            false：
	 */
	@SuppressWarnings("rawtypes")
	public void initData(Class clazz, boolean delDate) {
		initData.initData(clazz, delDate);
	}

	private static synchronized void initTester() {
		if (TESTER == null) {
			FACTORY = ServerFactory.getServerFactory();
			FACTORY.setDataSource("JUNIT", new JUnitDataSource());
			PageCount pageCount = new PageCount();
			pageCount.setNowPage(1);
			pageCount.setRows4Page(20);
			User user = new User();
			user.setOptID("ADMIN");
			user.setMenuID("junitTest");
			user.setFuncID("testBiz");
			user.setCurOptDeptList("000000", "0000");
			user.setDid("000000");
			USER = user;
			initData = (InitXmlData) FACTORY.getInstance(new InitXmlData());
			TESTER = (SMSTester) FACTORY.getInstance(
					"com.newtouch.utils.junit.SMSTester", user, pageCount);
		}
	}

	public void setUserID(String userID) {
		USER.setOptID(userID);
	}
}
