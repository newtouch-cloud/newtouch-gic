package com.ca.cacore.msss.webapp.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.domain.IPkgLifeDomain;
import com.ca.cacore.msss.model.vo.IPkgLifeVOModel;
import com.ca.cacore.msss.model.vo.PkgLifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

/**
 * 
 * @author  Wang_ds
 * @since 2013-11-10
 * @description 产品信息Service层
 */
@Service
public class PkgLifeService extends ServerBase implements IPkgLifeService  {

	@Autowired
	private IPkgLifeDomain PkgLifeDomain;


	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询出所有产品组合信息，或根据条件查询
	 */
	public ReturnMsg queryPkgLifeList(IPkgLifeVOModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IPkgLifeVOModel> relist = PkgLifeDomain.queryPkgLifeList(model);
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个ReturnMsg对象
	 * @description 查询单个产品组合信息
	 */
	public ReturnMsg getPkgLifeVO(IPkgLifeVOModel model) {
		ReturnMsg msg = new ReturnMsg();
		IPkgLifeVOModel VOModel = PkgLifeDomain.getPkgLifeVO(model);
		List<IPkgLifeVOModel> list = new ArrayList<IPkgLifeVOModel>();
		list.add(VOModel);
		msg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		return msg;
	}
	
	
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 修改出产品组合信息
	 */
	 public ReturnMsg modifyPkgLife(IPkgLifeVOModel model,IUserModel user) throws BusinessException{
	 ReturnMsg reutn = new ReturnMsg();
	 try{
		 PkgLifeDomain.modifyPkgLife(model, user);
		 reutn.setSuccessMessage("修改成功");
		
	 }catch(BusinessException e){
		 reutn.setFailMessage(e.getMessage(), true);
		}
	 
	 return reutn;
	 }
	 
	 
 	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 添加产品组合信息
	 */
	@Override
	public ReturnMsg addPkgLife(IPkgLifeVOModel model, IUserModel user) throws BusinessException{
		ReturnMsg reutn = new ReturnMsg();
		try{
			
			ValidateHelper.IsNullOrEmptyThrowException(model.getPkg_id(), "产品组合代码不可为空，请检查。");//产品组合代码为空检验
			ValidateHelper.IsNullOrEmptyThrowException(model.getPkg_name(), "产品组合名称不可为空，请检查。");//产品组合名称为空检验
			
			PkgLifeDomain.addPkgLife(model,user);
			reutn.setSuccessMessage("保存成功");
		 }catch(BusinessException e){
			reutn.setFailMessage(e.getMessage(), true);
		 }
		 return reutn;
	}
	/**
	 * @param 传入String pkg_id
	 * @param 传入String pkg_name
	 * @return 返回一个ModelAndView对象
	 * @description 验证产品组合代码和产品组合名称是否唯一
	 */
	@Override
	public Boolean checkPkgIdAndNameUnique(String pkg_id, String pkg_name) {
		IPkgLifeVOModel model = new PkgLifeVOModel();
		model.setPkg_id(pkg_id);
        model.setPkg_name(pkg_name);
		return PkgLifeDomain.checkPkgIdAndNameUnique(model);
	}
	@Override// [{key:1,value:1},{key:2,value:2}]
	public String queryInsBranch() { 
//		List<InsBranchModel> list=insBranchDomain.queryInsBranch();
		JSONArray array=new JSONArray();
//		for(InsBranchModel model : list){
//			JSONObject obj=new JSONObject();
//			obj.put("key", model.getInsBranch_id());
//			obj.put("value", model.getInsBranch_name());
//			array.add(obj);
//		}
		return array.toString();
	}

}
