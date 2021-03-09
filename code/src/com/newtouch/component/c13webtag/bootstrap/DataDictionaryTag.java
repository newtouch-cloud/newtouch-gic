package com.newtouch.component.c13webtag.bootstrap;

import java.util.List;

import javax.servlet.jsp.JspException;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.context.SpringContext;

/**
 * C13_1 - Data Dictionary Tag Component
 * 数据字典WebTag组件
 * @author sln
 *
 */
public class DataDictionaryTag extends RadioSelectTag{

	private static final long serialVersionUID = 5000971858323764771L;
	
	private String src = "";
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
			TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");
			TransHelper.stringBufferAppender(sbf,"<div class='controls'>",printRadio(),"</div>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
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
		IDynamicMultiSelectTagSupport radioSupport = (IDynamicMultiSelectTagSupport) SpringContext.getBean(this.getSrc());
		
		StringBuffer sbf = new StringBuffer();
		String[] values = this.getValue().split(TransHelper.SPLIT_DEFAULT);
		if(radioSupport!=null){
			List<DynamicRadioData> sds = radioSupport.getCheckbox();
			if(sds!=null){
				for(DynamicRadioData sd : sds){
					if(ValidateHelper.isHave(values, sd.getDisplayLable())){
						TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='checkbox' name='",this.getName(),"' value='",sd.getDisplayLable(),"' checked>",sd.getName(),"</label>");
					}else if(ValidateHelper.isNull(this.getValue()) 
						    && ValidateHelper.isHave(values, this.getDefaultValue())){//dd.getCode().equals(this.getDefaultValue())
						TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='checkbox'  name='",this.getName(),"' value='",sd.getDisplayLable(),"' checked>",sd.getName(),"</label>");
					}else{
						//TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getDisplayLable(),"' >",sd.getDisplayLable(),"</option>");
						TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='checkbox' name='",this.getName(),"' value='",sd.getDisplayLable(),"'>",sd.getName(),"</label>");
					}
				}
			}
		}
			
		return sbf.toString();
	}
}
