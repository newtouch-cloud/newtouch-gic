package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IConservationModel;
import com.ca.cacore.ibs.model.vo.IConservationVOModel;

/**
 * 
 * @author zhao
 * @ClassName: IConservationDomain 
 * @email zhaominqi1990@163.com 
 * @date 2014年1月27日 上午9:44:15 
 * @Description: TODO
 */
public interface IConservationDomain {

	/**
	 * 
	 * @author: zhao
	 * @Description: 根据保单信息里的保险公司机构和保单号得到保单信息
	 * @param model 保全信息模型
	 * @return IConservationVOModel  保全信息Vo模型
	 * @throws
	 * @time : 2014年1月27日上午9:46:08
	 */
	public IConservationVOModel getPoliInfo(IConservationModel model) ;

	/**
	 * 
	 * @author: zhao
	 * @Description: 增加保全信息 
	 * @param model 保全信息数据模型
	 * @param user 用户
	 * @return Boolean
	 * @throws
	 * @time : 2014年1月27日上午9:47:33
	 */
	public Boolean addConservation(IConservationModel model, IUserModel user);

	/**
	 * 
	 * @author: zhao
	 * @Description: 依据保单id取出所有具有申请资格的申请人
	 * @param policy_id 保单id
	 * @return List<IConservationVOModel>
	 * @throws
	 * @time : 2014年1月27日下午2:22:00
	 */
	public List<IConservationVOModel> getApplyInfo(Long policy_id);

	/**
	 * 
	 * @author: zhao
	 * @Description: 批量查询保全信息 
	 * @param model
	 * @return List<IConservationVOModel>
	 * @throws
	 * @time : 2014年2月8日上午10:03:52
	 */
	public List<IConservationVOModel> queryConservations(
			IConservationVOModel model);

	/**
	 * 
	 * @author: zhao
	 * @Description: 查询制定信息 
	 * @param model
	 * @return IConservationVOModel
	 * @throws
	 * @time : 2014年2月8日上午10:04:11
	 */
	public IConservationVOModel getConservationsInfo(IConservationModel model);

	/**
	 * 
	 * @author: zhao
	 * @Description: 修改保全信息 
	 * @param model
	 * @param user
	 * @return Boolean
	 * @throws
	 * @time : 2014年2月8日上午10:04:31
	 */
	public Boolean modifyConservation(IConservationModel model, IUserModel user);

	public Boolean checkDateOrder(IConservationModel model);


}
