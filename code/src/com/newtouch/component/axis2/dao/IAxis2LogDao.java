package com.newtouch.component.axis2.dao;

import com.newtouch.component.axis2.model.Axis2Log;


/**
 * 
* @since:    2014年4月17日   
* @author    ZhangChen
* @description:webservice log日志处理接口
 */
public interface IAxis2LogDao{

		/**
		 * 
		* @param model void
		* @description:插入service处理的log信息
		 */
		public void insertAxis2Log(Axis2Log log);
	
}
