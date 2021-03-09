package com.newtouch.core.rightsmgmt.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.newtouch.core.dao.IDao;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.stringutil.StringUtil;
import com.newtouch.utils.uniqueseq.UniqueSeq;

@Component("Ins_Ca_User_BindingDao")
public class Ins_Ca_User_BindingDao extends ServerBase implements IDao {
	private static List<String> clmList = new ArrayList<String>();
	private static List<String> baseList = new ArrayList<String>();
	static {
		clmList.add("usercode");
		clmList.add("username");
		clmList.add("password");
		clmList.add("ca_code");
		clmList.add("ca_name");
		clmList.add("ca_password");
		clmList.add("end_date");
		
		baseList.add("seq_id");
		baseList.add("createdate");
		baseList.add("status");
		baseList.add("opt_date");
		baseList.add("opt_name");

	}

	@Override
	public int insert(Map<String, Object> param) {
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		String clm = "seq_id,createdate,status,opt_date,opt_name";
		String value = " ?, ?, ?, ?, ?";
		update.add(UniqueSeq.getUniqueSeq("seq","INS_CA_USER_BINDING"));	
		update.add(DateUtil.sysTimestamp());
		update.add("1");
		update.add(DateUtil.sysTimestamp());
		update.add(param.get("ca_name"));

		
		for (String key : param.keySet()) {
			if (!this.isContinue(key))
				continue;
			clm += ", " + key;
			value += ", ?";
			update.add(param.get(key));
		}
		String sql = "INSERT INTO INS_CA_USER_BINDING (" + clm + ") VALUES(" + value + ")";
		update.setSql(sql);
		return this.dbHandle().update(update);
	}

	

	private boolean isContinue(String clmName) {
		if (Ins_Ca_User_BindingDao.clmList.contains(clmName)) {
			return true;
		}
		if (Ins_Ca_User_BindingDao.baseList.contains(clmName)) {
			if(clmName.equals("serno") || clmName.equals("data_flag")){
				return true;
			}
			return false;
		}
		return false;
	}
	@Override
	public int update(Map<String, Object> where, Map<String, Object> set) {
		if (where.isEmpty()) {
			throw new RuntimeException("update语句未设置where条件!");
		}
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		String setClm = "opt_date = ?, opt_name = ? ,status = ? ";
		update.add(DateUtil.sysTimestamp());
		update.add(this.user().getOptID());
		update.add(set.get("status"));

		String whereClm = "";
//		for (String key : set.keySet()) {
//			if (!this.isContinue(key))
//				continue;
//			setClm += ", " + key + " = ? ";
//			update.add(set.get(key));
//		}
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

		String sql = "UPDATE INS_CA_USER_BINDING SET " + setClm + " " + whereClm;
		update.setSql(sql);
		return this.dbHandle().update(update);
	}
	
	@Override
	public int update(String serno, Map<String, Object> set) {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("ca_code", serno);
		return this.update(where, set);
	}

	
	
	@Override
	public int delete(String serno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Map<String, Object> where) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFlag(String serno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFlag(Map<String, Object> where) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String, Object> query(Map<String, Object> param, boolean paginate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> param,
			boolean paginate) {
		// TODO Auto-generated method stub
		return null;
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


