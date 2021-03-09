package com.newtouch.component.axis2.service;

import com.newtouch.component.axis2.util.FeeInfoUtil;
import com.newtouch.component.axis2.util.SalesInfoUtil;

/**
* @since:    2014年4月14日   
* @author    ZhangChen
* @description:axis2 服务类 
 */
public class QuerySaleInfo {
	
	/**
	 * 服务类
	* @param sales_id  中介人员id
	* @param agreemengt_no 协议号
	* @return String 
	* @description: 接收客户端传递过来的中介人员id和协议号进行逻辑判断并返回相应信息
	 */
	
	public String querySaleInfo(String xml){
		
		SalesInfoUtil siu = new SalesInfoUtil(); //调用方法类
		
		return siu.analysisXML(xml);  //analysisXML返回的是一个封装好的String的xml格式数据
	}
	
	/**
	 * 服务类
	* @param 
	* @param 手续费对账请求报文
	* @return String 
	* @description:手续费对账接口
	 */
	
	public String checkFeeInfo(String xml){
		
		System.out.println("------------------开始调用----------------");
		
		FeeInfoUtil siu = new FeeInfoUtil(); //调用方法类
		
		System.out.println("------------------结束调用----------------");
		return siu.analysisXML(xml);  //analysisXML返回的是一个封装好的String的xml格式数据
		
	}
	
	
	
}
