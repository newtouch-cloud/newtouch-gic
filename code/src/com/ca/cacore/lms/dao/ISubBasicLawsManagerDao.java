package com.ca.cacore.lms.dao;

import java.util.List;

import com.ca.cacore.lms.model.bo.IBasicLawsModel;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:子基本法维护管理持久层
*/
public interface ISubBasicLawsManagerDao {

	/** 
	* 查询所有子基本法的信息
	* @param model
	* @return List<IBasicLawsVOModel>
	* @description:
	*/
	public List<IBasicLawsVOModel> getAllSubBasicLawsInfo(IBasicLawsVOModel model);
	
	/** 
	* 新增子基本法信息
	* @param model
	* @return boolean
	* @description:
	*/
	public boolean addSubBasicLaws(IBasicLawsVOModel model);
	
	/** 
	* 查询子基本法的详细信息
	* @param model
	* @return IBasicLawsVOModel
	* @description:
	*/
	public IBasicLawsVOModel getSubBasicLawView(IBasicLawsVOModel model);
	
	/** 
	* 子基本法信息修改
	* @param model
	* @return boolean
	* @description:
	*/
	public boolean modSubBasicLaws(IBasicLawsVOModel model);
	
	/** 
	* 子子基本法信息删除
	* @param serno
	* @return boolean
	* @description:
	*/
	public boolean delSubBasicLaws(String serno);
	
	/** 
	* @param basiclaw_no
	* @return IBasicLawsModel
	* @description:查询子基本法的详细信息
	*/
	public IBasicLawsModel getLawsInfo(String basiclaw_no);
	
}
