package com.ca.cacore.mass.model.bo;

import com.newtouch.core.model.IPageCount;

import java.sql.Date;

public interface ICompanyBranchModel extends IPageCount {
	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_parentid();

	public void setBranch_parentid(String branch_parentid);

	public String getBranch_parentname();

	public void setBranch_parentname(String branch_parentname);
	
	public String getBranch_no() ;

	public void setBranch_no(String branch_no) ;
	
	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBranch_name();

	public String getBranch_nameA();

	public void setBranch_nameA(String branch_nameA);

	public void setBranch_name(String branch_name);

	public String getBranch_abbr();

	public void setBranch_abbr(String branch_abbr);

	public String getBranch_level();

	public void setBranch_level(String branch_level);

	public String getBranch_allpath();

	public void setBranch_allpath(String branch_allpath);

	public String getDelegate();

	public void setDelegate(String delegate);

	public String getAddress();

	public void setAddress(String address);

	public String getZip();

	public void setZip(String zip);

	public String getTelephone();

	public void setTelephone(String telephone);

	public String getFax();

	public void setFax(String fax);

	public String getEmail();

	public void setEmail(String email);

	public Date getFound_date();

	public void setFound_date(Date found_date);

	public Date getRecall_date();

	public void setRecall_date(Date recall_date);

	public String getStatus();

	public void setStatus(String status);

	public String getStatusA();

	public void setStatusA(String statusA);

	public String getRemark();

	public void setRemark(String remark);

	public String getCreateUser();

	public void setCreateUser(String createUser);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getModifyUser();

	public void setModifyUser(String modifyUser);

	public Date getModifyDate();

	public void setCreateDateL(Date createDateL);

	public Date getCreateDateL();

	public void setCreateDateG(Date createDateG);

	public Date getCreateDateG();

	public void setModifyDate(Date modifyDate);

	public String getEmp_id();

	public void setEmp_id(String emp_id);

	public String getCost_center();

	public void setCost_center(String cost_center);

	public String getSettle_center();

	public void setSettle_center(String settle_center);

	public String getIs4Sub();

	public void setIs4Sub(String is4Sub);

	// 新增省市县--孙豪
	public String getProvince();

	public void setProvince(String province);

	public String getCity();

	public void setCity(String city);

	public String getArea();

	public void setArea(String area);

	public String getProvince_code();

	public void setProvince_code(String province_code);

	public String getCity_code();

	public void setCity_code(String city_code);

	public String getArea_code();

	public void setArea_code(String area_code);
	
	//新增机构树
	public String getBleng_branchid();
	public void setBleng_branchid(String bleng_branchid);
	public String getSales_org_name();
	public void setSales_org_name(String sales_org_name);
	public String getBranch_cpyid();
    public void setBranch_cpyid(String branch_cpyid);
    public String getBranch_cpyname();
    public void setBranch_cpyname(String branch_cpyname);


	public String getCpy_branch_level();

	public void setCpy_branch_level(String cpy_branch_level) ;
	public String getBranch_sort(); //zddxiu

	public void setBranch_sort(String branch_sort);
	public String getBranch_sortname();

	public void setBranch_sortname(String branch_sortname);

	String getBleng_branchname();

	void setBleng_branchname(String bleng_branchname);

	public String getCpy_serno();

	public void setCpy_serno(String cpy_serno);
	
	public String getSysbranch_id();

	public void setSysbranch_id(String sysbranch_id);
}