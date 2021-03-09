package com.ca.cacore.ibs.webapp.controller;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.bo.PolicyImageModel;
import com.ca.cacore.ibs.webapp.service.IPolicyImageService;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.StringHelper;
import com.newtouch.component.c1properties.StaticProperties;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.context.SpringContext;


/**
 * 文件上传实例
 * @author samLee
 *
 */
public class PolicyImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 2384226688121073713L;
       
    public PolicyImageUploadServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IUserModel user = ServletHelper.getSessionUser(request);; //获取用户
		
		//文件存放的目录
		//String rootPath = request.getSession().getServletContext().getRealPath("/");
		String imagePath = StaticProperties.getProperty("imageUploadPath");//从ini文件中读取保存中间地址
		String webPath = StaticProperties.getProperty("webPath");//从ini文件中读取配置的虚拟目录名称
		String savePath = imagePath.substring(imagePath.indexOf(webPath), imagePath.length()); //截取网络访问地址用于入库
		String dayPath = DateHelper.getSysStr("yyyyMMdd");
		String hourPath = DateHelper.getSysStr("HH");
		String path = imagePath + dayPath+"/"+hourPath+ "/";
		
		File tempDirPath =new File(path);
		
		if(!tempDirPath.exists()){
			tempDirPath.mkdirs();
		}
		
		//创建磁盘文件工厂
		DiskFileItemFactory fac = new DiskFileItemFactory();    
		//创建servlet文件上传组件
		ServletFileUpload upload = new ServletFileUpload(fac);    
		//文件列表
		List fileList = null;    
        //解析request从而得到前台传过来的文件
		try {    
            fileList = upload.parseRequest(request);    
        } catch (FileUploadException ex) {    
            ex.printStackTrace();    
            return;    
        } 
        //前段显示保存后的图片需要使用到的路径
        String webShowPath = null;
        //便利从前台得到的文件列表
        Iterator<FileItem> it = fileList.iterator();  
        String saveFile = "";
        String file_name = "";//上传后的文件名称
		String file_name_old = "";//上传前文件名称
		String extName = "";   //上传的文件后缀
        while(it.hasNext()){    
            FileItem item =  it.next();   
            //如果不是普通表单域，当做文件域来处理
            if(!item.isFormField()){
            file_name_old = item.getName();
            if (file_name_old.lastIndexOf(".") >= 0) {
				 extName = file_name_old.substring(file_name_old.lastIndexOf("."));// 扩展名
			 }
            if(ValidateHelper.checkChinese(file_name_old)){ //判断是否存在汉子，存在为true,存在汉子则不保存汉子文件名-->因为预览时有中文文件名则预览不了
            	file_name_old = StringHelper.randomString(5, StringHelper.SEED_LANDL)+extName;
            }
            file_name = StringHelper.randomString(10, StringHelper.SEED_LANDL)+"_"+file_name_old;//上传后文件名规则： 10为随机数+"_"+原文件名称
            
            webShowPath = savePath+ dayPath+"/"+hourPath+ "/"+file_name; //用于前端浏览器预览图片,需要使用网络地址webPath为 为文件保存目录配置的虚拟目录地址
            saveFile = path+file_name;
            
            	BufferedInputStream in = new BufferedInputStream(item.getInputStream());   
                BufferedOutputStream out = new BufferedOutputStream(      
                        new FileOutputStream(new File(saveFile)));
                Streams.copy(in, out, true);
                //长度超过15位js自动会把多的认为是0,0开头的则直接忽视0值故有以下设置
                String file_id ="9"+StringHelper.randomString(9, StringHelper.SEED_NUM);
                
               //为影像model赋值
                IPolicyImageModel model = new PolicyImageModel(file_id, "0",
            			"未关联", -1L, 1,webShowPath,file_name,"",user.getEmp_id(),user.getEmp_id());
                IPolicyImageService policySer = (IPolicyImageService)SpringContext.getBean("policyImageService");
                policySer.addPolicyImage(model);
                
                PrintWriter pw = this.encodehead(request, response);
        		//使用^符号拼接 图片保存的路径 和 图片的id 传到前台
        		pw.write(webShowPath+"^"+file_id);
        		pw.flush();
        		pw.close(); 
                
            }
        }
        
	}
	
	/**
	 * Ajax辅助方法 获取 PrintWriter
	 * @return
	 * @throws IOException 
	 * @throws IOException 
	 * request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
	 */
	private PrintWriter encodehead(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		return response.getWriter();
	}
}
