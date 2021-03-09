package com.ca.cacore.manage.model.bo;

import java.sql.Date;

/**
 * 菜单访问类型信息
 * 
 * @author admin
 * 
 */
public class FuncMenuURITypeModel {
	private Integer seq_id; // 主键
	private String uritype_code; // 访问类型代码
	private String uritype_name; // 访问类型名称
	private String remark; // 备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
	public FuncMenuURITypeModel(Integer seq_id) {
		this.setSeq_id(seq_id);
	}
	public FuncMenuURITypeModel(){}
	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	public String getUritype_code() {
		return uritype_code;
	}

	public void setUritype_code(String uritype_code) {
		this.uritype_code = uritype_code;
	}

	public String getUritype_name() {
		return uritype_name;
	}

	public void setUritype_name(String uritype_name) {
		this.uritype_name = uritype_name;
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

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
