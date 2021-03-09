package com.ca.cacore.msss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.domain.IInsRncDfnDomain;
import com.ca.cacore.msss.model.bo.IInsRncDfnModel;
import com.ca.cacore.msss.model.vo.IInsRncDfnVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 
 * @author  Wang_ds
 * @since 2013-11-10
 * @description 产品信息Service层
 */
@Service
public class InsRncDfnService extends ServerBase implements IInsRncDfnService  {

	@Autowired
	private IInsRncDfnDomain InsRncDfnDomain;


	/**
	 * @param 传入IInsRncDfnVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询出险类信息，或根据条件查询
	 */
	public ReturnMsg queryInsRncDfnList(IInsRncDfnModel model) {
		ReturnMsg msg = new ReturnMsg();
		System.out.println("我进来了");
		List<IInsRncDfnModel> relist = InsRncDfnDomain.queryInsRncDfnList(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description: 新增 其他保险公司 产品信息
	*/
	public ReturnMsg insRncDfnAdd(IInsRncDfnModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		IInsRncDfnModel m=InsRncDfnDomain.checkLimitsBranchid(model);//权限  by zdd02
		if(m==null) {
			returnMsg.setFailMessage("无权限添加该保险公司信息！");
		}else {
			try{
				boolean flag=InsRncDfnDomain.insRncDfnAdd(model);
				if(flag){
					returnMsg.setSuccessMessage("添加成功");
				}
			}catch(BusinessException be){
				returnMsg.setFailMessage(be.getMessage(), true);
			}
		}
		return returnMsg;
	}
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:修改 其他保险公司 产品信息
	*/
	public ReturnMsg insRncDfnModify(IInsRncDfnModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		try{
			boolean flag=InsRncDfnDomain.insRncDfnModify(model);
			if(flag){
				returnMsg.setSuccessMessage("修改成功");
			}
		}catch(BusinessException be){
			returnMsg.setFailMessage(be.getMessage(), true);
		}
		return returnMsg;
	}
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description: 查询 其他保险公司 产品信息
	*/
	public IInsRncDfnModel getInsRncDfn(IInsRncDfnModel model) {
		IInsRncDfnModel insmodel = InsRncDfnDomain.getInsRncDfn(model);
		return insmodel;
	}
	/** 
	* 
	* @param model
	* @return String
	* @description: 校验险种代码是否存在
	*/
	public String checkRiskCode(IInsRncDfnVOModel model) {
		String returnInfo="";
		returnInfo = returnInfo+InsRncDfnDomain.checkRiskCode(model);
		return returnInfo;
	}
	
	/** 
	* 
	* @param model
	* @return 
	* @description:校验险种代码是否存在（修改产品信息时）
	*/
	public String checkRiskCodeUnique(IInsRncDfnVOModel model) {
		String returnInfo="";
		returnInfo = returnInfo+InsRncDfnDomain.checkRiskCodeUnique(model);
		return returnInfo;
	}
	/**
	 * 修改状态
	 * sunhao
	 */
	@Override
	public ReturnMsg updateStatus(IInsRncDfnModel model) {
		ReturnMsg msg = new ReturnMsg();
		//zddxiu
		String str="";
		if("0".equals(model.getStatus())) {
			str="注销";
		}else {
			str="复效";
		}
		Integer flag = InsRncDfnDomain.updateStatus(model);
		try {
			if (flag == 1) {
				msg.setSuccessMessage(str+"成功");
			}
		} catch (Exception e) {
			msg.setFailMessage(e.getMessage());
		}
		
		return msg;
	}
	@Override
	public ReturnMsg queryCompanyTree() {
		ReturnMsg msg = new ReturnMsg();
		List<ITree> list = InsRncDfnDomain.queryCompanyTree();
		msg.setDataList(TransHelper.list2MapList(list));
		return msg;
	}
	@Override
	public ReturnMsg queryclassCode(IInsRncDfnModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IInsRncDfnModel> relist = InsRncDfnDomain.queryClassCode(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	@Override
	public ReturnMsg queryriskCode(IInsRncDfnModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IInsRncDfnModel> relist = InsRncDfnDomain.queryRiskCode(model);
		String poundage = InsRncDfnDomain.queryPoundage(model);
		for (int i = 0; i < relist.size(); i++) {
			relist.get(i).setPoundage(poundage);
		}
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	@Override
	public ReturnMsg querytype_code(IInsRncDfnModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IInsRncDfnModel> relist = InsRncDfnDomain.queryTypeCode(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	/**
	 * @param 传入IInsRncDfnVOModel model
	 * @return 返回一个List<IInsRncDfnModel>对象
	 * @description 导出信息
	 * by zdd02 20190617
	 */
	@Override
	public List<IInsRncDfnModel> exportInfo(IInsRncDfnModel model) {
		List<IInsRncDfnModel> relist = InsRncDfnDomain.exportInfo(model);
		return relist;
	}
	/**
	 * by zdd02 20190617
	 * @param branch_name
	 * @return
	 */
	@Override
	public String selectIsOrNobranchname(IInsRncDfnModel model) {
		return InsRncDfnDomain.selectIsOrNobranchname(model);
		
	}
	//by zdd02 20190617
	@Override
	public String insRncDfnAddzdd(IInsRncDfnModel model) {
		String ms="";
		   try{
				boolean flag=InsRncDfnDomain.insRncDfnAddzdd(model);
				if(flag){
					ms=model.getBranch_id()+">>添加成功";
				}
			}catch(BusinessException be){
				return model.getBranch_id()+">>数据异常，请重新操作！";
			}
		return ms;
	}
	//by zdd02  20190617
	@Override
	public IInsRncDfnModel selectCheckInsRncDfnModel(IInsRncDfnModel model) {
		return InsRncDfnDomain.selectCheckInsRncDfnModel(model);
	}
	
	//by zdd02 20190617
		@Override
		public String checkLimitsBranchid(IInsRncDfnModel model) {
			String ms="1";
			IInsRncDfnModel m=InsRncDfnDomain.checkLimitsBranchid(model);
			
			if(m==null) {
				ms=model.getBranch_id()+">>无权限添加该保险公司信息！";
			}
			return ms;
		}
		@Override
		public List<IInsRncDfnModel> getsysemlist(String branch_id) {
			// TODO Auto-generated method stub
			return InsRncDfnDomain.getsysemlist(branch_id);
		}
		@Override
		public String isPORL(IInsRncDfnModel model) {
			// TODO Auto-generated method stub
			return InsRncDfnDomain.isPORL(model);//zdd0724
		}
		@Override
		public void insRncDfnUpdate(IInsRncDfnModel model) {
			// TODO Auto-generated method stub
			InsRncDfnDomain.insRncDfnUpdate(model);
		}
	
}
