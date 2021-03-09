package com.newtouch.newutil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

/**
 * 集合类公用方式
 * 
 * @author shuai.ma@newtouch.cn
 *
 */
public class CollectionUtil {

	/**
	 * 深度复制map
	 * 
	 * @param source
	 *            被复制对象
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<?, ?> cloneMap(Map<?, ?> source) {
		Map target = null;
		try {
			target = source.getClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("克隆对象时创建新对象失败", e);
		}
		for (Object key : source.keySet()) {
			Object value = source.get(key);
			if (value instanceof Map) {
				target.put(key, CollectionUtil.cloneMap((Map) value));
				continue;
			}
			if (value instanceof List) {
				target.put(key, CollectionUtil.cloneList(((List) value)));
				continue;
			}
			if (CollectionUtil.isBaseType(value)) {
				target.put(key, value);
				continue;
			}
			if (value instanceof Cloneable) {
				try {
					Method method = value.getClass().getMethod("clone");
					Object newValue = method.invoke(value);
					target.put(key, newValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			}

			Object newValue = null;
			try {
				newValue = value.getClass().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("克隆对象时创建新对象失败", e);
			}
			BeanUtils.copyProperties(value, newValue);
			target.put(key, newValue);
		}
		return target;

	}

	/**
	 * 深度复制List
	 * 
	 * @param source
	 *            被复制对象
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<?> cloneList(List<?> source) {
		List target = null;
		try {
			target = source.getClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("克隆对象时创建新对象失败", e);
		}
		for (Object value : source) {
			if (value instanceof Map) {
				target.add(CollectionUtil.cloneMap((Map) value));
				continue;
			}
			if (value instanceof List) {
				target.add(CollectionUtil.cloneList(((List) value)));
				continue;
			}
			if (CollectionUtil.isBaseType(value)) {
				target.add(value);
				continue;
			}
			Object newValue = null;
			try {
				newValue = value.getClass().newInstance();
			} catch (Exception e) {
				throw new RuntimeException("克隆对象时创建新对象失败", e);
			}
			BeanUtils.copyProperties(value, newValue);
			target.add(newValue);
		}
		return target;
	}

	public static boolean isBaseType(Object arg) {
		return arg instanceof String || arg instanceof Boolean || arg instanceof Short || arg instanceof Integer || arg instanceof Long
				|| arg instanceof Float || arg instanceof Double || arg instanceof Character;
	}
}
