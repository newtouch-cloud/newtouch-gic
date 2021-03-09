package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class ConservationItemModel implements IConservationItemModel {
	
	private Integer seq_id;				//序号
	private Long conservationItem_Code;	//保全项目代码
	private String conservationItem_Name;//保全项目名称
	private Integer orderNum;			//排序号
	private String  Remark;				//备注
	private String createUser;			//创建人
	private Date createDate;			//创建时间
	private String modifyUser;			//修改人
	private Date modifyDate;			//修改时间
	
	private PageCount pageCount;
	
	
	public PageCount getPageCount() {
		return pageCount;
	}

	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	public Long getConservationItem_Code() {
		return conservationItem_Code;
	}

	public void setConservationItem_Code(Long conservationItem_Code) {
		this.conservationItem_Code = conservationItem_Code;
	}

	public String getConservationItem_Name() {
		return conservationItem_Name;
	}

	public void setConservationItem_Name(String conservationItem_Name) {
		this.conservationItem_Name = conservationItem_Name;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
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
	

	public int getStart() {
		return this.getPageCount().getStart();
	}

	public int getLimit() {
		return this.getPageCount().getLimit();
	}
}
