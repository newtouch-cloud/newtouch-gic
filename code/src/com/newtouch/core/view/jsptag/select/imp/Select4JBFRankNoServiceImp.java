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
public class Select4JBFRankNoServiceImp implements ISelectService {

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String impmeansver_no = "";             
		String str = queryObj.get("queryId").toString();
		if(!StringUtils.isEmpty(str)){
			JSONObject json = JSONObject.fromObject(str);
			JSONArray jsonArray = (JSONArray) json.get("defmap");
			for (int i = 0; i < jsonArray.size(); ++i) {
				JSONObject o = (JSONObject) jsonArray.get(i);
				impmeansver_no = o.getString("impmeansver_no");
			}
		}

		String querySql = "select distinct rank_no as code,rank_name as name from t_rank where data_flag=1 ";
		if(impmeansver_no != null && !impmeansver_no.equals("")){
			querySql += "AND impmeansver_no = ?";
			query.add(impmeansver_no);
		}
		query.setSql(querySql);
		query.setPaginate(false);
		return dbHandle.queryList(query);
	
	}

}
