package com.ca.cacore.mass.webapp.service;

import com.newtouch.core.returnmsg.ReturnMsg;

public interface ILibraryService {
	public ReturnMsg queryCommonStatus();
	public ReturnMsg queryGender();
	public ReturnMsg queryApprovalStatus();
	public ReturnMsg queryContractType();
	public ReturnMsg queryCertiType();
	public ReturnMsg queryDegreeType();
	public ReturnMsg queryNation();
	public ReturnMsg queryEducation();
	public ReturnMsg queryPolitical();
	public ReturnMsg queryMarital();
	public ReturnMsg queryNationality();
	public ReturnMsg queryHealth();
	public ReturnMsg queryJobType();
	public ReturnMsg queryPayMode();
}
