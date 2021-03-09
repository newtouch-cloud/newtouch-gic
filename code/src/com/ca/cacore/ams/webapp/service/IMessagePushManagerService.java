package com.ca.cacore.ams.webapp.service;

import com.ca.cacore.ams.model.vo.IMessagePushVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IMessagePushManagerService {
	/**
	 * @param 传入IMessagePushVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询
	 */
	public ReturnMsg queryMessagePush(IMessagePushVOModel model);
	
	/**
	 * @param 传入IMessagePushVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 根据taskid查询
	 */
	public ReturnMsg queryMessagePushByTaskId(IMessagePushVOModel model);
	
	public ReturnMsg saveMessagePushModify(IMessagePushVOModel model);
	
	public String queryMessagePushStatus(IMessagePushVOModel model);
	public ReturnMsg dealMessagePush(IMessagePushVOModel model);
}
