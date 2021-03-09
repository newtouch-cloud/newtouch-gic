package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;

/**
 * C13_12 - MultiUpload Tag Component
 * 多附件上传
 * @author szl
 *
 */
public class MultiUploadTag extends  BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7725415502112955566L;
	private String name;
	private String id;
	private String value;
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


	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
	  /*  <span class="btn btn-success fileinput-button">
        <i class="icon-plus icon-white"></i>
        <input id="fileupload" type="file" name="files[]"  multiple>
    </span>
    <br>
    <br>
    <table role="presentation" class="table table-striped">
      <tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery">
      </tbody>
    </table>
    <button type="submit" class="btn btn-primary" id="confirm" onclick="file2submit()" disabled="disabled">确认</button>*/
    
		try {
			StringBuffer sbf = new StringBuffer();
			TransHelper.stringBufferAppender(sbf,"<div class='control-group span4'>");
		//	TransHelper.stringBufferAppender(sbf,"<label class='control-label' for='",this.getId(),"'>",this.getDisplayLable(),"</label>");
		//	TransHelper.stringBufferAppender(sbf,"<div class='controls'>");
			TransHelper.stringBufferAppender(sbf,"<div class='container'>");
			TransHelper.stringBufferAppender(sbf,"<span class='btn btn-success fileinput-button'> ");
			TransHelper.stringBufferAppender(sbf,"<i class='icon-plus icon-white'></i>");
			TransHelper.stringBufferAppender(sbf,"<input id='fileupload' type='file' name='files[]'  multiple>'</span><br><br>");
			TransHelper.stringBufferAppender(sbf," <table role='presentation' class='table table-striped'>");
			TransHelper.stringBufferAppender(sbf," <tbody class='files' data-toggle='modal-gallery' data-target='#modal-gallery'></tbody></table>");
			TransHelper.stringBufferAppender(sbf,"<button type='submit' class='btn btn-primary' id='confirm' onclick='file2submit()' disabled='disabled'>确认</button>");
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
