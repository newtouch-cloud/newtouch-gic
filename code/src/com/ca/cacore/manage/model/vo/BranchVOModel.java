package com.ca.cacore.manage.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;


public class BranchVOModel implements IBranchVOModel {
	
	private Integer seq_id; // 主键
	private String branch_parentid; // 上级机构代码
	private String branch_id; // 机构代码
	private String branch_name; // 机构名称
	private String branch_abbr; // 机构简称
	private String branch_level; // 机构级别
	private String branch_allpath;//机构路径
	private String delegate;//法人代表
	private String address; // 联系地址
	private String zip; // 邮政编码
	private String telephone; // 电话号码
	private String fax; // 机构传真
	private String email;//邮箱
	private Date found_date; // 建立日期
	private Date recall_date; // 撤消日期
	private String status; // 状态
	private String status_name;//状态名称
	private String remark; // 备注
	private String cost_center;//成本中心
	private String settle_center;//结算中心
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间

	
	private String parent_branch_name;
	private String parent_branch_level;
	private String parent_branch_id;
	private Integer parent_seq_id; 
	private String branch_level_name;
	private String branch_ids;
	private int isSetTax;//是否设置过税率
	private String emp_id;
	
	//添加省县市(孙豪)
	private String province;//省
	private String city;//市
	private String area;//县
	private String province_code;//省id
	private String city_code;//市id
	private String area_code;//县id
	//添加权限管理--增加用户的权限集   by  孙豪
	private String branch_list;
	public String permitcode;//保监许可机构编码
	public String permitarea;//地址
	private String channelcode;//渠道代码信息
	private String branchstatus;
	
	//统一社会信用代码 by zdd 20190610
	private String unifiedSocialCreditNO;
    private Date buslicensefounddate;  //营业执照成立日期
    private Date exittime;//退出时间
    private String licensepath;//营业执照
    private String deatailedaddress; //详细地址
    private String sbname;
    // by zdd end
    
	public String getUnifiedSocialCreditNO() {
		return unifiedSocialCreditNO;
	}
    
	public void setUnifiedSocialCreditNO(String unifiedSocialCreditNO) {
		this.unifiedSocialCreditNO = unifiedSocialCreditNO;
	}
   

	public String getBranchstatus() {
		return branchstatus;
	}

	public void setBranchstatus(String branchstatus) {
		this.branchstatus = branchstatus;
	}

	public String getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}

	public String getPermitcode() {
		return permitcode;
	}

	public void setPermitcode(String permitcode) {
		this.permitcode = permitcode;
	}

	public String getPermitarea() {
		return permitarea;
	}

	public void setPermitarea(String permitarea) {
		this.permitarea = permitarea;
	}

	public String getBranch_list() {
		return branch_list;
	}

	public void setBranch_list(String branch_list) {
		this.branch_list = branch_list;
	}
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	private PageCount pageCount= new PageCount();
	
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
	
	public Integer getParent_seq_id() {
		return parent_seq_id;
	}
	public void setParent_seq_id(Integer parent_seq_id) {
		this.parent_seq_id = parent_seq_id;
	}
	public String getParent_branch_name() {
		return parent_branch_name;
	}
	public void setParent_branch_name(String parent_branch_name) {
		this.parent_branch_name = parent_branch_name;
	}
	public String getParent_branch_level() {
		return parent_branch_level;
	}
	public void setParent_branch_level(String parent_branch_level) {
		this.parent_branch_level = parent_branch_level;
	}
	public String getParent_branch_id() {
		return parent_branch_id;
	}
	public void setParent_branch_id(String parent_branch_id) {
		this.parent_branch_id = parent_branch_id;
	}

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getBranch_parentid() {
		return branch_parentid;
	}
	public void setBranch_parentid(String branch_parentid) {
		this.branch_parentid = branch_parentid;
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

	public String getBranch_abbr() {
		return branch_abbr;
	}


	public void setBranch_abbr(String branch_abbr) {
		this.branch_abbr = branch_abbr;
	}


	public String getBranch_level() {
		return branch_level;
	}


	public void setBranch_level(String branch_level) {
		this.branch_level = branch_level;
	}


	public String getBranch_allpath() {
		return branch_allpath;
	}


	public void setBranch_allpath(String branch_allpath) {
		this.branch_allpath = branch_allpath;
	}


	public String getDelegate() {
		return delegate;
	}


	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getFound_date() {
		return found_date;
	}


	public void setFound_date(Date found_date) {
		
		this.found_date = found_date;
	}


	public Date getRecall_date() {
		return recall_date;
	}


	public void setRecall_date(Date recall_date) {
		this.recall_date = recall_date;
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


	public String getCreateUser() {
		return createUser;
	}


	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	@Override
	public Date getCreateDate() {;
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
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getBranch_level_name() {
		return branch_level_name;
	}
	public void setBranch_level_name(String branch_level_name) {
		this.branch_level_name = branch_level_name;
	}
	public String getCost_center() {
		return cost_center;
	}
	public void setCost_center(String cost_center) {
		this.cost_center = cost_center;
	}
	public String getSettle_center() {
		return settle_center;
	}
	public void setSettle_center(String settle_center) {
		this.settle_center = settle_center;
	}
	public String getBranch_ids() {
		return branch_ids;
	}
	public void setBranch_ids(String branch_ids) {
		this.branch_ids = branch_ids;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public int getIsSetTax() {
		return isSetTax;
	}
	public void setIsSetTax(int isSetTax) {
		this.isSetTax = isSetTax;
	}

	public Date getBuslicensefounddate() {
		return buslicensefounddate;
	}

	public void setBuslicensefounddate(Date buslicensefounddate) {
		this.buslicensefounddate = buslicensefounddate;
	}

	public Date getExittime() {
		return exittime;
	}

	public void setExittime(Date exittime) {
		this.exittime = exittime;
	}

	public String getLicensepath() {
		return licensepath;
	}

	public void setLicensepath(String licensepath) {
		this.licensepath = licensepath;
	}

	@Override
	public String getDeatailedaddress() {
		return deatailedaddress;
	}
	@Override
	public void setDeatailedaddress(String deatailedaddress) {
		this.deatailedaddress = deatailedaddress;
	}

	public String getSbname() {
		return sbname;
	}

	public void setSbname(String sbname) {
		this.sbname = sbname;
	}
	
	
}
