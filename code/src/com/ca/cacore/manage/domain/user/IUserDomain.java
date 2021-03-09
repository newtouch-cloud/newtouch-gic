package com.ca.cacore.manage.domain.user;

import java.util.List;









import com.ca.cacore.manage.model.bo.IUserAuthsModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.IUserPortraitModel;
import com.ca.cacore.manage.model.bo.UserAuthsModel;
import com.ca.cacore.manage.model.vo.BranchTree;
import com.ca.cacore.manage.model.vo.UserAuthsVO;
import com.ca.cacore.manage.model.vo.UserAuths_Role_BranchModel;

public interface IUserDomain {
	 
	public List<IUserModel> queryUser(IUserModel model);
	public IUserModel getUser(IUserModel model);
	public IUserModel addUser(IUserModel model);
	public boolean updateUser(IUserModel model);
	public List<UserAuths_Role_BranchModel> queryUserRole(UserAuths_Role_BranchModel model);
	public List<UserAuths_Role_BranchModel> queryUserRoles(UserAuths_Role_BranchModel model);
	public List<UserAuthsVO> queryUserAuthsVO(IUserModel model);
	public List<String> queryBranchTreeToJson(UserAuthsModel model);
	public boolean deleteUserAuths(BranchTree model);
	/**
	 * author:liu zheng
	 * 查询当前用户的权限信息
	 * @param user
	 * @return
	 */
	public List<UserAuthsVO> getUserAuths(IUserModel user);
	/**
	 * 删除用户权限信息
	 * @param user
	 */
	public void deleteUserAuthor(IUserModel user);
	/**
	 * 插入用户权限信息
	 * @param model
	 */
	public IUserAuthsModel addUserAuthor(IUserAuthsModel model);
	
	/**
	 * author:张晨
	 * 保存用户头像信息到数据库 
	 * @param model
	 * @return
	 */
	public void addUserPortrait(IUserPortraitModel model);
	
	/**
	 * author:张晨
	 * 他通过用户号码查找用户头像保存路径
	 * @param model
	 * @return
	 */
	public String getPortraitPath(IUserModel model);
}
