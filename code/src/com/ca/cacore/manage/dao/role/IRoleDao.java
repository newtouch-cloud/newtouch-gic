package com.ca.cacore.manage.dao.role;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.manage.model.bo.IRoleAuthsModel;
import com.ca.cacore.manage.model.bo.IRoleModel;
import com.ca.cacore.manage.model.vo.RolePrivilege;

@Repository
public interface IRoleDao {
	public List<IRoleModel>  queryAllRole(IRoleModel model);
	public IRoleModel getRole(IRoleModel model);
	public IRoleModel addRole(IRoleModel model);
	public boolean updateRole(IRoleModel model);
	public boolean deleteRole(IRoleModel model);
	public IRoleModel getOnlyRoleByParams(IRoleModel model);
	public List<RolePrivilege> getRolePrivilegeAll(IRoleModel model);
	public boolean deleteRoleAuths(IRoleAuthsModel model);
	public boolean addRoleAuths(IRoleAuthsModel model);
	public List<IRoleAuthsModel> getRoleAuthsList(IRoleAuthsModel model);
	public Integer queryForVerifyData(IRoleModel model);
}
