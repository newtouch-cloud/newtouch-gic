package com.ca.cacore.manage.model.bo;

import java.sql.Date;
import com.newtouch.core.model.IPageCount;

/**
 * 按钮接口类
 * @author guochunhua
 *
 */
public interface IFuncButtonModel extends IPageCount{
	public Integer getSeq_id();
	public void setSeq_id(Integer seq_id);
	public String getMenu_id();
	public void setMenu_id(String menu_id);
	public String getButtonId();
	public void setButtonId(String button_id);
	public String getButtonName();
	public void setButtonName(String button_name);
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
	public String getMenu_name();
	public void setMenu_name(String menu_name);
}
