package com.ca.cacore.msss.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 
 * @author Wang_ds
 * @since 2013-12-4
 * @description PDT_Product_Llife 寿险组合产品信息';
 */
public class PkgLifeVOModel implements IPkgLifeVOModel {
	private Integer seq_id; // 主键
	private String pkg_id; // 组合产品代码
	private String pkg_name; // 组合产品名称
	private String pkg_abbr; // 组合产品简称
	private String pkg_enName; //组合产品英文名称
	private String pkg_enAbbr; //组合产品英文简称
	private Date start_date; // 起售时间
	private Date end_date; // 停售时间
	private String status; // 状态
	private String status_name;//状态名称
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private String [] product_id ;//产品代码
	private String [] insBranch_id;//多个保险公司代码
	private List<IProductLlifeVOModel> productList = new ArrayList<IProductLlifeVOModel>();
	private PageCount pageCount=new PageCount();
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getPkg_id() {
		return pkg_id;
	}
	public void setPkg_id(String pkg_id) {
		this.pkg_id = pkg_id;
	}
	public String getPkg_name() {
		return pkg_name;
	}
	public void setPkg_name(String pkg_name) {
		this.pkg_name = pkg_name;
	}
	public String getPkg_abbr() {
		return pkg_abbr;
	}
	public void setPkg_abbr(String pkg_abbr) {
		this.pkg_abbr = pkg_abbr;
	}
	public String getPkg_enName() {
		return pkg_enName;
	}
	public void setPkg_enName(String pkg_enName) {
		this.pkg_enName = pkg_enName;
	}
	public String getPkg_enAbbr() {
		return pkg_enAbbr;
	}
	public void setPkg_enAbbr(String pkg_enAbbr) {
		this.pkg_enAbbr = pkg_enAbbr;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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
	public String[] getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String[] product_id) {
		this.product_id = product_id;
	}
	public List<IProductLlifeVOModel> getProductList() {
		return productList;
	}
	public void setProductList(List<IProductLlifeVOModel> productList) {
		this.productList = productList;
	}
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	
	
	
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
	public String[] getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String[] insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	
}
