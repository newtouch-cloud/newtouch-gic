package com.ca.cacore.mass.dao;

import java.util.List;

import org.springframework.stereotype.Component;

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
import com.newtouch.core.daobase.BaseDao;

/**
* @since:    2013年11月16日 下午2:04:03   
* @author    GCH
*/

@SuppressWarnings("unchecked")
@Component
public class LibraryDao extends BaseDao implements ILibraryDao{

	/**
	* @return    List<CommonStatusModel>
	* @description:查询通用状态数据字典
	*/
	@Override
	public List<CommonStatusModel> queryCommonStatus() {
		CommonStatusModel model=new CommonStatusModel();
		List<CommonStatusModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryCommonStatus", model);
		return libList;
	}

	/** 
	* @return    List<GenderModel>
	* @description:查询性别数据字典
	*/
	@Override
	public List<GenderModel> queryGender() {
		GenderModel model=new GenderModel();
		List<GenderModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryGender", model);
		return libList;
	}

	
	
	/** 
	* @return    List<ApprovalStatusModel>
	* @description:查询审批状态数据字典
	*/
	@Override
	public List<ApprovalStatusModel> queryApprovalStatus() {
		ApprovalStatusModel model=new ApprovalStatusModel();
		List<ApprovalStatusModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryApprovalStatus", model);
		return libList;
	}

	
	/** 
	* @return    List<ContractTypeModel>
	* @description:查询合同类型字典信息
	*/
	@Override
	public List<ContractTypeModel> queryContractType() {
		ContractTypeModel model=new ContractTypeModel();
		List<ContractTypeModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryContractType", model);
		return libList;
	}

	
	/** 
	* @return    List<CertiTypeModel>
	* @description:查询证件类型字典信息
	*/
	@Override
	public List<CertiTypeModel> queryCertiType() {
		CertiTypeModel model=new CertiTypeModel();
		List<CertiTypeModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryCertiType", model);
		return libList;
	}

	
	/**  
	* @return     List<DegreeTypeModel>
	* @description:查询学位类型字典信息
	*/
	@Override
	public List<DegreeTypeModel> queryDegreeType() {
		DegreeTypeModel model=new DegreeTypeModel();
		List<DegreeTypeModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryDegreeType", model);
		return libList;
	}

	
	/**    
	* @return    List<NationModel>
	* @description:查询民族字典信息
	*/
	@Override
	public List<NationModel> queryNation() {
		NationModel model=new NationModel();
		List<NationModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryNation", model);
		return libList;
	}

	
	/**   
	* @return    List<EducationModel>
	* @description:查询教育程度字典信息
	*/
	@Override
	public List<EducationModel> queryEducation() {
		EducationModel model=new EducationModel();
		List<EducationModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryEducation", model);
		return libList;
	}

	
	/**    
	* @return    List<PoliticalModel>
	* @description:查询政治面貌字典信息
	*/
	@Override
	public List<PoliticalModel> queryPolitical() {
		PoliticalModel model=new PoliticalModel();
		List<PoliticalModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryPolitical", model);
		return libList;
	}

	
	/**      
	* @return     List<MaritalModel>
	* @description:查询婚姻状况字典信息
	*/
	@Override
	public List<MaritalModel> queryMarital() {
		MaritalModel model=new MaritalModel();
		List<MaritalModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryMarital", model);
		return libList;
	}

	
	/**      
	* @return    List<NationalityModel> 
	* @description:查询国籍字典信息
	*/
	@Override
	public List<NationalityModel> queryNationality() {
		NationalityModel model = new NationalityModel();
		List<NationalityModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryNationality", model);
		return libList;
	}

	
	/**    
	* @return     List<HealthModel>
	* @description:查询健康状况字典信息
	*/
	@Override
	public List<HealthModel> queryHealth() {
		HealthModel model=new HealthModel();
		List<HealthModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryHealth", model);
		return libList;
	}

	
	/**     
	* @return     List<JobTypeModel>
	* @description:查询职业类型字典信息
	*/
	@Override
	public List<JobTypeModel> queryJobType() {
		JobTypeModel model = new JobTypeModel();
		List<JobTypeModel> libList=this.getSqlMapClientTemplate().queryForList("library.queryJobType", model);
		return libList;
	}

	
	/**    
	* @return      List<PayModeModel>
	* @description:查询付款方式字典信息
	*/
	@Override
	public List queryPayMode() {
		List libList=null;
		return libList;
	}

	@Override
	public List<InvoiceType> queryInvoice() {
		InvoiceType model=new InvoiceType();
		List<InvoiceType> libList=this.getSqlMapClientTemplate().queryForList("library.queryInvoice",model);
		return libList;
	}
	
}
