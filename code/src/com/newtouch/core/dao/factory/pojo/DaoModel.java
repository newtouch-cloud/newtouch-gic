package com.newtouch.core.dao.factory.pojo;

import java.util.ArrayList;
import java.util.List;

public class DaoModel {
	private String packageName = "";

	private String className = "";

	private String tableName = "";

	private List<String> clmList = new ArrayList<String>();

	private List<String> baseList = new ArrayList<String>();

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getClmList() {
		return clmList;
	}

	public void setClmList(List<String> clmList) {
		this.clmList = clmList;
	}

	public void addClmList(String clmList) {
		this.clmList.add(clmList);
	}

	public List<String> getBaseList() {
		return baseList;
	}

	public void setBaseList(List<String> baseList) {
		this.baseList = baseList;
	}

	public void addBaseList(String baseClm) {
		this.baseList.add(baseClm);
	}
}
