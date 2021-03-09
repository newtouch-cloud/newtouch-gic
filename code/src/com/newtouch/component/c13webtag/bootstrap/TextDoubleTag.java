package com.newtouch.component.c13webtag.bootstrap;

import java.text.DecimalFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 * C13_2 - Text Tag Component
 * 文本输入框WebTag组件
 * @author sln
 *
 */
public class TextDoubleTag extends BodyTagSupport {
	DecimalFormat df = new DecimalFormat("0.00");
	private static final long serialVersionUID = -1266067343512203617L;
	private String name;
	private String id;
	private String value;
	private String displayLable;
	private String readonly;
	private String onclick;
	private String onblur;
	private String onpaste;   //控制粘贴事件
	private String oncontextmenu; //控制右键菜单事件
	private String isBank;
	private String iclass;
	private String isdisplay="false";//是否显示可为空
	
	public String getOnpaste() {
		return onpaste;
	}
	public void setOnpaste(String onpaste) {
		this.onpaste = onpaste;
	}
	public String getOncontextmenu() {
		return oncontextmenu;
	}
	public void setOncontextmenu(String oncontextmenu) {
		this.oncontextmenu = oncontextmenu;
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
		if(null!=this.value&&!"".equals(this.value)){
			//不为空时转换
			value = df.format(Double.valueOf(this.value));
		}
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
	
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	
	public String getIsBank() {
		return isBank;
	}
	public void setIsBank(String isBank) {
		this.isBank = isBank;
	}
	
	public String getIsdisplay() {
		return isdisplay;
	}
	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}
	
	
	public String getOnblur() {
		return onblur;
	}
	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}
	public String getIclass() {
		return iclass;
	}
	public void setIclass(String iclass) {
		this.iclass = iclass;
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
					TransHelper.stringBufferAppender(sbf,"    <div class='controls'><input type='text' class='input-medium ",this.getIclass(),"' iclass='' name='",this.getName(),"' id='",this.getId(),"' value='",this.getValue(),"' onblur='",this.getOnblur(),"' onpaste='",this.getOnpaste(),"' oncontextmenu='",this.getOncontextmenu(),"'></div>");
			}else if(this.getReadonly()=="true"){
					TransHelper.stringBufferAppender(sbf,"    <div class='controls'><input type='text' class='input-medium ",this.getIclass(),"' iclass='' name='",this.getName(),"' id='",this.getId(),"' value='",this.getValue(),"' readonly='",this.getReadonly(),"'  onclick='",this.getOnclick(),"' onblur='",this.getOnblur(),"' onpaste='",this.getOnpaste(),"' oncontextmenu='",this.getOncontextmenu(),"'></div>");
			}
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
