package com.newtouch.report.dao;

import java.util.List;

import com.newtouch.report.model.vo.IBusinessTypeModel;

public interface IBussinessTypeDao {

	List<IBusinessTypeModel> queryBusinessType(IBusinessTypeModel businessTypeModel);

	List<IBusinessTypeModel> queryBusinessType1(IBusinessTypeModel businessTypeModel);

}
