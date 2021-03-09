package com.ca.cacore.mass.model.bo;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

import java.sql.Date;

/**
 * 机构基本信息
 * 
 * @author ma_cj
 *
 */
public class CompanyBranchModel implements ICompanyBranchModel {
	private Integer seq_id; // 主键
	private String branch_parentid; // 上级机构代码
	private String branch_parentname; // 上级机构名称
	private String branch_no; //保险公司代码
	private String branch_id; // 机构代码
	private String branch_name; // 机构名称
	private String branch_nameA; // 查询用机构名称
	private String branch_abbr; // 机构简称
	private String branch_level; // 机构级别
	private String branch_allpath;// 机构路径
	private String delegate;// 法人代表
	private String address; // 联系地址
	private String zip; // 邮政编码
	private String telephone; // 电话号码
	private String fax; // 机构传真
	private String email;// 电子邮箱
	private Date found_date; // 建立日期
	private Date recall_date; // 撤消日期
	private String status; // 状态
	private String statusA; // 详情状态
	private String remark; // 备注
	private String cost_center;// 成本中心
	private String settle_center;// 结算中心
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private Date createDateL; // 查询时间起
	private Date createDateG; // 查询时间末
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private String emp_id; // 员工代码(为了控制权限)
	private PageCount pageCount = new PageCount();
	private String is4Sub;// 是否包含下级机构标志
	// 添加省县市(孙豪)
	private String province;// 省
	private String city;// 市
	private String area;// 县
	private String province_code;// 省id
	private String city_code;// 市id
	private String area_code;// 县id

	//添加机构代码
	private String bleng_branchid;
	private String sales_org_name;
	private String  branch_cpyid;
	private String branch_cpyname;
    private String branch_sort;//保险公司分类  zddxiu
    private String branch_sortname;//保险公司分类  zddxiu
    private String bleng_branchname;
    
    private String cpy_serno ;//流水号  add by lyn
    private String sysbranch_id;
    
	//保险公司机构级别 区别销售公司
	private String cpy_branch_level;

	public String getCpy_branch_level() {
		return cpy_branch_level;
	}

	public void setCpy_branch_level(String cpy_branch_level) {
		this.cpy_branch_level = cpy_branch_level;
	}

	/**
	 * @return the createDateL
	 */
	public Date getCreateDateL() {
		return createDateL;
	}

	/**
	 * @param createDateL
	 *            the createDateL to set
	 */
	public void setCreateDateL(Date createDateL) {
		this.createDateL = createDateL;
	}

	/**
	 * @return the createDateG
	 */
	public Date getCreateDateG() {
		return createDateG;
	}

	/**
	 * @param createDateG
	 *            the createDateG to set
	 */
	public void setCreateDateG(Date createDateG) {
		this.createDateG = createDateG;
	}

	/**
	 * @return the branch_parentname
	 */
	public String getBranch_parentname() {
		return branch_parentname;
	}

	/**
	 * @param branch_parentname
	 *            the branch_parentname to set
	 */
	public void setBranch_parentname(String branch_parentname) {
		this.branch_parentname = branch_parentname;
	}

	public CompanyBranchModel() {
	};

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

	public CompanyBranchModel(Integer seq_id) {
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
	public String getBranch_no() {
		return branch_no;
	}

	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
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

	/**
	 * @return the branch_nameA
	 */
	public String getBranch_nameA() {
		return branch_nameA;
	}

	/**
	 * @param branch_nameA
	 *            the branch_nameA to set
	 */
	public void setBranch_nameA(String branch_nameA) {
		this.branch_nameA = branch_nameA;
	}

	@Override
	public void setBranch_abbr(String branch_abbr) {
		this.branch_abbr = branch_abbr;
	}

	@Override
	public String getBranch_level() {
		return branch_level;
	}

	/**
	 * @return the statusA
	 */
	public String getStatusA() {
		return statusA;
	}

	/**
	 * @param statusA
	 *            the statusA to set
	 */
	public void setStatusA(String statusA) {
		this.statusA = statusA;
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

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the province_code
	 */
	public String getProvince_code() {
		return province_code;
	}

	/**
	 * @param province_code
	 *            the province_code to set
	 */
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}

	/**
	 * @return the city_code
	 */
	public String getCity_code() {
		return city_code;
	}

	/**
	 * @param city_code
	 *            the city_code to set
	 */
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	/**
	 * @return the area_code
	 */
	public String getArea_code() {
		return area_code;
	}

	/**
	 * @param area_code
	 *            the area_code to set
	 */
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getBleng_branchid() {
		return bleng_branchid;
	}

	public void setBleng_branchid(String bleng_branchid) {
		this.bleng_branchid = bleng_branchid;
	}

	public String getSales_org_name() {
		return sales_org_name;
	}

	public void setSales_org_name(String sales_org_name) {
		this.sales_org_name = sales_org_name;
	}

	public String getBranch_cpyid() {
		return branch_cpyid;
	}

	public void setBranch_cpyid(String branch_cpyid) {
		this.branch_cpyid = branch_cpyid;
	}

	public String getBranch_cpyname() {
		return branch_cpyname;
	}

	public void setBranch_cpyname(String branch_cpyname) {
		this.branch_cpyname = branch_cpyname;
	}

	public String getBranch_sort() {
		return branch_sort;
	}

	public void setBranch_sort(String branch_sort) {
		this.branch_sort = branch_sort;
	}

	public String getBranch_sortname() {
		return branch_sortname;
	}

	public void setBranch_sortname(String branch_sortname) {
		this.branch_sortname = branch_sortname;
	}
    @Override
	public String getBleng_branchname() {
		return bleng_branchname;
	}
    @Override
	public void setBleng_branchname(String bleng_branchname) {
		this.bleng_branchname = bleng_branchname;
	}

	public String getCpy_serno() {
		return cpy_serno;
	}

	public void setCpy_serno(String cpy_serno) {
		this.cpy_serno = cpy_serno;
	}

	public String getSysbranch_id() {
		return sysbranch_id;
	}

	public void setSysbranch_id(String sysbranch_id) {
		this.sysbranch_id = sysbranch_id;
	}

	

    


}
