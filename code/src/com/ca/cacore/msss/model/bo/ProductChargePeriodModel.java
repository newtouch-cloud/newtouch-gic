package com.ca.cacore.msss.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 
 * @author Wangds
 * @since 2013-12-5
 * @description PDT_Product_Charge_Period 产品可选缴费期限类型
 */
public class ProductChargePeriodModel implements IProductChargePeriodModel  {
	
	private Integer seq_id; // 主键
	private String insBranch_id; // 保险公司
	private String product_id; // 险种代码
	private String product_ver; // 险种版本
	private String charge_period_code; //缴费期限类型代码
	private int orderNum; //排序序号
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private PageCount pageCount=new PageCount();
	
	public ProductChargePeriodModel() {
	}
	public ProductChargePeriodModel(String product_id) {
		this.product_id = product_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_ver() {
		return product_ver;
	}
	public void setProduct_ver(String product_ver) {
		this.product_ver = product_ver;
	}
	public String getCharge_period_code() {
		return charge_period_code;
	}
	public void setCharge_period_code(String charge_period_code) {
		this.charge_period_code = charge_period_code;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
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
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
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
