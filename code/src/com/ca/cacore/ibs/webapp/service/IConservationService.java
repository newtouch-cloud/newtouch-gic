package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IConservationModel;
import com.ca.cacore.ibs.model.vo.IConservationVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * 
 * @author zhao
 * @ClassName: IConservationService 
 * @email zhaominqi1990@163.com 
 * @date 2014年1月27日 上午9:52:43 
 * @Description: 保全业务层接口
 */
public interface IConservationService {
	/**
	 * 
	 * @author: zhao
	 * @Description: 根据保险机构和保单号获取保单信息 
	 * @param model
	 * @return IConservationVOModel 保全信息VO数据模型
	 * @throws
	 * @time : 2014年1月27日上午9:53:00
	 */
	public IConservationVOModel getPoliInfo(IConservationModel model) ;

	/**
	 * 
	 * @author: zhao
	 * @Description: 增加保全信息 
	 * @param modelForAdd   保全bo数据
	 * @param user 用户
	 * @return ReturnMsg 返回值
	 * @throws
	 * @time : 2014年1月27日上午9:53:44
	 */
	public ReturnMsg addConservation(IConservationModel modelForAdd, IUserModel user);

	/**
	 * 
	 * @author: zhao
	 * @Description: 依据保单id取出所有的 具有申请人资格的人
	 * @param policy_id
	 * @return List<IConservationVOModel>
	 * @throws
	 * @time : 2014年1月27日下午2:15:46
	 */
	public List<IConservationVOModel> getApplyInfo(Long policy_id);

	/**
	 * 
	 * @author: zhao
	 * @Description: 批量查询保全信息 
	 * @param modelForQuery
	 * @return ReturnMsg
	 * @throws
	 * @time : 2014年2月7日下午2:15:46
	 */
	public ReturnMsg queryConservations(IConservationVOModel modelForQuery);

	/**
	 * 
	 * @author: zhao
	 * @Description: 查询单个保全信息，用于查看明细和修改 
	 * @param model
	 * @return ReturnMsg
	 * @throws
	 * @time : 2014年2月8日上午9:59:52
	 */
	public ReturnMsg getConservationsInfo(IConservationModel model);

	/**
	 * 
	 * @author: zhao
	 * @Description: 修改保全信息 
	 * @param model
	 * @param user
	 * @return ReturnMsg
	 * @throws
	 * @time : 2014年2月8日上午10:00:03
	 */
	public ReturnMsg modifyConservation(IConservationModel model,
			IUserModel user);

	public Boolean checkDateOrder(IConservationModel model);
}
