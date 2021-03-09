package com.ca.cacore.manage.model.vo;


public class RolePrivilege  {
	private String Role_id;
	private String menu_id;
	private String menu_name;
	private String button_id;
	private String button_name;
	private String selected;
	

	public RolePrivilege() {}
	
	
	
	public RolePrivilege(String menu_id, String menu_name,
			String button_id, String button_name, String selected) {
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.button_id = button_id;
		this.button_name = button_name;
		this.selected = selected;
	}





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

	
	public String getButton_id() {
		return button_id;
	}

	
	public void setButton_id(String button_id) {
		this.button_id = button_id;
	}

	
	public String getButton_name() {
		return button_name;
	}

	
	public void setButton_name(String button_name) {
		this.button_name = button_name;
	}

	
	public String getSelected() {
		return selected;
	}
	
	
	public void setSelected(String selected) {
		this.selected = selected;
	}



	public String getRole_id() {
		return Role_id;
	}



	public void setRole_id(String role_id) {
		Role_id = role_id;
	}



	

	
	
	
	
}
