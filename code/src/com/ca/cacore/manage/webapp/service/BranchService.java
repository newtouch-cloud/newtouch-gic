package com.ca.cacore.manage.webapp.service;

import com.ca.cacore.manage.domain.branch.IBranchDomain;
import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.vo.BusinessLicenseHisModel;
import com.ca.cacore.manage.model.vo.IBranchStatusHisVOModel;
import com.ca.cacore.manage.model.vo.IBranchVOModel;
import com.ca.cacore.csm.domain.CustomerAndCustomerContact.ICustomerDomain;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.dao.T_Data_AuthDao;
import com.newtouch.core.rightsmgmt.dao.T_OperatorDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BranchService implements IBranchService {
	@Autowired
	private IBranchDomain branchDomain;
	@Autowired
	private T_OperatorDao optDao;
	@Autowired
	private T_Data_AuthDao tdaDao;

	@Autowired
	private ICustomerDomain customerDomain;

	/**
	 * 查询所有机构信息
	 */
	@Override
	public ReturnMsg queryAllBranch(IBranchModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IBranchVOModel> relist = branchDomain.queryAllBranch(model);
		List<IBranchVOModel> relists=new ArrayList<IBranchVOModel>();
		//zddxiu
		/*for (IBranchVOModel branchViewModel : relist) {
			if (branchViewModel.getArea_code() != null && branchViewModel.getProvince_code() != null
					&& branchViewModel.getCity_code() != null) {
				branchViewModel.setArea(branchDomain.getCityByCode(branchViewModel.getArea_code()).getArea());
				branchViewModel.setProvince(branchDomain.getCityByCode(branchViewModel.getProvince_code()).getArea());
				branchViewModel.setCity(branchDomain.getCityByCode(branchViewModel.getCity_code()).getArea());
			}
			relists.add(branchViewModel);
		}*/
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}

	@Override
	public List<IBranchVOModel> exportBranchInfo(IBranchModel model) {
		List<IBranchVOModel> list = branchDomain.exportBranchInfo(model);
		
		return list;
	}

	/**
	 * 查看详细功能
	 */
	@Override
	public ReturnMsg getByBranchView(IBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IBranchVOModel branchViewModel = branchDomain.getBranchView(model);
		/*if (branchViewModel.getArea_code() != null && branchViewModel.getProvince_code() != null
				&& branchViewModel.getCity_code() != null) {
			branchViewModel.setArea(branchDomain.getCityByCode(branchViewModel.getArea_code()).getArea());
			branchViewModel.setProvince(branchDomain.getCityByCode(branchViewModel.getProvince_code()).getArea());
			branchViewModel.setCity(branchDomain.getCityByCode(branchViewModel.getCity_code()).getArea());
		}*/ //zddxiu
		returnMsg.setDataTable(TransHelper.obj2map(branchViewModel));
		return returnMsg;
	}

	/**
	 * 添加时获取上级信息功能
	 */
	@Override
	public ReturnMsg getBranchAddVO(IBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IBranchVOModel voModel = branchDomain.getBranchAddVO(model);
		returnMsg.setDataTable(TransHelper.obj2map(voModel));
		return returnMsg;
	}

	/**
	 * 添加信息保存功能
	 */
	@Override
	public ReturnMsg addBranch(IBranchModel branchModel,IUserModel userModel) throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();
		Map<String, Object> optMap = new HashMap<String, Object>();
		optMap.put("opt_no", userModel.getEmp_id());
		try {// 权限树查询更改，无需添加到权限表
				branchDomain.addBranch(branchModel, userModel);
				returnMsg.setSuccessMessage("保存成功");
				returnMsg.setParameter("flag", "1");
			
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		return returnMsg;
	}

	/**
	 * 获取修改跳转页面信息功能
	 */
	@Override
	public ReturnMsg getByBranchModifyVO(IBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IBranchVOModel branchVOModel = branchDomain.getBranchView(model);
		//by zdd 20190611 start
		/*if ( branchVOModel.getProvince_code() != null
				&& branchVOModel.getCity_code() != null) {
			branchVOModel.setProvince(branchDomain.getCityByCode(branchVOModel.getProvince_code()).getArea());
			branchVOModel.setCity(branchDomain.getCityByCode(branchVOModel.getCity_code()).getArea());
			branchVOModel.setArea(branchDomain.getCityByCode(branchVOModel.getArea_code()).getArea());
		}*/
		//by zdd 20190611 end
		returnMsg.setDataTable(TransHelper.obj2map(branchVOModel));
		return returnMsg;
	}
	
	/**
	 * 获取新增下级机构跳转页面信息功能
	 */
	@Override
	public ReturnMsg getByBranchAddVO(IBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IBranchVOModel branchVOModel = branchDomain.queryBranch(model);
		branchVOModel.setParent_branch_id(branchVOModel.getBranch_id());
		branchVOModel.setParent_branch_name(branchVOModel.getBranch_name());
		branchVOModel.setDelegate(null);
		branchVOModel.setBranch_name(null);
		branchVOModel.setBranch_abbr(null);
		branchVOModel.setBuslicensefounddate(null);
		branchVOModel.setChannelcode(null);
		branchVOModel.setProvince(null);
		branchVOModel.setCity(null);
		branchVOModel.setArea(null);
		branchVOModel.setFound_date(null);
		branchVOModel.setFax(null);
		branchVOModel.setAddress(null);
		branchVOModel.setZip(null);
		branchVOModel.setTelephone(null);
		branchVOModel.setPermitcode(null);
		branchVOModel.setPermitarea(null);
		branchVOModel.setEmail(null);
		branchVOModel.setUnifiedSocialCreditNO(null);
		String branch_id = branchVOModel.getBranch_id()+"00";
		if(branch_id.length() > 8 ) {
			returnMsg.setFailMessage("下级机构不可再增加");
		}else {
			String branchId = branchDomain.branchIdSelect(branchVOModel.getBranch_id());

			if(branchId==null||branchId.equals("")) {
				branchVOModel.setBranch_id(branch_id);
			}else {
				if(branchId.length()<4){
					branchId = "000"+branchId;
				}
				branchVOModel.setBranch_id(branchId);
			}
			returnMsg.setDataTable(TransHelper.obj2map(branchVOModel));
		
		}
	return returnMsg;
	}


	/**
	 * 修改信息保存功能
	 */
	@Override
	public ReturnMsg updateBranch(IBranchModel model, IUserModel userModel) throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			//if-else by zdd
			/*if(model.getBranch_abbr()==null||"".equals(model.getBranch_abbr())) {
				returnMsg.setFailMessage("机构简称不能为空！");
				IBranchVOModel branchViewModel = branchDomain.getBranchView(model);
				returnMsg.setDataTable(TransHelper.obj2map(branchViewModel));
			}else {*/
				branchDomain.updateBranch(model, userModel);
				IBranchVOModel branchViewModel = branchDomain.getBranchView(model);
				returnMsg.setDataTable(TransHelper.obj2map(branchViewModel));
				returnMsg.setSuccessMessage("修改成功");
			/*}*/
			
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		return returnMsg;
	}

	/**
	 * 更改机构状态 *
	 */
	@Override
	public ReturnMsg updateStatus(IBranchModel model, IUserModel user) throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();
		IBranchModel model0 = branchDomain.getByBranch(model);
		try {
			/*
			 * 判断该机构下是否拥有人员 如果团队下存在人员，能不能注销该团队 否则就可以注销该团队
			 */
			ICustomerVOModel model1 = new CustomerVOModel();
			model1.setBranch_id(model0.getBranch_id());
			List<ICustomerVOModel> modelHas = branchDomain.isHasPerson(model1);
			// 用于存放人员ID
			List<String> sum = new ArrayList<String>();
			for (ICustomerVOModel list : modelHas) {
				if (list.getCustomer_id() != null) {
					sum.add(list.getCustomer_id());
				}
			}
			// 该团队下不存在人员，可以进行注销团队操作
			if (sum.size() == 0) {
				branchDomain.updateStatus(model, user);
				if ("1".equals(model0.getStatus())) {
					returnMsg.setSuccessMessage("注销成功");
				} else {
					returnMsg.setSuccessMessage("恢复成功");
				}
				// 该团队下存在人员不能注销该团队
			} else {
				returnMsg.setFailMessage("团队下存在人员，不能注销该团队");
			}
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		return returnMsg;
	}

	@Override
	public int checkRepeatReturnInt(IBranchModel model) {
		return branchDomain.checkRepeatReturnInt(model);
	}

	/**
	 * 2013-12-18新添加方法 BY 张晨 校验前台输入的数据是否已存在
	 * 
	 * @param model
	 * @return Integer
	 */
	@Override
	public Integer queryForVerifyData(IBranchModel model) {
		return branchDomain.queryForVerifyData(model);
	}

	@Override
	public ReturnMsg queryAllBranchStatusHis(IBranchStatusHisVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IBranchStatusHisVOModel> list = branchDomain.queryAllBranchStatusHis(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	@Override
	public ReturnMsg queryBranchTree(IBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ITree> list = branchDomain.queryBranchTree(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	@Override
	public ReturnMsg queryBranchTreeExceptCentral(IBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ITree> list = branchDomain.queryBranchTreeExceptCentral(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/**
	 * 工作流使用
	 */
	@Override
	public ReturnMsg queryBranchTree4WF(IBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ITree> list = branchDomain.queryBranchTree4WF(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/**
	 * 查询省 孙豪
	 */
	@Override
	public ReturnMsg queryProvince() {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IBranchModel> list = branchDomain.queryProvince();
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/**
	 * 查询市 孙豪
	 */
	@Override
	public ReturnMsg queryCity(String parent_code) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IBranchModel> list = branchDomain.queryCity(parent_code);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/**
	 * 查询县 孙豪
	 */
	@Override
	public ReturnMsg queryArea(String parent_code) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IBranchModel> list = branchDomain.queryArea(parent_code);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	public ReturnMsg queryBranchTreeTeam(IBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ITree> list = branchDomain.queryBranchTreeTeam(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	/**
	 * 插入机构信息
	 * by zdd 20190611
	 */
	@Override
	public String insertBranchzdd(IBranchModel model, IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		Map<String, Object> optMap = new HashMap<String, Object>();
		optMap.put("opt_no", user.getEmp_id());
		String msgInfo="";
		try { 
			
			/*if (model.getArea() != null && model.getProvince() != null
					&& model.getCity() != null) {
				
				model.setArea_code(branchDomain.getCityByNamez(model.getArea()));
				model.setProvince_code(branchDomain.getCityByNamez(model.getProvince()));
				model.setCity_code(branchDomain.getCityByNamez(model.getCity()));
				
			}*/
			   //zddxiu
				String msg = branchDomain.insertBranchZdd(model, user);
				if("sucess".equals(msg)) {
					msgInfo="sucess";
				}
			
		} catch (BusinessException e) {
			msgInfo=e.getMessage();
		}
		return msgInfo;
	}
    /**
     * by zdd 20190611
     */
	@Override
	public String importbranchIMgORPdf(IBranchModel model) {
		
		return branchDomain.importbranchIMgORPdf(model);
	}
	
	
	/**
	 * 获取省/市/县
	 * by zdd 20190613 
	 */
	@Override
    public String getPCAByCode(String code) {

		String PCAname=	branchDomain.getCityByCode(code).getArea();
			
		return PCAname;
    }
	/**
	 * 获取机构层级
	 * zdd  20190613
	 * @return
	 */
	@Override
	public List<String> getBranchLeavel() {
		List<String> models=branchDomain.getBranchLeavel();
		return models;
		
	}
	@Override
	public List<String> getzzstuts(String str){
		return branchDomain.getzzstuts(str);
	}

	@Override
	public String insertbranchList(IBranchModel model) {
		branchDomain.insertbranchList( model);
		return null;
	}

	@Override
	public ReturnMsg businessLicenseHis(int parseInt) {
		ReturnMsg msg = new ReturnMsg();
		List<BusinessLicenseHisModel> relist = branchDomain.businessLicenseHis(parseInt);
		for (BusinessLicenseHisModel businessLicenseHisModel : relist) {
			String path = businessLicenseHisModel.getLicensepath();
			String fileName = path.substring(path.lastIndexOf("/")+1);
			String fileName1 = fileName.substring(0,fileName.length()-4);
			businessLicenseHisModel.setFileName(fileName1);
		}
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	

	@Override
	public ReturnMsg upbusinessLicenseHis(BusinessLicenseHisModel model) {
		String bo= branchDomain.upbusinessLicenseHis(model);
		ReturnMsg msg = new ReturnMsg();
		if("1".equals(bo)) {
			msg.setSuccessMessage("删除成功！");
		}
		List<BusinessLicenseHisModel> relist = branchDomain.businessLicenseHis(model.getSeq_id());
		for (BusinessLicenseHisModel businessLicenseHisModel : relist) {
			String path = businessLicenseHisModel.getLicensepath();
			String fileName = path.substring(path.lastIndexOf("/")+1);
			String fileName1 = fileName.substring(0,fileName.length()-4);
			businessLicenseHisModel.setFileName(fileName1);
		}
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	
	public static void main(String[] args) {
		String path = "/E:/svn-download/touch0731/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/touchnew/uploadFile/武器 - 副本.JPG";
		String fileName = path.substring(path.lastIndexOf("/")+1);
		System.out.println(fileName+fileName.length());
		String ss =fileName.substring(0,fileName.length()-4);
		System.out.println(ss);
	}
	
	/**
	 * 添加下级机构信息保存功能
	 */
	@Override
	public ReturnMsg addBranchJunior(IBranchModel branchModel, IUserModel userModel) throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();
		Map<String, Object> optMap = new HashMap<String, Object>();
		optMap.put("opt_no", userModel.getEmp_id());
		try {// 权限树查询更改，无需添加到权限表
				branchDomain.addBranchJunior(branchModel, userModel);
				returnMsg.setSuccessMessage("保存成功");
				returnMsg.setParameter("flag", "1");
			
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		return returnMsg;
	}
}
