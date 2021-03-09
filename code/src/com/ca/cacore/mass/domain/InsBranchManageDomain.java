package com.ca.cacore.mass.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.mass.dao.IInsBranchManageDao;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.ca.cacore.mass.dao.cpyBranchId.ICpyBranchIdDao;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.utils.stringutil.StringUtil;

@Service
public class InsBranchManageDomain implements IInsBranchManageDomain {
	@Autowired
	private IInsBranchManageDao insBranchDao;
	@Autowired
	private ICommonSeqDao commSeq;
	@Autowired
	private ICpyBranchIdDao cpyBranch;

	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:查询保险公司信息列表
	 */
	public List<ICompanyBranchModel> insBranchQuery(ICompanyBranchModel model) {
		return insBranchDao.insBranchQuery(model);
	}

	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:新增保险公司
	 */
	public boolean insBranchAdd(ICompanyBranchModel model, IUserModel user) {
		if (ValidateHelper.IsNullOrEmpty(model.getBranch_cpyname())) {
			throw new BusinessException("保险公司名称不能为空");
		}
		//by zdd02 20190615
		/*if(ValidateHelper.IsNullOrEmpty(model.getFound_date())) {
			throw new BusinessException("成立日期不能为空");
		}*/
		if(ValidateHelper.IsNullOrEmpty(model.getStatus())) {
			throw new BusinessException("状态不能为空");
		}
		if(ValidateHelper.IsNullOrEmpty(model.getBranch_sort())) {
			throw new BusinessException("新增保险公司分类不能为空");
		}
		if(ValidateHelper.IsNullOrEmpty(model.getBleng_branchid())) {
			throw new BusinessException("中介公司机构不能为空");
		}
		int branch_level = Integer.valueOf(model.getCpy_branch_level());
		if(branch_level == 2){
			if(ValidateHelper.IsNullOrEmpty(model.getProvince_code())) {
				throw new BusinessException("省不能为空");
			}
		}
		if(branch_level == 3){
			if(ValidateHelper.IsNullOrEmpty(model.getProvince_code())) {
				throw new BusinessException("省不能为空");
			}
			if(ValidateHelper.IsNullOrEmpty(model.getCity_code())) {
				throw new BusinessException("市不能为空");
			}
		}
		if(branch_level >= 4){
			if(ValidateHelper.IsNullOrEmpty(model.getProvince_code())) {
				throw new BusinessException("省不能为空");
			}
			if(ValidateHelper.IsNullOrEmpty(model.getCity_code())) {
				throw new BusinessException("市不能为空");
			}
			if(ValidateHelper.IsNullOrEmpty(model.getArea_code())) {
				throw new BusinessException("县不能为空");
			}
		}
//		String id = commSeq.queryCommonSeq("CPY_SEQID");
//		model.setSeq_id(Integer.valueOf(id));
		String branch_cpyid="";
		/* if(model.getBranch_parentid()==null||"".equals(model.getBranch_parentid())) {
			 branch_cpyid= cpyBranch.getPCQ(model);
		 }else {
		 branch_cpyid = cpyBranch.getCpyBranchId(model.getBranch_parentid(), model.getBranch_level());
		 }*/
		String cpy_branch_level = model.getCpy_branch_level();
		if("1".equals(cpy_branch_level)){
			
			Integer max_cpyserno = cpyBranch.queryCpySerno();
			if(max_cpyserno == null){
				model.setCpy_serno("10001");
			}else{
				model.setCpy_serno(String.valueOf(max_cpyserno+1));
			}
		}else{
			String branch_parentid = model.getBranch_parentid();
			String branch_id = branch_parentid.substring(0,2);//公司编码的前两位
			if("2".equals(cpy_branch_level)){
				branch_id = branch_id + model.getProvince_code().substring(0,2);
				model.setBranch_id(branch_id);
				Integer max_cpyserno = cpyBranch.queryCpySerno1(model);
				if(max_cpyserno == null){
					model.setCpy_serno("10000");
				}else{
					model.setCpy_serno(String.valueOf(max_cpyserno+1));
				}
			}else if("3".equals(cpy_branch_level)){
				branch_id = branch_id + model.getCity_code().substring(0,4);
				model.setBranch_id(branch_id);
				Integer max_cpyserno = cpyBranch.queryCpySerno1(model);
				if(max_cpyserno == null){
					model.setCpy_serno("10000");
				}else{
					Integer max_cpyserno1 = cpyBranch.queryCpySerno2(model);
					model.setCpy_serno(String.valueOf(max_cpyserno1+1));
				}
			}else{
				branch_id = branch_id + model.getCity_code().substring(0,4);
				model.setBranch_id(branch_id);
				Integer max_cpyserno = cpyBranch.queryCpySerno2(model);
				if(max_cpyserno == null){
					model.setCpy_serno("10001");
				}else{
					model.setCpy_serno(String.valueOf(max_cpyserno+1));
				}
			}
			model.setBranch_id(branch_id);
		}
		/*else{
			String branch_parentid = model.getBranch_parentid();
			String branch_id = branch_parentid.substring(0,2);//公司编码的前两位
			if(model.getArea_code()!=null && !"".equals(model.getArea_code())){
				branch_id = branch_id + model.getCity_code().substring(0,4);
				Integer max_cpyserno = cpyBranch.queryCpySerno2(model);
				if(max_cpyserno == null){
					model.setCpy_serno("10001");
				}else{
					model.setCpy_serno(String.valueOf(max_cpyserno+1));
				}
			}else if(model.getCity_code()!=null && !"".equals(model.getCity_code())){
				branch_id = branch_id + model.getCity_code().substring(0,4);
			}else if(model.getProvince_code()!=null && !"".equals(model.getProvince_code())){
				branch_id = branch_id + model.getProvince_code().substring(0,2);
			}
			model.setBranch_id(branch_id);
		}*/
		
		/*else{
			String branch_parentid = model.getBranch_parentid();
			String branch_id = "";
			if("2".equals(cpy_branch_level)){
				branch_id = branch_parentid.substring(0,2);//公司编码的前两位
			}else if("3".equals(cpy_branch_level)){
				branch_id = branch_parentid.substring(0,4);//公司编码的前四位
			}else{
				branch_id = branch_parentid.substring(0,6);//公司编码的前六位
			}
			model.setBranch_id(branch_id);
			if("2".equals(cpy_branch_level) || "3".equals(cpy_branch_level)){
				Integer max_cpyserno = cpyBranch.queryCpySerno1(model);
				if(max_cpyserno == null){
					model.setCpy_serno("10001");
				}else{
					model.setCpy_serno(String.valueOf(max_cpyserno+1));
				}
			}else{
				Integer max_cpyserno = cpyBranch.queryCpySerno2(model);
				if(max_cpyserno == null){
					model.setCpy_serno("10001");
				}else{
					model.setCpy_serno(String.valueOf(max_cpyserno+1));
				}
			}
			
		}*/
		
		branch_cpyid= cpyBranch.getPCQ(model);
		model.setBranch_cpyid(branch_cpyid);
		//判断该保险公司是否存在
		int count = insBranchDao.findIsExist(model);
		if(count > 0){
			throw new BusinessException("该保险公司已存在");
		}
		/*model.setStatus("1");*/
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return insBranchDao.insBranchAdd(model, user);
	}

	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:保险公司明细查询
	 */
	public ICompanyBranchModel getInsBranch(ICompanyBranchModel model) {
		return insBranchDao.getInsBranch(model);
	}

	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:保险公司信息修改
	 */
	public boolean insBranchModify(ICompanyBranchModel model, IUserModel user) {
		if (ValidateHelper.IsNullOrEmpty(model.getBranch_cpyname())) {
			throw new BusinessException("保险公司名称不能为空");
		}
		//by zdd02 20190615
		/*if(ValidateHelper.IsNullOrEmpty(model.getFound_date())) {
			throw new BusinessException("成立日期不能为空");
		}*/
		if(ValidateHelper.IsNullOrEmpty(model.getStatus())) {
			throw new BusinessException("状态不能为空");
		}
		model.setModifyUser(user.getEmp_id());
		insBranchDao.insBranchModify(model, user);
		return true;
	}

	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:查询保险公司机构是否已经存在
	 */
	public boolean checkInsbName(ICompanyBranchModel model) {
		ICompanyBranchModel m = insBranchDao.checkInsbName(model);
		if (m != null) {
			/*if (model.getBranch_name().equals(m.getBranch_name()) && !model.getSeq_id().equals(m.getSeq_id())) {
				return false;
			}*/
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return List<IInsBranchVOModel>
	 * @description:2014-2-25 获取保险公司代码和名称--by张晨用于投诉录入
	 */
	public List<ICompanyBranchModel> getAllInsBranch() {
		return insBranchDao.getAllInsBranch();
	}

	/**
	 * @author GCH
	 * @param model
	 * @return String
	 * @description:生成保险公司机构代码："11"+流水号（8位），共10位
	 */
	public String createInsbranchId() {
		String seq_code = commSeq.queryCommonSeq("seq_id");
		try {
			// 调用现成的方法对取出的识别码进行8位补0
			seq_code = StringUtil.alignLeft(seq_code, 8);
		} catch (Message e) {
			e.printStackTrace();
		}
		String insBranchId = "11" + seq_code;
		return insBranchId;
	}

	public List<ITree> queryBranchTree(Map<String,Object> param) {
		return insBranchDao.queryBranchTree(param);
	}

	@Override
	public int checkpower0626(String emp_id) {
		
		return insBranchDao.checkpower0626(emp_id);
	}
    //zddxiu
	@Override
	public List<ITree> querySalesFirmBranchTree2() {
		return insBranchDao.querySalesFirmBranchTree2();
	}

	@Override
	public List<ICompanyBranchModel> getbranchSort() {
		// TODO Auto-generated method stub
		return insBranchDao.getbranchSort();
	}

	@Override
	public String getfirstLeavelsort(String brand_id) {
		// TODO Auto-generated method stub
		return insBranchDao.getfirstLeavelsort(brand_id);
	}

	@Override
	public List<ITree> queryBranchTreeZ(IBranchModel model) {
		// TODO Auto-generated method stub
		return insBranchDao.queryBranchTreeZ(model);
	}

	@Override
	public List<String> getIsPOrLZ() {
		// TODO Auto-generated method stub
		return insBranchDao.getIsPOrLZ();
	}

	@Override
	public List<String> queryIdbyUserName(String userName) {

		return insBranchDao.queryIdbyUserName(userName);
	}
}
