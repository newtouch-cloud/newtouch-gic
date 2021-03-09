package com.ca.cacore.ibs.webapp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.ICommonAsynRequestDomain;
import com.ca.cacore.ibs.domain.IPolicyLifeDomain;
import com.ca.cacore.ibs.domain.IPolicyLifeManageDomain;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeHolderModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductFeePremVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeProductFeePremVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeVOModel;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.csm.dao.customer.ICustomerDao;
import com.ca.cacore.csm.domain.CustomerAndCustomerContact.ICustomerDomain;
import com.ca.cacore.csm.model.bo.CustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.stringutil.StringUtil;
@Service
public class PolicyLifeService implements IPolicyLifeService{
	@Autowired private IPolicyLifeDomain policyLifeDomain;
	@Autowired private IPolicyLifeManageDomain policyLifeManageDomain;
	@Autowired private ICommonAsynRequestDomain commonAsynRequestDomain;
	@Autowired private ICommonSeqDao seqDao;
	@Autowired private ICustomerDomain customerDomain;
	@Autowired private IPolicyImageService  policyImageService ;
	@Autowired private ICustomerDao customerDao ;
	
	/**
	 * 增加客户基本信息保存方法
	 */
	
	@Override
	public String  addCustomerAndContact(IPolicyLifePeopleVOModel CustomerModel, IUserModel user) {
	 	    String branch_id = commonAsynRequestDomain.getBranchID();
	 	    Integer seq_id = policyLifeDomain.getSeq_id();
	 	    CustomerModel.setSeq_id(seq_id);//设置客户的seq_id
	 	    CustomerModel.setLog_seq_id(seq_id);//设置备份数据主键
	 	    CustomerModel.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//？？？
	 	    CustomerModel.setLog_remark("录入投保单或保单的时候添加客户信息");//备份备注
		    String customer_id = this.createId(branch_id, CodeTypeConst.POLICYLIFE_CUSTOMER_IDENTIFIER);
		    CustomerModel.setCustomer_id(customer_id);
		    //添加客户基本信息
			policyLifeDomain.addCustome(CustomerModel,user);
			//添加客户联系信息
			policyLifeDomain.addCustomerContactModel(CustomerModel,user);
			//添加客户基本信息日志
			policyLifeDomain.addCustomerLog(CustomerModel,user);
 		    return customer_id;
	}


	@Override
	public String modifyCustomerAndContact(IPolicyLifePeopleVOModel cus,IUserModel user) {
		Integer seq_id = policyLifeDomain.getCustomeSeqId(cus.getCustomer_id());//得到客户表的seq_id
		boolean boo = checkContactInfo(cus);//校验联系信息是否发生变化
		cus.setLog_seq_id(seq_id);//备份数据主键
		cus.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//备份业务类型？??
	    cus.setLog_remark("录入投保单或保单的时候添加客户信息");//备份备注
		policyLifeManageDomain.modifyCustomer(cus,user);
		if(!boo){//false时联系信息发生变化
			//添加客户联系信息
			policyLifeDomain.addCustomerContactModel(cus,user);
		}
		//添加客户基本信息日志
		policyLifeDomain.addCustomerLog(cus, user);
		return cus.getCustomer_id();
	}
	
	@Override
	public ReturnMsg saveAllPolicyLife(PolicyLifeInfoVOModel pl, IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		  try{
			  //校验
			  this.validateData(pl);
			  pl.setSeq_id(policyLifeDomain.getSeq_id());
			  pl.setStatus(CodeTypeConst.POLICYLIFE_STATUS_WAITCHECK);//投保单状态:6待核保
			  //添加投保人
			  PolicyLifeHolderModel holder = pl.getHolderModel();
			  holder.setPolicy_id(pl.getPolicy_id());
			  policyLifeDomain.addPolicyLifeHolder(holder,user);
			  //循环添加被保人
			  for(int i =0 ;i < pl.getInsurantList().size() ; i++){
				  PolicyLifeInsuredModel model = pl.getInsurantList().get(i);
				  model.setPolicy_id(pl.getPolicy_id());
				  policyLifeDomain.addPolicyLifeInsured(model,user);
			  }
			  //循环添加受益人
			  for(int i =0 ;i < pl.getBeneficiaryList().size() ; i++){
				  PolicyLifeBeneficiaryModel beneficiaryMode = pl.getBeneficiaryList().get(i);
				  beneficiaryMode.setPolicy_id(pl.getPolicy_id());
				  policyLifeDomain.addPolicyLifeBeneficiary(beneficiaryMode, user);
			  }
			  //添加险种、费用明细
			  for(int i =0 ;i < pl.getProductList().size() ; i++){
				  IPolicyLifeProductFeePremVOModel model = pl.getProductList().get(i);
				  model.setPolicy_id(pl.getPolicy_id());
				  policyLifeDomain.addPolicyLifeProduct(model, user);
				  policyLifeDomain.addPolicyLifeProductFee(model, user);
				  //添加日志表
				  model.setLog_seq_id(model.getSeq_id());
				  model.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
				  model.setLog_remark("投保单的产品添加日志");
				  policyLifeDomain.addPolicyLifeProductLog(model, user);
				  model.setLog_remark("投保单的产品费用明细添加日志");
				  policyLifeDomain.addPolicyLifeProductFeeLog(model, user);
			  }
			  //添加影像信息
			  if(  pl.getPolicyImageModel().getFile_ids()!=null&&pl.getPolicyImageModel().getFile_ids().length!=0){
				  IPolicyImageModel policyImageModel  = pl.getPolicyImageModel();
				  policyImageModel.setPolicy_id(pl.getPolicy_id());//保单id
				  policyImageService.concernPolicyImage(policyImageModel, user);
				  //设置影像上传时间
				  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				  String sysDate = sf.format(System.currentTimeMillis());
				  pl.setScan_time(DateUtil.string2Date(sysDate));
			  }
			  //添加投保单信息
			 String inner_send_code = "";
			 inner_send_code = pl.getInsBranch_id()+pl.getSend_code()+seqDao.queryCommonSeq("seq_id");
			 pl.setInner_send_code(inner_send_code);//内部投保单号码
			  policyLifeDomain.addPolicyLife(pl, user);
			 //添加投保单日志表
			  pl.setLog_seq_id(pl.getSeq_id());
			  pl.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
			  pl.setLog_remark("投保单添加日志");
			  policyLifeDomain.addPolicyLifeLog(pl, user);
		  }catch(BusinessException e){
				returnMsg.setFailMessage(e.getMessage(), true);
		  }
		return returnMsg;
	}

	@Override
	public ReturnMsg modifyAllPolicyLife(PolicyLifeInfoVOModel pl, IUserModel user) throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();
		  try{
			  //校验
			  this.validateData(pl);
			  //修改投保人
			  PolicyLifeHolderModel holder = pl.getHolderModel();
			  holder.setPolicy_id(pl.getPolicy_id());
			  policyLifeDomain.modifyPolicyLifeHolder(holder,user);
			  //循环修改被保人
			  for(int i =0 ;i < pl.getInsurantList().size() ; i++){
				  PolicyLifeInsuredModel model = pl.getInsurantList().get(i);
				  model.setPolicy_id(pl.getPolicy_id());
				  policyLifeDomain.modifyPolicyLifeInsured(model,user);
			  }
			  //循环修改受益人
			  for(int i =0 ;i < pl.getBeneficiaryList().size() ; i++){
				  PolicyLifeBeneficiaryModel beneficiaryMode = pl.getBeneficiaryList().get(i);
				  beneficiaryMode.setPolicy_id(pl.getPolicy_id());
				  policyLifeDomain.modifyPolicyLifeBeneficiary(beneficiaryMode, user);
			  }
			  //修改险种相关信息
			  for(int i =0 ;i < pl.getProductList().size() ; i++){
				  IPolicyLifeProductFeePremVOModel model = pl.getProductList().get(i);
				  model.setPolicy_id(pl.getPolicy_id());
				  //policyLifeDomain.modifyPolicyLifePrem(model, user);
				  policyLifeDomain.modifyPolicyLifeProduct(model, user);
				  policyLifeDomain.modifyPolicyLifeProductFee(model, user);
				  //添加日志表
				  model.setLog_seq_id(model.getSeq_id());
				  model.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
				  model.setLog_remark("投保单的产品添加日志");
				  policyLifeDomain.addPolicyLifeProductLog(model, user);
				  model.setLog_remark("投保单的产品费用添加日志");
				 // policyLifeDomain.addPolicyLifeProductPremLog(model, user);
				  model.setLog_remark("投保单的产品费用明细添加日志");
				  policyLifeDomain.addPolicyLifeProductFeeLog(model, user);
			  }
			  //修改总投保单
			  policyLifeDomain.modifyPolicyLife(pl, user);
			 //添加投保单日志表
			  pl.setLog_seq_id(pl.getSeq_id());
			  pl.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
			  pl.setLog_remark("投保单添加日志");
			  policyLifeDomain.addPolicyLifeLog(pl, user);
			  //回显信息
			   returnMsg.setSuccessMessage("修改成功");
		  }catch(BusinessException e){
				returnMsg.setFailMessage(e.getMessage(), true);
		  }
		List<PolicyLifeInfoVOModel> list = new ArrayList<PolicyLifeInfoVOModel>();
		list.add(pl);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		return returnMsg; 
	}
	
	public PolicyLifeInfoVOModel coreCheck(PolicyLifeInfoVOModel pl, IUserModel user){
		     //核心接口返回
			 String result_flag="true";
			 if("true".equals(result_flag)){//返回录入界面，更新投保单状态为首期带承保
				 PolicyLifeVOModel m = new PolicyLifeVOModel();
				 m.setStatus(CodeTypeConst.POLICYLIFE_STATUS_FIRSTCOVERED);//投保单状态:14   首期待承保
				 m.setInsBranch_id(pl.getInsBranch_id());
				 m.setSend_code(pl.getSend_code());
				 policyLifeDomain.modifyPolicyLifeStatus(m, user);
			 }else{//失败 修改页面回显
				 pl.setLog("核心返回失败");
				 result_flag = "false";
			 }
			 pl.setResult_flag(result_flag);
		    return pl;
	}
	@Override
	public ReturnMsg getCustomerView(ICustomerVOModel m) {
		ReturnMsg returnMsg = new ReturnMsg();
		ICustomerVOModel model = customerDomain.getCustomerNewInfo(m);
		model.setPolicy_tr_id(m.getPolicy_tr_id());//保留tr的值
		model.setFlag(m.getFlag());//操作标识 有值代表更新
		model.setRelation(m.getRelation());
		model.setRelation1(m.getRelation1());
		model.setRelation2(m.getRelation2());
		model.setBene_order(m.getBene_order());
		model.setBene_rate(m.getBene_rate());
		model.setBene_type(m.getBene_type());
		model.setInsurant_name(m.getInsurant_name());
		model.setInsurant_name_arr(m.getInsurant_name_arr());
		model.setInsurant_name_value(m.getInsurant_name_value());
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}
	
	public String createId(String branch_id,String series_code){
		String seq_code=seqDao.queryCommonSeq("seq_id");
		try {
			//调用现成的方法对取出的识别码进行10位补0
			seq_code=StringUtil.alignLeft(seq_code, 10);
		} catch (Message e) {
			e.printStackTrace();
		}
		return branch_id.substring(0, 3)+series_code+seq_code;
	}


	@Override
	public Integer getSeq_id() {
		return policyLifeDomain.getSeq_id();
	}
	
	/** 
	* 获得产品可选缴费方式的信息  ss
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getChargeType(IPolicyLifeProductFeePremVOModel model){
		return policyLifeDomain.getChargeType(model);
	}
	/** 
	* 获得产品可选缴费期限类型的信息  ss
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getCharge_Period(IPolicyLifeProductFeePremVOModel model){
		return policyLifeDomain.getCharge_Period(model);
	}
	/** 
	* 获得产品可选保障期限类型的信息 ss
	* @param model
	* @return List<String>
	* @description:
	*/
	public List<String> getCoveragePeriod(IPolicyLifeProductFeePremVOModel model){
		return policyLifeDomain.getCoveragePeriod(model);
	}
	
	public void  validateData(PolicyLifeInfoVOModel pl){
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
		//校验投保人
		if(pl.getHolderModel()==null){
			ValidateHelper.IsNullOrEmptyThrowException(null, "投保人不能为空");
		}else if(pl.getHolderModel()!=null){
			PolicyLifeHolderModel holderModel = pl.getHolderModel();
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
			List<PolicyLifeInsuredModel>insurantList = pl.getInsurantList();
			for(PolicyLifeInsuredModel insuredModel:insurantList ){
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
			List<PolicyLifeBeneficiaryModel>beneficiaryList = pl.getBeneficiaryList();
			for(PolicyLifeBeneficiaryModel beneficiaryModel:beneficiaryList ){
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
		if(pl.getProductList().size()==0){
			ValidateHelper.IsNullOrEmptyThrowException(null, "险种不能为空");
		}else if(pl.getProductList().size()==0){
			List<IPolicyLifeProductFeePremVOModel>productList = pl.getProductList();
			for(IPolicyLifeProductFeePremVOModel productModel:productList){
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
	public ReturnMsg addPolicylifePrem(PolicyLifeInfoVOModel pl, IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			//添加产品费用信息
			  List<IPolicyLifeProductFeePremVOModel> list = policyLifeDomain.sumPolicylifePrem(pl.getPolicy_id());
			  for(IPolicyLifeProductFeePremVOModel model:list){
				  IPolicyLifeProductFeePremVOModel model2 = new PolicyLifeProductFeePremVOModel();
				  Integer seq_id = policyLifeDomain.getSeq_id();
				  model2.setSeq_id(seq_id);
				  model2.setPeriod_prem(model.getPeriod_prem());
				  model2.setActual_prem(model.getPeriod_prem());
				  model2.setBranch_id(pl.getBranch_id());// 销售机构 
				  model2.setInsBranch_id(pl.getInsBranch_id());
				  model2.setPolicy_id(pl.getPolicy_id());
				  model2.setSend_code(pl.getSend_code());//投保单号 
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
				  policyLifeDomain.addPolicyLifePrem(model2, user);
				  model2.setLog_remark("投保单的产品费用添加日志");
				  model2.setLog_seq_id(seq_id);//备份数据主键
				  model2.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
				  policyLifeDomain.addPolicyLifeProductPremLog(model2, user);
			  }
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}


	@Override
	public ReturnMsg modifyPolicylifePrem(PolicyLifeInfoVOModel pl,IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			 //修改产品费用信息
			  List<IPolicyLifeProductFeePremVOModel> list = policyLifeDomain.sumPolicylifePrem(pl.getPolicy_id());
			  for(IPolicyLifeProductFeePremVOModel model:list){
				  IPolicyLifeProductFeePremVOModel model2 = new PolicyLifeProductFeePremVOModel();
				  Integer seq_id = policyLifeDomain.getSeq_id();
				  model2.setSeq_id(seq_id);
				  model2.setPeriod_prem(model.getPeriod_prem());
				  model2.setActual_prem(model.getPeriod_prem());
				  model2.setBranch_id(pl.getBranch_id());// 销售机构 
				  model2.setInsBranch_id(pl.getInsBranch_id());
				  model2.setPolicy_id(pl.getPolicy_id());
				  model2.setSend_code(pl.getSend_code());//投保单号 
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
				  policyLifeDomain.modifyPolicyLifePrem(model2, user);
				  model2.setLog_remark("投保单的产品费用添加日志");
				  model2.setLog_seq_id(seq_id);//备份数据主键
				  model2.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//???
				  policyLifeDomain.addPolicyLifeProductPremLog(model2, user);
			     } 
			  }catch(BusinessException e){
					returnMsg.setFailMessage(e.getMessage(), true);
				}
		return returnMsg;
	}
	
	/**
	 * 判断客户联系信息是否重复
	* @param model
	* @param conModel
	* @return boolean
	* @description:
	 */
	private boolean checkContactInfo(IPolicyLifePeopleVOModel model){
		boolean boo = true;
		ICustomerContactModel customerContact = new CustomerContactModel(); 
		//根据客户代码查询联系信息
		customerContact.setCustomer_id(model.getCustomer_id());
		ICustomerContactModel contactDB = customerDao.getNewestCustomerContact(customerContact);//数据库的联系信息数据
		//数据库中的联系信息与接收页面的信息比较
		if(!model.getAddress().equals(contactDB.getAddress())){//住址
			//接受页面的值不为“”时
			if(!"".equals(model.getAddress())){
				boo =  false;
			}
		}
		if(!model.getZip().equals(contactDB.getZip())){//邮编
			if(!"".equals(model.getZip())){
				boo =  false;
			}
		}
		if(!model.getMobile().equals(contactDB.getMobile())){//联系电话
			if(!"".equals(model.getMobile())){
				boo =  false;
			}
		}
		if(!model.getFax().equals(contactDB.getFax())){//传真
			if(!"".equals(model.getFax())){
				boo =  false;
			}
		}
		if(!model.getTelphone().equals(contactDB.getTelphone())){//移动电话
			if(!"".equals(model.getTelphone())){
				boo =  false;
			}
		}
		if(!model.getEmail().equals(contactDB.getEmail())){//邮箱
			if(!"".equals(model.getEmail())){
				boo =  false;
			}
		}
		if(!model.getJob_com().equals(contactDB.getJob_com())){//工作单位
			if(!"".equals(model.getJob_com())){
				boo =  false;
			}
		}
		if(!model.getJob_tel().equals(contactDB.getJob_tel())){//工作电话
			if(!"".equals(model.getJob_tel())){
				boo = false;
			}
		}
		return boo;
	}
		
}
