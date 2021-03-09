package com.ca.cacore.ams.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.domain.IRegulationManagerDomain;
import com.ca.cacore.ams.domain.IRegulationStatusDomain;
import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class RegulationStatusService extends ServerBase implements IRegulationStatusService {

	@Autowired private IRegulationStatusDomain regulationStatusDomain;
	@Autowired private IRegulationManagerDomain regulationManagerDomain;

	/** 
	* 
	* @param model
	* @param user
	* @return ReturnMsg
	* @description: 更新规章制度信息表中的流程状态 
	*/
	@Override
	public ReturnMsg updateRegulationStatus(IPreserveRegVOModel model,
			IUserModel user) {
           ReturnMsg returnMsg = new ReturnMsg();
           try{
        	   regulationStatusDomain.updateRegulationStatus(model, user);//调用domain层更新规章制度信息表中的流程状态
			//根据seq_id查询招募人员简历信息
			for(int i=0; i<model.getSeq_ids().length; i++){
				model.setSeq_id(model.getSeq_ids()[i]);
			}
			List<IPreserveRegVOModel> listModel= regulationManagerDomain.queryStatus(model);//在查询一次规章制度表
			returnMsg.setDataList(TransHelper.list2MapList(listModel));
		   returnMsg.setSuccessMessage("发布成功");
		 }catch(BusinessException e){
			 returnMsg.setFailMessage(e.getMessage(), true);
		 }
        //回显数据
   		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}
	

}
