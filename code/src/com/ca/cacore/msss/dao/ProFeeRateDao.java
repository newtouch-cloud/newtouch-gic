package  com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.msss.model.bo.IProductFeeRateModel;
import com.ca.cacore.msss.model.vo.IProductFeeRateVOModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
 * @author fqy
 * @since 2014-1-10
 * @description 产品手续费率管理DAO层
 */
@Component
public class ProFeeRateDao extends BaseDao implements IProFeeRateDao{
	/**
	 * @param 传入IProductFeeRateVOModel
	 * @return 返回一个List对象
	 * @description 查询出所有产品手续费率信息
	 */
	@Override
	public List<IProductFeeRateVOModel> queryProFeeRateList(
			IProductFeeRateVOModel model) {
		int count = (Integer)this.getSqlMapClientTemplate().queryForObject("productFeeRate.queryProFeeRate_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IProductFeeRateVOModel>)this.getSqlMapClientTemplate().queryForList("productFeeRate.queryProFeeRate", model);
	}
	
	/** 
	* @param IProductFeeRateVOModel
	* @return IProductFeeRateVOModel
	* @description:查询选中的产品手续费率明细信息
	*/
	@Override
	public IProductFeeRateVOModel queryProFeeRateView(IProductFeeRateVOModel model) {
		return (IProductFeeRateVOModel)this.getSqlMapClientTemplate().queryForObject("productFeeRate.queryProFeeRateView",model);
	}
	
	/** 
	* @param IProductFeeRateModel
	* @return IProductFeeRateModel
	* @description:根据产品手续费率纬度查询产品手续费率信息
	*/
	@Override
	public IProductFeeRateModel getProFeeRateBO(IProductFeeRateModel model) {
		return (IProductFeeRateModel)this.getSqlMapClientTemplate().queryForObject("productFeeRate.getProFeeRateBO",model);
	}
	
	/** 
	* @param model
	* @param user
	* @return boolean
	* @description:修改选中的产品手续费率信息
	*/
	@Override
	public boolean modifyProFeeRate(IProductFeeRateVOModel model) {
		this.getSqlMapClientTemplate().update("productFeeRate.modifyProFeeRate",model);
		return true;
	}
	
	/** 
	* @param model
	* @param user
	* @return boolean
	* @description:新增产品手续费率信息
	*/
	@Override
	public boolean insertProFeeRate(IProductFeeRateVOModel model) {
		this.getSqlMapClientTemplate().insert("productFeeRate.insertProFeeRate",model);
		return true;
	}
}
