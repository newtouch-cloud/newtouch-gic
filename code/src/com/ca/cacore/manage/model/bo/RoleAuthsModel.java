package com.ca.cacore.manage.model.bo;

import java.sql.Date;

/**
 * 角色权限信息
 *
 */
public class RoleAuthsModel implements IRoleAuthsModel {
	private Integer seq_id		;        //主键	
	private String 	role_id	;        //角色代码
	private String menu_id		;        //菜单代码	
	private String button_id		;        //按钮代码	
	private String status		;        //状态	
	private String remark		;        //备注		             
	private String createUser	;	     //创建人	             
	private Date createDate	;        	//创建时间	             
	private String modifyUser	;        	//最后修改人	             
	private Date modifyDate	;         //最后修改时间	 
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getSeq_id()
	 */
	@Override
	public Integer getSeq_id() {
		return seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setSeq_id(java.lang.Integer)
	 */
	@Override
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getRole_id()
	 */
	@Override
	public String getRole_id() {
		return role_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setRole_id(java.lang.String)
	 */
	@Override
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getMenu_id()
	 */
	@Override
	public String getMenu_id() {
		return menu_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setMenu_id(java.lang.String)
	 */
	@Override
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getButton_id()
	 */
	@Override
	public String getButton_id() {
		return button_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setButton_id(java.lang.String)
	 */
	@Override
	public void setButton_id(String button_id) {
		this.button_id = button_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getStatus()
	 */
	@Override
	public String getStatus() {
		return status;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setStatus(java.lang.String)
	 */
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getRemark()
	 */
	@Override
	public String getRemark() {
		return remark;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setRemark(java.lang.String)
	 */
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getCreateUser()
	 */
	@Override
	public String getCreateUser() {
		return createUser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setCreateUser(java.lang.String)
	 */
	@Override
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getCreateDate()
	 */
	@Override
	public Date getCreateDate() {
		return createDate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setCreateDate(java.sql.Date)
	 */
	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getModifyUser()
	 */
	@Override
	public String getModifyUser() {
		return modifyUser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setModifyUser(java.lang.String)
	 */
	@Override
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#getModifyDate()
	 */
	@Override
	public Date getModifyDate() {
		return modifyDate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.manage.model.impl.IRoleAuthsModel#setModifyDate(java.sql.Date)
	 */
	@Override
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
