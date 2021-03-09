package com.newtouch.organization.webapp.webtag;

import java.util.List;

public interface IDynamicSelectAgency {

	public List<IDynamicSelectAgency> getNo();
	public List<IDynamicSelectAgency> getNo(String no);
	
	public String getAgreement_no();
	public void setAgreement_no(String agreement_no);
	
	public String getDisplayLable();
	public void setDisplayLable(String displayLable);
	
	public String getIsSelected();
	public void setIsSelected(String isSelected);
	
}
