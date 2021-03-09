package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 * C13_9 - Textarea Tag Component
 * 文本域WebTag组件
 * @author szl
 *
 */
public class TextareaTag extends  BodyTagSupport{

	private static final long serialVersionUID = 7725415502112955566L;
	private String name;
	private String id;
	private String value;
	private String rows;
	private String displayLable;
	private String readonly = "false";
	private String isdisplay;
	  
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
	
	public String getIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}
	
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
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
			if(this.getReadonly()==null||this.getReadonly()=="false"){
				TransHelper.stringBufferAppender(sbf,"    <div class='controls'> <textarea class='input-xlarge' id='",this.getId(),"' name='",this.getName(),"' rows='",this.getRows(),"' >",this.getValue(),"</textarea></div>");
			}else{
				TransHelper.stringBufferAppender(sbf,"    <div class='controls'> <textarea class='input-xlarge' id='",this.getId(),"' name='",this.getName(),"' rows='",this.getRows(),"' readonly='true' >",this.getValue(),"</textarea></div>");
				
			}
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	
}
