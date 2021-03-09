package com.newtouch.component.c13webtag.bootstrap;

import java.util.List;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.context.SpringContext;
import com.newtouch.utils.stringutil.StringUtil;

/**
 * C13_4_2 - Dynamic Select Tag Component
 * 动态下拉列表框WebTag组件
 * @author sln
 *
 */
public class DynamicSelectTag extends SelectTag{

	private static final long serialVersionUID = 7653811678183048846L;
	private String src = "";
	private String parameter = "";
	private String iclass;
	
	public String getIclass() {
		return iclass;
	}
	public void setIclass(String iclass) {
		this.iclass = iclass;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	
	
	
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	/**
	 * 渲染下拉列表框
	 * @return
	 */
	public String printSelect(){
		IDynamicSelectDataSupport selectSupport = (IDynamicSelectDataSupport) SpringContext.getBean(this.getSrc());
		StringBuffer sbf = new StringBuffer();
		String spanstart="";
		String spanend="";
		
		if(!"".equals( this.getOnmousemove())&& !"".equals( this.getOnmouseout())&&!"".equals( this.getOnfocus())){
			spanstart ="<span onmousemove='"+this.getOnmousemove()+"' onmouseout='"+this.getOnmouseout()+"' onfocus='"+this.getOnfocus()+"'>";
			spanend="</span>";
		}
		String selectHead="<select class='input-medium "+this.getIclass()+"' iclass='' name='"+this.getName()+"' id='"+this.getId()+"' >";
		TransHelper.stringBufferAppender(sbf,printEmpty());
		//
		
		if(selectSupport!=null){
			List<IDynamicSelectData> sds= null;
			if(StringUtil.isNull(getParameter())){
				sds= selectSupport.getData();
			}else{
				sds= selectSupport.getData(getParameter());
			}
			
			if(sds!=null){
				
				for(IDynamicSelectData sd : sds){
					if ((sd.getDisplayLable().equals(this.getDefaultValue())||sd.getId().equals(this.getValue()))&&this.getReadonly().equals("readonly")) {
						selectHead="<select class='input-medium "+this.getIclass()+"' iclass='' name='"+this.getName()+"' id='"+this.getId()+"' readonly='"+this.getReadonly()+"' >";
						TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getId(),"' selected>",sd.getDisplayLable(),"</option>");
					}else if(((this.getDefaultValue()==null||this.getValue().equals("")))&&this.getReadonly().equals("readonly")){
						selectHead="<select class='input-medium "+this.getIclass()+"' iclass='' name='"+this.getName()+"' id='"+this.getId()+"' readonly='"+this.getReadonly()+"' >";
						TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getId(),"'>",sd.getDisplayLable(),"</option>");
					}else if(sd.getId().equals(this.getValue())){
						TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getId(),"' selected>",sd.getDisplayLable(),"</option>");
					}else{
						TransHelper.stringBufferAppender(sbf,"<option id='",sd.getId(),"' value='",sd.getId(),"' >",sd.getDisplayLable(),"</option>");
					}
				}
				
			}
		}
		//组合select字符串
		String str=sbf.toString();
		sbf=new StringBuffer();
		TransHelper.stringBufferAppender(sbf,spanstart,selectHead,str,"</select>",spanend);
		//TransHelper.stringBufferAppender(sbf,"</span>");
		return sbf.toString();
	}
	
}
