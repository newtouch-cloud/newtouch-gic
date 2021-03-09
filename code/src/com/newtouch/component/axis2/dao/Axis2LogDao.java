package com.newtouch.component.axis2.dao;

import org.springframework.stereotype.Component;

import com.newtouch.component.axis2.model.Axis2Log;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
* @since:    2014年4月17日   
* @author    ZhangChen
* @description:webservice log日志信息插入
 */
@Component
public class Axis2LogDao extends BaseDao implements IAxis2LogDao{

		/**
		 * 
		* @param model
		* @return ProtocolQueryVoModel
		* @description: querySaleInfo 查询中介人员是否存在 by张晨
		 */
		@Override
		public void insertAxis2Log(Axis2Log log){
			this.getSqlMapClientTemplate().insert("axis2Log.insertAxis2Log",log);
		}
	
}
