package com.ca.cacore.lms.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.common.GetFormatId;
import com.ca.cacore.lms.dao.IBasicLawsManagerDao;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;
/**
* @since:    2014年3月3日   
* @author    ss
* @description:
*/
@Service
public class BasicLawsManagerDomain implements IBasicLawsManagerDomain {
	@Autowired private IBasicLawsManagerDao basicLawsManagerDao;
	@Autowired private GetFormatId getFormatId;
	/** 
	* 获得所有基本法的信息
	* @param model
	* @return List<IBasicLawsVOModel>
	* @description:
	*/
	public List<IBasicLawsVOModel> getAllBasicLawsInfo(IBasicLawsVOModel model) {
		return basicLawsManagerDao.getAllBasicLawsInfo(model);
	}

	/** 
	* 新增基本法信息
	* @param model
	* @param user
	* @return boolean
	* @description:
	*/
	public boolean addBasicLaws(IBasicLawsVOModel model,IUserModel user){
		String basiclaw_no = getFormatId.createSalesId(5, "basiclaw_no");
		model.setBasiclaw_no(basiclaw_no);
		model.setBasiclaw_ver("1");
		model.setIss_unit("总公司");
		model.setCrt_user(user.getEmp_id());
		model.setMdf_user(user.getEmp_id());
		return basicLawsManagerDao.addBasicLaws(model);
	}
	
	/** 
	* 查询基本法明细
	* @param model
	* @return IBasicLawsVOModel
	* @description:
	*/
	public IBasicLawsVOModel getBasiclawView(IBasicLawsVOModel model){
		return basicLawsManagerDao.getBasiclawView(model);
	}
	
	/** 
	* 修改基本法信息
	* @param model
	* @param user
	* @return boolean
	* @description:
	*/
	public boolean modBasicLaws(IBasicLawsVOModel model,IUserModel user){
		return basicLawsManagerDao.modBasicLaws(model);
	}
	
	/** 
	* 删除基本法
	* @param serno
	* @return boolean
	* @description:
	*/
	public boolean delBasicLaws(String serno){
		return basicLawsManagerDao.delBasicLaws(serno);
	}
}
