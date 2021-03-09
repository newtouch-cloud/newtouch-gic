package com.newtouch.core.rightsmgmt.model;

import com.newtouch.core.model.IPageCount;

public interface ISuperiorModel  extends IPageCount{
	public String getOpt_no();
	public void setOpt_no(String opt_no);
	public String getOpt_name();
	public void setOpt_name(String opt_name);
	public String getSuperior_no();
	public void setSuperior_no(String superior_no);
	public String getSuperior_name();
	public void setSuperior_name(String superior_name);
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name();
	public void setBranch_name(String branch_name);
	public String getSuperior_id();
	public void setSuperior_id(String superior_id);

}
