package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 *  Attachment Download Tag Component
 * 附件下载标签
 * @author 张晨
 *
 */
public class AttachmentDownloadTag extends  BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7721124202112955566L;
	private String name;
	private String id;
	private String displayLable;
	private String noAttachment; //没有附件显示信息
	private String value; 
	private String basePath; //项目路径
	private boolean trueFlag; //用于判断是否长传附件
	private String busId; //业务代码
	private String attachmentType;//附件类型
	private String fileName; //文件名称
	
	
	
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
	
	public String getNoAttachment() {
		return noAttachment;
	}

	public void setNoAttachment(String noAttachment) {
		this.noAttachment = noAttachment;
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
	
	

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public boolean isTrueFlag() {
		return trueFlag;
	}

	public void setTrueFlag(boolean trueFlag) {
		this.trueFlag = trueFlag;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='row'>");
			if(this.isTrueFlag()){
				TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
				TransHelper.stringBufferAppender(sbf,"<label class='control-label' for='download'>",this.getDisplayLable(),"</label>");
				TransHelper.stringBufferAppender(sbf,"<div style='margin-top:5px;width:300px;' class='controls'>");
				TransHelper.stringBufferAppender(sbf,"<a  href='",this.getBasePath(),"/TMS/upload/downloadAttachment.do?bus_id=",this.getBusId(),"&attachment_type=",this.getAttachmentType(),"' >",this.getFileName(),"</a>");
				TransHelper.stringBufferAppender(sbf,"</div>");
				TransHelper.stringBufferAppender(sbf,"</div>");
			}else{
				TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
				TransHelper.stringBufferAppender(sbf,"<label class='control-label' for='download'>",this.getDisplayLable(),"</label>");
				TransHelper.stringBufferAppender(sbf,"<div class='controls'>");
				TransHelper.stringBufferAppender(sbf,"<label style='text-align:left;padding-top:5px;'  for='noattachment'>",this.getNoAttachment(),"</label>");
				TransHelper.stringBufferAppender(sbf,"</div>");
				TransHelper.stringBufferAppender(sbf,"</div>");
			}
			TransHelper.stringBufferAppender(sbf,"</div>");
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
