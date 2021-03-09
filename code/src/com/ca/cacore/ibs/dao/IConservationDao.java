package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IConservationModel;
import com.ca.cacore.ibs.model.vo.IConservationVOModel;
/**
 * 
 * @author zhao
 * @ClassName: IConservationDao 
 * @email zhaominqi1990@163.com 
 * @date 2014年1月26日 下午5:16:53 
 * @Description: 保全Dao接口
 */
public interface IConservationDao {
	
	/**
	* @author: zhao
	* @Description: 得到保单信息 
	* @param: model 保全BO模型
	* @return IConservationVOModel 保全VO模型
	* @throws
	 */
	public IConservationVOModel getPoliInfo(IConservationModel model);

	/**
	 * 
	 * @author: zhao
	 * @Description: 增加保全信息
	 * @param model 保全模型
	 * @param user 用户模型
	 * @return Boolean 操作成功与否
	 * @throws
	 * @time : 2014年1月26日下午5:35:44
	 */
	public Boolean addConservation(IConservationModel model, IUserModel user);
	
	/**
	 * 
	 * @author: zhao
	 * @Description: 依据保单id 查询申请人 
	 * @param policy_id
	 * @return List<IConservationVOModel>
	 * @throws
	 * @time : 2014年1月27日下午2:23:36
	 */
	public List<IConservationVOModel> getApplyInfo(Long policy_id);

	/**
	 * 
	 * @author: zhao
	 * @Description: 批量查询保全信息 
	 * @param model
	 * @return List<IConservationVOModel>
	 * @throws
	 * @time : 2014年2月8日上午10:09:24
	 */
	public List<IConservationVOModel> queryConservations(
			IConservationVOModel model);

	/**
	 * 
	 * @author: zhao
	 * @Description: 查询单个保全信息 
	 * @param model
	 * @return IConservationVOModel
	 * @throws
	 * @time : 2014年2月8日上午10:09:39
	 */
	public IConservationVOModel getConservationsInfo(IConservationModel model);

	/**
	 * 
	 * @author: zhao
	 * @Description: 修改保全信息 
	 * @param model
	 * @return Boolean
	 * @throws
	 * @time : 2014年2月8日上午10:09:57
	 */
	public Boolean modifyConservation(IConservationModel model);


}
