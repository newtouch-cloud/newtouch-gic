package com.ca.cacore.manage.model.vo;

import java.util.ArrayList;
import java.util.List;


public class RoleAuthsMenu {
	private String menu_id;
	private String menu_name;
	private List<RolePrivilegeButton> buttonList = new ArrayList<RolePrivilegeButton>();

	
	
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public List<RolePrivilegeButton> getButtonList() {
		return buttonList;
	}
	public void setButton(RolePrivilegeButton rolePrivilegeButton) {
		buttonList.add(rolePrivilegeButton);
	}
	
	

	

	
	
	
}
