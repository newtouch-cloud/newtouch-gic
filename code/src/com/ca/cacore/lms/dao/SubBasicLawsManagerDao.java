package com.ca.cacore.lms.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.lms.model.bo.IBasicLawsModel;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;
import com.newtouch.core.daobase.BaseDao;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:子基本法维护管理持久层
*/
@Component
public class SubBasicLawsManagerDao extends BaseDao implements ISubBasicLawsManagerDao {
	/** 
	* 查询所有子基本法的信息
	* @param model
	* @return List<IBasicLawsVOModel>
	* @description:
	*/
	@SuppressWarnings("unchecked")
	public List<IBasicLawsVOModel> getAllSubBasicLawsInfo(IBasicLawsVOModel model){
		Integer count=(Integer) this.getSqlMapClientTemplate().queryForObject("subBasicLawsManager.getAllSubBasicLaws_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return this.getSqlMapClientTemplate().queryForList("subBasicLawsManager.getAllBasicLawsInfo",model);
	}
	
	/** 
	* 新增子基本法信息
	* @param model
	* @return boolean
	* @description:
	*/
	public boolean addSubBasicLaws(IBasicLawsVOModel model){
		this.getSqlMapClientTemplate().insert("subBasicLawsManager.addSubBasicLaws",model);
		return true;
	}
	
	/** 
	* 查询子基本法的详细信息
	* @param model
	* @return IBasicLawsVOModel
	* @description:
	*/
	public IBasicLawsVOModel getSubBasicLawView(IBasicLawsVOModel model){
		return (IBasicLawsVOModel) this.getSqlMapClientTemplate().queryForObject("subBasicLawsManager.getSubBasicLawView",model);
	}
	
	/** 
	* 子基本法信息修改
	* @param model
	* @return boolean
	* @description:
	*/
	public boolean modSubBasicLaws(IBasicLawsVOModel model){
		this.getSqlMapClientTemplate().update("subBasicLawsManager.modSubBasicLaws",model);
		return true;
	}
	
	/** 
	* 子子基本法信息删除
	* @param serno
	* @return boolean
	* @description:
	*/
	public boolean delSubBasicLaws(String serno){
		this.getSqlMapClientTemplate().delete("subBasicLawsManager.delSubBasicLaws",serno);
		return true;
	}
	/** 
	* @param basiclaw_no
	* @return IBasicLawsModel
	* @description:查询子基本法的详细信息
	*/
	public IBasicLawsModel getLawsInfo(String basiclaw_no){
		return (IBasicLawsModel) this.getSqlMapClientTemplate().queryForObject("subBasicLawsManager.getLawsInfo",basiclaw_no);
	}
	
}
