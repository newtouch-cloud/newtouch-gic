package com.ca.cacore.manage.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IBranchVOModel  extends IPageCount{

	public Integer getParent_seq_id();

	public void setParent_seq_id(Integer parent_seq_id);

	public String getParent_branch_name();

	public void setParent_branch_name(String parent_branch_name);

	public String getParent_branch_level();

	public void setParent_branch_level(String parent_branch_level);

	public String getParent_branch_id();

	public void setParent_branch_id(String parent_branch_id);

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_parentid();

	public void setBranch_parentid(String branch_parentid);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBranch_name();

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

	public String getRemark();

	public void setRemark(String remark);

	public String getCreateUser();

	public void setCreateUser(String createUser);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getModifyUser();

	public void setModifyUser(String modifyUser);

	public Date getModifyDate();

	public void setModifyDate(Date modifyDate);
	
	public String getStatus_name() ;
	
	public void setStatus_name(String status_name) ;
	
	public String getBranch_level_name() ;
	
	public void setBranch_level_name(String branch_level_name) ;
	
	public String getCost_center() ;
	
	public void setCost_center(String cost_center) ;
	
	public String getSettle_center();
	
	public void setSettle_center(String settle_center) ;
	
	public String getBranch_ids() ;
	public void setBranch_ids(String branch_ids) ;
	
	public String getEmp_id() ;
	
	public void setEmp_id(String emp_id) ;
	
	public int getIsSetTax() ;
	
	public void setIsSetTax(int isSetTax);
	
	//新增省市县--孙豪
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
	
	//添加权限管理--增加用户的权限集   by  孙豪
	public String getBranch_list();

	public void setBranch_list(String branch_list);
	public String getPermitcode();

	public void setPermitcode(String permitcode);

	public String getPermitarea();

	public void setPermitarea(String permitarea);
	
	public String getChannelcode();

	public void setChannelcode(String channelcode);
	
	public String getBranchstatus();

	public void setBranchstatus(String branchstatus);
	//by zdd 20190610 start
	public String getUnifiedSocialCreditNO();

	public void setUnifiedSocialCreditNO(String unifiedSocialCreditNO);
    
	public Date getBuslicensefounddate();

	public void setBuslicensefounddate(Date buslicensefounddate);

	public Date getExittime();

	public void setExittime(Date exittime);
	public String getLicensepath();
	public void setLicensepath(String licensepath);
	//by zdd 20190610 end

	String getDeatailedaddress();

	void setDeatailedaddress(String deatailedaddress);
	
	public String getSbname();

	public void setSbname(String sbname);
}