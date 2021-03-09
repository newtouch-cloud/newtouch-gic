package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IClaimsModel;
import com.ca.cacore.ibs.model.vo.IClaimsVOModel;

public interface IClaimsDao {
	public IClaimsVOModel getPoliInfo(IClaimsModel model);

	public Boolean addClaims(IClaimsModel model, IUserModel user);

	public List<IClaimsVOModel> queryClaims(IClaimsVOModel model);

	public IClaimsVOModel getClaimsInfo(IClaimsModel model);

	public List<IClaimsVOModel> getEventInfo(Long policy_id);

	public Boolean modifyClaims(IClaimsModel model, IUserModel user);
}
