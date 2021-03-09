package com.ca.cacore.lms.domain;

import java.util.List;

import net.sf.json.JSONArray;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.lms.model.bo.IBasicLawsModel;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:
*/
public interface ISubBasicLawsManagerDomain {
	/** 
	* 获得所有基本法的信息
	* @param model
	* @return List<IBasicLawsVOModel>
	* @description:
	*/
	public List<IBasicLawsVOModel> getAllSubBasicLawsInfo(IBasicLawsVOModel model);
	
	/** 
	* 新增基本法信息
	* @param model
	* @param user
	* @return boolean
	* @description:
	*/
	public boolean addSubBasicLaws(IBasicLawsVOModel model,IUserModel user);
	
	/** 
	* 查询基本法明细
	* @param model
	* @return IBasicLawsVOModel
	* @description:
	*/
	public IBasicLawsVOModel getSubBasiclawView(IBasicLawsVOModel model);
	
	/** 
	* 修改基本法信息
	* @param model
	* @param user
	* @return boolean
	* @description:
	*/
	public boolean modSubBasicLaws(IBasicLawsVOModel model,IUserModel user);
	
	/** 
	* 删除基本法
	* @param serno
	* @return boolean
	* @description:
	*/
	public boolean delSubBasicLaws(String serno);
	
	/** 
	* 
	* @param basiclaw_no
	* @return String
	* @description:
	*/
	public String getLawsInfo(String basiclaw_no);
}
