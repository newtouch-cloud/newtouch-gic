package com.ca.cacore.manage.domain.role;

import java.util.List;

import com.ca.cacore.manage.model.bo.IRoleAuthsModel;
import com.ca.cacore.manage.model.bo.IRoleModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.vo.RoleAuthsMenu;
import com.newtouch.component.c11assistant.BusinessException;

public interface IRoleDomain {

	public List<IRoleModel> queryAllRole(IRoleModel model);
	public IRoleModel getRole(IRoleModel model);
	public boolean addRole(IRoleModel model,IUserModel user) throws BusinessException;
	public boolean updateRole(IRoleModel model,IUserModel user)throws BusinessException;
	public  List<RoleAuthsMenu> getRoleAuthsAll(IRoleModel model);
	public boolean addRoleAuths(List<IRoleAuthsModel> model,IUserModel user) throws BusinessException;
	public Integer queryForVerifyData(IRoleModel model);
}