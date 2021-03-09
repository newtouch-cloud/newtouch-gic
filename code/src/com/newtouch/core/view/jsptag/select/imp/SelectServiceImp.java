package com.newtouch.core.view.jsptag.select.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.select.ISelectService;

@Service
public class SelectServiceImp implements ISelectService {

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		String queryId = queryObj.get("queryId").toString();
		String channel = "01";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String s = "select t.enum_code as code, t.enum_name as name from T_ENUM t where t.enum_parentid=? and t.dept_type=? order by t.enum_id";
		query.setSql(s);
		query.setPaginate(false);
		query.add(queryId);
		query.add(channel);
		return dbHandle.queryList(query);
	}
}
