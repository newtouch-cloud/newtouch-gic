package com.ca.cacore.manage.dao.branch;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IBranchStatusHisModel;
import com.ca.cacore.manage.model.vo.BusinessLicenseHisModel;
import com.ca.cacore.manage.model.vo.IBranchStatusHisVOModel;
import com.ca.cacore.manage.model.vo.IBranchVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.core.daobase.BaseDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BranchDao extends BaseDao implements IBranchDao {

	@Override
	public List<IBranchVOModel> queryAllBranch(IBranchModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("branch.queryBranch_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		return (List<IBranchVOModel>) this.getSqlMapClientTemplate().queryForList("branch.queryBranch", model);
	}

	@Override
	public List<IBranchVOModel> exportBranchInfo(IBranchModel model) {
		return (List<IBranchVOModel>) this.getSqlMapClientTemplate().queryForList("branch.exportBranchInfo", model);
	};

	/*
	 * 查询
	 */
	@Override
	public IBranchModel getByBranch(IBranchModel model) {
		return (IBranchModel) this.getSqlMapClientTemplate().queryForObject("branch.getModelBySeqid", model);
	}

	/*
	 * 查询本级机构和上级机构名称
	 */
	@Override
	public IBranchVOModel getBranchView(IBranchModel model) {
		return (IBranchVOModel) this.getSqlMapClientTemplate().queryForObject("branch.queryVOBySeqId", model);

	}

	/*
	 * 添加机构信息
	 */
	@Override
	public boolean addBranch(IBranchModel model) {
		this.getSqlMapClientTemplate().insert("branch.insertBranch", model);
		return true;
	}

	/*
	 * 更新机构信息
	 */
	@Override
	public boolean updateBranch(IBranchModel model) {
		this.getSqlMapClientTemplate().update("branch.updateBranch", model);
		return true;
	}

	/*
	 * 验证表里字段值是否存在(插入验证)
	 */
	@Override
	public List<IBranchModel> queryByVerifyAll(IBranchModel model) {
		return (List<IBranchModel>) this.getSqlMapClientTemplate().queryForList("branch.queryByVerifyAll", model);
	}

	/*
	 * 验证其他数据中字段是否存在（更新验证）
	 */
	@Override
	public List<IBranchModel> queryByVerifyOtherData(IBranchModel model) {
		return (List<IBranchModel>) this.getSqlMapClientTemplate().queryForList("branch.queryByVerifyOtherData", model);
	}

	/**
	 * 2013-12-18新添加方法 BY 张晨 校验前台输入的数据是否已存在
	 * 
	 * @param model
	 * @return Integer
	 */
	@Override
	public Integer queryForVerifyData(IBranchModel model) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("branch.queryForVerifyData", model);
	}

	/**
	 * 机构轨迹表添加
	 */
	@Override
	public boolean addBranchStatusHis(IBranchStatusHisModel model) {
		this.getSqlMapClientTemplate().insert("branch.insertBranchStatusHis", model);
		return true;
	}

	/**
	 * 机构轨迹表查询
	 */
	@Override
	public List<IBranchStatusHisVOModel> queryAllBranchStatusHis(IBranchStatusHisVOModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("branch.queryBranchStatusHis_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		return (List<IBranchStatusHisVOModel>) this.getSqlMapClientTemplate().queryForList("branch.queryBranchStatusHisAll", model);
	}

	/**
	 * 机构树查询 yanqiguang 下午2:18:57 TODO
	 */
	@Override
	public List<ITree> queryBranchTree(IBranchModel model) {
		//如果没有传父id 则是初始化查询，只查父节点
		if( model.getBranch_id()!=null ){
			return (List<ITree>) this.getSqlMapClientTemplate().queryForList("branch.queryBranchTreeChild", model);
		}else{
			return (List<ITree>) this.getSqlMapClientTemplate().queryForList("branch.queryBranchTreeParent", model);
		}
	}

	@Override
	public List<ITree> queryBranchTreeTeam(IBranchModel model) {
		return (List<ITree>) this.getSqlMapClientTemplate().queryForList("branch.queryBranchTreeTeam", model);
	}

	@Override
	public List<ITree> queryBranchTreeExceptCentral(IBranchModel model) {
		return (List<ITree>) this.getSqlMapClientTemplate().queryForList("branch.queryBranchTreeExceptCentral", model);
	}

	/**
	 * 工作流使用
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ITree> queryBranchTree4WF(IBranchModel model) {
		return (List<ITree>) this.getSqlMapClientTemplate().queryForList("branch.queryBranchTree4WF", model);
	}

	/**
	 * 过得下级机构信息 ss
	 * 
	 * @param branch_parentid
	 * @return List<IBranchVOModel>
	 * @description:根据上级机构代码，得到所有的下级机构信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IBranchVOModel> getSubBranchInfo(String branch_parentid) {
		return this.getSqlMapClientTemplate().queryForList("branch.getSubBranchInfo", branch_parentid);
	}



	/**
	 * 判断该机构下是否拥有人员,团队下存在人员，不能注销该团队
	 * 
	 * @param
	 *
	 * @param
	 * @description:
	 */
	@Override
	public List<ICustomerVOModel> isHasPerson(ICustomerVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("branch.isHasPerson", model);
	}

	@Override
	public boolean updateNcDept(IBranchModel model) {
		this.getSqlMapClientTemplate().update("branch.updateNcDept", model);
		return true;
	}

	@Override
	public IBranchModel queryNcBranch(IBranchModel ncmodel) {
		return (IBranchModel) this.getSqlMapClientTemplate().queryForObject("branch.queryNcBranch", ncmodel);
	}

	@Override
	public boolean addNcBranch(IBranchModel ncmodel) {
		this.getSqlMapClientTemplate().insert("branch.insertITF_NC_DEPT", ncmodel);
		return true;
	}

	/**
	 * 查询省 孙豪
	 * 
	 * @return
	 */
	@Override
	public List<IBranchModel> queryProvince() {
		return (List<IBranchModel>) this.getSqlMapClientTemplate().queryForList("branch.queryProvince");
	}

	@Override
	public List<IBranchModel> queryCity(String parent_code) {
		return (List<IBranchModel>) this.getSqlMapClientTemplate().queryForList("branch.queryCity", parent_code);
	}

	@Override
	public List<IBranchModel> queryArea(String parent_code) {
		return (List<IBranchModel>) this.getSqlMapClientTemplate().queryForList("branch.queryArea", parent_code);
	}

	/**
	 * 根据省市县code查询name
	 * 
	 * @param area_code
	 * @return
	 */
	@Override
	public IBranchVOModel getCityByCode(String area_code) {
		return (IBranchVOModel) this.getSqlMapClientTemplate().queryForObject("branch.getCityByCode", area_code);
	}

	@Override
	public String queryBranchLevelName(String branch_level) {
		return (String) this.getSqlMapClientTemplate().queryForObject("branch.queryBranchLevelName", branch_level);
	}
	
	/**
	 * byzdd 20190611
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getParentInfo(IBranchModel model) {
		return (String) this.getSqlMapClientTemplate().queryForObject("branch.getParentInfo", model);
	}
    /**byzdd 20190611
     * 查询该机构是否存在
     */
	@Override
	public int getIsORNoBranchId(String branch_id) {
		return (int) this.getSqlMapClientTemplate().queryForObject("branch.isornoBranchId", branch_id);

	}
   /**
    * byzdd 20190611
    */
	@Override
	public void insertBusinessLicense(IBranchModel model) {
		this.getSqlMapClientTemplate().update("branch.insertBusinessLicense", model);
		BusinessLicenseHisModel model2= new BusinessLicenseHisModel();
		model2.setSeq_id(model.getSeq_id());
		model2.setLicensepath(model.getLicensepath());
		model2.setC_crt_user(model.getCreateUser());
		this.getSqlMapClientTemplate().update("branch.upbusinessLicenseHisc_last_mark",model2.getSeq_id());
		this.getSqlMapClientTemplate().insert("branch.insertBusinessLicenseHis", model2);
	}
	/**
	 * byzdd 20190613
	 * @return
	 */
	@Override
	public List<String>  getBranchLeavel(){
		return (List<String>) this.getSqlMapClientTemplate().queryForList("branch.getBranchLeavel");
	}
	@Override
	public String getCityByNamez(String arename) {
		return (String) this.getSqlMapClientTemplate().queryForObject("branch.getCityByNamez", arename);
	}
    //zddxiu
	@Override
	public List<String> getzzstuts(String str) {
		return (List<String>) this.getSqlMapClientTemplate().queryForList("branch.getzzstuts",str);
	}

	@Override
	public boolean insertbranchList(List<IBranchModel> lists) {
		this.getSqlMapClientTemplate().insert("branch.insertbranchList", lists);
		return true;
	}
  /* zdd05*/  /*zdd0722*/
	@Override
	public int querHeightYON(IBranchModel model) {
		String branch_parentid= model.getBranch_id();
		return (Integer) this.getSqlMapClientTemplate().queryForObject("branch.querHeightYON", model);
	}
   /* zdd05*/
	@Override
	public boolean updatebeanchyezhizhao(IBranchModel model) {
		this.getSqlMapClientTemplate().update("branch.updatebeanchyezhizhao", model);
		return true;
	}

	@Override
	public int isORNoPower(IBranchModel model) {
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("branch.isORNoPower", model);
	}

	@Override
	public List<BusinessLicenseHisModel> businessLicenseHis(int parseInt) {
		return (List<BusinessLicenseHisModel>) this.getSqlMapClientTemplate().queryForList("branch.businessLicenseHis", parseInt);
	}
	@Override
	public void upbusinessLicenseHis(BusinessLicenseHisModel model) {
		this.getSqlMapClientTemplate().update("branch.upbusinessLicenseHis",model);
	}
	
	@Override
	public String branchIdSelect(String branch_id) {
		return (String)this.getSqlMapClientTemplate().queryForObject("branch.branchIdSelect",branch_id);
	}
	
	@Override
	public IBranchVOModel queryBranch(IBranchModel model) {
		return (IBranchVOModel) this.getSqlMapClientTemplate().queryForObject("branch.BranchBySeqId", model);
	}
	
	/*
	 * 添加下级机构信息
	 */
	@Override
	public boolean addBranchJunior(IBranchModel model) {
		this.getSqlMapClientTemplate().insert("branch.insertBranchJunior", model);
		return true;
	}
	
}
