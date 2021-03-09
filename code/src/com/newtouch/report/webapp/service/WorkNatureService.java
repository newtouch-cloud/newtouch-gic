package com.newtouch.report.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.report.dao.IWorkNatureDao;
import com.newtouch.report.model.vo.IWorkNatureModel;


@Service
public class WorkNatureService implements IWorkNatureService{
	
	@Autowired
	private IWorkNatureDao workNatureDao;
	
	@Override
	public ReturnMsg queryWorkNature(IWorkNatureModel model) {
		// TODO Auto-generated method stub
		ReturnMsg msg = new ReturnMsg();
		try{
			List<IWorkNatureModel> list = workNatureDao.queryWorkNature(model);
			msg.setDataList(TransHelper.list2MapList(list));
		}catch(Exception e){
			msg.setFailMessage("查询出错");
			e.printStackTrace();
		}	
		return msg;
	}
	
	@Override
	public List<IWorkNatureModel> queryWorkNatures(IWorkNatureModel model) {
		// TODO Auto-generated method stub
		return workNatureDao.queryWorkNature(model);
	}

	@Override
	public IWorkNatureModel queryTotal(IWorkNatureModel model) {
		// TODO Auto-generated method stub
		return workNatureDao.queryTotal(model);
	}

	@Override
	public IWorkNatureModel queryHeadquarter(IWorkNatureModel model) {
		// TODO Auto-generated method stub
		return workNatureDao.queryHeadquarter(model);
	}

}
