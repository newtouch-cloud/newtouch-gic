package com.newtouch.utils.junit.initxmldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.newtouch.core.dbconnection.SpringFactory;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.stringutil.StringUtil;

/**
 * 主函数
 * 
 * 
 */
public class InitXmlData extends ServerBase {
	private static Map<String, DBHandleable> DB_MAP = new HashMap<String, DBHandleable>();

	/**
	 * 初始化数据。不删除数据
	 * 
	 * @param patch
	 *            相对路径，包含文件名和扩展名。不能以"/"开始。
	 */
	public void initData(String patch) {
		initData(patch, false);
	}

	/**
	 * 初始化数据。自动删除数据
	 * 
	 * @param patch
	 *            相对路径，包含文件名和扩展名。不能以"/"开始。
	 * @param isDelData
	 *            是否删除数据<br>
	 *            true：删除tabel标签中数据<br>
	 *            false：不删除table标签中数据
	 */
	public void initData(String patch, boolean isDelData) {
		if (patch.startsWith("/")) {
			patch = patch.substring(1, patch.length());
		}
		try {
			String patch1 = URLDecoder.decode(InitXmlData.class
					.getProtectionDomain().getCodeSource().getLocation()
					.getFile(), "UTF-8")
					+ patch;
			this.impDatabase(patch1, isDelData);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化数据。不删除数据<br>
	 * 如果xml文件与类在同级目录下，并且文件名相同，可使用此方法。
	 * 
	 * @param clazz
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void initData(Class clazz) {
		initData(clazz, false);
	}

	/**
	 * 初始化数据。自动删除数据<br>
	 * 如果xml文件与类在同级目录下，并且文件名相同，可使用此方法。
	 * 
	 * @param clazz
	 *            与此类同名的XML文件
	 * @param delDate
	 *            删除数据<br>
	 *            true：删除<br>
	 *            false：
	 */

	@SuppressWarnings("rawtypes")
	public void initData(Class clazz, boolean delDate) {
		try {
			String patch = URLDecoder.decode(clazz.getProtectionDomain()
					.getCodeSource().getLocation().getFile(), "UTF-8")
					+ clazz.getCanonicalName().replaceAll("\\.", "/") + ".xml";
			this.impDatabase(patch, delDate);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param patch
	 *            将得到的数据，存入库中
	 * @param isDelete
	 *            删除数据<br>
	 *            true：删除<br>
	 *            false：
	 */
	private void impDatabase(String patch, boolean isDelete) {
		// 数据集
		List<DataSet> dataset = this.getXmlData(patch, isDelete);
		// 保存数据信息
		this.saveData(dataset);
	}

	/**
	 * 保存XML中数据
	 * 
	 * @param dataSetList
	 *            sql列表
	 */
	private void saveData(List<DataSet> dataSetList) {
		for (DataSet dataSet : dataSetList) {
			this.saveData(dataSet);
		}
	}

	private void saveData(DataSet dataSet) {
		DBHandleable db = this.initDBHandle(dataSet.getDataSource());
		for (UpdateSqlable update : dataSet.getSqlsList()) {
			try {
				System.out.println(db.update(update));
			} catch (RuntimeException e) {
				if (update.getSql().toLowerCase().indexOf("drop table") >= 0) {
					continue;
				}
				throw e;
			}
		}
	}

	/**
	 * 提交由InitXmlData创建的数据库连接(除defaultDataSource外的所有链接)
	 */
	public void commit() {
		// 将当前连接设置为默认连接
		// this.setDBHandle(DB_MAP.get("defaultDataSource"));
		// 不关闭默认连接，只关闭自己创建的连接
		// DB_MAP.remove("defaultDataSource");
		for (DBHandleable db : DB_MAP.values()) {
			db.commitTransaction();
		}

	}

	/**
	 * 初始化数据库操作对象
	 * 
	 * @param dbSourceType
	 *            数据源类型
	 * @param dataSource
	 *            数据源对象
	 * @param dbHandle
	 *            数据库操作对象
	 */
	private synchronized DBHandleable initDBHandle(String dbSourceType) {
		if (DB_MAP.isEmpty()) {
			DB_MAP.put("defaultDataSource", this.dbHandle());
		}
		if ("".equals(dbSourceType)) {
			dbSourceType = "defaultDataSource";
		}
		if (DB_MAP.get(dbSourceType) != null) {
			DB_MAP.get(dbSourceType).startTransaction();
			return DB_MAP.get(dbSourceType);
		}
		ThreadLocal<ServerObj> uamsTL = new ThreadLocal<ServerObj>();
		uamsTL.set(new ServerObj());
		uamsTL.get().setSourceType(dbSourceType);
		uamsTL.get().setDataSoruce(
				SpringFactory.getInstance().getDataSource(dbSourceType));
		DBHandleable dbHandle = DBHandleCreator.getInstance().getDBHandle(
				uamsTL);
		dbHandle.startTransaction();
		DB_MAP.put(dbSourceType, dbHandle);
		return dbHandle;
	}

	/**
	 * 得到xml的数据，返回节点集
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<DataSet> getXmlData(String patch, boolean isDelete) {
		List<DataSet> sqlList = new ArrayList<DataSet>();
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		try {
			doc = (Document) sb.build(new FileInputStream(patch));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		// 得到dataset
		List<Element> rootList = (List<Element>) root.getChildren();
		Element element = null;
		for (Element dataSet : rootList) {
			DataSet aDataSet = new DataSet();
			aDataSet.setDataSource(dataSet.getAttribute("datasource") != null ? dataSet
					.getAttribute("datasource").getValue() : "");
			element = dataSet.getChild("sqls");
			if (element != null) {
				// 需要先执行了SQL，再继续，如果表更新，后续操作会报错
				this.initSqls(element.getChildren("ddlsql"), aDataSet);
				this.saveData(aDataSet);
				// 将执行过的SQL清空，不再执行
				aDataSet.getSqlsList().clear();
				this.initSqls(element.getChildren("dmlsql"), aDataSet);
			}
			element = dataSet.getChild("tables");
			this.initTables(element, aDataSet, isDelete);
			sqlList.add(aDataSet);
		}
		return sqlList;
	}

	/**
	 * 初始化sql文件
	 * 
	 * @param element
	 *            存放SQL文件的节点
	 * 
	 */
	private void initSqls(List<Element> element, DataSet aDataSet) {
		if (element == null) {
			return;
		}
		for (Element sql : element) {
			UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
			update.setSql(sql.getValue());
			aDataSet.getSqlsList().add(update);
		}
	}

	/**
	 * 初始化tables节点
	 * 
	 * @param element
	 *            存放table的文件
	 * @param aDataSet
	 *            存放SQL数据的对象
	 * @param isDelete
	 *            是否创建删除语句
	 *            <p>
	 *            true：创建删除语句<br>
	 *            false：不创建删除语句
	 */
	@SuppressWarnings("unchecked")
	private void initTables(Element element, DataSet aDataSet, boolean isDelete) {
		if (element == null) {
			return;
		}
		List<Element> elemntList = (List<Element>) element.getChildren();
		// 表字段类型
		Map<String, Integer> types = new HashMap<String, Integer>();
		String tableName = "";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		for (Element item : elemntList) {
			// item.getName()获得表名。如果表名和已有的表名不相同，则查询字段类型
			if (!item.getName().equals(tableName)) {
				tableName = item.getName();
				query.setSql("SELECT * FROM  " + tableName + " WHERE 1<>1");
				// 查询表中字段类型
				types = this.initDBHandle(aDataSet.getDataSource())
						.queryMetaData(query);
			}
			List<Attribute> oneAnnal = item.getAttributes();

			if (isDelete) {
				aDataSet.getSqlsList().addAll(
						this.joinDelSql(tableName, types, oneAnnal,
								item.getChildren()));
			}
			// 拼接SQL
			aDataSet.getSqlsList().add(
					this.joinInsSql(tableName, types, oneAnnal));
		}
	}

	/**
	 * 拼接删除SQL语句
	 * 
	 * @param tableName
	 *            表名
	 * @param types
	 *            字段类型
	 * @param oneAnnal
	 *            字段值
	 * @param delList
	 *            删除条件
	 * @return 删除语句列表
	 * @throws Exception
	 *             日志转换错误
	 */
	private List<UpdateSqlable> joinDelSql(String tableName,
			Map<String, Integer> types, List<Attribute> oneAnnal,
			List<Element> delList) {
		List<UpdateSqlable> delSqlList = new ArrayList<UpdateSqlable>();
		// 为空时则添加所有语句为删除语句
		if (delList.isEmpty()) {
			delSqlList.add(this.initDelWhere(tableName, types,
					new ArrayList<String>(), oneAnnal));
		}
		for (Element delElmt : delList) {
			delSqlList.add(this.initDelWhere(tableName, types,
					StringUtil.array2List(delElmt.getValue().split(",")),
					oneAnnal));
		}
		return delSqlList;
	}

	private UpdateSqlable initDelWhere(String tableName,
			Map<String, Integer> types, List<String> delWhereList,
			List<Attribute> oneAnnal) {
		String fieldName = "";
		String deleteWhere = "";
		List<Object> valueList = new ArrayList<Object>();
		// 如果删除列表为空，则表示where中需要加入所有字段，初始化删除列表
		if (delWhereList.isEmpty()) {
			for (Attribute annal : oneAnnal) {
				delWhereList.add(annal.getName());
			}
		}
		int i = 0;
		// 所有字段列表
		for (Attribute annal : oneAnnal) {
			if (annal.getValue() == null || annal.getValue().equals("")) {
				continue;// 值为空则结束本次
			}
			fieldName = annal.getName().toUpperCase();
			// 如果已经循环够删除列表中的次数，则不再循环
			if (i >= delWhereList.size()) {
				break;
			}
			// 如果删除列表里没有此字段，则不加到删除语句后
			if (!delWhereList.contains(annal.getName())) {
				continue;// 不需要删除则结束本次
			}
			i++;// 到此处表示肯定会赋值，每赋值一次，就加1。
			// 如果没有找到字段，则抛出错误
			if (types.get(fieldName) == null) {
				throw new RuntimeException("在[" + tableName + "]表未找到["
						+ fieldName + "]字段，请检查XML数据文件。");
			}
			// 加where子句
			deleteWhere += " " + fieldName + " = ? AND ";
			// 对删除条件赋值
			// 日期类型
			if (types.get(fieldName) == Types.DATE) {
				// 精确到秒的日期
				if (annal.getValue().indexOf(":") >= 0) {
					valueList.add(DateUtil.string2Timestamp(annal.getValue()));
					continue;// 赋值后则结束本次,以免重复赋值
				}
				// 精确到天的日期
				valueList.add(DateUtil.string2Date(annal.getValue()));
				continue;// 赋值后则结束本次,以免重复赋值
			}
			// Double类型
			if (types.get(fieldName) == Types.DOUBLE) {
				valueList.add(Double.parseDouble(annal.getValue()));
				continue;
			}
			// 整型
			if (types.get(fieldName) == Types.INTEGER) {
				valueList.add(Integer.parseInt(annal.getValue()));
				continue;
			}
			// 非日期类型
			valueList.add(annal.getValue());
		}
		String sql = "";
		if (deleteWhere.length() >= 3) {
			sql = "DELETE FROM " + tableName + " WHERE "
					+ deleteWhere.substring(0, deleteWhere.length() - 4);
		} else {
			sql = "DELETE FROM " + tableName + " WHERE 1 = 1";
		}
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		update.setSql(sql);
		update.setParameter(valueList.toArray());
		return update;
	}

	/**
	 * 拼接插入SQL语句
	 * 
	 * @param tableName
	 *            表名
	 * @param types
	 *            此名的字段类型
	 * @param oneAnnal
	 *            一条记录
	 * @return 拼接好的Sql
	 * @throws Exception
	 */
	private UpdateSqlable joinInsSql(String tableName,
			Map<String, Integer> types, List<Attribute> oneAnnal) {
		String fieldNameList = "";
		String fieldName = "";
		String value = "";
		List<Object> valueList = new ArrayList<Object>();
		for (Attribute annal : oneAnnal) {
			fieldName = annal.getName().toUpperCase();
			if (fieldName == null || "".equals(fieldName)) {
				continue;
			}
			fieldNameList += annal.getName() + ", ";
			value += "?, ";
			if (types.get(fieldName) == null) {
				throw new RuntimeException("在[" + tableName + "]表未找到["
						+ fieldName + "]字段，请检查XML数据文件。");
			}
			if (types.get(fieldName) == Types.DATE) {
				if (annal.getValue().indexOf(":") >= 0) {
					valueList.add(DateUtil.string2Timestamp(annal.getValue()));
					continue;
				}
				valueList.add(java.sql.Date.valueOf(annal.getValue()));
				continue;
			}
			// Double类型
			if (types.get(fieldName) == Types.DOUBLE) {
				valueList.add(Double.parseDouble(annal.getValue()));
				continue;
			}
			// 整型
			if (types.get(fieldName) == Types.INTEGER) {
				valueList.add(Integer.parseInt(annal.getValue()));
				continue;
			}
			// 非日期类型
			valueList.add(annal.getValue());
		}
		String sql = "insert into " + tableName + "("
				+ fieldNameList.substring(0, fieldNameList.length() - 2)
				+ ") values(" + value.substring(0, value.length() - 2) + ")";
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		update.setSql(sql);
		update.setParameter(valueList.toArray());
		return update;
	}

	public static void main(String[] args) {

		// ServerFactory ser = ServerFactory.getServerFactory();
		// // ser.setDataSource(new JUnitDataSource());
		// InitXmlData d = (InitXmlData) ser.getInstance(InitXmlData.class
		// .getName());
		InitXmlData d = new InitXmlData();
		d.initData(InitXmlData.class);
	}

}
