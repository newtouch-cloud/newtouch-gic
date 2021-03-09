package com.ca.cacore.lms.dao;

import java.util.List;

import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:基本法维护管理持久层
*/
public interface IBasicLawsManagerDao {

	/** 
	* 查询所有基本法的信息
	* @param model
	* @return List<IBasicLawsVOModel>
	* @description:
	*/
	public List<IBasicLawsVOModel> getAllBasicLawsInfo(IBasicLawsVOModel model);
	
	/** 
	* 新增基本法信息
	* @param model
	* @return boolean
	* @description:
	*/
	public boolean addBasicLaws(IBasicLawsVOModel model);
	
	/** 
	* 查询基本法的详细信息
	* @param model
	* @return IBasicLawsVOModel
	* @description:
	*/
	public IBasicLawsVOModel getBasiclawView(IBasicLawsVOModel model);
	
	/** 
	* 基本法信息修改
	* @param model
	* @return boolean
	* @description:
	*/
	public boolean modBasicLaws(IBasicLawsVOModel model);
	
	/** 
	* 基本法信息删除
	* @param serno
	* @return boolean
	* @description:
	*/
	public boolean delBasicLaws(String serno);
}
