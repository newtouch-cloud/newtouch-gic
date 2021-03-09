package com.newtouch.component.c13webtag.bootstrap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;

/**
 * @author sln
 *
 */
public class ReturnMsgTag extends  BodyTagSupport{

	private static final long serialVersionUID = -1806140401781937606L;
	private String name;
	private String id;
	private boolean successflag;
	private String msg;
	
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
	
	public boolean isSuccessflag() {
		return successflag;
	}

	public void setSuccessflag(boolean successflag) {
		this.successflag = successflag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Tag驱动程序
	 */
	public int doEndTag() throws JspException  {
		try {
			StringBuffer sbf = new StringBuffer();
			if(ValidateHelper.isNotNull(this.getMsg())){
				if(this.isSuccessflag()){
					TransHelper.stringBufferAppender(sbf,"<div class='alert alert-success' id ='"+ getId()+"' >");
					TransHelper.stringBufferAppender(sbf,"<strong>提示信息：</strong>",this.getMsg());
					TransHelper.stringBufferAppender(sbf,"</div>");
				}else{
					TransHelper.stringBufferAppender(sbf,"<div class='alert alert-error' id ='"+ getId()+"' >");
					TransHelper.stringBufferAppender(sbf,"<strong>提示信息：</strong>",this.getMsg());
					TransHelper.stringBufferAppender(sbf,"</div>");
				}
			}
			pageContext.getOut().write(sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
