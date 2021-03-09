package com.ca.cacore.csm.domain.customerType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.csm.dao.customerType.ICustomerTypeDao;
import com.ca.cacore.csm.model.bo.ICustomerTypeModel;
/**
 * 
* @since:    2014年1月4日   13:27
* @author    ZhangChen
* @description: 获取客户类型集合
 */
@Service
public class CustomerTypeDomain implements ICustomerTypeDomain{
	@Autowired private ICustomerTypeDao customerTypeDao;
	
	public List<ICustomerTypeModel> getTypes() {
		return customerTypeDao.getTypes();
	}

}
