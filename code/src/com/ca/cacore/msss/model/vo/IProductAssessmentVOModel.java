package com.ca.cacore.msss.model.vo;

import java.sql.Date;

/**
 * Created by yanfb on 13-12-26.
 */
public interface IProductAssessmentVOModel {

    public Integer getSeq_id();

    public void setSeq_id(Integer seq_id);

    public String getProduct_id() ;

	public void setProduct_id(String product_id) ;
	public String getInsBranch_id() ;

	public void setInsBranch_id(String insBranch_id) ;


    public String getAssessment_type_code();

    public void setAssessment_type_code(String assessment_type_code);

    public Integer getAssess_stars();

    public void setAssess_stars(Integer assess_stars);

    public String getRemark();

    public void setRemark(String remark);

    public String getCreateUser();

    public void setCreateUser(String createUser);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public String getModifyUser();

    public void setModifyUser(String modifyUser);

    public Date getModifyDate();

    public void setModifyDate(Date modifyDate);

    public String getAssess_type_name();

    public void setAssess_type_name(String assess_type_name);
}
