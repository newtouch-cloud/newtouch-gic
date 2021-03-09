
package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.IConservationDomain;
import com.ca.cacore.ibs.model.bo.IConservationModel;
import com.ca.cacore.ibs.model.vo.IConservationVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public class ConservationService implements IConservationService {

	@Autowired
	private IConservationDomain conservationDomain;
	@Override
	public IConservationVOModel getPoliInfo(IConservationModel model) {
		return conservationDomain.getPoliInfo(model);
	}

	@Override
	public ReturnMsg addConservation(IConservationModel modelForAdd,
			IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			conservationDomain.addConservation(modelForAdd, user);
			returnMsg.setSuccessMessage("保存成功");
		} catch (Exception e) {
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}

	@Override
	public List<IConservationVOModel> getApplyInfo(Long policy_id) {
		return conservationDomain.getApplyInfo(policy_id);
	}

	@Override
	public ReturnMsg queryConservations(IConservationVOModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		List<IConservationVOModel> list=conservationDomain.queryConservations(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg getConservationsInfo(IConservationModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		IConservationVOModel m=conservationDomain.getConservationsInfo(model);
		returnMsg.setDataTable(TransHelper.obj2map(m));
		return returnMsg;
	}

	@Override
	public ReturnMsg modifyConservation(IConservationModel model,
			IUserModel user) {
		ReturnMsg msg = new ReturnMsg();
		try{
		conservationDomain.modifyConservation(model,user);
		msg.setDataTable(TransHelper.obj2map(model));
		msg.setSuccessMessage("修改成功");
		}catch(BusinessException e){
			msg.setFailMessage(e.getMessage(),true);
		}
		return msg;
	}

	@Override
	public Boolean checkDateOrder(IConservationModel model) {
		return conservationDomain.checkDateOrder(model);
	}

}
