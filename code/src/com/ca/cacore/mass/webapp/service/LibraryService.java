package com.ca.cacore.mass.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.ApprovalStatusModel;
import com.ca.cacore.manage.model.bo.CertiTypeModel;
import com.ca.cacore.manage.model.bo.CommonStatusModel;
import com.ca.cacore.manage.model.bo.ContractTypeModel;
import com.ca.cacore.manage.model.bo.DegreeTypeModel;
import com.ca.cacore.manage.model.bo.EducationModel;
import com.ca.cacore.manage.model.bo.GenderModel;
import com.ca.cacore.manage.model.bo.HealthModel;
import com.ca.cacore.manage.model.bo.JobTypeModel;
import com.ca.cacore.manage.model.bo.MaritalModel;
import com.ca.cacore.manage.model.bo.NationModel;
import com.ca.cacore.manage.model.bo.NationalityModel;
import com.ca.cacore.manage.model.bo.PoliticalModel;
import com.ca.cacore.mass.domain.ILibraryDomain;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
* @company:  newtouch
* @since:    2013年11月16日 下午2:56:07   
* @author    GCH
* @description:数据字典
*/
@Service
public class LibraryService implements ILibraryService{

	@Autowired private ILibraryDomain libraryDomain;
	
	/** 
	* @return    ReturnMsg对象
	* @description:通用状态字典信息插叙服务
	*/
	@Override
	public ReturnMsg queryCommonStatus() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<CommonStatusModel> libList=libraryDomain.queryCommonStatus();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}
	
	
	/** 
	* @return    ReturnMsg对象
	* @description:性别字典信息查询服务
	*/
	@Override
	public ReturnMsg queryGender() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<GenderModel> libList=libraryDomain.queryGender();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}

	/** 
	* @return    ReturnMsg对象
	* @description:审批状态字典信息查询服务
	*/
	@Override
	public ReturnMsg queryApprovalStatus() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<ApprovalStatusModel> libList=libraryDomain.queryApprovalStatus();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}

	
	/** 
	* @return    ReturnMsg对象
	* @description:查询合同类型字典信息
	*/
	@Override
	public ReturnMsg queryContractType() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<ContractTypeModel> libList=libraryDomain.queryContractType();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}
	
	/** 
	* @return    ReturnMsg对象
	* @description:查询证件类型字典信息
	*/
	@Override
	public ReturnMsg queryCertiType() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<CertiTypeModel> libList=libraryDomain.queryCertiType();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}

	
	/** 
	* @return    ReturnMsg对象
	* @description:查询学位类型字典信息
	*/
	@Override
	public ReturnMsg queryDegreeType() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<DegreeTypeModel> libList=libraryDomain.queryDegreeType();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}

	/**    
	* @return    ReturnMsg对象
	* @description:查询民族字典信息
	*/
	@Override
	public ReturnMsg queryNation() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<NationModel> libList=libraryDomain.queryNation();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}

	
	/**      
	* @return    ReturnMsg对象
	* @description:查询教育程度字典信息
	*/
	@Override
	public ReturnMsg queryEducation() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<EducationModel> libList=libraryDomain.queryEducation();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}

	
	/**     
	* @return    ReturnMsg对象
	* @description:查询政治面貌字典信息
	*/
	@Override
	public ReturnMsg queryPolitical() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<PoliticalModel> libList=libraryDomain.queryPolitical();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}

	
	/**     
	* @return     ReturnMsg对象
	* @description:查询婚姻状况字典信息
	*/
	@Override
	public ReturnMsg queryMarital() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<MaritalModel> libList=libraryDomain.queryMarital();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}

	
	/**    
	* @return    ReturnMsg对象
	* @description:查询国籍字典信息
	*/
	@Override
	public ReturnMsg queryNationality() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<NationalityModel> libList=libraryDomain.queryNationality();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}


	/**     
	* @return     ReturnMsg对象
	* @description:查询健康状况字典信息
	*/
	@Override
	public ReturnMsg queryHealth() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<HealthModel> libList=libraryDomain.queryHealth();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}

	
	/**     
	* @return    ReturnMsg对象
	* @description:查询职业类别字典信息
	*/
	@Override
	public ReturnMsg queryJobType() {
		ReturnMsg returnMsg=new ReturnMsg();
		List<JobTypeModel> libList=libraryDomain.queryJobType();
		returnMsg.setDataList(TransHelper.list2MapList(libList));
		return returnMsg;
	}


	/**      
	* @return     ReturnMsg对象
	* @description:查询付款方式字典信息
	*/
	@Override
	public ReturnMsg queryPayMode() {
		ReturnMsg returnMsg=new ReturnMsg();
		return returnMsg;
	}
	
}
