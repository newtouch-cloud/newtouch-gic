package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ConservationItemModel;

/**
 * 
 * @author zhao
 * @ClassName: IConservationItemDomain 
 * @email zhaominqi1990@163.com 
 * @date 2014年2月8日 上午10:04:55 
 * @Description: 供自定义标签保全项调用  取出保全项
 */
public interface IConservationItemDomain {
	/**
	 * 
	 * @author: zhao
	 * @Description: 取出所有保全项 
	 * @return List<ConservationItemModel>
	 * @throws
	 * @time : 2014年2月8日上午10:06:02
	 */
	public List<ConservationItemModel> queryConservationItem();
}
