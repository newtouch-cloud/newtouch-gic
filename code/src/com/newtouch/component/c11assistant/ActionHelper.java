package com.newtouch.component.c11assistant;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.serverbase.ServerBase;

public class ActionHelper {

	/**
	 * 获取界面分页对象
	 * @param req
	 * @return
	 */
	public static PageCount getPage(HttpServletRequest req){
		int nowPage=Integer.parseInt(getNullToZero(req.getParameter("nowPage")));
		return new PageCount().setNowPage(nowPage);
	}
	
	/**
	 * 获取界面分页对象
	 * @param req
	 * @return
	 */
	public static PageCount getPage(){
		PageCount pageCount = new ServerBase().threadLocal.get().getPageCount();
		if(pageCount==null){pageCount=new PageCount(); }
		return pageCount;
	}
	
	/**
	 * 将空对象，并未字符0
	 * @param obj
	 * @return
	 */
	private static String getNullToZero(Object obj){
		String s="0";
		if(obj!=null && !obj.equals("")){
			s=String.valueOf(obj);
		}
		
		return s;
	}
	
	/**
	 * 将空对象变为空字符串
	 * @param obj
	 * @return
	 */
	public static String getNullToStr(Object obj){
		String s="";
		if(obj!=null && !obj.equals("")){
				//去除提交数据文本中的空格及换行符
			    try {
			    	s=URLDecoder.decode(String.valueOf(obj).trim().replaceAll("%", "%25"),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		}

		return s;
	}
	
	/**
	  * 字符串编码转换的实现方法
	  * @param str  待转换编码的字符串
	  * @param newCharset 目标编码
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	 public static String changeCharset(String str, String newCharset)
	   throws UnsupportedEncodingException {
	  if (str != null) {
	   //用默认字符编码解码字符串。
	   byte[] bs = str.getBytes();
	   //用新的字符编码生成字符串
	   return new String(bs, newCharset);
	  }
	  return null;
	 }
	 

	
	/**
	 * 将空对象变为空字符串
	 * @param obj
	 * @return
	 */
	public static Integer getNullToInteger(Object obj){
		Integer s=0;
		if("".equals(obj)){
			return null;
		}
		if(obj!=null && !obj.equals("")){
			s=Integer.parseInt(getNullToZero(obj));
		}

		return s;
	}
	

	/**
	 * 将空对象变为空字符串
	 * @param obj
	 * @return
	 */
	public static Double getNullToDouble(Object obj){
		Double s=0D;
		if("".equals(obj)){
			return null;
		}
		if(obj!=null && !obj.equals("")){
			s=Double.parseDouble(getNullToZero(obj));
		}

		return s;
	}
	
	/**
	 * 获取user对象
	 * @param req
	 * @return
	 */
	public static IUserModel getUserFromSession(HttpServletRequest req){
		return (IUserModel)req.getSession().getAttribute("user");
	}
	
	
	/**
	 * 
	* @param d
	* @return String
	* @description: 把数字类型转换成字符串
	 */
	public static String toStr(Double d){
    	DecimalFormat df = new DecimalFormat("###0.00#");//最多保留几位小数，就用几个#，最少位就用0来确定  
    	if(d==null){
    		return "0.00";
    	}
    	String s=df.format(d); 
    	
    	return s;
    }
    
	public static String toStr(Integer d){
    	DecimalFormat df = new DecimalFormat("###0");//最多保留几位小数，就用几个#，最少位就用0来确定  
    	if(d==null){
    		return "0";
    	}
    	String s=df.format(d); 
    	
    	return s;
    }
    
	public static String toStr(Object d){
    	DecimalFormat df = new DecimalFormat("###0.00#");//最多保留几位小数，就用几个#，最少位就用0来确定  
    	if(d==null){
    		return "0.00";
    	}
    	
    	String s=df.format(new Double((String)d)); 
    	
    	return s;
    }
    
	public static String toStr(Date d){
		
    	if(d == null){
    		return "";
    	}
    	return d.toString();
    }
}
