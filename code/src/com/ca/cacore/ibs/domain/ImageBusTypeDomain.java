package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IImageBusTypeDao;
import com.ca.cacore.ibs.model.bo.ImageBusTypeModel;
/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:影像件类型标签 domain 实现类
 */
@Service
public class ImageBusTypeDomain implements IImageBusTypeDomain {
	@Autowired private IImageBusTypeDao imageBusTypeDao;
	
	@Override
	public List<ImageBusTypeModel> getTypes() {
		
		return imageBusTypeDao.getTypes() ;
	}

}
