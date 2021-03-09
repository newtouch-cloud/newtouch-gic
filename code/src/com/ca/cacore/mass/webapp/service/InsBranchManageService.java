package com.ca.cacore.mass.webapp.service;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.mass.domain.IInsBranchManageDomain;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.menumgmt.service.IMenuMgmtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InsBranchManageService implements IInsBranchManageService {
	@Autowired
	private IInsBranchManageDomain insBranchDomain;
	@Autowired
	private IMenuMgmtService menuMgmtService;
	/**
	 * @author guochunhua
	 * @param model
	 * @return
	 * @description:保险公司信息列表查询
	 */
	public ReturnMsg insBranchQuery(ICompanyBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ICompanyBranchModel> relist = insBranchDomain.insBranchQuery(model);
		returnMsg.setParameter("user_branch", "");
		returnMsg.setDataList(TransHelper.list2MapList(relist));
		return returnMsg;
	}

	/**
	 * @author guochunhua
	 * @param model
	 * @param user
	 * @return ReturnMsg
	 * @description:新增保险公司
	 */
	public ReturnMsg insBranchAdd(ICompanyBranchModel model, IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			boolean flag = insBranchDomain.insBranchAdd(model, user);
			if (flag) {
				returnMsg.setDataTable(TransHelper.obj2map(model));
				returnMsg.setSuccessMessage("保存成功");
			}
		} catch (BusinessException be) {
			returnMsg.setFailMessage(be.getMessage(), true);
		}
		return returnMsg;
	}

	/**
	 * @author guochunhua
	 * @param model
	 * @return
	 * @description:查询保险公司信息
	 */
	public ReturnMsg getInsBranch(ICompanyBranchModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		ICompanyBranchModel m = insBranchDomain.getInsBranch(model);
		returnMsg.setDataTable(TransHelper.obj2map(m));
		return returnMsg;
	}

	/**
	 * @author guochunhua
	 * @param model
	 * @param user
	 * @return
	 * @description:修改保险公司信息
	 */
	public ReturnMsg insBranchModify(ICompanyBranchModel model, IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			boolean flag = insBranchDomain.insBranchModify(model, user);
			if (flag) {
				ICompanyBranchModel m = insBranchDomain.getInsBranch(model);
				returnMsg.setDataTable(TransHelper.obj2map(m));
				returnMsg.setSuccessMessage("修改成功");
			}
		} catch (BusinessException be) {
			returnMsg.setFailMessage(be.getMessage(), true);
		}
		return returnMsg;
	}

	/**
	 * @author guochunhua
	 * @param model
	 * @return
	 * @description:检查保险公司名称是否已经存在
	 */
	public String checkInsbName(ICompanyBranchModel model) {
		String returnInfo = "";
		returnInfo = returnInfo + insBranchDomain.checkInsbName(model);
		return returnInfo;
	}

	/**
	 * 
	 * @return List<IInsBranchVOModel>
	 * @description:2014-2-25 获取保险公司代码和名称--by张晨用于投诉录入
	 */
	public List<ICompanyBranchModel> getAllInsBranch() {
		return insBranchDomain.getAllInsBranch();
	}

	/**
	 * 
	 * @return List<IInsBranchVOModel>
	 * @description:2017-11-30 获取保险公司代码和名称
	 */
	public ReturnMsg queryBranchTree(Map<String,Object> param) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ITree> list = insBranchDomain.queryBranchTree(param);
		System.out.println(list);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
    //zddxiu
	@Override
	public int checkpower0626(String emp_id) {
		
		return insBranchDomain.checkpower0626(emp_id);
	}

	@Override
	public ReturnMsg querySalesFirmBranchTree2() {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ITree> list = insBranchDomain.querySalesFirmBranchTree2();
		System.out.println(list);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
    //zddxiu
	@Override
	public List<ICompanyBranchModel> getbranchSort() {
		// TODO Auto-generated method stub
		return insBranchDomain.getbranchSort();
	}
    //zddxiu
	@Override
	public String getfirstLeavelsort(String brand_id) {
		// TODO Auto-generated method stub
		return insBranchDomain.getfirstLeavelsort( brand_id);
	}

	@Override
	public ReturnMsg queryBranchTreeZ(IBranchModel model) {
		List<ITree> list =insBranchDomain.queryBranchTreeZ(model);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public List<String> getIsPOrLZ() {
		// TODO Auto-generated method stub
		return insBranchDomain.getIsPOrLZ();
	}

	@Override
	public ReturnMsg getInsBranch1(ICompanyBranchModel companyModel) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();
		ICompanyBranchModel m = insBranchDomain.getInsBranch(companyModel);
		m.setBranch_id(companyModel.getBleng_branchid());
		returnMsg.setDataTable(TransHelper.obj2map(m));
		return returnMsg;
	}

	@Override
	public List<String> queryIdbyUserName(String userName) {

		return insBranchDomain.queryIdbyUserName(userName);
	}


}
