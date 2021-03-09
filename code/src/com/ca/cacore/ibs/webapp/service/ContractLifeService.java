package com.ca.cacore.ibs.webapp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.ICommonAsynRequestDomain;
import com.ca.cacore.ibs.domain.IContractLifeDomain;
import com.ca.cacore.ibs.domain.IPolicyLifeManageDomain;
import com.ca.cacore.ibs.model.bo.ContractLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.ContractLifeHolderModel;
import com.ca.cacore.ibs.model.bo.ContractLifeInsurantModel;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.vo.ContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.ContractLifeSaveVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeVOModel;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;

@Service
public class ContractLifeService implements IContractLifeService{
	@Autowired private IContractLifeDomain contractLifeDomain;
	@Autowired private ICommonAsynRequestDomain commonAsynRequestDomain;
	@Autowired private IPolicyImageService  policyImageService ;
	@Autowired private IPolicyLifeManageDomain policyLifeManageDomain;
	@Autowired private ICommonSeqDao seqDao;
	
	@Override
	public ReturnMsg saveAllContractLife(ContractLifeSaveVOModel cl,IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		  try{
			     //校验
			    this.validateData(cl);
			     cl.setSeq_id(contractLifeDomain.getSeq_id());//添加的时候得到保单的seq_id
			     cl.setStatus(CodeTypeConst.CONTRACTLIFE_STATUS_WAITACK);//保单状态: 已承保，待客户回执
			     //添加投保人
			     ContractLifeHolderModel holder = cl.getHolderModel();
			     holder.setPolicy_id(cl.getPolicy_id());
			     contractLifeDomain.addContractLifeHolder(cl.getHolderModel(), user);
	            //添加被保人
				 for(int i =0 ;i < cl.getInsurantList().size() ; i++){
					 ContractLifeInsurantModel model = cl.getInsurantList().get(i);
					 model.setPolicy_id(cl.getPolicy_id());
				     contractLifeDomain.addContractLifeInsured(model, user);
				  }
				 //添加受益人
				  for(int i =0 ;i < cl.getBeneficiaryList().size() ; i++){
					  ContractLifeBeneficiaryModel beneficiaryMode = cl.getBeneficiaryList().get(i);
					  beneficiaryMode.setPolicy_id(cl.getPolicy_id());
					 contractLifeDomain.addContractLifeBeneficiary(beneficiaryMode, user);
				  }
				  //添加险种相关信息
				  for(int i =0 ;i < cl.getProductListView().size() ; i++){
					  ContractLifeProductVOModel model = cl.getProductListView().get(i);
					  model.setPolicy_id(cl.getPolicy_id());
					//添加投保单费用分险种明细信息
				    contractLifeDomain.addContractLifeProductFee(model, user);
					 //添加产品
					 contractLifeDomain.addContractLifeProduct(model, user);
					 //寿险保单手续费分险种明细信息
					 contractLifeDomain.addContractLifeProductInsFee(model, user);
					  //添加日志表
					  model.setLog_seq_id(model.getSeq_id());
					  model.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
					  model.setLog_remark("投保单的产品添加日志");
					  contractLifeDomain.addContractLifeProductLog(model, user);
					  model.setLog_remark("投保单的产品费用明细添加日志");
					  contractLifeDomain.addContractLifeProductFeeLog(model, user);
					  model.setLog_remark("投保单的产品手续费用明细添加日志");
					  contractLifeDomain.addContractLifeProductInsFeeLog(model, user);
				  }
				  //添加影像信息
				  IPolicyImageModel policyImageModel  = cl.getPolicyImageModel();
				  policyImageModel.setPolicy_id(cl.getPolicy_id());//保单id
				  //影像信息
				  if( cl.getPolicyImageModel().getFile_ids()!=null&&cl.getPolicyImageModel().getFile_ids().length!=0){
					  policyImageService.concernPolicyImage(policyImageModel, user);
					  //设置影像上传时间
					  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					  String sysDate = sf.format(System.currentTimeMillis());
					  cl.setScan_time(DateUtil.string2Date(sysDate));
				  }
			     //添加总的保单
				  String inner_policy_code = "";
				  inner_policy_code = cl.getInsBranch_id()+cl.getSend_code()+seqDao.queryCommonSeq("seq_id");
				  cl.setInner_policy_code(inner_policy_code);//内部投保单号码
			     contractLifeDomain.addContractLife(cl, user);
			    //添加保单日志表
				 cl.setLog_seq_id(cl.getSeq_id());
				 cl.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//
				 cl.setLog_remark("保单添加日志");
				 contractLifeDomain.addContractLifeLog(cl, user);
			     //保单添加成功后修改投保单的状态 
			     IPolicyLifeVOModel plf = new PolicyLifeVOModel();
			     plf.setSend_code(cl.getSend_code());
			     plf.setInsBranch_id(cl.getInsBranch_id());
			     plf.setStatus(CodeTypeConst.POLICYLIFE_STATUS_CONTRACTLIFE_EFFECTIVE);//保单进入正常有效状态
			     contractLifeDomain.modifyPolicyLifeStatus(plf, user);
			returnMsg.setSuccessMessage("保存成功!是否上传影像文件?");
		  }catch(BusinessException e){
				returnMsg.setFailMessage(e.getMessage(), true);
		  }
		return returnMsg;
	}
	
	@Override
	public ReturnMsg modifyAllContractLife(ContractLifeSaveVOModel cl,IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		String result_flag="";
		  try{
			     //校验
			    this.validateData(cl);
			     //修改投保人
			     ContractLifeHolderModel holder = cl.getHolderModel();
			     holder.setPolicy_id(cl.getPolicy_id());
			     contractLifeDomain.modifycontractLifeHolder(holder, user);
	            //修改被保人
				 for(int i =0 ;i < cl.getInsurantList().size() ; i++){
					 ContractLifeInsurantModel model = cl.getInsurantList().get(i);
					 model.setPolicy_id(cl.getPolicy_id());
					  contractLifeDomain.modifyContractLifeInsured(model, user);
				  }
				 //修改受益人
				  for(int i =0 ;i < cl.getBeneficiaryList().size() ; i++){
					  ContractLifeBeneficiaryModel beneficiaryMode = cl.getBeneficiaryList().get(i);
					  beneficiaryMode.setPolicy_id(cl.getPolicy_id());
					  contractLifeDomain.modifyContractLifeBeneficiary(beneficiaryMode, user);
				  }
				  //修改险种
				  for(int i =0 ;i < cl.getProductListView().size() ; i++){
					  ContractLifeProductVOModel model = cl.getProductListView().get(i);
					  model.setPolicy_id(cl.getPolicy_id());
					 //修改产品
					  contractLifeDomain.modifyContractLifeProduct(model, user);
					//修改投保单费用分险种明细信息
					  contractLifeDomain.modifyContractLifeProductFee(model, user);
					  //更新 寿险保单手续费分险种明细信息
					  contractLifeDomain.modifyContractLifeProductInsFee(model, user);
					  //添加日志表
					  model.setLog_seq_id(model.getSeq_id());
					  model.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
					  model.setLog_remark("投保单的产品添加日志");
					  contractLifeDomain.addContractLifeProductLog(model, user);
					  model.setLog_remark("投保单的产品费用明细添加日志");
					  contractLifeDomain.addContractLifeProductFeeLog(model, user);
					  model.setLog_remark("投保单的产品手续费用明细添加日志");
					  contractLifeDomain.addContractLifeProductInsFeeLog(model, user);
				  }
			     //修改总的保单
			     contractLifeDomain.modifyContractLife(cl, user);
			     //添加保单日志表
				 cl.setLog_seq_id(cl.getSeq_id());
				 cl.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
				 cl.setLog_remark("保单添加日志");
				 contractLifeDomain.addContractLifeLog(cl, user);
			    //核心接口返回
			  result_flag = "false"; //失败
			  cl.setResult_flag(result_flag);
				//回显信息
			returnMsg.setSuccessMessage("修改成功");
		  }catch(BusinessException e){
				returnMsg.setFailMessage(e.getMessage(), true);
		  }
		List<ContractLifeSaveVOModel> list = new ArrayList<ContractLifeSaveVOModel>();
		list.add(cl);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		return returnMsg;
	}

	@Override
	public Integer getSeq_id() {
		return contractLifeDomain.getSeq_id();
	}
	
	public void  validateData(ContractLifeSaveVOModel pl){
		//投保单的非空校验
		ValidateHelper.IsNullOrEmptyThrowException(pl.getBranch_id(), "销售机构不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getInsBranch_id(), "保险公司机构不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getSend_code(), "投保单不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getPolicy_code(), "保单号不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getAgent_id(), "保单销售人员不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getService_id(), "保单服务人员不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getHolder_id(), "投保人不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getInsurant_id(), "第一被保人不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getRelation_id(), "投保人与主被保人关系不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getPay_mode(), "当期付款方式不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getPeriod_prem(), "保费合计不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getMoney_id(), "币种不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getBank_code(), "开户银行不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getAccount_type(), "银行账号类型不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getBank_accName(), "银行开户人不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getBank_account(), "银行账号不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getAgent_id(), "保费逾期未付不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getService_id(), "投保日期不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getHolder_id(), "是否高额件不能为空");
		ValidateHelper.IsNullOrEmptyThrowException(pl.getValidate_date(), "保单生效日期不能为空");
		//校验投保人
		if(pl.getHolderModel()==null){
			ValidateHelper.IsNullOrEmptyThrowException(null, "投保人不能为空");
		}else if(pl.getHolderModel()!=null){
			ContractLifeHolderModel holderModel = pl.getHolderModel();
			ValidateHelper.IsNullOrEmptyThrowException(holderModel.getCustomer_id(), "投保人客户id不能为空");
			ValidateHelper.IsNullOrEmptyThrowException(holderModel.getApp_age(), "投保人的年龄不能为空");
			ValidateHelper.IsNullOrEmptyThrowException(holderModel.getApp_certi_code(), "投保人的证件类型不能为空");
			ValidateHelper.IsNullOrEmptyThrowException(holderModel.getApp_certi_no(), "投保人的证件号码能为空");
			ValidateHelper.IsNullOrEmptyThrowException(holderModel.getApp_name(), "投保人的名字不能为空");
		}
		//校验被保人
		if(pl.getInsurantList().size()==0){
			ValidateHelper.IsNullOrEmptyThrowException(null, "被保人不能为空");
		}else if(pl.getInsurantList().size()!=0){
			List<ContractLifeInsurantModel>insurantList = pl.getInsurantList();
			for(ContractLifeInsurantModel insuredModel:insurantList ){
				ValidateHelper.IsNullOrEmptyThrowException(insuredModel.getCustomer_id(), "被保人客户id不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(insuredModel.getRelation1(), "被保人与投保人关系不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(insuredModel.getRelation2(), "被保人与被保人关系不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(insuredModel.getInsurant_age(), "被保人的年龄不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(insuredModel.getInsurant_certi_code(), "被保人的证件类型不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(insuredModel.getInsurant_certi_no(), "被保人的证件号码能为空");
				ValidateHelper.IsNullOrEmptyThrowException(insuredModel.getInsurant_name(), "被保人的名字不能为空");
			}
		}
		//校验受益人
		if(pl.getBeneficiaryList().size()==0){
			ValidateHelper.IsNullOrEmptyThrowException(null, "受益人不能为空");
		}else if(pl.getBeneficiaryList().size()!=0){
			List<ContractLifeBeneficiaryModel>beneficiaryList = pl.getBeneficiaryList();
			for(ContractLifeBeneficiaryModel beneficiaryModel:beneficiaryList ){
				ValidateHelper.IsNullOrEmptyThrowException(beneficiaryModel.getCustomer_id(), "受益人客户id不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(beneficiaryModel.getBene_age(), "受益人的年龄不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(beneficiaryModel.getBene_certi_code(), "受益人的证件类型不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(beneficiaryModel.getBene_certi_no(), "受益人的证件号码不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(beneficiaryModel.getInsurant_id(), "受益人对应的被保人不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(beneficiaryModel.getRelation1(), "受益人与投保人关系不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(beneficiaryModel.getRelation2(), "受益人与被保人关系不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(beneficiaryModel.getBene_order(), "受益比例不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(beneficiaryModel.getBene_rate(), "受益顺序不能为空");
			}
		}
		//校验险种
		if(pl.getProductListView().size()==0){
			ValidateHelper.IsNullOrEmptyThrowException(null, "险种不能为空");
		}else if(pl.getProductListView().size()==0){
			List<ContractLifeProductVOModel>productList = pl.getProductListView();
			for(ContractLifeProductVOModel productModel:productList){
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getProduct_id(), "产品代码不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getInsurant_id(), "产品对应的被保人不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getCharge_type(), "当期缴费方式不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getCharge_next(), "下期缴费方式不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getCoverage_period(), "保障期限类型不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getCoverage_year(), "保障年数不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getCharge_year(), "缴费年限不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getCharge_period(), "缴费期限类型不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getAmount(), "保额不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getPeriod_prem(), "保费不能为空");
				ValidateHelper.IsNullOrEmptyThrowException(productModel.getIs_autoRen(), "是否自动续保不能为空");
			}
		}
	}
	
	@Override
	public ReturnMsg addContractlifePrem(ContractLifeSaveVOModel pl, IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			//添加产品费用信息
			  List<ContractLifeProductVOModel> list = contractLifeDomain.sumContractlifePrem(pl.getPolicy_id());
			  for(ContractLifeProductVOModel model:list){
				  ContractLifeProductVOModel model2 = new ContractLifeProductVOModel();
				  Integer seq_id = contractLifeDomain.getSeq_id();
				  model2.setSeq_id(seq_id);
				  model2.setPeriod_prem(model.getPeriod_prem());
				  model2.setActual_prem(model.getPeriod_prem());
				  model2.setBranch_id(pl.getBranch_id());// 销售机构 
				  model2.setInsBranch_id(pl.getInsBranch_id());
				  model2.setPolicy_id(pl.getPolicy_id());
				  model2.setSend_code(pl.getSend_code());//投保单号 
				  model2.setPolicy_code(pl.getPolicy_code());
				  model2.setAgent_id(pl.getAgent_id());//保单销售人员 
				  model2.setService_id(pl.getService_id());//保单服务人员
				  model2.setCustomer_id(pl.getHolder_id());
				  model2.setInsurant_id(model.getInsurant_id());
				  model2.setPolicy_period(CodeTypeConst.POLICYLIFE_PERIOD);//缴费期次
				  model2.setPolicy_year(CodeTypeConst.POLICYLIFE_YEAR);//保单年度 
				  model2.setFee_type(CodeTypeConst.FEE_TYPE_FIRST);//费用业务类型 :41-首期保费收入
				  model2.setPay_mode(pl.getPay_mode());//当期付款方式  默认为投保单的付款方式 
				  model2.setBank_code(pl.getBank_code());// 开户银行 
				  model2.setBank_account(pl.getBank_account());// 银行账号
				  model2.setMoney_id(pl.getMoney_id());//币种 
				  model2.setDue_time(pl.getHold_date());
				  model2.setFee_status(CodeTypeConst.FEE_STATUS_WAIT);//费用处理状态 0：待处理 
				  contractLifeDomain.addContractLifeProductPre(model2, user);
				  model2.setLog_remark("投保单的产品费用添加日志");
				  model2.setLog_seq_id(seq_id);//备份数据主键
				  model2.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
				  contractLifeDomain.addContractLifeProductPreLog(model2, user);
			  }
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}


	@Override
	public ReturnMsg modifyContractlifePrem(ContractLifeSaveVOModel pl,IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			 //修改产品费用信息
			  List<ContractLifeProductVOModel> list = contractLifeDomain.sumContractlifePrem(pl.getPolicy_id());
			  for(ContractLifeProductVOModel model:list){
				  ContractLifeProductVOModel model2 = new ContractLifeProductVOModel();
				  Integer seq_id = contractLifeDomain.getSeq_id();
				  model2.setSeq_id(seq_id);
				  model2.setPeriod_prem(model.getPeriod_prem());
				  model2.setActual_prem(model.getPeriod_prem());
				  model2.setBranch_id(pl.getBranch_id());// 销售机构 
				  model2.setInsBranch_id(pl.getInsBranch_id());
				  model2.setPolicy_id(pl.getPolicy_id());
				  model2.setSend_code(pl.getSend_code());//投保单号 
				  model2.setPolicy_code(pl.getPolicy_code());
				  model2.setAgent_id(pl.getAgent_id());//保单销售人员 
				  model2.setService_id(pl.getService_id());//保单服务人员
				  model2.setCustomer_id(pl.getHolder_id());
				  model2.setInsurant_id(model.getInsurant_id());
				  model2.setPolicy_period(CodeTypeConst.POLICYLIFE_PERIOD);//缴费期次
				  model2.setPolicy_year(CodeTypeConst.POLICYLIFE_YEAR);//保单年度 
				  model2.setFee_type(CodeTypeConst.FEE_TYPE_FIRST);//费用业务类型 :41-首期保费收入
				  model2.setPay_mode(pl.getPay_mode());//当期付款方式  默认为投保单的付款方式 
				  model2.setBank_code(pl.getBank_code());// 开户银行 
				  model2.setBank_account(pl.getBank_account());// 银行账号
				  model2.setMoney_id(pl.getMoney_id());//币种 
				  model2.setDue_time(pl.getHold_date());
				  model2.setFee_status(CodeTypeConst.FEE_STATUS_WAIT);//费用处理状态 0：待处理 
				  contractLifeDomain.modifyContractLifePrem(model2, user);
				  model2.setLog_remark("投保单的产品费用添加日志");
				  model2.setLog_seq_id(seq_id);//备份数据主键
				  model2.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
				  contractLifeDomain.addContractLifeProductPreLog(model2, user);
			     } 
			  }catch(BusinessException e){
					returnMsg.setFailMessage(e.getMessage(), true);
				}
		return returnMsg;
	}

	@Override
	public IPolicyLifeVOModel getPolicyLifeInfoBySendCode(PolicyLifeVOModel model) {
		IPolicyLifeVOModel returnModel = new PolicyLifeVOModel();
		//验证投保单是否存在
		IPolicyLifeVOModel dbModel = commonAsynRequestDomain.validateSendCode(model);
		if(dbModel==null){
			returnModel.setResult_flag("false1");
		}else if(CodeTypeConst.POLICYLIFE_STATUS_FIRSTCOVERED.equals(dbModel.getStatus())){
			//首期带承保的投保单带出信息：根据seq_id带值
			IPolicyLifeVOModel pl = new PolicyLifeVOModel();
			pl.setSeq_id(dbModel.getSeq_id());//投保单的seq_id
			returnModel = policyLifeManageDomain.getPolicyLifeView(pl);//得到投保单详细信息
			returnModel.setResult_flag("");
		}else{
			//状态不对
			returnModel.setResult_flag("false2");
			returnModel.setSend_code(model.getSend_code());
			returnModel.setInsBranch_id(model.getInsBranch_id());
		}
		return returnModel;
	}
}
