package com.newtouch.core.view.jsptag.select;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.select.ISelectService;

@Service
public class Select4DeptTypeServiceImp implements ISelectService {

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String sql = "select t.enum_code code,t.enum_name name " +
				"from T_ENUM t where t.enum_parentid=? order by t.enum_id";
		query.add(queryObj.get("queryId"));
		query.setSql(sql);
		query.setPaginate(false);
		return dbHandle.queryList(query);
	}
}
