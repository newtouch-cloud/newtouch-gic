package com.newtouch.core.view.jsptag.select.imp;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.select.ISelectService;

@Service
public class Select4BankServiceImp  implements ISelectService{
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String str = queryObj.get("queryId").toString();
		String branch_id = ""; 
		if(!StringUtils.isEmpty(str)){
			JSONObject json = JSONObject.fromObject(str);
			JSONArray jsonArray = (JSONArray) json.get("defmap");
			for (int i = 0; i < jsonArray.size(); ++i) {
				JSONObject o = (JSONObject) jsonArray.get(i);
				branch_id = o.getString("branch_id");
			}
		}
		String querySql = "select bank_no code,bank_no name from t_bank where branch_id = ? ";
		//查询基本法所有的考核指标
		query.add(branch_id);
		query.setSql(querySql);
		query.setPaginate(false);
		return dbHandle.queryList(query);
	
	}
}
