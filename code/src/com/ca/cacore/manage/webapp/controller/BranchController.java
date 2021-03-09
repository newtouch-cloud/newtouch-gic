package com.ca.cacore.manage.webapp.controller;

import com.ca.cacore.manage.dao.user.IUserDao;
import com.ca.cacore.manage.model.bo.*;
import com.ca.cacore.manage.model.vo.BranchStatusHisVOModel;
import com.ca.cacore.manage.model.vo.BusinessLicenseHisModel;
import com.ca.cacore.manage.model.vo.IBranchStatusHisVOModel;
import com.ca.cacore.manage.model.vo.IBranchVOModel;
import com.ca.cacore.manage.webapp.service.IBranchService;
import com.ca.cacore.mass.webapp.service.IInsBranchManageService;
import com.newtouch.component.c11assistant.*;
import com.newtouch.component.c1properties.StaticProperties;
import com.newtouch.component.c4fileDownload.FileDownLoadUtil;
import com.newtouch.component.c4fileDownload.FileDownLoadUtil2;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.usermgmt.service.IUserMgmtService;
import com.newtouch.utils.PathFactory;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.excel.ExcelUtil;
import com.newtouch.utils.excel.ExcelWrite;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class BranchController extends BaseController {

	private Logger log = Logger.getLogger(BranchController.class);

	@Autowired
	private IBranchService branchService;
	@Autowired
	private IUserMgmtService userService;
	@Autowired
	private IInsBranchManageService insBranchService;
	@Autowired
	private IUserDao userDao;

	/**
	 * @param req
	 * @param res
	 * @return
	 * @throws ModelAndView
	 * @description:重定向到主页面面和查询
	 */
	@RequestMapping("/Manage/Branch/toQueryBranch.do")
	public ModelAndView toQueryBranch(HttpServletRequest req, HttpServletResponse res) {
		getBranchLeavel(req, res);// by zdd 20190613
		return new ModelAndView("ca/cacore/manage/branch/branchQuery");
	}

	/**
	 * 主页面面和查询
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/queryBranch.do")
	public ModelAndView queryBranch(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ThreadLocalHelper.set("isOpen", true);
		IUserModel user = ServletHelper.getSessionUser(req); 
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));// 机构编号
		/**
		 * 机构查询条件更改 by 孙豪
		 */
		getBranchLeavel(req, res);// by zdd 20190613
		Date buslicensefounddate = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("found_date")));// 成立日期
		Date recall_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("recall_date")));// 至
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name1"));// 机构名称
		String branch_abbr = ActionHelper.getNullToStr(req.getParameter("branch_id1"));// 机构编号
		String branch_level_name = ActionHelper.getNullToStr(req.getParameter("branch_level_name"));// 机构层级 by zdd
		String beglongarea = ActionHelper.getNullToStr(req.getParameter("beglongarea"));
		String permitarea = ActionHelper.getNullToStr(req.getParameter("permitarea"));
	    String status = ActionHelper.getNullToStr(req.getParameter("status"));
	    String licensepath = ActionHelper.getNullToStr(req.getParameter("licensepath"));
	    String channelcode = ActionHelper.getNullToStr(req.getParameter("channelcode"));
		IBranchModel model = new BranchModel();
		model.setEmp_id(user.getEmp_id());
		model.setBranch_name(branch_name.trim());
		model.setBranch_id(branch_id.trim());
		model.setBranch_abbr(branch_abbr);
		model.setBranch_level_name(branch_level_name); // by zdd 20190606
		model.setPermitarea(permitarea);
		model.setBeglongarea(beglongarea);
		model.setStatus(status);
		model.setChannelcode(channelcode);
		model.setModifyUser(user.getEmp_id());
		model.setLicensepath(licensepath);
		/**
		 * 机构查询条件更改 by 孙豪
		 */
		model.setBuslicensefounddate(buslicensefounddate);
		model.setRecall_date(recall_date);
		model.setPageCount(ActionHelper.getPage(req));// 分页
		model.setBranch_list(user.getDept_list());

		ReturnMsg returnMsg = branchService.queryAllBranch(model);
		req.setAttribute("buslicensefounddate", buslicensefounddate);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));

		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchQuery");
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception ModelAndView
	 * @description:导出机构查询信息
	 */
	@RequestMapping("/Branch/exportBranchInfo.do")
	public ModelAndView exportBranchInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name1"));// 机构名称
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));// 机构名称

		// String
		// is4Sub=ActionHelper.getNullToStr(req.getParameter("is4Sub"));//是否包含下级
		String status = ActionHelper.getNullToStr(req.getParameter("status"));// 状态
		String branch_level_code = ActionHelper.getNullToStr(req.getParameter("branch_level_code"));// 机构级别代码
		String branch_abbr = ActionHelper.getNullToStr(req.getParameter("branch_id1"));// 机构编号 by zdd 20190613
		String branch_level_name = ActionHelper.getNullToStr(req.getParameter("branch_level_name"));// 机构层级 by zdd
																									// 20190606

		Date buslicensefounddate = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("found_date")));// 成立日期
																													// by
																													// zdd
																													// 20190613
		Date recall_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("recall_date")));// 至 by zdd
																											// 20190613
		String licensepath = ActionHelper.getNullToStr(req.getParameter("licensepath"));//20190925 add by lyn
		String channelcode = ActionHelper.getNullToStr(req.getParameter("channelcode"));
		
		IBranchModel model = new BranchModel();
		model.setEmp_id(user.getEmp_id());
		model.setBranch_name(branch_name.trim());
		model.setBranch_id(branch_id.trim());
		model.setStatus(status.trim());
		model.setBranch_level(branch_level_code.trim());
		model.setBranch_level_name(branch_level_name);// by zdd 20190613
		model.setBranch_abbr(branch_abbr); // by zdd 20190613
		model.setBuslicensefounddate(buslicensefounddate);// by zdd 20190613
		model.setRecall_date(recall_date);// by zdd 20190613
		model.setChannelcode(channelcode);
		model.setBranch_list(user.getDept_list());// by zdd 20190613
		model.setModifyUser(user.getEmp_id());
		model.setLicensepath(licensepath);
		// 获取报表数据
		List<IBranchVOModel> list = branchService.exportBranchInfo(model);//

		Map<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		// sheet
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		// row 行数
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			String a = j + 1 + "";
			IBranchVOModel vo = list.get(j);
			// cell 每行中每列的值 注意列字不能少
			/*
			 * hashMap.put("列" + 0, a); // 序号 hashMap.put("列" + 1, vo.getBranch_id()); //
			 * 机构代码 hashMap.put("列" + 2, vo.getBranch_name()); // 机构名称 hashMap.put("列" + 3,
			 * vo.getParent_branch_id());// 上级机构代码 hashMap.put("列" + 4,
			 * vo.getParent_branch_name()); // 上级机构名称 hashMap.put("列" + 5,
			 * vo.getBranch_abbr());// 机构简称 hashMap.put("列" + 6,
			 * vo.getBranch_level_name());// 机构层级 hashMap.put("列" + 7, vo.getDelegate());//
			 * 法人代表 hashMap.put("列" + 8, vo.getStatus_name());// 机构状态 hashMap.put("列" + 9,
			 * vo.getFound_date());// 建立日期 hashMap.put("列" + 10, vo.getRecall_date());//
			 * 撤销日期
			 */
			/*String pc = vo.getProvince_code();
			String cc = vo.getCity_code();
			String ac = vo.getArea_code();

			if (!("".equals(pc) || pc == null)) {
				pc = branchService.getPCAByCode(pc);
			}
			if (!("".equals(cc) || cc == null)) {
				cc = branchService.getPCAByCode(cc);
			}
			if (!("".equals(ac) || ac == null)) {
				ac = branchService.getPCAByCode(ac);
			}
			String pca = pc + cc + ac + vo.getAddress();*/
			StringBuffer sf = new StringBuffer();
			if(model.getProvince()!=null&&!"".equals(model.getProvince())) {
			sf.append(model.getProvince());
			}
			if(vo.getCity()!=null&&!"".equals(vo.getCity())) {
			sf.append(vo.getCity());
			}
			if(vo.getArea()!=null&&!"".equals(vo.getArea())) {
			sf.append(vo.getArea());
			}
			if(vo.getAddress()!=null&&!"".equals(vo.getDeatailedaddress())) {
			sf.append(vo.getDeatailedaddress());
			}
			// by zdd 20190610
			hashMap.put("列" + 0, a); // 序号
			hashMap.put("列" + 1, vo.getBranch_id()); // 机构代码
			hashMap.put("列" + 2, vo.getBranch_name()); // 机构名称
			hashMap.put("列" + 3, vo.getSbname()); // 机构名称
			hashMap.put("列" + 4, vo.getBranch_level_name());// 机构层级
			hashMap.put("列" + 5, vo.getChannelcode());// 渠道代码
			hashMap.put("列" + 6, vo.getUnifiedSocialCreditNO());// 统一社会信用代码
			hashMap.put("列" + 7, vo.getDelegate());// 负责人
			hashMap.put("列" + 8, sf.toString());// 营业场所
			hashMap.put("列" + 9, vo.getBuslicensefounddate());// 营业执照成立日期
			hashMap.put("列" + 10, vo.getPermitcode());// 银保监机构编码
			hashMap.put("列" + 11, vo.getPermitarea());// 许可经营区域
			if ("0".equals(vo.getStatus())) {
				hashMap.put("列" + 12, "删除");// 机构状态
			} else if ("1".equals(vo.getStatus())) {
				hashMap.put("列" + 12, "启用");// 机构状态
			} else if ("2".equals(vo.getStatus())) {
				hashMap.put("列" + 12, "禁用");// 机构状态
			}
			hashMap.put("列" + 13, vo.getExittime());// 退出时间
			sheetList.add(hashMap);
		}
		excelMap.put("机构管理报表", sheetList);
		// 正常表头 注意列字不能少
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		/*
		 * head.put("列" + 0, "序号"); head.put("列" + 1, "机构代码"); head.put("列" + 2,
		 * "机构名称"); head.put("列" + 3, "上级机构代码"); head.put("列" + 4, "上级机构名称");
		 * head.put("列" + 5, "机构简称"); head.put("列" + 6, "机构层级"); head.put("列" + 7,
		 * "法人代表"); head.put("列" + 8, "机构状态"); head.put("列" + 9, "建立日期"); head.put("列" +
		 * 10, "撤销日期");
		 */
		// by zdd 20190610 start
		head.put("列" + 0, "序号");
		head.put("列" + 1, "组织编码");
		head.put("列" + 2, "组织名称");
		head.put("列" + 3, "组织详情");
		head.put("列" + 4, "组织层级");
		head.put("列" + 5, "渠道代码");
		head.put("列" + 6, "统一社会信用代码");
		head.put("列" + 7, "负责人");
		head.put("列" + 8, "营业场所");
		head.put("列" + 9, "营业执照成立日期");
		head.put("列" + 10, "银保监机构编码");
		head.put("列" + 11, "许可经营区域");
		head.put("列" + 12, "机构状态");
		head.put("列" + 13, "退出时间");
		// by zdd 20190610 end
		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("机构管理报表", sheetHead); // 对应上面excelMap put的销售人员基本信息报表

		ExcelWrite write = new ExcelWrite();
		// 设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition",
				"attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// write.addNotLockArea("测试1", "A3:F8");
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
		return null;
	}

	/**
	 * 详细
	 */

	@RequestMapping("/Branch/viewBranch.do")
	public ModelAndView viewBranch(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IBranchModel model = new BranchModel();
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = branchService.getByBranchView(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/manage/branch/branchView");
	}

	/**
	 * 转向添加
	 */

	@RequestMapping("/Branch/goBranchAdd.do")
	public ModelAndView goBranchAdd(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));// 主键
		IBranchModel model = new BranchModel();
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = branchService.getBranchAddVO(model);
		// 设置返回参数
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		req.setAttribute("modify_flag", "2");// 修改标志，判断省市是否置灰 2017-12-04 by yanqiguang
		return new ModelAndView("ca/cacore/manage/branch/branchAdd");
	}

	/**
	 * 保存添加机构信息
	 */

	@RequestMapping("/Branch/branchAdd.do")
	public ModelAndView branchAdd(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
		String seq_id = ActionHelper.getNullToStr(req.getParameter("seq_id"));// 上级机构主键
		String branch_parentid = ActionHelper.getNullToStr(req.getParameter("branch_parentid"));// 上级机构代码
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));// 机构名称
		String branch_abbr = ActionHelper.getNullToStr(req.getParameter("branch_abbr"));// 机构简称
		String status = ActionHelper.getNullToStr(req.getParameter("status"));// 机构状态
		String found_date = ActionHelper.getNullToStr(req.getParameter("found_date"));// 建立日期
		String delegate = ActionHelper.getNullToStr(req.getParameter("delegate"));// 法人代表
		String address = ActionHelper.getNullToStr(req.getParameter("address"));// 联系地址
		String zip = ActionHelper.getNullToStr(req.getParameter("zip"));// 邮政编码
		String telephone = ActionHelper.getNullToStr(req.getParameter("telephone"));// 电话号码
		String fax = ActionHelper.getNullToStr(req.getParameter("fax"));// 机构传真
		String email = ActionHelper.getNullToStr(req.getParameter("email"));// 电子邮箱
		String remark = ActionHelper.getNullToStr(req.getParameter("remark"));// 备注
		// String cost_center =
		// ActionHelper.getNullToStr(req.getParameter("cost_center"));// 成本中心
		// String settle_center =
		// ActionHelper.getNullToStr(req.getParameter("settle_center"));// 核算中心
		String permitcode = ActionHelper.getNullToStr(req.getParameter("permitcode"));// 保监许可机构编码
		String permitarea = ActionHelper.getNullToStr(req.getParameter("permitarea"));// 地址
		String channelcode = ActionHelper.getNullToStr(req.getParameter("channelcode"));// 渠道代码信息

		// 增加省市县 ---孙豪
		String province_code = ActionHelper.getNullToStr(req.getParameter("province_code"));
		String city_code = ActionHelper.getNullToStr(req.getParameter("city_code"));
		String area_code = ActionHelper.getNullToStr(req.getParameter("area_code"));

		IBranchModel branchModel = new BranchModel();

		branchModel.setBranch_parentid(branch_parentid);
		branchModel.setBranch_name(branch_name);
		branchModel.setBranch_abbr(branch_abbr);
		branchModel.setZip(zip);
		branchModel.setAddress(address);
		branchModel.setTelephone(telephone);
		branchModel.setFax(fax);
		branchModel.setRemark(remark);
		branchModel.setEmail(email);
		branchModel.setFound_date(DateUtil.string2Date(found_date));
		branchModel.setDelegate(delegate);
		// branchModel.setCost_center(cost_center);
		// branchModel.setSettle_center(settle_center);
		branchModel.setStatus(status);
		branchModel.setSeq_id(Integer.valueOf(seq_id));
		branchModel.setPermitcode(permitcode);
		branchModel.setPermitarea(permitarea);
		branchModel.setChannelcode(channelcode);
		// 省市县添加到实体类 ---孙豪
		branchModel.setProvince_code(province_code);
		branchModel.setCity_code(city_code);
		branchModel.setArea_code(area_code);

		ReturnMsg returnMsg = new ReturnMsg();
		try {
			returnMsg = branchService.addBranch(branchModel, userModel);
			req.setAttribute("removeflag", "1");
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		// 更新session中的数据权限
		List<Map<String, Object>> list_data_auth = userService.getDataList(userModel.getEmp_id());
		// 遍历拼接为（‘10’，‘101’）格式
		StringBuffer deptList = new StringBuffer("(");
		if (list_data_auth.size() > 0) {
			for (int k = 0; k < list_data_auth.size(); k++) {
				deptList.append("'");
				deptList.append(list_data_auth.get(k).get("id"));
				deptList.append("'");
				if ((list_data_auth.size() - 1) != k) {
					deptList.append(",");
				}
			}
		} else {
			deptList.append("'");
			deptList.append("'");
		}
		deptList.append(")");
		log.info(deptList.toString());
		User user = new User();
		user.setDeptList(deptList.toString());
		req.getSession().setAttribute("CF_USER", user);
		// 将User转换为IUserModel放到会话中
		userModel.setDept_list(deptList.toString());
		req.getSession().setAttribute("user", userModel);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		req.setAttribute("modify_flag", "2");
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchAdd");
	}

	private IUserModel User2UserModel(User user) {
		IUserModel userModel = new UserModel();
		userModel.setBranch_id(user.getDid());
		userModel.setEmp_id(user.getOptID());
		userModel.setUserName(user.getOptName());
		userModel.setSex_code(user.getSex());
		userModel.setEmail(user.getMail());
		userModel.setStatus(user.getStatus());
		userModel.setPassword(user.getOptpass());
		userModel.setUser_type(user.getOptType());
		userModel.setPortraitPath(userDao.getPortraitPath(userModel));// 获取用户头像地址-by张晨
		userModel.setDept_list(user.getDeptList());// 添加用户数据权限 -by yanqiguang
		IUserModel model = userDao.getCoreUser(userModel); // 获取绑定的核心用户和密码
		if (model != null) {
			userModel.setCore_usercode(model.getCore_usercode());
			userModel.setCore_password(model.getCore_password());
		}

		return userModel;
	}

	/**
	 * 跳转到修改页面显示修改信息
	 */

	@RequestMapping("/Branch/branchGoModify.do")
	public ModelAndView branchGoModify(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IBranchModel model = new BranchModel();
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = branchService.getByBranchModifyVO(model);
		// 设置返回参数
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		req.setAttribute("modify_flag", "1");// 修改标志，判断省市是否置灰 2017-12-04 by yanqiguang
		return new ModelAndView("ca/cacore/manage/branch/branchModify");
	}

	/**
	 * 保存修改信息
	 */

	@RequestMapping("/Branch/modifyBranch.do")
	public ModelAndView modifyBranch(HttpServletRequest req, HttpServletResponse res) throws Exception {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));// 主键
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));// 机构名称
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));// 机构代码
		String branch_abbr = ActionHelper.getNullToStr(req.getParameter("branch_abbr"));// 机构简称
		String status = ActionHelper.getNullToStr(req.getParameter("status"));// 机构状态
		String status1 = ActionHelper.getNullToStr(req.getParameter("status1"));
		String found_date = ActionHelper.getNullToStr(req.getParameter("found_date"));// 建立日期
		String delegate = ActionHelper.getNullToStr(req.getParameter("delegate"));// 法人代表
		String address = ActionHelper.getNullToStr(req.getParameter("address"));// 联系地址
		String zip = ActionHelper.getNullToStr(req.getParameter("zip"));// 邮政编码
		String telephone = ActionHelper.getNullToStr(req.getParameter("telephone"));// 电话号码
		String fax = ActionHelper.getNullToStr(req.getParameter("fax"));// 机构传真
		String email = ActionHelper.getNullToStr(req.getParameter("email"));// 电子邮箱
		String remark = ActionHelper.getNullToStr(req.getParameter("remark"));// 备注
		// String cost_center =
		// ActionHelper.getNullToStr(req.getParameter("cost_center"));// 成本中心代码
		// String settle_center =
		// ActionHelper.getNullToStr(req.getParameter("settle_center"));// 核算单位

		// 增加省市县 ---孙豪
		/*String province_code = ActionHelper.getNullToStr(req.getParameter("province_code"));
		String city_code = ActionHelper.getNullToStr(req.getParameter("city_code"));
		String area_code = ActionHelper.getNullToStr(req.getParameter("area_code"));*/
		String province = ActionHelper.getNullToStr(req.getParameter("province"));
		String city = ActionHelper.getNullToStr(req.getParameter("city"));
		String area = ActionHelper.getNullToStr(req.getParameter("area"));
		
		String permitcode = ActionHelper.getNullToStr(req.getParameter("permitcode"));// 保监许可机构编码
		String permitarea = ActionHelper.getNullToStr(req.getParameter("permitarea"));// 地址
		String channelcode = ActionHelper.getNullToStr(req.getParameter("channelcode"));// 渠道代码信息
        String deatailedaddress =  ActionHelper.getNullToStr(req.getParameter("deatailedaddress"));
        String exittime = ActionHelper.getNullToStr(req.getParameter("exittime"));
        String unifiedSocialCreditNO = ActionHelper.getNullToStr(req.getParameter("unifiedSocialCreditNO"));
        
		IBranchModel model = new BranchModel();
		model.setSeq_id(seq_id);
		model.setBranch_name(branch_name);
		model.setBranch_id(branch_id);
		model.setBranch_abbr(branch_abbr);
		model.setZip(zip);
		model.setAddress(address);
		model.setTelephone(telephone);
		model.setFax(fax);
		model.setRemark(remark);
		model.setDelegate(delegate);
		model.setEmail(email);
		model.setUnifiedSocialCreditNO(unifiedSocialCreditNO);
		//model.setFound_date(DateUtil.string2Date(found_date));
		if(exittime!=null&&!"".equals(exittime)) {
			model.setExittime(sd.parse(exittime));
		}
        try {
		model.setBuslicensefounddate(sd.parse(found_date));
        }catch(Exception e){
        	System.out.println("营业执照成立日期为空！");
        }
		// model.setCost_center(cost_center);
		// model.setSettle_center(settle_center);
		
		if("禁用".equals(status)) {
			status="2";
		}
		if("失效".equals(status)) {
			status="0";
		}
		if("启用".equals(status)) {
			status="1";
		}
//        if(!status1.equals(status)&&"0".equals(status)) {
////        	model.setExittime(sd.parse(sd.format(new Date())));
////        }else if("0".equals(status)&&("".equals(exittime)||exittime==null)){
////        	model.setExittime(sd.parse(sd.format(new Date())));
////        }
        model.setStatus(status);
		// 省市县添加到实体类 ---孙豪
	/*	model.setProvince_code(province_code);
		model.setCity_code(city_code);
		model.setArea_code(area_code);*/
		model.setProvince(province);
		model.setCity(city);
		model.setArea(area);
		model.setPermitarea(permitarea);
		model.setPermitcode(permitcode);
		model.setChannelcode(channelcode);
        model.setDeatailedaddress(deatailedaddress);
        model.setUnifiedSocialCreditNO(unifiedSocialCreditNO);
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			returnMsg = branchService.updateBranch(model, userModel);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		req.setAttribute("modify_flag", "1");
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchModify");
	}
	
	/**
	 * 跳转到新增下级机构页面显示修改信息
	 */
	
	@RequestMapping("/Branch/branchGoAddJunior.do")
	public ModelAndView branchGoAddJunior(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
//		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));
		IBranchModel model = new BranchModel();
		model.setSeq_id(seq_id);
//		model.setBranch_id(branch_id+"00");
		ReturnMsg returnMsg = branchService.getByBranchAddVO(model);
		
		// 设置返回参数
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		req.setAttribute("modify_flag", "1");// 修改标志，判断省市是否置灰 2017-12-04 by yanqiguang
		return new ModelAndView("ca/cacore/manage/branch/branchAddJunior");
	}
	
	/**
	 * 保存添加信息
	 */

	@RequestMapping("/Branch/insertBranch.do")
	public ModelAndView insertBranch(HttpServletRequest req, HttpServletResponse res) throws Exception {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
		String seq_id = ActionHelper.getNullToStr(req.getParameter("seq_id"));
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));// 机构名称
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));// 机构代码
		String branch_abbr = ActionHelper.getNullToStr(req.getParameter("branch_abbr"));// 机构简称
		String status = ActionHelper.getNullToStr(req.getParameter("status"));// 机构状态
		
		String province = ActionHelper.getNullToStr(req.getParameter("province"));//省
		String city = ActionHelper.getNullToStr(req.getParameter("city"));//市
		String area = ActionHelper.getNullToStr(req.getParameter("area"));//区/县
		
		String permitcode = ActionHelper.getNullToStr(req.getParameter("permitcode"));// 保监许可机构编码
		String permitarea = ActionHelper.getNullToStr(req.getParameter("permitarea"));// 地址
		String channelcode = ActionHelper.getNullToStr(req.getParameter("channelcode"));// 渠道代码信息
		String found_date = ActionHelper.getNullToStr(req.getParameter("found_date"));// 建立日期
		String delegate = ActionHelper.getNullToStr(req.getParameter("delegate"));// 负责人
		String address = ActionHelper.getNullToStr(req.getParameter("address"));// 联系地址
		String zip = ActionHelper.getNullToStr(req.getParameter("zip"));// 邮政编码
		String telephone = ActionHelper.getNullToStr(req.getParameter("telephone"));// 电话号码
		String fax = ActionHelper.getNullToStr(req.getParameter("fax"));// 机构传真
		String email = ActionHelper.getNullToStr(req.getParameter("email"));// 电子邮箱
		String deatailedaddress = ActionHelper.getNullToStr(req.getParameter("deatailedaddress"));//详细地址
		String branch_level_id = ActionHelper.getNullToStr(req.getParameter("branch_level_id"));//机构层级
		String parent_branch_id = ActionHelper.getNullToStr(req.getParameter("parent_branch_id"));//上级机构代码
		IBranchModel model = new BranchModel();
		model.setSeq_id(Integer.valueOf(seq_id));
		model.setBranch_id(branch_id);
		model.setBranch_name(branch_name);
		model.setBranch_parentid(parent_branch_id);
		model.setDeatailedaddress(deatailedaddress);
		model.setEmail(email);
		model.setFax(fax);
		model.setTelephone(telephone);
		model.setZip(zip);
		model.setAddress(address);
		model.setBranch_abbr(branch_abbr);
		model.setProvince(province);
		model.setCity(city);
		model.setArea(area);
		model.setPermitarea(permitarea);
		model.setPermitcode(permitcode);
		model.setChannelcode(channelcode);
		model.setDelegate(delegate);
		try {
			model.setBuslicensefounddate(sd.parse(found_date));
	        }catch(Exception e){
	        	System.out.println("营业执照成立日期为空！");
	        }
		
		if("禁用".equals(status)) {
			status="2";
		}
		if("失效".equals(status)) {
			status="0";
		}
		if("启用".equals(status)) {
			status="1";
		}
		model.setStatus(status);
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			returnMsg = branchService.addBranchJunior(model, userModel);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		req.setAttribute("modify_flag", "1");
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchAddJunior");
	}
	
	/**
	 * 更改状态
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/Branch/updateStatus.do")
	public ModelAndView updateStatus(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));// 主键
		// String branch_id =
		// ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构名称
		IBranchModel model = new BranchModel(seq_id);
		// model.setBranch_id(branch_id);
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			returnMsg = branchService.updateStatus(model, userModel);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		// 设置返回参数
		req.setAttribute("returnHepler", new ReturnMsgHelper(req, returnMsg));
		return queryBranch(req, res);
	}

	/**
	 * 增加检查
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/Branch/addCheckRepeat.do")
	public ModelAndView addCheckRepeat(HttpServletRequest req, HttpServletResponse res) throws IOException {
		IBranchModel model = new BranchModel();
		model.setBranch_name(ActionHelper.getNullToStr(req.getParameter("branch_name").trim()));
		Integer count = branchService.checkRepeatReturnInt(model);
		String returnInfo = "";
		if (count == 0) {
			// 没有相同的机构名称，那么返回输入成功
			returnInfo = "{isSuccess:" + true + "}";
		} else {
			// 有相同的证件信息，那么返回失败
			returnInfo = "{isSuccess:" + false + "}";
		}
		res.getWriter().print(returnInfo);
		return null;
	}

	/**
	 * 2013-12-18新添加校验方法 BY 张晨 添加机构校验机构代码ID是否已经存在
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/Branch/queryForVerifyData.do")
	public ModelAndView queryForVerifyData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		IBranchModel model = new BranchModel();
		model.setBranch_id(ActionHelper.getNullToStr(req.getParameter("branch_id")));
		Integer count = branchService.queryForVerifyData(model);
		res.getWriter().print(count + ""); // 返回数字的字符串,前台接收如果=='0'则表示不在可以添加或修改
		return null;
	}

	/**
	 * 修改检查
	 */

	@RequestMapping("/Branch/updateCheckRepeate.do")
	public ModelAndView updateCheckRepeate(HttpServletRequest req, HttpServletResponse res) throws IOException {
		IBranchModel model = new BranchModel();
		model.setBranch_name(ActionHelper.getNullToStr(req.getParameter("branch_name").trim()));
		model.setSeq_id(ActionHelper.getNullToInteger(req.getParameter("seq_id")));
		Integer count = branchService.checkRepeatReturnInt(model);
		String returnInfo = "";
		if (count == 0) {
			// 没有相同的机构名称，那么返回输入成功
			returnInfo = "{isSuccess:" + true + "}";
		} else {
			// 有相同的证件信息，那么返回失败
			returnInfo = "{isSuccess:" + false + "}";
		}
		res.getWriter().print(returnInfo);
		return null;
	}

	/**
	 * @param req
	 * @param res
	 * @return
	 * @throws ModelAndView
	 * @description:重定向到轨迹表查询
	 */
	@RequestMapping("/Manage/Branch/toQueryAllBranchStatusHis.do")
	public ModelAndView toQueryAllBranchStatusHis(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
		return new ModelAndView("ca/cacore/manage/branch/branchStatusHisQuery");
	}

	/**
	 * 轨迹表查询
	 */
	@RequestMapping("/Branch/queryAllBranchStatusHis.do")
	public ModelAndView queryAllBranchStatusHis(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));// 机构代码
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));// 机构名称
		String aft_stat_code = ActionHelper.getNullToStr(req.getParameter("status"));// 状态
		IBranchStatusHisVOModel model = new BranchStatusHisVOModel();
		model.setEmp_id(user.getEmp_id());
		model.setBranch_id(branch_id);
		model.setBranch_name(branch_name);
		model.setAft_stat_code(aft_stat_code);
		model.setPageCount(ActionHelper.getPage(req));// 分页
		ReturnMsg returnMsg = branchService.queryAllBranchStatusHis(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchStatusHisQuery");
	}

	/**
	 * 查询机构树
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/queryBranchTreePage.do")
	public ModelAndView queryBranchTreePage(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setEmp_id(userModel.getEmp_id());
		ReturnMsg returnMsg = branchService.queryBranchTree(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/util/tree");
	}

	@RequestMapping("/Branch/queryBranchTreeExceptCentral.do")
	public ModelAndView queryBranchTreeExceptCentral(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setEmp_id(userModel.getEmp_id());
		ReturnMsg returnMsg = branchService.queryBranchTreeExceptCentral(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/util/tree");
	}

	/**
	 * 查询机构树
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/queryBranchTreePage4WF.do")
	public ModelAndView queryBranchTreePage4WF(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setEmp_id(userModel.getEmp_id());
		ReturnMsg returnMsg = branchService.queryBranchTree4WF(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/util/tree");
	}

	/**
	 * 查询机构树 基本法
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/queryBranchTree4JBF.do")
	public ModelAndView queryBranchTree4JBF(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setEmp_id(userModel.getEmp_id());
		ReturnMsg returnMsg = branchService.queryBranchTree(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/util/tree");
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception ModelAndView
	 * @description:是否包含子机构查询
	 */
	@RequestMapping("/Branch/queryBranchTree4Sub.do")
	public ModelAndView queryBranchTree4Sub(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setEmp_id(userModel.getEmp_id());
		ReturnMsg returnMsg = branchService.queryBranchTree(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/util/tree4Sub");
	}

	/**
	 * 查询省 by 孙豪
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/queryProvince.do")
	public ModelAndView queryProvince(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ReturnMsg returnMsg = branchService.queryProvince();
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchModify");
	}

	/**
	 * 查询市 by 孙豪
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/queryCity.do")
	public ModelAndView queryCity(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String parent_code = ActionHelper.getNullToStr(req.getParameter("parent_code"));// 父节点
		ReturnMsg returnMsg = branchService.queryCity(parent_code);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchModify");
	}

	/**
	 * 查询县 by 孙豪
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/queryArea.do")
	public ModelAndView queryArea(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String parent_code = ActionHelper.getNullToStr(req.getParameter("parent_code"));// 父节点
		ReturnMsg returnMsg = branchService.queryArea(parent_code);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchModify");
	}

	/**
	 * 
	 * yanqiguang 下午2:14:18 TODO 销售公司机构树查询，更改了权限的查询方式，减少数据库的数量级
	 */
	@RequestMapping("/Branch/querySalesFirmBranchTree.do")
	public ModelAndView querySalesFirmBranchTree(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setEmp_id(userModel.getEmp_id());
		model.setBranch_list(userModel.getDept_list());// 数据权限集合
		String org_level = StringUtils.trim(req.getParameter("org_level"));// 父机构级别
		String branchid = StringUtils.trim(req.getParameter("branchid"));// 父机构代码
		model.setBranch_id(branchid);
		model.setBranch_level(org_level);
		ReturnMsg returnMsg = branchService.queryBranchTree(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/util/NewBranchTree");
	}

	/**
	 * 查询团队机构树 by PZ
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/queryBranchTreeTeam.do")
	public ModelAndView queryBranchTreeTeam(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setEmp_id(userModel.getEmp_id());
		model.setBranch_list(userModel.getDept_list());
		ReturnMsg returnMsg = branchService.queryBranchTreeTeam(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/util/branchTreeTeam");
	}

	/**
	 * zdd 20190606
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/importRedirect.do")
	public ModelAndView importRedirect(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		/*查询*/
		//String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));
		
		/*if(!(branch_name==null||"".equals(branch_name))) {
			branch_name = new String(branch_name.getBytes("ISO-8859-1"),"utf-8");
		}*/
		//String licensepath=req.getParameter("licensepath");
		IBranchModel model = new BranchModel();
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = branchService.getByBranchView(model);
		String branch_name =(String) returnMsg.getDataTable().get("branch_name");
		String licensepath = (String) returnMsg.getDataTable().get("licensepath");
		
		String s="";
		if(!(licensepath==null||"".equals(licensepath))) {
			/*licensepath=new String(licensepath.getBytes("ISO-8859-1"),"utf-8");*/
			s=licensepath.substring(licensepath.lastIndexOf("/")+1);
		}
		/*IBranchModel model = new BranchModel();
		model.setSeq_id(seq_id);
		model.setBranch_name(branch_name);*/
		
		//ReturnMsg returnMsg = branchService.getByBranchView(model);
		req.setAttribute("branch_name", branch_name);
		req.setAttribute("inf", s);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/manage/branch/branchYingyeZhizhao");

	}

	/**
	 * zdd 20190606
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Branch/importexcel.do")
	public ModelAndView importexcel(HttpServletRequest req, HttpServletResponse res) throws Exception {

		return new ModelAndView("ca/cacore/manage/branch/branchImport");

	}

	/**
	 * zdd 20190609
	 * 
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping("/Branch/upload/downloadTemplate.do")
	public void downloadTemplate(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String file_name = ActionHelper.getNullToStr(req.getParameter("file_name")); // 附件id

		/*
		 * 使用虚拟目录下载方法备份
		 */   
		/*String downloadPath = StaticProperties.getProperty("downloadPath"); // 模板保存路径
*/		/*zdd05*/
		String downloadPath =PathFactory.getSource() + "/download/";
		File file = new File(downloadPath + file_name); // 拼接

		// 使用项目部署路径目录下载
//	        String downloadPath = req.getSession().getServletContext().getRealPath("/download/");
//			File file = new File(downloadPath+"/"+file_name);  //拼接

		String fname = file_name.substring(0, file_name.lastIndexOf("."));// 获取文件名前缀
//			FileDownLoadUtil.downLoadFiles(file,fname,req, resp);//调用下载方法
		FileDownLoadUtil.downloadZipNew(file, file_name, resp);// 调用下载原格式的方法
	}

	/**
	 * zdd 20190609
	 * 
	 * @param req
	 * @param res
	 * @param mFile
	 * @return
	 * @throws Exception 机构导入数据
	 */
	
	@RequestMapping("/Branch/uploadbranchImport.do")

	// 导入功能
	public void importuploadbranch(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("file") CommonsMultipartFile mFile) throws Exception {
		res.setContentType("text/json;charset=UTF-8");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		IUserModel user = ServletHelper.getSessionUser(req);
		String info = "";
		
		List<String> listsmsgInfo = new ArrayList<>();
		try {
			for (MultipartFile file : files.values()) {
				
				System.out.println(file.getOriginalFilename());
				// info = cmainPolicyService.importInsurace2(file, user);

				ExcelUtil excel = new ExcelUtil();
				String[] titles = new String[] { "bianhao", "Branch_id", "Branch_name", "UnifiedSocialCreditNO",
						"Found_date", "Province", "City", "Area", "Address", "Permitcode", "permitarea"

				};

				ExcelUtil util = new ExcelUtil();
				
				Map<String, List<Object>> excelMap = util.initSheet4Stream(file.getInputStream(), new Object(), titles);
				
				List<IBranchModel> lists = new ArrayList<IBranchModel>();
				Map<String, Object> map = null;
				int i=0;
				for (String key : excelMap.keySet()) {
					Iterator<Object> mapit = excelMap.get(key).iterator();
					while (mapit.hasNext()) {
						map = (Map<String, Object>) mapit.next();
						if (map.size() > 1) {
							IBranchModel model = new BranchModel();
							String bianhao = "";
							for (Map.Entry<String, Object> entry : map.entrySet()) {
								if ("bianhao".equals(entry.getKey())) {
									bianhao = "序号" + String.valueOf(entry.getValue());
								}
								if ("Branch_id".equals(entry.getKey())) {

									model.setBranch_id(String.valueOf(entry.getValue()));
									System.out.println("Branch_id"+entry.getValue());
								}
								if ("Branch_name".equals(entry.getKey())) {
									System.out.println("Branch_name"+entry.getValue());
									model.setBranch_name(String.valueOf(entry.getValue()));
								}
								if ("UnifiedSocialCreditNO".equals(entry.getKey())) {
									model.setUnifiedSocialCreditNO(String.valueOf(entry.getValue()));
								}
								if ("Found_date".equals(entry.getKey())) {
									String str="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(10|12|0?[13578])([-\\/\\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(11|0?[469])([-\\/\\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(0?2)([-\\/\\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([3579][26]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$))" ;
									boolean m = Pattern.matches(str,String.valueOf(entry.getValue()));
									if(m) {
									model.setBuslicensefounddate(DateUtil.string2Date(entry.getValue()));
									i=1;
									}
									if(!m) {
										i=0;
									}
									
								}
								if ("Province".equals(entry.getKey())) {
									model.setProvince(String.valueOf(entry.getValue()));
								}
								if ("City".equals(entry.getKey())) {
									model.setCity(String.valueOf(entry.getValue()));
								}
								if ("Area".equals(entry.getKey())) {
									model.setArea(String.valueOf(entry.getValue()));
								}
								if ("Address".equals(entry.getKey())) {

									model.setDeatailedaddress(String.valueOf(entry.getValue()));
								}
								if ("Permitcode".equals(entry.getKey())) {
									model.setPermitcode(String.valueOf(entry.getValue()));
								}
								if ("permitarea".equals(entry.getKey())) {
									model.setPermitarea(String.valueOf(entry.getValue()));
								}

								System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
							}
							if ("".equals(model.getBranch_id()) || model.getBranch_id() == null) {
								listsmsgInfo.add(bianhao + ">>组织编号不能为空");
								// req.setAttribute("listsmsgInfo", listsmsgInfo);
								continue;
							}
							if ("".equals(model.getBranch_name()) || model.getBranch_name() == null) {
								listsmsgInfo.add(bianhao + ">>组织名称不能为空");
								// req.setAttribute("listsmsgInfo", listsmsgInfo);
								continue;
							}
							if(i==0) {
								listsmsgInfo.add(bianhao + ">>时间格式不正确【如：2019-10-01】");
								// req.setAttribute("listsmsgInfo", listsmsgInfo);
								continue;
							}
                           
							String str = model.getBranch_id();
							// 先确定branch_id去掉0的长度---
							/*String qt = str.replaceAll("0*$", "");
							String parbranch_id = qt.substring(0, qt.length() - 2);
							// 长度不足10位，补0
							for (int i = parbranch_id.length(); i < 10; i++) {
								parbranch_id = parbranch_id + 0;
							}
							model.setBranch_parentid(parbranch_id);
							if (qt.length() <= 4&& qt.length()>=3) {
								model.setBranch_level("2");
							} else if (qt.length() <= 6&&qt.length()>=5) {
								model.setBranch_level("3");
							} else if (qt.length() <= 8&qt.length() >=7 ) {
								model.setBranch_level("4");
							} else {
								listsmsgInfo.add(qt + ">>“组织层级”必须为分公司、三级分支机构或四级分支机构");
								// req.setAttribute("listsmsgInfo", listsmsgInfo);
								continue;
							}*/
							model.setModifyUser(userModel.getEmp_id());
							model.setCreateUser(userModel.getEmp_id());
							model.setStatus("1");
							String msgInfo = branchService.insertBranchzdd(model, userModel);
							if(!"sucess".equals(msgInfo)) {
								listsmsgInfo.add( model.getBranch_id() + ">>" + msgInfo);
							}
							model.setBranch_allpath(model.getBranch_id());
							
							Date d = new Date();
							model.setCreateDate(sd.parse(sd.format(d)));
							model.setModifyDate(sd.parse(sd.format(d)));
							lists.add(model);

						}
					}
				}
				if(listsmsgInfo==null||listsmsgInfo.size()<=0) {
					//进行插入
					if(lists.size()>0) {
						for (IBranchModel model : lists) {
							branchService.insertbranchList(model);
						}
						
					listsmsgInfo.add("数据导入成功！");
					}
					
				}
				req.setAttribute("listsmsgInfo", listsmsgInfo);
			}
			// resp.getWriter().write("{\"uploadMsg\": \"failure\"}");
			res.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \"" + listsmsgInfo + "\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
		}

	}

	/**
	 * // 营业执照导入功能 by zdd
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */ 
	@RequestMapping("/Branch/importbranchIMgPdf.do")
	public ModelAndView importbranchIMgORPdf(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("file") CommonsMultipartFile mFile) throws Exception {
		final String originalFilename = mFile.getOriginalFilename();
		IUserModel userModel = ServletHelper.getSessionUser(req);
		ReturnMsg returnMsg = new ReturnMsg();
		String seq_id = req.getParameter("seq_id");
		String filename = mFile.getOriginalFilename();
		System.out.println(filename);
		String branch_name = req.getParameter("branch_name");
	/*	if(!(branch_name==null||"".equals(branch_name))) {
			branch_name=new String(branch_name.getBytes("ISO-8859-1"),"utf-8");
		}*/
		// System.out.println(originalFilename);
		String savePath = PathFactory.getSource() + "/uploadFile/";
		File file1 = new File(savePath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
		System.out.println("路径：" + savePath);
		String p = savePath + filename;
		/* p=p.substring(1); */
		IBranchModel model = new BranchModel();
		model.setSeq_id(ActionHelper.getNullToInteger(seq_id));
		model.setLicensepath(p);
		model.setCreateUser(userModel.getEmp_id());
		String re = branchService.importbranchIMgORPdf(model);
		if ("1".equals(re)) {
			File file = new File(savePath, originalFilename);
			FileOutputStream fop = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = mFile.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			 returnMsg = branchService.getByBranchView(model);
			req.setAttribute("branch_name", branch_name);
			req.setAttribute("seq_id", seq_id);
			req.setAttribute("inf", filename);
			returnMsg.setSuccessMessage("导入成功！");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			    
		}
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchYingyeZhizhao");
	}

	/**
	 * by zdd 20190613
	 * 
	 * @param req
	 * @param res
	 */

	public void getBranchLeavel(HttpServletRequest req, HttpServletResponse res) {
		// 查询机构层级 by zdd 20190613 start
		Map<String,String> m = new HashMap<String,String>();
		List<String> branchLeavels = branchService.getBranchLeavel();
		/*if (branchLeavels != null && branchLeavels.size() > 0) {
			for (String string : branchLeavels) {
				if (string != null && string.length() > 0) {
					m.put(string.split(",")[0], string.split(",")[1]);
				}
			}
		}*/
		String type="zzstatus";
		List<String> zzstuts = branchService.getzzstuts(type);
		if(zzstuts !=null&&zzstuts.size()>0) {
			for (String string : zzstuts) {
				if (string != null && string.length() > 0) {
					m.put(string.split(",")[0], string.split(",")[1]);
				}
			}
		}
		req.setAttribute("maps", branchLeavels);
		req.setAttribute("m", m);
	}

	// 下载 by zdd 20190618
	@RequestMapping("/Branch/downImg.do")
	public void downImg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String seq_id= new String(ActionHelper.getNullToStr(req.getParameter("u"))); // 附件id
		IBranchModel model = new BranchModel();
		model.setSeq_id(Integer.parseInt(seq_id));
		ReturnMsg returnMsg = branchService.getByBranchView(model);
		String branch_name =(String) returnMsg.getDataTable().get("branch_name");
		String file_name  = (String) returnMsg.getDataTable().get("licensepath");
		
		/* /CACore/upload/ */
		String downloadPath = StaticProperties.getProperty("uploads"); // 模板保存路径
		try {
		File file = new File(file_name); // 拼接
		String name = file_name.substring(file_name.lastIndexOf("/") + 1);
		resp.setHeader("Content-Type", "text/html;charset=utf-8");
	
		FileDownLoadUtil2.downloadZipNew(file,name, resp);// 调用下载原格式的方法
		
		}catch(Exception e) {
			//returnMsg.setFailMessage("文件不存在！");
			resp.setContentType("text/html; charset=UTF-8"); //转码
			resp.getWriter().println("<script>");
		    
			resp.getWriter().println("alert('文件不存在！');");
			resp.getWriter().println("history.back();");
			resp.getWriter().println("</script>");
		}
		
	}

	@RequestMapping("/Branch/uploadimg.do")
	public ModelAndView uploadimg(HttpServletRequest req, HttpServletResponse resp) {
		String branch_id = req.getParameter("branch_id");
		String url = req.getParameter("lurl");
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setBranch_id(branch_id);
		// model.setBranch_list(userModel.getDept_list());
		ReturnMsg returnMsg = new ReturnMsg();
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, true));
		return returnPage(resp, returnMsg, url);

	}

	@RequestMapping("/Branch/cometrueuploadimg.do")
	public void cometrueuploadimg(HttpServletRequest req, HttpServletResponse resp) {
		String branch_id = req.getParameter("branch_id");
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setBranch_id(branch_id);
		// model.setBranch_list(userModel.getDept_list());

	}
	@RequestMapping("/Branch/businessLicenseHis.do")
	public ModelAndView businessLicenseHis (HttpServletRequest req, HttpServletResponse res) {
		String seq_id = req.getParameter("seq_id");
		
		ReturnMsg returnMsg = branchService.businessLicenseHis(Integer.parseInt(seq_id));
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchYingyeZhizhaohis");
	}
	
	@RequestMapping("/Branch/upbusinessLicenseHis.do")
	public ModelAndView upbusinessLicenseHis (HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String seq_id = req.getParameter("seq_id");
		BusinessLicenseHisModel model = new BusinessLicenseHisModel();
		model.setId(Integer.parseInt(id));
		model.setSeq_id(Integer.parseInt(seq_id));
		ReturnMsg returnMsg = branchService.upbusinessLicenseHis(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchYingyeZhizhaohis");
	}
	@RequestMapping("/Branch/downImgList.do")
	public void downImgList(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String file_name  = ActionHelper.getNullToStr(req.getParameter("u"));
		
		/* /CACore/upload/ */
		String downloadPath = StaticProperties.getProperty("uploads"); // 模板保存路径
		try {
		File file = new File(file_name); // 拼接
		String name = file_name.substring(file_name.lastIndexOf("/") + 1);
		resp.setHeader("Content-Type", "text/html;charset=utf-8");
	
		FileDownLoadUtil2.downloadZipNew(file,name, resp);// 调用下载原格式的方法
		
		}catch(Exception e) {
			//returnMsg.setFailMessage("文件不存在！");
			resp.setContentType("text/html; charset=UTF-8"); //转码
			resp.getWriter().println("<script>");
		    
			resp.getWriter().println("alert('文件不存在！');");
			resp.getWriter().println("history.back();");
			resp.getWriter().println("</script>");
		}
		
	}
	public static void main(String[] args) {
		System.out.println();
	}
}
