package com.newtouch.core.view.jsptag.select.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.select.ISelectService;

@Service
public class SelectConfirmServiceImp implements ISelectService {

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String sql = "select t.rank_no code, t.rank_name name from T_RANK t";
		query.setSql(sql);
		query.setPaginate(false);
		List<Map<String, Object>> listMap = dbHandle.queryList(query);
		JSONArray jsonArray = JSONArray.fromObject(listMap);
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code",queryObj.get("queryId")+"");
		map.put("name", jsonArray.toString());
		list.add(map);
		return list;
	}
}
