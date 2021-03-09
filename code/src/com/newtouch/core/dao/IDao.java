package com.newtouch.core.dao;

import java.util.List;
import java.util.Map;

public interface IDao {
	public int insert(Map<String, Object> param);

	public int insert(Map<String, Object> param, boolean autoQuery);

	public int update(Map<String, Object> where, Map<String, Object> set);

	public int update(String serno, Map<String, Object> set);

	public int update(Map<String, Object> set);

	public int delete(String serno);

	public int delete(Map<String, Object> where);

	public int delete();

	public int deleteFlag(String serno);

	public int deleteFlag(Map<String, Object> where);

	public int deleteFlag();

	public Map<String, Object> query(Map<String, Object> param);

	public Map<String, Object> query();

	public List<Map<String, Object>> queryList(Map<String, Object> param);

	/**
	 * 
	 * @param param
	 *            查询参数
	 * @param isPaginate
	 *            是否分页
	 *            <p>
	 *            true：分页<br>
	 *            false：不分页
	 * @return
	 */
	public List<Map<String, Object>> queryList(Map<String, Object> param,
			boolean isPaginate);

	public List<Map<String, Object>> queryList();

	public List<Map<String, Object>> queryList(boolean isPaginate);

	public boolean equalsData(String serno, Map<String, Object> data);

	/**
	 * 比较数据库中是否已经存在相同数据
	 * 
	 * @param where
	 *            查询条件
	 * @param data
	 *            被比较数据
	 * @return true 已存在相同数据<br>
	 *         false 不存在相同数据
	 */
	public boolean equalsData(Map<String, Object> where,
			Map<String, Object> data);

	public boolean equalsData(Map<String, Object> data);

	/**
	 * 获取query()中的查询结果，可用于重用，不需要多次查询
	 * 
	 * @return
	 */
	public Map<String, Object> getData();

	/**
	 * 设置排序条件
	 * 
	 * @param order
	 */
	public void setOrder(String order);

	public String getOrder();

	/**
	 * 单独设置=查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addEq(String filed, Object value);

	/**
	 * 单独设置!=查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addNotEq(String filed, Object value);

	/**
	 * 单独设置in查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addIn(String filed, List<Object> value);

	/**
	 * 单独设置not in查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addNotIn(String filed, List<Object> value);

	/**
	 * 单独设置like %?%查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addLike(String filed, Object value);

	/**
	 * 单独设置like ?%查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addLikeR(String filed, Object value);

	/**
	 * 单独设置like %?查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addLikeL(String filed, Object value);

	/**
	 * 单独设置not like %?%查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addNotLike(String filed, Object value);

	/**
	 * 单独设置not like ?%查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addNotLikeR(String filed, Object value);

	/**
	 * 单独设置not like %?查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addNotLikeL(String filed, Object value);

	/**
	 * 单独设置between and 查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addBetween(Object value, String filed1, String filed2);

	/**
	 * 单独设置小于(&lt)查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addLT(String filed, Object value);

	/**
	 * 单独设置大于(&gt)查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addGT(String filed, Object value);

	/**
	 * 单独设置小于等于(&lt=)查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addLTEq(String filed, Object value);

	/**
	 * 单独设置大于等于(&gt=)查询条件
	 * 
	 * @param filed
	 *            字段
	 * @param value
	 *            值
	 */
	public void addGTEq(String filed, Object value);

	/**
	 * 添加别名
	 * 
	 * @param field
	 *            字段
	 * @param as
	 *            别名
	 */
	public void addAs(String field, String as);

	/**
	 * 清空add的参数
	 */
	public void clearParam();

	/**
	 * 是否自动清空参数
	 * 
	 * @return true:自动清空<br>
	 *         false:不自动清空
	 */
	public boolean isClsParam();

	/**
	 * 设置自动清空参数
	 * <p>
	 * true:自动清空(默认)<br>
	 * false:不自动清空
	 * 
	 * @return
	 */
	public void setClsParam(boolean clsParam);

}
