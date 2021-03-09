package com.newtouch.organization.webapp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ModelHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.PathFactory;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.excel.ExcelUtil;
import com.newtouch.organization.model.vo.impl.AgencyModel;
import com.newtouch.organization.webapp.service.IAgencyService;
import com.newtouch.protocol.dao.IProtocolManageDao;
import com.newtouch.protocol.model.vo.IProtocolManageModel;
import com.newtouch.protocol.model.vo.ProtocolManageModel;
import com.newtouch.protocol.webapp.service.IProtocolManageService;

import net.sf.json.JSONArray;

@Controller
public class AgencyController extends BaseController {

	private String page = "Q";
	@Autowired
	IAgencyService iagencyService;
	@Autowired
	IProtocolManageService protocolManageService;
	@Autowired
	private IProtocolManageDao protocolManageDao;

	// 中介管理查询
	@RequestMapping("/agency/Agency/selectAgency.do")
	public ModelAndView selectAgency(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel user = ServletHelper.getSessionUser(req);
		AgencyModel model = new AgencyModel();
		model = (AgencyModel) ModelHelper.getModel(model, req);
		model.setDept_list(user.getDept_list());
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg = iagencyService.selectAgency(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return new ModelAndView("/newtouch/agency/agency");
	}

	// 中介维护明细
	@RequestMapping("/agency/Agency/selectUpdateAgency.do")
	public ModelAndView queryUpdateAgency(HttpServletRequest req, HttpServletResponse res) throws Exception {
		AgencyModel model = new AgencyModel();
		IUserModel usermodel = ServletHelper.getSessionUser(req);
		String dept_list = usermodel.getDept_list();
		String repair_coding = ActionHelper.getNullToStr(req.getParameter("repair_coding"));
		model = (AgencyModel) ModelHelper.getModel(model, req);
		model.setDept_list(dept_list);
		model.setRepair_coding(repair_coding);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg = iagencyService.QueryAgencyInfo(model);
		List<Map<String, Object>> list = iagencyService.queryRelationAn(model);
		JSONArray json = JSONArray.fromObject(list);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		req.setAttribute("list", json.toString());
		return new ModelAndView("/newtouch/agency/detailagency");
	}

	// 中介维护查询
	@RequestMapping("/agency/Agency/QueryAgencyInfo.do")
	public ModelAndView QueryAgencyInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		AgencyModel model = new AgencyModel();
		IUserModel usermodel = ServletHelper.getSessionUser(req);
		String dept_list = usermodel.getDept_list();
		String repair_coding = ActionHelper.getNullToStr(req.getParameter("repair_coding"));
		model.setDept_list(dept_list);
		model.setRepair_coding(repair_coding);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg = iagencyService.QueryAgencyInfo(model);
		List<Map<String, Object>> list = iagencyService.queryRelationAn(model);
		JSONArray json = JSONArray.fromObject(list);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		if (this.page == "M") {
			returnMsg.setSuccessMessage(new Message("保存成功"));
		}
		this.page = "Q";
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		req.setAttribute("list", json.toString());
		return new ModelAndView("/newtouch/agency/updateagency");
	}

	// 保存
	@RequestMapping("/agency/Agency/insertAgency.do")
	public ModelAndView insertAgency(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel usermodel = ServletHelper.getSessionUser(req);
		String repair_coding = ActionHelper.getNullToStr(req.getParameter("repair_coding"));
		String dept_list = usermodel.getDept_list();
		// 维护社会统一代码和
		String org_id = ActionHelper.getNullToStr(req.getParameter("org_id"));
		String business_license = ActionHelper.getNullToStr(req.getParameter("business_license"));
		String permission_license = ActionHelper.getNullToStr(req.getParameter("permission_license"));
		Date found_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("found_date")));
		String agency_address = ActionHelper.getNullToStr(req.getParameter("agency_address"));
		String agency_phone = ActionHelper.getNullToStr(req.getParameter("agency_phone"));
		String social_code = ActionHelper.getNullToStr(req.getParameter("social_code"));
		String agency_no = ActionHelper.getNullToStr(req.getParameter("agency_no"));
		AgencyModel model = new AgencyModel();
		model.setDept_list(dept_list);
		model.setOrg_id(org_id);
		model.setSocial_code(social_code);
		model.setRepair_coding(repair_coding);
		model.setAgency_no(agency_no);
		model.setBusiness_license(business_license);
		model.setPermission_license(permission_license);
		model.setFound_date(found_date);
		model.setAgency_address(agency_address);
		model.setAgency_phone(agency_phone);
		model.setRepair_coding(repair_coding);
		model.setCreate_user(usermodel.getEmp_id());
		// 修改中介信息
		iagencyService.updateSocialCode(model);
		// 维护的时候数据库存在的数据
		String[] DB_agreement_no = req.getParameterValues("DB_agreement_no");
		int agreement_count = Integer.parseInt(req.getParameter("agreement_count"));
		String count = ActionHelper.getNullToStr(req.getParameter("count"));
		// 如果全部失效将数据库里的协议号全部删除
		List<String> agreementno_all = new ArrayList<String>();
		for (int i = 0; i < DB_agreement_no.length; i++) {
			agreementno_all.add(DB_agreement_no[i]);
		}

		// 先添加协议信息
		List<IProtocolManageModel> list = new ArrayList<IProtocolManageModel>();
		List<IProtocolManageModel> list_update = new ArrayList<IProtocolManageModel>();
		// 传到后台的所有协议号集合
		List<String> strings = new ArrayList<String>();
		for (int i = 0; i < agreement_count; i++) {
			String l = Integer.toString(i);
			l = "," + l + ",";
			if (count.contains(l)) {
				continue;
			} else {
				IProtocolManageModel Protocol_model = new ProtocolManageModel();
				String agreement_no = req.getParameter("agreement_no" + i);
				String branch_name = req.getParameter("branch_name" + i); // 获取协议机构
				String branch_id = req.getParameter("branch_id" + i); // 获取协议机构
				String startdate = req.getParameter("startdate" + i);// 生效日期
				String enddate = req.getParameter("enddate" + i);// 终止日期
				String dateofsign = req.getParameter("dateofsign" + i);// 协议签订日期
				String agreement_agency_type = req.getParameter("agreement_agency_type" + i); // 协议类型
				Protocol_model.setBranch_id(branch_id);
				Protocol_model.setBranch_name(branch_name);
				Protocol_model.setDateofsign(dateofsign);
				Protocol_model.setStartdate(startdate);
				Protocol_model.setEnddate(enddate);
				Protocol_model.setAgreement_agency_type(agreement_agency_type);
				Protocol_model.setAgreement_no(agreement_no);
				strings.add(agreement_no);
				list.add(Protocol_model);
			}
		}
		// 删除此中介机构下的原有所有协议号关系数据
		iagencyService.deleteAgencyAll(agency_no);
		// 删除中介机构下原有的协议号数据
		if (agreementno_all.size() > 0) {
			iagencyService.deleteAreement(agency_no);
		}
		if (list.size() > 0) {
			// 新增中介机构下现有的协议号数据
			protocolManageService.addProtocol(list);
		}
		// 新增中介机构下的现有所有协议号关系数据
		if (strings == null || strings.size() == 0) {
		} else {
			model.setList(strings);
			iagencyService.insertAgency(model);
		}
		model.setAgency_no(repair_coding);
		this.page = "M";
		return QueryAgencyInfo(req, res);
	}

	// 首页面跳转
	@RequestMapping("/agency/Agency/selectLoginAgency.do")
	public ModelAndView loginAgency(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("/newtouch/agency/agency");
	}

	// 机构树查询协议号
	@RequestMapping("/agency/Agency/queryAgreement.do")
	public ModelAndView queryAgreement(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));
		AgencyModel model = new AgencyModel();
		model.setBranch_id(branch_id);
		ReturnMsg returnMsg = iagencyService.queryAgreement(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "/newtouch/agency/updateagency");
	}

	// 添加时判断协议号是否重复
	@RequestMapping("/agency/Agency/queryAgreementNo.do")
	public void queryAgreementNo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String agreement_no = ActionHelper.getNullToStr(req.getParameter("agreement_no"));
		String org_id = ActionHelper.getNullToStr(req.getParameter("org_id"));
		AgencyModel model = new AgencyModel();
		model.setAgreement_no(agreement_no);
		model.setOrg_id(org_id);
		int count = iagencyService.queryAgreementNo(model);
		try {
			// 存在
			if (count < 1) {
				res.getWriter().write("true");
			} else {
				res.getWriter().write("false");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 添加时判断推荐维修码是否重复
	@RequestMapping("/agency/Agency/queryrepair_coding.do")
	public void queryrepair_coding(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String repair_coding = ActionHelper.getNullToStr(req.getParameter("repair_coding"));
		String business_license = ActionHelper.getNullToStr(req.getParameter("business_license"));
		AgencyModel model = new AgencyModel();
		model.setRepair_coding(repair_coding);
		model.setBusiness_license(business_license);
		int count = iagencyService.queryrepair_coding(model);
		try {
			// 存在
			if (count < 1) {
				res.getWriter().write("true");
			} else {
				res.getWriter().write("false");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 跳转导入
	@RequestMapping("/agency/Agency/jumpImportInfo.do")
	public ModelAndView jumpImportInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return new ModelAndView("/newtouch/agency/importagency");
	}

	// 导入功能
	@RequestMapping(value = "/agency/Agency/importInfo.do", method = RequestMethod.POST)
	public ModelAndView importInfo(HttpServletRequest req, HttpServletResponse res, @RequestParam("file") CommonsMultipartFile mFile)
			throws Exception {
		final String originalFilename = mFile.getOriginalFilename();
		String filename = mFile.getOriginalFilename();
		System.out.println(filename);
		// System.out.println(originalFilename);
		String savePath = PathFactory.getSource() + "/upload/";
		File file = new File(savePath, originalFilename);
		FileOutputStream fop = new FileOutputStream(file);
		if (!file.exists()) {
			file.createNewFile();
		}
		byte[] contentInBytes = mFile.getBytes();
		fop.write(contentInBytes);
		fop.flush();
		fop.close();

		// File localFile = new File(savePath, originalFilename);
		// if (!localFile.getParentFile().exists()) {
		// localFile.getParentFile().mkdirs();
		// }
		// try {
		// mFile.transferTo(localFile);
		//
		// } catch (Exception e) {
		// if(localFile!=null)
		// localFile.delete();
		// }
		ExcelUtil excel = new ExcelUtil();
		String[] titles = new String[] { "repair_coding", "org_id", "agency_name", /*"agency_no",*/ "social_code", "permission_license",
				"found_date", "agency_address", "agency_phone"

		};
		String[] agTitles = new String[] { "财险公司编码(*)", "合作代理机构名称(*)", /*"合作代理机构编码(*)",*/ "统一社会信用编码", "许可证编号", "成立时间", "地址", "电话" };
		// Map<String, List<Object>> excelMap =
		// excel.initSheet4Stream(mFile.getInputStream(), titles, agTitles);
		Map<String, List<Object>> excelMap = excel.initSheet4Stream(savePath + "/" + originalFilename, agTitles, titles);
		ReturnMsg returnMsg = new ReturnMsg();
		req.setAttribute("impflag", "true");
		if (!excel.getReturnMsg().isSuccessflag()) {
			returnMsg.setFailMessage((Message) excel.getReturnMsg().getMsgList());
			return returnPage(res, returnMsg, "newtouch/agency/importagency");
		}
		// Map<String,Object> map = null;
		// for (String key : excelMap.keySet()) {
		// Iterator<Object> mapit = excelMap.get(key).iterator();
		// while(mapit.hasNext()){
		// map=(Map<String, Object>)mapit.next();
		// if (map == null || map.isEmpty()){
		// mapit.remove();
		// }
		// }
		// }

		returnMsg = iagencyService.makecheckInfo(excelMap);
		// localFile.deleteOnExit();
		// 删除文件
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// File file = new File(savePath,originalFilename);
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// file.delete();
		// System.out.println("文件删除成功");
		// }
		// }).start();
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, true));
		return returnPage(res, returnMsg, "newtouch/agency/importagency");
	}

	// 模板下载
	@RequestMapping(value = "/agency/excelOut.do")
	public void downloadAgencyTemplate(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String save = req.getRealPath("/");
		save = save + "/excel/agencyTemplate.xlsx";
		File f = new File(save);
		res.reset();
		res.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			res.setHeader("Content-Disposition",
					"attachment;filename=" + new String(("agencyTemplate" + ".xlsx").getBytes(), "iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ServletOutputStream out = res.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(f));
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}

	}
	/*
	 * public static void main(String[] args) { Double aDouble= 1.7611876655E10;
	 * 
	 * System.err.println( bgstrString); }
	 */
}
