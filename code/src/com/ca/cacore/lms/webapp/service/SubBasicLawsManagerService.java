package com.ca.cacore.lms.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.lms.domain.ISubBasicLawsManagerDomain;
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
public class SubBasicLawsManagerService implements ISubBasicLawsManagerService {
	@Autowired private ISubBasicLawsManagerDomain subBasicLawsManagerDomain;
	
	/** 
	* 获得所有基本法的信息
	* @param model
	* @return ReturnMsg
	* @description:
	*/
	@Override
	public ReturnMsg getAllSubBasicLawsInfo(IBasicLawsVOModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setDataList(TransHelper.list2MapList(subBasicLawsManagerDomain.getAllSubBasicLawsInfo(model)));
		return returnMsg;
	}

	/** 
	* 新增基本法
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg addSubBasicLaws(IBasicLawsVOModel model,IUserModel user){
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			subBasicLawsManagerDomain.addSubBasicLaws(model,user);
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
	public ReturnMsg getSubBasiclawView(IBasicLawsVOModel model){
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataTable(TransHelper.obj2map(subBasicLawsManagerDomain.getSubBasiclawView(model)));
		return returnMsg;
	}
	
	/** 
	* 修改基本法信息
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg modSubBasicLaws(IBasicLawsVOModel model,IUserModel user){
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			subBasicLawsManagerDomain.modSubBasicLaws(model, user);
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
	public ReturnMsg delSubBasicLaws(String serno){
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			subBasicLawsManagerDomain.delSubBasicLaws(serno);
		} catch (BusinessException e) {
			//如果有异常，捕获并设置回显
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		//设置返回参数
		returnMsg.setSuccessMessage("删除成功");
		return returnMsg;
	}
	/** 
	* @param model
	* @return ReturnMsg
	* @description:根据基本法代码查询信息
	*/
	public String getLawsInfo(String basiclaw_no){
		return subBasicLawsManagerDomain.getLawsInfo(basiclaw_no);
	}
}
