package com.newtouch.core.quanxianguanli.login;

import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.ca.cacore.ams.model.vo.UserMgMtVOModel;
import com.ca.cacore.ams.webapp.service.IUserMgMtService;
import com.ca.cacore.manage.dao.user.IUserDao;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.UserModel;
import com.ca.cacore.common.CheckCodeUtil;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.quanxianguanli.service.IQueryDeptListService;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.dao.T_Data_Auth_TypeDao;
import com.newtouch.core.rightsmgmt.dao.T_DeptDao;
import com.newtouch.core.rightsmgmt.dao.T_OperatorDao;
import com.newtouch.core.rightsmgmt.usermgmt.service.IUserMgmtService;
import com.newtouch.utils.md5.Md5;
import com.newtouch.utils.stringutil.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since: 2014年3月19日
 * @author NewTouch_King
 * @description:
 */
@Controller
public class DengLuController extends BaseController {

	private Logger log = Logger.getLogger(DengLuController.class);

	@Autowired
	private IQueryDeptListService deptList;
	@Autowired
	private T_OperatorDao operDao;
	@Autowired
	private T_DeptDao deptDao;
	@Autowired
	private IUserMgmtService userService;
	@Autowired
	private T_Data_Auth_TypeDao authTypeDate;
	@Autowired
	private IUserMgMtService userMgmtService;
	@Autowired
	private IUserDao userDao; // 设置头像

	private static Map<String, Object> authTypeMap = null;

	@RequestMapping("/indexframe.do")
	public ModelAndView goindexframe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/login/indexframe");
	}

	@RequestMapping("/index.do")
	public ModelAndView goindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/login/index");
	}

	@RequestMapping("/dev.do")
	public ModelAndView goindexframe4dev(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/login/dev/indexframe4dev");
	}

	@RequestMapping("/index4dev.do")
	public ModelAndView goindex4dev(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/login/dev/index4dev");
	}

	@RequestMapping("/demo.do")
	public ModelAndView goindexframe4demo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/login/demo/indexframe4demo");
	}

	@RequestMapping("/index4demo.do")
	public ModelAndView goindex4demo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/login/demo/index4demo");
	}

	@RequestMapping("/depttreejsp.do")
	public ModelAndView goDeptTreeJsp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnMsg msg = deptList.queryDeptList();
		return returnPage(response, msg, "newtouch/login/indexdepttree");
	}

	@RequestMapping("/setoptdept.do")
	public ModelAndView setOptDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("CF_USER");
		String did = StringUtil.trimStr(request.getParameter("dept_no"));
		if (StringUtil.isNull(did)) {
			did = user.getDid();
		}
		if (authTypeMap == null || authTypeMap.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data_auth_type", "DEPT");
			authTypeMap = authTypeDate.query(map, false);
		}
		String no = (String) authTypeMap.get("mapping_no");
		String mapping_rule = (String) authTypeMap.get("mapping_rule");
		String table_name = (String) authTypeMap.get("mapping_tab");
		String sql = "SELECT tm." + no + " data_auth_no FROM " + table_name + " tm, t_data_auth tr WHERE tm." + no
				+ " = tr.data_auth_no AND tr.object_no = ? " + "   AND tr.data_auth_type = ? " + "   AND tr.object_type = ? ";
		String[] rule = mapping_rule.split("##");
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		query.add(user.getOptID());
		query.add("DEPT");
		query.add("USER");
		if (!StringUtil.isNull(did)) {
			sql += "AND tm." + no + " LIKE ?";
			query.add("'" + did + "%'");
		}
		String sub_rule[];
		for (int i = 0; i < rule.length; i++) {
			sub_rule = rule[i].replaceAll("'", "").split("=");
			sql += " AND tm." + sub_rule[0] + " = ? ";
			query.add(sub_rule[1]);
		}
		query.setSql(sql);
		// user.setDeptList(query.getSql());
		user.setDid(did);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept_no", did);
		// Map<String, Object> dept_type = deptDao.query(map, false);
		// user.setDept_type(StringUtil.trimStr(dept_type.get("dept_type")));
		request.getSession().setAttribute("CF_USER", user);
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 * @description:进入系统时-首页上的退出功能
	 */
	@RequestMapping("/runloginOut2.controller")
	public ModelAndView runloginOut2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUserModel usermodel = ServletHelper.getSessionUser(request);
		String opt_no = usermodel.getEmp_id();
		// 退出-更新登录轨迹表中的sessioid
		IUserMgMtVOModel umg = new UserMgMtVOModel();
		umg.setSession_id("");
		umg.setStatus("0");
		;
		umg.setOpt_no(opt_no);
		userMgmtService.updateOptPathInfo(umg);
		if (!request.getSession().isNew())
			request.getSession().invalidate();
		return new ModelAndView("newtouch/login/runLoginOut");
	}

	@RequestMapping("/runloginOut.controller")
	public ModelAndView runloginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!request.getSession().isNew())
			request.getSession().invalidate();
		return new ModelAndView("ca/cacore/login");
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 * @description:登录验证
	 */
	/**
	 * 
	 * yanqiguang 2017年11月27日下午4:37:35 TODO
	 * 添加当前用户的数据权限放到session中，减少与数据库的交互，提高效率，同时在权限哪块对当前用户的session中的数据权限进行更新处理
	 */

	@RequestMapping("/redirect.controller")
	public ModelAndView redirect(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String linkUrl = StringUtil.trimStr(req.getParameter("linkUrl"));
		String message = "";// 提示消息
		String flag = "";// 判断标志
		Map<String, Object> map = this.getRequestMap(req);
		String opt_no = (String) map.get("opt_no");
		String validateCode = (String) map.get("validateCode");
		String opt_password = (String) map.get("opt_password");
		map.put("opt_password", Md5.EncoderPwdByMd5(map.get("opt_password").toString()));

		// 判断用户名是否为空
		if (opt_no == null || opt_no.equals("")) {
			message = "用户名不能为空";
		}
		// 判断密码是否为空
		if (opt_password == null || opt_password.equals("")) {
			if (message.equals("")) {
				message = "密码不能为空";
			} else {
				flag = "1";
				message = "用户名，密码不能为空";
			}
		}
		// 判断验证码是否为空
		if (validateCode == null || validateCode.equals("")) {
			if (message.equals("")) {
				message = "验证码不能为空";
			} else {
				if (flag == "1") {
					message = "用户名，密码，验证码不能为空";
				} else {
					message = "密码，验证码不能为空";
				}
			}
		}
		// 判断验证码是否正确
		String imageCode = (String) req.getSession().getAttribute("imageCode");
		if (validateCode != null && !validateCode.equals("") && opt_no != null && !opt_no.equals("") && opt_password != null
				&& !opt_password.equals("")) {
			System.out.println("session中的验证码为：" + imageCode + "=====================");
			System.out.println("页面传过来的验证码为：" + validateCode + "=====================");
			if (!validateCode.equalsIgnoreCase(imageCode)) {
				message = "验证码错误";
			}
		}
		// 判断用户名密码是否匹配
		Map<String, Object> userMap = operDao.query(map, false);
		if (opt_no != null && !opt_no.equals("") && opt_password != null && !opt_password.equals("")) {
			if (userMap == null || userMap.isEmpty()) {
				message = "用户名密码不匹配，请重新录入";
			}
		}
		// 验证失败
		if (!"".equals(message)) {
			req.setAttribute("opt_no", opt_no);
			req.setAttribute("opt_password", opt_password);
			req.setAttribute("message", message);
			return new ModelAndView("ca/cacore/login");
		}
/*		// 首页-用户密码过期提示
		linkUrl = this.checkPassWordIsOverDue(req, res, opt_no, opt_password, linkUrl);
		// 将sessionid更新到登录轨迹表
		if (!linkUrl.equals("ca/cacore/login")) {
			linkUrl = this.updateOptPathInfo(req, res, opt_no, opt_password, linkUrl);
		}*/
		//if (!linkUrl.equals("ca/cacore/login")) {
			ReturnMsg allMenu = userService.queryUserMenuList(userMap);// 所有，包含按钮，转换为List<String>，设置给user对象，用于判断权限是否有。
			List<Map<String, Object>> listMap = allMenu.getDataList();
			List<String> listStr = new ArrayList<String>();
			for (Map<String, Object> mapStr : listMap) {
				listStr.add(mapStr.get("aurl").toString());
			}
			User user = new User();
			user.setOptID(opt_no);
			user.setOptpass(opt_password);
			user.setOptName(StringUtil.trimStr(userMap.get("opt_name")));
			user.setDid(userMap.get("dept_no") + "");
			userMap.clear();
			userMap.put("opt_no", user.getOptID());
			// TODO 修改为查询机构表
			ReturnMsg rmsg = userService.queryUserByOptno(userMap);
			user.setDept_type(StringUtil.trimStr(rmsg.getDataTable().get("dept_type")));
			user.setFuncLis(listStr);
			user.setDeptName(StringUtil.trimStr(rmsg.getDataTable().get("dept_name")));
			//sql in 限制1000 拼接sql进去  yanqiguang
			String deptList="( SELECT id FROM (SELECT DISTINCT branch_id id,branch_name name,branch_parentid pid FROM sys_branch t WHERE t.status = '1' START WITH t.branch_id IN "
					+" ( SELECT tda.data_auth_no FROM t_data_auth tda WHERE tda.is_half_check IS NULL AND tda.object_no = '"+opt_no+"'AND object_type ='USER') CONNECT BY PRIOR t.branch_id = branch_parentid ) )";
//			// --add by yanqiguang 2017-11-27查询这当前人的数据权限，拼接为字符串。
//			List<Map<String, Object>> list_data_auth = userService.getDataList(opt_no);
//			// 遍历拼接为（‘10’，‘101’）格式
//			StringBuffer deptList = new StringBuffer("(");
//			if (list_data_auth.size() > 0) {
//				for (int k = 0; k < list_data_auth.size(); k++) {
//					deptList.append("'");
//					deptList.append(list_data_auth.get(k).get("id"));
//					deptList.append("'");
//					if ((list_data_auth.size() - 1) != k) {
//						deptList.append(",");
//					}
//				}
//			} else {
//				deptList.append("'");
//				deptList.append("'");
//			}
//			deptList.append(")");
			user.setDeptList(deptList);
			req.getSession().setAttribute("CF_USER", user);
			// 将User转换为IUserModel放到会话中
			req.getSession().setAttribute("user", this.User2UserModel(user));
			this.setOptDept(req, res);
		//}
		return new ModelAndView(linkUrl);
	}

	@RequestMapping("/redirect.do")
	public ModelAndView redirectdo(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = this.getRequestMap(req);
		Map<String, Object> userMap = operDao.query(map, false);
		userMap.put("menu_type", "menu");
		ReturnMsg msg = userService.queryUserMenuList(userMap);// 只有菜单，前台菜单树用
		return returnPage(res, msg, "ca/cacore/indexM");
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @description://后台菜单树用
	 */
	@RequestMapping("/redirectToBack.do")
	public ModelAndView redirectToBack(HttpServletRequest req, HttpServletResponse res) {

		IUserModel user = ServletHelper.getSessionUser(req);// 从session获取user信息
		Map<String, Object> map = this.getRequestMap(req);
		map.put("funcName", "redirectToBack.do");
		map.put("opt_no", user.getEmp_id());
		Map<String, Object> userMap = operDao.query(map, false);
		userMap.put("menu_type", "menu");
		ReturnMsg msg = userService.queryUserMenuList(userMap);
		return returnPage(res, msg, "ca/cacore/indexN");
	}

	@RequestMapping("/sendValidateCode.controller")
	public ModelAndView sendValidateCode(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 1调用组件，生成图片和验证码
		Map<String, BufferedImage> imageMap = CheckCodeUtil.getCheckCode1();
		// 2将验证码记录到session,后面验证备用
		String imageCode = imageMap.keySet().iterator().next();
		req.getSession().setAttribute("imageCode", imageCode);
		// 3将生成的图片转换成输入流转换给输出属性
		BufferedImage image = imageMap.get(imageCode);
		OutputStream out = res.getOutputStream();
		ImageIO.write(image, "gif", out);
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping("/checkValidateCode.controller")
	public ModelAndView checkValidateCode(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String validateCode = ActionHelper.getNullToStr(req.getParameter("validateCode"));
		String imageCode = (String) req.getSession().getAttribute("imageCode");
		String pass = "";
		if (validateCode.equalsIgnoreCase(imageCode)) {
			pass = "true";
		} else {
			pass = "false";
		}
		res.getWriter().print(pass);
		return null;
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
//		IUserModel model = userDao.getCoreUser(userModel); // 获取绑定的核心用户和密码
//		if (model != null) {
//			userModel.setCore_usercode(model.getCore_usercode());
//			userModel.setCore_password(model.getCore_password());
//		}

		return userModel;
	}

	// 首页-用户密码过期提示
	public String checkPassWordIsOverDue(HttpServletRequest req, HttpServletResponse res, String opt_no, String opt_password,
			String linkUrl) {
		IUserMgMtVOModel model = new UserMgMtVOModel();
		model.setOpt_no(opt_no);
		String aoo = userMgmtService.checkPassWordIsOverDue(model);
		if (aoo != "") {// 不为空 密码过期
			if (aoo == "1") {
				req.setAttribute("msg", "1");
			}
			req.setAttribute("opt_no", opt_no);
			req.setAttribute("opt_password", opt_password);
			linkUrl = "ca/cacore/login";
		}
		return linkUrl;
	}

	// 将sessionid更新到登录轨迹表
	public String updateOptPathInfo(HttpServletRequest req, HttpServletResponse res, String opt_no, String opt_password, String linkUrl) {
		HttpSession session = req.getSession();
		String session_id = session.getId();
		IUserMgMtVOModel mm = new UserMgMtVOModel();
		mm.setSession_id(session_id.toString());
		mm.setOpt_no(opt_no);
		mm.setStatus("1");
		ReturnMsg msg = userMgmtService.updateOptPathInfo(mm);
		if (msg.getMsgStr() != null && !"".equals(msg.getMsgStr())) {
			req.setAttribute("opt_no", opt_no);
			req.setAttribute("opt_password", opt_password);
			req.setAttribute("message", "发生异常错误");
			linkUrl = "ca/cacore/login";
		}
		return linkUrl;
	}

	
	 @RequestMapping("/tobgimage.do") 
	 public ModelAndView tobgimage(HttpServletRequest req,HttpServletResponse res) throws IOException{ 
		 return new ModelAndView("ca/cacore/bgimage"); 
	}
	 
}
