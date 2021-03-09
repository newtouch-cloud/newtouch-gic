package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ConservationItemModel;
/**
 * 
 * @author zhao
 * @ClassName: IConservationItemDao 
 * @email zhaominqi1990@163.com 
 * @date 2014年1月27日 下午4:42:51 
 * @Description: TODO
 */
public interface IConservationItemDao {
	public List<ConservationItemModel> queryConservationItem();
}
