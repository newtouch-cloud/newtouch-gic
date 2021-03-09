package com.newtouch.protocol.webapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.ModelHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c1properties.StaticProperties;
import com.newtouch.component.c4fileDownload.FileDownLoadUtil;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.PathFactory;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.enumpak.vo.EnumEntity;
import com.newtouch.enumpak.webaoo.service.EnumService;
import com.newtouch.protocol.dao.IProtocolManageDao;
import com.newtouch.protocol.model.vo.ContractType;
import com.newtouch.protocol.model.vo.IProtocolManageModel;
import com.newtouch.protocol.model.vo.ProtocolCategoryModel;
import com.newtouch.protocol.model.vo.ProtocolManageModel;
import com.newtouch.protocol.webapp.service.IProtocolManageService;
import com.newtouch.newutil.lang.Constants;

import net.sf.json.JSONArray;

@Controller
public class ProtocolManageController extends BaseController {

	/**
	 * 定义报表表头
	 */
	public static final String[] PROTOCOLT_NAME = { "协议号", "协议类型", "合同类型", "中介公司所属机构编码", "中介公司所属机构", "甲方", "乙方","丙方",
			"归属保险公司机构编码", "归属保险公司机构名称", "代理推荐维修码", "代理企业类型", "代理社会统一信用代码", "汽车信息咨询", "代理开户行", "代理银行账号", "代理联系人",
			"代理联系人电话", "合同金额",
			"协议签订日期", "协议生效起期", "协议生效止期",// "补充协议编号", "补充协议内容", "补充协议生效日期",
			"协议状态", "签订类型","合同金额","备注" };

	/**
	 * 定义报表表头参数
	 */
	public static final String[] PROTOCOLT_TITLE = { "agreement_no", // 协议号
			"protocol_category", // 协议类型
			"contract_type", // 合同类型
			"branch_id", // 中介公司所属机构编码
			"branch_name", // 中介公司所属机构
			"party_a", // 甲方
			"party_b", // 乙方
			"party_c", // 乙方
			"ins_branch", // 乙方归属保险公司机构编码
			"ins_branchname", // 乙方归属保险公司机构名称
			"push_code", // 乙方推荐维修码
			"enterprise_type", // 乙方企业类型
			"credit_code", // 乙方社会统一信用代码
			"isconsult", // 汽车信息咨询
			"bank_name", // 乙方开户行
			"bank_code", // 乙方银行账号
			"party_b_name", // 乙方联系人
			"party_b_phone", // 乙方联系人电话
		     "amount", // 合同金额
			"dateofsign", // 协议签订日期
			"startdate", // 协议生效起期
			"enddate", // 协议生效止期
		//	"sup_protocol_no", // 补充协议编号
		//	"sup_protocol_content", // 补充协议内容
		//	"sup_protocol_stadate", // 补充协议生效日期
			"status", // 协议状态
			"sign_type",// 签订类型
			"amount",//合同金额
			 "remarks"//备注
	};

	@Autowired
	IProtocolManageService protocolManageService;
	@Autowired
	private ICommonSeqDao seqDao;
	@Autowired
	private IProtocolManageDao protocolManageDao;
	@Autowired
	private EnumService enumServiceImpl;

	/**
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @description:协议管理-跳转页面-查询
	 */
	@RequestMapping("/organization/Protocol/goProtocolList.do")
	public ModelAndView goProtocolList(HttpServletRequest req, HttpServletResponse res) {
		// 查询条件下拉框-协议类型
		List<ProtocolCategoryModel> listPro = protocolManageService.findCategory();
		// 查询条件下拉框-协议状态
		List<EnumEntity> listStatus = enumServiceImpl.findEnum(Constants.PROTOCOL_STATUS);
		// 查询条件下拉框-签订类型
		List<EnumEntity> listSignType = enumServiceImpl.findEnum(Constants.PROTOCOL_SIGN_TYPE);
		HttpSession session = req.getSession();
		session.setAttribute("listPro", listPro);
		session.setAttribute("listStatus", listStatus);
		session.setAttribute("listSignType", listSignType);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
		return new ModelAndView("newtouch/organization/protocol/protocolQuery");
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 * @description:协议管理-查询页面
	 */
	@RequestMapping("/organization/Protocol/queryProtocolList.do")
	public ModelAndView queryProtocolList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel usermodel = ServletHelper.getSessionUser(req);
		IProtocolManageModel model = new ProtocolManageModel();
		model = (IProtocolManageModel) ModelHelper.getModel(model, req);
		model.setEmp_id(usermodel.getEmp_id());
		model.setdept_list(usermodel.getDept_list());
		// 调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg = protocolManageService.queryProtocol(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg, "newtouch/organization/protocol/protocolQuery");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 * @description:协议管理-注销 by sunhao
	 */
	@RequestMapping("/organization/Protocol/upProStatus.do")
	public ModelAndView upProStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// String
		// agreement_no=ActionHelper.getNullToStr(request.getParameter("agreement_no"));//用agreement_no注销
		Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));// 用seq_id注销
		// 获取入参信息
		IProtocolManageModel model = new ProtocolManageModel();
		model.setSeq_id(seq_id);
		// model.setAgreement_no(agreement_no);
		// 调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		
		returnMsg = protocolManageService.getProtocolModifyView(model, "modify");
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("newtouch/organization/protocol/protocolReason");
	}
	/**
	 * 记录解除原因
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/organization/Protocol/upProReason.do")
	public ModelAndView upProReason(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));// 用seq_id注销
		// 获取入参信息
		IProtocolManageModel model = this.getProtocolManageModel(request);
		model.setSeq_id(seq_id);
		// model.setAgreement_no(agreement_no);
		// 调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg = protocolManageService.updateProtocolStatus(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("newtouch/organization/protocol/protocolReason");
	}

	/**
	 * 增加协议
	 */
	@RequestMapping("/organization/Protocol/addProtocol.do")
	public ModelAndView addProtocol(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IProtocolManageModel model = this.getProtocolManageModel(req);
		String seq_id = seqDao.queryCommonSeq("seq_id");
		model.setSeq_id(Integer.parseInt(seq_id));
		model.setAgreement_no(this.getAgreement_no(model.getProtocol_category(), model.getBranch_id()));
		ReturnMsg returnMsg = new ReturnMsg();
		List<IProtocolManageModel> list = new ArrayList<>();
		list.add(model);
		// 调用新增方法
		returnMsg = protocolManageService.addProtocol(list);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("newtouch/organization/protocol/protocolAddSuccess");
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @description:协议管理-增加
	 */
	// @RequestMapping("/organization/Protocol/addProtocol.do")
	// public ModelAndView AddProtocol(HttpServletRequest
	// req,HttpServletResponse res) throws Exception{
	// IProtocolManageModel model = this.getProtocolManageModel(req);
	// String
	// branch_name=ActionHelper.getNullToStr(req.getParameter("branch_name"));
	// Date
	// startdate=DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("startdate")));//生效日期
	// Date enddate
	// =DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("enddate")));//终止日期
	// Date
	// dateofsign=DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("dateofsign")));//协议签订日期
	// //获得branch_id
	// String
	// branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));
	// model.setBranch_id(branch_id);
	// model.setBranch_name(branch_name);
	// model.setDateofsign(dateofsign);
	// model.setStartdate(startdate);
	// model.setEnddate(enddate);
	//
	// ReturnMsg returnMsg=new ReturnMsg();
	// //returnMsg=protocolManageService.addProtocol(model);
	// /*try{
	// //String seq_id=seqDao.queryCommonSeq("seq_id");
	// ////上传保险公司协议附件 传入协议号
	//// returnMsg =
	// AttachmentUploadUtil.uploadAttachment(CodeTypeConst.ATTACHMENT_BUSTYPE_PROTOCOL,seq_id,req,res);
	// //保存文件操作
	// //model.setSeq_id(Integer.valueOf(seq_id));
	// returnMsg=protocolManageService.addProtocol(model);
	// }catch (BusinessException e) {
	// returnMsg.setFailMessage(e.getMessage());
	// }*/
	// ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
	// req.setAttribute("rmHelper", rmHelper);//保存成功带回成功信息,并刷新界面
	// return new ModelAndView("newtouch/organization/protocol/protocolAdd");
	//
	// }

	/**
	 * 保险公司协议号唯一性校验
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/organization/protocol/toCheckAgreement_no.do")
	public ModelAndView checkAgreement_no(HttpServletRequest request, HttpServletResponse response) {
		String agreement_no = ActionHelper.getNullToStr(request.getParameter("agreement_no"));
		String branch_id = ActionHelper.getNullToStr(request.getParameter("agreement_no"));
		IProtocolManageModel model = new ProtocolManageModel();
		model.setAgreement_no(agreement_no);
		model.setBranch_id(branch_id);
		boolean b = protocolManageDao.checkAgreement_no(model);
		try {
			response.getWriter().print(JSONArray.fromObject(b).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保险公司协议
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/organization/protocol/toCheckBranch.do")
	public ModelAndView checkBranch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));
		boolean b = protocolManageDao.checkBranch(branch_id);
		response.getWriter().print(JSONArray.fromObject(b).toString());
		return null;
	}

	/**
	 * 
	 * @param req
	 * @return IProtocolModel
	 * @throws ParseException 
	 * @description:协议管理-入参，反参-用于复用
	 */
	public IProtocolManageModel getProtocolManageModel(HttpServletRequest req) throws ParseException {
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));
		String startdate = ActionHelper.getNullToStr(req.getParameter("startdate"));
		String enddate = ActionHelper.getNullToStr(req.getParameter("enddate"));
		String dateofsign = ActionHelper.getNullToStr(req.getParameter("dateofsign"));
		String ins_branchs = ActionHelper.getNullToStr(req.getParameter("ins_branchs"));
		String insBranch_name = ActionHelper.getNullToStr(req.getParameter("insBranch_name"));
		String protocol_type = ActionHelper.getNullToStr(req.getParameter("protocol_type"));// 协议类型
		String protocol_category = ActionHelper.getNullToStr(req.getParameter("protocol_category"));// 合同类型
		String sign_type = ActionHelper.getNullToStr(req.getParameter("sign_type"));// 签订类型
		String push_code = ActionHelper.getNullToStr(req.getParameter("push_code"));// 签订类型
		String enterprise_type = ActionHelper.getNullToStr(req.getParameter("enterprise_type"));// 乙方企业类型
		String credit_code = ActionHelper.getNullToStr(req.getParameter("credit_code"));// 乙方社会信用码
		String isconsult = ActionHelper.getNullToStr(req.getParameter("isconsult"));// 是否汽车咨询
		String bank_name = ActionHelper.getNullToStr(req.getParameter("bank_name"));
		String bank_code = ActionHelper.getNullToStr(req.getParameter("bank_code"));
		String party_b_name = ActionHelper.getNullToStr(req.getParameter("party_b_name"));
		String party_b_phone = ActionHelper.getNullToStr(req.getParameter("party_b_phone"));
		String contract_type = ActionHelper.getNullToStr(req.getParameter("contract_type"));
		String party_a = ActionHelper.getNullToStr(req.getParameter("party_a"));
		String party_b = ActionHelper.getNullToStr(req.getParameter("party_b"));
		String party_c= ActionHelper.getNullToStr(req.getParameter("party_c"));
		String agreement_no = ActionHelper.getNullToStr(req.getParameter("agreement_no"));
		String status = ActionHelper.getNullToStr(req.getParameter("status"));
		String ins_branch = ActionHelper.getNullToStr(req.getParameter("ins_branch"));
		String ins_branchName = ActionHelper.getNullToStr(req.getParameter("ins_branchname"));
		String sup_protocol_no =  ActionHelper.getNullToStr(req.getParameter("sup_protocol_no"));
		String sup_protocol_content =  ActionHelper.getNullToStr(req.getParameter("sup_protocol_content"));
		String sup_protocol_stadate = ActionHelper.getNullToStr(req.getParameter("sup_protocol_stadate"));
		String reason = ActionHelper.getNullToStr(req.getParameter("reason"));
		String amount = ActionHelper.getNullToStr(req.getParameter("amount"));
		String remarks = ActionHelper.getNullToStr(req.getParameter("remarks"));
		
		IProtocolManageModel model = new ProtocolManageModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(null!=amount&&!"".equals(amount)){
			model.setAmount(Double.valueOf(amount));
		}
		model.setRemarks(remarks);
		model.setIns_branch(ins_branch);
		model.setIns_branchname(ins_branchName);
		model.setIsconsult(isconsult);
		model.setParty_a(party_a);
		model.setParty_b(party_b);
		model.setBranch_id(branch_id);
		model.setAgreement_no(agreement_no);
		model.setBranch_name(branch_name);
		model.setStartdate(startdate);
		model.setEnddate(enddate);
		model.setDateofsign(dateofsign);
		// model.setIns_branch(ins_branchs);
		model.setProtocol_type(protocol_type);
		model.setProtocol_category(protocol_category);
		model.setParty_c(party_c);
		model.setSign_type(sign_type);
		model.setPush_code(push_code);
		model.setEnterprise_type(enterprise_type);
		model.setCredit_code(credit_code);
		model.setIsconsult(isconsult);
		model.setBank_name(bank_name);
		model.setBank_code(bank_code);
		model.setParty_b_name(party_b_name);
		model.setParty_b_phone(party_b_phone);
		model.setContract_type(contract_type);
		model.setInsBranch_name(insBranch_name);
		model.setStatus(status);
		model.setSup_protocol_no(sup_protocol_no);
		model.setSup_protocol_content(sup_protocol_content);
		model.setReason(reason);
		if(null!=sup_protocol_stadate&&!"".equals(sup_protocol_stadate)){
			model.setSup_protocol_stadate(sdf.parse(sup_protocol_stadate));
		}
		return model;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 * @description:协议管理-修改-页面
	 */
	@RequestMapping("/organization/Protocol/toModifyProtocol.do")
	public ModelAndView getProtocolModifyView(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));// 用seq_id查询处理
		// 获取入参信息
		IProtocolManageModel model = new ProtocolManageModel();
		model.setSeq_id(seq_id);
		// 调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg = protocolManageService.getProtocolModifyView(model, "modify");
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("newtouch/organization/protocol/protocolModifyNew");
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 * @description:协议管理-修改
	 */
	@RequestMapping("/organization/Protocol/modifyProtocol.do")
	public ModelAndView modifyProtocol(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IProtocolManageModel model = this.getProtocolManageModel(req);
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));// 获取seq_id用于保存时返回数据
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			if ("0".equals(model.getSign_type())) {
				String seqId = seqDao.queryCommonSeq("seq_id");
				List<IProtocolManageModel> list = new ArrayList<>();
				model.setSeq_id(Integer.parseInt(seqId));
				model.setAgreement_no(this.getAgreement_no(model.getProtocol_category(), model.getBranch_id()));
				list.add(model);
				// 调用新增方法
				returnMsg = protocolManageService.addProtocol(list);
			} else if ("1".equals(model.getSign_type())) {
				returnMsg = protocolManageService.modifyProtocol(model);
			}
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		returnMsg.setDataTable(TransHelper.obj2map(protocolManageService.getProtocolModifyView(model, "modify")));
		ReturnMsgHelper returnMsgHelper = new ReturnMsgHelper(req, returnMsg, true);
		req.setAttribute("rmHelper", returnMsgHelper);
		return new ModelAndView("newtouch/organization/protocol/protocolModifyNew");
	}

	/**
	 * 续签按钮
	 */
	@RequestMapping("/organization/Protocol/upSignType.do")
	public ModelAndView getSignType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));// 用seq_id查询处理
		// 获取入参信息
		IProtocolManageModel model = new ProtocolManageModel();
		model.setSeq_id(seq_id);
		// 调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg = protocolManageService.getProtocolModifyView(model, "modify");
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("newtouch/organization/protocol/signTypeModify");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 * @description:协议管理-明细
	 */
	@RequestMapping("/organization/Protocol/goProtocolModifyView.do")
	public ModelAndView goProtocolModifyView(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));// 用seq_id查询处理
		// 获取入参信息
		IProtocolManageModel model = new ProtocolManageModel();
		model.setSeq_id(seq_id);
		// 调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg = protocolManageService.getProtocolModifyView(model, "modify");
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("newtouch/organization/protocol/protocolViewNew");
	}

	/**
	 * @author 导出协议数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/organization/Protocol/doExpProtocol.do", method = RequestMethod.POST)
	public ModelAndView dofindFundFirstInvestment(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> params = this.getRequestMap(request);
		IUserModel usermodel = ServletHelper.getSessionUser(request);
		IProtocolManageModel model = new ProtocolManageModel();
		model = (IProtocolManageModel) ModelHelper.getModel(model, request);
		model.setEmp_id(usermodel.getEmp_id());
		model.setdept_list(usermodel.getDept_list());
		List<Map<String, Object>> lists = protocolManageService.queryProtocolAll(model).getDataList();
		// 查询结果
		Map<String, List<Map<String, Object>>> excelMap = new Hashtable<String, List<Map<String, Object>>>();
		excelMap.put("协议信息", lists);
		// 维护表头信息（支持多行表头）
		// 把表头添加到List中，如果有多行表头就添加多个
		List<LinkedHashMap<String, String>> sheetHead = genExcelHead(params, PROTOCOLT_TITLE, PROTOCOLT_NAME);
		// 设置表头与数据对应的对象
		Map<String, List<LinkedHashMap<String, String>>> map = new Hashtable<String, List<LinkedHashMap<String, String>>>();
		map.put("协议信息", sheetHead);
		request.setAttribute("excelName", "Protoclt" + DateUtil.sysDate4Str());
		request.setAttribute("ExcelHead", map);
		request.setAttribute("ExcelData", excelMap);
		return new ModelAndView("newtouch/excelexp/ExcelExp");
	}

	/**
	 * 生成协议号
	 */
	public String getAgreement_no(String protocol_type, String branch_id) {
		String agreement_no = "";
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		// 获取当前的合同类型
		if (protocol_type.length() < 2) {
			agreement_no = "0" + protocol_type;
		} else {
			agreement_no = protocol_type;
		}
		/*if (branch_id.length() > 4) {
			branch_id = branch_id.substring(0, 7);
		}*/
		agreement_no += branch_id + year;
		// 处理当前表中数据
		ProtocolManageModel model = protocolManageDao.findAgreementNo();
		String agreement_code;
		if (null != model) {
			Integer i = Integer.parseInt(model.getAgreement_no()) + 1;
			agreement_code = i + "";
			while (agreement_code.toString().length() < 5) {
				agreement_code = "0" + agreement_code;
			}
		}else{
			agreement_code = "00001";
		}
		return agreement_no + agreement_code;
	}

	/**
	 * 导出
	 * 
	 * @param map
	 * @return
	 */
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

	/**
	 * 导入功能页面跳转
	 */
	@RequestMapping(value = "/organization/Protocol/goImpProtocol.do", method = RequestMethod.GET)
	public String goImpProtocol(HttpServletRequest req) {
		String protocol_category = req.getParameter("protocol_category");
		if ("1".equals(protocol_category)) {
			return "newtouch/organization/protocol/protocolBXUpLoad";
		} else if ("2".equals(protocol_category)) {
			return "newtouch/organization/protocol/protocolCSUpLoad";
		} else if ("3".equals(protocol_category)) {
			return "newtouch/organization/protocol/protocolQTUpLoad";
		}
		return "";
	}

	@RequestMapping("/organization/Protocol/upload2.do")
	public void upload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/json;charset=UTF-8");
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		
		String protocol_category = req.getParameter("protocol_category");
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		IUserModel user = ServletHelper.getSessionUser(req);
		String info = "";
		try {
			for (MultipartFile file : files.values()) {
				System.out.println(file.getOriginalFilename());
				info = protocolManageService.importProtocol(file, user,protocol_category);
			}
			resp.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \"" + info + "\"}");
		} catch (IOException e) {
			e.printStackTrace();
			resp.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
		}
	}
	@RequestMapping("/organization/Protocol/uploadBx.do")
	public void uploadBx(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/json;charset=UTF-8");
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		IUserModel user = ServletHelper.getSessionUser(req);
		String info = "";
		try {
			for (MultipartFile file : files.values()) {
				System.out.println(file.getOriginalFilename());
				info = protocolManageService.importProtocol(file, user,"1");
			}
			resp.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \"" + info + "\"}");
		} catch (IOException e) {
			e.printStackTrace();
			resp.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
		}
	}
	
	@RequestMapping("/organization/Protocol/uploadCs.do")
	public void uploadCs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/json;charset=UTF-8");
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		IUserModel user = ServletHelper.getSessionUser(req);
		String info = "";
		try {
			for (MultipartFile file : files.values()) {
				System.out.println(file.getOriginalFilename());
				info = protocolManageService.importProtocol(file, user,"2");
			}
			resp.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \"" + info + "\"}");
		} catch (IOException e) {
			e.printStackTrace();
			resp.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
		}
	}

	@RequestMapping("/organization/Protocol/uploadQt.do")
	public void uploadQt(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/json;charset=UTF-8");
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		IUserModel user = ServletHelper.getSessionUser(req);
		String info = "";
		try {
			for (MultipartFile file : files.values()) {
				System.out.println(file.getOriginalFilename());
				info = protocolManageService.importProtocol(file, user,"3");
			}
			resp.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \"" + info + "\"}");
		} catch (IOException e) {
			e.printStackTrace();
			resp.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
		}
	}
	/**
	 * 导入pdf文件
	 */
	@RequestMapping(value = "/organization/Protocol/goImpProtocolPdf.do", method = RequestMethod.GET)
	public ModelAndView goImpProtocolPdf(HttpServletRequest req, HttpServletResponse resp) {
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IProtocolManageModel model = new ProtocolManageModel();
		model.setSeq_id(seq_id);
		// 查询当前pdf的地址
		ReturnMsg msg = protocolManageService.getProtocolModifyView(model, "");
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, msg);
		req.setAttribute("rmHelper", rmHelper.setReturnParams(msg.getDataTable()));
		return new ModelAndView("newtouch/organization/protocol/protocolUploadPdfNew");
	}

	/**
	 * 导入合同文件
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping("/organization/Protocol/impProtocolPdf.do")
	public ModelAndView importbranchIMgORPdf(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		res.setContentType("text/json;charset=UTF-8");
		IUserModel userModel = ServletHelper.getSessionUser(req);// 浠巗ession鑾峰彇user淇℃伅

		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 寰楀埌鏂囦欢map瀵硅薄
		IProtocolManageModel model = new ProtocolManageModel();

		model.setSeq_id(seq_id);
		String info = "";
		List<String> listsmsgInfo = new ArrayList<>();
		ReturnMsg msg = new ReturnMsg();
		try {
			for (MultipartFile mulfile : files.values()) {
				System.out.println(mulfile.getOriginalFilename());
				String originalFilename = "Agreement" + DateHelper.getSysStr("yyyyMMddHHmmss") + ".jpg";
				String savePath = PathFactory.getSource() + "/upload/";
				String p = savePath + originalFilename;
				model.setPicture_path(p);
				String re = "";
				// branchService.importbranchIMgORPdf(model);
				// 上传pdf文件，保存路径
				protocolManageService.addProtocolPdf(model);
				msg = protocolManageService.getProtocolModifyView(model, "");
				// if("1".equals(re)) {
				File file = new File(savePath, originalFilename);
				FileOutputStream fop = new FileOutputStream(file);
				if (!file.exists()) {
					file.createNewFile();
				}
				byte[] contentInBytes = mulfile.getBytes();
				fop.write(contentInBytes);
				fop.flush();
				fop.close();

				msg.setSuccessMessage("协议合同上传成功");
				// req.setAttribute("seq_id", seq_id);
				// res.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\":
				// \""+"导入pdf文件成功"+"\"}");
				// break;
				// }
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setFailMessage(e.getMessage());
			// res.getWriter().write("{\"ret\":
			// \"-0001\",\"uploadMsg\":\"导入pdf文件失败\"}");
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, msg);
		req.setAttribute("rmHelper", rmHelper.setReturnParams(msg.getDataTable()));

		return returnPage(res, msg, "newtouch/organization/protocol/protocolUploadPdfNew");
	}

	@RequestMapping("/organization/Protocol/downImg.do")
	public void downImg(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String file_name = new String(ActionHelper.getNullToStr(req.getParameter("fileName")).getBytes("ISO-8859-1"),
				"utf-8"); // 闄勪欢id
		/* /CACore/upload/ */
		String downloadPath = StaticProperties.getProperty("upload");
		File file = new File(file_name); // file_name
		String name = file_name.substring(file_name.lastIndexOf("/") + 1);
		FileDownLoadUtil.downloadZipNew(file, name, resp);
	}

	/**
	 * 获取协议类型和合同类型
	 */
	@RequestMapping(value = "/organization/Protocol/treeList.do", method = RequestMethod.GET)
	public ModelAndView getProtocolType(HttpServletRequest request, HttpServletResponse response) {
		// 协议类型
		List<ProtocolCategoryModel> listPro = new ArrayList<>();
		// 合同类型
		List<ContractType> listCon = new ArrayList<>();
		// 获取协议信息
		listPro = protocolManageService.findCategory();
		// 获取合信息
		listCon = protocolManageService.findContract();
		List<Map<String, Object>> listMap = new ArrayList<>();
		for (ProtocolCategoryModel proModel : listPro) {
			List<Map<String, Object>> contractList = new ArrayList<>();
			for (ContractType contract : listCon) {
				if (proModel.getCode().equals(contract.getParentId())) {
					Map<String, Object> conMap = new HashMap<>();
					conMap.put("conCode", contract.getCode());
					conMap.put("conName", contract.getName());
					contractList.add(conMap);
				}
			}
			Map<String, Object> map = new HashMap<>();
			map.put("proCode", proModel.getCode());
			map.put("proName", proModel.getName());
			map.put("contractList", contractList);
			listMap.add(map);
		}
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataList(listMap);
		// 转换为json
		String json = BaseController.return2Json(returnMsg);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取协议类型
	 */
	@RequestMapping("/organization/Protocol/proCategory.do")
	public ModelAndView getProtocolCategory(HttpServletRequest request, HttpServletResponse response) {
		List<EnumEntity> list = new ArrayList<>();
		EnumEntity entity = new EnumEntity();
		entity.setUpEnum(Constants.PROTOCOL_CATEGORY);
		list = enumServiceImpl.findEnumPro(entity);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataList(this.getEnumEntity(list));
		String json = BaseController.return2Json(returnMsg);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据协议类型查询合同类型
	 */
	@RequestMapping(value = "/organization/Protocol/proCategoryType.do")
	public ModelAndView getProtocolCategoryType(HttpServletRequest request, HttpServletResponse response) {
		List<EnumEntity> list = new ArrayList<>();
		String proCategory = request.getParameter("proCategory");
		EnumEntity entity = new EnumEntity();
		entity.setUpEnum(Constants.CONTRACT_TYPE);
		entity.setRemark(proCategory);
		list = enumServiceImpl.findEnumPro(entity);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataList(this.getEnumEntity(list));
		String json = BaseController.return2Json(returnMsg);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取协议状态
	 */
	@RequestMapping("/organization/Protocol/proStatus.do")
	public ModelAndView getProtocolStatus(HttpServletRequest request, HttpServletResponse response) {
		List<EnumEntity> list = new ArrayList<>();
		list = enumServiceImpl.findEnum(Constants.PROTOCOL_STATUS);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataList(this.getEnumEntity(list));
		String json = BaseController.return2Json(returnMsg);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取签订类型
	 */
	@RequestMapping("/organization/Protocol/proSignType.do")
	public ModelAndView getProtocolSignType(HttpServletRequest request, HttpServletResponse response) {
		List<EnumEntity> list = new ArrayList<>();
		list = enumServiceImpl.findEnum(Constants.PROTOCOL_SIGN_TYPE);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataList(this.getEnumEntity(list));
		String json = BaseController.return2Json(returnMsg);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实体类转换为map类型
	 */
	public List<Map<String, Object>> getEnumEntity(List<EnumEntity> list) {
		List<Map<String, Object>> listMap = new ArrayList<>();
		for (EnumEntity entity : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", entity.getCode());
			map.put("name", entity.getName());
			listMap.add(map);
		}
		return listMap;
	}

}
