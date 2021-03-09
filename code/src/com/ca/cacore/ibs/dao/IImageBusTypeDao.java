package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ImageBusTypeModel;

/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:影像件类型标签 dao 接口
 */
public interface IImageBusTypeDao {

	public List<ImageBusTypeModel> getTypes();
}
