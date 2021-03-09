package com.newtouch.organization.webapp.service;

import java.util.List;
import java.util.Map;

import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.organization.model.vo.IAgencyModel;
import com.newtouch.organization.model.vo.impl.AgencyModel;

public interface IAgencyService {

	// 中介管理查询
	public ReturnMsg selectAgency(IAgencyModel model);

	// 中介维护查询
	public ReturnMsg QueryAgencyInfo(IAgencyModel model);

	// 根据机构树查询协议号
	public ReturnMsg queryAgreement(IAgencyModel model);

	// 根据协议号
	public Integer queryAgreementNo(IAgencyModel model);

	// 根据查询推荐维修码
	public Integer queryrepair_coding(IAgencyModel model);

	// 明细
	public ReturnMsg selectUpdateAgency(IAgencyModel model);

	// 保存
	public ReturnMsg insertAgency(IAgencyModel model);

	// 查询协议号
	public List<Map<String, Object>> queryRelationAn(AgencyModel model);

	// 删除
	public Integer deleteAgency(List<String> list, String repair_coding);

	// 校验吧
	public ReturnMsg makecheckInfo(Map<String, List<Object>> excelMap);

	// 统一社会信用编码保存
	public ReturnMsg updateSocialCode(AgencyModel model);
	// 根据维护代理机构

	public Integer updateAgencyNO(String repair_coding_mdf, String repair_coding);

	public int deleteAgencyAll(String agency_no);

	public int deleteAreement(String agency_no);

}
