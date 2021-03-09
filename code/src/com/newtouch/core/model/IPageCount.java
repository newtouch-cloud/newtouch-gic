package com.newtouch.core.model;

import com.newtouch.core.quanxianguanli.pojo.PageCount;


public interface IPageCount {

	public PageCount getPageCount();
	public void setPageCount(PageCount pageCount);
	public int getStart();
	public int getLimit();
}
