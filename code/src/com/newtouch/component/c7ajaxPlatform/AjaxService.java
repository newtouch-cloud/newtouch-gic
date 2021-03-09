package com.newtouch.component.c7ajaxPlatform;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.context.SpringContext;

@Controller
public class AjaxService extends BaseController{
	
	@RequestMapping("/ajaxplatform/ajax.do")
	public void service(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		try {
			Enumeration<String> enumeration=request.getParameterNames();
			Map<String, String> map=new HashMap<String, String>();
			//这个是请求的服务的名称，就是SPRING中的Bean名称
			String serviceName="servicename";
			String methodName="methodname";
			while (enumeration.hasMoreElements()) {
				String key=enumeration.nextElement();
				if (serviceName.equalsIgnoreCase(key)) { continue; }
				map.put(key, URLDecoder.decode(request.getParameter(key),"UTF-8"));
			}
			//查找执行ajax平台
			//System.out.println(request.getParameter(methodName)+"_______methodName");
			Object service=SpringContext.getBean(request.getParameter(serviceName));
			Class serviceClass=service.getClass();
			System.out.println("class"+service.getClass().getName());
			Method method=serviceClass.getMethod(request.getParameter(methodName),Map.class);
			JSONObject json=(JSONObject)method.invoke(service, map);
			out.print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			out.print("error");
		}finally{
			out.flush();
			out.close();
		}
		
	}
}
