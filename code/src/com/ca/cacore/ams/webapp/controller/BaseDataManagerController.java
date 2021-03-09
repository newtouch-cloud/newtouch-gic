package com.ca.cacore.ams.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.ams.model.vo.BaseDataVOModel;
import com.ca.cacore.ams.model.vo.IBaseDataVOModel;
import com.ca.cacore.ams.webapp.service.IBaseDataManagerService;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.dateutil.DateUtil.DateType;

@Controller
public class BaseDataManagerController extends BaseController{
	@Autowired private IBaseDataManagerService baseDataManagerService;
	
	@RequestMapping("/AMS/BaseDataManagerController/toQueryBaseData.do")
	public ModelAndView toQueryBaseData(HttpServletRequest req,HttpServletResponse res) throws BusinessException{
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/ams/baseDataManager/baseDataManagerQuery");
	}
	
	@RequestMapping("/AMS/BaseDataManagerController/queryBaseData.do")
	public ModelAndView queryBaseData(HttpServletRequest req,HttpServletResponse res)throws BusinessException{

		String typecode=ActionHelper.getNullToStr(req.getParameter("typecode"));
		String created=ActionHelper.getNullToStr(req.getParameter("created"));
		
		IBaseDataVOModel model=new BaseDataVOModel();
		model.setTypecode(typecode);
		model.setCreated(DateUtil.string2Date(created));
		model.setPageCount(ActionHelper.getPage(req));
		
		ReturnMsg returnMsg=baseDataManagerService.queryBaseData(model);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true);
	   
		req.setAttribute("rmHelper",rmHelper);
		return returnPage(res, returnMsg,"ca/cacore/ams/baseDataManager/baseDataManagerQuery");
	}
	
	
	/*@RequestMapping("/AMS/BaseDataManagerController/queryBaseDataTypecode.do")
	public void queryBaseDataTypecode(HttpServletRequest req,HttpServletResponse res)throws Exception{
		String typecode=req.getParameter("typecode");
		IBaseDataVOModel model=new BaseDataVOModel();
		String currTypecode=baseDataManagerService.queryBaseDataByTypecode(model);
		res.getWriter().print(currTypecode);
	}*/
	
	@RequestMapping("/AMS/BaseDataManagerController/addBaseData.do")
	public ModelAndView addBaseData(HttpServletRequest req,HttpServletResponse res)throws BusinessException{				
		String typecode=ActionHelper.getNullToStr(req.getParameter("typecode"));
		String[] codes=req.getParameterValues("code");
		String[] names=req.getParameterValues("name");
		IBaseDataVOModel model=new BaseDataVOModel();
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		ReturnMsg returnMsg=new ReturnMsg();
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(req,returnMsg);
		//增加交验
		for(int i=0;i<codes.length;i++){
			for(int j=codes.length-1;j>i;j--){
				if(codes[j].equals(codes[i])){
					returnMsg.setFailMessage("数据编码不能相同");								
					req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg));
					rmHelper.setReturnMsg(returnMsg);
					return new ModelAndView("ca/cacore/ams/baseDataManager/baseDataAdd");
				}
			}			
		}
		
		//获得最大主类型id
		String typeid=baseDataManagerService.getBaseDataTypeid(model);
		//判断主类型是否存在
		model.setTypecode(typecode);
		String currtypecode=baseDataManagerService.queryBaseDataByTypecode(model);
		if(!"".equals(currtypecode)){
			returnMsg.setFailMessage("主类型已经存在");								
			req.setAttribute("msg",returnMsg);
			return new ModelAndView("ca/cacore/ams/baseDataManager/baseDataAdd");
		}
				
		String tid;//定义类型id
		String id;//定义主键id
		//主类型不存在则插入主类型相关数据获取最大主类型值+1
		//if("".equals(currtid)){
		id=baseDataManagerService.getBaseDataId(model);	
		if("".equals(typeid)){
			model.setTypeid("01");
		}
		tid=Integer.parseInt(typeid)<10?"0"+(Integer.parseInt(typeid)+1):(Integer.parseInt(typeid)+1)+"";
		model.setTypeid(tid);
		model.setTypecode(typecode);
		model.setCode(null);
		model.setLevelnum(0);
		model.setIsleaf("0");
		model.setStatus("0");
		if("".equals(id)){
			model.setId("1");
		}
		int nid=Integer.parseInt(id);
		model.setId((nid+1)+"");
		model.setName(names[0]);
		model.setCreated(DateUtil.sysDate());
		model.setUpdated(DateUtil.sysDate());
		model.setStartdate(DateUtil.date2String(DateUtil.sysDate()));
		model.setEnddate(DateUtil.date2String(DateUtil.addDate(DateUtil.sysDate(), DateType.YEAR, 5)));			
		model.setSeqno(0);
		model.setCreatedby(user.getUserName());
		model.setUpdatedby(user.getUserName());
		try{
			returnMsg = baseDataManagerService.addBaseData(model);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
		}
		
		for(int i=0;i<codes.length;i++){
			//如果当前子类型为空则继续插入下一个
			if("".equals(codes[i])){
				continue;
			}										
			if("".equals(typeid)){
				model.setTypeid("01");
			}
			model.setTypeid(tid);
			model.setTypecode(typecode);
			model.setCode(codes[i]);
			model.setLevelnum(1);
			model.setIsleaf("1");
			model.setStatus("1");			
			id=baseDataManagerService.getBaseDataId(model);
			nid=Integer.parseInt(id);
			model.setId((nid+1)+"");
			model.setName(names[i+1]);
			model.setCreated(DateUtil.sysDate());
			model.setUpdated(DateUtil.sysDate());
			model.setStartdate(DateUtil.date2String(DateUtil.sysDate()));
			model.setEnddate(DateUtil.date2String(DateUtil.addDate(DateUtil.sysDate(), DateType.YEAR, 5)));			
			model.setSeqno(0);
			model.setCreatedby(user.getUserName());
			model.setUpdatedby(user.getUserName());
			try{
				returnMsg = baseDataManagerService.addBaseData(model);
			}catch (BusinessException e){
				returnMsg.setFailMessage(e.getMessage());
			}						
			req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg));
		}
		//}else{
			//主类型存在则循环插入从类型typeid为当前主类型id
			/*for(int i=0;i<codes.length;i++){
				//如果当前子类型为空则继续插入下一个
				if("".equals(codes[i])){
					continue;
				}						
				tid=Integer.parseInt(currtid)<10?"0"+Integer.parseInt(currtid):Integer.parseInt(currtid)+"";
				model.setTypeid(tid);
				model.setTypecode(typecode);
				model.setCode(codes[i]);
				model.setLevelnum(1);
				model.setIsleaf("1");
				model.setStatus("1");			
				id=baseDataManagerService.getBaseDataId(model);
				int nid=Integer.parseInt(id);
				model.setId((nid+1)+"");
				model.setName(names[i+1]);
				model.setCreated(DateUtil.sysDate());
				model.setUpdated(DateUtil.sysDate());
				model.setStartdate(DateUtil.date2String(DateUtil.sysDate()));
				model.setEnddate(DateUtil.date2String(DateUtil.addDate(DateUtil.sysDate(), DateType.YEAR, 5)));			
				model.setSeqno(0);
				model.setCreatedby(user.getUserName());
				model.setUpdatedby(user.getUserName());
				try{
					returnMsg = baseDataManagerService.addBaseData(model);
				}catch (BusinessException e){
					returnMsg.setFailMessage(e.getMessage());
				}						
				req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg));
			}
		}	*/	
		return new ModelAndView("ca/cacore/ams/baseDataManager/baseDataAdd"); 
	}
	
	@RequestMapping("/AMS/BaseDataManagerController/deleteBaseData.do")
	public ModelAndView deleteBaseData(HttpServletRequest req,HttpServletResponse res)throws BusinessException{
		String typecode=ActionHelper.getNullToStr(req.getParameter("typecode"));
		IBaseDataVOModel model=new BaseDataVOModel();
		model.setTypecode(typecode);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg=new ReturnMsg();
		try{
			returnMsg = baseDataManagerService.deleteBaseData(model);
			returnMsg = baseDataManagerService.queryBaseData(model);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
		}	
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true);
		   
		req.setAttribute("rmHelper",rmHelper);
		return returnPage(res, returnMsg,"ca/cacore/ams/baseDataManager/baseDataManagerQuery");
	}
	
	@RequestMapping("/AMS/BaseDataManagerController/modifyBaseData.do")
	public ModelAndView modifyBaseData(HttpServletRequest req,HttpServletResponse res)throws BusinessException{
		String typecode=ActionHelper.getNullToStr(req.getParameter("typecode"));
		IBaseDataVOModel model=new BaseDataVOModel();
		model.setTypecode(typecode);
		ReturnMsg returnMsg=baseDataManagerService.queryListBaseDataTypecode(model);
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);

		return returnPage(res, returnMsg,"ca/cacore/ams/baseDataManager/baseDataManagerMidify");		
		
	}
	
	@RequestMapping("/AMS/BaseDataManagerController/saveModifyBaseData.do")
	public ModelAndView saveModifyBaseData(HttpServletRequest req,HttpServletResponse res)throws BusinessException{
		String typecode=ActionHelper.getNullToStr(req.getParameter("typecode"));
		String[] typeids=req.getParameterValues("typeid");
		String[] codes=req.getParameterValues("code");
		String[] names=req.getParameterValues("name");
		String[] ids=req.getParameterValues("id");
		
		ReturnMsg returnMsg = new ReturnMsg();
		IUserModel user = ServletHelper.getSessionUser(req);
		IBaseDataVOModel model=new BaseDataVOModel();
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(req,returnMsg);
		//增加交验
		for(int i=ids.length;i<codes.length;i++){
			for(int j=0;j<codes.length;j++){
				if(codes[i].equals(codes[j])){
					returnMsg.setFailMessage("数据编码不能相同");								
					req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg));
					rmHelper.setReturnMsg(returnMsg);
					return new ModelAndView("ca/cacore/ams/baseDataManager/baseDataManagerMidify");
				}
			}			
		}
		//更新主类型
		model.setId(ids[0]);
		model.setTypecode(typecode);
		model.setCode(null);
		model.setName(names[0]);		
		try{
			returnMsg = baseDataManagerService.modifyBaseData(model);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
		}
		//循环更新（增，删，改）子类型
		String currid;//获得数据库当前id值
		int nid;//计算插入id值
		for(int i=0;i<codes.length;i++){
			//新增的子类型
			if(i>ids.length-2){
				for(int j=ids.length-1;j<codes.length;j++){
					currid=baseDataManagerService.getBaseDataId(model);
					nid=Integer.parseInt(currid);
					model.setId((nid+1)+"");
					model.setCode(codes[j]);
					model.setName(names[j+1]);
					model.setTypecode(typecode);
					model.setLevelnum(1);
					model.setIsleaf("1");
					model.setStatus("1");
					model.setTypeid(typeids[0]);
					model.setCreated(DateUtil.sysDate());
					model.setUpdated(DateUtil.sysDate());
					model.setStartdate(DateUtil.date2String(DateUtil.sysDate()));
					model.setEnddate(DateUtil.date2String(DateUtil.addDate(DateUtil.sysDate(), DateType.YEAR, 5)));			
					model.setSeqno(0);
					model.setCreatedby(user.getUserName());
					model.setUpdatedby(user.getUserName());
					returnMsg = baseDataManagerService.addBaseData(model);
				}
				req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg));
				break;
			}
			//删除的子类型
			if("".equals(codes[i])){
				model.setId(ids[i+1]);
				returnMsg = baseDataManagerService.deleteBaseDataById(model);
			}
			//修改的子类型
			model.setId(ids[i+1]);
			model.setCode(codes[i]);
			model.setName(names[i+1]);
			model.setTypecode(typecode);
			returnMsg = baseDataManagerService.modifyBaseData(model);			
		}
		req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg,"ca/cacore/ams/baseDataManager/baseDataManagerMidify");
	}
}
