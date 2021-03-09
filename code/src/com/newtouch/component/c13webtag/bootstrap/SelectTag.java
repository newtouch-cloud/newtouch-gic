package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 * C13_4_1 - Static Select Tag Component
 * 静态下拉列表框WebTag组件
 * @author sln
 *
 */
public class SelectTag extends BodyTagSupport {

	private static final long serialVersionUID = -1266067343512203617L;
	
	private String id;
	private String name;
	private String value;
	private String displayLable;
	private String style;
	private String defaultValue;
	private String isBank;
	private String isEmptyOption = "true";
	private String readonly="";
	private String onmousemove="";
	private String onmouseout="";
	private String onfocus="";
	private String isdisplay="false";//是否显示
	private String iclass;  //校验及样式
	
	public String getIclass() {
		return iclass;
	}
	public void setIclass(String iclass) {
		this.iclass = iclass;
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
	public String getIsEmptyOption() {
		if(this.isEmptyOption==null||"".equals(this.isEmptyOption)){this.isEmptyOption="true";}
		return isEmptyOption;
	}
	public void setIsEmptyOption(String isEmptyOption) {
		this.isEmptyOption = isEmptyOption;
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
	
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	
	public String getOnfocus() {
		return onfocus;
	}
	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}
 
	public String getOnmousemove() {
		return onmousemove;
	}
	public void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}
	public String getOnmouseout() {
		return onmouseout;
	}
	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}
	

	public String getIsdisplay() {
		return isdisplay;
	}
	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}
	/**
	 * 渲染请选择
	 * @return
	 */
	public String printEmpty(){
		if("true".equals(this.getIsEmptyOption())){
			return "<option value=''>---请选择---</option>";
		}
		return "";
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
			TransHelper.stringBufferAppender(sbf,"    <div class='controls'>",printSelect(),"</div>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 渲染下拉列表框
	 * @return
	 */
	public String printSelect(){
		StringBuffer sbf = new StringBuffer();
		if(this.getReadonly().equals("readonly")){
			TransHelper.stringBufferAppender(sbf,"        <select disabled='true'  readonly='readonly' class='input-medium ",this.getIclass(),"' iclass='' name='",this.getName(),"' id='",this.getId(),"' >");
		}else{
			TransHelper.stringBufferAppender(sbf,"        <select class='input-medium ",this.getIclass(),"' iclass='' name='",this.getName(),"' id='",this.getId(),"' >");

		}
		TransHelper.stringBufferAppender(sbf,printEmpty());
		TransHelper.stringBufferAppender(sbf,this.getBodyContent().getString());
		TransHelper.stringBufferAppender(sbf,"        </select>");
		return sbf.toString();
	}

}
