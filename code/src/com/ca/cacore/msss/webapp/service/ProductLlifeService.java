package com.ca.cacore.msss.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.domain.IProductLlifeDomain;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.vo.IProductLlifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 
 * @author  Wang_ds
 * @since 2013-11-10
 * @description 产品信息Service层
 */
@Service
public class ProductLlifeService extends ServerBase implements IProductLlifeService {

	@Autowired
	private IProductLlifeDomain ProductLlifeDomain;


	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个ModelAndView对象
	 * @description 查询出所有产品信息，或根据条件查询(没有关联产品组合表)
	 */
	public ReturnMsg queryProductLlifeList(IProductLlifeVOModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IProductLlifeVOModel> relist = ProductLlifeDomain.queryProductLlifeList(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个ModelAndView对象
	 * @description 查询出所有产品信息，或根据条件查询(关联了产品组合表)
	 *//*
	public ReturnMsg queryProductLlifeVOList(IProductLlifeVOModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IProductLlifeVOModel> relist = ProductLlifeDomain.queryProductLlifeVOList(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}*/
	
	/**
	 * @param model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询单个产品信息
	 */
	public ReturnMsg getProductLlifeVO(IProductLlifeVOModel model) {
		ReturnMsg msg = new ReturnMsg();
		IProductLlifeVOModel VOModel = ProductLlifeDomain.getProductLlifeVO(model);
		List<IProductLlifeVOModel> list = new ArrayList<IProductLlifeVOModel>();
		list.add(VOModel);
		msg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		return msg;
	}
	

	
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 修改出所有产品信息
	 */
	 public ReturnMsg modifyProductLlife(IProductLlifeVOModel model,IUserModel user) throws BusinessException{
	 ReturnMsg reutn = new ReturnMsg();
	 try{
		 ValidateHelper.IsNullOrEmptyThrowException(model.getProduct_name(), "产品名称不可为空，请检查。");//是否允许多被保人为空检验
		 ValidateHelper.IsNullOrEmptyThrowException(model.getInsuredFlag(), "是否允许多被保人不可为空，请检查。");//是否允许多被保人为空检验
		 ProductLlifeDomain.modifyProductLlife(model, user);
		 reutn.setSuccessMessage("修改成功");
	 }catch(BusinessException e){
			reutn.setFailMessage(e.getMessage(), true);
		}
	 return reutn;
	 }
 
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 修改产品评估信息
	 */
	 public ReturnMsg modifyProductEvaluation(IProductLlifeVOModel model,IUserModel user){
	 ReturnMsg reutn = new ReturnMsg();
	 try{
			ProductLlifeDomain.addProductEvaluation(model, user);
			
			reutn.setSuccessMessage("评估成功");
	 }catch(BusinessException e){
			reutn.setFailMessage(e.getMessage(), true);
		}
	 return reutn;
	 }
 	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 添加产品信息
	 */
	@Override
	public ReturnMsg addProductLlife(IProductLlifeVOModel model, IUserModel user) throws BusinessException{
		ReturnMsg reutn = new ReturnMsg();
		try{
				//为空校验开始
				ValidateHelper.IsNullOrEmptyThrowException(model.getInsBranch_id(), "保险公司不可为空，请检查。");//保险公司为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getProduct_id(), "险种代码不可为空，请检查。");//险种代码为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getProduct_name(), "产品名称不可为空，请检查。");//产品名称为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getProduct_type(), "产品分类（产品大类）不可为空，请检查。");//产品分类（产品大类）为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getProduct_type2(), "产品分类2不可为空，请检查。");//产品分类2为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getProduct_type3(), "产品分类3不可为空，请检查。");//产品分类3为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getIns_type(), "主附险标志不可为空，请检查。");//主附险标志检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getPeriod_type(), "保险期限类型不可为空，请检查。");//保险期限类型为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getInsuredFlag(), "是否允许多被保人不可为空，请检查。");//是否允许多被保人为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getCharge_type_codes(), "缴费方式不可为空，请检查。");//缴费方式为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getCharge_period_codes(), "缴费期限类型不可为空，请检查。");//缴费期限类型为空检验
				ValidateHelper.IsNullOrEmptyThrowException(model.getCoverage_period_codes(), "保障期限类型不可为空，请检查。");//保障期限类型为空检验
				//为空校验结束
				ProductLlifeDomain.addProductLlife(model,user);
				reutn.setSuccessMessage("保存成功");
		 }catch(BusinessException e){
			reutn.setFailMessage(e.getMessage(), true);
		 }
		List<IProductLlifeVOModel> list = new ArrayList<IProductLlifeVOModel>();
		list.add(model);
		reutn.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		 return reutn;
	}
	
	/** 
	* 
	* @param model
	* @param user
	* @return
	* @throws BusinessException 
	* @description:删除产品相关信息
	*/
	@Override
	public ReturnMsg deteleProductLlife(IProductLlifeModel model, IUserModel user) throws BusinessException{
		ReturnMsg reutn = new ReturnMsg();
		try{
			ProductLlifeDomain.deleteProductLlife(model,user);
			reutn.setSuccessMessage("删除成功");
		 }catch(BusinessException e){
			reutn.setFailMessage(e.getMessage(), true);
		 }
		 return reutn;
	}
	
	
	
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个String对象
	 * @description 用于前台输入产品代码，带出产品信息
	 */

	public String getProductLife4Pkg(IProductLlifeVOModel model){
		IProductLlifeVOModel plVO=ProductLlifeDomain.getProductLife4Pkg(model);
		String returnInfo="";
		if (plVO==null) {
			returnInfo="{isSuccess:"+false+"}";
		}else {
			returnInfo="{isSuccess:"+true+",product_name:"+plVO.getProduct_name()+",insBranch_id:"+plVO.getInsBranch_id()+",insBranch_name:"+plVO.getInsBranch_name()+",product_abbr:"+(plVO.getProduct_abbr()==null?"":plVO.getProduct_abbr())+",product_type1_name:"+plVO.getProduct_type1_name()+",product_type2_name:"+plVO.getProduct_type2_name()+",product_type3_name:"+plVO.getProduct_type3_name()+",ins_type_name:"+plVO.getIns_type_name()+",periodtype_name:"+plVO.getPeriodtype_name()+",ins_type_code:"+plVO.getIns_type()+"}";
		}
		return returnInfo;
	}
	/** 
	* productLlifeNameIsHave
	* @param model
	* @return 
	* @description:判断产品代码是否重复（同一家保险公司）
	*/
	@Override
	public String checkProductIdUnique(IProductLlifeVOModel model) {
		IProductLlifeVOModel plVO=ProductLlifeDomain.getProductLife4Pkg(model);
		String returnInfo="";
		if (plVO==null) {
			returnInfo="true";
		}else {
			returnInfo="false";
		}
		return returnInfo;
	}
	
	/** 
	* 
	* @param model
	* @return 
	* @description:判断产品名称是否重复（同一家保险公司）
	*/
	@Override
	public String checkProductNameUnique(IProductLlifeVOModel model) {
		String returnInfo="";
		returnInfo = returnInfo+ProductLlifeDomain.productLlifeNameIsHave(model);
		return returnInfo;
	}
}
