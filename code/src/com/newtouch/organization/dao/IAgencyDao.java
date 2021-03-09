package com.newtouch.organization.dao;

import java.util.List;
import java.util.Map;

import com.newtouch.organization.model.vo.IAgencyModel;
import com.newtouch.organization.model.vo.impl.AgencyModel;

public interface IAgencyDao {
	// 中介管理查询
	public List<IAgencyModel> selectAgency(IAgencyModel model);

	// 中介管理维护
	public int updateAgency(IAgencyModel model);

	// 中介管理维护查询
	public IAgencyModel QueryAgencyInfo(IAgencyModel model);

	// 根据机构树查询协议号
	public List<IAgencyModel> queryAgreement(IAgencyModel model);

	// 根据协议号查询时间
	public Integer queryAgreementNo(IAgencyModel model);

	public Integer queryrepair_coding(IAgencyModel model);

	// 明细
	public IAgencyModel selectUpdateAgency(IAgencyModel model);

	// 保存
	public Integer insertAgency(IAgencyModel model);

	// 通过中介表查询协议号
	public List<Map<String, Object>> queryRelationAn(AgencyModel model);

	// 删除重复数据
	public Integer deleteExcel(List<String> remove_list);

	public Integer deleteAgency(List<String> list, String repair_coding);

	// 失效
	// public Integer updateState(AgencyModel model);

	// 导入
	public Integer importInfo(Map<String, Object> map);

	// 统一社会信用编码保存
	public Integer updateSocialCode(IAgencyModel model);

	public Integer updateAgencyNO(String repair_coding_mdf, String repair_coding);

	public Integer deleteAgencyAll(String agency_no);

	public Integer deleteAreement(String agency_no);

	// 查询excel数据
	public List<String> queryExcel();

	// jiaoyan
	public List<String> queryExcel(String repair_coding);
}
