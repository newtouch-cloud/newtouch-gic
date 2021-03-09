package com.newtouch.component.c11assistant;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * C11_11 - RetuMsg Helper Component
 * 查询交易反馈助手
 * @author sln
 *
 */
public class ReturnMsgHelper {
	
	public PageCount pageCount;
	public ReturnMsg returnMsg;
	public Map<String, Object> returnParams = new HashMap<String, Object>();
	
	public ReturnMsgHelper(HttpServletRequest req){
		this.setReturnMsg(new ReturnMsg());
	}
	public ReturnMsgHelper(HttpServletRequest req,ReturnMsg returnMsg,PageCount pageCount){
		this(req,returnMsg,pageCount,false);
	}
	public ReturnMsgHelper(HttpServletRequest req,ReturnMsg returnMsg,PageCount pageCount,boolean isInitParams){
		this.setReturnMsg(returnMsg);
		if(isInitParams || !this.getReturnMsg().isSuccessflag()){
			this.initReturnParams(req);
		}
		this.setPageCount(pageCount);
	}
	
	public ReturnMsgHelper(HttpServletRequest req,ReturnMsg returnMsg){
		this(req,returnMsg,false);
	}
	public ReturnMsgHelper(HttpServletRequest req,ReturnMsg returnMsg,boolean isInitParams){
		this.setReturnMsg(returnMsg);
		if(isInitParams || !this.getReturnMsg().isSuccessflag()){
			this.initReturnParams(req);
		}
		if(!isInitParams){
			String params = ActionHelper.getNullToStr(req.getParameter("params"));
			returnMsg.getDataTable().put("params", params);
		}
	}
	
	/**
	 * 获取分页对象
	 * @return
	 */
	public PageCount getPageCount() {
		if(pageCount==null){
			this.pageCount = ActionHelper.getPage();
		}
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	public ReturnMsg getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(ReturnMsg returnMsg) {
		this.returnMsg = returnMsg;
	}
	public Map<String, Object> getReturnParams() {
		return returnParams;
	}
	public ReturnMsgHelper setReturnParams(Map<String, Object> returnParams) {
		this.returnParams = returnParams;
		return this;
	}
	/**
	 * 初始化返回参数
	 * @param req
	 */
	public void initReturnParams(HttpServletRequest req){
		Enumeration<String> e = req.getParameterNames();
        String parameterName;
        while(e.hasMoreElements()){
            parameterName = e.nextElement();
            String parameterValue="";
			try {
				parameterValue = URLDecoder.decode(req.getParameter(parameterName).replaceAll("%", "%25"),"utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
            String[] strListValue = req.getParameterValues(parameterName);
            if(strListValue.length >=2 ){
            	parameterValue = TransHelper.transStrBySplit(strListValue, TransHelper.SPLIT_DEFAULT);
            }
            returnParams.put(parameterName, parameterValue);
        }
	}
	
	/**
	 * 刷新内置信息
	 * @param returnMsg
	 */
	public void flushMsg(ReturnMsg returnMsg) {
		if(returnMsg!=null){
			if(returnMsg.isSuccessflag()){
				this.getReturnMsg().setSuccessMessage(returnMsg.getMsgStr());
			}else{
				this.getReturnMsg().setFailMessage(returnMsg.getMsgStr(), true);
			}
		}
		
	}
	
	/**
	 * 获取返回参数
	 * @param key
	 * @return
	 */
	public Object getReturnParam(String key){
		return this.returnParams.get(key);
	}
	
	/**
	 * 设置错误消息
	 * @param msgStr
	 */
	public void setFailMessage(String msgStr){
		this.getReturnMsg().setFailMessage(msgStr, true);
	}
	
	/**
	 * 设置成功消息
	 * @param msgStr
	 */
	public void setSuccessMessage(String msgStr){
		this.getReturnMsg().setSuccessMessage(msgStr);
	}
	
	/**
	 * 获取提示信息消息
	 * @return
	 */
	public String getMsgStr(){
		return this.getReturnMsg().getMsgStr();
	}
	
	/**
	 * 获取交易状态
	 * @return
	 */
	public boolean isSuccessflag(){
		return this.getReturnMsg().isSuccessflag();
	}
	
}
