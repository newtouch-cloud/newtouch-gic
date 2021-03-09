package com.newtouch.core.rightsmgmt.dao;

import java.util.ArrayList;

import com.newtouch.core.rightsmgmt.model.ISuperiorModel;
import com.newtouch.core.rightsmgmt.model.SuperiorModel;

public interface ISuperiorDao {

	public SuperiorModel querySuperior(ISuperiorModel model);

	public ArrayList<SuperiorModel> querySuperiorList(ISuperiorModel model);

	public void insertSuperior(ISuperiorModel model);

	public void updateSuperior(ISuperiorModel model);

	public ArrayList<SuperiorModel> findSupersByBranchId(ISuperiorModel model);

	public ArrayList<SuperiorModel> querySuperiorInfos(ISuperiorModel model);

	public void deleteSuperior(ISuperiorModel model);

	public ISuperiorModel queryOpt(ISuperiorModel model);

	public Boolean queryIsExistByName(String superior);

}
