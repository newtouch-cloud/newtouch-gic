package com.ca.cacore.ams.webapp.service;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

import com.ca.cacore.manage.model.bo.IUserModel;

public interface IStatisticalDataService {

	public void statisticalDataYear(ServletOutputStream ops,IUserModel user) throws IOException;
	
	public void statisticalDataMonth(ServletOutputStream ops,IUserModel user) throws IOException;
	
}
