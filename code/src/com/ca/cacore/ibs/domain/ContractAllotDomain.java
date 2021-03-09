package com.ca.cacore.ibs.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IContractAllotDao;
import com.ca.cacore.ibs.model.vo.ContractAllotHisVOModel;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;

/**
* @since:    2013年12月23日   
* @author    WanBo
* @description: 保单分配Domain层
*/
@Service
public class ContractAllotDomain implements IContractAllotDomain{
	@Autowired private IContractAllotDao contractAllotDao;
	
	/**
	 * 查询服务人员所有的保单信息列表
	 * @param model
	 * @return List<String>
	 * @description: 根据分配前服务人员代码查询服务人员所有的保单信息列表
	 */
	@Override
	public List<String> queryContList(IContractAllotHisVOModel model) {
		List<String> list=new ArrayList<String>();
		List<IContractAllotHisVOModel> policyModels=contractAllotDao.queryContList(model);//调用dao层查询服务人员所有保单
		for (IContractAllotHisVOModel iContractAllotHisVOModel : policyModels) {
			String str="{policy_id:'"+iContractAllotHisVOModel.getPolicy_id()+//保单ID
					"',bef_service_name:'"+iContractAllotHisVOModel.getBef_service_name()+//分配前服务人员
					"',branch_id:'"+iContractAllotHisVOModel.getBranch_id()+//机构代码
					"',branch_name:'"+iContractAllotHisVOModel.getBranch_name()+//机构名称
					"',insBranch_name:'"+iContractAllotHisVOModel.getInsBranch_name()+//保险公司名称
					"',policy_code:'"+iContractAllotHisVOModel.getPolicy_code()+//保单号
					"',holder_name:'"+iContractAllotHisVOModel.getHolder_name()+//投保人
					"',product_name:'"+iContractAllotHisVOModel.getProduct_name()+//险种
					"',amount:'"+iContractAllotHisVOModel.getAmount()+//保额
					"',period_prem:'"+iContractAllotHisVOModel.getPeriod_prem()+//保费
					"',validate_date:'"+iContractAllotHisVOModel.getValidate_date()+"'}";//保单生效日期
			list.add(str);
		}	
		return list;
	}
	/**
	 * 更新保单服务人员
	 * @param model
	 * @param user
	 * @return Boolean
	 * @description: 更新保单服务人员
	 */
	@Override
	public Boolean updateContS(IContractAllotHisVOModel model,IUserModel user) {
		boolean upPolicy=true;
		for(int i=0; i<model.getPolicy_ids().length; i++){
			model.setPolicy_id(model.getPolicy_ids()[i]);//保单ID
			model.setModifyUser(user.getUserName());//修改人
			upPolicy=contractAllotDao.updateContS(model, user);//调用dao层更新保单服务人员
		}
		return upPolicy;
	}
	/**
	 * 查询分配前服务人员以及机构,保单等信息
	 * @param model
	 * @return List<String>
	 * @description: 根据保单号查询分配前服务人员以及机构,保单等信息
	 */
	@Override
	public List<String> queryContInfo(
			IContractAllotHisVOModel model) {
		List<String> list=new ArrayList<String>();
		IContractAllotHisVOModel policyModels=contractAllotDao.queryContInfo(model);//调用dao层查询服务人员,机构,保单信息
		String str="{policy_id:'"+policyModels.getPolicy_id()+//保单ID
				"',bef_service_id:'"+policyModels.getBef_service_id()+//分配前服务人员代码
				"',bef_service_name:'"+policyModels.getBef_service_name()+//分配前服务人员姓名
				"',branch_id:'"+policyModels.getBranch_id()+//机构代码
				"',branch_name:'"+policyModels.getBranch_name()+//机构名称
				"',bef_holder_name:'"+policyModels.getBef_holder_name()+//投保人
				"',insBranch_name:'"+policyModels.getInsBranch_name()+//保险公司名称
				"',policy_code:'"+policyModels.getPolicy_code()+//保单号
				"',holder_name:'"+policyModels.getHolder_name()+//投保人
				"',product_name:'"+policyModels.getProduct_name()+//险种
				"',amount:'"+policyModels.getAmount()+//保额
				"',period_prem:'"+policyModels.getPeriod_prem()+//保费
				"',validate_date:'"+policyModels.getValidate_date()+"'}";//保单生效日期
		list.add(str);
		return list;
	}
	/**
	 * 通过分配后服务人员代码查询姓名
	 * @param model
	 * @return IContractAllotHisVOModel
	 * @description: 通过分配后服务人员代码查询姓名
	 */
	@Override
	public IContractAllotHisVOModel queryAftSName(IContractAllotHisVOModel model) {
		return contractAllotDao.queryAftSName(model);
	}
	/**
	 * 根据分配前服务人员代码查询服务人员代码
	 * @param model
	 * @return IContractAllotHisVOModel
	 * @description: 根据分配前服务人员代码查询服务人员代码 
	 */
	@Override
	public IContractAllotHisVOModel queryServiceId(IContractAllotHisVOModel model) {
		return contractAllotDao.queryServiceId(model);
	}
	/**
	 * 根据保单号查询保单号
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 根据保单号查询保单号
	 */
	@Override
	public IContractAllotHisVOModel queryPCode(
			IContractAllotHisVOModel model) {
		return contractAllotDao.queryPCode(model);
	}
	/** 
	* 增加服务人员变更历史记录
	* @param model
	* @param user
	* @return Boolean
	* @description:增加服务人员变更历史记录
	*/
	public Boolean addContHis(IContractAllotHisVOModel model, IUserModel user){
		boolean add=true;
		IContractAllotHisVOModel hisModel=new ContractAllotHisVOModel();
		hisModel.setBranch_id(model.getBranch_id());//机构代码
		hisModel.setInsBranch_id(model.getInsBranch_id());//保险公司机构代码
		hisModel.setBef_service_id(model.getBef_service_id());//分配前服务人员代码
		hisModel.setAft_service_id(model.getAft_service_id());//分配后服务人员代码
		hisModel.setRemark("分配人员变更操作");//备注
		hisModel.setCreateUser(user.getUserName());//创建人
		hisModel.setModifyUser(user.getUserName());//修改人
		for(int i=0; i<model.getPolicy_ids().length; i++){
			hisModel.setPolicy_id(model.getPolicy_ids()[i]);//保单号
			add= contractAllotDao.addContHis(hisModel, user);//调用dao层增加历史记录
		}
		return add;
	}

}
