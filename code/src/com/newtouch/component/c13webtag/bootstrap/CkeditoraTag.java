package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 * 富媒体（C13_10 - Ckeditor Tag Component）
 * @author szl
 *
 */
public class CkeditoraTag extends  BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 497067204645551052L;
	private String name;
	private String id;
	private String value;
	private String rows;
	private String cols;
	private String displayLable;
	
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

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='control-group'>");
			TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");
			TransHelper.stringBufferAppender(sbf,"    <div class='controls'> <textarea id='",this.getId(),"' name='",this.getName(),"' rows='",this.getRows(),"'>",this.getValue(),"</textarea><script type='text/javascript'>CKEDITOR.replace('",this.getName(),"');</script></div>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
