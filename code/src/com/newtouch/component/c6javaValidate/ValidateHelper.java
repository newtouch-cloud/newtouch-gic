package com.newtouch.component.c6javaValidate;

import org.apache.commons.lang.StringUtils;

import com.newtouch.component.c11assistant.BusinessException;


public class ValidateHelper {
  
	/**
	 * 校验身份证号格式是否正确
	 * @param str
	 * @return
	 */
	public static boolean isCheckCnId(String str) {
		boolean result = false;
		String regex = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
		if (str.matches(regex)) {result = true;}
		return result;
	}
	
	/**
	 * 校验电话格式是否匹配
	 * @param str
	 * @return
	 */
	public static boolean IsCheckTel(String str) {
		boolean result = false;
		String regex = "(^[0-9]{3,4}\\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\\([0-9]{3,4}\\)[0-9]{7,8}$)|(^0{0,1}1[0-9]{10}$)";
		if (str.matches(regex)) {result = true;}
		return result;
	}
	
	/**
	 * 校验邮编格式是否匹配
	 * @param str
	 * @return
	 */
	public static boolean IsCheckPost(String str) {
		boolean result = false;
		String regex = "^[1-9]\\d{5}$";
		if (str.matches(regex)) {result = true;}
		return result;
	}
	
	/**
	 * 校验Email格式是否匹配
	 * @param str
	 * @return
	 */
	public static boolean IsCheckEmail(String str) {
		boolean result = false;
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		if (str.matches(regex)) {result = true;}
		return result;
	}
	
	/**
	 * 判断是否为数字
	 * @param value
	 * @return
	 */
	public static Boolean isNumber(String str) {
		try {
			Double.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 判断两个字符串的值是否相等
	 * @param s1 字符串1
	 * @param s2 字符串2
	 * @return 相等则返回true
	 */
	public static boolean isEqual(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return true;
		} else if (s1 != null) {
			return s1.equals(s2);
		}
		return false;
	}	

	/**
	 * 判断字符串是否为空，空格字符串也视为空
	 * @param s  判断的字符串
	 * @return 为空时返回true
	 */
	public static boolean isNull(String s) {
		return StringUtils.isBlank(s);
	}
	
	
	/** 判断字符串是否为空，空格字符串也视为空
	 * 如果为空，则抛出异常，异常信息为msgStr
	 * @param s
	 * @param msgStr
	 * @return
	 */
	public static void isNullThrowException(String str,String msgStr) {
		if(isNull(str)){
			throw new BusinessException(msgStr);
		}
	}


	
	
	/**
	 * 判断字符串是否为空，空格字符串也视为空
	 * @param s  判断的字符串
	 * @return 为空时返回true
	 */
	public static boolean isNotNull(String s) {
		return !isNull(s);
	}
	
	/** 
	 * 判断对象是否为null或者为空 
	 *  
	 * @param object 
	 *            判断的对象 
	 * @return 
	 */  
	public static boolean IsNullOrEmpty(Object object) {  
		return object == null || "".equals(object.toString().trim());  
	}  
	/** 
	 * 判断对象是否为null或者为空 
	 * 如果为空，则抛出异常，异常信息为msgStr
	 * @param s
	 * @param msgStr
	 * @return
	 */  
	public static void  IsNullOrEmptyThrowException(Object object,String msgStr){
		if (IsNullOrEmpty(object)) {
			throw new BusinessException(msgStr);
		}
	}
	
	
	/**
	 * 判断字符串在字符数组内是否存在
	 * @param strs
	 * @param str
	 * @return
	 */
	public static boolean isHave(String[] strs,String str){
		for(String s : strs){
			if(isEqual(s,str)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断字符串是否存在中文
	 * @param str
	 * @return
	 */
	public static boolean checkChinese(String str){     
	       
	    return str.getBytes().length == str.length() ? false : true ;    
	} 
}
	
	
	