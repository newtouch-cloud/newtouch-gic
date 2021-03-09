package com.ca.cacore.mass.dao;

import java.util.List;

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

public interface ILibraryDao {
	public List<CommonStatusModel> queryCommonStatus();
	public List<GenderModel> queryGender();
	public List<ApprovalStatusModel> queryApprovalStatus();
	public List<ContractTypeModel> queryContractType();
	public List<CertiTypeModel> queryCertiType();
	public List<DegreeTypeModel> queryDegreeType();
	public List<NationModel> queryNation();
	public List<EducationModel> queryEducation();
	public List<PoliticalModel> queryPolitical();
	public List<MaritalModel> queryMarital();
	public List<NationalityModel> queryNationality();
	public List<HealthModel> queryHealth();
	public List<JobTypeModel> queryJobType();
	public List queryPayMode();
	public List<InvoiceType> queryInvoice();
}
