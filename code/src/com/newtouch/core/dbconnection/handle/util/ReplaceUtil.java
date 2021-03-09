package com.newtouch.core.dbconnection.handle.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.newtouch.core.context.SpringContext;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.core.view.jsptag.select.SelectTag;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.stringutil.StringUtil;

public class ReplaceUtil {
	private static boolean onlyDisplayName = true;// 为true时只显示名称，为flase时显示代码-名称
	private static Map<String, Map<String, Object>> ENUM_MAP = new HashMap<String, Map<String, Object>>();
	private static SelectTag selectTag = null;

	/**
	 * 根据字段类型将查询结果转换为对应类型，保存到Map中
	 * 
	 * @param rsmd
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Object> initResult(ResultSetMetaData rsmd,
			ResultSet rs) throws SQLException {
		Map<String, Object> resultSetTable = new LinkedHashMap<String, Object>();
		// System.out.print("ReplaceUtil|");
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			int dataType = rsmd.getColumnType(i);
			// System.out.print("[" + rsmd.getColumnName(i).toLowerCase() + "="
			// + rs.getString(i) + "]\t");
			if (dataType == Types.TIMESTAMP) {
				java.sql.Timestamp timestamp = rs.getTimestamp(i);
				if (timestamp == null) {
					continue;
				}
				String t = timestamp.toString();
				if (t.endsWith("00:00:00.0")) {
					resultSetTable.put(rsmd.getColumnName(i).toLowerCase(),
							DateUtil.string2Date(t.substring(0, 10)));
					continue;
				}
				resultSetTable.put(rsmd.getColumnName(i).toLowerCase(),
						timestamp);
				continue;
			}
			if (dataType == Types.DATE) {
				java.sql.Date date = rs.getDate(i);
				if (date != null) {
					resultSetTable.put(rsmd.getColumnName(i).toLowerCase(),
							date);
				}
				continue;
			}
			if (dataType == Types.NUMERIC) {
				if ("rate".equals(rsmd.getColumnName(i).toLowerCase())) {
					resultSetTable.put(rsmd.getColumnName(i).toLowerCase(),
							rs.getDouble(i));
					continue;
				}
				String retVal = rs.getString(i);
				retVal = retVal == null ? "" : retVal;
				String clmName = rsmd.getColumnName(i).toLowerCase();
				resultSetTable.put(clmName, retVal);
				// 替换枚举、部门、职级等信息
				Map<String, Object> valName = ReplaceUtil.replaceEnum(clmName,
						retVal);
				if (valName != null && !valName.isEmpty()) {
					resultSetTable.putAll(valName);
				}
				continue;
			}
			if (dataType == Types.CLOB) {
				Clob clob = rs.getClob(i);
				String clobValue = "";
				if (clob != null) {
					Reader reader = clob.getCharacterStream();
					char[] c = new char[(int) clob.length()];
					try {
						reader.read(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
					clobValue = new String(c);
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				resultSetTable.put(rsmd.getColumnName(i).toLowerCase(),
						clobValue);
				continue;
			}
			String retVal = StringUtil.trimStr(rs.getString(i));
			retVal = retVal == null ? "" : retVal;
			String clmName = rsmd.getColumnName(i).toLowerCase();
			resultSetTable.put(clmName, retVal);
			// 替换枚举、部门、职级等信息
			Map<String, Object> valName = ReplaceUtil.replaceEnum(clmName,
					retVal);
			if (valName != null && !valName.isEmpty()) {
				resultSetTable.putAll(valName);
				continue;
			}
			if (clmName.indexOf("#") >= 0) {
				clmName = clmName.substring(clmName.lastIndexOf("#") + 1,
						clmName.length());
				resultSetTable.put(clmName, retVal);
			}
		}
		// Ulog.debug("");
		return resultSetTable;
	}

	/**
	 * 根据字段类型，将查询结果转换为对应的类型，直接返回
	 * 
	 * @param rsmd
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static Object initResult4Object(ResultSetMetaData rsmd, ResultSet rs)
			throws SQLException {
		Object value = new Object();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			int dataType = rsmd.getColumnType(i);

			if (dataType == Types.TIMESTAMP) {
				java.sql.Timestamp timestamp = rs.getTimestamp(i);
				if (timestamp != null) {
					value = timestamp;
				}
				continue;
			}
			if (dataType == Types.DATE) {
				java.sql.Date date = rs.getDate(i);
				if (date != null) {
					value = date;
				}
				continue;
			}
			String retVal = rs.getString(i);
			retVal = retVal == null ? "" : retVal;
			value = retVal;
		}
		return value;
	}

	public static String replaceSql(Object[] paramList, String sql) {
		if (paramList == null || paramList.length == 0) {
			return sql;
		}
		int size = paramList.length;
		ReplaceUtil util = new ReplaceUtil();
		for (int i = 0; i < size; i++) {
			Object obj = paramList[i];
			if (obj instanceof String) {
				obj = util.replaceSpecialChar((String) obj);
				if (obj.toString().toUpperCase().contains("SELECT ")) {
					sql = sql.replaceFirst("\\?", obj.toString());
					continue;
				}
				sql = sql.replaceFirst("\\?", "'" + obj.toString() + "'");
				continue;
			}
			if (obj instanceof Integer) {
				sql = sql.replaceFirst("\\?", String.valueOf(obj));
				continue;
			}
			if (obj instanceof Timestamp) {
				sql = sql.replaceFirst("\\?",
						"to_timestamp('" + String.valueOf(obj)
								+ "','yyyy-MM-dd hh24:mi:ss.ff')");
				continue;
			}
			if (obj instanceof Date) {
				sql = sql.replaceFirst("\\?", "to_date('" + String.valueOf(obj)
						+ "','yyyy-MM-dd')");
				continue;
			}
			sql = sql.replaceFirst(
					"\\?",
					"'"
							+ (StringUtil.trimStr(obj)).replaceAll("'",
									"' || chr(39) || '") + "'");
		}
		sql = util.revertSpecialChar(sql);
		return sql;
	}

	/**
	 * 替换特殊字符
	 * 
	 * @param arg
	 * @return
	 */
	public String replaceSpecialChar(String arg) {
		arg = arg.replaceAll("\\?", "？");
		arg = arg.replaceAll("\\$", String.valueOf(0x07));
		arg = arg.replaceAll(";", "");
		arg = arg.replaceAll("--", "");
		arg = arg.replaceAll("\\\\", "\\\\\\\\");
		arg = ReplaceUtil.replaceSpace(arg);
		// 如果是管理机构的子查询，则不替换单引号
		String _arg = arg.toUpperCase();
		if (_arg.contains("SELECT TM") && _arg.contains(" DATA_AUTH_NO FROM ")
				&& _arg.contains(" TM, T_DATA_AUTH TR WHERE TM.")
				&& _arg.contains(" = TR.DATA_AUTH_NO AND TR.OBJECT_NO = ")
				&& _arg.contains(" AND TR.DATA_AUTH_TYPE = ")) {
			return arg;
		}
		arg = arg.replaceAll("'", "");
		return arg;
	}

	/**
	 * 将特殊字符替换回来
	 * 
	 * @param arg
	 * @return
	 */
	public String revertSpecialChar(String arg) {
		arg.replaceAll(String.valueOf(0x07), "\\$");
		return arg;
	}

	public static Map<String, Object> replaceEnum(String clmName, String value) {
		if (StringUtil.isNull(value)) {
			return null;
		}
		if (clmName.indexOf("#") < 0) {
			return null;
		}
		String startWith = clmName.substring(0, clmName.lastIndexOf("#"));// re/rd/rr
		ServerBase server = (ServerBase) SpringContext.getBean("serverbase");
		String deptType = server.getThreadLocal().get().getUser()
				.getDept_type();
		// 从map中取值
		Map<String, Object> retStr = ENUM_MAP.get(deptType + startWith + value);
		if (retStr != null && !retStr.isEmpty()) {
			if (onlyDisplayName) {
				retStr = ENUM_MAP.get(deptType + startWith + value
						+ "_ONLY_NAME");
			}
			// 机构不返回编码
			if (clmName.startsWith("rd#")) {
				return retStr;
			}
			if (clmName.startsWith("re#") || clmName.startsWith("rr#")
					|| clmName.startsWith("ren#")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(clmName.substring(clmName.lastIndexOf("#") + 1,
						clmName.length()), retStr.get(clmName.substring(
						clmName.indexOf("#") + 1, clmName.lastIndexOf("#"))));
				return map;
			}
			return retStr;
		}

		// 若值不存在则初始化之后再取值
		ReplaceUtil.initSelectTag();// 初始化取值对象
		// 根据不同的类型初始化取值列表，re_:枚举；rr_:职级；rd_:管理机构
		String queryId = "";
		String classPath = "";
		if (clmName.startsWith("re#")) {
			queryId = clmName.substring(clmName.indexOf("#") + 1,
					clmName.lastIndexOf("#"));
			classPath = "selectServiceImp";
		}
		if (clmName.startsWith("ren#")) {
			queryId = clmName.substring(clmName.indexOf("#") + 1,
					clmName.lastIndexOf("#"));
			classPath = "CustomSelect";
		}
		if (clmName.startsWith("rr#")) {
			// 职级代码，职级序列
			queryId = value;
			classPath = "select4RankServiceImp";
		}
		if (clmName.startsWith("rd#"))
			classPath = "deptSelectServiceImp";
		if (clmName.startsWith("ro#")) {
			queryId = value;
			if ("01".equals(deptType)) {
				classPath = "selectDeptServiceImp";
			} else if ("02".equals(deptType)) {
				classPath = "selectXCDeptServiceImp";
			} else if ("03".equals(deptType)) {
				classPath = "selectQHDeptServiceImp";
			}
		}
		if (clmName.startsWith("rc#")) {
			String confirmRank[] = value.split("@");
			if (confirmRank.length > 1) {
				queryId = confirmRank[1];
				value = confirmRank[1];
				classPath = "select4RankServiceImp";
			} else {
				queryId = value;
				classPath = "selectConfirmServiceImp";
			}
		}
		if (clmName.startsWith("rl#")) {
			String confirmRank[] = value.split("@");
			if (confirmRank.length > 1) {
				queryId = confirmRank[1];
				value = confirmRank[1];
				classPath = "selectRankLevelServiceImp";
			} else {
				queryId = value;
				classPath = "selectLevelServiceImp";
			}
		}
		if ("".equals(classPath))
			return null;
		ReplaceUtil.initEnumValue(queryId, classPath, clmName, startWith);
		// 机构不返回编码
		if (clmName.startsWith("rd#")) {
			if (onlyDisplayName) {
				return ENUM_MAP
						.get(deptType + startWith + value + "_ONLY_NAME");
			}
		}
		retStr = ENUM_MAP.get(deptType + startWith + value);
		if (onlyDisplayName) {
			retStr = ENUM_MAP.get(deptType + startWith + value + "_ONLY_NAME");
		}
		if (retStr == null || retStr.isEmpty()) {
			return null;
		}
		if (clmName.startsWith("ren#") || clmName.startsWith("re#")
				|| clmName.startsWith("rr#")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(clmName.substring(clmName.lastIndexOf("#") + 1,
					clmName.length()), retStr.get(clmName.substring(
					clmName.indexOf("#") + 1, clmName.lastIndexOf("#"))));
			return map;
		}
		return retStr;

	}

	/**
	 * 
	 * @param queryId
	 *            枚举型查询的父节点
	 * @param classPath
	 *            要调用的类
	 * @param clmName
	 *            查询的字段名，用来替换
	 * @param startWith
	 *            第一个#号开始之前内容
	 */
	private static void initEnumValue(Object queryId, String classPath,
			String clmName, String startWith) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryId", queryId);
		map.put("custom_no", "enum");
		map.put("up_no", queryId);
		ServerBase server = (ServerBase) SpringContext.getBean("serverbase");
		String deptType = server.getThreadLocal().get().getUser()
				.getDept_type();
		map.put("dept_type", "00");
		List<Map<String, Object>> listMap = selectTag.queryList(map, classPath);
		for (Map<String, Object> valueMap : listMap) {
			if ("6".equals(valueMap.get("code").toString())) {
				System.out.println("11");
			}
			Map<String, Object> olName = new HashMap<String, Object>();
			olName.putAll(valueMap);
			olName.remove("code");
			olName.remove("rn");
			olName.remove("name");// 移动当前查询的code、name，只保留上级的code、name
			if (clmName.startsWith("re#") || clmName.startsWith("rr#")
					|| clmName.startsWith("ren#"))
				olName.put(
						clmName.substring(clmName.indexOf("#") + 1,
								clmName.lastIndexOf("#")), valueMap.get("name"));
			else
				olName.put(
						clmName.substring(clmName.lastIndexOf("#") + 1,
								clmName.length()), valueMap.get("name"));
			ENUM_MAP.put(deptType + startWith + valueMap.get("code")
					+ "_ONLY_NAME", olName);

			Map<String, Object> numName = new HashMap<String, Object>();
			numName.putAll(valueMap);
			numName.remove("code");
			numName.remove("rn");
			numName.remove("name");// 移动当前查询的code、name，只保留其他属性\

			if (clmName.startsWith("re#") || clmName.startsWith("rr#")
					|| clmName.startsWith("ren#"))
				numName.put(
						clmName.substring(clmName.indexOf("#") + 1,
								clmName.lastIndexOf("#")), valueMap.get("code")
								+ "-" + valueMap.get("name"));
			else
				numName.put(
						clmName.substring(clmName.lastIndexOf("#") + 1,
								clmName.length()), valueMap.get("name"));
			ENUM_MAP.put(deptType + startWith + valueMap.get("code"), numName);
		}

	}

	private static void initSelectTag() {
		if (selectTag == null) {
			selectTag = new SelectTag();
		}
	}

	public static String replaceSpace(String string) {
		while (string.indexOf("\t") >= 0) {
			string = string.replaceAll("\t", " ");
		}
		while (string.indexOf("\n") >= 0) {
			string = string.replaceAll("\n", " ");
		}
		while (string.indexOf("\r") >= 0) {
			string = string.replaceAll("\r", " ");
		}
		while (string.indexOf("  ") >= 0) {
			string = string.replaceAll("  ", " ");
		}
		return string;
	}

	public static void main(String args[]) {
		String arg = "test%' and 1=1 and '%'='";
		arg = arg.replaceAll("'", "");
		arg = arg.replaceAll(";", "");
		arg = arg.replaceAll("--", "");
		Ulog.debug(arg);
	}

}
