package com.newtouch.protocol.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.protocol.model.vo.ContractType;
import com.newtouch.protocol.model.vo.IProtocolManageModel;
import com.newtouch.protocol.model.vo.ProtocolCategoryModel;
import com.newtouch.protocol.model.vo.ProtocolManageModel;

@Component
public class ProtocolManageDao extends BaseDao implements IProtocolManageDao {
	/**
	 * 
	 * @param model
	 * @return
	 * @description:协议管理-增加
	 */

	@Override
	public boolean addProtocol(List<IProtocolManageModel> list) {
		Integer i = (Integer) this.getSqlMapClientTemplate().insert("protocolmanage.addProtocol", list);
		return true;
	}

	public boolean updateProtocol(List<IProtocolManageModel> list) {
		for (IProtocolManageModel model : list) {
			this.getSqlMapClientTemplate().update("protocolmanage.updateProtocol", model);
		}
		return true;
	}
	
	@Override
	public boolean addProtocolNew(List<ProtocolManageModel> list) {
		Integer i = (Integer) this.getSqlMapClientTemplate().insert("protocolmanage.addProtocolNew", list);
		return true;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:协议管理-查询
	 */

	@Override
	public List<IProtocolManageModel> queryProtocol(IProtocolManageModel model) {
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("protocolmanage.queryProtocols_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		return this.getSqlMapClientTemplate().queryForList("protocolmanage.queryProtocols", model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:协议管理-修改
	 */

	@Override
	public boolean modifyProtocol(IProtocolManageModel model) {
		this.getSqlMapClientTemplate().update("protocolmanage.updateProtocol", model);
		return true;
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
	 * @description:协议管理-维护-页面
	 */
	@Override
	public IProtocolManageModel getProtocolModifyView(IProtocolManageModel model) {
		return (IProtocolManageModel) this.getSqlMapClientTemplate().queryForObject("protocolmanage.getProtocolView", model);
	}

	/**
	 * 查询保险公司协议
	 */
	@Override
	public boolean checkAgreement_no(IProtocolManageModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("protocolmanage.queryCountAgreement_no", model);
		if (count >= 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 查询保险公司协议机构是否重复
	 * 
	 * @param branch_id
	 * @return
	 */
	public boolean checkBranch(String branch_id) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("protocolmanage.queryCountBranch", branch_id);
		if (count >= 1) {
			return false;
		} else {
			return true;
		}
	}

	// 注销
	@Override
	public boolean updateProtocolStatus(IProtocolManageModel model) {
		this.getSqlMapClientTemplate().update("protocolmanage.updateProtocolStatus", model);
		return true;
	}
	// /**
	// * 拼接协议号
	// * @param branch_id
	// * @return
	// */
	// @Override
	// public String getAgreement_no(String branch_id) {
	// SimpleDateFormat dateFormat=new SimpleDateFormat("yyyymmdd");
	// Date date = new Date();
	// String date1=dateFormat.format(date);
	// int count = (Integer)
	// this.getSqlMapClientTemplate().queryForObject("protocolmanage.getAgreement_no",branch_id);
	// String count2 = null;
	// if(count<100){
	// String count1=StrUtil.alignLeft(count, 3);
	// count2 = count1;
	// }else{
	// count2 = String.valueOf(count);
	// }
	//
	// StringBuilder agreement=new StringBuilder();
	// agreement.append(date1);
	// agreement.append(branch_id);
	// agreement.append(count2);
	// String agreement1=agreement.toString();
	// return agreement1;

	@Override
	public List<ProtocolManageModel> queryProtocolAll(IProtocolManageModel model) {
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("protocolmanage.queryProtocols_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		return this.getSqlMapClientTemplate().queryForList("protocolmanage.queryProtocolsAll", model);
	}

	public List<ProtocolManageModel> queryProtocolsAgr(IProtocolManageModel model) {
		return this.getSqlMapClientTemplate().queryForList("protocolmanage.queryProtocolsAgr", model);
	}
	
	@Override
	public Integer addProtocolPdf(IProtocolManageModel model) {
		this.getSqlMapClientTemplate().update("protocolmanage.addProtocolPdf", model);
		return null;
	}

	@Override
	public List<ProtocolCategoryModel> findCategory() {
		ProtocolCategoryModel model = new ProtocolCategoryModel();
		return this.getSqlMapClientTemplate().queryForList("protocolmanage.findCategory", model);
	}

	@Override
	public List<ContractType> findContract() {
		// TODO Auto-generated method stub
		ContractType model = new ContractType();
		return this.getSqlMapClientTemplate().queryForList("protocolmanage.findContract", model);
	}

	@Override
	public Integer findSysBranch(IProtocolManageModel model) {
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("protocolmanage.sysBranch", model);
		return count;
	}

	@Override
	public Integer findCpyBranch(IProtocolManageModel model) {
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("protocolmanage.cpyBranch", model);
		return count;
	}
	
	public ProtocolManageModel findAgreementNo(){
		ProtocolManageModel model = new ProtocolManageModel();
		model = (ProtocolManageModel)this.getSqlMapClientTemplate().queryForObject("protocolmanage.findAgreementNo", "");
		return model;
	}

	@Override
	public Integer findSupProtocol(ProtocolManageModel model) {
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("protocolmanage.findSupProtocol", model);
		return count;
	}

	@Override
	public boolean saveSupProtocol(ProtocolManageModel model) {
		this.getSqlMapClientTemplate().insert("protocolmanage.saveSupProtocol", model);
		return true;
	}

	@Override
	public boolean updateSupProtocol(IProtocolManageModel model) {
		this.getSqlMapClientTemplate().update("protocolmanage.updateSupProtocol", model);
		return true;
	}
	
}
