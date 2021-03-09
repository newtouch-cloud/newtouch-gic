package com.ca.cacore.csm.domain.customerLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.dao.customer.ICustomerDao;
import com.ca.cacore.csm.dao.customerLog.ICustomerLogDao;
import com.ca.cacore.csm.model.bo.CustomerLogModel;
import com.ca.cacore.csm.model.bo.CustomerModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;


@Service
public class CustomerLogDomain implements ICustomerLogDomain{

	@Autowired private ICustomerLogDao customerLogDao;
	@Autowired private ICustomerDao customerDao ;
	 
	
	@Override
	public boolean addCustomerLogDomain(ICustomerModel model,IUserModel user,boolean flag) {
		ICustomerModel model0 = new CustomerModel();
		model0.setCustomer_id(model.getCustomer_id());
		model0 = customerDao.validateCustomer(model0).get(0);
		
		//赋值操作
		CustomerLogModel customerLogModel = new CustomerLogModel();
		//BeanUtils.copyProperties(model1,customerLogModel);
		customerLogModel.setBank_account_name(model0.getBank_account_name());
		customerLogModel.setBank_account_no(model0.getBank_account_no());
		customerLogModel.setBank_code(model0.getBank_code());
		customerLogModel.setBirthday(model0.getBirthday());
		customerLogModel.setCerti_no(model0.getCerti_no());
		customerLogModel.setCerti_type(model0.getCerti_type());
//		customerLogModel.setCerti_validdate(model0.getCerti_validdate());
		customerLogModel.setCustomer_id(model0.getCustomer_id());
//		customerLogModel.setCustomer_type(model0.getCustomer_type());
		customerLogModel.setEducation(model0.getEducation());
		customerLogModel.setEducation2(model0.getEducation2());
		customerLogModel.setGender(model0.getGender());
		customerLogModel.setHealth(model0.getHealth());
		customerLogModel.setHeight(model0.getHeight());
		customerLogModel.setHomeplace(model0.getHomeplace());
//		customerLogModel.setIncome_type(model0.getIncome_type());
//		customerLogModel.setIs_telmsgservice(model0.getIs_telmsgservice());
		customerLogModel.setJob_code(model0.getJob_code());
		customerLogModel.setJob_type(model0.getJob_type());
		customerLogModel.setLog_bustype(model0.getJob_type());
		customerLogModel.setMarital_stat(model0.getMarital_stat());
		customerLogModel.setName(model0.getName());
		customerLogModel.setNation(model0.getNation());
		customerLogModel.setNationality(model0.getNationality());
		customerLogModel.setPolitical(model0.getPolitical());
		customerLogModel.setRemark(model0.getRemark());
		customerLogModel.setSeat_address(model0.getSeat_address());
		customerLogModel.setStatus(model0.getStatus());
		customerLogModel.setWeight(model0.getWeight());
//		customerLogModel.setCustomer_type(model.getCustomer_type());
		customerLogModel.setTitle(model0.getTitle());
		customerLogModel.setLog_bustype("111");
		customerLogModel.setLog_seq_id(model.getSeq_id());
		//customerLogModel.setCerti_validdate(certi_validdate);
		//CRM_CUSTOMER_LOG"."CERTI_VALIDDATE
		customerLogModel.setCreateuser(user.getEmp_id());
		customerLogModel.setModifyuser(user.getEmp_id());
		if(flag){
			customerLogModel.setLog_remark("客户基本信息为添加型操作");
		}else{
			customerLogModel.setLog_remark("客户基本信息为修改型操作");
		}
		
		return  customerLogDao.addCustomerLog(customerLogModel);
	}

}
