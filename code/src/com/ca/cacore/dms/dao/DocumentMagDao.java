package com.ca.cacore.dms.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.dms.model.vo.IDocumentMagVOModel;
import com.newtouch.core.daobase.BaseDao;
import com.ca.cacore.dms.model.bo.IDocumentMagModel;
/**
 * 
 * @author zdd03
 *
 */
@Component
public class DocumentMagDao extends BaseDao implements IDocumentMagDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<IDocumentMagModel> Querydocmanagent(IDocumentMagModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("DocumentMag.querydocmanagent_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		return (List<IDocumentMagModel>)this.getSqlMapClientTemplate().queryForList("DocumentMag.querydocmanagent", model);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IDocumentMagModel> exportdocmanagent(IDocumentMagModel model) {
		
		return (List<IDocumentMagModel>)this.getSqlMapClientTemplate().queryForList("DocumentMag.exportdocmanagent", model);
	}

	@Override
	public int checkpermissionYON(IDocumentMagModel model) {
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("DocumentMag.checkpermissionYON", model);
	}

	@Override
	public IDocumentMagModel checkapjbYON(IDocumentMagModel model) {
		
		return (IDocumentMagModel)this.getSqlMapClientTemplate().queryForObject("DocumentMag.checkapjbYON", model);
	}

	@Override
	public IDocumentMagModel checkrpjbYON(IDocumentMagModel model) {
		return (IDocumentMagModel)this.getSqlMapClientTemplate().queryForObject("DocumentMag.checkrpjbYON", model);
	}

	@Override
	public String importdocmanagent(IDocumentMagModel model) {
		       
		this.getSqlMapClientTemplate().insert("DocumentMag.importdocmanagent", model);
		return "数据导入成功";
	}

	@Override
	public IDocumentMagModel documentexistYON(IDocumentMagModel model) {
		return (IDocumentMagModel)this.getSqlMapClientTemplate().queryForObject("DocumentMag.documentexistYON", model);
	}

	@Override
	public void insertsysEnumdanzheng(IDocumentMagModel model) {
		int coun=(Integer)this.getSqlMapClientTemplate().queryForObject("DocumentMag.getIsOrNodanzheng", model);
		if(coun==0) {
		 this.getSqlMapClientTemplate().insert("DocumentMag.insertsysEnumdanzheng", model);  
		}
		
	}

}
