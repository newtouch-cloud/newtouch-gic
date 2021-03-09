package com.newtouch.core.rightsmgmt.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.newtouch.core.dao.IDao;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.stringutil.StringUtil;
import com.newtouch.utils.uniqueseq.UniqueSeq;

@Component("T_OperatorDao")
public class T_OperatorDao extends ServerBase implements IDao {
	private static List<String> clmList = new ArrayList<String>();
	private static List<String> baseList = new ArrayList<String>();
	static {
		clmList.add("branch_no");
		clmList.add("dept_no");
		clmList.add("opt_no");
		clmList.add("opt_password");
		clmList.add("opt_name");
		clmList.add("opt_type");
		clmList.add("end_date");
		clmList.add("start_date");
		clmList.add("opt_mail");
		clmList.add("opt_confer");
		clmList.add("opt_sex");
		clmList.add("opt_phone");
		clmList.add("opt_status");
		clmList.add("is4Sub");
		baseList.add("serno");
		baseList.add("crt_date");
		baseList.add("mdf_date");
		baseList.add("crt_user");
		baseList.add("mdf_user");
		baseList.add("data_flag");
		baseList.add("patch_memo");
	}

	@Override
	public int insert(Map<String, Object> param) {
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		String clm = "serno, crt_date, mdf_date, crt_user, mdf_user, data_flag";
		String value = "?, ?, ?, ?, ?, ?";
		update.add(UniqueSeq.getUniqueSeq("serno", "t_operator"));
		update.add(DateUtil.sysTimestamp());
		update.add(DateUtil.sysTimestamp());
		update.add(this.user().getOptID());
		update.add(this.user().getOptID());
		update.add("1");
		for (String key : param.keySet()) {
			if (!this.isContinue(key))
				continue;
			clm += ", " + key;
			value += ", ?";
			update.add(param.get(key));
		}
		String sql = "INSERT INTO t_operator (" + clm + ") VALUES(" + value + ")";
		update.setSql(sql);
		return this.dbHandle().update(update);
	}

	public int addUserPathList(Map<String, Object> param) {
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		String clm = "seq_id, createuser,modifyuser,createdate,modifydate,opt_no,opt_name,opt_password,start_date,end_date,status ";
		String value = "?, ?, ?, ?, ?, ? , ? , ?, ?, ?, ? ";
		update.add(UniqueSeq.getUniqueSeq("seq_id", "t_operator_path"));
		update.add(this.user().getOptID());
		update.add(this.user().getOptID());
		update.add(DateUtil.sysTimestamp());
		update.add(DateUtil.sysTimestamp());
		update.add(param.get("opt_no"));
		update.add(param.get("opt_name"));
		update.add(param.get("opt_password"));
		update.add(param.get("start_date"));
		update.add(param.get("end_date"));
		update.add(param.get("status"));
		String sql = "INSERT INTO t_operator_path (" + clm + ") VALUES(" + value + ")";
		update.setSql(sql);
		return this.dbHandle().update(update);
	}
	/**
	 * 修改密码
	 */
	@Override
	public int update(Map<String, Object> where, Map<String, Object> set) {
		if (where.isEmpty()) {
			throw new RuntimeException("update语句未设置where条件!");
		}
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		String setClm = "mdf_date = ? ";
		update.add(DateUtil.sysTimestamp());
		
		String whereClm = "";
		for (String key : set.keySet()) {
			if (!this.isContinue(key))
				continue;
			setClm += ", " + key + " = ?";
			update.add(set.get(key));
		}
		for (String key : where.keySet()) {
			if (!this.isContinue(key))
				continue;
			if (StringUtil.isNull(whereClm)) {
				whereClm += " WHERE " + key + " = ?";
				update.add(where.get(key));
				continue;
			}
			whereClm += " AND " + key + " = ?";
			update.add(where.get(key));
		}

		String sql = "UPDATE t_operator SET " + setClm + " " + whereClm;
		update.setSql(sql);
		return this.dbHandle().update(update);
	}

	@Override
	public int update(String serno, Map<String, Object> set) {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("serno", serno);
		return this.update(where, set);
	}

	public Map<String, Object> query(Map<String, Object> param, boolean isPaginate) {
		return this.dbHandle().query(this.joinQuerySql(param, isPaginate));
	}

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> param,
			boolean isPaginate) {
		return this.dbHandle().queryList((this.joinQuerySql(param, isPaginate)));
	}

	@Override
	public int delete(String serno) {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("serno", serno);
		return this.delete(where);
	}

	@Override
	public int delete(Map<String, Object> where) {
		if (where.isEmpty()) {
			throw new RuntimeException("delete语句未设置where条件!");
		}
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		String whereClm = "";
		for (String key : where.keySet()) {
			if (!this.isContinue(key))
				continue;
			if (StringUtil.isNull(whereClm)) {
				whereClm += " WHERE " + key + " = ?";
				update.add(where.get(key));
				continue;
			}
			whereClm += " AND " + key + " = ?";
			update.add(where.get(key));
		}

		String sql = "DELETE FROM t_operator " + whereClm;
		System.out.println("========"+sql+"========");
		update.setSql(sql);
		return this.dbHandle().update(update);
	}
	
	@Override
	public int deleteFlag(String serno) {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("serno", serno);
		return this.delete(where);
	}

	@Override
	public int deleteFlag(Map<String, Object> where) {
		Map<String, Object> set = new HashMap<String, Object>();
		set.put("data_flag", "0");
		return this.update(where, set);
	}	
	
	private boolean isContinue(String clmName) {
		if (T_OperatorDao.clmList.contains(clmName)) {
			return true;
		}
		if (T_OperatorDao.baseList.contains(clmName)) {
			if(clmName.equals("serno") || clmName.equals("data_flag")){
				return true;
			}
			return false;
		}
		return false;
	}

	
	private QuerySqlable joinQuerySql(Map<String, Object> param,
			boolean isPaginate) {
		String sql = "SELECT * FROM t_operator where opt_status = ?";
		String where = "";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.setPaginate(isPaginate);
		query.add("1");
		for (String key : param.keySet()) {
			if (!this.isContinue(key))
				continue;
			if (StringUtil.isNull(param.get(key)))
				continue;
			/*if (StringUtil.isNull(where)) {
				where += " WHERE " + key + " = ? ";
				query.add(param.get(key));
				continue;
			}*/
			where += " AND " + key + " = ? ";
			query.add(param.get(key));
		}
		query.setSql(sql + where);
		return query;
	}

	@Override
	public int insert(Map<String, Object> param, boolean autoQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Map<String, Object> set) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFlag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> query(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> query() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryList(boolean isPaginate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equalsData(String serno, Map<String, Object> data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equalsData(Map<String, Object> where,
			Map<String, Object> data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equalsData(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrder(String order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEq(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNotEq(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addIn(String filed, List<Object> value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNotIn(String filed, List<Object> value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLike(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLikeR(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLikeL(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNotLike(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNotLikeR(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNotLikeL(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBetween(Object value, String filed1, String filed2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLT(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGT(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLTEq(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGTEq(String filed, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAs(String field, String as) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearParam() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isClsParam() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setClsParam(boolean clsParam) {
		// TODO Auto-generated method stub
		
	}
}


