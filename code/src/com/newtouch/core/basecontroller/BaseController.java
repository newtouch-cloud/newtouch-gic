package com.newtouch.core.basecontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;

import com.newtouch.core.context.SpringContext;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.stringutil.StringUtil;

public class BaseController {
	private static ServerBase serverBase = null;

	/**
	 * 返回明细页面
	 * 
	 * @param viewName
	 *            页面名称
	 * @param msg
	 *            查询结果
	 * @return 包含数据的ModelAndView
	 */
	public static ModelAndView query4Details(String viewName, ReturnMsg msg) {
		if (serverBase == null)
			serverBase = (ServerBase) SpringContext.getBean("serverbase");
		JSONObject jsonObj = new JSONObject();
		if (msg != null) {
			String jsonStr = BaseController.return2Json(msg);
			jsonObj = JSONObject.fromObject(jsonStr);
		}
		if (!StringUtil.isNull(serverBase.getThreadLocal().get().getForm())) {
			jsonObj.put("hiddenform", JSONObject.fromObject("{"
					+ serverBase.getThreadLocal().get().getForm() + "}"));
		}
		return new ModelAndView(viewName, "json", jsonObj.toString());
	}

	/**
	 * 返回页面
	 * 
	 * @param viewName
	 *            页面名称
	 * 
	 * @return 包含数据的ModelAndView
	 */
	public static ModelAndView returnPage(HttpServletResponse response,
			String viewName) {
		return returnPage(response, new ReturnMsg(), viewName);
	}

	/**
	 * 返回页面
	 * 
	 * @param viewName
	 *            页面名称
	 * @param msg
	 *            查询结果
	 * @return 包含数据的ModelAndView
	 */
	public static ModelAndView returnPage(HttpServletResponse response,
			com.newtouch.core.returnmsg.ReturnMsg msg, String viewName) {
		if (serverBase == null)
			serverBase = (ServerBase) SpringContext.getBean("serverbase");
		String jsonStr = "";
		if (msg != null) {
			jsonStr = BaseController.return2Json(msg);
		}
		String rqstType = serverBase.getThreadLocal().get().getRqstType();
		if ("AJAX".equals(rqstType)) {
			try {
				response.getWriter().write(jsonStr);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if ("MOBILE".equals(rqstType)) {
			try {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(jsonStr);
				out.flush();
				out.close();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new ModelAndView(viewName, "json", jsonStr);
	}
	
	/**
	 * 返回页面
	 * 
	 * @param viewName
	 *            页面名称
	 * @param msg
	 *            查询结果
	 * @return 包含数据的ModelAndView
	 */
	public static ModelAndView returnPage(HttpServletResponse response,
			com.newtouch.newutil.ReturnMsg msg, String viewName) {
		if (serverBase == null)
			serverBase = (ServerBase) SpringContext.getBean("serverbase");
		String jsonStr = "";
		if (msg != null) {
			jsonStr = BaseController.return2Json(msg);
		}
		String rqstType = serverBase.getThreadLocal().get().getRqstType();
		if ("AJAX".equals(rqstType)) {
			try {
				response.getWriter().write(jsonStr);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if ("MOBILE".equals(rqstType)) {
			try {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(jsonStr);
				out.flush();
				out.close();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new ModelAndView(viewName, "json", jsonStr);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getRequestMap(HttpServletRequest request) {
		Map<String, String[]> parameterMap = (Map<String, String[]>) request
				.getParameterMap();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String keyStr = "";
		for (Object key : parameterMap.keySet()) {
			keyStr = StringUtil.trimStr(key);
			String parameter = StringUtil.trimStr(request.getParameter(keyStr));
			if (keyStr.indexOf("][") > 1) {
				keyStr = keyStr.replaceAll("]", "").replace("[", "#");
				String[] keyArray = keyStr.split("#");
				Map<String, Map<String, Object>> keyList = (Map<String, Map<String, Object>>) paramMap
						.get(keyArray[0]);
				if (keyList == null) {
					keyList = new HashMap<String, Map<String, Object>>();
				}
				Map<String, Object> keyMap = keyList.get(keyArray[1]);
				if (keyMap == null) {
					keyMap = new HashMap<String, Object>();
				}
				keyMap.put(keyArray[2], parameter);
				keyList.put(keyArray[1], keyMap);
				paramMap.put(keyArray[0], keyList);
				continue;
			}
			if (parameter.indexOf(":'") >= 0) {
				Map<String, Object> map = (Map<String, Object>) JSONObject
						.fromObject("{" + parameter + "}");
				paramMap.put(keyStr, map);
				continue;
			}
			if (parameter == null || "".equals(parameter)) {
				parameter = StringUtil.trimStr(request.getAttribute(keyStr));
			}
			if (keyStr.toLowerCase().endsWith("date")
					&& !StringUtil.isNull(parameter)) {
				Object obj = new Object();
				obj = parameter;
				paramMap.put(keyStr, DateUtil.string2Date(obj));
				continue;
			}
			paramMap.put(keyStr, parameter);
		}
		Ulog.debug(paramMap.toString(), 1);
		return paramMap;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getJsonMap(HttpServletRequest request) {
		String hiddenForm = request.getParameter("hiddenform");
		if (StringUtil.isNull(hiddenForm)) {
			hiddenForm = hiddenForm.replaceAll("\n", "");
			hiddenForm = hiddenForm.replaceAll("\r", "");
		}
		Ulog.debug(hiddenForm, 1);
		Map<String, Object> paramMap = (Map<String, Object>) JSONObject
				.fromObject("{" + hiddenForm + "}");
		return paramMap;
	}

	@SuppressWarnings("unchecked")
	private static String return2Json(com.newtouch.newutil.ReturnMsg returnMsg) {
		if (returnMsg == null) {
			return "";
		}
		if (serverBase == null)
			serverBase = (ServerBase) SpringContext.getBean("serverbase");
		PageCount pageCount = serverBase.getThreadLocal().get().getPageCount();
		JSONObject jsonPage = new JSONObject();
		if (returnMsg.getDataList() != null
				&& !returnMsg.getDataList().isEmpty()) {
			List<Map<String, Object>> rows = returnMsg.getDataList();
			JSONArray jsonArr = BaseController.list2Json(rows);
			jsonPage.put("retList", jsonArr);
		}
		if (returnMsg.getDataTable() != null
				&& !returnMsg.getDataTable().isEmpty()) {
			Map<String, Object> returnMap = returnMsg.getDataTable();
			Object obj = null;
			JSONArray defJsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			for (String key : returnMap.keySet()) {
				JSONArray jsonArr = null;
				obj = returnMap.get(key);
				if (obj instanceof Map) {
					Map<String, Object> map = (Map<String, Object>) obj;
					jsonArr = BaseController.map2Json(map);
					// jsonPage.put(key, jsonArr);
					// JSONObject mapJsonObj = new JSONObject();
					jsonObj.put(key, jsonArr);
					// defJsonArr.add(mapJsonObj);
					continue;
				}
				if (obj instanceof List) {
					List<Map<String, Object>> rows = (List<Map<String, Object>>) obj;
					jsonArr = BaseController.list2Json(rows);
					// jsonPage.put(key, jsonArr);
					// JSONObject listJsonObj = new JSONObject();
					jsonObj.put(key, jsonArr);
					// defJsonArr.add(listJsonObj);
					continue;
				}
				jsonObj.put(key, StringUtil.trimStr(obj));
			}
			defJsonArr.add(jsonObj);
			if (!defJsonArr.isEmpty()) {
				jsonPage.put("defmap", defJsonArr);
			}
		}
		// 警告信息
		if (returnMsg.isWarning()) {
			JSONArray warnArr = new JSONArray();
			for (Message msg : returnMsg.getWarnList()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(msg.getMsgCode(), msg.getMsgRemark());
				warnArr.add(jsonObj);
			}
			jsonPage.put("flag", "warn");
			jsonPage.put("msg", warnArr);
		}
		// 成功信息
		if (returnMsg.isSuccessflag()) {
			JSONArray succArr = new JSONArray();
			for (Message msg : returnMsg.getMsgList()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(msg.getMsgCode(), msg.getMsgRemark());
				succArr.add(jsonObj);
			}
			if (succArr.isEmpty()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("", "成功");
				succArr.add(jsonObj);
			}
			jsonPage.put("flag", "success");
			jsonPage.put("msg", succArr);
		}
		// 失败信息
		if (!returnMsg.isSuccessflag()) {
			JSONArray succArr = new JSONArray();
			for (Message msg : returnMsg.getMsgList()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(msg.getMsgCode(), msg.getMsgRemark());
				succArr.add(jsonObj);
			}
			if (succArr.isEmpty()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("", "执行失败");
				succArr.add(jsonObj);
			}
			jsonPage.put("flag", "fail");
			jsonPage.put("msg", succArr);
		}

		jsonPage.put("nowPage", pageCount.getNowPage());// 设置当前页数
		jsonPage.put("pageAll", pageCount.getAllPage());// 设置一共有多少页
		jsonPage.put("rowAll", pageCount.getAllRows());// 设置一共有多少条记录
		jsonPage.put("row4Page", pageCount.getRows4Page());// 设置每页显示多少条记录
		jsonPage.put("returnRows", returnMsg.getDataList().size());// 设置本次返回的记录数
		if (!StringUtil.isNull(serverBase.getThreadLocal().get().getForm())) {
			jsonPage.put("hiddenform", serverBase.getThreadLocal().get()
					.getForm());
		}
		Ulog.debug(jsonPage.toString(), 2);
		return jsonPage.toString();
	}

	@SuppressWarnings("unchecked")
	public static String return2Json(com.newtouch.core.returnmsg.ReturnMsg returnMsg) {
		if (returnMsg == null) {
			return "";
		}
		if (serverBase == null)
			serverBase = (ServerBase) SpringContext.getBean("serverbase");
		PageCount pageCount = serverBase.getThreadLocal().get().getPageCount();
		JSONObject jsonPage = new JSONObject();
		if (returnMsg.getDataList() != null
				&& !returnMsg.getDataList().isEmpty()) {
			List<Map<String, Object>> rows = returnMsg.getDataList();
			JSONArray jsonArr = BaseController.list2Json(rows);
			jsonPage.put("retList", jsonArr);
		}
		if (returnMsg.getDataTable() != null
				&& !returnMsg.getDataTable().isEmpty()) {
			Map<String, Object> returnMap = returnMsg.getDataTable();
			Object obj = null;
			JSONArray defJsonArr = new JSONArray();
			JSONObject jsonObj = new JSONObject();
			for (String key : returnMap.keySet()) {
				JSONArray jsonArr = null;
				obj = returnMap.get(key);
				if (obj instanceof Map) {
					Map<String, Object> map = (Map<String, Object>) obj;
					jsonArr = BaseController.map2Json(map);
					// jsonPage.put(key, jsonArr);
					// JSONObject mapJsonObj = new JSONObject();
					jsonObj.put(key, jsonArr);
					// defJsonArr.add(mapJsonObj);
					continue;
				}
				if (obj instanceof List) {
					List<Map<String, Object>> rows = (List<Map<String, Object>>) obj;
					jsonArr = BaseController.list2Json(rows);
					// jsonPage.put(key, jsonArr);
					// JSONObject listJsonObj = new JSONObject();
					jsonObj.put(key, jsonArr);
					// defJsonArr.add(listJsonObj);
					continue;
				}
				jsonObj.put(key, StringUtil.trimStr(obj));
			}
			defJsonArr.add(jsonObj);
			if (!defJsonArr.isEmpty()) {
				jsonPage.put("defmap", defJsonArr);
			}
		}
		// 警告信息
		if (returnMsg.isWarning()) {
			JSONArray warnArr = new JSONArray();
			for (Message msg : returnMsg.getWarnList()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(msg.getMsgCode(), msg.getMsgRemark());
				warnArr.add(jsonObj);
			}
			jsonPage.put("flag", "warn");
			jsonPage.put("msg", warnArr);
		}
		// 成功信息
		if (returnMsg.isSuccessflag()) {
			JSONArray succArr = new JSONArray();
			for (Message msg : returnMsg.getMsgList()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(msg.getMsgCode(), msg.getMsgRemark());
				succArr.add(jsonObj);
			}
			if (succArr.isEmpty()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("", "成功");
				succArr.add(jsonObj);
			}
			jsonPage.put("flag", "success");
			jsonPage.put("msg", succArr);
		}
		// 失败信息
		if (!returnMsg.isSuccessflag()) {
			JSONArray succArr = new JSONArray();
			for (Message msg : returnMsg.getMsgList()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(msg.getMsgCode(), msg.getMsgRemark());
				succArr.add(jsonObj);
			}
			if (succArr.isEmpty()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("", "执行失败");
				succArr.add(jsonObj);
			}
			jsonPage.put("flag", "fail");
			jsonPage.put("msg", succArr);
		}

		jsonPage.put("nowPage", pageCount.getNowPage());// 设置当前页数
		jsonPage.put("pageAll", pageCount.getAllPage());// 设置一共有多少页
		jsonPage.put("rowAll", pageCount.getAllRows());// 设置一共有多少条记录
		jsonPage.put("row4Page", pageCount.getRows4Page());// 设置每页显示多少条记录
		jsonPage.put("returnRows", returnMsg.getDataList().size());// 设置本次返回的记录数
		if (!StringUtil.isNull(serverBase.getThreadLocal().get().getForm())) {
			jsonPage.put("hiddenform", serverBase.getThreadLocal().get()
					.getForm());
		}
		if (!StringUtil.isNull(serverBase.getThreadLocal().get().getInitPage())) {
			jsonPage.put("InitPageType", serverBase.getThreadLocal().get()
					.getInitPage());
		}
		Ulog.debug(jsonPage.toString(), 2);
		return jsonPage.toString();
	}
	
	/**
	 * 将Map转为json格式
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static JSONArray map2Json(Map<String, Object> map) {
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		Object obj = null;
		for (String valueKey : map.keySet()) {
			obj = map.get(valueKey);
			if (obj instanceof Map) {
				Map<String, Object> inMap = (Map<String, Object>) obj;
				for (String key : inMap.keySet()) {
					jsonObj.put(key, inMap.get(key).toString());
				}
			} else if (obj instanceof List) {
				List<Map<String, Object>> inMap = (List<Map<String, Object>>) obj;
				JSONArray inJsonArr = new JSONArray();
				for (Map<String, Object> lMap : inMap) {
					JSONObject inJson = new JSONObject();
					for (String key : lMap.keySet()) {
						inJson.put(key, lMap.get(key).toString());
					}
					inJsonArr.add(inJson);
				}
				jsonObj.put(valueKey, inJsonArr);
			} else {
				jsonObj.put(valueKey, map.get(valueKey).toString());
			}
		}
		jsonArr.add(jsonObj);
		return jsonArr;
	}

	/**
	 * 将List转为 json格式
	 * 
	 * @param rows
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static JSONArray list2Json(List<Map<String, Object>> rows) {
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = null;
		for (Map<String, Object> map : rows) {
			jsonObj = new JSONObject();
			for (String key : map.keySet()) {
				if (map.get(key) instanceof List) {
					jsonObj.put(key,
							list2Json((List<Map<String, Object>>) map.get(key)));
					continue;
				}
				if (map.get(key) instanceof Map) {
					jsonObj.put(key,
							map2Json((Map<String, Object>) map.get(key)));
					continue;
				}
				jsonObj.put(key, StringUtil.trimStr(map.get(key)));
			}
			jsonArr.add(jsonObj);
		}
		return jsonArr;
	}

	public void exceptionHandle(Exception e, HttpServletResponse response) {
		e.printStackTrace();
		JSONObject json = new JSONObject();
		json.put("fail", e.getMessage() + "");
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 返回移动端需要的信息
	 * 
	 * @param response
	 * @param value
	 * @throws Exception
	 */
	public void returnClient(HttpServletResponse response, String value)
			throws Exception {
		Ulog.debug(value);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(value);
		out.flush();
		out.close();
	}
	/**
	 * 接口调用返回
	 * 
	 * @param response
	 * @param returnMsg
	 * @return
	 */
	public static ModelAndView returnResult(HttpServletResponse response,
			ReturnMsg returnMsg) {
		if (returnMsg.isSuccessflag()) {
			try {
				response.getWriter().write("SUCCESS");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			try {
				response.getWriter().write("FAIL");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
