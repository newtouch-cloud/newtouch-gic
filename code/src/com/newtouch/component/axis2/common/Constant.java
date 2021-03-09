package com.newtouch.component.axis2.common;

/**
* @since:    2014年4月15日   
* @author    ZhangChen
* @description:axis2 常量类
 */
public class Constant {
	/**
	 * 0 失败的请求
	 */
	public static final String CHECKFLAG_FAILURE = "0";
	/**
	 * 1 请求成功
	 */
	public static final String CHECKFLAG_SUCCESS = "1";
	/**
	 * 00 xml解析错误,或者Date日期格式错误
	 */
	public static final String XML_ERROR = "00";
	/**
	 * 01 报文头解析验证失败
	 */
	public static final String TRANDHEAD_ERROR = "01";
	/**
	 * 02 报文体解析验证失败
	 */
	public static final String TRANDBODY_ERROR = "02";
	/**
	 * 03 不存在该代理人编码
	 */
	public static final String NO_SALES_ERROR = "03";
	/**
	 * 04 不存在该协议号
	 */
	public static final String NO_AGREEMENT_ERROR = "04";
	/**
	 * 05 该人员不能跨机构出单
	 */
	public static final String SALESBRANCH_ERROR = "05";
	/**
	 * 06-验证成功
	 */
	public static final String CHECK_SUCCESS = "06";
	
	/**
	 * 0 表示销售人员的机构号没有对应的协议号 
	 */
	public static final String AGREEMENT_FLAG_NO = "0";
	/**
	 * 1 表示销售人员的机构号有对应的协议号
	 */
	public static final String AGREEMENT_FLAG_YES = "1";

}
