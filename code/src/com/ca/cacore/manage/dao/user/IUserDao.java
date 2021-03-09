package com.ca.cacore.manage.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.manage.model.bo.IUserAuthsModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.IUserPortraitModel;
import com.ca.cacore.manage.model.bo.UserAuthsModel;
import com.ca.cacore.manage.model.vo.BranchTree;
import com.ca.cacore.manage.model.vo.UserAuthsVO;
import com.ca.cacore.manage.model.vo.UserAuths_Role_BranchModel;
@Repository
public interface IUserDao {
 
		public List<IUserModel> queryUser(IUserModel model);
		public IUserModel getUser(IUserModel model);
		public IUserModel addUser(IUserModel model);
		public boolean updateUser(IUserModel model);
		public List<UserAuths_Role_BranchModel> queryUserRole(UserAuths_Role_BranchModel model);
		public List<UserAuths_Role_BranchModel> queryUserRoles(UserAuths_Role_BranchModel model);
		public List<UserAuthsVO> queryRoleSelected(IUserModel model);
		public List<BranchTree> queryBranchTree(UserAuthsModel model);
		public boolean deleteUserAuths(UserAuthsModel model);
		public List<UserAuthsVO> getUserAuths(IUserModel user);
		/**
		 * 删除用户的权限信息author:liuzheng
		 * @param user
		 */
		public void deleteUserAuths(IUserModel user);
		/**
		 * 添加用户的权限信息author:liu_zheng
		 * @param model
		 * @return
		 */
		public IUserAuthsModel addUserAuths(IUserAuthsModel model);
		/**
		 * author:张晨
		 * 保存用户头像信息到数据库 
		 * @param model
		 * @return
		 */
		public boolean addUserPortrait(IUserPortraitModel model);
		/**
		 * author:张晨
		 * 检测用户是否已经保存头像
		 * @param model
		 * @return
		 */
		public boolean checkUserPortrait(IUserPortraitModel model);
		/**
		 * author:张晨
		 * 如果用户已经存在头像则执行更新操作
		 * @param model
		 * @return
		 */
		public boolean updateUserPortrait(IUserPortraitModel model);
		
		/**
		 * author:张晨
		 * 他通过用户号码查找用户头像保存路径
		 * @param model
		 * @return
		 */
		public String getPortraitPath(IUserModel model);
		
		/**
		 * author:张晨
		 * 他通过用户号码查找绑定的核心系统用户名和密码
		 * @param model
		 * @return
		 */
		public IUserModel getCoreUser(IUserModel model);
}
