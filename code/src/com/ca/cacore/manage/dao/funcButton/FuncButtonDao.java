package com.ca.cacore.manage.dao.funcButton;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IFuncButtonModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * 
 * @author guochunhua
 * @discrible 功能按钮dao层
 */
@Component
public class FuncButtonDao extends BaseDao implements IFuncButtonDao{

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个List对象
	 *@discrible 查询所有的功能按钮信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IFuncButtonModel> queryAllFuncButton(IFuncButtonModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("funcButton.queryFuncButton_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IFuncButtonModel>)this.getSqlMapClientTemplate().queryForList("funcButton.queryFuncButton", model);
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个IFuncButtonModel对象
	 *@discrible 添加功能按钮
	 */
	@Override
	public IFuncButtonModel addFuncButton(IFuncButtonModel model) {
		this.getSqlMapClientTemplate().insert("funcButton.insertFuncButton", model);
		return null; 
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个boolean类型的数据
	 *@discrible 修改功能按钮
	 */
	@Override
	public boolean updateFuncButton(IFuncButtonModel model) {
		this.getSqlMapClientTemplate().update("funcButton.updateFuncButton", model);
		return true;
	}
	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个boolean类型的数据
	 *@discrible 删除功能按钮
	 */
	@Override
	public boolean deleteFuncButton(IFuncButtonModel model) {
		this.getSqlMapClientTemplate().delete("funcButton.deleteFuncButton", model);
		return false;
	}

	/**
	 *@author guochunhua
	 *@param 传入一个FuncButtonModel对象
	 *@return 返回一个IFuncButtonModel对象
	 *@discrible 查询一个功能按钮的信息
	 */
	@Override
	public Integer getFuncButtonInt(IFuncButtonModel model) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("funcButton.queryBySeqIdInt",model);
	}

	@Override
	public IFuncButtonModel getFuncButton(IFuncButtonModel model) {
		// TODO Auto-generated method stub
		return (IFuncButtonModel)this.getSqlMapClientTemplate().queryForObject("funcButton.queryBySeqId",model);
	}

}
