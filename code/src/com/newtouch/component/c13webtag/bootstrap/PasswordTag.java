package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 * C13_6 - Password Tag Component
 * 密码框WebTag组件
 * @author szl
 *
 */
public class PasswordTag extends  BodyTagSupport{

	private static final long serialVersionUID = -1806140401781937606L;
	private String name;
	private String id;
	private String value;
	private String displayLable;
	private String readonly= "false";
	private String isdisplay;
	
	public String getIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDisplayLable() {
		return displayLable;
	}

	public void setDisplayLable(String displayLable) {
		this.displayLable = displayLable;
	}

	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
			if(isdisplay=="true"){
				TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"(<font color=\"red\">*</font>):</label>");
			}else{
				TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");
			}
			
			/*TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");*/
			if("false".equals(getReadonly()) || "".equals(getReadonly())){
				TransHelper.stringBufferAppender(sbf,"    <div class='controls'><input type='password' class='input-medium'  name='",this.getName(),"' id='",this.getId(),"' value='",this.getValue(),"'></div>");
				
			}else{
				TransHelper.stringBufferAppender(sbf,"    <div class='controls'><input type='password' readonly='true' class='input-medium'  name='",this.getName(),"' id='",this.getId(),"' value='",this.getValue(),"'></div>");
			}
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
