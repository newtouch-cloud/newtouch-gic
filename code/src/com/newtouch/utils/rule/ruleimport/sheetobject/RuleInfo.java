package com.newtouch.utils.rule.ruleimport.sheetobject;

public class RuleInfo {
	// 规则名称
	private String ruleName;
	// 规则说明
	private String ruleMemo;
	// 规则类型
	private String ruleType;
	// 规则内容
	private String ruleDetail;
	// 依赖规则（名称）
	private String dependRuleName;
	// 依赖规则(类型)
	private String depntRuleType;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleMemo() {
		return ruleMemo;
	}

	public void setRuleMemo(String ruleMemo) {
		this.ruleMemo = ruleMemo;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getRuleDetail() {
		return ruleDetail;
	}

	public void setRuleDetail(String ruleDetail) {
		this.ruleDetail = ruleDetail;
	}

	public String getDependRuleName() {
		return dependRuleName;
	}

	public void setDependRuleName(String dependRuleName) {
		this.dependRuleName = dependRuleName;
	}

	public String getDepntRuleType() {
		return depntRuleType;
	}

	public void setDepntRuleType(String depntRuleType) {
		this.depntRuleType = depntRuleType;
	}

}
