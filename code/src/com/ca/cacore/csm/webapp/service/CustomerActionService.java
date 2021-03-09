package com.ca.cacore.csm.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.domain.CustomerAndCustomerContact.ICustomerDomain;
import com.ca.cacore.csm.domain.customerAction.ICustomerActionDomain;
import com.ca.cacore.csm.model.bo.ICustomerActionModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerActionVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c14export.CatchExportSql;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.stringutil.StringUtil;



/**
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户行为Service
*/
@Service
public class CustomerActionService implements ICustomerActionService {
	
	@Autowired private ICustomerActionDomain customerActionDomain; 
	@Autowired private ICustomerDomain customerDomain ;
	@Autowired private CatchExportSql catchExportSql; //导出操作
	

	/** 
	* 
	* @param model
	* @param user
	* @return 
	* @description:添加客户行为信息
	*/
	public ReturnMsg addCustomerAction(ICustomerActionModel model,IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			if(StringUtil.isNull(model.getCustomer_id())){
				throw new BusinessException("客户代码不可为空，请核实!");
			}
			if(StringUtil.isNull(model.getAction_type())){
				throw new BusinessException("行为类型不可为空，请核实!");
			}
			if(StringUtil.isNull(model.getAction_notes())){
				throw new BusinessException("行为描述不可为空，请核实!");
			}
			if(StringUtil.isNull(model.getBranch_id())){
				throw new BusinessException("机构代码不可为空，请核实!");
			}

			customerActionDomain.addCustomerAction(model, user);
			returnMsg.setSuccessMessage("客户行为录入成功");
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:分页查询客户行为信息
	*/
	public ReturnMsg queryList(ICustomerActionVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ICustomerActionModel> list = customerActionDomain.queryAll(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
	/** 
	* 
	* @param model
	* @return 
	* @description:查询单个客户行为信息（以客户号）
	*/
	public CustomerViewVOModel queryForAction(ICustomerModel model) {
		
		return customerActionDomain.queryForAction(model);
	}

	/**
	 * 
	* 2014-1-6 16:06张晨
	* @param modle
	* @return ReturnMsg
	* @description:用户行为明细操作方法
	 */
	public ReturnMsg queryForDetail(ICustomerModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		CustomerViewVOModel model10 = customerActionDomain.queryForDetail(model);
		returnMsg.setDataTable(TransHelper.obj2map(model10));
		return returnMsg;
	}


}
