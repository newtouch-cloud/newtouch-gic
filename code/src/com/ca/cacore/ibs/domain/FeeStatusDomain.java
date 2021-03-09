package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IFeeStatusDao;
import com.ca.cacore.ibs.model.bo.FeeStatusModel;

/**
 * 
* @since:    2014年1月10日   
* @author    ZhangChen
* @description:费用处理状态标签Domain层实现类
 */
@Service
public class FeeStatusDomain implements IFeeStatusDomain{
	@Autowired private IFeeStatusDao feeStatusDao;
	/**
	 * 
	* 
	* @return List
	* @description: 查询所有费用处理状态内容
	 */
	public List<FeeStatusModel> getStatus() {
		
		return feeStatusDao.getStatus();
	}
	
	

}
