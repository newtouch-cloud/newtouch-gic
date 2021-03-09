package com.ca.cacore.msss.webapp.service;

import java.util.List;

import com.ca.cacore.msss.model.bo.IInsRncDfnModel;
import com.ca.cacore.msss.model.vo.IInsRncDfnVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IInsRncDfnService {

	/**
	 * @param 传入IInsRncDfnVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询出所有产品组合信息，或根据条件查询
	 */
	public ReturnMsg queryInsRncDfnList(IInsRncDfnModel model);
	public ReturnMsg insRncDfnAdd(IInsRncDfnModel model);
	public String checkRiskCode(IInsRncDfnVOModel model);
	public ReturnMsg insRncDfnModify(IInsRncDfnModel model); 
	public IInsRncDfnModel getInsRncDfn(IInsRncDfnModel model);
	public String checkRiskCodeUnique(IInsRncDfnVOModel model);
	public ReturnMsg updateStatus(IInsRncDfnModel model);
	public ReturnMsg queryCompanyTree();
	public ReturnMsg queryclassCode(IInsRncDfnModel model);
	public ReturnMsg queryriskCode(IInsRncDfnModel model);
	public ReturnMsg querytype_code(IInsRncDfnModel model);
	List<IInsRncDfnModel> exportInfo(IInsRncDfnModel model);//by zdd02 20190617
	String selectIsOrNobranchname(IInsRncDfnModel model);//by zdd02 20190617
	public String insRncDfnAddzdd(IInsRncDfnModel model);//by zdd02 20190617
	public IInsRncDfnModel selectCheckInsRncDfnModel(IInsRncDfnModel model);//by zdd02 20190617
	String checkLimitsBranchid(IInsRncDfnModel model);//by zdd02 20190617
	public List<IInsRncDfnModel> getsysemlist(String branch_id);//zddxiu
	public String isPORL(IInsRncDfnModel model);//zdd0724
	public void insRncDfnUpdate(IInsRncDfnModel model);//lyn 20190911
}