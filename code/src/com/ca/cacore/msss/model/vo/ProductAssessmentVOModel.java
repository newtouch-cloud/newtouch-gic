package com.ca.cacore.msss.model.vo;

import java.sql.Date;

/**
 * @author wang_ds
 * @since: 2013年12月25日
 * @description: PDT_Product_Assessment  寿险产品、产品组合评估信息
 */
public class ProductAssessmentVOModel implements IProductAssessmentVOModel{

    private Integer seq_id; // 主键
    private String product_id; // 险种代码
    private String insBranch_id; // 保险公司
    private String assessment_type_code; // 评估类型
    private Integer assess_stars; // 评估星级
    private String remark; // 备注
    private String createUser; // 创建人
    private Date createDate; // 创建时间
    private String modifyUser; // 最后修改人
    private Date modifyDate; // 最后修改时间
    private String assess_type_name; //  评估类型代码名称

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

	public String getAssessment_type_code() {
        return assessment_type_code;
    }

    public void setAssessment_type_code(String assessment_type_code) {
        this.assessment_type_code = assessment_type_code;
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

    public String getAssess_type_name() {
        return assess_type_name;
    }

    public void setAssess_type_name(String assess_type_name) {
        this.assess_type_name = assess_type_name;
    }
}
