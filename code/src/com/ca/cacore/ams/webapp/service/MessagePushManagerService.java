package com.ca.cacore.ams.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.domain.IMessagePushManagerDomain;
import com.ca.cacore.ams.model.vo.IMessagePushVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class MessagePushManagerService extends ServerBase implements IMessagePushManagerService{
	@Autowired private IMessagePushManagerDomain messagePushManagerDomain;

	@Override
	public ReturnMsg queryMessagePush(IMessagePushVOModel model) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		List<IMessagePushVOModel> list = messagePushManagerDomain.queryList(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg queryMessagePushByTaskId(IMessagePushVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IMessagePushVOModel object = messagePushManagerDomain.queryById(model);
		returnMsg.setDataTable(TransHelper.obj2map(object));
		return returnMsg;
	}

	@Override
	public ReturnMsg saveMessagePushModify(IMessagePushVOModel model) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		 try{
			 messagePushManagerDomain.saveMessagePushModify(model);
			 returnMsg.setSuccessMessage("修改成功");
		 }catch(BusinessException e){
			 returnMsg.setFailMessage(e.getMessage(), true);
			}
		 return returnMsg;
	}

	@Override
	public String queryMessagePushStatus(IMessagePushVOModel model) {
		List<IMessagePushVOModel> listVO=messagePushManagerDomain.queryMessagePushStatus(model);
		String returnInfo="";
		if (listVO.size()==0) {
			returnInfo="{isSuccess:"+false+"}";
		}else {
			returnInfo="{isSuccess:"+true+"}";
			for(int i=0;i<listVO.size();i++){
                if(i==listVO.size()-1){
                	returnInfo+=listVO.get(i).getTask_content()+"|"+listVO.get(i).getTask_proce_date()+"|"+listVO.get(i).getTaskid();	
                	break;
				}
				returnInfo+=listVO.get(i).getTask_content()+"|"+listVO.get(i).getTask_proce_date()+"|"+listVO.get(i).getTaskid()+":";		
			}
		}
		return returnInfo;
	}

	@Override
	public ReturnMsg dealMessagePush(IMessagePushVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			messagePushManagerDomain.dealMessagePush(model);
			returnMsg.setSuccessMessage("处理完毕");
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
		}
		return returnMsg;
	}

}
