package com.ca.cacore.msss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.domain.IProFeeRateDomain;
import com.ca.cacore.msss.model.vo.IProductFeeRateVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 
 * @author  fqy
 * @since 2014-1-10
 * @description 产品手续费率Service层接口
 */
@Service
public class ProFeeRateService extends ServerBase implements IProFeeRateService {

	@Autowired
	private IProFeeRateDomain ProFeeRateDomain;

	/**
	 * @param 传入IProductFeeRateVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 根据查询条件查询出所有产品手续费率信息
	 */
	@Override
	public ReturnMsg queryProFeeRateList(IProductFeeRateVOModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IProductFeeRateVOModel> list = ProFeeRateDomain.queryProFeeRateList(model);
		msg.setDataList(TransHelper.list2MapList(list));
		return msg;
	}

	/**
	 * @param 传入IProductFeeRateVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询选中的产品手续费率明细信息
	 */
	@Override
	public ReturnMsg queryProFeeRateView(IProductFeeRateVOModel model) {
		ReturnMsg msg = new ReturnMsg();
		IProductFeeRateVOModel view = ProFeeRateDomain.queryProFeeRateView(model);
		msg.setDataTable(TransHelper.obj2map(view));
		return msg;
	}

	/** 
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:修改选中的产品手续费率信息
	*/
	@Override
	public ReturnMsg modifyProFeeRate(IProductFeeRateVOModel model,IUserModel user) throws BusinessException{
		// 初始化返回参数
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			//校验非空
			this.validateData(model);
			ProFeeRateDomain.modifyProFeeRate(model, user);
			//进行查询，设置回显
			returnMsg.setDataTable(TransHelper.obj2map(ProFeeRateDomain.queryProFeeRateView(model)));
		} catch (BusinessException e) {
			//如果有异常，捕获并设置回显
			returnMsg.setFailMessage(e.getMessage(), true);
			returnMsg.setDataTable(TransHelper.obj2map(model));
		}
		//设置返回参数
		returnMsg.setSuccessMessage("修改成功");
		return returnMsg;
	}
	
	/** 
	* @param model
	* @return ReturnMsg
	* @description:新增产品手续费率信息
	*/
	@Override
	public ReturnMsg insertProFeeRate(IProductFeeRateVOModel model) throws BusinessException{
		// 初始化返回参数
		ReturnMsg returnMsg = new ReturnMsg();
		// 进行业务处理
		try {
			//调用校验方法，进行数据合法性校验
			this.validateData(model);
			ProFeeRateDomain.insertProFeeRate(model);
		} catch (BusinessException e) {
			//如果有校验不符合，就捕获，并设置返回信息
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		// 设置返回参数
		returnMsg.setSuccessMessage("保存成功");
		return returnMsg;
	}
	
	/** 
	* 校验必录字段
	* @param     model
	* @return    
	* @description:校验数据的公共方法
	* @throws BusinessException
	*/
	private void validateData(IProductFeeRateVOModel model){
		// 进行入参校验
		ValidateHelper.isNullThrowException(model.getInsBranch_id(),"保险公司不能为空");
		ValidateHelper.isNullThrowException(model.getProduct_id(), "产品代码不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(model.getCoverage_period(), "保障期限类型不能为空");
		ValidateHelper.isNullThrowException(model.getCoverage_year(), "保障年数不能为空");
		ValidateHelper.isNullThrowException(model.getSell_way_name(), "销售方式不能为空");
		ValidateHelper.isNullThrowException(model.getCharge_type(), "缴费方式不能为空");
		ValidateHelper.isNullThrowException(Integer.toString(model.getCharge_year()), "缴费年限不能为空");
		ValidateHelper.isNullThrowException(model.getPolicy_year(), "保单年度不能为空");
		ValidateHelper.isNullThrowException(Double.toString(model.getFee_rate()), "手续费率不能为空");
	}
}
