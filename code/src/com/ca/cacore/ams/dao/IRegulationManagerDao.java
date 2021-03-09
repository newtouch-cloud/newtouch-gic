package com.ca.cacore.ams.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;


@Repository
public interface IRegulationManagerDao {
	
	/** 
	* @author WYB
	* @param model
	* @return List<IPreserveRegVOModel>
	* @description:规章制度的查询
	*/
	public List<IPreserveRegVOModel> queryList(IPreserveRegVOModel model);
	/** 
	* @author WYB
	* @param model
	* @return List<IPreserveRegVOModel>
	* @description:规章制度的查询(待发布的记录)
	*/
	public List<IPreserveRegVOModel> queryStatus(IPreserveRegVOModel model);
	public boolean addRegulationBasic(IPreserveRegVOModel model,IUserModel user);
	public IPreserveRegVOModel getRegulationView(IPreserveRegVOModel model);
    public boolean modifyRegulation(IPreserveRegVOModel model);
	/** 
	* 
	* @param model
	* @param user
	* @return Boolean
	* @description: 更新规章制度信息表中的流程状态 
	*/
	public boolean updateRegulationStatus(IPreserveRegVOModel model,IUserModel user);
	
	/**
	 * 更新规章制度附件上传时间
	* @param model
	* @return boolean
	* @description:
	 */
	public boolean updateRegulationTime(IPreserveRegVOModel model);
	/**
	 *
	* @param model
	* @return
	* @description:
	 */
	public Integer checkRegulationIdUnique(IPreserveRegVOModel model);
}
