package com.newtouch.organization.webapp.webtag.impl;

import java.util.List;

import com.newtouch.organization.webapp.webtag.IDynamicSelectAgency;

public class DynamicSelectAgency implements IDynamicSelectAgency{
	private String agreement_no = "";
	private String displayLable = "";
	private String isSelected = "";

	public String getAgreement_no() {
		return agreement_no;
	}

	public void setAgreement_no(String agreement_no) {
		this.agreement_no = agreement_no;
	}

	public String getDisplayLable() {
		return displayLable;
	}

	public void setDisplayLable(String displayLable) {
		this.displayLable = displayLable;
	}
	
	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

	@Override
	public List<IDynamicSelectAgency> getNo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDynamicSelectAgency> getNo(String no) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
