package com.newtouch.core.dbconnection.handle;

public interface QuerySqlable extends Sqlable {

	/**
	 * 设置要查询出的字段。相当于SQL中select以后from以前中间的值，不需要加表名。
	 * <P>
	 * 如果为空时表示select * from dual。
	 * 
	 * @param field
	 *            要查询的字段
	 * 
	 */
	public void setField(String field);

	/**
	 * 获取要查询的字段。
	 * 
	 * @return 要查询的字段
	 */
	public String getField();

	/**
	 * 设置排序条件
	 * 
	 * @param orderBy
	 *            查询条件
	 */
	public void setOrderBy(String orderBy);

	/**
	 * 是否分页
	 * <p>
	 * 默认为true 分页
	 * 
	 * @return true 分页<br>
	 *         false 不分页
	 */
	public boolean isPaginate();

	/**
	 * 设置分页信息
	 * 
	 * @param paginate
	 *            true 分页.默认;<br>
	 *            false 不分页
	 */
	public void setPaginate(boolean paginate);

	/**
	 * 使用程序中的排序条件，或使用系统默认的排序条件
	 * 
	 * @return true：使用程序中的排序条件(默认)<br>
	 *         false：使用系统默认的排序条件
	 */
	public boolean isLocalOrder();

	/**
	 * 设置使用程序中的排序条件，或使用系统默认的排序条件。
	 * 
	 * @param localOrder
	 *            true：使用程序中的排序条件)<br>
	 *            false：使用系统默认的排序条件(默认)
	 */
	public void setLocalOrder(boolean localOrder);

}
