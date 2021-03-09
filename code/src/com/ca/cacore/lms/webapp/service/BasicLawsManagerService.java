package com.ca.cacore.lms.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.lms.domain.IBasicLawsManagerDomain;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:
*/
@Service
public class BasicLawsManagerService implements IBasicLawsManagerService {
	@Autowired private IBasicLawsManagerDomain basicLawsManagerDomain;
	
	/** 
	* 获得所有基本法的信息
	* @param model
	* @return ReturnMsg
	* @description:
	*/
	@Override
	public ReturnMsg getAllBasicLawsInfo(IBasicLawsVOModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setDataList(TransHelper.list2MapList(basicLawsManagerDomain.getAllBasicLawsInfo(model)));
		return returnMsg;
	}

	/** 
	* 新增基本法
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg addBasicLaws(IBasicLawsVOModel model,IUserModel user){
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			basicLawsManagerDomain.addBasicLaws(model,user);
		} catch (BusinessException e) {
			//如果有校验不符合，就捕获，并设置返回信息
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		// 设置返回参数
		returnMsg.setSuccessMessage("保存成功");
		return returnMsg;
	}
	
	/** 
	* 查询基本法明细信息
	* @param model
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg getBasiclawView(IBasicLawsVOModel model){
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataTable(TransHelper.obj2map(basicLawsManagerDomain.getBasiclawView(model)));
		return returnMsg;
	}
	
	/** 
	* 修改基本法信息
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg modBasicLaws(IBasicLawsVOModel model,IUserModel user){
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			basicLawsManagerDomain.modBasicLaws(model, user);
		} catch (BusinessException e) {
			//如果有异常，捕获并设置回显
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		//设置返回参数
		returnMsg.setSuccessMessage("修改成功");
		return returnMsg;
	}
	
	/** 
	* 删除基本法的信息
	* @param serno
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg delBasicLaws(String serno){
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			basicLawsManagerDomain.delBasicLaws(serno);
		} catch (BusinessException e) {
			//如果有异常，捕获并设置回显
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		//设置返回参数
		returnMsg.setSuccessMessage("删除成功");
		return returnMsg;
	}
}
