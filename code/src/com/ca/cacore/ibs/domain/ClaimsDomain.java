package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IClaimsDao;
import com.ca.cacore.ibs.model.bo.IClaimsModel;
import com.ca.cacore.ibs.model.vo.IClaimsVOModel;
import com.newtouch.component.c11assistant.BusinessException;

@Service
public class ClaimsDomain implements IClaimsDomain{
	@Autowired private IClaimsDao claimsDao;
	
	/** 
	* 
	* @param model
	* @return 
	* @description:
	*/
	@Override
	public IClaimsVOModel getPoliInfo(IClaimsModel model) {
		return claimsDao.getPoliInfo(model);
	}
	
	
	@Override
	public Boolean addClaims(IClaimsModel model,IUserModel user) {
		//字段长度校验
		if(!"".equals(model.getEvent_process()) && model.getEvent_process().length() > 2000){
			throw new BusinessException("出险经过内容长度超过2000字符，请检查。");
		}
		//校验出险时间大于或等于保单生效日期
		if(!checkDateOrder(model)){
			throw new BusinessException("出险时间应大于或等于保单生效日期，请检查。");
		}
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return claimsDao.addClaims(model,user);
	}
	
	/** 
	* 
	* @param model
	* @return 
	* @description:查询所有的理赔信息
	*/
	@Override
	public List<IClaimsVOModel> queryClaims(IClaimsVOModel model) {
		return claimsDao.queryClaims(model);
	}
	
	
	/** 
	* 
	* @param model
	* @return IClaimsModel
	* @description:getClaimsInfo获得理赔信息
	*/
	@Override
	public IClaimsVOModel getClaimsInfo(IClaimsModel model) {
		return claimsDao.getClaimsInfo(model);
	}


	@Override
	public List<IClaimsVOModel> getEventInfo(Long policy_id) {
		return claimsDao.getEventInfo(policy_id);
	}


	@Override
	public Boolean modifyClaims(IClaimsModel model, IUserModel user) {
		
		//字段长度校验
		if(!"".equals(model.getEvent_process()) && model.getEvent_process().length() > 2000){
			throw new BusinessException("出险经过内容长度超过2000字符，请检查。");
		}
		//校验出险时间大于或等于保单生效日期
		if(!checkDateOrder(model)){
			throw new BusinessException("出险时间应大于或等于保单生效日期，请检查。");
		}
		model.setModifyUser(user.getEmp_id());
		return claimsDao.modifyClaims(model,user);
	}


	/** 
	* 
	* @param model
	* @return 
	* @description:
	*/
	@Override
	public Boolean checkDateOrder(IClaimsModel model) {
		//校验出险时间大于或等于保单生效日期
		IClaimsVOModel voModel = claimsDao.getPoliInfo(model);
		if(voModel != null){
			if(voModel.getValidate_date() == null){
				return true;
			}
			if(model.getEvent_date().before(voModel.getValidate_date())){
				return false;
			}
		}
		return true;
	}
}
