package com.ca.cacore.msss.dao;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.bo.IProductAssessmentModel;
import com.ca.cacore.msss.model.bo.IProductChargePeriodModel;
import com.ca.cacore.msss.model.bo.IProductChargeTypeModel;
import com.ca.cacore.msss.model.bo.IProductCoveragePeriodModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.vo.IProductAssessmentVOModel;
import com.ca.cacore.msss.model.bo.IProductRelationModel;
import com.ca.cacore.msss.model.vo.IProductLlifeVOModel;
/**
 * 
 * @author wang_ds
 * @since 2013-12-04
 * @description 产品信息信息维护DAO层接口
 */
public interface IProductLlifeDao {
	public List<IProductLlifeVOModel>  queryProductLlifeList(IProductLlifeVOModel model);
	public Boolean modifyProductLlife(IProductLlifeModel model,IUserModel user);
	public Boolean addProductLlife(IProductLlifeModel model,IUserModel user);
	public IProductLlifeModel getProductLlifeBO(IProductLlifeVOModel model);
	public IProductLlifeVOModel getProductLlifeVO(IProductLlifeVOModel model);
	public IProductLlifeVOModel getProductLlifeVO4Pkg(IProductLlifeVOModel model);
	public Boolean addProductChargeType(IProductChargeTypeModel chargeTypeModelBO,IUserModel user);
	public Boolean addProductCoveragePeriod(IProductCoveragePeriodModel coveragePeriodModelBO, IUserModel user);
	public Boolean addProductChargePeriod(IProductChargePeriodModel chargePeriodModelBO, IUserModel user);
	//查询缴费方式信息
	public List<IProductChargeTypeModel> queryProductChargeTypeList(IProductChargeTypeModel model);
	//查询保障期限类型信息
	public List<IProductCoveragePeriodModel> queryProductCoveragePeriodList(IProductCoveragePeriodModel model);
	//查询缴费期限类型
	public List<IProductChargePeriodModel> queryProductChargePeriodList(IProductChargePeriodModel model);
	//添加产品评估说明
	public void addProductEvaluation(IProductLlifeVOModel model, IUserModel user);
	
	/*List<IProductLlifeVOModel> queryProductLlifeVOList(IProductLlifeVOModel model);*/
	
	public void addProductAssessment(IProductAssessmentModel productAssessment, IUserModel user);
	
	public void deleteProductAssessment(IProductAssessmentModel productAssessmentForDelete);
    /**
     * 获取产品评估信息
     * @param product_id
     * @return
     */
    List<IProductAssessmentVOModel> getProductAssessments(IProductAssessmentVOModel model);
	public void addProductRelation(IProductRelationModel productRelationBO,IUserModel user);
	
	public List<IProductRelationModel> queryProductRelationList(IProductRelationModel productRelationBO);
	public Boolean deleteProductRelation(IProductRelationModel productRelationBO);
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出组合下的产品
	 */
	public List<IProductLlifeVOModel> queryProductLlifeVOList4Pkg(IProductLlifeVOModel model);
	public void deleteProductEvaluation(IProductLlifeVOModel model,IUserModel user);
	
	/** 
	* 
	* @param model
	* @param user void
	* @description:
	*/
	public void deleteProductChargeType(IProductChargeTypeModel model, IUserModel user);
	/** 
	* 
	* @param model
	* @param user void
	* @description:
	*/
	public void deleteProductCoveragePeriod(IProductCoveragePeriodModel model,IUserModel user);
	/** 
	* 
	* @param model
	* @param user void
	* @description:
	*/
	public void deleteProductChargePeriod(IProductChargePeriodModel model,IUserModel user);
	/** 
	* 
	* @param model
	* @param user void
	* @description:
	*/
	public void deleteProductLlife(IProductLlifeModel model, IUserModel user);
	/** 
	* 
	* @param seq_id
	* @return Integer
	* @description:
	*/
	public Integer checkIsInPkg(String seq_id);

}
