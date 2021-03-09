package com.newtouch.core.quanxianguanli.service.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.quanxianguanli.service.IQueryDeptListService;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.dao.T_Data_Auth_TypeDao;
import com.newtouch.core.serverbase.ServerBase;

@Service
public class QueryDeptListServiceImp extends ServerBase implements
		IQueryDeptListService {
	@Autowired
	private T_Data_Auth_TypeDao authTypeDate;

	@Override
	public ReturnMsg queryDeptList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data_auth_type", "DEPT");
		Map<String, Object> authTypeMap = authTypeDate.query(map, false);
		String no = (String) authTypeMap.get("mapping_no");
		String name = (String) authTypeMap.get("mapping_name");
		String parent = (String) authTypeMap.get("mapping_parent");
		String mapping_rule = (String) authTypeMap.get("mapping_rule");
		String table_name = (String) authTypeMap.get("mapping_tab");
		String sql = "SELECT tm."
				+ no
				+ " data_auth_no, tm."
				+ name
				+ " name, tm."
				+ parent
				+ " parent_data_auth          "
				+ "  FROM "
				+ table_name
				+ " tm, t_data_auth tr "
				+ " WHERE tm."
				+ no
				+ " = tr.data_auth_no "
				+ "   AND tr.object_no = ? "
				+ "   AND tr.data_auth_type = ? AND tr.object_type = ? AND tr.is_display = ?";
		String[] rule = mapping_rule.split("##");
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		query.add(this.user().getOptID());
		query.add(map.get("data_auth_type"));
		query.add("USER");
		query.add("true");
		String sub_rule[];
		for (int i = 0; i < rule.length; i++) {
			sub_rule = rule[i].replaceAll("'", "").split("=");
			sql += " AND tm." + sub_rule[0] + " = ? ";
			query.add(sub_rule[1]);
		}
		query.setSql(sql);
		ReturnMsg msg = new ReturnMsg();
		msg.setDataList(this.dbHandle().queryList(query));
		return msg;
	}

}
