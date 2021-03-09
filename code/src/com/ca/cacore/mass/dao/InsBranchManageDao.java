package com.ca.cacore.mass.dao;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.core.daobase.BaseDao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class InsBranchManageDao extends BaseDao implements IInsBranchManageDao {

	@SuppressWarnings("unchecked")
	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:主查询：查询保险公司信息列表
	 */
	public List<ICompanyBranchModel> insBranchQuery(ICompanyBranchModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("insBranchManage.insBranchQuery_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		List<ICompanyBranchModel> list = this.getSqlMapClientTemplate().queryForList("insBranchManage.insBranchQuery", model);
		return list;
	}

	/**
	 * @author GCH
	 * @param model
	 * @param user
	 * @return boolean
	 * @description:新增保险公司
	 */
	public boolean insBranchAdd(ICompanyBranchModel model, IUserModel user) {
		//zdd0724
		if("1".equals(model.getCpy_branch_level())) {
			model.setCreateUser(user.getEmp_id());
			this.getSqlMapClientTemplate().insert("insBranchManage.insBranchAddSysE", model);
		}
		this.getSqlMapClientTemplate().insert("insBranchManage.insBranchAdd", model);
		return true;
	}

	/**
	 * @author GCH
	 * @param model
	 * @return IInsBranchVOModel
	 * @description:保险公司明细查询
	 */
	public ICompanyBranchModel getInsBranch(ICompanyBranchModel model) {
		return (ICompanyBranchModel) this.getSqlMapClientTemplate().queryForObject("insBranchManage.getInsBranch", model);
	}

	/**
	 * @author GCH
	 * @param model
	 * @param user
	 * @return boolean
	 * @description:保险公司信息修改
	 */
	public boolean insBranchModify(ICompanyBranchModel model, IUserModel user) {

		this.getSqlMapClientTemplate().update("insBranchManage.insBranchModifySysE", model);

		this.getSqlMapClientTemplate().update("insBranchManage.insBranchModify", model);
		return true;
	}

	/**
	 * @author GCH
	 * @param model
	 * @return IInsBranchVOModel
	 * @description:查询保险公司名称是否存在
	 */
	public ICompanyBranchModel checkInsbName(ICompanyBranchModel model) {
		return (ICompanyBranchModel) this.getSqlMapClientTemplate().queryForObject("insBranchManage.checkInsbName", model);
	}

	/**
	 * 
	 * @return List<IInsBranchVOModel>
	 * @description:2014-2-25 获取保险公司代码和名称--by张晨用于投诉录入
	 */
	@SuppressWarnings("unchecked")
	public List<ICompanyBranchModel> getAllInsBranch() {
		return this.getSqlMapClientTemplate().queryForList("insBranchManage.getAllInsBranch");
	}

	/**
	 * 机构树查询
	 * 
	 * 下午2:18:57 TODO
	 */
	@SuppressWarnings("unchecked")
	public List<ITree> queryBranchTree(Map<String,Object> param) {
		if(param.get("parentorg_id")==null){
			param.put("parentorg_id","gen");//财险公司的根节点是00000000 全国     zddxiu
		}else{
			param.put("parentorg_level", String.valueOf(Integer.parseInt(param.get("parentorg_level").toString())+1));
		}
		return (List<ITree>) this.getSqlMapClientTemplate().queryForList("insBranchManage.queryCpyBranchTree",param);
	}

	@Override
	public int checkpower0626(String emp_id) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("insBranchManage.checkpower0626", emp_id);

	}

	@Override
	public List<ITree> querySalesFirmBranchTree2() {
		return (List<ITree>) this.getSqlMapClientTemplate().queryForList("insBranchManage.querySalesFirmBranchTree2");
	}

	@Override
	public List<ICompanyBranchModel> getbranchSort() {
		// TODO Auto-generated method stub
		return  (List<ICompanyBranchModel>)this.getSqlMapClientTemplate().queryForList("insBranchManage.getbranchSort");
	}

	@Override
	public String getfirstLeavelsort(String brand_id) {
		// TODO Auto-generated method stub
		return (String) this.getSqlMapClientTemplate().queryForObject("insBranchManage.getfirstLeavelsort", brand_id);
	}

	@Override
	public String selectMaxOneLevel() {
		return (String) this.getSqlMapClientTemplate().queryForObject("insBranchManage.selectMaxOneLevel");
	}

	@Override
	public List<ITree> queryBranchTreeZ(IBranchModel model) {
		//如果没有传父id 则是初始化查询，只查父节点
				if( model.getBranch_id()!=null ){
					return (List<ITree>) this.getSqlMapClientTemplate().queryForList("insBranchManage.queryBranchTreeChild", model);
				}else{
					return (List<ITree>) this.getSqlMapClientTemplate().queryForList("insBranchManage.queryBranchTreeParent", model);
				}
	}

	@Override
	public List<String> getIsPOrLZ() {
		
		return (List<String>) this.getSqlMapClientTemplate().queryForList("insBranchManage.getIsPOrLZ");
	}

	@Override
	public int findIsExist(ICompanyBranchModel model) {
		// TODO Auto-generated method stub
		return (int) this.getSqlMapClientTemplate().queryForObject("insBranchManage.findIsExist",model);
	}

	@Override
	public List<String> queryIdbyUserName(String userName) {
		return (List<String>) this.getSqlMapClientTemplate().queryForList("insBranchManage.queryIdbyUserName",userName);
	}


}
