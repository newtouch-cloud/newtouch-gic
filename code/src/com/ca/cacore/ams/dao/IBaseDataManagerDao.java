package com.ca.cacore.ams.dao;

import java.util.List;

import com.ca.cacore.ams.model.vo.IBaseDataVOModel;

public interface IBaseDataManagerDao {
	public List<IBaseDataVOModel> queryBaseData(IBaseDataVOModel model);
	public List<IBaseDataVOModel> queryListBaseDataTypecode(IBaseDataVOModel model);
	public IBaseDataVOModel queryBaseDataById(IBaseDataVOModel model);
	public IBaseDataVOModel queryBaseDataByTypecode(IBaseDataVOModel model);
	public IBaseDataVOModel getBaseDataId(IBaseDataVOModel model);
	public IBaseDataVOModel getBaseDataTypeid(IBaseDataVOModel model);
	public boolean addBaseData(IBaseDataVOModel model);
	public boolean modifyBaseData(IBaseDataVOModel model);
	public boolean deleteBaseData(IBaseDataVOModel model);
	public boolean deleteBaseDataById(IBaseDataVOModel model);
	
}
