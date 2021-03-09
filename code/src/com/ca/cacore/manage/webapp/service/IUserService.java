package com.ca.cacore.manage.webapp.service;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.IUserPortraitModel;
import com.ca.cacore.manage.model.bo.UserAuthsModel;
import com.ca.cacore.manage.model.vo.UserAuths_Role_BranchModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IUserService {

	public ReturnMsg queryUser(IUserModel model);
	public ReturnMsg getUser(IUserModel model); 
	public ReturnMsg addUser(IUserModel model) throws BusinessException;
	public ReturnMsg updateUser(IUserModel model) throws BusinessException;	
	public ReturnMsg queryUserRole(UserAuths_Role_BranchModel model);
	public ReturnMsg queryUserRoles(UserAuths_Role_BranchModel model);
	public ReturnMsg queryUserAuthsVO(IUserModel model);
	public List<String> queryBranchTree(UserAuthsModel model);
	/**
	 * 查询用户和权限信息,author:liu_zheng
	 * @param user
	 * @return
	 */
	public ReturnMsg getUserAndAuths(IUserModel user);
	/**
	 * 保存用户的权限author:liuzheng
	 * @param user
	 * @param roles
	 * @return
	 */
	public ReturnMsg updateUserAuths(IUserModel user,String [] roles,IUserModel userModel);
	/**
	 * 保存用户头像信息到数据库 author:张晨
	 * @param model
	 * @return
	 */
	public ReturnMsg addUserPortrait(IUserPortraitModel model);
	
	/**
	 * author:张晨
	 * 他通过用户号码查找用户头像保存路径
	 * @param model
	 * @return
	 */
	public String getPortraitPath(IUserModel model);
}

