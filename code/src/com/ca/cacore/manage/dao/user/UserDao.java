package com.ca.cacore.manage.dao.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IUserAuthsModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.IUserPortraitModel;
import com.ca.cacore.manage.model.bo.UserAuthsModel;
import com.ca.cacore.manage.model.vo.BranchTree;
import com.ca.cacore.manage.model.vo.UserAuthsVO;
import com.ca.cacore.manage.model.vo.UserAuths_Role_BranchModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * @author 吴思波
 * @since 2013-11-11
 * @discrible 用户管理Dao层
 */

@Component 
public class UserDao extends BaseDao implements IUserDao {

	/**
	 * 查询user
	 * @param IUserModel
	 * @return List<IUserModel>
	 * @throws Exception
	 */
	public List<IUserModel> queryUser(IUserModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("user.queryUser_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		return (List<IUserModel>) this.getSqlMapClientTemplate().queryForList("user.queryUser", model);
	}
	
	public IUserModel getUser(IUserModel model) {
		return (IUserModel)this.getSqlMapClientTemplate().queryForObject("user.queryUserBySeqId", model);
	}

	/**
	 * 添加user
	 * @param IUserModel
	 * @return IUserModel
	 * @throws Exception
	 */
	public IUserModel addUser(IUserModel model) {
		this.getSqlMapClientTemplate().insert("user.insertUser", model);
		return null;
	}

	/**
	 * 修改user
	 * @param IUserModel
	 * @return IUserModel
	 * @throws Exception
	 */
	public boolean updateUser(IUserModel model) {
		this.getSqlMapClientTemplate().update("user.updateUser",model);
		return true;
	}

	@Override
	public List<UserAuths_Role_BranchModel> queryUserRole(UserAuths_Role_BranchModel model) {
		return (List<UserAuths_Role_BranchModel>) this.getSqlMapClientTemplate().queryForList("userAuths.queryUserByRoel", model);
	}

	@Override
	public List<UserAuths_Role_BranchModel> queryUserRoles(UserAuths_Role_BranchModel model) {
		return (List<UserAuths_Role_BranchModel>) this.getSqlMapClientTemplate().queryForList("userAuths.queryUserByBranch", model);
	}

	/**
	 * 根据用户名查询出所有的角色以及角色下对应的选中信息
	 */
	@Override
	public List<UserAuthsVO> queryRoleSelected(IUserModel model) {
		return (List<UserAuthsVO>) this.getSqlMapClientTemplate().queryForList("user.queryRoleSelected", model);
	}

	/**
	 * 查询出机构数的数据
	 */
	@Override
	public List<BranchTree> queryBranchTree(UserAuthsModel model) {
		return (List<BranchTree>) this.getSqlMapClientTemplate().queryForList("user.queryAllBranch",model);
	}
	
	/**
	 * 对用户权限表进行操作
	 */
	public boolean deleteUserAuths(UserAuthsModel model) {
		this.getSqlMapClientTemplate().delete("user.deleteUserAuths",model);
		return true;
	}

	@Override
	public List<UserAuthsVO> getUserAuths(IUserModel user) {
		return (List<UserAuthsVO>)this.getSqlMapClientTemplate().queryForList("user.queryUserAuths",user);
	}

	@Override
	public void deleteUserAuths(IUserModel user) {
		getSqlMapClientTemplate().delete("user.deleteUserAuths",user);
	}

	@Override
	public IUserAuthsModel addUserAuths(IUserAuthsModel model) {
		return (IUserAuthsModel)getSqlMapClientTemplate().insert("user.addUserAuths",model);
	}

	/**
	 * author:张晨
	 * 保存用户头像信息到数据库 
	 * @param model
	 * @return
	 */
	@Override
	public boolean addUserPortrait(IUserPortraitModel model) {
		this.getSqlMapClientTemplate().insert("user.addUserPortrait", model);
		return true;
	}

	/**
	 * author:张晨
	 * 检测用户是否已经保存头像
	 * @param model
	 * @return
	 */
	@Override
	public boolean checkUserPortrait(IUserPortraitModel model) {
		Integer count= (Integer) this.getSqlMapClientTemplate().queryForObject("user.checkUserPortrait", model);
		if(count >= 1){ //如果个数大于1 表示已经存在头像,执行更新操作
			return false;
		}
		return true; //表示没有头像新添加记录
	}
	/**
	 * author:张晨
	 * 如果用户已经存在头像则执行更新操作
	 * @param model
	 * @return
	 */
	@Override
	public boolean updateUserPortrait(IUserPortraitModel model) {
		this.getSqlMapClientTemplate().insert("user.updateUserPortrait", model);
		return true;  //更新用户头像信息
	}
	/**
	 * author:张晨
	 * 他通过用户号码查找用户头像保存路径
	 * @param model
	 * @return
	 */
	@Override
	public String getPortraitPath(IUserModel model) {
		
		return (String) this.getSqlMapClientTemplate().queryForObject("user.getPortraitPath", model);
	}
	
	/**
	 * author:张晨
	 * 他通过用户号码查找绑定的核心用户和密码
	 * @param model
	 * @return
	 */
	@Override
	public IUserModel getCoreUser(IUserModel model) {
		
		return (IUserModel) this.getSqlMapClientTemplate().queryForObject("user.getCoreUser", model);
	}
	
}