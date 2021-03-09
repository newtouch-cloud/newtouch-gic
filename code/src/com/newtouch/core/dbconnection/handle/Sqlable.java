package com.newtouch.core.dbconnection.handle;

public interface Sqlable {
	/**
	 * 获取拼接好的SQL。
	 * <P>
	 * 格式为select setSelect from (setSQL) order by setOrderBy;
	 * 
	 * @return String
	 */
	public String getPrintSql();

	/**
	 * 获取拼接好的SQL。
	 * <P>
	 * 格式为select setSelect from (setSQL) order by setOrderBy;
	 * 
	 * @return String
	 */
	public String getSql();

	/**
	 * 设置查询语句
	 * <p>
	 * 带问号的格式。
	 * 
	 * @param sql
	 *            查询语句
	 */
	public void setSql(String sql);

	/**
	 * 向SQL中加入参数
	 * <p>
	 * 执行语句后会清空设置的参数
	 * 
	 * @param para
	 *            加入参数
	 */
	public void add(Object para);

	/**
	 * 清空参数
	 */
	public void clearParam();

	/**
	 * 设置查询参数。
	 * 
	 * @param paramList
	 *            查询参数
	 */
	public void setParameter(Object[] paramList);

}
