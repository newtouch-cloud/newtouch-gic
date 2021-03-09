package com.ca.cacore.ibs.model.bo;

import java.util.Date;



/**
 * 2013-12-11 15:30
 * 影像基本信息Model  对应表 IMG_Image
 * @author ZhangChen
 *
 */

public class PolicyImageModel implements IPolicyImageModel {
	private Integer seq_id; 	// 序号
	private String file_id; 	//影像件编号
	private String bus_type; 	// 影像业务类型	
	private String send_code; 	// 投保单号码		
	private Long policy_id; 	// 保单号
	
	private String insBranch_id; // 保险公司机构 界面显示
	private String policy_code;  // 保单号码 用户输入的所属保单号
	private String app_name;    // 投保人姓名 用于界面显示
	private String branch_id;   //机构id用于界面显示
	private String branch_name; //机构名称用于界面显示
	private String [] file_ids; //用于保单id管理影像时保持影像编号数组
	private String [] delete_ids;//用于删除影像
	
	private Integer seq_num;	// 页码
	private Date scan_time;		// 扫描时间
	private String file_path;	// 文件路径
	private String file_name;	// 文件名
	private String remark;		//备注
	private String createUser; 	// 创建人
	private Date createDate; 	// 创建时间
	private String modifyUser; 	// 最后修改人
	private Date modifyDate; 	// 最后时间
	
	
	
	
	public PolicyImageModel() {

	}
	public PolicyImageModel(String file_id, String bus_type,
			String send_code, Long policy_id, Integer seq_num, 
			String file_path, String file_name, String remark,
			String createUser, String modifyUser
			) {
		this.file_id = file_id;
		this.bus_type = bus_type;
		this.send_code = send_code;
		this.policy_id = policy_id;
		this.seq_num = seq_num;
		this.file_path = file_path;
		this.file_name = file_name;
		this.remark = remark;
		this.createUser = createUser;
		this.modifyUser = modifyUser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getSeq_id()
	 */
	@Override
	public Integer getSeq_id() {
		return seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setSeq_id(java.lang.Integer)
	 */
	@Override
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getFile_id()
	 */
	@Override
	public String getFile_id() {
		return file_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setFile_id(java.lang.String)
	 */
	@Override
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getBus_type()
	 */
	@Override
	public String getBus_type() {
		return bus_type;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setBus_type(java.lang.String)
	 */
	@Override
	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getSend_id()
	 */
	@Override
	public String getSend_code() {
		return send_code;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setSend_id(java.lang.String)
	 */
	@Override
	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getPolicy_id()
	 */
	@Override
	public Long getPolicy_id() {
		return policy_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setPolicy_id(java.lang.String)
	 */
	@Override
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getSeq_num()
	 */
	@Override
	public Integer getSeq_num() {
		return seq_num;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setSeq_num(java.lang.Integer)
	 */
	@Override
	public void setSeq_num(Integer seq_num) {
		this.seq_num = seq_num;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getScan_time()
	 */
	@Override
	public Date getScan_time() {
		return scan_time;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setScan_time(java.util.Date)
	 */
	@Override
	public void setScan_time(Date scan_time) {
		this.scan_time = scan_time;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getFile_path()
	 */
	@Override
	public String getFile_path() {
		return file_path;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setFile_path(java.lang.String)
	 */
	@Override
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getFile_name()
	 */
	@Override
	public String getFile_name() {
		return file_name;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setFile_name(java.lang.String)
	 */
	@Override
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getRemark()
	 */
	@Override
	public String getRemark() {
		return remark;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setRemark(java.lang.String)
	 */
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getCreateUser()
	 */
	@Override
	public String getCreateUser() {
		return createUser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setCreateUser(java.lang.String)
	 */
	@Override
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getCreateDate()
	 */
	@Override
	public Date getCreateDate() {
		return createDate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setCreateDate(java.util.Date)
	 */
	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getModifyUser()
	 */
	@Override
	public String getModifyUser() {
		return modifyUser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setModifyUser(java.lang.String)
	 */
	@Override
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#getModifyDate()
	 */
	@Override
	public Date getModifyDate() {
		return modifyDate;
	}
	/* (non-Javadoc)
	 * @see com.ca.cacore.cbs.model.bo.IPolicyImageModel#setModifyDate(java.util.Date)
	 */
	@Override
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getInsBranch_id() {
		return insBranch_id;
	}

	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}

	public String getPolicy_code() {
		return policy_code;
	}

	public void setPolicy_code(String policy_code) {
		this.policy_code = policy_code;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
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
	public String[] getFile_ids() {
		return file_ids;
	}
	public void setFile_ids(String[] file_ids) {
		this.file_ids = file_ids;
	}
	
	
}
