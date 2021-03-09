package com.newtouch.component.c13webtag.bootstrap;

import java.util.List;

import javax.servlet.jsp.JspException;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.context.SpringContext;

/**
 * C13_7_2 - Dynamic Radio Select Tag Component
 * 动态单选框WebTag组件
 * @author szl
 *
 */
public class DynamicRadioTag extends RadioSelectTag{

	private static final long serialVersionUID = 5000971858323764771L;
	
	private String src = "";
	private String readonly = "";
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
			TransHelper.stringBufferAppender(sbf,"    <label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");
			TransHelper.stringBufferAppender(sbf,"<div class='oneline'>",printRadio(),"</div>");
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
//	public String printRadio(){
//		IDynamicRadioTagSupport radioSupport = (IDynamicRadioTagSupport) SpringContext.getBean(this.getSrc());
//		
//		StringBuffer sbf = new StringBuffer();
//		if(radioSupport!=null){
//			List<DynamicRadioData> sds = radioSupport.getRadio();
//			if(sds!=null){
//				for(DynamicRadioData sd : sds){
//					if(sd.getDisplayLable().equals(this.getValue())){
//						TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='radio' name='",this.getName(),"' value='",sd.getDisplayLable(),"' checked","readonly".equals(this.getReadonly())?" onclick='return false;' onkeydown='return false;' style='cursor: not-allowed;'>":">", sd.getName(),"</label>");
//					}else{
//						TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='radio' name='",this.getName(),"' value='",sd.getDisplayLable(),"'", "readonly".equals(this.getReadonly())?" onclick='return false;' onkeydown='return false;' style='cursor: not-allowed;'>":">", sd.getName(),"</label>");
//					}
//				}
//			}
//		}
//		return sbf.toString();
//	}
	
	/**
	 * 渲染单选列表框
	 * @return
	 */
	public String printRadio(){
		IDynamicRadioTagSupport radioSupport = (IDynamicRadioTagSupport) SpringContext.getBean(this.getSrc());
		
		StringBuffer sbf = new StringBuffer();
		String[] values = this.getValue().split(TransHelper.SPLIT_DEFAULT);
		if(radioSupport!=null){
			List<DynamicRadioData> sds = radioSupport.getRadio();
			if(sds!=null){
				for(DynamicRadioData sd : sds){
					if(ValidateHelper.isHave(values, sd.getDisplayLable())){
						TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='radio' name='",this.getName(),"' value='",sd.getDisplayLable(),"' checked","readonly".equals(this.getReadonly())?" disabled='disabled;' onclick='return false;' onkeydown='return false;' style='cursor: not-allowed;'>":">", sd.getName(),"</label>");
					}else if(ValidateHelper.isNull(this.getValue()) 
						    && ValidateHelper.isHave(values, this.getDefaultValue())){//dd.getCode().equals(this.getDefaultValue())
						TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='radio' name='",this.getName(),"' value='",sd.getDisplayLable(),"' checked","readonly".equals(this.getReadonly())?" disabled='disabled;' onclick='return false;' onkeydown='return false;' style='cursor: not-allowed;'>":">", sd.getName(),"</label>");
					}else{
						//TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getDisplayLable(),"' >",sd.getDisplayLable(),"</option>");
						TransHelper.stringBufferAppender(sbf,"<label class='",this.getStyle(),"'><input type='radio' name='",this.getName(),"' value='",sd.getDisplayLable(),"'", "readonly".equals(this.getReadonly())?" disabled='disabled;' onclick='return false;' onkeydown='return false;' style='cursor: not-allowed;'>":">", sd.getName(),"</label>");
					}
				}
			}
		}
//		System.out.println(sbf.toString());
		return sbf.toString();
	}
	
}
