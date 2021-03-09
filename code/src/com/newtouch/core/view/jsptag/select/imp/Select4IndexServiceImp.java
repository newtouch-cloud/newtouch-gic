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
import com.newtouch.utils.stringutil.StringUtil;

@Service
public class Select4IndexServiceImp implements ISelectService {

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String str = queryObj.get("queryId").toString();
		String basiclaw_no = ""; // 基本法编码
		if(!StringUtils.isEmpty(str)){
			JSONObject json = JSONObject.fromObject(str);
			JSONArray jsonArray = (JSONArray) json.get("defmap");
			for (int i = 0; i < jsonArray.size(); ++i) {
				JSONObject o = (JSONObject) jsonArray.get(i);
				basiclaw_no = o.getString("basiclaw_no");
			}
		}
		String querySql = "select index_no code,index_name name from t_index where index_type = ? and impmeans_no = ? ";
		//查询基本法所有的考核指标
		query.add("3A");
		query.add(basiclaw_no);
		query.setSql(querySql);
		query.setPaginate(false);
		return dbHandle.queryList(query);
	
	}

}
