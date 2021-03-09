package com.newtouch.core.quanxianguanli.pojo;

import java.io.Serializable;

public class PageCount implements Serializable{
	// 当前第几页
	private Integer nowPage = 1;
	// 总页数
	private Integer allPage = 0;
	// 本页记录数
	private Integer pageRows = 0;
	// 所有记录数
	private Integer allRows = 0;
	// 每页显示多少行
	private Integer rows4Page = 10;
	// 排序信息
	private String orderBy = "";
	//开始
	private int start=0;
	//结束
	private int limit=0;
	//是否分页
	private boolean isPage = true;
	/**
	 * 
	 * @return 当前第几页
	 */
	public Integer getNowPage() {
		return nowPage;
	}

	/**
	 * 
	 * @param nowPage
	 *            当前第几页
	 */
	public PageCount setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
		return this;
	}

	/**
	 * 
	 * @return 总页数
	 */
	public Integer getAllPage() {
		return allPage;
	}

	/**
	 * 
	 * @param allPage
	 *            总页数
	 */
	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}

	/**
	 * 
	 * @return 本页记录数
	 */
	public Integer getPageRows() {
		return pageRows;
	}

	/**
	 * 
	 * @param pageRows
	 *            本页记录数
	 * 
	 */
	public void setPageRows(Integer pageRows) {
		this.pageRows = pageRows;
	}

	/**
	 * 
	 * @return 总记录数
	 */
	public Integer getAllRows() {
		return allRows;
	}

	/**
	 * 
	 * @param allRows
	 *            总记录数
	 */
	public void setAllRows(Integer allRows) {
		this.allRows = allRows;
	}

	/**
	 * 
	 * @return 每页显示多少行
	 */
	public Integer getRows4Page() {
		return rows4Page;
	}

	/**
	 * 
	 * @param rows4Page
	 *            每页显示多少行
	 */
	public void setRows4Page(Integer rows4Page) {
		this.rows4Page = rows4Page;
	}

	/**
	 * 
	 * @return 排序信息
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 
	 * @param orderBy
	 *            排序信息
	 */
	public void setOrderBy(String orderBy) {
		if (orderBy != null && !orderBy.equals("")) {
			this.orderBy = orderBy;
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		if(limit==0){limit = rows4Page;}
		this.limit = limit;
	}
	
	public void calcPage(){
		if(this.isPage){
			int min_elem = nowPage*rows4Page+1;
			int max_elem = Math.min((nowPage+1) * rows4Page, allRows)+1;
			this.setStart(min_elem);
			this.setLimit(max_elem);
		}else{
			this.setStart(0);
			if(this.limit == 60000)
				this.setLimit(60000);
			else
				this.setLimit(999999999);
		}
	}

	public boolean isPage() {
		return isPage;
	}

	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}
	
}
