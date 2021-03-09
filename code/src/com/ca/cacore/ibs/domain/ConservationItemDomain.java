package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IConservationItemDao;
import com.ca.cacore.ibs.model.bo.ConservationItemModel;

@Service
public class ConservationItemDomain implements IConservationItemDomain{
	@Autowired private IConservationItemDao conservationItemDao;
	@Override
	public List<ConservationItemModel> queryConservationItem() {
		return conservationItemDao.queryConservationItem();
	}
}
