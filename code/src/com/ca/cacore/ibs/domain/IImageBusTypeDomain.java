package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ImageBusTypeModel;
/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:影像件类型标签 domain 接口
 */
public interface IImageBusTypeDomain {

	public List<ImageBusTypeModel> getTypes();
}
