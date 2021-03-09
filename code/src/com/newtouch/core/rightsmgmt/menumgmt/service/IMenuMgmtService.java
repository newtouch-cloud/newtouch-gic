package com.newtouch.core.rightsmgmt.menumgmt.service;

import java.util.List;
import java.util.Map;

import com.newtouch.core.returnmsg.ReturnMsg;


public interface IMenuMgmtService {

	public ReturnMsg insert(Map<String, Object> map);
	
	public ReturnMsg delete(Map<String, Object> map);
	
	public ReturnMsg query(Map<String, Object> map);
	
	public List<Map<String, Object>> queryTree();
	
	public ReturnMsg update(Map<String, Object> map);
}
