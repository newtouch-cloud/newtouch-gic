package com.ca.cacore.ams.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.dao.IRegulationManagerDao;
import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;

/**
* @since:    2014年2月25日   
* @author    WanBo
* @description: 招聘流程分析domain层
*/
@Service
public class RegulationManagerDomain implements IRegulationManagerDomain{
	@Autowired private IRegulationManagerDao regulationManagerDao;

	/** 
	* 
	* @param model
	* @return List<IPreserveRegVOModel>
	* @description: 查询规章制度信息
	*/
	@Override
	public List<IPreserveRegVOModel> queryList(IPreserveRegVOModel model) {
		return regulationManagerDao.queryList(model);
	}
	
	/** 
	* 
	* @param model
	* @return List<IPreserveRegVOModel>
	* @description: 查询规章制度信息(待发布的记录)
	*/
	@Override
	public List<IPreserveRegVOModel> queryStatus(IPreserveRegVOModel model) {
		return regulationManagerDao.queryStatus(model);
	}

	@Override
	public boolean addRegulationBasic(IPreserveRegVOModel model,
			IUserModel user) {
		model.setCreateuser(user.getEmp_id());
		model.setModifyuser(user.getEmp_id());
		regulationManagerDao.addRegulationBasic(model,user);
		return true;
	}

	@Override
	public IPreserveRegVOModel getRegulationView(IPreserveRegVOModel model) {
		return regulationManagerDao.getRegulationView(model);
	}

	@Override
	public boolean modifyRegulation(IPreserveRegVOModel model, IUserModel user) {
		model.setCreateuser(user.getEmp_id());
		model.setModifyuser(user.getEmp_id());
		regulationManagerDao.modifyRegulation(model);
		return true;
	}

	@Override
	public boolean updateRegulationStatus(IPreserveRegVOModel model,
			IUserModel user) {
		boolean up=true;
		for(int i=0; i<model.getSeq_ids().length; i++){
			model.setSeq_id(model.getSeq_ids()[i]);//人员简历信息seq_id
			model.setCreateuser(user.getEmp_id());
			model.setModifyuser(user.getEmp_id());
			up=regulationManagerDao.updateRegulationStatus(model, user);//调用dao层更新规章制度查询相应的信息
		}
		return up;
	}
	
	@Override
	//规章制度编号是否存在 返回true表示制度编号不存在，false表示存在 
	public Boolean checkRegulationIdUnique(IPreserveRegVOModel model){
		Integer count  = regulationManagerDao.checkRegulationIdUnique(model);
		if(count <= 0){ //表示制度编号不存在
			return true;
		}else{  //规章制度存在
			return false;
		}
	}
	
}
