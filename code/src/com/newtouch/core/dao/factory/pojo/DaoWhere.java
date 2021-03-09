package com.newtouch.core.dao.factory.pojo;

import java.util.List;

import com.newtouch.core.dbconnection.handle.Sqlable;
import com.newtouch.utils.stringutil.StringUtil;

public class DaoWhere {
	private String field1 = "";
	private String field2 = "";
	private String conFlag = "";
	private Object value = "";

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getConFlag() {
		return conFlag;
	}

	public void setConFlag(String conFlag) {
		this.conFlag = conFlag;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public String joinSql(Sqlable query) {
		String sql = "";
		if ("IN".equals(this.conFlag)) {
			if (this.value == null)
				return "";
			List<Object> list = (List<Object>) this.value;
			String in = "";
			for (Object o : list) {
				in += "?, ";
				query.add(o);
			}
			if (!StringUtil.isNull(in))
				in = in.substring(0, in.length() - 2);
			return this.field1 + " IN (" + in + ") ";
		}
		if ("NOTIN".equals(this.conFlag)) {
			if (this.value == null)
				return "";
			List<Object> list = (List<Object>) this.value;
			String in = "";
			for (Object o : list) {
				in += "?, ";
				query.add(o);
			}
			if (!StringUtil.isNull(in))
				in = in.substring(0, in.length() - 2);
			return this.field1 + " NOT IN (" + in + ") ";
		}
		if ("%Like%".equals(this.conFlag)) {
			query.add("%" + this.value + "%");
			return this.field1 + " LIKE ? ";
		}
		if ("%Like".equals(this.conFlag)) {
			query.add(this.value + "%");
			return this.field1 + " LIKE ? ";
		}
		if ("Like%".equals(this.conFlag)) {
			query.add("%" + this.value);
			return this.field1 + " LIKE ? ";
		}
		if ("%NOTLIKE%".equals(this.conFlag)) {
			query.add("%" + this.value + "%");
			return this.field1 + " NOT LIKE ? ";
		}
		if ("%NOTLIKE".equals(this.conFlag)) {
			query.add("%" + this.value);
			return this.field1 + " NOT LIKE ? ";
		}
		if ("NOTLIKE%".equals(this.conFlag)) {
			query.add(this.value + "%");
			return this.field1 + " NOT LIKE ? ";
		}
		if ("=".equals(this.conFlag)) {
			if (StringUtil.isNull(this.value))
				return this.field1 + " IS NULL ";
			query.add(this.value);
			return this.field1 + " = ? ";
		}
		if ("!=".equals(this.conFlag)) {
			if (StringUtil.isNull(this.value))
				return this.field1 + " IS NOT NULL ";
			query.add(this.value);
			return this.field1 + " != ? ";
		}

		query.add(this.value);
		if ("BETWEEN".equals(this.conFlag))
			return " ? BETWEEN " + this.field1 + " AND " + this.field2;
		if ("<".equals(this.conFlag))
			return this.field1 + " < ? ";
		if (">".equals(this.conFlag))
			return this.field1 + " > ? ";
		if ("<=".equals(this.conFlag))
			return this.field1 + " <= ? ";
		if (">=".equals(this.conFlag))
			return this.field1 + " >= ? ";
		return sql;
	}
}
