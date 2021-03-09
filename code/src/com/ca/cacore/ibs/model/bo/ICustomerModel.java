package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface ICustomerModel extends IPageCount{
	
	public String getCustomer_type();
	public void setCustomer_type(String customer_type);
	public Date getCerti_validDate();

	public void setCerti_validDate(Date certi_validDate) ;

	public String getIs_telMsgService() ;

	public void setIs_telMsgService(String is_telMsgService);

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract String getGender();

	public abstract void setGender(String gender);

	public abstract String getBirthday();

	public abstract void setBirthday(String birthday);

	public abstract String getCerti_type();

	public abstract void setCerti_type(String certi_type);

	public abstract String getCerti_no();

	public abstract void setCerti_no(String certi_no);

	public abstract String getEducation();

	public abstract void setEducation(String education);

	public abstract String getNationality();

	public abstract void setNationality(String nationality);

	public abstract String getNation();

	public abstract void setNation(String nation);

	public abstract String getHomeplace();

	public abstract void setHomeplace(String homeplace);

	public abstract String getSeat_address();

	public abstract void setSeat_address(String seat_address);

	public abstract String getHeight();

	public abstract void setHeight(String height);

	public abstract String getWeight();

	public abstract void setWeight(String weight);

	public abstract String getPolitical();

	public abstract void setPolitical(String political);

	public abstract String getEducation2();

	public abstract void setEducation2(String education2);

	public abstract String getMarital_stat();

	public abstract void setMarital_stat(String marital_stat);

	public abstract String getHealth();

	public abstract void setHealth(String health);

	public abstract String getJob_type();

	public abstract void setJob_type(String job_type);

	public abstract String getJob_code();

	public abstract void setJob_code(String job_code);

	public abstract String getIncome_type();

	public abstract void setIncome_type(String income);

	public abstract String getBank_code();

	public abstract void setBank_code(String bank_code);

	public abstract String getBank_account_no();

	public abstract void setBank_account_no(String bank_account_no);

	public abstract String getBank_account_name();

	public abstract void setBank_account_name(String bank_account_name);

	public abstract String getStatus();

	public abstract void setStatus(String status);

	public abstract String getRemark();

	public abstract void setRemark(String remark);

	public abstract String getCreateUser();

	public abstract void setCreateUser(String createUser);

	public abstract Date getCreateDate();

	public abstract void setCreateDate(Date createDate);

	public abstract String getModifyUser();

	public abstract void setModifyUser(String modifyUser);

	public abstract Date getModifyDate();

	public abstract void setModifyDate(Date modifyDate);

}