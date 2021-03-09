package com.ca.cacore.manage.domain.funcbutton;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.funcButton.IFuncButtonDao;
import com.ca.cacore.manage.model.bo.IFuncButtonModel;
/**
 * @author guochunhua
 * @since 2013-11-14
 * @describle 功能按钮domain层
 */
@Service
public class FuncButtonDomain implements IFuncButtonDomain{

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个List对象
	 *@discrible 查询所有的功能按钮信息
	 */
	@Autowired private IFuncButtonDao funcButtonDao;
	@Override
	public List<IFuncButtonModel> queryAllFuncButton(IFuncButtonModel model) {
		return funcButtonDao.queryAllFuncButton(model);
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个IFuncButtonModel对象
	 *@discrible 添加功能按钮
	 */
	@Override
	public IFuncButtonModel addFuncButton(IFuncButtonModel model) {
		return funcButtonDao.addFuncButton(model);
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个boolean类型的数据
	 *@discrible 修改功能按钮
	 */
	@Override
	public boolean updateFuncButton(IFuncButtonModel model) {
		return funcButtonDao.updateFuncButton(model);
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个boolean类型的数据
	 *@discrible 删除功能按钮
	 */
	@Override
	public boolean deleteFuncButton(IFuncButtonModel model) {
		return funcButtonDao.deleteFuncButton(model);
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个IFuncButtonModel对象
	 *@discrible 查询一个功能按钮的信息
	 */
	@Override
	public Integer getFuncButtonInt(IFuncButtonModel model) {
		return funcButtonDao.getFuncButtonInt(model);
	}

	@Override
	public IFuncButtonModel getFuncButton(IFuncButtonModel model) {
		// TODO Auto-generated method stub
		return funcButtonDao.getFuncButton(model);
	}
}
