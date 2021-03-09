package com.ca.cacore.ams.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.dao.IBaseDataManagerDao;
import com.ca.cacore.ams.model.vo.IBaseDataVOModel;

@Service
public class BaseDataManagerDomain implements IBaseDataManagerDomain{
	@Autowired private IBaseDataManagerDao baseDataManagerDao; 
	@Override
	public List<IBaseDataVOModel> queryBaseData(IBaseDataVOModel model) {
		return baseDataManagerDao.queryBaseData(model);
	}

	@Override
	public boolean addBaseData(IBaseDataVOModel model) {
		baseDataManagerDao.addBaseData(model);
		return true;
	}

	@Override
	public boolean modifyBaseData(IBaseDataVOModel model) {
		baseDataManagerDao.modifyBaseData(model);
		return true;
	}

	@Override
	public boolean deleteBaseData(IBaseDataVOModel model) {
		baseDataManagerDao.deleteBaseData(model);
		return true;
	}

	@Override
	public IBaseDataVOModel getBaseDataId(IBaseDataVOModel model) {
		return baseDataManagerDao.getBaseDataId(model);
	}

	@Override
	public IBaseDataVOModel queryBaseDataById(IBaseDataVOModel model) {
		return baseDataManagerDao.queryBaseDataById(model);
	}

	@Override
	public IBaseDataVOModel queryBaseDataByTypecode(IBaseDataVOModel model) {
		return baseDataManagerDao.queryBaseDataByTypecode(model);
	}
	
	@Override
	public IBaseDataVOModel getBaseDataTypeid(IBaseDataVOModel model) {
		return baseDataManagerDao.getBaseDataTypeid(model);
	}

	@Override
	public List<IBaseDataVOModel> queryListBaseDataTypecode(
			IBaseDataVOModel model) {
		return baseDataManagerDao.queryListBaseDataTypecode(model);
	}

	@Override
	public boolean deleteBaseDataById(IBaseDataVOModel model) {
		baseDataManagerDao.deleteBaseDataById(model);
		return true;
	}

}
