package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.vo.IPolicyImageVoModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 保单影像管理
 * create time : 2013-12-11 17:23
 * @author ZhangChen
 *
 */
public interface IPolicyImageService {
	
	/**
	 * 2013-12-12 11:00
	 * 上传影像时同时对影像信息进行入库保存
	 */
	public ReturnMsg addPolicyImage(IPolicyImageModel model);
	
	/**
	 * 2013-12-12 13:37
	 * 验证机构及投保单号是否对应,并反馈给前台页面
	 */
	public IPolicyImageModel validateBranchAndPolicy(IPolicyImageModel model);

	
	/**
	 * 
	* 2014-1-3 14:57
	* @param model
	* @return IPolicyImageModel
	* @description:验证机构及保单号是否对应,并反馈给前台页面
	 */
	public IPolicyImageModel validateBranchAndContract(IPolicyImageModel model);

	/**
	 * 2013-12-19 17:17
	 * 通过file_id 查找影像记录用于删除影像及关联保单号码
	 */
	public ReturnMsg getFileByFileId(IPolicyImageModel model);

	/**
	 * 2013-12-19 17:30
	 * 通过seq_id 删除影像信息
	 */
	public ReturnMsg deletePolicyImageInfo(IPolicyImageModel model);

	/**
	 * 2013-12-20 9:37
	 * 通过file_id 关联保单影像信息给保单信息
	 */
	public ReturnMsg concernPolicyImage(IPolicyImageModel model, IUserModel user) throws BusinessException;

	
	/**
	 * 2014-2-15 14:45
	 *  影像管理-影像上传界面进行管理操作
	 */
	public ReturnMsg concernAddPolicyImage(IPolicyImageModel model, IUserModel user);
	
	
	/**
	 * 2014-2-14 14:00
	 * 从保单/投保单录入界面进入影像上传界面进行管理操作
	 */
	public ReturnMsg concernPolicyAddImage(IPolicyImageModel model, IUserModel user);
	/**
	* 2014-2-13 14:31
	* @param model
	* @param user
	* @return ReturnMsg
	* @throws BusinessException 
	* @description: 修改保单影像件信息,如果未关联则进行更新操作,已经关联的则不进行操作
	 */
	public ReturnMsg modifyPolicyImage(IPolicyImageModel model, IUserModel user);
	
	/**
	 * 2014-2-15 10:42
	 * 保单管理-->投保单影像件上传-->查询没有添加影像件的投保单
	 * @param model
	 * @return
	 */
	public ReturnMsg queryAddPolicyImage(IPolicyImageVoModel model);
	
	/**
	 * 2013-12-23 10:02
	 * 保单管理-->保单影像管理-->投保单及影像查询方法
	 * @param model
	 * @return
	 */
	public ReturnMsg queryPolicyImage(IPolicyImageVoModel model);
	
	/**
	 * 2014-2-15 10:42
	 * 保单管理-->保单影像件上传-->查询没有添加影像件的保单
	 * @param model
	 * @return
	 */
	public ReturnMsg queryAddContractImage(IPolicyImageVoModel model);
	
	/**
	 * 2014-2-8 17:07
	 * 保单管理-->保单影像管理-->保单及影像查询方法
	 * @param model
	 * @return
	 */
	public ReturnMsg queryContractImage(IPolicyImageVoModel model);

	/**
	 * 2013-12-23 16:06
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->投保单影像件查询-->影像查看方法,通过投保单id
	 */
	public ReturnMsg viewPolicyImage(IPolicyImageVoModel model);
	
	/**
	 * 
	 * 2014-2-13 18:54
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewPolicyImageForAdd(IPolicyImageVoModel model);
	
	/**
	 * 
	 * 2014-2-10 10:55
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewContractImage(IPolicyImageVoModel model);
	
	/**
	 * 
	 * 2014-2-13 18:41
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewContractImageForAdd(IPolicyImageVoModel model);
	
	/**
	* @param model
	* @return List<IPolicyImageModel>
	* @description:查询所有的图片
	 */
	public  List<IPolicyImageModel> getImageList(IPolicyImageVoModel model);

	
	
}
