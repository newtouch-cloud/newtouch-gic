package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ClaimsStatusModel;

/**
* @since:    2014年2月10日   
* @author    wang_ds
* @description:
*/
public interface IClaimsStatusDao {

	/** 
	* 
	* @return List<ClaimsStatusModel>
	* @description:
	*/
	public List<ClaimsStatusModel> queryClaimsStatus();

}