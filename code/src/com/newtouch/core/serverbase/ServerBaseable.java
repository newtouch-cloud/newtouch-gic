package com.newtouch.core.serverbase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;

public interface ServerBaseable {

	/**
	 * @param user
	 *            操作员
	 */
	public void setUser(User user);

	/**
	 * 
	 * @return 操作员
	 */
	public User user();

	/**
	 * 
	 * @param pageCount
	 *            分页
	 */
	public void setPageCount(PageCount pageCount);

	/**
	 * 
	 * @return 分页对象
	 */
	public PageCount getPageCount();

	public ThreadLocal<ServerObj> getThreadLocal();

	/**
	 * 设置数据库连接。
	 * 
	 * @param dbHandle
	 *            数据库操作
	 */
	public void setDBHandle(DBHandleable dbHandle);
	public IUserMgMtVOModel queryOptPathInfo(IUserMgMtVOModel model);

}
