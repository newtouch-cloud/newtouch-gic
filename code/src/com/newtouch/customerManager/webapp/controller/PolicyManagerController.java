package com.newtouch.customerManager.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.customerManager.model.IPolicyManagerModel;
import com.newtouch.customerManager.webapp.service.IPolicyManagerService;

@Controller

public class PolicyManagerController extends BaseController {


    /**
     * 定义报表表头
     */
    public static final String[] POLICY_NAME = {
            "保险公司区县机构代码",
            "保险公司区县机构名称",
            /*"财险归属部门代码",
            "财险归属部门名称",
            "归属人姓名",
            "归属人工号",*/
            "经办人工号",
            "经办人姓名",
            "渠道代码",
            "险类名称",
            "险类代码",
            "险种名称",
            "险种代码",
            "签单日期",
            "投保单号",
            "保单号",
            "投保人姓名",
            "被保险人姓名",
            "被保险人身份证",
            "被保险人单位性质",
            "被保险人证件类型（代码/名称）",
            "被保险人地址",
            "被保险人邮箱",
            "被保险人手机",
            "被保险人固话",
            "车牌号",
            "车型编码",
            "车型名称",
            "车架号（vin码）",
            "发动机号",
            "车辆种类",
            "使用性质",
            "初登日期",
            "条款类型",
            "保额",
            "保费增值税",
            "总保费",
            "净保费（不含税）",
            "车船税",
            /*"车行名称",
            "推荐维修码",*/
            "核保日期",
            "起保日期",
            "终保日期",
            "跟单手续费比例",
            "跟单手续费",
            "新/续保标志",
            "保单打印日期",
            "保单生效日期（起报日期核保日期取大的）",
            "保险公司省级机构   名称",
            "保险公司省级机构   代码",
            "保险公司地市机构   名称",
            "保险公司地市机构   代码",
            "结算日期",
            "中介公司归属人员姓名",
            "中介公司归属人员编码",
            "代理人佣金支付比例",
            "代理人佣金金额",
            /*"业务类型",
            "互联网标识",*/
            "归属中介公司代码",
            "批单号",
            "数量变化",
            "批改日期",
            "批改生效日期"
            /*,"分单时间"*/
    };

    /**
     * 定义报表表头参数
     */


    public static final String[] POLICY_TITLE = {
            "area_orgid",//	财险区县机构代码
            "area_orgname",//	财险区县机构名称
            /*"org_id",//	财险归属经营单位代码
            "org_name",//	财险归属机构名称
            "person_name",//	（财险）归属人姓名
            "person_no",//	（财险）归属人工号*/
            "responsible_no",//	经办人工号
            "responsible_name",//	经办人姓名
            "channel_no",//	渠道代码
            "insurance_class_name",//	险类名称
            "insurance_class_no",//   险类代码
            "insurance_type_name",//	险种名称
            "insurance_type_no",//	险种代码
            "sign_date",//	签单日期
            "insure_no", //	投保单号
            "policy_no",//	保单号
            "applicant_name", //	投保人姓名
            "insured_name", //	被保险人姓名
            "insured_cid", //	被保险人身份证
            "insured_company_type", //	被保险人单位性质
            "insured_papertype", //	被保险人证件类型
            "insured_add", //	被保险人地址
            "insured_mailbox",//	被保险人邮箱
            "insured_phone",//	被保险人手机
            "insured_tel",//	被保险人固话
            "lpn",//	车牌号
            "car_type",//	车型编码
            "car_name",//	车型名称
            "vin",//	vin车架号
            "engine_no",//	发动机号
            "car_class",//	车辆种类
            "use_type",//	使用性质
            "first_date",//	初登日期
            "clause_type",//	条款类型
            "amount",//	保额
            "premium_tax",//	保费增值税
            "premium",//	保费
            "net_premium",//	净保费（不含税）
            "vehicel_tax",//	车船税
            /*"car_branchname", //	车行名称
            "repair_coding",//	推荐维修码*/
            "check_date", //	核保日期
            "begin_date", //	起保日期
            "end_date", //	终保日期
            "fee_ratio", //	中介公司跟单手续费比例
            "fee", //	中介公司跟单手续费
            "approval_flag", //	新/续保标志 ： 0：新 1：续
            "print_date",//	打印日期
            "ins_validate",//	保单生效日期（起报日期核保日期取大的）
            "province_orgname",//	财险省份机构名称
            "province_orgid", //	财险省份机构代码
            "city_orgname", //	财险市级机构名称
            "city_orgid",//	财险市级机构代码
            "settle_time",//结算日期
            "owner_name",//	（中介公司）业务归属人姓名
            "owner_no", //	（中介公司）业务归属人工号
            "wei_rate",//	代理人佣金比例
            "commission",//	代理人佣金
            /*"business_flag", //业务类型
            "inter_flag",//	互联网标识*/
            "ord_id",//	归属支公司代码
            "endor_no", //	批单号
            "change_nu",//	数量变化
            "endor_date",//	批改日期
            "endor_valid"//	批改生效日期
            //"settled_date"// 分单日期

    };

    @Autowired
    private IPolicyManagerService policyService;
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private ICmainPolicyDomain cmainPolicyDomain;

    /**
     * @param req
     * @param res sms/InsBranchManage/toInsBranchQuery.do
     * @return ModelAndView
     * @description:代理人查询-跳转页面-查询
     */
    @RequestMapping("/PolicyManager/goPolicyList.do")
    public ModelAndView goPolicyList(HttpServletRequest req, HttpServletResponse res) {
        //req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
        return new ModelAndView("newtouch/policymanager/policymanager/policyInfo");
    }

    //匹配业务类型
    @RequestMapping("/PolicyManager/uplodePolicy.do")
    public ModelAndView uplodePolicy(HttpServletRequest req, HttpServletResponse res) {
        return new ModelAndView("ca/cacore/cbs/policyLife/propertyInsuranceUpLoad");
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
    @RequestMapping("/PolicyManager/QueryPolicyList.do")
    public ModelAndView queryPolicyMsgList(HttpServletRequest req, HttpServletResponse res)
            throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        ReturnMsg returnMsg = new ReturnMsg();
        IPolicyManagerModel model = new IPolicyManagerModel();
        model = (IPolicyManagerModel) ModelHelper.getModel(model, req);

        IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
        model.setUser_jurisdiction(userModel.getEmp_id());
        returnMsg = policyService.policyQueryList(model);

        req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
        return returnPage(res, returnMsg, "newtouch/policymanager/policymanager/policyInfo");
    }

    @RequestMapping("/PolicyManager/QueryPolicyMsg.do")
    public ModelAndView queryPolicyMsg(HttpServletRequest req, HttpServletResponse res)
            throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        IPolicyManagerModel model = new IPolicyManagerModel();
        String policy_no = String.valueOf(ActionHelper.getNullToStr(req.getParameter("policy_no")));
        model.setPolicy_no(policy_no);
        ReturnMsg returnMsg = policyService.policyQueryMsg(model);
        ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
        rmHelper.setReturnParams(returnMsg.getDataTable());
        req.setAttribute("rmHelper", rmHelper);
        return new ModelAndView("newtouch/policymanager/policymanager/policyInfoView");
    }
    // 下载
	/*@RequestMapping("/PolicyManager/DownloadExcel.do")
	public void downloadExceltest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook(); // 定义一个新的工作簿
		HSSFSheet sheet = wb.createSheet("第一个Sheet页"); // 创建第一个Sheet页
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		
		HashMap<String,String> interMap = cmainPolicyDomain.findCodeToMap("ins_cmain.inter_flag"); 
		HashMap<String,String> businessMap =  cmainPolicyDomain.findCodeToMap("ins_cmain.business_flag");

		List<Object[]> list = new ArrayList<Object[]>();//
		int currentRowNum = 0;// 从第几行开始写
		int currentColNum = 0;// 从第几列开始写
		Row row1 = sheet.createRow(0); // 创建第二个行
		*/

    /**
     * 序号 保险公司机构 中介公司机构代码 归属人姓名 归属人工号 代理人姓名 渠道类型 险类名称 险种名称 签单日期 保单号 保费 核保日期 起保日期
     * 终保日期 渠道码
     *//*

		row1.createCell(0).setCellValue("序号");
		row1.createCell(1).setCellValue("财险区县机构代码"                               );  
		row1.createCell(2).setCellValue("财险区县机构名称"                               );
		row1.createCell(3).setCellValue("财险归属部门代码"                               );
		row1.createCell(4).setCellValue("财险归属部门名称"                               );
		row1.createCell(5).setCellValue("归属人姓名"                                     );
		row1.createCell(6).setCellValue("归属人工号"                                     );
		row1.createCell(7).setCellValue("经办人工号"                                     );
		row1.createCell(8).setCellValue("经办人姓名"                                     );
		row1.createCell(9).setCellValue("渠道代码"                                       );
		row1.createCell(10).setCellValue("险类名称"                                       );
		row1.createCell(11).setCellValue("险类代码"                                       );
		row1.createCell(12).setCellValue("险种名称"                                       );
		row1.createCell(13).setCellValue("险种代码"                                       );
		row1.createCell(14).setCellValue("签单日期"                                       );
		row1.createCell(15).setCellValue("投保单号"                                       );
		row1.createCell(16).setCellValue("保单号"                                         );
		row1.createCell(17).setCellValue("投保人姓名"                                     );
		row1.createCell(18).setCellValue("被保险人姓名"                                   );
		row1.createCell(19).setCellValue("被保险人身份证"                                 );
		row1.createCell(20).setCellValue("被保险人单位性质"                               );
		row1.createCell(21).setCellValue("被保险人证件类型（代码/名称）"                  );
		row1.createCell(22).setCellValue("被保险人地址"                                   );
		row1.createCell(23).setCellValue("被保险人邮箱"                                   );
		row1.createCell(24).setCellValue("被保险人手机"                                   );
		row1.createCell(25).setCellValue("被保险人固话"                                   );
		row1.createCell(26).setCellValue("车牌号"                                         );
		row1.createCell(27).setCellValue("车型编码"                                       );
		row1.createCell(28).setCellValue("车型名称"                                       );
		row1.createCell(29).setCellValue("车架号（vin码）"                                );
		row1.createCell(30).setCellValue("发动机号"                                       );
		row1.createCell(31).setCellValue("车辆种类"                                       );
		row1.createCell(32).setCellValue("使用性质"                                       );
		row1.createCell(33).setCellValue("初登日期"                                       );
		row1.createCell(34).setCellValue("条款类型"                                       );
		row1.createCell(35).setCellValue("保额"                                           );
		row1.createCell(36).setCellValue("保费增值税"                                     );
		row1.createCell(37).setCellValue("总保费"                                         );
		row1.createCell(38).setCellValue("净保费（不含税）"                               );
		row1.createCell(39).setCellValue("车船税"                                         );
		row1.createCell(40).setCellValue("车行名称"                                       );
		row1.createCell(41).setCellValue("推荐维修码"                                     );
		row1.createCell(42).setCellValue("核保日期"                                       );
		row1.createCell(43).setCellValue("起保日期"                                       );
		row1.createCell(44).setCellValue("终保日期"                                       );
		row1.createCell(45).setCellValue("跟单手续费比例"                                 );
		row1.createCell(46).setCellValue("跟单手续费"                                     );
		row1.createCell(47).setCellValue("新/续保标志"                                    );
		row1.createCell(48).setCellValue("保单打印日期"                                   );
		row1.createCell(49).setCellValue("保单生效日期（起报日期核保日期取大的）"         );
		row1.createCell(50).setCellValue("保险公司省级机构   名称"                        );
		row1.createCell(51).setCellValue("保险公司省级机构   代码"                        );
		row1.createCell(52).setCellValue("保险公司地市机构   名称"                        );
		row1.createCell(53).setCellValue("保险公司地市机构   代码"                        );
		row1.createCell(54).setCellValue("结算日期"                 );
		row1.createCell(55).setCellValue("中介公司归属人员姓名"                           );
		row1.createCell(56).setCellValue("中介公司归属人员编码"                           );
		row1.createCell(57).setCellValue("代理人佣金支付比例"                             );
		row1.createCell(58).setCellValue("代理人佣金金额"                                 );
		row1.createCell(59).setCellValue("业务类型"                                       );
		row1.createCell(60).setCellValue("互联网标识"                                     );
		row1.createCell(61).setCellValue("归属中介公司名称"                               );
		row1.createCell(62).setCellValue("批单号"                               );
		row1.createCell(63).setCellValue("数量变化"                               );
		row1.createCell(64).setCellValue("批改日期"                               );
		row1.createCell(65).setCellValue("批改生效日期"                               );

		sheet.setColumnWidth(1, 6300);
		sheet.setColumnWidth(2, 3300);
		sheet.setColumnWidth(3, 3300);
		sheet.setColumnWidth(5, 3300);
		sheet.setColumnWidth(6, 3300);
		sheet.setColumnWidth(7, 3300);
		sheet.setColumnWidth(8, 3300);
		sheet.setColumnWidth(9, 3300);
		sheet.setColumnWidth(10,3300);
		sheet.setColumnWidth(11,3300);
		sheet.setColumnWidth(12,3300);
		sheet.setColumnWidth(13,3300);
		sheet.setColumnWidth(14,3300);
		sheet.setColumnWidth(15,3300);
		sheet.setColumnWidth(16,3000);
		sheet.setColumnWidth(17,3000);
		sheet.setColumnWidth(18,3000);
		sheet.setColumnWidth(19,3000);
		sheet.setColumnWidth(20,3000);
		sheet.setColumnWidth(21,3000);
		sheet.setColumnWidth(22,3000);
		sheet.setColumnWidth(23,3000);
		sheet.setColumnWidth(24,3000);
		sheet.setColumnWidth(25,3000);
		sheet.setColumnWidth(26,3000);
		sheet.setColumnWidth(27,3000);
		sheet.setColumnWidth(28,3000);
		sheet.setColumnWidth(29,3000);
		sheet.setColumnWidth(30,3000);
		sheet.setColumnWidth(31,3000);
		sheet.setColumnWidth(32,3000);
		sheet.setColumnWidth(33,3000);
		sheet.setColumnWidth(34,3000);
		sheet.setColumnWidth(35,3000);
		sheet.setColumnWidth(36,3000);
		sheet.setColumnWidth(37,3000);
		sheet.setColumnWidth(38,3000);
		sheet.setColumnWidth(39,3000);
		sheet.setColumnWidth(40,3000);
		sheet.setColumnWidth(41,3000);
		sheet.setColumnWidth(42,3000);
		sheet.setColumnWidth(43,3000);
		sheet.setColumnWidth(44,3000);
		sheet.setColumnWidth(45,3000);
		sheet.setColumnWidth(46,3000);
		sheet.setColumnWidth(47,3000);
		sheet.setColumnWidth(48,3000);
		sheet.setColumnWidth(49,3000);
		sheet.setColumnWidth(50,3000);
		sheet.setColumnWidth(51,3000);
		sheet.setColumnWidth(52,3000);
		sheet.setColumnWidth(53,3000);
		sheet.setColumnWidth(54,3000);
		sheet.setColumnWidth(55,3000);
		sheet.setColumnWidth(56,3000);
		sheet.setColumnWidth(57,3000);
		sheet.setColumnWidth(58,3000);
		sheet.setColumnWidth(59,3000);
		sheet.setColumnWidth(60,3000);
		sheet.setColumnWidth(61,3000);
		sheet.setColumnWidth(62,3000);
		sheet.setColumnWidth(63,3000);
		sheet.setColumnWidth(64,3000);
		sheet.setColumnWidth(65,3000);
		
		IPolicyManagerModel model = new IPolicyManagerModel();
		model = (IPolicyManagerModel) ModelHelper.getModel(model, req);
		//加权限
		IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
		model.setUser_jurisdiction(userModel.getEmp_id());
		
		PageCount pageCount = model.getPageCount();
		pageCount.setNowPage(0);
		pageCount.setLimit(40000);
		pageCount.setStart(0);
		pageCount.setRows4Page(0);
		pageCount.setPage(false);
		List<IPolicyManagerModel> relist = policyService.policyQueryForExcel(model);
		pageCount.setPage(true);
		
		for (int i = 0; i < relist.size(); i++) {
			row1 = sheet.createRow(i+1);
			IPolicyManagerModel m = relist.get(i);
			row1.createCell(0) .setCellValue(i+1);
////			row1.createCell(1) .setCellValue(m.getBranch_name());
////			row1.createCell(2) .setCellValue(m.getBranch_id());
//			row1.createCell(3) .setCellValue(m.getPerson_name());
//			row1.createCell(4) .setCellValue(m.getPerson_no());
//			row1.createCell(5) .setCellValue(m.getPerson_name());
//			row1.createCell(6) .setCellValue(m.getChannel_type());
//			row1.createCell(7) .setCellValue(m.getInsurance_class_name());
//			row1.createCell(8) .setCellValue(m.getInsurance_type_name());
//			row1.createCell(9) .setCellValue(sdf2.format(m.getSign_date()));
//			row1.createCell(10).setCellValue(m.getPolicy_no());
//			row1.createCell(11).setCellValue(m.getPremium());
//			row1.createCell(12).setCellValue(sdf2.format(m.getCheck_date()));
//			row1.createCell(13).setCellValue(sdf2.format(m.getBegin_date()));
//			row1.createCell(14).setCellValue(sdf2.format(m.getEnd_date()));
//			row1.createCell(15).setCellValue(m.getChannel_no());
			
			row1.createCell(1).setCellValue (m.getArea_orgid());    
			row1.createCell(2).setCellValue (m.getArea_orgname());  
			row1.createCell(3).setCellValue (m.getOrg_id());  
			row1.createCell(4).setCellValue (m.getOrg_name());  
			row1.createCell(5).setCellValue (m.getPerson_name());  
			row1.createCell(6).setCellValue (m.getPerson_no());  
			row1.createCell(7).setCellValue (m.getResponsible_no());  
			row1.createCell(8).setCellValue (m.getResponsible_name());  
			row1.createCell(9).setCellValue (m.getChannel_type());  
			row1.createCell(9).setCellValue (m.getChannel_no());
			row1.createCell(10).setCellValue(m.getInsurance_class_name());  
			row1.createCell(11).setCellValue(m.getInsurance_class_no());  
			row1.createCell(12).setCellValue(m.getInsurance_type_name());  
			row1.createCell(13).setCellValue(m.getInsurance_type_no());  
			row1.createCell(14).setCellValue(formatDate(m.getSign_date()));  
			row1.createCell(15).setCellValue(m.getInsure_no());  
			row1.createCell(16).setCellValue(m.getPolicy_no());  
			row1.createCell(17).setCellValue(m.getApplicant_name());  
			row1.createCell(18).setCellValue(m.getInsured_name());  
			row1.createCell(19).setCellValue(m.getInsured_cid());  
			row1.createCell(20).setCellValue(m.getInsured_company_type());  
			row1.createCell(21).setCellValue(m.getInsured_papertype());  
			row1.createCell(22).setCellValue(m.getInsured_add());  
			row1.createCell(23).setCellValue(m.getInsured_mailbox());  
			row1.createCell(24).setCellValue(m.getInsured_phone());  
			row1.createCell(25).setCellValue(m.getInsured_tel());  
			row1.createCell(26).setCellValue(m.getLpn());  
			row1.createCell(27).setCellValue(m.getCar_type());  
			row1.createCell(28).setCellValue(m.getCar_name());  
			row1.createCell(29).setCellValue(m.getVin());  
			row1.createCell(30).setCellValue(m.getEngine_no());  
			row1.createCell(31).setCellValue(m.getCar_class());  
			row1.createCell(32).setCellValue(m.getUse_type());  
			row1.createCell(33).setCellValue(formatDate(m.getFirst_date()));  
			row1.createCell(34).setCellValue(m.getClause_type());  
			row1.createCell(35).setCellValue(m.getAmount());  
			row1.createCell(36).setCellValue(m.getPremium_tax());  
			row1.createCell(37).setCellValue(m.getPremium());  
			row1.createCell(38).setCellValue(m.getNet_premium());  
			row1.createCell(39).setCellValue(m.getVehicel_tax());  
			row1.createCell(40).setCellValue(m.getCar_branchname());  
			row1.createCell(41).setCellValue(m.getRepair_coding());  
			row1.createCell(42).setCellValue(formatDate(m.getCheck_date()));  
			row1.createCell(43).setCellValue(formatDate(m.getBegin_date()));  
			row1.createCell(44).setCellValue(formatDate(m.getEnd_date()));  
			row1.createCell(45).setCellValue(formatDouble(m.getFee_ratio()));  
			row1.createCell(46).setCellValue(formatDouble(m.getFee()));  
			row1.createCell(47).setCellValue(m.getApproval_flag());  
			row1.createCell(48).setCellValue(formatDate(m.getPrint_date()));  
			row1.createCell(49).setCellValue(formatDate(m.getIns_validate()));  
			row1.createCell(50).setCellValue(m.getProvince_orgname());  
			row1.createCell(51).setCellValue(m.getProvince_orgid());  
			row1.createCell(52).setCellValue(m.getCity_orgname());  
			row1.createCell(53).setCellValue(m.getCity_orgid());  
			row1.createCell(54).setCellValue(m.getSettled_date());  
			row1.createCell(55).setCellValue(m.getOwner_name());  
			row1.createCell(56).setCellValue(m.getOwner_no());  
			row1.createCell(57).setCellValue(formatDouble(m.getWei_rate()));  
			row1.createCell(58).setCellValue(formatDouble(m.getCommission()));  
			row1.createCell(59).setCellValue(businessMap.get(m.getBusiness_flag()));
			row1.createCell(60).setCellValue(interMap.get(m.getInter_flag()));
			row1.createCell(61).setCellValue(m.getOrd_id());  
			row1.createCell(62).setCellValue(m.getEndor_no());  
			row1.createCell(63).setCellValue(m.getChange_nu());  
			row1.createCell(64).setCellValue(formatDate(m.getEndor_date()));  
			row1.createCell(65).setCellValue(formatDate(m.getEndor_valid()));  
		}
		
		// 设置response 这样就可以前台弹出框进行下载了
		String fileName = URLEncoder.encode("保单基本信息查询结果导出.xls", "UTF-8");
		ExcelUtils excelUtils = new ExcelUtils();
		excelUtils.writeExcel(list, currentRowNum, currentColNum, wb, sheet, fileName, res);
	}*/
    @RequestMapping("/PolicyManager/DownloadExcel.do")
    public ModelAndView exportBranchInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, Object> params = this.getRequestMap(req);
        IPolicyManagerModel model = new IPolicyManagerModel();
        model = (IPolicyManagerModel) ModelHelper.getModel(model, req);
        //加权限
        IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
        model.setUser_jurisdiction(userModel.getEmp_id());
        PageCount pageCount = model.getPageCount();
        pageCount.setNowPage(0);
        pageCount.setLimit(60000);
        pageCount.setStart(0);
        pageCount.setRows4Page(0);
        pageCount.setPage(false);
        // 获取报表数据
        List<Map<String, Object>> list = policyService.policyQueryList(model).getDataList();
        pageCount.setPage(true);
        Map<String, List<Map<String, Object>>> excelMap = new Hashtable<String, List<Map<String, Object>>>();
        excelMap.put("保单基本信息", list);
        // 维护表头信息（支持多行表头）
        // 把表头添加到List中，如果有多行表头就添加多个
        List<LinkedHashMap<String, String>> sheetHead = genExcelHead(params, POLICY_TITLE, POLICY_NAME);
        // 设置表头与数据对应的对象
        Map<String, List<LinkedHashMap<String, String>>> map = new Hashtable<String, List<LinkedHashMap<String, String>>>();
        map.put("保单基本信息", sheetHead);
        req.setAttribute("excelName", "Policy" + DateUtil.sysDate4Str());
        req.setAttribute("ExcelHead", map);
        req.setAttribute("ExcelData", excelMap);
        return new ModelAndView("newtouch/excelexp/ExcelExp");
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

    private List<LinkedHashMap<String, String>> genExcelHead(Map<String, Object> map, String[] title,
                                                             String[] titleName) {
        map.put("fund_type", "1S");// 汇总
        LinkedHashMap<String, String> mergehead = new LinkedHashMap<String, String>();
        for (int i = 0; i < title.length; i++) {
            mergehead.put(title[i], titleName[i]);
        }
        // 把表头添加到List中，如果有多行表头就添加多个
        List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
        sheetHead.add(mergehead);
        return sheetHead;
    }
}
