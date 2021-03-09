package com.ca.cacore.maas.domain.protocol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.maas.dao.protocol.IProtocolDao;
import com.ca.cacore.maas.model.bo.IProtocolModel;
import com.ca.cacore.maas.model.bo.ProtocolModel;
import com.newtouch.component.c11assistant.BusinessException;

/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-Domain层
*/
@Service
public class ProtocolDomain implements IProtocolDomain {

	@Autowired IProtocolDao protocolDao;
	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-增加
	*/
	@Override
	public boolean addProtocol(IProtocolModel model) {
		protocolDao.addProtocol(model);
		return true;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-查询
	*/
	@Override
	public List<IProtocolModel> queryProtocol(IProtocolModel model) {
		return protocolDao.queryProtocol(model);
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-修改
	*/
	@Override
	public boolean modifyProtocol(IProtocolModel model) {
		protocolDao.modifyProtocol(model);
		return false;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-导出
	*/
	@Override
	public List<IProtocolModel> queryProtocolForExport(IProtocolModel model) {
		return protocolDao.queryProtocolForExport(model);
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-获取签订人信息(由工号查询姓名)
	*/
	@Override
	public IProtocolModel getProtocolPersonInformation(IProtocolModel model) {
		return protocolDao.getProtocolPersonInformation(model);
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-维护-页面
	*/
	@Override
	public IProtocolModel getProtocolModifyView(IProtocolModel model) {
		return protocolDao.getProtocolModifyView(model);
	}
	
	/** 
	* 
	* @param model void
	* @description:协议管理-长度检查
	*/
	public void checkProtocolLength(IProtocolModel model){
		if(model.getIns_branch().length()>200){
			throw new BusinessException("保险公司不能超过100个字符，请重新输入！");
		}
		if(model.getIns_branchname().length()>100){
			throw new BusinessException("签约机构不能超过100个字符，请重新输入！");
		}
		if(model.getContacts_name().length()>9){
			throw new BusinessException("联系人不能超过9个字符，请重新输入！");
		}
		if(model.getAgreement_no().length()>19){
			throw new BusinessException("协议号不能超过19个字符，请重新输入！");
		}
	}

	@Override
	public List<IProtocolModel> getProductName(IProtocolModel model) {
		return protocolDao.getProductName(model);
	}

	@Override
	public List<IProtocolModel> getProductCode(IProtocolModel model) {
		return protocolDao.getProductCode(model);
	}

	@Override
	public Integer addProtocolView() {
		return protocolDao.addProtocolView();
	}

	@Override
	public Integer updateProtocolStatus(IProtocolModel model) {
		return protocolDao.updateProtocolStatus(model);
	}

	@Override
	public List<ProtocolModel> checkagreement(IProtocolModel model) {
		return protocolDao.checkagreement(model);
	}
}
