package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;

/**
 * C13_5 - Option Tag Component
 * 下拉列表选项WebTag组件
 * @author sln
 *
 */
public class OptionTag extends BodyTagSupport {

	private static final long serialVersionUID = -1266067343512203617L;
	private String defaultValue;
	private String name;
	private String id;
	private String value;
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
	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			SelectTag pt = (SelectTag)this.getParent();
//			System.out.println("pt.getValue() "+pt.getValue()+"      pt.getDefaultValue()="+pt.getDefaultValue()+"     getValue()="+getValue());
			if(this.getValue().equals(pt.getValue())){
				TransHelper.stringBufferAppender(sbf,"<option id='",this.getId(),"' value='",this.getValue(),"' selected>",this.getDisplayLable(),"</option>");
			} else if(ValidateHelper.isNull(pt.getValue())
					    && this.getValue().equals(pt.getDefaultValue())){
				TransHelper.stringBufferAppender(sbf,"<option id='",this.getId(),"' value='",this.getValue(),"' selected>",this.getDisplayLable(),"</option>");
			} else{
				TransHelper.stringBufferAppender(sbf,"<option id='",this.getId(),"' value='",this.getValue(),"'>",this.getDisplayLable(),"</option>");
			}
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
