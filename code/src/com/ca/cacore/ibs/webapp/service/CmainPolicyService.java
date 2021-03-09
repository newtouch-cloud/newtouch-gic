package com.ca.cacore.ibs.webapp.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.ICmainPolicyDao;
import com.ca.cacore.ibs.dao.ICommonAsynRequestDao;
import com.ca.cacore.ibs.domain.ICmainPolicyDomain;
import com.ca.cacore.ibs.model.vo.CmainPolicyINSPersonVOMModel;
import com.ca.cacore.ibs.model.vo.CmainPolicyINSVOMModel;
import com.ca.cacore.ibs.model.vo.CmainPolicyVOMModel;
import com.ca.cacore.ibs.model.vo.ICmainPolicyVOModel;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.csm.dao.customer.ICustomerDao;
import com.ca.cacore.csm.model.bo.CustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.msss.dao.IInsRncDfnDao;
import com.ca.cacore.msss.model.bo.IInsRncDfnModel;
import com.ca.cacore.msss.model.bo.InsRncDfnModel;
import com.ca.cacore.msss.model.vo.IInsRncDfnVOModel;
import com.ca.cacore.msss.model.vo.InsRncDfnVOModel;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.excel.ExcelUtil;
import com.newtouch.utils.stringutil.StringUtil;

/**
 * 保单导入
 *
 * @author SUNXM
 * @since: 2014年11月21日
 * @description:
 */
@Service
public class CmainPolicyService implements ICmainPolicyService {
    @Autowired
    private ICmainPolicyDomain cmainPolicyDomain;
    @Autowired
    private ICmainPolicyDao cmainPolicyDao;
    @Autowired
    private ICustomerDao customerDao;
    @Autowired
    private ICommonAsynRequestDao commonAsynRequestDao;
    @Autowired
    private ICommonSeqDao seqDao;
    @Autowired
    private IInsRncDfnDao InsRncDfnDao;

    /**
     * 保单导入
     *
     * @param uploadFileName
     * @param excelPath
     * @param user
     * @return
     * @description:
     */
    @Override
    public String importInsurace(String uploadFileName, String excelPath, IUserModel user) {
        //为空校验开始
        ValidateHelper.IsNullOrEmptyThrowException(uploadFileName, "文件不可为空，请检查。");//文件为空校验
        String info = "";// 导入的回显消息
        Map<String, List<Object>> dataMap = readDataFromExcel(new File(excelPath, uploadFileName));
        //校验数据是否完全正确
        List<String> msgList = cmainPolicyDomain.checkTraPlanInfoIsTrue(dataMap);
        if (msgList.size() == 0) { //校验数据是否完全正确
            //将excel数据进行封装成保单
            List<CmainPolicyVOMModel> propList = setPropertyCmainPolicyVOMModel(dataMap);
            //解析保单入库
            for (CmainPolicyVOMModel policy : propList) {
                //1.插入保单信息
                cmainPolicyDao.deletePolicy(policy);
                cmainPolicyDao.addMainPolicy(policy);
                //2.险种处理
                //封装model
                IInsRncDfnVOModel riskcode = packageIInsRncDfnVO(policy);
//						InsRncDfnVOModel riskCodeVO=cmainPolicyDao.checkRiskCode(riskcode);
//						if(riskCodeVO !=null){
                cmainPolicyDao.deleteRisk(policy);
//						}
                //增加险种
                IInsRncDfnModel vo = converModel(riskcode);
                vo.setStatus(CodeTypeConst.PRO_STATUS_CODE1);//产品状态有效
                InsRncDfnDao.insRncDfnAdd(vo);
                //3.添加客户信息
                String branch_id = commonAsynRequestDao.getBranchID();
                policy.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//备份业务类型？
                policy.setLog_remark("导入投保单或保单的时候变更客户信息");//备份备注
                policy.setCreateuser(user.getEmp_id());
                policy.setModifyuser(user.getEmp_id());
                policy.setStatus(CodeTypeConst.CUSTOMER_STATUS_EFFECTIVE);
                policy.setBranch_id(branch_id);
                cmainPolicyDao.deleteCustomer(policy);//删除保单号对应的客户信息
                Integer seq_id = cmainPolicyDao.addCustomer(policy);//返回主键备份
                policy.setLog_seq_id(seq_id);//备份数据主键
                //添加客户联系信息
                cmainPolicyDao.addCustomerContact(policy);
                //添加客户基本信息日志
                cmainPolicyDao.addCustomerLog(policy);

            }
            info = "{\"isSuccess\": \"true\", \"msg\": \"" + "导入数据成功" + "\"}";
        } else {
            for (String s : msgList) {
                info = info + s + "\\r\\n";
            }
            info = info + "请核对有误的数据再重新导入！";
            info = "{\"isSuccess\": \"false\", \"msg\": \"" + info + "\"}";
        }

        return info;
    }


    /**
     * 保单导入
     *
     * @param
     * @param
     * @param user
     * @return
     * @throws IOException
     * @description:
     */
    @Override
    public String importInsurace2(MultipartFile file, IUserModel user) throws IOException {
        //为空校验开始
        //ValidateHelper.IsNullOrEmptyThrowException(uploadFileName, "文件不可为空，请检查。");//文件为空校验
        StringBuilder info = new StringBuilder();//导入的回显消息
        Map<String, List<Object>> dataMap = readDataFromExcel2(file);
        //校验数据是否完全正确
        Map<String, List<String>> msg = cmainPolicyDomain.checkTraPlanInfoIsTrue2(dataMap);

        List<String> msgList = msg.get("msgList");
        List<String> repeatmsg = msg.get("repeatmsg");

        for (int i = 0; i < repeatmsg.size(); i++) {
            info = info.append(repeatmsg.get(i)).append("\\r\\n");
        }

        String message = "";
        SimpleDateFormat format = new SimpleDateFormat();
        if (msgList.size() == 0) { //校验数据是否完全正确
            //将excel数据进行封装成保单
            List<CmainPolicyINSVOMModel> propList = setPropertyCmainPolicyINSVOMModel(dataMap);

            List<CmainPolicyINSVOMModel> endor_no_yes = null;
            List<CmainPolicyINSVOMModel> endor_no_no = null;

            //解析保单入库
            for (CmainPolicyINSVOMModel policy : propList) {
                //cmainPolicyDao.deleteMainINSPolicy(policy);
                endor_no_yes = new ArrayList();
                endor_no_no = new ArrayList();
                //通过导入的保单号查询数据库表中是否有该保单号 数据
                List<CmainPolicyINSVOMModel> policyINSVOMModelList = cmainPolicyDao.queryCmainPolicyINSVOMModel(policy);
                if (policyINSVOMModelList.size() > 0) {//数据库表中有该保单号数据
                    for (CmainPolicyINSVOMModel policyINSVOMModel : policyINSVOMModelList) {
                        if (policyINSVOMModel.getSettle_time() != null) {  //**导入的结算日期不能为空，只需判断数据库数据的结算日期
                            //将批单号不为空的保单数据存储进endor_no_yes集合
                            endor_no_yes.add(policyINSVOMModel);
                        } else {
                            //将批单号为空的保单数据存储进endor_no_no集合
                            endor_no_no.add(policyINSVOMModel);
                        }
                    }
                    //遍历（保单号相同）有批单号的保单数据
                    for (CmainPolicyINSVOMModel policyINSVOMModel : endor_no_yes) {
                        if (policyINSVOMModel.getEndor_no().equals(policy.getEndor_no())) {//保单号相同，批单号相同
                            //判断日期
                            if (policyINSVOMModel.getSettle_time() != null) {//查出来的保单数据结算日期不为空；导入的结算日期不能为空，无需判断
                                Date dbs_settle_date = policyINSVOMModel.getSettle_time();
                                String dbs_date = format.format(dbs_settle_date);
                                String[] dbs = dbs_date.split("-");

                                Date excel_settle_date = policy.getSettle_time();
                                String excel_date = format.format(excel_settle_date);
                                String[] excel = excel_date.split("-");

                                if ((dbs[0] + dbs[1]).equals(excel[0] + excel[1])) {//导入的和查出来的保单结算日期为同年同月，覆盖
                                    cmainPolicyDao.deleteMainINSPolicy(policy);
                                    cmainPolicyDao.addMainINSPolicy(policy);
                                } else {
                                    cmainPolicyDao.addMainINSPolicy(policy);//加入
                                }
                            } else {//查出来的保单数据结算日期为空
                                cmainPolicyDao.addMainINSPolicy(policy);//加入
                            }
                        } else {//保单号相同，批单号不同
                            cmainPolicyDao.addMainINSPolicy(policy);//加入
                        }
                    }

                    //遍历（保单号相同）无批单号的保单数据
                    for (CmainPolicyINSVOMModel policyINSVOMModel : endor_no_yes) {
                        //判断日期
                        if (policyINSVOMModel.getSettle_time() != null) {//查出来的保单数据结算日期不为空；导入的结算日期不能为空，无需判断
                            Date dbs_settle_date = policyINSVOMModel.getSettle_time();
                            String dbs_date = format.format(dbs_settle_date);
                            String[] dbs = dbs_date.split("-");

                            Date excel_settle_date = policy.getSettle_time();
                            String excel_date = format.format(excel_settle_date);
                            String[] excel = excel_date.split("-");

                            if ((dbs[0] + dbs[1]).equals(excel[0] + excel[1])) {//导入的和查出来的保单结算日期为同年同月，覆盖
                                cmainPolicyDao.deleteMainINSPolicy(policy);
                                cmainPolicyDao.addMainINSPolicy(policy);
                            } else {
                                cmainPolicyDao.addMainINSPolicy(policy);//加入
                            }
                        } else {//查出来的保单数据结算日期为空
                            cmainPolicyDao.addMainINSPolicy(policy);//加入
                        }
                    }
						
						
						/*if(policyINSVOMModel.getEndor_no()==null){//判断数据库表该保单号数据的批单号是否为空====为空
							if(policyINSVOMModel.getSettle_time()!=null){//导入的结算日期不能为空，无需判断。判断数据库表的结算日期不为空
								Date dbs_settle_date = policyINSVOMModel.getSettle_time();
								String dbs_date = format.format(dbs_settle_date);
								String[] dbs = dbs_date.split("-");
								
								Date excel_settle_date = policy.getSettle_time();
								String excel_date = format.format(excel_settle_date);
								String[] excel = excel_date.split("-");
								
								if((dbs[0]+dbs[1]).equals(excel[0]+excel[1])){//导入的和查出来的保单结算日期为同年同月，覆盖
									cmainPolicyDao.deleteMainINSPolicy(policy);
									cmainPolicyDao.addMainINSPolicy(policy);
								}else{
									cmainPolicyDao.addMainINSPolicy(policy);//加入
								}
							}else{//数据库表该保单号数据没有结算日期
								//TODO
								cmainPolicyDao.addMainINSPolicy(policy);
							}
						}else{//数据库表该保单号数据的批单号不为空
							//判断导入的批单号是否为空====不为空
							if(policy.getEndor_no()!=null&&policy.getEndor_no()!=""){
								//判断导入的批单号与数据库批单号是否相同====相同
								if(policyINSVOMModel.getEndor_no().equals(policy.getEndor_no())){
									if(policyINSVOMModel.getSettle_time()!=null){//导入的结算日期不能为空，无需判断。
										Date dbs_settle_date = policyINSVOMModel.getSettle_time();
										String dbs_date = format.format(dbs_settle_date);
										String[] dbs = dbs_date.split("-");
										
										Date excel_settle_date = policy.getSettle_time();
										String excel_date = format.format(excel_settle_date);
										String[] excel = excel_date.split("-");
										
										if((dbs[0]+dbs[1]).equals(excel[0]+excel[1])){//同年同月，覆盖
											cmainPolicyDao.deleteMainINSPolicy(policy);
											cmainPolicyDao.addMainINSPolicy(policy);
										}else{
											cmainPolicyDao.addMainINSPolicy(policy);//加入
										}
									}else{
										//TODO
										cmainPolicyDao.addMainINSPolicy(policy);
									}
								}else{//导入的和数据库表中批单号不同，加入
									cmainPolicyDao.addMainINSPolicy(policy);
								}
							}else{//数据库有批单号，导入的没有，就加入
								cmainPolicyDao.addMainINSPolicy(policy);
							}
						}
					}*/
                } else {//数据库表没有该保单号数据
                    cmainPolicyDao.addMainINSPolicy(policy);
                }
                //根据保单号、批单号查询数据，可以加入机构查询数据
                message = "导入数据成功";
            }
        } else {
            for (String s : msgList) {
                message = message + s + "\\r\\n";
            }
            message = message + "请核对有误的数据再重新导入！";
        }
        message = info.toString() + message;
        System.out.println(message);

        return message;
    }


    @Override
    public String importInsuracePerson(MultipartFile file, IUserModel user) throws IOException {
        //为空校验开始
        //ValidateHelper.IsNullOrEmptyThrowException(uploadFileName, "文件不可为空，请检查。");//文件为空校验
        String info = "";// 导入的回显消息
        Map<String, List<Object>> dataMap = readDataFromExcelPerson(file);
        //校验数据是否完全正确
        List<String> msgList = cmainPolicyDomain.checkTraPlanInfoIsTruePerson(dataMap);
        if (msgList.size() == 0) { //校验数据是否完全正确
            //将excel数据进行封装成保单
            List<CmainPolicyINSPersonVOMModel> propList = setPropertyCmainPolicyINSPersonVOMModel(dataMap);
            //解析保单入库
            String policyMessage = "";
            for (CmainPolicyINSPersonVOMModel policy : propList) {
                String channel_no = policy.getChannel_no();
                String sale_org_id = cmainPolicyDao.findByChannel_no(channel_no);
                policy.setSale_org_id(sale_org_id);
                //cmainPolicyDao.deleteMainINSPersonPolicy(policy);

                //通过导入的保单号查询数据库表中是否有该条数据
               // CmainPolicyINSPersonVOMModel cmainPolicyINSPersonVOMModel = cmainPolicyDao.findCmainPolicyINSPersonVOMModel(policy);
                cmainPolicyDao.addMainINSPersonPolicy(policy);
            }
            info = "导入数据成功。";
        } else {
            for (String s : msgList) {
                info = info + s + "\\r\\n";
            }
            info = info + "请核对有误的数据再重新导入！";
        }
        return info;
    }


    @Override
    public String importBusiness(MultipartFile file, IUserModel user) throws Exception {
        //为空校验开始
        //ValidateHelper.IsNullOrEmptyThrowException(uploadFileName, "文件不可为空，请检查。");//文件为空校验
        String info = "";// 导入的回显消息
        Map<String, List<Object>> dataMap = readDataBusiness(file);
        //校验数据是否完全正确
        List<String> msgList = cmainPolicyDomain.checkTraPlanInfoBusiness(dataMap);
        if (msgList.size() == 0) { //校验数据是否完全正确
            //将excel数据进行封装成保单
            List<CmainPolicyINSVOMModel> propList = setPropertyCmainPolicyBusiness(dataMap);
            //解析保单入库
            for (CmainPolicyINSVOMModel policy : propList) {
                cmainPolicyDao.addBusiness(policy);
            }
            info = "导入数据成功";
        } else {
            for (String s : msgList) {
                info = info + s + "\\r\\n";
            }
            info = info + "请核对有误的数据再重新导入！";
        }

        return info;
    }

    @Override
    public String importBusinessPreson(MultipartFile file, IUserModel user) throws Exception {
        //为空校验开始
        //ValidateHelper.IsNullOrEmptyThrowException(uploadFileName, "文件不可为空，请检查。");//文件为空校验
        String info = "";// 导入的回显消息
        Map<String, List<Object>> dataMap = readDataBusinessPerson(file);
        //校验数据是否完全正确
        List<String> msgList = cmainPolicyDomain.checkTraPlanInfoBusinessPerson(dataMap);
        if (msgList.size() == 0) { //校验数据是否完全正确
            //将excel数据进行封装成保单
            List<CmainPolicyINSPersonVOMModel> propList = setPropertyCmainPolicyBusinessPerson(dataMap);
            //解析保单入库
            for (CmainPolicyINSPersonVOMModel policy : propList) {
                cmainPolicyDao.addBusinessPerson(policy);
            }
            info = "导入数据成功";
        } else {
            for (String s : msgList) {
                info = info + s + "\\r\\n";
            }
            info = info + "请核对有误的数据再重新导入！";
        }

        return info;
    }


    @Override
    public String importInter(MultipartFile file, IUserModel user) throws Exception {
        //为空校验开始
        //ValidateHelper.IsNullOrEmptyThrowException(uploadFileName, "文件不可为空，请检查。");//文件为空校验
        String info = "";// 导入的回显消息
        Map<String, List<Object>> dataMap = readDataInter(file);
        //校验数据是否完全正确
        List<String> msgList = cmainPolicyDomain.checkTraPlanInfoInter(dataMap);
        if (msgList.size() == 0) { //校验数据是否完全正确
            //将excel数据进行封装成保单
            List<CmainPolicyINSVOMModel> propList = setPropertyCmainPolicyInter(dataMap);
            //解析保单入库
            for (CmainPolicyINSVOMModel policy : propList) {
                cmainPolicyDao.addInter(policy);


//					//1.插入保单信息
//					cmainPolicyDao.deletePolicy(policy);
//					cmainPolicyDao.addMainPolicy(policy);
//					//2.险种处理
//						//封装model
//						IInsRncDfnVOModel riskcode = packageIInsRncDfnVO(policy);
////						InsRncDfnVOModel riskCodeVO=cmainPolicyDao.checkRiskCode(riskcode);
////						if(riskCodeVO !=null){
//							cmainPolicyDao.deleteRisk(policy);
////						}
//					//增加险种
//					IInsRncDfnModel vo = converModel(riskcode);
//					vo.setStatus(CodeTypeConst.PRO_STATUS_CODE1);//产品状态有效
//					InsRncDfnDao.insRncDfnAdd(vo);
//					//3.添加客户信息
//					String branch_id = commonAsynRequestDao.getBranchID();
//					policy.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//备份业务类型？
//					policy.setLog_remark("导入投保单或保单的时候变更客户信息");//备份备注
//					policy.setCreateuser(user.getEmp_id());
//					policy.setModifyuser(user.getEmp_id());
//					policy.setStatus(CodeTypeConst.CUSTOMER_STATUS_EFFECTIVE);
//					policy.setBranch_id(branch_id);
//					cmainPolicyDao.deleteCustomer(policy);//删除保单号对应的客户信息
//					Integer seq_id=cmainPolicyDao.addCustomer(policy);//返回主键备份
//					policy.setLog_seq_id(seq_id);//备份数据主键
//					//添加客户联系信息
//					cmainPolicyDao.addCustomerContact(policy);
//					//添加客户基本信息日志
//					cmainPolicyDao.addCustomerLog(policy);

            }
            info = "导入数据成功";
        } else {
            for (String s : msgList) {
                info = info + s + "\\r\\n";
            }
            info = info + "请核对有误的数据再重新导入！";
        }

        return info;
    }

    @Override
    public String importInterPerson(MultipartFile file, IUserModel user) throws Exception {
        //为空校验开始
        //ValidateHelper.IsNullOrEmptyThrowException(uploadFileName, "文件不可为空，请检查。");//文件为空校验
        String info = "";// 导入的回显消息
        Map<String, List<Object>> dataMap = readDataInterPerson(file);
        //校验数据是否完全正确
        List<String> msgList = cmainPolicyDomain.checkTraPlanInfoInterPerson(dataMap);
        if (msgList.size() == 0) { //校验数据是否完全正确
            //将excel数据进行封装成保单
            List<CmainPolicyINSPersonVOMModel> propList = setPropertyCmainPolicyInterPerson(dataMap);
            //解析保单入库
            for (CmainPolicyINSPersonVOMModel policy : propList) {
                cmainPolicyDao.addInterPerson(policy);


//					//1.插入保单信息
//					cmainPolicyDao.deletePolicy(policy);
//					cmainPolicyDao.addMainPolicy(policy);
//					//2.险种处理
//						//封装model
//						IInsRncDfnVOModel riskcode = packageIInsRncDfnVO(policy);
////						InsRncDfnVOModel riskCodeVO=cmainPolicyDao.checkRiskCode(riskcode);
////						if(riskCodeVO !=null){
//							cmainPolicyDao.deleteRisk(policy);
////						}
//					//增加险种
//					IInsRncDfnModel vo = converModel(riskcode);
//					vo.setStatus(CodeTypeConst.PRO_STATUS_CODE1);//产品状态有效
//					InsRncDfnDao.insRncDfnAdd(vo);
//					//3.添加客户信息
//					String branch_id = commonAsynRequestDao.getBranchID();
//					policy.setLog_bustype(CodeTypeConst.LOG_BUSTYPE);//备份业务类型？
//					policy.setLog_remark("导入投保单或保单的时候变更客户信息");//备份备注
//					policy.setCreateuser(user.getEmp_id());
//					policy.setModifyuser(user.getEmp_id());
//					policy.setStatus(CodeTypeConst.CUSTOMER_STATUS_EFFECTIVE);
//					policy.setBranch_id(branch_id);
//					cmainPolicyDao.deleteCustomer(policy);//删除保单号对应的客户信息
//					Integer seq_id=cmainPolicyDao.addCustomer(policy);//返回主键备份
//					policy.setLog_seq_id(seq_id);//备份数据主键
//					//添加客户联系信息
//					cmainPolicyDao.addCustomerContact(policy);
//					//添加客户基本信息日志
//					cmainPolicyDao.addCustomerLog(policy);

            }
            info = "导入数据成功";
        } else {
            for (String s : msgList) {
                info = info + s + "\\r\\n";
            }
            info = info + "请核对有误的数据再重新导入！";
        }

        return info;
    }


    /**
     * 生成customer_id
     *
     * @param branch_id
     * @param series_code
     * @return String
     * @description:
     */
    public String createId(String branch_id, String series_code) {
        String seq_code = seqDao.queryCommonSeq("seq_id");
        try {
            //调用现成的方法对取出的识别码进行10位补0
            seq_code = StringUtil.alignLeft(seq_code, 10);
        } catch (Message e) {
            e.printStackTrace();
        }
        return branch_id.substring(0, 3) + series_code + seq_code;
    }

    /**
     * 判断客户联系信息是否变化
     *
     * @param model
     * @param
     * @return boolean
     * @description:
     */
    private boolean checkContactInfo(ICmainPolicyVOModel model) {
        boolean boo = true;
        ICustomerContactModel customerContact = new CustomerContactModel();
        //根据客户代码查询联系信息
        customerContact.setCustomer_id(model.getCustomer_id());
        ICustomerContactModel contactDB = customerDao.getNewestCustomerContact(customerContact);//数据库的联系信息数据
        //数据库中的联系信息与用户填写的信息比较
        if (!ValidateHelper.IsNullOrEmpty(model.getAddress())) {
            if (!model.getAddress().equals(contactDB.getAddress())) {//住址
                if (!"".equals(model.getAddress())) {
                    boo = false;
                }
            }
        }
        if (!ValidateHelper.IsNullOrEmpty(model.getMobile())) {

            if (!model.getMobile().equals(contactDB.getMobile())) {//联系电话
                if (!"".equals(model.getMobile())) {
                    boo = false;
                }
            }
        }
        if (!ValidateHelper.IsNullOrEmpty(model.getTelphone())) {
            if (!model.getTelphone().equals(contactDB.getTelphone())) {//移动电话
                if (!"".equals(model.getTelphone())) {
                    boo = false;
                }
            }
        }
        return boo;
    }

    /**
     * IInsRncDfnVOModel转成IInsRncDfnModel
     *
     * @param riskcode
     * @return IInsRncDfnModel
     * @description:
     */
    private IInsRncDfnModel converModel(IInsRncDfnVOModel riskcode) {
        IInsRncDfnModel vo = new InsRncDfnModel();
        vo.setClassCode(riskcode.getClassCode());
        vo.setClassName(riskcode.getClassName());
        vo.setRiskCode(riskcode.getRiskCode());
        vo.setRiskName(riskcode.getRiskName());
        vo.setRtype(riskcode.getRtype());
        vo.setProduct_source(riskcode.getProduct_source());
        return vo;
    }

    /**
     * 封装IInsRncDfnVOModelmodel
     *
     * @param policy
     * @return IInsRncDfnVOModel
     * @description:
     */
    private IInsRncDfnVOModel packageIInsRncDfnVO(ICmainPolicyVOModel policy) {
        IInsRncDfnVOModel riskcode = new InsRncDfnVOModel();
        riskcode.setRiskCode(policy.getRiskcode());
        riskcode.setRiskName(policy.getRiskname());
        riskcode.setClassCode(policy.getClasscode());
        riskcode.setClassName(policy.getClassname());
        riskcode.setRtype(policy.getRtype());
        riskcode.setStatus(CodeTypeConst.PRO_STATUS_CODE1);
        riskcode.setProduct_source(policy.getProduct_source());
        riskcode.setRiskCodeOrign(policy.getRiskcode());
        return riskcode;
    }

    /**
     * 查询保单是否存在(保单号)
     *
     * @param
     * @return boolean
     * @description:
     */
    private boolean isHavePolicy(String policyno) {
        List<CmainPolicyVOMModel> policys = cmainPolicyDao.findPolicy(policyno);
        if (policys == null || policys.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 将excel封装成List
     *
     * @param dataMap
     * @return List<CmainPolicyVOMModel>
     * @description:
     */
    private List<CmainPolicyVOMModel> setPropertyCmainPolicyVOMModel(
            Map<String, List<Object>> dataMap) {
        List<CmainPolicyVOMModel> voList = new ArrayList<CmainPolicyVOMModel>();
        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }
            for (int i = 0; i < practice.size(); i++) {
                CmainPolicyVOMModel cpvo = new CmainPolicyVOMModel();
                Map praMap = (HashMap) practice.get(i);

                cpvo.setPolicyno(ActionHelper.getNullToStr(praMap.get("policyno")));//保单号*
                cpvo.setRiskcode(ActionHelper.getNullToStr(praMap.get("riskcode")));//险种代码*
                cpvo.setRiskname(ActionHelper.getNullToStr(praMap.get("riskname")));//险种名称*
                cpvo.setClasscode(ActionHelper.getNullToStr(praMap.get("classcode")));//险类代码*
                cpvo.setClassname(ActionHelper.getNullToStr(praMap.get("classname")));//险类名称*
                cpvo.setRtype(ActionHelper.getNullToStr(praMap.get("rtype")));//保监会分类*
                cpvo.setProduct_source(ActionHelper.getNullToStr(praMap.get("product_source")));//产品来源*
                cpvo.setCcurno(ActionHelper.getNullToStr(praMap.get("ccurno")));//币种*
                cpvo.setNetpremium(ActionHelper.getNullToDouble(praMap.get("netpremium")));//保费*
                cpvo.setSigndate(DateUtil.string2Date(praMap.get("signdate")));//签单日期*
                cpvo.setOperatedate(DateUtil.string2Date(praMap.get("operatedate")));//核保日期*
                cpvo.setStartdate(DateUtil.string2Date(praMap.get("startdate")));//起保日期*
                cpvo.setEnddate(DateUtil.string2Date(praMap.get("enddate")));//终保日期*
                cpvo.setSumamount(ActionHelper.getNullToDouble(praMap.get("sumamount")));//保额*
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("stype")).trim())) {
                    cpvo.setStype(ActionHelper.getNullToStr(praMap.get("stype")));//业务分类
                }
                cpvo.setFrate(ActionHelper.getNullToDouble(praMap.get("frate")));//手续费比例*
                cpvo.setFnum(ActionHelper.getNullToDouble(praMap.get("fnum")));//手续费金额*
                cpvo.setApplicode(ActionHelper.getNullToStr(praMap.get("applicode")));//投保人代码*
                cpvo.setAppliname(ActionHelper.getNullToStr(praMap.get("appliname")));//投保人名称*
                cpvo.setInsuredcode(ActionHelper.getNullToStr(praMap.get("insuredcode")));//被保险人代码*
                cpvo.setInsuredname(ActionHelper.getNullToStr(praMap.get("insuredname")));//被保险人名称*
                cpvo.setHandlercode(ActionHelper.getNullToStr(praMap.get("handlercode")));//销售员代码(销售公司)*
                cpvo.setHandlername(ActionHelper.getNullToStr(praMap.get("handlername")));//销售员名称(销售公司)*
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("car_no")))) {
                    cpvo.setCar_no(ActionHelper.getNullToStr(praMap.get("car_no")));//车牌号
                }
                cpvo.setRenewalflag(ActionHelper.getNullToStr(praMap.get("renewalflag")));//续保标志*
                cpvo.setDcoldte(DateUtil.string2Date(praMap.get("dcoldte")));//实收付日期*
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("paymode")))) {
                    cpvo.setPaymode(ActionHelper.getNullToStr(praMap.get("paymode")));//缴费方式
                }
                cpvo.setCustomer_id(ActionHelper.getNullToStr(praMap.get("customer_id")));//客户编号*
                cpvo.setName(ActionHelper.getNullToStr(praMap.get("name")));//客户名称*
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("customer_type")))) {
                    cpvo.setCustomer_type(ActionHelper.getNullToStr(praMap.get("customer_type")));//客户类型
                }
                cpvo.setCerti_type(ActionHelper.getNullToStr(praMap.get("certi_type")));//客户证件类型*
                cpvo.setCerti_no(ActionHelper.getNullToStr(praMap.get("certi_no")));//客户证件号码*
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("gender")))) {
                    cpvo.setGender(ActionHelper.getNullToStr(praMap.get("gender")));//客户性别
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("birthday")))) {
                    cpvo.setBirthday(DateUtil.string2Date(praMap.get("birthday")));//客户出生日期
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("mobile")))) {
                    cpvo.setMobile(ActionHelper.getNullToStr(praMap.get("mobile")));//客户手机号
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("telphone")))) {
                    cpvo.setTelphone(ActionHelper.getNullToStr(praMap.get("telphone")));//客户家庭电话号
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("company_telphone")))) {
                    cpvo.setCompany_telphone(ActionHelper.getNullToStr(praMap.get("company_telphone")));//客户办公电话号
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("address")))) {
                    cpvo.setAddress(ActionHelper.getNullToStr(praMap.get("address")));//客户地址
                }
                cpvo.setBusinesscode("1");//个人业务
                voList.add(i, cpvo);
            }
        }
        return voList;
    }


    /**
     * 将excel封装成List
     *
     * @param dataMap
     * @return List<CmainPolicyVOMModel>
     * @description:
     */
    private List<CmainPolicyINSVOMModel> setPropertyCmainPolicyINSVOMModel(Map<String, List<Object>> dataMap) {
        List<CmainPolicyINSVOMModel> voList = new ArrayList<CmainPolicyINSVOMModel>();
        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }
            for (int i = 0; i < practice.size(); i++) {
                CmainPolicyINSVOMModel cpvo = new CmainPolicyINSVOMModel();
                Map praMap = (HashMap) practice.get(i);
                cpvo.setOrd_id(ActionHelper.getNullToStr(praMap.get("ord_id")).trim());//归属支公司代码
                cpvo.setOrg_name(ActionHelper.getNullToStr(praMap.get("org_name")).trim());//机构名称
                cpvo.setStatistics_type(ActionHelper.getNullToStr(praMap.get("statistics_type")).trim());//统计口径（不用）
                //cpvo.setChannel_type(ActionHelper.getNullToStr(praMap.get("channel_type")));//渠道类型'专业代理'
                cpvo.setChannel_no(ActionHelper.getNullToStr(praMap.get("channel_no")).trim());//渠道代码
                cpvo.setInsurance_type_name(ActionHelper.getNullToStr(praMap.get("insurance_type_name")).trim());//险种名称
                cpvo.setProduct_name(ActionHelper.getNullToStr(praMap.get("product_name")).trim());//产品名称

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("sign_date")).trim()))
                    cpvo.setSign_date(DateUtil.string2Date(praMap.get("sign_date")));//签单日期

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("accomplish_date")).trim()))
                    cpvo.setAccomplish_date(DateUtil.string2Date(praMap.get("accomplish_date")));//成单日期

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("statistics_date")).trim()))
                    cpvo.setStatistics_date(DateUtil.string2Date(praMap.get("statistics_date")));//统计时间

                cpvo.setInsure_no(ActionHelper.getNullToStr(praMap.get("insure_no")).trim());//投保单号
                cpvo.setPolicy_no(ActionHelper.getNullToStr(praMap.get("policy_no")).trim());//保单号
                cpvo.setSerial_no(ActionHelper.getNullToStr(praMap.get("serial_no")).trim());//快钱流水号
                cpvo.setApplicant_name(ActionHelper.getNullToStr(praMap.get("applicant_name")).trim());//投保人姓名
                cpvo.setInsured_name(ActionHelper.getNullToStr(praMap.get("insured_name")).trim());//被保险人姓名
                cpvo.setLpn(ActionHelper.getNullToStr(praMap.get("lpn")).trim());//车牌号
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("premium")).trim())) {
                    cpvo.setPremium(ActionHelper.getNullToDouble(praMap.get("premium")));//保费
                } else {
                    cpvo.setPremium(0.0);//保费
                }

                cpvo.setRepair_coding(ActionHelper.getNullToStr(praMap.get("repair_coding")).trim());//推荐维修码
                cpvo.setRack_no(ActionHelper.getNullToStr(praMap.get("rack_no")).trim());//车架号

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("check_date")).trim()))
                    cpvo.setCheck_date(DateUtil.string2Date(praMap.get("check_date")));//核保日期

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("begin_date")).trim()))
                    cpvo.setBegin_date(DateUtil.string2Date(praMap.get("begin_date")));//起保日期

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("end_date")).trim()))
                    cpvo.setEnd_date(DateUtil.string2Date(praMap.get("end_date")));//终保日期

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("approval_date")).trim()))
                    cpvo.setApproval_date(DateUtil.string2Date(praMap.get("approval_date")));//	批单日期

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("fee_ratio")).trim())) {
                    cpvo.setFee_ratio(ActionHelper.getNullToDouble(praMap.get("fee_ratio")));//	销售公司跟单手续费比例
                } else {
                    cpvo.setFee_ratio(0.0);//销售公司跟单手续费比例
                }

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("fee")).trim())) {
                    cpvo.setFee(ActionHelper.getNullToDouble(praMap.get("fee")));//销售公司跟单手续费
                } else {
                    cpvo.setFee(0.0);//	销售公司跟单手续费
                }

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("clean_charge")).trim())) {
                    cpvo.setClean_charge(ActionHelper.getNullToDouble(praMap.get("clean_charge")));//已结算手续费金额
                } else {
                    cpvo.setClean_charge(0.0);//已结算手续费金额
                }

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("wei_rate")).trim())) {
                    cpvo.setWei_rate(ActionHelper.getNullToDouble(praMap.get("wei_rate")));//代理人佣金比例
                } else {
                    cpvo.setWei_rate(0.0);
                }

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("commission")).trim())) {
                    cpvo.setCommission(ActionHelper.getNullToDouble(praMap.get("commission")));//代理人佣金
                } else {
                    cpvo.setCommission(0.0);//代理人佣金
                }

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("cash")).trim())) {
                    cpvo.setCash(ActionHelper.getNullToDouble(praMap.get("cash")));//提现金额
                } else {
                    cpvo.setCash(0.0);//提现金额
                }

                cpvo.setStatus(ActionHelper.getNullToStr(praMap.get("status")).trim());//有效无效
                cpvo.setPerson_name(ActionHelper.getNullToStr(praMap.get("person_name")).trim());//（财险）归属人工号
                cpvo.setPerson_no(ActionHelper.getNullToStr(praMap.get("person_no")).trim());//	（财险）归属人姓名
                cpvo.setInsurance_class_no(ActionHelper.getNullToStr(praMap.get("insurance_class_no")).trim());//	险类代码
                cpvo.setInsurance_type_no(ActionHelper.getNullToStr(praMap.get("insurance_type_no")).trim());//	险种代码
                cpvo.setProduct_no(ActionHelper.getNullToStr(praMap.get("product_no")).trim());//产品代码
                cpvo.setInsured_cid(ActionHelper.getNullToStr(praMap.get("insured_cid")).trim());//被保险人身份证
                cpvo.setInsured_company_type(ActionHelper.getNullToStr(praMap.get("insured_company_type")).trim());//被保险人单位性质
                cpvo.setInsured_papertype(ActionHelper.getNullToStr(praMap.get("insured_papertype")).trim());//被保险人证件类型
                cpvo.setInsured_add(ActionHelper.getNullToStr(praMap.get("insured_add")).trim());//	被保险人地址
                cpvo.setInsured_mailbox(ActionHelper.getNullToStr(praMap.get("insured_mailbox")).trim());//被保险人邮箱
                cpvo.setInsured_phone(ActionHelper.getNullToStr(praMap.get("insured_phone")).trim());//	被保险人手机
                cpvo.setInsured_tel(ActionHelper.getNullToStr(praMap.get("insured_tel")).trim());//	被保险人固话
                cpvo.setInsurance_class_name(ActionHelper.getNullToStr(praMap.get("insurance_class_name")).trim());//险类名称
                cpvo.setEndor_no(ActionHelper.getNullToStr(praMap.get("endor_no")).trim());//批单号
                cpvo.setCar_type(ActionHelper.getNullToStr(praMap.get("car_type")).trim());//车型编码
                cpvo.setVin(ActionHelper.getNullToStr(praMap.get("vin")).trim());//vin车架号
                cpvo.setEngine_no(ActionHelper.getNullToStr(praMap.get("engine_no")).trim());//发动机号
                cpvo.setCar_class(ActionHelper.getNullToStr(praMap.get("car_class")).trim());//车辆种类
                cpvo.setUse_type(ActionHelper.getNullToStr(praMap.get("use_type")).trim());//使用性质

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("first_date")).trim()))
                    cpvo.setFirst_date(DateUtil.string2Date(praMap.get("first_date")));//初登日期

                cpvo.setClause_type(ActionHelper.getNullToStr(praMap.get("clause_type")).trim());//	条款类型

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("net_premium")).trim())) {
                    cpvo.setNet_premium(ActionHelper.getNullToDouble(praMap.get("net_premium")));//	净保费（不含税）
                } else {
                    cpvo.setNet_premium(0.0);//净保费（不含税）
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("vehicel_tax")).trim())) {
                    cpvo.setVehicel_tax(ActionHelper.getNullToDouble(praMap.get("vehicel_tax")));//	车船税
                } else {
                    cpvo.setVehicel_tax(0.0);//	车船税
                }

                cpvo.setCar_branchname(ActionHelper.getNullToStr(praMap.get("car_branchname")).trim());//	车行名称

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("print_date")).trim()))
                    cpvo.setPrint_date(DateUtil.string2Date(praMap.get("print_date")));//	打印日期

                cpvo.setApproval_flag(ActionHelper.getNullToStr(praMap.get("approval_flag")).trim());//	新/续保标志 ： 0：新 1：续

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("amount")).trim())) {
                    cpvo.setAmount(ActionHelper.getNullToDouble(praMap.get("amount")));//	保额
                } else {
                    cpvo.setAmount(0.0);//	保额
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("premium_tax")).trim())) {
                    cpvo.setPremium_tax(ActionHelper.getNullToDouble(praMap.get("premium_tax")));//	保费增值税
                } else {
                    cpvo.setPremium_tax(0.0);//	保额
                }

                cpvo.setEndor_type(ActionHelper.getNullToStr(praMap.get("endor_type")).trim());//	批单类型
                cpvo.setProvince_orgid(ActionHelper.getNullToStr(praMap.get("province_orgid")).trim());//	财险省份机构代码
                cpvo.setProvince_orgname(ActionHelper.getNullToStr(praMap.get("province_orgname")).trim());//	财险省份机构名称
                cpvo.setCity_orgid(ActionHelper.getNullToStr(praMap.get("city_orgid")).trim());//	财险市级机构代码
                cpvo.setCity_orgname(ActionHelper.getNullToStr(praMap.get("city_orgname")).trim());//	财险市级机构名称
                cpvo.setArea_orgid(ActionHelper.getNullToStr(praMap.get("area_orgid")).trim());//	财险区县机构代码
                cpvo.setArea_orgname(ActionHelper.getNullToStr(praMap.get("area_orgname")).trim());//	财险区县机构名称
                cpvo.setResponsible_no(ActionHelper.getNullToStr(praMap.get("responsible_no")).trim());//	经办人工号
                cpvo.setResponsible_name(ActionHelper.getNullToStr(praMap.get("responsible_name")).trim());//	经办人姓名
                cpvo.setPerson_cid(ActionHelper.getNullToStr(praMap.get("person_cid")).trim());//	代理人身份证号

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("create_date")).trim()))
                    cpvo.setCreate_date(DateUtil.string2Date(praMap.get("create_date")));//	创建时间

                cpvo.setCreate_user(ActionHelper.getNullToStr(praMap.get("create_user")).trim());//	创建人

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("mdf_date")).trim()))
                    cpvo.setMdf_date(DateUtil.string2Date(praMap.get("mdf_date")));//	修改时间

                cpvo.setMdf_user(ActionHelper.getNullToStr(praMap.get("mdf_user")).trim());//	修改人
                cpvo.setCar_name(ActionHelper.getNullToStr(praMap.get("car_name")).trim());//	车型名称
                cpvo.setOrg_id(ActionHelper.getNullToStr(praMap.get("org_id")));//	财险归属经营单位代码

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("ins_validate")).trim()))
                    cpvo.setIns_validate(DateUtil.string2Date(praMap.get("ins_validate")));//	保单生效日期（起报日期核保日期取大的）

                cpvo.setData_type(ActionHelper.getNullToStr(praMap.get("data_type")).trim());//	数据类型：0 ：保单类型 1：批单类型
                cpvo.setChange_nu(ActionHelper.getNullToStr(praMap.get("change_nu")).trim());//	数量变化
                cpvo.setOwner_no(ActionHelper.getNullToStr(praMap.get("owner_no")).trim());//	（销售公司）业务归属人工号
                cpvo.setOwner_name(ActionHelper.getNullToStr(praMap.get("owner_name")).trim());//	（销售公司）业务归属人姓名
                cpvo.setInter_flag(ActionHelper.getNullToStr(praMap.get("inter_flag")).trim());//	（销售公司）业务归属人姓名
                cpvo.setBusiness_flag(ActionHelper.getNullToStr(praMap.get("business_flag")).trim());//	（销售公司）业务归属人姓名

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("service_fee")).trim())) {
                    cpvo.setService_fee(ActionHelper.getNullToDouble(praMap.get("service_fee")));
                } else {
                    cpvo.setService_fee(0.0);
                }

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("car_value")).trim())) {
                    cpvo.setCar_value(ActionHelper.getNullToDouble(praMap.get("car_value")));
                } else {
                    cpvo.setCar_value(0.0);
                }

                cpvo.setRemarks(ActionHelper.getNullToStr(praMap.get("remarks")).trim());
                cpvo.setCar_use_year(ActionHelper.getNullToStr(praMap.get("car_use_year")).trim());
                cpvo.setSettled_date(ActionHelper.getNullToStr(praMap.get("settled_date")).trim());
                cpvo.setChannel_no(ActionHelper.getNullToStr(praMap.get("channel_no")).trim());

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("endor_date")).trim()))
                    cpvo.setEndor_date(DateUtil.string2Date(praMap.get("endor_date")));
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("endor_valid")).trim()))
                    cpvo.setEndor_valid(DateUtil.string2Date(praMap.get("endor_valid")));

                cpvo.setEndor_no(ActionHelper.getNullToStr(praMap.get("endor_no")).trim());
                cpvo.setChange_nu(ActionHelper.getNullToStr(praMap.get("change_nu")).trim());
                if (null == cpvo.getEndor_no() || "".equals(cpvo.getEndor_no())) {
                    cpvo.setData_type("0");
                } else {
                    cpvo.setData_type("1");
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("settle_time")).trim()))
                    cpvo.setSettle_time(DateUtil.string2Date(praMap.get("settle_time")));//	结算日期
                voList.add(i, cpvo);
            }
        }
        return voList;
    }


    /**
     * 将excel封装成List
     *
     * @param dataMap
     * @return List<CmainPolicyVOMModel>
     * @description:
     */
    private List<CmainPolicyINSPersonVOMModel> setPropertyCmainPolicyINSPersonVOMModel(Map<String, List<Object>> dataMap) {
        List<CmainPolicyINSPersonVOMModel> voList = new ArrayList<CmainPolicyINSPersonVOMModel>();
        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }
            for (int i = 0; i < practice.size(); i++) {
                CmainPolicyINSPersonVOMModel cpvo = new CmainPolicyINSPersonVOMModel();
                HashMap praMap = (HashMap) practice.get(i);
                cpvo.setProvince_orgname(ActionHelper.getNullToStr(praMap.get("province_orgname")).trim());//	保险公司省级机构名称
                cpvo.setProvince_orgid(ActionHelper.getNullToStr(praMap.get("province_orgid")).trim());//	保险公司省级机构代码
                cpvo.setCity_orgname(ActionHelper.getNullToStr(praMap.get("city_orgname")).trim());//	保险公司地市机构名称
                cpvo.setCity_orgid(ActionHelper.getNullToStr(praMap.get("city_orgid")).trim());//	保险公司地市机构代码
                cpvo.setArea_orgid(ActionHelper.getNullToStr(praMap.get("area_orgid")).trim());//	保险公司县支公司名称
                cpvo.setArea_orgname(ActionHelper.getNullToStr(praMap.get("area_orgname")).trim());//	保险公司县支公司代码
                String province_orgid = ActionHelper.getNullToStr(praMap.get("province_orgid")).trim();
                String city_orgid = ActionHelper.getNullToStr(praMap.get("city_orgid")).trim();
                String area_orgid = ActionHelper.getNullToStr(praMap.get("area_orgid")).trim();
                if (area_orgid != null && !"".equals(area_orgid)) {
                    cpvo.setOrg_id(area_orgid);
                } else if (city_orgid != null && !"".equals(city_orgid)) {
                    cpvo.setOrg_id(city_orgid);
                } else if (province_orgid != null && !"".equals(province_orgid)) {
                    cpvo.setOrg_id(province_orgid);
                } else {
                    cpvo.setOrg_id("");
                }
                cpvo.setSale_org_name(ActionHelper.getNullToStr(praMap.get("sale_org_name")).trim());//	销售公司机构名称
                //cpvo.setSale_org_id			(ActionHelper.getNullToStr(praMap.get("sale_org_id")))			;//	销售公司机构代码
                cpvo.setChannel_no(ActionHelper.getNullToStr(praMap.get("channel_no")).trim());//	销售公司渠道代码
                cpvo.setPolicy_no(ActionHelper.getNullToStr(praMap.get("policy_no")).trim());//	保单号
                cpvo.setInsure_no(ActionHelper.getNullToStr(praMap.get("insure_no")).trim());//	投保单号
                cpvo.setCenter_risk_flag(ActionHelper.getNullToStr(praMap.get("center_risk_flag")).trim());//	主险/附加险标识
                cpvo.setCenter_risk_name(ActionHelper.getNullToStr(praMap.get("center_risk_name")).trim());//	险种名称
                cpvo.setCenter_risk_id(ActionHelper.getNullToStr(praMap.get("center_risk_id")).trim());//	险种代码
                cpvo.setApplicant_name(ActionHelper.getNullToStr(praMap.get("applicant_name")).trim());//	投保人
                cpvo.setInsured_name(ActionHelper.getNullToStr(praMap.get("insured_name")).trim());//	被保险人

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("insure_date")).trim())) {
                    String insure_date = ActionHelper.getNullToStr(praMap.get("insure_date")).trim();
                    cpvo.setInsure_date(DateUtil.string2Date(insure_date));//	承保日期
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("paper_date")).trim()))
                    cpvo.setPaper_date(DateUtil.string2Date(praMap.get("paper_date")));//	签发日期

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("cancel_date")).trim()))
                    cpvo.setCancel_date(DateUtil.string2Date(praMap.get("cancel_date")));//	回执核销日期
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("ins_validate")).trim()))
                    cpvo.setIns_validate(DateUtil.string2Date(praMap.get("ins_validate")));//	回执核销日期

                cpvo.setCancel_reason(ActionHelper.getNullToStr(praMap.get("cancel_reason")).trim());//	终止原因名称
                cpvo.setPay_fee_type(ActionHelper.getNullToStr(praMap.get("pay_fee_type")).trim());//	缴费方式名称
                cpvo.setPay_date(ActionHelper.getNullToStr(praMap.get("pay_date")).trim());//	缴费期
                cpvo.setInsured_during(ActionHelper.getNullToStr(praMap.get("insured_during")).trim());//	保险期间
                cpvo.setPay_type(ActionHelper.getNullToStr(praMap.get("pay_type")).trim());//	付款方式名称
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("premium")).trim())) {
                    cpvo.setPremium(ActionHelper.getNullToDouble(praMap.get("premium")));//	保费
                } else {
                    cpvo.setPremium(0.0);//	保费
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("amount")).trim())) {
                    cpvo.setAmount(ActionHelper.getNullToDouble(praMap.get("amount")));//	保额
                } else {
                    cpvo.setAmount(0.0);//	保额

                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("fee_ratio")).trim())) {
                    cpvo.setFee_ratio(ActionHelper.getNullToDouble(praMap.get("fee_ratio")));//	手续费比例
                } else {
                    cpvo.setFee_ratio(0.0);//	手续费比例
                }

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("new_paper_fee")).trim())) {
                    cpvo.setNew_paper_fee(ActionHelper.getNullToDouble(praMap.get("new_paper_fee")));//	新单佣金
                } else {
                    cpvo.setNew_paper_fee(0.0);//	新单佣金
                }

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("renew_paper_fee")).trim())) {
                    cpvo.setRenew_paper_fee(ActionHelper.getNullToDouble(praMap.get("renew_paper_fee")));//	续期佣金
                } else {
                    cpvo.setRenew_paper_fee(0.0);//	续期佣金
                }

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("total_fee")).trim())) {
                    cpvo.setTotal_fee(ActionHelper.getNullToDouble(praMap.get("total_fee")));//	手续费（合计）
                } else {
                    cpvo.setTotal_fee(0.0);//	手续费（合计）
                }


                cpvo.setOwner_name(ActionHelper.getNullToStr(praMap.get("owner_name")).trim());//	销售公司归属人员姓名
                cpvo.setOwner_no(ActionHelper.getNullToStr(praMap.get("owner_no")).trim());//	销售公司归属人员编码
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("agent_fee_rate")).trim())) {
                    cpvo.setAgent_fee_rate(ActionHelper.getNullToDouble(praMap.get("agent_fee_rate")));//	代理人佣金支付比例
                } else {
                    cpvo.setAgent_fee_rate(0.0);//	代理人佣金支付比例
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("agent_fee")).trim())) {
                    cpvo.setAgent_fee(ActionHelper.getNullToDouble(praMap.get("agent_fee")));//	代理人佣金金额
                } else {
                    cpvo.setAgent_fee(0.0);//	代理人佣金金额
                }

                cpvo.setBusiness_flag(ActionHelper.getNullToStr(praMap.get("business_flag")).trim());//	业务类型
                cpvo.setInter_flag(ActionHelper.getNullToStr(praMap.get("inter_flag")).trim());//	互联网标识
                cpvo.setEndor_no(ActionHelper.getNullToStr(praMap.get("endor_no")).trim());//	批单号

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("endor_date")).trim()))
                    cpvo.setEndor_date(DateUtil.string2Date(praMap.get("endor_date")));//	批改日期

                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("endor_valid")).trim()))
                    cpvo.setEndor_valid(DateUtil.string2Date(praMap.get("endor_valid")));//	批改生效日期
                cpvo.setSettled_date(ActionHelper.getNullToStr(praMap.get("settled_date")).trim());
                cpvo.setChannel_no(ActionHelper.getNullToStr(praMap.get("channel_no")).trim());

                if ("".equals(cpvo.getEndor_no())) {
                    cpvo.setData_type("0");
                } else {
                    cpvo.setData_type("1");
                }
                if (!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("change_nu")).trim())) {
                    cpvo.setChange_nu(ActionHelper.getNullToInteger(praMap.get("change_nu")));
                } else {
                    cpvo.setChange_nu(0);//	代理人佣金金额
                }


                cpvo.setCenter_risk_type(ActionHelper.getNullToStr(praMap.get("center_risk_type")).trim());
                cpvo.setLong_risk_flag(ActionHelper.getNullToStr(praMap.get("long_risk_flag")).trim());
                //cpvo.setRenew_flag	            (ActionHelper.getNullToStr(praMap.get("renew_flag"))) ;
                cpvo.setApplicant_id(ActionHelper.getNullToStr(praMap.get("applicant_id")).trim());
                cpvo.setInsured_id(ActionHelper.getNullToStr(praMap.get("insured_id")).trim());
                cpvo.setInsured_during_flag(ActionHelper.getNullToStr(praMap.get("insured_during_flag")).trim());
                cpvo.setPay_fee_year(ActionHelper.getNullToStr(praMap.get("pay_fee_year")).trim());
                cpvo.setRisk_year(ActionHelper.getNullToStr(praMap.get("risk_year")).trim());
                String settled_time = ActionHelper.getNullToStr(praMap.get("settled_time")).trim();//结算时间
                cpvo.setSettled_time(settled_time);
                String settled_date = ActionHelper.getNullToStr(praMap.get("settled_date")).trim();//分单时间
                if (settled_date == null || "".equals(settled_date)) {
                    cpvo.setSettled_date(settled_time);//	分单时间
                } else {
                    cpvo.setSettled_date(settled_date);//	分单时间
                }

                voList.add(i, cpvo);
            }
        }
        return voList;
    }


    /**
     * 将excel封装成List
     *
     * @param dataMap
     * @return List<CmainPolicyVOMModel>
     * @description:
     */
    private List<CmainPolicyINSVOMModel> setPropertyCmainPolicyBusiness(Map<String, List<Object>> dataMap) {
        List<CmainPolicyINSVOMModel> voList = new ArrayList<CmainPolicyINSVOMModel>();
        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }
            for (int i = 0; i < practice.size(); i++) {
                CmainPolicyINSVOMModel cpvo = new CmainPolicyINSVOMModel();
                Map praMap = (HashMap) practice.get(i);
//				"org_id",//销售公司机构
//				"policy_no"	,//保单号码
//				"business_flag"//业务类型
                //cpvo.setOrg_id             (ActionHelper.getNullToStr(praMap.get("org_id")))                ;
                cpvo.setPolicy_no(ActionHelper.getNullToStr(praMap.get("policy_no")));
                cpvo.setBusiness_flag(ActionHelper.getNullToStr(praMap.get("business_flag")));
                voList.add(i, cpvo);
            }
        }
        return voList;
    }

    private List<CmainPolicyINSPersonVOMModel> setPropertyCmainPolicyBusinessPerson(Map<String, List<Object>> dataMap) {
        List<CmainPolicyINSPersonVOMModel> voList = new ArrayList<CmainPolicyINSPersonVOMModel>();
        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }
            for (int i = 0; i < practice.size(); i++) {
                CmainPolicyINSPersonVOMModel cpvo = new CmainPolicyINSPersonVOMModel();
                Map praMap = (HashMap) practice.get(i);
//				"org_id",//销售公司机构
//				"policy_no"	,//保单号码
//				"business_flag"//业务类型
                //cpvo.setArea_orgid            (ActionHelper.getNullToStr(praMap.get("area_orgid")))                ;
                cpvo.setPolicy_no(ActionHelper.getNullToStr(praMap.get("policy_no")));
                cpvo.setBusiness_flag(ActionHelper.getNullToStr(praMap.get("business_flag")));
                voList.add(i, cpvo);
            }
        }
        return voList;
    }


    /**
     * 将excel封装成List
     *
     * @param dataMap
     * @return List<CmainPolicyVOMModel>
     * @description:
     */
    private List<CmainPolicyINSPersonVOMModel> setPropertyCmainPolicyInterPerson(Map<String, List<Object>> dataMap) {
        List<CmainPolicyINSPersonVOMModel> voList = new ArrayList<CmainPolicyINSPersonVOMModel>();
        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }
            for (int i = 0; i < practice.size(); i++) {
                CmainPolicyINSPersonVOMModel cpvo = new CmainPolicyINSPersonVOMModel();
                Map praMap = (HashMap) practice.get(i);
                //cpvo.setArea_orgid             (ActionHelper.getNullToStr(praMap.get("area_orgid")))                ;
                cpvo.setPolicy_no(ActionHelper.getNullToStr(praMap.get("policy_no")));
                cpvo.setInter_flag(ActionHelper.getNullToStr(praMap.get("inter_flag")));//	（销售公司）业务归属人姓名
                voList.add(i, cpvo);
            }
        }
        return voList;
    }

    /**
     * 将excel封装成List
     *
     * @param dataMap
     * @return List<CmainPolicyVOMModel>
     * @description:
     */
    private List<CmainPolicyINSVOMModel> setPropertyCmainPolicyInter(Map<String, List<Object>> dataMap) {
        List<CmainPolicyINSVOMModel> voList = new ArrayList<CmainPolicyINSVOMModel>();
        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }
            for (int i = 0; i < practice.size(); i++) {
                CmainPolicyINSVOMModel cpvo = new CmainPolicyINSVOMModel();
                Map praMap = (HashMap) practice.get(i);
                //cpvo.setOrd_id             (ActionHelper.getNullToStr(praMap.get("org_id")))                ;
                cpvo.setPolicy_no(ActionHelper.getNullToStr(praMap.get("policy_no")));
                cpvo.setInter_flag(ActionHelper.getNullToStr(praMap.get("inter_flag")));//	（销售公司）业务归属人姓名
                voList.add(i, cpvo);
            }
        }
        return voList;
    }


    // 设置导入保单的信息，取数据使用
    private Map<String, List<Object>> readDataFromExcel(File excelFile) {
        ExcelUtil util = new ExcelUtil();
        // 导入信息对应导入模板的列字段
        String[] titles = new String[]{

                "policyno",//保单号*
                "riskcode",//险种代码*
                "riskname",//险种名称*
                "classcode",//险类代码*
                "classname",//险类名称*
                "rtype",//保监会分类*
                "product_source",//产品来源(*)
                "ccurno",//币种*
                "netpremium",//保费*
                "signdate",//签单日期*
                "operatedate",//核保日期*
                "startdate",//起保日期*
                "enddate",//终保日期*
                "sumamount",//保额*
                "stype",//业务分类
                "frate",//手续费比例*
                "fnum",//手续费金额*
                "applicode",//投保人代码*
                "appliname",//投保人名称*
                "insuredcode",//被保险人代码*
                "insuredname",//被保险人名称*
                "handlercode",//销售员代码(销售公司)*
                "handlername",//销售员名称(销售公司)*
                "car_no",//车牌号
                "renewalflag",//续保标志*
                "dcoldte",//实收付日期*
                "paymode",//缴费方式
                "customer_id",//客户编号*
                "name",//客户名称*
                "customer_type",//客户类型
                "certi_type",//客户证件类型*
                "certi_no",//客户证件号码*
                "gender",//客户性别
                "birthday",//客户出生日期
                "mobile",//客户手机号
                "telphone",//客户家庭电话号
                "company_telphone",//客户办公电话号
                "address",//客户地址

        };
        Map<String, List<Object>> resultMap = util.initSheet4Stream(
                excelFile.getAbsolutePath(), new Object(), titles);
        return resultMap;
    }


    // 设置导入保单的信息，取数据使用
    private Map<String, List<Object>> readDataFromExcel2(MultipartFile excelFile) throws IOException {
        ExcelUtil util = new ExcelUtil();
        // 导入信息对应导入模板的列字段
        String[] titles = new String[]{
                "seq_id", //序号
                "province_orgname",//省级机构  名称
                "province_orgid",//省级机构 代码
                "city_orgname",//地市机构名称
                "city_orgid",//地市机构   代码
                "area_orgid",//区县机构代码
                "area_orgname",//区县机构名称
               /* "org_id",//归属部门代码
                "org_name",//归属部门名称
                "person_name",//归属人姓名
                "person_no",//归属人工号*/
                "responsible_no",//经办人工号
                "responsible_name",//经办人姓名
                "channel_no",//渠道代码
                "insurance_class_name",//险类名称
                "insurance_class_no",//险类代码
                "insurance_type_name",//险种名称
                "insurance_type_no",//险种代码
                "sign_date",//签单日期
                "insure_no",//投保单号
                "policy_no",//保单号
                "applicant_name",//投保人姓名
                "insured_name",//被保险人姓名
                "insured_cid",//被保险人身份证
                "insured_company_type",//被保险人单位性质
                "insured_papertype",//被保险人证件类型（代码/名称）
                "insured_add",//被保险人地址
                "insured_mailbox",//被保险人邮箱
                "insured_phone",//被保险人手机
                "insured_tel",//被保险人固话
                "lpn",//车牌号
                "car_type",//车型编码
                "car_name",//车型名称
                "vin",//车架号（vin码）
                "engine_no",//发动机号
                "car_class",//车辆种类
                "use_type",//使用性质
                "first_date",//初登日期
                "clause_type",//条款类型
                "amount",//保额
                "premium_tax",//保费增值税
                "premium",//总保费
                "net_premium",//净保费（不含税）
                "vehicel_tax",//车船税
               /* "car_branchname",//车行名称
                "repair_coding",//推荐维修码*/
                "check_date",//核保日期
                "begin_date",//起保日期
                "end_date",//终保日期
                "fee_ratio",//跟单手续费比例
                "fee",//跟单手续费
                "approval_flag",//新/续保标志
                "print_date",//保单打印日期
                "ins_validate",//保单生效日期（起报日期核保日期取大的）
                /*"settled_date",//分单日期（年月，例如：2019-05）*/
                "owner_name",//销售公司归属人员姓名
                "owner_no",//销售公司归属人员编码
                "wei_rate",//代理人佣金支付比例
                "commission",//代理人佣金金额
                /*"business_flag",//业务类型
                "inter_flag",//互联网标识*/
                "ord_id",//销售公司机构代码
                "endor_no",//批单号
                "change_nu",//数量变化
                "approval_date",//批改日期
                "ins_validate",//批改生效日期
                "settle_time"//结算日期
        };
        Map<String, List<Object>> resultMap = util.initSheet4Stream(excelFile.getInputStream(), new Object(), titles);
        return resultMap;
    }

    // 设置导入保单的信息，取数据使用
    private Map<String, List<Object>> readDataFromExcelPerson(MultipartFile excelFile) throws IOException {
        ExcelUtil util = new ExcelUtil();
        // 导入信息对应导入模板的列字段
        String[] titles = new String[]{
                /*"ser_no",
                "province_orgname",
                "province_orgid",
                "city_orgname",
                "city_orgid",
                "area_orgname",
                "area_orgid",
                "sale_org_name",
                "sale_org_id",
                "policy_no",
                "center_risk_id",
                "center_risk_name",
                "center_risk_type",
                "long_risk_flag",
                "renew_flag",
                "applicant_name",
                "applicant_id",
                "insured_name",
                "insured_id",
                "premium",
                "amount",
                "insured_during_flag",
                "insured_during",
                "pay_fee_type",
                "pay_type",
                "pay_fee_year",
                "risk_year",
                "fee_ratio",
                "new_paper_fee",
                "ins_validate",
                "insure_date",
                "paper_date",
                "cancel_date",
                "visit_date",
                "owner_name",
                "owner_no",
                "agent_fee_rate",
                "agent_fee",
                "endor_no",
                "endor_date",
                "endor_valid",
                "change_nu",
                "settled_time"*/
                "ser_no",
                "province_orgname",
                "province_orgid",
                "city_orgname",
                "city_orgid",
                "area_orgname",
                "area_orgid",
                "sale_org_name",
                "channel_no",
                /*"sale_org_id",*/
               /* "settled_date",*/
                "policy_no",
                "center_risk_id",
                "center_risk_name",
                "center_risk_type",
                "long_risk_flag",
                /*"renew_flag",*/
                "applicant_name",
                "applicant_id",
                "insured_name",
                "insured_id",
                "premium",
                "amount",
                "insured_during_flag",
                "insured_during",
                "pay_fee_type",
                "pay_type",
                "pay_fee_year",
                "risk_year",
                "fee_ratio",
                "new_paper_fee",
                "ins_validate",
                "insure_date",
                "paper_date",
                "cancel_date",
                "visit_date",
                "owner_name",
                "owner_no",
                "agent_fee_rate",
                "agent_fee",
               /* "business_flag",
                "inter_flag",*/
                "endor_no",
                "endor_date",
                "endor_valid",
                /*"org_id",
                "org_name",*/
                "change_nu",
                "settled_time"
        };
        Map<String, List<Object>> resultMap = util.initSheet4Stream(
                excelFile.getInputStream(), new Object(), titles);
        return resultMap;
    }


    // 设置导入保单的信息，取数据使用
    private Map<String, List<Object>> readDataBusiness(MultipartFile excelFile) throws IOException {
        ExcelUtil util = new ExcelUtil();
        // 导入信息对应导入模板的列字段
        String[] titles = new String[]{
                "series_no",//序号
                //保险公司机构
                //"org_id",//销售公司机构
                "policy_no",//保单号码
                "business_flag"//业务类型
        };
        Map<String, List<Object>> resultMap = util.initSheet4Stream(
                excelFile.getInputStream(), new Object(), titles);
        return resultMap;
    }

    // 设置导入保单的信息，取数据使用
    private Map<String, List<Object>> readDataBusinessPerson(MultipartFile excelFile) throws IOException {
        ExcelUtil util = new ExcelUtil();
        // 导入信息对应导入模板的列字段
        String[] titles = new String[]{
                "series_no",//序号
                //保险公司机构
                //"area_orgid",//销售公司机构
                "policy_no",//保单号码
                "business_flag"//业务类型
        };
        Map<String, List<Object>> resultMap = util.initSheet4Stream(
                excelFile.getInputStream(), new Object(), titles);
        return resultMap;
    }


    // 设置导入保单的信息，取数据使用
    private Map<String, List<Object>> readDataInter(MultipartFile excelFile) throws IOException {
        ExcelUtil util = new ExcelUtil();
        // 导入信息对应导入模板的列字段
        String[] titles = new String[]{
                "series_no",//序号
                //保险公司机构
                //"org_id",//销售公司机构
                "policy_no",//保单号码
                "inter_flag"//业务类型
        };
        Map<String, List<Object>> resultMap = util.initSheet4Stream(
                excelFile.getInputStream(), new Object(), titles);
        return resultMap;
    }

    // 设置导入保单的信息，取数据使用
    private Map<String, List<Object>> readDataInterPerson(MultipartFile excelFile) throws IOException {
        ExcelUtil util = new ExcelUtil();
        // 导入信息对应导入模板的列字段
        String[] titles = new String[]{
                "series_no", // 序号
                // 保险公司机构
                //"area_orgid", // 销售公司机构
                "policy_no", // 保单号码
                "inter_flag"// 业务类型
        };
        Map<String, List<Object>> resultMap = util.initSheet4Stream(excelFile.getInputStream(), new Object(), titles);
        return resultMap;
    }


}
