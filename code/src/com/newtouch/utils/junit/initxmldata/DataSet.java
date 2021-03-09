package com.newtouch.utils.junit.initxmldata;

import java.util.ArrayList;
import java.util.List;

import com.newtouch.core.dbconnection.handle.UpdateSqlable;

public class DataSet {
	private String dataSource = "";
	private List<UpdateSqlable> sqlsList = new ArrayList<UpdateSqlable>();

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public List<UpdateSqlable> getSqlsList() {
		return sqlsList;
	}

}
