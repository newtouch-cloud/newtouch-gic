package com.ca.cacore.ams.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public interface IUserMgMtVOModel {

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getStatus();

	public abstract void setStatus(String status);

	public abstract String getRemark();

	public abstract void setRemark(String remark);

	public abstract String getCreateuser();

	public abstract void setCreateuser(String createuser);

	public abstract Date getCreatedate();

	public abstract void setCreatedate(Date createdate);

	public abstract String getModifyuser();

	public abstract void setModifyuser(String modifyuser);

	public abstract Date getModifydate();

	public abstract void setModifydate(Date modifydate);

	public abstract String getOpt_no();

	public abstract void setOpt_no(String opt_no);

	public abstract String getOpt_name();

	public abstract void setOpt_name(String opt_name);

	public abstract String getOpt_password();

	public abstract void setOpt_password(String opt_password);

	public abstract String getStart_date();

	public abstract void setStart_date(String start_date);

	public abstract String getEnd_date();

	public abstract void setEnd_date(String end_date);

	public abstract String getSession_id();

	public abstract void setSession_id(String session_id);
	
	public Integer getSerno();
	public void setSerno(Integer serno);
	public String getBranch_no() ;
	public void setBranch_no(String branch_no);
	public String getDept_no();
	public void setDept_no(String dept_no);
	public String getOpt_type();
	public void setOpt_type(String opt_type);
	public String getOpt_mail();
	public void setOpt_mail(String opt_mail);
	public String getOpt_confer();
	public void setOpt_confer(String opt_confer);
	public String getOpt_sex();
	public void setOpt_sex(String opt_sex);
	public String getOpt_status();
	public void setOpt_status(String opt_status);
	public Timestamp getCrt_date();
	public void setCrt_date(Timestamp crt_date);
	public Timestamp getMdf_date();
	public void setMdf_date(Timestamp mdf_date);
	public String getCrt_user();
	public void setCrt_user(String crt_user) ;
	public String getMdf_user();
	public void setMdf_user(String mdf_user);
	public String getData_flag() ;
	public void setData_flag(String data_flag);
	public String getPatch_memo();
	public void setPatch_memo(String patch_memo);
	public String getIs4sub();
	public void setIs4sub(String is4sub);
	public String getPerson_type();
	public void setPerson_type(String person_type);
	public Date getStart_date_new();
	public void setStart_date_new(Date start_date_new);
	public Date getEnd_date_new();
	public void setEnd_date_new(Date end_date_new);
	public String getRole_no();
	public void setRole_no(String role_no);
	
	public String getObject_no();
	public void setObject_no(String object_no);
	public String getObject_type();
	public void setObject_type(String object_type);
	public String getData_auth_no();
	public void setData_auth_no(String data_auth_no);
	public String getData_auth_type();
	public void setData_auth_type(String data_auth_type);
	public String getData_auth_child();
	public void setData_auth_child(String data_auth_child);
	public String getIs_half_check();
	public void setIs_half_check(String is_half_check);
	public String getIs_display() ;
	public void setIs_display(String is_display);
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name();
	public void setBranch_name(String branch_name) ;
	public Double getSales_count();
	public void setSales_count(Double sales_count);
	public Double getPolicyno_count();
	public void setPolicyno_count(Double policyno_count);
	public Double getNetpremium_count();
	public void setNetpremium_count(Double netpremium_count);
	public Double getFnum_count();
	public void setFnum_count(Double fnum_count);
	public Date getSigndate();
	public void setSigndate(Date signdate);
	public String getSales_status();
	public void setSales_status(String sales_status);
	public String getSales_id();
	public void setSales_id(String sales_id);
	public String getSales_name();
	public void setSales_name(String sales_name);
	
}