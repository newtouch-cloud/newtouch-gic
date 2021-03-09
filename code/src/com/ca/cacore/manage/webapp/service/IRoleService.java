package com.ca.cacore.manage.webapp.service;

import java.util.List;

import com.ca.cacore.manage.model.bo.IRoleAuthsModel;
import com.ca.cacore.manage.model.bo.IRoleModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IRoleService {

	public ReturnMsg queryAllRole(IRoleModel model);
	public ReturnMsg getRole(IRoleModel model);
	public ReturnMsg addRole(IRoleModel model,IUserModel user) throws BusinessException;
	public ReturnMsg updateRole(IRoleModel model,IUserModel user) throws BusinessException;
	public ReturnMsg getRoleAuthsAll(IRoleModel model,IUserModel user);
	public ReturnMsg addRoleAuths(List<IRoleAuthsModel> modellist,IUserModel user);
	public Integer queryForVerifyData(IRoleModel model);
}