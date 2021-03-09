package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IYYPersonReportDao;
import com.ca.cacore.rsss.model.vo.IYYPersonReportVOModel;
@Service
public class YYPersonReportDomain implements IYYPersonReportDomain {
	
@Autowired private  IYYPersonReportDao iyypersonreportdao;
	
	
	/**
	 * 
	* 
	* @param model
	* @return List<IYYPersonReportVOModel>
	* @description:查询营运人员效力情况表
	 */
	@Override
	public List<IYYPersonReportVOModel> queryYYPersonReport(IYYPersonReportVOModel model) {
        	return iyypersonreportdao.queryYYPersonReport(model);
	}

	/**
	 * 
	* 
	* @param model
	* @return List<IYYPersonReportVOModel>
	* @description:导出营运人员效力情况表
	 */
	@Override
	public List<IYYPersonReportVOModel> exportYYPersonReport(
			IYYPersonReportVOModel model) {
		return iyypersonreportdao.exportYYPersonReport(model);
	}
}
