package com.newtouch.utils.rule;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.newtouch.newutil.ReturnMsg;
import com.newtouch.core.context.SpringContext;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.stringutil.StringUtil;

@Component
public class RuleEngine extends ServerBase {
	private static Map<String, ReturnMsg> ruleMap = new HashMap<String, ReturnMsg>();
	private static Map<String, List<Map<String, Object>>> ruleParaMap = new HashMap<String, List<Map<String, Object>>>();

	/**
	 * 
	 * @param function_no
	 *            功能编码
	 * @param insurerch_code
	 *            渠道类型
	 * @param param
	 *            入参
	 * @return
	 */
	public ReturnMsg executeRule(String function_no, String insurerch_code,
			Map<String, Object> param) {
		Map<String, Object> condition = new HashMap<String, Object>();
		// 设置查询规则的条件
		condition.put("function_no", function_no);// 功能编码
		//condition.put("insurerch_code", insurerch_code);// 渠道类型
		return this.executeRule(condition, param);
	}

	/**
	 * 
	 * @param condition
	 *            查找规则的条件,可选以下内容做为key： <br>
	 *            功能编码：function_no<br>
	 *            渠道类型：insurerch_code<br>
	 *            规则所属项目：project_no<br>
	 *            规则所属公司：company_no<br>
	 *            规则其他分类：other_sort
	 * @param param
	 *            规则需要使用到的参数<br>
	 *            使用数据库中配置入参做为key进行查询，将查找到的值做为参数调用对应方法
	 * @return
	 */
	public ReturnMsg executeRule(Map<String, Object> condition,
			Map<String, Object> param) {
		ReturnMsg retMsg = new ReturnMsg();
		if (condition.isEmpty()) {
			Message msg = new Message();
			// msg.setMsgCode(msgCode);
			msg.setMsgRemark("规则查询条件为空。");
			retMsg.setFailMessage(msg);
			return retMsg;
		}
		// 查询规则分组对应的规则
		retMsg = this.queryRules(condition);
		if (!retMsg.isSuccessflag())
			return retMsg;
		// 取到规则分组对应的规则
		List<Map<String, Object>> rulesList = retMsg.getDataList();
		// 调用分组规则中的每个规则
		return this.callRules(rulesList, param);
	}

	/**
	 * 调用规则
	 * <p>
	 * (修改返回值，一个给下一个规则使用，一个给业务逻辑使用)<br>
	 * (规则配置中需要配置下一个规则如何使用上一个规则的返回值，然后封装成MAP，传到下一个规则中)
	 * 
	 * @param rulesList
	 *            规则列表
	 * @param param
	 *            参数
	 * @return
	 */
	private ReturnMsg callRules(List<Map<String, Object>> rulesList,
			Map<String, Object> param) {
		String rule_sub_no = "";
		String rule_no = "";
		String rule_detail = "";
		ReturnMsg retMsg = new ReturnMsg();
		// 查询每个规则的入参、出参，然后进行调用
		String sql = "SELECT * FROM reg_rules_parameter trp WHERE rule_no = ? AND param_sort = ? ";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		query.setSql(sql);
		for (Map<String, Object> rule : rulesList) {
			rule_sub_no = StringUtil.trimStr(rule.get("rule_sub_no"));// 子规则编码
			rule_no = StringUtil.trimStr(rule.get("rule_no"));// 规则编码
			// 查找出参编码
			if (ruleParaMap.isEmpty() || ruleParaMap.get(rule_no) == null) {
				query.add(rule_no);
				query.add("OUTPUT");// TODO 换成常量
				// 参数编码(以MAP形式存储所有出参代码)
				ruleParaMap.put(rule_no, this.dbHandle().queryList(query));
			}

			// 非JAVA类型的规则不调用
			if (!"JAVA".equals(rule.get("rule_type"))) { // TODO 换成常量
				Message msg = new Message();
				// msg.setMsgCode(msgCode);
				msg.setMsgRemark("规则[" + rule_sub_no + "]非JAVA类型，系统未调用。");
				retMsg.setWarnMessage(msg);
				continue;
			}
			rule_detail = StringUtil.trimStr(rule.get("rule_detail")); // 规则内容
			// 调用规则
			ReturnMsg subMsg = this.callRule(rule_detail, param);
			// 将规则返回值放到Map中，供业务逻辑进行判断使用
			retMsg.getDataTable().put(rule_sub_no, subMsg);
			// 将出参放到入参的列表中，供后面的规则使用
			// TODO 根据类型匹配，不同的类型放到不同的地方
			Map<String, Object> output = new HashMap<String, Object>();
			for (Map<String, Object> map : ruleParaMap.get(rule_no)) {// 取当前规则的出参有哪些
				output.put(map.get("param_no").toString(), subMsg
						.getDataTable().get(map.get("param_no")));
			}
			param.put(rule_no, output);// 将出参放到入参的列表中，供后面的规则使用，后面参数使用入参编码，取出对应的MAP，进行使用。
			if (subMsg.isSuccessflag())
				continue;
			// 将失败信息写入返回消息中
			Message msg = new Message();
			// msg.setMsgCode(msgCode);
			msg.setMsgRemark("规则[" + rule_sub_no + "]调用失败。");
			retMsg.setFailMessage(msg);
			// 若调用未成功，并且规则的异常类型为出错不继续，则退出执行
			if ("BREAK".equals(rule.get("exception_handle"))) // TODO 换成常量
				break;
		}
		return retMsg;
	}

	/**
	 * 查询每个规则组对应的规则
	 * 
	 * @param condition
	 *            查询条件
	 * @return 需要调用 的规则
	 */
	private ReturnMsg queryRules(Map<String, Object> condition) {
		String sql = "SELECT trs.rule_group_no, trs.schedule_order, trs.exception_handle, "
				+ "          sub.rule_no, sub.rule_sub_no, sub.rule_type, sub.rule_detail "
				+ "     FROM reg_rules_group trg, reg_rules_schedule trs, reg_rules_sub sub "
				+ "    WHERE trg.rule_group_no = trs.rule_group_no "
				+ "      AND trs.rule_sub_no = sub.rule_sub_no "
				+ "      AND trs.rule_type = sub.rule_type ";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		String keyset = "";
		for (String key : condition.keySet()) {
			query.add(condition.get(key));
			sql += " AND trg." + key + " = ? ";
			keyset += condition.get(key) + "、";
			// break;
		}
		if (!ruleMap.isEmpty() && ruleMap.get(keyset) != null) {
			return ruleMap.get(keyset);
		}
		sql += " ORDER BY trs.schedule_order";
		query.setSql(sql);
		List<Map<String, Object>> rulesList = this.dbHandle().queryList(query);
		ReturnMsg retMsg = new ReturnMsg();
		if (rulesList.isEmpty()) {
			Message msg = new Message();
			// msg.setMsgCode(msgCode);
			msg.setMsgRemark("根据[" + keyset.substring(0, keyset.length() - 1)
					+ "]未查找到对应的规则组。");
			retMsg.setFailMessage(msg);
			return retMsg;
		}
		retMsg.setDataList(rulesList);
		ruleMap.put(keyset, retMsg);
		return retMsg;
	}

	/**
	 * 根据子编码调用对应规则
	 * 
	 * 
	 * @param rule_detail
	 *            规则内容
	 * @param param
	 *            参数
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("rawtypes")
	private ReturnMsg callRule(String rule_detail, Map<String, Object> param) {
		String[] array = rule_detail.split("\\.");
		Object obj = SpringContext.getBean(array[array.length - 2]);
		Object[] paraValues = new Object[] { param };
		Class[] paraTypes = new Class[] { Map.class };
		ReturnMsg msg = new ReturnMsg();
		try {
			Method method = obj.getClass().getMethod(array[array.length - 1],
					paraTypes);
			msg = (ReturnMsg) method.invoke(obj, paraValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return msg;
	}
}
