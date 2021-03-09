package com.ca.cacore.manage.model.bo;

import java.sql.Date;

import org.springframework.stereotype.Repository;

import com.newtouch.core.model.IPageCount;

/**
 *  功能菜单基本信息接口类
 * 
 * @author ma_cj
 * 
 */
@Repository
public interface IFuncMenuModel extends IPageCount {

	public Integer getSeq_id() ;

	public void setSeq_id(Integer seq_id) ;

	public String getMenu_id();

	public void setMenu_id(String menu_id) ;

	public String getMenu_name() ;
	

	public void setMenu_name(String menu_name) ;
	
	public String getMenu_parentid();
	
	public void setMenu_parentid(String menu_parentid);
	
	
	public String getMenu_type();

	public void setMenu_type(String menu_type);

	public String getMenu_uritype() ;

	public void setMenu_uritype(String menu_uritype);

	public String getMenu_uri();

	public void setMenu_uri(String menu_uri);

	public String getMenu_opentype();

	public void setMenu_opentype(String menu_opentype);

	public String getMenu_dispath();

	public void setMenu_dispath(String menu_dispath);

	public Integer getMenu_disorder();

	public void setMenu_disorder(Integer menu_disorder);

	public String getRemark();

	public void setRemark(String remark);

	public String getCreateUser();

	public void setCreateUser(String createUser);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getModifyUser();

	public Date getModifyDate();

	public void setModifyDate(Date modifyDate);

	public String getStatus();

	public void setStatus(String status);

	public void setModifyUser(String modifyUser);
	
	public String getMenu_allPath();

	public void setMenu_allPath(String menu_allPath);
	
	public String getButton_assign();

	public void setButton_assign(String button_assign);
	
}
