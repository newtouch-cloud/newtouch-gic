package com.ca.cacore.manage.model.bo;

import com.newtouch.component.c11assistant.ITree;

public class BranchTreeModel extends BranchModel implements ITree{

	private String pid;
	private String id;
	private String name;
	private String blevel;
	private String agency;
	
	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	@Override
	public String getPid() {
		return this.getBranch_parentid();
	}

	@Override
	public void setPid(String branch_parentid) {
		this.setBranch_parentid(branch_parentid);
	}
	
	public String getId() {
		return this.getBranch_id();
	}

	public void setId(String branch_id) {
		this.setBranch_id(branch_id);
	}
	
	public String getName(){
		return this.getBranch_name();
	}

	public void setName(String branch_name){
		this.setBranch_name(branch_name);
	}

	public String getBlevel() {
		return blevel;
	}

	public void setBlevel(String blevel) {
		this.blevel = blevel;
	}
}
