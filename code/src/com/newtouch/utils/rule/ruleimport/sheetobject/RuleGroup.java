package com.newtouch.utils.rule.ruleimport.sheetobject;

public class RuleGroup {
	// 功能编码
	private String funcCode;
	// 功能名称
	private String funcName;
	// 分组名称
	private String groupName;
	// 分组说明
	private String groupMemo;
	// 渠道类型
	private String insurerchCode;
	// 规则名称
	private String ruleName;
	// 规则类型
	private String ruleType;
	// 调用顺序
	private String scheduleOrder;
	// 异常处理方式
	private String exceptionHandle;

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupMemo() {
		return groupMemo;
	}

	public void setGroupMemo(String groupMemo) {
		this.groupMemo = groupMemo;
	}

	public String getInsurerchCode() {
		return insurerchCode;
	}

	public void setInsurerchCode(String insurerchCode) {
		this.insurerchCode = insurerchCode;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getScheduleOrder() {
		return scheduleOrder;
	}

	public void setScheduleOrder(String scheduleOrder) {
		this.scheduleOrder = scheduleOrder;
	}

	public String getExceptionHandle() {
		return exceptionHandle;
	}

	public void setExceptionHandle(String exceptionHandle) {
		this.exceptionHandle = exceptionHandle;
	}

}
