package com.newtouch.utils.rule.ruleimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.excel.ExcelUtil;
import com.newtouch.utils.rule.ruleimport.sheetobject.CallParam;
import com.newtouch.utils.rule.ruleimport.sheetobject.RuleGroup;
import com.newtouch.utils.rule.ruleimport.sheetobject.RuleInfo;
import com.newtouch.utils.rule.ruleimport.sheetobject.RuleParam;
import com.newtouch.utils.uniqueseq.UniqueSeq;

public class RuleImport extends ServerBase {
	public static PrintWriter pw = null;

	public void readExcel() {
		InputStream in;
		try {
			in = new FileInputStream(
					new File(
							"D:\\规则模版.xlsx"));
			// 从excel中读取数据
			Map<String, List<Object>> resultMap = readDataFromExcel(in);
			// 保存到库表
			pw = new PrintWriter("D:\\log.sql");
			saveDataToDB(resultMap);
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private Map<String, List<Object>> readDataFromExcel(InputStream in) {
		ExcelUtil util = new ExcelUtil();
		// 规则信息
		String[] ruleInfoTitle = new String[6];
		ruleInfoTitle[0] = "ruleName";
		ruleInfoTitle[1] = "ruleMemo";
		ruleInfoTitle[2] = "ruleType";
		ruleInfoTitle[3] = "ruleDetail";
		ruleInfoTitle[4] = "dependRuleName";
		ruleInfoTitle[5] = "depntRuleType";
		// 规则参数
		String[] ruleParamTitle = new String[6];
		ruleParamTitle[0] = "ruleName";
		ruleParamTitle[1] = "paramName";
		ruleParamTitle[2] = "paramCode";
		ruleParamTitle[3] = "paramSort";
		ruleParamTitle[4] = "paramType";
		ruleParamTitle[5] = "paramOrder";
		// 规则分组
		String[] ruleGroupTitle = new String[9];
		ruleGroupTitle[0] = "funcCode";
		ruleGroupTitle[1] = "funcName";
		ruleGroupTitle[2] = "groupName";
		ruleGroupTitle[3] = "groupMemo";
		ruleGroupTitle[4] = "insurerchCode";
		ruleGroupTitle[5] = "ruleName";
		ruleGroupTitle[6] = "ruleType";
		ruleGroupTitle[7] = "scheduleOrder";
		ruleGroupTitle[8] = "exceptionHandle";
		// 调用参数
		String[] callParamTitle = new String[5];
		callParamTitle[0] = "funcCode";
		callParamTitle[1] = "groupName";
		callParamTitle[2] = "paramCode";
		callParamTitle[3] = "paramName";
		callParamTitle[4] = "paramAttribute";

		Object[] objects = new Object[4];
		objects[0] = new RuleInfo();
		objects[1] = new RuleParam();
		objects[2] = new RuleGroup();
		objects[3] = new CallParam();

		List<String[]> titles = new ArrayList<String[]>();
		titles.add(ruleInfoTitle);
		titles.add(ruleParamTitle);
		titles.add(ruleGroupTitle);
		titles.add(callParamTitle);
		Map<String, List<Object>> resultMap = util
				.initSheet4Stream(
						"D:\\规则模版.xlsx",
						objects, titles);
		return resultMap;
	}

	private void saveDataToDB(Map<String, List<Object>> resultMap) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		QuerySqlable query4Enum = DBHandleCreator.getInstance()
				.getQuerySql4Calc();
		query4Enum
				.setSql("select enum_code from t_enum where enum_name=? and insurerch_code=?");
		// 规则信息
		List<Object> ruleInfoList = resultMap.get("规则信息");
		StringBuffer ruleSql = new StringBuffer("");
		StringBuffer ruleSqlNew = new StringBuffer("");
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		for (Object obj : ruleInfoList) {
			// 规则基本信息表
			ruleSql = new StringBuffer(
					"insert into t_rules (serno,rule_no,rule_name,rule_memo, crtdate, mdfdate, crtuser, mdfuser, dataflag) values (?,?,?,?,?,?,?,?,?)");
			// 子规则信息表
			ruleSqlNew = new StringBuffer(
					"insert into t_rules_sub(serno,rule_no,rule_sub_no,rule_type,rule_detail,depend_sub_no, crtdate, mdfdate, crtuser, mdfuser, dataflag) values(?,?,?,?,?,?,?,?,?,?,?)");
			RuleInfo info = (RuleInfo) obj;
			// 规则基本信息表
			Long rule_no = UniqueSeq.getUniqueSeq("rule_no", "t_rules");
			update.setSql(ruleSql.toString());
			update.add(UniqueSeq.getUniqueSeq("serno", "t_rules"));
			update.add(rule_no);
			update.add(info.getRuleName());
			update.add(info.getRuleMemo());
			update.add(DateUtil.sysDate());
			update.add(DateUtil.sysDate());
			update.add("sysadmin");
			update.add("sysadmin");
			update.add("1");
			this.dbHandle().update(update);
			pw.write(ruleSql + ";\n");
			// 子规则信息表
			String depentcode = "''";
			if (info.getDependRuleName() != null
					&& !info.getDependRuleName().equals("")
					&& info.getDepntRuleType() != null
					&& !info.getDepntRuleType().equals("")) {
				query.setSql(" select sub.rule_sub_no from t_rules rule,t_rules_sub sub where rule.rule_no=sub.rule_no and rule.rule_name=? and sub.rule_type=? ");
				query.add(info.getDependRuleName());
				query.add(info.getDepntRuleType());
				String rulsubno = (String) this.dbHandle().query(query)
						.get("rule_sub_no");
				if (rulsubno != null && !"".equals(rulsubno))
					depentcode = rulsubno;
			}

			update.setSql(ruleSqlNew.toString());
			update.add(UniqueSeq.getUniqueSeq("serno", "t_rules_sub"));
			update.add(rule_no);
			update.add(UniqueSeq.getUniqueSeq("rule_sub_no", "t_rules_sub"));
			update.add(info.getRuleType());
			update.add(info.getRuleDetail());
			update.add(depentcode);
			update.add(DateUtil.sysDate());
			update.add(DateUtil.sysDate());
			update.add("sysadmin");
			update.add("sysadmin");
			update.add("1");
			this.dbHandle().update(update);
			pw.write(ruleSqlNew + ";\n");
		}

		// 规则参数
		List<Object> ruleParamList = resultMap.get("规则参数");
		// 规则参数信息表
		for (Object obj : ruleParamList) {
			RuleParam param = (RuleParam) obj;
			ruleSql = new StringBuffer(
					"insert into t_rules_parameter(serno,rule_no,param_no,param_name,param_sort,param_type,param_order, crtdate, mdfdate, crtuser, mdfuser, dataflag) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			query.setSql("select rule_no from t_rules where rule_name = ?");
			query.add(param.getRuleName());
			query4Enum.add(param.getParamSort());
			query4Enum.add("8");
			update.setSql(ruleSql.toString());
			update.add(UniqueSeq.getUniqueSeq("serno", "t_rules_parameter"));
			update.add(this.dbHandle().query(query).get("rule_no"));
			update.add(param.getParamCode());
			update.add(param.getParamName());
			update.add(this.dbHandle().query(query4Enum).get("enum_code"));
			query4Enum.clearParam();
			query4Enum.add(param.getParamType().substring(0,param.getParamType().indexOf("(")));
			query4Enum.add("8");
			update.add(this.dbHandle().query(query4Enum).get("enum_code"));
			update.add(param.getParamOrder());
			update.add(DateUtil.sysDate());
			update.add(DateUtil.sysDate());
			update.add("sysadmin");
			update.add("sysadmin");
			update.add("1");
			this.dbHandle().update(update);
			pw.write(ruleSql + ";\n");
		}
		// 规则分组
		List<Object> ruleGroupList = resultMap.get("规则分组");
		List<String> sqlList = new ArrayList<String>();
		for (Object obj : ruleGroupList) {
			RuleGroup group = (RuleGroup) obj;
			// 规则调用信息表
			ruleSql = new StringBuffer(
					"insert into t_rules_schedule (serno,rule_group_no,rule_sub_no,rule_type,schedule_order,exception_handle, crtdate, mdfdate, crtuser, mdfuser, dataflag) values (?,?,?,?,?,?,?,?,?,?,?)");
			// 规则分组信息表
			ruleSqlNew = new StringBuffer(
					"insert into t_rules_group(serno,function_no,function_name,rule_group_no,rule_group_name,rule_group_memo,insurerch_code, crtdate, mdfdate, crtuser, mdfuser, dataflag) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			Long rule_group_no = UniqueSeq.getUniqueSeq("rule_group_no",
					"t_rules_schedule");
			query.setSql("select sub.rule_sub_no from t_rules rule,t_rules_sub sub where rule.rule_no = sub.rule_no and rule.rule_name=?");
			query.add(group.getRuleName());
			query4Enum.add(group.getExceptionHandle());
			query4Enum.add("8");
			if (!sqlList.contains(ruleSql)) {
				update.setSql(ruleSql.toString());
				update.add(UniqueSeq.getUniqueSeq("serno", "t_rules_schedule"));
				update.add(rule_group_no);
				update.add(this.dbHandle().query(query).get("rule_sub_no"));
				update.add(group.getRuleType());
				update.add(group.getScheduleOrder());
				update.add(this.dbHandle().query(query4Enum).get("enum_code"));
				update.add(DateUtil.sysDate());
				update.add(DateUtil.sysDate());
				update.add("sysadmin");
				update.add("sysadmin");
				update.add("1");
				this.dbHandle().update(update);
				pw.write(ruleSql + ";\n");
			}
			update.setSql(ruleSqlNew.toString());
			update.add(UniqueSeq.getUniqueSeq("serno", "t_rules_group"));
			update.add(group.getFuncCode());
			update.add(group.getFuncName());
			update.add(rule_group_no);
			update.add(group.getGroupName());
			update.add(group.getGroupMemo());
			update.add(group.getInsurerchCode());
			update.add(DateUtil.sysDate());
			update.add(DateUtil.sysDate());
			update.add("sysadmin");
			update.add("sysadmin");
			update.add("1");
			this.dbHandle().update(update);
			pw.write(ruleSqlNew + ";\n");
		}
		// 调用参数
		List<Object> callParamList = resultMap.get("调用参数");
		for (Object obj : callParamList) {
			CallParam param = (CallParam) obj;
			ruleSql = new StringBuffer(
					"insert into t_rules_schedule_param(serno,rule_group_no,rule_sub_no,param_no,param_name,param_attribute, crtdate, mdfdate, crtuser, mdfuser, dataflag)values(?,?,?,?,?,?,?,?,?,?,?)");
			query.setSql("select rule_group_no from t_rules_group where rule_group_name = ?");
			query.add(param.getGroupName());
			update.setSql(ruleSql.toString());
			update.add(UniqueSeq
					.getUniqueSeq("serno", "t_rules_schedule_param"));
			update.add(this.dbHandle().query(query).get("rule_group_no"));
			query.setSql("select sch.rule_sub_no from t_rules_group rugroup ,t_rules_schedule sch where rugroup.rule_group_no = sch.rule_group_no and rugroup.function_no=?");
			query.add(param.getFuncCode());
			update.add(this.dbHandle().query(query).get("rule_sub_no"));
			update.add(param.getParamCode());
			update.add(param.getParamName());
			update.add(param.getParamAttribute());
			update.add(DateUtil.sysDate());
			update.add(DateUtil.sysDate());
			update.add("sysadmin");
			update.add("sysadmin");
			update.add("1");
			this.dbHandle().update(update);
			pw.write(ruleSql + ";\n");
		}
	}
}
