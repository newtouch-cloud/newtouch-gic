package com.newtouch.newutil.json;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassUtil {
	/**
	 * 获取本类的属性
	 */
	public static List<Field> getClassFields(Class<?> cla) {
		List<Field> fields = new ArrayList<Field>();
		// 获取本类的属性
		Field[] fieldArray = cla.getDeclaredFields();
		fields.addAll(Arrays.asList(fieldArray));
		// 判断如果父类不是Object,获取父类的方法
		Class<?> superClass = cla.getSuperclass();
		if (superClass != Object.class) {
			fields.addAll(getClassFields(superClass));
		}
		return fields;
	}

	/**
	 * 获取属性的泛型
	 * 
	 * @param field
	 * @return 返回该属性的泛型，否则返回null
	 */
	public static Class<?> getFieldGenericity(Field field) {
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) field.getGenericType();
			Class<?> c = (Class<?>) pt.getActualTypeArguments()[0];
			return c;
		}
		return null;
	}

	/**
	 * 判断是不是基本类型<br/>
	 * 基本类型：<br/>
	 * java.lang.String<br/>
	 * java.sql.Date<br/>
	 * java.util.Date<br/>
	 * java.util.Map<br/>
	 * java.util.HashMap<br/>
	 * java.lang.Integer<br/>
	 * java.lang.Double<br/>
	 * java.lang.Float<br/>
	 * 
	 * @param classType
	 * @return
	 */
	public static boolean isBaseType(Class<?> classType) {
		if (classType == String.class || classType == java.sql.Date.class || classType == java.util.Date.class || classType == Map.class
				|| classType == HashMap.class || classType == Integer.class || classType == Double.class || classType == Float.class) {
			return true;
		}
		return false;
	}
}
