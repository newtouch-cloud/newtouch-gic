package com.ca.cacore.manage.webapp.controller;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.IUserPortraitModel;
import com.ca.cacore.manage.model.bo.UserAuthsModel;
import com.ca.cacore.manage.model.bo.UserModel;
import com.ca.cacore.manage.model.bo.UserPortraitModel;
import com.ca.cacore.manage.model.vo.UserAuths_Role_BranchModel;
import com.ca.cacore.manage.webapp.service.IUserService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.StringHelper;
import com.newtouch.component.c1properties.StaticProperties;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @author 吴思波
 * @since 2013-11-11
 * @discrible 用户管理Controller层
 */
@Controller
public class UserController extends BaseController{

	@Autowired private IUserService userService;

	/**
	 * 查询user
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/queryUser.do")
	public ModelAndView queryUser(HttpServletRequest req,HttpServletResponse res) {
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id")); //机构代码
		String user_type = ActionHelper.getNullToStr(req.getParameter("user_type")); //用户类型
		String userName = ActionHelper.getNullToStr(req.getParameter("userName")); //用户名
		String name = ActionHelper.getNullToStr(req.getParameter("name")); //姓名
		String status = ActionHelper.getNullToStr(req.getParameter("status")); //状态
		IUserModel model = new UserModel();				
		model.setBranch_id(branch_id);
		model.setUser_type(user_type);
		model.setUserName(userName);
		model.setName(name);
		model.setStatus(status);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = userService.queryUser(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res, returnMsg, "ca/cacore/manage/user/userQuery");
		
	}
	
	/**
	 * 添加user
	 * @param req
	 * @param res
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws Exception
	 */
	@RequestMapping("/User/addUser.do")
	public ModelAndView addUser(HttpServletRequest req,HttpServletResponse res) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		String user_type = ActionHelper.getNullToStr(req.getParameter("user_type"));//用户类型
		String userName = ActionHelper.getNullToStr(req.getParameter("userName"));//用户名
		String password = ActionHelper.getNullToStr(StringHelper.encoderByMd5(req.getParameter("password")));//登陆密码
		String name = ActionHelper.getNullToStr(req.getParameter("name"));//姓名	
		String sex_code = ActionHelper.getNullToStr(req.getParameter("sex_code"));//性别
		String mobile = ActionHelper.getNullToStr(req.getParameter("mobile")); //手机
		String fixed_line = ActionHelper.getNullToStr(req.getParameter("fixed_line"));//固定电话
		String email = ActionHelper.getNullToStr(req.getParameter("email"));//电子邮件
		String emp_id = ActionHelper.getNullToStr(req.getParameter("emp_id"));//员工代码
		String job_tel = ActionHelper.getNullToStr(req.getParameter("job_tel"));//办公电话
		String job_address = ActionHelper.getNullToStr(req.getParameter("job_address"));//办公地址
		String status = ActionHelper.getNullToStr(1);//状态
		String remark = ActionHelper.getNullToStr(req.getParameter("remark"));	//备注	 
		IUserModel model = new UserModel();
		model.setBranch_id(branch_id);
		model.setUser_type(user_type);
		model.setUserName(userName);
		model.setPassword(password);
		model.setName(name);
		model.setSex_code(sex_code);
		model.setMobile(mobile);
		model.setFixed_line(fixed_line);
		model.setEmail(email);
		model.setEmp_id(emp_id);
		model.setJob_tel(job_tel);
		model.setJob_address(job_address);
		model.setStatus(status);
		model.setRemark(remark);
		model.setCreateUser("a");
		model.setModifyUser("a");
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = userService.addUser(model);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg));
		return new ModelAndView("ca/cacore/manage/user/userAdd");
	}
	
	/**
	 * 修改跳转user
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/userGoModify.do")
	public ModelAndView userGoModify(HttpServletRequest req,HttpServletResponse res) {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		String userName = null;
		IUserModel model = new UserModel(seq_id,userName);
		ReturnMsg returnMsg = userService.getUser(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/manage/user/userModify");
	}
	
	/**
	 * 修改保存User
	 * @param req
	 * @param res
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws Exception
	 */
	@RequestMapping("/User/userModify.do")
	public ModelAndView userModify(HttpServletRequest req,HttpServletResponse res) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		String user_type = ActionHelper.getNullToStr(req.getParameter("user_type"));//用户类型
		String userName = ActionHelper.getNullToStr(req.getParameter("userName"));//用户名	
		String password = ActionHelper.getNullToStr(StringHelper.encoderByMd5(req.getParameter("password")));//登陆密码
		String name = ActionHelper.getNullToStr(req.getParameter("name"));//姓名	
		String sex_code = ActionHelper.getNullToStr(req.getParameter("sex_code"));//性别
		String mobile = ActionHelper.getNullToStr(req.getParameter("mobile"));//手机
		String fixed_line = ActionHelper.getNullToStr(req.getParameter("fixed_line"));//固定电话
		String email = ActionHelper.getNullToStr(req.getParameter("email")); //电子邮件
		String emp_id = ActionHelper.getNullToStr(req.getParameter("emp_id"));//员工代码
		String job_tel = ActionHelper.getNullToStr(req.getParameter("job_tel"));//办公电话
		String job_address = ActionHelper.getNullToStr(req.getParameter("job_address"));//办公地址
		String remark = ActionHelper.getNullToStr(req.getParameter("remark")); //备注
		IUserModel model = new UserModel(seq_id);
		model.setBranch_id(branch_id);
		model.setUser_type(user_type);
		model.setUserName(userName);
		model.setPassword(password);
		model.setName(name);
		model.setSex_code(sex_code);
		model.setMobile(mobile);
		model.setFixed_line(fixed_line);
		model.setEmail(email);
		model.setEmp_id(emp_id);
		model.setJob_tel(job_tel);
		model.setJob_address(job_address);
		model.setRemark(remark);
        model.setModifyUser("a");
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = userService.updateUser(model);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/manage/user/userModify");
	}
	
	/**
	 * 明细查看user
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/userView.do")
	public ModelAndView userView(HttpServletRequest req,HttpServletResponse res) {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		String userName = null;
		IUserModel model = new UserModel(seq_id,userName);
		ReturnMsg returnMsg = userService.getUser(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg));
		return new ModelAndView("ca/cacore/manage/user/userView");
    }
	
	
	/**
	 * 查看用户权限分配信息
	 * 获取角色信息
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/userAuths.do")
	public ModelAndView goUserAuths(HttpServletRequest req,HttpServletResponse res){
		UserAuths_Role_BranchModel roleModel = new UserAuths_Role_BranchModel();
		String seq_id = ActionHelper.getNullToStr(req.getParameter("seq_id"));
		roleModel.setUserName("a");
		ReturnMsg returnMsg = userService.queryUserRole(roleModel);//获取角色列表信息
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/manage/user/userAuths");
		
	}
	
	/**
	 * 查看用户权限分配信息
	 * 获取机构信息
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/queryUserRoles.do")
	public ModelAndView queryUserRoles(HttpServletRequest req,HttpServletResponse res) {
		String role_id = ActionHelper.getNullToStr(req.getParameter("role_id"));//角色id
		UserAuths_Role_BranchModel branchModel = new UserAuths_Role_BranchModel();
		branchModel.setRole_id(role_id);
		ReturnMsg returnMsg = userService.queryUserRoles(branchModel);//获取机构信息
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/manage/user/userAuths");
	}
	
	
	
	/**
	 * 
	 *用户权限分配
	 */
	
	@RequestMapping("/User/goUserAuths.do")
	public ModelAndView queryUserAuthsVO(HttpServletRequest req,HttpServletResponse res){
		String userName = ActionHelper.getNullToStr(req.getParameter("userName"));
		IUserModel model = new UserModel();
		model.setUserName(userName);
		ReturnMsg returnMsg = userService.queryUserAuthsVO(model);
		req.setAttribute("userName",userName);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg));
		return new ModelAndView("ca/cacore/manage/user/userAuths");
	}
	
	
	
	/**
	 * 获取机构树ajax请求
	 * @throws IOException 
	 */
	
	@RequestMapping("/User/getBranchTree.do")
	public ModelAndView getBranchTree(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String userName = ActionHelper.getNullToStr(req.getParameter("userName"));
		String role_id = ActionHelper.getNullToStr(req.getParameter("role_id"));
		UserAuthsModel model = new UserAuthsModel();
		model.setUserName(userName);
		model.setRole_id(role_id);
		List<String> lstTree=	userService.queryBranchTree(model);
		res.getWriter().print(JSONArray.fromObject(lstTree).toString());
		return null;
	}
	
	
	
	/**
	 * author:liu_zheng主要是完成用户权限的保存
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/User/updateUserAuths.do")
	public ModelAndView updateUserAuths(HttpServletRequest req,HttpServletResponse res) throws Exception{
		String userName = ActionHelper.getNullToStr(req.getParameter("username").trim());
		String branch_id =ActionHelper.getNullToStr(req.getParameter("branch_id").trim());
		//String role_id =ActionHelper.getNullToStr(req.getParameter("role_id").trim());
		String seq_id=ActionHelper.getNullToStr(req.getParameter("seq_id").trim());
		String [] checkRoles=req.getParameterValues("role");
		IUserModel user=new UserModel();
		user.setSeq_id(Integer.valueOf(seq_id));
		user.setUserName(userName);
		user.setBranch_id(branch_id);
		IUserModel userModel=ActionHelper.getUserFromSession(req);
		ReturnMsg msg=userService.updateUserAuths(user, checkRoles,userModel);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg));
		return new ModelAndView("ca/cacore/manage/user/userAuths");
		
		
//		String isChecked = ActionHelper.getNullToStr(req.getParameter("isChecked").trim());
//		BranchTree bt = new BranchTree();
//		bt.setUserName(userName);
//		bt.setBranch_id(branch_id);
//		bt.setRole_id(role_id);
//		bt.setIsChecked(isChecked);
//		
//		res.getWriter().print("操作成功!");
//		return null;
	}
	/**
	 * author 刘峥
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/queryUserAuths.do")
	public ModelAndView queryUserAuths(HttpServletRequest request,HttpServletResponse res) throws Exception {
		String seq_id=ActionHelper.getNullToStr(request.getParameter("seq_id").trim());
		IUserModel user=new UserModel();
		user.setSeq_id(Integer.valueOf(seq_id));
		ReturnMsg msg=userService.getUserAndAuths(user);
		request.setAttribute("rmHelper", new ReturnMsgHelper(request, msg));
		return new ModelAndView("ca/cacore/manage/user/userAuths");
	}
	
	/**
	 * 头像上传界面
	 * author 张晨
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/addUserPortrait.do")
	public ModelAndView addUserPortrait(HttpServletRequest req,HttpServletResponse res) throws Exception {
		

		return new ModelAndView("ca/cacore/manage/user/userAddPortrait");
	}
	
	/**
	 * 头像裁剪界面
	 * author 张晨
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/goTailorPortrait.do")
	public ModelAndView goTailorPortrait(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String portraitName = ActionHelper.getNullToStr(req.getParameter("portraitName"));
		req.setAttribute("portraitName", portraitName);
		return new ModelAndView("ca/cacore/manage/user/userTailorPortrait");
	}
	
	
	/**
	 * 头像上传并压缩成300*350 方便进行裁剪  ,这个大小需要和jsp中裁剪的img 大小一样
	 * author 张晨
	 * @param req
	 * @param resq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/uploadPortraitFile.do")
    public ModelAndView uploadPortraitFile(HttpServletRequest req, HttpServletResponse resp) {

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
                        String extName = filenameOld.substring(filenameOld.lastIndexOf("."));// 扩展名
                         
                        String filename = StringHelper.randomString(10, StringHelper.SEED_LANDL) + extName;//上传后文件名规则： 10为随机数+原文件后缀
                       
                        //String savePath = req.getSession().getServletContext().getRealPath("/portrait/temporary/"); //文件夹
                        String savePath = StaticProperties.getProperty("temporaryPath"); //获取临时文件夹目录
                        String webPath = StaticProperties.getProperty("webPath");
                        String path = savePath.substring(savePath.indexOf(webPath), savePath.length())+filename; //截取网络访问地址用于预览
                        File localFile = new File(savePath, filename);

                        if (!localFile.getParentFile().exists()) {
                            localFile.getParentFile().mkdirs();
                        }
                        
                        file.transferTo(localFile); //保存原头像
                        
                        reduce(savePath,filename, 300, 350,true); //压缩头像
                        
                        resp.getWriter().write("{\"uploadMsg\": \"success\", \"portraitName\": \"" +filename + "\", \"portraitPath\": \"" +path+ "\"}");

                        return null;
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

        return null;
    }
	
	
	
	/**
	 * 对压缩的头像进行裁剪
	 * author 张晨
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/User/toTailorPortrait.do")
	public ModelAndView toTailorPortrait(HttpServletRequest req,HttpServletResponse res) throws Exception {
		//获取session中的用户
		IUserModel user = ServletHelper.getSessionUser(req); //获取用户
		//获取传参
		Integer left = ActionHelper.getNullToInteger(req.getParameter("x"));
		Integer top = ActionHelper.getNullToInteger(req.getParameter("y"));
		Integer width = ActionHelper.getNullToInteger(req.getParameter("w"));
		Integer height = ActionHelper.getNullToInteger(req.getParameter("h"));
		String portraitName = ActionHelper.getNullToStr(req.getParameter("portraitName"));
		
		String temporaryPath = StaticProperties.getProperty("temporaryPath")+portraitName;//获取临时存放头像空间地址
		
		String ext = portraitName.substring(portraitName.lastIndexOf(".")+1); //获取文件后缀名称

		  String filename = user.getEmp_id()+"_"+ portraitName;
		  //裁剪后的保存到用户专有的文件夹中
		  String basePath = StaticProperties.getProperty("portraitPath")+user.getEmp_id();
		  //获取虚拟目录路径
          String webPath =  StaticProperties.getProperty("webPath");
		  //入库地址
		  String savePath = basePath.substring(basePath.indexOf(webPath), basePath.length())+"/"+filename; //截取网络访问地址用于入库
		  //保存图片之前删除用户头像文件夹中的其他图片
          File userFile = new File(basePath); 
		  
		  File localFile = new File(basePath,filename);

          if (!localFile.getParentFile().exists()) {
              localFile.getParentFile().mkdirs();
          }else{
        	  deleteDir(userFile); //如果用户已经保存了头像则调用删除原有头像操作
          }
           //调用剪切图片方法
           cut(left,top,width,height,ext,temporaryPath,localFile);
		   
		   File file1 = new File(temporaryPath); //删除临时头像文件
		   file1.delete(); //删除临时文件

		   //长度超过15位js自动会把多的认为是0,0开头的则直接忽视0值故有以下设置
           String file_id ="9"+StringHelper.randomString(9, StringHelper.SEED_NUM);//头像id
           
           IUserPortraitModel model = new UserPortraitModel();
           model.setFile_id(file_id);
           model.setEmp_id(user.getEmp_id());
           model.setFile_name(filename);
           model.setFile_path(savePath);
           //保存用户头像信息
           userService.addUserPortrait(model);
           //重新设置usersession 用于更新用户的头像地址
           req.getSession().removeAttribute("user");
           User cf_user = (User) req.getSession().getAttribute("CF_USER");
           req.getSession().setAttribute("user", user2UserModel(cf_user));
           
           res.getWriter().write("{\"uploadMsg\": \"success\", \"portraitPath\": \"" +savePath+ "\"}");

           return null;
	}
	
	
	 /**
 	 * @see 剪切图片 (第二套剪切方法 2014-2-20 by张晨)
 	 * @param x x坐标 对应left
 	 * @param y y坐标 对应top
 	 * @param width 长
 	 * @param height 高
 	 * @param ext 后缀名
 	 * @param srcpath 临时文件地址
 	 * @param subpath 剪切后需要保存的文件
 	 * @return void
 	 */
	private void cut(Integer x,Integer y,Integer width, Integer height,String ext,String srcpath,File subpath) throws IOException{ 
        
        FileInputStream is = null ;
        ImageInputStream iis =null ;
     
        try{   
            //读取图片文件
            is = new FileInputStream(srcpath); 
            
            /*
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 
             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
             *（例如 "jpeg" 或 "tiff"）等 。 
             */
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");  
            ImageReader reader = it.next(); 
            //获取图片流 
            iis = ImageIO.createImageInputStream(is);
               
            /* 
             * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
             *  避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
             */
            reader.setInput(iis,true) ;
            
            /* 
             * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O 
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回 
             * ImageReadParam 的实例。  
             */
            ImageReadParam param = reader.getDefaultReadParam(); 
             
            /*
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
             * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。 
             */ 
            Rectangle rect = new Rectangle(x, y, width, height); 
            
              
            //提供一个 BufferedImage，将其用作解码像素数据的目标。 
            param.setSourceRegion(rect); 

            /*
             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
             * 它作为一个完整的 BufferedImage 返回。
             */
            BufferedImage bi = reader.read(0,param);                
      
            //保存新图片 
            ImageIO.write(bi, ext, subpath);     
        }
        
        finally{
            if(is!=null)
               is.close() ;       
            if(iis!=null)
               iis.close();  
        } 
    }

	
	
     /**
 	 * @see 压缩图片 -- by 张晨
 	 * @param base 根目录
 	 * @param imgPath 要压缩的图片路径
 	 * @param width 压缩宽
 	 * @param height 压缩高
 	 * @param percentage 是否等比例压缩,true则宽高比自动调整
 	 * @return void
 	 */
 	private void reduce(String base, String imgPath, int width, int height, boolean percentage) {
 		try {
 			File srcfile = new File(base+"/"+imgPath);
 			BufferedImage src = ImageIO.read(srcfile);
 			if (percentage) {
 				double rate1 = ((double) src.getWidth(null)) / (double) width + 0.1;
 				double rate2 = ((double) src.getHeight(null)) / (double) height + 0.1;
 				double rate = rate1 > rate2 ? rate1 : rate2;
 				width = (int) (((double) src.getWidth(null)) / rate);
 				height = (int) (((double) src.getHeight(null)) / rate);
 			}
 			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);				
 			image.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING), 0, 0, null);
 			FileOutputStream out = new FileOutputStream(base+"/"+imgPath);
 			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
 			encoder.encode(image);
 			image.flush();
 			out.flush();
 			out.close();
 			src.flush();
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		}
 	}
 	
    /**
     * 
    * 用于上传头像重新保存新的头像地址给用户session中  by张晨
    * @param user
    * @return IUserModel
    * @description:从新设置UserModel session 
     */
    private IUserModel user2UserModel(User user){
		IUserModel userModel=new UserModel();
		userModel.setBranch_id(user.getDid());
		userModel.setEmp_id(user.getOptID());
		userModel.setUserName(user.getOptName());
		userModel.setSex_code(user.getSex());
		userModel.setEmail(user.getMail());
		userModel.setStatus(user.getStatus());
		userModel.setPassword(user.getOptpass());
		userModel.setUser_type(user.getOptType());
		userModel.setPortraitPath(userService.getPortraitPath(userModel));//获取用户头像地址-by张晨
		return userModel;
	}
    
    /**
     * 
    * by张晨
    * @param dir void
    * @description://更新用户头像时进行删除已有头像操作
     */
    private void deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();//递归删除目录中的子目录下
            if(children.length > 0){
            	for (int i=0; i<children.length; i++) {
                    new File(dir, children[i]).delete();
                }
            }
            
        }
    }
	
}
