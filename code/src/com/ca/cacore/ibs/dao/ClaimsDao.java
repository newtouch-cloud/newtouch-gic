package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IClaimsModel;
import com.ca.cacore.ibs.model.vo.IClaimsVOModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class ClaimsDao extends BaseDao implements IClaimsDao{

	/** 
	* 
	* @param model
	* @return 
	* @description:拿到保单信息
	*/
	@Override
	public IClaimsVOModel getPoliInfo(IClaimsModel model) {
		return (IClaimsVOModel) this.getSqlMapClientTemplate().queryForObject("claims.getPoliInfo", model);
	}
	
	/** 
	* 
	* @param model
	* @param user
	* @return Boolean
	* @description:添加理赔信息
	*/
	@Override
	public Boolean addClaims(IClaimsModel model,IUserModel user) {
		this.getSqlMapClientTemplate().insert("claims.addClaims", model);
		return true;
	}
	
	/** 
	* 
	* @param model
	* @return 
	* @description:查询所有的理赔信息
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<IClaimsVOModel> queryClaims(IClaimsVOModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("claims.queryClaims_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IClaimsVOModel>)this.getSqlMapClientTemplate().queryForList("claims.queryClaims", model);
	}
	
	/** 
	* 
	* @param model
	* @return 
	* @description:
	*/
	@Override
	public IClaimsVOModel getClaimsInfo(IClaimsModel model) {
		return (IClaimsVOModel) this.getSqlMapClientTemplate().queryForObject("claims.getClaimsInfo", model);
	}

	/** 
	* 
	* @param policy_id
	* @return 
	* @description:
	*/
	@Override
	public List<IClaimsVOModel> getEventInfo(Long policy_id) {
		 return (List<IClaimsVOModel>)this.getSqlMapClientTemplate().queryForList("claims.getEventInfo", policy_id);
	}

	@Override
	public Boolean modifyClaims(IClaimsModel model, IUserModel user) {
		 this.getSqlMapClientTemplate().update("claims.updateClaims", model);
		 return true;
	}
	
	
}
