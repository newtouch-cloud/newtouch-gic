package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.IPolicyImageDomain;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.bo.PolicyImageModel;
import com.ca.cacore.ibs.model.vo.IPolicyImageVoModel;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 保单影像管理
 * create time : 2013-12-11 17:23
 * @author ZhangChen
 *
 */
@Service
public class PolicyImageService implements IPolicyImageService{

	@Autowired private IPolicyImageDomain policyImageDomain;
	
	/**
	 * 2013-12-12 11:19
	 * 上传影像时同时对影像信息进行入库保存
	 */
	public ReturnMsg addPolicyImage(IPolicyImageModel model) {
		policyImageDomain.addPolicyImage(model);
		return null;
	}

	/**
	 * 2013-12-12 13:37
	 * 验证机构及投保单号是否对应,并反馈给前台页面
	 */
	public IPolicyImageModel validateBranchAndPolicy(IPolicyImageModel model) {
		
		return policyImageDomain.validateBranchAndPolicy(model);
	}
	
	/**
	 * 
	* 2014-1-3 14:57
	* @param model
	* @return IPolicyImageModel
	* @description:验证机构及保单号是否对应,并反馈给前台页面
	 */
	public IPolicyImageModel validateBranchAndContract(IPolicyImageModel model) {
		
		return policyImageDomain.validateBranchAndContract(model);
	}

	/**
	 * 2013-12-19 17:17
	 * 通过file_id 查找影像记录用于删除影像及关联保单号码
	 */
	public ReturnMsg getFileByFileId(IPolicyImageModel model) {
		
		ReturnMsg returnMsg = new ReturnMsg();
		
		returnMsg.setDataTable(TransHelper.obj2map(policyImageDomain.getFileByFileId(model)));
		
		return returnMsg;
	}

	/**
	 * 2013-12-19 17:30
	 * 通过seq_id 删除影像信息
	 */
	public ReturnMsg deletePolicyImageInfo(IPolicyImageModel model) {
		policyImageDomain.deletePolicyImageInfo(model);
		return null;
	}

	/**
	 * 2013-12-20 9:37
	 * 通过file_id 关联保单影像信息给保单信息
	 */
	public ReturnMsg concernPolicyImage(IPolicyImageModel model, IUserModel user) throws BusinessException{
		ReturnMsg returnMsg  = new ReturnMsg();
		try{
			//没有上传影像的时候不该抛出这个异常：保单回执和问题件录入的功能
			if(model.getFile_ids() == null){
				throw new BusinessException("影像上传失败,没有相应的影像信息");
			}
			if (model.getPolicy_id()==null) {
				throw new BusinessException("保单号不能为空,请核实");
			}
			if (ValidateHelper.isNull(model.getSend_code())) {
				throw new BusinessException("投保单号码不能为空,请核实");
			}
			
			String [] file_ids = model.getFile_ids();
			int index=0; //判断是否全部关联完毕,如果中途出错则此index不会等于数组的长度,则向前台报错
			for(String file_id : file_ids){ //foreach循环遍历file_id数组逐一进行管理保单信息
				index++;
				IPolicyImageModel imodel = new PolicyImageModel();
				imodel.setFile_id(file_id);
				imodel.setPolicy_id(model.getPolicy_id());
				imodel.setSend_code(model.getSend_code());
				imodel.setBus_type(model.getBus_type());
				imodel.setSeq_num(index);
				policyImageDomain.concernPolicyImage(imodel,user);
			}
			
			if(index == file_ids.length){
				returnMsg.setSuccessMessage("上传成功");
			}else{
				returnMsg.setFailMessage("上传失败", true);
			}
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}
	
	/**
	 * 2014-2-15 14:45
	 *  影像管理-影像上传界面进行管理操作
	 */
	public ReturnMsg concernAddPolicyImage(IPolicyImageModel model, IUserModel user) throws BusinessException{
		ReturnMsg returnMsg  = new ReturnMsg();
		String type = model.getBus_type();
		try{
			//没有上传影像的时候不该抛出这个异常：保单回执和问题件录入的功能
			if(model.getFile_ids() == null){
				throw new BusinessException("影像上传失败,没有相应的影像信息");
			}
			
			
			String [] file_ids = model.getFile_ids();
			int index=0; //判断是否全部关联完毕,如果中途出错则此index不会等于数组的长度,则向前台报错
			for(String file_id : file_ids){ //foreach循环遍历file_id数组逐一进行管理保单信息
				index++;
				IPolicyImageModel imodel = new PolicyImageModel();
				imodel.setFile_id(file_id);
				imodel.setPolicy_id(model.getPolicy_id());
				imodel.setSend_code(model.getSend_code());
				imodel.setBus_type(model.getBus_type());
				imodel.setSeq_num(index);
				policyImageDomain.concernPolicyImage(imodel,user);
			}
			
			if(index == file_ids.length){
				if(type.equals(CodeTypeConst.POLICY_IMAGE_BUS_TYPE)){ //投保单
					returnMsg.setSuccessMessage("上传成功,是否返回投保单影像件上传查询界面?");
				}else{ //保单
					returnMsg.setSuccessMessage("上传成功,是否返回保单影像件上传查询界面?");
				}
				
			}else{
				returnMsg.setFailMessage("上传失败", true);
			}
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}
	
	/**
	 * 2014-2-14 14:00
	 *  从保单/投保单录入界面进入影像上传界面进行管理操作
	 */
	public ReturnMsg concernPolicyAddImage(IPolicyImageModel model, IUserModel user) throws BusinessException{
		ReturnMsg returnMsg  = new ReturnMsg();
		String type = model.getBus_type();
		try{
			//没有上传影像的时候不该抛出这个异常：保单回执和问题件录入的功能
			if(model.getFile_ids() == null){
				throw new BusinessException("影像上传失败,没有相应的影像信息");
			}
			
			
			String [] file_ids = model.getFile_ids();
			int index=0; //判断是否全部关联完毕,如果中途出错则此index不会等于数组的长度,则向前台报错
			for(String file_id : file_ids){ //foreach循环遍历file_id数组逐一进行管理保单信息
				index++;
				IPolicyImageModel imodel = new PolicyImageModel();
				imodel.setFile_id(file_id);
				imodel.setPolicy_id(model.getPolicy_id());
				imodel.setSend_code(model.getSend_code());
				imodel.setBus_type(model.getBus_type());
				imodel.setSeq_num(index);
				policyImageDomain.concernPolicyImage(imodel,user);
			}
			
			if(index == file_ids.length){
				if(type.equals(CodeTypeConst.POLICY_IMAGE_BUS_TYPE)){ //投保单
					returnMsg.setSuccessMessage("影像上传成功,是否进行新的投保单录入?");
				}else{ //保单
					returnMsg.setSuccessMessage("影像上传成功,是否进行新的保单录入?");
				}
				
			}else{
				returnMsg.setFailMessage("上传失败", true);
			}
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}

	/**
	* 2014-2-13 14:31
	* @param model
	* @param user
	* @return ReturnMsg
	* @throws BusinessException 
	* @description: 修改投保单/保单影像件信息(通用方法),如果未关联则进行更新操作,已经关联的则不进行操作
	 */
	public ReturnMsg modifyPolicyImage(IPolicyImageModel model, IUserModel user) throws BusinessException{
		ReturnMsg returnMsg  = new ReturnMsg();
		int index=0;
		if(model.getFile_ids() != null){	
			String [] file_ids = model.getFile_ids();
			 //判断是否全部关联完毕,如果中途出错则此index不会等于数组的长度,则向前台报错
			for(String file_id : file_ids){ //foreach循环遍历file_id数组逐一进行管理保单信息
				index++;
				model.setFile_id(file_id);
				boolean boo = policyImageDomain.checkFileByFileId(model);
				if(boo){ //校验文件是否存在,true表示没有进行关联
					IPolicyImageModel imodel = new PolicyImageModel();
					imodel.setFile_id(file_id);
					imodel.setPolicy_id(model.getPolicy_id());
					imodel.setSend_code(model.getSend_code());
					imodel.setBus_type(model.getBus_type());
					imodel.setSeq_num(index);
					policyImageDomain.concernPolicyImage(imodel,user);
				}
				
			}
			if(index == file_ids.length){
				returnMsg.setSuccessMessage("修改成功");
			}else{
				returnMsg.setFailMessage("修改失败", true);
			}
		}
			
		
		return returnMsg;
	}
	
	/**
	 * 2014-2-15 10:42
	 * 保单管理-->投保单影像件上传-->查询没有添加影像件的投保单
	 * @param model
	 * @return
	 */
	public ReturnMsg queryAddPolicyImage(IPolicyImageVoModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyImageVoModel>list = policyImageDomain.queryAddPolicyImage(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/**
	 * 2013-12-23 10:03
	 * 保单管理-->保单影像管理-->投保单及影像查询方法
	 * @param model
	 * @return
	 */
	public ReturnMsg queryPolicyImage(IPolicyImageVoModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyImageVoModel>list = policyImageDomain.queryPolicyImage(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
	/**
	 * 2014-2-15 10:42
	 * 保单管理-->保单影像件上传-->查询没有添加影像件的保单
	 * @param model
	 * @return
	 */
	public ReturnMsg queryAddContractImage(IPolicyImageVoModel model){
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyImageVoModel>list = policyImageDomain.queryAddContractImage(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
	/**
	 * 2014-2-8 17:07
	 * 保单管理-->保单影像管理-->保单及影像查询方法
	 * @param model
	 * @return
	 */
	public ReturnMsg queryContractImage(IPolicyImageVoModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyImageVoModel>list = policyImageDomain.queryContractImage(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/**
	 * 
	 * 2013-12-23 16:06
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->投保单影像件查询-->影像查看方法,通过投保单id
	 */
	public ReturnMsg viewPolicyImage(IPolicyImageVoModel model) {

		return policyImageDomain.viewPolicyImage(model);
	}
	
	/**
	 * 
	 * 2014-2-13 18:54
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewPolicyImageForAdd(IPolicyImageVoModel model){
		return policyImageDomain.viewPolicyImageForAdd(model);
	}
	
	/**
	 * 
	 * 2014-2-10 10:55
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewContractImage(IPolicyImageVoModel model){
		return policyImageDomain.viewContractImage(model);
	}
	
	/**
	 * 
	 * 2014-2-13 18:41
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewContractImageForAdd(IPolicyImageVoModel model){
		return policyImageDomain.viewContractImageForAdd(model);
	}

	@Override
	public List<IPolicyImageModel> getImageList(IPolicyImageVoModel model) {
		return policyImageDomain.getImageList(model);
	}



}
