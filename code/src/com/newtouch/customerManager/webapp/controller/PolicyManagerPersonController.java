package com.newtouch.customerManager.webapp.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.ICmainPolicyDomain;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ModelHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.customerManager.model.IPolicyManagerPersonModel;
import com.newtouch.customerManager.webapp.service.IPolicyManagerPersonService;
import com.newtouch.report.utils.ExcelUtils;

@Controller

public class PolicyManagerPersonController extends BaseController {
    @Autowired
    private IPolicyManagerPersonService policyService;
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private ICmainPolicyDomain cmainPolicyDomain;

    /**
     * @param req
     * @param res sms/InsBranchManage/toInsBranchQuery.do
     * @return ModelAndView
     * @description:代理人查询-跳转页面-查询
     */
    @RequestMapping("/PolicyManagerPerson/goPolicyList.do")
    public ModelAndView goPolicyList(HttpServletRequest req, HttpServletResponse res) {
        //req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
        return new ModelAndView("newtouch/policymanager/policymanagerperson/policyInfo");
    }

    @RequestMapping("/PolicyManagerPerson/uplodePolicy.do")
    public ModelAndView uplodePolicy(HttpServletRequest req, HttpServletResponse res) {
        return new ModelAndView("ca/cacore/cbs/policyLifePerson/propertyInsuranceUpLoad");
    }

    /**
     * @param req
     * @param res
     * @return ModelAndView
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @description:代理人查询列表-查询
     */
    @RequestMapping("/PolicyManagerPerson/QueryPolicyList.do")
    public ModelAndView queryPolicyMsgList(HttpServletRequest req, HttpServletResponse res)
            throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        ReturnMsg returnMsg = new ReturnMsg();
        IPolicyManagerPersonModel model = new IPolicyManagerPersonModel();
        model = (IPolicyManagerPersonModel) ModelHelper.getModel(model, req);

        IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
        model.setUser_jurisdiction(userModel.getEmp_id());

        returnMsg = policyService.policyQueryList(model);

        req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
        return returnPage(res, returnMsg, "newtouch/policymanager/policymanagerperson/policyInfo");
    }

    @RequestMapping("/PolicyManagerPerson/QueryPolicyMsg.do")
    public ModelAndView queryPolicyMsg(HttpServletRequest req, HttpServletResponse res)
            throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        IPolicyManagerPersonModel model = new IPolicyManagerPersonModel();
        String policy_no = String.valueOf(ActionHelper.getNullToStr(req.getParameter("policy_no")));
        model.setPolicy_no(policy_no);
        ReturnMsg returnMsg = policyService.policyQueryMsg(model);
        ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
        rmHelper.setReturnParams(returnMsg.getDataTable());
        req.setAttribute("rmHelper", rmHelper);
        return new ModelAndView("newtouch/policymanager/policymanagerperson/policyInfoView");
    }


    // 下载
    @RequestMapping("/PolicyManagerPerson/DownloadExcel.do")
    public void downloadExceltest(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook(); // 定义一个新的工作簿
        HSSFSheet sheet = wb.createSheet("第一个Sheet页"); // 创建第一个Sheet页
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

        HashMap<String, String> interMap = cmainPolicyDomain.findCodeToMap("ins_cmain.inter_flag");
        HashMap<String, String> businessMap = cmainPolicyDomain.findCodeToMap("ins_cmain.business_flag");

        List<Object[]> list = new ArrayList<Object[]>();//
        int currentRowNum = 0;// 从第几行开始写
        int currentColNum = 0;// 从第几列开始写
        Row row1 = sheet.createRow(0); // 创建第二个行
        /**
         * 序号 保险公司机构 中介公司机构代码 归属人姓名 归属人工号 代理人姓名 渠道类型 险类名称 险种名称 签单日期 保单号 保费 核保日期 起保日期
         * 终保日期 渠道码
         */
        row1.createCell(0).setCellValue("序号");
        row1.createCell(1).setCellValue("保险公司省级机构");
        row1.createCell(2).setCellValue("保险公司省级机构代码");
        row1.createCell(3).setCellValue("保险公司地市机构");
        row1.createCell(4).setCellValue("保险公司地市机构代码");
        row1.createCell(5).setCellValue("保险公司县支公司");
        row1.createCell(6).setCellValue("保险公司县支公司代码");
        row1.createCell(7).setCellValue("中介公司机构名称");
        row1.createCell(8).setCellValue("中介公司渠道代码");
        row1.createCell(9).setCellValue("保单号");
        row1.createCell(10).setCellValue("产品代码");
        row1.createCell(11).setCellValue("产品名称");
        row1.createCell(12).setCellValue("产品类型");
        row1.createCell(13).setCellValue("长短险标志");
        row1.createCell(14).setCellValue("新单续收标识");
        row1.createCell(15).setCellValue("投保人姓名");
        row1.createCell(16).setCellValue("投保人身份证号");
        row1.createCell(17).setCellValue("被保险人");
        row1.createCell(18).setCellValue("被保险险人身份证号");
        row1.createCell(19).setCellValue("保费收入(元)");
        row1.createCell(20).setCellValue("保额(元)");
        row1.createCell(21).setCellValue("保险期类型");
        row1.createCell(22).setCellValue("保险期间");
        row1.createCell(23).setCellValue("缴费方式");
        row1.createCell(24).setCellValue("付款方式名称");
        row1.createCell(25).setCellValue("缴费年限");
        row1.createCell(26).setCellValue("保单年度");
        row1.createCell(27).setCellValue("佣金费率");
        row1.createCell(28).setCellValue("佣金(元)");
        row1.createCell(29).setCellValue("业务生效日期");
        row1.createCell(30).setCellValue("承保日期");
        row1.createCell(31).setCellValue("客户签收日期");
        row1.createCell(32).setCellValue("回执核销日期");
        row1.createCell(33).setCellValue("回访成功日期");
        row1.createCell(34).setCellValue("中介公司归属人员姓名");
        row1.createCell(35).setCellValue("中介公司归属人员编码");
        row1.createCell(36).setCellValue("代理人佣金支付比例");
        row1.createCell(37).setCellValue("代理人佣金金额");
        row1.createCell(38).setCellValue("批单号");
        row1.createCell(39).setCellValue("批改日期");
        row1.createCell(40).setCellValue("批改生效日期");
        row1.createCell(41).setCellValue("数量变化");
        row1.createCell(42).setCellValue("结算时间");

        sheet.setColumnWidth(1, 6300);
        sheet.setColumnWidth(2, 3300);
        sheet.setColumnWidth(3, 3300);
        sheet.setColumnWidth(5, 3300);
        sheet.setColumnWidth(6, 3300);
        sheet.setColumnWidth(7, 3300);
        sheet.setColumnWidth(8, 3300);
        sheet.setColumnWidth(9, 3300);
        sheet.setColumnWidth(10, 3300);
        sheet.setColumnWidth(11, 3300);
        sheet.setColumnWidth(12, 3300);
        sheet.setColumnWidth(13, 3300);
        sheet.setColumnWidth(14, 3300);
        sheet.setColumnWidth(15, 3300);
        sheet.setColumnWidth(16, 3000);
        sheet.setColumnWidth(17, 3000);
        sheet.setColumnWidth(18, 3000);
        sheet.setColumnWidth(19, 3000);
        sheet.setColumnWidth(20, 3000);
        sheet.setColumnWidth(21, 3000);
        sheet.setColumnWidth(22, 3000);
        sheet.setColumnWidth(23, 3000);
        sheet.setColumnWidth(24, 3000);
        sheet.setColumnWidth(25, 3000);
        sheet.setColumnWidth(26, 3000);
        sheet.setColumnWidth(27, 3000);
        sheet.setColumnWidth(28, 3000);
        sheet.setColumnWidth(29, 3000);
        sheet.setColumnWidth(30, 3000);
        sheet.setColumnWidth(31, 3000);
        sheet.setColumnWidth(32, 3000);
        sheet.setColumnWidth(33, 3000);
        sheet.setColumnWidth(34, 3000);
        sheet.setColumnWidth(35, 3000);
        sheet.setColumnWidth(36, 3000);
        sheet.setColumnWidth(37, 3000);
        sheet.setColumnWidth(38, 3000);
        sheet.setColumnWidth(39, 3000);
        sheet.setColumnWidth(40, 3000);
        sheet.setColumnWidth(41, 3000);
        sheet.setColumnWidth(42, 3000);


        IPolicyManagerPersonModel model = new IPolicyManagerPersonModel();
        model = (IPolicyManagerPersonModel) ModelHelper.getModel(model, req);
        //加权限
        IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
        model.setUser_jurisdiction(userModel.getEmp_id());

        PageCount pageCount = model.getPageCount();
        pageCount.setNowPage(0);
        pageCount.setLimit(60000);
        pageCount.setStart(0);
        pageCount.setRows4Page(0);
        pageCount.setPage(false);
        List<IPolicyManagerPersonModel> relist = policyService.policyQueryForExcel(model);
        pageCount.setPage(true);

        for (int i = 0; i < relist.size(); i++) {
            row1 = sheet.createRow(i + 1);
            IPolicyManagerPersonModel m = relist.get(i);
            row1.createCell(0).setCellValue(i + 1);
            row1.createCell(1).setCellValue(m.getProvince_orgname());
            row1.createCell(2).setCellValue(m.getProvince_orgid());
            row1.createCell(3).setCellValue(m.getCity_orgname());
            row1.createCell(4).setCellValue(m.getCity_orgid());
            row1.createCell(5).setCellValue(m.getArea_orgname());
            row1.createCell(6).setCellValue(m.getArea_orgid());
            row1.createCell(7).setCellValue(m.getSale_org_name());
            row1.createCell(8).setCellValue(m.getSale_org_id());
            row1.createCell(9).setCellValue(m.getPolicy_no());
            row1.createCell(10).setCellValue(m.getCenter_risk_id());
            row1.createCell(11).setCellValue(m.getCenter_risk_name());
            row1.createCell(12).setCellValue(m.getCenter_risk_type());
            row1.createCell(13).setCellValue(m.getLong_risk_flag());
            row1.createCell(14).setCellValue(m.getRenew_flag());
            row1.createCell(15).setCellValue(m.getApplicant_name());
            row1.createCell(16).setCellValue(m.getApplicant_id());
            row1.createCell(17).setCellValue(m.getInsured_name());
            row1.createCell(18).setCellValue(m.getInsured_id());
            row1.createCell(19).setCellValue(formatDouble(m.getPremium()));
            row1.createCell(20).setCellValue(formatDouble(m.getAmount()));
            row1.createCell(21).setCellValue(m.getInsured_during_flag());
            row1.createCell(22).setCellValue(m.getInsured_during());
            row1.createCell(23).setCellValue(m.getPay_fee_type());
            row1.createCell(24).setCellValue(m.getPay_type());
            row1.createCell(25).setCellValue(m.getPay_fee_year());
            row1.createCell(26).setCellValue(m.getRisk_year());
            row1.createCell(27).setCellValue(formatDouble(m.getFee_ratio()));
            row1.createCell(28).setCellValue(formatDouble(m.getNew_paper_fee()));
            row1.createCell(29).setCellValue(formatDate(m.getIns_validate()));
            row1.createCell(30).setCellValue(formatDate(m.getInsure_date()));
            row1.createCell(31).setCellValue(formatDate(m.getPaper_date()));
            row1.createCell(32).setCellValue(formatDate(m.getCancel_date()));
            row1.createCell(33).setCellValue(formatDate(m.getVisit_date()));
            row1.createCell(34).setCellValue(m.getOwner_name());
            row1.createCell(35).setCellValue(m.getOwner_no());
            row1.createCell(36).setCellValue(formatDouble(m.getAgent_fee_rate()));
            row1.createCell(37).setCellValue(formatDouble(m.getAgent_fee()));
            row1.createCell(38).setCellValue(m.getEndor_no());
            row1.createCell(39).setCellValue(formatDate(m.getEndor_date()));
            row1.createCell(40).setCellValue(formatDate(m.getEndor_valid()));
            if (null != m.getChange_nu()) {
                row1.createCell(41).setCellValue(m.getChange_nu());
            }
            row1.createCell(42).setCellValue(m.getSettled_time());
        }
        // 设置response 这样就可以前台弹出框进行下载了
        String fileName = URLEncoder.encode("保单基本信息查询结果导出.xls", "UTF-8");
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.writeExcel(list, currentRowNum, currentColNum, wb, sheet, fileName, res);
    }

    String formatDate(java.sql.Date date) {
        String returnString = "";
        if (null != date) {
            returnString = sdf2.format(date);
        }
        return returnString;
    }

    String formatDouble(Double doubleDouble) {
        String returnString = "";
        if (null != doubleDouble) {
            returnString = doubleDouble.toString();
        }
        return returnString;
    }

}
