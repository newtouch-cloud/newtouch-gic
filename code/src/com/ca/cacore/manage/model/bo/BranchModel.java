package com.ca.cacore.manage.model.bo;

import java.util.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;



/**
 * 机构基本信息
 * @author ma_cj
 *
 */
public class BranchModel implements IBranchModel{
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
	private String email;//电子邮箱
	private Date found_date; // 建立日期
	private Date recall_date; // 撤消日期
	private String status; // 状态
	private String remark; // 备注
	private String cost_center;//成本中心
	private String settle_center;//结算中心
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private String emp_id;  //员工代码(为了控制权限)
	private PageCount pageCount= new PageCount();
	private String branch_level_name;//机构层级 by zdd 20190606
	private String deatailedaddress; //详细地址
	private String is4Sub;//是否包含下级机构标志
	
	public BranchModel(){};
	
	//添加省县市   by  孙豪
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
	
	//添加是否是父节点属性
	private String isParent;
	private String agency;
	//统一社会信用代码 by zdd 20190610
	private String unifiedSocialCreditNO;
    private Date buslicensefounddate;  //营业执照成立日期
    private Date exittime;//退出时间
    private String licensepath;//营业执照
    private String beglongarea;//所属区域  zddxiu
  // by zdd 20190610 end
    
    private String branch_level_code;//机构层级code值   a by liu_yn
    
    
	public String getBranch_level_code() {
		return branch_level_code;
	}

	public void setBranch_level_code(String branch_level_code) {
		this.branch_level_code = branch_level_code;
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

	@Override
	public PageCount getPageCount() {
		return pageCount;
	}

	@Override
	public void setPageCount(PageCount pagination) {
		this.pageCount = pagination;
	}
	

	public int getStart() {
		return this.getPageCount().getStart();
	}

	public int getLimit() {
		return this.getPageCount().getLimit();
	}	
	
	public BranchModel(Integer seq_id){
		this.setSeq_id(seq_id);
	}

	@Override
	public Integer getSeq_id() {
		return seq_id;
	}

	
	@Override
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}


	@Override
	public String getBranch_parentid() {
		return branch_parentid;
	}


	@Override
	public void setBranch_parentid(String branch_parentid) {
		this.branch_parentid = branch_parentid;
	}
	
	public String getBranch_id() {
		return branch_id;
	}

	@Override
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	
	@Override
	public String getBranch_name() {
		return branch_name;
	}

	@Override
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	@Override
	public String getBranch_abbr() {
		return branch_abbr;
	}
	@Override
	public void setBranch_abbr(String branch_abbr) {
		this.branch_abbr = branch_abbr;
	}
	@Override
	public String getBranch_level() {
		return branch_level;
	}
	@Override
	public void setBranch_level(String branch_level) {
		this.branch_level = branch_level;
	}
	@Override
	public String getBranch_allpath() {
		return branch_allpath;
	}
	@Override
	public void setBranch_allpath(String branch_allpath) {
		this.branch_allpath = branch_allpath;
	}
	@Override
	public String getDelegate() {
		return delegate;
	}
	@Override
	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}

	public String getAddress() {
		return address;
	}
	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getZip() {
		return zip;
	}
	@Override
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Override
	public String getTelephone() {
		return telephone;
	}

	@Override
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String getFax() {
		return fax;
	}
	@Override
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public Date getFound_date() {
		return found_date;
	}

	@Override
	public void setFound_date(Date found_date) {
		this.found_date = found_date;
	}

	@Override
	public Date getRecall_date() {
		return recall_date;
	}

	@Override
	public void setRecall_date(Date recall_date) {
		this.recall_date = recall_date;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String getRemark() {
		return remark;
	}
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getCreateUser() {
		return createUser;
	}

	@Override
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String getModifyUser() {
		return modifyUser;
	}

	@Override
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Override
	public Date getModifyDate() {
		return modifyDate;
	}
	@Override
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
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

	public String getIs4Sub() {
		return is4Sub;
	}

	public void setIs4Sub(String is4Sub) {
		this.is4Sub = is4Sub;
	}

	@Override
	public String getIsParent() {
		return isParent;
	}
	@Override
	public void setIsParent(String isparent) {
		isParent = isparent;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	@Override
	public String getBranch_level_name() {
		
		return branch_level_name;
	}

	public String getUnifiedSocialCreditNO() {
		return unifiedSocialCreditNO;
	}

	public void setUnifiedSocialCreditNO(String unifiedSocialCreditNO) {
		this.unifiedSocialCreditNO = unifiedSocialCreditNO;
	}

	@Override
	public void setBranch_level_name(String branch_level_name) {
		this.branch_level_name=branch_level_name;
		
	}
	@Override
	public Date getBuslicensefounddate() {
		return buslicensefounddate;
	}
	@Override
	public void setBuslicensefounddate(Date buslicensefounddate) {
		this.buslicensefounddate = buslicensefounddate;
	}
	@Override
	public Date getExittime() {
		return exittime;
	}
	@Override
	public void setExittime(Date exittime) {
		this.exittime = exittime;
	}
	@Override
	public String getLicensepath() {
		return licensepath;
	}
	@Override
	public void setLicensepath(String licensepath) {
		this.licensepath = licensepath;
	}
	@Override
	public String getBeglongarea() {
		return beglongarea;
	}
	@Override
	public void setBeglongarea(String beglongarea) {
		this.beglongarea = beglongarea;
	}
	@Override
	public String getDeatailedaddress() {
		return deatailedaddress;
	}
	@Override
	public void setDeatailedaddress(String deatailedaddress) {
		this.deatailedaddress = deatailedaddress;
	}

	
}
