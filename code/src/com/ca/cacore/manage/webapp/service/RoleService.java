package com.ca.cacore.manage.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.domain.funcmenu.IFuncMenuDomain;
import com.ca.cacore.manage.domain.role.IRoleDomain;
import com.ca.cacore.manage.model.bo.IRoleAuthsModel;
import com.ca.cacore.manage.model.bo.IRoleModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.RoleModel;
import com.ca.cacore.manage.model.vo.FuncMenuVOModel;
import com.ca.cacore.manage.model.vo.IFuncMenuVOModel;
import com.ca.cacore.manage.model.vo.RoleAuthsMenu;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class RoleService extends ServerBase implements IRoleService{

	@Autowired private IRoleDomain roleDomain;
	@Autowired private IFuncMenuDomain funcMenuDomain;

	public ReturnMsg queryAllRole(IRoleModel model){
		ReturnMsg msg = new ReturnMsg();
		List<IRoleModel> relist = roleDomain.queryAllRole(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}

	
	public ReturnMsg getRole(IRoleModel model){
		ReturnMsg msg = new ReturnMsg();
		IRoleModel relist = roleDomain.getRole(model);
		msg.setDataTable(TransHelper.obj2map(relist));
		return msg;
	}
	
	public ReturnMsg addRole(IRoleModel model,IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			roleDomain.addRole(model, user);
			returnMsg.setSuccessMessage("数据插入成功！");
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}
		
		

	
	public ReturnMsg updateRole(IRoleModel model,IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			roleDomain.updateRole(model, user);
			returnMsg.setSuccessMessage("数据更新成功！");
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}
	


	
	/*
	 *查询所有的菜单和按钮
	 **/
	public ReturnMsg getRoleAuthsAll(IRoleModel model,IUserModel user) {
		ReturnMsg msg = new ReturnMsg();
//		List<RoleAuthsMenu> relist= roleDomain.getRoleAuthsAll(model);
		IFuncMenuVOModel funcModel = new FuncMenuVOModel();
		funcModel.setMenu_type(user.getUser_type());
		List<IFuncMenuVOModel> relist = funcMenuDomain.queryAllFuncMenuPrior(funcModel);
		msg.setDataList(TransHelper.list2MapList(relist));
//		msg.setParameter("relist", relist);
		return msg;
	}



	public ReturnMsg addRoleAuths(List<IRoleAuthsModel> modellist,IUserModel user) {
		ReturnMsg msg = new ReturnMsg();
		try{
			roleDomain.addRoleAuths(modellist, user);
			IRoleModel model = new RoleModel();
			model.setRole_id(modellist.get(0).getRole_id());
			List<RoleAuthsMenu> relist = roleDomain.getRoleAuthsAll(model);
			msg.setParameter("relist", relist);
			msg.setSuccessMessage("操作成功!");
		}catch(BusinessException e){
			msg.setFailMessage(e.getMessage(), true);
		}
		return msg;
	}
	
	/**
	 * 2013-12-20新添加方法 BY 张晨
	 * 校验前台输入的数据是否已存在 
	 * @param model
	 * @return Integer
	 */
	@Override
	public Integer queryForVerifyData(IRoleModel model) {
		return roleDomain.queryForVerifyData(model);
	}
	
}
