package com.newtouch.component.c13webtag.bootstrap;

public class DynamicSelectData implements IDynamicSelectData {

	private String id = "";
	private String name = "";
	private String displayLable = "";
	private String isSelected = "";
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getDisplayLable() {
		return displayLable;
	}
	@Override
	public void setDisplayLable(String displayLable) {
		this.displayLable = displayLable;
	}
	@Override
	public String getIsSelected() {
		return isSelected;
	}
	@Override
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	
}
