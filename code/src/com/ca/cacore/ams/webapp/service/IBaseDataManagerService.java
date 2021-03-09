package com.ca.cacore.ams.webapp.service;

import java.util.List;

import com.ca.cacore.ams.model.vo.IBaseDataVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IBaseDataManagerService {
	public ReturnMsg queryBaseData(IBaseDataVOModel model);
	public ReturnMsg queryListBaseDataTypecode(IBaseDataVOModel model);
	public ReturnMsg queryBaseDataById(IBaseDataVOModel model);
	public String queryBaseDataByTypecode(IBaseDataVOModel model);	
	public ReturnMsg addBaseData(IBaseDataVOModel model);
	public ReturnMsg modifyBaseData(IBaseDataVOModel model);
	public ReturnMsg deleteBaseData(IBaseDataVOModel model);
	public ReturnMsg deleteBaseDataById(IBaseDataVOModel model);
	public String getBaseDataId(IBaseDataVOModel model);
	public String getBaseDataTypeid(IBaseDataVOModel model);
}
