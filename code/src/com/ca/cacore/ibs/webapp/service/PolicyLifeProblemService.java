package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.IPolicyLifeProblemDomain;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.vo.IPolicyImageVoModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProblemModel;
import com.ca.cacore.ibs.model.vo.PolicyImageVoModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeProblemModel;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public class PolicyLifeProblemService implements IPolicyLifeProblemService{
	@Autowired private IPolicyLifeProblemDomain policyLifeProblemDomain;
	@Autowired private IPolicyImageService  policyImageService ;
	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个ReturnMsg对象
	* @description:查询所有问题件信息
	*/
	@Override
	public ReturnMsg queryPolicyLifeProblem(IPolicyLifeProblemModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		List<IPolicyLifeProblemModel> list=policyLifeProblemDomain.queryPolicyLifeProblem(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个returnMsg对象
	* @description:查询问题件信息
	*/
	@Override
	public ReturnMsg getPolicyLifeProblemInfo(IPolicyLifeProblemModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		IPolicyLifeProblemModel m=policyLifeProblemDomain.getPolicyLifeProblemInfo(model);
		IPolicyImageVoModel pm=new PolicyImageVoModel();
		pm.setPolicy_id(m.getPolicy_id());
		//将影像的policy_id赋值---seq_id
		String policy_id = m.getSeq_id().toString();
	     pm.setPolicy_id(Long.valueOf(policy_id));
		pm.setBus_type(CodeTypeConst.POLICY_PROBLEM_IMAGE_BUS_TYPE);//投保单问题件影像
		List<IPolicyImageModel> list = policyImageService.getImageList(pm);
		returnMsg.setDataList(TransHelper.list2MapList(list));//影像显示
		returnMsg.setDataTable(TransHelper.obj2map(m));
		return returnMsg;
	}
	
	/** 
	* @author     GCH 
	* @description:问题件状态更新
	*/
	@Override
	public ReturnMsg modifyStatus(IPolicyLifeProblemModel model,IUserModel user) {
		ReturnMsg returnMsg=new ReturnMsg();
		policyLifeProblemDomain.modifyStatus(model,user);
		returnMsg.setSuccessMessage("修改成功");
		IPolicyLifeProblemModel m = new PolicyLifeProblemModel();
		if("1".equals(model.getFlag())){//投保单状态更新显示
			m=policyLifeProblemDomain.getPolicyLifeProblemInfo(model);
		}else{//理赔和保全状态更新回显
			m = policyLifeProblemDomain.getConserClaiProblemInfo(model);
		}
		m.setFlag(model.getFlag());//页面跳转的标识
		returnMsg.setDataTable(TransHelper.obj2map(m));//回显
		return returnMsg;
	}
	
	/** 
	* @author     GCH
	* @description:问题件信息修改
	*/
	@Override
	public ReturnMsg modifyPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user) {
		ReturnMsg returnMsg=new ReturnMsg();
		if(policyLifeProblemDomain.modifyPolicyLifeProblem(model,user)){
			returnMsg.setSuccessMessage("修改成功！");
		}
		IPolicyLifeProblemModel m=policyLifeProblemDomain.getPolicyLifeProblemInfo(model);
		returnMsg.setDataTable(TransHelper.obj2map(m));
		return returnMsg;
	}
	
	/** 
	* @author     GCH  
	* @description:问题件录入
	*/
	@Override
	public ReturnMsg addPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user) throws BusinessException{
		ReturnMsg returnMsg=new ReturnMsg();
		try{
			policyLifeProblemDomain.addPolicyLifeProblem(model,user);//添加问题件
			if(model.getPolicyImageModel()!=null){
				policyImageService.concernPolicyImage(model.getPolicyImageModel(), user);
			}
			returnMsg.setSuccessMessage("保存成功");
		}catch(BusinessException e){
			returnMsg.setDataTable(TransHelper.obj2map(model));
			returnMsg.setFailMessage(e.getMessage(),true);
		}
		
		return returnMsg;
	}
	@Override
	public IPolicyLifeProblemModel getInfo(IPolicyLifeProblemModel model) {
		return policyLifeProblemDomain.getInfo(model);
	}

	@Override
	public IPolicyLifeProblemModel getInfoForClaimsConser(IPolicyLifeProblemModel model) {
		return policyLifeProblemDomain.getInfoForClaimsConser(model);
	}

	@Override
	public ReturnMsg getConserClaiProblemInfo(IPolicyLifeProblemModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		String bus_type="";
		if("1".equals(model.getFlag())){
			bus_type = CodeTypeConst.CONSER_PROBLEM_IMAGE_BUS_TYPE;//保全问题件影像
		}else{
			bus_type = CodeTypeConst.CLAIM_PROBLEM_IMAGE_BUS_TYPE;//理赔问题件影像
		}
		IPolicyLifeProblemModel m=policyLifeProblemDomain.getConserClaiProblemInfo(model);
		m.setFlag(model.getFlag());//跳转标志：2：理赔修改页面 1：保全修改页面
		IPolicyImageVoModel pm=new PolicyImageVoModel();
		//将影像的policy_id赋值---seq_id
		String policy_id = m.getSeq_id().toString();
		pm.setPolicy_id(Long.valueOf(policy_id));
		pm.setBus_type(bus_type);//回执的影像
		List<IPolicyImageModel> list = policyImageService.getImageList(pm);
		returnMsg.setDataList(TransHelper.list2MapList(list));//影像显示
		returnMsg.setDataTable(TransHelper.obj2map(m));
		return returnMsg;
	}

	@Override
	public ReturnMsg queryConserClaiProblem(IPolicyLifeProblemModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		List<IPolicyLifeProblemModel> list=policyLifeProblemDomain.queryConserClaiProblem(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
}
