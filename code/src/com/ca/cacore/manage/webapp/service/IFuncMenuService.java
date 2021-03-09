package com.ca.cacore.manage.webapp.service;

import java.util.List;

import com.ca.cacore.manage.model.bo.IFuncMenuModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;


public interface IFuncMenuService {
	public ReturnMsg queryAllFuncMenu(IFuncMenuModel model);
	public ReturnMsg getFuncMenu(IFuncMenuModel model);
	public ReturnMsg addFuncMenu(IFuncMenuModel model,IUserModel user) throws BusinessException;
	public ReturnMsg updateFuncMenu(IFuncMenuModel model) throws BusinessException;
	public ReturnMsg deleteFuncMenu(IFuncMenuModel model);
	public List<String> funcMenuTree();
}
