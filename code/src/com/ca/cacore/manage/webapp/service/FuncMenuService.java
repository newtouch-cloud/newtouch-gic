package com.ca.cacore.manage.webapp.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.domain.funcmenu.IFuncMenuDomain;
import com.ca.cacore.manage.model.bo.FuncMenuModel;
import com.ca.cacore.manage.model.bo.IFuncMenuModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;


/**
 * 
 * @author 王得胜
 * @since 2013-11-10
 * @discrible 功能菜单Service层
 */
@Service
public class FuncMenuService extends ServerBase implements IFuncMenuService{

	@Autowired private IFuncMenuDomain FuncMenuDomain;
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象
	 * @return  返回一个ReturnMsg对象
	 * @discrible 查询出所有的功能菜单model
	 */
	public ReturnMsg queryAllFuncMenu(IFuncMenuModel model){
		ReturnMsg msg = new ReturnMsg();
		List<IFuncMenuModel> relist = FuncMenuDomain.queryAllFuncMenu(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象
	 * @return  返回一个ReturnMsg对象
	 * @discrible 查询指定参数的一个功能菜单model
	 */
	public ReturnMsg getFuncMenu(IFuncMenuModel model){
		ReturnMsg msg = new ReturnMsg();		
		IFuncMenuModel funcMenuModel=  FuncMenuDomain.getFuncMenu(model);
		msg.setDataTable(TransHelper.obj2map(funcMenuModel));
		return msg;
	}
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象，user对象
	 * @return  返回一个ReturnMsg
	 * @discrible 添加一个功能菜单model，或根据条件查询
	 */
	public ReturnMsg addFuncMenu(IFuncMenuModel model,IUserModel user) throws BusinessException{
		ReturnMsg reutn = new ReturnMsg();
		if (ValidateHelper.isNull(model.getMenu_id())) {
			reutn.setFailMessage("菜单代码 不能为空", true);
		}else if (ValidateHelper.isNull(model.getMenu_name())) {
			reutn.setFailMessage("菜单名称不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_type())) {
			reutn.setFailMessage("菜单类型不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_uritype())) {
			reutn.setFailMessage("菜单访问类型不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_uri())) {
			reutn.setFailMessage("菜单访问路径不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_opentype())) {
			reutn.setFailMessage("菜单打开方式不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_dispath())) {
			reutn.setFailMessage("菜单显示路径不能为空", true);
		}
		else if (ValidateHelper.IsNullOrEmpty(model.getMenu_disorder())) {
			reutn.setFailMessage("菜单显示顺序不能为空", true);
		}
		if(!reutn.isSuccessflag()){ 
		reutn.setDataTable(TransHelper.obj2map(model));//将model数据带回页面
		return reutn;     //未通过校验，回显
		}
		else{
			IFuncMenuModel model_menu_id =  new FuncMenuModel();
			IFuncMenuModel model_menu_name = new FuncMenuModel();
			model_menu_id.setMenu_id(model.getMenu_id().trim());
			model_menu_name.setMenu_name(model.getMenu_name().trim());
			IFuncMenuModel model1 =  FuncMenuDomain.getFuncMenu(model_menu_id);//根据menu_id查询出相应的model
			IFuncMenuModel model2 =  FuncMenuDomain.getFuncMenu(model_menu_name);//根据menu_name查询出相应的model
			if(model1 != null){        //判断menu_id这条数据存不存在
				reutn.setFailMessage("菜单id号已存在,核对后重新操作!", true);
				reutn.setDataTable(TransHelper.obj2map(model));//将model数据带回页面
			} 
			else if(model2 != null){         //判断menu_name这条数据存不存在
				reutn.setFailMessage("菜单名称已存在,核对后重新操作!", true);
				reutn.setDataTable(TransHelper.obj2map(model));//将model数据带回页面
			}
			else{
				FuncMenuDomain.addFuncMenu(model,user);//校验通过，继续添加
				reutn.setSuccessMessage("保存成功");
			}
			return reutn;//添加成功，重新刷新添加页面
		}
	}
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象，user对象
	 * @return  返回一个ReturnMsg
	 * @discrible 更新一个功能菜单model
	 */
	public ReturnMsg updateFuncMenu(IFuncMenuModel model) throws BusinessException{
		ReturnMsg reutn = new ReturnMsg();
		if (ValidateHelper.isNull(model.getMenu_id())) {
			reutn.setFailMessage("菜单代码 不能为空", true);
		}else if (ValidateHelper.isNull(model.getMenu_name())) {
			reutn.setFailMessage("菜单名称不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_type())) {
			reutn.setFailMessage("菜单类型不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_uritype())) {
			reutn.setFailMessage("菜单访问类型不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_uri())) {
			reutn.setFailMessage("菜单访问路径不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_opentype())) {
			reutn.setFailMessage("菜单打开方式不能为空", true);
		}
		else if (ValidateHelper.isNull(model.getMenu_dispath())) {
			reutn.setFailMessage("菜单显示路径不能为空", true);
		}
		else if (ValidateHelper.IsNullOrEmpty(model.getMenu_disorder())) {
			reutn.setFailMessage("菜单显示顺序不能为空", true);
		}
		if(!reutn.isSuccessflag()){ 
		reutn.setDataTable(TransHelper.obj2map(model));//将model数据带回页面
		return reutn;     //未通过校验，回显
		}
		else{
			IFuncMenuModel model_menu_id = new FuncMenuModel();
			IFuncMenuModel model_menu_name = new FuncMenuModel();
			model_menu_id.setMenu_id(model.getMenu_id().trim());
			model_menu_name.setMenu_name(model.getMenu_name().trim());
			IFuncMenuModel model1 = FuncMenuDomain.getFuncMenu(model_menu_id);// 根据menu_id查询出相应的model
			IFuncMenuModel model2 = FuncMenuDomain.getFuncMenu(model_menu_name);// 根据menu_name查询出相应的model
			if (model1 != null) {
				if (!model1.getSeq_id().equals(model.getSeq_id())) { // 判断menu_id这条数据是同一条,如果是就允许修改。
					reutn.setFailMessage("菜单id号已存在,核对后重新操作!", true);
					reutn.setDataTable(TransHelper.obj2map(model));// 将model数据带回页面
					return reutn;
				}
			}
			if (model2 != null) {
				if (!model2.getSeq_id().equals(model.getSeq_id())) { // 判断menu_name这条数据存不存在
					reutn.setFailMessage("菜单名称已存在,核对后重新操作!", true);
					reutn.setDataTable(TransHelper.obj2map(model));// 将model数据带回页面
					return reutn;
				}
			} 
				
		FuncMenuDomain.updateFuncMenu(model);
		reutn.setSuccessMessage("修改成功!");
		return reutn;//修改成功，重新刷新添加页面
		}
	}
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象，user对象
	 * @return  返回一个ReturnMsg
	 * @discrible 删除一个功能菜单model
	 */
	public ReturnMsg deleteFuncMenu(IFuncMenuModel model){
        FuncMenuDomain.deleteFuncMenu(model);
		return null;
	}
	/**
	 * 
	 */
	@Override
	public List<String> funcMenuTree() {
		// TODO Auto-generated method stub
		List<IFuncMenuModel> list = FuncMenuDomain.funcMenuTree();
		String root="";//根节点
		String childnode="";//子节点
		List<String> lstTree = new ArrayList<String>(); 
		for(IFuncMenuModel im: list){
			if(im.getMenu_parentid()=="null"||"null".equals(im.getMenu_parentid())||im.getMenu_parentid()==null){
				root="{id:'"+im.getMenu_id()+"', pId:0, name:'"+im.getMenu_name()+"' , open:'false'}"; 
				lstTree.add(root);
			}else {
				childnode="{id:'"+im.getMenu_id()+"', pId:'"+im.getMenu_parentid()+"', name:'"+im.getMenu_name()+"' , open:'false'}"; 
				lstTree.add(childnode);
			}
		}
		return lstTree;
	}
	
}
