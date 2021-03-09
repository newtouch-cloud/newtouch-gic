package com.newtouch.utils.callajax;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.context.SpringContext;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.stringutil.StringUtil;

@Controller
public class CallAjaxController extends BaseController {
	/**
	 * 通用调用Ajax方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/callAjax.do")
	public ModelAndView doCallAjax(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> dataMap = this.getRequestMap(request);
		String classname = StringUtil.trimStr(dataMap.get("classname"));
		String method = StringUtil.trimStr(dataMap.get("method"));
		Object obj = SpringContext.getBean(classname);
		Class[] argTypes = new Class[] { Map.class };
		Method method1 = obj.getClass().getMethod(method, argTypes);
		Object[] argValues = new Object[] { dataMap };
		ReturnMsg returnMsg = (ReturnMsg) method1.invoke(obj, argValues);
		Map<String, Object> alert = new HashMap<String, Object>();
		int i = 0;
		for (Message msg : returnMsg.getMsgList()) {
			alert.put("ajaxtype", "alert");
			alert.put(msg.getMsgCode() + i, msg.getMsgRemark());
			i++;
		}
		if (!alert.isEmpty())
			returnMsg.getDataList().add(alert);
		String json = this.toJson(returnMsg.getDataList());
		Ulog.debug(json);
		response.getWriter().write(json);
		return null;
	}

	private String toJson(List<Map<String, Object>> returnMsg) {
		JSONObject alert = new JSONObject();
		JSONObject focus = new JSONObject();
		JSONObject style = new JSONObject();
		JSONObject msg = new JSONObject();
		JSONObject readonly = new JSONObject();
		JSONObject disabled = new JSONObject();
		JSONObject edit = new JSONObject();
		JSONObject select = new JSONObject();
		JSONObject input = new JSONObject();
		JSONObject grid = new JSONObject();
		for (Map<String, Object> map : returnMsg) {
			this.toJson("alert", map, alert);
			this.toJson("focus", map, focus);
			this.toJson("style", map, style);
			this.toJson("msg", map, msg);
			this.toJson("readonly", map, readonly);
			this.toJson("disabled", map, disabled);
			this.toJson("edit", map, edit);
			this.toJson("input", map, input);
			this.gridToJson(map, grid);
			this.selectToJson(map, select);
		}
		JSONObject jsonPage = new JSONObject();
		JSONObject returnJson = new JSONObject();
		if (!alert.isEmpty())
			jsonPage.put("alert", alert);
		if (!focus.isEmpty())
			jsonPage.put("focus", focus);
		if (!style.isEmpty())
			jsonPage.put("style", style);
		if (!msg.isEmpty())
			jsonPage.put("msg", msg);
		if (!readonly.isEmpty())
			jsonPage.put("readonly", readonly);
		if (!disabled.isEmpty())
			jsonPage.put("disabled", disabled);
		if (!edit.isEmpty())
			jsonPage.put("edit", edit);
		if (!select.isEmpty())
			jsonPage.put("select", select);
		if (!input.isEmpty())
			jsonPage.put("input", input);
		if (!grid.isEmpty())
			jsonPage.put("grid", grid);
		if (!jsonPage.isEmpty())
			returnJson.put("retList", jsonPage);
		return returnJson.toString();
	}

	/**
	 * 转成json
	 * 
	 * @param map
	 * @param focus
	 */
	private void toJson(String type, Map<String, Object> map, JSONObject json) {
		if (map.get("ajaxtype") == null || !type.equals(map.get("ajaxtype"))) {
			return;
		}
		for (String key : map.keySet()) {
			if ("ajaxtype".equals(key))
				continue;
			Ulog.debug(key + " | " + map.get(key));
			json.put(key,
					map.get(key) != null ? StringUtil.trimStr(map.get(key))
							: map.get(key));
		}
	}

	/**
	 * 转成json
	 * 
	 * @param map
	 * @param focus
	 */
	private void selectToJson(Map<String, Object> map, JSONObject json) {
		Ulog.debug(map.get("ajaxtype"));
		if (map.get("ajaxtype") == null
				|| !"select".equals(map.get("ajaxtype"))) {
			return;
		}
		for (String key : map.keySet()) {
			if ("ajaxtype".equals(key))
				continue;
			if ("ajaxscope".equals(key)) {
				json.put("ajaxscope", map.get(key));
				continue;
			}
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> valueMap = (List<Map<String, Object>>) map
					.get(key);
			JSONArray array = new JSONArray();
			for (Map<String, Object> aOption : valueMap) {
				JSONObject valueObj = new JSONObject();
				System.out.println(aOption.get("code") + " | "
						+ aOption.get("name"));
				valueObj.put("code", StringUtil.trimStr(aOption.get("code")));
				valueObj.put("name", StringUtil.trimStr(aOption.get("name")));
				if (!StringUtil.isNull(aOption.get("selected")))
					valueObj.put("selected",
							StringUtil.trimStr(aOption.get("selected")));
				array.add(valueObj);
			}
			json.put(key, array);
		}

	}

	/**
	 * 转成json
	 * 
	 * @param map
	 * @param focus
	 */
	@SuppressWarnings("unchecked")
	private void gridToJson(Map<String, Object> map, JSONObject json) {
		Ulog.debug(map.get("ajaxtype"));
		if (map.get("ajaxtype") == null || !"grid".equals(map.get("ajaxtype"))) {
			return;
		}
		for (String key : map.keySet()) {
			if ("ajaxtype".equals(key))
				continue;
			List<Map<String, Object>> valueMap = (List<Map<String, Object>>) map
					.get(key);
			json.put(key, list2Json(valueMap));
		}

	}
	private static JSONArray list2Json(List<Map<String, Object>> rows) {
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = null;
		for (Map<String, Object> map : rows) {
			jsonObj = new JSONObject();
			for (String key : map.keySet()) {
				jsonObj.put(key, StringUtil.trimStr(map.get(key)));
			}
			jsonArr.add(jsonObj);
		}
		return jsonArr;
	}
}
