package com.newtouch.component.c13webtag.bootstrap;

import java.util.List;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.serverbase.ServerBase;

public class ButtonTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private String value = null;// 值，可为空
	private String name = null;// 名称，不可为空
	private String id = null;
	private String classCss = null;
	private String iClassCss = null;
	private String onClick = null; // 单击事件,可为空
	private String type = null; //
	private ServerBase serverbase;

	@Override
	protected int doStartTagInternal() throws Exception {
		try {
			if (serverbase == null) {
				serverbase = (ServerBase) this.getRequestContext()
						.getWebApplicationContext().getBean("serverbase");
			}
			User user = serverbase.getThreadLocal().get().getUser();
			List<String> listStr = user.getFuncLis();
			String button = "";
			if (listStr.contains(name)) {
				button += "<button name='" + this.name + "' ";
				// id不为空
				if (this.id != null && !"".equals(this.id))
					button += " id='" + this.id + "' ";
				// value不为空
				if (this.value != null && !"".equals(this.value))
					button += " value='" + this.value + "' ";
				// onClick单击事件不为空
				if (this.onClick != null && !"".equals(this.onClick))
					button += " onclick='" + this.onClick + "' ";
				
				if (this.classCss != null && !"".equals(this.classCss))
					button += " class='" + this.classCss + "' ";	
				// type不为空
				if (this.type != null && !"".equals(this.type))
					button += " type='" + this.type + "' ";
				
				button += "> " + "<i class='"+this.iClassCss+"'></i>"+this.value+ "</button>";
				this.pageContext.getOut().write(button);
			} else {
				this.pageContext.getOut().write("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RequestContextAwareTag.EVAL_PAGE;
	}

	public String getiClassCss() {
		return iClassCss;
	}

	public void setiClassCss(String iClassCss) {
		this.iClassCss = iClassCss;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

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

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClassCss() {
		return classCss;
	}

	public void setClassCss(String classCss) {
		this.classCss = classCss;
	}


}