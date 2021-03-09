package com.ca.cacore.msss.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;


/**
* @since:    2013年12月25日   
* @author    wang_ds
* @description: PDT_Product_Relation 寿险主险可选附加产品信息
*/
public class ProductRelationModel implements IProductRelationModel {
	
	private Integer seq_id; // 主键
	private String insBranch_id; // 保险公司
	private String master_id; // 主险产品代码
	private String product_id; // 附加险产品代码
	private String status; // 状态
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private PageCount pageCount=new PageCount();
	
	
	
	public ProductRelationModel() {
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

	public String getMaster_id() {
		return master_id;
	}

	public void setMaster_id(String master_id) {
		this.master_id = master_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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
