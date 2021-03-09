package com.ca.cacore.manage.dao.branch;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.BranchLevelModel;
import com.newtouch.core.daobase.BaseDao;
/**
 * @author admin
 *	获取机构级别标签内容
 */
@Component
public class BranchLevelDao extends BaseDao {
	@SuppressWarnings("unchecked")
	public List<BranchLevelModel> getAllBranchLevelData() {
		return (List<BranchLevelModel>)this.getSqlMapClientTemplate().queryForList("branchLevel.getBranchLevelData");
	}
	
	@SuppressWarnings("unchecked")
	public List<BranchLevelModel> getAllBranchLevelDataNew() {
		return (List<BranchLevelModel>)this.getSqlMapClientTemplate().queryForList("branchLevel.getBranchLevelDataNew");
	}
}
