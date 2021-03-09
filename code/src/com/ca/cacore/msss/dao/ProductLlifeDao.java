package  com.ca.cacore.msss.dao;

import java.util.List;

import com.ca.cacore.msss.model.vo.IProductAssessmentVOModel;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.vo.IProductLlifeVOModel;
import com.ca.cacore.msss.model.bo.IProductAssessmentModel;
import com.ca.cacore.msss.model.bo.IProductChargePeriodModel;
import com.ca.cacore.msss.model.bo.IProductChargeTypeModel;
import com.ca.cacore.msss.model.bo.IProductCoveragePeriodModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.bo.IProductRelationModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
 * @author Wang_ds
 * @since 2013-11-20
 * @description 产品信息维护DAO层
 */
@Component
public class ProductLlifeDao extends BaseDao  implements IProductLlifeDao{
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出所有产品信息
	 */
	@Override
	public List<IProductLlifeVOModel> queryProductLlifeList(IProductLlifeVOModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("ProductLlife.queryProductLlife_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IProductLlifeVOModel>)this.getSqlMapClientTemplate().queryForList("ProductLlife.queryProductLlife", model);
	}
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出所有产品信息
	 *//*
	@Override
	public List<IProductLlifeVOModel> queryProductLlifeVOList(IProductLlifeVOModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("ProductLlife.queryProductLlife_countVO",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IProductLlifeVOModel>)this.getSqlMapClientTemplate().queryForList("ProductLlife.queryProductLlifeVO", model);
	}*/
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 修改产品信息
	 */
	@Override
	public Boolean modifyProductLlife(IProductLlifeModel model,IUserModel user) {
		this.getSqlMapClientTemplate().update("ProductLlife.updateProductLlife",model);
		return true;
	}

	/** 
	* 
	* @param model
	* @param user 
	* @description:删除产品的相关信息
	*/
	@Override
	public void deleteProductLlife(IProductLlifeModel model,IUserModel user) {
		//删除产品基本信息
		this.getSqlMapClientTemplate().delete("ProductLlife.deleteProductLlife",model.getSeq_id());
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出单个产品信息
	 */
	@Override
	public IProductLlifeModel getProductLlifeBO(IProductLlifeVOModel model) {
		return (IProductLlifeModel) this.getSqlMapClientTemplate().queryForObject("ProductLlife.getProductLlifeBO", model);
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出单个产品信息
	 */
	@Override
	public IProductLlifeVOModel getProductLlifeVO(IProductLlifeVOModel model) {
		return (IProductLlifeVOModel) this.getSqlMapClientTemplate().queryForObject("ProductLlife.getProductLlifeVO", model);
	}
	
	public IProductLlifeVOModel getProductLlifeVO4Pkg(IProductLlifeVOModel model) {
		return (IProductLlifeVOModel) this.getSqlMapClientTemplate().queryForObject("ProductLlife.getProductLlifeVO4Pkg", model);
	}
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出组合下的产品
	 */
	public List<IProductLlifeVOModel> queryProductLlifeVOList4Pkg(IProductLlifeVOModel model) {
		return (List<IProductLlifeVOModel>) this.getSqlMapClientTemplate().queryForList("ProductLlife.queryProductLlifeVOList4Pkg", model);
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 新增产品信息
	 */
	@Override
	public Boolean addProductLlife(IProductLlifeModel model,IUserModel user) {
		 this.getSqlMapClientTemplate().insert("ProductLlife.insertProductLlife",model);
		return true;
	}
	//添加缴费方式信息
	@Override
	public Boolean addProductChargeType(IProductChargeTypeModel chargeTypeModelBO, IUserModel user) {
		this.getSqlMapClientTemplate().insert("ProductLlife.insertProductChargeType",chargeTypeModelBO);
		return true;
	}
	//删除缴费方式信息
	@Override
	public void deleteProductChargeType(IProductChargeTypeModel model,IUserModel user) {
		this.getSqlMapClientTemplate().delete("ProductLlife.deleteProductChargeType",model);
	}
	//添加保障期限类型信息
	@Override
	public Boolean addProductCoveragePeriod(IProductCoveragePeriodModel coveragePeriodModelBO, IUserModel user) {
		this.getSqlMapClientTemplate().insert("ProductLlife.insertProductCoveragePeriod",coveragePeriodModelBO);
		return true;
	}
	//删除保障期限类型信息
	@Override
	public void deleteProductCoveragePeriod(IProductCoveragePeriodModel model,IUserModel user) {
		this.getSqlMapClientTemplate().delete("ProductLlife.deleteProductCoveragePeriod",model);
	}
	//添加缴费期限类型
	@Override
	public Boolean addProductChargePeriod(IProductChargePeriodModel chargePeriodModelBO, IUserModel user) {
		this.getSqlMapClientTemplate().insert("ProductLlife.insertProductChargePeriod",chargePeriodModelBO);
		return true;
	}
	//删除缴费期限类型信息
	@Override
	public void deleteProductChargePeriod(IProductChargePeriodModel model,IUserModel user) {
		this.getSqlMapClientTemplate().delete("ProductLlife.deleteProductChargePeriod",model);
	}
	//查询缴费方式信息
	public List<IProductChargeTypeModel> queryProductChargeTypeList(IProductChargeTypeModel model) {
		return (List<IProductChargeTypeModel>)this.getSqlMapClientTemplate().queryForList("ProductLlife.queryProductChargeType", model);
	}
	//查询保障期限类型信息
	public List<IProductCoveragePeriodModel> queryProductCoveragePeriodList(IProductCoveragePeriodModel model) {
		return (List<IProductCoveragePeriodModel>)this.getSqlMapClientTemplate().queryForList("ProductLlife.queryProductCoveragePeriod", model);
	}
	//查询缴费期限类型
	public List<IProductChargePeriodModel> queryProductChargePeriodList(IProductChargePeriodModel model) {
		return (List<IProductChargePeriodModel>)this.getSqlMapClientTemplate().queryForList("ProductLlife.queryProductChargePeriod", model);
	}
	//添加评估说明信息
	@Override
	public void addProductEvaluation(IProductLlifeVOModel model, IUserModel user) {
		this.getSqlMapClientTemplate().insert("ProductLlife.insertProductEvaluation",model);
	}
	
/*	//修改评估说明信息
	@Override
	public void updateProductEvaluation(IProductLlifeVOModel model,IUserModel user) {
		this.getSqlMapClientTemplate().update("ProductLlife.updateProductEvaluation",model);
	}
*/	
	//删除评估说明信息
	@Override
	public void deleteProductEvaluation(IProductLlifeVOModel model,IUserModel user) {
		this.getSqlMapClientTemplate().delete("ProductLlife.deleteProductEvaluation",model);
	}
	//添加星星评估说明信息
	@Override
	public void addProductAssessment(IProductAssessmentModel productAssessment, IUserModel user) {
		this.getSqlMapClientTemplate().insert("ProductLlife.insertProductAssessment",productAssessment);
	}
	@Override
	public void deleteProductAssessment(IProductAssessmentModel productAssessmentForDelete) {
		this.getSqlMapClientTemplate().delete("ProductLlife.deletesProductAssessment",productAssessmentForDelete);
		
	}
	//添加寿险主险可选附加产品信息
	@Override
	public void addProductRelation(IProductRelationModel productRelationBO,IUserModel user) {
		this.getSqlMapClientTemplate().insert("ProductLlife.insertProductRelation",productRelationBO);
	}

    @Override
    public List<IProductAssessmentVOModel> getProductAssessments(IProductAssessmentVOModel model) {
        return (List<IProductAssessmentVOModel>)this.getSqlMapClientTemplate().queryForList("ProductLlife.getProductAssessments", model);
    }
	@Override
	public List<IProductRelationModel> queryProductRelationList(IProductRelationModel productRelationBO) {
		return (List<IProductRelationModel>)this.getSqlMapClientTemplate().queryForList("ProductLlife.queryProductRelation", productRelationBO);
	}
	@Override
	public Boolean deleteProductRelation(IProductRelationModel productRelationBO) {
		this.getSqlMapClientTemplate().delete("ProductLlife.deleteProductRelation", productRelationBO);
		return true;
	}
	/** 
	* 
	* @param seq_id
	* @return Integer
	* @description:判断产品是否存在组合产品中
	*/
	@Override
	public Integer checkIsInPkg(String seq_id) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("ProductLlife.checkIsInPkg", seq_id);
	}
	

}
