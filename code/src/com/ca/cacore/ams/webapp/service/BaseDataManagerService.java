package com.ca.cacore.ams.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.domain.IBaseDataManagerDomain;
import com.ca.cacore.ams.model.vo.IBaseDataVOModel;
import com.ca.cacore.ams.model.vo.IMessagePushVOModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class BaseDataManagerService extends ServerBase implements IBaseDataManagerService{
	@Autowired private IBaseDataManagerDomain baseDataManagerDomain;

	@Override
	public ReturnMsg queryBaseData(IBaseDataVOModel model) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		List<IBaseDataVOModel> list = baseDataManagerDomain.queryBaseData(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg addBaseData(IBaseDataVOModel model) throws BusinessException{
		ReturnMsg reutn = new ReturnMsg();
		try{			
			baseDataManagerDomain.addBaseData(model);		
		    reutn.setSuccessMessage("保存成功");
		}catch(BusinessException e){
			reutn.setFailMessage(e.getMessage(), true);			
		}
		return reutn;// 添加成功，重新刷新添加页面
	}

	@Override
	public ReturnMsg modifyBaseData(IBaseDataVOModel model) {
		ReturnMsg reutn = new ReturnMsg();
		try{			
			baseDataManagerDomain.modifyBaseData(model);	
		    reutn.setSuccessMessage("修改成功");
		}catch(BusinessException e){
			reutn.setFailMessage(e.getMessage(), true);			
		}
		return reutn;
	}

	@Override
	public ReturnMsg deleteBaseData(IBaseDataVOModel model) {
		ReturnMsg reutn = new ReturnMsg();
		try{			
			baseDataManagerDomain.deleteBaseData(model);	
		    reutn.setSuccessMessage("删除成功");
		}catch(BusinessException e){
			reutn.setFailMessage(e.getMessage(), true);			
		}
		return reutn;
	}

	@Override
	public String getBaseDataId(IBaseDataVOModel model) {
		
		IBaseDataVOModel idVO = baseDataManagerDomain.getBaseDataId(model);
		if(null==idVO){
			return "";
		}
		String id=idVO.getId();
		return id;
	}

	@Override
	public ReturnMsg queryBaseDataById(IBaseDataVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IBaseDataVOModel object = baseDataManagerDomain.queryBaseDataById(model);
		if(object!=null){
			returnMsg.setDataTable(TransHelper.obj2map(object));
			return returnMsg;
		}
		return null;
	}

	@Override
	public String queryBaseDataByTypecode(IBaseDataVOModel model) {
		IBaseDataVOModel object = baseDataManagerDomain.queryBaseDataByTypecode(model);
		if(object!=null){
			return object.getTypecode();
		}
		return "";
	}

	@Override
	public String getBaseDataTypeid(IBaseDataVOModel model) {
		IBaseDataVOModel typeidVO = baseDataManagerDomain.getBaseDataTypeid(model);
		if(typeidVO!=null){
			return typeidVO.getTypeid();
		}
		return "";
	}

	@Override
	public ReturnMsg queryListBaseDataTypecode(IBaseDataVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IBaseDataVOModel> list = baseDataManagerDomain.queryListBaseDataTypecode(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg deleteBaseDataById(IBaseDataVOModel model) {
		ReturnMsg reutn = new ReturnMsg();
		try{			
			baseDataManagerDomain.deleteBaseDataById(model);	
		    reutn.setSuccessMessage("删除成功");
		}catch(BusinessException e){
			reutn.setFailMessage(e.getMessage(), true);			
		}
		return reutn;
	}
	
}
