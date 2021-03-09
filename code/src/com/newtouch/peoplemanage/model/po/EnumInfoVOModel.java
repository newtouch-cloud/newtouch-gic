package com.newtouch.peoplemanage.model.po;

public class EnumInfoVOModel implements IEnumInfoVOModel{
	
	private String enum_code;
	
	private String enum_name;
	
	private String up_enum;
	
	private String enum_order;

	public String getEnum_code() {
		return enum_code;
	}

	public void setEnum_code(String enum_code) {
		this.enum_code = enum_code;
	}

	public String getEnum_name() {
		return enum_name;
	}

	public void setEnum_name(String enum_name) {
		this.enum_name = enum_name;
	}

	public String getUp_enum() {
		return up_enum;
	}

	public void setUp_enum(String up_enum) {
		this.up_enum = up_enum;
	}

	public String getEnum_order() {
		return enum_order;
	}

	public void setEnum_order(String enum_order) {
		this.enum_order = enum_order;
	}
	
	
}
