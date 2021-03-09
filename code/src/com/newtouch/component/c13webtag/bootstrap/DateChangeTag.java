package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 * C13_3 - Date Tag Component
 * 日期输入框WebTag组件
 * @author sln
 *
 */
public class DateChangeTag extends BodyTagSupport {

	private static final long serialVersionUID = -1266067343512203617L;
	private String name;
	private String id;
	private String value;
	private String displayLable;
	private String readonly;
	private String isdisplay;
	private String iclass;
    private String dateFormat;
	public String getIclass() {
		return iclass;
	}
	public void setIclass(String iclass) {
		this.iclass = iclass;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	public String getIsdisplay() {
		return isdisplay;
	}
	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
			if(this.getIsdisplay()=="true"){
				TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"(<font color=\"red\">*</font>):</label>");
			}else{
				TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");
			}
			if(this.getReadonly()==null||this.getReadonly()=="false"){
                if (this.getDateFormat() != null && !"".equals(this.getDateFormat())) {
                    TransHelper.stringBufferAppender(sbf,"    <div class='controls'><input type='text' class='input-medium ",this.getIclass(),"' iclass='' onFocus='custom_focus(this)'  id='",this.getId(),"' value='",this.getValue(),"'  ></div>");
                } else {
                    TransHelper.stringBufferAppender(sbf,"    <div class='controls'><input type='text' class='input-medium ",this.getIclass(),"' iclass='' onFocus='custom_focus(this)' name='",this.getName(),"' id='",this.getId(),"' value='",this.getValue(),"'  ></div>");
                }
			}else{
				TransHelper.stringBufferAppender(sbf,"    <div class='controls'><input type='text' class='input-medium ",this.getIclass(),"' iclass=''  name='",this.getName(),"' id='",this.getId(),"' value='",this.getValue(),"' readonly='",this.getReadonly(),"' ></div>");
				
			}
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
