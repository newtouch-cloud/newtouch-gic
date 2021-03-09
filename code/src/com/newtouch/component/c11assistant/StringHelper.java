package com.newtouch.component.c11assistant;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


import net.sf.json.JSONObject;

import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.utils.stringutil.StringUtil;
import com.rt.BASE64Encoder;

/**
 * C11_5 - String Helper Component
 * 字符处理助手
 * @author sln
 *
 */
public class StringHelper {
	public static final String SEED_UPSTR  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String SEED_LOWSTR = "abcdefghijklmnopqrstuvwxyz";
	public static final String SEED_NUM    = "1234567890";
	public static final String SEED_STR    = SEED_UPSTR+SEED_LOWSTR;
	public static final String SEED_NUMSTR = SEED_UPSTR+SEED_LOWSTR+SEED_NUM;
	public static final String SEED_LANDL = SEED_LOWSTR+SEED_NUM;
	
	/**
	 * 获取随机字符串
	 * @param length
	 * @return
	 */
	public static String randomString(int length,String seed) {
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < length; i++) {  
	        int number = random.nextInt(seed.length());  
	        sb.append(seed.charAt(number));  
	    }
	    return sb.toString();  
	}
	
	/**
	 * 将空对象变为空字符串
	 * @param obj
	 * @return
	 */
	public static String getNullToStr(Object obj){
		String s="";
		if(obj!=null && !obj.equals("")){
			s=String.valueOf(obj);
		}
		return s;
	}
	
	
	/**
	 * 将空对象，并未字符0
	 * @param obj
	 * @return
	 */
	public static String getNullToZero(Object obj){
		String s="0";
		if(obj!=null && !obj.equals("")){
			s=String.valueOf(obj);
		}
		return s;
	}
	
	/**
	 * 格式化浮点数
	 *
	 * @param format 格式 如"##.##"
	 * @param f 要格式化的浮点数
	 * @return
	 */
	public static String formatFloat(String format, double f) {
		StringBuffer formatString = new StringBuffer(format);
		java.text.DecimalFormat df = new java.text.DecimalFormat(formatString.toString());
		return df.format(f);
	}

	
	/**
	 * 从字符串得到整型数组
	 * @param str 输入的字符串
	 * @param regx 分割字符串的表达式
	 * @return 整型数组，如果为空则返回长度为0的数组，不返回null
	 */
	public static int[] getIntArrayFromStr(String str, String regx) {
		if (StringUtil.isNull(str)) {
			return new int[0];
		}
		String[] days = str.split(regx);
		Set<String> set = new TreeSet<String>();
		for (int i = 0; i < days.length; i++) {
			if (!StringUtil.isNull(days[i])) {
				set.add(days[i]);
			}
		}
		int[] ret = new int[set.size()];
		int i = 0;
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			try {
				ret[i] = Integer.parseInt(it.next());
			}
			catch (Exception e) {
				ret[i] = 0;
			}
			i++;
		}
		return ret;
	}

	
	/**
	 * 将LIST转为json
	 * 
	 * @param list
	 * @return
	 */
	public static String transList2Json(List list) {
		if (list != null) {
			Map map = new HashMap();
			map.put("list", list);
			map.put("flag", "success");
			String str = JSONObject.fromObject(map).toString();
//			System.out.println(str);
			return str;
		}
		return null;
	}
	
	/**
	 * 将String转为json
	 * @param list
	 * @return
	 */
	public static String transString2Json(String str){
		if(str!=null){
			Map map = new HashMap();
			map.put("str",str);
			return JSONObject.fromObject(map).toString();
		}
		return null;
	} 
	
	  /** 
	   * 获取对象的String值 
	   *  
	   * @param object 
	   *            操作的对象 
	   * @return 
	   */  
	  public static String getStrValue(Object object) {  
	    if (ValidateHelper.IsNullOrEmpty(object)) {  
	      return "";  
	    } else {  
	      return object.toString().trim();  
	    }  
	  }  
	  
	  /** 利用MD5进行加密 
	   * @param str   待加密的字符串 
	   * @return   加密后的字符串 
	   * @throws NoSuchAlgorithmException   没有这种产生消息摘要的算法 
	   * @throws UnsupportedEncodingException   
	   */ 
	  public static String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		  //确定计算方法 
		  MessageDigest md5=MessageDigest.getInstance("MD5"); 
		  BASE64Encoder base64en = new BASE64Encoder();   
		  String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));//加密后的字符串 
		  return newstr; 
	  }
	  
	  /** 判断用户密码是否正确 
	　　  * @param newpasswd   用户输入的密码 
	　　  * @param oldpasswd   数据库中存储的密码－－用户密码的摘要 
	　　  * @return 
	　　  * @throws NoSuchAlgorithmException 
	　　  * @throws UnsupportedEncodingException 
	　　 */ 
	  public boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{ 
		  if(encoderByMd5(newpasswd).equals(oldpasswd)) 
			  return true; 
		  else 
			  return false; 
	  } 
}