package com.ca.cacore.msss.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2013年12月25日   
* @author    wang_ds
* @description: PDT_Product_Assessment  寿险产品、产品组合评估信息
*/
public class ProductAssessmentModel implements IProductAssessmentModel {
	
	private Integer seq_id; // 主键
	private String product_id; // 险种代码
	private String insBranch_id; //保险公司 
	private String assessment_type; // 评估类型
	private Integer assess_stars; // 评估星级
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private PageCount pageCount=new PageCount();
	
	
	public ProductAssessmentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public ProductAssessmentModel(String assessment_type, Integer assess_stars) {
		super();
		this.assessment_type = assessment_type;
		this.assess_stars = assess_stars;
	}



	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	

	public String getProduct_id() {
		return product_id;
	}



	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}



	public String getInsBranch_id() {
		return insBranch_id;
	}



	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}



	public String getAssessment_type() {
		return assessment_type;
	}

	public void setAssessment_type(String assessment_type) {
		this.assessment_type = assessment_type;
	}

	

	public Integer getAssess_stars() {
		return assess_stars;
	}



	public void setAssess_stars(Integer assess_stars) {
		this.assess_stars = assess_stars;
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
