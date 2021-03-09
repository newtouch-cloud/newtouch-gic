package ${DaoModel.packageName};

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

@Component("${DaoModel.className}")
public class ${DaoModel.className} extends ServerBase implements IDao {
	private static List<String> clmList = new ArrayList<String>();
	private static List<String> baseList = new ArrayList<String>();
	static {
	[#list DaoModel.clmList as clmName]
		clmList.add("${clmName}");
	[/#list]
	[#list DaoModel.baseList as baseClm]
		baseList.add("${baseClm}");
	[/#list]
	}

	@Override
	public int insert(Map<String, Object> param) {
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		String clm = "serno, crt_date, mdf_date, crt_user, mdf_user, data_flag";
		String value = "?, ?, ?, ?, ?, ?";
		update.add(UniqueSeq.getUniqueSeq("serno", "${DaoModel.tableName}"));
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
		String sql = "INSERT INTO ${DaoModel.tableName} (" + clm + ") VALUES(" + value + ")";
		update.setSql(sql);
		return this.dbHandle().update(update);
	}

	@Override
	public int update(Map<String, Object> where, Map<String, Object> set) {
		if (where.isEmpty()) {
			throw new RuntimeException("update语句未设置where条件!");
		}
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		String setClm = "mdf_date = ?, mdf_user = ? ";
		update.add(DateUtil.sysTimestamp());
		update.add(this.user().getOptID());
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

		String sql = "UPDATE ${DaoModel.tableName} SET " + setClm + " " + whereClm;
		update.setSql(sql);
		return this.dbHandle().update(update);
	}

	@Override
	public int update(String serno, Map<String, Object> set) {
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("serno", serno);
		return this.update(where, set);
	}

	@Override
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

		String sql = "DELETE FROM ${DaoModel.tableName} " + whereClm;
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
		if (${DaoModel.className}.clmList.contains(clmName)) {
			return true;
		}
		if (${DaoModel.className}.baseList.contains(clmName)) {
			if(clmName.equals("serno") || clmName.equals("data_flag")){
				return true;
			}
			return false;
		}
		return false;
	}

	private QuerySqlable joinQuerySql(Map<String, Object> param,
			boolean isPaginate) {
		String sql = "SELECT * FROM ${DaoModel.tableName} ";
		String where = "";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.setPaginate(isPaginate);
		for (String key : param.keySet()) {
			if (!this.isContinue(key))
				continue;
			if (StringUtil.isNull(param.get(key)))
				continue;
			if (StringUtil.isNull(where)) {
				where += " WHERE " + key + " = ? ";
				query.add(param.get(key));
				continue;
			}
			where += " AND " + key + " = ? ";
			query.add(param.get(key));
		}
		query.setSql(sql + where);
		return query;
	}
}


