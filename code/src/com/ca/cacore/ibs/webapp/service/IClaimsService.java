package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IClaimsModel;
import com.ca.cacore.ibs.model.vo.IClaimsVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IClaimsService {
	public IClaimsVOModel getPoliInfo(IClaimsModel model) ;

	public ReturnMsg addClaims(IClaimsModel modelForAdd, IUserModel user);

	public ReturnMsg queryClaims(IClaimsVOModel modelForQuery);

	public ReturnMsg getClaimsInfo(IClaimsModel model);

	public List<IClaimsVOModel> getEventInfo(Long policy_id);

	public ReturnMsg modifyClaims(IClaimsModel model, IUserModel user);

	public Boolean checkDateOrder(IClaimsModel model);
}
