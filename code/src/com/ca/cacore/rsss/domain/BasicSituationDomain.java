package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IBasicSituationDao;
import com.ca.cacore.rsss.model.vo.IBasicSituationModel;
@Service
public class BasicSituationDomain implements IBasicSituationDomain {
	@Autowired private  IBasicSituationDao ibasicsituationdao;
	/**
	 * 
	* 
	* @param model
	* @return List<IBasicSituationModel>
	* @description:查询基本情况
	 */
	@Override
	public List<IBasicSituationModel> queryBasicSituation(IBasicSituationModel model) {
		return ibasicsituationdao.queryBasicSituation(model);
	}


	@Override
	public List<IBasicSituationModel> exportBasicSituation(
			IBasicSituationModel model) {
		return ibasicsituationdao.exportBasicSituation(model);
	}
	
}
