package com.ca.cacore.manage.domain.funcbutton;

import java.util.List;

import com.ca.cacore.manage.model.bo.IFuncButtonModel;

/**
 * 
 * @author guochunhua
 * @describle domain层接口类
 *
 */
public interface IFuncButtonDomain {
	public List<IFuncButtonModel> queryAllFuncButton(IFuncButtonModel model);
	public Integer getFuncButtonInt(IFuncButtonModel model);
	public IFuncButtonModel getFuncButton(IFuncButtonModel model);
	public IFuncButtonModel addFuncButton(IFuncButtonModel model);
	public boolean updateFuncButton(IFuncButtonModel model);
	public boolean deleteFuncButton(IFuncButtonModel model);
}
