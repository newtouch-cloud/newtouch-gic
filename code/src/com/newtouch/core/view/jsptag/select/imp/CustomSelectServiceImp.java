package com.newtouch.core.view.jsptag.select.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.select.ISelectService;
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.stringutil.StringUtil;

@Component("CustomSelect")
public class CustomSelectServiceImp implements ISelectService {
	private static Map<String, Map<String, Object>> SELECT_MAP = new HashMap<String, Map<String, Object>>();

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> param,
			DBHandleable dbHandle) {
		String queryId = param.get("custom_no").toString();
		Map<String, Object> sMap = SELECT_MAP.get(queryId);
		if (sMap == null || sMap.isEmpty())
			sMap = initSelectMap(dbHandle, queryId);
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String sql = "select distinct " + sMap.get("mapping_code")
				+ " as code, "+sMap.get("mapping_order")+"," + sMap.get("mapping_name") + " as name from "
				+ sMap.get("mapping_tab") + " WHERE 1 = 1 ";
		if (!StringUtil.isNull(sMap.get("mapping_parent"))) {
			sql += " AND " + sMap.get("mapping_parent") + " = ? ";
			query.add(param.get("up_no"));
		}
		if (!StringUtil.isNull(sMap.get("mapping_rule"))) {
			String rule[] = sMap.get("mapping_rule").toString().split("##");
			String sub_rule[];
			String prefix = "";
			String suffix = "";
			for (int i = 0; i < rule.length; i++) {
				sub_rule = rule[i].replaceAll("'", "").split("\\|");
				sql += " AND " + sub_rule[0] + " " + sub_rule[1] + " ? ";
				if (sub_rule[2].startsWith("%")) {
					prefix += "%";
					sub_rule[2] = sub_rule[2]
							.substring(1, sub_rule[2].length());
				}
				if (sub_rule[2].endsWith("%")) {
					suffix += "%";
					sub_rule[2] = sub_rule[2].substring(0,
							sub_rule[2].length() - 1);
				}
				if (sub_rule[2].startsWith("^"))
					query.add(prefix
							+ param.get(sub_rule[2].substring(1,
									sub_rule[2].length())) + suffix);
				else
					query.add(prefix + sub_rule[2] + suffix);
			}
		}
		query.setSql(sql);
		query.setPaginate(false);
		query.setLocalOrder(true);
		if (!StringUtil.isNull(sMap.get("mapping_order")))
			query.setOrderBy(StringUtil.trimStr(sMap.get("mapping_order")));
		Ulog.debug(sql);
		return dbHandle.queryList(query);
	}

	private Map<String, Object> initSelectMap(DBHandleable dbHandle,
			String queryId) {
		String sql = "SELECT * FROM sys_select_custom WHERE custom_no = ?";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.setSql(sql);
		query.add(queryId);
		Map<String, Object> map = dbHandle.query(query);
		if (map == null || map.isEmpty())
			throw new RuntimeException("未查找到下拉列表自定义代码[" + queryId + "]");
		SELECT_MAP.put(queryId, map);
		return map;
	}
}
