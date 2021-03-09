package com.ca.cacore.csm.domain.customerType;

import java.util.List;

import com.ca.cacore.csm.model.bo.ICustomerTypeModel;
/**
 * 
* @since:    2014年1月4日   13:27
* @author    ZhangChen
* @description: 获取客户类型集合
 */

public interface ICustomerTypeDomain {
	

	public List<ICustomerTypeModel> getTypes();

}
