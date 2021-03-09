package com.newtouch.core.dao.factory.imp;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.dao.factory.IDaoFactory;
import com.newtouch.core.dao.factory.pojo.DaoModel;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.freemarker.ConfigurationHelper;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.stringutil.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class DaoFactoryImp extends ServerBase implements IDaoFactory {
	private List<String> baseClmList = new ArrayList<String>();
	private List<String> table_List = new ArrayList<String>();
	private String basePath = "";

	@Override
	public void addBaseClm(String clmName) {
		this.baseClmList.add(clmName.toLowerCase());
	}

	@Override
	public void addTables(String table) {
		this.table_List.add(table.toLowerCase());
	}

	@Override
	public void generationDao(String dbSourceType, String daoDir) {
		List<DaoModel> modelList = this.queryTabList(daoDir);// 查询所有表
		try {
			basePath = Thread.currentThread().getContextClassLoader()
					.getResource("log4j.properties").getFile();
			if (basePath.indexOf("WEB-INF") > 0) {
				basePath = basePath.substring(0,
						basePath.indexOf("WebRoot/WEB-INF/classes"));// '/WEB-INF/classes'为16位
			}
			String ftlDir = basePath + "src/com/newtouch/core/dao/sample/";// 模板路径
			String ftlName = "" + "DaoSample.ftl";// 模板名称
			Ulog.debug(ftlDir);
			Ulog.debug(ftlName);
			this.generateJava(modelList, ftlDir, ftlName);// 生成DAO类
			this.baseClmList.clear();
			this.table_List.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有表，并封装成DaoModel形式返回
	 * 
	 * @param daoDir
	 * @return
	 */
	private List<DaoModel> queryTabList(String daoDir) {
		String userTable = "SELECT table_name FROM user_tables";// 查询所有表
		String tableClm = "SELECT column_name FROM user_tab_columns t WHERE t.table_name = ?";// 查询所有表的字段
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		query.setSql(userTable);
		List<Map<String, Object>> tableList = this.dbHandle().queryList(query);// 查询所有表
		List<DaoModel> modelList = new ArrayList<DaoModel>();
		List<Map<String, Object>> clmList = null;
		query.setSql(tableClm);
		String lowTabName = "";
		String lowClmName = "";
		for (String key : this.baseClmList) {
			Ulog.debug(key);
		}
		for (Map<String, Object> tableMap : tableList) {
			lowTabName = tableMap.get("table_name").toString().toLowerCase();
			if (!table_List.isEmpty() && !table_List.contains(lowTabName)) {
				continue;
			}
			DaoModel model = new DaoModel();
			model.setPackageName(daoDir);
			model.setClassName(this.parseClassName(lowTabName));
			model.setTableName(lowTabName);
			model.getBaseList().clear();
			model.getBaseList().addAll(this.baseClmList);
			query.clearParam();
			query.add(tableMap.get("table_name"));
			clmList = this.dbHandle().queryList(query);// 查询表的字段
			for (Map<String, Object> clmName : clmList) {
				lowClmName = clmName.get("column_name").toString()
						.toLowerCase();
				if (!baseClmList.contains(lowClmName))
					model.addClmList(lowClmName);
			}
			modelList.add(model);
		}
		return modelList;
	}

	/**
	 * 
	 * @param modelList
	 *            模板列表
	 * @param ftlDir
	 *            模板路径
	 * @param ftlName
	 *            模板名称
	 * @throws Exception
	 */
	private void generateJava(List<DaoModel> modelList, String ftlDir,
			String ftlName) throws Exception {
		Configuration config = ConfigurationHelper.getConfiguration(ftlDir);// 得到freemarker配置对象
		Template template = config.getTemplate(ftlName);// 得到模板
		// 设置数据
		Map<String, Object> data = new HashMap<String, Object>();
		for (DaoModel model : modelList) {
			data.put("DaoModel", model);
			// 设置输出的地址(src+包名+类名+.java)
			File output = new File(basePath + "src/"
					+ model.getPackageName().replaceAll("\\.", "/") + "/"
					+ model.getClassName() + ".java");
			Writer writer = new FileWriter(output);
			template.process(data, writer);// 生成dao类
			writer.close();
		}
	}

	/**
	 * 将类名转换为首字段大写，并且下划线后首字母大写
	 * 
	 * @param tabName
	 * @return
	 */
	private String parseClassName(String tabName) {
		String[] nameChar = tabName.split("_");
		String tableName = "";
		for (String name : nameChar) {
			tableName += StringUtil.uppFstChar(name) + "_";
		}
		return tableName.substring(0, tableName.length() - 1) + "Dao";
	}

	public static void main(String[] args) throws Exception {
		DaoModel model = new DaoModel();
		model.setPackageName("com.newtouch.px.dao");
		model.setClassName("E_RankDaoTest");
		model.setTableName("e_rank");
		model.addBaseList("serno");
		model.addBaseList("crtdate");
		model.addBaseList("mdfdate");
		model.addBaseList("crtuser");
		model.addBaseList("mdfuser");
		model.addBaseList("dataflag");
		model.addClmList("rank_id");
		model.addClmList("rank_name");
		model.addClmList("start_date");
		model.addClmList("enddate_date");
		String basePath = System.getProperty("user.dir");
		String ftlDir = basePath + "/src/com/newtouch/core/dao/sample";
		String ftlName = "DaoSample.ftl";
		Configuration config = ConfigurationHelper.getConfiguration(ftlDir);
		Template template = config.getTemplate(ftlName);
		// 设置数据
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("DaoModel", model);
		// 设置输出的地址
		File output = new File("src/"
				+ model.getPackageName().replaceAll("\\.", "/") + "/"
				+ model.getClassName() + ".java");
		Writer writer = new FileWriter(output);
		template.process(data, writer);
		writer.close();
	}
}
