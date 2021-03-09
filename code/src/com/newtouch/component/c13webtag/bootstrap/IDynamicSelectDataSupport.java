package com.newtouch.component.c13webtag.bootstrap;

import java.util.List;

/**
 * 动态列表获取
 * @author sln
 *
 */
public interface IDynamicSelectDataSupport {

	public List<IDynamicSelectData> getData();
	public List<IDynamicSelectData> getData(String filterParameter);
	
}
