package com.newtouch.core.quanxianguanli.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一标识。
	 */
	private String id = "";

	/**
	 * 操作员姓名
	 */
	private String optName = "";

	/**
	 * 操作员英文名
	 */
	private String optEnglishName = "";

	/**
	 * 操作员编号
	 */
	private String optID = "";

	/**
	 * 操作员性别。
	 */
	private String sex = "";

	/**
	 * 邮箱
	 */
	private String mail = "";

	/**
	 * 密码
	 */
	private String optpass = "";

	/**
	 * 状态
	 */
	private String status = "";
	/**
	 * 关联用户代码
	 */
	private String ins_usercode = "";

	/**
	 * 操作员类型
	 */
	private String optType = "";
	/**
	 * 所属机构
	 */
	private String did = "";

	private String deptName = "";
	/**
	 * 可操作下级机构，使用','分割。
	 */
	private String deptList = "";

	/**
	 * 可授予角色，使用','分割。
	 */
	private String roleList = "";

	/**
	 * 当前操作可操作按钮集合
	 */
	private List<String> funcLis = new ArrayList<String>();
	/**
	 * 当前操作机构，使用','分割。
	 */
	private String curOptDeptList = "";
	// 当前操作部门名称
	private String curOptDeptName = "";
	/**
	 * 当前操作的菜单编号
	 */
	private String menuID = "";
	/**
	 * 当前操作的功能编号
	 */
	private String funcID = "";

	private String dept_type = "";

	private String dept_level = "";

	/**
	 * @roseuid 48FFC93001B5
	 */
	public User() {

	}

	public String getDept_level() {
		return dept_level;
	}

	public void setDept_level(String dept_level) {
		this.dept_level = dept_level;
	}

	public void setOptID(String optID) {
		this.optID = optID;
	}

	/**
	 * 
	 * @param did
	 *            当前操作机构，使用','分割。
	 */
	public void setCurOptDeptList(String did, String deptType) {
		// LoginInterFace login = FuncManage.getLogin();
		// List<Map<String, Object>> listMap = login.queryCurOptDept(this.optID,
		// did, deptType);
		// this.curOptDeptList = login.joinString(listMap, "did");
		// if (!listMap.isEmpty()) {
		// this.curOptDeptName = (String) listMap.get(0).get("deptname");
		// }
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOptName() {
		return optName;
	}

	/**
	 * 获得操作员名称
	 * 
	 * @param optName
	 */
	public void setOptName(String optName) {
		this.optName = optName;
	}

	/**
	 * 获得操作员ID
	 * 
	 * @return
	 */
	public String getOptID() {
		return optID;
	}

	/**
	 * 获得操作员性别
	 * 
	 * @return
	 */
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 密码
	 * 
	 * @return
	 */
	public String getOptpass() {
		return optpass;
	}

	public void setOptpass(String optpass) {
		this.optpass = optpass;
	}

	/**
	 * 状态
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 类型
	 * 
	 * @return
	 */
	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	/**
	 * 可操作部门，使用','分割。
	 * 
	 * @return
	 */
	public String getDeptList() {
		return deptList;
	}

	public void setDeptList(String deptList) {
		this.deptList = deptList;
	}

	/**
	 * 可授予角色，使用','分割。
	 * 
	 * @return
	 */
	public String getRoleList() {
		return roleList;
	}

	public void setRoleList(String roleList) {
		this.roleList = roleList;
	}

	/**
	 * 
	 * @return 当前操作机构，使用','分割。
	 */
	public String getCurOptDeptList() {
		return curOptDeptList;
	}

	/**
	 * 
	 * @return 当前操作员所操作的菜单
	 */
	public String getMenuID() {
		return menuID;
	}

	/**
	 * 
	 * @param menuID
	 *            当前操作员所操作的菜单
	 */
	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

	/**
	 * 
	 * @return 当前操作员所操作的功能
	 */
	public String getFuncID() {
		return funcID;
	}

	/**
	 * 
	 * @param funcID
	 *            当前操作员所操作的功能
	 */
	public void setFuncID(String funcID) {
		this.funcID = funcID;
	}

	/**
	 * 所属机构
	 * 
	 * @return
	 */
	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getCurOptDeptName() {
		return curOptDeptName;
	}

	public void setCurOptDeptName(String curOptDeptName) {
		this.curOptDeptName = curOptDeptName;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getFuncLis() {
		return funcLis;
	}

	public void setFuncLis(List<String> funcLis) {
		this.funcLis = funcLis;
	}

	public String getOptEnglishName() {
		return optEnglishName;
	}

	public void setOptEnglishName(String optEnglishName) {
		this.optEnglishName = optEnglishName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	

	public String getDept_type() {
		return dept_type;
	}

	public void setDept_type(String dept_type) {
		this.dept_type = dept_type;
	}

	public String getIns_usercode() {
		return ins_usercode;
	}

	public void setIns_usercode(String ins_usercode) {
		this.ins_usercode = ins_usercode;
	}
	
	
}
