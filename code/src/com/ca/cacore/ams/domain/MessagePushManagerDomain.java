package com.ca.cacore.ams.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.dao.IMessagePushManagerDao;
import com.ca.cacore.ams.model.vo.IMessagePushVOModel;


@Service
public class MessagePushManagerDomain implements IMessagePushManagerDomain{
	@Autowired private IMessagePushManagerDao messagePushManagerDao;

	@Override
	public List<IMessagePushVOModel> queryList(IMessagePushVOModel model) {
		return messagePushManagerDao.queryList(model);
	}

	@Override
	public IMessagePushVOModel queryById(IMessagePushVOModel model) {
		return messagePushManagerDao.queryById(model);
	}

	@Override
	public void saveMessagePushModify(IMessagePushVOModel model) {
		messagePushManagerDao.saveMessagePushModify(model);
	}

	@Override
	public List<IMessagePushVOModel> queryMessagePushStatus(
			IMessagePushVOModel model) {
		return messagePushManagerDao.queryMessagePushStatus(model);
	}

	@Override
	public boolean dealMessagePush(IMessagePushVOModel model) {
		messagePushManagerDao.dealMessagePush(model);
		return true;
	}

}
