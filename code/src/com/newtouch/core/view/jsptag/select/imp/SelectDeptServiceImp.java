package com.newtouch.core.view.jsptag.select.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.select.ISelectService;

@Service
public class SelectDeptServiceImp implements ISelectService {

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String querySql = "SELECT t.dept_no code, t.dept_name name, "
				+ " t.dept_no dept_no1, t.dept_name dept_name1, "
				+ " t2.dept_no dept_no2, t2.dept_name dept_name2, "
				+ " t3.dept_no dept_no3, t3.dept_name dept_name3"
				+ " FROM t_dept t, t_dept t2, t_dept t3 "
				+ " WHERE t.up_dept = t2.dept_no "
				+ "     AND t2.up_dept = t3.dept_no          "
				+ "     AND t.dept_level = 3";
		query.setSql(querySql);
		query.setPaginate(false);
		List<Map<String, Object>> list = dbHandle.queryList(query);
		querySql = "SELECT t2.dept_no code, t2.dept_name name, "
				+ " t2.dept_no dept_no2, t2.dept_name dept_name2, "
				+ " t3.dept_no dept_no3, t3.dept_name dept_name3"
				+ " FROM t_dept t2, t_dept t3 "
				+ " WHERE t2.up_dept = t3.dept_no          "
				+ "   AND t2.dept_level = 2";
		query.setSql(querySql);
		query.setPaginate(false);
		for(Map<String, Object> map : dbHandle.queryList(query)){
			list.add(map);
		}
		querySql = "SELECT t3.dept_no code, t3.dept_name name, "
				+ " t3.dept_no dept_no3, t3.dept_name dept_name3"
				+ " FROM t_dept t3 "
				+ " WHERE t3.dept_level = 1";
		query.setSql(querySql);
		query.setPaginate(false);
		for(Map<String, Object> map : dbHandle.queryList(query)){
			list.add(map);
		}
		return list;
	}

}
