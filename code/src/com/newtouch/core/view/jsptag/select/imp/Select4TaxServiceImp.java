package com.newtouch.core.view.jsptag.select.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.select.ISelectService;

@Service
public class Select4TaxServiceImp implements ISelectService {

	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String sql = "SELECT  TAX_CODE as code,  TAX_NAME as name FROM E_T_TAX WHERE INSURERCH_CODE = ?";
		query.setSql(sql);
		query.add("8");
		query.setPaginate(false);
		return dbHandle.queryList(query);
	}
}
