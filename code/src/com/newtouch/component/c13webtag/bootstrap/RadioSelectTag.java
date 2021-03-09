package com.newtouch.component.c13webtag.bootstrap;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.context.SpringContext;

/**
 * C13_7_1 - Radio Select Tag Component
 * 静态单选框WebTag组件
 * @author szl
 *
 */
public class RadioSelectTag  extends  BodyTagSupport{

	private static final long serialVersionUID = 3473802141403703711L;
	private String sysId;
	private String channel_id;
	private String parentCode;
	private String id;
	private String name;
	private String value;
	private String displayLable;
	private String style;
	private String defaultValue;
	private String isBank;
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDisplayLable() {
		return displayLable;
	}
	public void setDisplayLable(String displayLable) {
		this.displayLable = displayLable;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getIsBank() {
		return isBank;
	}
	public void setIsBank(String isBank) {
		this.isBank = isBank;
	}
	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
			TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");
			/*TransHelper.stringBufferAppender(sbf,"<div class='controls'>",printRadio(),"</div>");*/
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	

}
