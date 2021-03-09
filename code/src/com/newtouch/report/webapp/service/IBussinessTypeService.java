package com.newtouch.report.webapp.service;

import java.util.List;

import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.report.model.vo.IBusinessTypeModel;

public interface IBussinessTypeService {

	ReturnMsg queryBusinessType(IBusinessTypeModel businessTypeModel);

	List<IBusinessTypeModel> queryBusinessType1(IBusinessTypeModel businessTypeModel);

}
