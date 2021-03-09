package com.ca.cacore.ams.dao;

import java.util.List;

import com.ca.cacore.ams.model.vo.IMessagePushVOModel;


public interface IMessagePushManagerDao {
	/** 
	* @author 
	* @param model
	* @return List<IMessagePushVOModel>
	* @description:查询
	*/
	public List<IMessagePushVOModel> queryList(IMessagePushVOModel model);
	
	public IMessagePushVOModel queryById(IMessagePushVOModel model);
	
	public void saveMessagePushModify(IMessagePushVOModel model);
	
	public List<IMessagePushVOModel> queryMessagePushStatus(IMessagePushVOModel model);
	
	public boolean dealMessagePush(IMessagePushVOModel model);
	
	public boolean insertCaTask(IMessagePushVOModel model);
	
}
