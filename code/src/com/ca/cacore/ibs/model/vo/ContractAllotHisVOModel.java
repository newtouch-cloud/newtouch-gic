package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2013年12月24日   
* @author    WanBo
* @description:  封装以保单信息表为主的保单相关信息
* table: 寿险保单信息CBS_ContractLife,保单服务人员变更历史CBS_Contract_Service_Change,
*      寿险保单投保人信息CBS_ContractLife_Holder,寿险保单被保险人信息CBS_ContractLife_Insured,
*      寿险保单产品信息CBS_ContractLife_Product,寿险产品信息PDT_Product_Llife,投保单状态数据字典CBS_Policy_Status
*      机构基本信息SYS_Branch,保险公司机构基本信息SYS_InsBranch,销售人员基本信息SMIS_Sales,渠道基本法职级信息SMIS_Rank
*/
public class ContractAllotHisVOModel implements IContractAllotHisVOModel{
	private Integer seq_id; // 序号
	private String policy_id;//保单id Ref：CBS_Policy_Life
	private String branch_id;//销售机构  Ref：SYS_Branch
	private String branch_name;//销售机构名称   Ref：SYS_Branch
	private String insBranch_id;//保险公司机构  Ref：SYS_InsBranch
	private String insBranch_name;//保险公司机构名称  
	private String send_code; // 投保单号码 
	private String policy_code; // 保单号
	private String holder_id;//投保人代码    Ref：CBS_Customer
	private String holder_name;//投保人姓名
	private String insurant_id;//第一被保人代码 
	private String insurant_name;//第一被保人
	private String send_status; // 投保单状态 
	private Date hold_date; //投保日期 
	private String agent_id;//保单销售人员代码  Ref：SMIS_Sales
	private String agent_name;//保单销售人员姓名
	private String service_id;//保单服务人员代码
	private String policy_status; // 保单状态 
	private String policy_status_name;//保单状态名称  Ref：CBS_Policy_Status
	private String bef_service_id;//分配前保单服务人员代码
	private String bef_service_name;//分配前保单服务人员姓名  
	private String bef_policy_code;//分配前所属保单
	private String bef_holder_name;//分配前投保人
	private String aft_service_id;//分配后保单服务人员代码
	private String aft_service_name;//分配后保单服务人员姓名
	private String aft_service_status;//分配后保单服务人员状态
	private Date validate_date;//保单生效日期
	private Date allot_date;//保单分配时间
	private String service_post_code;//分配人员职位代码   Ref：SMIS_Rank
	
	private String product_id;//产品代码 (险种代码)
	private String product_name;//产品名称（险种名称）
	private Integer amount;//保额
	private Integer period_prem;//保费
	private String[]  policy_ids;//保单号id数组
	private String remark;//备注
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后修改时间
 	
	private PageCount pageCount = new PageCount();
	
	public ContractAllotHisVOModel(){}
	public ContractAllotHisVOModel(Integer seq_id){
		this.seq_id=seq_id;
	}
	
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	
	public String getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(String policy_id) {
		this.policy_id = policy_id;
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
	public String getSend_code() {
		return send_code;
	}
	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	public String getPolicy_code() {
		return policy_code;
	}
	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}
	public String getHolder_id() {
		return holder_id;
	}
	public void setHolder_id(String holder_id) {
		this.holder_id = holder_id;
	}
	public String getHolder_name() {
		return holder_name;
	}
	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}
	public String getInsurant_id() {
		return insurant_id;
	}
	public void setInsurant_id(String insurant_id) {
		this.insurant_id = insurant_id;
	}
	public String getInsurant_name() {
		return insurant_name;
	}
	public void setInsurant_name(String insurant_name) {
		this.insurant_name = insurant_name;
	}
	
	public String getSend_status() {
		return send_status;
	}
	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}
	public String getPolicy_status() {
		return policy_status;
	}
	public void setPolicy_status(String policy_status) {
		this.policy_status = policy_status;
	}
	
	public String getPolicy_status_name() {
		return policy_status_name;
	}
	public void setPolicy_status_name(String policy_status_name) {
		this.policy_status_name = policy_status_name;
	}
	public Date getHold_date() {
		return hold_date;
	}
	public void setHold_date(Date hold_date) {
		this.hold_date = hold_date;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	
	public String getBef_service_id() {
		return bef_service_id;
	}
	public void setBef_service_id(String bef_service_id) {
		this.bef_service_id = bef_service_id;
	}
	
	public String getAft_service_id() {
		return aft_service_id;
	}
	public void setAft_service_id(String aft_service_id) {
		this.aft_service_id = aft_service_id;
	}
	
	public String getBef_service_name() {
		return bef_service_name;
	}
	public void setBef_service_name(String bef_service_name) {
		this.bef_service_name = bef_service_name;
	}
	public String getAft_service_name() {
		return aft_service_name;
	}
	public void setAft_service_name(String aft_service_name) {
		this.aft_service_name = aft_service_name;
	}
	
	public String getAft_service_status() {
		return aft_service_status;
	}
	public void setAft_service_status(String aft_service_status) {
		this.aft_service_status = aft_service_status;
	}
	public Date getValidate_date() {
		return validate_date;
	}
	public void setValidate_date(Date validate_date) {
		this.validate_date = validate_date;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Date getAllot_date() {
		return allot_date;
	}
	public void setAllot_date(Date allot_date) {
		this.allot_date = allot_date;
	}
	
	public String getService_post_code() {
		return service_post_code;
	}
	public void setService_post_code(String service_post_code) {
		this.service_post_code = service_post_code;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getPeriod_prem() {
		return period_prem;
	}
	public void setPeriod_prem(Integer period_prem) {
		this.period_prem = period_prem;
	}
	
	public String getBef_policy_code() {
		return bef_policy_code;
	}
	public void setBef_policy_code(String bef_policy_code) {
		this.bef_policy_code = bef_policy_code;
	}
	public String getBef_holder_name() {
		return bef_holder_name;
	}
	public void setBef_holder_name(String bef_holder_name) {
		this.bef_holder_name = bef_holder_name;
	}
	
	public String[] getPolicy_ids() {
		return policy_ids;
	}
	public void setPolicy_ids(String[] policy_ids) {
		this.policy_ids = policy_ids;
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
	public int getStart() {
		return this.getPageCount().getStart();
	}
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	
	
	

	
	



}
