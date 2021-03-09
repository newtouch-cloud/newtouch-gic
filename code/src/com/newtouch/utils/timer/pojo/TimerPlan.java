package com.newtouch.utils.timer.pojo;

/**
 * 调度计划类
 * 
 * 
 */
public class TimerPlan {
	// 调度名称
	private String planName = "";
	// 调度代码
	private String planCode = "";
	// 调度所对应类
	private String planModule = "";
	// 调度所对应方法mthd
	private String planMthd = "";
	// 调度运行频率(DWMY)(天、周、月、年)
	private String planFrequency = "";
	// 运行日期1、
	/*
	 * 1、D。当是天时(填写1表示每天执行) 2、W。1、2、3、4、5、6、7(周日、一、二、三、四、五、六)
	 * 3、M。1、2、3、4。。。。。。L(表示最后一天执行) 4、Y。日期格式(12-12)每年的12月12日执行
	 */
	private String planDate = "";
	// 调度运行时间(几点)
	private String planTime = "";
	// 源数据库驱动类型
	private String dbDriveName = "";
	// 源数据库连接地址
	private String dbUrl = "";
	// 源数据库用户名
	private String dbUsername = "";
	// 源数据库密码
	private String dbPassword = "";
	// 是否有效
	private String flag = "";
	// 超时时间，小时
	private double timeout = 6;

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getPlanModule() {
		return planModule;
	}

	public void setPlanModule(String planModule) {
		this.planModule = planModule;
	}

	public String getPlanMthd() {
		return planMthd;
	}

	public void setPlanMthd(String planMthd) {
		this.planMthd = planMthd;
	}

	public String getPlanFrequency() {
		return planFrequency;
	}

	public void setPlanFrequency(String planFrequency) {
		this.planFrequency = planFrequency;
	}

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getDbDriveName() {
		return dbDriveName;
	}

	public void setDbDriveName(String dbDriveName) {
		this.dbDriveName = dbDriveName;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public double getTimeout() {
		return timeout;
	}

	public void setTimeout(double timeout) {
		this.timeout = timeout;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

}
