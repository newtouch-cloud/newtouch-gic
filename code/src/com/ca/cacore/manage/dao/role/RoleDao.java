package com.ca.cacore.manage.dao.role;



import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IRoleAuthsModel;
import com.ca.cacore.manage.model.bo.IRoleModel;
import com.ca.cacore.manage.model.vo.RolePrivilege;
import com.newtouch.core.daobase.BaseDao;

@Component
public class RoleDao extends BaseDao implements IRoleDao{

	@Override
	public List<IRoleModel> queryAllRole(IRoleModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("role.queryRole_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IRoleModel>)this.getSqlMapClientTemplate().queryForList("role.queryRole", model);
	}

	@Override
	public IRoleModel getRole(IRoleModel model) {
		return (IRoleModel)this.getSqlMapClientTemplate().queryForObject("role.queryBySeqId", model);
	}

	@Override
	public IRoleModel addRole(IRoleModel model) {

		this.getSqlMapClientTemplate().insert("role.insertRole",model);
		return null;
	}

	@Override
	public boolean updateRole(IRoleModel model) {
		this.getSqlMapClientTemplate().update("role.updateRole",model);
		return true;
	}

	@Override
	public boolean deleteRole(IRoleModel model) {
		this.getSqlMapClientTemplate().delete("role.deleteRole",model);
		return true;
	}


	@Override
	public IRoleModel getOnlyRoleByParams(IRoleModel model) {
		return (IRoleModel)this.getSqlMapClientTemplate().queryForObject("role.queryOnlyModelByOP", model);
	}
	
	
	/*
	 * 查询菜单和按钮
	 */
	public List<RolePrivilege> getRolePrivilegeAll(IRoleModel model){
		return (List<RolePrivilege>)this.getSqlMapClientTemplate().queryForList("role.selectRolePrivilege",model);
	}
	
	public boolean deleteRoleAuths(IRoleAuthsModel model){
		this.getSqlMapClientTemplate().delete("role.deleteRoleAuths",model);
		return true;
	}
	

	
	public boolean addRoleAuths(IRoleAuthsModel model){
		this.getSqlMapClientTemplate().insert("role.saveRoleAuths",model);
		return true;
	}

	@Override
	public List<IRoleAuthsModel> getRoleAuthsList(IRoleAuthsModel model){
		return (List<IRoleAuthsModel>)this.getSqlMapClientTemplate().queryForList("role.queryRoleAuths",model);
	}

	/**
	 * 2013-12-20新添加方法 BY 张晨
	 * 校验前台输入的数据是否已存在 
	 * @param model
	 * @return Integer
	 */
	public Integer queryForVerifyData(IRoleModel model) {
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("role.queryForVerifyData",model);
	}
	

	
}
