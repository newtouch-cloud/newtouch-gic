package com.newtouch.component.axis2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.newtouch.component.axis2.common.Constant;
import com.newtouch.component.axis2.dao.IQuerySaleInfoDao;
import com.newtouch.component.axis2.model.ProtocolQueryVoModel;
import com.newtouch.component.axis2.model.reqeust.Reserve;
import com.newtouch.component.axis2.model.reqeust.Root;
import com.newtouch.component.axis2.model.reqeust.TransBody;
import com.newtouch.component.axis2.model.reqeust.TransData;
import com.newtouch.component.axis2.model.reqeust.TransHead;
import com.newtouch.core.context.SpringContext;
import com.thoughtworks.xstream.XStream;

/**
* @since:    2014年4月16日   
* @author    ZhangChen
* @description:服务类 QuerySaleInfo 对应的方法类
 */
public class SalesInfoUtil {
	
	IQuerySaleInfoDao querySaleInfoDao = (IQuerySaleInfoDao)SpringContext.getBean("querySaleInfoDao");	
	ResponseXML resXML = new ResponseXML();
	
	/**
	 * 
	* @param xml
	* @return String
	* @description: 处理传入报文,并返回成功/失败报文
	 */
	public String analysisXML(String xml){
		String transType = null; //交易码
		String transDate = null; //数据传送日期
		String transTime = null; //数据传送时间
		
		String agentCode = null;//中介人员/代理人代码
		String agentLicNo = null; //协议号
		String agentGroup = null; // 中介人员所属机构代码 (非必须)
		
		try {//校验xml格式是否正确,不能正确转换时会抛出异常
			
			XStream xstream = new XStream();
			xstream.alias("Reserve", Reserve.class);
			xstream.alias("TransData", TransData.class);
			xstream.alias("TransHead", TransHead.class);
			xstream.alias("TransBody", TransBody.class);
			xstream.alias("Root", Root.class);
			
			Root root = (Root)xstream.fromXML(xml); //转换String型的xml为java对象
			TransData transData = root.getTransData();
			TransHead transHead = transData.getTransHead();
			TransBody transBody =transData.getTransBody();
			
			transType = transHead.getTransType(); //交易码
			transDate = transHead.getTransDate(); //数据传送日期
			transTime = transHead.getTransTime(); //数据传送时间
			
			agentCode = transBody.getAgentCode();//中介人员/代理人代码
			agentLicNo = transBody.getAgentLicNo(); //协议号
			agentGroup = transBody.getAgentGroup(); // 中介人员所属机构代码 (非必须)
			
		} catch (Exception e) {
			//传入的xml字符串无法解析
			System.out.println("┌---------------------------QuerySaleInfo服务发生异常信息开始--------------------------┐");
			System.out.println(e.getMessage());
			System.out.println("└----------------------------QuerySaleInfo服务异常信息结束----------------------------┘");
			return resXML.responseError(Constant.XML_ERROR, "报文解析验证失败：xml格式转换错误",null,null,null,null,null,null);
		}
		
		try {
			
			//校验报文头字段合法性
			String headError = checkHead(transType,transDate,transTime);
			if(!headError.equals("")){
				return resXML.responseError(Constant.TRANDHEAD_ERROR, "报文头解析验证失败:"+headError,transType,transDate,transTime,agentCode,agentLicNo,agentGroup);
			}
			
			//校验报文体字段合法性
			String bodyError = checkBody(agentCode,agentLicNo,agentGroup);
			if(!bodyError.equals("")){
				return resXML.responseError(Constant.TRANDBODY_ERROR, "报文体解析验证失败:"+bodyError,transType,transDate,transTime,agentCode,agentLicNo,agentGroup);
			}
			
			ProtocolQueryVoModel model = querySalesInfo(agentCode,agentLicNo);
			if(model == null){
				//对应的中介人员id不存在
				return resXML.responseError(Constant.NO_SALES_ERROR, "不存在该代理人编码",transType,transDate,transTime,agentCode,agentLicNo,agentGroup);
			}else{
				//查看协议号存不存在 //报错找出多个协议号 待定协议号与机构关系
				List<ProtocolQueryVoModel> PQVList = checkHasAgreement(agentLicNo);
				
				if(PQVList.size() > 0){ //表示协议号存在
					String branch_id = model.getBranch_id();//中介人员的机构代码
					String branch_allpath = model.getBranch_allpath();
					boolean flag = checkAgreementInfo(branch_id,branch_allpath,PQVList,agentLicNo, model.getAgreement_flag());
					if(flag){ //表示接收的参数都是正确的,则返回对应的中介人员id和中介人员姓名
						//参数正确,处理正确,返回成功信息
						return resXML.responseSuccess(Constant.CHECK_SUCCESS, "验证成功", transType,agentLicNo,agentGroup,transDate,transTime,model.getSales_id(),model.getSales_name());
					}else{
						//不能夸机构出单
						return resXML.responseError(Constant.SALESBRANCH_ERROR, "该人员不能跨机构出单",transType,transDate,transTime,agentCode,agentLicNo,agentGroup);
					}
				}else{
					//协议号不存在
					return resXML.responseError(Constant.NO_AGREEMENT_ERROR, "该协议号无效或不存在该协议号",transType,transDate,transTime,agentCode,agentLicNo,agentGroup);
				}
			}
		} catch (Exception e) {
			//catch程序内部发生的异常
			System.out.println("┌---------------------------QuerySaleInfo服务发生异常信息开始--------------------------┐");
			System.out.println(e.getMessage());
			System.out.println("└----------------------------QuerySaleInfo服务异常信息结束----------------------------┘");
			return resXML.responseError(Constant.XML_ERROR, "webservice服务程序内部发生异常",null,null,null,null,null,null);
		}
	
	
	}
	
	/**
	 * 
	* @param transType  交易码  VARCHAR2(10)
	* @param transDate  数据传送日期  VARCHAR2(10)
	* @param transTime  数据传送时间  VARCHAR2(8)
	* @return String
	* @description: 报文头数据合法性校验
	 */
	public String checkHead(String transType,String transDate,String transTime){
		StringBuffer error = new StringBuffer("");
		if(transType == null || "".equals(transType)){
			error.append("交易码为空;");
		}else if(transType.length() > 10){
			error.append("交易码长度过长;");
		}
		
		if(transDate ==null || "".equals(transDate)){
			error.append("数据传送日期为空;");
		}else if(transDate.length() > 10){
			error.append("数据传送日期长度过长;");
		}
		
		//校验日期格式,如果抛异常则表示日期格式错误
		SimpleDateFormat  sdf =   new SimpleDateFormat("yyyy-MM-dd");   
		try {
			java.util.Date  date  =  sdf.parse(transDate);          
			java.sql.Date  sqlDate  =  new java.sql.Date(date.getTime());        
		} catch (ParseException e) {
			error.append("数据传送日期格式错误;");
		}  
			
		
		if(transTime ==null || "".equals(transTime)){
			error.append("数据传送时间为空;");
		}else if(transTime.length() > 8){
			error.append("数据传送时间长度过长;");
		}
		
		//校验数据传送时间格式必须是23:59:59之内的格式  长度过长保存设置校验待测试
		String el = "^([01][0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$";
		Pattern p = Pattern.compile(el);   
	    Matcher m = p.matcher(transTime);   
	    boolean boo = m.matches();  
	    if(!boo){  
	        error.append("数据传送时间格式错误;");
	    }
	    
		return error.toString();
	}
	
	
	/**
	 * 
	* @param agentCode  中介人员代码  VARCHAR2(20)
	* @param agentLicNo 协议号 VARCHAR2(18)
	* @param agentGroup 中介人员机构  VARCHAR2(30)
	* @return String
	* @description: 报文体字段合法性校验
	 */
	public String checkBody(String agentCode,String agentLicNo,String agentGroup){
		StringBuffer error = new StringBuffer("");
		
		if(agentCode == null || "".equals(agentCode)){
			error.append("中介人员代码为空;");
		}else if(agentCode.length() > 20){
			error.append("中介人员代码长度过长;");
		}
		
		if(agentLicNo == null || "".equals(agentLicNo)){
			error.append("协议号为空;");
		}else if(agentLicNo.length() > 19){
			error.append("协议号长度过长;");
		}
		//非必填需要判断
		if(agentGroup != null && !agentGroup.equals("")){
			if(agentGroup.length() > 30){
				error.append("中介人员机构长度过长;");
			}
		}
		
		return error.toString();
	}
	
	
	/**
	 * 
	* @param sales_id
	* @return ProtocolQueryVoModel
	* @description:查找中介人员是否存在
	 */
	private ProtocolQueryVoModel querySalesInfo(String sales_id,String agreement_no){
		ProtocolQueryVoModel model = new ProtocolQueryVoModel();
		model.setSales_id(sales_id);
		model.setAgreement_no(agreement_no);
		return querySaleInfoDao.querySalesInfo(model);//校验中介人员是否存在
		 
	}
	
	/**
	 * 
	* @param agreement_no
	* @return boolean
	* @description:校验协议号是否存在
	 */
	private List<ProtocolQueryVoModel> checkHasAgreement(String agreement_no){
		ProtocolQueryVoModel model = new ProtocolQueryVoModel();
		model.setAgreement_no(agreement_no);
		return querySaleInfoDao.checkAgreemengtInfo(model);//校验协议号是否存在
		 
	}
	
	/**
	 * 
	* @param branch_id中介人员机构代码
	* @param branch_allpath 中介人员机构代码的上下级关系
	* @param PQVList 协议号对应的机构组合
	* @param agreement_no 接收到的协议号
	* @param agreement_flag 判断中介人机构的协议号与接收到的协议号是否相同 0不相同1相同
	* @return boolean
	* @description:校验协议号对应机构代码与中介人员人员的机构代码是否对应
	 */
	private boolean checkAgreementInfo(String branch_id,String branch_allpath,List<ProtocolQueryVoModel> PQVList,String agreement_no,String agreement_flag){
		if(Constant.AGREEMENT_FLAG_YES.equals(agreement_flag)){ //传入的协议号与中介人员机构的协议号相同 则表示通过 0表示机构没有协议号需要查找 上级机构的协议号 1表示有
			return true;
		}else{
			boolean boo = false;
			for(ProtocolQueryVoModel model : PQVList){
				String branchAllpath = model.getBranch_allpath(); //协议号机构代码的上下级关系
				String branchId = model.getBranch_id(); //协议号对应的机构id
				if(branch_allpath.indexOf(branchId) != -1){//判断中介人员所在机构与协议号所有机构是否有上下级机构(找上级机构) 
					boo = true;
					return true; //接收到的中介人员机构id是此协议号对应机构id的下级机构 
				}
				if(branchAllpath.indexOf(branch_id) != -1){ //判断中介人员所在机构与协议号所有机构是否有上下级机构(找下级机构) 
					boo = true;
					return true; //接收到的中介人员机构id是此协议号对应机构id的上级机构
				}
			}
			return boo; //没有上下级关系则返回boo的默认值false
		}
	}

}
