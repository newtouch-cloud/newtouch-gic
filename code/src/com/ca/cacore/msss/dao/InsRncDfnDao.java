package  com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.msss.model.bo.IInsRncDfnModel;
import com.ca.cacore.msss.model.vo.IInsRncDfnVOModel;
import com.ca.cacore.mass.model.bo.CompanyBranchModel;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
 * @author Wang_ds
 * @since 2013-11-20
 * @description DAO层
 */
@Component
public class InsRncDfnDao extends BaseDao implements IInsRncDfnDao {
	/** 
	* 
	* @param model
	* @return 
	* @description:险类查询
	*/
	public List<IInsRncDfnModel> queryInsRncDfnList(IInsRncDfnModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.queryInsRncDfn_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IInsRncDfnModel>)this.getSqlMapClientTemplate().queryForList("InsRncDfn.queryInsRncDfn", model);
	}
	
	/** 
	* 
	* @param model
	* @return boolean
	* @description:新增 其他保险公司 产品信息
	*/
	public boolean insRncDfnAdd(IInsRncDfnModel model) {
		this.getSqlMapClientTemplate().insert("InsRncDfn.insRncDfnAdd", model);
		return true;
	}
	/** 
	* 
	* @param model
	* @return boolean
	* @description:修改 其他保险公司 产品信息
	*/
	public boolean insRncDfnModify(IInsRncDfnModel model) {
		this.getSqlMapClientTemplate().update("InsRncDfn.insRncDfnUpdate", model);
		return true;
	}
	
	/** 
	* 
	* @param model
	* @return IInsRncDfnModel
	* @description: 查询 其他保险公司 产品信息
	*/
	public IInsRncDfnModel getInsRncDfn(IInsRncDfnModel model) {
		return (IInsRncDfnModel) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.getInsRncDfn", model);
	}
	/** 
	* 
	* @param model
	* @return IInsRncDfnModel
	* @description:校验险种代码是否存在
	*/
	public IInsRncDfnVOModel checkRiskCode(IInsRncDfnVOModel model) {
		return (IInsRncDfnVOModel) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.checkRiskCode", model);
	}

	/**
	 * 查询所有险类  by 孙豪
	 */
	@Override
	public List<IInsRncDfnModel> queryClassCode(IInsRncDfnModel model) {
		return (List<IInsRncDfnModel>)this.getSqlMapClientTemplate().queryForList("InsRncDfn.queryClassCode",model);
	}
	/**
	 * 查询所有险种  by 孙豪
	 */
	@Override
	public List<IInsRncDfnModel> queryRiskCode(IInsRncDfnModel model) {
		return (List<IInsRncDfnModel>)this.getSqlMapClientTemplate().queryForList("InsRncDfn.queryRiskCode",model);
	}
	/**
	 * 查询所有险别  by 孙豪
	 */
	@Override
	public List<IInsRncDfnModel> queryTypeCode(IInsRncDfnModel model) {
		return (List<IInsRncDfnModel>)this.getSqlMapClientTemplate().queryForList("InsRncDfn.queryTypeCode",model);
	}
	/**
	 * 查询所有总公司 by 孙豪
	 */
	@Override
	public List<CompanyBranchModel> queryCompany() {
		return (List<CompanyBranchModel>)this.getSqlMapClientTemplate().queryForList("InsRncDfn.queryCompany");
	}

	@Override
	public Integer updateStatus(IInsRncDfnModel model) {
		return this.getSqlMapClientTemplate().update("InsRncDfn.updateStatus", model);
	}

	@Override
	public List<ITree> queryCompanyTree() {
		return (List<ITree>) this.getSqlMapClientTemplate().queryForList("InsRncDfn.queryCompanyTree");
	}

	@Override
	public String queryPoundage(IInsRncDfnModel model) {
		return (String) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.queryPoundage",model);
	}
    //by zdd02 20190617
	@SuppressWarnings("unchecked")
	@Override
	public List<IInsRncDfnModel> exportInfo(IInsRncDfnModel model) {
		return (List<IInsRncDfnModel>)this.getSqlMapClientTemplate().queryForList("InsRncDfn.exportInfo", model);
	}
	//by zdd02 20190617
	@Override
	public String selectIsOrNobranchname(String branch_id) {
		 return (String) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.selectIsOrNobranchname",branch_id);

	}
	//by zdd02 20190617
		@Override
	public boolean insRncDfnAddzdd(IInsRncDfnModel model) {
		this.getSqlMapClientTemplate().insert("InsRncDfn.insRncDfnAddzdd", model);
		return true;
	}
	//by zdd02 20190617
	@Override
	public IInsRncDfnModel selectCheckInsRncDfnModel(IInsRncDfnModel model) {
			//
		String isPORL =(String) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.isPORL",model);
		if("P".equals(isPORL)) {
		model.setRtype(isPORL);
		}else {
			model.setRtype("");
		}
		return (IInsRncDfnModel) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.selectCheckInsRncDfnModel",model);
	}
	//by zdd02 20190619
		@Override
	public int validateRtype(IInsRncDfnModel model) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.validateRtype",model);
	}
		//by zdd02 20190620
	@Override
	public IInsRncDfnModel checkLimitsBranchid(IInsRncDfnModel model) {
		return (IInsRncDfnModel) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.checkLimitsBranchid",model);	
	}

	@Override
	public List<IInsRncDfnModel> getsysemlist(String branch_id) {
		// TODO Auto-generated method stub
		return (List<IInsRncDfnModel>)this.getSqlMapClientTemplate().queryForList("InsRncDfn.getsysemlist",branch_id);
	}
    /*zdd0724*/
	@Override
	public String isPORL(IInsRncDfnModel model) {
		 return (String) this.getSqlMapClientTemplate().queryForObject("InsRncDfn.isPORL",model);
	}

	@Override
	public void insRncDfnUpdate(IInsRncDfnModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("InsRncDfn.insRncDfnUpdate1",model);
	}
}
