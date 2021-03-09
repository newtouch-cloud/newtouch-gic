package com.ca.cacore.manage.webapp.webtag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.branch.BranchLevelDao;
import com.ca.cacore.manage.model.bo.BranchLevelModel;
import com.newtouch.component.c13webtag.bootstrap.DynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectData;
import com.newtouch.component.c13webtag.bootstrap.IDynamicSelectDataSupport;
import com.newtouch.core.serverbase.ServerBase;
@Service
public class BranchLevelSelectNew extends ServerBase implements IDynamicSelectDataSupport {
	@Autowired private BranchLevelDao branchLevelDao;
	//获取机构级别标签内容（下拉）
	@Override
	public List<IDynamicSelectData> getData() {
		List<BranchLevelModel> list=branchLevelDao.getAllBranchLevelDataNew();
		List<IDynamicSelectData> sds=new ArrayList<IDynamicSelectData>();
		for (BranchLevelModel branchLevelModel : list) {
			IDynamicSelectData sd=new DynamicSelectData();
			sd.setId(branchLevelModel.getBranch_level_code());
			sd.setName(branchLevelModel.getBranch_level_name());
			sd.setDisplayLable(branchLevelModel.getBranch_level_name());
			sds.add(sd);
		}
		return sds;
	}
	@Override
	public List<IDynamicSelectData> getData(String parameter) {
		return null;
	}

}
