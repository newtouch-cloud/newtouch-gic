package com.newtouch.core.quanxianguanli.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetJspTitle {
	private static String title = "";
	private String parentid = "";
	public static Map<String, String> resultMap = new HashMap<String, String>();

	@RequestMapping("/getJspTitle.do")
	public ModelAndView getJspTitle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String menuString = (String) request.getSession().getAttribute(
				"menuString");
		menuString = "[{ id:10, pId:0, name:'组织机构管理',actionUrl:''},{ id:1001, pId:10, name:'机构管理',actionUrl:'test.do',open_type:'outweb'},{ id:1002, pId:10, name:'机构维护',actionUrl:'test2.do',open_type:'outweb'}]";
		String menuid = request.getParameter("menuid");
		String funid = request.getParameter("funid");
		if (menuid != null && funid != null) {
			if (resultMap.get(menuid + funid) != null
					&& !"".equals(resultMap.get(menuid + funid))) {
				title = resultMap.get(menuid + funid);
			} else {
				JSONArray arry = JSONArray.fromObject(menuString);
				List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
				for (int i = 0; i < arry.size(); i++) {
					JSONObject jsonObject = arry.getJSONObject(i);
					Map<String, String> map = new HashMap<String, String>();
					for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
						String key = (String) iter.next();
						String value = jsonObject.get(key).toString();
						map.put(key, value);
					}
					rsList.add(map);
				}
				title = "";
				parentid = "";
				getTitle(funid, rsList);
				if (title.length() > 2)
					title = title.substring(0, title.length() - 2);
				resultMap.put(menuid + funid, title);
			}
		}

		JSONObject json = new JSONObject();
		json.put("titleDiv", title);
		response.getWriter().write(json.toString());
		return null;
	}

	private void getTitle(String actionUrl, List<Map<String, String>> rsList) {
		for (Map<String, String> map : rsList) {
			if (parentid.equals("")) {
				if (map.get("actionUrl").equals(actionUrl)) {
					recursion(rsList, map, actionUrl);
				}
			} else {
				if (map.get("id").equals(parentid)) {
					recursion(rsList, map, actionUrl);
				}
			}
		}
	}

	private void recursion(List<Map<String, String>> rsList,
			Map<String, String> map, String actionUrl) {
		if (!map.get("actionUrl").equals("")) {
			title = "<a href=\"\">" + map.get("name") + "</a>" + " > " + title;
		} else {
			title = map.get("name") + " > " + title;
		}
		if (!map.get("pId").equals("")) {
			parentid = map.get("pId").toString();
			getTitle(actionUrl, rsList);
		}
	}
}
