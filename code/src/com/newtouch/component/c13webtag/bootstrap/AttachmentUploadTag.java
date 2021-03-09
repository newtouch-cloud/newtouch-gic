package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 *  Attachment Upload Tag Component
 * 附件上传
 * @author 张晨
 *
 */
public class AttachmentUploadTag extends  BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7725415202112955566L;
	private String name;
	private String id;
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
			TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
			TransHelper.stringBufferAppender(sbf,"<label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");
			TransHelper.stringBufferAppender(sbf,"<div class='controls'>");
			TransHelper.stringBufferAppender(sbf,"<input style='width:180px;' type='file' name='",this.getName(),"' id='",this.getId(),"' />");
			TransHelper.stringBufferAppender(sbf,"</div>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
