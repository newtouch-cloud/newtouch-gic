package com.newtouch.utils.junit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class JUnitDataSource implements DataSource {

	/* 当前数据库类型 */
	private static String DriveName = "";

	private static String Url = "";

	private static String Username = "";

	private static String Password = "";

	private static Connection con = null;
	static {
		getDatabaseConfig();
	}

	public Connection getConnection() throws SQLException {
		try {

			/* 加载一个数据库驱动 */
			Class.forName(DriveName).newInstance();
			/* 建立一个数据库连接 */
			con = DriverManager.getConnection(Url, Username, Password);
			/* 设置事务提交方式，true为系统自动提交，false为手工提交 */
			con.setAutoCommit(true);
			System.out.println("********与数据库连接成功**************");
		} catch (SQLException e) {
			// 捕捉SQL违例
			System.out.println("在连接数据库时捕获");
			while (e != null) {
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("Message : " + e.getMessage());
				System.out.println("Vendor  : " + e.getErrorCode());
				e = e.getNextException();
			}
			con = null;
		} catch (ClassNotFoundException er) {
			er.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("连接成功[" + Url + "][" + Username + "|" + Password
				+ "]");
		return con;
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

	public PrintWriter getLogWriter() throws SQLException {

		return null;
	}

	public int getLoginTimeout() throws SQLException {

		return 0;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	public void setLoginTimeout(int seconds) throws SQLException {
	}

	/* 加载数据库连接配置文件 */
	private static void getDatabaseConfig() {
		InputStreamReader inputReader = null;
		BufferedReader br = null;
		InputStream input = null;

		try {
			// 后台自己使用
			input = ClassLoader
					.getSystemResourceAsStream("jdbc_postgresql.properties");
			Properties perties = new Properties();
			perties.load(input);
			DriveName = (String) perties
					.get("hibernate.connection.driver_class");
			Url = (String) perties.get("hibernate.connection.url");
			Username = (String) perties.get("hibernate.connection.username");
			Password = (String) perties.get("hibernate.connection.password");
			perties.clear();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (inputReader != null) {
					inputReader.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
}
