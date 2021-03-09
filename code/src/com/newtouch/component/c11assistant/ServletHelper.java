package com.newtouch.component.c11assistant;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.UserModel;
import com.newtouch.component.s10monitor.mlog.MRequestHeader;
import com.newtouch.core.quanxianguanli.pojo.User;

/**
 * C11_6 - Servlet Helper Component
 * Servlet处理助手
 * @author sln
 *
 */
public class ServletHelper {

	/**
	 * 获取HTTP客户端浏览器版本及型号
	 * @param req
	 * @return
	 */
	public static String getHttpClientAgent(HttpServletRequest req){
		return req.getHeader("User-Agent");
	}
	
	/**
	 * 获取HTTP客户端IP地址 
	 * @param req
	 * @return
	 */
	public static String getHttpClientIpAddr(HttpServletRequest req) {  
	    String ip = req.getHeader("x-forwarded-for");  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = req.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = req.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = req.getRemoteAddr();  
	    }  
	    return ip;  
	}
	
	/**
	 * 获取HTTP请求URL链接完整地址
	 * @param req
	 * @return
	 */
	public static String getHttpRequestURL(HttpServletRequest req){
		return req.getRequestURL().toString();
	}
	
	/**
	 * 获取HTTP请求完整参数
	 * @param req
	 * @return
	 */
	public static String getHttpRequestQueryString(HttpServletRequest req){
		StringBuffer qStr = new StringBuffer();
		Enumeration<String> e = req.getParameterNames();
        String parameterName, parameterValue = "";
        while(e.hasMoreElements()){
            parameterName = e.nextElement();
            if("nowPage".equals(parameterName)){continue;}
            try {
            	parameterValue=URLDecoder.decode(req.getParameter(parameterName).replaceAll("%", "%25"),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
            TransHelper.stringBufferAppender(qStr,parameterName,"=",parameterValue,"&");
        }
//        System.out.println(qStr);
        return qStr.toString();
	}
	
	/**
	 * 获取HTTP请求完整参数
	 * @param req
	 * @return
	 */
	public static String getHttpRequestQueryString2(HttpServletRequest req){
		StringBuffer qStr = new StringBuffer();
		Enumeration<String> e = req.getParameterNames();
        String parameterName, parameterValue = "";
        while(e.hasMoreElements()){
            parameterName = e.nextElement();
            if("nowPage".equals(parameterName)){continue;}
            try {
            	parameterValue=req.getParameter(parameterName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            TransHelper.stringBufferAppender(qStr,parameterName,"=",parameterValue,"&");
        }
//        System.out.println(qStr);
        return qStr.toString();
	}
	
	/**
	 * 获取HTTP请求信息
	 * @param req
	 * @return
	 */
	public static MRequestHeader getMRequestHeader(HttpServletRequest req){
		return new MRequestHeader(req);
	}

	/**
	 * 获取服务端IP地址列表
	 * @return
	 */
	public static String getHostIp() {
		try{
			Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()){
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()){
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address){
						System.out.println("网卡名=["+netInterface.getName()+"] 本机的IP = " + ip.getHostAddress());
					} 
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return " ip not found!";
		}
		return "";
	}
	/**
	 * 
	 * yanqiguang
	 * 2017年12月1日下午4:02:09
	 * TODO 在数据权限变更之后，需要更新IUserModel中的数据
	 */
	public static IUserModel getSessionUser(HttpServletRequest request) {
		IUserModel user=(IUserModel) request.getSession().getAttribute("user");
		User userSession=(User) request.getSession().getAttribute("CF_USER");
		if (user==null) {
			user=new UserModel();
			if(userSession!=null){
				user.setBranch_id(userSession.getDid());
				user.setUserName(userSession.getOptName());
				user.setSex_code(userSession.getSex());
				user.setEmail(userSession.getMail());
				user.setStatus(userSession.getStatus());
				user.setPassword(userSession.getOptpass());
				user.setUser_type(userSession.getOptType());
				user.setDept_list(userSession.getDeptList());
//			user.setSeq_id();
				user.setEmp_id(userSession.getOptID());
			}//确保IUserModel 中数据是session 中的最新数据  
		}else{
			user.setDept_list(userSession.getDeptList());
		}
		return user;
	}
}
