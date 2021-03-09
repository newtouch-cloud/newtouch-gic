package com.newtouch.protocol.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.protocol.dao.IProtocolManageDao;
import com.newtouch.protocol.model.vo.ContractType;
import com.newtouch.protocol.model.vo.IProtocolManageModel;
import com.newtouch.protocol.model.vo.ProtocolCategoryModel;
import com.newtouch.protocol.model.vo.ProtocolManageModel;
@Service
public class ProtocolManageDomain implements IProtocolManageDomain{
	@Autowired
	IProtocolManageDao protocolManageDao;

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-查询
	*/
	@Override
	public List<IProtocolManageModel> queryProtocol(IProtocolManageModel model) {
		// TODO Auto-generated method stub
		return protocolManageDao.queryProtocol(model);
	}
	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-修改
	*/

	@Override
	public boolean modifyProtocl(IProtocolManageModel model) {
		
		protocolManageDao.modifyProtocol(model);
		return false;
	}

	@Override
	public List<IProtocolManageModel> queryProtocolForExport(IProtocolManageModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProtocolManageModel getProtocolPersonInformation(IProtocolManageModel model) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-增加
	*/
	@Override
	public boolean addProtocol(List<IProtocolManageModel> list) {
		
		protocolManageDao.addProtocol(list);
		return true;
	}
	public boolean updateProtocol(List<IProtocolManageModel> list){
		protocolManageDao.updateProtocol(list);
		return true;
	}
	
	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-修改-页面
	*/
	@Override
	public IProtocolManageModel getProtocolModifyView(IProtocolManageModel model) {
		return protocolManageDao.getProtocolModifyView(model);
	}
	//注销
	@Override
	public boolean updateProtocolStatus(IProtocolManageModel model) {
		 protocolManageDao.updateProtocolStatus(model);
		 return true;
	}
//	/** 
//	* 
//	* @param model
//	* @return 
//	* @description:协议管理-修改-拼接协议号
//	*/
//	@Override
//	public String  getAgreement_no(String branch_id) {
//		return protocolManageDao.getAgreement_no(branch_id);
//	}
	@Override
	public boolean addProtocolNew(List<ProtocolManageModel> list) {
		protocolManageDao.addProtocolNew(list);
		return true;
	}
	@Override
	public List<ProtocolManageModel> queryProtocolAll(IProtocolManageModel model) {
		// TODO Auto-generated method stub
		return protocolManageDao.queryProtocolAll(model);
	}
	@Override
	public Integer addProtocolPdf(IProtocolManageModel model) {
		Integer num =  protocolManageDao.addProtocolPdf(model);
		return num;
	}
	@Override 
	public List<ProtocolCategoryModel> findCategory() {
		return protocolManageDao.findCategory();
	}
	@Override
	public List<ContractType> findContract() {
		// TODO Auto-generated method stub
		return protocolManageDao.findContract();
	}
	@Override
	public List<ProtocolManageModel> queryProtocolsAgr(IProtocolManageModel model) {
		// TODO Auto-generated method stub
		return protocolManageDao.queryProtocolsAgr(model);
	}

	
}
