package com.newtouch.component.c13webtag.bootstrap;

import java.util.List;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.serverbase.ServerBase;

public class AhrefTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private String id = null;//  
	private String value = null;// 值，可为空   按钮名称 
	private String name = null;// 名称，不可为空 权限.do
	private String path = null;   //应用上下文
	private String class_css = null;// a标签样式
	private String href = null; // 单击事件,可为空  链接
	private String iclass_css = null;  //标签图标样式
	private String onClick = null; // 单击事件,可为空
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
			String ahref = "";
			if (listStr.contains(name)) {
				ahref += "<a name='" + this.name + "' ";
				// id不为空
				if (this.id != null && !"".equals(this.id))
					ahref += " id='" + this.id + "' ";
				// value不为空
				if (this.value != null && !"".equals(this.value))
					ahref += " value='" + this.value + "' ";
				// onClick单击事件不为空
				if (this.href != null && !"".equals(this.href))
					ahref += " href='" + this.path + this.href + "' ";
				// onClick单击事件不为空
				if (this.onClick != null && !"".equals(this.onClick))
					ahref += " onclick='" + this.onClick + "' ";
				// a标签样式
				if (this.class_css != null && !"".equals(this.class_css))
					ahref += " class='" + this.class_css + "' ";
				// 标签图标样式
				ahref += "> " + "<i class='"+this.iclass_css+"'></i>"+this.value + "</a>";
				this.pageContext.getOut().write(ahref);
			} else {
				this.pageContext.getOut().write("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RequestContextAwareTag.EVAL_PAGE;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getClass_css() {
		return class_css;
	}

	public void setClass_css(String class_css) {
		this.class_css = class_css;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIclass_css() {
		return iclass_css;
	}

	public void setIclass_css(String iclass_css) {
		this.iclass_css = iclass_css;
	}

	public ServerBase getServerbase() {
		return serverbase;
	}

	public void setServerbase(ServerBase serverbase) {
		this.serverbase = serverbase;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

}
