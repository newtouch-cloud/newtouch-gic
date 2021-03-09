package com.ca.cacore.manage.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.domain.user.IUserDomain;
import com.ca.cacore.manage.model.bo.IUserAuthsModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.IUserPortraitModel;
import com.ca.cacore.manage.model.bo.UserAuthsModel;
import com.ca.cacore.manage.model.bo.UserModel;
import com.ca.cacore.manage.model.vo.UserAuthsVO;
import com.ca.cacore.manage.model.vo.UserAuths_Role_BranchModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.stringutil.StringUtil;

/**
 * 
 * @author 吴思波
 * @since 2013-11-11
 * @discrible 用户管理Service层
 */
@Service
public class UserService extends ServerBase implements IUserService {
	@Autowired private IUserDomain userDomain;
	
	/**
	 * 查询user
	 * @param IUserModel
	 * @return ReturnMsg
	 * @throws Exception
	 */
	public ReturnMsg queryUser(IUserModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IUserModel> relist = userDomain.queryUser(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}

	/**
	 * 获取user
	 * @param IUserModel
	 * @return ReturnMsg
	 * @throws Exception
	 */
	public ReturnMsg getUser(IUserModel model) {
		ReturnMsg msg = new ReturnMsg();
		IUserModel sm = userDomain.getUser(model);
		msg.setDataTable(TransHelper.obj2map(sm));
		return msg;
	}

	/**
	 * 添加user
	 * @param IUserModel
	 * @return ReturnMsg
	 * @throws Exception
	 */
	public ReturnMsg addUser(IUserModel model) throws BusinessException{
		ReturnMsg reutn = new ReturnMsg();
		if (StringUtil.isNull(model.getUser_type())) {
			reutn.setFailMessage("用户类型不可为空，核对后重新操作。", true);
		}else if (StringUtil.isNull(model.getUserName())) {
			reutn.setFailMessage("用户名不可为空,核对后重新操作。", true);
		}else if (StringUtil.isNull(model.getPassword())) {
			reutn.setFailMessage("用户密码不可为空,核对后重新操作。", true);
		}
		else if (StringUtil.isNull(model.getName())) {
			reutn.setFailMessage("姓名不可为空,核对后重新操作。", true);
		}
		else if (StringUtil.isNull(model.getSex_code())) {
			reutn.setFailMessage("性别不可为空,核对后重新操作。", true);
		}
		if(!reutn.isSuccessflag()){ 
			reutn.setDataTable(TransHelper.obj2map(model));//将model数据带回页面
			return reutn;     //未通过校验，回显
		}else{
	  	IUserModel model1 = new UserModel();
		model1.setUserName(model.getUserName());
		model1 = userDomain.getUser(model1);
		if(model1!=null){
			reutn.setFailMessage("用户名已存在，核对后重新操作。", true);
			reutn.setDataTable(TransHelper.obj2map(model));//回显数据
		   }
		userDomain.addUser(model);
		reutn.setSuccessMessage("新增成功");
		return reutn;}
	}

	/**
	 * 修改保存User
	 * @param IUserModel
	 * @return ReturnMsg
	 * @throws Exception
	 */
	public ReturnMsg updateUser(IUserModel model) throws BusinessException{
		ReturnMsg reutn = new ReturnMsg();
		if (StringUtil.isNull(model.getUser_type())) {
			reutn.setFailMessage("用户类型不可为空，核对后重新操作。", true);
		}else if (StringUtil.isNull(model.getUserName())) {
			reutn.setFailMessage("用户名不可为空,核对后重新操作。", true);
		}else if (StringUtil.isNull(model.getPassword())) {
			reutn.setFailMessage("用户密码不可为空,核对后重新操作。", true);
		}
		else if (StringUtil.isNull(model.getName())) {
			reutn.setFailMessage("姓名不可为空,核对后重新操作。", true);
		}
		else if (StringUtil.isNull(model.getSex_code())) {
			reutn.setFailMessage("性别不可为空,核对后重新操作。", true);
		}
		if(!reutn.isSuccessflag()){ 
			reutn.setDataTable(TransHelper.obj2map(model));//将model数据带回页面
			return reutn;     //未通过校验，回显
		}else{
		IUserModel model1 = new UserModel();
		model1.setUserName(model.getUserName());
		model1 = userDomain.getUser(model1);
		if(model1!=null){
			if(!model.getSeq_id().equals(model1.getSeq_id())){
			reutn.setFailMessage("用户名已存在，核对后重新操作。", true);
			reutn.setDataTable(TransHelper.obj2map(model));//回显数据
		   }}
		userDomain.updateUser(model);
		reutn.setSuccessMessage("修改成功！");
		return reutn;}
	}
	/**
	 * 查看用户权限分配信息
	 * 角色列表
	 * @return
	 * @throws Exception
	 */
	public ReturnMsg queryUserRole(UserAuths_Role_BranchModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<UserAuths_Role_BranchModel> relist = userDomain.queryUserRole(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}

	/**
	 * 查看用户权限分配信息
	 * 机构列表
	 * @return
	 * @throws Exception
	 */
	public ReturnMsg queryUserRoles(UserAuths_Role_BranchModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<UserAuths_Role_BranchModel> relist = userDomain.queryUserRoles(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	
	/**
	 * 查询出角色信息以及机构树信息
	 */
	public ReturnMsg queryUserAuthsVO(IUserModel model){
		ReturnMsg msg = new ReturnMsg();
		List<UserAuthsVO> rolelist = userDomain.queryUserAuthsVO(model);
		msg.setParameter("rolelist", rolelist);
		return msg;
	}
	
	/**
	 * 得到机构树
	 */
	public List<String> queryBranchTree(UserAuthsModel model){
		return userDomain.queryBranchTreeToJson(model);
	}

	@Override
	public ReturnMsg getUserAndAuths(IUserModel user) {
		ReturnMsg msg=new ReturnMsg();
		IUserModel userModel=userDomain.getUser(user);
		msg.setDataTable(TransHelper.obj2map(userModel));
		List<UserAuthsVO> list=userDomain.getUserAuths(userModel);
		msg.setDataList(TransHelper.list2MapList(list));
		return msg;
	}

	@Override
	public ReturnMsg updateUserAuths(IUserModel user, String[] roles,IUserModel userModel) {
		userDomain.deleteUserAuthor(user);
		IUserAuthsModel authsModel = null;
		for (int i = 0; i < roles.length; i++) {
			authsModel=new UserAuthsModel();
			authsModel.setBranch_id(user.getBranch_id());
			authsModel.setUserName(user.getUserName());
			authsModel.setRole_id(roles[i]);
			authsModel.setCreateUser("liu_z");
			authsModel.setModifyUser("liu_z");
//			authsModel.setCreateUser(userModel.getUserName());
//			authsModel.setModifyUser(userModel.getUserName());
			userDomain.addUserAuthor(authsModel);
		}
		return getUserAndAuths(user);
	}
	
	/**
	 * 保存用户头像信息到数据库 author:张晨
	 * @param model
	 * @return
	 */
	@Override
	public ReturnMsg addUserPortrait(IUserPortraitModel model){
		userDomain.addUserPortrait(model);
		return null;
	}

	/**
	 * author:张晨
	 * 他通过用户号码查找用户头像保存路径
	 * @param model
	 * @return
	 */
	@Override
	public String getPortraitPath(IUserModel model) {

		return userDomain.getPortraitPath(model);
	}
}
