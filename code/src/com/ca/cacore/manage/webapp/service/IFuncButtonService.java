package com.ca.cacore.manage.webapp.service;

import com.ca.cacore.manage.model.bo.IFuncButtonModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * @author guochunhua
 * @describle 功能按钮service层接口类
 */
public interface IFuncButtonService {
	public ReturnMsg queryAllFuncButtonCRUD(IFuncButtonModel model);
	public ReturnMsg getFuncButton(IFuncButtonModel model);
	public ReturnMsg addFuncButton(IFuncButtonModel model) throws BusinessException;
	public ReturnMsg updateFuncButton(IFuncButtonModel model) throws BusinessException;
	public ReturnMsg deleteFuncButton(IFuncButtonModel model);
}
