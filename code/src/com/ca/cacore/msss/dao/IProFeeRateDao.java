package com.ca.cacore.msss.dao;

import java.util.List;

import com.ca.cacore.msss.model.bo.IProductFeeRateModel;
import com.ca.cacore.msss.model.vo.IProductFeeRateVOModel;
/**
 * 
 * @author fqy
 * @since 2014-1-10
 * @description 产品手续费率管理DAO层接口
 */
public interface IProFeeRateDao {
	//查询产品手续费率信息
	public List<IProductFeeRateVOModel>  queryProFeeRateList(IProductFeeRateVOModel model);

	//查询选中的产品手续费率明细信息
	public IProductFeeRateVOModel queryProFeeRateView(IProductFeeRateVOModel model);
	
	//根据产品手续费率纬度查询产品手续费率信息
	public IProductFeeRateModel getProFeeRateBO(IProductFeeRateModel model);
	
	//修改选中的产品手续费率信息
	public boolean modifyProFeeRate(IProductFeeRateVOModel model);
	
	//新增产品手续费率信息
	public boolean insertProFeeRate(IProductFeeRateVOModel model);
}
