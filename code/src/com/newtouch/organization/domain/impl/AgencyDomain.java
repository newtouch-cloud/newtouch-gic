package com.newtouch.organization.domain.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.organization.dao.IAgencyDao;
import com.newtouch.organization.domain.IAgencyDomain;
import com.newtouch.organization.model.vo.IAgencyModel;
import com.newtouch.organization.model.vo.impl.AgencyModel;

@Service
public class AgencyDomain implements IAgencyDomain {
	@Autowired
	IAgencyDao iagencyDao;

	// 中介管理查询
	@Override
	public List<IAgencyModel> selectAgency(IAgencyModel model) {
		return iagencyDao.selectAgency(model);
	}

	// 中介管理维护查询
	@Override
	public IAgencyModel QueryAgencyInfo(IAgencyModel model) {
		return iagencyDao.QueryAgencyInfo(model);
	}

	// 中介维护保存
	@Override
	public Integer insertAgency(IAgencyModel model) {
		return iagencyDao.insertAgency(model);
	}

	// 中介管理维护
	@Override
	public int updateAgency(IAgencyModel model) {
		int flag = iagencyDao.updateAgency(model);
		return flag;
	}

	// 根据机构树查询协议号
	@Override
	public List<IAgencyModel> queryAgreement(IAgencyModel model) {
		return iagencyDao.queryAgreement(model);
	}

	// 协议号查询时间
	@Override
	public Integer queryAgreementNo(IAgencyModel model) {
		return iagencyDao.queryAgreementNo(model);
	}

	// 明细
	@Override
	public IAgencyModel selectUpdateAgency(IAgencyModel model) {
		return iagencyDao.selectUpdateAgency(model);
	}

	// 通过中介表查协议号
	@Override
	public List<Map<String, Object>> queryRelationAn(AgencyModel model) {
		return iagencyDao.queryRelationAn(model);
	}

	// 删除
	@Override
	public Integer deleteAgency(List<String> list, String repair_coding) {
		return iagencyDao.deleteAgency(list, repair_coding);
	}

	// 导入
	@Override
	public Integer importInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iagencyDao.importInfo(map);
	}

	// 统一社会信用编码保存
	@Override
	public Integer updateSocialCode(AgencyModel model) {
		return iagencyDao.updateSocialCode(model);
	}

	public Integer updateAgencyNO(String repair_coding_mdf, String repair_coding) {
		return iagencyDao.updateAgencyNO(repair_coding_mdf, repair_coding);
	}

	@Override
	public Integer deleteAgencyAll(String agency_no) {
		return iagencyDao.deleteAgencyAll(agency_no);
	}

	// 删除表中重复数据
	@Override
	public Integer deleteExcel(List<String> remove_list) {
		return iagencyDao.deleteExcel(remove_list);
	}

	// jiaoyan
	@Override
	public List<String> queryExcel() {
		return iagencyDao.queryExcel();
	}

	@Override
	public Integer deleteAreement(String agency_no) {

		return iagencyDao.deleteAreement(agency_no);
	}

	@Override
	public Integer queryrepair_coding(IAgencyModel model) {
		Integer count = iagencyDao.queryrepair_coding(model);

		return count;

	}

}
