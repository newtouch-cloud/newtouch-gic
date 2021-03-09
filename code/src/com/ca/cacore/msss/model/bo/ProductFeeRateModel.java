package com.ca.cacore.msss.model.bo;

import java.sql.Date;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2013年12月25日   
* @author    wang_ds
* @description: PDT_Product_Fee_Rate 寿险产品手续费率信息
*/
public class ProductFeeRateModel implements IProductFeeRateModel    {

	private Integer seq_id; // 主键
	private String insBranch_id; // 保险公司
	private String product_id; // 产品代码
	private String coverage_period; // 保障期限类型
	private String coverage_year; //保障年数
	private String sell_way; // 销售方式代码
	private String sell_way_name; //销售方式名称
	private String charge_type; // 缴费方式
	private Integer charge_year; //缴费年限
	private String policy_year; //保单年度
	private String policy_period; //缴费次数
	private double fee_rate; //手续费率
	private Date start_date; //开始时间
	private Date end_date; //结束时间
	private String status; // 状态
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private PageCount pageCount=new PageCount();

	
	public ProductFeeRateModel() {
		super();
		// TODO Auto-generated constructor stub
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


	public String getCoverage_period() {
		return coverage_period;
	}


	public void setCoverage_period(String coverage_period) {
		this.coverage_period = coverage_period;
	}


	public String getCoverage_year() {
		return coverage_year;
	}


	public void setCoverage_year(String coverage_year) {
		this.coverage_year = coverage_year;
	}


	public String getSell_way() {
		return sell_way;
	}


	public void setSell_way(String sell_way) {
		this.sell_way = sell_way;
	}


	public String getSell_way_name() {
		return sell_way_name;
	}


	public void setSell_way_name(String sell_way_name) {
		this.sell_way_name = sell_way_name;
	}


	public String getCharge_type() {
		return charge_type;
	}


	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}


	public Integer getCharge_year() {
		return charge_year;
	}


	public void setCharge_year(Integer charge_year) {
		this.charge_year = charge_year;
	}


	public String getPolicy_year() {
		return policy_year;
	}


	public void setPolicy_year(String policy_year) {
		this.policy_year = policy_year;
	}


	public String getPolicy_period() {
		return policy_period;
	}


	public void setPolicy_period(String policy_period) {
		this.policy_period = policy_period;
	}


	public double getFee_rate() {
		return fee_rate;
	}


	public void setFee_rate(double fee_rate) {
		this.fee_rate = fee_rate;
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
