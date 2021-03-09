package com.newtouch.organization.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.organization.dao.IAgencyDao;
import com.newtouch.organization.model.vo.IAgencyModel;
import com.newtouch.organization.model.vo.impl.AgencyModel;

@Component
public class AgencyDao extends BaseDao implements IAgencyDao {

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IAgencyModel> selectAgency(IAgencyModel model) {
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("agency.queryAgency_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		List<IAgencyModel> queryForList = this.getSqlMapClientTemplate().queryForList("agency.selectAgency", model);
		return queryForList;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-维护查询
	 */
	@Override
	public IAgencyModel QueryAgencyInfo(IAgencyModel model) {
		return (IAgencyModel) this.getSqlMapClientTemplate().queryForObject("agency.queryUpdateAgency", model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-维护-保存
	 */
	@Override
	public Integer insertAgency(IAgencyModel model) {
		Integer i = (Integer) this.getSqlMapClientTemplate().insert("agency.insertAgency", model);
		return i;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-维护
	 */
	public int updateAgency(IAgencyModel model) {
		this.getSqlMapClientTemplate().update("agency.updateAgency", model);
		this.getSqlMapClientTemplate().update("agency.updateAgency1", model);
		int i = this.getSqlMapClientTemplate().update("agency.updateAgency2", model);
		return i;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-根据机构树查询协议号
	 */
	@Override
	public List<IAgencyModel> queryAgreement(IAgencyModel model) {
		return this.getSqlMapClientTemplate().queryForList("agency.queryAgreement", model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-根据协议号查询时间
	 */
	@Override
	public Integer queryAgreementNo(IAgencyModel model) {
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("agency.queryAgreementNo", model);
		return count;

	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-明细
	 */
	@Override
	public IAgencyModel selectUpdateAgency(IAgencyModel model) {
		return (IAgencyModel) this.getSqlMapClientTemplate().queryForObject("agency.selectUpdateAgency", model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-通过中介表查询协议号
	 */
	@Override
	public List<Map<String, Object>> queryRelationAn(AgencyModel model) {
		return this.getSqlMapClientTemplate().queryForList("agency.queryRelationAn", model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-查重删除
	 */
	@Override
	public Integer deleteAgency(List<String> list, String repair_coding) {
		Map<String, Object> map_param = new HashMap<String, Object>();
		map_param.put("list", list);
		map_param.put("agency_no", repair_coding);
		return this.getSqlMapClientTemplate().delete("agency.deleteAgency", map_param);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-导入
	 */
	@Override
	public Integer importInfo(Map<String, Object> map) {
		if (map.get("repair_coding").equals("")) {
			return (Integer) this.getSqlMapClientTemplate().insert("agency.importInfoNull", map);
		} else {
			return (Integer) this.getSqlMapClientTemplate().insert("agency.importInfo", map);
		}

	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-统一社会信用编码保存
	 */
	@Override
	public Integer updateSocialCode(IAgencyModel model) {
		return (Integer) this.getSqlMapClientTemplate().update("agency.updateSocialCode", model);
	}

	@Override
	public Integer updateAgencyNO(String repair_coding_mdf, String repair_coding) {
		Map<String, Object> map_param = new HashMap<String, Object>();
		map_param.put("repair_coding_mdf", repair_coding_mdf);
		map_param.put("repair_coding", repair_coding);
		return (Integer) this.getSqlMapClientTemplate().update("agency.updateAgencyNO", map_param);
	}

	@Override
	public Integer deleteAgencyAll(String agency_no) {
		return this.getSqlMapClientTemplate().delete("agency.deleteAgencyAll", agency_no);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:中介管理-查询excel数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryExcel() {
		return this.getSqlMapClientTemplate().queryForList("agency.queryExcel");
	}

	// 删除表中重复数据
	@Override
	public Integer deleteExcel(List<String> remove_list) {
		// Map<String,Object> map_param=new HashMap<String,Object>();
		// map_param.put("list", re_list);
		// map_param.put("repair_coding", repair_coding);
		return this.getSqlMapClientTemplate().delete("agency.deleteExcel", remove_list);
	}

	// jiaoyan
	@Override
	public List<String> queryExcel(String repair_coding) {
		return this.getSqlMapClientTemplate().queryForList("agency.queryExcel", repair_coding);
	}

	@Override
	public Integer deleteAreement(String agency_no) {
		int a = this.getSqlMapClientTemplate().delete("agency.deleteAreement", agency_no);
		return a;
	}

	@Override
	public Integer queryrepair_coding(IAgencyModel model) {
		Integer i = (Integer) this.getSqlMapClientTemplate().queryForObject("agency.queryrepair_coding", model);
		return i;
	}

}
