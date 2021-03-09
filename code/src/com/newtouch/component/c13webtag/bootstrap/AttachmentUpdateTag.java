package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 *  Attachment Update Tag Component
 * 带附件上传的修改界面使用标签
 * @author 张晨
 *
 */
public class AttachmentUpdateTag extends  BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7721315202112955566L;
	private String name;
	private String id;
	private String displayLable;
	private String uploadLable;
	private String value;
	private String basePath;
	
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
	
	public String getUploadLable() {
		return uploadLable;
	}

	public void setUploadLable(String uploadLable) {
		this.uploadLable = uploadLable;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='row'>");
			TransHelper.stringBufferAppender(sbf,"<div id='deuplo'  class='control-group span4 hide'>");
			TransHelper.stringBufferAppender(sbf,"<label class='control-label' for='attachmentInfo'>",this.getDisplayLable(),"</label>");
			TransHelper.stringBufferAppender(sbf,"<div style='margin-top:5px;width: 300px;' class='controls' > ");
			TransHelper.stringBufferAppender(sbf,this.getValue(),"<a id='deup' onclick='deleteAttachment();' class='deup'><img alt='删除附件' title='删除附件' src='",this.getBasePath(),"/compent/default/images/tab/deletefile.gif'></a>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			TransHelper.stringBufferAppender(sbf,"<div class='row'>");
			TransHelper.stringBufferAppender(sbf,"<div id='uplo' class='control-group span4 hide'>");
			TransHelper.stringBufferAppender(sbf,"<label class='control-label' for='uploadify'>",this.getUploadLable(),"</label>");
			TransHelper.stringBufferAppender(sbf,"<div class='controls'>");
			TransHelper.stringBufferAppender(sbf,"<input style='width:180px;' type='file' name='",this.getName(),"' id='",this.getId(),"'/>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
