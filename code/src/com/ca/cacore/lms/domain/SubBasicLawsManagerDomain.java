package com.ca.cacore.lms.domain;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.common.GetFormatId;
import com.ca.cacore.lms.dao.ISubBasicLawsManagerDao;
import com.ca.cacore.lms.model.bo.IBasicLawsModel;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;
/**
* @since:    2014年3月3日   
* @author    ss
* @description:
*/
@Service
public class SubBasicLawsManagerDomain implements ISubBasicLawsManagerDomain {
	@Autowired private ISubBasicLawsManagerDao subBasicLawsManagerDao;
	@Autowired private GetFormatId getFormatId;
	/** 
	* 获得所有基本法的信息
	* @param model
	* @return List<IBasicLawsVOModel>
	* @description:
	*/
	public List<IBasicLawsVOModel> getAllSubBasicLawsInfo(IBasicLawsVOModel model) {
		
		//model.setData_flag("1");
		return subBasicLawsManagerDao.getAllSubBasicLawsInfo(model);
	}

	/** 
	* 新增子基本法信息
	* @param model
	* @param user
	* @return boolean
	* @description:
	*/
	public boolean addSubBasicLaws(IBasicLawsVOModel model,IUserModel user){
		//生成子基本法代码
		String impmeans_no = getFormatId.createSalesId(5, "basiclaw_no");
		model.setImpmeans_no(impmeans_no);
		model.setCrt_user(user.getEmp_id());
		model.setMdf_user(user.getEmp_id());
		model.setData_flag("Y");  //咱设置生效
		return subBasicLawsManagerDao.addSubBasicLaws(model);
	}
	
	/** 
	* 查询基本法明细
	* @param model
	* @return IBasicLawsVOModel
	* @description:
	*/
	public IBasicLawsVOModel getSubBasiclawView(IBasicLawsVOModel model){
		return subBasicLawsManagerDao.getSubBasicLawView(model);
	}
	
	/** 
	* 修改基本法信息
	* @param model
	* @param user
	* @return boolean
	* @description:
	*/
	public boolean modSubBasicLaws(IBasicLawsVOModel model,IUserModel user){
		return subBasicLawsManagerDao.modSubBasicLaws(model);
	}
	
	/** 
	* 删除基本法
	* @param serno
	* @return boolean
	* @description:
	*/
	public boolean delSubBasicLaws(String serno){
		return subBasicLawsManagerDao.delSubBasicLaws(serno);
	}
	/** 
	* 
	* @param model
	* @return 
	* @description:根据基本法代码查询基本法信息
	*/
	public String getLawsInfo(String basiclaw_no){
		 String info = "";
		 IBasicLawsModel bm = subBasicLawsManagerDao.getLawsInfo(basiclaw_no);
		 if(bm != null){
			 info = "{isSuccess:'true',start_date:'"+bm.getStart_date()+"',end_date:'"+bm.getEnd_date()+"'}";
		 }else{
			 info = "{isSuccess:'false'}";
		 }
		 List<String> list=new ArrayList<String>();
		 list.add(info);
		 return JSONArray.fromObject(list).toString();
	}
}