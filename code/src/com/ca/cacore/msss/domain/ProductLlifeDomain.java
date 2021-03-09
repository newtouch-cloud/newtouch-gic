package  com.ca.cacore.msss.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ca.cacore.msss.model.vo.IProductAssessmentVOModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.msss.dao.IProductLlifeDao;
import com.ca.cacore.msss.model.bo.IProductAssessmentModel;
import com.ca.cacore.msss.model.bo.IProductChargePeriodModel;
import com.ca.cacore.msss.model.bo.IProductChargeTypeModel;
import com.ca.cacore.msss.model.bo.IProductCoveragePeriodModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.bo.IProductRelationModel;
import com.ca.cacore.msss.model.bo.ProductAssessmentModel;
import com.ca.cacore.msss.model.bo.ProductChargePeriodModel;
import com.ca.cacore.msss.model.bo.ProductChargeTypeModel;
import com.ca.cacore.msss.model.bo.ProductCoveragePeriodModel;
import com.ca.cacore.msss.model.bo.ProductLlifeModel;
import com.ca.cacore.msss.model.bo.ProductRelationModel;
import com.ca.cacore.msss.model.vo.IProductLlifeVOModel;
import com.ca.cacore.msss.model.vo.ProductAssessmentVOModel;
import com.ca.cacore.msss.model.vo.ProductLlifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.serverbase.ServerBase;
/**
 * 
 * @author Wang_ds
 * @since 2013-11-20
 * @description 产品信息维护Domain层
 */
@Service
public class ProductLlifeDomain extends ServerBase implements IProductLlifeDomain{
	@Autowired private IProductLlifeDao ProductLlifeDao;
	/**
	 * @param model 传入IProductLlifeVOModel
	 * @return 返回一个 List<IProductLlifeVOModel>对象
	 * @description 查询出产品信息，或根据条件查询
	 */
	public List<IProductLlifeVOModel> queryProductLlifeList(IProductLlifeVOModel model){
		return ProductLlifeDao.queryProductLlifeList(model);
	}
	/**
	 * @param 传入IProductLlifeVOModel model,IUserModel user
	 * @return 返回一个Boolean
	 * @description 修改产品信息
	 */
	public Boolean modifyProductLlife(IProductLlifeVOModel model,IUserModel user) {
		
		Date timeTemp;
		if(model.getStart_date() == null){
			timeTemp = new java.sql.Date(System.currentTimeMillis());//没有录入起售时间，默认为立即起售
		}else{
			timeTemp = model.getStart_date();
		}
		IProductRelationModel productRelationBO = new ProductRelationModel(); //产品主附加险关系
		
		//录入的字段的长度校验
		checkProductLength(model);
		
		if(!productLlifeNameIsHave(model)){
			throw new BusinessException("该产品名称已存在，请重新录入!");
		}
		checkProductLlifeRelation(model);
		checkProductIdIsSame(model);
		 //校验停售时间不能小于起售时间
		if(model.getEnd_date() != null){
	        if(model.getEnd_date().before(timeTemp)){
	        	throw new BusinessException("停售时间不能小于起售时间!");
	        }
		}
		
		IProductLlifeModel modelBO = new ProductLlifeModel();
		IProductLlifeVOModel modelForQuery = new ProductLlifeVOModel();//用于查询IProductLlifeVOModel
		modelForQuery.setSeq_id(model.getSeq_id());
		
		modelBO = ProductLlifeDao.getProductLlifeBO(modelForQuery);//获取到要被修改的产品
		modelBO.setProduct_enName(model.getProduct_enName());
		modelBO.setProduct_abbr(model.getProduct_abbr());
		modelBO.setProduct_name(model.getProduct_name());
		modelBO.setProduct_enAbbr(model.getProduct_enAbbr());
		
		modelBO.setProduct_type(model.getProduct_type());
		modelBO.setProduct_type2(model.getProduct_type2());
		modelBO.setProduct_type3(model.getProduct_type3());
		modelBO.setIns_type(model.getIns_type());
		modelBO.setPeriod_type(model.getPeriod_type());
		
		modelBO.setSurr_permit(model.getSurr_permit());
		modelBO.setRenew(model.getRenew());
		modelBO.setInsuredFlag(model.getInsuredFlag());
		modelBO.setIs_auth_partreceived(model.getIs_auth_partreceived());
		modelBO.setIs_can_borrow(model.getIs_can_borrow());
		modelBO.setBenefit_type(model.getBenefit_type());
		modelBO.setDuty(model.getDuty());
		modelBO.setPolicy_holder(model.getPolicy_holder());
		modelBO.setException(model.getException());
		modelBO.setRemark(model.getRemark());
		modelBO.setStart_date(timeTemp);
		modelBO.setEnd_date(model.getEnd_date());
		modelBO.setModifyUser(user.getEmp_id());
		//修改方式类型start
		IProductChargeTypeModel chargeTypeModelBO = new ProductChargeTypeModel();//缴费方式
		IProductCoveragePeriodModel coveragePeriodModelBO= new ProductCoveragePeriodModel();//保障期限类型
		IProductChargePeriodModel chargePeriodModelBO = new ProductChargePeriodModel(); //缴费期限类型
		chargeTypeModelBO.setProduct_id(model.getProduct_id());
		chargeTypeModelBO.setInsBranch_id(model.getInsBranch_id());
		coveragePeriodModelBO.setProduct_id(model.getProduct_id());
		coveragePeriodModelBO.setInsBranch_id(model.getInsBranch_id());
		chargePeriodModelBO.setProduct_id(model.getProduct_id());
		chargePeriodModelBO.setInsBranch_id(model.getInsBranch_id());
		
		//删除缴费方式
		ProductLlifeDao.deleteProductChargeType(chargeTypeModelBO, user);
		//循环添加缴费方式信息
		for(int i=0;i<model.getCharge_type_codes().length;i++){
			
			chargeTypeModelBO.setInsBranch_id(model.getInsBranch_id());
			chargeTypeModelBO.setProduct_id(model.getProduct_id());
			chargeTypeModelBO.setProduct_ver("2013");//产品版本
			chargeTypeModelBO.setCharge_type_code(model.getCharge_type_codes()[i]);
			chargeTypeModelBO.setRemark(model.getRemark());
			chargeTypeModelBO.setCreateUser(user.getEmp_id());
			chargeTypeModelBO.setModifyUser(user.getEmp_id());
			ProductLlifeDao.addProductChargeType(chargeTypeModelBO,user);
		}
		//删除保障期限类型信息
		ProductLlifeDao.deleteProductCoveragePeriod(coveragePeriodModelBO, user);
		//循环添加保障期限类型信息
       for(int i=0;i<model.getCoverage_period_codes().length;i++){
			
    	   coveragePeriodModelBO.setInsBranch_id(model.getInsBranch_id());
    	   coveragePeriodModelBO.setProduct_id(model.getProduct_id());
    	   coveragePeriodModelBO.setProduct_ver("2013");//产品版本
    	   coveragePeriodModelBO.setCoverage_period_code(model.getCoverage_period_codes()[i]);
    	   coveragePeriodModelBO.setRemark(model.getRemark());
    	   coveragePeriodModelBO.setCreateUser(user.getEmp_id());
    	   coveragePeriodModelBO.setModifyUser(user.getEmp_id());
			ProductLlifeDao.addProductCoveragePeriod(coveragePeriodModelBO,user);
		}
	     //删除保障期限类型信息
		ProductLlifeDao.deleteProductChargePeriod(chargePeriodModelBO, user);
		//循环添加缴费期限类型
	   for(int i=0;i<model.getCharge_period_codes().length;i++){
			
		   chargePeriodModelBO.setInsBranch_id(model.getInsBranch_id());
		   chargePeriodModelBO.setProduct_id(model.getProduct_id());
		   chargePeriodModelBO.setProduct_ver("2013");//产品版本
		   chargePeriodModelBO.setCharge_period_code(model.getCharge_period_codes()[i]);
		   chargePeriodModelBO.setRemark(model.getRemark());
		   chargePeriodModelBO.setCreateUser(user.getEmp_id());
		   chargePeriodModelBO.setModifyUser(user.getEmp_id());
			ProductLlifeDao.addProductChargePeriod(chargePeriodModelBO,user);
		}
	 //修改方式类型end
			
		//修改寿险主险可选附加产品信息(先删除，在重新添加)
			if(model.getIns_type().equals(CodeTypeConst.INS_TYPE_CODE1)){   //'1'代码主险，可以配置多个附加险
				 productRelationBO.setMaster_id(model.getProduct_id());
				 productRelationBO.setInsBranch_id(model.getInsBranch_id());
				 ProductLlifeDao.deleteProductRelation(productRelationBO);//删除主附加险关系
				 if(model.getProduct_ids().length != 1){
					 for(int i=0;i<model.getProduct_ids().length-1;i++){
				    	   
				    	   productRelationBO.setInsBranch_id(model.getInsBranch_id());
				    	   productRelationBO.setMaster_id(model.getProduct_id());
				    	   productRelationBO.setProduct_id(model.getProduct_ids()[i]);
				    	   productRelationBO.setStatus(CodeTypeConst.PRO_STATUS_CODE1);
				    	   productRelationBO.setCreateUser(user.getEmp_id());
				    	   productRelationBO.setModifyUser(user.getEmp_id());
				    	   
				    	   ProductLlifeDao.addProductRelation(productRelationBO,user);
					 }
				 }
			}else{   // 可以配置多个主险
				 productRelationBO.setProduct_id(model.getProduct_id());
				 productRelationBO.setInsBranch_id(model.getInsBranch_id());
				 ProductLlifeDao.deleteProductRelation(productRelationBO);//删除主附加险关系
				 if(model.getProduct_ids().length != 1){
					 for(int i=0;i<model.getProduct_ids().length-1;i++){
				    	   productRelationBO.setInsBranch_id(model.getInsBranch_id());
				    	   productRelationBO.setProduct_id(model.getProduct_id());
				    	   productRelationBO.setMaster_id(model.getProduct_ids()[i]);
				    	   productRelationBO.setStatus(CodeTypeConst.PRO_STATUS_CODE1);
				    	   productRelationBO.setCreateUser(user.getEmp_id());
				    	   productRelationBO.setModifyUser(user.getEmp_id());
				    	   
				    	   ProductLlifeDao.addProductRelation(productRelationBO,user);
			    	   }
				 }
			} 
		ProductLlifeDao.modifyProductLlife(modelBO, user);
		return true;
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ReturnMsg对象
	 * @description 查询单个产品信息
	 */
	@Override
	public IProductLlifeVOModel getProductLlifeVO(IProductLlifeVOModel model) {
		
		IProductLlifeVOModel productLlifeModelVO =  ProductLlifeDao.getProductLlifeVO(model);
		
		IProductChargeTypeModel chargeTypeModelBO = new ProductChargeTypeModel(productLlifeModelVO.getProduct_id());//缴费方式
		chargeTypeModelBO.setInsBranch_id(productLlifeModelVO.getInsBranch_id());
		IProductCoveragePeriodModel coveragePeriodModelBO= new ProductCoveragePeriodModel(productLlifeModelVO.getProduct_id());//保障期限类型
		coveragePeriodModelBO.setInsBranch_id(productLlifeModelVO.getInsBranch_id());
		IProductChargePeriodModel chargePeriodModelBO = new ProductChargePeriodModel(productLlifeModelVO.getProduct_id()); //缴费期限类型
		chargePeriodModelBO.setInsBranch_id(productLlifeModelVO.getInsBranch_id());
		
		IProductRelationModel productRelationBO = new ProductRelationModel(); //产品主附加险关系
		
		IProductAssessmentVOModel productAssessmentVO = new ProductAssessmentVOModel();
		
        // 获取产品评估结果
		productAssessmentVO.setProduct_id(productLlifeModelVO.getProduct_id());
		productAssessmentVO.setInsBranch_id(productLlifeModelVO.getInsBranch_id());
        List<IProductAssessmentVOModel> productAssessmentVOModelList = ProductLlifeDao.getProductAssessments(productAssessmentVO);
        productLlifeModelVO.setProductAssessmentVOModelList(productAssessmentVOModelList);
		
		//取出缴费方式
		List<IProductChargeTypeModel> chargeTypeList = ProductLlifeDao.queryProductChargeTypeList(chargeTypeModelBO);
		String [] charge_type_codes = new String[chargeTypeList.size()];
		String charge_type_code = "";
		//拼接成a#,#b#,#c格式的字符串。用于传给动态多选框
		for(int i=0;i<chargeTypeList.size();i++){
			charge_type_codes[i] = chargeTypeList.get(i).getCharge_type_code();
			if(i == chargeTypeList.size()-1){
				charge_type_code =charge_type_code+charge_type_codes[i];
			}else{
			 charge_type_code =charge_type_code+charge_type_codes[i]+"#,#";
			}
		}
		productLlifeModelVO.setCharge_type_code(charge_type_code);
		
		//取出保障期限类型
		List<IProductCoveragePeriodModel> coveragePeriodList = ProductLlifeDao.queryProductCoveragePeriodList(coveragePeriodModelBO);
		String [] coverage_period_codes = new String[coveragePeriodList.size()];
		String coverage_period_code = "";
		//拼接成a#,#b#,#c格式的字符串。用于传给动态多选框
		for(int i=0;i<coveragePeriodList.size();i++){
			coverage_period_codes[i] = coveragePeriodList.get(i).getCoverage_period_code();
			if(i == coveragePeriodList.size()-1){
				coverage_period_code =coverage_period_code+coverage_period_codes[i];
			}else{
				coverage_period_code =coverage_period_code+coverage_period_codes[i]+"#,#";
			}
		}
		productLlifeModelVO.setCoverage_period_code(coverage_period_code);
		
		//取出缴费期限类型
		List<IProductChargePeriodModel> chargePeriodList = ProductLlifeDao.queryProductChargePeriodList(chargePeriodModelBO);
		String [] charge_period_codes = new String[chargePeriodList.size()];
		String charge_period_code  = "";
		//拼接成a#,#b#,#c格式的字符串。用于传给动态多选框
		for(int i=0;i<chargePeriodList.size();i++){
			charge_period_codes[i] = chargePeriodList.get(i).getCharge_period_code();
			if(i == chargePeriodList.size()-1){
				charge_period_code =charge_period_code+charge_period_codes[i];
			}else{
				charge_period_code =charge_period_code+charge_period_codes[i]+"#,#";
			}
		}
		productLlifeModelVO.setCharge_period_code(charge_period_code);
		//取配置的主附加险
				IProductLlifeVOModel productLlifeForQuery = new ProductLlifeVOModel();
				List<IProductLlifeVOModel> productList = new ArrayList<IProductLlifeVOModel>();
				if(productLlifeModelVO.getIns_type().equals(CodeTypeConst.INS_TYPE_CODE1)){  //产品表存的‘1’主险。取出多个附加险
					productRelationBO.setMaster_id(productLlifeModelVO.getProduct_id());
					productRelationBO.setInsBranch_id(productLlifeModelVO.getInsBranch_id()); //根据保险公司代码，产品代码确定唯一性
					List<IProductRelationModel> productRelationList = ProductLlifeDao.queryProductRelationList(productRelationBO);//拿到该主险下的附加险
					for(IProductRelationModel pR : productRelationList){
						productLlifeForQuery.setProduct_id(pR.getProduct_id());
						productLlifeForQuery.setInsBranch_id(pR.getInsBranch_id());//根据保险公司代码，产品代码确定唯一性
						IProductLlifeVOModel productLlife2 = ProductLlifeDao.getProductLlifeVO(productLlifeForQuery);//循环取出附加险产品信息
						productList.add(productLlife2);
					}
				}else{//产品表存的附加险。取出多个主险
					productRelationBO.setProduct_id(productLlifeModelVO.getProduct_id());
					productRelationBO.setInsBranch_id(productLlifeModelVO.getInsBranch_id());//根据保险公司代码，产品代码确定唯一性
					List<IProductRelationModel> productRelationList = ProductLlifeDao.queryProductRelationList(productRelationBO);//拿到该附加险下的主险
					for(IProductRelationModel pR : productRelationList){
						productLlifeForQuery.setProduct_id(pR.getMaster_id());
						productLlifeForQuery.setInsBranch_id(pR.getInsBranch_id());//根据保险公司代码，产品代码确定唯一性
						IProductLlifeVOModel productLlife1 = ProductLlifeDao.getProductLlifeVO(productLlifeForQuery);//循环取出附加险产品信息
						productList.add(productLlife1);
					}
				}
				productLlifeModelVO.setProductList(productList);
		
		return productLlifeModelVO;
	}

	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 添加产品信息
	 */
	@Override
	public Boolean addProductLlife(IProductLlifeVOModel model, IUserModel user) {
		
		Date timeTemp;
		if(model.getStart_date() == null){//没有录入起售时间
			timeTemp = new java.sql.Date(System.currentTimeMillis());//没有录入起售时间，默认为立即起售
		}else{   
			timeTemp = model.getStart_date();//录入的起售时间
		}
		
		//业务校验开始
		if(!productLlifeIdIsHave(model)){
			throw new BusinessException("该产品代码已存在，请重新录入!");
		}
		if(!productLlifeNameIsHave(model)){
			throw new BusinessException("该产品名称已存在，请重新录入!");
		}
		
		checkProductLength(model);//录入的字段的长度校验
       
        checkProductLlifeRelation(model); // 判断主险附加险校验
       
        checkProductIdIsSame(model); //校验是否录入了相同的产品
       
        if(model.getEnd_date() != null){ //校验停售时间不能小于起售时间
	        if(model.getEnd_date().before(timeTemp)){
	        	throw new BusinessException("停售时间不能小于起售时间!");
	        }
        }
		//业务校验结束
		
		IProductLlifeModel productLlifeModelBO = new ProductLlifeModel();//产品信息
		IProductChargeTypeModel chargeTypeModelBO = new ProductChargeTypeModel();//缴费方式
		IProductCoveragePeriodModel coveragePeriodModelBO= new ProductCoveragePeriodModel();//保障期限类型
		IProductChargePeriodModel chargePeriodModelBO = new ProductChargePeriodModel(); //缴费期限类型
		IProductRelationModel productRelationBO = new ProductRelationModel(); //产品主附加险关系
		//新增产品信息
		productLlifeModelBO.setInsBranch_id(model.getInsBranch_id());
		productLlifeModelBO.setProduct_id(model.getProduct_id());
		productLlifeModelBO.setProduct_ver("2013");      //产品的版本设了个固定值
		productLlifeModelBO.setProduct_type(model.getProduct_type());
		productLlifeModelBO.setProduct_type2(model.getProduct_type2());
		productLlifeModelBO.setProduct_type3(model.getProduct_type3());
		productLlifeModelBO.setIns_type(model.getIns_type());
		productLlifeModelBO.setPeriod_type(model.getPeriod_type());
		productLlifeModelBO.setStatus(CodeTypeConst.PRO_STATUS_CODE1);  //设置状态为"1",代表有效
		productLlifeModelBO.setProduct_enName(model.getProduct_enName());
		productLlifeModelBO.setProduct_abbr(model.getProduct_abbr());
		productLlifeModelBO.setProduct_name(model.getProduct_name());
		productLlifeModelBO.setProduct_enAbbr(model.getProduct_enAbbr());
		productLlifeModelBO.setSurr_permit(model.getSurr_permit());
		productLlifeModelBO.setRenew(model.getRenew());
		productLlifeModelBO.setIs_auth_partreceived(model.getIs_auth_partreceived());
		productLlifeModelBO.setIs_can_borrow(model.getIs_can_borrow());
		productLlifeModelBO.setInsuredFlag(model.getInsuredFlag());
		productLlifeModelBO.setBenefit_type(model.getBenefit_type());
		productLlifeModelBO.setDuty(model.getDuty());
		productLlifeModelBO.setPolicy_holder(model.getPolicy_holder());
		productLlifeModelBO.setException(model.getException());
		productLlifeModelBO.setRemark(model.getRemark());
		productLlifeModelBO.setStart_date(timeTemp);//起售时间
		productLlifeModelBO.setEnd_date(model.getEnd_date());
		productLlifeModelBO.setCreateUser(user.getEmp_id());
		productLlifeModelBO.setModifyUser(user.getEmp_id());
		//生成内部产品代码
		String innerPId =  createInnerPId(model.getInsBranch_id(),model.getProduct_id());
		productLlifeModelBO.setInner_product_id(innerPId);
		
		ProductLlifeDao.addProductLlife(productLlifeModelBO, user);
		//循环添加缴费方式信息
		for(int i=0;i<model.getCharge_type_codes().length;i++){
			
			chargeTypeModelBO.setInsBranch_id(model.getInsBranch_id());
			chargeTypeModelBO.setProduct_id(model.getProduct_id());
			chargeTypeModelBO.setProduct_ver("2013");//产品版本
			chargeTypeModelBO.setCharge_type_code(model.getCharge_type_codes()[i]);
			chargeTypeModelBO.setRemark(model.getRemark());
			chargeTypeModelBO.setCreateUser(user.getEmp_id());
			chargeTypeModelBO.setModifyUser(user.getEmp_id());
			ProductLlifeDao.addProductChargeType(chargeTypeModelBO,user);
		}
		//循环添加保障期限类型信息
       for(int i=0;i<model.getCoverage_period_codes().length;i++){
			
    	   coveragePeriodModelBO.setInsBranch_id(model.getInsBranch_id());
    	   coveragePeriodModelBO.setProduct_id(model.getProduct_id());
    	   coveragePeriodModelBO.setProduct_ver("2013");//产品版本
    	   coveragePeriodModelBO.setCoverage_period_code(model.getCoverage_period_codes()[i]);
    	   coveragePeriodModelBO.setRemark(model.getRemark());
    	   coveragePeriodModelBO.setCreateUser(user.getEmp_id());
    	   coveragePeriodModelBO.setModifyUser(user.getEmp_id());
			ProductLlifeDao.addProductCoveragePeriod(coveragePeriodModelBO,user);
		}
		//循环添加缴费期限类型
       for(int i=0;i<model.getCharge_period_codes().length;i++){
			
    	   chargePeriodModelBO.setInsBranch_id(model.getInsBranch_id());
    	   chargePeriodModelBO.setProduct_id(model.getProduct_id());
    	   chargePeriodModelBO.setProduct_ver("2013");//产品版本
    	   chargePeriodModelBO.setCharge_period_code(model.getCharge_period_codes()[i]);
    	   chargePeriodModelBO.setRemark(model.getRemark());
    	   chargePeriodModelBO.setCreateUser(user.getEmp_id());
    	   chargePeriodModelBO.setModifyUser(user.getEmp_id());
			ProductLlifeDao.addProductChargePeriod(chargePeriodModelBO,user);
		}
       
       //寿险主险可选附加产品信息
       if(model.getProduct_ids().length != 1)//配置了主/附险信息
	       if(model.getIns_type().equals(CodeTypeConst.INS_TYPE_CODE1)){//添加的产品为主险，1代表主险
	    	
		       for(int i=0;i<model.getProduct_ids().length-1;i++){
		    	   productRelationBO.setInsBranch_id(model.getInsBranch_id());
		    	   productRelationBO.setMaster_id(model.getProduct_id());
		    	   productRelationBO.setProduct_id(model.getProduct_ids()[i]);
		    	   productRelationBO.setStatus(CodeTypeConst.PRO_STATUS_CODE1);  //设置状态为"1",代表有效
		    	   productRelationBO.setCreateUser(user.getEmp_id());
		    	   productRelationBO.setModifyUser(user.getEmp_id());
		    	   
		    	   ProductLlifeDao.addProductRelation(productRelationBO,user);
				}
	       }else{ //添加的产品为附加险
	    	   for(int i=0;i<model.getProduct_ids().length-1;i++){
		    	   productRelationBO.setInsBranch_id(model.getInsBranch_id());
		    	   productRelationBO.setProduct_id(model.getProduct_id());
		    	   productRelationBO.setMaster_id(model.getProduct_ids()[i]);
		    	   productRelationBO.setStatus(CodeTypeConst.PRO_STATUS_CODE1); //设置状态为"1",代表有效
		    	   productRelationBO.setCreateUser(user.getEmp_id());
		    	   productRelationBO.setModifyUser(user.getEmp_id());
		    	   
		    	   ProductLlifeDao.addProductRelation(productRelationBO,user);
	    	   }
	       }
			return true;
	}
	
	
	/** 
	* 
	* @param insb_id
	* @param pro_id
	* @return String
	* @description:生成内部产品代码 生成规则为保险公司机构号+产品代码+流水号，一共30位。
	*/
	public String createInnerPId(String insb_id,String pro_id){
		String inner_product_id =""; 
		inner_product_id = insb_id+pro_id;
		int len= inner_product_id.length();
		if(len < 30){
			for(int i= 0;i<30-len;i++){
				inner_product_id=inner_product_id+"0";
			}
		}
		return inner_product_id;
	}
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 添加产品评估信息
	 */
	@Override
	public Boolean addProductEvaluation(IProductLlifeVOModel model,IUserModel user) {
		int s = model.getDescription().length();
		if(model.getDescription().length() >500){
			throw  new BusinessException("评估说明不能超过500个字符！");
		}
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());

        //更新星级评估
        IProductAssessmentModel productAssessmentForDelete = new ProductAssessmentModel();
        productAssessmentForDelete.setProduct_id(model.getProduct_id());
        productAssessmentForDelete.setInsBranch_id(model.getInsBranch_id());
        //先删除
        ProductLlifeDao.deleteProductAssessment(productAssessmentForDelete);
        List<IProductAssessmentModel> productAssessmentList  = model.getProductAssessmentList();
        for(IProductAssessmentModel productAssessment : productAssessmentList){
            productAssessment.setProduct_id(model.getProduct_id());
            productAssessment.setInsBranch_id(model.getInsBranch_id());
            productAssessment.setCreateUser(user.getEmp_id());
            productAssessment.setModifyUser(user.getEmp_id());

            productAssessment.setAssess_stars(ValidateHelper.IsNullOrEmpty(productAssessment.getAssess_stars())?0:productAssessment.getAssess_stars());

            //重新添加
            ProductLlifeDao.addProductAssessment(productAssessment,user);
        }
        
        //先删除增评估说明
        ProductLlifeDao.deleteProductEvaluation(model,user);
       
        if(!model.getDescription().equals("")){ //重新添加新增评估说明
			ProductLlifeDao.addProductEvaluation(model,user);
        }
		
		return true;
	}
	/** 
	* 
	* @param model
	* @param user 
	* @description:删除产品的相关信息
	*/
	@Override
	public Boolean deleteProductLlife(IProductLlifeModel model,IUserModel user) {
		
		//判断这个产品是否存在产品组合当中，若存在不允许删除
		if(ProductLlifeDao.checkIsInPkg(model.getSeq_id()+"") != 0){
			throw new BusinessException("该产品存在组合产品中，不允许删除!");
		}
		//删除产品基本信息
		ProductLlifeDao.deleteProductLlife(model, user);
		//删除缴费方式
		IProductChargeTypeModel pctm = new ProductChargeTypeModel();
		pctm.setProduct_id(model.getProduct_id());
		pctm.setInsBranch_id(model.getInsBranch_id());
		ProductLlifeDao.deleteProductChargeType(pctm, user);
		//删除保障期限类型
		IProductCoveragePeriodModel  pcpm = new ProductCoveragePeriodModel();
		pcpm.setProduct_id(model.getProduct_id());
		pcpm.setInsBranch_id(model.getInsBranch_id());
		ProductLlifeDao.deleteProductCoveragePeriod(pcpm, user);
		//删除缴费期限类型
		IProductChargePeriodModel pgpm = new ProductChargePeriodModel();
		pgpm.setProduct_id(model.getProduct_id());
		pgpm.setInsBranch_id(model.getInsBranch_id());
		ProductLlifeDao.deleteProductChargePeriod(pgpm, user);
		//删除评估信息
		IProductLlifeVOModel plvm = new ProductLlifeVOModel();
		plvm.setProduct_id(model.getProduct_id());
		plvm.setInsBranch_id(model.getInsBranch_id());
		ProductLlifeDao.deleteProductEvaluation(plvm, user);
		//删除星级评估
		IProductAssessmentModel pasm = new ProductAssessmentModel();
		pasm.setProduct_id(model.getProduct_id());
		pasm.setInsBranch_id(model.getInsBranch_id());
		ProductLlifeDao.deleteProductAssessment(pasm);
		//删除主附险配置信息
		IProductRelationModel prtm = new ProductRelationModel();
		if(model.getIns_type().equals(CodeTypeConst.INS_TYPE_CODE1)){   //'1'代码主险_id());
			prtm.setMaster_id(model.getProduct_id());
		}else{
			prtm.setProduct_id(model.getProduct_id());
		}
		prtm.setInsBranch_id(model.getInsBranch_id());
		ProductLlifeDao.deleteProductRelation(prtm);
		return true;
	}
	
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个String对象
	 * @description getProductLife4Pkg 用于前台输入产品代码，带出产品信息
	 */
	//输入产品代码带出产品信息
	@Override
	public IProductLlifeVOModel getProductLife4Pkg(IProductLlifeVOModel model) {
		return ProductLlifeDao.getProductLlifeVO4Pkg(model);
	}
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个Boolean对象
	 * @description productLlifeIdIsHave 产品代码是否相同的校验方法实现
	 */
	//判断产品代码是否重复
	public Boolean productLlifeIdIsHave(IProductLlifeVOModel model){//产品代码是否相同的校验方法实现
		IProductLlifeVOModel queryVO = new ProductLlifeVOModel();
		queryVO.setProduct_id(model.getProduct_id().trim());
		queryVO.setInsBranch_id(model.getInsBranch_id().trim());
		IProductLlifeModel productLlifeModel = ProductLlifeDao.getProductLlifeBO(queryVO);
	
		if(productLlifeModel != null){//判断是不是为空。
			if(productLlifeModel.getInsBranch_id().equals(model.getInsBranch_id()) && !productLlifeModel.getSeq_id().equals(model.getSeq_id()) && 
				productLlifeModel.getProduct_id().equals(model.getProduct_id())){  //判断是不是同一家公司 //不是同一条数据才不允许修改，是就允许修改。
					return false;
				}
		}
		return true;
	}
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个Boolean对象
	 * @description productLlifeNameIsHave 判断产品名称是否重复
	 */
    public Boolean productLlifeNameIsHave(IProductLlifeVOModel model){  //产品代码是否相同的校验方法实现
        IProductLlifeVOModel queryVO = new ProductLlifeVOModel();
        queryVO.setProduct_name(model.getProduct_name().trim());
        queryVO.setInsBranch_id(model.getInsBranch_id().trim());
        IProductLlifeModel productLlifeModel = ProductLlifeDao.getProductLlifeBO(queryVO);
        if(productLlifeModel != null){//判断是不是为空。
            if(productLlifeModel.getInsBranch_id().equals(model.getInsBranch_id()) && !productLlifeModel.getSeq_id().equals(model.getSeq_id()) &&
                productLlifeModel.getProduct_name().equals(model.getProduct_name())){  //判断是不是同一家公司           不是同一条数据才不允许修改，是就允许修改。
                    return false;
                }
        }
        return true;
    }
    /**
     * 校验是否录入存在相同的产品
     * @param model 产品model
     * @throws BusinessException 不合法时抛出此异常
     */
    public void checkProductIdIsSame(IProductLlifeVOModel model) throws BusinessException{
    	String[] product_ids = model.getProduct_ids();
    	if(product_ids != null){
    	 if(product_ids.length > 2){//至少有2条，校验是否有相同的产品信息
    		 for(int i=0;i<product_ids.length;i++){        
    			 for(int j=i+1;j<product_ids.length;j++){      
    				 if(product_ids[i].equals(product_ids[j])){    //如果元素相同，抛出异常                    
    					 throw new BusinessException("存在相同的产品！");
    				 }
    			 }
    		 }
    	 }
    	}
    }
    
    /**
     * 校验录入字段的长度超过数据库定义的长度
     * @param model 产品model
     * @throws BusinessException 不合法时抛出此异常
     */
    public void checkProductLength(IProductLlifeVOModel model) throws BusinessException{

    	if(model.getProduct_id().length() > 10){
    		 throw new BusinessException("产品代码不能超过10个字符！");
    	}
    	if(model.getProduct_name().length() > 50){
    		throw new BusinessException("产品名称不能超过50个字符！");
    	}
    	if(model.getProduct_abbr().length() > 50){
    		throw new BusinessException("产品简称不能超过50个字符！");
    	}
    	if(model.getProduct_enName().length() > 50){
    		throw new BusinessException("产品英文名称不能超过50个字符！");
    	}
    	if(model.getProduct_enAbbr().length() > 50){
    		throw new BusinessException("产品英文简称不能超过50个字符！");
    	}
    	if(model.getBenefit_type().length() > 2000){
    		throw new BusinessException("保障范围说明不能超过2000个字符！");
    	}
    	if(model.getDuty().length() > 2000){
    		throw new BusinessException("基本责任说明不能超过2000个字符！");
    	}
    	if(model.getPolicy_holder().length() > 2000){
    		throw new BusinessException("投保人员限制说明不能超过2000个字符！");
    	}
    	if(model.getException().length() > 2000){
    		throw new BusinessException("保险责任免除说明说明不能超过2000个字符！");
    	}
    	if(model.getRemark().length() > 501){
    		throw new BusinessException("备注说明说明不能超过500个字符！");
    	}
    }
    /**
     * 校验主险附加险正确性
     * @param model 产品model
     * @throws BusinessException 不合法时抛出此异常
     */
    public void checkProductLlifeRelation(IProductLlifeVOModel model) throws BusinessException {
        if(model.getProduct_ids() != null && model.getProduct_ids().length > 1) {
            String[] insTypeNameArr = model.getIns_type_names();
            String[] insBranchIdArr = model.getInsBranch_ids();
            if(insTypeNameArr != null){
            // 主险
	            if (CodeTypeConst.INS_TYPE_CODE1.equals(model.getIns_type())) {
	                // 主险标记
	                boolean flag = false;
	                for (String insTypeName : insTypeNameArr) {
	                    if (CodeTypeConst.INS_TYPE_CODE1.equals(insTypeName)) {
	                        flag = true;
	                        break;
	                    }
	                }
	
	                if (flag) {
	                    throw new BusinessException("选择主险标记时，只能添加附加险！");
	                }
	            } else {
	                // 附加险标记
	                boolean flag = false;
	                for (String insTypeName : insTypeNameArr) {
	                    if (CodeTypeConst.INS_TYPE_CODE2.equals(insTypeName)) {
	                        flag = true;
	                        break;
	                    }
	                }
	
	                if (flag) {
	                    throw new BusinessException("选择附加险标记时，只能添加主险！");
	                }
	            }
            }
            // 校验是否属于同一保险公司
            if(insBranchIdArr != null){
	            for (String insBranchId : insBranchIdArr) {
	                if (!insBranchId.equals(model.getInsBranch_id())) {
	                    throw new BusinessException("产品不属于同一家保险公司机构！");
	                }
	            }
            }
        }
    }
	
}
