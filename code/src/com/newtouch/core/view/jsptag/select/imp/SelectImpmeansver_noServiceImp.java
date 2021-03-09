package com.newtouch.core.view.jsptag.select.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.select.ISelectService;

@Service
public class SelectImpmeansver_noServiceImp  implements ISelectService{
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String querySql = "select impmeans_no as code, impmeans_name as name  from t_impmeans ";
		query.setSql(querySql);
		query.setPaginate(false);
		return dbHandle.queryList(query);
	
	}
}
