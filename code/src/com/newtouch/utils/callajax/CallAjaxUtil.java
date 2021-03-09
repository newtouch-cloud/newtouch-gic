package com.newtouch.utils.callajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newtouch.utils.stringutil.StringUtil;

public class CallAjaxUtil {
	/**
	 * 将List结果转换为SELECT需要的MAP
	 * 
	 * @param slctList
	 *            查询结果
	 * @param optName
	 *            需要显示在option中的TEXT，在slctList中所对应的KEY
	 * @param optValue
	 *            option中的VALUE，在slctList中所对应的KEY
	 * @return
	 */
	public static Map<String, Object> list2SelectMap(
			List<Map<String, Object>> slctList, String optName, String optValue) {
		Map<String, Object> select = new HashMap<String, Object>();
		for (Map<String, Object> option : slctList) {
			select.put(StringUtil.trimStr(option.get(optValue)),
					StringUtil.trimStr(option.get(optName)));
		}
		return select;

	}

	public void test() {
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("ajaxtype", "focus");
		// map.put("total_prem", "none");
		// policyList.add(map);

		// Map<String, Object> msg = new HashMap<String, Object>();
		// msg.put("ajaxtype", "alert");
		// msg.put("msg1", "提示信息1.");
		// msg.put("msg2", "提示信息2.");
		// msg.put("msg3", "提示信息3.");
		// msg.put("msg4", "提示信息4.");
		// msg.put("msg5", "提示信息5.");
		// policyList.add(msg);

		// Map<String, Object> style = new HashMap<String, Object>();
		// style.put("ajaxtype", "style");
		// style.put("netcom_name", "FF0000");
		// policyList.add(style);
		//
		// Map<String, Object> readonly = new HashMap<String, Object>();
		// readonly.put("ajaxtype", "disabled");
		// readonly.put("netcom_name", "none");
		// readonly.put("canel_insurance", "退保");
		// policyList.add(readonly);
		//
		// Map<String, Object> edit = new HashMap<String, Object>();
		// edit.put("ajaxtype", "edit");
		// edit.put("qu_team_code", "none");
		// edit.put("qu_team_name", "退保");
		// policyList.add(edit);
		//
		// Map<String, Object> select = new HashMap<String, Object>();
		// select.put("ajaxtype", "select");
		// Map<String,Object> end_tyep = new HashMap<String, Object>();
		// end_tyep.put("0001", "没有这个类型");
		// end_tyep.put("0002", "有这个类型");
		// end_tyep.put("0003", "真没有这个类型");
		// end_tyep.put("0004", "真有这个类型");
		// select.put("end_type", end_tyep);
		//
		// Map<String,Object> tyep = new HashMap<String, Object>();
		// tyep.put("0011", "不能投诉");
		// tyep.put("0012", "能投诉");
		// tyep.put("0013", "真不能投诉");
		// tyep.put("0014", "真能投诉");
		// tyep.put("selected", "0013");
		// select.put("type", tyep);
		// policyList.add(select);
	}
}
