package com.newtouch.component.c13webtag.bootstrap;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.context.SpringContext;

/**
 * C13_13_1 - Static Multi Select Tag Component
 * 静态多选框WebTag组件
 * @author szl
 *
 */
public class MultiSelectTag  extends  BodyTagSupport{

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
	private String isEmptyOption = "true";
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
	public String getIsEmptyOption() {
		return isEmptyOption;
	}
	public void setIsEmptyOption(String isEmptyOption) {
		this.isEmptyOption = isEmptyOption;
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
	
	/**
	 * 渲染下拉列表框
	 * @return
	 *//*
	public String printRadio(){
		StringBuffer sbf = new StringBuffer();
		IDataDictionaryService selectService = (IDataDictionaryService) SpringContext.getBean("dataDictionaryService");
		List<DataDictionary> dds = selectService.getDataByParentCode(this.getSysId(), this.getChannel_id(), this.getParentCode());
//		System.out.println(this.getValue()+"--");
		String[] values = this.getValue().split(TransHelper.SPLIT_DEFAULT);
		for(DataDictionary dd:dds){
			if(ValidateHelper.isHave(values, dd.getCode())){//dd.getCode().equals(this.getValue())
				TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='checkbox'  name='",this.getName(),"' value='",dd.getCode(),"' checked>",dd.getName(),"</label>");
			}else if(ValidateHelper.isNull(this.getValue())
					    && ValidateHelper.isHave(values, this.getDefaultValue())){//dd.getCode().equals(this.getDefaultValue())
				TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='checkbox'  name='",this.getName(),"' value='",dd.getCode(),"' checked>",dd.getName(),"</label>");
			}else{
				TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='checkbox'  name='",this.getName(),"' value='",dd.getCode(),"'>",dd.getName(),"</label>");
			}

		}
		return sbf.toString();
	}*/
}
