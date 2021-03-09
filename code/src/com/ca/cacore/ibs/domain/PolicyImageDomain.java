package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IPolicyImageDao;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.vo.IPolicyImageVoModel;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 保单影像管理
 * create time : 2013-12-11 17:44
 * @author ZhangChen
 *
 */

@Service
public class PolicyImageDomain implements IPolicyImageDomain{

	@Autowired private IPolicyImageDao policyImageDao ;

	/**
	 * 2013-12-12 11-19
	 * 上传影像时同时对影像信息进行入库保存
	 */
	public boolean addPolicyImage(IPolicyImageModel model) {
	  
		return policyImageDao.addPolicyImage(model);
		
	}

	/**
	 * 2013-12-12 13:37
	 * 验证机构及保单号是否对应,并反馈给前台页面
	 */
	public IPolicyImageModel validateBranchAndPolicy(IPolicyImageModel model) {
		
		return policyImageDao.validateBranchAndPolicy(model);
	}
	
	/**
	 * 
	* 2014-1-3 15:31
	* @param model
	* @return IPolicyImageModel
	* @description:验证机构及保单号是否对应,并反馈给前台页面
	 */
	public IPolicyImageModel validateBranchAndContract(IPolicyImageModel model){
		return policyImageDao.validateBranchAndContract(model);
	}

	/**
	 * 2013-12-19 17:17
	 * 通过file_id 查找影像记录用于删除影像及关联保单号码
	 */
	public IPolicyImageModel getFileByFileId(IPolicyImageModel model) {
		
		return policyImageDao.getFileByFileId(model);
	}
	
	/**
	 * 
	* 2014-2-13
	* @param model
	* @return boolean
	* @description:通过file_id 检验文件是否进行关联,如果文件是send_code是未关联的则返回true 
	 */
	public boolean checkFileByFileId(IPolicyImageModel model){
		return policyImageDao.checkFileByFileId(model);
	}

	/**
	 * 2013-12-19 17:30
	 * 通过seq_id 删除影像信息
	 */
	public boolean deletePolicyImageInfo(IPolicyImageModel model) {
		
		return policyImageDao.deletePolicyImageInfo(model);
	}

	/**
	 * 2013-12-20 9:37
	 * 通过file_id 关联保单影像信息给保单信息
	 */
	public boolean concernPolicyImage(IPolicyImageModel model, IUserModel user){
		
		model.setModifyUser(user.getEmp_id());
		boolean boo = policyImageDao.concernPolicyImage(model);
		
		if(boo){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 2014-2-15 10:42
	 * 保单管理-->投保单影像件上传-->查询没有添加影像件的投保单
	 * @param model
	 * @return
	 */
	public List<IPolicyImageVoModel> queryAddPolicyImage(IPolicyImageVoModel model) {
		return policyImageDao.queryAddPolicyImage(model);
	}

	/**
	 * 2013-12-23 10:03
	 * 保单管理-->保单影像管理-->投保单及影像查询方法
	 * @param imgModel
	 * @return
	 */
	public List<IPolicyImageVoModel> queryPolicyImage(IPolicyImageVoModel model) {
		
		return policyImageDao.queryPolicyImage(model);
	}
	
	/**
	 * 2014-2-15 10:42
	 * 保单管理-->保单影像件上传-->查询没有添加影像件的保单
	 * @param model
	 * @return
	 */
	public List<IPolicyImageVoModel> queryAddContractImage(IPolicyImageVoModel model){
		return policyImageDao.queryAddContractImage(model);
	}
	
	/**
	 * 2014-2-8 17:09
	 * 保单管理-->保单影像管理-->保单及影像查询方法
	 * @param model
	 * @return
	 */
	public List<IPolicyImageVoModel> queryContractImage(IPolicyImageVoModel model) {
		
		return policyImageDao.queryContractImage(model);
	}

	/**
	 * 
	* 2013-12-23 16:11
	* @param model
	* @return ReturnMsg
	* @description:保单管理-->保单影像管理-->保单及影像查询-->影像查看方法,通过保单主键id
	 */
	public ReturnMsg viewPolicyImage(IPolicyImageVoModel model) {

		return policyImageDao.viewPolicyImage(model);
	}
	
	/**
	 * 
	 * 2014-2-13 18:54
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewPolicyImageForAdd(IPolicyImageVoModel model){
		return policyImageDao.viewPolicyImageForAdd(model);
	}
	
	/**
	 * 
	 * 2014-2-10 10:55
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewContractImage(IPolicyImageVoModel model) {

		return policyImageDao.viewContractImage(model);
	}
	
	/**
	 * 
	 * 2014-2-13 18:41
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewContractImageForAdd(IPolicyImageVoModel model){
		return policyImageDao.viewContractImageForAdd(model);
	}

	@Override
	public List<IPolicyImageModel> getImageList(IPolicyImageVoModel model) {
		return policyImageDao.getImageList(model);
	}

	

}
