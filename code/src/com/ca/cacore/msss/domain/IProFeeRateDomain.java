package  com.ca.cacore.msss.domain;
import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.vo.IProductFeeRateVOModel;

/**
 * 
 * @author fqy
 * @since 2014-1-10
 * @description 产品手续费率管理Domain层接口
 */
public interface IProFeeRateDomain {
	/**
	 * @param 传入IProductFeeRateVOModel
	 * @return 返回一个 List<IProductFeeRateVOModel>对象
	 * @description 根据查询条件返回产品手续费率信息
	 */
	public List<IProductFeeRateVOModel>  queryProFeeRateList(IProductFeeRateVOModel model);
	
	/**
	 * @param 传入IProductFeeRateVOModel
	 * @return 返回一个IProductFeeRateVOModel对象
	 * @description 查询选中的产品手续费率明细信息
	 */
	public IProductFeeRateVOModel queryProFeeRateView(IProductFeeRateVOModel model);
	
	/**
	 * @param 传入IProductFeeRateVOModel model
	 * @return 返回一个Boolean对象
	 * @description proFeeRateIsHave 校验相同纬度的产品手续费是否已经配置过
	 */
	public Boolean proFeeRateIsHave(IProductFeeRateVOModel model);
	
	/**
	 * @param model 传入IProductFeeRateVOModel，IUserModel
	 * @return 返回boolean
	 * @description 修改选中的产品手续费率信息
	 */
	public boolean modifyProFeeRate(IProductFeeRateVOModel model, IUserModel user);
	
	/**
	 * @param model 传入IProductFeeRateVOModel
	 * @return 返回boolean
	 * @description 新增产品手续费率信息
	 */
	public boolean insertProFeeRate(IProductFeeRateVOModel model);
}