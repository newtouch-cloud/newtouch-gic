package com.ca.cacore.msss.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2013年12月25日   
* @author    NewTouch_King
* @description: PDT_Product_Assessment  寿险产品、产品组合评估信息接口
*/
public interface IProductAssessmentModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getProduct_id() ;



	public void setProduct_id(String product_id) ;



	public String getInsBranch_id() ;



	public void setInsBranch_id(String insBranch_id) ;
	public String getAssessment_type();

	public void setAssessment_type(String assessment_type);

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

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

	public int getStart();

	public int getLimit();

}