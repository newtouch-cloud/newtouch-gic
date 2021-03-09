package com.newtouch.component.c13webtag.bootstrap;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.context.SpringContext;

/**
 * C13_8 - Check Select Tag Component
 * 复选框WebTag组件
 * @author sln
 *
 */
public class DynamicCheckSelectTag extends BodyTagSupport{

	private static final long serialVersionUID = 5000971858323764771L;
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
	private String src = "";
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
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
	public String getDisplayLable() {
		return displayLable;
	}
	public void setDisplayLable(String displayLable) {
		this.displayLable = displayLable;
	}

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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
			TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");
			TransHelper.stringBufferAppender(sbf,"    <div class='controls'>",printRadio(),"</div>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			System.out.println(sbf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	/**
	 * 渲染单选列表框
	 * @return
	 */
	public String printRadio(){
		IDynamicCheckSelectTagSupport radioSupport = (IDynamicCheckSelectTagSupport) SpringContext.getBean(this.getSrc());
		
		StringBuffer sbf = new StringBuffer();
		
		TransHelper.stringBufferAppender(sbf,"        <select class='input-medium' name='",this.getName(),"' id='",this.getId(),"'  multiple=‘multiple’ >");
		String[] values = this.getValue().split(TransHelper.SPLIT_DEFAULT);
		if(radioSupport!=null){
			List<DynamicRadioData> sds = radioSupport.getCheckSelectTag();
			if(sds!=null){
				for(DynamicRadioData sd : sds){
					if(ValidateHelper.isHave(values, sd.getDisplayLable())){
						TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getDisplayLable(),"' selected>",sd.getName(),"</option>");
					}else if(ValidateHelper.isNull(this.getValue()) 
						    && ValidateHelper.isHave(values, this.getDefaultValue())){//dd.getCode().equals(this.getDefaultValue())
						TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getDisplayLable(),"' selected>",sd.getName(),"</option>");
					}else{
						//TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getDisplayLable(),"' >",sd.getDisplayLable(),"</option>");
						TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getDisplayLable(),"' >",sd.getName(),"</option>");
					}
				}
			}
		}
		TransHelper.stringBufferAppender(sbf,"        </select>");
		return sbf.toString();

	}
}
