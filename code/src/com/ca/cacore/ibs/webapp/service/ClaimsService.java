package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.IClaimsDomain;
import com.ca.cacore.ibs.model.bo.IClaimsModel;
import com.ca.cacore.ibs.model.vo.IClaimsVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public class ClaimsService implements IClaimsService{
	@Autowired private IClaimsDomain claimsDomain;
	
	
	
	@Override
	public IClaimsVOModel getPoliInfo(IClaimsModel model) {
		return claimsDomain.getPoliInfo(model);
	}
	
	@Override
	public ReturnMsg addClaims(IClaimsModel model,IUserModel user) throws BusinessException{
		ReturnMsg returnMsg=new ReturnMsg();
		try{
			claimsDomain.addClaims(model,user);//添加理赔信息
			returnMsg.setSuccessMessage("理赔信息录入成功");
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(),true);
		}
		
		return returnMsg;
	}
	
	/** 
	* 
	* @param model
	* @return 
	* @description:查询理赔信息
	*/
	@Override
	public ReturnMsg queryClaims(IClaimsVOModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		List<IClaimsVOModel> list=claimsDomain.queryClaims(model);
		if(list.size() == 0){
			returnMsg.setFailMessage("没有找到任何信息，请更改查询条件。");
		}
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
	
	/** 
	* 
	* @param model
	* @return 
	* @description:根据seq_id获得理赔信息
	*/
	@Override
	public ReturnMsg getClaimsInfo(IClaimsModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		IClaimsVOModel m=claimsDomain.getClaimsInfo(model);
		returnMsg.setDataTable(TransHelper.obj2map(m));
		return returnMsg;
	}

	/** 
	* 
	* @param policy_id
	* @return 
	* @description:
	*/
	@Override
	public List<IClaimsVOModel> getEventInfo(Long policy_id) {
		return claimsDomain.getEventInfo(policy_id);
		 
	}

	/** 
	* 
	* @param model
	* @param user
	* @return 
	* @description:
	*/
	@Override
	public ReturnMsg modifyClaims(IClaimsModel model, IUserModel user) {
		ReturnMsg msg = new ReturnMsg();
		try{
		claimsDomain.modifyClaims(model,user);
		msg.setSuccessMessage("修改成功");
		}catch(BusinessException e){
			msg.setFailMessage(e.getMessage(),true);
		}
		return msg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:
	*/
	@Override
	public Boolean checkDateOrder(IClaimsModel model) {
		return claimsDomain.checkDateOrder(model);
	}
	
}
