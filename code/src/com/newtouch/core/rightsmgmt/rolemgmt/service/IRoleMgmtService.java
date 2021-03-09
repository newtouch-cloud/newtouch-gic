package com.newtouch.core.rightsmgmt.rolemgmt.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public interface IRoleMgmtService {
	public ReturnMsg queryRoleList(Map<String, Object> map);

	public ReturnMsg addRole(Map<String, Object> map);

	public ReturnMsg mdfRoleBaseInfo(Map<String, Object> map);

	public ReturnMsg mdfRoleAuthInfo(Map<String, Object> map);

	public ReturnMsg queryRoleAuthList(Map<String, Object> map);

	public ReturnMsg queryRoleUserList(Map<String, Object> map);
	
	public ReturnMsg deleteRoleUserList(Map<String, Object> map);
	
	public ReturnMsg addRoleUserList(Map<String, Object> map,String current,String target);

	public ReturnMsg addRoleRelationUser(Map<String, Object> map, String string, String string2);
}
