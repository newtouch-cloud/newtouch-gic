package com.ca.cacore.csm.dao.customerAction;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.csm.model.bo.ICustomerActionModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerActionVOModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户行为信息Dao
 */

@Repository
public class CustomerActionDao extends BaseDao implements ICustomerActionDao{

	/**
	 * 
	* 
	* @param model
	* @return 
	* @description:增加客户行为信息
	 */
	public boolean addCustomerAction(ICustomerActionModel model) {
		this.getSqlMapClientTemplate().insert("customerAction.insertCustomerAction",model);
		return true;
	}

	/**
	 * 
	* 
	* @param model
	* @return 
	* @description:分页查询客户行为信息
	 */
	public List<ICustomerActionModel> queryAll(ICustomerActionVOModel model) {
		int count  = (Integer)this.getSqlMapClientTemplate().queryForObject("customerAction.queryCount",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<ICustomerActionModel>)this.getSqlMapClientTemplate().queryForList("customerAction.queryAll", model);
		
	}
	
	/**
	 * 
	* 2014-1-6 15:50
	* @param model
	* @return CustomerViewVOModel
	* @description: 用于客户行为录入客户信息查询
	 */
	public CustomerViewVOModel queryForAction(ICustomerModel model) {
		
		return (CustomerViewVOModel)this.getSqlMapClientTemplate().queryForObject("customerAction.queryForAction", model);
	}

	/**
	 * 
	* 2014-1-6 16:09张晨
	* @param modle
	* @return ReturnMsg
	* @description:用户行为明细操作方法
	 */
	public CustomerViewVOModel queryForDetail(ICustomerModel model) {

		return (CustomerViewVOModel)this.getSqlMapClientTemplate().queryForObject("customerAction.queryForDetail", model);
	}


	
}
