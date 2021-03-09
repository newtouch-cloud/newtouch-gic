package com.ca.cacore.ams.domain;

import java.util.List;

import com.ca.cacore.ams.model.vo.IMessagePushVOModel;

public interface IMessagePushManagerDomain {
	
	/**  
	* @param IMessagePushVOModel model
	* @return List<IMessagePushVOModel>
	* @description: 查询
	*/
	public List<IMessagePushVOModel> queryList(IMessagePushVOModel model);
	
	/**
	 * @param IMessagePushVOModel model
	 * @return IMessagePushVOModel
	 * @description: 根据taskid查询
	 */
	public IMessagePushVOModel queryById(IMessagePushVOModel model);
	
	public void saveMessagePushModify(IMessagePushVOModel model);
	
	public List<IMessagePushVOModel> queryMessagePushStatus(IMessagePushVOModel model);
	public boolean dealMessagePush(IMessagePushVOModel model);
	
	
}
