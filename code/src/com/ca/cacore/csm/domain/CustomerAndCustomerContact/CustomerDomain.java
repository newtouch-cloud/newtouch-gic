package com.ca.cacore.csm.domain.CustomerAndCustomerContact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.branch.IBranchDao;
import com.ca.cacore.manage.model.bo.BranchModel;
import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.csm.dao.customer.ICustomerDao;
import com.ca.cacore.csm.model.bo.CustomerContactModel;
import com.ca.cacore.csm.model.bo.CustomerJieChuModel;
import com.ca.cacore.csm.model.bo.CustomerLogModel;
import com.ca.cacore.csm.model.bo.CustomerModel;
import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerJieChuModel;
import com.ca.cacore.csm.model.bo.ICustomerLogModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.bo.IInfclaimsModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.ca.cacore.csm.model.vo.ILaspolicyModel;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.utils.stringutil.StringUtil;

/**
 * 
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户基本信息和联系人信息Domain
 */

@Service
public class CustomerDomain implements ICustomerDomain{

	@Autowired private ICustomerDao customerDao ;
	@Autowired private IBranchDao branchDao;
	@Autowired private ICommonSeqDao seqDao;  //添加客户id
	@Autowired private ICustomerDao jiechuDao;
	
	/**
	 * 
	* 
	* @param customer
	* @param customerContact
	* @param user
	* @return 
	* @description:添加客户信息与联系人信息
	 */
	public boolean addCustomerr(ICustomerModel customermodel,
			ICustomerContactModel contactmodel,ICustomerLogModel logmodel,
			IUserModel user,String customer_id,String[] contactpersonInfo) {
		//1.校验录入的机构代码是否正确
		BranchModel branchModel = new BranchModel();
		branchModel.setBranch_id(contactmodel.getBranch_id());
		List<IBranchModel> list = branchDao.queryByVerifyAll(branchModel);
		if(list.size()!=1){  
			throw new BusinessException("录入的机构代码错误，请重新录入 ");
		}
		//2.新增客户基本信息
		customermodel.setStatus(CodeTypeConst.CUSTOMER_STATUS_VALID);
		customermodel.setCreateuser(user.getEmp_id());
		customermodel.setModifyuser(user.getEmp_id());
		customermodel.setCustomer_id(customer_id);
		customerDao.addCustomer(customermodel);//增加客户基本信息
		Integer seqId = customerDao.getSeqId(customermodel);//获取客户基本信息表中的seq_id
		//3.根据客户类型不同进行不同的新增操作
		String customer_type=customermodel.getCustomer_type();//客户类型
		if("0".equals(customer_type)){//客户类型：0-个人，1-法人
			//个人时：判断证件号码和证件类型是否存在相同的
			/*boolean  identity = verifyIsExistIdentityAndType(customermodel);
			if(identity){
				throw new BusinessException("客户已存在，请重新录入");
			}*/
			contactmodel.setStatus(CodeTypeConst.CUSTOMER_STATUS_VALID);
			contactmodel.setCreateuser(user.getEmp_id());
			contactmodel.setModifyuser(user.getEmp_id());
			contactmodel.setCustomer_id(customer_id);
			contactmodel.setLog_seq_id(seqId);//备份数据主键
			contactmodel.setLog_bustype(CodeTypeConst.CUSTOMER_STATUS_VALID);//备份业务类型
			contactmodel.setLog_remark("添加客户联系信息时进行备注");//备份备注
			customerDao.addCustomerContanct(contactmodel);//添加客户联系信息
		}else if("1".equals(customer_type)){
			for(String str:contactpersonInfo){
				String[] ones=str.split("#");
				String corporation_contact_person=ones[0];//联系人
				String corporation_contact_mobile=ones[1];//联系人电话
				String corporation_contact_qq = "";
				String corporation_contact_wechat = "";
				if(ones.length>2){
					 corporation_contact_qq=ones[2];//联系人qq
					 corporation_contact_wechat=ones[3];//联系人微信
				}
				contactmodel.setCorporation_contact_person(corporation_contact_person);
				contactmodel.setCorporation_contact_mobile(corporation_contact_mobile);
				contactmodel.setCorporation_contact_qq(corporation_contact_qq);
				contactmodel.setCorporation_contact_wechat(corporation_contact_wechat);
				contactmodel.setStatus(CodeTypeConst.CUSTOMER_STATUS_VALID);
				contactmodel.setCreateuser(user.getEmp_id());
				contactmodel.setModifyuser(user.getEmp_id());
				contactmodel.setCustomer_id(customer_id);
				contactmodel.setLog_seq_id(seqId);//备份数据主键
				contactmodel.setLog_bustype(CodeTypeConst.CUSTOMER_STATUS_VALID);//备份业务类型
				contactmodel.setLog_remark("添加客户联系信息时进行备注");//备份备注
				customerDao.addCustomerContanct(contactmodel);//添加客户联系信息
			}
		}
		logmodel.setCustomer_id(customer_id);
		logmodel.setStatus(CodeTypeConst.CUSTOMER_STATUS_VALID);
		logmodel.setLog_bustype(CodeTypeConst.CUSTOMER_LOG_BUSTYPE);
		logmodel.setCreateuser(user.getEmp_id());
		logmodel.setModifyuser(user.getEmp_id());
		logmodel.setLog_seq_id(seqId);
		logmodel.setLog_remark("添加客户信息时进行备注");//日志表的备份备注
		customerDao.addCustomerLog(logmodel);
		return true;
	}

	/**
	 * 
	* 
	* @param model
	* @return 
	* @description:分页查询VO信息
	 */
	public List<ICustomerVOModel> getVOAll(
			ICustomerVOModel model) {
		return  customerDao.getVOAll(model);
	}

	/**
	 * 
	* 
	* @param model
	* @return 
	* @description:查看详细信息
	 */
	public CustomerViewVOModel getModifyView(ICustomerModel model) {
		return customerDao.getView(model);
	}


	/** 
	* 
	* @param model
	* @return 
	* @description:
	*/
	public CustomerViewVOModel queryCustomer (ICustomerModel model) {
		return  customerDao.queryCustomer(model).get(0);
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:分页查询客户联系信息
	*/
	public List<ICustomerContactModel> queryCustomerContact(
			ICustomerModel model) {
		return customerDao.queryCustomerContact(model);
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:严格查询客户基本信息
	*/
	public ICustomerModel queryRigid(ICustomerModel model) {
		return customerDao.queryRigid(model);
	}

	/**
	 * 
	* 
	* @param model
	* @param user
	* @return 
	* @description:更新客户信息和客户联系人信息功能
	 */
	public boolean modifyCustomerAndCustomerContact(CustomerViewVOModel model, IUserModel user,String[] contactpersonInfo) {
		ICustomerModel cus = new CustomerModel();
		cus.setSeq_id(model.getCustomer_seq_id());
		cus.setCerti_type(model.getCerti_type());
		cus.setCerti_no(model.getCerti_no());
		
		ICustomerModel customer = new CustomerModel(); //客户基本信息修改操作
//		customer.setSeq_id(model.getCustomer_seq_id());
//		customer = customerDao.queryRigid(customer);
		customer.setCompany_name(model.getCompany_name());
		customer.setCompany_address(model.getCompany_address());
		customer.setCompany_telphone(model.getCompany_telphone());
		customer.setCompany_fax(model.getCompany_fax());
		customer.setCompany_mobile(model.getCompany_mobile());
		customer.setCompany_postcode(model.getCompany_postcode());
		customer.setCorporation_represen(model.getCorporation_represen());
		customer.setCompany_url(model.getCompany_url());
		customer.setCompany_mail(model.getCompany_mail());
		customer.setCompany_industry(model.getCompany_industry());
		customer.setCorporation_represen_qq(model.getCorporation_represen_qq());
		customer.setCorporation_represen_wechat(model.getCorporation_represen_wechat());
		customer.setCompany_remark(model.getCompany_remark());
		
		customer.setCustomer_id(model.getCustomer_id());
		customer.setCustomer_type(model.getCustomer_type());
		customer.setName(model.getName());
		customer.setMember_id(model.getMember_id());
		customer.setTitle(model.getTitle());
		customer.setGender(model.getGender());
		customer.setBirthday(model.getBirthday());
		customer.setCerti_type(model.getCerti_type());
		customer.setCerti_no(model.getCerti_no());
		customer.setCerti_validdate(model.getCerti_validdate());
		customer.setEducation(model.getEducation());
		customer.setNationality(model.getNationality());
		customer.setNation(model.getNation());
		customer.setHomeplace(model.getHomeplace());
		customer.setSeat_address(model.getSeat_address());
		customer.setHeight(model.getHeight());
		customer.setWeight(model.getWeight());
		customer.setPolitical(model.getPolitical());
		customer.setEducation2(model.getEducation2());
		customer.setMarital_stat(model.getMarital_stat());
		customer.setHealth(model.getHealth());
		customer.setJob_type(model.getJob_type());
		customer.setJob_code(model.getJob_code());
		customer.setIncome_type(model.getIncome_type());
		//customer.setIncome(model.getIncome());
		customer.setBank_code(model.getBank_code());
		customer.setBank_account_no(model.getBank_account_no());
		customer.setBank_account_name(model.getBank_account_name());
		customer.setIs_telmsgservice(model.getIs_telmsgservice());
		customer.setRemark(model.getRemark());
		customer.setModifyuser(user.getEmp_id());
		customerDao.updateCustomer(customer);
		
		Integer seqId = customerDao.getSeqId(customer);//获取客户基本信息表中的seq_id
		
		String customer_type=model.getCustomer_type();
		ICustomerContactModel customerContact = new CustomerContactModel(); //客户联系信息插入操作
		if("0".equals(customer_type)){
			customerContact.setCustomer_id(customer.getCustomer_id());
			ICustomerContactModel contactModel = customerDao.getNewestCustomerContact(customerContact);
			boolean boo = checkContactModel(model,contactModel); //执行检查方法
			if(!boo){ //为false的情况下才会插入数据,表示修改了客户联系信息
				customerContact.setBranch_id(model.getBranch_id());
				customerContact.setAddress(model.getAddress());
				customerContact.setZip(model.getZip());
				customerContact.setMobile(model.getMobile());
				customerContact.setFax(model.getFax());
				customerContact.setTelphone(model.getTelphone());
				customerContact.setEmail(model.getEmail());
				customerContact.setJob_com(model.getJob_com());
				customerContact.setJob_tel(model.getJob_tel());
				customerContact.setStatus(CodeTypeConst.CUSTOMER_STATUS_VALID);
				customerContact.setCreateuser(user.getEmp_id());
				customerContact.setModifyuser(user.getEmp_id());
				customerContact.setLog_seq_id(seqId);//备份数据主键
				customerContact.setLog_bustype(CodeTypeConst.CUSTOMER_LOG_BUSTYPE);//备份业务类型
				customerContact.setLog_remark("修改客户联系信息时进行备份");//备份备注
				customerDao.addCustomerContanct(customerContact);
			}
		}else{
			customerDao.deleteCustomerContanct(customer);
			for(String str:contactpersonInfo){
				String[] ones=str.split("#");
				String index=ones[0];//seq_id
				String[] corporation_contact_persons=ones[1].split(",");//联系人
				String corporation_contact_person="";
				if(corporation_contact_persons.length>1){
					corporation_contact_person=corporation_contact_persons[1];//联系人
				}
				String[] corporation_contact_mobiles=ones[2].split(",");//联系人电话
				String corporation_contact_mobile="";//联系人电话
				if(corporation_contact_mobiles.length>1){
					 corporation_contact_mobile=corporation_contact_mobiles[1];//联系人电话
				}
				String[] corporation_contact_qqs=ones[3].split(",");//联系人qq
				String corporation_contact_qq="";//联系人qq
				if(corporation_contact_qqs.length>1){
					 corporation_contact_qq=corporation_contact_qqs[1];//联系人qq
				}
				String[] corporation_contact_wechats=ones[4].split(",");//联系人微信
				String corporation_contact_wechat="";//联系人微信
				if(corporation_contact_wechats.length>1){
					 corporation_contact_wechat=corporation_contact_wechats[1];//联系人微信
				}
				customerContact.setBranch_id(model.getBranch_id());
				customerContact.setCustomer_id(model.getCustomer_id());
				customerContact.setCorporation_contact_person(corporation_contact_person);
				customerContact.setCorporation_contact_mobile(corporation_contact_mobile);
				customerContact.setCorporation_contact_qq(corporation_contact_qq);
				customerContact.setCorporation_contact_wechat(corporation_contact_wechat);
				customerContact.setStatus(CodeTypeConst.CUSTOMER_STATUS_VALID);
				customerContact.setCreateuser(user.getEmp_id());
				customerContact.setModifyuser(user.getEmp_id());
				customerContact.setLog_seq_id(seqId);//备份数据主键
				customerContact.setLog_bustype(CodeTypeConst.CUSTOMER_STATUS_VALID);//备份业务类型
				customerContact.setLog_remark("修改客户联系信息时进行备注");//备份备注
				customerDao.addCustomerContanct(customerContact);//添加客户联系信息
			}
		}
		
		
		ICustomerLogModel customerLog = new CustomerLogModel(); //客户日志表插入操作
		customerLog.setCompany_name(model.getCompany_name());
		customerLog.setCompany_address(model.getCompany_address());
		customerLog.setCompany_telphone(model.getCompany_telphone());
		customerLog.setCompany_fax(model.getCompany_fax());
		customerLog.setCompany_mobile(model.getCompany_mobile());
		customerLog.setCompany_postcode(model.getCompany_postcode());
		customerLog.setCorporation_represen(model.getCorporation_represen());
		customerLog.setCompany_url(model.getCompany_url());
		customerLog.setCompany_mail(model.getCompany_mail());
		customerLog.setCompany_industry(model.getCompany_industry());
		customerLog.setCorporation_represen_qq(model.getCorporation_represen_qq());
		customerLog.setCorporation_represen_wechat(model.getCorporation_represen_wechat());
		customerLog.setCompany_remark(model.getCompany_remark());
		customerLog.setCustomer_id(model.getCustomer_id());
		customerLog.setCustomer_type(model.getCustomer_type());
		customerLog.setName(model.getName());
		customerLog.setTitle(model.getTitle());
		customerLog.setGender(model.getGender());
		customerLog.setBirthday(model.getBirthday());
		customerLog.setCerti_type(model.getCerti_type());
		customerLog.setCerti_no(model.getCerti_no());
		customerLog.setCerti_validdate(model.getCerti_validdate());
		customerLog.setEducation(model.getEducation());
		customerLog.setNationality(model.getNationality());
		customerLog.setNation(model.getNation());
		customerLog.setHomeplace(model.getHomeplace());
		customerLog.setSeat_address(model.getSeat_address());
		customerLog.setHeight(model.getHeight());
		customerLog.setWeight(model.getWeight());
		customerLog.setPolitical(model.getPolitical());
		customerLog.setEducation2(model.getEducation2());
		customerLog.setMarital_stat(model.getMarital_stat());
		customerLog.setHealth(model.getHealth());
		customerLog.setJob_type(model.getJob_type());
		customerLog.setJob_code(model.getJob_code());
		customerLog.setIncome_type(model.getIncome_type());
		customerLog.setBank_code(model.getBank_code());
		customerLog.setBank_account_no(model.getBank_account_no());
		customerLog.setBank_account_name(model.getBank_account_name());
		customerLog.setIs_telmsgservice(model.getIs_telmsgservice());
		customerLog.setRemark(model.getRemark());
		customerLog.setStatus(CodeTypeConst.CUSTOMER_STATUS_VALID);
		customerLog.setCreateuser(user.getEmp_id());
		customerLog.setModifyuser(user.getEmp_id());
		customerLog.setLog_seq_id(seqId);//备份数据主键
		customerLog.setLog_bustype(CodeTypeConst.CUSTOMER_LOG_BUSTYPE);//备份业务类型
		customerLog.setLog_remark("修改客户联系信息时进行备份");//备份备注
		customerDao.addCustomerLog(customerLog);
		
		return true;
	}

	
	/** 
	* 
	* @param model
	* @return 
	* @description:效验机构代码是否存在  存在 true 不存在 false
	*/
	public boolean verifyIsExistBranch(ICustomerContactModel model) {
		BranchModel branchModel = new BranchModel();
		branchModel.setBranch_id(model.getBranch_id());
		List<IBranchModel> list = branchDao.queryByVerifyAll(branchModel);
		if(list.size()==1){
			return true;
		}else{
			return false;
		}
		
	}


	/** 
	* 
	* @param model
	* @return 
	* @description:插入	效验证件号码与证件类型是否一致存在   存在 true 不存在 false
	*/
	public boolean verifyIsExistIdentityAndType(ICustomerModel model) {
		ICustomerModel cusmodel = new CustomerModel();
		List<CustomerViewVOModel> list = null;
		boolean returnbooean = true;
		cusmodel.setCerti_type(model.getCerti_type());
		cusmodel.setCerti_no(model.getCerti_no());
		cusmodel.setAttachment_type(CodeTypeConst.ATTACHMENT_BUSTYPE_CRMFENXI);
		
		list  = customerDao.queryCustomer(cusmodel);
		if(StringUtil.isNull(model.getSeq_id())){//如果是插入操作
			if(list.size()==0){
				returnbooean = false;
			}else{
				returnbooean = true;
			}
		}else{	//如果是更新操作
			if(list.size()==0){
				returnbooean = false;
			}else if(list.size() == 1 && list.get(0).getSeq_id().equals(model.getSeq_id())){//如果没有更改 允许
				returnbooean = false;
			}else{
				returnbooean = true;
			}
		}
		return returnbooean;
		
	}
	
	/**
	 * 
	* 2014-2-7  张晨
	* @param model
	* @return ICustomerModel
	* @description:校验会员编号是否存在
	 */
	public boolean checkMemberId(ICustomerModel model) {
		Integer count  = customerDao.checkMemberId(model);
		if(count <= 0){ //小于等于0表示会员编号不存在
			return true;
		}else{ 
			return false;
		}
	}

	/**
	 * 
	* 2014-1-4  张晨
	* @param model
	* @return ICustomerModel
	* @description:查询最新修改的客户信息
	 */
	public ICustomerVOModel getCustomerNewInfo(ICustomerVOModel model) {
		
		return customerDao.getCustomerNewInfo(model);
	}
	
	//生成15位客户id号码  序列号默认是 series_code : 04 
	protected String createId(String branch_id,String series_code){
		String seq_code=seqDao.queryCommonSeq("seq_id");
		
			//调用现成的方法对取出的识别码进行10位补0
			try {
				seq_code=StringUtil.alignLeft(seq_code, 10);
			} catch (Message e) {
				e.printStackTrace();
			}
		
		
		return branch_id.substring(0, 2)+series_code+seq_code;
	}
	
	/**
	 * 
	* @param model
	* @param conModel
	* @return boolean
	* @description: 检查是否对客户联系信息进行了修改 by 张晨
	 */
	private boolean checkContactModel(CustomerViewVOModel model,ICustomerContactModel conModel){
		boolean boo = true;
		if(!model.getAddress().equals(conModel.getAddress())){
			if(model.getAddress().equals("") ==false){
				boo =  false;
			}
		}
		if(!model.getZip().equals(conModel.getZip())){
			if(model.getZip().equals("") ==false){	
				boo =  false;
			}
		}
		if(!model.getMobile().equals(conModel.getMobile())){
			if(model.getMobile().equals("") ==false){	
				boo =  false;
			}
		}
		if(!model.getFax().equals(conModel.getFax())){
			if(model.getFax().equals("")  ==false){	
				boo =  false;
			}
		}
		if(!model.getTelphone().equals(conModel.getTelphone())){
			if(model.getTelphone().equals("") ==false){	
				boo =  false;
			}
		}
		if(!model.getEmail().equals(conModel.getEmail())){
			if(model.getEmail().equals("")  ==false){
				boo =  false;
			}
		}
		if(!model.getJob_com().equals(conModel.getJob_com())){
			if(model.getJob_com().equals("") ==false){
				boo =  false;
			}
		}
		if(!model.getJob_tel().equals(conModel.getJob_tel())){
			if(model.getJob_tel().equals("") ==false){
				boo = false;
			}
		}
		return boo;
	}
	
	/**
	 * 客户信息维护导出
	 */
	@Override
	public List<ICustomerVOModel> doachuCustomer(ICustomerVOModel model) {
		// TODO Auto-generated method stub
		return (List<ICustomerVOModel>) customerDao.daochuCustomer(model);
	}
	/**
	 * 修改客户接触信息
	 */
	@Override
	public boolean addCustomerJieChu(ICustomerJieChuModel model,IUserModel user) {
		ICustomerJieChuModel mod=new CustomerJieChuModel();
		
		mod.setSeq_id(model.getSeq_id());
		mod.setAction_notes(model.getAction_notes());
		mod.setAction_time(model.getAction_time());
		mod.setCustomer_id(model.getCustomer_id());
		jiechuDao.addCustomerJieChu(model);
		return true;
	}
	/**
	 * 查询客户接触信息
	 */
	@Override
	public CustomerViewVOModel queryJieChu(ICustomerModel model) {
		return customerDao.queryJieChu(model);
	}
	/**
	 * 理赔信息
	 */
	@Override
	public List<IInfclaimsModel> queryInfclaimsAll(ICustomerModel model){
		return customerDao.queryInfclaimsAll(model);
	}
	/**
	 * 客户明细接触信息
	 */
	@Override
	public List<ICustomerJieChuModel> queryJieChuAll(ICustomerModel model) {
		return customerDao.queryJiechuAll(model);
	}
	/**
	 * 承包信息
	 */
	@Override
	public List<ILaspolicyModel> queryLas(ICustomerModel model) {
		return customerDao.queryLas(model);
	}

	@Override
	public ICustomerModel getClasscode(ICustomerModel model) {
		return customerDao.getClasscode(model);
	}
	/*
	 * 新增
	 */
	@Override
	public boolean queryCustomer1(ICustomerVOModel model) {
		customerDao.queryCustomer1(model);
		return true;
		}
	/*
	 *修改
	 */
	@Override
	public boolean modifySave(ICustomerVOModel model) {
		customerDao.modifySave(model);
		return true;
	}
/*
 * //查询单个
	
 */
	@Override
	public List<CustomerVOModel> goModify(String customer_id) {
		List<CustomerVOModel> list = customerDao.goModify(customer_id);
		return list;
		
	}
}