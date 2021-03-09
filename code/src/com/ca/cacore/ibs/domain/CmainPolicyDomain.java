package com.ca.cacore.ibs.domain;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.branch.IBranchDao;
import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.ibs.dao.ICmainPolicyDao;
import com.ca.cacore.ibs.model.vo.CmainPolicyINSPersonVOMModel;
import com.ca.cacore.ibs.model.vo.CmainPolicyINSVOMModel;
import com.ca.cacore.ibs.model.vo.CodeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.webapp.service.ICommonAsynRequestService;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.stringutil.StringUtil;

/**
 * 保单导入
 *
 * @author SUNXM
 * @since: 2014年11月22日
 * @description:
 */
@Service
public class CmainPolicyDomain implements ICmainPolicyDomain {
    @Autowired
    private ICommonAsynRequestService commonAsynRequestService;
    static HashMap<String, String> interMap = null;
    static HashMap<String, String> businessMap = null;
    @Autowired
    private ICmainPolicyDao cmainPolicyDao;
    @Autowired
    private IBranchDao iBranchDao;

    /**
     * 校验导入的数据是否正确
     *
     * @param dataMap
     * @return
     * @description:
     */
    @Override
    public List<String> checkTraPlanInfoIsTrue(Map<String, List<Object>> dataMap) {
        List<String> msgList = new ArrayList<String>();
        int line = 0;

        //证件类型编码
        String certi_codes[] = {"11", "12", "13", "14", "15",
                "16", "17", "21", "22", "23", "24", "29", "31",
                "32", "33", "34", "99"};
        List<String> codes = Arrays.asList(certi_codes);
        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }

            for (int i = 0; i < practice.size(); i++) {
                line++;
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (HashMap<String, Object>) practice.get(i);
                // 获取校验字段
                String policyno = ActionHelper.getNullToStr(map.get("policyno"));//保单号*
                String riskcode = ActionHelper.getNullToStr(map.get("riskcode"));//险种代码*
                String riskname = ActionHelper.getNullToStr(map.get("riskname"));//险种名称*
                String classcode = ActionHelper.getNullToStr(map.get("classcode"));//险类代码*
                String classname = ActionHelper.getNullToStr(map.get("classname"));//险类名称*
                String rtype = ActionHelper.getNullToStr(map.get("rtype"));//保监会分类*
                String product_source = ActionHelper.getNullToStr(map.get("product_source"));//产品来源*
                String ccurno = ActionHelper.getNullToStr(map.get("ccurno"));//币种*
                String netpremium = ActionHelper.getNullToStr(map.get("netpremium"));//保费*
                Object signdate = ActionHelper.getNullToStr(map.get("signdate"));//签单日期*
                Object operatedate = ActionHelper.getNullToStr(map.get("operatedate"));//核保日期*
                Object startdate = ActionHelper.getNullToStr(map.get("startdate"));//起保日期*
                Object enddate = ActionHelper.getNullToStr(map.get("enddate"));//终保日期*
                String sumamount = ActionHelper.getNullToStr(map.get("sumamount"));//保额*
                String stype = ActionHelper.getNullToStr(map.get("stype"));//业务分类
                String frate = ActionHelper.getNullToStr(map.get("frate"));//手续费比例*
                String fnum = ActionHelper.getNullToStr(map.get("fnum"));//手续费金额*
                String applicode = ActionHelper.getNullToStr(map.get("applicode"));//投保人代码*
                String appliname = ActionHelper.getNullToStr(map.get("appliname"));//投保人名称*
                String insuredcode = ActionHelper.getNullToStr(map.get("insuredcode"));//被保险人代码*
                String insuredname = ActionHelper.getNullToStr(map.get("insuredname"));//被保险人名称*
                String handlercode = ActionHelper.getNullToStr(map.get("handlercode"));//中介员代码(中介公司)*
                String handlername = ActionHelper.getNullToStr(map.get("handlername"));//中介员名称(中介公司)*
                String car_no = ActionHelper.getNullToStr(map.get("car_no"));//车牌号

                String newrenewalflag = ActionHelper.getNullToStr(map.get("renewalflag"));//续保标志*
                String renewalflag[] = newrenewalflag.split("_");


                Object dcoldte = ActionHelper.getNullToStr(map.get("dcoldte"));//实收付日期*
                String paymode = ActionHelper.getNullToStr(map.get("paymode"));//缴费方式
                String customer_id = ActionHelper.getNullToStr(map.get("customer_id"));//客户编号*
                String name = ActionHelper.getNullToStr(map.get("name"));//客户名称*

                String newcustomer_type = ActionHelper.getNullToStr(map.get("customer_type"));//客户类型
                String customer_type[] = newcustomer_type.split("_");

                String newcerti_type = ActionHelper.getNullToStr(map.get("certi_type"));//客户证件类型*
                String certi_type[] = newcerti_type.split("_");

                String certi_no = ActionHelper.getNullToStr(map.get("certi_no"));//客户证件号码*

                String newgender = ActionHelper.getNullToStr(map.get("gender"));//客户性别
                String gender[] = newgender.split("_");

                Object birthday = ActionHelper.getNullToStr(map.get("birthday"));//客户出生日期
                String mobile = ActionHelper.getNullToStr(map.get("mobile"));//客户手机号
                String telphone = ActionHelper.getNullToStr(map.get("telphone"));//客户家庭电话号
                String company_telphone = ActionHelper.getNullToStr(map.get("company_telphone"));//客户办公电话号
                String address = ActionHelper.getNullToStr(map.get("address"));//客户地址

                //校验
                if ("".equals(policyno.trim())) {
                    msgList.add("第" + (i + 2) + "行，保单号不能为空，请核对！");
                } else if (StringUtil.getBytesLength(policyno.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，保单号长度超出数据库设计范围，请核对！");
                }
                if ("".equals(riskcode.trim())) {
                    msgList.add("第" + (i + 2) + "行，险种代码不能为空，请核对！");
                } else if (StringUtil.getBytesLength(riskcode.trim()) > 6) {
                    msgList.add("第" + (i + 2) + "行，险种代码长度超出数据库设计范围，请核对！");
                }
                if ("".equals(riskname.trim())) {
                    msgList.add("第" + (i + 2) + "行，险种名称不能为空，请核对！");
                } else if (StringUtil.getBytesLength(riskname.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，险种名称长度超出数据库设计范围，请核对！");
                }
                if ("".equals(classcode.trim())) {
                    msgList.add("第" + (i + 2) + "行，险类代码不能为空，请核对！");
                } else if (StringUtil.getBytesLength(classcode.trim()) > 4) {
                    msgList.add("第" + (i + 2) + "行，险类代码长度超出数据库设计范围，请核对！");
                }
                if ("".equals(classname.trim())) {
                    msgList.add("第" + (i + 2) + "行，险类名称不能为空，请核对！");
                } else if (StringUtil.getBytesLength(classname.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，险类名称长度超出数据库设计范围，请核对！");
                }
                if ("".equals(rtype.trim())) {
                    msgList.add("第" + (i + 2) + "行，保监会分类不能为空，请核对！");
                } else if (StringUtil.getBytesLength(rtype.trim()) > 20) {
                    msgList.add("第" + (i + 2) + "行，保监会分类长度超出数据库设计范围，请核对！");
                }

                if ("".equals(product_source.trim())) {
                    msgList.add("第" + (i + 2) + "行，产品来源不能为空，请核对！");
                } else if (StringUtil.getBytesLength(product_source.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，产品来源长度超出数据库设计范围，请核对！");
                }

                if ("".equals(ccurno.trim())) {
                    msgList.add("第" + (i + 2) + "行，币种不能为空，请核对！");
                } else if (StringUtil.getBytesLength(ccurno.trim()) > 4) {
                    msgList.add("第" + (i + 2) + "行，币种长度超出数据库设计范围，请核对！");
                }
                if ("".equals(netpremium.trim())) {
                    msgList.add("第" + (i + 2) + "行，保费不能为空，请核对！");
                } else {
                    try {
                        ActionHelper.getNullToDouble(netpremium);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，保费请输入数字，请核对！");
                    }
                }
                if ("".equals(signdate)) {
                    msgList.add("第" + (i + 2) + "行，签单日期不能为空，请核对！");
                } else {
                    try {
                        DateUtil.string2Date(signdate);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，签单日期格式不正确，请核对。");
                    }
                }
                if ("".equals(operatedate)) {
                    msgList.add("第" + (i + 2) + "行，核保日期不能为空，请核对！");
                } else {
                    try {
                        DateUtil.string2Date(operatedate);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，核保日期格式不正确，请核对。");
                    }
                }

                boolean flag = true; // 判断起始、终止日期格是否正确
                if ("".equals(startdate)) {
                    msgList.add("第" + (i + 2) + "行，起保日期不能为空，请核对！");
                    flag = false;
                } else {
                    try {
                        DateUtil.string2Date(startdate);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，起保日期格式不正确，请核对。");
                        flag = false;
                    }
                }
                if ("".equals(enddate)) {
                    msgList.add("第" + (i + 2) + "行，终保日期不能为空，请核对！");
                    flag = false;
                } else {
                    try {
                        Date string2Date = DateUtil.string2Date(enddate);
                        System.out.println(string2Date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，终保日期格式不正确，请核对。");
                        flag = false;
                    }
                }

                if (flag) {
                    if (DateUtil.string2Date(startdate).getTime() > DateUtil.string2Date(enddate).getTime()) {
                        msgList.add("第" + (i + 2) + "行，终保日期应晚于起保日期，请核对。");
                    }
                }


                if ("".equals(sumamount.trim())) {
                    msgList.add("第" + (i + 2) + "行，保额不能为空，请核对！");
                } else {
                    try {
                        ActionHelper.getNullToDouble(sumamount);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，保额请输入数字，请核对！");
                    }
                }
                if (StringUtil.getBytesLength(stype.trim()) > 10) {
                    msgList.add("第" + (i + 2) + "行，业务分类长度超出数据库设计范围，请核对！");
                }
                if ("".equals(frate.trim())) {
                    msgList.add("第" + (i + 2) + "行，手续费比例不能为空，请核对！");
                } else {
                    try {
                        ActionHelper.getNullToDouble(frate);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，手续费比例请输入数字，请核对！");
                    }
                }
                if ("".equals(fnum.trim())) {
                    msgList.add("第" + (i + 2) + "行，手续费金额不能为空，请核对！");
                } else {
                    try {
                        ActionHelper.getNullToDouble(fnum);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，手续费金额请输入数字，请核对！");
                    }
                }
                if ("".equals(applicode.trim())) {
                    msgList.add("第" + (i + 2) + "行，投保人代码不能为空，请核对！");
                } else if (StringUtil.getBytesLength(applicode.trim()) > 20) {
                    msgList.add("第" + (i + 2) + "行，投保人代码长度超出数据库设计范围，请核对！");
                }
                if ("".equals(appliname.trim())) {
                    msgList.add("第" + (i + 2) + "行，投保人名称不能为空，请核对！");
                } else if (StringUtil.getBytesLength(appliname.trim()) > 150) {
                    msgList.add("第" + (i + 2) + "行，投保人名称长度超出数据库设计范围，请核对！");
                }
                if ("".equals(insuredcode.trim())) {
                    msgList.add("第" + (i + 2) + "行，被保险人代码不能为空，请核对！");
                } else if (StringUtil.getBytesLength(insuredcode.trim()) > 20) {
                    msgList.add("第" + (i + 2) + "行，被保险人代码长度超出数据库设计范围，请核对！");
                }
                if ("".equals(insuredname.trim())) {
                    msgList.add("第" + (i + 2) + "行，被保险人名称不能为空，请核对！");
                } else if (StringUtil.getBytesLength(insuredname.trim()) > 150) {
                    msgList.add("第" + (i + 2) + "行，被保险人名称长度超出数据库设计范围，请核对！");
                }
                Boolean salesIsOk = true;//中介人员基本验证是否通过
                if ("".equals(handlercode.trim())) {
                    msgList.add("第" + (i + 2) + "行，中介员代码不能为空，请核对！");
                    salesIsOk = false;
                } else if (StringUtil.getBytesLength(handlercode.trim()) > 18) {
                    msgList.add("第" + (i + 2) + "行，中介员代码长度超出数据库设计范围，请核对！");
                    salesIsOk = false;
                }
                if ("".equals(handlername.trim())) {
                    msgList.add("第" + (i + 2) + "行，中介员名称不能为空，请核对！");
                    salesIsOk = false;
                } else if (StringUtil.getBytesLength(handlername.trim()) > 18) {
                    msgList.add("第" + (i + 2) + "行，中介员名称长度超出数据库设计范围，请核对！");
                    salesIsOk = false;
                }
                if (salesIsOk) {
                    IPolicyLifePeopleVOModel salesInfo = commonAsynRequestService.getSalesInfo(handlercode.trim());
                    if (salesInfo == null) {
                        msgList.add("第" + (i + 2) + "行，中介员不存在，请核对中介员信息!");
                    } else if (!(CodeTypeConst.SALES_STATUS_NORMAL.equals(salesInfo.getSales_status()) && handlername.trim().equals(salesInfo.getSales_name()))) {
                        msgList.add("第" + (i + 2) + "行，中介员不存在，请核对中介员信息!");
                    }
                }
                if (StringUtil.getBytesLength(car_no.trim()) > 18) {
                    msgList.add("第" + (i + 2) + "行，车牌号长度超出数据库设计范围，请核对！");
                }
                if ("".equals(renewalflag[0].trim())) {
                    msgList.add("第" + (i + 2) + "行，续保标志不能为空，请核对！");
                } else if (!"1".equals(renewalflag[0].trim()) && !"2".equals(renewalflag[0].trim())) {
                    msgList.add("第" + (i + 2) + "行，请录入正确的续保标志，请核对！");
                }
                if ("".equals(dcoldte)) {
                    msgList.add("第" + (i + 2) + "行，实收付日期不能为空，请核对！");
                } else {
                    try {
                        DateUtil.string2Date(dcoldte);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，实收付日期格式不正确，请核对。");
                    }
                }
                if (StringUtil.getBytesLength(paymode.trim()) > 10) {
                    msgList.add("第" + (i + 2) + "行，缴费方式长度超出数据库设计范围，请核对！");
                }
                if ("".equals(customer_id.trim())) {
                    msgList.add("第" + (i + 2) + "行，客户编号不能为空，请核对！");
                } else if (StringUtil.getBytesLength(customer_id.trim()) > 15) {
                    msgList.add("第" + (i + 2) + "行，客户编号长度超出数据库设计范围，请核对！");
                }
                if ("".equals(name.trim())) {
                    msgList.add("第" + (i + 2) + "行，客户名称不能为空，请核对！");
                } else if (StringUtil.getBytesLength(name.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，客户名称长度超出数据库设计范围，请核对！");
                }
                if ("".equals(customer_type[0].trim())) {
                    msgList.add("第" + (i + 2) + "行，客户类型不能为空，请核对！");
                } else if (!"".equals(customer_type[0].trim()) && "0".equals(customer_type[0].trim()) && "1".equals(customer_type[0].trim())) {
                    msgList.add("第" + (i + 2) + "行，客户类型请填入正确的编码，请核对！");
                }

                if ("".equals(certi_type[0].trim())) {
                    msgList.add("第" + (i + 2) + "行，客户证件类型不能为空，请核对！");
                } else if (!codes.contains(certi_type[0])) {
                    msgList.add("第" + (i + 2) + "行，客户证件类型请输入正确的类型编码，请核对！");
                }
                if ("".equals(certi_no.trim())) {
                    msgList.add("第" + (i + 2) + "行，客户证件号码不能为空，请核对！");
                } else if (StringUtil.getBytesLength(certi_no.trim()) > 50) {
                    msgList.add("第" + (i + 2) + "行，客户证件号码长度超出数据库设计范围，请核对！");
                } else {
                    if ("11".equals(certi_type[0].trim())) {
                        if (!ValidateHelper.isCheckCnId(certi_no.trim())) {
                            msgList.add("第" + (i + 2) + "行，客户身份证号码填写不正确，请核对！");
                        }
                    }
                }
                if (!"".equals(gender[0].trim())) {
                    if (!"1".equals(gender[0].trim()) && !"2".equals(gender[0].trim())) {
                        msgList.add("第" + (i + 2) + "行，客户性别请填入正确编码，请核对！");
                    }
                }
                if (!"".equals(birthday)) {
                    try {
                        DateUtil.string2Date(birthday);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，出生日期格式不正确，请核对。");
                    }
                }
                if (!"".equals(mobile.trim())) {
                    if (!ValidateHelper.IsCheckTel(mobile.trim())) {
                        msgList.add("第" + (i + 2) + "行，客户手机号填写不正确，请核对。");
                    }
                }
                if (!"".equals(telphone.trim())) {
                    if (!ValidateHelper.IsCheckTel(telphone.trim())) {
                        msgList.add("第" + (i + 2) + "行，客户家庭电话号填写不正确，请核对。");
                    }
                }
                if (!"".equals(company_telphone.trim())) {
                    if (!ValidateHelper.IsCheckTel(company_telphone.trim())) {
                        msgList.add("第" + (i + 2) + "行，客户办公电话号填写不正确，请核对。");
                    }
                }
                if (StringUtil.getBytesLength(address.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，客户地址长度超出数据库设计范围，请核对。");
                }

            }

        }
        if (line == 0) {
            msgList.add("excel中数据为空，请重新录入。");
        }
        return msgList;
    }


    /**
     * 校验导入的数据是否正确
     *
     * @param dataMap
     * @return
     * @description:
     */
    @Override
    public Map<String, List<String>> checkTraPlanInfoIsTrue2(Map<String, List<Object>> dataMap) {
        List<String> msgList = new ArrayList<String>();
        List<String> repeatmsg = new ArrayList<String>();

        Map<String, List<String>> msg = new HashMap();
        int line = 0;
        HashMap<String, String> check_cpy_branch = new HashMap<String, String>();
        HashMap<String, String> check_sys_branch = new HashMap<String, String>();

        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {

            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { //如果长度小于1 则continue
                continue;
            }

            for (int i = 0; i < practice.size(); i++) {
                System.out.println(practice.get(i));
                line++;
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (HashMap<String, Object>) practice.get(i);
                // 获取校验字段
                //保批单公共校验                                              空对象变为空字符串
                String policy_no = ActionHelper.getNullToStr(map.get("policy_no"));// 保单号
                String province_orgname = ActionHelper.getNullToStr(map.get("province_orgname"));//省级机构名称
                String province_orgid = ActionHelper.getNullToStr(map.get("province_orgid"));//省级机构代码
                String city_orgname = ActionHelper.getNullToStr(map.get("city_orgname"));//地市机构名称
                String city_orgid = ActionHelper.getNullToStr(map.get("city_orgid"));//地市机构  代码
                String area_orgname = ActionHelper.getNullToStr(map.get("area_orgname"));//区县机构名称
                String area_orgid = ActionHelper.getNullToStr(map.get("area_orgid")); //区县机构代码
                //String org_id = ActionHelper.getNullToStr(map.get("org_id"));// 归属部门代码
                //String org_name = ActionHelper.getNullToStr(map.get("org_name"));//归属部门名称
                String insurance_type_no = ActionHelper.getNullToStr(map.get("insurance_type_no"));// 险种代码
                String insured_name = ActionHelper.getNullToStr(map.get("insured_name"));//被保险人姓名
                String endor_no = ActionHelper.getNullToStr(map.get("endor_no"));// 批单号
                CmainPolicyINSVOMModel policy = new CmainPolicyINSVOMModel();
                policy.setPolicy_no(policy_no);
                policy.setEndor_no(endor_no);

				/*List<CmainPolicyINSVOMModel> policymodel=cmainPolicyDao.queryCmainPolicyINSVOMModel(policy);
				if(policymodel!=null){
					//repeatmsg.add("第" + (i + 2) + "行，该保单号数据在数据库中已存在");
				}*/

                // 校验
                if ("".equals(policy_no.trim())) {
                    msgList.add("第" + (i + 2) + "行，保单号不能为空，请核对！");
                } else if (StringUtil.getBytesLength(policy_no.trim()) > 80) {
                    msgList.add("第" + (i + 2) + "行，保单号长度超出数据库设计范围，请核对！");
                }

                String amount = ActionHelper.getNullToStr(map.get("amount"));
                if ("".equals(amount.trim())) {
                    msgList.add("第" + (i + 2) + "行，保额不能为空，请核对！");
                } else
                    try {
                        ActionHelper.getNullToDouble(amount);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，保额 不符合数字格式，请核对！");
                    }

                String premium_tax = ActionHelper.getNullToStr(map.get("premium_tax"));
                if ("".equals(premium_tax.trim())) {
                    msgList.add("第" + (i + 2) + "行，保费增值税不能为空，请核对！");
                } else
                    try {
                        ActionHelper.getNullToDouble(premium_tax);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，保费增值税 不符合数字格式，请核对！");
                    }

                String premium = ActionHelper.getNullToStr(map.get("premium"));
                if ("".equals(premium.trim())) {
                    msgList.add("第" + (i + 2) + "行，保费不能为空，请核对！");
                } else
                    try {
                        ActionHelper.getNullToDouble(premium);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，保费请输入数字，请核对！");
                    }

                String net_premium = ActionHelper.getNullToStr(map.get("net_premium"));
                if ("".equals(net_premium.trim())) {
                    msgList.add("第" + (i + 2) + "行，净保费（不含税）不能为空，请核对！");
                } else
                    try {
                        ActionHelper.getNullToDouble(net_premium);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，净保费（不含税） 不符合数字格式，请核对！");
                    }

                String begin_date = ActionHelper.getNullToStr(map.get("begin_date"));
                if ("".equals(begin_date.trim())) {
                    msgList.add("第" + (i + 2) + "行，起保日期不能为空，请核对！");
                } else
                    try {
                        DateUtil.string2Date(begin_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，起保日期 格式不正确，请核对！");
                    }

                String end_date = ActionHelper.getNullToStr(map.get("end_date"));
                if ("".equals(end_date.trim())) {
                    msgList.add("第" + (i + 2) + "行，终保日期不能为空，请核对！");
                } else
                    try {
                        DateUtil.string2Date(end_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，终保日期 格式不正确，请核对！");
                    }

                String fee_ratio = ActionHelper.getNullToStr(map.get("fee_ratio"));
                if ("".equals(fee_ratio.trim())) {
                    msgList.add("第" + (i + 2) + "行，跟单手续费比例不能为空，请核对！");
                } else
                    try {
                        ActionHelper.getNullToDouble(fee_ratio);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，跟单手续费比例不符合数字格式，请核对！");
                    }

                String fee = ActionHelper.getNullToStr(map.get("fee"));
                if ("".equals(fee.trim())) {
                    msgList.add("第" + (i + 2) + "行，跟单手续费不能为空，请核对！");
                } else
                    try {
                        ActionHelper.getNullToDouble(fee);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，跟单手续费请输入数字，请核对！");
                    }

                String settle_time = ActionHelper.getNullToStr(map.get("settle_time"));
                if ("".equals(settle_time.trim())) {
                    msgList.add("第" + (i + 2) + "行，结算日期不能为空，请核对！");
                } else
                    try {
                        DateUtil.string2Date(settle_time);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，结算日期格式不正确，请核对。");
                    }

                if (endor_no == null || "".equals(endor_no)) {
                    if ("".equals(province_orgname.trim())) {
                        msgList.add("第" + (i + 2) + "行，省级机构名称不能为空，请核对！");
                    }
                    if ("".equals(province_orgid.trim())) {
                        msgList.add("第" + (i + 2) + "行，省级机构代码不能为空，请核对！");
                    }
                    if ("".equals(city_orgname.trim())) {
                        msgList.add("第" + (i + 2) + "行，地市机构名称不能为空，请核对！");
                    }
                    if ("".equals(city_orgid.trim())) {
                        msgList.add("第" + (i + 2) + "行，地市机构代码不能为空，请核对！");
                    }
                    if ("".equals(area_orgid.trim())) {
                        msgList.add("第" + (i + 2) + "行，区县机构代码不能为空，请核对！");
                    }
                    if ("".equals(area_orgname.trim())) {
                        msgList.add("第" + (i + 2) + "行，区县机构名称不能为空，请核对！");
                    }
					/*if ("".equals(org_id.trim())) {
						msgList.add("第" + (i + 2) + "行，归属部门代码不能为空，请核对！");
					} 
					if ("".equals(org_name.trim())) {
						msgList.add("第" + (i + 2) + "行，归属部门名称不能为空，请核对！");
					}*/
                    if ("".equals(insurance_type_no.trim())) {
                        msgList.add("第" + (i + 2) + "行，险种代码不能为空，请核对！");
                    }
                    if ("".equals(insured_name.trim())) {
                        msgList.add("第" + (i + 2) + "行，被保险人姓名 不能为空，请核对！");
                    }
                } else {
                    if (StringUtil.getBytesLength(endor_no.trim()) > 30) {
                        msgList.add("第" + (i + 2) + "行，批单号  超出数据库设计范围，请核对！");
                    }
                    String change_nu = ActionHelper.getNullToStr(map.get("change_nu"));
                    if (StringUtil.getBytesLength(change_nu.trim()) > 0) {
                        try {
                            Integer.parseInt(change_nu);
                        } catch (NumberFormatException e) {
                            msgList.add("第" + (i + 2) + "行，数量变化和数据库设计不符合，请核对！");
                            e.printStackTrace();
                        }
                    } else {
                        msgList.add("第" + (i + 2) + "行，数量变化不能为空，请核对！");
                    }
                    String approval_date = ActionHelper.getNullToStr(map.get("approval_date"));
                    if ("".equals(approval_date.trim())) {
                        msgList.add("第" + (i + 2) + "行，批改日期不能为空，请核对！");
                    } else
                        try {
                            DateUtil.string2Date(approval_date);
                        } catch (Exception e) {
                            msgList.add("第" + (i + 2) + "行，批改日期 格式不正确，请核对。");
                        }

                    String ins_validate1 = ActionHelper.getNullToStr(map.get("ins_validate"));
                    if ("".equals(ins_validate1.trim())) {
                        msgList.add("第" + (i + 2) + "行，批改生效日期不能为空，请核对！");
                    } else
                        try {
                            DateUtil.string2Date(ins_validate1);
                        } catch (Exception e) {
                            msgList.add("第" + (i + 2) + "行，批改生效日期 格式不正确，请核对。");
                        }
                }
                if (StringUtil.getBytesLength(province_orgname.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，省级机构 名称 超出数据库设计范围，请核对！");
                }
                if (StringUtil.getBytesLength(province_orgid.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，省级机构 代码  超出数据库设计范围，请核对！");
                }
                if ((province_orgid != null && !"".equals(province_orgid)) || (province_orgname != null && !"".equals(province_orgname))) {
                    if (!check_cpy_branch(check_cpy_branch, province_orgid, province_orgname)) {
                        msgList.add("第" + (i + 2) + "行，省级机构 名称和代码  和数据库不匹配，请核对！");
                    }
                }
                if (StringUtil.getBytesLength(city_orgname.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，地市机构名称  超出数据库设计范围，请核对！");
                }
                if (StringUtil.getBytesLength(city_orgid.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，地市机构代码  超出数据库设计范围，请核对！");
                }
                if ((city_orgid != null && !"".equals(city_orgid)) || (city_orgname != null && !"".equals(city_orgname))) {
                    if (!check_cpy_branch(check_cpy_branch, city_orgid, city_orgname)) {
                        msgList.add("第" + (i + 2) + "行，地市机构 名称和代码  和数据库不匹配，请核对！");
                    }
                }
                if (StringUtil.getBytesLength(area_orgid.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，区县机构代码  超出数据库设计范围，请核对！");
                }
                if (StringUtil.getBytesLength(area_orgname.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，区县机构名称  超出数据库设计范围，请核对！");
                }
                if ((area_orgid != null && !"".equals(area_orgid)) || (area_orgname != null && !"".equals(area_orgname))) {
                    if (!check_cpy_branch(check_cpy_branch, area_orgid, area_orgname)) {
                        msgList.add("第" + (i + 2) + "行，区县机构 名称和代码  和数据库不匹配，请核对！");
                    }
                }
				/*if (StringUtil.getBytesLength(org_id.trim()) > 100) {
					msgList.add("第" + (i + 2) + "行，归属部门代码 长度超出数据库设计范围，请核对！");
				}
				if (StringUtil.getBytesLength(org_name.trim()) > 80) {
					msgList.add("第" + (i + 2) + "行，归属部门名称  超出数据库设计范围，请核对！");
				}
				if((org_id!=null  && !"".equals(org_id))||(org_name!= null &&!"".equals(org_name))){
					if (!check_cpy_branch(check_cpy_branch, org_id, org_name)) {
						msgList.add("第" + (i + 2) + "行，归属部门 名称和代码  和数据库不匹配，请核对！");
					}
				}*/
                if (StringUtil.getBytesLength(insurance_type_no.trim()) > 80) {
                    msgList.add("第" + (i + 2) + "行， 险种代码长度超出数据库设计范围，请核对！");
                }
                if (StringUtil.getBytesLength(insured_name.trim()) > 120) {
                    msgList.add("第" + (i + 2) + "行，被保险人姓名 长度超出数据库设计范围，请核对！");
                }
                String vehicel_tax = ActionHelper.getNullToStr(map.get("vehicel_tax"));
                if ("".equals(vehicel_tax.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(vehicel_tax);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，车船税不符合数字格式，请核对！");
                    }
                String ord_id = ActionHelper.getNullToStr(map.get("ord_id"));// 中介公司机构
                // 校验
				/*if ("".equals(ord_id.trim())) {
					msgList.add("第" + (i + 2) + "行， 中介公司机构不能为空，请核对！");
				} else */
                if (StringUtil.getBytesLength(ord_id.trim()) > 80) {
                    msgList.add("第" + (i + 2) + "行， 中介公司机构长度超出数据库设计范围，请核对！");
                }

				/*String repair_coding = ActionHelper.getNullToStr(map.get("repair_coding"));// 推荐维修码
				// 校验
				if (StringUtil.getBytesLength(repair_coding.trim()) > 80) {
					msgList.add("第" + (i + 2) + "行， 推荐维修码长度超出数据库设计范围，请核对！");
				}*/
				/*String car_branchname = ActionHelper.getNullToStr(map.get("car_branchname"));// 代理名称
				// 校验
				if (StringUtil.getBytesLength(car_branchname.trim()) > 255) {
					msgList.add("第" + (i + 2) + "行， 车行名称长度超出数据库设计范围，请核对！");
				}*/
                String lpn = ActionHelper.getNullToStr(map.get("lpn"));
                // 校验
                if (StringUtil.getBytesLength(lpn.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，车牌号 长度超出数据库设计范围，请核对！");
                }
                String car_name = ActionHelper.getNullToStr(map.get("car_name"));
                // 校验
                if (StringUtil.getBytesLength(car_name.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，车型名称 长度超出数据库设计范围，请核对！");
                }
                String vin = ActionHelper.getNullToStr(map.get("vin"));
                // 校验
                if (StringUtil.getBytesLength(vin.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，车架号（vin码） 超出数据库设计范围，请核对！");
                }
                String first_date = ActionHelper.getNullToStr(map.get("first_date"));
                if ("".equals(first_date.trim())) {

                } else
                    try {
                        DateUtil.string2Date(first_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，初登日期 格式不正确，请核对。");
                    }
                String insured_phone = ActionHelper.getNullToStr(map.get("insured_phone"));
                // 校验
                if (StringUtil.getBytesLength(insured_phone.trim()) > 50) {
                    msgList.add("第" + (i + 2) + "行，被保险人手机 超出数据库设计范围，请核对！");
                }
                String sign_date = ActionHelper.getNullToStr(map.get("sign_date"));
                if ("".equals(sign_date.trim())) {

                } else
                    try {
                        DateUtil.string2Date(sign_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，签单日期格式不正确，请核对。");
                    }
                businessMap = this.getBusinessMap();
                //String business_flag   =  ActionHelper.getNullToStr(map.get("business_flag" ));
				/*if ("".equals(business_flag.trim())) {
					msgList.add("第" + (i + 2) + "行，业务类型不能为空，请核对！");
				} else
				if(!"".equals(business_flag.trim())){
					if (null == businessMap.get(business_flag)) {
						msgList.add("第" + (i + 2) + "行，无此业务类型！");
					}
				}
				map.put("business_flag", businessMap.get(business_flag));*/

                interMap = getInterMap();
                //String inter_flag =  ActionHelper.getNullToStr(map.get("inter_flag"));
				/*if ("".equals(inter_flag.trim())) {
					msgList.add("第" + (i + 2) + "行，互联网标识不能为空，请核对！");
				} else */
				/*if(!"".equals(inter_flag.trim())){
					if (null == interMap.get(inter_flag)) {
						msgList.add("第" + (i + 2) + "行，无此互联网标识！");
					}
					
				}
				map.put("inter_flag", interMap.get(inter_flag));
*/
				/*String person_name = ActionHelper.getNullToStr(map.get("person_name"));
				if (StringUtil.getBytesLength(person_name.trim()) > 50) {
					msgList.add("第" + (i + 2) + "行，归属人姓名  超出数据库设计范围，请核对！");
				}
				
				String person_no = ActionHelper.getNullToStr(map.get("person_no"));
				if (StringUtil.getBytesLength(person_no.trim()) > 30) {
					msgList.add("第" + (i + 2) + "行，归属人工号  超出数据库设计范围，请核对！");
				}
				*/
                String responsible_no = ActionHelper.getNullToStr(map.get("responsible_no"));
                if (StringUtil.getBytesLength(responsible_no.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，经办人工号  超出数据库设计范围，请核对！");
                }

                String responsible_name = ActionHelper.getNullToStr(map.get("responsible_name"));
                if (StringUtil.getBytesLength(responsible_name.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，经办人姓名  超出数据库设计范围，请核对！");
                }

                String channel_no = ActionHelper.getNullToStr(map.get("channel_no"));
                if (StringUtil.getBytesLength(channel_no.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，渠道代码  超出数据库设计范围，请核对！");
                }

                String insurance_class_name = ActionHelper.getNullToStr(map.get("insurance_class_name"));
                if (StringUtil.getBytesLength(insurance_class_name.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，险类名称  超出数据库设计范围，请核对！");
                }

                String insurance_class_no = ActionHelper.getNullToStr(map.get("insurance_class_no"));
                if (StringUtil.getBytesLength(insurance_class_no.trim()) > 300) {
                    msgList.add("第" + (i + 2) + "行，险类代码  超出数据库设计范围，请核对！");
                }

                String insurance_type_name = ActionHelper.getNullToStr(map.get("insurance_type_name"));
                if (StringUtil.getBytesLength(insurance_type_name.trim()) > 300) {
                    msgList.add("第" + (i + 2) + "行，险种名称  超出数据库设计范围，请核对！");
                }

                String insure_no = ActionHelper.getNullToStr(map.get("insure_no"));
                if (StringUtil.getBytesLength(insure_no.trim()) > 80) {
                    msgList.add("第" + (i + 2) + "行，投保单号  超出数据库设计范围，请核对！");
                }

                String applicant_name = ActionHelper.getNullToStr(map.get("applicant_name"));
                if (StringUtil.getBytesLength(applicant_name.trim()) > 120) {
                    msgList.add("第" + (i + 2) + "行，投保人姓名 超出数据库设计范围，请核对！");
                }

                String insured_cid = ActionHelper.getNullToStr(map.get("insured_cid"));
                if (StringUtil.getBytesLength(insured_cid.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，被保险人身份证  超出数据库设计范围，请核对！");
                }

                String insured_company_type = ActionHelper.getNullToStr(map.get("insured_company_type"));
                if (StringUtil.getBytesLength(insured_company_type.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，被保险人单位性质  超出数据库设计范围，请核对！");
                }

                String insured_papertype = ActionHelper.getNullToStr(map.get("insured_papertype"));
                if (StringUtil.getBytesLength(insured_papertype.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，被保险人证件类型（代码/名称）  超出数据库设计范围，请核对！");
                }
                String insured_add = ActionHelper.getNullToStr(map.get("insured_add"));
                if (StringUtil.getBytesLength(insured_add.trim()) > 255) {
                    msgList.add("第" + (i + 2) + "行，被保险人地址  超出数据库设计范围，请核对！");
                }
                String insured_mailbox = ActionHelper.getNullToStr(map.get("insured_mailbox"));
                if (StringUtil.getBytesLength(insured_mailbox.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，被保险人邮箱  超出数据库设计范围，请核对！");
                }
                String insured_tel = ActionHelper.getNullToStr(map.get("insured_tel"));
                if (StringUtil.getBytesLength(insured_tel.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，被保险人固话  超出数据库设计范围，请核对！");
                }
                String car_type = ActionHelper.getNullToStr(map.get("car_type"));
                if (StringUtil.getBytesLength(car_type.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，车型编码  超出数据库设计范围，请核对！");
                }
                String engine_no = ActionHelper.getNullToStr(map.get("engine_no"));
                if (StringUtil.getBytesLength(engine_no.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，发动机号  超出数据库设计范围，请核对！");
                }
                String car_class = ActionHelper.getNullToStr(map.get("car_class"));
                if (StringUtil.getBytesLength(car_class.trim()) > 120) {
                    msgList.add("第" + (i + 2) + "行，车辆种类  超出数据库设计范围，请核对！");
                }
                String use_type = ActionHelper.getNullToStr(map.get("use_type"));
                if (StringUtil.getBytesLength(use_type.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，使用性质  超出数据库设计范围，请核对！");
                }
                String clause_type = ActionHelper.getNullToStr(map.get("clause_type"));
                if (StringUtil.getBytesLength(clause_type.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，条款类型  超出数据库设计范围，请核对！");
                }

                String check_date = ActionHelper.getNullToStr(map.get("check_date"));
                if ("".equals(check_date.trim())) {
                } else
                    try {
                        DateUtil.string2Date(check_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，核保日期 格式不正确，请核对。");
                    }

                String approval_flag = ActionHelper.getNullToStr(map.get("approval_flag"));
                if (StringUtil.getBytesLength(approval_flag.trim()) > 8) {
                    msgList.add("第" + (i + 2) + "行，新/续保标志  超出数据库设计范围，请核对！");
                }
                String print_date = ActionHelper.getNullToStr(map.get("print_date"));
                if ("".equals(print_date.trim())) {
                } else
                    try {
                        DateUtil.string2Date(print_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，保单打印日期  格式不正确，请核对。");
                    }

                String ins_validate = ActionHelper.getNullToStr(map.get("ins_validate"));
                if ("".equals(ins_validate.trim())) {
                } else
                    try {
                        DateUtil.string2Date(ins_validate);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，保单生效日期（起报日期核保日期取大的）  格式不正确，请核对。");
                    }

                String owner_name = ActionHelper.getNullToStr(map.get("owner_name"));
                if (StringUtil.getBytesLength(owner_name.trim()) > 50) {
                    msgList.add("第" + (i + 2) + "行，中介公司归属人员姓名  超出数据库设计范围，请核对！");
                }
                String owner_no = ActionHelper.getNullToStr(map.get("owner_no"));
                if (StringUtil.getBytesLength(owner_no.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，中介公司归属人员编码  超出数据库设计范围，请核对！");
                }
                if (!"".equals(owner_name) || !"".equals(owner_no)) {
                    CmainPolicyINSPersonVOMModel model = new CmainPolicyINSPersonVOMModel();
                    model.setChannel_no(channel_no);
                    model.setOwner_name(owner_name);
                    model.setOwner_no(owner_no);
                    List<CmainPolicyINSPersonVOMModel> retList = cmainPolicyDao.queryPerson(model);
                    boolean valid = false;
                    for (int j = 0; j < retList.size(); j++) {
                        if (owner_name.equals(retList.get(j).getOwner_name())) {
                            if (owner_no.equals(retList.get(j).getOwner_no())) {
                                valid = true;
                            }
                        }
                    }
                    if (!valid) {
                        msgList.add("第" + (i + 2) + "行，在所属机构中没有找到对应的中介公司归属人员或中介公司归属人员工号和姓名不匹配");
                        //return msgList;
                    }
                }

                String wei_rate = ActionHelper.getNullToStr(map.get("wei_rate"));
                if ("".equals(wei_rate.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(wei_rate);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，代理人佣金支付比例   请输入数字，请核对！");
                    }
                if (StringUtil.getBytesLength(wei_rate.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，代理人佣金支付比例  超出数据库设计范围，请核对！");
                }

                String commission = ActionHelper.getNullToStr(map.get("commission"));
                if ("".equals(commission.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(commission);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，代理人佣金金额 请输入数字，请核对！");
                    }
                if (StringUtil.getBytesLength(commission.trim()) > 30) {
                    msgList.add("第" + (i + 2) + "行，代理人佣金金额  超出数据库设计范围，请核对！");
                }

                if (null != ord_id && !"".equals(ord_id) && null != channel_no && !"".equals(channel_no)) {
                    IBranchModel iBranchModel = cmainPolicyDao.queryBranchById(ord_id);
                    if (iBranchModel == null || null == iBranchModel.getChannelcode() || "".equals(iBranchModel.getChannelcode())) {
                        msgList.add("第" + (i + 2) + "行， 根据中介公司id没有找到渠道代码，请核实，请核对！");
                    } else {
                        if (!channel_no.equals(iBranchModel.getChannelcode())) {
                            msgList.add("第" + (i + 2) + "行， 根据中介公司id和渠道代码不匹配，请核对！");
                        }
                    }
                    map.put("channel_no", iBranchModel.getChannelcode());
                }
				
				/*String settled_date = ActionHelper.getNullToStr(map.get("settled_date"));
				if (StringUtil.getBytesLength(settled_date.trim()) > 7) {
					msgList.add("第" + (i + 2) + "行，分单日期（年月，例如：2019-05）  超出数据库设计范围，请核对！");
				}*/
				
				/*if ("".equals(settled_date.trim())) {
				}else{
					try {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
						java.util.Date settled_date_date = format.parse(settled_date);
						if (new java.util.Date().getTime() <= settled_date_date.getTime()) {
							msgList.add("第" + (i + 2) + "行，分单日期  结算日期不能大于当前日期，请核对。");
						}
						if("".equals(owner_no.trim())){
							msgList.add("第" + (i + 2) + "行，有分单时间,中介公司归属人员编码不能为空，请核对！");
						}
						if("".equals(owner_name.trim())){
							msgList.add("第" + (i + 2) + "行，有分单时间,中介公司归属人员姓名不能为空，请核对！");
						}
					} catch (Exception e) {
						msgList.add("第" + (i + 2) + "行，分单日期  格式不正确，请核对。");
					}
				}*/


				/*CmainPolicyINSVOMModel policy=new CmainPolicyINSVOMModel();
				policy.setPolicy_no(policy_no);
				policy.setEndor_no(endor_no);
				
				CmainPolicyINSVOMModel policymodel=cmainPolicyDao.queryCmainPolicyINSVOMModel(policy);
				if(policymodel!=null){
					System.out.println("=========1111111111111========");
					repeatmsgList.add("第" + (i + 2) + "行，该保单号数据在数据库中已存在");
					if(policymodel.getProvince_orgid()!=null&&(province_orgid!=null&&province_orgid!="")){
						if(!policymodel.getProvince_orgid().equals(province_orgid)){
							repeatmsgList.add("第" + (i + 2) + "行，导入的省级机构代码为"+province_orgid+",数据库里省级机构代码为"+policymodel.getProvince_orgid());
						}
					}else if((province_orgid==null||province_orgid=="")&&policymodel.getProvince_orgid()!=null){
						repeatmsgList.add("第" + (i + 2) + "行，导入的省级机构代码为空,数据库里省级机构代码为"+policymodel.getProvince_orgid());
					}else if((province_orgid!=null&&province_orgid!="")&&policymodel.getProvince_orgid()==null){
						repeatmsgList.add("第" + (i + 2) + "行，导入的省级机构代码为"+province_orgid+",数据库里省级机构代码为空");
					}
					
					if(policymodel.getProvince_orgname()!=null&&(province_orgname!=null&&province_orgname!="")){
						if(!policymodel.getProvince_orgname().equals(province_orgname)){
							repeatmsgList.add("第" + (i + 2) + "行，导入的省级机构名称为"+province_orgname+",数据库里省级机构名称为"+policymodel.getProvince_orgname());
						}
					}else if(policymodel.getProvince_orgname()!=null&&(province_orgname==null||province_orgname=="")){
						repeatmsgList.add("第" + (i + 2) + "行，导入的省级机构名称为空,数据库里省级机构名称为"+policymodel.getProvince_orgname());
					}else if((province_orgname!=null&&province_orgname!="")&&policymodel.getProvince_orgname()==null){
						repeatmsgList.add("第" + (i + 2) + "行，导入的省级机构名称为"+province_orgname+",数据库里省级机构名称为空");
					}
					
					if(policymodel.getCity_orgid()!=null&&(city_orgid!=null&&city_orgid!="")){
						if(!policymodel.getCity_orgid().equals(city_orgid)){
							repeatmsgList.add("第" + (i + 2) + "行，导入的市级机构代码为"+city_orgid+",数据库里市级机构代码为"+policymodel.getCity_orgid());
						}
					}else if(policymodel.getCity_orgid()!=null&&(city_orgid==null||city_orgid=="")){
						repeatmsgList.add("第" + (i + 2) + "行，导入的市级机构代码为空,数据库里市级机构代码为"+policymodel.getCity_orgid());
					}else if(policymodel.getCity_orgid()!=null&&(city_orgid!=null&&city_orgid!="")){
						repeatmsgList.add("第" + (i + 2) + "行，导入的市级机构代码为"+city_orgid+",数据库里市级机构代码为空");
					}
					
					if(policymodel.getCity_orgname()!=null&&(city_orgname!=null&&city_orgname!="")){
						if(!policymodel.getCity_orgname().equals(city_orgname)){
							repeatmsgList.add("第" + (i + 2) + "行，导入的市级机构名称为"+city_orgname+",数据库里市级机构名称为"+policymodel.getCity_orgname());
						}
					}else if((city_orgname==null||city_orgname=="")&&policymodel.getCity_orgname()!=null){
						repeatmsgList.add("第" + (i + 2) + "行，导入的市级机构名称为空,数据库里市级机构名称为"+policymodel.getCity_orgname());
					}else if((city_orgname!=null&&city_orgname!="")&&policymodel.getCity_orgname()==null){
						repeatmsgList.add("第" + (i + 2) + "行，导入的市级机构名称为"+city_orgname+",数据库里市级机构名称为空");
					}
					
					if(policymodel.getArea_orgid()!=null&&(area_orgid!=null&&area_orgid!="")){
						if(!policymodel.getArea_orgid().equals(area_orgid)){
							repeatmsgList.add("第" + (i + 2) + "行，导入的区县机构代码为"+area_orgid+",数据库里区县机构代码为"+policymodel.getArea_orgid());
						}
					}else if(policymodel.getArea_orgid()!=null&&(area_orgid==null||area_orgid=="")){
						repeatmsgList.add("第" + (i + 2) + "行，导入的区县机构代码为空,数据库里省级机构代码为"+policymodel.getArea_orgid());
					}else if(policymodel.getArea_orgid()==null&&(area_orgid!=null&&area_orgid!="")){
						repeatmsgList.add("第" + (i + 2) + "行，导入的区县机构代码为"+area_orgid+",数据库里区县机构代码为空");
					}
					
					if(policymodel.getArea_orgname()!=null&&(area_orgname!=null&&area_orgname!="")){
						if(!policymodel.getArea_orgname().equals(area_orgname)){
							repeatmsgList.add("第" + (i + 2) + "行，导入的区县机构名称为"+area_orgname+",数据库里区县机构名称为"+policymodel.getArea_orgname());
						}
					}else if(policymodel.getArea_orgname()!=null&&(area_orgname==null||area_orgname=="")){
						repeatmsgList.add("第" + (i + 2) + "行，导入的区县机构名称为空,数据库里区县机构名称为"+policymodel.getArea_orgname());
					}else if(policymodel.getArea_orgname()==null&&(area_orgname!=null&&area_orgname!="")){
						repeatmsgList.add("第" + (i + 2) + "行，导入的区县机构名称为"+area_orgname+",数据库里区县机构名称为空");
					}
					
					if(policymodel.getOrg_id()!=null&&(org_id!=null&&org_id!="")){
						if(!policymodel.getOrg_id().equals(org_id)){
							repeatmsgList.add("第" + (i + 2) + "行，导入的归属部门代码为"+org_id+",数据库里归属部门代码为"+policymodel.getOrg_id());
						}
					}else if(policymodel.getOrg_id()!=null&&(org_id==null||org_id=="")){
						repeatmsgList.add("第" + (i + 2) + "行，导入的归属部门代码为空,数据库里归属部门代码为"+policymodel.getOrg_id());
					}else if(policymodel.getOrg_id()!=null&&(org_id!=null&&org_id!="")){
						repeatmsgList.add("第" + (i + 2) + "行，导入的归属部门代码为"+org_id+",数据库里归属部门代码为空");
					}
					
					if(policymodel.getOrg_name()!=null&&(org_name!=null&&org_name!="")){
						if(!policymodel.getOrg_name().equals(org_name)){
							msgList.add("第" + (i + 2) + "行，导入的归属部门名称为"+org_name+",数据库里归属部门名称为"+policymodel.getOrg_name());
						}
					}else if(policymodel.getOrg_name()!=null&&(org_name==null||org_name=="")){
						msgList.add("第" + (i + 2) + "行，导入的归属部门名称为空,数据库里归属部门名称为"+policymodel.getOrg_name());
					}else if(policymodel.getOrg_name()==null&&(org_name!=null&&org_name!="")){
						msgList.add("第" + (i + 2) + "行，导入的归属部门名称为"+org_name+",数据库里归属部门名称为空");
					}
					
					if(policymodel.getOrg_name()==null&&(org_name!=null&&org_name!="")){
						if(!policymodel.getPerson_no().equals(person_no)){
							msgList.add("第" + (i + 2) + "行，导入的归属人工号为"+person_no+",数据库里归属人工号为空");
						}
					}else if(policymodel.getOrg_name()==null&&(org_name==null||org_name=="")){
						msgList.add("第" + (i + 2) + "行，导入的归属人工号为空,数据库里归属人工号为"+policymodel.getPerson_no());
					}else if(policymodel.getOrg_name()==null&&(org_name!=null&&org_name!="")){
						msgList.add("第" + (i + 2) + "行，导入的归属人工号为"+person_no+",数据库里归属人工号为空");
					}
					
					if(policymodel.getPerson_name()!=null&&(person_name!=null&&person_name!="")){
						if(!policymodel.getPerson_name().equals(person_name)){
							msgList.add("第" + (i + 2) + "行，导入的归属人姓名为"+person_name+",数据库里归属人姓名为"+policymodel.getPerson_name());
						}
					}else if(policymodel.getPerson_name()!=null&&(person_name==null||person_name=="")){
						msgList.add("第" + (i + 2) + "行，导入的归属人姓名为空,数据库里归属人姓名为"+policymodel.getPerson_name());
					}else if(policymodel.getPerson_name()==null&&(person_name!=null&&person_name!="")){
						msgList.add("第" + (i + 2) + "行，导入的归属人姓名为"+person_name+",数据库里归属人姓名为空");
					}
					
					if(policymodel.getResponsible_no()!=null&&(responsible_no!=null&&responsible_no!="")){
						if(!policymodel.getResponsible_no().equals(responsible_no)){
							msgList.add("第" + (i + 2) + "行，导入的经办人工号为"+responsible_no+",数据库里归属人工号为"+policymodel.getResponsible_no());
						}
					}else if(policymodel.getResponsible_no()!=null&&(responsible_no==null||responsible_no=="")){
						msgList.add("第" + (i + 2) + "行，导入的经办人工号为空,数据库里归属人工号为"+policymodel.getResponsible_no());
					}else if(policymodel.getResponsible_no()==null&&(responsible_no!=null&&responsible_no!="")){
						msgList.add("第" + (i + 2) + "行，导入的经办人工号为"+responsible_no+",数据库里归属人工号为空");
					}
					
					
					if(policymodel.getChannel_no()!=null&&(channel_no!=null&&channel_no!="")){
						if(!policymodel.getChannel_no().equals(channel_no)){
							msgList.add("第" + (i + 2) + "行，导入的渠道代码为"+channel_no+",数据库里渠道代码为"+policymodel.getChannel_no());
						}
					}else if(policymodel.getChannel_no()!=null&&(channel_no==null||channel_no=="")){
						msgList.add("第" + (i + 2) + "行，导入的渠道代码为空,数据库里渠道代码为"+policymodel.getChannel_no());
					}else if(policymodel.getChannel_no()==null&&(channel_no!=null&&channel_no!="")){
						msgList.add("第" + (i + 2) + "行，导入的渠道代码为"+channel_no+",数据库里渠道代码为空");
					}
					
					/*if(cmainPolicyINSVOMModel.getInsurance_class_no()!=null){
						if(!cmainPolicyINSVOMModel.getInsurance_class_no().equals(insurance_class_no)){
							msgList.add("第" + (i + 2) + "行，导入的险类代码为"+insurance_class_no+",数据库里险类代码为"+cmainPolicyINSVOMModel.getInsurance_class_no());
						}
					}else if(insurance_class_no!=null){
						msgList.add("第" + (i + 2) + "行，导入的险类代码为"+insurance_class_no+",数据库里险类代码为"+cmainPolicyINSVOMModel.getInsurance_class_no());
					}
					
					if(cmainPolicyINSVOMModel.getInsurance_class_name()!=null){
						if(!cmainPolicyINSVOMModel.getInsurance_class_name().equals(insurance_class_name)){
							msgList.add("第" + (i + 2) + "行，导入的险类名称为"+insurance_class_name+",数据库里险类名称为"+cmainPolicyINSVOMModel.getInsurance_class_name());
						}
					}else if(insurance_class_name!=null){
						msgList.add("第" + (i + 2) + "行，导入的险类名称为"+insurance_class_name+",数据库里险类名称为"+cmainPolicyINSVOMModel.getInsurance_class_name());
					}
					
					if(cmainPolicyINSVOMModel.getInsurance_type_no()!=null){
						if(!cmainPolicyINSVOMModel.getInsurance_type_no().equals(insurance_type_no)){
							msgList.add("第" + (i + 2) + "行，导入的险种代码为"+insurance_type_no+",数据库里险种代码为"+cmainPolicyINSVOMModel.getInsurance_type_no());
						}
					}else if(insurance_type_no!=null){
						msgList.add("第" + (i + 2) + "行，导入的险种代码为"+insurance_type_no+",数据库里险种代码为"+cmainPolicyINSVOMModel.getInsurance_type_no());
					}
					
					if(cmainPolicyINSVOMModel.getInsurance_type_name()!=null){
						if(!cmainPolicyINSVOMModel.getInsurance_type_name().equals(insurance_type_name)){
							msgList.add("第" + (i + 2) + "行，导入的险种名称为"+insurance_type_name+",数据库里险种名称为"+cmainPolicyINSVOMModel.getInsurance_type_name());
						}
					}else if(insurance_type_name!=null){
						msgList.add("第" + (i + 2) + "行，导入的险种名称为"+insurance_type_name+",数据库里险种名称为"+cmainPolicyINSVOMModel.getInsurance_type_name());
					}
					
					if(cmainPolicyINSVOMModel.getSign_date()!=null){
						if(!cmainPolicyINSVOMModel.getSign_date().equals(sign_date)){
							msgList.add("第" + (i + 2) + "行，导入的签单日期为"+sign_date+",数据库里签单日期为"+cmainPolicyINSVOMModel.getSign_date());
						}
					}else if(sign_date!=null){
						msgList.add("第" + (i + 2) + "行，导入的签单日期为"+sign_date+",数据库里签单日期为"+cmainPolicyINSVOMModel.getSign_date());
					}
					
					if(cmainPolicyINSVOMModel.getInsure_no()!=null){
						if(!cmainPolicyINSVOMModel.getInsure_no().equals(insure_no)){
							msgList.add("第" + (i + 2) + "行，导入的投保单号为"+insure_no+",数据库里投保单号为"+cmainPolicyINSVOMModel.getInsure_no());
						}
					}else if(insure_no!=null){
						msgList.add("第" + (i + 2) + "行，导入的投保单号为"+insure_no+",数据库里投保单号为"+cmainPolicyINSVOMModel.getInsure_no());
					}
					
					if(cmainPolicyINSVOMModel.getApplicant_name()!=null){
						if(!cmainPolicyINSVOMModel.getApplicant_name().equals(applicant_name)){
							msgList.add("第" + (i + 2) + "行，导入的投保人姓名为"+applicant_name+",数据库里投保人姓名为"+cmainPolicyINSVOMModel.getApplicant_name());
						}
					}else if(applicant_name!=null){
						msgList.add("第" + (i + 2) + "行，导入的投保人姓名为"+applicant_name+",数据库里投保人姓名为"+cmainPolicyINSVOMModel.getApplicant_name());
					}
					
					if(cmainPolicyINSVOMModel.getInsured_name()!=null){
						if(!cmainPolicyINSVOMModel.getInsured_name().equals(insured_name)){
							msgList.add("第" + (i + 2) + "行，导入的被保险人姓名为"+insured_name+",数据库里被保险人姓名为"+cmainPolicyINSVOMModel.getInsured_name());
						}
					}else if(insured_name!=null){
						msgList.add("第" + (i + 2) + "行，导入的被保险人姓名为"+insured_name+",数据库里被保险人姓名为"+cmainPolicyINSVOMModel.getInsured_name());
					}
					
					if(cmainPolicyINSVOMModel.getInsured_cid()!=null){
						if(!cmainPolicyINSVOMModel.getInsured_cid().equals(insured_cid)){
							msgList.add("第" + (i + 2) + "行，导入的被保险人身份证为"+insured_cid+",数据库里被保险人身份证为"+cmainPolicyINSVOMModel.getInsured_cid());
						}
					}else if(insured_cid!=null){
						msgList.add("第" + (i + 2) + "行，导入的被保险人身份证为"+insured_cid+",数据库里被保险人身份证为"+cmainPolicyINSVOMModel.getInsured_cid());
					}
					
					if(cmainPolicyINSVOMModel.getInsured_company_type()!=null){
						if(!cmainPolicyINSVOMModel.getInsured_company_type().equals(insured_company_type)){
							msgList.add("第" + (i + 2) + "行，导入的被保险人单位性质为"+insured_company_type+",数据库里被保险人单位性质为"+cmainPolicyINSVOMModel.getInsured_company_type());
						}
					}else if(insured_company_type!=null){
						msgList.add("第" + (i + 2) + "行，导入的被保险人单位性质为"+insured_company_type+",数据库里被保险人单位性质为"+cmainPolicyINSVOMModel.getInsured_company_type());
					}
					
					if(cmainPolicyINSVOMModel.getInsured_papertype()!=null){
						if(!cmainPolicyINSVOMModel.getInsured_papertype().equals(insured_papertype)){
							msgList.add("第" + (i + 2) + "行，导入的被保险人证件类型为"+insured_papertype+",数据库里被保险人证件类型为"+cmainPolicyINSVOMModel.getInsured_papertype());
						}
					}else if(insured_papertype!=null){
						msgList.add("第" + (i + 2) + "行，导入的被保险人证件类型为"+insured_papertype+",数据库里被保险人证件类型为"+cmainPolicyINSVOMModel.getInsured_papertype());
					}
					
					if(cmainPolicyINSVOMModel.getInsured_add()!=null){
						if(!cmainPolicyINSVOMModel.getInsured_add().equals(insured_add)){
							msgList.add("第" + (i + 2) + "行，导入的被保险人地址为"+insured_add+",数据库里被保险人地址为"+cmainPolicyINSVOMModel.getInsured_add());
						}
					}else if(insured_add!=null){
						msgList.add("第" + (i + 2) + "行，导入的被保险人地址为"+insured_add+",数据库里被保险人地址为"+cmainPolicyINSVOMModel.getInsured_add());
					}
					
					if(cmainPolicyINSVOMModel.getInsured_mailbox()!=null){
						if(!cmainPolicyINSVOMModel.getInsured_mailbox().equals(insured_mailbox)){
							msgList.add("第" + (i + 2) + "行，导入的被保险人邮箱为"+insured_mailbox+",数据库里被保险人邮箱为"+cmainPolicyINSVOMModel.getInsured_mailbox());
						}
					}else if(insured_mailbox!=null){
						msgList.add("第" + (i + 2) + "行，导入的被保险人邮箱为"+insured_mailbox+",数据库里被保险人邮箱为"+cmainPolicyINSVOMModel.getInsured_mailbox());
					}
					
					if(cmainPolicyINSVOMModel.getInsured_phone()!=null){
						if(!cmainPolicyINSVOMModel.getInsured_phone().equals(insured_phone)){
							msgList.add("第" + (i + 2) + "行，导入的被保险人手机为"+insured_phone+",数据库里被保险人手机为"+cmainPolicyINSVOMModel.getInsured_phone());
						}
					}else if(insured_phone!=null){
						msgList.add("第" + (i + 2) + "行，导入的被保险人手机为"+insured_phone+",数据库里被保险人手机为"+cmainPolicyINSVOMModel.getInsured_phone());
					}
					
					if(cmainPolicyINSVOMModel.getInsured_tel()!=null){
						if(!cmainPolicyINSVOMModel.getInsured_tel().equals(insured_tel)){
							msgList.add("第" + (i + 2) + "行，导入的被保险人固话为"+insured_tel+",数据库里被保险人固话为"+cmainPolicyINSVOMModel.getInsured_tel());
						}
					}else if(insured_tel!=null){
						msgList.add("第" + (i + 2) + "行，导入的被保险人固话为"+insured_tel+",数据库里被保险人固话为"+cmainPolicyINSVOMModel.getInsured_tel());
					}
					
					if(cmainPolicyINSVOMModel.getLpn()!=null){
						if(!cmainPolicyINSVOMModel.getLpn().equals(lpn)){
							msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
						}
					}else if(lpn!=null){
						msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
					}
					
					if(cmainPolicyINSVOMModel.getCar_type()!=null){
						if(!cmainPolicyINSVOMModel.getCar_type().equals(car_type)){
							msgList.add("第" + (i + 2) + "行，导入的车型编码为"+car_type+",数据库里车型编码为"+cmainPolicyINSVOMModel.getCar_type());
						}
					}else if(car_type!=null){
						msgList.add("第" + (i + 2) + "行，导入的车型编码为"+car_type+",数据库里车型编码为"+cmainPolicyINSVOMModel.getCar_type());
					}
					
					if(cmainPolicyINSVOMModel.getLpn()!=null){
						if(!cmainPolicyINSVOMModel.getLpn().equals(lpn)){
							msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
						}
					}else if(lpn!=null){
						msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
					}
					
					if(cmainPolicyINSVOMModel.getLpn()!=null){
						if(!cmainPolicyINSVOMModel.getLpn().equals(lpn)){
							msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
						}
					}else if(lpn!=null){
						msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
					}
					
					if(cmainPolicyINSVOMModel.getLpn()!=null){
						if(!cmainPolicyINSVOMModel.getLpn().equals(lpn)){
							msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
						}
					}else if(lpn!=null){
						msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
					}
					
					if(cmainPolicyINSVOMModel.getLpn()!=null){
						if(!cmainPolicyINSVOMModel.getLpn().equals(lpn)){
							msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
						}
					}else if(lpn!=null){
						msgList.add("第" + (i + 2) + "行，导入的车牌号为"+lpn+",数据库里车牌号为"+cmainPolicyINSVOMModel.getLpn());
					}
				}*/

                msg.put("repeatmsg", repeatmsg);
                msg.put("msgList", msgList);

                if (line == 0) {
                    msgList.add("excel中数据为空，请重新录入。");
                }
            }
        }
        return msg;
    }


    /**
     * 校验导入的数据是否正确
     *
     * @param dataMap
     * @return
     * @description:
     */
    @Override
    public List<String> checkTraPlanInfoIsTruePerson(Map<String, List<Object>> dataMap) {
        List<String> msgList = new ArrayList<String>();
        int line = 0;
        HashMap<String, String> check_cpy_branch = new HashMap<String, String>();
        HashMap<String, String> check_sys_branch = new HashMap<String, String>();

        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }

            for (int i = 0; i < practice.size(); i++) {
                line++;
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (HashMap<String, Object>) practice.get(i);
                // 获取校验字段


                /**
                 *	保险公司省级机构名称	province_orgname
                 保险公司省级机构代码	province_orgid
                 保险公司地市机构名称	city_orgname
                 保险公司地市机构代码	city_orgid
                 保险公司县支公司名称	area_orgid
                 保险公司县支公司代码	area_orgname
                 中介公司机构名称	sale_org_name
                 中介公司机构代码	sale_org_id
                 */
                String province_orgname = ActionHelper.getNullToStr(map.get("province_orgname")).trim();
                if (StringUtil.getBytesLength(province_orgname.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，保险公司省级机构 名称 超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String province_orgid = ActionHelper.getNullToStr(map.get("province_orgid")).trim();
                if (StringUtil.getBytesLength(province_orgid.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，保险公司省级机构 代码  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                if ((province_orgid != null && !"".equals(province_orgid)) || (province_orgname != null && !"".equals(province_orgname))) {
                    if (!check_cpy_branch(check_cpy_branch, province_orgid, province_orgname)) {
                        msgList.add("第" + (i + 2) + "行，保险公司省级机构 名称和代码  和数据库不匹配，请核对！");
                        //return msgList;
                    }
                }

                String city_orgname = ActionHelper.getNullToStr(map.get("city_orgname")).trim();
                if (StringUtil.getBytesLength(city_orgname.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，保险公司地市机构名称  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String city_orgid = ActionHelper.getNullToStr(map.get("city_orgid")).trim();
                if (StringUtil.getBytesLength(city_orgid.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，保险公司地市机构代码  超出数据库设计范围，请核对！");
                    //return msgList;
                }

                if ((city_orgid != null && !"".equals(city_orgid)) || (city_orgname != null && !"".equals(city_orgname))) {
                    if (!check_cpy_branch(check_cpy_branch, city_orgid, city_orgname)) {
                        msgList.add("第" + (i + 2) + "行，保险公司地市机构 名称和代码  和数据库不匹配，请核对！");
                        //return msgList;
                    }
                }


                String area_orgid = ActionHelper.getNullToStr(map.get("area_orgid")).trim();
                if (StringUtil.getBytesLength(area_orgid.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，财险区县机构代码  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String area_orgname = ActionHelper.getNullToStr(map.get("area_orgname")).trim();
                if (StringUtil.getBytesLength(area_orgname.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，财险区县机构名称  超出数据库设计范围，请核对！");
                    //return msgList;
                }

                if ((area_orgid != null && !"".equals(area_orgid)) || (area_orgname != null && !"".equals(area_orgname))) {
                    if (!check_cpy_branch(check_cpy_branch, area_orgid, area_orgname)) {
                        msgList.add("第" + (i + 2) + "行，财险区县机构 名称和代码  和数据库不匹配，请核对！");
                        //return msgList;
                    }
                }
				
				
				/*String   sale_org_id              =   					ActionHelper.getNullToStr(map.get("sale_org_id"  ));  
				if (StringUtil.getBytesLength(sale_org_id.trim()) > 100) {
					msgList.add("第" + (i + 2) + "行，中介公司机构代码  超出数据库设计范围，请核对！");
				}
				String   sale_org_name = ActionHelper.getNullToStr(map.get("sale_org_name"));
				if (StringUtil.getBytesLength(sale_org_name.trim()) > 200) {
					msgList.add("第" + (i + 2) + "行，中介公司机构名称  超出数据库设计范围，请核对！");
				}
				if (!check_sys_branch(check_sys_branch, sale_org_id, sale_org_name)) {
					msgList.add("第" + (i + 2) + "行，中介公司机构 名称和代码  和数据库不匹配，请核对！");
				}*/
                String channel_no = ActionHelper.getNullToStr(map.get("channel_no")).trim();
                if (StringUtil.getBytesLength(channel_no.trim()) > 100) {
                    msgList.add("第" + (i + 2) + "行，中介公司渠道代码  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String sale_org_name = ActionHelper.getNullToStr(map.get("sale_org_name")).trim();
                if (StringUtil.getBytesLength(sale_org_name.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，中介公司机构名称  超出数据库设计范围，请核对！");
                    //return msgList;
                }

                if ((channel_no != null && !"".equals(channel_no)) || (sale_org_name != null && !"".equals(sale_org_name))) {
                    if (!check_sys_branch(check_sys_branch, channel_no, sale_org_name)) {
                        msgList.add("第" + (i + 2) + "行，中介公司机构 名称和中介公司渠道代码  和数据库不匹配，请核对！");
                        //return msgList;
                    }
                }

				
				/*IBranchModel iBranchModel= cmainPolicyDao.queryBranchById(sale_org_id);
				if (iBranchModel == null ||  null == iBranchModel.getChannelcode() || "".equals(iBranchModel.getChannelcode())) {
					msgList.add("第" + (i + 2) + "行， 根据中介公司机构代码没有找到渠道代码，请核实，请核对！");
				}else {
					map.put("channel_no", iBranchModel.getChannelcode());
				}*/


                /**
                 结算日期	settled_date
                 保单号	policy_no
                 投保单号	insure_no
                 主险/附加险标识	center_risk_flag
                 险种名称	center_risk_name
                 险种代码	center_risk_id
                 投保人	applicant_name
                 被保险人	insured_name
                 * */
                String policy_no = ActionHelper.getNullToStr(map.get("policy_no")).trim();// 保单号*
                // 校验
                if ("".equals(policy_no.trim())) {
                    msgList.add("第" + (i + 2) + "行，保单号不能为空，请核对！");
                    //return msgList;
                } else if (StringUtil.getBytesLength(policy_no.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，保单号长度超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String insure_no = ActionHelper.getNullToStr(map.get("insure_no")).trim();
                if (StringUtil.getBytesLength(insure_no.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，投保单号  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String center_risk_flag = ActionHelper.getNullToStr(map.get("center_risk_flag")).trim();
                if (StringUtil.getBytesLength(center_risk_flag.trim()) > 20) {
                    msgList.add("第" + (i + 2) + "行，主险/附加险标识  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String center_risk_name = ActionHelper.getNullToStr(map.get("center_risk_name")).trim();
                if (StringUtil.getBytesLength(center_risk_name.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，产品名称  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String center_risk_id = ActionHelper.getNullToStr(map.get("center_risk_id")).trim();
                if (StringUtil.getBytesLength(center_risk_id.trim()) > 20) {
                    msgList.add("第" + (i + 2) + "行，产品代码  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String center_risk_type = ActionHelper.getNullToStr(map.get("center_risk_type")).trim();
                if (StringUtil.getBytesLength(center_risk_type.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，产品类型  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String long_risk_flag = ActionHelper.getNullToStr(map.get("long_risk_flag")).trim();
                if (StringUtil.getBytesLength(long_risk_flag.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，长短险标志  超出数据库设计范围，请核对！");
                    //return msgList;
                }
				/*String   renew_flag = ActionHelper.getNullToStr(map.get("renew_flag"));
				if (StringUtil.getBytesLength(renew_flag.trim()) > 200) {
					msgList.add("第" + (i + 2) + "行，新单续收标识  超出数据库设计范围，请核对！");
				}*/

                String applicant_name = ActionHelper.getNullToStr(map.get("applicant_name")).trim();
                if (StringUtil.getBytesLength(applicant_name.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，投保人姓名 超出数据库设计范围，请核对！");
                    //return msgList;
                }

                String applicant_id = ActionHelper.getNullToStr(map.get("applicant_id")).trim();
                if (StringUtil.getBytesLength(applicant_id.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，投保人身份证号  超出数据库设计范围，请核对！");
                    //return msgList;
                }

                String insured_name = ActionHelper.getNullToStr(map.get("insured_name")).trim();
                // 校验
                if ("".equals(insured_name.trim())) {
                    msgList.add("第" + (i + 2) + "行，被保险人 不能为空，请核对！");
                    //return msgList;
                } else if (StringUtil.getBytesLength(insured_name.trim()) > 120) {
                    msgList.add("第" + (i + 2) + "行，被保险人姓名 长度超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String insured_id = ActionHelper.getNullToStr(map.get("insured_id")).trim();
                if (StringUtil.getBytesLength(insured_id.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，被保险险人身份证号  超出数据库设计范围，请核对！");
                    //return msgList;
                }

                /**
                 承保日期	insure_date
                 签发日期	paper_date
                 回执核销日期	cancel_date
                 终止原因名称	cancel_reason
                 * */
                String insure_date = ActionHelper.getNullToStr(map.get("insure_date")).trim();
                if ("".equals(insure_date.trim())) {
                } else
                    try {
                        DateUtil.string2Date(insure_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，承保日期 格式不正确，请核对。");
                        //return msgList;
                    }
                String paper_date = ActionHelper.getNullToStr(map.get("paper_date")).trim();
                if ("".equals(paper_date.trim())) {
                } else
                    try {
                        DateUtil.string2Date(paper_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，客户签收日期  格式不正确，请核对。");
                        //return msgList;
                    }
                String visit_date = ActionHelper.getNullToStr(map.get("visit_date")).trim();
                if ("".equals(paper_date.trim())) {
                } else
                    try {
                        DateUtil.string2Date(visit_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，回访成功日期  格式不正确，请核对。");
                        //return msgList;
                    }
                String cancel_date = ActionHelper.getNullToStr(map.get("cancel_date")).trim();
                if ("".equals(paper_date.trim())) {
                } else
                    try {
                        DateUtil.string2Date(cancel_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，回执核销日期  格式不正确，请核对。");
                        //return msgList;
                    }
                String cancel_reason = ActionHelper.getNullToStr(map.get("cancel_reason")).trim();
                if (StringUtil.getBytesLength(cancel_reason.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，终止原因名称  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                /**
                 缴费方式名称	pay_fee_type
                 缴费期	pay_date
                 保险期间	insured_during
                 付款方式名称	pay_type
                 * */
                String pay_fee_type = ActionHelper.getNullToStr(map.get("pay_fee_type")).trim();
                if (StringUtil.getBytesLength(pay_fee_type.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，缴费方式名称  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String pay_fee_year = ActionHelper.getNullToStr(map.get("pay_fee_year")).trim();
                if (StringUtil.getBytesLength(pay_fee_year.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，缴费年限  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String risk_year = ActionHelper.getNullToStr(map.get("risk_year")).trim();
                if ("".equals(risk_year.trim())) {
                    msgList.add("第" + (i + 2) + "行，保单年度  不能为空，请核对！");
                    //return msgList;
                } else if (StringUtil.getBytesLength(risk_year.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，保单年度  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String ins_validate = ActionHelper.getNullToStr(map.get("ins_validate")).trim();
                if ("".equals(ins_validate.trim())) {
                } else
                    try {
                        DateUtil.string2Date(ins_validate);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，业务生效日期  格式不正确，请核对。");
                        //return msgList;
                    }


                String pay_date = ActionHelper.getNullToStr(map.get("cancel_date")).trim();
                if ("".equals(pay_date.trim())) {
                } else
                    try {
                        DateUtil.string2Date(pay_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，回执核销日期  格式不正确，请核对。");
                        //return msgList;
                    }
                String insured_during = ActionHelper.getNullToStr(map.get("insured_during")).trim();
                if (StringUtil.getBytesLength(insured_during.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，保险期间  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String pay_type = ActionHelper.getNullToStr(map.get("pay_type")).trim();
                if (StringUtil.getBytesLength(pay_type.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，付款方式名称  超出数据库设计范围，请核对！");
                    //return msgList;
                }

                /**
                 保费	premium
                 保额	amount
                 手续费比例	fee_ratio
                 新单佣金	new_paper_fee
                 续期佣金	renew_paper_fee
                 手续费（合计）	total_fee
                 * */
                String premium = ActionHelper.getNullToStr(map.get("premium")).trim();
                if ("".equals(premium.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(premium);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，总保费  请输入数字，请核对！");
                        //return msgList;
                    }
                String insured_during_flag = ActionHelper.getNullToStr(map.get("insured_during_flag")).trim();
                if (StringUtil.getBytesLength(insured_during_flag.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，保险期类型  超出数据库设计范围，请核对！");
                    //return msgList;
                }


                String amount = ActionHelper.getNullToStr(map.get("amount")).trim();
                if ("".equals(amount.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(amount);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，保额 不符合数字格式，请核对！");
                        //return msgList;
                    }
                String fee_ratio = ActionHelper.getNullToStr(map.get("fee_ratio")).trim();
                if ("".equals(fee_ratio.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(fee_ratio);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，佣金费率  不符合数字格式，请核对！");
                        //return msgList;
                    }
                String new_paper_fee = ActionHelper.getNullToStr(map.get("new_paper_fee")).trim();
                if ("".equals(new_paper_fee.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(new_paper_fee);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，佣金  不符合数字格式，请核对！");
                        //return msgList;
                    }
                String renew_paper_fee = ActionHelper.getNullToStr(map.get("renew_paper_fee")).trim();
                if ("".equals(renew_paper_fee.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(renew_paper_fee);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，续期佣金  不符合数字格式，请核对！");
                        //return msgList;
                    }
                String total_fee = ActionHelper.getNullToStr(map.get("total_fee")).trim();
                if ("".equals(total_fee.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(total_fee);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，手续费（合计）  不符合数字格式，请核对！");
                        //return msgList;
                    }
                /**
                 中介公司归属人员姓名	owner_name
                 中介公司归属人员编码	owner_no
                 代理人佣金支付比例	agent_fee_rate
                 代理人佣金金额	agent_fee
                 业务类型	business_flag
                 互联网标识	inter_flag
                 批单号	endor_no
                 批改日期	endor_date
                 批改生效日期	endor_valid
                 * */
                String owner_name = ActionHelper.getNullToStr(map.get("owner_name")).trim();
                if (StringUtil.getBytesLength(owner_name.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，中介公司归属人员姓名  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                String owner_no = ActionHelper.getNullToStr(map.get("owner_no")).trim();
                if (StringUtil.getBytesLength(owner_no.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行，中介公司归属人员编码  超出数据库设计范围，请核对！");
                    //return msgList;
                }
                if (!"".equals(owner_no) || !"".equals(owner_name)) {
                    CmainPolicyINSPersonVOMModel model = new CmainPolicyINSPersonVOMModel();
                    model.setChannel_no(channel_no);
                    model.setOwner_name(owner_name);
                    model.setOwner_no(owner_no);
                    List<CmainPolicyINSPersonVOMModel> retList = cmainPolicyDao.queryPerson(model);
                    boolean valid = false;
                    for (int j = 0; j < retList.size(); j++) {
                        if (owner_name.equals(retList.get(j).getOwner_name())) {
                            if (owner_no.equals(retList.get(j).getOwner_no())) {
                                valid = true;
                            }
                        }
                    }
                    if (!valid) {
                        msgList.add("第" + (i + 2) + "行，在所属机构中没有找到对应的中介公司归属人员或中介公司归属人员工号和姓名不匹配");
                        //return msgList;
                    }
                }

                String agent_fee_rate = ActionHelper.getNullToStr(map.get("agent_fee_rate")).trim();
                if ("".equals(agent_fee_rate.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(agent_fee_rate);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，代理人佣金支付比例  不符合数字格式，请核对！");
                        //return msgList;
                    }
                String agent_fee = ActionHelper.getNullToStr(map.get("agent_fee")).trim();
                if ("".equals(agent_fee.trim())) {
                } else
                    try {
                        ActionHelper.getNullToDouble(agent_fee);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，代理人佣金金额  不符合数字格式，请核对！");
                        //return msgList;
                    }

                businessMap = this.getBusinessMap();
                //String business_flag = ActionHelper.getNullToStr(map.get("business_flag" )).trim();
				/*if ("".equals(business_flag.trim())) {
					msgList.add("第" + (i + 2) + "行，业务类型不能为空，请核对！");
					return msgList;
				} else
				if(!"".equals(business_flag.trim())){
					if (null == businessMap.get(business_flag)) {
						msgList.add("第" + (i + 2) + "行，无此业务类型！");
						//return msgList;
					}
				}
				map.put("business_flag", businessMap.get(business_flag));*/

                interMap = getInterMap();
                //String inter_flag = ActionHelper.getNullToStr(map.get("inter_flag")).trim();
				/*if ("".equals(inter_flag.trim())) {
					msgList.add("第" + (i + 2) + "行，互联网标识不能为空，请核对！");
				} else */
				/*if(!"".equals(inter_flag.trim())){
					if (null == interMap.get(inter_flag)) {
						msgList.add("第" + (i + 2) + "行，无此互联网标识！");
						//return msgList;
					}
				}

				map.put("inter_flag", interMap.get(inter_flag));*/
                String endor_no = ActionHelper.getNullToStr(map.get("endor_no")).trim();
                // 校验
                if (StringUtil.getBytesLength(endor_no.trim()) > 200) {
                    msgList.add("第" + (i + 2) + "行， 批单号 超出数据库设计范围，请核对！");
                    //return msgList;
                }

                String endor_date = ActionHelper.getNullToStr(map.get("endor_date")).trim();
                if ("".equals(endor_date.trim())) {
                } else
                    try {
                        DateUtil.string2Date(endor_date);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，批改日期  格式不正确，请核对。");
                        //return msgList;
                    }
                String endor_valid = ActionHelper.getNullToStr(map.get("endor_valid")).trim();
                if ("".equals(pay_date.trim())) {
                } else
                    try {
                        DateUtil.string2Date(endor_valid);
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，批改生效日期  格式不正确，请核对。");
                        //return msgList;
                    }

                String change_nu = ActionHelper.getNullToStr(map.get("change_nu")).trim();
                if (StringUtil.getBytesLength(change_nu.trim()) > 0) {
                    try {
                        Integer.parseInt(change_nu);
                    } catch (NumberFormatException e) {
                        msgList.add("第" + (i + 2) + "行，数量变化和数据库设计不符合，请核对！");
                        e.printStackTrace();
                        //return msgList;
                    }
                }
                String settled_time = ActionHelper.getNullToStr(map.get("settled_time")).trim();
                if ("".equals(settled_time.trim())) {
                    msgList.add("第" + (i + 2) + "行，结算时间不能为空，请核对！");
                } else if (StringUtil.getBytesLength(settled_time.trim()) > 20) {
                    msgList.add("第" + (i + 2) + "行，结算时间  超出数据库设计范围，请核对！");
                    //return msgList;
                } else
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                        java.util.Date settled_date_date = format.parse(settled_time);
                        if (new java.util.Date().getTime() <= settled_date_date.getTime()) {
                            msgList.add("第" + (i + 2) + "行，结算时间不能大于当前日期，请核对。");
                            //return msgList;
                        }
                    } catch (Exception e) {
                        msgList.add("第" + (i + 2) + "行，结算时间  格式不正确，请核对。");
                        //return msgList;
                    }


               /* String settled_date = ActionHelper.getNullToStr(map.get("settled_date")).trim();
                if (StringUtil.getBytesLength(settled_date.trim()) > 20) {
                    msgList.add("第" + (i + 2) + "行，分单时间   超出数据库设计范围，请核对！");
                    //return msgList;
                } else if (!"".equals(settled_date) && settled_date != null) {
                    if (!settled_date.equals(settled_time)) {
                        msgList.add("第" + (i + 2) + "行，分单时间和结算时间不一致，请核对！");
                    }
                    if ("".equals(owner_no.trim())) {
                        msgList.add("第" + (i + 2) + "行，有分单时间,中介公司归属人员编码不能为空，请核对！");
                    }
                    if ("".equals(owner_name.trim())) {
                        msgList.add("第" + (i + 2) + "行，有分单时间,中介公司归属人员姓名不能为空，请核对！");
                    }
					*//*if("".equals(agent_fee_rate.trim())){
						msgList.add("第" + (i + 2) + "行，代理人佣金支付比例不能为空，请核对！");
					}*//*
                }*/

            }
        }
        if (line == 0) {
            msgList.add("excel中数据为空，请重新录入。");
        }
        return msgList;
    }


    /**
     * 校验导入的数据是否正确
     *
     * @param dataMap
     * @return
     * @description:
     */
    @Override
    public List<String> checkTraPlanInfoBusiness(Map<String, List<Object>> dataMap) {
        List<String> msgList = new ArrayList<String>();
        int line = 0;

        //businessMap.put(arg0, arg1);

        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }

            for (int i = 0; i < practice.size(); i++) {
                line++;
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (HashMap<String, Object>) practice.get(i);
                // 获取校验字段
//				"series_no",//序号
//				//保险公司机构
//				"org_name",//中介公司机构
//				"policy_no"	,//保单号码
//				"business_flag"//业务类型
                String series_no = ActionHelper.getNullToStr(map.get("series_no"));//序号
                //String business_flag = ActionHelper.getNullToStr(map.get("business_flag"));//业务类型
                String policy_no = ActionHelper.getNullToStr(map.get("policy_no"));//业务类型
                List<CmainPolicyINSVOMModel> a = cmainPolicyDao.findPolicyByNo(policy_no);
                if (a.size() <= 0) {
                    msgList.add("第" + (i + 1) + "行，根据保单号没有找到数据");
                }
                businessMap = getBusinessMap();
				/*if(businessMap.containsKey(business_flag)) {
					String business_code = businessMap.get(business_flag);
					map.put("business_flag",business_code);
				}else {
					msgList.add("第" + (i + 1)+ "行，序号为"+series_no+"业务类型无效，没有找到此业务类型");
				}*/

            }

        }
        if (line == 0) {
            msgList.add("excel中数据为空，请重新录入。");
        }
        return msgList;
    }


    /**
     * 校验导入的数据是否正确
     *
     * @param dataMap
     * @return
     * @description:
     */
    public List<String> checkTraPlanInfoInter(Map<String, List<Object>> dataMap) {
        List<String> msgList = new ArrayList<String>();
        int line = 0;

        //businessMap.put(arg0, arg1);

        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }

            for (int i = 0; i < practice.size(); i++) {
                line++;
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (HashMap<String, Object>) practice.get(i);
                // 获取校验字段
//				"series_no",//序号
//				//保险公司机构
//				"org_name",//中介公司机构
//				"policy_no"	,//保单号码
//				"inter_flag"//业务类型
                String series_no = ActionHelper.getNullToStr(map.get("series_no"));//序号
                String inter_flag = ActionHelper.getNullToStr(map.get("inter_flag"));//业务类型
                String policy_no = ActionHelper.getNullToStr(map.get("policy_no"));//业务类型
                List<CmainPolicyINSVOMModel> a = cmainPolicyDao.findPolicyByNo(policy_no);
                if (a.size() <= 0) {
                    msgList.add("第" + (i + 1) + "行，根据保单号没有找到数据");
                }
                interMap = getInterMap();
                if (interMap.containsKey(inter_flag)) {
                    String inter_code = interMap.get(inter_flag);
                    map.put("inter_flag", inter_code);
                } else {
                    msgList.add("第" + (i + 1) + "行，序号为" + series_no + "互联网标识无效，没有找到此互联网标识");
                }

            }

        }
        if (line == 0) {
            msgList.add("excel中数据为空，请重新录入。");
        }
        return msgList;
    }


    /**
     * 校验导入的数据是否正确
     *
     * @param dataMap
     * @return
     * @description:
     */
    @Override
    public List<String> checkTraPlanInfoBusinessPerson(Map<String, List<Object>> dataMap) {
        List<String> msgList = new ArrayList<String>();
        int line = 0;

        //businessMap.put(arg0, arg1);

        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }

            for (int i = 0; i < practice.size(); i++) {
                line++;
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (HashMap<String, Object>) practice.get(i);
                // 获取校验字段
//				"series_no",//序号
//				//保险公司机构
//				"org_name",//中介公司机构
//				"policy_no"	,//保单号码
//				"business_flag"//业务类型
                String series_no = ActionHelper.getNullToStr(map.get("series_no"));//序号
                //String business_flag = ActionHelper.getNullToStr(map.get("business_flag"));//业务类型
                String policy_no = ActionHelper.getNullToStr(map.get("policy_no"));//业务类型
                List<CmainPolicyINSVOMModel> a = cmainPolicyDao.findPolicyByNoPerson(policy_no);
                if (a.size() <= 0) {
                    msgList.add("第" + (i + 1) + "行，根据保单号没有找到数据");
                }
                businessMap = getBusinessMap();
				/*if(businessMap.containsKey(business_flag)) {
					String business_code = businessMap.get(business_flag);
					map.put("business_flag",business_code);
				}else {
					msgList.add("第" + (i + 1)+ "行，序号为"+series_no+"业务类型无效，没有找到此业务类型");
				}*/

            }

        }
        if (line == 0) {
            msgList.add("excel中数据为空，请重新录入。");
        }
        return msgList;
    }


    /**
     * 校验导入的数据是否正确
     *
     * @param dataMap
     * @return
     * @description:
     */
    @Override
    public List<String> checkTraPlanInfoInterPerson(Map<String, List<Object>> dataMap) {
        List<String> msgList = new ArrayList<String>();
        int line = 0;

        //businessMap.put(arg0, arg1);

        for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
            List<Object> practice = entry.getValue();
            if (practice.size() < 1) { // 如果长度小于1 则continue
                continue;
            }

            for (int i = 0; i < practice.size(); i++) {
                line++;
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (HashMap<String, Object>) practice.get(i);
                // 获取校验字段
//				"series_no",//序号
//				//保险公司机构
//				"org_name",//中介公司机构
//				"policy_no"	,//保单号码
//				"inter_flag"//业务类型
                String series_no = ActionHelper.getNullToStr(map.get("series_no"));//序号
                String inter_flag = ActionHelper.getNullToStr(map.get("inter_flag"));//业务类型
                String policy_no = ActionHelper.getNullToStr(map.get("policy_no"));//业务类型
                List<CmainPolicyINSVOMModel> a = cmainPolicyDao.findPolicyByNoPerson(policy_no);
                if (a.size() <= 0) {
                    msgList.add("第" + (i + 1) + "行，根据保单号没有找到数据");
                }
                interMap = getInterMap();
                if (interMap.containsKey(inter_flag)) {
                    String inter_code = interMap.get(inter_flag);
                    map.put("inter_flag", inter_code);
                } else {
                    msgList.add("第" + (i + 1) + "行，序号为" + series_no + "互联网标识无效，没有找到此互联网标识");
                }

            }

        }
        if (line == 0) {
            msgList.add("excel中数据为空，请重新录入。");
        }
        return msgList;
    }


    public HashMap<String, String> findCodeToMap(String codeType) {
        CodeVOModel codeVOModel = new CodeVOModel();
        codeVOModel.setCodeType(codeType);
        ArrayList<CodeVOModel> temp = cmainPolicyDao.findCode(codeVOModel);
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < temp.size(); i++) {
            CodeVOModel temp1 = temp.get(i);
            map.put(temp1.getCodeCode(), temp1.getCodeName());
        }
        return map;
    }

    public HashMap<String, String> findCodeNameToMap(String codeType) {
        CodeVOModel codeVOModel = new CodeVOModel();
        codeVOModel.setCodeType(codeType);
        ArrayList<CodeVOModel> temp = cmainPolicyDao.findCode(codeVOModel);
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < temp.size(); i++) {
            CodeVOModel temp1 = temp.get(i);
            map.put(temp1.getCodeName(), temp1.getCodeCode());
        }
        return map;
    }

    @Override
    public HashMap<String, String> getBusinessMap() {
        if (businessMap != null) {
        } else {
            businessMap = this.findCodeNameToMap("ins_cmain.business_flag");
        }
        return businessMap;
    }

    @Override
    public HashMap<String, String> getInterMap() {
        if (interMap != null) {
        } else {
            interMap = this.findCodeNameToMap("ins_cmain.inter_flag");
        }
        return interMap;
    }


    /**
     * 检查保险公司机构树
     */
    boolean check_cpy_branch(HashMap<String, String> check_cpy_branch, String branch_id, String branch_name) {
		/*if((branch_id != null &&"".equals(branch_id))||(branch_name !=null && "".equals(branch_name))){
			return false;
		}*/
        if (check_cpy_branch.containsKey(branch_id)) {
            if (branch_name.equals(check_cpy_branch.get(branch_id))) {
                return true;
            } else
                return false;
        } else {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("branch_id", branch_id);
            //temp.put("branch_name", branch_name);
            ArrayList<HashMap<String, String>> list = cmainPolicyDao.find_cpy_branch(temp);
            if (list.size() > 0) {
                HashMap<String, String> aHashMap = list.get(0);
                if (!branch_name.equals(aHashMap.get("branch_name"))) {
                    return false;
                }
                check_cpy_branch.put(aHashMap.get("branch_id"), aHashMap.get("branch_name"));
                return true;
            }
        }
        return true;
    }

    /**
     * 检查中介公司团队
     */
    boolean check_sys_branch(HashMap<String, String> check_sys_branch, String channel_no, String branch_name) {
        boolean retu = false;
        if ((channel_no != null && "".equals(channel_no)) || (branch_name != null && "".equals(branch_name))) {
            return retu;
        }
        if (check_sys_branch.containsKey(channel_no)) {
            if (branch_name.equals(check_sys_branch.get(channel_no))) {
                retu = true;
            }
        } else {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("channel_no", channel_no);
            temp.put("branch_name", branch_name);
            ArrayList<HashMap<String, String>> list = cmainPolicyDao.find_sys_branch(temp);
            if (list.size() > 0) {
                retu = true;
                HashMap<String, String> aHashMap = list.get(0);
                check_sys_branch.put(aHashMap.get("channel_no"), aHashMap.get("branch_name"));
            }
        }
        return retu;
    }


}
