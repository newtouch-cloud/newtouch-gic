package com.ca.cacore.manage.model.bo;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

import java.io.Serializable;
import java.util.Date;
import com.ca.cacore.manage.model.bo.IUserModel;
/**
 * 操作用户基本信息
 *
 */
public class UserModel implements IUserModel,Serializable{

	private static final long serialVersionUID = 1L;
	private Integer seq_id;//主键
	private String branch_id;//机构代码
	private String user_type;//用户类型
	private String userName;//用户名	
	private String password;//登陆密码
	private String name;    //姓名	
	private String sex_code;//性别
	private String mobile;  //手机
	private String fixed_line;//固定电话
	private String email;   //电子邮件
	private String emp_id;  //员工代码
	private String job_tel; //办公电话
	private String job_address; //办公地址
	private String status;  //状态
	private String remark;  //备注
	private String createUser;//创建人
	private Date createDate;  //创建时间
	private String modifyUser;//最后修改人
	private Date modifyDate; //最后修改时间
	private String user_tupeName;//用户类型的名称
	private String portraitPath;//用户头像保存路径--用于显示用户头像-by张晨
	private String core_usercode;//绑定的核心用户代码 -by张晨
	private String core_password;//绑定的核心用户密码 -by张晨
	private PageCount pageCount = new PageCount();
	private String dept_list;//数据权限集合('10','1001')
	
	public UserModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}
	public UserModel(Integer seq_id,String userName){
		this.setSeq_id(seq_id);
		this.setUserName(userName);
	}
	
	public UserModel() {
	}

	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pagination) {
		this.pageCount = pagination;
	}
	public int getStart() {
		return this.getPageCount().getStart();
	}
	public int getLimit() {
		return this.getPageCount().getLimit();
	}	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex_code() {
		return sex_code;
	}
	public void setSex_code(String sex_code) {
		this.sex_code = sex_code;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFixed_line() {
		return fixed_line;
	}
	public void setFixed_line(String fixed_line) {
		this.fixed_line = fixed_line;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getJob_tel() {
		return job_tel;
	}
	public void setJob_tel(String job_tel) {
		this.job_tel = job_tel;
	}
	public String getJob_address() {
		return job_address;
	}
	public void setJob_address(String job_address) {
		this.job_address = job_address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public void setUser_typeName(String user_typeNme) {
		this.user_tupeName=user_typeNme;
	}
	@Override
	public String getUser_typeName() {
		return this.user_tupeName;
	}
	@Override
	public String getPortraitPath() {
		return portraitPath;
	}
	@Override
	public void setPortraitPath(String portraitPath) {
		this.portraitPath = portraitPath;
	}
	public String getCore_usercode() {
		return core_usercode;
	}
	public void setCore_usercode(String core_usercode) {
		this.core_usercode = core_usercode;
	}
	public String getCore_password() {
		return core_password;
	}
	public void setCore_password(String core_password) {
		this.core_password = core_password;
	}
	public String getDept_list() {
		return dept_list;
	}
	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}
	
	
}
