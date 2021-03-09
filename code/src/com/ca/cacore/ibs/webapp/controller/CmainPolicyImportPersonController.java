package com.ca.cacore.ibs.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.webapp.service.ICmainPolicyService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.StringHelper;
import com.newtouch.component.c1properties.StaticProperties;
import com.newtouch.core.basecontroller.BaseController;

@Controller
public class CmainPolicyImportPersonController extends BaseController{
	@Autowired private ICmainPolicyService cmainPolicyService ;
	/**
	 * 转到上传页面
	 */
	@RequestMapping("/CBS/policyImportPerson/policyUpload.do")
	public String toPolicyUpload(){
		return "ca/cacore/cbs/policyLifePerson/propertyInsuranceUpLoad";
	}
	
	/**
	 * 保单导入文件上传
	* 
	* @param req
	* @param resp
	* @throws Exception void
	* @description:
	 */
	 @RequestMapping("/CBS/policyImportPerson/uploadImportInsurance.do")
	 public void importCarInsurance(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		 resp.setContentType("text/json;charset=UTF-8");
	        try {
	            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
	                    req.getSession().getServletContext());
	            // 检查form是否有enctype="multipart/form-data"
	            if (multipartResolver.isMultipart(req)) {
	                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
	                Iterator<String> iter = multiRequest.getFileNames();
	                if (iter.hasNext()) {
	                    // 由CommonsMultipartFile继承而来,拥有上面的方法.
	                    MultipartFile file = multiRequest.getFile(iter.next());
	                    if (file != null) {
	                        String filenameOld = file.getOriginalFilename();
	                        String filename = StringHelper.randomString(10, StringHelper.SEED_LANDL) + "_" + filenameOld;//上传后文件名规则： 10为随机数+"_"+原文件名称
	                        String savePath = StaticProperties.getProperty("temporaryPath");
	                        File localFile = new File(savePath, filename);
	                        if (!localFile.getParentFile().exists()) {
	                            localFile.getParentFile().mkdirs();
	                        }
	                        file.transferTo(localFile);
	                        resp.getWriter().write("{\"uploadMsg\": \"success\", \"uploadFilename\": \"" + filename + "\"}");
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            try {
	                resp.getWriter().write("{\"uploadMsg\": \"failure\"}");
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	 }
	 
	 
	 
		/**
		 * 
		* 
		* @param req
		* @param resp
		* @throws Exception void
		* @description: 保单导入
		 */
	    @RequestMapping("/CBS/policyImportPerson/importInsurance.do")
	    public void importPolicy(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			String info = "";
	        String uploadFileName = ActionHelper.getNullToStr(req.getParameter("uploadFilename"));
	        String excelPath = StaticProperties.getProperty("temporaryPath");
	        IUserModel user = ServletHelper.getSessionUser(req);
	        info = cmainPolicyService.importInsurace(uploadFileName, excelPath, user);
	        //删除上传的excel文件
	        File file = new File(excelPath+uploadFileName);
	        System.out.println(file.canWrite());
	        file.delete();
	    	resp.getWriter().write(info);
	    }	
	
	    
	    /**
		 * 
		* 
		* @param req
		* @param resp
	     * @throws IOException 
		* @throws Exception void
		* @description: 保单导入
		 */
	    @RequestMapping("/CBS/policyImportPerson/importInsurance2.do")
	    public void importPolicy2(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	    	resp.setContentType("text/json;charset=UTF-8");
			MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
			Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
			IUserModel user = ServletHelper.getSessionUser(req);
			String info = "";
			try {
				for (MultipartFile file : files.values()) {
					System.out.println(file.getOriginalFilename());
					info = cmainPolicyService.importInsuracePerson(file, user);
				}
				//resp.getWriter().write("{\"uploadMsg\": \"failure\"}");
				resp.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \""+info+"\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
			}
	    }	
	    
	    
	    
	    /**
		 * 
		* 
		* @param req
		* @param resp
	     * @throws IOException 
		* @throws Exception void
		* @description: 保单导入
		 */
	    @RequestMapping("/CBS/policyImportPerson/importInsurancePerson.do")
	    public void importPolicyPerson(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	    	resp.setContentType("text/json;charset=UTF-8");
			MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
			Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
			IUserModel user = ServletHelper.getSessionUser(req);
			String info = "";
			try {
				for (MultipartFile file : files.values()) {
					System.out.println(file.getOriginalFilename());
					info = cmainPolicyService.importInsuracePerson(file, user);
				}
				//resp.getWriter().write("{\"uploadMsg\": \"failure\"}");
				resp.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \""+info+"\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
			}
	    }	
	    
	
	    /**
		 * 
		* 
		* @param req
		* @param resp
	     * @throws IOException 
		* @throws Exception void
		* @description: 保单导入
		 */
	    @RequestMapping("/CBS/policyImportPerson/importBusiness.do")
	    public void importBusiness(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	    	resp.setContentType("text/json;charset=UTF-8");
			MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
			Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
			IUserModel user = ServletHelper.getSessionUser(req);
			String info = "";
			try {
				for (MultipartFile file : files.values()) {
					System.out.println(file.getOriginalFilename());
					info = cmainPolicyService.importBusinessPreson(file, user);
				}
				//resp.getWriter().write("{\"uploadMsg\": \"failure\"}");
				resp.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \""+info+"\"}");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
			}
	    }	
	
	
	    
	    
	    /**
		 * 
		* 
		* @param req
		* @param resp
	     * @throws IOException 
		* @throws Exception void
		* @description: 保单导入
		 */
	    @RequestMapping("/CBS/policyImportPerson/importInter.do")
	    public void importInter(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	    	resp.setContentType("text/json;charset=UTF-8");
			MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
			Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
			IUserModel user = ServletHelper.getSessionUser(req);
			String info = "";
			try {
				for (MultipartFile file : files.values()) {
					System.out.println(file.getOriginalFilename());
					info = cmainPolicyService.importInterPerson(file, user);
				}
				//resp.getWriter().write("{\"uploadMsg\": \"failure\"}");
				resp.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \""+info+"\"}");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
			}
	    }	
}


/**
 * 保单导入文件上传
* 
* @param req
* @param resp
* @throws Exception void
* @description:
 */
/* @RequestMapping("/CBS/policyImportPerson/uploadImportInsurance.do")
 public void importCarInsurance(HttpServletRequest req, HttpServletResponse resp) throws Exception{
	 resp.setContentType("text/json;charset=UTF-8");
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    req.getSession().getServletContext());
            // 检查form是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(req)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
                Iterator<String> iter = multiRequest.getFileNames();
                if (iter.hasNext()) {
                    // 由CommonsMultipartFile继承而来,拥有上面的方法.
                    MultipartFile file = multiRequest.getFile(iter.next());
                    if (file != null) {
                        String filenameOld = file.getOriginalFilename();
                        String filename = StringHelper.randomString(10, StringHelper.SEED_LANDL) + "_" + filenameOld;//上传后文件名规则： 10为随机数+"_"+原文件名称
                        String savePath = StaticProperties.getProperty("temporaryPath");
                        File localFile = new File(savePath, filename);
                        if (!localFile.getParentFile().exists()) {
                            localFile.getParentFile().mkdirs();
                        }
                        file.transferTo(localFile);
                        resp.getWriter().write("{\"uploadMsg\": \"success\", \"uploadFilename\": \"" + filename + "\"}");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                resp.getWriter().write("{\"uploadMsg\": \"failure\"}");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
 }*/
 