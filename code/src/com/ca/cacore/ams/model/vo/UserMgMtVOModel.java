package com.ca.cacore.ams.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class UserMgMtVOModel implements IUserMgMtVOModel {

	private Integer seq_id;//主键
	private String status;// 是否登录状态：1-是，0-否
	private String remark; // 备注
	private String createuser; // 创建人
	private Date createdate; // 创建时间
	private String modifyuser; // 最后修改人
	private Date modifydate; // 最后时间
	
	private String opt_no;//用户代码
	private String opt_name;//用户名
	private String opt_password;//用户密码
	private String   start_date;//有效开始日期
	private String   end_date;//有效结束日期
	private String session_id;//sessionid
	
	 private Integer serno;      
	 private String branch_no;//机构代码
	 private String dept_no;//部门代码
	 private String opt_type;//用户类型  
	 private String opt_mail;   
	 private String opt_confer; 
	 private String opt_sex;    
	 private String opt_status; 
	 private Timestamp   crt_date;   
	 private Timestamp   mdf_date ;  
	 private String crt_user;   
	 private String mdf_user;   
	 private String data_flag ; 
	 private String patch_memo; 
	 private String is4sub ;    
	 private String person_type;//用户类型
	 private Date   start_date_new;//有效开始日期
	 private Date   end_date_new;//有效结束日期
	 private String role_no;//角色代码
	 private String object_no;       
	 private String object_type;     
	 private String data_auth_no;    
	 private String data_auth_type;  
     private String data_auth_child; 
     private String is_half_check;   
	 private String is_display;
	 private String branch_id;//机构代码
	 private String branch_name;//机构名称
	 
	 private Double sales_count;//在职人员数量
	 private Double policyno_count;//保单数量
	 private Double netpremium_count;//保费金额
	 private Double fnum_count;//手续费金额
	 private Date   signdate;//签单日期
	 
	 private String sales_status;//人员状态
	 private String sales_id;//销售员代码
	 private String sales_name;//销售员姓名
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Integer getSeq_id() {
		return seq_id;
	}
	/** 
	* 
	* @param seq_id 
	* @description:
	*/
	@Override
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getStatus() {
		return status;
	}
	/** 
	* 
	* @param status 
	* @description:
	*/
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getRemark() {
		return remark;
	}
	/** 
	* 
	* @param remark 
	* @description:
	*/
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCreateuser() {
		return createuser;
	}
	/** 
	* 
	* @param createuser 
	* @description:
	*/
	@Override
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getCreatedate() {
		return createdate;
	}
	/** 
	* 
	* @param createdate 
	* @description:
	*/
	@Override
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getModifyuser() {
		return modifyuser;
	}
	/** 
	* 
	* @param modifyuser 
	* @description:
	*/
	@Override
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getModifydate() {
		return modifydate;
	}
	/** 
	* 
	* @param modifydate 
	* @description:
	*/
	@Override
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getOpt_no() {
		return opt_no;
	}
	/** 
	* 
	* @param opt_no 
	* @description:
	*/
	@Override
	public void setOpt_no(String opt_no) {
		this.opt_no = opt_no;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getOpt_name() {
		return opt_name;
	}
	/** 
	* 
	* @param opt_name 
	* @description:
	*/
	@Override
	public void setOpt_name(String opt_name) {
		this.opt_name = opt_name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getOpt_password() {
		return opt_password;
	}
	/** 
	* 
	* @param opt_password 
	* @description:
	*/
	@Override
	public void setOpt_password(String opt_password) {
		this.opt_password = opt_password;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getStart_date() {
		return start_date;
	}
	/** 
	* 
	* @param start_date 
	* @description:
	*/
	@Override
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getEnd_date() {
		return end_date;
	}
	/** 
	* 
	* @param end_date 
	* @description:
	*/
	@Override
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getSession_id() {
		return session_id;
	}
	/** 
	* 
	* @param session_id 
	* @description:
	*/
	@Override
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public Integer getSerno() {
		return serno;
	}
	public void setSerno(Integer serno) {
		this.serno = serno;
	}
	public String getBranch_no() {
		return branch_no;
	}
	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}
	public String getDept_no() {
		return dept_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}
	public String getOpt_type() {
		return opt_type;
	}
	public void setOpt_type(String opt_type) {
		this.opt_type = opt_type;
	}
	public String getOpt_mail() {
		return opt_mail;
	}
	public void setOpt_mail(String opt_mail) {
		this.opt_mail = opt_mail;
	}
	public String getOpt_confer() {
		return opt_confer;
	}
	public void setOpt_confer(String opt_confer) {
		this.opt_confer = opt_confer;
	}
	public String getOpt_sex() {
		return opt_sex;
	}
	public void setOpt_sex(String opt_sex) {
		this.opt_sex = opt_sex;
	}
	public String getOpt_status() {
		return opt_status;
	}
	public void setOpt_status(String opt_status) {
		this.opt_status = opt_status;
	}
	public Timestamp getCrt_date() {
		return crt_date;
	}
	public void setCrt_date(Timestamp crt_date) {
		this.crt_date = crt_date;
	}
	public Timestamp getMdf_date() {
		return mdf_date;
	}
	public void setMdf_date(Timestamp mdf_date) {
		this.mdf_date = mdf_date;
	}
	public String getCrt_user() {
		return crt_user;
	}
	public void setCrt_user(String crt_user) {
		this.crt_user = crt_user;
	}
	public String getMdf_user() {
		return mdf_user;
	}
	public void setMdf_user(String mdf_user) {
		this.mdf_user = mdf_user;
	}
	public String getData_flag() {
		return data_flag;
	}
	public void setData_flag(String data_flag) {
		this.data_flag = data_flag;
	}
	public String getPatch_memo() {
		return patch_memo;
	}
	public void setPatch_memo(String patch_memo) {
		this.patch_memo = patch_memo;
	}
	public String getIs4sub() {
		return is4sub;
	}
	public void setIs4sub(String is4sub) {
		this.is4sub = is4sub;
	}
	public String getPerson_type() {
		return person_type;
	}
	public void setPerson_type(String person_type) {
		this.person_type = person_type;
	}
	public Date getStart_date_new() {
		return start_date_new;
	}
	public void setStart_date_new(Date start_date_new) {
		this.start_date_new = start_date_new;
	}
	public Date getEnd_date_new() {
		return end_date_new;
	}
	public void setEnd_date_new(Date end_date_new) {
		this.end_date_new = end_date_new;
	}
	public String getRole_no() {
		return role_no;
	}
	public void setRole_no(String role_no) {
		this.role_no = role_no;
	}
	public String getObject_no() {
		return object_no;
	}
	public void setObject_no(String object_no) {
		this.object_no = object_no;
	}
	public String getObject_type() {
		return object_type;
	}
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}
	public String getData_auth_no() {
		return data_auth_no;
	}
	public void setData_auth_no(String data_auth_no) {
		this.data_auth_no = data_auth_no;
	}
	public String getData_auth_type() {
		return data_auth_type;
	}
	public void setData_auth_type(String data_auth_type) {
		this.data_auth_type = data_auth_type;
	}
	public String getData_auth_child() {
		return data_auth_child;
	}
	public void setData_auth_child(String data_auth_child) {
		this.data_auth_child = data_auth_child;
	}
	public String getIs_half_check() {
		return is_half_check;
	}
	public void setIs_half_check(String is_half_check) {
		this.is_half_check = is_half_check;
	}
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public Double getSales_count() {
		return sales_count;
	}
	public void setSales_count(Double sales_count) {
		this.sales_count = sales_count;
	}
	public Double getPolicyno_count() {
		return policyno_count;
	}
	public void setPolicyno_count(Double policyno_count) {
		this.policyno_count = policyno_count;
	}
	public Double getNetpremium_count() {
		return netpremium_count;
	}
	public void setNetpremium_count(Double netpremium_count) {
		this.netpremium_count = netpremium_count;
	}
	public Double getFnum_count() {
		return fnum_count;
	}
	public void setFnum_count(Double fnum_count) {
		this.fnum_count = fnum_count;
	}
	public Date getSigndate() {
		return signdate;
	}
	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}
	public String getSales_status() {
		return sales_status;
	}
	public void setSales_status(String sales_status) {
		this.sales_status = sales_status;
	}
	public String getSales_id() {
		return sales_id;
	}
	public void setSales_id(String sales_id) {
		this.sales_id = sales_id;
	}
	public String getSales_name() {
		return sales_name;
	}
	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}
	
	
}
