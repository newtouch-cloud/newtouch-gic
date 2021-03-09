package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.ICustomeGDDomain;
import com.ca.cacore.rsss.model.vo.ICustomeGDVOModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public class CustomeGDService implements ICustomeGDService{
		
@Autowired private ICustomeGDDomain icustomegddomain;
	
	/**
	* @param model
	* @return ReturnMsg
	* @description:查询客户群体分布表
	 */
	@Override
	public ReturnMsg queryCustomeGD(ICustomeGDVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ICustomeGDVOModel> list = icustomegddomain.queryCustomeGD(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	/**
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:导出客户群体分布表
	 */
	@Override
	public List<ICustomeGDVOModel> exportCustomeGD(
			ICustomeGDVOModel model) {
		List<ICustomeGDVOModel> list = icustomegddomain.exportCustomeGD(model);
		return list;
	}
}
