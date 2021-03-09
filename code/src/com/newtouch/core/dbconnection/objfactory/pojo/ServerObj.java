package com.newtouch.core.dbconnection.objfactory.pojo;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;

/**
 * 
 * server实体类
 * 
 */
@Component("serverobj")
public class ServerObj {

	private PageCount pageCount = new PageCount();
	private Connection con;
	private User user = new User();

	private String sourceType;
	private String form = "";
	private String rqstType = "";
	private String initPage = "";
	

	public String getRqstType() {
		return rqstType;
	}

	public void setRqstType(String rqstType) {
		this.rqstType = rqstType;
	}

	@Resource(name = "dataSource")
	private DataSource dataSoruce;

	private DBHandleable dbHandle = null;

	public DBHandleable getDbHandle() {
		return dbHandle;
	}

	public void setDbHandle(DBHandleable dbHandle) {
		this.dbHandle = dbHandle;
	}

	public PageCount getPageCount() {
		return pageCount;
	}

	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}

	public Connection getConnection() {
		return con;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public DataSource getDataSoruce() {
		return dataSoruce;
	}

	public void setDataSoruce(DataSource dataSoruce) {
		this.dataSoruce = dataSoruce;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getInitPage() {
		return initPage;
	}

	public void setInitPage(String initPage) {
		this.initPage = initPage;
	}
	
	
}
