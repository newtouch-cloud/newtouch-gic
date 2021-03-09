package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.bo.FeeStatusModel;
/**
 * 
* @since:    2014年1月10日   
* @author    ZhangChen
* @description:费用处理状态标签Domain层接口
 */
public interface IFeeStatusDomain {

	public List<FeeStatusModel> getStatus();

}
