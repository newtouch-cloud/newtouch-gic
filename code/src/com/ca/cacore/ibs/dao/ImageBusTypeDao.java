package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.ImageBusTypeModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:影像件类型标签 dao 实现类
 */
@Component
public class ImageBusTypeDao extends BaseDao implements IImageBusTypeDao {

	@Override
	public List<ImageBusTypeModel> getTypes() {
		
		return (List<ImageBusTypeModel>)this.getSqlMapClientTemplate().queryForList("policyImage.getTypes");
	}

}
