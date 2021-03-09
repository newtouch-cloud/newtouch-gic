package com.ca.cacore.msss.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IProductEvaluationModel extends IPageCount{

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getProduct_id();

	public void setProduct_id(String product_id);

	public String getDescription();

	public void setDescription(String description);

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
	
	public String getInsBranch_id();
	
	public void setInsBranch_id(String insBranch_id) ;


}