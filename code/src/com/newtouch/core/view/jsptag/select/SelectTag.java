package com.newtouch.core.view.jsptag.select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.newtouch.core.context.SpringContext;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.stringutil.StringUtil;

public class SelectTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private String queryId = null;// 查询的PID 必填
	private String value = null;// 值 必填
	private String name = null;// 名称 必填
	private String id = null;
	private String style = "form-control";// 样式
	private String classPath = "selectServiceImp";// 查询数据的实现类的全路径，默认为查询枚举 必填
	private String disable = null;// 是否可用
	private String onChange = null;// 点击触发事件
	private String show = null;
	private String notnull = null;// 是否为空
	private String readonly = null;// 不可修改
	private String notrst = null;// 是否清空
	private String up_no = null;
	private String datans = null;//数据命名空间
	// 备注：全部和空白不能同时存在
	private String isBlank = "1";// 0表示不需要空白、1表示需要空白
	private ServerBase serverbase;
	private static Map<String, List<Map<String, Object>>> selectMap = new HashMap<String, List<Map<String, Object>>>();;

	@Override
	protected int doStartTagInternal() throws Exception {
		try {
			if (serverbase == null) {
				serverbase = (ServerBase) this.getRequestContext()
						.getWebApplicationContext().getBean("serverbase");
			}
			User user = serverbase.getThreadLocal().get().getUser();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("queryId", queryId);
			queryMap.put("channel", StringUtil.trimStr(user.getDept_type()));
			queryId = StringUtil.trimStr(user.getDept_type()) + queryId;
			if (selectMap.get(queryId) == null)
				selectMap.put(queryId, queryList(queryMap, this.classPath));
			StringBuffer sb = new StringBuffer();
			sb.append("<select id='").append(name).append("' name='")
					.append(name).append("'");
			sb.append(" class='").append(style).append("'");
			if (!StringUtil.isNull(show))
				sb.append(" style='").append(show).append("'");
			if (!StringUtil.isNull(disable))
				sb.append(" disabled");
			if (!StringUtil.isNull(readonly))
				sb.append(" readonly");
			if (!StringUtil.isNull(notrst))
				sb.append(" data-notrst = " + notrst);
			if (!StringUtil.isNull(datans))
				sb.append(" data-ns = " + datans);
			if (!StringUtil.isNull(onChange))
				sb.append(" onChange=" + onChange);
			if (!StringUtil.isNull(notnull))
				sb.append(" notnull ");
			sb.append(">");
			if (isBlank != null && "1".equals(isBlank))
				sb.append("<option ").append("id='").append(id)
						.append("_option'").append(" value=''></option>");
			for (Map<String, Object> map : selectMap.get(queryId)) {
				sb.append("<option value='").append(map.get("code"))
						.append("' ");
				if (value.equals(map.get("code")))
					sb.append("selected");
				sb.append(">").append(map.get("name")).append("</option>");
			}

			sb.append("</select>");
			this.pageContext.getOut().write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RequestContextAwareTag.EVAL_PAGE;
	}

	public List<Map<String, Object>> queryList(Map<String, Object> param,
			String classPath) {
		ISelectService selectService = (ISelectService) SpringContext
				.getBean(classPath);
		if (serverbase == null)
			serverbase = (ServerBase) SpringContext.getBean("serverbase");
		return selectService.queryList(param, serverbase.dbHandle());
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsBlank() {
		return isBlank;
	}

	public void setIsBlank(String isBlank) {
		this.isBlank = isBlank;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getNotnull() {
		return notnull;
	}

	public void setNotnull(String notnull) {
		this.notnull = notnull;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getNotrst() {
		return notrst;
	}

	public void setNotrst(String notrst) {
		this.notrst = notrst;
	}

	public String getUp_no() {
		return up_no;
	}
	
	public void setDatans(String datans) {
		this.datans = datans;
	}

	public void setUp_no(String up_no) {
		this.up_no = up_no;
	}

}
