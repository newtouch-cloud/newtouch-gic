package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class PolicyLifeProblemModel implements IPolicyLifeProblemModel{ 
	private Integer seq_id;
	private String branch_id;   //机构代码
	private String branch_name; //机构名称
	private String insBranch_id;//保险公司机构id
	private String insBranch_name;//保险公司机构名称
	private String send_id;     //所属投保单号
	private String policy_code;     //所属保单号
	private Long policy_id;   //保单id
	private String send_code;   //投保单号
	private String app_name;    //投保人姓名
	private String type_code;   //问题件类型id
	private String type_name;   //问题件类型名称
	private String origin_type; //问题件业务来源id
	private String origin_type_code; //问题件业务来源id
	private String origin_type_name;//问题件业务来源
	private String notes;       //问题件说明
	private String return_notes;//问题件反馈说明
	private String status;      //问题件状态id
	private String status_code; //问题件状态id
	private String status_name; //问题件状态名称
	private String remark;      //备注
	private String createUser;  //创建人
	private Date createDate;    //创建时间
	private String modifyUser;  //修改人
	private Date modifyDate;    //修改时间
	private IPolicyImageModel policyImageModel;//影像上传的model
	private String flag ;
	private PageCount pageCount=new PageCount();
	public PolicyLifeProblemModel() {}
	public String getPolicy_code() {
		return policy_code;
	}

	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}

	public PolicyLifeProblemModel(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public IPolicyImageModel getPolicyImageModel() {
		return policyImageModel;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setPolicyImageModel(IPolicyImageModel policyImageModel) {
		this.policyImageModel = policyImageModel;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
	public String getInsBranch_name() {
		return insBranch_name;
	}
	public void setInsBranch_name(String insBranch_name) {
		this.insBranch_name = insBranch_name;
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	public Long getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	public String getSend_code() {
		return send_code;
	}
	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getOrigin_type() {
		return origin_type;
	}
	public void setOrigin_type(String origin_type) {
		this.origin_type = origin_type;
	}
	public String getOrigin_type_code() {
		return origin_type_code;
	}
	public void setOrigin_type_code(String origin_type_code) {
		this.origin_type_code = origin_type_code;
	}
	public String getOrigin_type_name() {
		return origin_type_name;
	}
	public void setOrigin_type_name(String origin_type_name) {
		this.origin_type_name = origin_type_name;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getReturn_notes() {
		return return_notes;
	}
	public void setReturn_notes(String return_notes) {
		this.return_notes = return_notes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
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
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
}
