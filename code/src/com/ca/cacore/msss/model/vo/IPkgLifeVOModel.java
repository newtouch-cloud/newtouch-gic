package com.ca.cacore.msss.model.vo;

import java.sql.Date;
import java.util.List;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IPkgLifeVOModel extends IPageCount {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getPkg_id();

	public void setPkg_id(String pkg_id);

	public String getPkg_name();

	public void setPkg_name(String pkg_name);

	public String getPkg_abbr();

	public void setPkg_abbr(String pkg_abbr);

	public String getPkg_enName();

	public void setPkg_enName(String pkg_enName);

	public String getPkg_enAbbr();

	public void setPkg_enAbbr(String pkg_enAbbr);

	public Date getStart_date();

	public void setStart_date(Date start_date);

	public Date getEnd_date();

	public void setEnd_date(Date end_date);

	public String getStatus();

	public void setStatus(String status);

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

	public String[] getProduct_id();

	public void setProduct_id(String[] product_id);

	public List<IProductLlifeVOModel> getProductList();

	public void setProductList(List<IProductLlifeVOModel> productList);
	
	public String getStatus_name();
	
	public void setStatus_name(String status_name);
	
	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);
	
	public String[] getInsBranch_id() ;
	
	public void setInsBranch_id(String[] insBranch_id) ;

}