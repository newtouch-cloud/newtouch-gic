package com.ca.cacore.mass.domain;

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
import com.ca.cacore.manage.model.bo.InvoiceType;
import com.ca.cacore.manage.model.bo.JobTypeModel;
import com.ca.cacore.manage.model.bo.MaritalModel;
import com.ca.cacore.manage.model.bo.NationModel;
import com.ca.cacore.manage.model.bo.NationalityModel;
import com.ca.cacore.manage.model.bo.PoliticalModel;
import com.ca.cacore.mass.dao.ILibraryDao;

/**
* @since:    2013年11月16日 下午2:55:42   
* @author    GCH
* @description: 数据字典
*/
@Service
public class LibraryDomain implements ILibraryDomain{

	@Autowired private ILibraryDao libraryDao;
	
	/**
	* @since:    2013年11月16日 下午12:49:35   
	* @description:通用状态字典信息
	*/
	@Override
	public List<CommonStatusModel> queryCommonStatus() {
		return libraryDao.queryCommonStatus();
	}
	
	/**  
	* @return    List<GenderModel>
	* @description:查询性别数据字典
	*/
	@Override
	public List<GenderModel> queryGender() {
		return libraryDao.queryGender();
	}
	
	/**  
	* @return    List<GenderModel>
	* @description:查询发票类型数据字典
	*/
	@Override
	public List<InvoiceType> queryInvoice(){
		return libraryDao.queryInvoice();
	}
	/** 
	* @return    List<ApprovalStatusModel>
	* @description:查询审批状态字典信息
	*/
	@Override
	public List<ApprovalStatusModel> queryApprovalStatus() {
		return libraryDao.queryApprovalStatus();
	}

	
	/** 
	* @return     List<ContractTypeModel>
	* @description:查询合同类型字典信息
	*/
	@Override
	public List<ContractTypeModel> queryContractType() {
		return libraryDao.queryContractType();
	}

	
	/** 
	* @return     List<CertiTypeModel>
	* @description:查询证件类型字典信息
	*/
	@Override
	public List<CertiTypeModel> queryCertiType() {
		return libraryDao.queryCertiType();
	}

	
	/** 
	* @return     List<DegreeTypeModel>
	* @description:查询学位类型字典信息
	*/
	@Override
	public List<DegreeTypeModel> queryDegreeType() {
		return libraryDao.queryDegreeType();
	}

	
	/**    
	* @return    List<NationModel>
	* @description:查询民族字典信息
	*/
	@Override
	public List<NationModel> queryNation() {
		return libraryDao.queryNation();
	}

	
	/**     
	* @return    List<EducationModel>
	* @description:查询教育程度字典信息
	*/
	@Override
	public List<EducationModel> queryEducation() {
		return libraryDao.queryEducation();
	}

	
	/**    
	* @return    List<PoliticalModel>
	* @description:查询政治面貌字典信息
	*/
	@Override
	public List<PoliticalModel> queryPolitical() {
		return libraryDao.queryPolitical();
	}

	
	/**     
	* @return    List<MaritalModel>
	* @description:查询婚姻状况字典信息
	*/
	@Override
	public List<MaritalModel> queryMarital() { 
		return libraryDao.queryMarital();
	}

	
	/**     
	* @return     List<NationalityModel>
	* @description:查询国籍字典信息
	*/
	@Override
	public List<NationalityModel> queryNationality() {
		return libraryDao.queryNationality();
	}

	
	/**    
	* @return     List<HealthModel>
	* @description:查询健康状况字典信息
	*/
	@Override
	public List<HealthModel> queryHealth() {
		return libraryDao.queryHealth();
	}

	
	/**    
	* @return    List<JobTypeModel> 
	* @description:查询职业类别字典信息
	*/
	@Override
	public List<JobTypeModel> queryJobType() {
		return libraryDao.queryJobType();
	}

	/**     
	* @return    List<PayModeModel>
	* @description:查询付款方式字典信息
	*/
	@Override
	public List queryPayMode() {
		return libraryDao.queryPayMode();
	}

}
