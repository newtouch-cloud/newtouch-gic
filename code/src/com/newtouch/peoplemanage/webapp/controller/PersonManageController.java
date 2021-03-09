package com.newtouch.peoplemanage.webapp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.ModelHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c4fileDownload.FileDownLoadUtil;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.PathFactory;
import com.newtouch.peoplemanage.model.po.EducationVOModel;
import com.newtouch.peoplemanage.model.po.GleaderInfoVOModel;
import com.newtouch.peoplemanage.model.po.IEducationVOModel;
import com.newtouch.peoplemanage.model.po.IEnumInfoVOModel;
import com.newtouch.peoplemanage.model.vo.IPersonManageVOModel;
import com.newtouch.peoplemanage.model.vo.PersonManageVOModel;
import com.newtouch.peoplemanage.webapp.service.IPersonManageService;
import com.newtouch.report.utils.ExcelUtils;




/**
 * 人员管理controller层
 * @author Ming Ying
 * @time 2017-11-30
 */


/**
 * 人员基本信息管理管理
 * 
 * @author Ming Ying
 *
 */
@Controller
public class PersonManageController extends BaseController {
	@Autowired
	private IPersonManageService personManageService;

	/*public  boolean isValidNumber(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}*/




	/**
	 * 
	 * @param req
	 * @param res
	 * @return 跳转到查询人员信息页面
	 */
	@RequestMapping("/Person/PersonManage/toQueryPersonInfo.do")
	public ModelAndView toQueryPersonInfo(HttpServletRequest req, HttpServletResponse res) {
		// String pageName=req.getParameter("pageName");
		//a by liu_yn 初始化人员信息页面的机构层级下拉选
		List<BranchModel> msg_branchLevels = personManageService.queryBranchLevel();
		//List<IEnumInfoVOModel> work_naturelist = personManageService.querySelectList("work_nature");
		//List<IEnumInfoVOModel> work_relationList = personManageService.querySelectList("work_relation");
		List<IEnumInfoVOModel> person_classList = personManageService.querySelectList("person_class");
		List<IEnumInfoVOModel> person_statusList = personManageService.querySelectList("person_status");
		//使用request 对象获取Session , 然后进行操作
		HttpSession session =  req.getSession();
		session.setAttribute("msg_branchLevels", msg_branchLevels);
		//session.setAttribute("work_naturelist", work_naturelist);
		//session.setAttribute("work_relationList", work_relationList);
		session.setAttribute("person_classList", person_classList);
		session.setAttribute("person_statusList", person_statusList);
		//System.out.println(session.getAttribute("msg_branchLevels"));
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
		return new ModelAndView("/newtouch/person/personManage/personInfo");
	}

	/**
	 * 查询人员基本信息
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * 
	 */

	@RequestMapping("/Person/PersonManage/QueryPersonInfo.do")
	public ModelAndView QueryPersonInfo(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		IUserModel user = ServletHelper.getSessionUser(req);
		System.out.println(user.getDept_list());
		// 获得要跳转的页面名
		String pageName = req.getParameter("pageName");
		
		// 设置查询条件
		ModelHelper modelHelper = new ModelHelper();
		IPersonManageVOModel personVO = new PersonManageVOModel();
		personVO = (IPersonManageVOModel) modelHelper.getModel(personVO, req);
		personVO.setDept_list(user.getDept_list());
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));
		/*
		原来逻辑
		String branch_level = null;
		if (!"".equals(branch_id)) {
			branch_level = personManageService.getbranch_level(branch_id);
		}
		if ("0".equals(branch_level) || "1".equals(branch_level)) {
			personVO.setBranch_id(branch_id);
		}else {
			personVO.setBranch_id(null);
			personVO.setTeam_id(branch_id);
		}*/
		// m by liu_yn start
		/*String branch_level = personVO.getBranch_level_code();
		personVO.setBranch_level(branch_level);
		if ("0".equals(branch_level) || "1".equals(branch_level)) {
			personVO.setBranch_id(branch_id);
		}else {
			personVO.setBranch_id(null);
			personVO.setTeam_id(branch_id);
		}*/
		personVO.setBranch_id(branch_id);//查询修改
		String education = personVO.getEducation();

		// m by liu_yn end
		// 获取查询结果集
		String dept_list = personVO.getDept_list();
		ReturnMsg returnMsg = personManageService.queryPersonVOs(personVO);
		//req.setAttribute("branch_level",personVO.getBranch_level());
		//根据当前用户判断当前用户是否有操作高管的权限
	/*	Object work_nature = returnMsg.getDataTable().get("work_nature");
		Object work_nature_name = returnMsg.getDataTable().get("work_nature_name");*/
		for (Map<String, Object> map : returnMsg.getDataList()) {
			String s=(String)map.get("work_nature_name");
			System.out.println(map.get("work_nature_name"));
		}
		String flag = "";
		int count = personManageService.queryUserDataAuth(personVO);
		if(count>0){
			flag = "1";
		}else{
			flag = "0";
		}
		req.setAttribute("flag", flag);
		// 设置返回信息
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, personVO.getPageCount(), true));
		return returnPage(res, returnMsg, "newtouch/person/personManage/" + pageName);
	}

	/*
	 * 明细 页面展示
	 */
	@RequestMapping("/Person/PersonManage/PersonInfoView.do")
	public ModelAndView PersonInfoView(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		// String seq_id=ActionHelper.getNullToStr(req.getParameter("seq_id"));
		
		ReturnMsg returnMsg = new ReturnMsg();
		personInfoShow(returnMsg,req,res);
		return returnPage(res, returnMsg, "newtouch/person/personManage/personInfoView");
	}
	
	
	
	public void personInfoShow(ReturnMsg returnMsg,HttpServletRequest req, HttpServletResponse res){
		String person_no = ActionHelper.getNullToStr(req.getParameter("person_no"));
		System.out.println(person_no);
		IEducationVOModel model = new EducationVOModel();
		model.setPerson_no(person_no);
		model.setType("E");
		// 获取查询结果集
		returnMsg = personManageService.PersonInfoView(person_no);

		//ReturnMsg msg_family = personManageService.queryPersonFamily(person_no);
		ReturnMsg msg_edu = personManageService.queryPersonEducation(model);
		model.setType("W");
		//ReturnMsg msg_work = personManageService.queryPersonEducation(model);
		ReturnMsg msg_note = personManageService.queryEmpNote(person_no);
		//ReturnMsg msg_license = personManageService.queryLicenseInfo(person_no);
		ReturnMsg positionInfo = personManageService.queryPositionInfo(person_no);
		//a by liu_yn 增加高管信息
		//ReturnMsg gleaderInfo = personManageService.queryGleaderInfo(person_no);
		//System.out.println(gleaderInfo.getDataList());
		// 设置返回信息
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("family", msg_family.getDataList());
		// map.put("education",msg_edu.getDataList());
		//msg_family.setDataTable(map);
		//req.setAttribute("gleaderInfo",new ReturnMsgHelper(req, gleaderInfo).setReturnParams(gleaderInfo.getDataTable()));
		//System.out.println(req.getAttribute("gleaderInfo"));
		req.setAttribute("education", new ReturnMsgHelper(req, msg_edu, new PageCount(), true));
		//req.setAttribute("work", new ReturnMsgHelper(req, msg_work, new PageCount(), true));
		//req.setAttribute("family", new ReturnMsgHelper(req, msg_family).setReturnParams(msg_family.getDataTable()));
		req.setAttribute("note", new ReturnMsgHelper(req, msg_note, new PageCount(), true));
		//req.setAttribute("license", new ReturnMsgHelper(req, msg_license, new PageCount(), true));
		req.setAttribute("positionInfo", new ReturnMsgHelper(req, positionInfo, new PageCount(), true));
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return 导出人员基本信息
	 * @throws IOException
	 */
	@RequestMapping("/Person/PersonManage/SentPersonInfo.do")
	public ModelAndView SentPersonInfo(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
		// String seq_id=ActionHelper.getNullToStr(req.getParameter("seq_id"));
		String person_no = ActionHelper.getNullToStr(req.getParameter("person_no"));
		ByteArrayOutputStream outputStream = personManageService.sentpersioninfo(person_no);
		// 设置response 这样就可以前台弹出框进行了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHH") + "1111111.pdf");
		OutputStream fileOut = res.getOutputStream();
		fileOut.write(outputStream.toByteArray());
		fileOut.flush();
		fileOut.close();
		fileOut = null;
		return null;
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @return 导出执业证
	 * @throws IOException
	 */
	@RequestMapping("/Person/PersonManage/SentZhiyezhengInfo.do")
	public ModelAndView SentZhiyezhengInfo(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
		// String seq_id=ActionHelper.getNullToStr(req.getParameter("seq_id"));
		String person_no = ActionHelper.getNullToStr(req.getParameter("person_no"));
		ByteArrayOutputStream outputStream = personManageService.sentzhiyezheng(person_no);
		// 设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHH") + "1111111.pdf");
		OutputStream fileOut = res.getOutputStream();
		fileOut.write(outputStream.toByteArray());
		fileOut.flush();
		fileOut.close();
		fileOut = null;
		return null;
	}
	
	/**
	 * 跳转高管信息页面
	 */
	@RequestMapping("/Person/PersonManage/GleaderInfoView.do")
	public ModelAndView GleaderInfoView(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
		String person_no = ActionHelper.getNullToStr(req.getParameter("person_no"));
		Boolean flag = personManageService.findGleaderInfo(person_no);
		if(flag){
			ReturnMsg gleaderInfo = personManageService.queryGleaderInfo(person_no);
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, gleaderInfo).setReturnParams(gleaderInfo.getDataTable()));
			return returnPage(res, gleaderInfo, "newtouch/person/personManage/gleaderinfo");
		}else{
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
			return new ModelAndView("/newtouch/person/personManage/gleaderinfo");
		}
	}
	
	/*
	 * 上传批复文件
	 */
	@RequestMapping("/Person/PersonManage/uploadApprovalFile.do")
	public ModelAndView uploadApprovalFile(HttpServletRequest req, HttpServletResponse res, @RequestParam("uploadfile") CommonsMultipartFile file)
			throws Exception {
		GleaderInfoVOModel gleaderInfo = new GleaderInfoVOModel();
		ReturnMsg msg = new ReturnMsg();
		if (!file.isEmpty()) {
            String originalFileName = file.getOriginalFilename(); 
            // 获取图片后缀
            //String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")); 
            // 生成图片存储的名称，UUID 避免相同图片名冲突，并加上图片后缀
            //String fileName = UUID.randomUUID().toString() + suffix;
            //String fileName = "\\"+originalFileName;
            String filePath =  PathFactory.getSource() + "/uploadFile/" + originalFileName;
            //String filePath = req.getRealPath("/upload/") + fileName;
            System.out.println(filePath);
            File saveDir = new File(filePath);
            if (!saveDir.getParentFile().exists())
                saveDir.getParentFile().mkdirs();
            // 转存文件
            file.transferTo(saveDir);
            gleaderInfo.setUpload_approval_file(filePath);
            String person_no = ActionHelper.getNullToStr(req.getParameter("person_no"));
            gleaderInfo.setPerson_no(person_no);
            
            msg = personManageService.updateApprovalFile(gleaderInfo);
			 //res.getWriter().write("保存成功!");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg).setReturnParams(msg.getDataTable()));  	
        }
		return returnPage(res, msg, "newtouch/person/personManage/uploadFilePage");
	}
	/**
	 * 下载批复文件
	 */
	@RequestMapping("/Person/PersonManage/downloadPic.do")
	public void downloadPic(HttpServletRequest req, HttpServletResponse res) throws IOException{
		try{
			//String file_name = new String(ActionHelper.getNullToStr(req.getParameter("url")).getBytes("ISO-8859-1"),"utf-8");
			String file_name = new String(ActionHelper.getNullToStr(req.getParameter("url"))); 
	        File file = new File(file_name); // file_name
	        String name= file_name.substring(file_name.lastIndexOf("/")+1);
			FileDownLoadUtil.downloadZipNew(file,DateHelper.getSysStr("yyyyMMddHHmmss")+name,res);
		}catch(Exception e) {
			//returnMsg.setFailMessage("文件不存在！");
			res.setContentType("text/html; charset=UTF-8"); //转码
			res.getWriter().println("<script>");
		    
			res.getWriter().println("alert('文件不存在！');");
			res.getWriter().println("history.back();");
			res.getWriter().println("</script>");
		}
		
	}
	
	/**
	 * 保存高管信息
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Person/PersonManage/saveGleaderInfo.do")
	public ModelAndView saveGleaderInfo(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
			 ReturnMsg msg = new ReturnMsg();
			 ModelHelper modelHelper = new ModelHelper();
			 GleaderInfoVOModel gleaderInfo = new GleaderInfoVOModel();
			 gleaderInfo = (GleaderInfoVOModel) modelHelper.getModel(gleaderInfo, req);
			 IPersonManageVOModel personVO = new PersonManageVOModel();
			 personVO = (IPersonManageVOModel)modelHelper.getModel(personVO, req);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			 String employment_term = gleaderInfo.getEmployment_term();
			 String gvalid_time = gleaderInfo.getGvalid_time();
			 String approval_time = gleaderInfo.getApproval_time();
			 Date employment_term1 = null;//高管聘期
			 Date gvalid_time1 = null;//高管有效期
			 //Date approval_time1 = null;//高管批复时间
			 if(employment_term!=null && !"".equals(employment_term) && gvalid_time!=null && !"".equals(gvalid_time)){
				 employment_term1 = sdf.parse(employment_term);
				 gvalid_time1 = sdf.parse(gvalid_time);
				 if(employment_term1.after(gvalid_time1)){
					 msg.setFailMessage("保存失败,高管聘期大于有效期");
					 req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg, gleaderInfo.getPageCount(), true));
					 //req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg).setReturnParams(msg.getDataTable()));
					 return returnPage(res, msg, "newtouch/person/personManage/gleaderinfo");
				 }
			 }
			 /*if(employment_term!=null && approval_time!=null){
				 employment_term1 = sdf.parse(employment_term);
				 approval_time1 = sdf.parse(approval_time);
				 if(employment_term1.after(approval_time1)){
					 msg.setFailMessage("保存失败,高管聘期大于高管批复时间");
					 req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg, gleaderInfo.getPageCount(), true));
					 //req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg).setReturnParams(msg.getDataTable()));
					 return returnPage(res, msg, "newtouch/person/personManage/gleaderinfo");
				 }
			 }*/
			 Boolean flag = personManageService.findGleaderInfo(gleaderInfo.getPerson_no());
			 if(flag){
				 msg = personManageService.updateGleaderInfo(gleaderInfo);
				 /*String is_gleader = gleaderInfo.getGleader();
				 if(is_gleader != null && !"".equals(is_gleader) && "1".equals(is_gleader)){
					 personVO.setPerson_class("1");
					 personManageService.updatePersonClass(personVO);
				 }*/
			 }else{
				 msg = personManageService.insertGleaderInfo(gleaderInfo);
				 personVO.setPerson_class("1");
				 personManageService.updatePersonClass(personVO);
			 }
			 
			 //res.getWriter().write("保存成功!");
			 req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg).setReturnParams(msg.getDataTable()));
			 return returnPage(res, msg, "newtouch/person/personManage/gleaderinfo");
	}
	
	/**
	 * 导入人员信息页面
	 */
	@RequestMapping("/Person/PersonManage/importPersonInfoPage.do")
	public ModelAndView importPersonInfoPage(HttpServletRequest req, HttpServletResponse res)
			throws Exception{
		
		return new ModelAndView("/newtouch/person/personManage/importPersonInfoPage");
	}
	
	/**
	 * 导出人员信息
	 */
	@RequestMapping("/Person/PersonManage/exportPersonInfo.do")
	public void exportPersonInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook(); // 定义一个新的工作簿
		HSSFSheet sheet = wb.createSheet("第一个Sheet页"); // 创建第一个Sheet页
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

		List<Object[]> list = new ArrayList<Object[]>();//
		int currentRowNum = 0;// 从第几行开始写
		int currentColNum = 0;// 从第几列开始写
		Row row1 = sheet.createRow(0); // 创建第二个行
		
		row1.createCell(0).setCellValue("序号");
		row1.createCell(1).setCellValue("归属机构");
		row1.createCell(2).setCellValue("所在团队");
		row1.createCell(3).setCellValue("员工编码");
		row1.createCell(4).setCellValue("员工姓名");
		row1.createCell(5).setCellValue("性别");
		row1.createCell(6).setCellValue("民族");
		row1.createCell(7).setCellValue("身份证号");
		row1.createCell(8).setCellValue("手机号");
		row1.createCell(9).setCellValue("合同类型");
		row1.createCell(10).setCellValue("执业证编号");
		row1.createCell(11).setCellValue("有效期开始");
		row1.createCell(12).setCellValue("有效期截止");
		row1.createCell(13).setCellValue("入职日期");
		row1.createCell(14).setCellValue("离职时间");

		  
		sheet.setColumnWidth(1, 6300);
		sheet.setColumnWidth(2, 3300);
		sheet.setColumnWidth(3, 3300);
		sheet.setColumnWidth(4, 3300);
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

	
		IUserModel user = ServletHelper.getSessionUser(req);
		ModelHelper modelHelper = new ModelHelper();
		IPersonManageVOModel personVO = new PersonManageVOModel();
		personVO = (IPersonManageVOModel) modelHelper.getModel(personVO, req);
		personVO.setDept_list(user.getDept_list());
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));
		String branch_level = ActionHelper.getNullToStr(req.getParameter("branch_level_code"));
		personVO.setBranch_level_code(branch_level);
		personVO.setBranch_level(branch_level);
		if ("0".equals(branch_level) || "1".equals(branch_level)) {
			personVO.setBranch_id(branch_id);
		}else {
			personVO.setBranch_id(null);
			personVO.setTeam_id(branch_id);
		}
		PageCount pageCount = personVO.getPageCount();
		pageCount.setNowPage(0);
		pageCount.setLimit(60000);
		pageCount.setStart(0);
		pageCount.setRows4Page(0);
		pageCount.setPage(false);
		// 获取查询结果集
		List<IPersonManageVOModel> relist = personManageService.queryPersonInfoList(personVO);
		pageCount.setPage(true);
		SimpleDateFormat  sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < relist.size(); i++) {
			row1 = sheet.createRow(i+1);
			PersonManageVOModel m = (PersonManageVOModel) relist.get(i);
			row1.createCell(0) .setCellValue(i+1);
			if(m.getBranch_name()!=null){
				row1.createCell(1) .setCellValue(m.getBranch_name());
			}else{
				row1.createCell(1) .setCellValue("");
			}
			if(m.getTeam_name()!=null){
				row1.createCell(2) .setCellValue(m.getTeam_name());
			}else{
				row1.createCell(2) .setCellValue("");
			}
			if(m.getPerson_no()!=null){
				row1.createCell(3) .setCellValue(m.getPerson_no());
			}else{
				row1.createCell(3) .setCellValue("");
			}
			if(m.getPerson_name()!=null){
				row1.createCell(4) .setCellValue(m.getPerson_name());
			}else{
				row1.createCell(4) .setCellValue("");
			}
			if(m.getSex()!=null){
				row1.createCell(5) .setCellValue(m.getSex());
			}else{
				row1.createCell(5) .setCellValue("");
			}
			if(m.getNational()!=null){
				row1.createCell(6) .setCellValue(m.getNational());
			}else{
				row1.createCell(6) .setCellValue("");
			}
			if(m.getIdcard()!=null){
				row1.createCell(7) .setCellValue(m.getIdcard());
			}else{
				row1.createCell(7) .setCellValue("");
			}
			if(m.getPhone()!=null){
				row1.createCell(8) .setCellValue(m.getPhone());
			}else{
				row1.createCell(8) .setCellValue("");
			}
			
			if(m.getWork_nature_name()!=null){
				row1.createCell(9).setCellValue(m.getWork_nature_name());
			}else{
				row1.createCell(9).setCellValue("");
			}
			if(m.getPractice_no()!=null){
				row1.createCell(10).setCellValue(m.getPractice_no());
			}else{
				row1.createCell(10).setCellValue("");
			}		
			if(m.getPractice_startdate()!=null){

				row1.createCell(11).setCellValue(sdf2.format(m.getPractice_startdate()));
			}else{
				row1.createCell(11).setCellValue("");
			}
			if(m.getPractice_enddate()!=null){
				row1.createCell(12).setCellValue(sdf2.format(m.getPractice_enddate()));
			}else{
				row1.createCell(12).setCellValue("");
			}
			
			if(m.getEntry_date()!=null){
				row1.createCell(13).setCellValue(sdf2.format(m.getEntry_date()));
			}else{
				row1.createCell(13).setCellValue("");
			}
			if(m.getEnd_date()!=null){
				row1.createCell(14).setCellValue(sdf2.format(m.getEnd_date()));
			}else{
				row1.createCell(15).setCellValue("");
			}

		}
		
		// 设置response 这样就可以前台弹出框进行下载了
		String fileName = URLEncoder.encode("人员基本信息查询结果导出.xls", "UTF-8");
		ExcelUtils excelUtils = new ExcelUtils();
		excelUtils.writeExcel(list, currentRowNum, currentColNum, wb, sheet, fileName, res);

	}
	
	/**
	 * 下载模板
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/Person/PersonManage/downloadPersonTemplate.do")
	public void downloadPersonTemplate(HttpServletRequest req, HttpServletResponse res)
			throws IOException{
		String save = req.getRealPath("/");
		save = save + "/excel/personInfoTemplate.xls";
		File f = new File(save);
		res.reset();
		res.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			res.setHeader("Content-Disposition",
					"attachment;filename=" + new String(("personInfoTemplate" + ".xls").getBytes(), "iso-8859-1"));
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
	
	/**
	 * 导入
	 * @param req
	 * @param res
	 */
	@RequestMapping("/Person/PersonManage/importPersonInfos.do")
	public void importPersonInfos(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		res.setContentType("text/json;charset=UTF-8");
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		IUserModel user = ServletHelper.getSessionUser(req);
		String info = "";
		try {
			for (MultipartFile file : files.values()) {
				System.out.println(file.getOriginalFilename());
				info = personManageService.importPersonInfos(file, user);
			}
			//resp.getWriter().write("{\"uploadMsg\": \"failure\"}");
			res.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \""+info+"\"}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
		}
	}
	
	
	@RequestMapping("/Person/PersonManage/modifyPersonInfo.do")
	public ModelAndView modifyPersonInfo(HttpServletRequest req, HttpServletResponse res) {
		List<IEnumInfoVOModel> empNoteList = personManageService.querySelectList("emp_note");
		//使用request 对象获取Session , 然后进行操作
		HttpSession session =  req.getSession();
		session.setAttribute("empNoteList", empNoteList);
		ReturnMsg returnMsg = new ReturnMsg();
		personInfoShow(returnMsg,req,res);
		String pageName=req.getParameter("pageName");
		return returnPage(res, returnMsg, "/newtouch/person/personManage/"+pageName);
	}
	@RequestMapping("/Person/PersonManage/enterPersonInfo.do")
	public ModelAndView enterPersonInfo(HttpServletRequest req, HttpServletResponse res)
		throws Exception{
		
			
			return new ModelAndView("/newtouch/person/personManage/enterPersonInfo");
		}
	
	//添加
	@RequestMapping("/Person/PersonManage/insertPersonInfo.do")
	public ModelAndView insertPersonInfo(HttpServletRequest req, HttpServletResponse res) 
			throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException {
		Map<String,String> educationMap;
		educationMap=new HashMap<>();
		educationMap.put("文盲","1");
		educationMap.put("小学","2");
		educationMap.put("初中","3");
		educationMap.put("高中","4");
		educationMap.put("中专","5");
		educationMap.put("中技","6");
		educationMap.put("大专","7");
		educationMap.put("本科","8");
		educationMap.put("研究生及以上","9");
		educationMap.put("博士","10");
		educationMap.put("博士后","11");
		ModelHelper modelHelper = new ModelHelper();
		IPersonManageVOModel personVO = new PersonManageVOModel();
		personVO = (IPersonManageVOModel) modelHelper.getModel(personVO, req);
		Date date = new Date();//获取当前的日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");//设置日期格式
		String str = df.format(date);//获取String类型的时间
		String personNo = str.toString().replace("-","");
		String person_no = personManageService.findSysEmployor(personNo);
		personVO.setPerson_no(person_no);
		ReturnMsg returnMsg = new ReturnMsg();
		//非空项检验
		try{
			//添加金蝶中未存在的字段
			//1.sys_employee
			//防止回显出错
			if(personVO!=null){
				Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
				if (!pattern.matcher(personVO.getEducation()).matches()) {
					personVO.setEducation(educationMap.get(personVO.getEducation()));
				}
			}

            String workNature1=personVO.getWork_nature();
			personManageService.saveEmployee(personVO);
			//2.sys_employee_info
			//person_no,
			personManageService.saveEmployeeInfo(personVO);
			//3.sys_employee_position
			personManageService.saveEmployeePosition(personVO);
			//4.sys_education_info 教育信息
			personManageService.saveEduction(personVO);//新增页面少学历==
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date nowTime = new Date();
			nowTime = sdf1.parse(sdf1.format(nowTime));
			if(personVO!=null && personVO.getPractice_startdate()!=null && !"".equals(personVO.getPractice_startdate()) && personVO.getPractice_enddate()!=null && !"".equals(personVO.getPractice_enddate())){
				Date practice_startdate = sdf.parse(sdf.format(personVO.getPractice_startdate()));
				Date practice_enddate = sdf.parse(sdf.format(personVO.getPractice_enddate()));
				if((practice_startdate.getTime() <= nowTime.getTime()) && (practice_enddate.getTime() >= nowTime.getTime())){
					personVO.setIs_practice("1");
				}else{
					personVO.setIs_practice("0");
				}
			}else if(personVO.getPractice_startdate()!=null && !"".equals(personVO.getPractice_startdate()) && (personVO.getPractice_enddate()==null || "".equals(personVO.getPractice_enddate()))){
				Date practice_startdate = sdf.parse(sdf.format(personVO.getPractice_startdate()));
				if(practice_startdate.getTime() <= nowTime.getTime()){
					personVO.setIs_practice("1");
				}else{
					personVO.setIs_practice("0");
				}
			}else if(personVO.getPractice_enddate()!=null && !"".equals(personVO.getPractice_enddate()) && (personVO.getPractice_startdate()==null || "".equals(personVO.getPractice_startdate()))){
				Date practice_enddate = sdf.parse(sdf.format(personVO.getPractice_enddate()));
				if(practice_enddate.getTime() >= nowTime.getTime()){
					personVO.setIs_practice("1");
				}else{
					personVO.setIs_practice("0");
				}
			}else{
				personVO.setIs_practice("0");
			}
			//3.sys_agent_info
			Boolean flag1 = personManageService.findSysAgentInfo(personVO);
			if(flag1){
				personManageService.modifySysAgentInfo(personVO);
			}else{
				personManageService.insertSysAgentInfo(personVO);
			}
			//5.SYS_EMP_NOTE
			Boolean flag2 = personManageService.findSysEmpNote(personVO);
			if(flag2){
				personManageService.modifySysEmpNote(personVO);
			}else{
				personManageService.insertSysEmpNote(personVO);
			}
			
			returnMsg.setSuccessMessage("保存成功");
			personInfoShow(returnMsg, req, res);
		}catch(Exception e){
			returnMsg.setFailMessage("保存失败");
			e.printStackTrace();
		}
		req.setAttribute("rmHelper1", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		return  returnPage(res,returnMsg,"/newtouch/person/personManage/enterPersonInfo");
	}




	@RequestMapping("/Person/PersonManage/savePersonInfo.do")
	public ModelAndView savePersonInfo(HttpServletRequest req, HttpServletResponse res) 
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException{
		Map<String,String> educationMap;
		educationMap=new HashMap<>();
		educationMap.put("文盲","1");
		educationMap.put("小学","2");
		educationMap.put("初中","3");
		educationMap.put("高中","4");
		educationMap.put("中专","5");
		educationMap.put("中技","6");
		educationMap.put("大专","7");
		educationMap.put("本科","8");
		educationMap.put("研究生及以上","9");
		educationMap.put("博士","10");
		educationMap.put("博士后","11");
		educationMap.put("全日制","1");
		educationMap.put("在职","2");
		ModelHelper modelHelper = new ModelHelper();
		IPersonManageVOModel personVO = new PersonManageVOModel();
		personVO = (IPersonManageVOModel) modelHelper.getModel(personVO, req);
		String person_no = personVO.getPerson_no();
		req.setAttribute("person_no", person_no);
		ReturnMsg returnMsg = new ReturnMsg();
		if(personVO!=null && personVO.getPractice_startdate()!=null && !"".equals(personVO.getPractice_startdate()) && personVO.getPractice_enddate()!=null && !"".equals(personVO.getPractice_enddate())){
			if(personVO.getPractice_startdate().after(personVO.getPractice_enddate())){
				returnMsg.setSuccessMessage("保存失败,执业证有效起期大于止期");
				personInfoShow(returnMsg, req, res);
				req.setAttribute("rmHelper1", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
				return returnPage(res, returnMsg, "/newtouch/person/personManage/modifyPersonInfo");
			}
		}
		if(personVO!=null && personVO.getPolitical_startdate()!=null && !"".equals(personVO.getPolitical_startdate()) && personVO.getPolitical_joindate()!=null && !"".equals(personVO.getPolitical_joindate())){
			if(personVO.getPolitical_startdate().after(personVO.getPolitical_joindate())){
				returnMsg.setSuccessMessage("保存失败,转正时间大于入党时间");
				personInfoShow(returnMsg, req, res);
				req.setAttribute("rmHelper1", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
				return returnPage(res, returnMsg, "/newtouch/person/personManage/modifyPersonInfo");
			}	
		}
		try{
			//修改金蝶中未存在的字段
			if(personVO!=null){
				Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
				if (!pattern.matcher(personVO.getEducation()).matches()) {
					personVO.setEducation(educationMap.get(personVO.getEducation()));
				}
			}
			if(personVO!=null){
				Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
				if (!pattern.matcher(personVO.getEducation_type()).matches()) {
					personVO.setEducation(educationMap.get(personVO.getEducation_type()));
				}
			}
			//1.sys_employee
			personManageService.modifySysEmployee(personVO);
			//2.sys_employee_info
			personManageService.modifySysEmployeeInfo(personVO);
			//3.sys_agent_info
			Boolean flag1 = personManageService.findSysAgentInfo(personVO);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date nowTime = new Date();
			nowTime = sdf1.parse(sdf1.format(nowTime));
			if(personVO!=null && personVO.getPractice_startdate()!=null && !"".equals(personVO.getPractice_startdate()) && personVO.getPractice_enddate()!=null && !"".equals(personVO.getPractice_enddate())){
				Date practice_startdate = sdf.parse(sdf.format(personVO.getPractice_startdate()));
				Date practice_enddate = sdf.parse(sdf.format(personVO.getPractice_enddate()));
				if((practice_startdate.getTime() <= nowTime.getTime()) && (practice_enddate.getTime() >= nowTime.getTime())){
					personVO.setIs_practice("1");
				}else{
					personVO.setIs_practice("0");
				}
			}else if(personVO.getPractice_startdate()!=null && !"".equals(personVO.getPractice_startdate()) && (personVO.getPractice_enddate()==null || "".equals(personVO.getPractice_enddate()))){
				Date practice_startdate = sdf.parse(sdf.format(personVO.getPractice_startdate()));
				if(practice_startdate.getTime() <= nowTime.getTime()){
					personVO.setIs_practice("1");
				}else{
					personVO.setIs_practice("0");
				}
			}else if(personVO.getPractice_enddate()!=null && !"".equals(personVO.getPractice_enddate()) && (personVO.getPractice_startdate()==null || "".equals(personVO.getPractice_startdate()))){
				Date practice_enddate = sdf.parse(sdf.format(personVO.getPractice_enddate()));
				if(practice_enddate.getTime() >= nowTime.getTime()){
					personVO.setIs_practice("1");
				}else{
					personVO.setIs_practice("0");
				}
			}else{
				personVO.setIs_practice("0");
			}
			if(flag1){
				personManageService.modifySysAgentInfo(personVO);
			}else{
				personManageService.insertSysAgentInfo(personVO);
			}
			//4.SYS_EMP_NOTE
			Boolean flag2 = personManageService.findSysEmpNote(personVO);
			if(flag2){
				personManageService.modifySysEmpNote(personVO);
			}else{
				personManageService.insertSysEmpNote(personVO);
			}
			
			returnMsg.setSuccessMessage("保存成功");
			personInfoShow(returnMsg, req, res);
		}catch(Exception e){
			returnMsg.setFailMessage("保存失败");
			e.printStackTrace();
		}
		req.setAttribute("rmHelper1", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		return returnPage(res, returnMsg, "/newtouch/person/personManage/modifyPersonInfo");
	}

	/**
	 * 解约=========================================
	 * @param req
	 * @param res
	 * @return
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
	@RequestMapping("/Person/PersonManage/rescindPersonInfo.do")
	public ModelAndView rescindPersonInfo(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException{
		Map<String,String> educationMap;
		educationMap=new HashMap<>();
		educationMap.put("文盲","1");
		educationMap.put("小学","2");
		educationMap.put("初中","3");
		educationMap.put("高中","4");
		educationMap.put("中专","5");
		educationMap.put("中技","6");
		educationMap.put("大专","7");
		educationMap.put("本科","8");
		educationMap.put("研究生及以上","9");
		educationMap.put("博士","10");
		educationMap.put("博士后","11");
		educationMap.put("全日制","1");
		educationMap.put("在职","2");
		ModelHelper modelHelper = new ModelHelper();
		IPersonManageVOModel personVO = new PersonManageVOModel();
		personVO = (IPersonManageVOModel) modelHelper.getModel(personVO, req);
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			//防止回显出错
			if(personVO!=null){
				Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
				if (!pattern.matcher(personVO.getEducation()).matches()) {
					personVO.setEducation(educationMap.get(personVO.getEducation()));
				}
			}
			if(personVO!=null){
				Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
				if (!pattern.matcher(personVO.getEducation_type()).matches()) {
					personVO.setEducation(educationMap.get(personVO.getEducation_type()));
				}
			}
			personManageService.modifySysEmployee1(personVO);
			returnMsg.setSuccessMessage("保存成功");
			personInfoShow(returnMsg, req, res);
		}catch (Exception e){
			returnMsg.setFailMessage("解约失败");
			e.printStackTrace();
		}

		req.setAttribute("rmHelper1", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		return returnPage(res, returnMsg, "/newtouch/person/personManage/rescindyPersonInfo");
	}
	//跳转到解约页面+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@RequestMapping("/Person/PersonManage/Rescind.do")
	public ModelAndView Rescind(HttpServletRequest req, HttpServletResponse res) {
		List<IEnumInfoVOModel> empNoteList = personManageService.querySelectList("emp_note");
		//使用request 对象获取Session , 然后进行操作
		HttpSession session =  req.getSession();
		session.setAttribute("empNoteList", empNoteList);
		ReturnMsg returnMsg = new ReturnMsg();
		personInfoShow(returnMsg,req,res);
		String pageName=req.getParameter("pageName");
		return returnPage(res, returnMsg, "/newtouch/person/personManage/"+pageName);
	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=
	
	@RequestMapping("/Person/PersonManage/uploadFilePage.do")
	public ModelAndView uploadFilePage(HttpServletRequest req, HttpServletResponse res) 
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException{
		String person_no = ActionHelper.getNullToStr(req.getParameter("person_no"));
		Boolean flag = personManageService.findGleaderInfo(person_no);
		if(flag){
			ReturnMsg gleaderInfo = personManageService.queryGleaderInfo(person_no);
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, gleaderInfo).setReturnParams(gleaderInfo.getDataTable()));
			//return returnPage(res, gleaderInfo, "newtouch/person/personManage/uploadFilePage");
			return new ModelAndView("/newtouch/person/personManage/uploadFilePage");
		}else{
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
			return new ModelAndView("/newtouch/person/personManage/uploadFilePage");
		}
		
	}
	@RequestMapping("/Person/PersonManage/queryTeamNameBrachName.do")
	public ModelAndView queryTeamNameBrachName(HttpServletRequest req,HttpServletResponse res) throws IOException{
		IPersonManageVOModel model=new PersonManageVOModel();
		model.setTeam_id(ActionHelper.getNullToStr(req.getParameter("team_id")));
		res.getWriter().print(personManageService.queryTeamNameBranchName(model));
		return null;
	}
}
