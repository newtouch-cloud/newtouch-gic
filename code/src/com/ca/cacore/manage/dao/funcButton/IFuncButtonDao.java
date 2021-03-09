package com.ca.cacore.manage.dao.funcButton;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.manage.model.bo.IFuncButtonModel;
/**
 * dao层接口类
 * @author guochunhua
 *
 */
@Repository
public interface IFuncButtonDao {
	public List<IFuncButtonModel> queryAllFuncButton(IFuncButtonModel model);
	public Integer getFuncButtonInt(IFuncButtonModel model);
	public IFuncButtonModel getFuncButton(IFuncButtonModel model);
	public IFuncButtonModel addFuncButton(IFuncButtonModel model);
	public boolean updateFuncButton(IFuncButtonModel model);
	public boolean deleteFuncButton(IFuncButtonModel model);
}
