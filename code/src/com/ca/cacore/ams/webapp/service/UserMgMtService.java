package com.ca.cacore.ams.webapp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.domain.IUserMgMtDomain;
import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;


@Service
public class UserMgMtService extends ServerBase implements IUserMgMtService{

	@Autowired private IUserMgMtDomain userMgmtDomain;
	/** 
	* 
	* @param model
	* @return 
	* @description:查询-用户信息轨迹表信息
	*/
	@Override
	public IUserMgMtVOModel queryOptPathInfo(IUserMgMtVOModel model) {
		IUserMgMtVOModel umt=userMgmtDomain.queryOptPathInfo(model);
		return umt;
	}
	
	/** 
	* 
	* @param model
	* @return 
	* @description:登录页面-检查用户密码是否过期
	*/
	@Override
	public String checkPassWordIsOverDue(IUserMgMtVOModel model) {
		//查询用户密码到期时间
		IUserMgMtVOModel umvo=userMgmtDomain.queryOptPathInfo(model);
		String flag="";
		try {
			if(umvo!=null){
				Date now=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date date=sdf.parse(sdf.format(now));//当前时间
				Date end_date=sdf.parse(umvo.getEnd_date());//密码到期时间
				if(date.getTime()>end_date.getTime()){//如果当前时间大于密码到期时间则密码过期
					flag="1";//返回标志位
				}
			}
		} catch (Exception e) {
			flag="发生异常错误";
		}
		return flag;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:登录页面-登录成功时将sessionid更新到登录轨迹表中
	*/
	@Override
	public ReturnMsg updateOptPathInfo(IUserMgMtVOModel model) {
		ReturnMsg msg=new ReturnMsg();
		try {
			boolean flag=userMgmtDomain.updateOptPathInfo(model);
			if(flag==false){
				msg.setFailMessage("发生异常错误");
			}
		} catch (Exception e) {
			msg.setFailMessage(e.getMessage());
		}
		return msg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:更新用户密码
	*/
	@Override
	public ReturnMsg updatePassWord(IUserMgMtVOModel model) {
		ReturnMsg msg=new ReturnMsg();
		try {
			boolean flag=userMgmtDomain.updatePassWord(model);
			if(flag==false){
				msg.setFailMessage("发生异常错误");
			}
		} catch (Exception e) {
			msg.setFailMessage(e.getMessage());
		}
		return msg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:查询用户信息
	*/
	@Override
	public ReturnMsg queryUserInfo(IUserMgMtVOModel model) {
		ReturnMsg msg=new ReturnMsg();
		try {
			IUserMgMtVOModel umg=userMgmtDomain.queryUserInfo(model);
			msg.setDataTable(TransHelper.obj2map(umg));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return msg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:新增用户信息
	*/
	@Override
	public ReturnMsg addUserInfo(IUserMgMtVOModel model) {
		ReturnMsg msg=new ReturnMsg();
		try {
			userMgmtDomain.addUserInfo(model);
			msg.setSuccessMessage("用户信息添加成功");
		} catch (Exception e) {
			e.getStackTrace();
		}
		return msg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:新增用户轨迹信息
	*/
	@Override
	public ReturnMsg addOptPathInfo(IUserMgMtVOModel model) {
		ReturnMsg msg=new ReturnMsg();
		try {
			userMgmtDomain.addOptPathInfo(model);
			msg.setSuccessMessage("用户轨迹信息添加成功");
		} catch (Exception e) {
			e.getStackTrace();
		}
		return msg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:新增用户角色信息
	*/
	@Override
	public ReturnMsg addOperatorRolesInfo(IUserMgMtVOModel model) {
		ReturnMsg msg=new ReturnMsg();
		try {
			userMgmtDomain.addOperatorRolesInfo(model);
			msg.setSuccessMessage("用户角色信息添加成功");
		} catch (Exception e) {
			e.getStackTrace();
		}
		return msg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:新增用户菜单信息
	*/
	@Override
	public ReturnMsg addDataAuthInfo(IUserMgMtVOModel model) {
		ReturnMsg msg=new ReturnMsg();
		try {
			userMgmtDomain.addDataAuthInfo(model);
			msg.setSuccessMessage("用户菜单信息添加成功");
		} catch (Exception e) {
			e.getStackTrace();
		}
		return msg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:查询分公司下的所有机构
	*/
	@Override
	public List<IUserMgMtVOModel> queryBranchList(IUserMgMtVOModel model) {
		List<IUserMgMtVOModel> list=userMgmtDomain.queryBranchList(model);
		return list;
	}
	
}
