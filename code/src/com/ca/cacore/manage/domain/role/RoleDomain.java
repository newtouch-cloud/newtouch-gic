package com.ca.cacore.manage.domain.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.role.IRoleDao;
import com.ca.cacore.manage.model.bo.IRoleAuthsModel;
import com.ca.cacore.manage.model.bo.IRoleModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.RoleAuthsModel;
import com.ca.cacore.manage.model.bo.RoleModel;
import com.ca.cacore.manage.model.vo.RoleAuthsMenu;
import com.ca.cacore.manage.model.vo.RolePrivilege;
import com.ca.cacore.manage.model.vo.RolePrivilegeButton;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.stringutil.StringUtil;

@Service
public class RoleDomain extends ServerBase implements IRoleDomain{
	@Autowired private IRoleDao roleDao;
	
	public List<IRoleModel> queryAllRole(IRoleModel model){
		return roleDao.queryAllRole(model);
	}
	
	public IRoleModel getRole(IRoleModel model){
		return roleDao.getRole(model);
	}
	
	public boolean addRole(IRoleModel model,IUserModel user) throws BusinessException{
		
		if(StringUtil.isNull(model.getRole_name())){
			throw new BusinessException("角色名称不能为空，请检查"); 
		}else if(StringUtil.isNull(model.getRole_id())){
			throw new BusinessException("角色代码不能为空,请检查"); 
		}else if(StringUtil.isNull(model.getRole_type())){
			throw new BusinessException("角色类型不能为空,请检查"); 
		}
		
		IRoleModel model0 = new RoleModel();
		IRoleModel returnRoleModel = new RoleModel();
		model0.setRole_name(model.getRole_name());
		returnRoleModel=roleDao.getOnlyRoleByParams(model0);
		if(!ValidateHelper.IsNullOrEmpty(returnRoleModel)){
			if(model.getRole_id().equals(returnRoleModel.getRole_id())){
				throw new BusinessException("角色名称已存在，角色代码已存在,请检查"); 
			}else{
				throw new BusinessException("角色名称已存在，请检查"); 
			}
		}else{
			model0.setRole_name(null);
			model0.setRole_id(model.getRole_id());
			returnRoleModel=roleDao.getOnlyRoleByParams(model0);
			if(!ValidateHelper.IsNullOrEmpty(returnRoleModel)){
				throw new BusinessException("角色代码已存在,请检查"); 
			}else{
				model.setStatus("1");
				model.setCreateUser(user.getName());
				model.setModifyUser(user.getName());
				roleDao.addRole(model);
				return true;
			}
		}
		
	}
	
	public boolean updateRole(IRoleModel model,IUserModel user) throws BusinessException{
		if(StringUtil.isNull(model.getRole_name())){
			throw new BusinessException("角色名称不能为空，请检查"); 
		}else if(StringUtil.isNull(model.getRole_id())){
			throw new BusinessException("角色代码不能为空,请检查"); 
		}else if(StringUtil.isNull(model.getRole_type())){
			throw new BusinessException("角色类型不能为空,请检查"); 
		}
		IRoleModel model0 = new RoleModel();
		model0.setSeq_id(model.getSeq_id());
		model0.setRole_id(model.getRole_id());
		IRoleModel returnModel1 =roleDao.getOnlyRoleByParams(model0);
		
		model0.setRole_id(null);
		model0.setRole_name(model.getRole_name());
		IRoleModel returnModel2 =roleDao.getOnlyRoleByParams(model0);
			
		if(ValidateHelper.IsNullOrEmpty(returnModel1) && ValidateHelper.IsNullOrEmpty(returnModel2)){
			IRoleModel roleModel = roleDao.getRole(model);
			roleModel.setRemark(model.getRemark());
			roleModel.setRole_id(model.getRole_id());
			roleModel.setRole_name(model.getRole_name());
			roleModel.setRole_type(model.getRole_type());
			roleModel.setStatus(model.getStatus());
			roleModel.setModifyUser(user.getName());
			roleDao.updateRole(roleModel);
			return true;
		}else{
			if(!ValidateHelper.IsNullOrEmpty(returnModel1) && ValidateHelper.IsNullOrEmpty(returnModel2)){
				throw new BusinessException("角色代码已存在,请检查"); 
			}else if(!ValidateHelper.IsNullOrEmpty(returnModel2) && ValidateHelper.IsNullOrEmpty(returnModel1)){
				throw new BusinessException("角色名称已存在,请检查"); 
			}else{
				throw new BusinessException("角色代码和角色名称已存在,请检查");
			}
		}
		
	}
	




	
	/*
	 * 得出的数据由IRolePrivilege转换成List<RolePrivilegeMenu>数据，方便页面循环
	 */
	@Override
	public  List<RoleAuthsMenu> getRoleAuthsAll(IRoleModel model) {
		List<RolePrivilege> list =  roleDao.getRolePrivilegeAll(model);
		List<RoleAuthsMenu> rpmList =new ArrayList<RoleAuthsMenu>();
		
		for(int i=0,lengh=list.size();i<lengh;i++){
			RoleAuthsMenu rpm = new RoleAuthsMenu();
			String menu_id = list.get(i).getMenu_id();
			rpm.setMenu_name(list.get(i).getMenu_name());
			rpm.setMenu_id(menu_id);
			for(int j=i;j<lengh;j++){
				if(menu_id.equals(list.get(j).getMenu_id())){
					RolePrivilegeButton rpb = new RolePrivilegeButton();
					rpb.setButton_id(list.get(j).getButton_id());
					rpb.setButton_name(list.get(j).getButton_name());
					rpb.setSelected(list.get(j).getSelected());
					rpm.setButton(rpb);
					list.remove(j);
					j= j-1;
					lengh =lengh-1;
				}
			}
			i= i-1;
			rpmList.add(rpm);
		} 
		
		
		return rpmList;
	}

	@Override
	public boolean addRoleAuths(List<IRoleAuthsModel> modelList, IUserModel user)
			throws BusinessException {
		if(ValidateHelper.IsNullOrEmpty(modelList)){
			throw new BusinessException("连接异常!");
		}
		
		IRoleAuthsModel modelDelete = modelList.get(0);
		roleDao.deleteRoleAuths(modelDelete);
		
		for(IRoleAuthsModel model0:modelList){
			if(!StringUtil.isNull(model0.getRole_id())
				&& !StringUtil.isNull(model0.getMenu_id())
				&& !StringUtil.isNull(model0.getButton_id())
					){
				IRoleAuthsModel model = new RoleAuthsModel();
				model.setCreateUser(user.getName());
				model.setModifyUser(user.getName());
				model.setStatus("1");
				model.setRole_id(model0.getRole_id());
				model.setMenu_id(model0.getMenu_id());
				model.setButton_id(model0.getButton_id());
				model.setRemark("");
				roleDao.addRoleAuths(model);
			}
			
		}
		return true;
	}


	/**
	 * 2013-12-20新添加方法 BY 张晨
	 * 校验前台输入的数据是否已存在 
	 * @param model
	 * @return Integer
	 */
	public Integer queryForVerifyData(IRoleModel model) {
		
		return roleDao.queryForVerifyData(model);
	}

	
	
}
