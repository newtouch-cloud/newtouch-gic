package com.ca.cacore.ams.domain;

import java.util.List;

import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;

public interface IRegulationManagerDomain {
	/** 
	* 
	* @param model
	* @return List<IPreserveRegVOModel>
	* @description: 查询规章制度信息
	*/
	public List<IPreserveRegVOModel> queryList(IPreserveRegVOModel model);
	
	/** 
	* 
	* @param model
	* @return List<IPreserveRegVOModel>
	* @description: 查询规章制度信息(待发布的记录)
	*/
	public List<IPreserveRegVOModel> queryStatus(IPreserveRegVOModel model);
	public boolean addRegulationBasic(IPreserveRegVOModel model,IUserModel user);
	public IPreserveRegVOModel getRegulationView(IPreserveRegVOModel model);
    public boolean modifyRegulation(IPreserveRegVOModel model,IUserModel user);
	/** 
	* 
	* @param model
	* @param user
	* @return Boolean
	* @description: 更新规章编号信息表中的流程状态 
	*/
   public boolean updateRegulationStatus(IPreserveRegVOModel model,IUserModel user);
   
   public Boolean checkRegulationIdUnique(IPreserveRegVOModel model);

}
