package com.newtouch.report.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.report.model.vo.IBusinessTypeModel;

@Component
public class BussinessTypeDao extends BaseDao implements IBussinessTypeDao{

	@Override
	public List<IBusinessTypeModel> queryBusinessType(IBusinessTypeModel businessTypeModel) {
		// TODO Auto-generated method stub
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("bussinessType.queryBusinessType_count", businessTypeModel);
		System.out.println(count);
		businessTypeModel.getPageCount().setAllRows(count);
		businessTypeModel.getPageCount().calcPage();
		businessTypeModel.getPageCount().setNowPage(businessTypeModel.getPageCount().getNowPage() + 1);
		return this.getSqlMapClientTemplate().queryForList("bussinessType.queryBusinessType", businessTypeModel);
	}

	@Override
	public List<IBusinessTypeModel> queryBusinessType1(
			IBusinessTypeModel businessTypeModel) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("bussinessType.queryBusinessType", businessTypeModel);
	}

}
