package com.ca.cacore.manage.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.domain.funcbutton.IFuncButtonDomain;
import com.ca.cacore.manage.model.bo.FuncButtonModel;
import com.ca.cacore.manage.model.bo.IFuncButtonModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.stringutil.StringUtil;
/**
 * @author guochunhua
 * @since 2013-11-14
 * @describle 功能按钮service层
 */
@Service
public class FuncButtonService implements IFuncButtonService{
	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个ReturnMsg对象
	 *@discrible 查询所有的功能按钮信息
	 */
	@Autowired private IFuncButtonDomain funcButtonDomain;
	@Override
	public ReturnMsg queryAllFuncButtonCRUD(IFuncButtonModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IFuncButtonModel> relist =funcButtonDomain.queryAllFuncButton(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个ReturnMsg对象
	 *@discrible 查询一个功能按钮的信息
	 */
	@Override
	public ReturnMsg getFuncButton(IFuncButtonModel model) {
		ReturnMsg msg = new ReturnMsg();
		IFuncButtonModel bm = funcButtonDomain.getFuncButton(model);
		msg.setDataTable(TransHelper.obj2map(bm));
		return msg;
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个ReturnMsg对象
	 *@discrible 添加功能按钮
	 */
	@Override
	public ReturnMsg addFuncButton(IFuncButtonModel model) throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();
		if(ValidateHelper.isNull(model.getMenu_id())){
			returnMsg.setFailMessage("菜单ID不能为空！", true);
		}else if(ValidateHelper.isNull(model.getButtonId())){
			returnMsg.setFailMessage("按钮ID不能为空！", true);
		}else if(ValidateHelper.isNull(model.getButtonName())){
			returnMsg.setFailMessage("按钮名字不能为空！", true);
		}
		IFuncButtonModel model_menu_id = new FuncButtonModel();
		IFuncButtonModel model_button_id = new FuncButtonModel();
		model_menu_id.setMenu_id(model.getMenu_id().trim());
		model_button_id.setButtonId(model.getButtonId().trim());
		
		Integer int_button_id  = funcButtonDomain.getFuncButtonInt(model_button_id);//根据button_id查询出相应的model
		 if(int_button_id>0){         //判断button_id这条数据存不存在
			returnMsg.setFailMessage("按钮id已存在,核对后重新操作!", true);
		}
		else{
			funcButtonDomain.addFuncButton(model);//校验通过，继续添加
			returnMsg.setSuccessMessage("保存成功");
		}
		return returnMsg;//添加成功，重新刷新添加页面
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个ReturnMsg对象
	 *@discrible 修改功能按钮
	 */
	@Override
	public ReturnMsg updateFuncButton(IFuncButtonModel model) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		if(StringUtil.isNull(model.getMenu_id())){
			returnMsg.setFailMessage("菜单ID不能为空！", true);
		}
		if(StringUtil.isNull(model.getButtonId())){
			returnMsg.setFailMessage("按钮ID不能为空！", true);
		}
		if(StringUtil.isNull(model.getButtonName())){
			returnMsg.setFailMessage("按钮名字不能为空！", true);
		}
		if(StringUtil.isNull(model.getStart())){
			returnMsg.setFailMessage("按钮状态不能为空！", true);
		}
		IFuncButtonModel model_menu_id = new FuncButtonModel();
		IFuncButtonModel model_button_id = new FuncButtonModel();
		model_menu_id.setMenu_id(model.getMenu_id().trim());
		model_button_id.setButtonId(model.getButtonId().trim());
		
		Integer int_button_id  = funcButtonDomain.getFuncButtonInt(model_button_id);//根据button_id查询出相应的model
		if(int_button_id>0){         //判断button_id这条数据存不存在
			returnMsg.setFailMessage("按钮id已存在,核对后重新操作!", true);
		}
		funcButtonDomain.updateFuncButton(model);
		returnMsg.setSuccessMessage("修改成功！");
		return returnMsg;
	}
	
	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个ReturnMsg对象
	 *@discrible 删除功能按钮
	 */
	@Override
	public ReturnMsg deleteFuncButton(IFuncButtonModel model) {
		funcButtonDomain.deleteFuncButton(model);
		return null;
	}
}
