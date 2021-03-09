package com.ca.cacore.ams.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ams.model.vo.BaseDataVOModel;
import com.ca.cacore.ams.model.vo.IBaseDataVOModel;
import com.ca.cacore.ams.model.vo.IMessagePushVOModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class BaseDataManagerDao extends BaseDao implements IBaseDataManagerDao{

	@Override
	public List<IBaseDataVOModel> queryBaseData(IBaseDataVOModel model) {
		int count=(Integer) this.getSqlMapClientTemplate().queryForObject("baseDataManager.getBaseData_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return this.getSqlMapClientTemplate().queryForList("baseDataManager.queryBaseData", model);
	}

	@Override
	public boolean addBaseData(IBaseDataVOModel model) {
		this.getSqlMapClientTemplate().insert("baseDataManager.addBaseData", model);
		return true;
	}

	@Override
	public boolean modifyBaseData(IBaseDataVOModel model) {
		this.getSqlMapClientTemplate().update("baseDataManager.modifyBaseData", model);
		return true;
	}

	@Override
	public boolean deleteBaseData(IBaseDataVOModel model) {
		this.getSqlMapClientTemplate().delete("baseDataManager.deleteBaseData", model);
		return true;
	}

	@Override
	public IBaseDataVOModel getBaseDataId(IBaseDataVOModel model) {
		String id=(String)this.getSqlMapClientTemplate().queryForObject("baseDataManager.getBaseDataid", model);
		IBaseDataVOModel idVO=new BaseDataVOModel();
		idVO.setId(id);
		return idVO;
	}

	@Override
	public IBaseDataVOModel queryBaseDataById(IBaseDataVOModel model) {
		IBaseDataVOModel vo=(IBaseDataVOModel)this.getSqlMapClientTemplate().queryForObject("baseDataManager.queryBaseDataById", model);
		return vo;
	}

	@Override
	public IBaseDataVOModel queryBaseDataByTypecode(IBaseDataVOModel model) {
		IBaseDataVOModel vo=(IBaseDataVOModel)this.getSqlMapClientTemplate().queryForObject("baseDataManager.queryBaseDataByTypecode", model);
		return vo;
	}

	@Override
	public IBaseDataVOModel getBaseDataTypeid(IBaseDataVOModel model) {
		int typeid=(Integer)this.getSqlMapClientTemplate().queryForObject("baseDataManager.getBaseDataTypeid", model);
		IBaseDataVOModel typeidVO=new BaseDataVOModel();
		typeidVO.setTypeid(typeid+"");
		return typeidVO;
	}

	@Override
	public List<IBaseDataVOModel> queryListBaseDataTypecode(
			IBaseDataVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("baseDataManager.queryBaseDataListByTypecode", model);
	}

	@Override
	public boolean deleteBaseDataById(IBaseDataVOModel model) {
		this.getSqlMapClientTemplate().delete("baseDataManager.deleteBaseDataid", model);
		return true;
	}

}
