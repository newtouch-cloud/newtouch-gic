package com.newtouch.report.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.report.dao.IBussinessTypeDao;
import com.newtouch.report.model.vo.IBusinessTypeModel;

@Service
public class BussinessTypeService implements IBussinessTypeService{
	
	@Autowired
	private IBussinessTypeDao bussinessTypeDao;
	@Override
	public ReturnMsg queryBusinessType(IBusinessTypeModel businessTypeModel) {
		// TODO Auto-generated method stub
		ReturnMsg msg = new ReturnMsg();
		List<IBusinessTypeModel> list = bussinessTypeDao.queryBusinessType(businessTypeModel);
		msg.setDataList(TransHelper.list2MapList(list));
		return msg;
	}
	@Override
	public List<IBusinessTypeModel> queryBusinessType1(
			IBusinessTypeModel businessTypeModel) {
		// TODO Auto-generated method stub
		return bussinessTypeDao.queryBusinessType1(businessTypeModel);
	}

}
