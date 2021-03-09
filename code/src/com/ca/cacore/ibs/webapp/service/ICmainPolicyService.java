package com.ca.cacore.ibs.webapp.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ca.cacore.manage.model.bo.IUserModel;

/**
 * 保单导入
* @since:    2014年11月21日   
* @author    SUNXM
* @description:
 */
public interface ICmainPolicyService {
	
	/**
	 * 保单导入
	* @param uploadFileName
	* @param excelPath
	* @param user
	* @return String
	* @description:
	 */
	public String importInsurace(String uploadFileName, String excelPath,
			IUserModel user);

	String importInsurace2(MultipartFile file, IUserModel user) throws IOException;

	String importInsuracePerson(MultipartFile file, IUserModel user) throws IOException;

	public String importBusiness(MultipartFile file, IUserModel user) throws Exception;

	public String importBusinessPreson(MultipartFile file, IUserModel user) throws Exception;

	public String importInter(MultipartFile file, IUserModel user) throws Exception;

	public String importInterPerson(MultipartFile file, IUserModel user) throws Exception;


}
