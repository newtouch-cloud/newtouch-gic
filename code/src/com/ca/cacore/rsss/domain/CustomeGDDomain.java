package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.ICustomeGDDao;
import com.ca.cacore.rsss.model.vo.ICustomeGDVOModel;

@Service
public class CustomeGDDomain implements ICustomeGDDomain{
@Autowired private  ICustomeGDDao icustomegddao;
	/**
	 * 
	* 
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:查询询客户群体分布表
	 */
	@Override
	public List<ICustomeGDVOModel> queryCustomeGD(ICustomeGDVOModel model) {
//		return null;
		return icustomegddao.queryCustomeGD(model);
	}

	/**
	 * 
	* 
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:导出询客户群体分布表
	 */
	@Override
	public List<ICustomeGDVOModel> exportCustomeGD(
			ICustomeGDVOModel model) {
		return icustomegddao.exportCustomeGD(model);
	}
	
}
