package com.ca.cacore.msss.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IInsRncDfnModel {

	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name();
	public void setBranch_name(String branch_name);
	
	public String getPoundage();
	public void setPoundage(String poundage);
	
	public String getClassCode();

	public void setClassCode(String classCode);

	public String getClassName();

	public void setClassName(String className);

	public String getRiskCode();

	public void setRiskCode(String riskCode);

	public String getRiskName();

	public void setRiskName(String riskName);

	public String getStatus();

	public void setStatus(String status);

	public String getStatus_name();

	public void setStatus_name(String status_name);

	public String getRtype();

	public void setRtype(String rtype);
	
	public String getProduct_source();
	public void setProduct_source(String product_source);

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

	public int getStart();

	public int getLimit();

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);
	
	public Date getModifydate();

	public void setModifydate(Date modifydate);
	
	public String getProduct_code();
	public void setProduct_code(String product_code);
	public String getProduct_name();
	public void setProduct_name(String product_name);
	public String getType_code();
	public void setType_code(String type_code);
	public String getType_name();
	public void setType_name(String type_name);
	//by zdd02 20190617
    public String getParname();
	public void setParname(String parname);
	public String getParcode() ;
	public void setParcode(String parcode);
	public String getOrg_level();
	public void setOrg_level(String org_level);
	public String getModifyUser();
	public void setModifyUser(String modifyUser);
	public String getBjtype();
	public void setBjtype(String bjtype);//zddxiu
	//by zdd02 20190619
	public String getBjtypename();
	public void setBjtypename(String bjtypename);
	//start add by lyn 20190911
	public String getFlag();
	public void setFlag(String flag);
	//end add by lyn 20190911
}