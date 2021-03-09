package com.newtouch.core.dao.factory;

public interface IDaoFactory {
	/**
	 * 添加基础列
	 * <p>
	 * 添加每个表都需要有的列，像唯一序号、创建人、创建时间、修改人、修改时间等信息。
	 * 
	 * @param clmName
	 */
	public void addBaseClm(String clmName);

	/**
	 * 
	 * @param dbSourceType
	 *            数据库连接地址
	 * @param daoDir
	 *            生成类的package
	 */
	public void generationDao(String dbSourceType, String daoDir);

	/**
	 * 添加需要生成的表
	 * 
	 * @param table
	 *            表名
	 */
	public void addTables(String table);

}
