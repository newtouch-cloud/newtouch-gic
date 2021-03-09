package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IConservationDao;
import com.ca.cacore.ibs.model.bo.IConservationModel;
import com.ca.cacore.ibs.model.vo.IConservationVOModel;
import com.newtouch.component.c11assistant.BusinessException;

/**
 * 
 * @author zhao
 * @ClassName: ConservationDaomain 
 * @email zhaominqi1990@163.com 
 * @date 2014年1月27日 上午9:49:15 
 * @Description: 
 */
@Service
public class ConservationDomain implements IConservationDomain {

	@Autowired
	private IConservationDao conservationDao;
	@Override
	public IConservationVOModel getPoliInfo(IConservationModel model) {
		// TODO Auto-generated method stub
		return conservationDao.getPoliInfo(model);
	}

	@Override
	public Boolean addConservation(IConservationModel model, IUserModel user) {
		//日期校验
		if(!checkDateOrder(model)){
			throw new BusinessException("申请日期应大于或等于保单生效日期，请检查。");
		}
		if(!model.getRemark().equals("") && model.getRemark() != null){
			if(model.getRemark().length() > 550){
				throw new BusinessException("备注的长度超过500，请检查。");
			}
		}
		conservationDao.getPoliInfo(model);
		// TODO Auto-generated method stub
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return conservationDao.addConservation(model, user);
	}

	@Override
	public List<IConservationVOModel> getApplyInfo(Long policy_id) {
		// TODO Auto-generated method stub
		return conservationDao.getApplyInfo(policy_id);
	}

	@Override
	public List<IConservationVOModel> queryConservations(
			IConservationVOModel model) {
		// TODO Auto-generated method stub
		return conservationDao.queryConservations(model);
	}

	@Override
	public IConservationVOModel getConservationsInfo(IConservationModel model) {
		// TODO Auto-generated method stub
		return conservationDao.getConservationsInfo(model);
	}

	@Override
	public Boolean modifyConservation(IConservationModel model, IUserModel user) {
		//日期校验
		if(!checkDateOrder(model)){
			throw new BusinessException("申请日期应大于或等于保单生效日期，请检查。");
		}
		if(!model.getRemark().equals("") && model.getRemark() != null){
			if(model.getRemark().length() > 550){
				throw new BusinessException("备注的长度超过500，请检查。");
			}
		}
		model.setModifyUser(user.getEmp_id());
		return conservationDao.modifyConservation(model);
	}
	/** 
	* 
	* @param model
	* @return 
	* @description:
	*/
	@Override
	public Boolean checkDateOrder(IConservationModel model) {
		//校验出险时间大于或等于保单生效日期
		IConservationVOModel voModel = conservationDao.getPoliInfo(model);
		if(voModel != null){
			if(voModel.getValidate_date() == null){
				return true;
			}
			if(model.getApplication_time().before(voModel.getValidate_date())){
				return false;
			}
		}
		return true;
	}



}
