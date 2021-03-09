package com.newtouch.core.view.jsptag.button.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.button.IButtonService;

@Service("buttonServiceImp")
public class ButtonServiceImp implements IButtonService {

	@Override
	public boolean query(DBHandleable dbHandle, String name, String roleId,
			String insurerch_code) {
		String sql = "select t1.role_id from t_role_function_relations t1, t_function_fpoint_relation t2, t_functionpoint t3,  t_role_functionpoint_relations t4 "
				+ "where t1.FUNCTION_ID=t2.function_id "
				+ "and t2.functionpoint_id=t3.functionpoint_code "
				+ "and t2.menu_b_relation_id=t4.menu_b_relation_id "
				+ "and t1.role_id = t4.role_id "
				+ "and t1.insurerch_code=? "
				+ " and t3.functionpoint_service = ? and t1.role_id=? ";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.setSql(sql);
		query.add(insurerch_code);
		query.add(name);
		query.add(roleId);
		query.setPaginate(false);
		List<Map<String, Object>> list = dbHandle.queryList(query);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}
}
