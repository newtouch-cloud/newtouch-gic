package com.ca.cacore.dms.webapp.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.dms.model.bo.DocumentMagModel;
import com.ca.cacore.dms.model.bo.IDocumentMagModel;
import com.ca.cacore.dms.model.vo.DocumentMagVOModel;
import com.ca.cacore.dms.model.vo.IDocumentMagVOModel;
import com.ca.cacore.dms.webapp.service.IDocumentMagService;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.ModelHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.excel.ExcelUtil;
import com.newtouch.utils.excel.ExcelWrite;

/**
 * 单证管理
 * 
 * @author zdd03
 *
 */
@Controller
public class DocumentMagController extends BaseController {

	@Autowired
	private IDocumentMagService iDocumentMagService;

	/*
	 * @description:重定向到单证查询页面
	 */
	@RequestMapping("/DMS/docmanagent.do")
	public ModelAndView docmanagent(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, null, new PageCount(), true));
		return new ModelAndView("ca/cacore/dms/document/documentQuery");
	}

	@RequestMapping("/DMS/Querydocmanagent.do")
	public ModelAndView Querydocmanagent(HttpServletRequest req, HttpServletResponse res)
			throws UnsupportedEncodingException, SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {

		IUserModel user = ServletHelper.getSessionUser(req);
		String ccode = ActionHelper.getNullToStr(req.getParameter("ccode"));
		String apply_sysbranchname = ActionHelper.getNullToStr(req.getParameter("branch_name"));
		String ccodename = ActionHelper.getNullToStr(req.getParameter("ccodename"));
		String document_idcard = ActionHelper.getNullToStr(req.getParameter("document_idcard"));
		String receiptdate = ActionHelper.getNullToStr(req.getParameter("receiptdate"));
		String receiptdate1 = ActionHelper.getNullToStr(req.getParameter("receiptdate1"));
		String return_time = ActionHelper.getNullToStr(req.getParameter("return_time"));
		String return_time1 = ActionHelper.getNullToStr(req.getParameter("return_time1"));
		String applyuser_name = ActionHelper.getNullToStr(req.getParameter("applyuser_name"));
		String returnuser_name = ActionHelper.getNullToStr(req.getParameter("returnuser_name"));
		String applyuser_id = ActionHelper.getNullToStr(req.getParameter("applyuser_id"));
		String returnuser_code = ActionHelper.getNullToStr(req.getParameter("returnuser_code"));
		IDocumentMagModel model = new DocumentMagModel();
		if (apply_sysbranchname == null || "".equals(apply_sysbranchname)) {
			// 排除apply_sysbranchid默认为#,##,#的
		} else {
			model.setCcode(ccode);
		}

		model.setApply_sysbranchname(apply_sysbranchname);
		model.setCcodename(ccodename);
		model.setDocument_idcard(document_idcard);
		model.setReceiptdate(receiptdate);
		model.setReceiptdate1(receiptdate1);
		model.setReturn_time(return_time);
		model.setReturn_time1(return_time1);
		model.setApplyuser_name(applyuser_name);
		model.setReturnuser_name(returnuser_name);
		model.setModify_user(user.getEmp_id());
		model.setApplyuser_id(applyuser_id);
		model.setReturnuser_code(returnuser_code);
		model.setDept_list(user.getDept_list());
		/*IDocumentMagModel model = new DocumentMagModel();
		IDocumentMagModel model2 = (IDocumentMagModel) ModelHelper.getModel(model, req);*/
		model.setPageCount(ActionHelper.getPage(req));// 分页
		ReturnMsg returnMsg = iDocumentMagService.Querydocmanagent(model);
		 /*ReturnMsg returnMsg=new ReturnMsg(); */
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return new ModelAndView("ca/cacore/dms/document/documentQuery");
	}

	/**
	 * 导出
	 * 
	 * @param req
	 * @param res
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/DMS/exportdocmanagent.do")
	public void exportdocmanagent(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		IUserModel user = ServletHelper.getSessionUser(req);
		String ccode = ActionHelper.getNullToStr(req.getParameter("ccode"));
		String apply_sysbranchname = ActionHelper.getNullToStr(req.getParameter("branch_name"));
		String ccodename = ActionHelper.getNullToStr(req.getParameter("ccodename"));
		String document_idcard = ActionHelper.getNullToStr(req.getParameter("document_idcard"));
		String receiptdate = ActionHelper.getNullToStr(req.getParameter("receiptdate"));
		String receiptdate1 = ActionHelper.getNullToStr(req.getParameter("receiptdate1"));
		String return_time = ActionHelper.getNullToStr(req.getParameter("return_time"));
		String return_time1 = ActionHelper.getNullToStr(req.getParameter("return_time1"));
		String applyuser_name = ActionHelper.getNullToStr(req.getParameter("applyuser_name"));
		String returnuser_name = ActionHelper.getNullToStr(req.getParameter("returnuser_name"));
		IDocumentMagModel model = new DocumentMagModel();
		if (apply_sysbranchname == null || "".equals(apply_sysbranchname)) {
			// 排除apply_sysbranchid默认为#,##,#的
		} else {
			model.setCcode(ccode);
		}

		model.setApply_sysbranchname(apply_sysbranchname);
		model.setCcodename(ccodename);
		model.setDocument_idcard(document_idcard);
		model.setReceiptdate(receiptdate);
		model.setReceiptdate1(receiptdate1);
		model.setReturn_time(return_time);
		model.setReturn_time1(return_time1);
		model.setApplyuser_name(applyuser_name);
		model.setReturnuser_name(returnuser_name);
		model.setModify_user(user.getEmp_id());
		List<IDocumentMagModel> list = iDocumentMagService.exportdocmanagent(model);
		Map<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		// sheet
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			String a = j + 1 + "";
			IDocumentMagModel vo = list.get(j);
			hashMap.put("列" + 0, a);// 序号
			hashMap.put("列" + 1, vo.getReceiptdate());// 领用日期
			hashMap.put("列" + 2, vo.getApplyuser_id());// 领用人/使用人
			hashMap.put("列" + 3, vo.getApplyuser_name());// 领用人/使用人签名
			hashMap.put("列" + 4, vo.getApply_sysbranchname());// 领用机构
			hashMap.put("列" + 5, vo.getApplay_cpybranchname());// 保险公司
			hashMap.put("列" + 6, String.valueOf(vo.getDocument_idcard()));// 单证识别码
			hashMap.put("列" + 7, vo.getDocument_name());// 单证名称
			hashMap.put("列" + 8, String.valueOf(vo.getDocument_serial_beg()));// 单证流水号（起）
			hashMap.put("列" + 9, String.valueOf(vo.getDocument_serial_end()));// 单证流水号（止）
			hashMap.put("列" + 10, String.valueOf(vo.getApplay_num()));// 数量
			hashMap.put("列" + 11, vo.getReturn_time());// 交回时间
			hashMap.put("列" + 12, vo.getReturnuser_code());// 交回人代码
			hashMap.put("列" + 13, vo.getReturnuser_name());// 交回人姓名
			hashMap.put("列" + 14, vo.getReturn_sysbranchname());// 交回机构
			hashMap.put("列" + 15, vo.getReturn_cpybranchname());// 保险公司
			hashMap.put("列" + 16, vo.getRedocument_idcard());// 单证识别码
			hashMap.put("列" + 17, String.valueOf(vo.getRedocument_serial_beg()));// 单证流水号（起）
			hashMap.put("列" + 18, String.valueOf(vo.getRedocument_serial_end()));// 单证流水号（止）
			hashMap.put("列" + 19, String.valueOf(vo.getReturn_num()));// 数量
			hashMap.put("列" + 20, String.valueOf(vo.getUser_number()));// 其中：已使用
			hashMap.put("列" + 21, String.valueOf(vo.getObsolete_number()));// 其中：作废
			hashMap.put("列" + 22, String.valueOf(vo.getNot_used_number()));// 其中：未使用址
			hashMap.put("列" + 23, vo.getRemarks());// 备注
			sheetList.add(hashMap);
		}
		excelMap.put("单证管理信息表", sheetList);
		// 正常表头 注意列字不能少
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();

		LinkedHashMap<String, String> head0 = new LinkedHashMap<String, String>();
		head0.put("列" + 0, "序号");
		head0.put("列" + 1, "领用");
		head0.put("列" + 2, "");
		head0.put("列" + 3, "");
		head0.put("列" + 4, "");
		head0.put("列" + 5, "");
		head0.put("列" + 6, "");
		head0.put("列" + 7, "");
		head0.put("列" + 8, "");
		head0.put("列" + 9, "");
		head0.put("列" + 10, "");
		head0.put("列" + 11, "交换");
		head0.put("列" + 12, "");
		head0.put("列" + 13, "");
		head0.put("列" + 14, "");
		head0.put("列" + 15, "");
		head0.put("列" + 16, "");
		head0.put("列" + 17, "");
		head0.put("列" + 18, "");
		head0.put("列" + 19, "");
		head0.put("列" + 20, "");
		head0.put("列" + 21, "");
		head0.put("列" + 22, "");
		head0.put("列" + 23, "备注");
		sheetHead.add(head0);

		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		head.put("列" + 0, "序号");
		head.put("列" + 1, "领用日期");
		head.put("列" + 2, "领用人/使用人");
		head.put("列" + 3, "领用人/使用人签名");
		head.put("列" + 4, "领用机构");
		head.put("列" + 5, "保险公司");
		head.put("列" + 6, "单证识别码");
		head.put("列" + 7, "单证名称");
		head.put("列" + 8, "单证流水号（起）");
		head.put("列" + 9, "单证流水号（止）");
		head.put("列" + 10, "数量");
		head.put("列" + 11, "交回时间");
		head.put("列" + 12, "交回人代码");
		head.put("列" + 13, "交回人姓名");
		head.put("列" + 14, "交回机构");
		head.put("列" + 15, "保险公司");
		head.put("列" + 16, "单证识别码");
		head.put("列" + 17, "单证流水号（起）");
		head.put("列" + 18, "单证流水号（止）");
		head.put("列" + 19, "数量");
		head.put("列" + 20, "其中：已使用");
		head.put("列" + 21, "其中：作废");
		head.put("列" + 22, "其中：未使用址");
		head.put("列" + 23, "备注");
		// 设置每个sheet页的表头

		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("单证管理信息表", sheetHead); // 对应上面excelMap put的销售人员基本信息报表

		ExcelWrite write = new ExcelWrite();
		// 设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition",
				"attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		write.writeExcel(excelMap);

	}

	@RequestMapping("/DMS/toImportdocmanagent.do")
	public ModelAndView toImportdocmanagent(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("ca/cacore/dms/document/documentImport");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/DMS/importdocmanagent.do")
	public void importdocmanagent(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/json;charset=UTF-8");
		IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		String info = "";
		String ccode="";
		String ccodename="";
		List<String> listsmsgInfo = new ArrayList<>();
		try {
			for (MultipartFile file : files.values()) {
				System.out.println(file.getOriginalFilename());
				ExcelUtil excel = new ExcelUtil();
				String[] titles = new String[] { "receiptdate", "applyuser_id", "applyuser_name",
						"apply_sysbranchname", "applay_cpybranchname", "document_idcard", "document_name",
						"document_serial_beg", "document_serial_end", "applay_num", "return_time", "returnuser_code",
						"returnuser_name", "return_sysbranchname", "return_cpybranchname", "redocument_idcard",
						"redocument_name", "redocument_serial_beg", "redocument_serial_end", "return_num",
						"user_number", "obsolete_number", "not_used_number", "remarks"

				};
				ExcelUtil util = new ExcelUtil();
				Map<String, List<Object>> excelMap = util.initSheet4Stream(file.getInputStream(), new Object(), titles);
				List<IDocumentMagModel> lists = new ArrayList<IDocumentMagModel>();
				Map<String, Object> map = null;
				
				for (String key : excelMap.keySet()) {
					Iterator<Object> mapit = excelMap.get(key).iterator();
					while (mapit.hasNext()) {
						map = (Map<String, Object>) mapit.next();
						if (map.size() > 10) {
							String code="";
							IDocumentMagModel model = new DocumentMagModel();
							for (Map.Entry<String, Object> entry : map.entrySet()) {
								if ("receiptdate".equals(entry.getKey())) {
									model.setReceiptdate(String.valueOf(entry.getValue()));
								}
								if ("applyuser_id".equals(entry.getKey())) {
									model.setApplyuser_id(String.valueOf(entry.getValue()));
								}
								if ("applyuser_name".equals(entry.getKey())) {
									model.setApplyuser_name(String.valueOf(entry.getValue()));
								}
								if ("apply_sysbranchname".equals(entry.getKey())) {
									model.setApply_sysbranchname(String.valueOf(entry.getValue()));
								}
								if ("applay_cpybranchname".equals(entry.getKey())) {
									model.setApplay_cpybranchname(String.valueOf(entry.getValue()));
								}
								if ("document_idcard".equals(entry.getKey())) {
									model.setDocument_idcard(String.valueOf(entry.getValue()));
								}
								if ("document_name".equals(entry.getKey())) {
									model.setDocument_name(String.valueOf(entry.getValue()));
								}
								if ("document_serial_beg".equals(entry.getKey())) {
									String pattern = "^\\d{1,}$";
									boolean isMatch = Pattern.matches(pattern, String.valueOf(entry.getValue()));
									if (!("".equals(String.valueOf(entry.getValue()))
											|| String.valueOf(entry.getValue()) == null) && isMatch) {
										model.setDocument_serial_beg(Integer.valueOf(String.valueOf(entry.getValue())));
									}
								}
								if ("document_serial_end".equals(entry.getKey())) {
									String pattern = "^\\d{1,}$";
									boolean isMatch = Pattern.matches(pattern, String.valueOf(entry.getValue()));
									if (!("".equals(String.valueOf(entry.getValue()))
											|| String.valueOf(entry.getValue()) == null) && isMatch) {
										model.setDocument_serial_end(Integer.valueOf(String.valueOf(entry.getValue())));
									}
								}
								if ("applay_num".equals(entry.getKey())) {
									String pattern = "^\\d{1,}$";
									boolean isMatch = Pattern.matches(pattern, String.valueOf(entry.getValue()));
									if (!("".equals(String.valueOf(entry.getValue()))
											|| String.valueOf(entry.getValue()) == null) && isMatch) {
										model.setApplay_num(Integer.valueOf(String.valueOf(entry.getValue())));
									}
								}
								if ("return_time".equals(entry.getKey())) {
									model.setReturn_time(String.valueOf(entry.getValue()));
								}
								if ("returnuser_code".equals(entry.getKey())) {
									model.setReturnuser_code(String.valueOf(entry.getValue()));
								}
								if ("returnuser_name".equals(entry.getKey())) {
									model.setReturnuser_name(String.valueOf(entry.getValue()));
								}
								if ("return_sysbranchname".equals(entry.getKey())) {
									model.setReturn_sysbranchname(String.valueOf(entry.getValue()));
								}
								if ("return_cpybranchname".equals(entry.getKey())) {
									model.setReturn_cpybranchname(String.valueOf(entry.getValue()));
								}
								if ("redocument_idcard".equals(entry.getKey())) {
									model.setRedocument_idcard(String.valueOf(entry.getValue()));
								}
								if ("redocument_name".equals(entry.getKey())) {
									model.setRedocument_name(String.valueOf(entry.getValue()));
								}
								if ("redocument_serial_beg".equals(entry.getKey())) {
									String pattern = "^\\d{1,}$";
									boolean isMatch = Pattern.matches(pattern, String.valueOf(entry.getValue()));
									if (!("".equals(String.valueOf(entry.getValue()))
											|| String.valueOf(entry.getValue()) == null) && isMatch) {
										model.setRedocument_serial_beg(
												Integer.valueOf(String.valueOf(entry.getValue())));
									}
								}
								if ("redocument_serial_end".equals(entry.getKey())) {
									String pattern = "^\\d{1,}$";
									boolean isMatch = Pattern.matches(pattern, String.valueOf(entry.getValue()));
									if (!("".equals(String.valueOf(entry.getValue()))
											|| String.valueOf(entry.getValue()) == null) && isMatch) {
										model.setRedocument_serial_end(
												Integer.valueOf(String.valueOf(entry.getValue())));
									}
								}
								if ("return_num".equals(entry.getKey())) {
									String pattern = "^\\d{1,}$";
									boolean isMatch = Pattern.matches(pattern, String.valueOf(entry.getValue()));
									if (!("".equals(String.valueOf(entry.getValue()))
											|| String.valueOf(entry.getValue()) == null) && isMatch) {
										model.setReturn_num(Integer.valueOf(String.valueOf(entry.getValue())));
									}
								}
								if ("user_number".equals(entry.getKey())) {
									String pattern = "^\\d{1,}$";
									boolean isMatch = Pattern.matches(pattern, String.valueOf(entry.getValue()));
									if (!("".equals(String.valueOf(entry.getValue()))
											|| String.valueOf(entry.getValue()) == null) && isMatch) {
										model.setUser_number(Integer.valueOf(String.valueOf(entry.getValue())));
									}

								}
								if ("obsolete_number".equals(entry.getKey())) {
									String pattern = "^\\d{1,}$";
									boolean isMatch = Pattern.matches(pattern, String.valueOf(entry.getValue()));
									if (!("".equals(String.valueOf(entry.getValue()))
											|| String.valueOf(entry.getValue()) == null) && isMatch) {
										model.setObsolete_number(Integer.valueOf(String.valueOf(entry.getValue())));
									}
								}
								if ("not_used_number".equals(entry.getKey())) {
									String pattern = "^\\d{1,}$";
									boolean isMatch = Pattern.matches(pattern, String.valueOf(entry.getValue()));
									if (!("".equals(String.valueOf(entry.getValue()))
											|| String.valueOf(entry.getValue()) == null) && isMatch) {
										model.setNot_used_number(Integer.valueOf(String.valueOf(entry.getValue())));
									}

								}
								if ("remarks".equals(entry.getKey())) {
									model.setRemarks(String.valueOf(entry.getValue()));
								}
							}
							if(model.getReceiptdate().contains("--")) {
								code=model.getReceiptdate();
				                String[] split = code.split("--");   
				               ccode=split[0];
				               ccodename=split[1];
				               model.setReceiptdate("2019-10-01");
				               if(!(ccode==null||"".equals(ccode))) {
									String regex = "^[a-z0-9A-Z]+$";
									if(!Pattern.matches(regex,ccode)) {
										listsmsgInfo.add("分公司/营业部未录入！");
										req.setAttribute("listsmsgInfo", listsmsgInfo);
										break;
									}
								}
				               continue;
							}
							String pattern = "^\\d{4}-\\d{1,2}-\\d{1,2}.*";
							boolean isMatch = Pattern.matches(pattern, model.getReceiptdate());
							if (!isMatch && !(model.getReceiptdate() == null || "".equals(model.getReceiptdate()))) {
								//System.out.println("zdd"+model.getReceiptdate().trim().length()+">>"+model.getReceiptdate());
								String t =model.getReceiptdate();
								if(!"单证使用登记表".equals(t)
										&&!"领 用".equals(t)
										&&!"领用日期".equals(t)
										&&!"说明：".equals(t)
										&&!"数量=单证流水号（止）-单证流水号（始）+1".equals(t)
										&&!"交回的数量还要等于：其中已使用+其中作废+其中未使用".equals(t)
										&&!"领用人代码，即销售公司人员代码，输入后自动带出领用人姓名、领用机构".equals(t)
										&&!"交回人代码，即销售公司人员代码，输入后自动带出交回人姓名、交回机构".equals(t)) {
									
									
									if(model.getReceiptdate().length()>0) {
										listsmsgInfo.add(model.getApplyuser_id()+"领用日期格式不正确！");
									}
								}
								continue;
							}
							
							String pattern1 = "^\\d{4}-\\d{1,2}-\\d{1,2}.*";
							boolean isMatch1 = Pattern.matches(pattern, model.getReturn_time());
							if (!isMatch1 && !(model.getReturn_time() == null || "".equals(model.getReturn_time()))) {
								//System.out.println("zdd"+model.getReceiptdate().trim().length()+">>"+model.getReceiptdate());
								String t =model.getReturn_time();
								if(!"单证使用登记表".equals(t)
										&&!"领用".equals(t)
										&&!"领用日期".equals(t)
										&&!"说明：".equals(t)
										&&!"数量=单证流水号（止）-单证流水号（始）+1".equals(t)
										&&!"交回的数量还要等于：其中已使用+其中作废+其中未使用".equals(t)
										&&!"领用人代码，即销售公司人员代码，输入后自动带出领用人姓名、领用机构".equals(t)
										&&!"交回人代码，即销售公司人员代码，输入后自动带出交回人姓名、交回机构".equals(t)) {
									if(model.getReceiptdate().length()>0) {
										listsmsgInfo.add(model.getApplyuser_id()+"交回时间格式不正确！");
									}
								}
								continue;
							}
							if (!(model.getDocument_serial_end() == null)
							&& !(model.getDocument_serial_beg() == null) && !(model.getApplay_num() == null)
							&& ((model.getDocument_serial_end() - model.getDocument_serial_beg() + 1) != model
									.getApplay_num())) {
								listsmsgInfo.add(model.getApplyuser_id()+"领用的数量不正确！");
								continue;
							}
							if (!(model.getRedocument_serial_end() == null)
									&& !(model.getRedocument_serial_beg() == null)
									&& !(model.getUser_number() == null) && !(model.getObsolete_number() == null)
									&& !(model.getNot_used_number() == null) && !(model.getReturn_num() == null)
									&& ((model.getUser_number() + model.getObsolete_number() + model.getNot_used_number() != model.getReturn_num()) 
									|| (model.getRedocument_serial_end() - model.getRedocument_serial_beg() + 1) != model.getReturn_num())) {
								listsmsgInfo.add(model.getApplyuser_id()+"交回的数量不正确！");
					         	continue;
				        	}/*xx(编号)--XX分公司/营业部*/
							
							/*if((ccode==null||"".equals(ccode))) {
								String regex = "^[a-z0-9A-Z]+$";
								if(!Pattern.matches(regex,ccode)) {
									listsmsgInfo.add("分公司/营业部未录入！");
									req.setAttribute("listsmsgInfo", listsmsgInfo);
									break;
								}
							}*/
							
							
							
							model.setCcode(ccode);
							model.setCcodename(ccodename);
							model.setModify_user(userModel.getEmp_id());
							
							/*String msg = iDocumentMagService.importdocmanagent(model);
							if(!"0".equals(msg)) {
								listsmsgInfo.add(msg);
								continue;
							}*/
							//校验
							/*领用人代码，即销售公司人员代码，输入后自动带出领用人姓名、领用机构	
							交回人代码，即销售公司人员代码，输入后自动带出交回人姓名、交回机构	*/
							if(model.getApplyuser_id()==null||"".equals(model.getApplyuser_id())) {
								listsmsgInfo.add("领用人代码未填写");
								continue;
							}
							if(model.getReturnuser_code()==null||"".equals(model.getReturnuser_code())) {
								listsmsgInfo.add("交回人代码未填写");
								continue;
							}
                            //校验领用人
							IDocumentMagModel AcheckapjbYON = iDocumentMagService.checkapjbYON(model);
							if(AcheckapjbYON==null) {
								listsmsgInfo.add(model.getApplyuser_id()+"领用人代码填写错误！");
								continue;
							}
							if(!model.getApplyuser_name().equals(AcheckapjbYON.getApplyuser_name())) {
								listsmsgInfo.add(model.getApplyuser_id()+"领用人姓名填写不正确！");
								continue;
							}
							if(!model.getApply_sysbranchname().equals(AcheckapjbYON.getApply_sysbranchname())) {
								listsmsgInfo.add(model.getApplyuser_id()+"领用人机构填写不正确！");
								continue;
							}
							//校验交回人
							IDocumentMagModel model2=new DocumentMagModel();
							model2.setApplyuser_id(model.getReturnuser_code());
							IDocumentMagModel RcheckapjbYON2 = iDocumentMagService.checkapjbYON(model2);
							if(RcheckapjbYON2==null) {
								listsmsgInfo.add(model.getReturnuser_code()+"交回人代码填写错误！");
								continue;
							}
							if(!model.getReturnuser_name().equals(RcheckapjbYON2.getApplyuser_name())) {
								listsmsgInfo.add(model.getReturnuser_code()+"交回人姓名填写不正确！");
								continue;
							}
							if(!model.getReturn_sysbranchname().equals(RcheckapjbYON2.getApply_sysbranchname())) {
								listsmsgInfo.add(model.getReturnuser_code()+"交回人机构填写不正确！");
								continue;
							}
							lists.add(model);
						}
					}
				}
			
			if(listsmsgInfo.size()==0) {
				for (IDocumentMagModel model : lists) {
					iDocumentMagService.importdocmanagent2(model);
				}
				listsmsgInfo.add("数据导入成功！");
			}
			
			//String //
			IDocumentMagModel model = new DocumentMagModel();
			model.setCcode(ccode);
			model.setCcodename(ccodename);
			model.setModify_user(userModel.getEmp_id());
			model.setCreate_user(userModel.getEmp_id());
			iDocumentMagService.insertsysEnumdanzheng(model);
			req.setAttribute("listsmsgInfo", listsmsgInfo);
		   }
			res.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \"" + listsmsgInfo + "\"}");
		} catch (Exception e) {
			e.printStackTrace();
			res.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
		}
	}
	public static void main(String[] args) {
		String pattern = "^\\d{4}-\\d{1,2}-\\d{1,2}.*";
		
		System.out.println(Pattern.matches(pattern,"2019-10-01"));
		System.out.println(Pattern.matches(pattern,"2019-9-1"));
		System.out.println(Pattern.matches(pattern,"2019-9-01"));
		System.out.println(Pattern.matches(pattern,"2019-10-1"));

	}
}
