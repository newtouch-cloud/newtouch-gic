package com.ca.cacore.manage.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
 * 功能菜单基本信息
 * 
 * @author ma_cj
 * 
 */
public class FuncMenuModel implements IFuncMenuModel{
	
	private Integer seq_id; // 主键
	private String menu_id;// 菜单代码
	private String menu_name;// 菜单名称
	private String menu_parentid;// 上级菜单代码
	private String menu_allPath;//菜单完整路径使用-符号分割'
	private String menu_type;// 菜单类型
	private String menu_uritype;// 菜单访问类型
	private String menu_uri;// 菜单访问路径
	private String menu_opentype; // 菜单打开方式
	private String button_assign; //是否可分配按钮Y 可分配N 不可分配'
	private String menu_dispath; // 菜单显示顺序
	private Integer menu_disorder; // 菜单显示顺序
	private String status; // 状态
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private PageCount pageCount= new PageCount();
	
	
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pagination) {
		this.pageCount = pagination;
	}
	public int getStart() {
		return this.getPageCount().getStart();
	}
	public int getLimit() {
		return this.getPageCount().getLimit();
	}	
	public FuncMenuModel(Integer seq_id){
		this.seq_id=seq_id;
	}
	public FuncMenuModel(){
	}
	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_type() {
		return menu_type;
	}

	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}

	public String getMenu_uritype() {
		return menu_uritype;
	}

	public void setMenu_uritype(String menu_uritype) {
		this.menu_uritype = menu_uritype;
	}

	public String getMenu_uri() {
		return menu_uri;
	}

	public void setMenu_uri(String menu_uri) {
		this.menu_uri = menu_uri;
	}

	public String getMenu_opentype() {
		return menu_opentype;
	}

	public void setMenu_opentype(String menu_opentype) {
		this.menu_opentype = menu_opentype;
	}

	public String getMenu_dispath() {
		return menu_dispath;
	}

	public void setMenu_dispath(String menu_dispath) {
		this.menu_dispath = menu_dispath;
	}

	public Integer getMenu_disorder() {
		return this.menu_disorder;
	}

	public void setMenu_disorder(Integer menu_disorder) {
		this.menu_disorder = menu_disorder;
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

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getMenu_parentid() {
		return menu_parentid;
	}
	public void setMenu_parentid(String menu_parentid) {
		this.menu_parentid = menu_parentid;
	}
	public String getMenu_allPath() {
		return menu_allPath;
	}
	public void setMenu_allPath(String menu_allPath) {
		this.menu_allPath = menu_allPath;
	}
	public String getButton_assign() {
		return button_assign;
	}
	public void setButton_assign(String button_assign) {
		this.button_assign = button_assign;
	}
	

}
