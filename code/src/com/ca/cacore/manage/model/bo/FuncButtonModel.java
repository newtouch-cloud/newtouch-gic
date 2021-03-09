package com.ca.cacore.manage.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
 * 功能按钮基本信息
 */
public class FuncButtonModel implements IFuncButtonModel{
	private Integer seq_id; // 主键
	private String menu_id;// 菜单代码
	private String button_id;// 按钮代码
	private String button_name;// 按钮名称
	private String status; // 状态
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	private String menu_name; // 最后修改时间
	private PageCount pageCount = new PageCount();
	
	public FuncButtonModel(){}
	
	public FuncButtonModel(Integer seq_id){
		this.setSeq_id(seq_id);
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

	public String getButton_id() {
		return button_id;
	}

	public void setButton_id(String button_id) {
		this.button_id = button_id;
	}

	public String getButton_name() {
		return button_name;
	}

	public void setButton_name(String button_name) {
		this.button_name = button_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		return sdf.format(createDate);
		return createDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDate() {
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		return sdf.format(modifyDate);
		return modifyDate;
	}

	
	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	@Override 
	public PageCount getPageCount() {
		return pageCount;
	}

	@Override
	public void setPageCount(PageCount pageCount) {
		this.pageCount=pageCount;
	}

	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}

	@Override
	public String getButtonId() {
		return button_id;
	}

	@Override
	public void setButtonId(String button_id) {
		this.button_id=button_id;
	}

	@Override
	public String getButtonName() {
		return button_name;
	}

	@Override
	public void setButtonName(String button_name) {
		this.button_name=button_name;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate=createDate;
	}

	@Override
	public void setModifyDate(Date modifyDate) {
		this.modifyDate=modifyDate;
	}

}
