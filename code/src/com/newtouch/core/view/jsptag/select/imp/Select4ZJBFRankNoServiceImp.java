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
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.stringutil.StringUtil;

@Service
public class Select4ZJBFRankNoServiceImp implements ISelectService {

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String impmeans_no = ""; // 子基本法编码
		String basiclaw_no = ""; // 基本法编码
		// 判断 queryId 是否添加参数
		String str = queryObj.get("queryId").toString();
		if (!StringUtils.isEmpty(str)) {
			JSONObject json = JSONObject.fromObject(str);
			JSONArray jsonArray = (JSONArray) json.get("defmap");
			if (StringUtil.isNull(jsonArray)) {
				Ulog.debug(json.get("hiddenform").getClass());
				String temp = (String) json.get("hiddenform");
				Ulog.debug(temp.substring(temp.indexOf("impmeans_no") + 13,
						temp.indexOf(",rank_no") - 1));
				impmeans_no = temp.substring(temp.indexOf("impmeans_no") + 13,
						temp.indexOf(",rank_no") - 1);
				basiclaw_no = temp.substring(temp.indexOf("basiclaw_no") + 13,
						temp.indexOf(",impmeans_name") - 1);

			} else {
				for (int i = 0; i < jsonArray.size(); ++i) {
					JSONObject o = (JSONObject) jsonArray.get(i);
					impmeans_no = o.getString("impmeans_no");
					basiclaw_no = o.getString("basiclaw_no");
				}
			}

		}

		/*String querySql = "select r.rank_no as code, r.rank_name as name from T_IMPMEANS i, T_RANK r  where r.impmeansver_no=i.impmeans_no and r.data_flag=1 ";
		if (impmeans_no != null && !impmeans_no.equals("")) {
			querySql += "and i.impmeans_no = ?";
			query.add(impmeans_no);
		}
		query.setSql(querySql);
		query.setPaginate(false);
		List<Map<String, Object>> temp = dbHandle.queryList(query);
*/
		String querySql = "SELECT rank_no code,rank_name name from t_rank where impmeans_no = ? ";
		query.add(impmeans_no);
		query.setSql(querySql);
		query.setPaginate(false);
		return dbHandle.queryList(query);

	}

	public static void main(String[] args) {
		String s = "dept_type:'个贷',impmeansver_name:'2016版基本法',basiclaw_no:'3102',impmeans_name:'2016版子基本法',impmeans_no:'602',rank_no:'',chg_type:'',row4Page:'10',nowPage:'1'";
		Ulog.debug(s.substring(s.indexOf("impmeans_no") + 13,
				s.indexOf(",rank_no") - 1));
		Ulog.debug(s.substring(s.indexOf("basiclaw_no") + 13,
				s.indexOf(",impmeans_name") - 1));

	}

}
