package com.newtouch.core.dbconnection.handle;

public interface UpdateSqlable extends Sqlable {

	/**
	 * 
	 * @return 获取参数值
	 */
	public Object[] getParamList();

}
