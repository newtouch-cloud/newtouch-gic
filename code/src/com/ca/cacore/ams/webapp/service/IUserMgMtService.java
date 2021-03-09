package com.ca.cacore.ams.webapp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;


public interface IUserMgMtService {

	public IUserMgMtVOModel queryOptPathInfo(IUserMgMtVOModel model);
	
	public String checkPassWordIsOverDue(IUserMgMtVOModel model);
	
	public ReturnMsg updateOptPathInfo(IUserMgMtVOModel model);
	
	public ReturnMsg updatePassWord(IUserMgMtVOModel model);
	
	public ReturnMsg queryUserInfo(IUserMgMtVOModel model);
	
	public ReturnMsg addUserInfo(IUserMgMtVOModel model);
	
	public ReturnMsg addOptPathInfo(IUserMgMtVOModel model);
	
	public ReturnMsg addOperatorRolesInfo(IUserMgMtVOModel model);
	
	public ReturnMsg addDataAuthInfo(IUserMgMtVOModel model);
	
	public List<IUserMgMtVOModel> queryBranchList(IUserMgMtVOModel model);

	 
}
