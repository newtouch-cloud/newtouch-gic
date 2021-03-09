package com.ca.cacore.ams.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ams.model.vo.IMessagePushVOModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class MessagePushManagerDao extends BaseDao implements IMessagePushManagerDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<IMessagePushVOModel> queryList(IMessagePushVOModel model) {
		int count=(Integer) this.getSqlMapClientTemplate().queryForObject("messagePushManager.getMessagePush_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return this.getSqlMapClientTemplate().queryForList("messagePushManager.getAllMessagePush", model);
	}

	@Override
	public IMessagePushVOModel queryById(IMessagePushVOModel model) {
		return (IMessagePushVOModel)this.getSqlMapClientTemplate().queryForObject("messagePushManager.getMessagePushtaskid", model);
	}

	@Override
	public void saveMessagePushModify(IMessagePushVOModel model) {
		this.getSqlMapClientTemplate().update("messagePushManager.saveMessagePushModify", model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IMessagePushVOModel> queryMessagePushStatus(
			IMessagePushVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("messagePushManager.queryMessagePushStatus", model);
	}

	@Override
	public boolean dealMessagePush(IMessagePushVOModel model) {
		this.getSqlMapClientTemplate().update("messagePushManager.dealMessagePush", model);
		return true;
	}

	@Override
	public boolean insertCaTask(IMessagePushVOModel model) {
		this.getSqlMapClientTemplate().insert("messagePushManager.insertCaTask", model);
		return true;
	}
	
}
