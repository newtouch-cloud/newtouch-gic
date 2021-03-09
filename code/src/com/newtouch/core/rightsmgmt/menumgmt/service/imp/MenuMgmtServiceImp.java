package com.newtouch.core.rightsmgmt.menumgmt.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.dao.T_MenuDao;
import com.newtouch.core.rightsmgmt.menumgmt.service.IMenuMgmtService;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.uniqueseq.UniqueSeq;

@Service
public class MenuMgmtServiceImp extends ServerBase implements IMenuMgmtService {

	@Autowired
	public T_MenuDao menuDao;

	public ReturnMsg insert(Map<String, Object> map) {
		ReturnMsg retMsg = new ReturnMsg();
		Message msg = new Message();
		Map<String, Object> menuNo = menuDao.query(map, false);
		Map<String, Object> menuMap = new HashMap<String, Object>();
		menuMap.put("name", map.get("menu_name"));
		menuMap.put("id", UniqueSeq.getUniqueSeq("menu_no", "t_menu"));
		map.remove("menu_no");
		map.put("menu_no", menuMap.get("id"));
		map.put("menu_seq", map.get("menu_seq") + "." + map.get("menu_no"));
		retMsg.setDataTable(menuMap);
		if (menuNo.size() == 0) {
			int count = menuDao.insert(map);
			if (count > 0) {
				msg.setMsgRemark("保存成功！");
				retMsg.setSuccessMessage(msg);
			} else {
				msg.setMsgRemark("保存失败！");
				retMsg.setFailMessage(msg);
			}
		} else {
			msg.setMsgRemark("菜单编号已存在，请重新输入！");
			retMsg.setFailMessage(msg);
		}
		return retMsg;
	}

	public ReturnMsg delete(Map<String, Object> map) {
		ReturnMsg retMsg = new ReturnMsg();
		Message msg = new Message();
		UpdateSqlable query = DBHandleCreator.getInstance().getUpdateSql();
		String sql = "delete from t_data_auth auth where data_auth_no in ( select menu_no from t_menu where menu_seq like ?) ";
		query.add(map.get("menu_seq") + "%");
		query.setSql(sql);
		int count = this.dbHandle().update(query);
		sql = "delete from t_menu tm where tm.menu_seq like ?";
		query.setSql(sql);
		query.add(map.get("menu_seq") + "%");
		count = this.dbHandle().update(query);
		if (count > 0) {
			msg.setMsgRemark("成功！");
			retMsg.setSuccessMessage(msg);
		} else {
			msg.setMsgRemark("删除失败！");
			retMsg.setFailMessage(msg);
		}
		return retMsg;
	}

	public ReturnMsg query(Map<String, Object> map) {
		ReturnMsg retMsg = new ReturnMsg();
		Message msg = new Message();

		String sql = "SELECT t.menu_no,t.menu_name,t.menu_url,t.menu_order,"
				+ "t.menu_type,t.menu_status,t.parent_no,tm.menu_name as parent_name "
				+ "FROM t_menu t left join t_menu tm on tm.menu_no=t.parent_no "
				+ "WHERE t.menu_no=? ORDER BY t.parent_no, t.menu_order ";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.setPaginate(false);
		query.add(map.get("menu_no"));
		query.setSql(sql);
		Map<String, Object> menuMap = this.dbHandle().query(query);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("form1", menuMap);
		retMsg.setDataTable(returnMap);
		return retMsg;
	}

	public List<Map<String, Object>> queryTree() {
		String sql = "SELECT t.menu_no as id,t.menu_name as name,t.parent_no as pid,t.menu_seq "
				+ "FROM t_menu t ORDER BY serno ";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.setPaginate(false);
		query.setSql(sql);
		return this.dbHandle().queryList(query);
	}

	public ReturnMsg update(Map<String, Object> map) {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("menu_no", map.get("menu_no"));
		int count = menuDao.update(whereMap, map);
		ReturnMsg retMsg = new ReturnMsg();
		Message msg = new Message();
		if (count > 0) {
			msg.setMsgRemark("成功！");
			retMsg.setSuccessMessage(msg);
		} else {
			msg.setMsgRemark("失败！");
			retMsg.setFailMessage(msg);
		}
		return retMsg;
	}
}
