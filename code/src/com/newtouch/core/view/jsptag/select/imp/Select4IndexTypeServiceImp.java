package com.newtouch.core.view.jsptag.select.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.view.jsptag.select.ISelectService;
import com.newtouch.utils.log.Ulog;

/**
 * 通过给定指标类型，查找对应的指标信息
 * 
 * @author lv
 * 
 */
@Service
public class Select4IndexTypeServiceImp implements ISelectService {

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String sql = "SELECT DISTINCT  i.index_no as code, index_name as name "
				+ "FROM  t_index i, t_rank_index ri "
				+ "WHERE i.index_no = ri.index_no " + "AND   i.index_type = ? "
				+ "ORDER BY code ";
		Ulog.debug("自定义标签queryId  ：  " + queryObj.get("queryId"));
		query.add(queryObj.get("queryId"));
		query.setSql(sql);
		query.setPaginate(false);
		return dbHandle.queryList(query);
	}

}
