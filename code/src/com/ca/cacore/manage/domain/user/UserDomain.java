package com.ca.cacore.manage.domain.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.user.IUserDao;
import com.ca.cacore.manage.model.bo.IUserAuthsModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.IUserPortraitModel;
import com.ca.cacore.manage.model.bo.UserAuthsModel;
import com.ca.cacore.manage.model.vo.BranchTree;
import com.ca.cacore.manage.model.vo.UserAuthsVO;
import com.ca.cacore.manage.model.vo.UserAuths_Role_BranchModel;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 
 * @author 吴思波
 * @since 2013-11-11
 * @discrible 用户管理Domain层
 */
@Service
public class UserDomain extends ServerBase implements IUserDomain{

		@Autowired private IUserDao userDao;
		
		/**
		 * 查询user
		 * @param IUserModel
		 * @return ReturnMsg
		 * @throws Exception
		 */
		public List<IUserModel> queryUser(IUserModel model) {
			return userDao.queryUser(model);
		}

		/**
		 * 获取user
		 * @param IUserModel
		 * @return ReturnMsg
		 * @throws Exception
		 */
		public IUserModel getUser(IUserModel model) {
			return userDao.getUser(model);
		}
		
		/**
		 * 添加user
		 * @param IUserModel
		 * @return ReturnMsg
		 * @throws Exception
		 */
		public IUserModel addUser(IUserModel model){
		    return userDao.addUser(model);
		}

		/**
		 * 修改保存User
		 * @param req
		 * @param res
		 * @return
		 * @throws Exception
		 */
		public boolean updateUser(IUserModel model) {
			return userDao.updateUser(model);
		}

		@Override
		public List<UserAuths_Role_BranchModel> queryUserRole(UserAuths_Role_BranchModel model) {
			return userDao.queryUserRole(model);
		}
		@Override
		public List<UserAuths_Role_BranchModel> queryUserRoles(UserAuths_Role_BranchModel model) {
			return userDao.queryUserRoles(model);
		}

		@Override
		public List<UserAuthsVO> queryUserAuthsVO(IUserModel model) {
			return userDao.queryRoleSelected(model);
		}
		
		/**
		 * 将机构数据转换成json数据
		 */
		public List<String> queryBranchTreeToJson(UserAuthsModel model){
			List<BranchTree> list  =userDao.queryBranchTree(model);
			String root="";//根节点
			String childnode="";//子节点
			List<String> lstTree = new ArrayList<String>(); 
			
			for(int i =0 ;i<list.size();i++){
				BranchTree bt = list.get(i);
				if(bt.getBranch_parentid()=="null"||"null".equals(bt.getBranch_parentid()) || bt.getBranch_parentid()==null){
					root="{ 'id':'"+bt.getBranch_id()+"', 'pId':0, name:'"+bt.getBranch_name()+"', 'isChecked':false }"; 
					lstTree.add(root);
				}else {
					childnode="{ 'id':'"+bt.getBranch_id()+"', 'pId':'"+bt.getBranch_parentid()+"', name:'"+bt.getBranch_name()+"', 'isChecked':"+bt.getSelected()+"}"; 
					lstTree.add(childnode);
				}
			}
			return lstTree;
		}

		@Override
		public boolean deleteUserAuths(BranchTree model) {
			
			return false;
		}

		@Override
		public List<UserAuthsVO> getUserAuths(IUserModel user) {
			return userDao.getUserAuths(user);
		}

		@Override
		public void deleteUserAuthor(IUserModel user) {
			userDao.deleteUserAuths(user);
		}

		@Override
		public IUserAuthsModel addUserAuthor(IUserAuthsModel model) {
			return userDao.addUserAuths(model);
		}
		/**
		 * author:张晨
		 * 先要查询用户是否已经上传头像,若没有则保存用户头像信息到数据库 ,已有则进行update操作
		 * @param model
		 * @return
		 */
		@Override
		public void addUserPortrait(IUserPortraitModel model) {
			boolean boo = userDao.checkUserPortrait(model);
			if(boo){
				userDao.addUserPortrait(model);  //如果还没有删除则执行插入
			}else{ 
				userDao.updateUserPortrait(model); //如果已经有头像了就进行更新操作
			}
			
		}
		/**
		 * author:张晨
		 * 通过用户号码查找用户头像保存路径
		 * @param model
		 * @return
		 */
		@Override
		public String getPortraitPath(IUserModel model) {

			return userDao.getPortraitPath(model);
		}

}
