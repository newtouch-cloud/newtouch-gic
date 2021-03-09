package com.ca.cacore.lms.dao;

import java.util.List;

import org.apache.poi.hssf.model.Model;
import org.springframework.stereotype.Component;

import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;
import com.newtouch.core.daobase.BaseDao;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:基本法维护管理持久层
*/
@Component
public class BasicLawsManagerDao extends BaseDao implements IBasicLawsManagerDao {
	/** 
	* 查询所有基本法的信息
	* @param model
	* @return List<IBasicLawsVOModel>
	* @description:
	*/
	@SuppressWarnings("unchecked")
	public List<IBasicLawsVOModel> getAllBasicLawsInfo(IBasicLawsVOModel model){
		Integer count=(Integer) this.getSqlMapClientTemplate().queryForObject("basicLawManager.getAllBasicLaws_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return this.getSqlMapClientTemplate().queryForList("basicLawManager.getAllBasicLawsInfo",model);
	}
	
	/** 
	* 新增基本法信息
	* @param model
	* @return boolean
	* @description:
	*/
	public boolean addBasicLaws(IBasicLawsVOModel model){
		this.getSqlMapClientTemplate().insert("basicLawManager.addBasicLaws",model);
		return true;
	}
	
	/** 
	* 查询基本法的详细信息
	* @param model
	* @return IBasicLawsVOModel
	* @description:
	*/
	public IBasicLawsVOModel getBasiclawView(IBasicLawsVOModel model){
		return (IBasicLawsVOModel) this.getSqlMapClientTemplate().queryForObject("basicLawManager.getBasiclawView",model);
	}
	
	/** 
	* 基本法信息修改
	* @param model
	* @return boolean
	* @description:
	*/
	public boolean modBasicLaws(IBasicLawsVOModel model){
		this.getSqlMapClientTemplate().update("basicLawManager.modBasicLaws",model);
		return true;
	}
	
	/** 
	* 基本法信息删除
	* @param serno
	* @return boolean
	* @description:
	*/
	public boolean delBasicLaws(String serno){
		this.getSqlMapClientTemplate().delete("basicLawManager.delBasicLaws",serno);
		return true;
	}
}
