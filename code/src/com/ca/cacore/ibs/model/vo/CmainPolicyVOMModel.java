package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

/**
 * 
* @since:    2014年11月21日   
* @author    SUNXM
* @description:cbs_smc_cmain
 */
public class CmainPolicyVOMModel implements ICmainPolicyVOModel {
	private Integer seq_id;             
	private String policyno;//                  保单号       
	private String riskcode;    //              险种         
	private String riskname;	//                  险种名称  
	
	//险种信息
	private String classcode;//险类代码
	private String classname;//险类名称
	private String rtype;//保监会分类
	private String product_source;//产品来源
	
	private String ccurno;	//                    币种         
	private Double netpremium;	//                保费         
	private Date signdate;	//                 签单日期     
	private Date operatedate;	//               核保日期     
	private Date startdate;  //               起保日期     
	private Date enddate ;	//                  终保日期     
	private Date dcoldte ;    //              实收付日期   
	private Double sumamount;	//                 保额         
	private String paymode;	//                   缴费方式     
	private String stype;	//                     业务分类     
	private Double frate;   //                  手续费比例   
	private Double fnum ;	//                     手续费金额   
	private String applicode; //                 投保人代码   
	private String appliname;	//                 投保人名称   
	private String insuredcode;	//               被保险人代码 
	private String insuredname;	//               被保险人名称 
	private String handlercode;	//               销售员代码(销售公司)  
	private String handlername;	//               销售员名称(销售公司)  
	private String team_no;	//                   团队名称     
	private String car_no;	//                    车牌号	       
	private Date modifydate;	//                写入时间     
	private Date is_pay_date;	//                            
	private String renewalflag;	//         	续保标志*                   
	private String businesscode;	//              业务类型代码 
	private String commission_poundage_scale;   //   佣金占手续费比例
	
	//客户信息
	private String customer_id;//客户编号*
	private String name;//客户名称*
	private String customer_type;//客户类型
	private String certi_type;//客户证件类型*
	private String certi_no;//客户证件号码*
	private String gender;//客户性别
	private Date birthday;//客户出生日期
	private String company_telphone;//客户办公电话号
	private String modifyuser;//修改人
	
	//客户联系人信息
	private String branch_id;                      //机构代码               
	private String address;                        //家庭地址               
	private String mobile;                         //移动电话               
	private String telphone;                       //住宅电话               
	private String status;                         //状态
	private String remark;                         //备注                   
	private String createuser;                     //创建人                 
	
	//顾客备份表
    private Integer log_seq_id;//	备份数据主键;
    private String  log_bustype;//	备份业务类型;
    private Date    log_busdate;//	备份业务归属时间;
    private Date    log_date;//	备份时间;
    private String  log_remark;//	备份备注;
    private String  flag;//状态标志位
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Integer getSeq_id() {
		return seq_id;
	}
	/** 
	* 
	* @param seq_id 
	* @description:
	*/
	@Override
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getPolicyno() {
		return policyno;
	}
	/** 
	* 
	* @param policyno 
	* @description:
	*/
	@Override
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getRiskcode() {
		return riskcode;
	}
	/** 
	* 
	* @param riskcode 
	* @description:
	*/
	@Override
	public void setRiskcode(String riskcode) {
		this.riskcode = riskcode;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getRiskname() {
		return riskname;
	}
	/** 
	* 
	* @param riskname 
	* @description:
	*/
	@Override
	public void setRiskname(String riskname) {
		this.riskname = riskname;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getClasscode() {
		return classcode;
	}
	/** 
	* 
	* @param classcode 
	* @description:
	*/
	@Override
	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getClassname() {
		return classname;
	}
	/** 
	* 
	* @param classname 
	* @description:
	*/
	@Override
	public void setClassname(String classname) {
		this.classname = classname;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getRtype() {
		return rtype;
	}
	/** 
	* 
	* @param rtype 
	* @description:
	*/
	@Override
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	@Override
	public String getProduct_source() {
		return product_source;
	}
	/** 
	 * 
	 * @param rtype 
	 * @description:
	 */
	@Override
	public void setProduct_source(String product_source) {
		this.product_source = product_source;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCcurno() {
		return ccurno;
	}
	/** 
	* 
	* @param ccurno 
	* @description:
	*/
	@Override
	public void setCcurno(String ccurno) {
		this.ccurno = ccurno;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Double getNetpremium() {
		return netpremium;
	}
	/** 
	* 
	* @param netpremium 
	* @description:
	*/
	@Override
	public void setNetpremium(Double netpremium) {
		this.netpremium = netpremium;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getSigndate() {
		return signdate;
	}
	/** 
	* 
	* @param signdate 
	* @description:
	*/
	@Override
	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getOperatedate() {
		return operatedate;
	}
	/** 
	* 
	* @param operatedate 
	* @description:
	*/
	@Override
	public void setOperatedate(Date operatedate) {
		this.operatedate = operatedate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getStartdate() {
		return startdate;
	}
	/** 
	* 
	* @param startdate 
	* @description:
	*/
	@Override
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getEnddate() {
		return enddate;
	}
	/** 
	* 
	* @param enddate 
	* @description:
	*/
	@Override
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getDcoldte() {
		return dcoldte;
	}
	/** 
	* 
	* @param dcoldte 
	* @description:
	*/
	@Override
	public void setDcoldte(Date dcoldte) {
		this.dcoldte = dcoldte;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Double getSumamount() {
		return sumamount;
	}
	/** 
	* 
	* @param sumamount 
	* @description:
	*/
	@Override
	public void setSumamount(Double sumamount) {
		this.sumamount = sumamount;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getPaymode() {
		return paymode;
	}
	/** 
	* 
	* @param paymode 
	* @description:
	*/
	@Override
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getStype() {
		return stype;
	}
	/** 
	* 
	* @param stype 
	* @description:
	*/
	@Override
	public void setStype(String stype) {
		this.stype = stype;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Double getFrate() {
		return frate;
	}
	/** 
	* 
	* @param frate 
	* @description:
	*/
	@Override
	public void setFrate(Double frate) {
		this.frate = frate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Double getFnum() {
		return fnum;
	}
	/** 
	* 
	* @param fnum 
	* @description:
	*/
	@Override
	public void setFnum(Double fnum) {
		this.fnum = fnum;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getApplicode() {
		return applicode;
	}
	/** 
	* 
	* @param applicode 
	* @description:
	*/
	@Override
	public void setApplicode(String applicode) {
		this.applicode = applicode;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getAppliname() {
		return appliname;
	}
	/** 
	* 
	* @param appliname 
	* @description:
	*/
	@Override
	public void setAppliname(String appliname) {
		this.appliname = appliname;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getInsuredcode() {
		return insuredcode;
	}
	/** 
	* 
	* @param insuredcode 
	* @description:
	*/
	@Override
	public void setInsuredcode(String insuredcode) {
		this.insuredcode = insuredcode;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getInsuredname() {
		return insuredname;
	}
	/** 
	* 
	* @param insuredname 
	* @description:
	*/
	@Override
	public void setInsuredname(String insuredname) {
		this.insuredname = insuredname;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getHandlercode() {
		return handlercode;
	}
	/** 
	* 
	* @param handlercode 
	* @description:
	*/
	@Override
	public void setHandlercode(String handlercode) {
		this.handlercode = handlercode;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getHandlername() {
		return handlername;
	}
	/** 
	* 
	* @param handlername 
	* @description:
	*/
	@Override
	public void setHandlername(String handlername) {
		this.handlername = handlername;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getTeam_no() {
		return team_no;
	}
	/** 
	* 
	* @param team_no 
	* @description:
	*/
	@Override
	public void setTeam_no(String team_no) {
		this.team_no = team_no;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCar_no() {
		return car_no;
	}
	/** 
	* 
	* @param car_no 
	* @description:
	*/
	@Override
	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getModifydate() {
		return modifydate;
	}
	/** 
	* 
	* @param modifydate 
	* @description:
	*/
	@Override
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getIs_pay_date() {
		return is_pay_date;
	}
	/** 
	* 
	* @param is_pay_date 
	* @description:
	*/
	@Override
	public void setIs_pay_date(Date is_pay_date) {
		this.is_pay_date = is_pay_date;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getRenewalflag() {
		return renewalflag;
	}
	/** 
	* 
	* @param renewalflag 
	* @description:
	*/
	@Override
	public void setRenewalflag(String renewalflag) {
		this.renewalflag = renewalflag;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getBusinesscode() {
		return businesscode;
	}
	/** 
	* 
	* @param businesscode 
	* @description:
	*/
	@Override
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCommission_poundage_scale() {
		return commission_poundage_scale;
	}
	/** 
	* 
	* @param commission_poundage_scale 
	* @description:
	*/
	@Override
	public void setCommission_poundage_scale(String commission_poundage_scale) {
		this.commission_poundage_scale = commission_poundage_scale;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCustomer_id() {
		return customer_id;
	}
	/** 
	* 
	* @param customer_id 
	* @description:
	*/
	@Override
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getName() {
		return name;
	}
	/** 
	* 
	* @param name 
	* @description:
	*/
	@Override
	public void setName(String name) {
		this.name = name;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCustomer_type() {
		return customer_type;
	}
	/** 
	* 
	* @param customer_type 
	* @description:
	*/
	@Override
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCerti_type() {
		return certi_type;
	}
	/** 
	* 
	* @param certi_type 
	* @description:
	*/
	@Override
	public void setCerti_type(String certi_type) {
		this.certi_type = certi_type;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCerti_no() {
		return certi_no;
	}
	/** 
	* 
	* @param certi_no 
	* @description:
	*/
	@Override
	public void setCerti_no(String certi_no) {
		this.certi_no = certi_no;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getGender() {
		return gender;
	}
	/** 
	* 
	* @param gender 
	* @description:
	*/
	@Override
	public void setGender(String gender) {
		this.gender = gender;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getBirthday() {
		return birthday;
	}
	/** 
	* 
	* @param birthday 
	* @description:
	*/
	@Override
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCompany_telphone() {
		return company_telphone;
	}
	/** 
	* 
	* @param company_telphone 
	* @description:
	*/
	@Override
	public void setCompany_telphone(String company_telphone) {
		this.company_telphone = company_telphone;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getModifyuser() {
		return modifyuser;
	}
	/** 
	* 
	* @param modifyuser 
	* @description:
	*/
	@Override
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getBranch_id() {
		return branch_id;
	}
	/** 
	* 
	* @param branch_id 
	* @description:
	*/
	@Override
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getAddress() {
		return address;
	}
	/** 
	* 
	* @param address 
	* @description:
	*/
	@Override
	public void setAddress(String address) {
		this.address = address;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getMobile() {
		return mobile;
	}
	/** 
	* 
	* @param mobile 
	* @description:
	*/
	@Override
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getTelphone() {
		return telphone;
	}
	/** 
	* 
	* @param telphone 
	* @description:
	*/
	@Override
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getStatus() {
		return status;
	}
	/** 
	* 
	* @param status 
	* @description:
	*/
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getRemark() {
		return remark;
	}
	/** 
	* 
	* @param remark 
	* @description:
	*/
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getCreateuser() {
		return createuser;
	}
	/** 
	* 
	* @param createuser 
	* @description:
	*/
	@Override
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Integer getLog_seq_id() {
		return log_seq_id;
	}
	/** 
	* 
	* @param log_seq_id 
	* @description:
	*/
	@Override
	public void setLog_seq_id(Integer log_seq_id) {
		this.log_seq_id = log_seq_id;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getLog_bustype() {
		return log_bustype;
	}
	/** 
	* 
	* @param log_bustype 
	* @description:
	*/
	@Override
	public void setLog_bustype(String log_bustype) {
		this.log_bustype = log_bustype;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getLog_busdate() {
		return log_busdate;
	}
	/** 
	* 
	* @param log_busdate 
	* @description:
	*/
	@Override
	public void setLog_busdate(Date log_busdate) {
		this.log_busdate = log_busdate;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public Date getLog_date() {
		return log_date;
	}
	/** 
	* 
	* @param log_date 
	* @description:
	*/
	@Override
	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getLog_remark() {
		return log_remark;
	}
	/** 
	* 
	* @param log_remark 
	* @description:
	*/
	@Override
	public void setLog_remark(String log_remark) {
		this.log_remark = log_remark;
	}
	/** 
	* 
	* @return 
	* @description:
	*/
	@Override
	public String getFlag() {
		return flag;
	}
	/** 
	* 
	* @param flag 
	* @description:
	*/
	@Override
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
