package com.newtouch.component.c11assistant;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.utils.dateutil.DateUtil;

public class ModelHelper {
	/**
	 * 获取对象
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static Object getModel(Object obj, HttpServletRequest req)
			throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException {
		// 获取对象类
		Class<? extends Object> class1 = obj.getClass();
		// 获得某个类的所有申明的字段，即包括public、private和proteced，
		// 但是不包括父类的申明字段。
		Field[] fields = class1.getDeclaredFields();
		// 遍历数组
		for (Field f : fields) {
			// 去除静态变量和私有变量
			if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) {
				continue;
			}
			f.setAccessible(true);
			// 获取变量名
			String name = f.getName();
			// 获取变量属性
			Class<?> type = f.getType();
			String string = type.toString();
			// 判断变量属性，根据属性处理数据
			if (string.contains("java.lang.Integer")) {
				Integer nullToStr = null;
				if (ActionHelper.getNullToStr(req.getParameter(name)) != "") {
					nullToStr = Integer.valueOf(ActionHelper.getNullToStr(req.getParameter(name)));
				}
				// 把此变量对应的值赋值给对象变量
				f.set(obj, nullToStr);
			} else if (string.contains("Date")) {
				Date nullToStr = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter(name)));
				f.set(obj, nullToStr);
			} else if (string.contains("PageCount")) {
				PageCount nullToStr = ActionHelper.getPage(req);
				f.set(obj, nullToStr);
			} else if (string.contains("String")) {
				String nullToStr = ActionHelper.getNullToStr(req.getParameter(name));
				f.set(obj, nullToStr);
			} else if (string.contains("Long")) {
				Long nullToStr = null;
				if (ActionHelper.getNullToStr(req.getParameter(name)) != "") {
					nullToStr = Long.valueOf(ActionHelper.getNullToStr(req.getParameter(name)));
				}
				f.set(obj, nullToStr);
			} else if (string.contains("Double")) {
				Double nullToStr = null;
				if (ActionHelper.getNullToStr(req.getParameter(name)) != "") {
					 nullToStr = Double.valueOf(ActionHelper.getNullToStr(req.getParameter(name)));
				}
				f.set(obj, nullToStr);
			} else if (string.contains("Float")) {
				Float nullToStr = null;
				if (ActionHelper.getNullToStr(req.getParameter(name)) != "") {
					 nullToStr = Float.valueOf(ActionHelper.getNullToStr(req.getParameter(name)));
				}
				f.set(obj, nullToStr);
			}
		}
		return obj;
	}

}
