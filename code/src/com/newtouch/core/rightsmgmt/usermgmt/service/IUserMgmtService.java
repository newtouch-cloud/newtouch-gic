package com.newtouch.core.rightsmgmt.usermgmt.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.model.ISuperiorModel;
import com.newtouch.core.rightsmgmt.model.SuperiorModel;


public interface IUserMgmtService {
	public String queryIs4Sub(String empid);
	
	public ReturnMsg queryUserList(Map<String, Object> map);
	
	public ReturnMsg queryUserMappingList(Map<String, Object> map);

	public ReturnMsg queryUserByOptno(Map<String, Object> map);

	public ReturnMsg queryUserRoleList(Map<String, Object> map);

	public ReturnMsg queryUserAuthList(Map<String, Object> map);

	public ReturnMsg queryUserMenuList(Map<String, Object> map);

	public ReturnMsg addUserList(Map<String, Object> map);
	
	public ReturnMsg addUserPathList(Map<String, Object> map);

	public ReturnMsg addUserRoleList(Map<String, Object> map);

	public ReturnMsg addUserDeptList(Map<String, Object> map);

	public ReturnMsg delUserDeptList(Map<String, Object> map);

	public ReturnMsg mdfUserDeptList(Map<String, Object> map);

	public ReturnMsg addUserMapping(Map<String, Object> map);
	
	public boolean editUser(Map<String, Object> map);
	public ReturnMsg mdfUserPassWord(Map<String, Object> map);

	ReturnMsg restUserPassWord(Map<String, Object> map);
	/** 
	* 
	* @param map
	* @return ReturnMsg
	* @description:检查用户姓名是否存在（新增接口）
	*/
	public ReturnMsg checkUserName(Map<String, Object> map);
	/**
	 * 
	 * yanqiguang
	 * 下午3:56:53
	 * TODO 查询当前用户的机构数据权限
	 */
	public List<Map<String, Object>> getDataList(String opt_no);

	public ReturnMsg updateUserPassWord(Map<String, Object> map);
	
	public SuperiorModel querySuperior(ISuperiorModel model);

	public ArrayList<SuperiorModel> querySuperiorList(ISuperiorModel model);

	public ReturnMsg insertSuperior(ISuperiorModel model);

	public ReturnMsg updateSuperior(ISuperiorModel model);

	public Map<String, Object> getInfo(Map<String, Object> map);

	public Map<String, Object> queryUserDataAuth(IUserModel model);

	public ReturnMsg goUpdateOptStatus(Map<String, Object> map);

	public ArrayList<SuperiorModel> findSupersByBranchId(ISuperiorModel model);

	public ArrayList<SuperiorModel> querySuperiorInfos(ISuperiorModel model);

	public void deleteSuperior(ISuperiorModel model);

	public Boolean queryIsExistByName(String superior);

	public ISuperiorModel queryOpt(ISuperiorModel model);
}
