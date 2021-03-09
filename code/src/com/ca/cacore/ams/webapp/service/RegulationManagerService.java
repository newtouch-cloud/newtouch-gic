package com.ca.cacore.ams.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.domain.IRegulationManagerDomain;
import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class RegulationManagerService extends ServerBase implements IRegulationManagerService {

	@Autowired private IRegulationManagerDomain regulationManagerDomain;

	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description: 查询规章制度信息(全部记录)
	*/
	 
    /**
     * 查询返回多条数据，返回类型为一个list集合
     */
	@Override
	public ReturnMsg queryList(IPreserveRegVOModel model) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPreserveRegVOModel> list = regulationManagerDomain.queryList(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description: 查询规章制度信息(待发布的记录)
	*/
	@Override
	public ReturnMsg queryStatus(IPreserveRegVOModel model) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPreserveRegVOModel> list = regulationManagerDomain.queryStatus(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg addRegulationBasic(IPreserveRegVOModel model,
			IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		boolean flag=regulationManagerDomain.checkRegulationIdUnique(model);
		//字段长度校验
		checkLength(model);
		try{
			//非空及唯一性校验
			ValidateHelper.IsNullOrEmptyThrowException(model.getRegulation_id(), "规章编号不可为空!");
			ValidateHelper.IsNullOrEmptyThrowException(model.getRegulation_name(), "规章名称不可为空!");
			ValidateHelper.IsNullOrEmptyThrowException(model.getMakers(), "制定人不可为空!");
			ValidateHelper.IsNullOrEmptyThrowException(model.getMake_time(), "制定时间不可为空!");
			if(flag==false){
				//判断输入的规章编号是否唯一(如果前台的js失效时，可以后台进行校验规章编号是否唯一)
				throw new BusinessException("规章编号存在");
			}
			regulationManagerDomain.addRegulationBasic(model, user);
			//设置保存成功信息
			returnMsg.setSuccessMessage("保存成功");
		}catch(BusinessException e){
			//保存失败，设置失败信息
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}
    
    /**
     * 查询明细返回一条数据，返回类型为一个model对象
     */
	@Override
	public ReturnMsg getRegulationView(IPreserveRegVOModel model) {
		//初始化返回参数
		ReturnMsg msg = new ReturnMsg();
		IPreserveRegVOModel pr= regulationManagerDomain.getRegulationView(model);
		//将一个model对象转换为map对象放在返回对象上返回给controller层
		msg.setDataTable(TransHelper.obj2map(pr));
		return msg;
	}

	@Override
	public ReturnMsg modifyRegulation(IPreserveRegVOModel model, IUserModel user)
			throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();
		//字段长度校验
		checkLength(model);
		//非空校验
		try{
			//非空校验
			ValidateHelper.IsNullOrEmptyThrowException(model.getRegulation_name(), "规章名称不可为空!");
			ValidateHelper.IsNullOrEmptyThrowException(model.getMakers(), "制定人不可为空!");
			ValidateHelper.IsNullOrEmptyThrowException(model.getMake_time(), "制定时间不可为空!");
			regulationManagerDomain.modifyRegulation(model, user);
			returnMsg.setSuccessMessage("修改成功");
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		//回显数据
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}
	
	@Override
	public Boolean checkRegulationIdUnique(IPreserveRegVOModel model) {
		return regulationManagerDomain.checkRegulationIdUnique(model);
	}
	
	/** 
	* 
	* @param model
	* @throws BusinessException void
	* @description:字段长度校验
	*/
	public void checkLength(IPreserveRegVOModel model) throws BusinessException{
		if(model.getRegulation_id().length()>20){
			throw new BusinessException("规章编号长度太大！");
		}
		if(model.getRegulation_name().length()> 400){
			throw new BusinessException("规章名称长度太大！");
		}
		if(model.getRegulation_description().length()>1000){
			throw new BusinessException("规章描述长度太大！");
		}
		if(model.getMakers().length()>50){
			throw new BusinessException("制定人长度太大！");
		}
	}
    
 
	

}
