package com.newtouch.core.view.jsptag.select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.stringutil.StringUtil;

public class CustomSelectTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;
	private String id = "";
	private String value = "";
	private String clazz = "form-control";// 样式
	private String style = "";// 单独样式
	private String disabled = "";
	private String readonly = "";
	private String notrst = "";
	private String onchange = "";
	private String onblur = "";
	private String blankline = "";// 是否插入空白行，默认为插入
	private String service = "CustomSelect";// 服务，默认为
	private String custom_no = "";// sys_select_custom表内对应字段
	private String up_no = "";// 对应mapping_parent字段
	private String other = "";// 对应mapping_rule字段，通过json字符串表示

	private ServerBase serverbase;
	private static Map<String, List<Map<String, Object>>> SELECT_MAP = new HashMap<String, List<Map<String, Object>>>();
	private SelectTag baseSlctTag = new SelectTag();

	@SuppressWarnings("unchecked")
	@Override
	protected int doStartTagInternal() throws Exception {
		if (serverbase == null)
			serverbase = (ServerBase) this.getRequestContext()
					.getWebApplicationContext().getBean("serverbase");
		User user = serverbase.getThreadLocal().get().getUser();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("custom_no", custom_no);
		param.put("up_no", up_no);
		if (!StringUtil.isNull(this.other)) {
			Map<String, Object> paramMap = (Map<String, Object>) JSONObject
					.fromObject("{" + this.other + "}");
			param.putAll(paramMap);
		}
		param.put("dept_type", StringUtil.trimStr(user.getDept_type()));
		String SELECT_MAP_KEY = this.service + this.custom_no + this.up_no
				+ this.other;
		if (SELECT_MAP.get(SELECT_MAP_KEY) == null
				|| SELECT_MAP.get(SELECT_MAP_KEY).isEmpty()) {
			SELECT_MAP.put(SELECT_MAP_KEY,
					baseSlctTag.queryList(param, this.service));
			// 如果仍然为空，则查找默认00的指标
			if (SELECT_MAP.get(SELECT_MAP_KEY) == null
					|| SELECT_MAP.get(SELECT_MAP_KEY).isEmpty()) {
				param.put("dept_type", "00");
				SELECT_MAP.put(SELECT_MAP_KEY,
						baseSlctTag.queryList(param, this.service));
			}
		}
		String select = "<select id=\"" + this.id + "\" name=\"" + this.id
				+ "\"";
		if (!StringUtil.isNull(this.clazz))
			select += " class=\"" + this.clazz + "\"";

		if (!StringUtil.isNull(this.style))
			select += " style=\"" + this.style + "\"";
		if (!StringUtil.isNull(this.disabled))
			select += " disabled=\"disabled\"";
		if (!StringUtil.isNull(this.readonly))
			select += " readonly=\"readonly\"";
		if (!StringUtil.isNull(this.notrst))
			select += " data-notrst=\"true\"";
		if (!StringUtil.isNull(this.onchange))
			select += " onchange=\"" + this.onchange + "\"";
		if (!StringUtil.isNull(this.onblur))
			select += " onblur=\"" + this.onblur + "\"";
		select += ">";
		if (StringUtil.isNull(this.blankline))
			select += "<option value=\"\"></option>";
		for (Map<String, Object> map : SELECT_MAP.get(SELECT_MAP_KEY)) {
			select += "<option value=\"" + map.get("code") + "\"";
			if (value.equals(map.get("code")))
				select += " selected ";
			select += ">" + map.get("name") + "</option>";
			// map.get("code") + "-" +
		}
		select += "</select>";
		this.pageContext.getOut().write(select);
		return RequestContextAwareTag.EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
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

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getBlankline() {
		return blankline;
	}

	public void setBlankline(String blankline) {
		this.blankline = blankline;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCustom_no() {
		return custom_no;
	}

	public void setCustom_no(String custom_no) {
		this.custom_no = custom_no;
	}

	public String getUp_no() {
		return up_no;
	}

	public void setUp_no(String up_no) {
		this.up_no = up_no;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
}
